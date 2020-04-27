/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.business.queries.ju;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

/**
 * @author Erwan Brottier
 */
public class BQTestConstants {

  public static final String PROMPT_STRING = ">>"; //$NON-NLS-1$

  public static final String TEST_SUITE_FILE_EXTENSION = "testSuite"; //$NON-NLS-1$
  public static final String TEST_SUITES_RELATIVE_FOLDER = "testSuite"; //$NON-NLS-1$

  public static final String TEST_CASES_PACKAGE = "{0}/testcases/{1}/{2}"; //$NON-NLS-1$
  public static final String TEST_CASES_RELATIVE_FOLDER = "src-gen/"; //$NON-NLS-1$

  public static final String GET_AVAILABLE_METHOD_NAME = "getAvailableElements"; //$NON-NLS-1$
  public static final String GET_CURRENT_METHOD_NAME = "getCurrentElements"; //$NON-NLS-1$

  public static final String DISCARDED_BQ_CONFIGURATION_FILE_RELATIVE_PATH = "config/discardedBusinessQuery.conf"; //$NON-NLS-1$

  private static List<String> discardedBQs;

  public static final String JUNIT_TEMPLATE_FILE_RELATIVE_PATH = "template/JUnit.template.java"; //$NON-NLS-1$
  public static final String TEST_SUITE_TEMPLATE_FILE_RELATIVE_PATH = "template/testSuite.template"; //$NON-NLS-1$

  public static final String JUNIT_TEST_CASE_FILE_PACKAGE = "\\$JUNIT_TEST_CASE_FILE_PACKAGE\\$"; //$NON-NLS-1$
  public static final String JUNIT_TEST_CASE_FILE_NAME__VARIABLE = "\\$JUNIT_TEST_CASE_FILE_NAME\\$"; //$NON-NLS-1$
  public static final String PROJECT_NAME_FOR_TEST__VARIABLE = "\\$PROJECT_NAME_FOR_TEST\\$"; //$NON-NLS-1$
  public static final String LIB_PROJECT_NAME_FOR_TEST__VARIABLE = "\\$LIB_PROJECT_NAME_FOR_TEST\\$"; //$NON-NLS-1$
  public static final String BUSINESS_QUERY_FQN__VARIABLE = "\\$BUSINESS_QUERY_FQN\\$"; //$NON-NLS-1$
  public static final String TEST_CASES_DATA = "\\$TEST_CASES_DATA\\$"; //$NON-NLS-1$

  public static boolean isDiscardedBQ(String bqFqn) {
    if (discardedBQs == null) {
      try {
        File fileConf = IResourceHelpers.getFileInPlugin(TestBusinessQueriesPlugin.class,
            BQTestConstants.DISCARDED_BQ_CONFIGURATION_FILE_RELATIVE_PATH);
        String data = IResourceHelpers.readFileAsString(fileConf);
        discardedBQs = Arrays.asList(data.split("\\r?\\n")); //$NON-NLS-1$
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return discardedBQs.contains(bqFqn);
  }
}
