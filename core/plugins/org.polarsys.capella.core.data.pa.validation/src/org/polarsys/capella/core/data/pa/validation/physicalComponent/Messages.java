package org.polarsys.capella.core.data.pa.validation.physicalComponent;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String DeploymentChildPCChecks_0;
	public static String DeploymentChildPCChecks_1;
	public static String DeploymentParentPCChecks_0;
	public static String DeploymentParentPCChecks_1;
	public static String DeploymentParentPCChecks_2;
	public static String PhysicalActor_RealizedLogicalActor_0;
	public static String PhysicalComponent_RealizedLogicalComponents_0;
	public static String PhysicalComponentContainedAndDeployed_0;
	public static String PhysicalSystem_RealizedLogicalSystem_DefaultRootComponentName;
	public static String PhysicalSystem_RealizedLogicalSystem_4;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
