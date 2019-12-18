/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

import org.eclipse.osgi.util.NLS;

public class Messages {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.ui.search.messages"; //$NON-NLS-1$
  public static String CapellaSearchRegexExplanation;
  public static String CapellaSearchEmptyString;
  public static String CapellaSearchContainingText;
  public static String CapellaReplaceQuery_Validation_Replacement_Null;
  public static String CapellaSearchDialog_ShowIn_NotFound_Message;
  public static String CapellaSearchDialog_Title;
  public static String CapellaSearchField_Description;
  public static String CapellaSearchField_Name;
  public static String CapellaSearchField_Summary;
  public static String CapellaSearchMatchFilter_CapellaElement_Description;
  public static String CapellaSearchMatchFilter_CapellaElement_Id;
  public static String CapellaSearchMatchFilter_CapellaElement_Label;
  public static String CapellaSearchMatchFilter_CapellaElement_Name;
  public static String CapellaSearchMatchFilter_NotModifiable_Description;
  public static String CapellaSearchMatchFilter_NotModifiable_Id;
  public static String CapellaSearchMatchFilter_NotModifiable_Label;
  public static String CapellaSearchMatchFilter_NotModifiable_Name;
  public static String CapellaSearchMatchFilter_Representation_Description;
  public static String CapellaSearchMatchFilter_Representation_Id;
  public static String CapellaSearchMatchFilter_Representation_Label;
  public static String CapellaSearchMatchFilter_Representation_Name;
  public static String CapellaSearchPage_Checkbox_CaseSensitive_Label;
  public static String CapellaSearchPage_Checkbox_Regex_Label;
  public static String CapellaSearchPage_Checkbox_WholeWord_Label;
  public static String CapellaSearchPage_Combo_Pattern_Label_Regex_Disabled;
  public static String CapellaSearchPage_Combo_Pattern_Label_Regex_Enabled;
  public static String CapellaSearchPage_ProjectsSelection_CheckedAll_Label;
  public static String CapellaSearchPage_ProjectsSelection_Group_Label;
  public static String CapellaSearchPage_ProjectsSelection_UnCheckedAll_Label;
  public static String CapellaSearchPage_Scope_Group_Label;
  public static String CapellaSearchPage_Validation_Message_OK;
  public static String CapellaSearchPage_Validation_Message_Pattern_Empty;
  public static String CapellaSearchPage_Validation_Message_Project_Selection;
  public static String CapellaSearchPage_Validation_Message_SearchField_Selection;
  public static String CapellaSearchQuery_Search_Pattern_Not_Validated_Message;
  public static String CapellaSearchResult_Label;
  public static String CapellaSearchResult_Label_With_Active_Filters;
  public static String CapellaSearchResult_Occurrences_Count_Label;
  public static String CapellaSearchResult_Occurrences_Count_Label_With_Active_Filters;
  public static String ReplaceDialog_Finished_Or_Canceled_Message;
  public static String ReplaceDialog_Label_Pattern;
  public static String ReplaceDialog_Label_Replacement;
  public static String ReplaceDialog_Label;
  public static String ReplaceDialog_No_Match_Found_Message;
  public static String ReplaceDialog_Title;
  public static String ReplaceJob_SubTitle;
  public static String ReplaceJob_Title;
  public static String SearchJob_SubTitle;
  public static String SearchJob_Title;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
