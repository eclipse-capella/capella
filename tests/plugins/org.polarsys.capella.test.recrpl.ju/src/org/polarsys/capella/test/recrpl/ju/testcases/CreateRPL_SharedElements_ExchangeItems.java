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
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class CreateRPL_SharedElements_ExchangeItems extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC with 2 functions and an exchange linked to a EI
    CatalogElement REC = createREC(getObjects(LF1, LF2, FUNCTIONALEXCHANGE_1));
    mustNotReference(REC, getObject(EI1));

    // Create a RPL, elements should be created, RPL element should be linked to EI
    CatalogElement RPL = createReplica(getObjects(LF1), REC);

    // EI should not be linked to RPL
    mustNotReference(RPL, getObject(EI1));
    // Functional exchange should be linked to EI
    EObject FE1rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(FUNCTIONALEXCHANGE_1));
    assertTrue(FE1rpl != getObject(FUNCTIONALEXCHANGE_1));
    assertTrue(((FunctionalExchange) FE1rpl).getExchangedItems().contains(getObject(EI1)));

    // Add EI1 to the REC
    updateCur(getObjects(EI1), REC);
    mustReference(REC, getObject(EI1));

    // RPL and elements should be linked to EIrpl, not EI1
    updateReplica(getObjects(LF1), RPL);
    FE1rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(FUNCTIONALEXCHANGE_1));
    assertTrue(FE1rpl != null);
    assertTrue(FE1rpl.eContainer() != null);
    // I1 should not be linked to RPL
    EObject I1rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(EI1));
    assertTrue(((FunctionalExchange) FE1rpl).getExchangedItems().contains(I1rpl));

    // TODO When an shared elements is referenced through a EReference.isMany, previous I1 is not removed, but i guess it should be
    // EObject I1 = getObject(ModelConstants_re.EI1);
    // assertTrue(!((FunctionalExchange) LF1rpl).getExchangedItems().contains(I1));
  }

}
