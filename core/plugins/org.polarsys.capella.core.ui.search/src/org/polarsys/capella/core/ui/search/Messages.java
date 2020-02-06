/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

public class Messages {
  public static final String CapellaSearchRegexExplanation = "(* = any string, ? = any character, \\\\ = escape for literals: * ? \\\\);";
  public static final String CapellaSearchEmptyString = "";
  public static final String CapellaSearchContainingText = "Containing text:";
  public static final String CapellaReplaceQuery_Validation_Replacement_Null="The replacement must not be null";
  public static final String CapellaSearchDialog_ShowIn_NotFound_Message = "The selected element is not present in the search result for pattern '%s'";
  public static final String CapellaSearchDialog_Title = "Capella Search";
  public static final String CapellaSearchField_Description = "Description";
  public static final String CapellaSearchField_Name = "Name";
  public static final String CapellaSearchField_Summary = "Summary";
  public static final String CapellaSearchPage_Checkbox_CaseSensitive_Label = "Case sensitive";
  public static final String CapellaSearchPage_Checkbox_Regex_Label = "Regular expression";
  public static final String CapellaSearchPage_Checkbox_WholeWord_Label = "Whole word";
  public static final String CapellaSearchPage_Combo_Pattern_Label_Regex_Disabled = "Containing text (* = any string, ? = any character, \\\\ = escape for literals: * ? \\\\\\\\):";
  public static final String CapellaSearchPage_Combo_Pattern_Label_Regex_Enabled = "Matching the regular expression:";
  public static final String CapellaSearchPage_Validation_Message_OK = "";
  public static final String CapellaSearchPage_Validation_Message_Pattern_Empty = "Search pattern must not be empty";
  public static final String CapellaSearchPage_Validation_Message_Project_Selection = "At least one project must be selected";
  public static final String CapellaSearchPage_Validation_Message_SearchField_Selection = "At least one search field must be selected";
  public static final String CapellaSearchQuery_Search_Pattern_Not_Validated_Message = "The search pattern '%s' is not validated because: %s";
  public static final String CapellaSearchResult_Label = "'%s' - %d occurrence(s) in %d element(s) of %d project(s)";
  public static final String CapellaSearchResult_Label_With_Active_Filters = "'%s' - %d occurrence(s) in %d element(s) of %d project(s) - %d occurrence(s) filtered by %d active filter(s)";
  public static final String ReplaceDialog_Finished_Or_Canceled_Message = "Replaced '%s' by '%s' for: %d occurrence(s) of %d element(s) in %d project(s)";
  public static final String ReplaceDialog_Label_Pattern = "Replace";
  public static final String ReplaceDialog_Label_Replacement = "With:";
  public static final String ReplaceDialog_Label = "Replace:";
  public static final String ReplaceDialog_Title = "Capella Replace";
  public static final String ReplaceJob_SubTitle = "Replace %d occurrence(s) in the project: %s";
  public static final String ReplaceJob_Title = "Replacing occurrences by '%s'...";
  public static final String SearchJob_SubTitle = "Scanning elements in the project: %s";
  public static final String SearchJob_Title = "Searching for pattern '%s'...";
  public static final String SelectAllButton_Name = "Select All";
  public static final String DeselectAllButton_Name = "Deselect All";
  public static final String Filters_Label = "Filters";
  public static final String Abstract_Label = "Abstract";
  public static final String Semantic_Label = "Semantic";
  public static final String SearchFor_Label = "Search For";
  public static final String ModelElements_Key = "Model Elements";
  public static final String DiagramElements_Key = "Diagram Elements";
}
