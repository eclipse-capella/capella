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

import java.util.List;

import junit.framework.Test;

/**
 * Generic implementation of a test suite. This implementation supports
 * libraries as test models.
 * 
 * @author Erwan Brottier
 */
public abstract class BasicTestSuite extends AbstractExtendedTestSuite {

//  private String testCaseRelativeFolder;
//  private File testCaseFolder;

  /**
   * Returns the root package of all BasicTestCases in the current test plugin.
   * The current test suite will instantiate all classes in this package and sub
   * ones as BasicTestCase.
   */
  //protected abstract String getTestCasesRootPackage();

  /** Returns the plugin ID of the current test plugin. */
  protected abstract String getPluginId();

  /** load all the test cases in test cases root package and sub ones. */
  @Override
  protected abstract List<? extends Test> getTests();

//  protected String getTestRelativePath() {
//    if (testCaseRelativeFolder == null) {
//      testCaseRelativeFolder = StringUtils.replace(getTestCasesRootPackage(), ".", "/") + "/";
//    }
//    return testCaseRelativeFolder;
//  }

//  protected File getTestCasefolder() {
//    if (testCaseFolder == null) {
//      URL fileURL = FileHelper.getFileFullUrl(getPluginId() + "/src/" + getTestRelativePath());
//      String path = fileURL.getFile();
//      try {
//        path = URLDecoder.decode(path, "utf-8");
//        testCaseFolder = new File(path);
//      } catch (UnsupportedEncodingException exception) {
//        exception.printStackTrace();
//      }
//    }
//    return testCaseFolder;
//  }

//  protected BasicTestCase instanciateTest(File file) {
//    try {
//      String[] tab = StringUtils.split(file.getName(), '.');
//      if (tab.length == 2) {
//        String fileName = tab[0];
//        String fileExtension = tab[1];
//        if (fileExtension.equals("java")) {
//          tab = StringUtils.replace(file.getParentFile().toString() + "/", "\\", "/").split(getTestRelativePath());
//          String qualifiedClassName = getTestCasesRootPackage() + ".";
//          if (tab.length > 1) {
//            qualifiedClassName += StringUtils.replace(tab[1], "/", ".") + fileName;
//          } else {
//            qualifiedClassName += fileName;
//          }
//          return (BasicTestCase) getClass().getClassLoader().loadClass(qualifiedClassName).getConstructors()[0]
//              .newInstance(new Object[] {});
//        }
//      }
//    } catch (Exception exception) {
//      // we ignore the test
//    }
//    return null;
//  }

//  protected BasicTestCase instanciateTest(String relativeFilePath) {
//    File file = new File(getTestCasefolder().getAbsolutePath() + "/" + relativeFilePath);
//    return instanciateTest(file);
//  }
}
