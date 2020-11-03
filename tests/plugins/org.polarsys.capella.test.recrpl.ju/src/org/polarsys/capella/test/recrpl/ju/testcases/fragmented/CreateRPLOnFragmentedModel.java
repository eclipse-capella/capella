/*******************************************************************************
 * Copyright (c) 2018, 2020, THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.recrpl.ju.testcases.fragmented;

import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.test.recrpl.ju.model.Fragmented;

public class CreateRPLOnFragmentedModel extends Fragmented { 
  
  @Override
  public void performTest() throws Exception {

    HashMap<String, Collection<String>> recToSourceMap = new HashMap<>();
    
    recToSourceMap.put(OA_REC_ID, Arrays.asList(OA_OPENTITIES_ID));
    recToSourceMap.put(SA_REC_ID, Arrays.asList(SA_LAYER_ID));
    recToSourceMap.put(LA_REC_ID, Arrays.asList(LA_LAYER_ID));
    recToSourceMap.put(PA_REC_ID, Arrays.asList(PA_LAYER_ID));
    recToSourceMap.put(EPBS_REC_ID, Arrays.asList(EPBS_LAYER_ID));

    recToSourceMap.forEach((recId, targetIds) -> {

      CatalogElement rec = (CatalogElement) getObject(recId);
      List<EObject> target = targetIds.stream().map(id -> getObject(id)).collect(Collectors.toList());

      CatalogElement rpl = createReplica(target, rec);

      rec.getOwnedLinks().forEach(link -> {

        EObject recElement = link.getTarget();
        EObject rplElement = ReplicableElementExt.getReferencingElement(rpl, recElement);

        assertNotNull(rplElement);
        assertNotEquals(recElement, rplElement);
      });

    });

  }
}
