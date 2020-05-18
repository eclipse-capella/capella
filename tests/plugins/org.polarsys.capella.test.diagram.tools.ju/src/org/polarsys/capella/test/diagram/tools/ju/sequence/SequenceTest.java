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

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.CapabilityDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.FSDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.ISDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.OA_ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.PA_ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SA_ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.SequenceDiagramProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;
import org.polarsys.capella.test.framework.model.GenericModel;

public abstract class SequenceTest extends SequenceDiagramProject {
  protected SessionContext context = null;

  protected String scenario = GenericModel.SCENARIO_1;
  protected String capability = GenericModel.CAPABILITY_1;
  protected String actor1 = "";
  protected String actor2 = "";
  protected String component1 = "";
  protected String component2 = "";
  protected String function1 = "";
  protected String function2 = "";
  
  public enum SequenceType {ES, FS, IS}
  
  public void testOnAllLevels(BlockArchitectureExt.Type[] types, SequenceType seqType) {
    for (BlockArchitectureExt.Type type : types) {
      doTest(type, seqType, getCapabilitiesId(type),
          capability, scenario);
    }
  }

  public abstract void test(SequenceDiagram diagram);
  
  protected void setUpDiagram(SequenceDiagram diagram) {
    if(diagram instanceof FSDiagram) {
      function1 = ((FSDiagram) diagram).createFunction();
      function2 = ((FSDiagram) diagram).createFunction();
    }
    else {
      actor1 = diagram.createActor();
      actor2 = diagram.createActor();
      BlockArchitectureExt.Type type = diagram.getDiagramBlockArchitecture(); 
      if(diagram instanceof ESDiagram) {
        switch(type) {
        case SA:
          break;
        case PA:
          component1 = ((PA_ESDiagram) diagram).createComponent(PhysicalComponentNature.BEHAVIOR);
          component2 = ((PA_ESDiagram) diagram).createComponent(PhysicalComponentNature.NODE);
          break;
        default:
          component1 = ((ESDiagram) diagram).createComponent();
          component2 = ((ESDiagram) diagram).createComponent();
          break;
        }
      }
    }
  }
  
  protected SequenceDiagram createDiagram(SessionContext context, BlockArchitectureExt.Type type, SequenceType seqType, String containerId,
      String capabilityId, String scenarioId) {
    SequenceDiagram diagram = null;
    switch(seqType) {
    case ES:
      diagram = ESDiagram.createDiagram(context, scenarioId);
      break;
    case FS:
      diagram = FSDiagram.createDiagram(context, scenarioId);
      break;
    case IS:
      diagram = ISDiagram.createDiagram(context, scenarioId);
      break;
    default:
      break;
    }
    return diagram;
  }
  
  protected void doTest(BlockArchitectureExt.Type type, SequenceType seqType, String containerId, String capabilityId,
      String scenarioId) {
    Session session = getSession(getRequiredTestModel());
    context = new SessionContext(session);
    SequenceDiagram diagram = openDiagram(type, seqType);
    if (diagram != null) {
      test(diagram);
      diagram.close();
    }
  }

  protected SequenceDiagram openDiagram(BlockArchitectureExt.Type type, SequenceType seqType) {
    SequenceDiagram diagram = null;
    switch (type) {
    case OA:
      switch (seqType) {
      case ES:
        diagram = OA_ESDiagram.openDiagram(context, OA_ES, type);
        break;
      default:
        break;
      }
      break;
    case SA:
      switch (seqType) {
      case ES:
        diagram = SA_ESDiagram.openDiagram(context, SA_ES, type);
        break;
      case IS:
        diagram = ISDiagram.openDiagram(context, SA_IS, type);
        break;
      case FS:
        diagram = FSDiagram.openDiagram(context, SA_FS, type);
        break;
      default:
        break;
      }
      break;
    case LA:
      switch (seqType) {
      case ES:
        diagram = ESDiagram.openDiagram(context, LA_ES, type);
        break;
      case IS:
        diagram = ISDiagram.openDiagram(context, LA_IS, type);
        break;
      case FS:
        diagram = FSDiagram.openDiagram(context, LA_FS, type);
        break;
      default:
        break;
      }
      break;
    case PA:
      switch (seqType) {
      case ES:
        diagram = PA_ESDiagram.openDiagram(context, PA_ES, type);
        break;
      case IS:
        diagram = ISDiagram.openDiagram(context, PA_IS, type);
        break;
      case FS:
        diagram = FSDiagram.openDiagram(context, PA_FS, type);
        break;
      default:
        break;
      }
      break;
    default:
      break;
    }
    return diagram;
  }

  // helper
  protected String createCapability(SessionContext context, BlockArchitectureExt.Type type, String containerId, String capabilityId) {
    CapabilityDiagram capabilityDiagram = CapabilityDiagram.createDiagram(context, containerId,
        getCapabilityDiagramName(type));
    capabilityDiagram.createCapability(capabilityId);
    return capabilityId;
  }
  
  // helper
  protected String createScenario(SessionContext context, String scenarioId, String capabilityId) {
    SkeletonHelper.createScenario(capabilityId, scenarioId, context);
    return scenarioId;
  }
  
  // helper
  protected String getCapabilityDiagramName(BlockArchitectureExt.Type type) {
    String capabilityDiagramName = "";
    switch (type) {
    case OA:
      capabilityDiagramName = IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME;
      break;
    case SA:
      capabilityDiagramName = IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME;
      break;
    default:
      capabilityDiagramName = IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK;
      break;
    }
    return capabilityDiagramName;
  }
}
