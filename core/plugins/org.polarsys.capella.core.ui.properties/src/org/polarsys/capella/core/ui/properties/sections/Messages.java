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
package org.polarsys.capella.core.ui.properties.sections;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.ui.properties.sections.messages"; //$NON-NLS-1$

  public static String UndefinedValue;
  public static String RepresentationSection_Name_Title;
  public static String RepresentationSection_SetCommand_Representation_Name_Label;
  public static String RepresentationSection_Command_Representation_Publication_Label;
  public static String VisibleInDocGroup_Label;
  public static String VisibleInLMGroup_Label;
  public static String ProgressStatus_Label;
  public static String AppliedPropertyValues_Label;
  public static String AppliedPropertyValueGroups_Label;
  public static String ContextualElements_Label;
  public static String Name_ColumnViewer_Label;
  public static String Value_ColumnViewer_Label;
  public static String Summary_ColumnViewer_Label;
  public static String ReviewGroup_Label;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Avoid to instantiate.
  }
}
