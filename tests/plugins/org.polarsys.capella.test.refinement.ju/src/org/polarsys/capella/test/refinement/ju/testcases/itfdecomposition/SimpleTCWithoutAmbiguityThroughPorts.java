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
package org.polarsys.capella.test.refinement.ju.testcases.itfdecomposition;

import java.util.Collections;

public class SimpleTCWithoutAmbiguityThroughPorts extends AbstractInterfaceDecompositionRefinementTest {

  @SuppressWarnings("unchecked")
  public SimpleTCWithoutAmbiguityThroughPorts() {
    super(Messages.SimpleTCWithoutAmbiguityThroughPorts_Name,
        Messages.SimpleTCWithoutAmbiguityThroughPorts_Desc,
        Collections.singletonList(Messages.SimpleTC_LogicalArchitecture),
        Collections.singletonList(Messages.SimpleTC_LogicalArchitecture),
        Messages.SimpleTCWithoutAmbiguityThroughPorts_Src,
        Messages.SimpleTCWithoutAmbiguityThroughPorts_Ref,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST);
  }
}
