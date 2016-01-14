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

public class Simple_Communication_Mechanisms_TC2b extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public Simple_Communication_Mechanisms_TC2b() {
    super(Messages.Simple_Communication_Mechanisms_TC2b_Name,
        Messages.Simple_Communication_Mechanisms_TC2b_Desc,
        Collections.singletonList(Messages.Simple_Communication_Mechanisms_TC_LC2),
        Arrays.asList(Messages.Simple_Communication_Mechanisms_TC_LC2, Messages.Simple_Communication_Mechanisms_TC_PhysicalArchitecture),
        Messages.Simple_Communication_Mechanisms_TC2b_Src,
        Messages.Simple_Communication_Mechanisms_TC2b_Ref,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST);
  }
}
