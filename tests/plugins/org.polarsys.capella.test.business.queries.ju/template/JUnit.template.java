/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
