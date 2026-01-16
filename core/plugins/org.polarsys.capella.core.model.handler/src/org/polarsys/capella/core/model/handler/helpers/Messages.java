package org.polarsys.capella.core.model.handler.helpers;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String RepresentationHelper_InvalidRepresentation;
	public static String RepresentationHelper_NotLoadedRepresentation;
	public static String RepresentationHelper_SynchronizedRepresentation;
	public static String RepresentationHelper_UnsynchronizedRepresentation;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
