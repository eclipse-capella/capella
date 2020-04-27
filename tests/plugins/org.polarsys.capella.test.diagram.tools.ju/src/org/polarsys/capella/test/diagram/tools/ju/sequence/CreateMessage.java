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
import org.polarsys.capella.test.diagram.common.ju.context.ISDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.OA_ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateMessage extends CreateSequenceMessage {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] typesES = { BlockArchitectureExt.Type.OA };
    BlockArchitectureExt.Type[] typesIS = { BlockArchitectureExt.Type.SA, BlockArchitectureExt.Type.LA,
        BlockArchitectureExt.Type.PA };

    testOnAllLevels(typesES, SequenceType.ES);
    testOnAllLevels(typesIS, SequenceType.IS);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    setUpDiagram(diagram);
    if (diagram instanceof ISDiagram) {
      ISDiagram isDiagram = (ISDiagram) diagram;
      isDiagram.createMessage(GenericModel.EXCHANGE_ITEM_ALLOCATION_1, actor1, actor2);
      isDiagram.createMessage(GenericModel.EXCHANGE_ITEM_ALLOCATION_2, actor2, actor1);
    } else if (diagram instanceof OA_ESDiagram) {
      OA_ESDiagram oasDiagram = (OA_ESDiagram) diagram;
      oasDiagram.createMessage(GenericModel.COMMUNICATION_MEAN_1, actor1, actor2);
      oasDiagram.createMessage(GenericModel.COMMUNICATION_MEAN_2, actor2, actor1);
    }
  }
}
