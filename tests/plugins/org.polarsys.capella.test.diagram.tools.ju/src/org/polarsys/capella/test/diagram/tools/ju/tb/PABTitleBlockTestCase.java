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

public class PABTitleBlockTestCase extends XABTitleBlockTestCase {

  private static final String ACTOR_ID = "13cccb6a-687d-4a81-89b7-69bdde6443cb";
  private static final String NODE_ID = "cd02d8ef-bb7f-4d09-ae46-d3c434b3999d";
  private static final String DIAGRAM_NAME = "[PAB] Structure";

  @Override
  public void initData() {
    elementsId.add(ACTOR_ID);
    elementsId.add(NODE_ID);
    diagramName = DIAGRAM_NAME;
    type = BlockArchitectureExt.Type.PA;
  }

}
