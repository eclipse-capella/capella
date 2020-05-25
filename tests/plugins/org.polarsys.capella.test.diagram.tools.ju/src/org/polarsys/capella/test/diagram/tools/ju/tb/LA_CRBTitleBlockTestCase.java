/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.tb;

import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

public class LA_CRBTitleBlockTestCase extends CRBTitleBlockTestCase {

  private static final String ACTOR_ID = "1351de2e-887d-43ec-8e8d-1b26af059c7d";
  private static final String COMPONENT_ID = "a473fc33-aa5e-4d8d-a39c-d1c3587083ef";
  private static final String CAPABILITY_ID = "66636c52-7019-4a03-88a2-05e1bc0c1ae5";
  private static final String DIAGRAM_NAME = "[CRB] Capabilities";
  private static final String CAPABILITY_PKG = "0f515f7b-6c03-4240-b848-c505d0f7411e";

  @Override
  public void initData() {
    elementsId.add(ACTOR_ID);
    elementsId.add(COMPONENT_ID);
    elementsId.add(CAPABILITY_ID);
    diagramName = DIAGRAM_NAME;
    type = BlockArchitectureExt.Type.LA;
    capabilityPKG = CAPABILITY_PKG;
  }

}
