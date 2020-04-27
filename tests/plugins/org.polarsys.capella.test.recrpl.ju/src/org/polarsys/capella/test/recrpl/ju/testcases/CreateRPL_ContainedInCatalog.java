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

import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class CreateRPL_ContainedInCatalog extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC with 1 function
    CatalogElement REC = createREC(getObjects(LF1));

    // Create a RPL. RPL should be contained in the REC Catalog
    CatalogElement RPL = createReplica(getObjects(LF1, LF2, FUNCTIONALEXCHANGE_1), REC);
    CatalogElementPkg pkg = (CatalogElementPkg) RPL.eContainer();
    assertTrue(pkg != null);
  }

}
