package org.polarsys.capella.core.data.interaction.validation.sequenceMessage;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	public static String MDCHK_SequenceMessage_ES_OES_InvokedOperation_0;
	public static String MDCHK_SequenceMessage_ES_OES_InvokedOperation_1;
	public static String MDCHK_SequenceMessage_ES_OES_InvokedOperation_4;
	public static String MDCHK_SequenceMessage_FS_OAS_InvokedOperation_0;
	public static String MDCHK_SequenceMessage_FS_OAS_InvokedOperation_1;
	public static String SequenceMessageInvokedOperationExchangeItems_1;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
