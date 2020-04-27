/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.detachment.propertyvalue.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.polarsys.capella.detachment.propertyvalue.messages.messages"; //$NON-NLS-1$
	
	public static String Error_RegularExpressionIsNotValide;
	public static String Error_UnableToInvokeMethod;
	public static String Title_DetachmentPropertyValues;
	public static String Label_FilterTextPattern;
	public static String Label_FilterType;
	public static String Collapse_expandAll;
	public static String Collapse_CollapsedAll;
	public static String Checkbox_CheckAll;
	public static String Checkbox_UncheckAll;
	public static String Pv;
	public static String PvPath;
	public static String Title_GroupFiltering;
	public static String Filter_PropertyValue;
	public static String PVDetachmentCommand_RuntimeError;
	public static String PVDetachmentCommand_MonitorMessage;
	
	
	static {
	    // initialize resource bundle
	    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	  }

	  private Messages() {
	    // Private constructor.
	  }
}
