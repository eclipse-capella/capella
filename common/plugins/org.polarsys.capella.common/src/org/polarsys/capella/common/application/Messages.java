package org.polarsys.capella.common.application;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.polarsys.capella.common.application.messages"; //$NON-NLS-1$

	public static String LOG_FILE_PATH__DESCRIPTION;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, CommonArgumentsConstants.class);
	}

	private Messages() {
	}
}
