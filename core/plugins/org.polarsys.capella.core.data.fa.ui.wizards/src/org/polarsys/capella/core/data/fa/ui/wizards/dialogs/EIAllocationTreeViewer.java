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
package org.polarsys.capella.core.data.fa.ui.wizards.dialogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

import org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer;

/**
 */
public class EIAllocationTreeViewer extends FieldsViewer {

  protected MyTreeViewer _treeViewer;
  protected Group _group;

  protected class MyTreeViewer extends TreeViewer {
    public MyTreeViewer(Composite parent_p, int style_p) {
      super(parent_p, style_p);
    }
    public Widget findMyItem(Object element_p) {
      return findItem(element_p);
    }
    public Widget[] findMyItems(Object element_p) {
      return findItems(element_p);
    }
  }

  /**
   * @param parent_p
   */
  public EIAllocationTreeViewer(Composite parent_p) {
    super(parent_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#createControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void createControl(Composite parent_p) {
    super.createControl(parent_p);
    parent_p.setLayout(new FillLayout());
    _group = new Group(parent_p, SWT.CENTER | SWT.SHADOW_NONE);

    GridLayout layout = new GridLayout();
    layout.marginHeight = 5;
    layout.marginWidth = 5;
    _treeViewer = new MyTreeViewer(_group, SWT.MULTI/* | SWT.VIRTUAL*/ | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

    _group.setLayout(layout);

    _treeViewer.setAutoExpandLevel(2);
    _treeViewer.getControl().setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
  }

  /**
   *
   */
  public boolean contains(Object elt) {
    return (null != _treeViewer.findMyItem(elt));
  }

  /**
   *
   */
  public Widget findItem(Object elt) {
    return _treeViewer.findMyItem(elt);
  }

  /**
   *
   */
  public Widget[] findItems(Object elt) {
    return _treeViewer.findMyItems(elt);
  }

  /**
   *
   */
  public List<Object> getOwnerData(Object data_p) {
    List<Object> result = new ArrayList<Object>();
    for (Widget widget : findItems(data_p)) {
      if (widget instanceof TreeItem) {
        TreeItem parent = ((TreeItem) widget).getParentItem();
        if (null != parent) {
          Object parentData = parent.getData();
          if ((null != parentData) && !result.contains(parentData)) {
            result.add(parentData);
          }
        }
      }
    }
    return result;
  }

  /**
   *
   */
  public static Map<Object, Object> getSelectionMap(TreeViewer treeViewer_p) {
    Map<Object, Object> selectionMap = new HashMap<Object, Object>();
    for (TreeItem treeItem : treeViewer_p.getTree().getSelection()) {
      TreeItem parentItem = treeItem.getParentItem();
      if (null != parentItem) {
        Object obj = parentItem.getData();
        if (null != obj) {
          selectionMap.put(treeItem.getData(), obj);
        }
      }
    }
    return selectionMap;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#getInput()
   */
  @Override
  public Object getInput() {
    return null;
  }

  /**
   * @return the TreeViewer
   */
  public TreeViewer getTreeViewer() {
    return _treeViewer;
  }

  /**
   * @return the TreeViewer
   */
  public void setGroupLabel(String label_p) {
    _group.setText(label_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#setInput(java.lang.Object)
   */
  @Override
  public void setInput(Object input_p) {
    _treeViewer.setInput(input_p);
  }
}
