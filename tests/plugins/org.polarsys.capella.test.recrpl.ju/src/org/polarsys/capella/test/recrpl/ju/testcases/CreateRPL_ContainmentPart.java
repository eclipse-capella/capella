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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class CreateRPL_ContainmentPart extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC with a part and its component
    CatalogElement REC = createREC(getObjects(LC_1, LC_1__LC_1));

    // Create a RPL, component should be next to its part
    CatalogElement RPL = createReplica(getObjects(LC_2), REC);
    EObject LF1rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(LC_1));
    EObject LF2rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(LC_1__LC_1));
    assertTrue(LF1rpl.eContainer() == LF2rpl.eContainer());

    // Create a REC with a part and its component, the component is inside its part
    REC = createREC(getObjects(LC_3_LC_3, LC_3_LC_3__LC_3));

    // Create a RPL, component should be inside its part
    RPL = createReplica(getObjects(LC_2), REC);
    LF1rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(LC_3_LC_3));
    LF2rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(LC_3_LC_3__LC_3));
    assertTrue(LF2rpl.eContainer() == LF1rpl);
  }

}
