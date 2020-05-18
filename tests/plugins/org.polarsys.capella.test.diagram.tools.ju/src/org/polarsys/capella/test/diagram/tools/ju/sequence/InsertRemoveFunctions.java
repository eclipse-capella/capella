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

public class InsertRemoveFunctions extends SequenceTest {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] types = { BlockArchitectureExt.Type.OA, BlockArchitectureExt.Type.SA,
        BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };

    testOnAllLevels(types, SequenceType.FS);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    if (diagram instanceof FSDiagram) {
      FSDiagram fsDiagram = (FSDiagram) diagram;
      setUpDiagram(fsDiagram);

      fsDiagram.removeFunction(function1);
      fsDiagram.insertFunction(function1);
      fsDiagram.removeFunction(function2);
      fsDiagram.insertFunction(function2);
    }
  }

  @Override
  public void setUpDiagram(SequenceDiagram diagram) {
    FSDiagram fsDiagram = (FSDiagram) diagram;
    function1 = fsDiagram.createFunction();
    function2 = fsDiagram.createFunction();
  }
}
