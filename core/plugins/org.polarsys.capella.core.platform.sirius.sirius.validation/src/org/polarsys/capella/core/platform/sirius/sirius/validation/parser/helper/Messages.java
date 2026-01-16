package org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String InvalidNameHandler_OutdatedDiagram;
	public static String InvalidNameHandler_OutdatedElement;
	public static String InvalidNameHandler_OutdatedDiagrams;
	public static String InvalidNameHandler_OutdatedElements;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
