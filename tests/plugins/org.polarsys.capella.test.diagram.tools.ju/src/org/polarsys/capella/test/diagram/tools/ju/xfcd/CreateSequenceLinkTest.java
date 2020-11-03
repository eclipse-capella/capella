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

public class CreateSequenceLinkTest extends FCDSequencingTest {

  public CreateSequenceLinkTest(Settings settings) {
    super(settings);
  }

  @Override
  protected void testLevel0() {
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL0);

    testSequenceLinkLevel0();

    xfcd.close();
  }

  @Override
  protected void testLevel1() {
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL1);

    testSequenceLinkLevel1();

    xfcd.close();
  }

  @Override
  protected void testLevel2() {
    initTest();
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL2);

    testSequenceLinkLevel2();

    xfcd.close();
  }

  protected void testSequenceLinkLevel0() {
    // SL between 2 FCIF
    xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION1_LEVEL0, settings.SEQ_FCIFUNCTION4_LEVEL0);
    // SL between FCIF and CN
    xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION1_LEVEL0, settings.CONTROL_NODE1_LEVEL0);
    xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL0, settings.SEQ_FCIFUNCTION4_LEVEL0);

    // cannot create (when cycle)
    xfcd.cannotCreateSequenceLink(settings.SEQ_FCIFUNCTION1_LEVEL0, settings.SEQ_FCIFUNCTION1_LEVEL0);
    xfcd.cannotCreateSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL0, settings.SEQ_FCIFUNCTION1_LEVEL0);
    xfcd.cannotCreateSequenceLink(settings.CONTROL_NODE1_LEVEL0, settings.CONTROL_NODE1_LEVEL0);
  }

  protected void testSequenceLinkLevel1() {
    // SL between 2 FCIF
    xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION3_LEVEL1, settings.SEQ_FCIFUNCTION4_LEVEL1);
    // SL between FCIF and CN
    xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION3_LEVEL1, settings.CONTROL_NODE1_LEVEL1);
    xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL1, settings.SEQ_FCIFUNCTION4_LEVEL1);

    // SL to a objects from a referenced FC
    xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL1, settings.SEQ_FCIFUNCTION3_LEVEL0);
    xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL1, settings.CONTROL_NODE1_LEVEL0);
    xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL1, settings.CONTROL_NODE1_LEVEL0);
    xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL1, settings.SEQ_FCIFUNCTION3_LEVEL0);

    // SL from objects from a referenced FC
    xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION1_LEVEL0, settings.SEQ_FCIFUNCTION3_LEVEL1);
    xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL0, settings.SEQ_FCIFUNCTION5_LEVEL1);

    // cannot create (when cycle)
    xfcd.cannotCreateSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL1, settings.SEQ_FCIFUNCTION4_LEVEL1);
    xfcd.cannotCreateSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL1, settings.SEQ_FCIFUNCTION1_LEVEL0);

    // create from this diagram at level 1, SL between elements from FCR
    testSequenceLinkLevel0();
  }

  protected void testSequenceLinkLevel2() {
    // SL between 2 FCIF
    xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION1_LEVEL2, settings.SEQ_FCIFUNCTION4_LEVEL2);
    // SL between FCIF and CN
    xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION1_LEVEL2, settings.CONTROL_NODE1_LEVEL2);
    xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL2, settings.SEQ_FCIFUNCTION4_LEVEL2);
    xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL2, settings.CONTROL_NODE2_LEVEL2);

    // SL to a objects from a referenced FC1 Level 0
    xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION5_LEVEL2, settings.SEQ_FCIFUNCTION4_LEVEL0);
    xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION5_LEVEL2, settings.CONTROL_NODE1_LEVEL0);
    xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL2, settings.CONTROL_NODE1_LEVEL0);
    xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL2, settings.SEQ_FCIFUNCTION4_LEVEL0);

    // SL from objects from a referenced FC1 Level 0
    xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION1_LEVEL0, settings.SEQ_FCIFUNCTION5_LEVEL2);
    xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION1_LEVEL0, settings.CONTROL_NODE1_LEVEL2);
    xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL0, settings.CONTROL_NODE2_LEVEL2);
    xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL0, settings.SEQ_FCIFUNCTION6_LEVEL2);

    // SL to a objects from a referenced FC1 Level 1
    xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION7_LEVEL2, settings.CONTROL_NODE1_LEVEL1);
    xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL2, settings.CONTROL_NODE1_LEVEL1);

    // create from this diagram at level 2, SL between elements from FCR
    testSequenceLinkLevel0();
    testSequenceLinkLevel1();
  }
}
