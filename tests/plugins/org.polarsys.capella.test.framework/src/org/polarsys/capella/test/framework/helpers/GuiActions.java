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
package org.polarsys.capella.test.framework.helpers;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.RenameResourceAction;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.sirius.ui.actions.OpenSessionAction;
import org.polarsys.capella.test.framework.actions.headless.HeadlessCloseSessionAction;

/**
 * An API gathering together launchers for GUI capella actions. All these actions are headless (they do not block on GUI
 * windows and does not need user interaction).
 * 
 * @author Erwan Brottier
 */
public class GuiActions {

  /**
   * Open a session by using the capella action @see OpenSessionAction.
   * 
   * @param airdFile
   *          the aird file
   */
  public static void openSession(IFile airdFile) {
    OpenSessionAction olsa = new OpenSessionAction();
    olsa.selectionChanged(new StructuredSelection(airdFile));
    olsa.run();
    flushASyncGuiThread();
  }

  /**
   * Close several sessions at the same time by using the capella action @see CloseSessionAction.
   * 
   * @param sessions
   *          the list of sessions to close
   */
  public static void closeSessions(List<Session> sessions) {
    HeadlessCloseSessionAction closeSessionAction = new HeadlessCloseSessionAction(sessions);
    closeSessionAction.run();
    flushASyncGuiThread();
  }

  public static void saveSession(Session session) {
    session.save(new NullProgressMonitor());
    flushASyncGuiThread();
  }

  public static void deleteEclipseProject(IProject eclipseProject) throws CoreException {
    eclipseProject.delete(false, true, new NullProgressMonitor());
    flushASyncGuiThread();
  }

  /**
   * Close a session by using the capella action @see CloseSessionAction.
   * 
   * @param session
   *          the session to close
   */
  public static void closeSession(Session session) {
    closeSessions(Collections.singletonList(session));
  }

  /**
   * Prevents that all async thread on UI Thread has been executed before returning. FIXME It is the best implementation
   * to date. May be insufficient.
   */
  public static void flushASyncGuiThread() {
    try {
      Display.getCurrent().update();
      while (Display.getCurrent().readAndDispatch()) {
        // do nothing
      }
    } catch (Exception e) {
      // do nothing
    }
  }

  public static void renameModelFile(IFile modelFile_p, final String newName_p) {
    Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
    RenameResourceAction renameAction = new RenameResourceAction(activeShell) {
      @Override
      protected String queryNewResourceName(final IResource resource) {
        return newName_p;
      }
    };
    IStructuredSelection selection = new StructuredSelection(modelFile_p);
    renameAction.selectionChanged(selection);
    renameAction.run();
  }

  /**
   * Simulate a model detachment. Do NOT perform a model detach, it's just to evaluate detach preconditions.
   * 
   * @param airdFile
   * @throws RuntimeException
   *           if one precondition is false
   */
  public static void lauchDetachModelAction(IFile airdFile) throws RuntimeException, Exception {
    String dettachCommandId = "org.polarsys.kitalpha.model.detachment.ui.command.a";
    executeEclipseCommand(dettachCommandId, airdFile);
  }

  /**
   * Execute an Eclipse command with the file as current selection
   * 
   * @param commandId
   * @param file
   *          is a Capella file
   * @throws Exception
   */
  protected static void executeEclipseCommand(String commandId, IFile file) throws Exception {
    Command command = ((ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class))
        .getCommand(commandId);
    IHandlerService hservice = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);

    setCurrentSelection(file);

    hservice.executeCommandInContext(ParameterizedCommand.generateCommand(command, null), null,
        hservice.createContextSnapshot(true));

  }

  /**
   * Set current selection on IFile file
   * @param file
   * @throws PartInitException
   */
  protected static void setCurrentSelection(IFile file) throws PartInitException {
    IWorkbenchPartSite site = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart()
        .getSite();

    StructuredSelection selection = new StructuredSelection(file);
    CapellaCommonNavigator capellaProjectView = (CapellaCommonNavigator) PlatformUI.getWorkbench()
        .getActiveWorkbenchWindow().getActivePage().showView("capella.project.explorer");

    site.setSelectionProvider(capellaProjectView.getCommonViewer());
    site.getSelectionProvider().setSelection(selection);
  }
}
