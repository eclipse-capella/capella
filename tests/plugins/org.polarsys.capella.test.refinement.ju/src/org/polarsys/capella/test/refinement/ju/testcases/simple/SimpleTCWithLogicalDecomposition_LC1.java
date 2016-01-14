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

public class SimpleTCWithLogicalDecomposition_LC1 extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public SimpleTCWithLogicalDecomposition_LC1() {
    super(Messages.SimpleTCWithLogicalDecomposition_Name1,
        Messages.SimpleTCWithLogicalDecomposition_Desc1,
        Collections.singletonList(Messages.Decomposition_TC_LC1),
        Arrays.asList(Messages.Decomposition_TC_LC1, Messages.Decomposition_TC_LC2, Messages.Decomposition_TC_PhysicalArchitecture),
        Messages.SimpleTCWithLogicalDecomposition_Src,
        Messages.SimpleTCWithLogicalDecomposition_Ref1,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST);
  }
}
