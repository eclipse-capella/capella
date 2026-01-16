package org.polarsys.capella.core.data.common.properties.fields;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String StateTransitionTriggerField_2;
	public static String StateTransitionTriggerField_3;
	public static String StateTransitionTriggerField_4;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
