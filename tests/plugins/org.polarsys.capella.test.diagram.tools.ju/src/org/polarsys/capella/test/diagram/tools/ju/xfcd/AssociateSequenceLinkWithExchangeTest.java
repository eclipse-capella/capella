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

public class AssociateSequenceLinkWithExchangeTest extends FCDSequencingTest {

  public AssociateSequenceLinkWithExchangeTest(Settings settings) {
    super(settings);
  }

  @Override
  protected void testLevel0() {
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL0);

    testAssociateSequenceLinkWithExchangeLevel0();

    xfcd.close();
  }

  @Override
  protected void testLevel1() {
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL1);

    testAssociateSequenceLinkWithExchangeLevel1();

    xfcd.close();
  }

  @Override
  protected void testLevel2() {
    initTest();
    xfcd = XFCDDiagram.getDiagram(context, settings.SEQ_FUNC_CHAIN_DIAGRAM_LEVEL2);

    testAssociateSequenceLinkWithExchangeLevel2();

    xfcd.close();
  }

  protected void testAssociateSequenceLinkWithExchangeLevel0() {
    // create association from SL towards FE
    String exchange = xfcd.involveExchange(settings.SEQ_FCIFUNCTION1_LEVEL0, settings.SEQ_FCIFUNCTION2_LEVEL0,
        settings.FUNCTIONAL_EXCHANGE_1_2);
    xfcd.associateSequenceLinkWithExchange(settings.SEQUENCE_LINK_1_LEVEL0, exchange, settings.SEQUENCE_LINK_1_LEVEL0);

    // create association from FE towards SL (CN involved)
    exchange = xfcd.involveExchange(settings.SEQ_FCIFUNCTION2_LEVEL0, settings.SEQ_FCIFUNCTION3_LEVEL0,
        settings.FUNCTIONAL_EXCHANGE_2_3);
    xfcd.associateSequenceLinkWithExchange(exchange, settings.SEQUENCE_LINK_1_1_LEVEL0,
        settings.SEQUENCE_LINK_1_1_LEVEL0);
    xfcd.associateSequenceLinkWithExchange(exchange, settings.SEQUENCE_LINK_1_2_LEVEL0,
        settings.SEQUENCE_LINK_1_2_LEVEL0);

    // cannot create association (opposite direction)
    String sequenceLink = xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL0, settings.SEQ_FCIFUNCTION3_LEVEL0);
    exchange = xfcd.involveExchange(settings.SEQ_FCIFUNCTION3_LEVEL0, settings.SEQ_FCIFUNCTION4_LEVEL0,
        settings.FUNCTIONAL_EXCHANGE_3_4);
    xfcd.cannotAssociateSequenceLinkWithExchange(sequenceLink, exchange);
    xfcd.cannotAssociateSequenceLinkWithExchange(exchange, sequenceLink);
  }

  protected void testAssociateSequenceLinkWithExchangeLevel1() {
    // create association having source a function from FCR
    xfcd.associateSequenceLinkWithExchange(settings.SEQUENCE_LINK_2_LEVEL1, settings.INVOLVEMENT_LINK_TO_FE_2_3_LEVEL1,
        settings.SEQUENCE_LINK_2_LEVEL1);

    String sequenceLink1 = xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL1, settings.CONTROL_NODE1_LEVEL1);
    String sequenceLink2 = xfcd.createSequenceLink(settings.CONTROL_NODE1_LEVEL1, settings.CONTROL_NODE2_LEVEL1);
    String sequenceLink3 = xfcd.createSequenceLink(settings.CONTROL_NODE2_LEVEL1, settings.SEQ_FCIFUNCTION5_LEVEL1);
    String exchange = xfcd.involveExchange(settings.SEQ_FCIFUNCTION4_LEVEL1, settings.SEQ_FCIFUNCTION5_LEVEL1,
        settings.FUNCTIONAL_EXCHANGE_4_5);
    xfcd.associateSequenceLinkWithExchange(exchange, sequenceLink1, sequenceLink1);
    xfcd.associateSequenceLinkWithExchange(exchange, sequenceLink2, sequenceLink2);
    xfcd.associateSequenceLinkWithExchange(sequenceLink3, exchange, sequenceLink3);

    // cannot create, opposite direction
    String sequenceLink = xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL0, settings.SEQ_FCIFUNCTION3_LEVEL1);
    exchange = xfcd.involveExchange(settings.SEQ_FCIFUNCTION3_LEVEL1, settings.SEQ_FCIFUNCTION4_LEVEL0,
        settings.FUNCTIONAL_EXCHANGE_3_4);
    xfcd.cannotAssociateSequenceLinkWithExchange(exchange, sequenceLink);

    // create associations inside FCR to FC1 (level 0)
    testAssociateSequenceLinkWithExchangeLevel0();
  }

  protected void testAssociateSequenceLinkWithExchangeLevel2() {
    // create association on computed links
    xfcd.associateSequenceLinkWithExchange(settings.SEQUENCE_LINK_6_LEVEL2, settings.INVOLVEMENT_LINK_TO_FE_2_1_LEVEL2,
        settings.SEQUENCE_LINK_6_LEVEL2);
    xfcd.associateSequenceLinkWithExchange(settings.SEQUENCE_LINK_7_LEVEL2, settings.INVOLVEMENT_LINK_TO_FE_1_3_LEVEL2,
        settings.SEQUENCE_LINK_7_LEVEL2);

    // create association having source a function from FCR FC2 level 1
    String exchange = xfcd.involveExchange(settings.SEQ_FCIFUNCTION1_LEVEL2, settings.SEQ_FCIFUNCTION3_LEVEL1,
        settings.FUNCTIONAL_EXCHANGE_1_3);
    xfcd.associateSequenceLinkWithExchange(settings.SEQUENCE_LINK_5_LEVEL2, exchange, settings.SEQUENCE_LINK_5_LEVEL2);

    // create association having source a function from FCR FC2 and one from FC1
    exchange = xfcd.involveExchange(settings.SEQ_FCIFUNCTION2_LEVEL0, settings.SEQ_FCIFUNCTION3_LEVEL1,
        settings.FUNCTIONAL_EXCHANGE_1_3);
    String sequenceLink = xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION2_LEVEL0, settings.SEQ_FCIFUNCTION3_LEVEL1);
    xfcd.associateSequenceLinkWithExchange(sequenceLink, exchange, sequenceLink);

    // cannot associate opposite direction
    exchange = xfcd.involveExchange(settings.SEQ_FCIFUNCTION3_LEVEL0, settings.SEQ_FCIFUNCTION4_LEVEL2,
        settings.FUNCTIONAL_EXCHANGE_3_4);
    sequenceLink = xfcd.createSequenceLink(settings.SEQ_FCIFUNCTION4_LEVEL2, settings.SEQ_FCIFUNCTION3_LEVEL0);
    xfcd.cannotAssociateSequenceLinkWithExchange(sequenceLink, exchange);
  }
}
