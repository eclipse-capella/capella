/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
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
    
    // Create a REC with an actor and its part at LA level
    REC = createREC(getObjects(LA_2, LA_2__LA_2));
    // Create a RPL, actor and its part should be instantiated in the Logical System
    RPL = createReplica(getObjects(LOGICAL_SYSTEM), REC);
    EObject LA2Rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(LA_2));
    EObject LA2PartRpl = ReplicableElementExt.getReferencingElement(RPL, getObject(LA_2__LA_2));
    assertTrue(LA2Rpl.eContainer() == getObject(LOGICAL_SYSTEM));
    assertTrue(LA2PartRpl.eContainer() == getObject(LOGICAL_SYSTEM));
    
    // Create a REC with an actor and its part at SA level
    REC = createREC(getObjects(SA_2, SA_2__SA_2));
    // Create a RPL, actor and its part should be instantiated in the Structure
    RPL = createReplica(getObjects(SYSTEM), REC);
    EObject SA2Rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(SA_2));
    EObject SA2PartRpl = ReplicableElementExt.getReferencingElement(RPL, getObject(SA_2__SA_2));
    assertTrue(SA2Rpl.eContainer() == getObject(SA_STRUCTURE));
    assertTrue(SA2PartRpl.eContainer() == getObject(SA_STRUCTURE));
  }

}
