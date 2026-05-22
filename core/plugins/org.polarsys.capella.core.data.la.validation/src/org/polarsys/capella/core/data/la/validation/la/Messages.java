package org.polarsys.capella.core.data.la.validation.la;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String MDCHK_LogicalArchitecture_LaToSaRealization_DefaultSAName;
	public static String MDCHK_LogicalArchitecture_LaToSaRealization_1;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
