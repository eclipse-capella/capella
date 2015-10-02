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

import java.util.Collections;

public class PhysicalTC_LA_2_PA extends AbstractPhysicalRefinementTest {

  @SuppressWarnings("unchecked")
  public PhysicalTC_LA_2_PA() {
    super(Messages.PhysicalTC_LA_2_PA_Name,
        Messages.PhysicalTC_LA_2_PA_Desc,
        Collections.singletonList(Messages.PhysicalTC_PhysicalArchitecture),
        Collections.singletonList(Messages.PhysicalTC_PhysicalArchitecture),
        Messages.PhysicalTC_LA_2_PA_Src,
        Messages.PhysicalTC_LA_2_PA_Ref,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST);
  }
}
