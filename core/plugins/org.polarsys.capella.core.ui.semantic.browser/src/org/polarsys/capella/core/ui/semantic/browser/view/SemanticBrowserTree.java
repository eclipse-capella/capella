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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryImpl;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.IBrowserContentProvider;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.CategoryWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.model.ISemanticBrowserModel;

public class SemanticBrowserTree extends TreeViewer {

  protected ISemanticBrowserModel model;
  protected String browserID;

  public SemanticBrowserTree(Composite parent, ISemanticBrowserModel model_p, String browserID_p) {
    super(parent);
    browserID = browserID_p;
    model = model_p;
    updateAutoExpandLevel();
    getTree().addListener(SWT.Expand, new TreeItemListenerForHistory(true, browserID, model, this));
    getTree().addListener(SWT.Collapse, new TreeItemListenerForHistory(false, browserID, model, this));
  }

  protected void updateAutoExpandLevel() {
    if (model.doesLimitateTreeExpansion()) {
      if (browserID == IBrowserContentProvider.ID_CURRENT_CP) {
        setAutoExpandLevel(3);
      } else {
        setAutoExpandLevel(2);
      }
    } else {
      setAutoExpandLevel(ALL_LEVELS);
    }
  }

  @Override
  protected Item[] getSelection(Control widget) {
    Item[] itemlList = ((Tree) widget).getSelection();
    for (Item item : itemlList) {
      Object data = item.getData();
      if (data instanceof EObjectWrapper) {
        EObjectWrapper wrapper = (EObjectWrapper) data;
        item.setData(wrapper.getElement());
      }
    }
    return itemlList;
  }

  @Override
  protected ColumnViewerEditor createViewerEditor() {
    // Disable ColumnViewer editing support.
    return null;
  }

  // store complete history of expand state
  protected void initializeHistory() {
    model.getOrCreateHistory(browserID);// because it is possible that no save is done and table must be initialized
    for (TreeItem treeItem : getAllItems()) {
      if (!getExpanded(treeItem)) {
        Object data = treeItem.getData();
        if ((data != null) && (data instanceof CategoryWrapper)) {
          CategoryImpl category = ((CategoryWrapper) data).getElement();
          model.saveExpandedState(category, browserID, false);
        }
      }
    }
  }

  // restore the expansion state of the given input
  protected void restoreExpansionState(Object input) {
    TreeItem topItem = getTree().getTopItem();
    for (TreeItem treeItem : getAllItems()) {
      Object data = treeItem.getData();
      if (data != null) {
        if (model.doesLimitateTreeExpansion()) {
          if (data instanceof CategoryWrapper) {
            CategoryImpl category = ((CategoryWrapper) data).getElement();
            boolean expanded = model.getExpandedState(category, browserID);
            treeItem.setExpanded(expanded);
          } else if ((browserID == IBrowserContentProvider.ID_CURRENT_CP) && (treeItem == topItem)) {
            treeItem.setExpanded(true);
          } else {
            treeItem.setExpanded(false);
          }
        }
      }
    }
  }

  @Override
  protected void inputChanged(Object input, Object oldInput) {
    // before tree update, we update the expansion depth and initialize history if necessary
    updateAutoExpandLevel();
    // we initialize history for the current tree if necessary
    if (oldInput != null) {
      if (!model.containsExpandedHistory(browserID)) {
        initializeHistory();
      }
    }
    super.inputChanged(input, oldInput);
    // after tree update, we set the current input in the model and refresh expansion state from the history if defined
    if ((input != null) && model.containsExpandedHistory(browserID)) {
      restoreExpansionState(input);
    }
  }

  // return a list of all items of tree whose data is not null
  private List<TreeItem> getAllItems() {
    List<TreeItem> allItems = new ArrayList<TreeItem>();
    for (TreeItem item : getTree().getItems()) {
      if (item.getData() != null) {
        allItems.add(item);
        getAllItems(item, allItems);
      }
    }
    return allItems;
  }

  private void getAllItems(TreeItem currentItem, List<TreeItem> allItems) {
    for (TreeItem element : currentItem.getItems()) {
      if (element.getData() != null) {
        allItems.add(element);
        getAllItems(element, allItems);
      }
    }
  }

}
