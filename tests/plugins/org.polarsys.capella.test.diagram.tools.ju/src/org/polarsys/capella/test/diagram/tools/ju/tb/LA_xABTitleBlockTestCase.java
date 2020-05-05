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

public class LA_xABTitleBlockTestCase extends xABTitleBlockTestCase {
  private static final String ACTOR_ID = "10a3c71c-3a7a-47b4-a554-b29d2c0ede35";
  private static final String COMPONENT_ID = "503e9596-b811-47d2-9531-4ec463c6c33b";
  private static final String DIAGRAM_NAME = "[LAB] Structure";

  @Override
  public void initData() {
    elementsId.add(ACTOR_ID);
    elementsId.add(COMPONENT_ID);
    diagramName = DIAGRAM_NAME;
    type = BlockArchitectureExt.Type.LA;
  }
}
