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
package org.polarsys.capella.core.commands.preferences.internalization.l10n;


/**
 * <p>
 * Repository for error codes and resource-bundle keys for corresponding
 * localized error messages.
 * </p>
 * <p>
 * This class is not intended to be used by clients.
 * </p>
 * 
 */
public final class CustomPreferencesStatusCodes {
	//
	// Integer-valued error codes
	//
	
	public static final int ALL_CONSTRAINTS_PASSED = 0;
	
	// avoid using 1 because it is generally abused (as a catch-all)
	public static final int OPERATION_CANCELED = 2;
	
	public static final int NO_CONSTRAINTS_EVALUATED = 10;
	public static final int SOME_CONSTRAINTS_FAILED = 11;
	public static final int SOME_CONSTRAINTS_INFO = 12;
	public static final int SOME_CONSTRAINTS_WARNING = 13;
	public static final int SOME_CONSTRAINTS_ERROR = 14;
	
	public static final int MESSAGES_NOT_INITED = 50;
	
	public static final int PROVIDER_NOT_INITED = 60;
	public static final int PROVIDER_FAILURE = 61;
	public static final int PROVIDER_NO_NAMESPACE_URI = 62;
	public static final int PROVIDER_DUPLICATE_CONSTRAINT = 63;
	
	public static final int ITEM_NOT_INITED = 70;
	public static final int CONSTRAINT_PARSER_NOT_INITED = 71;
	public static final int CONSTRAINT_PARSER_TYPE = 72;
	public static final int CONSTRAINT_PARSER_MISSING = 73;
	
	public static final int CONSTRAINT_DISABLED = 80;
	
	public static final int ERROR_PARSING_XML = 90;
	public static final int ERROR_PARSING_XML_FILE = 91;
	public static final int XML_STACK_EMPTY = 92;
	public static final int XML_RESOURCE_BUNDLE_NOT_FOUND = 93;
	
	public static final int TRAVERSAL_NO_NAMESPACE_URI = 100;
	public static final int TRAVERSAL_NO_CLASS = 101;
	public static final int TRAVERSAL_INTERFACE = 102;
	public static final int TRAVERSAL_ECLASS = 103;
	
	public static final int CLIENT_NO_ID = 110;
	public static final int CLIENT_NO_SELECTOR = 111;
	public static final int CLIENT_SELECTOR_WRONG_CLASS = 112;
	public static final int CLIENT_INVALID_EXPRESSION = 113;
	public static final int CLIENT_SELECTOR_FAILURE = 114;
	
	public static final int BINDING_NO_CLIENT = 120;
	public static final int BINDING_NO_SUCH_CLIENT = 121;
	public static final int BINDING_NO_CONSTRAINT = 122;
	public static final int BINDING_NO_CATEGORY = 123;
	public static final int BINDING_NO_CLIENT_CONTEXT = 124;
	
	public static final int LISTENER_UNCAUGHT_EXCEPTION = 130;
	
    public static final int DUPLICATE_EVENT_TYPE = 140;
    
	//
	// error messages
	//
	
	public static final String NO_CONSTRAINTS_EVALUATED_MSG = CustomPreferencesMessages.eval_none_INFO_;
	public static final String ALL_CONSTRAINTS_PASSED_MSG = CustomPreferencesMessages.eval_all_pass_INFO_;
	public static final String SOME_CONSTRAINTS_FAILED_MSG = CustomPreferencesMessages.eval_some_fail_WARN_;
	public static final String SOME_CONSTRAINTS_INFO_MSG = CustomPreferencesMessages.eval_some_info_INFO_;
	public static final String SOME_CONSTRAINTS_WARNING_MSG = CustomPreferencesMessages.eval_some_warn_WARN_;
	public static final String SOME_CONSTRAINTS_ERROR_MSG = CustomPreferencesMessages.eval_some_error_ERROR_;

	// no MESSAGES_NOT_INITED_KEY because this message cannot be translated
	//   (as the error condition is that the message bundle couldn't be loaded)
	
	public static final String PROVIDER_NOT_INITED_MSG = CustomPreferencesMessages.provider_not_init_ERROR_;
	public static final String PROVIDER_FAILURE_MSG = CustomPreferencesMessages.provider_failed_WARN_;
	public static final String PROVIDER_NO_NAMESPACE_URI_MSG = CustomPreferencesMessages.provider_no_nsuri_ERROR_;
    public static final String PROVIDER_DUPLICATE_CONSTRAINT_MSG = CustomPreferencesMessages.provider_dupe_constraint_WARN_;
	
	public static final String CONSTRAINT_NOT_INITED_MSG = CustomPreferencesMessages.constraint_not_init_WARN_;
	public static final String TARGET_TYPE_NOT_FOUND_MSG = CustomPreferencesMessages.constraint_target_type_WARN_;
	public static final String DELEGATE_CLASS_NOT_FOUND_MSG = CustomPreferencesMessages.constraint_delegate_class_ERROR_;
	public static final String DELEGATE_INSTANTIATION_MSG = CustomPreferencesMessages.constraint_delegate_instantiation_ERROR_;
	public static final String DELEGATE_METHOD_INACCESSIBLE_MSG = CustomPreferencesMessages.constraint_delegate_inaccessible_ERROR_;
	public static final String DELEGATE_METHOD_FAILED_MSG = CustomPreferencesMessages.constraint_delegate_failed_ERROR_;
	public static final String CONSTRAINT_PARSER_NOT_INITED_MSG = CustomPreferencesMessages.constraint_parser_not_init_WARN_;
	public static final String CONSTRAINT_PARSER_TYPE_MSG = CustomPreferencesMessages.constraint_parser_type_WARN_;
	public static final String CONSTRAINT_PARSER_MISSING_MSG = CustomPreferencesMessages.constraint_parser_missing_WARN_;
	
	public static final String CONSTRAINT_DISABLED_MSG = CustomPreferencesMessages.constraint_disabled_INFO_;
	public static final String CONSTRAINT_SUCCESS_MSG = CustomPreferencesMessages.constraint_success_INFO_;

	public static final String ERROR_PARSING_XML_MSG = CustomPreferencesMessages.xml_parsing_ERROR_;
	public static final String ERROR_PARSING_XML_FILE_MSG = CustomPreferencesMessages.xml_parsing_file_ERROR_;
	public static final String XML_CANNOT_POP_STACK_MSG = CustomPreferencesMessages.xml_parsing_pop;
	public static final String XML_CANNOT_PEEK_STACK_MSG = CustomPreferencesMessages.xml_parsing_peek;
	public static final String XML_NO_STACK_BODY_MSG = CustomPreferencesMessages.xml_parsing_body;
	public static final String XML_RESOURCE_BUNDLE_NOT_FOUND_MSG = CustomPreferencesMessages.xml_resource_bundle_WARN_;
	public static final String XML_WRONG_VERSION_MSG = CustomPreferencesMessages.xml_markup_version_ERROR_;
	
	public static final String XML_CREATE_EXTENSION_MSG = CustomPreferencesMessages.xml_create_extension_ERROR_;
	public static final String XML_INCLUDE_FILE_MSG = CustomPreferencesMessages.xml_include_file_ERROR_;
	
	public static final String TRAVERSAL_NO_NAMESPACE_URI_MSG = CustomPreferencesMessages.traversal_nonsuri_WARN_;
	public static final String TRAVERSAL_NO_CLASS_MSG = CustomPreferencesMessages.traversal_noclass_WARN_;
	public static final String TRAVERSAL_INTERFACE_MSG = CustomPreferencesMessages.traversal_interface_ERROR_;
	public static final String TRAVERSAL_ECLASS_MSG = CustomPreferencesMessages.traversal_eclass_WARN_;

	public static final String LISTENER_UNCAUGHT_EXCEPTION_MSG = CustomPreferencesMessages.listener_uncaught_EXC_;
	
    public static final String DUPLICATE_EVENT_TYPE_MSG = CustomPreferencesMessages.duplicate_eventType_ERROR_;
    
	/**
	 * Cannot be instantiated by clients.
	 */
	private CustomPreferencesStatusCodes() {
		// nothing to do
	}
}
