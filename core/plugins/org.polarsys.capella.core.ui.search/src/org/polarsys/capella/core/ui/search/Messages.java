package org.polarsys.capella.core.ui.search;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String CapellaReplaceQuery_Validation_Replacement_Null;
	public static String CapellaSearchDialog_ShowIn_NotFound_Message;
	public static String CapellaSearchDialog_Title;
	public static String CapellaAttribute_Description;
	public static String CapellaAttribute_Name;
	public static String CapellaAttribute_Summary;
	public static String CapellaSearchPage_Checkbox_CaseSensitive_Label;
	public static String CapellaSearchPage_Checkbox_Regex_Label;
	public static String CapellaSearchPage_Checkbox_WholeWord_Label;
	public static String CapellaSearchPage_Combo_Pattern_Label_Regex_Disabled;
	public static String CapellaSearchPage_Combo_Pattern_Label_Regex_Enabled;
	public static String CapellaSearchPage_Validation_Message_Whole_Word_Same_Time_Regex;
	public static String CapellaSearchPage_Validation_Message_Pattern_Empty;
	public static String CapellaSearchPage_Validation_Message_Project_Selection;
	public static String CapellaSearchPage_Validation_Message_SearchAttribute_Selection;
	public static String CapellaSearchPage_Validation_Message_SearchMetaClass_Selection;
	public static String CapellaSearchPage_Validation_Message_SearchFilter_Selection;
	public static String CapellaSearchQuery_Search_Pattern_Not_Validated_Message;
	public static String CapellaSearchResult_Label;
	public static String CapellaSearchResult_Label_With_Active_Filters;
	public static String ReplaceDialog_Finished_Or_Canceled_Message;
	public static String ReplaceDialog_Label_Pattern;
	public static String ReplaceDialog_Label_Replacement;
	public static String ReplaceDialog_Label;
	public static String ReplaceDialog_Title;
	public static String ReplaceDialog_No_Match_Found_Message;
	public static String ReplaceJob_SubTitle;
	public static String ReplaceJob_Title;
	public static String SearchJob_SubTitle;
	public static String SearchJob_Title;
	public static String SelectAllButton_Name;
	public static String DeselectAllButton_Name;
	public static String RestoreDefaultsButton_Name;
	public static String Filters_Label;
	public static String Abstract_Label;
	public static String Semantic_Label;
	public static String SearchFor_Label;
	public static String Diagram_Label;
	public static String Note_Label;
	public static String WorkspaceScope_text;
	public static String SelectedElementScope_text;
	public static String ProjectScope_text;
	public static String ScopeGroup_text;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
