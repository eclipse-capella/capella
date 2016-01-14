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

public class ComplexTC3 extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public ComplexTC3() {
    super(Messages.ComplexTC_Name3,
        Messages.ComplexTC_Desc3,
        Collections.singletonList(Messages.Complex_TC_Part_P2_TIMER),
        Collections.singletonList(Messages.Complex_TC_Part_P2_TIMER),
        Messages.ComplexTC_Src3,
        Messages.ComplexTC_Ref3,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST);
  }
}
