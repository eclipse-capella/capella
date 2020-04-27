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
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class UpdateRPL_RemoveElement extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC with 2 functions and an exchange
    CatalogElement REC = createREC(getObjects(LF1, LF2, FUNCTIONALEXCHANGE_1));

    // Create a RPL, elements should be created
    CatalogElement RPL = createReplica(getObjects(LF1, LF2, FUNCTIONALEXCHANGE_1), REC);
    EObject FErpl = ReplicableElementExt.getReferencingElement(RPL, getObject(FUNCTIONALEXCHANGE_1));

    // We remove the functional exchange of the REC
    CapellaDeleteCommand delete =
        new CapellaDeleteCommand(TransactionHelper.getExecutionManager(getObject(FUNCTIONALEXCHANGE_1)), Arrays.asList(getObject(FUNCTIONALEXCHANGE_1)), true,
            false, false);
    delete.execute();

    // Update a RPL, default behavior
    // We update the RPL after having removed an element on the REC, functional exchange of the RPL is removed by default
    updateReplica(Collections.singletonList((EObject) RPL), RPL);
    assertTrue(ReplicableElementExt.getReferencingReplicableElements(getObject(LF1)).size() == 1);
    mustNotReference(RPL, FErpl);

  }

}
