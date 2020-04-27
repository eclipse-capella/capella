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

import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class RemoveElement_CatalogElementLink extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC and remove a linked element. CatalogElementLink should be removed
    CatalogElement REC = createREC(getObjects(LF1, LF2, FUNCTIONALEXCHANGE_1));
    int nbLinks = REC.getOwnedLinks().size();

    // We remove the functional exchange
    CapellaDeleteCommand delete =
        new CapellaDeleteCommand(TransactionHelper.getExecutionManager(getObject(FUNCTIONALEXCHANGE_1)), Arrays.asList(getObject(FUNCTIONALEXCHANGE_1)),
            true, false, false);
    delete.execute();

    // REC Link towards FE1 must be removed
    int nbLinks2 = REC.getOwnedLinks().size();
    assertTrue((nbLinks - 1) == nbLinks2);
  }

}
