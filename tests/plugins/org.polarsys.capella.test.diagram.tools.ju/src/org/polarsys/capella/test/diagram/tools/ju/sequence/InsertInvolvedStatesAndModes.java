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

import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.StateMachineExt;
import org.polarsys.capella.core.model.preferences.IModeAndStateManagementPreferences;
import org.polarsys.capella.test.diagram.common.ju.context.ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.FSDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.MSMDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;
import org.polarsys.capella.test.framework.model.GenericModel;

public class InsertInvolvedStatesAndModes extends SequenceTest {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] types = { BlockArchitectureExt.Type.OA, BlockArchitectureExt.Type.SA,
        BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };
    BlockArchitectureExt.Type[] typesIS = { BlockArchitectureExt.Type.SA, BlockArchitectureExt.Type.LA,
        BlockArchitectureExt.Type.PA };

    testOnAllLevels(types, SequenceType.ES);
    testOnAllLevels(typesIS, SequenceType.IS);
    testOnAllLevels(types, SequenceType.FS);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    setUpDiagram(diagram);

    if (diagram instanceof FSDiagram) {
      testInsertInvolvedStatesAndModes(diagram, function1);
    } else {
      testInsertInvolvedStatesAndModes(diagram, actor1);
    }
  }

  protected void testInsertInvolvedStatesAndModes(SequenceDiagram diagram, String containerId) {
    diagram.insertState(containerId, GenericModel.STATE_1);
    diagram.insertState(containerId, GenericModel.STATE_2);
  }

  @Override
  public void setUpDiagram(SequenceDiagram diagram) {
    if (diagram instanceof FSDiagram) {
      function1 = ((FSDiagram) diagram).createFunction();
    } else {
      actor1 = diagram.createActor();
    }
    
    // create a state machine + region under actors
    addStateMachineRegion(actor1, "StateMachine1");
    AbstractType type = ScenarioExt.getAbstractType(context.getSemanticElement(actor1));
    Region region = StateMachineExt.getFirstStateMachinesRegion((Component) type);

    // create a MSM diagram under the recently created region and add a state
    MSMDiagram msm = MSMDiagram.createDiagram(context, region.getId());
    MSMDiagram.setUnsynchronized(msm);
    context.setPreference(IModeAndStateManagementPreferences.PREFS_MIXED_MODE_STATE_ALLOWED, false);
    msm.createState(msm.getDiagramId(), GenericModel.STATE_1);
    msm.createState(msm.getDiagramId(), GenericModel.STATE_2);
  }
  
  private void addStateMachineRegion(String elementId, String smName) {
    AbstractType type = ScenarioExt.getAbstractType(context.getSemanticElement(elementId));
    if(type instanceof Block) {
      addStateMachineRegion((Block) type, smName);
    }
  }
  
  private void addStateMachineRegion(Block block, String smName) {
    final AbstractCommand cmd = new AbstractReadWriteCommand() {
      public void run() {
        StateMachine sm = CapellacommonFactory.eINSTANCE.createStateMachine(smName);
        block.getOwnedStateMachines().add(sm);

        Region defaultRegion = null;
        for (Region region : sm.getOwnedRegions()) {
          defaultRegion = region;
        }
        if (defaultRegion == null) {
          defaultRegion = CapellacommonFactory.eINSTANCE.createRegion("Default Region");
          sm.getOwnedRegions().add(defaultRegion);
        }
      }
    };
    context.getExecutionManager().execute(cmd);
  }
}
