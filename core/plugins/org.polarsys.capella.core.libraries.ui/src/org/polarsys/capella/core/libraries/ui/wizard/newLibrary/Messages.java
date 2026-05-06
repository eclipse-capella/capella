package org.polarsys.capella.core.libraries.ui.wizard.newLibrary;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.polarsys.capella.core.libraries.ui.wizard.newLibrary.messages"; //$NON-NLS-1$
	
	public static String NewLibraryProjectWizard_window_title;
	public static String NewLibraryProjectWizard_page_title;
	public static String NewLibraryProjectWizard_page_description;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
