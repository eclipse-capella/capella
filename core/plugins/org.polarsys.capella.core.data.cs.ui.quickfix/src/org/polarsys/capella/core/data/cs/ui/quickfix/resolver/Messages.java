package org.polarsys.capella.core.data.cs.ui.quickfix.resolver;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String DWF_DC_36_Children_To_Closest_Valid_Ancestor_Resolver_1;
	public static String DWF_I_21_Resolver_transmission;
	public static String DWF_I_21_Resolver_acquisition;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
