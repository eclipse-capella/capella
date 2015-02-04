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
package org.polarsys.capella.test.framework.api;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.common.mdsofa.common.helper.ProjectHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Generic implementation of a test case.<br>
 * This implementation supports libraries as test models.
 * 
 * @author Erwan Brottier
 */
public abstract class BasicTestCase extends AbstractExtendedTest {

  private File pluginFolder;

  public BasicTestCase() {
    super();
    setName(getName()); // the name of the method implementing the test is fixed.
  }

  protected String getTestCasesRootFolderName() {
    return "model"; //$NON-NLS-1$
  }

  protected IFile getAirdFileForLoadedModel(String modelName) {
    return IResourceHelpers.getEclipseProjectInWorkspace(modelName).getFile(modelName + "." + CapellaResourceHelper.AIRD_FILE_EXTENSION); //$NON-NLS-1$
  }
  
  /**
   * Returns the plugin ID of the current test plugin.<br>
   * Should be overriden in an abstract class whose all test cases in a test plugin inherit.
   */
  protected abstract String getPluginId();

  /** The implementation of this test case. Must be overridden. */
  protected abstract void test() throws Exception;

  /** For presentation purpose in the JUnit view */
  @Override
  public String getName() {
    return "test" + getClass().getSimpleName(); //$NON-NLS-1$
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Reset context before the test case begins:<br>
   * - remove all projects in the workspace,<br>
   * - reset resource set.<br>
   *
   * @see org.polarsys.capella.test.framework.api.AbstractExtendedTest#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
//    System.out.println("-------------------------------"); //$NON-NLS-1$
//    System.out.println("> " + getName()); //$NON-NLS-1$
//    System.out.println("-------------------------------"); //$NON-NLS-1$
//    System.out.println();
    // remove all eclipse projects in the workspace.
//    try {
//      for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
//        project.delete(true, new NullProgressMonitor());
//      }
//    } catch (CoreException exception_p) {
//      exception_p.printStackTrace();
//    }
//
//    // reset resource set
//    for (TransactionalEditingDomain domain : ExecutionManagerRegistry.getInstance().getAllEditingDomains()) {
//      for (Resource res : domain.getResourceSet().getResources()) {
//        res.unload();
//      }
//    }
  }

  // ///////////////////////////////////
  // Helpers to enable test context //
  // /////////////////////////////////

//  /**
//   * Copy an eclipse project from the test data to the workspace.
//   * @return the new eclipse project in the workspace.
//   * @param eclipseProjectFolderInTestPlugin : folder that contains all Capella projects for test (relative to the test plugin project).
//   * @param projectName : the project folder name PRECONDITION : melodymodeller and aird files must have the same name as the projectName
//   */
//  protected IProject cloneProjectFromTestDataToWorkspace(String eclipseProjectsFolderInTestPlugin, String projectName) {
//    if (!eclipseProjectsFolderInTestPlugin.endsWith("/")) //$NON-NLS-1$
//      eclipseProjectsFolderInTestPlugin += "/"; //$NON-NLS-1$
//    String relativeModelFolder = eclipseProjectsFolderInTestPlugin + projectName + "/";//$NON-NLS-1$
//    String melodyModellerFileName = projectName + "." + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION;//$NON-NLS-1$
//    String airdFileName = projectName + "." + CapellaResourceHelper.AIRD_FILE_EXTENSION;//$NON-NLS-1$
//    return TestHelper.createCapellaProject(projectName, relativeModelFolder + melodyModellerFileName,
//        relativeModelFolder + airdFileName);
//  }

  /**
   * Copy an eclipse project from the test data to the workspace.
   * @return the new eclipse project in the workspace.
   * @param testProjectsRelativeFolder : folder that contains all Capella projects for test (relative to the test plugin project).
   * @param projectName : the project name to load (must correspond to the folder in testProjectsRelativeFolder).
   * @throws IOException 
   */
  protected IProject loadTestProjectInWorkspace(String projectFolderInTestPlugin, String projectName) throws IOException {
    File sourceFolder = getFileOrFolderInTestPlugin(projectFolderInTestPlugin);
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();    
    File targetFolder = new File(root.getRawLocation().toString()+"/"+projectName+"/"); //$NON-NLS-1$ //$NON-NLS-2$
    TestHelper.copy(sourceFolder, targetFolder);
    IProject project = TestHelper.createCapellaProject(projectName);
    ProjectHelper.refreshProject(project, new NullProgressMonitor());
    return project;
  }

  
  protected void copyTestDataFolderInWorkspace(String relativeFolderInTestPluginToCopy, IContainer target)
      throws IOException {
    URL fileURL = FileHelper.getFileFullUrl(getPluginId() + "/" + relativeFolderInTestPluginToCopy + "/"); //$NON-NLS-1$ //$NON-NLS-2$
    File sourceFile = new File(fileURL.getFile());
    File targetFile = new File(target.getLocation().toOSString() + "/"); //$NON-NLS-1$
    TestHelper.copy(sourceFile, targetFile);
  }

  /** Return the root folder of the current test plugin */
  protected File getPluginFolder() {
    if (pluginFolder == null) {
      pluginFolder = new File(FileHelper.getFileFullUrl(getPluginId() + "/").getFile()); //$NON-NLS-1$
    }
    return pluginFolder;
  }

  /**
   * Return the first file or folder found in the given folder or sub folders
   * with the given name.
   */
  protected File getFileBeyond(File rootFolder, String fileName) {
    for (File file : rootFolder.listFiles()) {
      if (file.isFile() && file.getName().equals(fileName)) {
        return file;
      } else if (file.isDirectory()) {
        File res = getFileBeyond(file, fileName);
        if (res != null) {
          return res;
        }
      }
    }
    return null;
  }

  protected File getFileOrFolderInTestPlugin(String relativePath) {
    return new File(getPluginFolder().toString() + "/" + relativePath); //$NON-NLS-1$
  }

}
