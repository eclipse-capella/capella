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


package org.polarsys.capella.core.model.helpers.naming;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class NamingConstants extends NLS {

	private static final String BUNDLE_NAME = "org.polarsys.capella.core.model.helpers.naming.messages"; //$NON-NLS-1$


	public static String TerminatePseudoState_Name;
	public static String ShallowHistoryPseudoState_Name;
	public static String JoinPseudoState_Name;
	public static String InitialPseudoState_Name;
	public static String ForkPseudoState_Name;
	public static String FinalState_Name;
	public static String ExitPointPseudoState_Name;
	public static String EntryPointPseudoState_Name;
	public static String DeepHistoryPseudoState_Name;
	public static String ChoicePseudoState_Name;

	public static String Function_FunctionKind_Duplicate;
	public static String Function_FunctionKind_Gather;
	public static String Function_FunctionKind_Split;
	public static String Function_FunctionKind_Select;
	public static String Function_FunctionKind_Route;

	public static String Region_DefaultRegion;
	public static String FunctionalExchange_OperationalAnalysis;

	public static String FunctionalInputPort_ShortName;
	public static String FunctionalOutputPort_ShortName;
	public static String ComponentPort_ShortName;
	public static String PhysicalPort_ShortName;

	public static String FunctionalExchange_ShortName;
	public static String ComponentExchange_ShortName;
	public static String ComponentExchange_Delegation_ShortName;
	public static String PhysicalLink_ShortName;
	
	public static String Capability_ShortName;
	public static String OperationalCapability_ShortName;

	// Default Structure Packages names.
	public static String SkeletonServicesImpl_package_name_systemAnalysis;
	public static String SkeletonServicesImpl_package_name_epbsArchitecture;
	public static String SkeletonServicesImpl_package_name_logicalArchitecture;
	public static String SkeletonServicesImpl_package_name_operationalAnalysis;
	public static String SkeletonServicesImpl_package_name_physicalArchitecture;

	// Common
	public static String CreateCommonCmd_data_pkg_name;
	public static String CreateCommonCmd_types_pkg_name;
	public static String CreateCommonCmd_interfaces_pkg_name;
	public static String CreateCommonCmd_capability_realisation_pkg_name;

	// Operational Analysis
	public static String CreateOpAnalysisCmd_name;
  public static String CreateOaAnalysisCmd_entity_name;
	public static String CreateOpAnalysisCmd_operationalActivity_root_name;
	public static String CreateOpAnalysisCmd_operationalActivities_pkg_name;
	public static String CreateOpAnalysisCmd_operationalCapabilities_pkg_name;
	public static String CreateOpAnalysisCmd_operationalEntities_pkg_name;
	public static String CreateOpAnalysisCmd_roles_pkg_name;
	public static String CreateOpAnalysisCmd_operational_context_name;

	// System Analysis
	public static String CreateSysAnalysisCmd_name;
	public static String CreateSysAnalysisCmd_system_function_root_name;
	public static String CreateSysAnalysisCmd_system_functions_pkg_name;
	public static String CreateSysAnalysisCmd_capabilities_pkg_name;
	public static String CreateSysAnalysisCmd_missions_pkg_name;
	public static String CreateSysAnalysisCmd_actors_pkg_name;
	public static String CreateSysAnalysisCmd_system_context_name;
	public static String CreateSysAnalysisCmd_system_name;
	public static String CreateSysAnalysisCmd_system_statemachine_name;

	// Logical Architecture
	public static String CreateLogicalArchCmd_name;
	public static String CreateLogicalArchCmd_logicalFunction_root_name;
	public static String CreateLogicalArchCmd_logicalFunctions_pkg_name;
	public static String CreateLogicalArchCmd_actors_pkg_name;
	public static String CreateLogicalArchCmd_logicalContext_name;
	public static String CreateLogicalArchCmd_logicalComponent_name;

	// Physical Architecture
	public static String CreatePhysicalArchCmd_name;
	public static String CreatePhysicalArchCmd_physicalFunction_root_name;
	public static String CreatePhysicalArchCmd_physicalFunctions_pkg_name;
	public static String CreatePhysicalArchCmd_actors_pkg_name;
	public static String CreatePhysicalArchCmd_physicalContext_name;
	public static String CreatePhysicalArchCmd_physicalComponent_name;

	// EPBS Architecture
	public static String CreateEPBSArchCmd_name;
	public static String CreateEPBSArchCmd_epbsContext_name;
	public static String CreateEPBSArchCmd_configurationItem_name;
  public static String CreateEPBSArchCmd_configurationItemPkg_name;
	

	// Predefined Types
	public static String PredefinedTypesCmd_boolean_name;
	public static String PredefinedTypesCmd_byte_name;
	public static String PredefinedTypesCmd_char_name;
	public static String PredefinedTypesCmd_double_name;
	public static String PredefinedTypesCmd_falseValue_name;
	public static String PredefinedTypesCmd_float_name;
	public static String PredefinedTypesCmd_hexadecimal_name;
	public static String PredefinedTypesCmd_integer_name;
	public static String PredefinedTypesCmd_long_name;
	public static String PredefinedTypesCmd_longLong_name;
	public static String PredefinedTypesCmd_predefinedDataTypePkg_name;
	public static String PredefinedTypesCmd_short_name;
	public static String PredefinedTypesCmd_string_name;
	public static String PredefinedTypesCmd_trueValue_name;
	public static String PredefinedTypesCmd_unsignedInteger_name;
	public static String PredefinedTypesCmd_unsignedLong_name;
	public static String PredefinedTypesCmd_unsignedLongLong_name;
	public static String PredefinedTypesCmd_unsignedShort_name;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, NamingConstants.class);
	}

	private NamingConstants() {
		// Private constructor.
	}
}
