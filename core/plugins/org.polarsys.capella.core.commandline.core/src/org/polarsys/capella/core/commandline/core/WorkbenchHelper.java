/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.dialogs.IOverwriteQuery;
import org.eclipse.ui.internal.wizards.datatransfer.ZipLeveledStructureProvider;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;

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
        public boolean visit(IResource resource_p) throws CoreException {
          final byte[] buffer = new byte[1024];
          try {
            if (resource_p instanceof IFile) {
              ZipEntry ze = new ZipEntry(resource_p.getFullPath().toString().substring(1));
              zos.putNextEntry(ze);
              FileInputStream in = new FileInputStream(resource_p.getLocation().toOSString());
              int len;
              while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
              }
              in.close();
              zos.closeEntry();
            }
          } catch (IOException exception_p) {
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

  public static Collection<IProject> importZipFile(IFile theZipFile) {
    try {

      ZipFile zipFile = new ZipFile(theZipFile.getFullPath().toOSString());
      IOverwriteQuery overwriteQuery = new IOverwriteQuery() {
        public String queryOverwrite(String file) {
          return ALL;
        }
      };

      ZipLeveledStructureProvider provider = new ZipLeveledStructureProvider(zipFile) {

        /**
         * {@inheritDoc}
         */
        @Override
        public List getChildren(Object element_p) {

          List parrent = super.getChildren(element_p);
          if (parrent == null) {
            return Collections.emptyList();
          }
          return parrent;
        }

      };

      List<IProject> projects = new ArrayList<IProject>();
      List<Object> fileSystemObjects = new ArrayList<Object>();

      HashMap<String, ArrayList<Object>> maps = new HashMap<String, ArrayList<Object>>();

      //Create all IProject stored in the zip
      Enumeration<? extends ZipEntry> entries = zipFile.entries();
      while (entries.hasMoreElements()) {
        ZipEntry entry = entries.nextElement();
        if (entry.getName().endsWith(".project")) {
          IProjectDescription description = ResourcesPlugin.getWorkspace().loadProjectDescription(zipFile.getInputStream(entry));
          IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
          maps.put(project.getName(), new ArrayList<Object>());
          if (!project.exists()) {
            project.create(null);
          }
          if (!project.isOpen()) {
            project.open(null);
          }

        }
      }

      //Dispatch files of the zip in each IProject
      entries = zipFile.entries();
      while (entries.hasMoreElements()) {
        ZipEntry entry = entries.nextElement();
        if ((entry.isDirectory())) {
          continue;
        }
        if (maps.size() == 1) {
          maps.get(maps.keySet().iterator().next()).add(entry);
        } else {
          for (String map : maps.keySet()) {
            if (entry.getName().startsWith(map)) {
              maps.get(map).add(entry);
            }
          }
        }
      }

      //Import files for all retrieved IProjects
      for (String map : maps.keySet()) {
        List<Object> files = maps.get(map);

        ImportOperation importOperation =
            new ImportOperation(ResourcesPlugin.getWorkspace().getRoot().getFullPath(), new ZipEntry(map), provider, overwriteQuery, files);
        importOperation.setCreateContainerStructure(true);
        importOperation.run(new NullProgressMonitor());
        projects.add(ResourcesPlugin.getWorkspace().getRoot().getProject(map));
      }

      return projects;

    } catch (Exception exception) {
      exception.printStackTrace();
      return Collections.emptyList();
    }
  }
}
