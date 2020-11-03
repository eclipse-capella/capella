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
import org.polarsys.capella.test.diagram.common.ju.context.FSDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateFunctionalExchange extends SequenceTest {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] types = { BlockArchitectureExt.Type.OA, BlockArchitectureExt.Type.SA,
        BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };

    testOnAllLevels(types, SequenceType.ES);
    testOnAllLevels(types, SequenceType.FS);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    setUpDiagram(diagram);

    testCreateFunctionalExchange(diagram);
    testCreateFunctionalExchangeWithReturnBranch(diagram);
  }

  @Override
  public void setUpDiagram(SequenceDiagram diagram) {
    super.setUpDiagram(diagram);

    BlockArchitectureExt.Type type = diagram.getDiagramBlockArchitecture();
    SkeletonHelper.createFunctionalExchange(getRootFunctionId(type), GenericModel.FUNCTIONAL_EXCHANGE_1,
        diagram.getSessionContext());
    SkeletonHelper.createFunctionalExchange(getRootFunctionId(type), GenericModel.FUNCTIONAL_EXCHANGE_4,
        diagram.getSessionContext());
    if (type != BlockArchitectureExt.Type.SA) {
      SkeletonHelper.createFunctionalExchange(getRootFunctionId(type), GenericModel.FUNCTIONAL_EXCHANGE_2,
          diagram.getSessionContext());
      SkeletonHelper.createFunctionalExchange(getRootFunctionId(type), GenericModel.FUNCTIONAL_EXCHANGE_3,
          diagram.getSessionContext());
      SkeletonHelper.createFunctionalExchange(getRootFunctionId(type), GenericModel.FUNCTIONAL_EXCHANGE_5,
          diagram.getSessionContext());
      SkeletonHelper.createFunctionalExchange(getRootFunctionId(type), GenericModel.FUNCTIONAL_EXCHANGE_6,
          diagram.getSessionContext());
    }
  }

  protected void testCreateFunctionalExchange(SequenceDiagram diagram) {
    BlockArchitectureExt.Type type = diagram.getDiagramBlockArchitecture();
    if (diagram instanceof FSDiagram) {
      diagram.createFunctionalExchange(GenericModel.FUNCTIONAL_EXCHANGE_1, function1, function2);
    } else {
      diagram.createFunctionalExchange(GenericModel.FUNCTIONAL_EXCHANGE_1, actor1, actor2);
      if (type != BlockArchitectureExt.Type.SA) {
        diagram.createFunctionalExchange(GenericModel.FUNCTIONAL_EXCHANGE_2, actor1, component1);
        diagram.createFunctionalExchange(GenericModel.FUNCTIONAL_EXCHANGE_3, component1, component2);
      }
    }
  }

  protected void testCreateFunctionalExchangeWithReturnBranch(SequenceDiagram diagram) {
    BlockArchitectureExt.Type type = diagram.getDiagramBlockArchitecture();

    if (diagram instanceof FSDiagram) {
      diagram.createFunctionalExchange(GenericModel.FUNCTIONAL_EXCHANGE_4, function1, function2);
    } else {
      diagram.createFunctionalExchangeWithReturnBranch(GenericModel.FUNCTIONAL_EXCHANGE_4, actor1, actor2);
      if (type != BlockArchitectureExt.Type.SA) {
        diagram.createFunctionalExchangeWithReturnBranch(GenericModel.FUNCTIONAL_EXCHANGE_5, actor1, component1);
        diagram.createFunctionalExchangeWithReturnBranch(GenericModel.FUNCTIONAL_EXCHANGE_6, component1, component2);
      }
    }
  }
}
