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

public class PA_CRBTitleBlockTestCase extends CRBTitleBlockTestCase {

  private static final String ACTOR_ID = "27f55958-60dc-400a-b284-2d1fa0653974";
  private static final String COMPONENT_ID = "961eecdb-26a4-4d3f-a1f7-8b36f470f534";
  private static final String CAPABILITY_ID = "ee54e52c-4357-4608-87b3-de0f7370a93c";
  private static final String DIAGRAM_NAME = "[CRB] Capabilities Physical";
  private static final String CAPABILITY_PKG = "0cfbe3e4-cb26-41cf-93e1-16730241bb16";

  @Override
  public void initData() {
    elementsId.add(ACTOR_ID);
    elementsId.add(COMPONENT_ID);
    elementsId.add(CAPABILITY_ID);
    diagramName = DIAGRAM_NAME;
    type = BlockArchitectureExt.Type.PA;
    capabilityPKG = CAPABILITY_PKG;
  }

}
