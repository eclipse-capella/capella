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
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class CreateRPL_SharedElements_Interface extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC with 1 LC linked to Interfaces
    CatalogElement REC = createREC(getObjects(LC_2));
    mustNotReference(REC, getObject(I1));
    mustNotReference(REC, getObject(I2));

    // Create a RPL, elements should be created, RPL element should be linked to EI, but not the RPL
    CatalogElement RPL = createReplica(getObjects(LF1, LF2, FUNCTIONALEXCHANGE_1), REC);
    mustNotReference(RPL, getObject(I1));
    mustNotReference(RPL, getObject(I2));

    EObject LC1rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(LC_2));
    assertTrue(((LogicalComponent) LC1rpl).getUsedInterfaces().contains(getObject(I2)));
    assertTrue(((LogicalComponent) LC1rpl).getProvidedInterfaces().contains(getObject(I1)));

    // Add I1 and I2 to the REC
    updateCur(getObjects(I1, I2), REC);
    mustReference(REC, getObject(I1));
    mustReference(REC, getObject(I2));

  }

}
