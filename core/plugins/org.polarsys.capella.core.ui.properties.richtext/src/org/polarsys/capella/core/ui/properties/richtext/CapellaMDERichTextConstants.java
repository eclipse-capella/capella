/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.richtext;

import java.net.URL;

import org.polarsys.kitalpha.richtext.common.util.MDERichTextHelper;

public class CapellaMDERichTextConstants {

  public static final String PASTE_AS_LINK_KEY = "PasteAsLink";
  
  public static final String PASTE_AS_LINK_LABEL = "Paste as Link (Ctrl + Shift + V)";
  
  public static final URL PASTE_AS_LINK_ICON = MDERichTextHelper.getURL(CapellaUIPropertiesRichtextPlugin.PLUGIN_ID,
      "icons/full/obj16/paste_as_link.gif");
    
  public static final String OPEN_IN_EDITOR_KEY = "OpenInEditor";
  
  public static final String OPEN_IN_EDITOR_LABEL = "Open in Editor";
  
  public static final URL OPEN_IN_EDITOR_ICON = MDERichTextHelper.getURL(CapellaUIPropertiesRichtextPlugin.PLUGIN_ID,
      "icons/full/obj16/open_in_editor.gif");
  
  
}
