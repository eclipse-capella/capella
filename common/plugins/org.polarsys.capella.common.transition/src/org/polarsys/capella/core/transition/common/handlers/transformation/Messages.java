package org.polarsys.capella.core.transition.common.handlers.transformation;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String DefaultTransformationHandler_0;
	public static String DefaultTransformationHandler_1;
	public static String DefaultTransformationHandler_2;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
