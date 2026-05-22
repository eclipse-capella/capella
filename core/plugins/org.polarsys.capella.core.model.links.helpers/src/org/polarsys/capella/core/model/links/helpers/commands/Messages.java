package org.polarsys.capella.core.model.links.helpers.commands;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
	
	public static String AddCompExchangeToCompExchangeCat_Label;
	public static String AddComponentExchangeToPhysicalLinkCommand_Label;
	public static String AddExchangeCategoryToFunctionalExchangeCommand_Label;
	public static String AddExchangeCategoryToPhysicalLinkCommand_Label;
	public static String AddExchangeItemToComponentExchangeCommand_Label;
	public static String AddExchangeItemToFunctionExchange_Label;
	public static String AddExchangeItemToFunctionPort_Label;
	public static String AddFunctionalExchangeToComponentExchangeCommand_Label;
	public static String AddModeStateToCapabilityCommand_Label;
	public static String AddModeStateToFunctionCommand_Label;
	
	public static String CreateAssociationCommand_Label;
	public static String CreateAssociationCommand_CreatedName;
	
	public static String CreateComponentExchangeAllocation_Label;
	public static String CreateExchangeItemAllocationCommand_Label;
	public static String CreateFunctionalAllocationCommand_Label;
	public static String CreateFunctionalExchangeAllocation_Label;
	public static String CreateGeneralizationsCommand_Label;
	public static String CreateRealizationLinksCommand_Label;
	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
