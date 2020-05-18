/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.validation.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Useful methods to manage resources in the workspace.
 */
public class CapellaResourceHelper {

  /**
   * Convenience method equivalent to <i>getFiles( project, "*" )</i>.
   * @param project the IProject that interest you.
   * @return all the IFile contained into this project (with no limit in the level).
   */
  public static List<IFile> getFiles(IProject project) {
    return getFiles(project, "*"); //$NON-NLS-1$
  }

  /**
   * Get all the files whose extension is <b>extension</b> and present in <b>project</b>.
   * @param project the IProject that interest you.
   * @param extension the file extension. Use "*" for any extension.
   * @return all the IFile contained into this project (with no limit in the level).
   */
  public static List<IFile> getFiles(IProject project, String extension) {
    List<IFile> result = new ArrayList<IFile>();
    try {
      IResource[] resources = project.members();
      for (IResource resource : resources) {
        switch (resource.getType()) {
          case IResource.FILE:
            String fileExtension = resource.getFileExtension().toLowerCase();
            if (fileExtension.equals(extension) || fileExtension.equals("*")) {
              result.add((IFile) resource);
            }
          break;
          case IResource.FOLDER:
            IFolder subFolder = (IFolder) resource;
            result.addAll(getFiles(subFolder, extension));
          break;
          default:
          break;
        }
      }
    } catch (CoreException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * Convenience method equivalent to <i>getFiles( folder, "*" )</i>.
   * @param folder the IFolder that interest you.
   * @return all the IFile contained into this folder (with no limit in the level).
   */
  public static List<IFile> getFiles(IFolder folder) {
    return getFiles(folder, "*"); //$NON-NLS-1$
  }

  /**
   * Get all the files whose extension is <b>extension</b> and present in <b>folder</b>.
   * @param folder the IFolder that interest you.
   * @param extension the file extension. Use "*" for any extension.
   * @return all the IFile contained into this folder (with no limit in the level).
   */
  public static List<IFile> getFiles(IFolder folder, String extension) {
    List<IFile> result = new ArrayList<IFile>();
    try {
      IResource[] resources = folder.members();
      for (IResource resource : resources) {
        switch (resource.getType()) {
          case IResource.FILE:
            String fileExtension = resource.getFileExtension().toLowerCase();
            if (fileExtension.equals(extension) || fileExtension.equals("*")) {
              result.add((IFile) resource);
            }
          break;
          case IResource.FOLDER:
            IFolder subFolder = (IFolder) resource;
            result.addAll(getFiles(subFolder, extension));
          break;
          default:
          break;
        }
      }
    } catch (CoreException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * Get the file edited in the active editor.
   * @return the file edited in the active editor or null if it could not be retrieved.
   */
  public static IFile getIFileFromEditor() {
    try {
      IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
      IEditorPart editorPart = activeWorkbenchWindow != null ? activeWorkbenchWindow.getActivePage().getActiveEditor() : null;
      IFile editedFile = editorPart != null ? ((FileEditorInput) editorPart.getEditorInput()).getFile() : null;

      return editedFile;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
