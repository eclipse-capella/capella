/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.test.recrpl.ju.model.Compliance;

public class BlackboxComplianceTest2 extends Compliance {

  private PhysicalArchitecture pa;

  private CatalogElement rec;
  private CatalogElement rpl1;

  private PhysicalFunction rootPf;

  private PhysicalFunction pfRec1;
  private PhysicalFunction pfRec2;

  private PhysicalFunction pfRpl1;
  private PhysicalFunction pfRpl2;

  @SuppressWarnings("nls")
  @Override
  public void test() throws Exception {

    SystemEngineering se = SystemEngineeringExt.getSystemEngineering(getProject());
    pa = SystemEngineeringExt.getPhysicalArchitecture(se);
    rootPf = (PhysicalFunction) BlockArchitectureExt.getRootFunction(pa);

    executeCommand(() -> {
      pfRec1 = PaFactory.eINSTANCE.createPhysicalFunction("pf1");
      pfRec2 = PaFactory.eINSTANCE.createPhysicalFunction("pf2");
      rootPf.getOwnedFunctions().add(pfRec1);
      rootPf.getOwnedFunctions().add(pfRec2);
    });

    rec = createREC(Arrays.asList(pfRec1, pfRec2));
    rpl1 = createReplica(Collections.singleton(pfRec1), rec, "SUFFIX");

    for (CatalogElementLink link : rpl1.getOwnedLinks()) {
      if (link.getOrigin().getTarget() == pfRec1) {
        pfRpl1 = (PhysicalFunction) link.getTarget();
      }
      if (link.getOrigin().getTarget() == pfRec2) {
        pfRpl2 = (PhysicalFunction) link.getTarget();
      }
    }
    assertNotNull(pfRpl1);
    assertNotNull(pfRpl2);

    //FIXME pfRpl1 and 2 have no container.. for now... just help out
    expectNoRollback(() -> rootPf.getOwnedFunctions().addAll(Arrays.asList(pfRpl1, pfRpl2)));

    // make the rpl readonly
    expectNoRollback(() -> rpl1.setReadOnly(true));

    // now delete a rpl function, which should rollback
    new CapellaDeleteCommand(TransactionHelper.getExecutionManager(pfRpl1), Collections.singleton(pfRpl1), true, false, false).execute();
    assertTrue(pfRpl1.eContainer() == rootPf); // 'test' for rollback

    // now delete the rpl function and its rpl link, which should also rollback
    new CapellaDeleteCommand(TransactionHelper.getExecutionManager(pfRpl1), Arrays.asList(pfRpl1, ReplicableElementExt.getReferencingLinks(pfRpl1).iterator().next()), true, false, false).execute();
    assertTrue(pfRpl1.eContainer() == rootPf); // 'test' for rollback

    // nest the rpl functions..
    expectRollback(() -> pfRpl1.getOwnedFunctions().add(pfRpl2));

    // nest the rec and the rpl functions, this should work
    expectNoRollback(() -> {
      pfRpl1.getOwnedFunctions().add(pfRpl2);
      pfRec1.getOwnedFunctions().add(pfRec2);
    });

    // un-nest the rpl function should rollback
    expectRollback(() -> rootPf.getOwnedFunctions().add(pfRpl2));

    // un-nest the rec function should work
    expectNoRollback(() -> rootPf.getOwnedFunctions().add(pfRec2));

    // now we can also un-nest the rpl function without a rollback
    expectNoRollback(() -> rootPf.getOwnedFunctions().add(pfRpl2));

    // now delete the rec function and update the rec
    new CapellaDeleteCommand(TransactionHelper.getExecutionManager(pfRec1), Collections.singleton(pfRec1), true, false, false).execute();
    assertTrue(pfRec1.eResource() == null);
    updateCur(Collections.singleton(pfRec2), rec);

    // now update the replica, which should remove pfRpl1
    updateReplica(Collections.singleton(pfRpl2), rpl1);
    assertTrue(pfRpl1.eResource() == null);

  }

}
