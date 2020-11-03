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
import org.polarsys.capella.test.diagram.common.ju.context.OA_ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateSequenceMessageInteraction extends CreateSequenceMessage {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] typesES = { BlockArchitectureExt.Type.OA };
    testOnAllLevels(typesES, SequenceType.ES);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    setUpDiagram(diagram);

    OA_ESDiagram oasDiagram = (OA_ESDiagram) diagram;
    oasDiagram.createMessage(GenericModel.INTERACTION_1, actor1, actor2);
    oasDiagram.createMessage(GenericModel.INTERACTION_2, actor2, actor1);
  }
}
