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
import org.polarsys.capella.test.diagram.common.ju.context.FSDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;

public class CreateFunction extends SequenceTest {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] types = { BlockArchitectureExt.Type.OA, BlockArchitectureExt.Type.LA,
        BlockArchitectureExt.Type.PA };

    testOnAllLevels(types, SequenceType.FS);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    if (diagram instanceof FSDiagram) {
      FSDiagram fsDiagram = (FSDiagram) diagram;
      function1 = fsDiagram.createFunction();
      function2 = fsDiagram.createFunction();
      if (diagram.getDiagramBlockArchitecture() != BlockArchitectureExt.Type.OA) {
        fsDiagram.createFunction(BlockArchitectureExt.FunctionType.DUPLICATE);
        fsDiagram.createFunction(BlockArchitectureExt.FunctionType.GATHER);
        fsDiagram.createFunction(BlockArchitectureExt.FunctionType.ROUTE);
        fsDiagram.createFunction(BlockArchitectureExt.FunctionType.SELECT);
        fsDiagram.createFunction(BlockArchitectureExt.FunctionType.SPLIT);
      }
    }
  }
}
