/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.amalgam.explorer.activity.ui.ActivityExplorerActivator;
import org.eclipse.amalgam.explorer.activity.ui.api.preferences.PreferenceConstants;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.progress.UIJob;
import org.polarsys.capella.core.explorer.activity.ui.actions.OpenActivityExplorerAction;
import org.polarsys.capella.core.model.obfuscator.actions.ObfuscateSessionAction;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCloneDiagramCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCopyToClipboardCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaPasteCommand;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.RenameResourceAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SortContentAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SortSelectionAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.handlers.DeleteHiddenElementsJob;
import org.polarsys.capella.core.platform.sirius.ui.navigator.handlers.RefreshDiagramsCommandHandler;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.platform.sirius.ui.project.NewProjectWizard;
import org.polarsys.capella.core.sirius.ui.actions.OpenSessionAction;
import org.polarsys.capella.test.framework.actions.headless.HeadlessCloseSessionAction;
import org.polarsys.capella.test.framework.actions.headless.HeadlessNewProjectWizard;
import org.polarsys.capella.test.framework.actions.headless.HeadlessWizardDialog;

/**
 * An API gathering together launchers for GUI capella actions. All these actions are headless (they do not block on GUI
 * windows and does not need user interaction).
 *
 * @author Erwan Brottier
 */
public class GuiActions {

    /**
     * Creates a capella project by using the capella wizard @see NewProjectWizard.
     *
     * @param projectName
     *            the name of the project to create
     * @param openActivityExplorer
     *            must open AE ?
     * @return the created eclipse project.
     */
    public static IProject newProject(String projectName, boolean openActivityExplorer) {
        // Set the Activity Explorer preference to the expected value
        boolean originalPrefValue = ActivityExplorerActivator.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.P_OPEN_ACTIVITY_EXPLORER);
        if (originalPrefValue != openActivityExplorer) {
            ActivityExplorerActivator.getDefault().getPreferenceStore().setValue(PreferenceConstants.P_OPEN_ACTIVITY_EXPLORER, openActivityExplorer);
        }

        IWorkbenchSite site = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart().getSite();
        NewProjectWizard wizard = new HeadlessNewProjectWizard(projectName);
        wizard.init(PlatformUI.getWorkbench(), StructuredSelection.EMPTY);
        HeadlessWizardDialog dialog = new HeadlessWizardDialog(site.getShell(), wizard);
        dialog.setBlockOnOpen(false);
        dialog.open();
        dialog.clickOnOk();

        // Reset the original value of the Activity Explorer preference
        if (originalPrefValue != openActivityExplorer) {
            ActivityExplorerActivator.getDefault().getPreferenceStore().setValue(PreferenceConstants.P_OPEN_ACTIVITY_EXPLORER, originalPrefValue);
        }
        flushASyncGuiThread();

        return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

    }

    /**
     * Open a session by using the capella action @see OpenSessionAction.
     *
     * @param airdFile
     *            the aird file
     * @param openActivityExplorer
     *            Open the ActivityExplorer on open session
     */
    public static void openSession(IFile airdFile, boolean openActivityExplorer) {
        // Set the corresponding preference to the expected value
        boolean originalPrefValue = ActivityExplorerActivator.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.P_OPEN_ACTIVITY_EXPLORER);
        if (originalPrefValue != openActivityExplorer) {
            ActivityExplorerActivator.getDefault().getPreferenceStore().setValue(PreferenceConstants.P_OPEN_ACTIVITY_EXPLORER, openActivityExplorer);
        }
        OpenSessionAction olsa = new OpenSessionAction(false);
        olsa.setOpenActivityExplorer(openActivityExplorer);
        olsa.selectionChanged(new StructuredSelection(airdFile));
        olsa.run();
        // Reset the original value of the preference
        if (originalPrefValue != openActivityExplorer) {
            ActivityExplorerActivator.getDefault().getPreferenceStore().setValue(PreferenceConstants.P_OPEN_ACTIVITY_EXPLORER, originalPrefValue);
        }

        flushASyncGuiThread();
    }

    /**
     * Close several sessions at the same time by using the Capella action
     *
     * @see CloseSessionAction.
     *
     * @param sessions
     *            the list of sessions to close
     */
    public static void closeSessions(List<Session> sessions, boolean saveSession) {
        HeadlessCloseSessionAction closeSessionAction = new HeadlessCloseSessionAction(sessions, saveSession);
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

    public static void eraseEclipseProject(IProject eclipseProject) throws CoreException {
        eclipseProject.delete(true, true, new NullProgressMonitor());
        flushASyncGuiThread();
    }

    /**
     * Close a session by using the capella action @see CloseSessionAction.
     *
     * @param session
     *            the session to close (if session is dirty, it will be saved)
     */
    public static void closeSession(Session session) {
        closeSessions(Collections.singletonList(session), true);
    }

    /**
     * Close a session by using the capella action @see CloseSessionAction.
     *
     * @param session
     *            the session to close (if session is dirty, it will be saved)
     * @param saveSession
     *            wether or not the session shall be saved (if dirty)
     */
    public static void closeSession(Session session, boolean saveSession) {
        closeSessions(Collections.singletonList(session), saveSession);
    }

    /**
     * Prevents that all async thread on UI Thread has been executed before returning. FIXME It is the best
     * implementation to date. May be insufficient.
     */
    public static void flushASyncGuiThread() {
        try {
            Display display = Display.getDefault();
            display.update();
            // Consume all pending work for the UI Thread
            while (display.readAndDispatch()) {
                // do nothing
            }
        } catch (Exception e) {
            // do nothing
        }
    }

    /**
     * Prevents that all UIJobs scheduled has been executed before returning.
     */
    public static void flushASyncGuiJobs() {
        Collection<Job> jobs = getUIJobs();
        while (jobs.size() > 0) {
            for (Job job : jobs) {
                try {
                    job.join(100, new NullProgressMonitor());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            flushASyncGuiThread();
            jobs = getUIJobs();
        }
    }

    protected static Collection<Job> getUIJobs() {
        IJobManager jobMan = Job.getJobManager();
        Job[] jobs = jobMan.find(null);

        Collection<Job> result = new ArrayList<Job>();
        for (Job job : jobs) {
            try {
                if (job instanceof UIJob) {
                    result.add(job);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public static void renameModelFile(IFile modelFile, final String newName) {

        // Execute the rename action
        RenameResourceAction renameAction = new RenameResourceAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()) {
            @Override
            protected String queryNewResourceName(IResource resource) {
                return newName;
            }
        };
        IStructuredSelection selection = new StructuredSelection(modelFile);
        renameAction.selectionChanged(selection);
        renameAction.run();

    }

    /**
     * Call the Sort Content action on an element
     *
     * @param elementToSort
     */
    public static void sortContent(EObject elementToSort) {
        SortContentAction _sortContent = new SortContentAction();
        IStructuredSelection selection = new StructuredSelection(elementToSort);
        _sortContent.selectionChanged(selection);
        _sortContent.run();
    }

    /**
     * Call the Sort Selection action on a list of selected elements
     *
     * @param elementsToSort
     */
    public static void sortSelection(List<EObject> elementsToSort) {
        SortSelectionAction _sortSelection = new SortSelectionAction();
        IStructuredSelection selection = new StructuredSelection(elementsToSort.toArray());
        _sortSelection.selectionChanged(selection);
        _sortSelection.run();
    }

    /**
     * Simulate a model detachment. Do NOT perform a model detach, it's just to evaluate detach preconditions.
     *
     * @param airdFile
     * @throws RuntimeException
     *             if one precondition is false
     */
    public static void lauchDetachModelAction(IFile airdFile) throws RuntimeException, Exception {
        String dettachCommandId = "org.polarsys.kitalpha.model.detachment.ui.command.a";
        executeEclipseCommand(dettachCommandId, airdFile);

        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
    }

    /**
     * Execute an Eclipse command with the file as current selection
     *
     * @param commandId
     * @param file
     *            is a Capella file
     * @throws Exception
     */
    protected static void executeEclipseCommand(String commandId, IFile file) throws Exception {
        Command command = PlatformUI.getWorkbench().getService(ICommandService.class).getCommand(commandId);
        IHandlerService hservice = PlatformUI.getWorkbench().getService(IHandlerService.class);

        setCurrentSelection(file);

        hservice.executeCommandInContext(ParameterizedCommand.generateCommand(command, null), null, hservice.createContextSnapshot(true));

    }

    /**
     * Open ActivityExplorersession by using the Capella action @see OpenActivityExplorerAction
     *
     * @param airdFile
     *            the aird file
     */
    public static void launchOpenActivityExplorerAction(IFile airdFile) {
        OpenActivityExplorerAction oaea = new OpenActivityExplorerAction();
        oaea.selectionChanged(new StructuredSelection(airdFile));
        oaea.run();
    }

    /**
     * Set current selection on IFile file
     *
     * @param file
     * @throws PartInitException
     */
    protected static void setCurrentSelection(IFile file) throws PartInitException {
        IWorkbenchPartSite site = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite();

        StructuredSelection selection = new StructuredSelection(file);
        CapellaCommonNavigator capellaProjectView = (CapellaCommonNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(CapellaCommonNavigator.ID);

        site.setSelectionProvider(capellaProjectView.getCommonViewer());
        site.getSelectionProvider().setSelection(selection);
    }

    public static void obfuscate(IFile airdFile) {
        ObfuscateSessionAction obfuscateAction = new ObfuscateSessionAction();
        IStructuredSelection selection = new StructuredSelection(airdFile);
        obfuscateAction.selectionChanged(selection);
        obfuscateAction.obfuscate();
    }

    /**
     * This code is called when using the "Remove Hidden Elements" action
     * 
     * @param session
     */
    public static void deleteHiddenElements(Session session, boolean isUnsyncDiagram) {
        Collection<DRepresentationDescriptor> representationsToRefresh = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
        Job job = new DeleteHiddenElementsJob(representationsToRefresh, session, isUnsyncDiagram);
        job.setThread(Display.getDefault().getThread());
        job.setUser(true);
        job.schedule();
    }

    public static void refreshAllSubRepresentations(IFile airdFile, Session session) {
        Collection<DRepresentationDescriptor> representationsToRefresh = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
        Job job = new RefreshDiagramsCommandHandler().new RefreshDiagramsJob(airdFile.getName(), representationsToRefresh, session, Display.getCurrent());
        job.setThread(Display.getDefault().getThread());
        job.setUser(true);
        job.schedule();
    }

    public static void closeAllOpenedEditors() {
        final IWorkbenchPage page = EclipseUIUtil.getActivePage();
        if (page != null) {
            final IEditorReference[] editorReferences = page.getEditorReferences();
            final List<IEditorReference> editorsToClose = new ArrayList<IEditorReference>();

            // Only take care of Sirius editors
            for (final IEditorReference editor : editorReferences) {
                if (editor.getEditor(false) instanceof IEditorPart)
                    editorsToClose.add(editor);
            }

            if (!editorsToClose.isEmpty()) {
                page.closeEditors(editorsToClose.toArray(new IEditorReference[editorsToClose.size()]), false);
            }
        }
    }

    /**
     * Copy a list of elements and paste to a target element
     * 
     * @param ted
     *            the transaction editing domain
     * @param elementsToCopy
     *            list of elements to copy
     * @param targetElement
     *            the target element to copy to
     */
    public static void copyAndPaste(TransactionalEditingDomain ted, Collection<EObject> elementsToCopy, EObject targetElement) {
        // Copy
        CapellaCopyToClipboardCommand capellaCopyToClipboardCommand = new CapellaCopyToClipboardCommand(ted, elementsToCopy, null);
        ted.getCommandStack().execute(capellaCopyToClipboardCommand);
        // Paste
        CapellaPasteCommand capellaPasteCommand = new CapellaPasteCommand(ted, targetElement, null, CommandParameter.NO_INDEX);
        ted.getCommandStack().execute(capellaPasteCommand);
    }

    /**
     * Clone a diagram and all this attach element
     * 
     * @param descriptors
     *            the representations to clone
     * 
     */
    public static Collection<DRepresentationDescriptor> CloneDiagram(Collection<DRepresentationDescriptor> descriptors) {
        CapellaCloneDiagramCommand capellaCloneDiagramCommand = new CapellaCloneDiagramCommand(descriptors);
        capellaCloneDiagramCommand.execute();
        return capellaCloneDiagramCommand.getResult();
    }
}
