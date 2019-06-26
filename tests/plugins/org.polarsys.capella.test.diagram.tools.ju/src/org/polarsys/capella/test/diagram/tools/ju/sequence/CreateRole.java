/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.sequence;

import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.OA_ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;

public class CreateRole extends SequenceTest {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] typesES = { BlockArchitectureExt.Type.OA };
    testOnAllLevels(typesES, SequenceType.ES);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    ((OA_ESDiagram)diagram).createRole();
  }
}
