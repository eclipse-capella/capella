/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package $JUNIT_TEST_CASE_FILE_PACKAGE$;

import org.polarsys.capella.test.business.queries.ju.BQTestCase;

/**
 * @generated
 */
public class $JUNIT_TEST_CASE_FILE_NAME$ extends BQTestCase {

  /**
   * @generated
   */
  public String getProjectForTest() {
    return "$PROJECT_NAME_FOR_TEST$"; //$NON-NLS-1$
  }
  
  /**
   * @generated
   */
  public String getLibProjectForTest() {
    return "$LIB_PROJECT_NAME_FOR_TEST$"; //$NON-NLS-1$
  }

  /**
   * @generated
   */
  @Override
  public String getBQFullQualifiedName() {
    return "$BUSINESS_QUERY_FQN$"; //$NON-NLS-1$
  }

}
