/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.constants;

public class ITopDownConstants {

  public static final String CONTEXTUAL_IGNORED_ELEMENTS = "CONTEXTUAL_IGNORED_ELEMENTS"; //$NON-NLS-1$

  public static final String TARGET_HANGLER = "TARGET_HANGLER"; //$NON-NLS-1$

  // Transformed elements to be stored in the same architecture than source
  // Transformed elements to be stored in the transformed architecture
  public static final String SOURCE_N2_ARCHITECTURE_ELEMENTS = "SOURCE_N2_ARCHITECTURE_ELEMENTS"; //$NON-NLS-1$
  public static final String SOURCE_N1_ARCHITECTURE_ELEMENTS = "SOURCE_N1_ARCHITECTURE_ELEMENTS"; //$NON-NLS-1$
  public static final String SOURCE_ARCHITECTURE_ELEMENTS = "SOURCE_ARCHITECTURE_ELEMENTS"; //$NON-NLS-1$
  public static final String TARGET_ARCHITECTURE_ELEMENTS = "TARGET_ARCHITECTURE_ELEMENTS"; //$NON-NLS-1$

  public static final String SELECTION_CONTEXT__PREVIOUS_N2_ARCHITECTURE = "SELECTION_CONTEXT__SOURCE_N2_ARCHITECTURE";
  public static final String SELECTION_CONTEXT__PREVIOUS_N1_ARCHITECTURE = "SELECTION_CONTEXT__TARGET_N1_ARCHITECTURE";
  public static final String SELECTION_CONTEXT__SOURCE_ARCHITECTURE = "SELECTION_CONTEXT__SOURCE_ARCHITECTURE";
  public static final String SELECTION_CONTEXT__TARGET_ARCHITECTURE = "SELECTION_CONTEXT__TARGET_ARCHITECTURE";

  public static final String TRANSITION_KIND = "TRANSITION_KIND";

  public static final String TOPDOWN_TRANSFORMATION_HANDLER = "TOPDOWN_TRANSFORMATION_HANDLER";

  public static final String OPTIONS_TRANSITION__INTERFACE = "interface.mode";
  public static final Boolean OPTIONS_TRANSITION__INTERFACE_DEFAULT = Boolean.FALSE;

  public static final String OPTIONS_TRANSITION__EXCHANGE_ITEM = "projection.exchangeItems";
  public static final Boolean OPTIONS_TRANSITION__EXCHANGE_ITEM_DEFAULT = Boolean.FALSE;

  public static final String OPTIONS_TRANSITION__DATATYPE = "projection.dataType";
  public static final Boolean OPTIONS_TRANSITION__DATATYPE_DEFAULT = Boolean.FALSE;

  public static final String OPTIONS_TRANSITION__STATE_MACHINE = "projection.component.stateMachine";
  public static final Boolean OPTIONS_TRANSITION__STATE_MACHINE_DEFAULT = Boolean.FALSE;

  public static final String OPTIONS_TRANSITION__FUNCTIONAL = "projection.functional";
  public static final Boolean OPTIONS_TRANSITION__FUNCTIONAL_DEFAULT = Boolean.TRUE;
  
  public static final String OPTIONS_INTERFACEGEN_PROPAGATE_EXCHANGE_ITEMS = "projection.interfaces.propagateEI";
  public static final String OPTIONS_INTERFACEGEN_CREATE_COMPONENT_EXCHANGE = "projection.interfaces.createCE";
  
  /**
   * FC2FS Scenario Initialization preference
   */
  public static final String OPTIONS_FC2FS_SEQUENCE_MESSAGE_STRATEGY = "transition.fc2fs.sequence.message.strategy";

  public static final String OPTIONS_FC2FS_MESSAGE_ONE_WAY = "fc2fs.msg.one.way";

  public static final String OPTIONS_FC2FS_MESSAGE_WITH_REPLY = "fc2fs.msg.with.reply";

  public static final String OPTIONS_FC2FS_SEQUENCE_MESSAGE_STRATEGY_DEFAULT = OPTIONS_FC2FS_MESSAGE_ONE_WAY;

  /**
   * OP2OAS Scenario Initialization preference
   */
  public static final String OPTIONS_OP2OAS_SEQUENCE_MESSAGE_STRATEGY = "transition.op2oas.sequence.message.strategy";

  public static final String OPTIONS_OP2OAS_MESSAGE_ONE_WAY = "op2oas.msg.one.way";

  public static final String OPTIONS_OP2OAS_MESSAGE_WITH_REPLY = "op2oas.msg.with.reply";

  public static final String OPTIONS_OP2OAS_SEQUENCE_MESSAGE_STRATEGY_DEFAULT = OPTIONS_OP2OAS_MESSAGE_ONE_WAY;
  
  public static final String OPTIONS_TRANSITION__SCENARIO_INITIALIZE = "transition.scenario.initialize";
  public static final Boolean OPTIONS_TRANSITION__SCENARIO_INITIALIZE_DEFAULT = Boolean.FALSE;

  public static final String OPTIONS_LOG = "log.enabled";
  public static final Boolean OPTIONS_LOG__DEFAULT = Boolean.TRUE;

  public static final String OPTIONS_TRANSITION__LCPC = "projection.lcpc.mode";
  public static final String OPTIONS_TRANSITION__LCPC_BREAKDOWN = "2";
  public static final String OPTIONS_TRANSITION__LCPC_LEAF = "1";
  public static final String OPTIONS_TRANSITION__LCPC_DEFAULT = OPTIONS_TRANSITION__LCPC_BREAKDOWN;

  public static final String OPTIONS__PROPERTY_VALUE__APPLIED_PROPERTY_VALUES = "projection.propertyValue.appliedPropertyValues";
  public static final Boolean OPTIONS__PROPERTY_VALUE__APPLIED_PROPERTY_VALUES__DEFAULT = Boolean.TRUE;

  public static final String OPTIONS__PROPERTY_VALUE__INVOLVED_ELEMENTS = "projection.propertyValue.involvedElements";
  public static final Boolean OPTIONS__PROPERTY_VALUE__INVOLVED_ELEMENTS__DEFAULT = Boolean.FALSE;
  
  public static final String OPTIONS_TRANSITION__PCCI_ENABLED = "projection.pc2ci.enabled";
  public static final Boolean OPTIONS_TRANSITION__PCCI_ENABLED_DEFAULT = Boolean.FALSE;
  
  public static final String OPTIONS_TRANSITION__PCCI_KIND = "projection.pc2ci.kind";
  public static final String OPTIONS_TRANSITION__PCCI_KIND_COTS = "COTSCI";
  public static final String OPTIONS_TRANSITION__PCCI_KIND_CS = "CSCI";
  public static final String OPTIONS_TRANSITION__PCCI_KIND_HW = "HWCI";
  public static final String OPTIONS_TRANSITION__PCCI_KIND_Interface = "InterfaceCI"; 
  public static final String OPTIONS_TRANSITION__PCCI_KIND_NDI = "NDICI";
  public static final String OPTIONS_TRANSITION__PCCI_KIND_Prime = "PrimeItemCI";
  public static final String OPTIONS_TRANSITION__PCCI_KIND_System = "SystemCI";
  public static final String OPTIONS_TRANSITION__PCCI_KIND_DEFAULT = OPTIONS_TRANSITION__PCCI_KIND_COTS;
  
  public static final String TRANSITION_TOPDOWN = "capella.core.transition.system.topdown";
  public static final String TRANSITION_TOPDOWN_FUNCTIONAL = "capella.core.transition.system.topdown.functional";
  public static final String TRANSITION_TOPDOWN_INTERFACE = "capella.core.transition.system.topdown.interface";
  public static final String TRANSITION_TOPDOWN_STATEMACHINE = "capella.core.transition.system.topdown.statemachine";
  public static final String TRANSITION_TOPDOWN_DATA = "capella.core.transition.system.topdown.data";
  public static final String TRANSITION_TOPDOWN_PROPERTYVALUE = "capella.core.transition.system.topdown.propertyvalue";
  public static final String TRANSITION_TOPDOWN_EXCHANGEITEM = "capella.core.transition.system.topdown.exchangeitem";
  public static final String TRANSITION_TOPDOWN_ACTOR = "capella.core.transition.system.topdown.actor";
  public static final String TRANSITION_TOPDOWN_SYSTEM = "capella.core.transition.system.topdown.system";
  public static final String TRANSITION_TOPDOWN_LC2PC = "capella.core.transition.system.topdown.lc2pc";
  public static final String TRANSITION_TOPDOWN_OE2ACTOR = "capella.core.transition.system.topdown.oe2actor";
  public static final String TRANSITION_TOPDOWN_OE2SYSTEM = "capella.core.transition.system.topdown.oe2system";
  public static final String TRANSITION_TOPDOWN_CAPABILITY = "capella.core.transition.system.topdown.capability";
  public static final String TRANSITION_TOPDOWN_OC2SM = "capella.core.transition.system.topdown.oc2mission";
  public static final String TRANSITION_TOPDOWN_OA2SC = "capella.core.transition.system.topdown.oa2capability";
  public static final String TRANSITION_TOPDOWN_OA2SM = "capella.core.transition.system.topdown.oa2mission";
  public static final String TRANSITION_TOPDOWN_PC2CI = "capella.core.transition.system.topdown.pc2ci";

  public static final String OPTIONS_SCOPE = "capella.core.transition.system.topdown";

  /** Properties id for windows>preference wizard */
  public static final String OPTIONS_SCOPE__PREFERENCES = "capella.core.transition.system.topdown.preferences";

  public static final String CONTEXT_SCOPE__AVOID_DIFF_ELEMENTS = "CONTEXT_SCOPE__AVOID_DIFF_ELEMENT";

  public static final String ABSTRACT_FUNCTION_ATTACHMENT_HANDLER = "ABSTRACT_FUNCTION_ATTACHMENT_HANDLER";
  public static final String ACTIVITY_ALLOCATION_ATTACHMENT_HANDLER = "ACTIVITY_ALLOCATION_ATTACHMENT_HANDLER";

}
