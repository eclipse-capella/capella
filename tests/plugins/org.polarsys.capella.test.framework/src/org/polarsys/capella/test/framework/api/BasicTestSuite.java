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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.framework.helpers.PerformanceHelper;

import junit.framework.AssertionFailedError;
import junit.framework.TestResult;
import junit.framework.TestSuite;

/**
 * Generic implementation of a test suite. This implementation supports libraries as test models.
 *
 * @author Erwan Brottier
 */
public abstract class BasicTestSuite extends TestSuite implements BasicTestArtefact {

  protected BasicTestSuite parentTestSuite;
  private long executionDurationInMillis;

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

  protected BasicTestSuite() {
    // Add all contained test cases.
    for (BasicTestArtefact testArtefact : getTests()) {
      addTest(testArtefact);
      testArtefact.setParentTestSuite(this);
    }
  }

  @Override
  public String getName() {
    return getClass().getName();
  }

  /**
   * Runs the tests and collects their result in a TestResult.
   */
  @Override
  public void run(TestResult result) {
    try {
      setUp();

      executionDurationInMillis = PerformanceHelper.getTimeInMillis();
      super.run(result);
      executionDurationInMillis = PerformanceHelper.getTimeInMillis() - executionDurationInMillis;

    } catch (AssertionFailedError e) {
      result.addFailure(this, e);
    } catch (ThreadDeath e) { // don't catch ThreadDeath by accident
      throw e;
    } catch (Throwable e) {
      result.addError(this, e);
    } finally {
      try {
        tearDown();
      } catch (AssertionFailedError e) {
        result.addFailure(this, e);
      } catch (ThreadDeath e) { // don't catch ThreadDeath by accident
        throw e;
      } catch (Throwable e) {
        result.addError(this, e);
      }
    }
  }

  @Override
  public long getExecutionDuration() {
    return executionDurationInMillis;
  }

  /**
   * Set up this test suite during test execution.<br>
   * Default implementation does nothing
   */
  protected void setUp() throws Exception {
    // require test models
    List<String> projectNamesToLoad = getRequiredTestModels();
    if (projectNamesToLoad != null && !projectNamesToLoad.isEmpty()) {
      ModelProviderHelper.getInstance().getModelProvider().requireTestModel(projectNamesToLoad, this); // $NON-NLS-1$
    }
  }

  /**
   * Dispose the test suite environment after its run.<br>
   * Default implementation does nothing
   */
  protected void tearDown() throws Exception {
    // release test models
    List<String> projectNamesToLoad = getRequiredTestModels();
    if (projectNamesToLoad != null && !projectNamesToLoad.isEmpty()) {
      for (String modelName : projectNamesToLoad) {
        ModelProviderHelper.getInstance().getModelProvider().releaseTestModel(modelName, this);
      }
    }
    getTests().clear();
  }

  /**
   * @return description about this test suite
   */
  public String getDescription() {
    return CommonTestMessages.noDescriptionAvailable;
  }

  /** load all the test cases in test cases root package and sub ones. */
  protected abstract List<BasicTestArtefact> getTests();

  private static final String INPUT_MODEL_FOLDER_NAME = "model"; //$NON-NLS-1$

  protected String getRelativeModelsFolderName() {
    return INPUT_MODEL_FOLDER_NAME; // $NON-NLS-1$
  }

  /**
   * Look for an existing folder in the plugin containing the current "real" class.<br>
   * Then, if none is found, look in plugins containing super classes.<br>
   * If no existing folder is found, return a path in the plugin containing the current "real" class.
   *
   * @param relativePath
   * @return
   */
  @Override
  public File getFolderInTestModelRepository(String relativePath) {
    String pathInPlugin = getRelativeModelsFolderName() + "/" + relativePath;
    Class<?> currentClass = getClass();
    while (currentClass != BasicTestSuite.class) {
      File testModelFolder = IResourceHelpers.getFileOrFolderInTestPlugin(currentClass, pathInPlugin);// $NON-NLS-1$
      if (testModelFolder.exists() && testModelFolder.isDirectory()) {
        return testModelFolder;
      }
      currentClass = currentClass.getSuperclass();
    }
    return IResourceHelpers.getFileOrFolderInTestPlugin(getClass(), pathInPlugin);// $NON-NLS-1$
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Collections.emptyList();
  }

}
