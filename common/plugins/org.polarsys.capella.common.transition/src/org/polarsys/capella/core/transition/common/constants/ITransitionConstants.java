/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
  String RULES_HANDLER = "_R_H";

  String RESOLVER_HANDLER = "RESOLVER_HANDLER";
  String SESSION_HANDLER = "_S_H";

  String TRACEABILITY_HANDLER = "TH";
  String TRACEABILITY_TRANSFORMATION_HANDLER = "TTrH";
  String TRACEABILITY_TARGET_HANDLER = "TTaH";
  String TRACEABILITY_SOURCE_MERGE_HANDLER = "TSMH";
  String TRACEABILITY_TARGET_MERGE_HANDLER = "TTMH";
  String TRANSFORMATION_HANDLER = "TrH";

  String NOTIFY_HANDLER = "NOTIFY_HANDLER";
  String ATTACHMENT_HANDLER = "ATTACHMENT_HANDLER";
  String SCOPE_HANDLER = "SCOPE_HANDLER";
  String LOG_HANDLER = "LOG_HANDLER";
  String OPTIONS_HANDLER = "_O__H";
  String OPTIONS_SCOPE = "_OS";
  String OPTIONS_PARAMETERS = "OPTIONS_PARAMETERS";

  String LEVEL_HANDLER = "LEVEL_HANDLER";

  String MERGE_DIFFERENCES_HANDLER = "MERGE_DIFFERENCES_HANDLER";
  String FILTERING_DIFFERENCES_HANDLER = "FILTERING_DIFFERENCES_HANDLER";

  // Transition variables
  String TRANSITION_SCOPE = "TRANSITION_SCOPE";

  /**
   * Original selection (not semantically filtered)
   */
  String TRANSPOSER_SELECTION = "TRANSPOSER_SELECTION";

  /**
   * Original selection (not semantically filtered)
   */
  String TRANSITION_SELECTION = "TRANSITION_SELECTION";

  /**
   * Semantic sources (computed from non filtered selection)
   */
  String TRANSITION_SOURCES = "TRANSITION_SOURCES";

  String TRANSITION_SOURCE_EDITING_DOMAIN = "TRANSITION_SOURCE_EDITING_DOMAIN";
  String TRANSITION_SOURCE_ROOT = "_TSRo";
  String TRANSITION_SOURCE_RESOURCE = "_T_S_Res";

  String TRANSITION_TARGET_EDITING_DOMAIN = "TRANSITION_TARGET_EDITING_DOMAIN";
  String TRANSITION_TARGET_ROOT = "_TTRo";
  String TRANSITION_TARGET_RESOURCE = "_T_T_Res";

  // Transformation variables
  String TRANSFORMATION_SCOPE = "TRANSFORMATION_SCOPE";
  String TRANSFORMATION_SOURCES = "TRANSFORMATION_SOURCE";

  String TRANSFORMATION_EDITING_DOMAIN = "TRANSFORMATION_EDITING_DOMAIN";
  String TRANSFORMATION_TARGET_ROOT = "_Tr_T_R";
  String TRANSFORMATION_SOURCE_ROOT = "_Tr_S_R";

  String TRANSFORMED_ELEMENTS = "TRANSFORMED_ELEMENTS";

  // Merge variables
  String MERGE_COMPARISON = "MERGE_COMPARISON";

  String MERGE_REFERENCE_CONTAINER = "MERGE_SOURCE_CONTAINER";
  String MERGE_TARGET_CONTAINER = "MERGE_TARGET_CONTAINER";

  String MERGE_REFERENCE_SCOPE = "MERGE_SOURCE_SCOPE";
  String MERGE_TARGET_SCOPE = "MERGE_TARGET_SCOPE";

  String MERGE_REFERENCE_DIFFERENCES = "MERGE_REFERENCE_DIFFERENCES";
  String MERGE_TARGET_DIFFERENCES = "MERGE_TARGET_DIFFERENCES";
  String MERGE_REFERENCE_DIFFERENCES_TO_MERGE = "MERGE_REFERENCE_DIFFERENCES_TO_MERGE";
  String MERGE_TARGET_DIFFERENCES_TO_MERGE = "MERGE_TARGET_DIFFERENCES_TO_MERGE";

  // Shared variables
  String SAVE_REQUIRED = "SAVE_REQUIRED";

  String DIFFMERGE_DISABLE = "DIFFMERGE_DISABLE";

  // Log variable
  String DEFAULT_REPORT_COMPONENT = IReportManagerDefaultComponents.DEFAULT;

  // Handlers for incomplete rules
  String INCOMPLETE_ELEMENTS = "INCOMPLETE_ELEMENTS";

  String INITIAL_SOURCE_SCOPE = "IIS_S";
  String SOURCE_SCOPE = "SOURCE_SCOPE";
  String CONTEXT_SCOPE_HANDLER = "C_S_H";

  String SELECTION_CONTEXTS_HANDLER = "S_C_H";
  String SELECTION_CONTEXT__TRANSFORMATION = "SC__T";

  String NOTIFY__BEGIN_TRANSFORMATION = "NOTIFY__BEGIN_TRANSFORMATION";
  String NOTIFY__END_TRANSFORMATION = "NOTIFY__END_TRANSFORMATION";

  String TRANSPOSER_INSTANCE = "TRANSPOSER_INSTANCE";
  String TRANSPOSER_PURPOSE = "TRANSPOSER_PURPOSE";
  String TRANSPOSER_MAPPING = "TRANSPOSER_MAPPING";
  String TRANSPOSER_APPLY_IS_COMPLETE = "TRANSPOSER_APPLY_IS_COMPLETE";
  String TRANSPOSER_APPLY_REQUIRED = "TRANSPOSER_APPLY_REQUIRED";

  String SCOPE_SOURCES = "SCOPE_SOURCES";

  String COMMAND_NAME = "COMMAND_NAME";

  String IS_ACTIVE = "_isActive";
  String IS_IN_FOCUS_MODE = "_isInFocusMode";

  String CATEGORY_SEMANTIC = "category.semantic";
  String CATEGORY_BUSINESS = "category.business";
  
  String LC_TO_PC_COMPUTED_NATURE = "LC_TO_PC_COMPUTED_NATURE";
}
