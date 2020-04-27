/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class CreateRPL_InvalidCatalogElementLink extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC with 2 functions and an exchange
    CatalogElement REC = createREC(getObjects(LF1, LF2, FUNCTIONALEXCHANGE_1));

    // We remove the link of functional exchange in the REC (with a null element)
    EObject fe = getObject(FUNCTIONALEXCHANGE_1);
    for (CatalogElementLink link : ReplicableElementExt.getReferencingLinks(fe)) {
      link.setTarget(null);
    }

    // Create a RPL after set a link to null on the REC. Link should not be propagated, but RPL created
    CatalogElement RPL = createReplica(getObjects(LF1), REC);
    EObject FE1rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(FUNCTIONALEXCHANGE_1));
    assertTrue(FE1rpl == null);
    assertTrue(REC.getOwnedLinks().size() == (RPL.getOwnedLinks().size() + 1));
  }

}
