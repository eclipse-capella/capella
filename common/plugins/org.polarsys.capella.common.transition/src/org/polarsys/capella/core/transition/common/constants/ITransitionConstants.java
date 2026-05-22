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
package org.polarsys.capella.core.transition.common.constants;

import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

/**
 *
 */
public interface ITransitionConstants {

  // Shared handlers (we use short string, to avoid long hashCode computation, but we must ensure their are unique
  String RULES_HANDLER = "_R_H"; //$NON-NLS-1$

  String RESOLVER_HANDLER = "RESOLVER_HANDLER"; //$NON-NLS-1$
  String SESSION_HANDLER = "_S_H"; //$NON-NLS-1$

  String TRACEABILITY_HANDLER = "TH"; //$NON-NLS-1$
  String TRACEABILITY_TRANSFORMATION_HANDLER = "TTrH"; //$NON-NLS-1$
  String TRACEABILITY_TARGET_HANDLER = "TTaH"; //$NON-NLS-1$
  String TRACEABILITY_SOURCE_MERGE_HANDLER = "TSMH"; //$NON-NLS-1$
  String TRACEABILITY_TARGET_MERGE_HANDLER = "TTMH"; //$NON-NLS-1$
  String TRANSFORMATION_HANDLER = "TrH"; //$NON-NLS-1$

  String NOTIFY_HANDLER = "NOTIFY_HANDLER"; //$NON-NLS-1$
  String ATTACHMENT_HANDLER = "ATTACHMENT_HANDLER"; //$NON-NLS-1$
  String SCOPE_HANDLER = "SCOPE_HANDLER"; //$NON-NLS-1$
  String LOG_HANDLER = "LOG_HANDLER"; //$NON-NLS-1$
  String OPTIONS_HANDLER = "_O__H"; //$NON-NLS-1$
  String OPTIONS_SCOPE = "_OS"; //$NON-NLS-1$
  String OPTIONS_PARAMETERS = "OPTIONS_PARAMETERS"; //$NON-NLS-1$

  String LEVEL_HANDLER = "LEVEL_HANDLER"; //$NON-NLS-1$

  String MERGE_DIFFERENCES_HANDLER = "MERGE_DIFFERENCES_HANDLER"; //$NON-NLS-1$
  String FILTERING_DIFFERENCES_HANDLER = "FILTERING_DIFFERENCES_HANDLER"; //$NON-NLS-1$

  // Transition variables
  String TRANSITION_SCOPE = "TRANSITION_SCOPE"; //$NON-NLS-1$

  /**
   * Original selection (not semantically filtered)
   */
  String TRANSPOSER_SELECTION = "TRANSPOSER_SELECTION"; //$NON-NLS-1$

  /**
   * Original selection (not semantically filtered)
   */
  String TRANSITION_SELECTION = "TRANSITION_SELECTION"; //$NON-NLS-1$

  /**
   * Semantic sources (computed from non filtered selection)
   */
  String TRANSITION_SOURCES = "TRANSITION_SOURCES"; //$NON-NLS-1$

  String TRANSITION_SOURCE_EDITING_DOMAIN = "TRANSITION_SOURCE_EDITING_DOMAIN"; //$NON-NLS-1$
  String TRANSITION_SOURCE_ROOT = "_TSRo"; //$NON-NLS-1$
  String TRANSITION_SOURCE_RESOURCE = "_T_S_Res"; //$NON-NLS-1$

  String TRANSITION_TARGET_EDITING_DOMAIN = "TRANSITION_TARGET_EDITING_DOMAIN"; //$NON-NLS-1$
  String TRANSITION_TARGET_ROOT = "_TTRo"; //$NON-NLS-1$
  String TRANSITION_TARGET_RESOURCE = "_T_T_Res"; //$NON-NLS-1$

  // Transformation variables
  String TRANSFORMATION_SCOPE = "TRANSFORMATION_SCOPE"; //$NON-NLS-1$
  String TRANSFORMATION_SOURCES = "TRANSFORMATION_SOURCE"; //$NON-NLS-1$

  String TRANSFORMATION_EDITING_DOMAIN = "TRANSFORMATION_EDITING_DOMAIN"; //$NON-NLS-1$
  String TRANSFORMATION_TARGET_ROOT = "_Tr_T_R"; //$NON-NLS-1$
  String TRANSFORMATION_SOURCE_ROOT = "_Tr_S_R"; //$NON-NLS-1$

  String TRANSFORMED_ELEMENTS = "TRANSFORMED_ELEMENTS"; //$NON-NLS-1$

  // Merge variables
  String MERGE_COMPARISON = "MERGE_COMPARISON"; //$NON-NLS-1$

  String MERGE_REFERENCE_CONTAINER = "MERGE_SOURCE_CONTAINER"; //$NON-NLS-1$
  String MERGE_TARGET_CONTAINER = "MERGE_TARGET_CONTAINER"; //$NON-NLS-1$

  String MERGE_REFERENCE_SCOPE = "MERGE_SOURCE_SCOPE"; //$NON-NLS-1$
  String MERGE_TARGET_SCOPE = "MERGE_TARGET_SCOPE"; //$NON-NLS-1$

  String MERGE_REFERENCE_DIFFERENCES = "MERGE_REFERENCE_DIFFERENCES"; //$NON-NLS-1$
  String MERGE_TARGET_DIFFERENCES = "MERGE_TARGET_DIFFERENCES"; //$NON-NLS-1$
  String MERGE_REFERENCE_DIFFERENCES_TO_MERGE = "MERGE_REFERENCE_DIFFERENCES_TO_MERGE"; //$NON-NLS-1$
  String MERGE_TARGET_DIFFERENCES_TO_MERGE = "MERGE_TARGET_DIFFERENCES_TO_MERGE"; //$NON-NLS-1$

  // Shared variables
  String SAVE_REQUIRED = "SAVE_REQUIRED"; //$NON-NLS-1$

  String DIFFMERGE_DISABLE = "DIFFMERGE_DISABLE"; //$NON-NLS-1$

  // Log variable
  String DEFAULT_REPORT_COMPONENT = IReportManagerDefaultComponents.DEFAULT;

  // Handlers for incomplete rules
  String INCOMPLETE_ELEMENTS = "INCOMPLETE_ELEMENTS"; //$NON-NLS-1$

  String INITIAL_SOURCE_SCOPE = "IIS_S"; //$NON-NLS-1$
  String SOURCE_SCOPE = "SOURCE_SCOPE"; //$NON-NLS-1$
  String CONTEXT_SCOPE_HANDLER = "C_S_H"; //$NON-NLS-1$

  String SELECTION_CONTEXTS_HANDLER = "S_C_H"; //$NON-NLS-1$
  String SELECTION_CONTEXT__TRANSFORMATION = "SC__T"; //$NON-NLS-1$

  String NOTIFY__BEGIN_TRANSFORMATION = "NOTIFY__BEGIN_TRANSFORMATION"; //$NON-NLS-1$
  String NOTIFY__END_TRANSFORMATION = "NOTIFY__END_TRANSFORMATION"; //$NON-NLS-1$

  String TRANSPOSER_INSTANCE = "TRANSPOSER_INSTANCE"; //$NON-NLS-1$
  String TRANSPOSER_PURPOSE = "TRANSPOSER_PURPOSE"; //$NON-NLS-1$
  String TRANSPOSER_MAPPING = "TRANSPOSER_MAPPING"; //$NON-NLS-1$
  String TRANSPOSER_APPLY_IS_COMPLETE = "TRANSPOSER_APPLY_IS_COMPLETE"; //$NON-NLS-1$
  String TRANSPOSER_APPLY_REQUIRED = "TRANSPOSER_APPLY_REQUIRED"; //$NON-NLS-1$

  String SCOPE_SOURCES = "SCOPE_SOURCES"; //$NON-NLS-1$

  String COMMAND_NAME = "COMMAND_NAME"; //$NON-NLS-1$

  String IS_ACTIVE = "_isActive"; //$NON-NLS-1$
  String IS_IN_FOCUS_MODE = "_isInFocusMode"; //$NON-NLS-1$

  String CATEGORY_SEMANTIC = "category.semantic"; //$NON-NLS-1$
  String CATEGORY_BUSINESS = "category.business"; //$NON-NLS-1$
  
  String LC_TO_PC_COMPUTED_NATURE = "LC_TO_PC_COMPUTED_NATURE"; //$NON-NLS-1$
}
