package org.polarsys.capella.core.preferences.configuration.project.ui.wizards;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String ReferecedConfigurationProjectSelectionPage_Icon_Column;
	public static String ReferecedConfigurationProjectSelectionPage_Name_Column;
	public static String ReferecedConfigurationProjectSelectionPage_Title;
	public static String ReferecedConfigurationProjectSelectionPage_ErrorTitle;
	public static String ReferecedConfigurationProjectSelectionPage_ErrorMessage;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
