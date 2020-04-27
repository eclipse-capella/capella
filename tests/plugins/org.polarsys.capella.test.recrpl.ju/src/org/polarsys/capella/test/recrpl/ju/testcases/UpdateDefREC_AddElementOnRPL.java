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

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class UpdateDefREC_AddElementOnRPL extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC with 2 functions and an exchange
    CatalogElement rec = createREC(getObjects(LF1, LF2, FUNCTIONALEXCHANGE_1));

    // Create a RPL, elements should be created
    CatalogElement RPL = createReplica(getObjects(LF3), rec);

    // We add LF4 to the RPL
    // A function should be created and 2 links, one on the RPL, one on the REC
    EObject lf1 = ReplicableElementExt.getReferencingElement(RPL, getObject(LF1));
    Collection<EObject> elements = Arrays.asList(lf1, getObject(LF4));
    updateDef(elements);
    mustReference(RPL, getObject(LF4));

    EObject object = getObject(LF4);
    CatalogElementLink link = ReplicableElementExt.getReferencingLinks(object).iterator().next();
    assertTrue(link != null);
    assertTrue(link.getSource().equals(RPL));
    assertTrue(link.getTarget().equals(object));

    assertTrue(link.getOrigin() != null);
    assertTrue(link.getOrigin().getSource().equals(rec));
    assertTrue(!link.getOrigin().getTarget().equals(object));
    assertTrue(link.getOrigin().getTarget().eContainer().equals(object.eContainer()));

    int nbLinks = rec.getOwnedLinks().size();
    int nbLinks2 = RPL.getOwnedLinks().size();
    assertTrue(nbLinks == nbLinks2);
  }

}
