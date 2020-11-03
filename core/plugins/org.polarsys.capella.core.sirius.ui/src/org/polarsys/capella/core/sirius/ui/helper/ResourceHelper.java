/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.SiriusUIPlugin;

public class ResourceHelper {

  private ResourceHelper() {
    // Do nothing
  }

  /**
   * 
   * @param project
   * @return All the main aird files inside the given project.
   */
  public static Collection<IFile> getAirdFilesToOpen(IProject project) {
    return collectFiles(project, new Predicate<String>() {
      @Override
      public boolean test(String fileExtension) {
        return CapellaResourceHelper.AIRD_FILE_EXTENSION.equals(fileExtension);
      }
    });
  }

  /**
   * 
   * @param container
   * @return A collection of image files which are supported by Sirius.
   */
  public static Collection<IFile> collectImageFiles(IContainer container) {

    return collectFiles(container, new Predicate<String>() {
      @Override
      public boolean test(String fileExtension) {
        return isSupportedImageFormat(fileExtension);
      }
    });
  }

  public static Collection<IFile> collectFiles(IContainer container) {
    return collectFiles(container, x -> true);
  }

  private static Collection<IFile> collectFiles(IContainer container, Predicate<String> predicate) {
    List<IFile> result = new ArrayList<>();
    if (container.isAccessible()) {
      try {
        for (IResource resource : container.members()) {
          switch (resource.getType()) {
          case IResource.FILE:
            String fileExtension = resource.getFileExtension();
            if (predicate.test(fileExtension)) {
              result.add((IFile) resource);
            }
            break;
          case IResource.FOLDER:
            IFolder subFolder = (IFolder) resource;
            result.addAll(collectFiles(subFolder, predicate));
            break;
          default:
            break;
          }
        }
      } catch (CoreException e) {
        IStatus status = new Status(e.getStatus().getSeverity(), SiriusUIPlugin.getDefault().getPluginId(),
            "Erros while collecting files!", e);
        SiriusUIPlugin.getDefault().getLog().log(status);
      }
    }
    return result;
  }

  public static boolean isCustomizedWorkspaceImageWorkspacePath(EObject obj) {
    if (obj instanceof WorkspaceImage) {
      WorkspaceImage workspaceImage = (WorkspaceImage) obj;
      return workspaceImage.getCustomFeatures()
          .contains(DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName());
    }
    return false;
  }

  /**
   * Tell if the specified fileExtension is one of a supported image format.
   * 
   * @param fileExtension
   *          the file extension of a file.
   * @return true the specified file extension if one of a supported image format.
   */
  private static boolean isSupportedImageFormat(String fileExtension) {
    boolean isSupportedImageFormat = false;
    for (ImageFileFormat element : ImageFileFormat.VALUES) {
      if (element.getName().equalsIgnoreCase(fileExtension)) {
        isSupportedImageFormat = true;
        break;
      }
    }
    return isSupportedImageFormat;
  }
}
