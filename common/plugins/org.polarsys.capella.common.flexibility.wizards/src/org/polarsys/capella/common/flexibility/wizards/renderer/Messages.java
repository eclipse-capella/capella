package org.polarsys.capella.common.flexibility.wizards.renderer;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String BrowseRenderer_SelectElementWizard_title;
	public static String BrowseRenderer_SelectElementWizard_message;
	public static String BrowseRenderer_0;
	public static String BrowseRenderer_1;
	public static String BrowseRenderer_3;
	public static String BrowseRenderer_4;
	public static String CopyTextRenderer_0;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
