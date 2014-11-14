/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
 */
public interface ITransitionConstants {

  // Shared handlers (we use short string, to avoid long hashCode computation, but we must ensure their are unique
  public static final String RULES_HANDLER = "_R_H";

  public static final String RESOLVER_HANDLER = "RESOLVER_HANDLER";
  public static final String SESSION_HANDLER = "_S_H";

  public static final String TRACEABILITY_HANDLER = "TH";
  public static final String TRACEABILITY_TRANSFORMATION_HANDLER = "TTrH";
  public static final String TRACEABILITY_TARGET_HANDLER = "TTaH";
  public static final String TRACEABILITY_SOURCE_MERGE_HANDLER = "TSMH";
  public static final String TRACEABILITY_TARGET_MERGE_HANDLER = "TTMH";
  public static final String TRANSFORMATION_HANDLER = "TrH";

  public static final String NOTIFY_HANDLER = "NOTIFY_HANDLER";
  public static final String ATTACHMENT_HANDLER = "ATTACHMENT_HANDLER";
  public static final String SCOPE_HANDLER = "SCOPE_HANDLER";
  public static final String LOG_HANDLER = "LOG_HANDLER";
  public static final String OPTIONS_HANDLER = "_O__H";
  public static final String OPTIONS_SCOPE = "_OS";
  public static final String OPTIONS_PARAMETERS = "OPTIONS_PARAMETERS";

  public static final String LEVEL_HANDLER = "LEVEL_HANDLER";

  public static final String FILTERING_DIFFERENCES_HANDLER = "FILTERING_DIFFERENCES_HANDLER";

  // Transition variables
  public static final String TRANSITION_SCOPE = "TRANSITION_SCOPE";

  public static final String TRANSITION_SELECTION = "TRANSITION_SELECTION";
  public static final String TRANSITION_SOURCES = "TRANSITION_SOURCES";
  public static final String TRANSITION_SOURCE_ROOT = "_TSRo";
  public static final String TRANSITION_SOURCE_ARCHITECTURE = "_TSA";
  public static final String TRANSITION_SOURCE_RESOURCE = "_T_S_Res";

  public static final String TRANSITION_TARGET_ROOT = "_TTRo";
  public static final String TRANSITION_TARGET_RESOURCE = "_T_T_Res";

  // Transformation variables
  public static final String TRANSFORMATION_SCOPE = "TRANSFORMATION_SCOPE";
  public static final String TRANSFORMATION_SOURCES = "TRANSFORMATION_SOURCE";

  public static final String TRANSFORMATION_TARGET_ROOT = "_Tr_T_R";
  public static final String TRANSFORMATION_SOURCE_ROOT = "_Tr_S_R";

  public static final String TRANSFORMED_ELEMENTS = "TRANSFORMED_ELEMENTS";

  // Merge variables
  public static final String MERGE_COMPARISON = "MERGE_COMPARISON";

  public static final String MERGE_REFERENCE_CONTAINER = "MERGE_SOURCE_CONTAINER";
  public static final String MERGE_TARGET_CONTAINER = "MERGE_TARGET_CONTAINER";

  public static final String MERGE_REFERENCE_SCOPE = "MERGE_SOURCE_SCOPE";
  public static final String MERGE_TARGET_SCOPE = "MERGE_TARGET_SCOPE";

  public static final String MERGE_REFERENCE_DIFFERENCES = "MERGE_REFERENCE_DIFFERENCES";
  public static final String MERGE_TARGET_DIFFERENCES = "MERGE_TARGET_DIFFERENCES";
  public static final String MERGE_REFERENCE_DIFFERENCES_TO_MERGE = "MERGE_REFERENCE_DIFFERENCES_TO_MERGE";
  public static final String MERGE_TARGET_DIFFERENCES_TO_MERGE = "MERGE_TARGET_DIFFERENCES_TO_MERGE";

  // Shared variables
  public static final String RESOURCE_SET = "RESOURCE_SET";
  public static final String SAVE_REQUIRED = "SAVE_REQUIRED";

  public static final String DIFFMERGE_DISABLE = "DIFFMERGE_DISABLE";

  // Log variable
  public static final String DEFAULT_REPORT_COMPONENT = IReportManagerDefaultComponents.DEFAULT;

  public static final String TRANSPOSER_APPLY_REQUIRED = "T_A_R";

  //Handlers for incomplete rules
  public static final String TRANSPOSER_APPLY_IS_COMPLETE = "T_I_C";
  public static final String INCOMPLETE_ELEMENTS = "INCOMPLETE_ELEMENTS";

  public static final String INITIAL_SOURCE_SCOPE = "IIS_S";
  public static final String SOURCE_SCOPE = "S_S";
  public static final String CONTEXT_SCOPE_HANDLER = "C_S_H";

  public static final String SELECTION_CONTEXTS_HANDLER = "S_C_H";
  public static final String SELECTION_CONTEXT__TRANSFORMATION = "SC__T";

  public static final String NOTIFY__BEGIN_TRANSFORMATION = "NOTIFY__BEGIN_TRANSFORMATION";
  public static final String NOTIFY__END_TRANSFORMATION = "NOTIFY__END_TRANSFORMATION";

  public static final String TRANSPOSER_INSTANCE = "TRANSPOSER_INSTANCE";
  public static final String TRANSPOSER_SELECTION = "TRANSPOSER_SELECTION";

  public static final String SCOPE_SOURCES = "SCOPE_SOURCES";

}
