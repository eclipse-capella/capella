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

public class ComplexTC1 extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public ComplexTC1() {
    super(Messages.ComplexTC_Name1,
        Messages.ComplexTC_Desc1,
        Collections.singletonList(Messages.Complex_TC_LogicalArchitecture),
        Collections.singletonList(Messages.Complex_TC_LogicalArchitecture),
        Messages.ComplexTC_Src1,
        Messages.ComplexTC_Ref1,
        Arrays.asList(Messages.Complex_TC_Part_P1_MANAGER, Messages.Complex_TC_Part_P2_MANAGER),
        Arrays.asList(Arrays.asList(Messages.Complex_TC_Part_P1_MANAGER, Messages.Complex_TC_Part_P2_MANAGER),
            Arrays.asList(Messages.Complex_TC_Part_P1_MANAGER, Messages.Complex_TC_Part_P2_MANAGER)),
        Collections.EMPTY_LIST);
  }
}
