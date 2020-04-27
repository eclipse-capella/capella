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

import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class CreateREC_FunctionalExchange extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC with 2 functions and an exchange. Ports must be included
    CatalogElement REC = createREC(getObjects(LF1, LF2, FUNCTIONALEXCHANGE_1));
    mustReference(REC, getObject(LF1));
    mustReference(REC, getObject(LF2));
    mustReference(REC, getObject(FUNCTIONALEXCHANGE_1));
    mustReference(REC, getObject(FIP_1));
    mustReference(REC, getObject(FOP_1));
  }

}
