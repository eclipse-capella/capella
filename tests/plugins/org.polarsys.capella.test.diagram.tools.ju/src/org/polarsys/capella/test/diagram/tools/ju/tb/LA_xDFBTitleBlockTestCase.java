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

public class LA_xDFBTitleBlockTestCase extends xDFBTitleBlockTestCase {

  private static final String ACTOR_ID = "310a22b0-8d6c-4d5d-bd6b-806f33dc17b2";
  private static final String FUNCTION_ID = "b1eb04f5-ac15-43b9-b25b-df8389c224c3";
  private static final String DIAGRAM_NAME = "[LDFB] Root Logical Function";

  @Override
  protected void initData() {
    elementsId.add(ACTOR_ID);
    elementsId.add(FUNCTION_ID);
    diagramName = DIAGRAM_NAME;
    type = BlockArchitectureExt.Type.LA;
  }

}
