package org.polarsys.capella.core.transition.system.ui.dialog;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String ProjectSelectionDialog_0;
	public static String ProjectSelectionDialog_1;
	public static String ProjectSelectionDialog_2;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
