package org.polarsys.capella.common.re.ui.renderers;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String Renderer_CustomLocation;
	public static String Renderer_NoLocation;
	public static String Renderer_DefaultLocation;
	public static String ReplicaRenderer_0;
	public static String ReplicaRenderer_2;
	public static String ReplicaRenderer_3;
	public static String SharedElementsRenderer_0;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
