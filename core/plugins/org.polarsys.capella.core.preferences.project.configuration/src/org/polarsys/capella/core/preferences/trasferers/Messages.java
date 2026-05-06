package org.polarsys.capella.core.preferences.trasferers;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String WizardPreferencesTransfererExportPage_ProjectSelectionMessage;
	public static String WizardPreferencesTransfererExportPage_ProjectSelectionTitle;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
