package org.polarsys.capella.core.data.information.datavalue.properties.sections;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String OpaqueExpressionSection_0;
	public static String OpaqueExpressionSection_1;
	public static String OpaqueExpressionSection_2;
	public static String OpaqueExpressionSection_3;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
