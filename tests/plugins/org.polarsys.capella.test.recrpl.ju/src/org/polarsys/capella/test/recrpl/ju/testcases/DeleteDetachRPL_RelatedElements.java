/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class DeleteDetachRPL_RelatedElements extends Re {

  @Override
  public void performTest() throws Exception {

    // Delete Replica and related elements
    deleteReplicaAndRelatedElements(getObjects(PC_11));
    assertTrue(getObject(PC_11) == null);
    assertTrue(getObject(PC_12) == null);
    assertTrue(getObject(PF_13) == null);

    EObject object = getObject(PC_21);
    CatalogElement rpl = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rpl != null);
    CatalogElement rec = rpl.getOrigin();
    Collection<EObject> links_rec = EObjectExt.getReferencers(rec, RePackage.Literals.CATALOG_ELEMENT__ORIGIN);
    assertTrue(links_rec.size() == 2);

    // Delete Replica and preserve related elements
    deleteReplicaPreserveRelatedElements(getObjects(PC_21));
    object = getObject(PC_21);
    assertTrue(getObject(PC_21) != null);
    assertTrue(getObject(PC_22) != null);
    assertTrue(getObject(PF_23) != null);
    assertTrue(ReplicableElementExt.getReferencingReplicableElements(object).size() == 0);
    object = getObject(PC_31);
    rpl = ReplicableElementExt.getReferencingReplicableElements(object).iterator().next();
    assertTrue(rpl != null);
    rec = rpl.getOrigin();
    links_rec = EObjectExt.getReferencers(rec, RePackage.Literals.CATALOG_ELEMENT__ORIGIN);
    assertTrue(links_rec.size() == 1);
  }

}
