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
package org.polarsys.capella.test.recrpl.ju.testcases;

import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class CreateREC_ComponentFunctionalAllocation extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC with 1 function and a component. Allocation must be included, only 1 allocation
    CatalogElement REC = createREC(getObjects(LC_1, LF1));
    mustReference(REC, getObject(COMPONENT_FUNCTIONAL_ALLOCATION_TO_LF1));
    mustNotReference(REC, getObject(LF2));
    mustNotReference(REC, getObject(COMPONENT_FUNCTIONAL_ALLOCATION_TO_LF2));

    // Add LF2 on REC, allocation must be include
    updateCur(getObjects(LF2), REC);
    mustReference(REC, getObject(LF2));
    mustReference(REC, getObject(COMPONENT_FUNCTIONAL_ALLOCATION_TO_LF2));
  }

}
