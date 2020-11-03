/*******************************************************************************
 * Copyright (c) 2019, 2020, THALES GLOBAL SERVICES.
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

import org.polarsys.capella.test.diagram.common.ju.context.XFCDDiagram;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.utils.FCDSequencingTest;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.utils.Settings;

public class CreateFunctionOnSequenceLinkTest extends FCDSequencingTest {

  public CreateFunctionOnSequenceLinkTest(Settings settings) {
    super(settings);
  }

  @Override
  protected void testLevel0() {
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL0);

    testCreateFunctionOnSequenceLinkLevel0();

    xfcd.close();
  }

  @Override
  protected void testLevel1() {
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL1);

    testCreateFunctionOnSequenceLinkLevel1();

    xfcd.close();
  }

  @Override
  protected void testLevel2() {
    initTest();
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL2);

    testCreateFunctionOnSequenceLinkLevel2();

    xfcd.close();
  }

  protected void testCreateFunctionOnSequenceLinkLevel0() {
    // create a FCIF on a SL connecting two functions
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION1, settings.SEQUENCE_LINK_1_LEVEL0);

    // create a FCIF on a SL connecting a function and a CN
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION2, settings.SEQUENCE_LINK_1_1_LEVEL0);
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION3, settings.SEQUENCE_LINK_1_2_LEVEL0);

    // create a FCIF on a SL connecting two CN
    String sequenceLink = xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL0, settings.CONTROL_NODE2_LEVEL0);
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION1, sequenceLink);
  }

  protected void testCreateFunctionOnSequenceLinkLevel1() {
    // create a FCIF on a SL connecting two functions from diagram
    String sequenceLink = xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL1, settings.SEQ_FCIFUNCTION5_LEVEL1);
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION1, sequenceLink);

    // create a FCIF on a SL connecting one CN and one function from diagram
    sequenceLink = xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL1, settings.SEQ_FCIFUNCTION5_LEVEL1);
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION1, sequenceLink);

    // create a FCIF on a SL connecting two CN
    sequenceLink = xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL1, settings.CONTROL_NODE2_LEVEL1);
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION2, sequenceLink);

    // create a FCIF on a SL connecting two functions (one from diagram, one from FCR)
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION1, settings.SEQUENCE_LINK_2_LEVEL1);

    // create a FCIF on a SL connecting two CN (one from diagram, other from FCR)
    sequenceLink = xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL0, settings.CONTROL_NODE1_LEVEL1);
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION1, sequenceLink);

    // create a FCIF on a SL connecting one CN from diagram and one function from FCR
    sequenceLink = xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL1, settings.SEQ_FCIFUNCTION3_LEVEL0);
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION1, sequenceLink);
  }

  protected void testCreateFunctionOnSequenceLinkLevel2() {
    // create a FCIF on a SL connecting two functions from diagram
    String sequenceLink = xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL2, settings.SEQ_FCIFUNCTION5_LEVEL2);
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION1, sequenceLink);

    // create a FCIF on a SL connecting one CN and one function from diagram
    sequenceLink = xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL2, settings.SEQ_FCIFUNCTION5_LEVEL2);
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION1, sequenceLink);

    // create a FCIF on a SL connecting two CN
    sequenceLink = xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL2, settings.CONTROL_NODE2_LEVEL2);
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION2, sequenceLink);

    // create a FCIF on a SL connecting two functions (one from diagram, one from FCR level 1)
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION1, settings.SEQUENCE_LINK_4_LEVEL2);

    // create a FCIF on a SL connecting two functions (one from diagram, one from FCR level 1)
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION1, settings.SEQUENCE_LINK_4_LEVEL2);

    // create a FCIF on a SL connecting two functions (one from diagram, one from FCR level 2)
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION2, settings.SEQUENCE_LINK_5_LEVEL2);

    // create a FCIF on a computed SL
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION3, settings.SEQUENCE_LINK_6_LEVEL2);
    xfcd.createFunctionOnSequenceLink(settings.SEQ_SYSTEMFUNCTION4, settings.SEQUENCE_LINK_7_LEVEL2);
  }
}
