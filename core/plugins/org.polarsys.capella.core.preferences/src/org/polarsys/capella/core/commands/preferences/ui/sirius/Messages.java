/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commands.preferences.ui.sirius;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.commands.preferences.ui.sirius.messages"; //$NON-NLS-1$


  public static String DateGroupLabel;

  public static String DateFormatLabel;
  public static String DateTimeZoneLabel;
  public static String DateFormatTooltip;
  public static String DatePreviewLabel;
  public static String DateTimeZoneTooltip;
  public static String PropertyPageId;
  public static String PropertyPageTitle;
  public static String GroupLabel;
  public static String LabelPrefDisplayNavigateOnDoubleClick;
  public static String TooltipPrefDisplayNavigateOnDoubleClick;
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  /**
   * Constructor.
   */
  private Messages() {
    // Do nothing.
  }
}
