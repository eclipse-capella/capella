/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.helpers;

import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;

/**
 *
 */
public class ToolProviderHelper {

  /**
   * Retrieve the tool's name to create an actor
   * 
   * @param diagram_p
   * @return
   */
  public static String getToolCreateActor(DDiagram diagram_p) {
    if (DDiagramHelper.isSAB(diagram_p)) {
      return IToolNameConstants.TOOL_SAB_CREATE_ACTOR;
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_CREATE_LOGICAL_ACTOR;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_CREATE_PHYSICAL_ACTOR;
    } else if (DDiagramHelper.isOCB(diagram_p)) {
      return IToolNameConstants.TOOL_OCB_CREATE_OPERATIONAL_ACTOR;
    } else if (DDiagramHelper.isMCB(diagram_p)) {
      return IToolNameConstants.TOOL_MCB_CREATE_ACTOR;
    } else if (DDiagramHelper.isCC(diagram_p)) {
      return IToolNameConstants.TOOL_CC_CREATE_ACTOR;
    } else if (DDiagramHelper.isCM(diagram_p)) {
      return IToolNameConstants.TOOL_CM_CREATE_ACTOR;
    } else if (DDiagramHelper.isCRB(diagram_p)) {
      return IToolNameConstants.TOOL_CRB_CREATE_ACTOR_NAME;
    } else if (DDiagramHelper.isMB(diagram_p)) {
      return IToolNameConstants.TOOL_MB_CREATE_ACTOR;
    } else if (DDiagramHelper.isCSA(diagram_p)) {
      return IToolNameConstants.TOOL_CSA_CREATE_ACTOR;
    } else if (DDiagramHelper.isIDB(diagram_p)) {
      return IToolNameConstants.TOOL_IDB_CREATE_ACTOR;
    }
    return null;
  }

  /**
   * Retrieve the tool's name to create a component
   * 
   * @param diagram_p
   * @return
   */
  public static String getToolCreateComponent(DDiagram diagram_p) {
    if (DDiagramHelper.isOAB(diagram_p)) {
      return IToolNameConstants.TOOL_OEB_CREATE_OE;
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_CREATE_COMPONENT;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_CREATE_BEHAVIOR_PHYSICAL_COMPONENT;
    }
    return null;
  }

  public static String getTollCreateNodePC(DDiagram diagram_p) {
    if (DDiagramHelper.isSAB(diagram_p)) {
      return "TOOL_SAB_CREATE_NODE_PHYSICAL_COMPONENT DOES NOT EXIST YES";
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return "TOOL_LAB_CREATE_NODE_PHYSICAL_COMPONENT DOES NOT EXIST YES";
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_CREATE_NODE_PHYSICAL_COMPONENT;
    }
    return null;
  }

  public static String getToolCreatePhysicalLink(DDiagram diagram_p) {
    if (DDiagramHelper.isSAB(diagram_p)) {
      return IToolNameConstants.TOOL_SAB_CREATE_PHYSICAL_LINK;
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_CREATE_PHYSICAL_LINK;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_CREATE_PHYSICAL_LINK;
    }
    return null;
  }

  public static String getToolCreatePhysicalPort(DDiagram diagram_p) {
    if (DDiagramHelper.isSAB(diagram_p)) {
      return IToolNameConstants.TOOL_SAB_CREATE_PHYSICAL_PORT;
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_CREATE_PHYSICAL_PORT;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_CREATE_PHYSICAL_PORT;
    }
    return null;
  }

  public static String getToolReconnectPLSource(DDiagram diagram_p) {
    if (DDiagramHelper.isSAB(diagram_p)) {
      return IToolNameConstants.TOOL_SAB_RECONNECT_PHYSICALLINK_SOURCE_ID;
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_RECONNECT_PHYSICALLINK_SOURCE_ID;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_RECONNECT_PHYSICALLINK_SOURCE;
    }
    return null;
  }

  public static String getToolReconnectPLTarget(DDiagram diagram_p) {
    if (DDiagramHelper.isSAB(diagram_p)) {
      return IToolNameConstants.TOOL_SAB_RECONNECT_PHYSICALLINK_TARGET_ID;
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_SAB_RECONNECT_PHYSICALLINK_TARGET_ID;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_RECONNECT_PHYSICALLINK_TARGET;
    }
    return null;
  }

  /**
   * Retrieve the tool's name to create a component exchange
   * 
   * @param diagram_p
   * @return
   */
  public static String getToolCreateComponentExchange(DDiagram diagram_p) {
    if (DDiagramHelper.isOAB(diagram_p)) {
      return IToolNameConstants.TOOL_OEB_CREATE_COMMUNICATION_MEAN;
    } else if (DDiagramHelper.isSAB(diagram_p)) {
      return IToolNameConstants.TOOL_SAB_CREATE_COMPONENT_EXCHANGE;
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_CREATE_COMPONENT_EXCHANGE;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_CREATE_COMPONENT_EXCHANGE;
    }
    return null;
  }

  /**
   * Retrieve the tool's name to reconnect a component exchange source
   * 
   * @param diagram_p
   * @return
   */
  public static String getToolReconnectComponentExchangeSource(DDiagram diagram_p) {
    if (DDiagramHelper.isOAB(diagram_p)) {
      return IToolNameConstants.TOOL_OAB_RECONNECT_COMMUNICATION_MEAN_SOURCE;
    } else if (DDiagramHelper.isSAB(diagram_p)) {
      return IToolNameConstants.TOOL_SAB_RECONNECT_EXCHANGES_SOURCE;
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_RECONNECT_CONNECTION_SOURCE;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_RECONNECT_COMPONENTEXCHANGE_SOURCE;
    } else if (DDiagramHelper.isOAIB(diagram_p)) {
      return IToolNameConstants.TOOL_OAIB_RECONNECT_EXCHANGES;
    } else if (DDiagramHelper.isSDFB(diagram_p)) {
      return IToolNameConstants.TOOL_SDFB_RECONNECT_EXCHANGES;
    } else if (DDiagramHelper.isLDFB(diagram_p)) {
      return IToolNameConstants.TOOL_LDFB_RECONNECT_EXCHANGE;
    } else if (DDiagramHelper.isPDFB(diagram_p)) {
      return IToolNameConstants.TOOL_PDFB_RECONNECT_EXCHANGE;
    }
    return null;
  }

  /**
   * Retrieve the tool's name to reconnect a component exchange target
   * 
   * @param diagram_p
   * @return
   */
  public static String getToolReconnectComponentExchangeTarget(DDiagram diagram_p) {
    if (DDiagramHelper.isOAB(diagram_p)) {
      return IToolNameConstants.TOOL_OAB_RECONNECT_COMMUNICATION_MEAN_TARGET;
    } else if (DDiagramHelper.isSAB(diagram_p)) {
      return IToolNameConstants.TOOL_SAB_RECONNECT_EXCHANGES_TARGET;
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_RECONNECT_CONNECTION_TARGET;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_RECONNECT_COMPONENTEXCHANGE_TARGET;
    } else if (DDiagramHelper.isOAIB(diagram_p)) {
      return IToolNameConstants.TOOL_OAIB_RECONNECT_EXCHANGES;
    } else if (DDiagramHelper.isSDFB(diagram_p)) {
      return IToolNameConstants.TOOL_SDFB_RECONNECT_EXCHANGES;
    } else if (DDiagramHelper.isLDFB(diagram_p)) {
      return IToolNameConstants.TOOL_LDFB_RECONNECT_EXCHANGE;
    } else if (DDiagramHelper.isPDFB(diagram_p)) {
      return IToolNameConstants.TOOL_PDFB_RECONNECT_EXCHANGE;
    }
    return null;
  }

  /**
   * Retrieve the tool's name to create a component exchange with delegations
   * 
   * @param diagram_p
   * @return
   */
  public static String getToolCreateComponentExchangeWithDelegations(DDiagram diagram_p) {
    if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_CREATE_COMPONENT_EXCHANGE_WITH_DELEGATIONS;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_CREATE_COMPONENT_EXCHANGE_WITH_DELEGATIONS;
    }
    return null;
  }

  /**
   * Retrieve the tool's name to create a component exchange
   * 
   * @param diagram_p
   * @return
   */
  public static String getToolCreateComponentPort(DDiagram diagram_p, ComponentPortKind flow_p,
      OrientationPortKind in_p) {
    if (DDiagramHelper.isSAB(diagram_p)) {
      if (flow_p == ComponentPortKind.FLOW) {
        if (in_p == OrientationPortKind.IN) {
          return IToolNameConstants.TOOL_SAB_CREATE_IN_FLOW_PORT;
        } else if (in_p == OrientationPortKind.INOUT) {
          return IToolNameConstants.TOOL_SAB_CREATE_INOUT_FLOW_PORT;
        } else if (in_p == OrientationPortKind.OUT) {
          return IToolNameConstants.TOOL_SAB_CREATE_OUT_FLOW_PORT;
        }
        return IToolNameConstants.TOOL_SAB_CREATE_IN_FLOW_PORT;
      }
      return IToolNameConstants.TOOL_SAB_CREATE_STANDARD_PORT;

    } else if (DDiagramHelper.isLAB(diagram_p)) {
      if (flow_p == ComponentPortKind.FLOW) {
        if (in_p == OrientationPortKind.IN) {
          return IToolNameConstants.TOOL_LAB_CREATE_IN_FLOW_PORT_PORT;
        } else if (in_p == OrientationPortKind.INOUT) {
          return IToolNameConstants.TOOL_LAB_CREATE_INOUT_FLOW_PORT_PORT;
        } else if (in_p == OrientationPortKind.OUT) {
          return IToolNameConstants.TOOL_LAB_CREATE_OUT_FLOW_PORT_PORT;
        }
        return IToolNameConstants.TOOL_LAB_CREATE_IN_FLOW_PORT_PORT;
      }
      return IToolNameConstants.TOOL_LAB_CREATE_STANDARD_PORT_PORT;

    } else if (DDiagramHelper.isPAB(diagram_p)) {
      if (flow_p == ComponentPortKind.FLOW) {
        if (in_p == OrientationPortKind.IN) {
          return IToolNameConstants.TOOL_PAB_CREATE_INFLOW_PORT;
        } else if (in_p == OrientationPortKind.INOUT) {
          return IToolNameConstants.TOOL_PAB_CREATE_INOUT_FLOW_PORT_PORT;
        } else if (in_p == OrientationPortKind.OUT) {
          return IToolNameConstants.TOOL_PAB_CREATE_OUTFLOW_PORT;
        }
        return IToolNameConstants.TOOL_PAB_CREATE_INFLOW_PORT;
      }
      return IToolNameConstants.TOOL_PAB_CREATE_STANDARD_PORT;

    } else if (DDiagramHelper.isIDB(diagram_p)) {

      if (flow_p == ComponentPortKind.FLOW) {
        if (in_p == OrientationPortKind.IN) {
          return IToolNameConstants.TOOL_IDB_CREATE_IN_FLOW_PORT;
        } else if (in_p == OrientationPortKind.INOUT) {
          return IToolNameConstants.TOOL_IDB_CREATE_INOUT_FLOW_PORT;
        } else if (in_p == OrientationPortKind.OUT) {
          return IToolNameConstants.TOOL_IDB_CREATE_OUT_FLOW_PORT;
        }
        return IToolNameConstants.TOOL_IDB_CREATE_IN_FLOW_PORT;
      }
      return IToolNameConstants.TOOL_IDB_CREATE_STANDARD_PORT;
    }

    return null;
  }

  /**
   * Retrieve the tool's name to create a delegation
   * 
   * @param diagram_p
   * @return
   */
  public static String getToolCreateDelegation(DDiagram diagram_p) {
    if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_CREATE_DELEGATION;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_CREATE_DELEGATION;
    }
    return null;
  }

  /**
   * Returns the tool's name of the create functional chain
   * 
   * @param view_p
   * @return
   */
  public static String getToolCreateFunctionalChain(DDiagram diagram_p) {
    if (DDiagramHelper.isOAB(diagram_p)) {
      return IToolNameConstants.TOOL_OAB_CREATE_OPERATIONAL_PROCESS;
    } else if (DDiagramHelper.isSAB(diagram_p)) {
      return IToolNameConstants.TOOL_SAB_CREATE_FUNCTIONAL_CHAIN;
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_CREATE_FUNCTIONAL_CHAIN;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_CREATE_FUNCTIONAL_CHAIN;
    } else if (DDiagramHelper.isOAIB(diagram_p)) {
      return IToolNameConstants.TOOL_OAIB_CREATE_OPERATIONAL_PROCESS;
    } else if (DDiagramHelper.isSDFB(diagram_p)) {
      return IToolNameConstants.TOOL_SDFB_CREATE_FUNCTIONAL_CHAIN;
    } else if (DDiagramHelper.isLDFB(diagram_p)) {
      return IToolNameConstants.TOOL_LDFB_CREATE_FUNCTIONAL_CHAIN;
    } else if (DDiagramHelper.isPDFB(diagram_p)) {
      return IToolNameConstants.TOOL_PDFB_CREATE_FUNCTIONAL_CHAIN;
    }

    return null;
  }

  public static String getToolCreateFunctionInputPort(DDiagram diagram_p) {
    if (DDiagramHelper.isSDFB(diagram_p)) {
      return IToolNameConstants.TOOL_SDFB_CREATE_INPUT_PORT;
    } else if (DDiagramHelper.isLDFB(diagram_p)) {
      return IToolNameConstants.TOOL_LDFB_CREATE_INPUT_PORT;
    } else if (DDiagramHelper.isPDFB(diagram_p)) {
      return IToolNameConstants.TOOL_PDFB_CREATE_INPUT_PORT;
    } else if (DDiagramHelper.isSAB(diagram_p)) {
      return IToolNameConstants.TOOL_SAB_CREATE_FUNCTION_INPUT_PORT;
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_CREATE_INPUT_PORT;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_CREATE_FUNCTION_INPUT_PORT;
    }
    return null;
  }

  public static String getToolCreateFunctionOutputPort(DDiagram diagram_p) {
    if (DDiagramHelper.isSDFB(diagram_p)) {
      return IToolNameConstants.TOOL_SDFB_CREATE_OUTPUT_PORT;
    } else if (DDiagramHelper.isLDFB(diagram_p)) {
      return IToolNameConstants.TOOL_LDFB_CREATE_OUTPUT_PORT;
    } else if (DDiagramHelper.isPDFB(diagram_p)) {
      return IToolNameConstants.TOOL_PDFB_CREATE_OUTPUT_PORT;
    } else if (DDiagramHelper.isSAB(diagram_p)) {
      return IToolNameConstants.TOOL_SAB_CREATE_FUNCTION_OUTPUT_PORT;
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_CREATE_OUTPUT_PORT;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_CREATE_FUNCTION_OUTPUT_PORT;
    }
    return null;
  }

  /**
   * Retrieve the tool's name to reconnect a functional exchange
   * 
   * @param diagram_p
   * @return
   */
  public static String getToolReconnectFunctionalExchange(DDiagram diagram_p) {
    if (DDiagramHelper.isOAIB(diagram_p)) {
      return IToolNameConstants.TOOL_OAIB_RECONNECT_EXCHANGES;
    } else if (DDiagramHelper.isSDFB(diagram_p)) {
      return IToolNameConstants.TOOL_SDFB_RECONNECT_EXCHANGE;
    } else if (DDiagramHelper.isLDFB(diagram_p)) {
      return IToolNameConstants.TOOL_LDFB_RECONNECT_EXCHANGE;
    } else if (DDiagramHelper.isPDFB(diagram_p)) {
      return IToolNameConstants.TOOL_PDFB_RECONNECT_EXCHANGE;
    } else if (DDiagramHelper.isOAB(diagram_p)) {
      return IToolNameConstants.TOOL_OAB_RECONNECT_INTERACTION;
    } else if (DDiagramHelper.isSAB(diagram_p)) {
      return IToolNameConstants.TOOL_SAB_RECONNECT_FUNCTION_EXCHANGES;
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_RECONNECT_FUNCTION_EXCHANGE;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_RECONNECT_FUNCTION_EXCHANGE;
    }
    return null;
  }

  /**
   * Returns the tool's name of the insert remove function
   * 
   * @param view_p
   * @return
   */
  public static String getToolInsertScenario(DDiagram diagram_p) {
    if (DDiagramHelper.isOAIB(diagram_p)) {
      return IToolNameConstants.TOOL_OAIB_INSERT_SCENARIO_ELEMENTS;
    } else if (DDiagramHelper.isSDFB(diagram_p)) {
      return IToolNameConstants.TOOL_SDFB_INSERT_SCENARIO_ELEMENTS;
    } else if (DDiagramHelper.isLDFB(diagram_p)) {
      return IToolNameConstants.TOOL_LDFB_INSERT_SCENARIO_ELEMENTS;
    } else if (DDiagramHelper.isPDFB(diagram_p)) {
      return IToolNameConstants.TOOL_PDFB_INSERT_SCENARIO_ELEMENTS;
    } else if (DDiagramHelper.isOAB(diagram_p)) {
      return IToolNameConstants.TOOL_OAB_INSERT_SCENARIO_ELEMENTS;
    } else if (DDiagramHelper.isSAB(diagram_p)) {
      return IToolNameConstants.TOOL_SAB_INSERT_SCENARIO_ELEMENTS;
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_INSERT_SCENARIO_ELEMENTS;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_INSERT_SCENARIO_ELEMENTS;
    }
    return null;
  }

  /**
   * Returns the tool's name of the insert remove function
   * 
   * @param view_p
   * @return
   */
  public static String getToolInsertStateMode(DDiagram diagram_p) {
    if (DDiagramHelper.isOAIB(diagram_p)) {
      return IToolNameConstants.TOOL_OAIB_INSERT_STATEMODE_ELEMENTS;
    } else if (DDiagramHelper.isSDFB(diagram_p)) {
      return IToolNameConstants.TOOL_SDFB_INSERT_STATEMODE_ELEMENTS;
    } else if (DDiagramHelper.isLDFB(diagram_p)) {
      return IToolNameConstants.TOOL_LDFB_INSERT_STATEMODE_ELEMENTS;
    } else if (DDiagramHelper.isPDFB(diagram_p)) {
      return IToolNameConstants.TOOL_PDFB_INSERT_STATEMODE_ELEMENTS;
    } else if (DDiagramHelper.isOAB(diagram_p)) {
      return IToolNameConstants.TOOL_OAB_INSERT_STATEMODE_ELEMENTS;
    } else if (DDiagramHelper.isSAB(diagram_p)) {
      return IToolNameConstants.TOOL_SAB_INSERT_STATEMODE_ELEMENTS;
    } else if (DDiagramHelper.isLAB(diagram_p)) {
      return IToolNameConstants.TOOL_LAB_INSERT_STATEMODE_ELEMENTS;
    } else if (DDiagramHelper.isPAB(diagram_p)) {
      return IToolNameConstants.TOOL_PAB_INSERT_STATEMODE_ELEMENTS;
    }
    return null;
  }

  /**
   * Returns the tool's name of the insert remove function
   * 
   * @param view_p
   * @return
   */
  public static String getToolInsertRemoveFunction(DDiagram diagram_p) {
    if (DDiagramHelper.isOAIB(diagram_p)) {
      return IToolNameConstants.TOOL_OAIB_SHOW_HIDE_OA;
    } else if (DDiagramHelper.isSDFB(diagram_p)) {
      return IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTIONS;
    } else if (DDiagramHelper.isLDFB(diagram_p)) {
      return IToolNameConstants.TOOL_LDFB_SHOW_HIDE_FUNCTION;
    } else if (DDiagramHelper.isPDFB(diagram_p)) {
      return IToolNameConstants.TOOL_PDFB_SHOW_HIDE_FUNCTIONS;
    }
    return null;
  }

  public static String getToolInsertRemoveFunctionPort(DDiagram diagram_p) {
    if (DDiagramHelper.isSDFB(diagram_p)) {
      return IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTION_PORTS;
    } else if (DDiagramHelper.isLDFB(diagram_p)) {
      return IToolNameConstants.TOOL_LDFB_SHOW_HIDE_FUNCTION_PORTS;
    } else if (DDiagramHelper.isPDFB(diagram_p)) {
      return IToolNameConstants.TOOL_PDFB_SHOW_HIDE_FUNCTION_PORTS;
    }
    return null;
  }

  public static String getToolInsertRemoveFunctionalExchange(DDiagram diagram_p) {
    if (DDiagramHelper.isOAIB(diagram_p)) {
      return IToolNameConstants.TOOL_OAIB_SHOW_HIDE_INTERACTION;
    } else if (DDiagramHelper.isSDFB(diagram_p)) {
      return IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTIONAL_EXCHANGES;
    } else if (DDiagramHelper.isLDFB(diagram_p)) {
      return IToolNameConstants.TOOL_LDFB_SHOW_HIDE_FUNCTIONAL_EXCHANGES;
    } else if (DDiagramHelper.isPDFB(diagram_p)) {
      return IToolNameConstants.TOOL_PDFB_SHOW_HIDE_FUNCTIONAL_EXCHANGES;
    }
    return null;
  }

  /**
   * retrieve the tool name to create capability
   * 
   * @param diagram_p
   * @return
   */
  public static String getToolCreateCapability(DDiagram diagram_p) {
    if (DDiagramHelper.isOCB(diagram_p)) {
      return IToolNameConstants.TOOL_OCB_CREATE_OPERATIONAL_CAPABILITY;
    } else if (DDiagramHelper.isMCB(diagram_p)) {
      return IToolNameConstants.TOOL_MCB_CREATE_CAPABILITY;
    } else if (DDiagramHelper.isCRB(diagram_p)) {
      return IToolNameConstants.TOOL_CRB_CREATE_CAPABILITY_REALIZATION;
    }
    return null;
  }

  /**
   * retrieve the tool name to create involvement
   * 
   * @param diagram_p
   * @return
   */
  public static String getToolCreateInvolvement(DDiagram diagram_p) {
    if (DDiagramHelper.isOCB(diagram_p)) {
      return IToolNameConstants.TOOL_OCB_CREATE_INVOLMENT;
    } else if (DDiagramHelper.isMCB(diagram_p)) {
      return IToolNameConstants.TOOL_MCB_CREATE_INVOLVED_ACTOR;
    } else if (DDiagramHelper.isCRB(diagram_p)) {
      return IToolNameConstants.TOOL_CRB_CREATE_INVOLVEMENT;
    }
    return null;
  }

  /**
   * retrieve the tool name to create extends
   * 
   * @param diagram_p
   * @return
   */
  public static String getToolCreateExtends(DDiagram diagram_p) {
    if (DDiagramHelper.isOCB(diagram_p)) {
      return IToolNameConstants.TOOL_OCB_CREATE_EXTENDS;
    } else if (DDiagramHelper.isMCB(diagram_p)) {
      return IToolNameConstants.TOOL_MCB_CREATE_EXTENDS;
    } else if (DDiagramHelper.isCRB(diagram_p)) {
      return IToolNameConstants.TOOL_CRB_CREATE_EXTENDS;
    }
    return null;
  }

  /**
   * retrieve the tool name to create includes
   * 
   * @param diagram_p
   * @return
   */
  public static String getToolCreateIncludes(DDiagram diagram_p) {
    if (DDiagramHelper.isOCB(diagram_p)) {
      return IToolNameConstants.TOOL_OCB_CREATE_INCLUDES;
    } else if (DDiagramHelper.isMCB(diagram_p)) {
      return IToolNameConstants.TOOL_MCB_CREATE_INCLUDES;
    } else if (DDiagramHelper.isCRB(diagram_p)) {
      return IToolNameConstants.TOOL_CRB_CREATE_INCLUDES;
    }
    return null;
  }

  /**
   * retrieve the tool name to create capability generalization
   * 
   * @param diagram_p
   * @return
   */
  public static String getToolCreateCapabilityGeneralization(DDiagram diagram_p) {
    if (DDiagramHelper.isOCB(diagram_p)) {
      return IToolNameConstants.TOOL_OCB_CREATE_OPERATIONAL_CAPABILITY_GENERALIZATION;
    } else if (DDiagramHelper.isMCB(diagram_p)) {
      return IToolNameConstants.TOOL_MCB_CREATE_CAPABILITY_GENERALIZATION;
    } else if (DDiagramHelper.isCRB(diagram_p)) {
      return IToolNameConstants.TOOL_CRB_CREATE_CAPABILITY_GENERALIZATION;
    }
    return null;
  }

  /**
   * retrieve the tool name to create actor generalization
   * 
   * @param diagram_p
   * @return
   */
  public static String getToolCreateActorGeneralization(DDiagram diagram_p) {
    if (DDiagramHelper.isMCB(diagram_p)) {
      return IToolNameConstants.TOOL_MCB_CREATE_ACTOR_GENERALIZATION;
    } else if (DDiagramHelper.isCRB(diagram_p)) {
      return IToolNameConstants.TOOL_CRB_CREATE_ACTOR_GENERALIZATION;
    } else if (DDiagramHelper.isCC(diagram_p)) {
      return IToolNameConstants.TOOL_CC_CREATE_ACTOR_GENERALIZATION;
    } else if (DDiagramHelper.isCM(diagram_p)) {
      return IToolNameConstants.TOOL_CM_CREATE_ACTOR_GENERALIZATION;
    } else if (DDiagramHelper.isMB(diagram_p)) {
      return IToolNameConstants.TOOL_MB_CREATE_ACTOR_GENERALIZATION;
    } else if (DDiagramHelper.isCSA(diagram_p)) {
      return IToolNameConstants.TOOL_CSA_CREATE_ACTOR_GENERALIZATION;
    }
    return null;
  }

  public static String getToolCreateGeneralization(DDiagram diagram_p) {
    if (DDiagramHelper.isCSA(diagram_p)) {
      return IToolNameConstants.TOOL_CSA_CREATE_ACTOR_GENERALIZATION;
    } else if (DDiagramHelper.isIDB(diagram_p)) {
      return IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION;
    } else if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_CREATE_GENERALIZATION;
    }
    return null;
  }

  public static String getToolCreateClass(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_CREATE_CLASS;
    }
    return null;
  }

  public static String getToolCreateAssociation(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_CREATE_ASSOCIATION;
    }
    return null;
  }

  public static String getToolCreateAggregation(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_CREATE_AGGREGATION;
    }
    return null;
  }

  public static String getToolCreateComposition(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_CREATE_COMPOSITION;
    }
    return null;
  }

  public static String getToolCreateCollection(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_CREATE_COLLECTION;
    }
    return null;
  }

  public static String getToolCreateCollectionType(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_CREATE_COLLECTION_TYPE;
    }
    return null;
  }

  public static String getToolCreateNumericType(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_CREATE_NUMERIC_TYPE;
    }
    return null;
  }

  public static String getToolCreateBooleanType(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_CREATE_BOOLEAN_TYPE;
    }
    return null;
  }

  public static String getToolReconnectAssociationSource(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_RECONNECT_ASSOCIATION_SOURCE;
    }
    return null;
  }

  public static String getToolReconnectAssociationTarget(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_RECONNECT_ASSOCIATION_TARGET;
    }
    return null;
  }

  public static String getToolReconnectGeneralizationSource(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_RECONNECT_GENERALIZATION_SOURCE;
    }
    return null;
  }

  public static String getToolReconnectGeneralizationTarget(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_RECONNECT_GENERALIZATION_TARGET;
    }
    return null;
  }

  public static String getToolReconnectCollectionType(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_RECONNECT_COLLECTION_TYPE;
    }
    return null;
  }

  public static String getToolCreateEventEI(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_CREATE_EI_EVENT;
    }
    return null;
  }

  public static String getToolCreateEIE(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_CREATE_EXCHANGE_ITEM_ELEMENT;
    }
    return null;
  }

  public static String getToolReconnectEIESource(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_RECONNECT_EXCHANGEITEMELEMENT_SOURCE;
    }
    return null;
  }

  public static String getToolReconnectEIETarget(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_RECONNECT_EXCHANGEITEMELEMENT_TARGET;
    }
    return null;
  }

  public static String getToolReconnectExchangeItemElementSource(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_RECONNECT_EXCHANGEITEMELEMENT_SOURCE;
    }
    return null;
  }

  public static String getToolReconnectExchangeItemElementTarget(DDiagram diagram_p) {
    if (DDiagramHelper.isCDB(diagram_p)) {
      return IToolNameConstants.TOOL_CDB_RECONNECT_EXCHANGEITEMELEMENT_TARGET;
    }
    return null;
  }
}
