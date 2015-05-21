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

public class CreateRPL_Containment extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC with 2 functions
    CatalogElement REC = createREC(getObjects(LF1, LF2));

    // Create a RPL in LF4, functions must be owned by LF4
    CatalogElement RPL = createReplica(getObjects(LF4), REC);
    EObject LF1rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(LF1));
    mustBeOwnedBy(LF1rpl, getObject(LF4));
    EObject LF2rpl = ReplicableElementExt.getReferencingElement(RPL, getObject(LF2));
    mustBeOwnedBy(LF2rpl, getObject(LF4));
  }

}
