package org.polarsys.capella.core.data.pa.ui.quickfix.resolver;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String PhysicalComponentNatureToBehaviorResolver_0;
	public static String PhysicalComponentNatureToNodeResolver_0;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
