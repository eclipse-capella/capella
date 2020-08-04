/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.PhysicalLinkExt;
import org.polarsys.capella.core.model.skeleton.CapellaModelSkeleton;
import org.polarsys.capella.test.recrpl.ju.RecRplCommandManager;
import org.polarsys.capella.test.recrpl.ju.RecRplTestCase;

/**
 * Test whether a Physical Link or a Component Exchange associated to an Actor is located by default under root
 * ComponentPkg, not a Component
 */
public class Location_CEPL extends RecRplTestCase {
  public static final String SC_1 = "d9227a67-4941-4569-8cc5-e4f1533946df"; //$NON-NLS-1$
  public static final String SC_3 = "ec0cae4d-2aaf-4b33-9f2a-6343f135cd2c"; //$NON-NLS-1$
  public static final String SPL_1 = "ca090179-0e76-489d-9817-e66fb9061a6b"; //$NON-NLS-1$
  public static final String SPL_2 = "51bbadc7-d55f-45c0-ae6a-78341e325126"; //$NON-NLS-1$
  public static final String PC_1 = "3b09f969-30ec-4eeb-b1d0-e70f1617cf78"; //$NON-NLS-1$
  public static final String PC_2 = "19e22309-cad0-4a1c-8312-85eba70098f3"; //$NON-NLS-1$
  public static final String PPL_1 = "cbae4cfe-44d2-4ffa-a994-ebd46c69cdd8"; //$NON-NLS-1$
  public static final String PPL_2 = "81d63f64-e45f-4bc3-8ed7-bc3f021c1e39"; //$NON-NLS-1$
  public static final String PCPL_1 = "2d4e9d56-7afb-45ee-872d-542c5fca3d24"; //$NON-NLS-1$
  public static final String PCC_1 = "8ccaf096-a897-4a13-8b3a-aab3816d8751"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(new String[] { "re-cepl" }); //$NON-NLS-1$
  }

  @Override
  public void test() throws Exception {
    CatalogElement rec = createREC(getObjects(SC_1, SC_3, PC_1, PC_2, SPL_1, SPL_2, PPL_1, PPL_2, PCPL_1, PCC_1));
    RecRplCommandManager.push(IReConstants.PROPERTY__TARGET_NAME, "specificPkgRPL"); //$NON-NLS-1$

    CapellaModelSkeleton project = new CapellaModelSkeleton.Builder(TransactionHelper.getExecutionManager(rec)).build();
    CatalogElement rpl = createReplica(Collections.singleton(project.getSystemEngineering()), rec);

    // All links shall be replicated, but owned by a Package or a Component
    // (Links are without source/target in this use case, as Source/Target are not present in target)
    for (CatalogElementLink link : rpl.getOwnedLinks()) {
      EObject target = link.getTarget();
      EObject origin = link.getOrigin().getTarget();

      if (origin instanceof ComponentExchange) {
        if (ComponentExchangeExt.isLinkToAnActor((ComponentExchange) origin)) {
          assertTrue(target.eContainer() instanceof ComponentPkg);
        } else {
          assertTrue(target.eContainer() instanceof Component);
        }

      } else if (origin instanceof PhysicalLink) {
        if (PhysicalLinkExt.isLinkToAnActor((PhysicalLink) origin)) {
          assertTrue(target.eContainer() instanceof ComponentPkg);
        } else {
          assertTrue(target.eContainer() instanceof Component);
        }

      } else {
        assertFalse(true);
      }
    }
    System.out.println();
  }

}
