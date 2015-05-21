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
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class CatalogPkgName extends Re {

  @Override
  public void performTest() throws Exception {

    // //////////////////////////////////////////////////////
    // Root catalog element should be named 'REC Catalog' //
    // //////////////////////////////////////////////////////

    createREC(getObjects(LOGICAL_SYSTEM));
    EObject object = getObject(LOGICAL_SYSTEM);
    CatalogElement rec = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    CatalogElementPkg pkg = (CatalogElementPkg) rec.eContainer();
    assertTrue(pkg != null);
    System.out.println(pkg.getName());
    assertTrue("REC Catalog".equals(pkg.getName()));
  }

}
