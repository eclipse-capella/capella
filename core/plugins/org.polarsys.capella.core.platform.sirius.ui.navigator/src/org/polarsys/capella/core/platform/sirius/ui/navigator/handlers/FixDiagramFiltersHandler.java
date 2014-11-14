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

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 *
 */
public class FixDiagramFiltersHandler extends AbstractDiagramCommandHandler {

  protected class FixDiagramsJob extends WorkspaceJob {

    private Session _session;
    private String _resName;
    private Display _display;

    public FixDiagramsJob(String name, String resName_p, Session session_p, Display display_p) {
      super(name);
      _session = session_p;
      _resName = resName_p;
      _display = display_p;
    }

    @Override
    public IStatus runInWorkspace(IProgressMonitor monitor_p) throws CoreException {
      monitor_p.beginTask(getName(), IProgressMonitor.UNKNOWN);

      if (_session != null) {
        Collection<DRepresentation> representations = DialectManager.INSTANCE.getAllRepresentations(_session);
        monitor_p.beginTask(Messages.FixFiltersAction_5 + _resName, representations.size());

        for (final DRepresentation dRepresentation : representations) {
          monitor_p.setTaskName(Messages.FixFiltersAction_6 + dRepresentation.getName() + Messages.FixFiltersAction_7);

          _display.syncExec(new Runnable() {
            public void run() {
              IEditorPart editor = DialectUIManager.INSTANCE.openEditor(_session, dRepresentation, new NullProgressMonitor());
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

          monitor_p.worked(1);
          if (monitor_p.isCanceled()) {
            break;
          }
        }
      }
      monitor_p.setTaskName(Messages.FixFiltersAction_8 + _resName + Messages.FixFiltersAction_9);
      monitor_p.done();
      return Status.OK_STATUS;
    }
  }

  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    if (!MessageDialog.openConfirm(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.FixFiltersAction_1, Messages.FixFiltersAction_2)) {
      return null;
    }

    IStructuredSelection selection = getSelection();
    Iterator<?> iterator = selection.iterator();
    while (iterator.hasNext()) {
      Object file = iterator.next();
      if (file instanceof IFile) {
        IFile resource = (IFile) file;
        Session session = SessionHelper.getSession(resource);
        FixDiagramsJob job = new FixDiagramsJob("Fix filters on aird", resource.getName(), session, Display.getCurrent());
        job.setThread(Display.getDefault().getThread());
        job.setUser(true);
        job.schedule();
      }
    }

    return null;
  }

}
