/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.semantic.browser.view;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryImpl;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.CategoryWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.model.ISemanticBrowserModel;

public class TreeItemListenerForHistory implements Listener {

  protected boolean hasBeenExpanded;
  protected String browserID;
  protected ISemanticBrowserModel model;
  protected SemanticBrowserTree browser;

  public TreeItemListenerForHistory(boolean hasBeenExpanded_p, String browserID_p, ISemanticBrowserModel model_p,
      SemanticBrowserTree browser_p) {
    hasBeenExpanded = hasBeenExpanded_p;
    browserID = browserID_p;
    model = model_p;
    browser = browser_p;
  }

  @Override
  public void handleEvent(Event e) {
    if (!model.containsExpandedHistory(browserID)) {
      browser.initializeHistory();
    }
    Widget widget = e.item;
    if (widget instanceof TreeItem) {
      TreeItem item = (TreeItem) widget;
      Object data = item.getData();
      if ((data != null) && (data instanceof CategoryWrapper)) {
        CategoryImpl category = ((CategoryWrapper) data).getElement();
        model.saveExpandedState(category, browserID, hasBeenExpanded);
      }
    }
  }
}
