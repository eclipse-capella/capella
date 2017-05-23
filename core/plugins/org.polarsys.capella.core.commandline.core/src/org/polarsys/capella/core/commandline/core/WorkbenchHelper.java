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
package org.polarsys.capella.core.commandline.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;

/**
 *
 */
public class WorkbenchHelper {

  public static void exportZipFile(IResource resource, IFile theZipFile) {
    try {

      final FileOutputStream fos = new FileOutputStream(theZipFile.getLocation().toOSString());
      final ZipOutputStream zos = new ZipOutputStream(fos);

      IResourceVisitor visitor = new IResourceVisitor() {

        @Override
        public boolean visit(IResource resource) throws CoreException {
          final byte[] buffer = new byte[1024];
          try {
            if (resource instanceof IFile) {
              ZipEntry ze = new ZipEntry(resource.getFullPath().toString().substring(1));
              zos.putNextEntry(ze);
              FileInputStream in = new FileInputStream(resource.getLocation().toOSString());
              int len;
              while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
              }
              in.close();
              zos.closeEntry();
            }
          } catch (IOException exception) {
        	//Catch exception silently,
          }
          return true;
        }
      };

      try {
        resource.accept(visitor);
        //remember close it

      } finally {

        zos.close();
      }

    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
