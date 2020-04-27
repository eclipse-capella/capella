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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class UpdateRPL_Containment extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC with 2 functions
    CatalogElement REC = createREC(getObjects(LF1, LF2));

    // Create a RPL in LF4, functions must be onwed by LF4
    CatalogElement RPL = createReplica(getObjects(LF4), REC);
    EObject LF1rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(LF1));
    mustBeOwnedBy(LF1rpl, getObject(LF4));
    EObject LF2rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(LF2));
    mustBeOwnedBy(LF2rpl, getObject(LF4));

    // Add LF3 on the REC
    updateCur(getObjects(LF3), REC);
    mustReference(REC, getObject(LF3));

    // updateReplica, LF3rpl should be next to LF3
    updateReplica(Arrays.asList(new EObject[] { RPL }), RPL);
    EObject LF3rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(LF3));
    mustBeOwnedBy(LF3rpl, getObject(LF3).eContainer());

  }

}
