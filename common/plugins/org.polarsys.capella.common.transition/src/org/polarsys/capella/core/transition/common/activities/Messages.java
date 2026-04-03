package org.polarsys.capella.core.transition.common.activities;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String AbstractActivity_0;
	
	public static String FinalizeTransitionActivity_0;
	public static String FinalizeTransitionActivity_1;
	public static String FinalizeTransitionActivity_2;
	
	public static String InitializeDiffMergeFromScopeActivity_0;
	public static String InitializeDiffMergeFromScopeActivity_1;
	
	public static String InitializeTransitionActivity_NoInput;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
