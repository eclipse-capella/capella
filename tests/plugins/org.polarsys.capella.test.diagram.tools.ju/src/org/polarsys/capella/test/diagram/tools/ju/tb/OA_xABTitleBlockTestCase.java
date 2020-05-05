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

public class OA_xABTitleBlockTestCase extends xABTitleBlockTestCase {
  private static final String ACTOR_ID = "134804c9-802b-4b16-80fa-4993456bb82b";
  private static final String ENTITY_ID = "56e2d841-e27c-49e1-ab37-37498d8ecbb5";
  private static final String DIAGRAM_NAME = "[OAB] Operational Entities";

  @Override
  public void initData() {
    elementsId.add(ACTOR_ID);
    elementsId.add(ENTITY_ID);
    diagramName = DIAGRAM_NAME;
    type = BlockArchitectureExt.Type.OA;
  }

}
