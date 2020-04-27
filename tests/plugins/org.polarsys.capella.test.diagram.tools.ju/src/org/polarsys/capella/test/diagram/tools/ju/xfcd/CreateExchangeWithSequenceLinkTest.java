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

import org.polarsys.capella.test.diagram.common.ju.context.XFCDDiagram;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.utils.FCDSequencingTest;
import org.polarsys.capella.test.diagram.tools.ju.xfcd.utils.Settings;

public class CreateExchangeWithSequenceLinkTest extends FCDSequencingTest {

  public CreateExchangeWithSequenceLinkTest(Settings settings) {
    super(settings);
  }

  @Override
  protected void testLevel0() {
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL0);

    testExchangeWithSequenceLinkLevel0();

    xfcd.close();
  }

  @Override
  protected void testLevel1() {
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL1);

    testExchangeWithSequenceLinkLevel1();

    xfcd.close();
  }

  @Override
  protected void testLevel2() {
    initTest();
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL2);

    testExchangeWithSequenceLinkLevel2();

    xfcd.close();
  }

  protected void testExchangeWithSequenceLinkLevel0() {
    // exchanges between functions in diagram
    xfcd.createExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION1_LEVEL0, settings.SEQ_FCIFUNCTION2_LEVEL0,
        settings.FUNCTIONAL_EXCHANGE_1_2);
    xfcd.createExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION2_LEVEL0, settings.SEQ_FCIFUNCTION3_LEVEL0,
        settings.FUNCTIONAL_EXCHANGE_2_3);
    xfcd.createExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION1_LEVEL0, settings.SEQ_FCIFUNCTION3_LEVEL0,
        settings.FUNCTIONAL_EXCHANGE_1_3);

    // cannot create exchange (FE does not exist between the selected functions)
    xfcd.cannotCreateExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION2_LEVEL0, settings.SEQ_FCIFUNCTION1_LEVEL0);

    // cannot create exchange, invalid source or target
    xfcd.invalidCreateExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION1_LEVEL0, settings.CONTROL_NODE1_LEVEL0);
  }

  protected void testExchangeWithSequenceLinkLevel1() {
    // exchanges between functions in diagram
    xfcd.createExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION3_LEVEL1, settings.SEQ_FCIFUNCTION4_LEVEL1,
        settings.FUNCTIONAL_EXCHANGE_3_4);
    xfcd.createExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL1, settings.SEQ_FCIFUNCTION5_LEVEL1,
        settings.FUNCTIONAL_EXCHANGE_4_5);

    // exchange between one function from diagram and one from FCR
    xfcd.createExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION2_LEVEL0, settings.SEQ_FCIFUNCTION3_LEVEL1,
        settings.FUNCTIONAL_EXCHANGE_2_3);

    // cannot create exchange (FE does not exist between the selected functions)
    xfcd.cannotCreateExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL1, settings.SEQ_FCIFUNCTION3_LEVEL1);
    xfcd.cannotCreateExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION3_LEVEL1, settings.SEQ_FCIFUNCTION2_LEVEL0);
    xfcd.cannotCreateExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION3_LEVEL1, settings.SEQ_FCIFUNCTION5_LEVEL1);

    // cannot create exchange, invalid source or target
    xfcd.invalidCreateExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL1, settings.CONTROL_NODE1_LEVEL1);

    // create from this diagram at level 1, SL between elements from FCR
    testExchangeWithSequenceLinkLevel0();
  }

  protected void testExchangeWithSequenceLinkLevel2() {
    // exchanges between functions in diagram
    xfcd.createExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL2, settings.SEQ_FCIFUNCTION5_LEVEL2,
        settings.FUNCTIONAL_EXCHANGE_4_5);

    // exchange between one function from diagram and one from FCR
    xfcd.createExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION1_LEVEL2, settings.SEQ_FCIFUNCTION3_LEVEL1,
        settings.FUNCTIONAL_EXCHANGE_1_3);

    // exchange between one function from FCR level 0 and one from FCR level 1
    xfcd.createExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION2_LEVEL0, settings.SEQ_FCIFUNCTION3_LEVEL1,
        settings.FUNCTIONAL_EXCHANGE_2_3);

    // cannot create exchange (FE does not exist between the selected functions)
    xfcd.cannotCreateExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL2, settings.SEQ_FCIFUNCTION6_LEVEL2);
    xfcd.createExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL2, settings.SEQ_FCIFUNCTION5_LEVEL2,
        settings.FUNCTIONAL_EXCHANGE_3_4);
    xfcd.cannotCreateExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION1_LEVEL2, settings.SEQ_FCIFUNCTION5_LEVEL1);

    // cannot create exchange, invalid source or target
    xfcd.invalidCreateExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL2, settings.CONTROL_NODE1_LEVEL2);
    xfcd.invalidCreateExchangeWithSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL2, settings.SEQUENCEFC2);

    // create from this diagram at level 2, SL between elements from FCR
    testExchangeWithSequenceLinkLevel0();
    testExchangeWithSequenceLinkLevel1();
  }
}
