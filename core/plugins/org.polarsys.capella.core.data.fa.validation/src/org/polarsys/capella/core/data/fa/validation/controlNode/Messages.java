package org.polarsys.capella.core.data.fa.validation.controlNode;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String ControlNode_Name;
	public static String ControlNode_Inconsistency_No_InOut_Sequence_Links;
	public static String ControlNode_Inconsistency_No_In_Sequence_Links;
	public static String ControlNode_Inconsistency_No_Out_Sequence_Links;
	public static String ControlNode_Inconsistency_Minimum_InOut_Sequence_Links;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
