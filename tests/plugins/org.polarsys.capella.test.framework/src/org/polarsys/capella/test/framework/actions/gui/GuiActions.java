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
package org.polarsys.capella.test.framework.actions.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.IOverwriteQuery;
import org.eclipse.ui.internal.wizards.datatransfer.ZipLeveledStructureProvider;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.platform.sirius.ui.project.NewProjectWizard;
import org.polarsys.capella.core.sirius.ui.actions.OpenSessionAction;
import org.polarsys.capella.test.framework.actions.headless.DesignerControlActionForTest;
import org.polarsys.capella.test.framework.actions.headless.HeadlessCapellaValidateAction;
import org.polarsys.capella.test.framework.actions.headless.HeadlessCloseSessionAction;
import org.polarsys.capella.test.framework.actions.headless.HeadlessNewLibraryWizard;
import org.polarsys.capella.test.framework.actions.headless.HeadlessNewProjectWizard;
import org.polarsys.capella.test.framework.actions.headless.HeadlessWizardDialog;
import org.polarsys.capella.test.framework.helpers.IFileRequestor;

/**
 * An API gathering together launchers for GUI capella actions. All these
 * actions are headless (they do not block on GUI windows and does not need user
 * interaction).
 * 
 * @author Erwan Brottier
 */
public class GuiActions {

  /**
   * Creates a capella project by using the capella wizard
   * 
   * @see NewProjectWizard
   * 
   * @param projectName
   *          the name of the project to create
   * @return the created eclipse project.
   */
  public static IProject newProject(String projectName) {
    IWorkbenchSite site = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart()
        .getSite();
    NewProjectWizard wizard = new HeadlessNewProjectWizard(projectName);
    wizard.init(PlatformUI.getWorkbench(), StructuredSelection.EMPTY);
    HeadlessWizardDialog dialog = new HeadlessWizardDialog(site.getShell(), wizard);
    dialog.setBlockOnOpen(false);
    dialog.open();
    dialog.clickOnOk();
    return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
  }

  /**
   * Creates a capella library by using the capella wizard @see
   * NewLibraryProjectWizard.
   * 
   * @param projectName
   *          the name of the library to create
   * @return the created eclipse project.
   */
  public static IProject newLibrary(String projectName) {
    IWorkbenchSite site = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart()
        .getSite();
    NewProjectWizard wizard = new HeadlessNewLibraryWizard(projectName);
    wizard.init(PlatformUI.getWorkbench(), StructuredSelection.EMPTY);
    HeadlessWizardDialog dialog = new HeadlessWizardDialog(site.getShell(), wizard);
    dialog.setBlockOnOpen(false);
    dialog.open();
    dialog.clickOnOk();
    return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
  }

  /**
   * Import a project from an archive. Close several sessions at the same time
   * by using the capella action @see CloseSessionAction.
   * 
   * @param projectName
   *          the name of the project in the workspace once it has been imported
   * @param theZipFile
   *          the archive file
   * @return the created eclipse project.
   */
  public static IProject importProjectFromArchive(String projectName, File theZipFile) {
    IProject res = null;
    try {
      IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IProjectDescription newProjectDescription = workspace.newProjectDescription(projectName);
      IProject newProject = workspace.getRoot().getProject(projectName);
      newProject.create(newProjectDescription, null);
      newProject.open(null);
      ZipFile zipFile = new ZipFile(theZipFile.toString());
      IOverwriteQuery overwriteQuery = new IOverwriteQuery() {
        public String queryOverwrite(String file) {
          return ALL;
        }
      };
      ZipLeveledStructureProvider provider = new ZipLeveledStructureProvider(zipFile);
      List<Object> fileSystemObjects = new ArrayList<Object>();
      Enumeration<? extends ZipEntry> entries = zipFile.entries();
      while (entries.hasMoreElements()) {
        fileSystemObjects.add(entries.nextElement());
      }
      ImportOperation importOperation = new ImportOperation(newProject.getFullPath(), new ZipEntry(projectName),
          provider, overwriteQuery, fileSystemObjects);
      importOperation.setCreateContainerStructure(false);
      importOperation.run(new NullProgressMonitor());
      res = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    flushASyncGuiThread();
    return res;
  }

  /**
   * Close several sessions at the same time by using the capella action @see
   * CloseSessionAction.
   * 
   * @param sessions
   *          the list of sessions to close
   */
  public static void closeSessions(List<Session> sessions) {
    HeadlessCloseSessionAction closeSessionAction = new HeadlessCloseSessionAction(sessions);
    closeSessionAction.run();
    flushASyncGuiThread();
  }

  /**
   * Close a session by using the capella action @see CloseSessionAction.
   * 
   * @param session
   *          the session to close
   */
  public static void closeSession(Session session) {
    List<Session> sessionsToClose = new ArrayList<Session>();
    sessionsToClose.add(session);
    closeSessions(sessionsToClose);
    flushASyncGuiThread();
  }

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
   * Open several sessions at the same time by using the capella action @see
   * OpenSessionAction.
   * 
   * @param airdFiles
   *          the list of aird files that correspond to the sessions to open.
   */
  public static void openSessions(List<IFile> airdFiles) {
    OpenSessionAction olsa = new OpenSessionAction();
    olsa.selectionChanged(new StructuredSelection(airdFiles));
    olsa.run();
    flushASyncGuiThread();
  }

  /**
   * Launch the capella validate action for the given element.
   * 
   * @return the capella element
   * @return element an instance of @see Diagnostic which describes the
   *         validation result. diagnostic.getSeverity() == Diagnostic.OK means
   *         the validation is ok. Use diagnostic.getChildren to get problem
   *         descriptions.
   */
  public static Diagnostic validate(CapellaElement element) {
    HeadlessCapellaValidateAction capellaValidateAction = new HeadlessCapellaValidateAction();
    capellaValidateAction.updateSelection(new StructuredSelection(element));
    capellaValidateAction.run();
    flushASyncGuiThread();
    return capellaValidateAction.getDiagnostic();
  }

  /**
   * Fragment the given element.
   * 
   * @param element
   *          the capella element to fragment
   */
  public static void fragment(CapellaElement element) {
    fragment(element, null);
  }

  /**
   * Fragment the given element and representations.
   * 
   * @param element
   *          the capella element to fragment
   * @param dRepresentationList_p
   *          list of DRepresentions to fragment
   */
  public static void fragment(CapellaElement element, List<DRepresentation> dRepresentationList_p) {
    DesignerControlActionForTest action = new DesignerControlActionForTest();
    action.selectionChanged(new StructuredSelection(element));
    if (dRepresentationList_p != null)
      action.setDRepresentationsToMove(dRepresentationList_p);
    action.run();
    // flushASyncGuiThread(); deleted because it generates NPE in
    // ExternalJavaActionTask.execute
  }

  /**
   * Unfragment the given element.
   * 
   * @param element
   *          the capella element to fragment
   * @param bShouldUncontrolRepresentations_p
   *          , if yes, DRepresentions will be unfragmented too
   */
  public static void unfragment(CapellaElement element, boolean bShouldUncontrolRepresentations_p) {
    DesignerControlActionForTest action = new DesignerControlActionForTest();
    action.selectionChanged(new StructuredSelection(element));
    action.setShouldUncontrolRepresentations(bShouldUncontrolRepresentations_p);
    action.run();
  }

  public static void deleteEclipseProject(IProject eclipseProject) throws CoreException {
    eclipseProject.delete(false, true, new NullProgressMonitor());
    flushASyncGuiThread();
  }

  public static void saveSession(Session session) {
    session.save(new NullProgressMonitor());
    flushASyncGuiThread();
  }

  /**
   * Prevents that all async thread on UI Thread has been executed before returning.
   * FIXME It is the best implementation to date. May be insufficient.
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

  public static Session getSession(IProject p) {
    IFile airdFile = new IFileRequestor().search(p, "aird").get(0); //$NON-NLS-1$
    URI airdFileUri = EcoreUtil2.getURI(airdFile);
    return SessionManager.INSTANCE.getSession(airdFileUri, new NullProgressMonitor());
  }

}
