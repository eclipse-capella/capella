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

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.FSDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.ISDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;

public class DragAndDropTest extends SequenceTest {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] typesES = { BlockArchitectureExt.Type.OA, BlockArchitectureExt.Type.SA,
        BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };
    BlockArchitectureExt.Type[] typesFS = { BlockArchitectureExt.Type.OA, BlockArchitectureExt.Type.SA,
        BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };
    BlockArchitectureExt.Type[] typesIS = { BlockArchitectureExt.Type.SA, BlockArchitectureExt.Type.LA,
        BlockArchitectureExt.Type.PA };
    testOnAllLevels(typesES, SequenceType.ES);
    testOnAllLevels(typesFS, SequenceType.FS);
    testOnAllLevels(typesIS, SequenceType.IS);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    BlockArchitectureExt.Type type = diagram.getDiagramBlockArchitecture();
    XABDiagram setupDiagram = xabSetupDiagram(type);

    if (diagram instanceof ESDiagram) {
      testDragandDropComponentES(diagram, setupDiagram, type);
    } else if (diagram instanceof FSDiagram) {
      testDragandDropComponentFS(diagram, setupDiagram);
    } else if (diagram instanceof ISDiagram) {
      testDragandDropComponentIS(diagram, setupDiagram);
    }

    testDragandDropConstraint(diagram, setupDiagram, type);
  }

  private void testDragandDropComponentES(SequenceDiagram diagram, XABDiagram setupDiagram,
      BlockArchitectureExt.Type type) {
    String component = setupDiagram.createActor("a1");
    if (type != BlockArchitectureExt.Type.OA) {
      Part partObj = setupDiagram.getSessionContext().getSemanticElement(component);
      component = partObj.getAbstractType().getId();
    }

    diagram.dragAndDropComponentFromExplorer(component, diagram.getDiagramId());
  }

  private void testDragandDropComponentFS(SequenceDiagram diagram, XABDiagram setupDiagram) {
    String container = setupDiagram.createActor("a1");

    String function = setupDiagram.createFunction("f1", container);
    diagram.dragAndDropComponentFromExplorer(function, diagram.getDiagramId());
  }

  private void testDragandDropComponentIS(SequenceDiagram diagram, XABDiagram setupDiagram) {
    String component = setupDiagram.createActor("a1");
    Part partObj = setupDiagram.getSessionContext().getSemanticElement(component);
    component = partObj.getAbstractType().getId();

    diagram.dragAndDropComponentFromExplorer(component, diagram.getDiagramId());
  }

  private void testDragandDropConstraint(SequenceDiagram diagram, XABDiagram setupDiagram,
      BlockArchitectureExt.Type type) {
    String constraint = setupDiagram.createConstraint("cn1");
    if (type != BlockArchitectureExt.Type.OA) {
      diagram.dragAndDropConstraintFromExplorer(constraint, diagram.getDiagramId());
    } else {
      diagram.dragAndDropConstraintsFromExplorer(constraint, diagram.getDiagramId());
    }
  }

  private XABDiagram xabSetupDiagram(BlockArchitectureExt.Type type) {
    String xabTargetId = "";
    switch (type) {
    case OA:
      xabTargetId = OA__OPERATIONAL_CONTEXT;
      break;
    case SA:
      xabTargetId = SA__SYSTEM;
      break;
    case LA:
      xabTargetId = LA__LOGICAL_SYSTEM;
      break;
    case PA:
      xabTargetId = PA__PHYSICAL_SYSTEM;
      break;
    default:
      break;
    }

    return XABDiagram.createDiagram(context, xabTargetId);
  }

}
