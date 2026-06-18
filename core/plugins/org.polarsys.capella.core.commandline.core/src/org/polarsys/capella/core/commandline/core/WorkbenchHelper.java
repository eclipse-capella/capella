/*******************************************************************************
 * Copyright (c) 2006, 2026 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commandline.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;

/**
 * Helper class to transform Workbench data.
 */
public class WorkbenchHelper {
   
  private static final Logger logger = Logger.getLogger(WorkbenchHelper.class.getName());
  
  private WorkbenchHelper () {
    // To hide the implicit public one
  }

  /**
   * Exports the resource in a Zip file.
   * <p>
   * If a resource is a container, included resources is also added to the archive.
   * </p>
   * 
   * @param resource content to compress
   * @param theZipFile target file
   */
  public static void exportZipFile(IResource resource, IFile theZipFile) {
    exportZipFile(List.of(resource), theZipFile.getLocation().toOSString());
  }

  /**
   * Exports set of resources in a Zip file.
   * <p>
   * If a resource is a container, included resources is also added to the archive.
   * </p>
   * 
   * @param sources element to archive
   * @param targetZipFile path of file
   */
  public static void exportZipFile(List<? extends IResource> sources, String targetZipFile) {
    try (final FileOutputStream fos = new FileOutputStream(targetZipFile); final ZipOutputStream zos = new ZipOutputStream(fos)) {

      IResourceVisitor visitor = new IResourceVisitor() {

        @Override
        public boolean visit(IResource resource) throws CoreException {
          final byte[] buffer = new byte[1024];
          try (FileInputStream in = new FileInputStream(resource.getLocation().toOSString())) {
            if (resource instanceof IFile) {
              ZipEntry ze = new ZipEntry(resource.getFullPath().toString().substring(1));
              zos.putNextEntry(ze);
              int len;
              while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
              }
              zos.closeEntry();
            }
          } catch (IOException exception) {
          //Catch exception silently,
          }
          return true;
        }
      };
      
      for (IResource source : sources) {
          source.accept(visitor);
       }
      
    } catch (Exception exception) {
      logger.log(Level.SEVERE, exception.getMessage(), exception);
    }
  }
}
