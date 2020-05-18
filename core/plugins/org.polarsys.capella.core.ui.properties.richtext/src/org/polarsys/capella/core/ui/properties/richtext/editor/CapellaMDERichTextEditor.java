/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.richtext.editor;

import org.polarsys.capella.core.ui.properties.richtext.CapellaMDERichTextFactory;
import org.polarsys.kitalpha.richtext.widget.editor.MDERichTextEditor;
import org.polarsys.kitalpha.richtext.widget.factory.MDERichTextFactory;

public class CapellaMDERichTextEditor extends MDERichTextEditor {

  public static final String ID = "org.polarsys.capella.core.ui.properties.richtext.editor";
  
  @Override
  protected MDERichTextFactory getRichTextFactory() {
    return new CapellaMDERichTextFactory();
  }
}
