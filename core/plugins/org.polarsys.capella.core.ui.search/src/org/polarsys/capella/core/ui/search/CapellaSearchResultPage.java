/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;

public class CapellaSearchResultPage extends AbstractTextSearchViewPage {

  public static final int FLAG_LAYOUT_CUSTOM = 3;
  public CapellaSearchResultPage() {
    super(FLAG_LAYOUT_CUSTOM);
  }

  @Override
  protected void elementsChanged(Object[] objects) {
    getViewer().refresh();
  }

  @Override
  protected void clear() {
    getViewer().refresh();
  }

  @Override
  protected void configureTreeViewer(TreeViewer viewer) {
    viewer.setContentProvider(new CapellaSearchResultTreeContentProvider(this));
    viewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new CapellaSearchResultLabelProvider(this)));
  }

  @Override
  protected void configureTableViewer(TableViewer viewer) {
    viewer.setContentProvider(new CapellaSearchResultListContentProvider(this));
    viewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new CapellaSearchResultLabelProvider(this)));
  }
  
  @Override
  public CapellaSearchResult getInput() {
    return (CapellaSearchResult) super.getInput();
  }

  public void setSelection(ISelection selection) {
    getViewer().setSelection(selection, true);
  }

  public ISelection getSelection() {
    return getViewer().getSelection();
  }
}
