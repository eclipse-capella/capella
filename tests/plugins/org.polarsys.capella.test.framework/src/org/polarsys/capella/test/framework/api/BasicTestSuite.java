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
import java.net.URISyntaxException;
import java.util.List;

import junit.framework.TestResult;
import junit.framework.TestSuite;

import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.framework.helpers.PerformanceHelper;

/**
  * Generic implementation of a test suite. This implementation supports
  * libraries as test models.
  * 
  * @author Erwan Brottier
  */
public abstract class BasicTestSuite extends TestSuite implements BasicTestArtefact {

	protected BasicTestSuite parentTestSuite;
	private File pluginFolder;
	private long executionDurationInMillis;
	
	@Override
	public void setParentTestSuite(BasicTestSuite parentTestSuite) {
		this.parentTestSuite = parentTestSuite;
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
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalError(e.toString());
		}
		try {
			executionDurationInMillis = PerformanceHelper.getTimeInMillis();			
			super.run(result);
			executionDurationInMillis = PerformanceHelper.getTimeInMillis() - executionDurationInMillis;
		} finally {
			try {
				tearDown();
			} catch (Exception e) {
				e.printStackTrace();
				throw new InternalError(e.toString());
			}			
		}
	}
	
  @Override
  public long getExcutionDuration() {
  	return executionDurationInMillis;
  }

  
  /**
   * Set up this test suite during test execution.<br>
   * Default implementation does nothing
   */
  protected void setUp() throws Exception {
    // require test models
    List<String> projectNamesToLoad = getRequiredTestModels();
    if (projectNamesToLoad != null)
	    for (String modelName : projectNamesToLoad)
	    	ModelProvider.requireTestModel(modelName, this); //$NON-NLS-1$
  }
  
  /**
   * Dispose the test suite environment after its run.<br>
   * Default implementation does nothing
   */
  protected void tearDown() throws Exception {
    // release test models
    List<String> projectNamesToLoad = getRequiredTestModels();
    if (projectNamesToLoad != null) {
    	for (String modelName : projectNamesToLoad) {
    		ModelProvider.releaseTestModel(modelName, this);
    	}
    }
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
  	return INPUT_MODEL_FOLDER_NAME; //$NON-NLS-1$
  }

  
  protected File getFileOrFolderInTestPlugin(String relativePath) {
    return new File(getPluginFolder().toString() + "/" + relativePath); //$NON-NLS-1$
  }
  
  public File getFileOrFolderInTestModelRepository(String relativePath) {
    return getFileOrFolderInTestPlugin(getRelativeModelsFolderName()+ "/" + relativePath);//$NON-NLS-1$
  }
  
  /** Return the root folder of the current test plugin */
  protected File getPluginFolder() {
    if (pluginFolder == null) {
  		try {
  			pluginFolder = IResourceHelpers.getFileInPlugin(getClass(), "/"); //$NON-NLS-1$
			} catch (URISyntaxException e1) {
			    e1.printStackTrace();
			} catch (IOException e1) {
			    e1.printStackTrace();
			}
  		//pluginFolder = new File(FileHelper.getFileFullUrl(getPluginId() + "/").getFile()); //$NON-NLS-1$
    }
    return pluginFolder;
  }
}
