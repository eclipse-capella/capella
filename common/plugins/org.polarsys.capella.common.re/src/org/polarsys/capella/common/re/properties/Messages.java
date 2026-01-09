package org.polarsys.capella.common.re.properties;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String CatalogElementLinkSuffixProperty_0;
	public static String CatalogElementLinkSuffixProperty_1;
	public static String CatalogElementSuffixProperty_0;
	public static String CatalogElementSuffixProperty_1;
	public static String DefaultChildrenCompliancyProperty_0;
	public static String DependenciesProperty_0;
	public static String InvalidSharedElementsProperty_0;
	public static String LocationTargetProperty_0;
	public static String SharedElementsProperty_0;
	public static String SuffixReminderProperty_0;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
