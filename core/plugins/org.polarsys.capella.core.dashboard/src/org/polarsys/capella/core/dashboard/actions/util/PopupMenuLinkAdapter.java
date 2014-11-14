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

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;

import org.polarsys.capella.core.dashboard.internal.QuickMenuCreator;

/**
 * An {@link HyperlinkAdapter} implementation that displays a popup menu on link activation.
 */
public abstract class PopupMenuLinkAdapter extends HyperlinkAdapter {

  /**
   * @see org.eclipse.ui.forms.events.HyperlinkAdapter#linkActivated(org.eclipse.ui.forms.events.HyperlinkEvent)
   */
  @Override
  final public void linkActivated(HyperlinkEvent event_p) {
    // Create a context menu.
    QuickMenuCreator menuCreator = new QuickMenuCreator() {
      @Override
      protected void fillMenu(IMenuManager menuManager_p) {
        fillPopupMenu(menuManager_p);
      }
    };
    // Create and open the menu.
    menuCreator.createMenu();
  }

  /**
   * Fill the popup menu.
   * @param menuManager_p
   */
  protected abstract void fillPopupMenu(IMenuManager menuManager_p);
}
