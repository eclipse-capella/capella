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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class UpdateDefREC_RemoveElement extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC with 2 functions and an exchange
    CatalogElement REC = createREC(getObjects(LF1, LF2, FUNCTIONALEXCHANGE_1));

    // Create a RPL, elements should be created
    CatalogElement RPL = createReplica(getObjects(LF3), REC);

    // We remove the functional exchange of the RPL
    EObject fe = ReplicableElementExt.getReferencingElement(RPL, getObject(FUNCTIONALEXCHANGE_1));
    CapellaDeleteCommand delete = new CapellaDeleteCommand(TransactionHelper.getExecutionManager(fe), Arrays.asList(fe), true, false, false);
    delete.execute();

    // After updating the REC, the functional exchange is not referenced any more
    Collection<EObject> expectedReferencedElements = new ArrayList<EObject>(REC.getReferencedElements());
    expectedReferencedElements.remove(getObject(FUNCTIONALEXCHANGE_1));

    // We update the REC according the selected RPL after removing an element on the RPL
    EObject lf1 = ReplicableElementExt.getReferencingElement(RPL, getObject(LF1));
    updateDef(Collections.singletonList(lf1));

    // The REC shouldn't reference the functional exchange any more
    assertEquals(expectedReferencedElements, REC.getReferencedElements());
  }

}
