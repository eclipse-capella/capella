package org.polarsys.capella.core.ui.properties.renderer;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String ToolkitBrowseRenderer_SelectionDialog_Title;
	public static String ToolkitBrowseRenderer_SelectionDialog_Message;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
