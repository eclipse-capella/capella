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

import java.util.Arrays;
import java.util.Collections;

public class SimpleTC_WithInteractionUseTC1LC3 extends AbstractRefinementWithFragmentsTest {

  @SuppressWarnings("unchecked")
  public SimpleTC_WithInteractionUseTC1LC3() {
    super(Messages.SimpleTC_WithInteractionUseTC1_Name,
        Messages.SimpleTC_WithInteractionUseTC1_Desc,
        Collections.singletonList(Messages.SimpleTC_LC3),
        Arrays.asList(Messages.SimpleTC_LC1, Messages.SimpleTC_LC2, Messages.SimpleTC_LC3, Messages.SimpleTC_PhysicalArchitecture),
        Messages.SimpleTC_WithInteractionUseTC1_Src,
        Messages.SimpleTC_WithInteractionUseTC1LC3_Ref,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST);
  }
}
