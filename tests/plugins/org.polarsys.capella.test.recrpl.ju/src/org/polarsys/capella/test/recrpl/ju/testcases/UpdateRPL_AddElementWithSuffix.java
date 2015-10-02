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

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class UpdateRPL_AddElementWithSuffix extends Re {

  @Override
  public void performTest() throws Exception {

    String suffix = "_SUFFIX";

    // Create a REC
    CatalogElement REC = createREC(getObjects(LF1, LF2));

    EObject REC_LF1 = getObject(LF1);
    EObject REC_LF2 = getObject(LF2);
    EObject REC_LF3 = getObject(LF3);
    String LF1name = ((LogicalFunction) REC_LF1).getName();
    String LF2name = ((LogicalFunction) REC_LF2).getName();
    String LF3name = ((LogicalFunction) REC_LF3).getName();

    // Ensure LF1 and LF2 will be suffixed
    setSuffixed(REC, LF1);
    setSuffixed(REC, LF2);

    // Create somewhere a RPL with a suffix
    CatalogElement RPL = createReplica(getObjects(LF4), REC, suffix);

    // Name of LF1 and LF2 should have suffix
    EObject RPL_LF1 = ReplicableElementExt.getReferencingElement(RPL, getObject(LF1));
    EObject RPL_LF2 = ReplicableElementExt.getReferencingElement(RPL, getObject(LF2));
    assertEquals(((LogicalFunction) RPL_LF1).getName(), LF1name + suffix);
    assertEquals(((LogicalFunction) RPL_LF2).getName(), LF2name + suffix);

    // Rename the RPL LF2 without suffix even if it is suffixable, update RPL should not rename it
    ((LogicalFunction) RPL_LF2).setName("suffixableRenamed"); // we set a name without suffix on a suffixable element
    updateReplica(Collections.singletonList((EObject) RPL), RPL);
    assertEquals(((LogicalFunction) RPL_LF1).getName(), LF1name + suffix);
    assertEquals(((LogicalFunction) RPL_LF2).getName(), "suffixableRenamed");

    // update the REC adding LF3, rename REC LF1 with newName
    updateCur(getObjects(LF1, LF2, LF3), REC);
    setSuffixed(REC, LF3);
    ((LogicalFunction) REC_LF1).setName("newName");

    // update the RPL with new suffix:
    // - must update RPL LF1 since its finish with previous suffix
    // - should not update RPL LF2 since doesn't contains previous suffix
    // - RPL LF3 should be suffixed
    String newSuffix = "NEW";
    updateReplica(Collections.singletonList((EObject) RPL), RPL, newSuffix);
    EObject RPL_LF3 = ReplicableElementExt.getReferencingElement(RPL, getObject(LF3));
    assertEquals(((LogicalFunction) RPL_LF1).getName(), "newName" + newSuffix);
    assertEquals(((LogicalFunction) RPL_LF2).getName(), "suffixableRenamed");
    assertEquals(((LogicalFunction) RPL_LF3).getName(), LF3name + newSuffix);

    // Another update should do nothing more
    updateReplica(Collections.singletonList((EObject) RPL), RPL, newSuffix);
    assertEquals(((LogicalFunction) RPL_LF1).getName(), "newName" + newSuffix);
    assertEquals(((LogicalFunction) RPL_LF2).getName(), "suffixableRenamed");
    assertEquals(((LogicalFunction) RPL_LF3).getName(), LF3name + newSuffix);

    // If user check all checkbox, names should be synchronized with suffix
    updateReplicaCheck(Collections.singletonList((EObject) RPL), RPL);
    assertEquals(((LogicalFunction) RPL_LF1).getName(), ((LogicalFunction) REC_LF1).getName() + newSuffix);
    assertEquals(((LogicalFunction) RPL_LF2).getName(), ((LogicalFunction) REC_LF2).getName() + newSuffix);
    assertEquals(((LogicalFunction) RPL_LF3).getName(), ((LogicalFunction) REC_LF3).getName() + newSuffix);

  }
}
