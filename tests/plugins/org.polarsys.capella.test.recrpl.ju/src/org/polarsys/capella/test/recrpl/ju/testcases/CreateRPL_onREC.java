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
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.test.recrpl.ju.model.Recs;

public class CreateRPL_onREC extends Recs {

  @Override
  public void performTest() throws Exception {

    // Create a rpl from a REC with this REC as selection
    CatalogElement rec1 = getREC(REC1);
    createReplica(Arrays.asList(new EObject[] { rec1 }), rec1);
    CatalogElement replica = ReplicableElementExt.getReplicas(rec1).iterator().next();
    for (CatalogElementLink link : replica.getOwnedLinks()) {
      if (!ReplicableElementExt.getReferencingReplicableElements(link.getOrigin().getTarget().eContainer()).contains(rec1)) {
        assertTrue("instance of root elements in the REC must have the same container as the root element", link.getTarget().eContainer() == link.getOrigin()
            .getTarget().eContainer());
      }
    }
  }

}
