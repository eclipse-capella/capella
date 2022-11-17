/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.compare;

import org.eclipse.osgi.util.NLS;

/**
 * Message management.
 */
@SuppressWarnings("javadoc")
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.compare.messages"; //$NON-NLS-1$
  public static String CapellaComparisonFactory_Label;
  public static String CapellaComparisonMethod_Usage_Default;
  public static String CapellaComparisonMethod_Usage_Default_Tooltip;
  public static String CapellaComparisonMethod_Usage_P2L;
  public static String CapellaComparisonMethod_Usage_P2L_Tooltip;
  public static String CapellaComparisonMethod_Usage_Transition;
  public static String CapellaComparisonMethod_Usage_Transition_Tooltip;
  public static String CapellaDifferenceCategoryProvider_CapellaSet_Description;
  public static String CapellaDifferenceCategoryProvider_CapellaSet_Text;
  public static String CapellaMatchPolicy_Criterion_ExchangeEnds;
  public static String CapellaMatchPolicy_Criterion_ExchangeEnds_Tooltip;
  public static String CapellaMatchPolicy_Criterion_P2L;
  public static String CapellaMatchPolicy_Criterion_P2L_Tooltip;
  public static String CapellaMatchPolicy_Criterion_SIDs;
  public static String CapellaMatchPolicy_Criterion_SIDs_Tooltip;
  public static String CapellaMatchPolicy_Criterion_Technical;
  public static String CapellaMatchPolicy_Criterion_Technical_Tooltip;
  public static String CapellaScope_DifferentVersion;
  public static String CapellaScope_DifferentVersionInterruption;
  public static String CapellaScope_PermissionCommandName;
  public static String CapellaScope_WriteError;
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
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  /**
   * Constructor
   */
  private Messages() {
    // Nothing needed
  }
}
