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
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class CreateREC_ContainedInCatalog extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC with 2 functions. REC should be contained in the REC Catalog
    CatalogElement REC = createREC(getObjects(LF1, LF2, FUNCTIONALEXCHANGE_1));
    CatalogElementPkg pkg = (CatalogElementPkg) REC.eContainer();
    assertTrue(pkg != null);
  }

}
