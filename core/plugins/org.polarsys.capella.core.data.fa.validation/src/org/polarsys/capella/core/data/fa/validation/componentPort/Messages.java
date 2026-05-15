package org.polarsys.capella.core.data.fa.validation.componentPort;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String MDCHK_ComponentPort_Orientation_UnknownPort;
	public static String MDCHK_ComponentPort_Orientation_WrongDirection_IN;
	public static String MDCHK_ComponentPort_Orientation_WrongDirection_OUT;
	public static String MDCHK_ComponentPort_Orientation_InconsistentDirection;
	public static String MDCHK_ComponentPort_Orientation_InconsistentDelegationDirection;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
