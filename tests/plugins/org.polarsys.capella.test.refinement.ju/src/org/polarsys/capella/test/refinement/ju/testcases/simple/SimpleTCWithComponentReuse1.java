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

public class SimpleTCWithComponentReuse1 extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public SimpleTCWithComponentReuse1() {
    super(Messages.SimpleTCWithComponentReuse_Name1,
        Messages.SimpleTCWithComponentReuse_Desc1,
        Collections.singletonList(Messages.ComponentReuse_TC_LC2),
        Arrays.asList(Messages.ComponentReuse_TC_LC1, Messages.ComponentReuse_TC_LC2, Messages.ComponentReuse_TC_PhysicalArchitecture),
        Messages.SimpleTCWithComponentReuse_Src1,
        Messages.SimpleTCWithComponentReuse_Ref1,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST);
  }
}
