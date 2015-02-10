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
package org.polarsys.capella.test.validation.rules.ju.testsuites;

import junit.framework.Test;

import org.polarsys.capella.test.framework.api.AutoLoadTestSuite;

public class ValidationRulesAutoLoadTestSuite extends AutoLoadTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new ValidationRulesAutoLoadTestSuite();
  }

  @Override
  protected String getTestCasesRootPackage() {
    return "org.polarsys.capella.test.validation.rules.ju.testcases"; //$NON-NLS-1$
  }

}
