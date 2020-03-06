/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.tb;

import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

public class EPBS_CRBTitleBlockTestCase extends CRBTitleBlockTestCase {

  private static final String COTS_ID = "71c0b60c-b092-49f5-b36a-a153d3e20543";
  private static final String CAPABILITY_ID = "1b1cda62-3ccc-4d1d-8886-e445c5cf4b6d";
  private static final String DIAGRAM_NAME = "[CRB] Capabilities EPBS";
  private static final String CAPABILITY_PKG = "fd7a8f16-029d-42dd-8e8c-ff8f6ba21a9f";

  @Override
  public void initData() {
    elementsId.add(COTS_ID);
    elementsId.add(CAPABILITY_ID);
    diagramName = DIAGRAM_NAME;
    type = BlockArchitectureExt.Type.EPBS;
    capabilityPKG = CAPABILITY_PKG;
  }

}
