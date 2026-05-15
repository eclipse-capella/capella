package org.polarsys.capella.common.re.re2rpl.create.properties;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String InitialSourceProperty_1;
	public static String ReplicaContentProperty_1;
	public static String ReplicaNameProperty_2;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
