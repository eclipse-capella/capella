/*******************************************************************************
 * Copyright (c) 2019, 2024 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.ui.properties.richtext.handlers.OpenInEditorHandler;
import org.polarsys.kitalpha.richtext.nebula.widget.MDENebulaBasedRichTextWidget;
import org.polarsys.kitalpha.richtext.nebula.widget.MDENebulaRichTextConfiguration;
import org.polarsys.kitalpha.richtext.nebula.widget.MDERichTextConstants;
import org.polarsys.kitalpha.richtext.widget.factory.MDERichTextFactory;

public class CapellaMDERichTextFactory extends MDERichTextFactory {

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

    widget.addToolbarItem(widget, CapellaMDERichTextConstants.OPEN_IN_EDITOR_KEY,
        CapellaMDERichTextConstants.OPEN_IN_EDITOR_KEY, CapellaMDERichTextConstants.OPEN_IN_EDITOR_LABEL,
        MDERichTextConstants.MDE_ENABLE_EDITING_TOOLBAR, CapellaMDERichTextConstants.OPEN_IN_EDITOR_ICON,
        new OpenInEditorHandler());

  }

}
