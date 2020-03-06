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

public class OAIBTitleBlockTestCase extends XDFBTitleBlockTestCase {

  private static final String ACTIVITY_ID = "71f32d65-40cb-416b-97ce-f71b03614d1c";
  private static final String DIAGRAM_NAME = "[OAIB] Root Operational Activity";
  private static final String ROOT_FUNCTION = "d6144390-0bac-4b12-832b-575b28c8f369";

  @Override
  protected void initData() {
    elementsId.add(ACTIVITY_ID);
    diagramName = DIAGRAM_NAME;
    type = BlockArchitectureExt.Type.OA;
    rootFunction = ROOT_FUNCTION;
  } 

}
