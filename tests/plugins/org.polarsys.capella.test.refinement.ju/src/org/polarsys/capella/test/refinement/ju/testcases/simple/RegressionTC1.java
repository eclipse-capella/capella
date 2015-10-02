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
package org.polarsys.capella.test.refinement.ju.testcases.simple;

import java.util.Arrays;
import java.util.Collections;

public class RegressionTC1 extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public RegressionTC1() {
    super(Messages.regression_TC1_Name,
        Messages.regression_TC1_Desc,
        Collections.singletonList(Messages.regression_TC1_Part_LC2),
        Collections.singletonList(Messages.regression_TC1_Part_LC2),
        Messages.regression_TC1_Src,
        Messages.regression_TC1_Ref,
        Arrays.asList(Messages.regression_TC1_Part_LC21b, Messages.regression_TC1_Part_LC21c),
        Arrays.asList(Arrays.asList(Messages.regression_TC1_Part_LC21a, Messages.regression_TC1_Part_LC21b, Messages.regression_TC1_Part_LC21c),
            Arrays.asList(Messages.regression_TC1_Part_LC21a, Messages.regression_TC1_Part_LC21b, Messages.regression_TC1_Part_LC21c)),
        Collections.EMPTY_LIST);
  }
}
