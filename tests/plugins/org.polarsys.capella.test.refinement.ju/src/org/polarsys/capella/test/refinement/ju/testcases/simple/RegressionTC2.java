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

import java.util.Collections;

public class RegressionTC2 extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public RegressionTC2() {
    super(Messages.regression_TC2_Name,
        Messages.regression_TC2_Desc,
        Collections.singletonList(Messages.regression_TC2_LogicalArchitecture),
        Collections.singletonList(Messages.regression_TC2_LogicalArchitecture),
        Messages.regression_TC2_Src,
        Messages.regression_TC2_Ref,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST);
  }
}
