/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
  static final String RULES_HANDLER = "_R_H";

  static final String RESOLVER_HANDLER = "RESOLVER_HANDLER";
  static final String SESSION_HANDLER = "_S_H";

  static final String TRACEABILITY_HANDLER = "TH";
  static final String TRACEABILITY_TRANSFORMATION_HANDLER = "TTrH";
  static final String TRACEABILITY_TARGET_HANDLER = "TTaH";
  static final String TRACEABILITY_SOURCE_MERGE_HANDLER = "TSMH";
  static final String TRACEABILITY_TARGET_MERGE_HANDLER = "TTMH";
  static final String TRANSFORMATION_HANDLER = "TrH";

  static final String NOTIFY_HANDLER = "NOTIFY_HANDLER";
  static final String ATTACHMENT_HANDLER = "ATTACHMENT_HANDLER";
  static final String SCOPE_HANDLER = "SCOPE_HANDLER";
  static final String LOG_HANDLER = "LOG_HANDLER";
  static final String OPTIONS_HANDLER = "_O__H";
  static final String OPTIONS_SCOPE = "_OS";
  static final String OPTIONS_PARAMETERS = "OPTIONS_PARAMETERS";

  static final String LEVEL_HANDLER = "LEVEL_HANDLER";

  static final String MERGE_DIFFERENCES_HANDLER = "MERGE_DIFFERENCES_HANDLER";
  static final String FILTERING_DIFFERENCES_HANDLER = "FILTERING_DIFFERENCES_HANDLER";

  // Transition variables
  static final String TRANSITION_SCOPE = "TRANSITION_SCOPE";

  /**
   * Original selection (not semantically filtered)
   */
  static final String TRANSPOSER_SELECTION = "TRANSPOSER_SELECTION";

  /**
   * Original selection (not semantically filtered)
   */
  static final String TRANSITION_SELECTION = "TRANSITION_SELECTION";

  /**
   * Semantic sources (computed from non filtered selection)
   */
  static final String TRANSITION_SOURCES = "TRANSITION_SOURCES";

  static final String TRANSITION_SOURCE_EDITING_DOMAIN = "TRANSITION_SOURCE_EDITING_DOMAIN";
  static final String TRANSITION_SOURCE_ROOT = "_TSRo";
  static final String TRANSITION_SOURCE_RESOURCE = "_T_S_Res";

  static final String TRANSITION_TARGET_EDITING_DOMAIN = "TRANSITION_TARGET_EDITING_DOMAIN";
  static final String TRANSITION_TARGET_ROOT = "_TTRo";
  static final String TRANSITION_TARGET_RESOURCE = "_T_T_Res";

  // Transformation variables
  static final String TRANSFORMATION_SCOPE = "TRANSFORMATION_SCOPE";
  static final String TRANSFORMATION_SOURCES = "TRANSFORMATION_SOURCE";

  static final String TRANSFORMATION_EDITING_DOMAIN = "TRANSFORMATION_EDITING_DOMAIN";
  static final String TRANSFORMATION_TARGET_ROOT = "_Tr_T_R";
  static final String TRANSFORMATION_SOURCE_ROOT = "_Tr_S_R";

  static final String TRANSFORMED_ELEMENTS = "TRANSFORMED_ELEMENTS";

  // Merge variables
  static final String MERGE_COMPARISON = "MERGE_COMPARISON";

  static final String MERGE_REFERENCE_CONTAINER = "MERGE_SOURCE_CONTAINER";
  static final String MERGE_TARGET_CONTAINER = "MERGE_TARGET_CONTAINER";

  static final String MERGE_REFERENCE_SCOPE = "MERGE_SOURCE_SCOPE";
  static final String MERGE_TARGET_SCOPE = "MERGE_TARGET_SCOPE";

  static final String MERGE_REFERENCE_DIFFERENCES = "MERGE_REFERENCE_DIFFERENCES";
  static final String MERGE_TARGET_DIFFERENCES = "MERGE_TARGET_DIFFERENCES";
  static final String MERGE_REFERENCE_DIFFERENCES_TO_MERGE = "MERGE_REFERENCE_DIFFERENCES_TO_MERGE";
  static final String MERGE_TARGET_DIFFERENCES_TO_MERGE = "MERGE_TARGET_DIFFERENCES_TO_MERGE";

  // Shared variables
  static final String SAVE_REQUIRED = "SAVE_REQUIRED";

  static final String DIFFMERGE_DISABLE = "DIFFMERGE_DISABLE";

  // Log variable
  static final String DEFAULT_REPORT_COMPONENT = IReportManagerDefaultComponents.DEFAULT;

  // Handlers for incomplete rules
  static final String INCOMPLETE_ELEMENTS = "INCOMPLETE_ELEMENTS";

  static final String INITIAL_SOURCE_SCOPE = "IIS_S";
  static final String SOURCE_SCOPE = "SOURCE_SCOPE";
  static final String CONTEXT_SCOPE_HANDLER = "C_S_H";

  static final String SELECTION_CONTEXTS_HANDLER = "S_C_H";
  static final String SELECTION_CONTEXT__TRANSFORMATION = "SC__T";

  static final String NOTIFY__BEGIN_TRANSFORMATION = "NOTIFY__BEGIN_TRANSFORMATION";
  static final String NOTIFY__END_TRANSFORMATION = "NOTIFY__END_TRANSFORMATION";

  static final String TRANSPOSER_INSTANCE = "TRANSPOSER_INSTANCE";
  static final String TRANSPOSER_PURPOSE = "TRANSPOSER_PURPOSE";
  static final String TRANSPOSER_MAPPING = "TRANSPOSER_MAPPING";
  static final String TRANSPOSER_APPLY_IS_COMPLETE = "TRANSPOSER_APPLY_IS_COMPLETE";
  static final String TRANSPOSER_APPLY_REQUIRED = "TRANSPOSER_APPLY_REQUIRED";

  static final String SCOPE_SOURCES = "SCOPE_SOURCES";

  static final String COMMAND_NAME = "COMMAND_NAME";

  static final String IS_ACTIVE = "_isActive";
  static final String IS_IN_FOCUS_MODE = "_isInFocusMode";

  static final String CATEGORY_SEMANTIC = "category.semantic";
  static final String CATEGORY_BUSINESS = "category.business";
}
