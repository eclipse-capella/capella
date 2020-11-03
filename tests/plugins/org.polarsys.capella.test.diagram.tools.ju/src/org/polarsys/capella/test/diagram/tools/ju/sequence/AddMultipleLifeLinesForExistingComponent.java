/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;

public class AddMultipleLifeLinesForExistingComponent extends SequenceTest {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] typesES = { BlockArchitectureExt.Type.OA, BlockArchitectureExt.Type.SA };
    BlockArchitectureExt.Type[] typesIS = { BlockArchitectureExt.Type.SA };

    testOnAllLevels(typesES, SequenceType.ES);
    testOnAllLevels(typesIS, SequenceType.IS);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    List<Part> parts = diagram.getContainedParts(scenario);
    for (Part part : parts) {
      if(diagram.getDiagramBlockArchitecture() == BlockArchitectureExt.Type.OA) {
        diagram.addMultipleLifeLinesForExistingComponent(part.getId());
      }
      else if (part.getAbstractType() instanceof Component) {
        diagram.addMultipleLifeLinesForExistingComponent(part.getId());
      }
    }
  }
  
  @Override
  public void setUpDiagram(SequenceDiagram diagram) {
    actor1 = diagram.createActor();
    actor2 = diagram.createActor();
  }
}
