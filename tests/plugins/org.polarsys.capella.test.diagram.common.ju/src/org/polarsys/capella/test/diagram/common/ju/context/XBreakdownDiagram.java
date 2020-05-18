/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.context;

import static org.junit.Assert.assertTrue;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.junit.Assert;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.epbs.ConfigurationItemKind;
import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateNodeTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XBreakdownDiagram extends CommonDiagram {

  BlockArchitectureExt.Type type = null;

  public XBreakdownDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(context, diagram);
    this.type = type;
  }

  public static XBreakdownDiagram createCBDiagram(SessionContext executionContext, String targetIdentifier) {
    BlockArchitecture architecture = BlockArchitectureExt
        .getRootBlockArchitecture(executionContext.getSemanticElement(targetIdentifier));
    final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);

    String name = null;
    if (type == Type.OA) {
      name = IDiagramNameConstants.OPERATIONAL_ENTITY_BREAKDOWN_DIAGRAM_NAME;
    } else if (type == Type.LA) {
      name = IDiagramNameConstants.LOGICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME;
    } else if (type == Type.PA) {
      name = IDiagramNameConstants.PHYSICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME;
    } else if (type == Type.EPBS) {
      name = IDiagramNameConstants.CONFIGURATION_ITEMS_BREAKDOWN_DIAGRAM_NAME;
    }

    return (XBreakdownDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new XBreakdownDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static XBreakdownDiagram createFBDiagram(SessionContext executionContext, String targetIdentifier) {
    BlockArchitecture architecture = BlockArchitectureExt
        .getRootBlockArchitecture(executionContext.getSemanticElement(targetIdentifier));
    final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);

    String name = null;
    if (type == Type.OA) {
      name = IDiagramNameConstants.OPERATIONAL_ACTIVITY_BREAKDOWN_DIAGRAM_NAME;
    } else if (type == Type.SA) {
      name = IDiagramNameConstants.SYSTEM_FUNCTION_BREAKDOWN_DIAGRAM_NAME;
    } else if (type == Type.LA) {
      name = IDiagramNameConstants.LOGICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME;
    } else if (type == Type.PA) {
      name = IDiagramNameConstants.PHYSICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME;
    }

    return (XBreakdownDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new XBreakdownDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static XBreakdownDiagram openDiagram(SessionContext executionContext, String name,
      final BlockArchitectureExt.Type type) {
    return (XBreakdownDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new XBreakdownDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public void createFunction(String id, final int expectedNewElements, String kind, String containerId,
      final String realContainerId) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OEB_CREATE_OPERATIONAL_ACTIVITY;
    } else if (type == Type.SA) {
      if (FunctionKind.FUNCTION.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_SFBD_CREATE_SYSTEM_FUNCTION;
      } else if (FunctionKind.DUPLICATE.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_SFBD_CREATE_DUPLICATE;
      } else if (FunctionKind.GATHER.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_SFBD_CREATE_GATHER;
      } else if (FunctionKind.ROUTE.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_SFBD_CREATE_ROUTE;
      } else if (FunctionKind.SELECT.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_SFBD_CREATE_SELECT;
      } else if (FunctionKind.SPLIT.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_SFBD_CREATE_SPLIT;
      }
    } else if (type == Type.LA) {
      if (FunctionKind.FUNCTION.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_LFBD_CREATE_LOGICAL_FUNCTION;
      } else if (FunctionKind.DUPLICATE.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_LFBD_CREATE_DUPLICATE;
      } else if (FunctionKind.GATHER.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_LFBD_CREATE_GATHER;
      } else if (FunctionKind.ROUTE.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_LFBD_CREATE_ROUTE;
      } else if (FunctionKind.SELECT.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_LFBD_CREATE_SELECT;
      } else if (FunctionKind.SPLIT.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_LFBD_CREATE_SPLIT;
      }
    } else if (type == Type.PA) {
      if (FunctionKind.FUNCTION.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_PFBD_CREATE_PHYSICAL_FUNCTION;
      } else if (FunctionKind.DUPLICATE.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_PFBD_CREATE_DUPLICATE;
      } else if (FunctionKind.GATHER.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_PFBD_CREATE_GATHER;
      } else if (FunctionKind.ROUTE.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_PFBD_CREATE_ROUTE;
      } else if (FunctionKind.SELECT.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_PFBD_CREATE_SELECT;
      } else if (FunctionKind.SPLIT.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_PFBD_CREATE_SPLIT;
      }
    }
    new CreateNodeTool(this, name, containerId, id) {
      @Override
      protected DSemanticDecorator getContainerView() {
        return getDiagramContext().getView(
            (realContainerId != null && !ICommonConstants.EMPTY_STRING.equals(realContainerId)) ? realContainerId
                : containerView);
      }

      @Override
      protected int expectedNewElements() {
        return expectedNewElements;
      }
    }.run();
  }

  public void createComponent(String id, int expectedNewElements, String containerId) {
    createComponent(id, expectedNewElements, ICommonConstants.EMPTY_STRING, containerId, containerId);
  }

  public void createComponent(String id, final int expectedNewElements, String kind, String containerId,
      final String realContainerId) {
    String name = null;
    if (type == Type.OA) {
      if (OaPackage.Literals.ENTITY.getName().equals(kind)) {
        name = IToolNameConstants.TOOL_OEB_CREATE_OA;
      } else {
        name = IToolNameConstants.TOOL_OEB_CREATE_OE;
      }
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LCBD_CREATE_LOGICAL_COMPONENT;
    } else if (type == Type.PA) {
      if (PhysicalComponentNature.BEHAVIOR.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_PCBD_CREATE_BEHAVIOR_PC;
      } else if (PhysicalComponentNature.NODE.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_PCBD_CREATE_NODE_PC;
      }
    } else if (type == Type.EPBS) {
      if (ConfigurationItemKind.COTSCI.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_CIBD_CREATE_COTS;
      } else if (ConfigurationItemKind.CSCI.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_CIBD_CREATE_CS;
      } else if (ConfigurationItemKind.HWCI.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_CIBD_CREATE_HW;
      } else if (ConfigurationItemKind.INTERFACE_CI.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_CIBD_CREATE_INTERFACE;
      } else if (ConfigurationItemKind.NDICI.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_CIBD_CREATE_NDI;
      } else if (ConfigurationItemKind.PRIME_ITEM_CI.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_CIBD_CREATE_PRIME_ITEM;
      } else if (ConfigurationItemKind.SYSTEM_CI.getLiteral().equals(kind)) {
        name = IToolNameConstants.TOOL_CIBD_CREATE_SYSTEM;
      }
    }
    new CreateNodeTool(this, name, containerId, id) {
      @Override
      protected DSemanticDecorator getContainerView() {
        return getDiagramContext().getView(
            (realContainerId != null && !ICommonConstants.EMPTY_STRING.equals(realContainerId)) ? realContainerId
                : containerView);
      }

      @Override
      protected int expectedNewElements() {
        return expectedNewElements;
      }
    }.run();
  }

  public void createActor(String id, String containerId, boolean isHuman) {
    String name = null;
    if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LCBD_CREATE_LOGICAL_ACTOR;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PCBD_CREATE_PHYSICAL_ACTOR;
    }
    CreateNodeTool tool = new CreateNodeTool(this, name, containerId, id);
    EObject result = tool.run().getTarget();
    if ((type == type.LA && result instanceof LogicalComponent)
        || (type == type.PA && result instanceof PhysicalComponent)) {
      Component actor = (Component) result;
      assertTrue("the component created is not actor", actor.isActor());

      TransactionHelper.getExecutionManager(actor).execute(new AbstractReadWriteCommand() {

        @Override
        public void run() {
          actor.setHuman(isHuman);

        }

      });
    } else {
      Assert.fail(" The result is not the expected one ");
    }

  }

  public void createCContainedIn(String sourceId, String targetId) {
    createCContainedIn(sourceId, targetId, null);
  }

  public void createCContainedIn(String sourceId, String targetId, String id) {
    String name = null;
    if (isA(IDiagramNameConstants.OPERATIONAL_ENTITY_BREAKDOWN_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_OEBD_CONTAINED_IN;
    } else if (isA(IDiagramNameConstants.LOGICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_LCBD_CONTAINED_IN;
    } else if (isA(IDiagramNameConstants.PHYSICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_PCBD_CONTAINED_IN;
    } else if (isA(IDiagramNameConstants.CONFIGURATION_ITEMS_BREAKDOWN_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_CIBD_CONTAINED_IN;
    }
    new CreateDEdgeTool(this, name, sourceId, targetId, id).run();
  }

  public void createFContainedIn(String sourceId, String targetId) {
    createFContainedIn(sourceId, targetId, null);
  }

  public void createFContainedIn(String sourceId, String targetId, String id) {
    String name = null;
    if (isA(IDiagramNameConstants.OPERATIONAL_ACTIVITY_BREAKDOWN_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_OABD_CONTAINED_IN;
    } else if (isA(IDiagramNameConstants.SYSTEM_FUNCTION_BREAKDOWN_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_SFBD_CONTAINED_IN;
    } else if (isA(IDiagramNameConstants.LOGICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_LFBD_CONTAINED_IN;
    } else if (isA(IDiagramNameConstants.PHYSICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_PFBD_CONTAINED_IN;
    }
    new CreateDEdgeTool(this, name, sourceId, targetId, id).run();
  }

  public void createPart(String sourceId, String targetId) {
    createPart(sourceId, targetId, getDiagramId());
  }

  public void createPart(String sourceId, String targetId, String id) {
    String name = null;
    if (isA(IDiagramNameConstants.LOGICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_LCBD_CREATE_PART;
    } else if (isA(IDiagramNameConstants.PHYSICAL_COMPONENT_BREAKDOWN_DIAGRAM_NAME)) {
      name = IToolNameConstants.TOOL_PCBD_CREATE_PART;
    }
    new CreateDEdgeTool(this, name, sourceId, targetId, id).run();
  }
}
