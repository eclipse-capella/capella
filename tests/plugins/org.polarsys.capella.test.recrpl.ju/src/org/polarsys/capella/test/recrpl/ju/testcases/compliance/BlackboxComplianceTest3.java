/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.recrpl.ju.testcases.compliance;

import java.util.Arrays;
import java.util.Collections;

import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.test.recrpl.ju.model.Compliance;

/**
 * Tests re-allocation of a rpl function on components under blackbox compliance.
 */
public class BlackboxComplianceTest3 extends Compliance {

  private PhysicalArchitecture pa;
  private PhysicalComponent rootPC;

  private CatalogElement rec;
  private PhysicalComponent pcRec1;
  private PhysicalComponent pcRec2;

  private CatalogElement rpl;
  private PhysicalComponent pcRpl1;
  private PhysicalComponent pcRpl2;

  private PhysicalFunction rootPf;

  private PhysicalFunction pfRec;
  private PhysicalFunction pfRpl;

  @SuppressWarnings("nls")
  @Override
  public void test() throws Exception {

    SystemEngineering se = SystemEngineeringExt.getSystemEngineering(getProject());
    pa = SystemEngineeringExt.getPhysicalArchitecture(se);
    rootPC = (PhysicalComponent)pa.getSystem();

    // create pc1,pc2,pf1 and allocate pf1 on pc1
    expectNoRollback(() -> {
      pcRec1 = PaFactory.eINSTANCE.createPhysicalComponent("PC1");
      pcRec1.setDescription("pc description");
      rootPC.getOwnedPhysicalComponents().add(pcRec1);

      pcRec2 = PaFactory.eINSTANCE.createPhysicalComponent("PC2");
      pcRec2.setDescription("pc 2 description");
      rootPC.getOwnedPhysicalComponents().add(pcRec2);

      rootPf = (PhysicalFunction) BlockArchitectureExt.getRootFunction(pa);
      pfRec = PaFactory.eINSTANCE.createPhysicalFunction("pf1");
      rootPf.getOwnedFunctions().add(pfRec);
      FaServices.getFaServices().allocateToComponent(pfRec, pcRec1);
    });

    rec = createREC(Arrays.asList(pcRec1, pcRec2, pcRec1.getOwnedFunctionalAllocation().get(0), pfRec));
    rpl = createReplica(Collections.singleton(rootPC), rec, "SUFFIX");

    for (CatalogElementLink l : rpl.getOwnedLinks()) {
      if (l.getOrigin().getTarget() == pcRec1) {
        pcRpl1 = (PhysicalComponent) l.getTarget();
      }
      if (l.getOrigin().getTarget() == pcRec2) {
        pcRpl2 = (PhysicalComponent) l.getTarget();
      }
      if (l.getOrigin().getTarget() == pfRec) {
        pfRpl = (PhysicalFunction) l.getTarget();
      }
    }

    // make the rpl readonly
    expectNoRollback(() -> rpl.setReadOnly(true));

    // now try to re-allocate pfRpl on pcRpl2, this is not allowed
    expectRollback(() -> {
      pcRpl2.getOwnedFunctionalAllocation().addAll(pcRpl1.getOwnedFunctionalAllocation());
      for (ComponentFunctionalAllocation alloc : pcRpl2.getOwnedFunctionalAllocation()) {
        alloc.setSourceElement(pcRpl2);
      }
    });

    // reallocate pfRec on pcRec2
    expectNoRollback(() -> {
      pcRec2.getOwnedFunctionalAllocation().addAll(pcRec1.getOwnedFunctionalAllocation());
      for (ComponentFunctionalAllocation alloc : pcRec2.getOwnedFunctionalAllocation()) {
        alloc.setSourceElement(pcRec2);
      }
    });

    // and update the rpl should be fine
    updateReplica(Collections.singleton(pcRpl1), rpl);

    assertTrue(pcRpl1.getAllocatedFunctions().isEmpty());
    assertTrue(pcRpl1.getOwnedFunctionalAllocation().isEmpty());
    assertTrue(pcRpl2.getOwnedFunctionalAllocation().size() == 1);
    assertTrue(pcRpl2.getAllocatedFunctions().size() == 1 && pcRpl2.getAllocatedFunctions().contains(pfRpl));

  }

}
