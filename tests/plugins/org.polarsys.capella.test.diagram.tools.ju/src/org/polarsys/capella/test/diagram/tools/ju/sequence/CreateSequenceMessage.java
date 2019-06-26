/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.sequence;

import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.ISDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateSequenceMessage extends SequenceTest {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] types = { BlockArchitectureExt.Type.SA, BlockArchitectureExt.Type.LA,
        BlockArchitectureExt.Type.PA };

    testOnAllLevels(types, SequenceType.IS);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    if (diagram instanceof ISDiagram) {
      setUpDiagram(diagram);
      ISDiagram isDiagram = (ISDiagram) diagram;
      isDiagram.createSequenceMessage(GenericModel.EXCHANGE_ITEM_ALLOCATION_1, actor1, actor2);
      isDiagram.createSequenceMessageWithReturnBranch(GenericModel.EXCHANGE_ITEM_ALLOCATION_2, actor1, actor2);
    }
  }

  @Override
  public void setUpDiagram(SequenceDiagram diagram) {
    actor1 = diagram.createActor();
    actor2 = diagram.createActor();
    if (diagram instanceof ISDiagram) {
      SkeletonHelper.createInterface(getInterfacesId(diagram.getDiagramBlockArchitecture()), GenericModel.INTERFACE_1,
          context);
      SkeletonHelper.createExchangeItem(getInterfacesId(diagram.getDiagramBlockArchitecture()),
          GenericModel.EXCHANGE_ITEM_1, context);
      SkeletonHelper.createExchangeItemAllocation(GenericModel.EXCHANGE_ITEM_ALLOCATION_1, GenericModel.INTERFACE_1,
          GenericModel.EXCHANGE_ITEM_1, context);
      SkeletonHelper.createExchangeItemAllocation(GenericModel.EXCHANGE_ITEM_ALLOCATION_2, GenericModel.INTERFACE_1,
          GenericModel.EXCHANGE_ITEM_1, context);
    }
  }
}
