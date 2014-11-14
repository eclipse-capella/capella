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
package org.polarsys.capella.core.dashboard.actions.util;

import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;

/**
 * Page link adapter for a {@link FormText} widget.<br>
 * This implementation workarounds some bugs about image hyperlink.
 */
public class FormTextPageLinkAdapter extends PageLinkAdapter {
  /**
   * Constructor.
   * @param editor_p
   */
  public FormTextPageLinkAdapter(FormEditor editor_p) {
    super(editor_p);
  }

  /**
   * @see org.polarsys.capella.core.dashboard.actions.util.PageLinkAdapter#linkExited(org.eclipse.ui.forms.events.HyperlinkEvent)
   */
  @Override
  public void linkExited(HyperlinkEvent e_p) {
    super.linkExited(e_p);
    if (e_p.widget instanceof FormText) {
      final FormText control = (FormText) e_p.widget;
      // Force asynchronously a redraw to correctly reverse back the foreground color applied when entering the hyper link.
      getEditor().getEditorSite().getShell().getDisplay().asyncExec(new Runnable() {
        public void run() {
          // Precondition.
          if (!control.isDisposed()) {
            control.redraw();
          }
        }
      });
    }
  }
}
