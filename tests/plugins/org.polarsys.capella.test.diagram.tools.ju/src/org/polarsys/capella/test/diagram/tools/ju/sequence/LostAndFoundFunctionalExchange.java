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
import org.polarsys.capella.test.diagram.common.ju.context.ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;
import org.polarsys.capella.test.framework.model.GenericModel;

public class LostAndFoundFunctionalExchange extends CreateFunctionalExchange {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] types = { BlockArchitectureExt.Type.LA };

    testOnAllLevels(types, SequenceType.ES);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    setUpDiagram(diagram);
    
    ((ESDiagram)diagram).createLostFunctionalExchange(GenericModel.FUNCTIONAL_EXCHANGE_1, actor1);
    ((ESDiagram)diagram).createLostFunctionalExchange(GenericModel.FUNCTIONAL_EXCHANGE_1, actor2);
  }

  @Override
  public void setUpDiagram(SequenceDiagram diagram) {
    super.setUpDiagram(diagram);
  } 
}
