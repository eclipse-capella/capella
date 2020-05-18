/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commands.preferences.internalization.l10n;

import org.eclipse.osgi.util.NLS;

/**
 * An accessor class for externalized strings.
 * 
 */
public class CustomPreferencesMessages extends NLS {

	private static final String BUNDLE_NAME = "org.polarsys.capella.core.commands.preferences.internalization.l10n.CustomPreferencesMessages"; //$NON-NLS-1$

	public static String list_separator;
	public static String list_prefix;
	public static String list_suffix;
	public static String category_default_name;
	public static String category_default_desc;
	public static String eval_none_INFO_;
	public static String eval_all_pass_INFO_;
	public static String eval_some_fail_WARN_;
	public static String eval_some_info_INFO_;
	public static String eval_some_warn_WARN_;
	public static String eval_some_error_ERROR_;
	public static String xml_parsing_ERROR_;
	public static String xml_parsing_file_ERROR_;
	public static String xml_parsing_pop;
	public static String xml_parsing_peek;
	public static String xml_parsing_body;
	public static String xml_create_extension_ERROR_;
	public static String xml_include_file_ERROR_;
	public static String xml_unknown_file;
	public static String xml_resource_bundle_WARN_;
	public static String xml_markup_version_ERROR_;
	public static String provider_not_init_ERROR_;
	public static String provider_failed_WARN_;
	public static String provider_no_nsuri_ERROR_;
	public static String provider_dupe_constraint_WARN_;
	public static String traversal_nonsuri_WARN_;
	public static String traversal_noclass_WARN_;
	public static String traversal_interface_ERROR_;
	public static String traversal_eclass_WARN_;
	public static String constraint_not_init_WARN_;
	public static String constraint_not_init_name;
	public static String constraint_reason_no_id;
	public static String constraint_target_type_WARN_;
	public static String constraint_delegate_class_ERROR_;
	public static String constraint_delegate_instantiation_ERROR_;
	public static String constraint_delegate_inaccessible_ERROR_;
	public static String constraint_delegate_failed_ERROR_;
	public static String constraint_parser_not_init_WARN_;
	public static String constraint_parser_type_WARN_;
	public static String constraint_parser_missing_WARN_;
	public static String constraint_disabled_INFO_;
	public static String constraint_success_INFO_;
	public static String rule_incomplete_ERROR_;
	public static String rule_id;
	public static String rule_name;
	public static String rule_body;
	public static String rule_message;
	public static String client_noId_ERROR_;
	public static String client_noSelector_ERROR_;
	public static String client_selectorClass_ERROR_;
	public static String client_badExpression_ERROR_;
	public static String client_selectorFailure_ERROR_;
	public static String binding_noContextId_ERROR_;
	public static String binding_noSuchContext_ERROR_;
	public static String binding_noConstraintRef_WARN_;
	public static String binding_noCategoryRef_WARN_;
	public static String binding_noClientContextRef_WARN_;
	public static String mode_live;
	public static String mode_batch;
	public static String mode_unknown;
	public static String severity_info;
	public static String severity_warning;
	public static String severity_error;
	public static String severity_cancel;
	public static String severity_null;
	public static String progress_task_validating;
	public static String listener_uncaught_EXC_;
    public static String duplicate_eventType_ERROR_;
	public static String emfadapter_disabled_WARN_;
	public static String emfadapter_noInterface_WARN_;
	public static String emfadapter_noMethod_WARN_;
	public static String emfadapter_notBoolean_WARN_;
	public static String emfadapter_illegalAccess_WARN_;
	public static String emfadapter_interfaceNotFound_WARN_;
	public static String emfadapter_methodNotFound_WARN_;
	public static String emfadapter_noMessage;

	static {
		NLS.initializeMessages(BUNDLE_NAME, CustomPreferencesMessages.class);
	}
}
