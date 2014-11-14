/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.platform.sirius.ui.navigator.refresh.SpecificRefreshCommand;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * This CommandHandler allows to refresh all diagrams below a selected ModelElement or .aird file.
 */
public class RefreshDiagramsCommandHandler extends AbstractDiagramCommandHandler {

  /**
   * Eclipse's job that will call the refresh diagrams command.
   */
  protected class RefreshDiagramsJob extends WorkspaceJob {

    private String _baseElementName;
    private Display _display;
    private Collection<DRepresentation> _representationsToRefresh;
    private Session _session;

    public RefreshDiagramsJob(String baseElementName_p, Session session_p, Collection<DRepresentation> representationsToRefresh_p, Display display_p) {
      super(Messages.RefreshDiagramsCommandHandler_JobName);
      _baseElementName = baseElementName_p;
      _session = session_p;
      _representationsToRefresh = representationsToRefresh_p;
      _display = display_p;
    }

    @Override
    public IStatus runInWorkspace(IProgressMonitor monitor_p) throws CoreException {
      final Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);
      monitor_p.beginTask(getName(), IProgressMonitor.UNKNOWN);
      logger.info("Starting refresh of diagrams below " + _baseElementName); //$NON-NLS-1$
      Collection<DRepresentation> modifiedRepresentations =
          SpecificRefreshCommand.refreshRepresentations(_session, _representationsToRefresh, monitor_p, _display);
      for (DRepresentation dRepresentation : modifiedRepresentations) {
        logger.info("Diagram " + dRepresentation.getName() + " refreshed."); //$NON-NLS-1$ //$NON-NLS-2$
      }
      logger.info("Refresh of diagrams below " + _baseElementName + " done."); //$NON-NLS-1$//$NON-NLS-2$
      monitor_p.done();
      return Status.OK_STATUS;
    }

  }

  /**
   * @param event_p
   * @return
   */
  protected boolean confirm(ExecutionEvent event_p) {
    return MessageDialog.openConfirm(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
        Messages.RefreshDiagramsCommandHandler_ConfirmRefreshDialog_Title, Messages.RefreshDiagramsCommandHandler_ConfirmRefreshDialog_Text);
  }

  @Override
  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    Object selectedElement = getSelection().getFirstElement();
    Session session = getSession(selectedElement);
    //
    // Compute representations to refresh.
    //
    Collection<DRepresentation> representationsToRefresh = Collections.emptyList();
    if (selectedElement instanceof ModelElement) {
      // Selected element is a ModelElement, only diagrams under this ModelElement are refreshed.
      representationsToRefresh = new ArrayList<DRepresentation>();
      Collection<DRepresentation> allSessionRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);
      for (DRepresentation representation : allSessionRepresentations) {
        if (representation instanceof DSemanticDecorator) {
          EObject associatedModelElement = ((DSemanticDecorator) representation).getTarget();
          if (EcoreUtil.isAncestor((ModelElement) selectedElement, associatedModelElement)) {
            representationsToRefresh.add(representation);
          }
        }
      }
    } else if (selectedElement instanceof IFile) {
      // Selected element is an IFile (the aird file), all diagrams are refreshed.
      representationsToRefresh = DialectManager.INSTANCE.getAllRepresentations(session);
    }
    // Is there a representation to refresh ?
    if (representationsToRefresh.isEmpty()) {
      // No representation -> refresh is over.
      MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.RefreshDiagramsCommandHandler_NoDiagramDialog_Title,
          Messages.RefreshDiagramsCommandHandler_NoDiagramDialog_Text);
      return null;
    }
    // Ask user confirmation.
    boolean refreshConfirmed = confirm(event_p);
    if (!refreshConfirmed) {
      return null;
    }
    //
    // Get name of selected element.
    //
    String selectedElementName = ICommonConstants.EMPTY_STRING;
    if (selectedElement instanceof IFile) {
      selectedElementName = ((IFile) selectedElement).getName();
    } else if (selectedElement instanceof NamedElement) {
      selectedElementName = ((NamedElement) selectedElement).getName();
    } else {
      selectedElementName = selectedElement.toString();
    }
    //
    // Create refresh job and schedule it.
    //
    Job job = new RefreshDiagramsJob(selectedElementName, session, representationsToRefresh, Display.getCurrent());
    job.setThread(Display.getDefault().getThread());
    job.setUser(true);
    job.schedule();

    return null;
  }

  /**
   * Get the Session associated to the given object.
   * @param object_p the object from which to find a Session (must be an IFile or a ModelElement).
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
}
