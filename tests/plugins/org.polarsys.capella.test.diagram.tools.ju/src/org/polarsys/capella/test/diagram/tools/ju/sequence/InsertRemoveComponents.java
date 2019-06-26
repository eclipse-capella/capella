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

import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.PA_ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;

public class InsertRemoveComponents extends SequenceTest {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type [] types = { BlockArchitectureExt.Type.OA, BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };
    
    testOnAllLevels(types, SequenceType.ES);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    ESDiagram esDiagram = ((ESDiagram) diagram);
    if (diagram instanceof PA_ESDiagram) {
      component1 = ((PA_ESDiagram) diagram).createComponent(PhysicalComponentNature.BEHAVIOR);
      component2 = ((PA_ESDiagram) diagram).createComponent(PhysicalComponentNature.NODE);
    } else {
      component1 = esDiagram.createComponent();
    }
    
    esDiagram.removeComponent(component1);
    esDiagram.insertComponent(component1);
    
    if (diagram instanceof PA_ESDiagram) {
      esDiagram.removeComponent(component2);
      esDiagram.insertComponent(component2);
    }
  }
}
