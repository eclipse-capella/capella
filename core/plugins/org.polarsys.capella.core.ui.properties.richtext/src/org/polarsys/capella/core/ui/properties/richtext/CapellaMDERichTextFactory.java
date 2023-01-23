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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.polarsys.capella.core.ui.properties.richtext.clipboard.RichTextLinksClipboard;
import org.polarsys.capella.core.ui.properties.richtext.handlers.OpenInEditorHandler;
import org.polarsys.capella.core.ui.properties.richtext.handlers.PasteAsDescriptionLinkHandler;
import org.polarsys.kitalpha.richtext.common.intf.MDERichTextWidget;
import org.polarsys.kitalpha.richtext.nebula.widget.MDENebulaBasedRichTextWidget;
import org.polarsys.kitalpha.richtext.nebula.widget.MDENebulaRichTextConfiguration;
import org.polarsys.kitalpha.richtext.nebula.widget.MDERichTextConstants;
import org.polarsys.kitalpha.richtext.widget.factory.MDERichTextFactory;

public class CapellaMDERichTextFactory extends MDERichTextFactory {

  @Override
  public MDERichTextWidget createMinimalRichTextWidget(Composite parent) {
    MDERichTextWidget widget = super.createMinimalRichTextWidget(parent);

    installKeyListener(widget);

    return widget;
  }

  @Override
  public MDERichTextWidget createDefaultRichTextWidget(Composite parent) {
    MDERichTextWidget widget = super.createDefaultRichTextWidget(parent);

    installKeyListener(widget);

    return widget;
  }

  @Override
  public MDERichTextWidget createEditorRichTextWidget(Composite parent) {
    MDERichTextWidget widget = super.createEditorRichTextWidget(parent);

    installKeyListener(widget);

    return widget;
  }

  @Override
  protected MDERichTextFactory initializeMDEMinimalToolbar() {
    MDERichTextFactory factory = super.initializeMDEMinimalToolbar();
    MDENebulaRichTextConfiguration configuration = (MDENebulaRichTextConfiguration) getConfiguration();

    configuration.removeToolbarItems(MDERichTextConstants.SUBSCRIPT, MDERichTextConstants.SUPERSCRIPT,
        MDERichTextConstants.MDE_OPEN_EDITOR);

    return factory;
  }

  @Override
  protected MDERichTextFactory initializeMDEDefaultToolbar(boolean addOpenInEditor) {
    MDERichTextFactory factory = super.initializeMDEDefaultToolbar(addOpenInEditor);
    MDENebulaRichTextConfiguration configuration = (MDENebulaRichTextConfiguration) getConfiguration();

    configuration.removeToolbarItems(MDERichTextConstants.SUBSCRIPT, MDERichTextConstants.SUPERSCRIPT,
        MDERichTextConstants.MDE_OPEN_EDITOR);

    return factory;
  }

  @Override
  protected void addEditorToolbarItems(MDENebulaBasedRichTextWidget widget) {
    super.addEditorToolbarItems(widget);

    widget.addToolbarItem(widget, CapellaMDERichTextConstants.PASTE_AS_HYPERLINK_KEY,
        CapellaMDERichTextConstants.PASTE_AS_HYPERLINK_KEY, CapellaMDERichTextConstants.PASTE_AS_HYPERLINK_LABEL,
        MDERichTextConstants.CLIPBOARD_TOOLBAR, CapellaMDERichTextConstants.PASTE_AS_HYPERLINK_ICON,
        new PasteAsDescriptionLinkHandler());

    widget.addToolbarItem(widget, CapellaMDERichTextConstants.OPEN_IN_EDITOR_KEY,
        CapellaMDERichTextConstants.OPEN_IN_EDITOR_KEY, CapellaMDERichTextConstants.OPEN_IN_EDITOR_LABEL,
        MDERichTextConstants.MDE_ENABLE_EDITING_TOOLBAR, CapellaMDERichTextConstants.OPEN_IN_EDITOR_ICON,
        new OpenInEditorHandler());

  }

  protected void installKeyListener(MDERichTextWidget widget) {
    KeyAdapter keyListener = new KeyAdapter() {

      private static final int SHIFT_AND_CTRL_KEY_CODE = SWT.CTRL | SWT.SHIFT;

      private static final int V_KEY_CODE = 86;

      private boolean isPasteElementLinkEvent(KeyEvent event) {
        return (((event.stateMask & SHIFT_AND_CTRL_KEY_CODE) == SHIFT_AND_CTRL_KEY_CODE)
            && event.keyCode == V_KEY_CODE);
      }

      @Override
      public void keyPressed(KeyEvent event) {

        if (isPasteElementLinkEvent(event)) {
          widget.insertRawText(RichTextLinksClipboard.getInstance().getCopiedElementsLinksHtml());
        }
      }

    };

    widget.addKeyListener(keyListener);
  }

}
