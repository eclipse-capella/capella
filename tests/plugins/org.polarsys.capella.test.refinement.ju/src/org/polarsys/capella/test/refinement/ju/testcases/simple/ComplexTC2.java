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

public class ComplexTC2 extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public ComplexTC2() {
    super(Messages.ComplexTC_Name2,
        Messages.ComplexTC_Desc2,
        Collections.singletonList(Messages.Complex_TC_LC_CONTROLLER),
        Arrays.asList(Messages.Complex_TC_LC_CONTROLLER, Messages.Complex_TC_LC_MANAGER, Messages.Complex_TC_LC_TIMER, Messages.Complex_TC_PhysicalArchitecture),
        Messages.ComplexTC_Src2,
        Messages.ComplexTC_Ref2,
        Collections.singletonList(Messages.Complex_TC_Part_CONTROL),
        Collections.singletonList(Arrays.asList(Messages.Complex_TC_Part_DIFFERENTIAL, Messages.Complex_TC_Part_CONTROL)),
        Collections.EMPTY_LIST);
  }
}
