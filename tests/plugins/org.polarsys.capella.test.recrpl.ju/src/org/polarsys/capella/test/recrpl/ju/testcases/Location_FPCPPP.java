/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.skeleton.CapellaModelSkeleton;
import org.polarsys.capella.test.recrpl.ju.RecRplCommandManager;
import org.polarsys.capella.test.recrpl.ju.RecRplTestCase;

/**
 * Test whether a FunctionPort owned by Root Function, or a ComponentPort/PhysicalPort owned by Root Component is
 * instanciated under an root element instead of raising warning
 */
public class Location_FPCPPP extends RecRplTestCase {
  public static final String FIP_1 = "d28af7d2-df78-49d2-bb3d-9804fd4964b9"; //$NON-NLS-1$
  public static final String FOP_1 = "c2740663-908c-4eb6-9a14-2a40a2b85774"; //$NON-NLS-1$
  public static final String SCP_1 = "c4fea7b2-ea55-4455-9ca6-f9e20d124b2b"; //$NON-NLS-1$
  public static final String SPP_2 = "38162b6e-1624-4909-9e8f-1c0fc5a7b117"; //$NON-NLS-1$
  public static final String ACP_1 = "97c364f1-2ef5-4d4d-8c9c-c646fc20c3eb"; //$NON-NLS-1$
  public static final String APP_2 = "8947d032-e3ff-42cd-ab57-a3a5c84f8a83"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(new String[] { "lib-fecepl" }); //$NON-NLS-1$
  }

  @Override
  public void test() throws Exception {
    CatalogElement rec = createREC(getObjects(FIP_1, FOP_1, SCP_1, SPP_2, ACP_1, APP_2));
    RecRplCommandManager.push(IReConstants.PROPERTY__TARGET_NAME, "specificPkgRPL"); //$NON-NLS-1$

    CapellaModelSkeleton project = new CapellaModelSkeleton.Builder(TransactionHelper.getExecutionManager(rec)).build();
    CatalogElement rpl = createPartialReplica(Collections.singleton(project.getSystemEngineering()), null, rec);

    // Port on root cpt shall have a location
    mustReplicate(rpl, getObject(SCP_1));
    mustReplicate(rpl, getObject(SPP_2));

    // Port on actors shall not have a location (as actor not exist in target)
    mustNotReplicate(rpl, getObject(ACP_1));
    mustNotReplicate(rpl, getObject(APP_2));

    for (CatalogElementLink link : rpl.getOwnedLinks()) {
      EObject target = link.getTarget();
      EObject origin = link.getOrigin().getTarget();

      // Ensure location is the root component
      if (origin instanceof ComponentPort || origin instanceof PhysicalPort) {
        BlockArchitecture architectureTarget = BlockArchitectureExt.getRootBlockArchitecture(target);
        assertTrue(target.eContainer().equals(BlockArchitectureExt.getFirstComponent(architectureTarget, false)));
      }
    }
  }

}
