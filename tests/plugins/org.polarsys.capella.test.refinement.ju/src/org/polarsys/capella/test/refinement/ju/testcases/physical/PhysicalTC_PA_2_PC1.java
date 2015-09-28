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
package org.polarsys.capella.test.refinement.ju.testcases.physical;

import java.util.Arrays;
import java.util.Collections;

public class PhysicalTC_PA_2_PC1 extends AbstractPhysicalRefinementTest {

  @SuppressWarnings("unchecked")
  public PhysicalTC_PA_2_PC1() {
    super(Messages.PhysicalTC_PA_2_PC1_Name,
        Messages.PhysicalTC_PA_2_PC1_Desc,
        Collections.singletonList(Messages.PhysicalTC_PC1),
        Arrays.asList(Messages.PhysicalTC_PC1, Messages.PhysicalTC_PC2, Messages.PhysicalTC_EPBSArchitecture),
        Messages.PhysicalTC_PA_2_PC1_Src,
        Messages.PhysicalTC_PA_2_PC1_Ref,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST);
  }
}
