/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.search.searchfor;

import java.util.Set;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

public class CheckboxFilteredTree extends FilteredTree {
  private Set<Object> checkedElements;

  public CheckboxFilteredTree(Composite parent, int style, PatternFilter filter, Set<Object> checkedElements) {
    super(parent, style, filter, true);
    this.checkedElements = checkedElements;
  }

  @Override
  protected TreeViewer doCreateTreeViewer(Composite parent, int style) {
    CheckboxTreeViewer checkboxTreeViewer = new CheckboxTreeViewer(parent, style);
    checkboxTreeViewer.setCheckStateProvider(new ICheckStateProvider() {

      @Override
      public boolean isGrayed(Object element) {
        return false;
      }

      @Override
      public boolean isChecked(Object element) {
        return checkedElements.contains(element);
      }
    });
    return checkboxTreeViewer;
  }

  public CheckboxTreeViewer getCheckboxTreeViewer() {
    return (CheckboxTreeViewer) getViewer();
  }
}
