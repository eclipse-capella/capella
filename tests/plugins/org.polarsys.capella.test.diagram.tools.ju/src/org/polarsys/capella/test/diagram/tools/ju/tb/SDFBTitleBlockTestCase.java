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

public class SDFBTitleBlockTestCase extends XDFBTitleBlockTestCase {

  private static final String ACTOR_ID = "898cbd05-174b-4f1f-abc8-8c1d2166d76c";
  private static final String FUNCTION_ID = "fb9be027-0512-4d43-ad54-aa2aeb2dece5";
  private static final String DIAGRAM_NAME = "[SDFB] Root System Function";
  private static final String ROOT_FUNCTION = "ac4dced8-3779-47e3-b3d6-489e265cd9ec";

  @Override
  protected void initData() {
    elementsId.add(ACTOR_ID);
    elementsId.add(FUNCTION_ID);
    diagramName = DIAGRAM_NAME;
    type = BlockArchitectureExt.Type.SA;
    rootFunction = ROOT_FUNCTION;
  }

}
