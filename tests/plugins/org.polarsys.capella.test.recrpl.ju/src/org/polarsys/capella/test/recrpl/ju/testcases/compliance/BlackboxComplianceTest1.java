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

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.helpers.TransactionHelper;
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
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.test.recrpl.ju.model.Compliance;

public class BlackboxComplianceTest1 extends Compliance {

  private PhysicalArchitecture pa;
  private PhysicalComponent rootPC;

  private CatalogElement rec;
  private PhysicalComponent pcRec;

  private CatalogElement rpl;
  private PhysicalComponent pcRpl;

  private PhysicalFunction rootPf;

  private PhysicalFunction pfRec;
  private PhysicalFunction pfRpl;

  @SuppressWarnings("nls")
  @Override
  public void test() throws Exception {

    SystemEngineering se = SystemEngineeringExt.getSystemEngineering(getProject());
    pa = SystemEngineeringExt.getPhysicalArchitecture(se);
    rootPC = (PhysicalComponent) pa.getSystem();
    rootPf = (PhysicalFunction) BlockArchitectureExt.getRootFunction(pa);

    expectNoRollback(() -> {
      pcRec = PaFactory.eINSTANCE.createPhysicalComponent("PC1");
      pcRec.setDescription("pc description");
      rootPC.getOwnedPhysicalComponents().add(pcRec);
    });

    rec = createREC(Collections.singleton(pcRec));
    rpl = createReplica(Collections.singleton(rootPC), rec, "SUFFIX");

    pcRpl = (PhysicalComponent) rpl.getOwnedLinks().iterator().next().getTarget();

    assertSame(pa.getSystem(), pcRpl.eContainer());

    final String origPcRplName = pcRpl.getName();
    final String origPcRplDescription = pcRpl.getDescription();

    // make the rpl readonly
    expectNoRollback(() -> rpl.setReadOnly(true));

    // try changing the name and the description, and expect a rollback for both
    expectRollback(() -> pcRpl.setName("x"));
    expectRollback(() -> pcRpl.setDescription("d"));

    // now make the rpl read write
    expectNoRollback(() -> rpl.setReadOnly(false));

    // now the name/description should change just fine
    expectNoRollback(() -> pcRpl.setName("x"));
    expectNoRollback(() -> pcRpl.setDescription("d"));

    // now make the rpl readonly again
    expectNoRollback(() -> rpl.setReadOnly(true));

    // we should still be able to reset the name/description to the 'correct' name of the rpl pc, which is what would happen during update rpl too..
    expectNoRollback(() -> pcRpl.setName(origPcRplName));
    expectNoRollback(() -> pcRpl.setDescription(origPcRplDescription));

    // allocate a new pf on the rec pc and then update the rec and the rpl
    expectNoRollback(() -> {
      pfRec = PaFactory.eINSTANCE.createPhysicalFunction("pf1");
      rootPf.getOwnedFunctions().add(pfRec);
      FaServices.getFaServices().allocateToComponent(pfRec, pcRec);
    });
    updateCur(Collections.singleton(pfRec), rec);
    updateReplica(Collections.singleton(rpl), rpl);

    // find the created pf in the rpl
    for (CatalogElementLink link : rpl.getOwnedLinks()) {
      if (link.getTarget() instanceof PhysicalFunction) {
        pfRpl = (PhysicalFunction) link.getTarget();
      }
    }
    assertSame(rootPf, pfRpl.eContainer());

    // adding one or multiple subcomponent to the rpl should rollback. This tests ADD and ADD_MANY notifications.
    expectRollback(() -> pcRpl.getOwnedPhysicalComponents().add(PaFactory.eINSTANCE.createPhysicalComponent("sub")));
    expectRollback(() -> {
      pcRpl.getOwnedPhysicalComponents().addAll(Arrays.asList(
          PaFactory.eINSTANCE.createPhysicalComponent("sub1"),
          PaFactory.eINSTANCE.createPhysicalComponent("sub2")
      ));
    });

    /*
     *  It shouldn't be possible to delete any elements of the RPL
     *
     *  Test deletion first with EcoreUtil, and then again with Capella Delete Command.
     *  The difference is that CapellaDeleteCommand will auto-delete the related CatalogElementLinks,
     *  and EcoreUtil will not.
     */
    final ComponentFunctionalAllocation alloc = pcRpl.getOwnedFunctionalAllocation().get(0);
    expectRollback(() -> EcoreUtil.delete(alloc));
    new CapellaDeleteCommand(TransactionHelper.getExecutionManager(pfRpl), Collections.singleton(alloc), true, false, false).execute();
    assertSame(pcRpl, alloc.eContainer()); // was rolled back

    expectRollback(() -> EcoreUtil.delete(pfRpl));
    new CapellaDeleteCommand(TransactionHelper.getExecutionManager(pfRpl), Collections.singleton(pfRpl), true, false, false).execute();
    assertSame(rootPf, pfRpl.eContainer()); // was rolled back

    expectRollback(() -> {
      EcoreUtil.delete(alloc);
      EcoreUtil.delete(pfRpl);
    });
    new CapellaDeleteCommand(TransactionHelper.getExecutionManager(pfRpl), Arrays.asList(pfRpl, pfRpl.getComponentFunctionalAllocations().get(0)), true, false, false).execute();
    assertSame(rootPf, pfRpl.eContainer());
    assertSame(pcRpl, alloc.eContainer());


    // now delete the rec function and its allocation (CapellaDeleteCommand should take care of deleting the allocation automatically)
    new CapellaDeleteCommand(TransactionHelper.getExecutionManager(pfRec), Collections.singleton(pfRec), true, false, false).execute();
    assertNull(pfRec.eResource());
    assertTrue(pcRec.getOwnedFunctionalAllocation().isEmpty());

    // Updating the replica should delete the rpl pf and allocation, no rollback
    updateReplica(Collections.singleton(pcRpl), rpl);

    assertNull(pfRpl.eResource());
    assertTrue(pcRpl.getAllocatedFunctions().isEmpty());
    assertTrue(pcRpl.getOwnedFunctionalAllocation().isEmpty());

  }

}
