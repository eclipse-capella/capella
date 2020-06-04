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
package org.polarsys.capella.core.ui.search.result;

import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.ui.search.result.providers.content.CapellaSearchResultListContentProvider;
import org.polarsys.capella.core.ui.search.result.providers.content.CapellaSearchResultTreeContentProvider;
import org.polarsys.capella.core.ui.search.result.providers.content.SearchResultContentProvider;
import org.polarsys.capella.core.ui.search.result.providers.label.CapellaSearchResultLabelProvider;

public class CapellaSearchResultPage extends AbstractTextSearchViewPage {

  public static final int FLAG_LAYOUT_CUSTOM = 3;

  public CapellaSearchResultPage() {
    super(FLAG_LAYOUT_CUSTOM);
  }

  @Override
  protected void elementsChanged(Object[] elements) {
    getViewerContentProvider().elementsChanged(elements);
  }

  @Override
  protected void clear() {
    getViewerContentProvider().clear();
  }

  @Override
  protected void configureTreeViewer(TreeViewer viewer) {
    viewer.setContentProvider(new CapellaSearchResultTreeContentProvider(this));
    viewer.setLabelProvider(new DecoratingLabelProvider(new CapellaSearchResultLabelProvider(),
        PlatformUI.getWorkbench().getDecoratorManager()));
  }

  @Override
  protected void configureTableViewer(TableViewer viewer) {
    viewer.setContentProvider(new CapellaSearchResultListContentProvider(this));
    viewer.setLabelProvider(new DecoratingLabelProvider(new CapellaSearchResultLabelProvider(),
        PlatformUI.getWorkbench().getDecoratorManager()));
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

  protected SearchResultContentProvider getViewerContentProvider() {
    return (SearchResultContentProvider) getViewer().getContentProvider();
  }

  /**
   * Make the viewer public.
   */
  @Override
  public StructuredViewer getViewer() {
    return super.getViewer();
  }

}
