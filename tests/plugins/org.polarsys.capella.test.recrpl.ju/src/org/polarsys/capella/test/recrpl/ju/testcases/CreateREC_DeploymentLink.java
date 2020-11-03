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

import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class CreateREC_DeploymentLink extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC with 2 PC. Deployment must be include (only 1 deployment)
    CatalogElement REC = createREC(getObjects(PC_1, PC_2));
    mustReference(REC, getObject(PC_1));
    mustReference(REC, getObject(PC_1__PC_1));
    mustReference(REC, getObject(PC_2));
    mustReference(REC, getObject(PC_2__PC_2));
    mustReference(REC, getObject(PART_DEPLOYMENT_LINK_TO_PC_2));
    mustNotReference(REC, getObject(PC_3));
    mustNotReference(REC, getObject(PC_3__PC_3));
    mustNotReference(REC, getObject(PART_DEPLOYMENT_LINK_TO_PC_3));

    // Add PC3, Deployment must be included
    updateCur(getObjects(PC_1, PC_3), REC);
    mustReference(REC, getObject(PC_3));
    mustReference(REC, getObject(PC_3__PC_3));
    mustReference(REC, getObject(PART_DEPLOYMENT_LINK_TO_PC_3));
  }

}
