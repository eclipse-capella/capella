/*******************************************************************************
 * Copyright (c) 2019, THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xfcd;

import org.polarsys.capella.core.data.fa.ControlNodeKind;
import org.polarsys.capella.test.diagram.common.ju.context.XFCDDiagram;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.utils.FCDSequencingTest;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.utils.Settings;

public class CreateControlNodeTest extends FCDSequencingTest {

  public CreateControlNodeTest(Settings settings) {
    super(settings);
  }

  @Override
  protected void testLevel0() {
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL0);

    testControlNodeTypeLevel0(ControlNodeKind.AND);
    testControlNodeTypeLevel0(ControlNodeKind.OR);
    testControlNodeTypeLevel0(ControlNodeKind.ITERATE);

    xfcd.close();
  }

  @Override
  protected void testLevel1() {
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL1);

    testControlNodeTypeLevel1(ControlNodeKind.AND);
    testControlNodeTypeLevel1(ControlNodeKind.OR);
    testControlNodeTypeLevel1(ControlNodeKind.ITERATE);

    xfcd.close();
  }

  @Override
  protected void testLevel2() {
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL2);

    testControlNodeTypeLevel2(ControlNodeKind.AND);
    testControlNodeTypeLevel2(ControlNodeKind.OR);
    testControlNodeTypeLevel2(ControlNodeKind.ITERATE);

    xfcd.close();
  }

  protected void testControlNodeTypeLevel0(ControlNodeKind kind) {
    // create the ControlNode inside the diagram (container is the diagram)
    xfcd.createControlNode(xfcd.getDiagramId(), kind);

    // create the ControlNode on a existing SequenceLink
    xfcd.createControlNode(settings.SEQUENCE_LINK_1_LEVEL0, xfcd.getDiagramId(), kind);

    // create a ContronNode on a SL between a FCIF and another CN
    xfcd.createControlNode(settings.SEQUENCE_LINK_1_1_LEVEL0, xfcd.getDiagramId(), kind);
    xfcd.createControlNode(settings.SEQUENCE_LINK_1_2_LEVEL0, xfcd.getDiagramId(), kind);
  }

  protected void testControlNodeTypeLevel1(ControlNodeKind kind) {
    // create the ControlNode inside the diagram (container is the diagram)
    xfcd.createControlNode(xfcd.getDiagramId(), kind);

    // create the ControlNode on a existing SequenceLink
    xfcd.createControlNode(settings.SEQUENCE_LINK_2_LEVEL1, xfcd.getDiagramId(), kind);

    // create the ControlNode inside a FunctionalChainReference (FC1)
    xfcd.createControlNode(settings.SEQUENCEFC1, kind);

    // create the ControlNode inside a FunctionalChainReference (FC1) on a sequence link
    xfcd.createControlNode(settings.SEQUENCE_LINK_1_LEVEL0, settings.SEQUENCEFC1, kind);

    // create a ContronNode on a SL between a FCIF and another CN
    xfcd.createControlNode(settings.SEQUENCE_LINK_1_1_LEVEL0, settings.SEQUENCEFC1, kind);
    xfcd.createControlNode(settings.SEQUENCE_LINK_1_2_LEVEL0, settings.SEQUENCEFC1, kind);
  }

  protected void testControlNodeTypeLevel2(ControlNodeKind kind) {
    // create the ControlNode inside the diagram (container is the diagram)
    xfcd.createControlNode(xfcd.getDiagramId(), kind);

    xfcd.createControlNode(settings.SEQUENCE_LINK_4_LEVEL2, xfcd.getDiagramId(), kind);
    xfcd.createControlNode(settings.SEQUENCE_LINK_5_LEVEL2, xfcd.getDiagramId(), kind);

    // create the ControlNode on a computed SequenceLink
    xfcd.createControlNode(settings.SEQUENCE_LINK_3_LEVEL2, xfcd.getDiagramId(), kind);
    xfcd.createControlNode(settings.SEQUENCE_LINK_6_LEVEL2, xfcd.getDiagramId(), kind);
    xfcd.createControlNode(settings.SEQUENCE_LINK_7_LEVEL2, xfcd.getDiagramId(), kind);

    // create a standalone control node inside a FCReference
    xfcd.createControlNode(settings.SEQUENCEFC1, kind);
    xfcd.createControlNode(settings.SEQUENCEFC2, kind);

    // create a control node inside a FCReference on a SL
    xfcd.createControlNode(settings.SEQUENCE_LINK_2_LEVEL1, settings.SEQUENCEFC2, kind);
    xfcd.createControlNode(settings.SEQUENCE_LINK_1_LEVEL0, settings.SEQUENCEFC1, kind);

    // create a ContronNode on a SL between a FCIF and another CN
    xfcd.createControlNode(settings.SEQUENCE_LINK_1_1_LEVEL0, settings.SEQUENCEFC1, kind);
    xfcd.createControlNode(settings.SEQUENCE_LINK_1_2_LEVEL0, settings.SEQUENCEFC1, kind);
  }
}
