package org.polarsys.capella.core.transition.common.transposer;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String ExtendedAnalyzer_0;
	public static String ExtendedAnalyzer_2;
	public static String ExtendedAnalyzer_4;
	public static String ExtendedCadenceLauncher_1;
	public static String ExtendedCadenceLauncher_3;
	public static String ExtendedCadenceLauncher_4;
	public static String ExtendedTopologicalSorter_message;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
