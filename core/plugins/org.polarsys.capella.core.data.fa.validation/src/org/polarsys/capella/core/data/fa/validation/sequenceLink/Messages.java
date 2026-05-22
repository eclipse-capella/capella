package org.polarsys.capella.core.data.fa.validation.sequenceLink;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String SequenceLinkEndStatusHelper_LinkEnd_ControlNode;
	public static String SequenceLinkEndStatusHelper_LinkEnd_Function;
	public static String SequenceLink_Opposite_Direction;
	public static String SequenceLink_Inconsistent_Associated_FCIL;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
