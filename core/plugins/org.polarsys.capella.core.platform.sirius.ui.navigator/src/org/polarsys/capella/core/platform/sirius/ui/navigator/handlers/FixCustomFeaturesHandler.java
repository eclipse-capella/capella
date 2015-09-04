/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.action.RefreshActionListenerRegistry;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.helpers.FixCustomFeaturesHelper;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 *
 */
public class FixCustomFeaturesHandler extends AbstractDiagramCommandHandler {

  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    if (!MessageDialog.openConfirm(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Confirm",
        "Are you sure you want to fix the custom features")) {
      return null;
    }

    final IFile airdFile = (IFile) getSelection().getFirstElement();
    IRunnableWithProgress operation = new IRunnableWithProgress() {

      @Override
      public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        final Session session = SessionHelper.getSession(airdFile);

        WorkspaceJob job = new WorkspaceJob(Messages.FixCustomFeaturesJobName) {

          @Override
          public IStatus runInWorkspace(final IProgressMonitor monitor) throws CoreException {
            final Set<DRepresentation> modifiedDiagrams = new HashSet<DRepresentation>();
            ExecutionManager executionManager = TransactionHelper.getExecutionManager(session);
            executionManager.execute(new AbstractReadWriteCommand() {

              @Override
              public void run() {
                Set<Resource> allSessionResources = session.getAllSessionResources();
                for (Resource resource : allSessionResources) {
                  modifiedDiagrams.addAll(FixCustomFeaturesHelper.removeCustomFeaturesIfNecessary(resource));
                }
              }
            });
            TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            for (DRepresentation dRepresentation : modifiedDiagrams) {
              RefreshActionListenerRegistry.INSTANCE.notifyRepresentationIsAboutToBeRefreshed(dRepresentation);
            }
            domain.getCommandStack().execute(
                new RefreshRepresentationsCommand(domain, new SubProgressMonitor(monitor, 1), modifiedDiagrams));
            return Status.OK_STATUS;
          }
        };
        job.setPriority(Job.BUILD);
        job.setRule(ResourcesPlugin.getWorkspace().getRuleFactory()
            .createRule(airdFile.getProject().getWorkspace().getRoot()));
        job.setUser(true);
        job.schedule();
      }
    };

    try {
      new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()).run(false, true,
          operation);
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }
}
