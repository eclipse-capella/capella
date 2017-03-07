/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
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
import org.polarsys.capella.core.platform.sirius.ui.navigator.helpers.AbstractFixDiagramHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.helpers.FixCustomFeaturesHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.helpers.FixDAnnotationsHelper;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

public abstract class AbstractFixDiagramsHandler extends AbstractDiagramCommandHandler {
	  private String dialogConfirmationMessage;
	  private String onlyCleanMessage;
	  private String jobName;
	  private AbstractFixDiagramHelper fixHelper;

	  @Override
	  public Object execute(ExecutionEvent event_p) throws ExecutionException {
		final boolean[] checked = {false};
		boolean fixConfirmed = false;
		
		if(null != getOnlyCleanMessage()){
			MessageDialogWithToggle messageDialogWithToggle =
			        new MessageDialogWithToggle(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.AbstractFixCommandHandler_ConfirmFix_Title, 
			        		null, this.getDialogConfirmationMessage(), MessageDialog.INFORMATION, new String[] { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL },
			            1, getOnlyCleanMessage(), false); //$NON-NLS-1$
			int rc = messageDialogWithToggle.open();
			fixConfirmed = (IDialogConstants.OK_ID == rc);
		    checked[0] = messageDialogWithToggle.getToggleState();
		} else {
			MessageDialog messageDialog = 
					new MessageDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.AbstractFixCommandHandler_ConfirmFix_Title,
							null, this.getDialogConfirmationMessage(), MessageDialog.INFORMATION, new String[] { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL }, 1);
					    
			int rc = messageDialog.open();
			fixConfirmed = (IDialogConstants.OK_ID == rc);
		}
		
	    if(!fixConfirmed) {
		      return null;
		}
	    
		
	    final IFile airdFile = (IFile) getSelection().getFirstElement();
	    IRunnableWithProgress operation = new IRunnableWithProgress() {

	      @Override
	      public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
	        final Session session = SessionHelper.getSession(airdFile);

	        WorkspaceJob job = new WorkspaceJob(getJobName()) {

	          @Override
	          public IStatus runInWorkspace(final IProgressMonitor monitor) throws CoreException {
	            final Set<DRepresentation> modifiedDiagrams = new HashSet<DRepresentation>();
	            ExecutionManager executionManager = TransactionHelper.getExecutionManager(session);
	            executionManager.execute(new AbstractReadWriteCommand() {

				@Override
	              public void run() {
					
					if(getFixHelper() instanceof FixCustomFeaturesHelper){
						modifiedDiagrams.addAll(
								((FixCustomFeaturesHelper)getFixHelper()).fixDiagram(session));
					}
					
					if(getFixHelper() instanceof FixDAnnotationsHelper){
						modifiedDiagrams.addAll(
								((FixDAnnotationsHelper)getFixHelper()).fixDiagramEventuallyClean(session, checked[0]));
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
	        job.setRule(
	            ResourcesPlugin.getWorkspace().getRuleFactory().createRule(airdFile.getProject().getWorkspace().getRoot()));
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

	  public String getDialogConfirmationMessage() {
	    return dialogConfirmationMessage;
	  }

	  public void setDialogConfirmationMessage(String dialogConfirmationMessage) {
	    this.dialogConfirmationMessage = dialogConfirmationMessage;
	  }

	  public String getJobName() {
	    return jobName;
	  }

	  public void setJobName(String jobName) {
	    this.jobName = jobName;
	  }

	  public AbstractFixDiagramHelper getFixHelper() {
	    return fixHelper;
	  }

	  public void setFixHelper(AbstractFixDiagramHelper fixHelper) {
	    this.fixHelper = fixHelper;
	  }

	  public String getOnlyCleanMessage() {
	    return onlyCleanMessage;
	  }

	  public void setOnlyCleanMessage(String onlyCleanMessage) {
	    this.onlyCleanMessage = onlyCleanMessage;
	  }
	}
