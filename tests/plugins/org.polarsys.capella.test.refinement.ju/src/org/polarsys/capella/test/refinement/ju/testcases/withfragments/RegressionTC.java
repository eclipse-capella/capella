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
package org.polarsys.capella.test.refinement.ju.testcases.withfragments;

import java.util.Collections;

public class RegressionTC extends AbstractRefinementWithFragmentsTest {

  @SuppressWarnings("unchecked")
  public RegressionTC() {
    super(Messages.regression_TC_Name,
        Messages.regression_TC_Desc,
        Collections.singletonList(Messages.regression_TC_LogicalArchitecture),
        Collections.singletonList(Messages.regression_TC_LogicalArchitecture),
        Messages.regression_TC_Src,
        Messages.regression_TC_Ref,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST);
  }
}
