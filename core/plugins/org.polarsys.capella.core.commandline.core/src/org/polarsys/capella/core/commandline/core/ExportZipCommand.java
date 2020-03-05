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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.app.IApplicationContext;

/**
 *
 */
public class ExportZipCommand extends DefaultCommandLine {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean execute(IApplicationContext context) throws CommandLineException {

    String name = argHelper.getExportProject();

    DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
    String zipName = argHelper.getZipNameProject();
    zipName = zipName.replace("qualifier", dateFormat.format(new Date()));

    for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
      if ((name == null) || name.equals(project.getName())) {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IFile file = workspace.getRoot().getFile(new Path(zipName));
        IProject newProject = workspace.getRoot().getProject(project.getName());
        WorkbenchHelper.exportZipFile(newProject, file);
      }
    }
    return super.execute(context);
  }
}
