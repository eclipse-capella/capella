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

public class SimpleTCWithComponentReuse2 extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public SimpleTCWithComponentReuse2() {
    super(Messages.SimpleTCWithComponentReuse_Name2,
        Messages.SimpleTCWithComponentReuse_Desc2,
        Collections.singletonList(Messages.ComponentReuse_TC_LC2_Part),
        Collections.singletonList(Messages.ComponentReuse_TC_LC2_Part),
        Messages.SimpleTCWithComponentReuse_Src2,
        Messages.SimpleTCWithComponentReuse_Ref2,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST);
  }
}
