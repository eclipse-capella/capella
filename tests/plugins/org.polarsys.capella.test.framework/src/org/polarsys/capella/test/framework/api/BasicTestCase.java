/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.api;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.framework.helpers.PerformanceHelper;
import org.polarsys.capella.test.framework.helpers.TestHelper;

import junit.framework.TestCase;

/**
 * Generic implementation of a test case.<br>
 * <ul>
 * <li>before each test case execution, load in the workspace capella models declared by overriding method
 * getProjectNamesToLoad</li>
 * <li>loaded models are firstly searched in the ones defined by the test suite, then by the test case</li>
 * </ul>
 * - before each test case execution, load
 * 
 * @author Erwan Brottier
 */
public abstract class BasicTestCase extends TestCase implements BasicTestArtefact {

  private static final String TEST_METHOD_NAME = "test"; //$NON-NLS-1$
  private static final String INPUT_MODEL_FOLDER_NAME = "model"; //$NON-NLS-1$

  private long executionDurationInMillis;
  protected BasicTestSuite parentTestSuite;

	@Override
	public void setParentTestSuite(BasicTestSuite parentTestSuite) {
		this.parentTestSuite = parentTestSuite;
	}

  @Override
  public BasicTestSuite getParentTestSuite() {
    return this.parentTestSuite;
  }
  
  @Override
  public List<BasicTestSuite> getAllParentTestSuites() {
    if (this.parentTestSuite == null) {
      return Collections.emptyList();
    }
    return Stream
        .concat(Arrays.asList(this.parentTestSuite).stream(), this.parentTestSuite.getAllParentTestSuites().stream())
        .collect(Collectors.toList());
  }

  /*
   * For presentation purpose in the JUnit view and because we freeze the test method name, we return the class name
   */
  @Override
  public String getName() {
    return getClass().getSimpleName();
  }

  /*
   * copy of default implementation where variable fName (value returned by method getName()) is replaced by
   * TEST_METHOD_NAME
   */
  @Override
  protected void runTest() throws Throwable {
    assertNotNull("test method must be defined", TEST_METHOD_NAME); // Some VMs crash when calling getMethod(null,null); //$NON-NLS-1$
    Method runMethod = null;
    try {
      runMethod = getClass().getMethod(TEST_METHOD_NAME, (Class[]) null);
    } catch (NoSuchMethodException e) {
      fail("Method \"" + TEST_METHOD_NAME + "\" not found"); //$NON-NLS-1$//$NON-NLS-2$
    }
    if (!Modifier.isPublic(runMethod.getModifiers())) {
      fail("Method \"" + TEST_METHOD_NAME + "\" should be public"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    try {
      executionDurationInMillis = PerformanceHelper.getTimeInMillis();
      runMethod.invoke(this);
      executionDurationInMillis = PerformanceHelper.getTimeInMillis() - executionDurationInMillis;
    } catch (InvocationTargetException e) {
      e.fillInStackTrace();
      throw e.getTargetException();
    } catch (IllegalAccessException e) {
      e.fillInStackTrace();
      throw e;
    }
  }

  @Override
  public long getExecutionDuration() {
    return executionDurationInMillis;
  }

  /** The default value is defined by constant INPUT_MODEL_FOLDER_NAME */
  protected String getTestCasesRootFolderName() {
    return INPUT_MODEL_FOLDER_NAME;
  }

  protected IFile getAirdFileForLoadedModel(String modelName) {
    return ModelProviderHelper.getInstance().getModelProvider().getAirdFileForLoadedModel(modelName);
  }

  protected IFile getCapellaFileForLoadedModel(String modelName) {
    return IResourceHelpers.getEclipseProjectInWorkspace(modelName).getFile(
        modelName + "." + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION); //$NON-NLS-1$
  }

  // /**
  // * Returns the plugin ID of the current test plugin.<br>
  // * Should be overriden in an abstract class whose all test cases in a test
  // plugin inherit.
  // */
  // protected abstract String getPluginId();

  /**
   * The implementation of this test case. Must be overridden.<br>
   * Notice it is public because JUnit expect it.
   */
  public abstract void test() throws Exception;

	protected CapellaModel getTestModel(String relativeModelPath) {
		return ModelProviderHelper.getInstance().getModelProvider().getTestModel(relativeModelPath, this);
	}

  protected Session getSession(String relativeModelPath) {
    return getSessionForTestModel(relativeModelPath);
  }

  protected Session getSessionForTestModel(String relativeModelPath) {
    return ModelProviderHelper.getInstance().getModelProvider().getSessionForTestModel(relativeModelPath, this);
  }

  protected IProject getEclipseProjectForTestModel(String relativeModelPath) {
    return AbstractProvider.getEclipseProjectForTestModel(relativeModelPath, this);
  }

  protected String getRelativeModelsFolderName() {
    return INPUT_MODEL_FOLDER_NAME;
  }

  public static BasicTestCase currentTest;
  
  /**
   * Set context before the test case begins:
   * <ul>
   * <li>load all required models in the workspace as eclipse project.</li>
   * </ul>
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    currentTest = this;
    TestHelper.disableAutoSaveJob();
    List<String> projectNamesToLoad = getRequiredTestModels();
    if (projectNamesToLoad != null) {
      ModelProviderHelper.getInstance().getModelProvider().requireTestModel(projectNamesToLoad, this);
    }
    GuiActions.flushASyncGuiJobs();
  }

  @Override
  protected void tearDown() throws Exception {
    try {
      super.tearDown();
      // release test models
      List<String> projectNamesToLoad = getRequiredTestModels();
      if (projectNamesToLoad != null) {
        for (String modelName : projectNamesToLoad) {
          ModelProviderHelper.getInstance().getModelProvider().releaseTestModel(modelName, this);
        }
      }
    } finally {
      TestHelper.disposeObject(this);
    }
    GuiActions.flushASyncGuiJobs();
  }

	protected void copyTestDataFolderInWorkspace(String relativeFolderInTestPluginToCopy, IContainer target)
			throws IOException {
		//    URL fileURL = FileHelper.getFileFullUrl(getPluginId() + "/" + relativeFolderInTestPluginToCopy + "/"); //$NON-NLS-1$ //$NON-NLS-2$
		// File sourceFile = new File(fileURL.getFile());
		File sourceFile = new File(getPluginFolder().toString() + "/" + relativeFolderInTestPluginToCopy + "/"); //$NON-NLS-1$ //$NON-NLS-2$
		File targetFile = new File(target.getLocation().toOSString() + "/"); //$NON-NLS-1$
		TestHelper.copy(sourceFile, targetFile);
	}

	protected File getPluginFolder() {
	  return IResourceHelpers.getPluginFolder(getClass());
	}

  /**
   * Return the first file or folder found in the given folder or sub folders with the given name.
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

	/**
	 * Get file or folder in the plugin containing the current "real" class
	 * @param relativePath
	 * @return
	 */
  protected File getFileOrFolderInTestPlugin(String relativePath) {
    return IResourceHelpers.getFileOrFolderInTestPlugin(getClass(), relativePath);
  }

  /**
   * Look for an existing folder in the plugin containing the current "real" class.<br>
   * Then, if none is found, look in plugins containing super classes.<br>
   * If no existing folder is found, return a path in the plugin containing the current "real" class.
   * @param relativePath
   * @return
   */
  @Override
  public File getFolderInTestModelRepository(String relativePath) {
    String pathInPlugin = getRelativeModelsFolderName() + "/" + relativePath;
    Class<?> currentClass = getClass();
    while (currentClass != BasicTestCase.class) {
      File testModelFolder = IResourceHelpers.getFileOrFolderInTestPlugin(currentClass, pathInPlugin);//$NON-NLS-1$
      if (testModelFolder.exists() && testModelFolder.isDirectory()) {
        return testModelFolder;
      }
      currentClass = currentClass.getSuperclass();
    }
    return IResourceHelpers.getFileOrFolderInTestPlugin(getClass(), pathInPlugin);//$NON-NLS-1$
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Collections.emptyList();
  }

}
