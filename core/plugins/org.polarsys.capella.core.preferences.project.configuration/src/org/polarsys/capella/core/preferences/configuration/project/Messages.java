package org.polarsys.capella.core.preferences.configuration.project;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String ConfigurationProjectContentsLocationArea_FolderAlreadyExists;
	public static String ConfigurationProjectContentsLocationArea_ForbiddenCharacter;
	public static String ProjectWizard_ReferencersSelection_Name;
	public static String ProjectWizard_ReferencersSelection_Title;
	public static String WizardProjectCreationPage_Description;
	public static String WizardProjectCreationPage_Description_1;
	public static String WizardProjectCreationPage_Description_2;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
