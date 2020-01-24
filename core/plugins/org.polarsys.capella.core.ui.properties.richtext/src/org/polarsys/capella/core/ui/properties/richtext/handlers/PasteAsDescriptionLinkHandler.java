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
package org.polarsys.capella.core.ui.properties.richtext.handlers;

import org.polarsys.capella.core.ui.properties.richtext.clipboard.RichTextLinksClipboard;
import org.polarsys.kitalpha.richtext.common.intf.MDERichTextWidget;
import org.polarsys.kitalpha.richtext.nebula.widget.toolbar.MDERichTextToolbarItemHandler;

public class PasteAsDescriptionLinkHandler implements MDERichTextToolbarItemHandler {

  @Override
  public void execute(MDERichTextWidget widget) {
    widget.insertRawText(RichTextLinksClipboard.getInstance().getCopiedElementsLinksHtml());
  }

}
