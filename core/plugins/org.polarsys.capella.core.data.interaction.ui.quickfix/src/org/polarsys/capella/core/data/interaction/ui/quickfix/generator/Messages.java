package org.polarsys.capella.core.data.interaction.ui.quickfix.generator;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String GOTO_Capability;
	public static String GOTO_Component;
	public static String GOTO_InvokedOperation;
	public static String GOTO_SeqMsgSource;
	public static String GOTO_SeqMsgTarget;
	public static String GOTO_AllocatedEI;
	public static String GOTO_AllocatingInterface;
	public static String GOTO_ExchangeSourceFunc;
	public static String GOTO_ExchangeTargetFunc;
	public static String GOTO_ExchangeSourceComp;
	public static String GOTO_ExchangeTargetComp;
	public static String GOTO_RelatedFunc;
	public static String GOTO_RoleComponent;
	public static String GOTO_RoleFunction;
	public static String GOTO_RelatedModeState;
	
	public static String DWF_DS_04_Resolutions_Delete;
	
	public static String DWF_DS_18_19_Resolutions_Delete;
	
	public static String DWF_DS_20_21_22_Resolutions_Delete;
	
	public static String Helper_Resolutions_0;
	public static String Helper_Resolutions_1;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
