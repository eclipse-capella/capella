package org.polarsys.capella.core.re.validation.design.consistency;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String DCON_02_Rpl2RecConformanceConstraint_0;
	public static String DCON_02_Rpl2RecConformanceConstraint_1;
	public static String DCON_08_detectNoSourceNoTarget_1;
	public static String DCON_08_detectNoSourceNoTarget_2;
	public static String DCON_08_detectNoSourceNoTarget_3;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
