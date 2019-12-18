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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;

public class CapellaSearchResultPage extends AbstractTextSearchViewPage {

  public static final int FLAG_LAYOUT_CUSTOM = 3; // 11 - allows both the display as line 01 and tree 10

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
    ViewerComparator capellaSearchResultComparator = new ViewerComparator() {
      @Override
      public int compare(Viewer viewer, Object e1, Object e2) {
        if (e1 instanceof CapellaSearchMatch && e2 instanceof CapellaSearchMatch) {
          CapellaSearchMatch m1 = (CapellaSearchMatch) e1;
          CapellaSearchMatch m2 = (CapellaSearchMatch) e2;

          int compareSearchField = m1.getSearchField().getLabel().compareToIgnoreCase(m2.getSearchField().getLabel());
          if (compareSearchField == 0) {
            // order line number increasingly
            return m1.getLineNumber() - m2.getLineNumber();
          }
          return compareSearchField;
        }

        return super.compare(viewer, e1, e2);
      }
    };
    viewer.setComparator(capellaSearchResultComparator);
    viewer.setContentProvider(new CapellaSearchResultTreeContentProvider(this));
    viewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new CapellaSearchResultLabelProvider(this)));
  }

  @Override
  public CapellaSearchResult getInput() {
    return (CapellaSearchResult) super.getInput();
  }

  @Override
  protected void configureTableViewer(TableViewer viewer) {
    ViewerComparator capellaSearchResultComparator = new ViewerComparator() {
      @Override
      public int compare(Viewer viewer, Object e1, Object e2) {
        if (e1 instanceof CapellaSearchMatch && e2 instanceof CapellaSearchMatch) {
          CapellaSearchMatch m1 = (CapellaSearchMatch) e1;
          CapellaSearchMatch m2 = (CapellaSearchMatch) e2;

          int compareSearchField = m1.getSearchField().getLabel().compareToIgnoreCase(m2.getSearchField().getLabel());
          if (compareSearchField == 0) {
            // order line number increasingly
            return m1.getLineNumber() - m2.getLineNumber();
          }
          return compareSearchField;
        }

        return super.compare(viewer, e1, e2);
      }
    };
    viewer.setComparator(capellaSearchResultComparator);
    viewer.setContentProvider(new CapellaSearchResultListContentProvider(this));
    viewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new CapellaSearchResultLabelProvider(this)));
  }

  @Override
  public void internalRemoveSelected() {
    CapellaSearchResult capellaSearchResult = getInput();
    if (capellaSearchResult == null)
      return;
    StructuredViewer viewer = getViewer();
    IStructuredSelection selection = viewer.getStructuredSelection();

    Set<CapellaSearchMatch> selectedMatches = new HashSet<>();
    for (Iterator<?> iterator = selection.iterator(); iterator.hasNext();) {
      Object selectedElement = iterator.next();
      selectedMatches.add((CapellaSearchMatch) selectedElement);
      if (selectedElement instanceof CapellaSearchMatch) {
        selectedMatches.add((CapellaSearchMatch) selectedElement);
      } else if (selectedElement instanceof IProject) {
        selectedMatches.addAll(capellaSearchResult.getDisplayedMatches(selectedElement));
      } else {
        selectedMatches.addAll(capellaSearchResult.getDisplayedMatches(selectedElement));
      }
    }

    CapellaSearchMatch[] toRemoveMatches = new CapellaSearchMatch[selectedMatches.size()];
    selectedMatches.toArray(toRemoveMatches);
    capellaSearchResult.removeMatches(toRemoveMatches);
  }

  public void setSelection(ISelection selection) {
    getViewer().setSelection(selection, true);
  }

  public ISelection getSelection() {
    return getViewer().getSelection();
  }
}
