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

package org.polarsys.capella.core.sirius.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * The save action for sessions.
 */
public class SaveSessionAction extends BaseSelectionListenerAction {
	
  /**
   * Constructs the save action for sessions.
   */
  public SaveSessionAction() {
    super("Save"); //$NON-NLS-1$
    setActionDefinitionId("org.eclipse.ui.file.save"); //$NON-NLS-1$
    setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/save.gif")); //$NON-NLS-1$
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    // Gets all selected sessions.
    final List<Session> sessions = new ArrayList<Session>();

    IStructuredSelection structuredSelection = getStructuredSelection();
    Iterator<?> iterator = structuredSelection.iterator();
    while (iterator.hasNext()) {
      Object object = iterator.next();
      if (object instanceof Session) {
        sessions.add((Session) object);
      } else if (object instanceof IFile) {
        Session session = SessionHelper.getSession((IFile) object);
        if (null != session) {
          sessions.add(session);
        }
      }
    }

    final List<Session> saveableSessions = new ArrayList<Session>();
    final List<Session> unsaveableSessions = new ArrayList<Session>();

    Collection<IFile> files = new ArrayList<IFile>();

    for (Session session : sessions) {
      if (SessionHelper.areSessionResourcesSaveable(session, files)) {
        saveableSessions.add(session);
      } else {
        unsaveableSessions.add(session);
      }
    }

    final Shell activeShell = Display.getCurrent().getActiveShell();
    ProgressMonitorDialog monitor = new ProgressMonitorDialog(activeShell);
    try {
    	
      List<IRunnableWithProgress> runnables = createRunnables(saveableSessions);

      for(IRunnableWithProgress runnable : runnables){
    	  monitor.run(false, false, runnable);
      }

      if (!unsaveableSessions.isEmpty()) {
        String msg;
        msg = Messages.unableToSaveDialog_TopMsg;
        for (IFile file : files) {
          msg += file.toString() + ICommonConstants.EOL_CHARACTER;
        }

        MessageDialog.openWarning(activeShell, Messages.unableToSaveDialog_Title, msg);
      }

    } catch (InvocationTargetException ite) {
      StatusManager.getManager().handle(
          new Status(IStatus.ERROR, getBundleId(ite.getCause()), ite.getCause().getMessage(), ite.getCause()),
          StatusManager.BLOCK);
    } catch (InterruptedException ie) {
      StatusManager.getManager().handle(new Status(IStatus.ERROR, getBundleId(ie), ie.getMessage(), ie));
    }
  }
  
  /**
   * Override this method to define the runnables to execute during a save action.
   * 
   * @return the list of {@link IRunnableWithProgress} to execute.
   */
  protected List<IRunnableWithProgress> createRunnables(final List<Session> sessionsToSave){
	  List<IRunnableWithProgress> runnables = new ArrayList<IRunnableWithProgress>();
	  IRunnableWithProgress saveSessionRunnable = new IRunnableWithProgress() {
	        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
	          for (Session session : sessionsToSave) {
	            session.save(monitor);
	          }
	        }};
	  runnables.add(saveSessionRunnable);
	  return runnables;
  }
  

  /**
   * @param obj
   * @return the bundle containing the object's class or the class's name if it's not contained in a bundle
   */
  private String getBundleId(Object obj) {
    Bundle bundle = FrameworkUtil.getBundle(obj.getClass());
    if (bundle != null)
    {
      return bundle.getSymbolicName();
    }
    return obj.getClass().getCanonicalName();
  }
}
