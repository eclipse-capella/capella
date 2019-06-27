/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
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
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

public class RefreshDiagramsCommandHandler extends AbstractDiagramCommandHandler {

  /**
   * Eclipse's job that will call the refresh diagrams command.
   */
  public class RefreshDiagramsJob extends WorkspaceJob {

    private Session _session;
    private Collection<DRepresentationDescriptor> _representationsToRefresh;
    private Display _display;

    public RefreshDiagramsJob(String name, Collection<DRepresentationDescriptor> representations, Session session_p,
        Display display_p) {
      super(name);
      _session = session_p;
      _representationsToRefresh = representations;
      _display = display_p;
    }

    @Override
    public IStatus runInWorkspace(IProgressMonitor monitor_p) throws CoreException {
      int repToRefreshNb = _representationsToRefresh.size();
      monitor_p.beginTask(getName(), repToRefreshNb);
      if (_session != null) {
        int counter = 0;
        for (final DRepresentationDescriptor dRepresentation : _representationsToRefresh) {
          counter++;
          monitor_p.subTask(Messages.bind(Messages.RefreshRepresentation_6, new Object[] { dRepresentation.getName(), counter, repToRefreshNb }));

          DRepresentation representation = dRepresentation.getRepresentation();
          OpeningDiagramJob job_opening = new OpeningDiagramJob(Messages.RefreshRepresentation_7, _session,
              representation);
          job_opening.setUser(false);
          job_opening.schedule();
          try {
            job_opening.join();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          if (job_opening.getResult().isOK()) {
            ClosingDiagramJob job_closing = new ClosingDiagramJob(Messages.RefreshRepresentation_8,
                job_opening.getCurrentEditor(), _display);
            job_closing.setUser(false);
            job_closing.schedule();
            try {
              job_closing.join();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }

          monitor_p.worked(1);
          if (monitor_p.isCanceled()) {
            break;
          }

        }
      }
      monitor_p.done();
      return Status.OK_STATUS;
    }
  }

  protected class OpeningDiagramJob extends WorkspaceJob {

    private Session _session;
    private DRepresentation _dRepresentation;
    private IEditorPart currentEditor;

    /**
     * @param name
     */
    public OpeningDiagramJob(String name, Session session, DRepresentation dRepresentation) {
      super(name);
      _session = session;
      _dRepresentation = dRepresentation;
      currentEditor = null;
    }

    @Override
    public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
      if (_session != null && _dRepresentation != null) {
        currentEditor = DialectUIManager.INSTANCE.openEditor(_session, _dRepresentation, monitor);
      }
      return Status.OK_STATUS;
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

  public Object execute(ExecutionEvent event_p) throws ExecutionException {

    // Get the selected element and its session
    Object selectedElement = getSelection().getFirstElement();
    Session session = getSession(selectedElement);

    // Compute representations to refresh.
    Collection<DRepresentationDescriptor> representationsToRefresh = Collections.emptyList();
    if (selectedElement instanceof ModelElement) {
      // Selected element is a ModelElement, only diagrams under this ModelElement are refreshed.
      representationsToRefresh = new ArrayList<DRepresentationDescriptor>();
      Collection<DRepresentationDescriptor> allSessionRepresentations = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
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

    if (representationsToRefresh.isEmpty()) {
      // If no representation, show information dialog
      MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
          Messages.RefreshRepresentation_9, Messages.RefreshRepresentation_10);
      
    } else {
      if (session.getStatus() == SessionStatus.DIRTY) {
        // If within the representations to refresh, there is a opened representation, close it. If it is dirty, ask for
        // saving it.
        if (MessageDialog.openConfirm(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
            Messages.RefreshRepresentation_4, Messages.RefreshRepresentation_5)) {

          // Save the current session
          session.save(new NullProgressMonitor());

          // Close the diagram editors related to the session
          IEditorReference[] openedEditorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
              .getActivePage().getEditorReferences();

          for (IEditorReference editorReference : openedEditorReferences) {
            IEditorPart editor = editorReference.getEditor(false);
            if (DialectUIManager.INSTANCE.canHandleEditor(editor)) {
              DialectUIManager.INSTANCE.closeEditor(editor, false);
            }
          }
        } else {
          return null;
        }
      }

      // Show to user the total number of representations to refresh and ask user to confirm for the last time before
      // refreshing.
      if (MessageDialog.openConfirm(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
          Messages.RefreshRepresentation_1,
          Messages.bind(Messages.RefreshRepresentation_2, representationsToRefresh.size()))) {

        // Activate the preference of Sirius: Open refresh on representation opening.
        final Boolean currentValueOfSiriusPrefRefreshOnOpening = SiriusEditPlugin.getPlugin().getPreferenceStore()
            .getBoolean(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name());
        SiriusEditPlugin.getPlugin().getPreferenceStore()
            .setValue(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), true);

        // Schedule the job
        RefreshDiagramsJob job = new RefreshDiagramsJob(Messages.RefreshRepresentation_0, representationsToRefresh, session,
            Display.getDefault());
        job.setUser(true);
        job.schedule();

        // Add job listener to return the state of Sirius preference.
        job.addJobChangeListener(new IJobChangeListener() {

          @Override
          public void sleeping(IJobChangeEvent event) {
            // do nothing
          }

          @Override
          public void scheduled(IJobChangeEvent event) {
            // do nothing
          }

          @Override
          public void running(IJobChangeEvent event) {
            // do nothing
          }

          /**
           * Always reset the preference of Sirius to the previous state.
           */
          @Override
          public void done(IJobChangeEvent event) {
            SiriusEditPlugin.getPlugin().getPreferenceStore().setValue(
                SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(),
                currentValueOfSiriusPrefRefreshOnOpening);
          }

          @Override
          public void awake(IJobChangeEvent event) {
            // do nothing
          }

          @Override
          public void aboutToRun(IJobChangeEvent event) {
            // do nothing
          }
        });
      }
    }
    return null;
  }
}
