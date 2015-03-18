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

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.model.CapellaLibraryExt;

import com.google.common.io.Files;

/**
 * Helper for writing JUnit tests for RCP application.
 */
public class TestHelper {

  /**
   * Capella project nature. Copied from CapellaNature.ID
   */
  public static final String CAPELLA_PROJECT_NATURE = "org.polarsys.capella.project.nature"; //$NON-NLS-1$

  public static Project getProjectFromMelodyModeller(IFile melodyModellerFile) {
    URI uri = URI.createPlatformResourceURI(melodyModellerFile.getFullPath().toOSString(), true);
    ExecutionManager execManager = ExecutionManagerRegistry.getInstance().addNewManager();    
    ResourceSet rsMelody = execManager.getEditingDomain().getResourceSet();
    Resource rMelody = rsMelody.getResource(uri, true);
    EcoreUtil.resolveAll(rMelody);
    return CapellaLibraryExt.getProject(rMelody);
  }  
//
  /**
   * Create an empty capella project with given name.
   * 
   * @param name_p
   *          project name.
   * @return a not <code>null</code> project.
   */
  public static IProject createCapellaProject(String name_p) {
    // Get the workspace.
    IWorkspace workspace = ResourcesPlugin.getWorkspace();

    // Get a project for given name.
    IProject project = workspace.getRoot().getProject(name_p);
    // Should not exist, workspace is supposed to be cleaned before the
    // tests execution.
    if (!project.exists()) {
      try {
        IProjectDescription description = workspace.newProjectDescription(name_p);
        // Add the project nature.
        description.setNatureIds(new String[] { CAPELLA_PROJECT_NATURE });
        project.create(description, new NullProgressMonitor());
      } catch (CoreException exception_p) {
        exception_p.printStackTrace();
        Assert.fail(exception_p.getMessage());
      }
    }
    // Open the project.
    try {
      project.open(new NullProgressMonitor());
    } catch (CoreException exception_p) {
      exception_p.printStackTrace();
      Assert.fail(exception_p.getMessage());
    }
    return project;
  }

  /**
   * Get the Capella Execution manager.
   * 
   * @return a not <code>null</code> execution manager.
   */
  public static ExecutionManager getExecutionManager(EObject eObject) {
    return TransactionHelper.getExecutionManager(eObject);
  }

  public static void copy(File sourceLocation, File targetLocation) throws IOException {
    if (sourceLocation.isDirectory()) {
      copyDirectory(sourceLocation, targetLocation);
    } else if (!targetLocation.isDirectory()) {
      Files.copy(sourceLocation, targetLocation);
    } else {
      File targetFile = new File(targetLocation.toString() + "/" + sourceLocation.getName()); //$NON-NLS-1$
      Files.copy(sourceLocation, targetFile);
    }
  }

  private static void copyDirectory(File source, File target) throws IOException {
    if (!target.exists()) {
      target.mkdir();
    }

    for (String f : source.list()) {
      copy(new File(source, f), new File(target, f));
    }
  }

}
