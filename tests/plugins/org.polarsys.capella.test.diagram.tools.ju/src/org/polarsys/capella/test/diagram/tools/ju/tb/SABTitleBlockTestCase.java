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

public class SABTitleBlockTestCase extends XABTitleBlockTestCase {

  private static final String ACTOR_ID = "9dcae60f-09c0-4fe1-b9c9-990c36bedc14";
  private static final String DIAGRAM_NAME = "[SAB] Structure";
  private static final String STRUCTURE_PKG = "0bb7473d-46f3-4284-b40c-09059e271e36";

  @Override
  public void initData() {
    elementsId.add(ACTOR_ID);
    diagramName = DIAGRAM_NAME;
    type = BlockArchitectureExt.Type.SA;
    structurePKG = STRUCTURE_PKG;
  }

}
