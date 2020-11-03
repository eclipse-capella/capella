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

import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateComponentExchange extends SequenceTest {
  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] types = { BlockArchitectureExt.Type.OA, BlockArchitectureExt.Type.LA,
        BlockArchitectureExt.Type.PA };

    testOnAllLevels(types, SequenceType.ES);
  }

  @Override
  public void test(SequenceDiagram diagram) {
      setUpDiagram(diagram);
      testCreateComponentExchange((ESDiagram)diagram);
      testCreateComponentExchangeWithReturnBranch((ESDiagram)diagram);
  }

  public void setUpDiagram(SequenceDiagram diagram) {
    super.setUpDiagram(diagram);

    BlockArchitectureExt.Type type = diagram.getDiagramBlockArchitecture();
    SkeletonHelper.createComponentExchange(getContextId(type), GenericModel.COMPONENT_EXCHANGE_1, context);
    SkeletonHelper.createComponentExchange(getContextId(type), GenericModel.COMPONENT_EXCHANGE_2, context);
    SkeletonHelper.createComponentExchange(getContextId(type), GenericModel.COMPONENT_EXCHANGE_3, context);
    SkeletonHelper.createComponentExchange(getContextId(type), GenericModel.COMPONENT_EXCHANGE_4, context);
    SkeletonHelper.createComponentExchange(getContextId(type), GenericModel.COMPONENT_EXCHANGE_5, context);
    SkeletonHelper.createComponentExchange(getContextId(type), GenericModel.COMPONENT_EXCHANGE_6, context);
  }

  protected void testCreateComponentExchange(ESDiagram diagram) {
    diagram.createComponentExchange(GenericModel.COMPONENT_EXCHANGE_1, actor1, actor2);
    diagram.createComponentExchange(GenericModel.COMPONENT_EXCHANGE_2, actor1, component1);
    diagram.createComponentExchange(GenericModel.COMPONENT_EXCHANGE_3, component1, component2);
  }

  protected void testCreateComponentExchangeWithReturnBranch(ESDiagram diagram) {
    diagram.createComponentExchangeWithReturnBranch(GenericModel.COMPONENT_EXCHANGE_4, actor1, actor2);
    diagram.createComponentExchangeWithReturnBranch(GenericModel.COMPONENT_EXCHANGE_5, actor1, component1);
    diagram.createComponentExchangeWithReturnBranch(GenericModel.COMPONENT_EXCHANGE_6, component1, component2);
  }
}
