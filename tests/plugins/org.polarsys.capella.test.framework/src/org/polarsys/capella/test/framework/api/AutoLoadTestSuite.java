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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.FileLocator;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/** 
 * Generic implementation of a test suite. This implementation supports libraries as test models.
 * 
 * @author Erwan Brottier
 */
public abstract class AutoLoadTestSuite extends BasicTestSuite {

	private String testCaseRelativeFolder;
	private File testCaseFolder;
	
  /**
   * Returns the root package of all BasicTestCases in the current test plugin. The current test suite will instantiate all classes in this package and sub ones
   * as BasicTestCase.
   */
  protected abstract String getTestCasesRootPackage();
	
  /** 
   * load all the test cases in test cases root package and sub ones. 
   */
  @Override
  protected List<BasicTestCase> getTests() {
    List<BasicTestCase> list = new ArrayList<BasicTestCase>();
    File testCasefolder = getTestCasefolder();
    List<File> testCaseFiles = getRecursivelyFilesInFolder(testCasefolder);
    for (File file : testCaseFiles) {
      BasicTestCase test = instanciateTest(file);
      if (test != null) {
        list.add(test);
      }
    }
    return list;
  }

  protected File getTestCasefolder() {
    if (testCaseFolder == null) {
    	testCaseFolder = new File(getPluginFolder().toString() + "/src/" + getTestRelativePath()); //$NON-NLS-1$
    }
    return testCaseFolder;
  }
  
  private File pluginFolder;
  
  /** Return the root folder of the current test plugin */
  protected File getPluginFolder() {
    if (pluginFolder == null) {
  		Bundle bundle = FrameworkUtil.getBundle(getClass());
  		URL fileURL = bundle.getEntry("/");    	 //$NON-NLS-1$
  		try {
  			pluginFolder = new File(FileLocator.resolve(fileURL).toURI());
			} catch (URISyntaxException e1) {
			    e1.printStackTrace();
			} catch (IOException e1) {
			    e1.printStackTrace();
			}      
    }
    return pluginFolder;
  }

  protected String getTestRelativePath() {
    if (testCaseRelativeFolder == null) {
      testCaseRelativeFolder = StringUtils.replace(getTestCasesRootPackage(), ".", "/") + "/";
    }
    return testCaseRelativeFolder;
  }
  
  protected BasicTestCase instanciateTest(File file) {
    try {
      String[] tab = StringUtils.split(file.getName(), '.');
      if (tab.length == 2) {
        String fileName = tab[0];
        String fileExtension = tab[1];
        if (fileExtension.equals("java")) {
          tab = StringUtils.replace(file.getParentFile().toString() + "/", "\\", "/").split(getTestRelativePath());
          String qualifiedClassName = getTestCasesRootPackage() + ".";
          if (tab.length > 1) {
            qualifiedClassName += StringUtils.replace(tab[1], "/", ".") + fileName;
          } else {
            qualifiedClassName += fileName;
          }
          return (BasicTestCase) getClass().getClassLoader().loadClass(qualifiedClassName).getConstructors()[0].newInstance(new Object[] {});
        }
      }
    } catch (Exception exception) {
      // we ignore the test
    }
    return null;
  }
  
  private List<File> getRecursivelyFilesInFolder(File folder) {
    List<File> files = new ArrayList<File>();
    for (File file : folder.listFiles()) {
      if (!file.isDirectory()) {
        files.add(file);
      } else {
        files.addAll(getRecursivelyFilesInFolder(file));
      }
    }
    return files;
  }

}
