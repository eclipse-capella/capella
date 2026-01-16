package org.polarsys.capella.core.data.la.validation.logicalComponent;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String LogicalActor_RealizedSystemActor_0;
	public static String LogicalSystem_RealizedSystemSystem_RootWithoutPreviousComponent;
	public static String LogicalSystem_RealizedSystemSystem_RootWithPreviousComponent;
	public static String MDCHK_LogicalComponent_Realization_RealizedComponentType;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
