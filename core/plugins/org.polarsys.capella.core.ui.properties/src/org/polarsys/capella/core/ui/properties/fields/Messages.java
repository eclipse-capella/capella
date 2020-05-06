/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.fields;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.ui.properties.fields.messages"; //$NON-NLS-1$

  public static String UndefinedValue;
  public static String BrowseSemanticField_DelBtn;
  public static String BrowseSemanticField_EditBtn;
  public static String BrowseSemanticField_BrowseBtn;
  public static String BrowseSemanticField_AddBtn;
  public static String BrowseSemanticField_ShortcutBtn;
  public static String ReferencesTableField_DeleteCommand_Label;
  public static String ReferencesTableField_SelectionElementDialog_Title;
  public static String BooleanValueGroup_Value_Label;
  public static String TextValueGroup_Value_Label;
  public static String TextValueGroup_ValueResetBtn_Label;
  public static String TitleBlock_Label_Label;
  public static String TitleBlock_Reference_Label;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Private constructor.
  }
}
