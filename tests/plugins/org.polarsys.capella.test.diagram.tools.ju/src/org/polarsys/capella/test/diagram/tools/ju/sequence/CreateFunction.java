/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
