/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.ui.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressConstants;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.util.IJobConstants;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.sirius.ui.SiriusUIPlugin;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

public class RefreshDiagramsCommandHandler extends AbstractDiagramCommandHandler {

  /**
   * Eclipse's job that will call the refresh diagrams command.
   */
  public class RefreshDiagramsJob extends WorkspaceJob {

    private Session session;

    private Collection<DRepresentationDescriptor> representationsDescriptorsToRefresh;

    private Display display;

    private UICallBack uiCallback;

    public RefreshDiagramsJob(Collection<DRepresentationDescriptor> representations, Session session, Display display) {
      super(Messages.RefreshRepresentation_0);
      setProperty(IJobConstants.ALWAYS_LOG_STATUS, true);
      this.session = session;
      this.representationsDescriptorsToRefresh = representations;
      this.display = display;

      // Add job listener to return the state of Sirius preference.
      addJobChangeListener(new JobChangeAdapter() {

        Boolean currentValueOfSiriusPrefRefreshOnOpening;

        @Override
        public void running(IJobChangeEvent event) {
          // Activate the preference of Sirius: Open refresh on representation opening.
          if (SessionHelper.hasSpecificSettingRefreshOnRepresentationOpening(session)) {
            currentValueOfSiriusPrefRefreshOnOpening = session.getSiriusPreferences().isRefreshOnRepresentationOpening();
          }
          session.getSiriusPreferences().setRefreshOnRepresentationOpening(true);
          // Avoid dialog box to be displayed
          uiCallback = SiriusEditPlugin.getPlugin().getUiCallback();
          SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
        }

        /**
         * Always reset the preference of Sirius to the previous state.
         */
        @Override
        public void done(IJobChangeEvent event) {
          if (currentValueOfSiriusPrefRefreshOnOpening == null) {
            session.getSiriusPreferences().unsetRefreshOnRepresentationOpening();
          } else {
            session.getSiriusPreferences().setRefreshOnRepresentationOpening(currentValueOfSiriusPrefRefreshOnOpening.booleanValue());
          }
          SiriusEditPlugin.getPlugin().setUiCallback(uiCallback);
        }

      });
    }

    @Override
    public IStatus runInWorkspace(IProgressMonitor monitor_p) throws CoreException {
      int repToRefreshNb = representationsDescriptorsToRefresh.size();

      List<DRepresentationDescriptor> representationWithOtherErrors = new ArrayList<DRepresentationDescriptor>();
      List<DRepresentationDescriptor> representationNotLoadable = new ArrayList<DRepresentationDescriptor>();
      List<DRepresentationDescriptor> representationDangling = new ArrayList<DRepresentationDescriptor>();

      monitor_p.beginTask(getName(), repToRefreshNb);
      if (session != null) {
        int counter = 0;
        for (final DRepresentationDescriptor dRepresentationDesc : representationsDescriptorsToRefresh) {
          counter++;
          monitor_p.subTask(Messages.bind(Messages.RefreshRepresentation_6,
              new Object[] { dRepresentationDesc.getName(), counter, repToRefreshNb }));

          DRepresentation representation = dRepresentationDesc.getRepresentation();
          if (representation != null) {
            if (new DRepresentationDescriptorQuery(dRepresentationDesc).isRepresentationValid()) {
              OpeningDiagramJob job_opening = new OpeningDiagramJob(Messages.RefreshRepresentation_7, session,
                  representation);
              job_opening.setProperty(IProgressConstants.NO_IMMEDIATE_ERROR_PROMPT_PROPERTY, Boolean.TRUE);
              job_opening.setUser(false);
              job_opening.schedule();
              try {
                job_opening.join();
              } catch (InterruptedException e) {
                e.printStackTrace();
              }

              if (job_opening.getResult().isOK()) {
                ClosingDiagramJob job_closing = new ClosingDiagramJob(Messages.RefreshRepresentation_8,
                    job_opening.getCurrentEditor(), display);
                job_closing.setUser(false);
                job_closing.schedule();
                try {
                  job_closing.join();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              } else {
                representationWithOtherErrors.add(dRepresentationDesc);
              }
            } else {
              representationDangling.add(dRepresentationDesc);
            }
          } else {
            representationNotLoadable.add(dRepresentationDesc);
          }
          monitor_p.worked(1);
          if (monitor_p.isCanceled()) {
            break;
          }

        }
      }
      monitor_p.done();

      int nbRefreshWithError = representationNotLoadable.size() + representationDangling.size()
          + representationWithOtherErrors.size();

      int nbRefreshWithSuccess = repToRefreshNb - nbRefreshWithError;

      StringBuilder strBuilder = new StringBuilder();
      if (nbRefreshWithSuccess > 1) {
        strBuilder.append(nbRefreshWithSuccess + " representations refreshed");
      } else {
        strBuilder.append(nbRefreshWithSuccess + " representation refreshed");
      }

      int severity = IStatus.OK;
      if (nbRefreshWithError > 0) {
        severity = IStatus.WARNING;
        strBuilder.append(" and " + nbRefreshWithError + " representation(s) failed to refresh");
        if (representationNotLoadable.size() > 0) {
          strBuilder.append("\nNot loadable representation(s) (invalid)");
          representationNotLoadable.stream().forEach(repDesc -> {
            String repDescInfo = addRepDescInfo(strBuilder, repDesc);

            Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM)
                .error(new EmbeddedMessage("The representation can not be loaded (invalid): " + repDescInfo, "",
                    Collections.singletonList(repDesc)));
          });
        }
        if (representationDangling.size() > 0) {
          strBuilder.append("\nRepresentation(s) with no valid semantic target (invalid)");
          representationDangling.stream().forEach(repDesc -> {
            String repDescInfo = addRepDescInfo(strBuilder, repDesc);

            Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM)
                .error(new EmbeddedMessage("The representation has no valid semantic target (invalid): " + repDescInfo,
                    "", Collections.singletonList(repDesc)));
          });
        }
        if (representationWithOtherErrors.size() > 0) {
          strBuilder.append("\nRepresentation(s) that failed to refresh");
          representationWithOtherErrors.stream().forEach(repDesc -> {
            String repDescInfo = addRepDescInfo(strBuilder, repDesc);

            Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).warn(new EmbeddedMessage(
                "The representation failed to refresh: " + repDescInfo, "", Collections.singletonList(repDesc)));
          });
        }
      }
      return new Status(severity, SiriusUIPlugin.getDefault().getPluginId(), strBuilder.toString());
    }

    private String addRepDescInfo(StringBuilder strBuilder, DRepresentationDescriptor repDesc) {
      String repDescInfo = getRepDescInfo(repDesc);
      strBuilder.append("\n - ");
      strBuilder.append(repDescInfo);
      return repDescInfo;
    }

    private String getRepDescInfo(DRepresentationDescriptor repDesc) {
      StringBuilder strBuilder = new StringBuilder();
      strBuilder.append("name: " + repDesc.getName());
      strBuilder.append(", uid: " + repDesc.getUid());
      if (repDesc.getTarget() instanceof ModelElement) {
        strBuilder.append(", target: " + ((ModelElement) repDesc.getTarget()).getLabel());
      }
      return strBuilder.toString();
    }
  }

  protected class OpeningDiagramJob extends WorkspaceJob {

    private Session session;

    private DRepresentation dRepresentation;

    private IEditorPart currentEditor;

    /**
     * @param name
     */
    public OpeningDiagramJob(String name, Session session, DRepresentation dRepresentation) {
      super(name);
      this.session = session;
      this.dRepresentation = dRepresentation;
      this.currentEditor = null;
    }

    @Override
    public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
      if (session != null && dRepresentation != null) {
        currentEditor = DialectUIManager.INSTANCE.openEditor(session, dRepresentation, monitor);
      }
      if (currentEditor != null && ((DialectEditor) currentEditor).isLastRepresentationRefreshSucceeded()
          .orElse(Boolean.FALSE).booleanValue()) {
        return Status.OK_STATUS;
      } else {
        // INFO severity will not be logged in error log view
        return new Status(Status.INFO, SiriusUIPlugin.getDefault().getPluginId(),
            "Failed to refresh the representation " + dRepresentation.getName());
      }
    }

    /**
     * @return the currentEditor
     */
    public IEditorPart getCurrentEditor() {
      return currentEditor;
    }
  }

  protected class ClosingDiagramJob extends WorkspaceJob {

    private Display _display;

    private IEditorPart editor;

    /**
     * @param name
     */
    public ClosingDiagramJob(String name, IEditorPart editor_p, Display display_p) {
      super(name);
      editor = editor_p;
      _display = display_p;
    }

    @Override
    public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
      _display.asyncExec(new Runnable() {
        @Override
        public void run() {
          if (editor != null) {
            IWorkbenchPartSite site = editor.getSite();
            if (site != null) {
              IWorkbenchPage page = site.getPage();
              if (page != null) {
                page.closeEditor(editor, false);
              }
            }
          }
        }
      });
      return Status.OK_STATUS;
    }

  }

  /**
   * Get the Session associated to the given object.
   * 
   * @param object_p
   *          the object from which to find a Session (must be an IFile or a ModelElement).
   * @return the found session or <code>null</code> if no session can be found.
   */
  protected Session getSession(Object object_p) {
    Session session = null;
    if (object_p instanceof IFile) {
      session = SessionHelper.getSessionForDiagramFile((IFile) object_p);
    } else if (object_p instanceof ModelElement) {
      session = SessionManager.INSTANCE.getSession((ModelElement) object_p);
    }
    return session;
  }

  /**
   * CommandHandler is enabled if current selection contains an IFile or a ModelElement from which a Session found.
   * 
   * @return
   */
  @Override
  public boolean isEnabled() {
    IStructuredSelection currentSelection = getSelection();
    if (currentSelection.isEmpty()) {
      return false;
    }
    Object selectedElement = currentSelection.getFirstElement();
    // Check a session could be get from the selected element.
    Session selectedElementSession = getSession(selectedElement);
    return (null != selectedElementSession);
  }

  @Override
  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    // Get the selected element and its session
    Object selectedElement = getSelection().getFirstElement();
    Session session = getSession(selectedElement);
    refreshRepresentations(selectedElement, session);
    return null;
  }

  /**
   * Returns representations to refresh.
   */
  public Collection<DRepresentationDescriptor> getSubRepresentations(Object selectedElement, Session session) {
    Collection<DRepresentationDescriptor> representationsToRefresh = Collections.emptyList();
    if (selectedElement instanceof ModelElement) {
      // Selected element is a ModelElement, only diagrams under this ModelElement are refreshed.
      representationsToRefresh = new ArrayList<DRepresentationDescriptor>();
      Collection<DRepresentationDescriptor> allSessionRepresentations = DialectManager.INSTANCE
          .getAllRepresentationDescriptors(session);
      for (DRepresentationDescriptor representation : allSessionRepresentations) {
        EObject associatedModelElement = representation.getTarget();
        if (EcoreUtil.isAncestor((ModelElement) selectedElement, associatedModelElement)) {
          representationsToRefresh.add(representation);
        }
      }
    } else if (selectedElement instanceof IFile) {
      // Selected element is an IFile (the aird file), all diagrams are refreshed.
      representationsToRefresh = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
    }
    return representationsToRefresh;
  }

  public Job refreshRepresentations(Object selectedElement, Session session) {
    Collection<DRepresentationDescriptor> representationDescriptors = getSubRepresentations(selectedElement, session);

    if (representationDescriptors.isEmpty()) {
      // If no representation, show information dialog
      MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
          Messages.RefreshRepresentation_9, Messages.RefreshRepresentation_10);

    } else {
      if (session.getStatus() == SessionStatus.DIRTY) {
        // If within the representations to refresh, there is a opened representation, close it. If it is dirty,
        // ask for
        // saving it.
        if (MessageDialog.openConfirm(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
            Messages.RefreshRepresentation_4, Messages.RefreshRepresentation_5)) {

          // Save the current session
          session.save(new NullProgressMonitor());
        } else {
          return null;
        }
      }

      // Show to user the total number of representations to refresh and ask user to confirm for the last time
      // before
      // refreshing.
      if (MessageDialog.openConfirm(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
          Messages.RefreshRepresentation_1,
          Messages.bind(Messages.RefreshRepresentation_2, representationDescriptors.size()))) {

        // Close the diagram editors related to the session
        IEditorReference[] openedEditorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
            .getEditorReferences();

        for (IEditorReference editorReference : openedEditorReferences) {
          IEditorPart editor = editorReference.getEditor(false);
          if (DialectUIManager.INSTANCE.canHandleEditor(editor)) {
            DialectUIManager.INSTANCE.closeEditor(editor, false);
          }
        }

        // Schedule the job
        RefreshDiagramsJob job = new RefreshDiagramsJob(representationDescriptors, session, Display.getDefault());
        job.setUser(true);
        job.schedule();
        return job;
      }
    }
    return null;
  }
}
