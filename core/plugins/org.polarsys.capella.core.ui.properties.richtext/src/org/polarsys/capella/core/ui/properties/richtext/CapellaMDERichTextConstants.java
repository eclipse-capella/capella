/*******************************************************************************
 * Copyright (c) 2019, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.richtext;

import java.net.URL;

import org.polarsys.kitalpha.richtext.common.util.MDERichTextHelper;

public class CapellaMDERichTextConstants {

  public static final String PASTE_AS_HYPERLINK_KEY = "PasteAsHyperlink";
  
  public static final String PASTE_AS_HYPERLINK_LABEL = "Paste as Hyperlink (Ctrl + Shift + V)";
  
  public static final URL PASTE_AS_HYPERLINK_ICON = MDERichTextHelper
      .getURL(CapellaUIPropertiesRichtextPlugin.PLUGIN_ID,
          "icons/full/obj16/paste_as_hyperlink.gif");
    
  public static final String OPEN_IN_EDITOR_KEY = "OpenInEditor";
  
  public static final String OPEN_IN_EDITOR_LABEL = "Open in Editor";
  
  public static final URL OPEN_IN_EDITOR_ICON = MDERichTextHelper.getURL(CapellaUIPropertiesRichtextPlugin.PLUGIN_ID,
      "icons/full/obj16/open_in_editor.gif");
  
  
}
