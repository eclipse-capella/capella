/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.postgeneration.egf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 */
@SuppressWarnings("nls")
public class UpdateIconsAction implements IObjectActionDelegate {

	public static final String __LABEL = "CTools Icons Update for Capella";

	// Number of name parts (between ".") which suffix metamodel definition projects
	// (by default 1, corresponding to ".def")
	private static final int __DATA_PROJECT_SUFFIX_LENGTH = 1;

	// Separator for parts in project names
	private static final String __SEPARATOR = "."; //$NON-NLS-1$

	// Name suffix for icon projects
	private static final String __ICONS_PROJECT_SUFFIX = "res.edit";

	// Path to the destination (ctool) icons folder within icon projects
	private static final String[] __DESTINATION_ICONS_FOLDER_PATH = { "icons", "full", "ctool16" };

	// Path to the source (ctool) icons folder within icon projects
	private static final String[] __SOURCE_ICONS_FOLDER_PATH = { "icons", "full", "obj16" };

//	private Shell _shell;
	private IStructuredSelection _selection;

	public UpdateIconsAction() {
		super();
		_selection = null;
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action_p, IWorkbenchPart targetPart_p) {
//		_shell = targetPart_p.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action_p) {
		try {
			List<IFile> selectedFiles = getValidSelectedFiles();
			if (selectedFiles.isEmpty()) {
				System.out.println("Selection must contain at least one ecore file.");
				return;
			}
			MetamodelLoader loader = new MetamodelLoader(selectedFiles, true);
//			ProgressMonitorDialog dialogLoad = new ProgressMonitorDialog(_shell);
//			dialogLoad.run(true, true, loader);
			loader.run(new NullProgressMonitor());
			ResourceSet rs = loader.getResultingResourceSet();
			Map<EClass, Set<EReference>> cr = loader.getResultingContainmentIncomingReferences();
			try {
				IFolder folder = getDestinationIconsFolder(getDestinationProject(selectedFiles.get(0).getProject()));
				clearFolder(folder);
			} catch (NullPointerException e) {
				// Error handled later
			}
			int mcsInt = 0;
			int createdInt = 0;
			for (IFile selectedFile : selectedFiles) {
				IProject sourceProject = selectedFile.getProject();
				IProject destinationProject = getDestinationProject(sourceProject);
				if (destinationProject == null || !destinationProject.exists()) {
					System.out.println("Cannot find corresponding icons project.");
					return;
				}
				IFolder srcIconsFolder = getSourceIconsFolder(destinationProject);
				if (srcIconsFolder == null || !srcIconsFolder.exists()) {
					System.out.println("Cannot find source icons folder.");
					return;
				}
				// if (srcIconsFolder.members().length == 0) {
				// MessageDialog.openError(_shell, __LABEL, "Source icons folder
				// is empty.");
				// return;
				// }
				IFolder destIconsFolder = getDestinationIconsFolder(destinationProject);
				if (destIconsFolder == null || !destIconsFolder.exists()) {
					System.out.println("Cannot find destination icons folder.");
					return;
				}
				IconsUpdater updater = new IconsUpdater(rs, cr, selectedFile, srcIconsFolder, destIconsFolder, true);
//				ProgressMonitorDialog dialogUpdate = new ProgressMonitorDialog(_shell);
//				dialogUpdate.run(true, true, updater);
				updater.run(new NullProgressMonitor());
				mcsInt += updater.getHandledMetaclassesNb();
				createdInt += updater.getCreatedIconsNb();
			}
			Integer mcs = new Integer(mcsInt);
			Integer created = new Integer(createdInt);
			String msg = UpdateIconsUtil.buildString("Found ", mcs, " concrete metaclasses, created ", created, " icons.");
			System.out.println(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action_p, ISelection selection_p) {
		if (selection_p instanceof IStructuredSelection) {
			_selection = (IStructuredSelection) selection_p;
		} else {
			_selection = null;
		}
	}

	private List<IFile> getValidSelectedFiles() {
		List<IFile> result = new ArrayList<IFile>();
		if (_selection != null) {
			@SuppressWarnings("unchecked")
			Iterator<Object> it = _selection.iterator();
			while (it.hasNext()) {
				Object selected = it.next();
				if (selected instanceof IFile) {
					IFile file = (IFile) selected;
					if (fileIsValid(file))
						result.add(file);
				}
			}
		}
		return result;
	}

	private boolean fileIsValid(IFile file_p) {
		return "ecore".equals(file_p.getFileExtension());
	}

	private IProject getDestinationProject(IProject sourceProject_p) {
		IProject result = null;
		String spName = sourceProject_p.getName();
		String[] nameParts = spName.split("\\" + __SEPARATOR);
		IWorkspace wk = sourceProject_p.getWorkspace();
		IWorkspaceRoot root = wk.getRoot();
		int length = nameParts.length - __DATA_PROJECT_SUFFIX_LENGTH;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append(nameParts[i]);
			builder.append(__SEPARATOR);
		}
		builder.append(__ICONS_PROJECT_SUFFIX);
		String dpName = builder.toString();
		result = root.getProject(dpName);
		return result;
	}

	private IPath makePath(String[] pathParts) {
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		for (String pathPart : pathParts) {
			if (first)
				first = false;
			else
				builder.append("/"); //$NON-NLS-1$
			builder.append(pathPart);
		}
		IPath result = new Path(builder.toString());
		return result;
	}

	private IFolder getSourceIconsFolder(IProject project_p) {
		IFolder result = null;
		IPath sifPath = makePath(__SOURCE_ICONS_FOLDER_PATH);
		IResource res = project_p.findMember(sifPath);
		if (res instanceof IFolder)
			result = (IFolder) res;
		return result;
	}

	private IFolder getDestinationIconsFolder(IProject project_p) {
		IFolder result = null;
		IPath sifPath = makePath(__DESTINATION_ICONS_FOLDER_PATH);
		IResource res = project_p.findMember(sifPath);
		if (res instanceof IFolder)
			result = (IFolder) res;
		return result;
	}

	private void clearFolder(IFolder folder_p) {
		try {
			IResource[] members = folder_p.members();
			for (IResource member : members) {
				member.delete(true, null);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

}
