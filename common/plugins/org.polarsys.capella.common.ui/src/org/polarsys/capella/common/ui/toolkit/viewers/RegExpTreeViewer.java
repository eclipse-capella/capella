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
package org.polarsys.capella.common.ui.toolkit.viewers;

import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.ui.toolkit.widgets.filter.FilteredTree;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.PatternFilter;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.TreePatternFilter;

public class RegExpTreeViewer extends AbstractRegExpViewer {

  /**
   * Constructor.
   * @param parent_p
   */
  public RegExpTreeViewer(Composite parent_p) {
    super(parent_p);
  }

  /**
   * Constructor.
   * @param parent_p
   * @param isMultipleSelection_p
   */
  public RegExpTreeViewer(Composite parent_p, boolean isMultipleSelection_p) {
    super(parent_p, isMultipleSelection_p);
  }

  /**
   * Constructor.
   * @param parent_p
   * @param isMultipleSelection_p
   * @param style_p
   */
  public RegExpTreeViewer(Composite parent_p, boolean isMultipleSelection_p, int style_p, int viewerExpandLevel_p) {
    super(parent_p, isMultipleSelection_p, style_p, viewerExpandLevel_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer#createFilterText(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void createFilterText(Composite parent_p) {
    // Do nothing as we use FilteredTree.
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer#createPatternFilter()
   */
  @Override
  protected PatternFilter createPatternFilter() {
    return new TreePatternFilter();
  }

  /**
   * Create a {@link FilteredTree} that auto expands all levels.
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer#doClientViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected TreeViewer doClientViewer(Composite parent_p) {
    parent_p.setLayoutData(new GridData(GridData.FILL_BOTH));
    // Create a filtered tree viewer that expands all systematically.
    FilteredTree filteredTree = new FilteredTree(parent_p, getTreeStyle(), getFilter()) {
      /**
       * @see org.polarsys.capella.common.ui.toolkit.widgets.filter.FilteredTree#handleTreeViewerExpansionWhenNoFilter(java.lang.Object[])
       */
      @Override
      protected void handleTreeViewerExpansionWhenNoFilter(Object[] expandedElements_p) {
        treeViewer.expandAll();
      }
    };
    TreeViewer viewer = filteredTree.getViewer();
    viewer.setAutoExpandLevel(AbstractTreeViewer.ALL_LEVELS);
    return viewer;
  }

  /**
   * Get the client viewer as a {@link TreeViewer}.
   * @return must be not <code>null</code>.
   */
  @Override
  public TreeViewer getClientViewer() {
    return (TreeViewer) super.getClientViewer();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer#getFilter()
   */
  @Override
  public TreePatternFilter getFilter() {
    return (TreePatternFilter) super.getFilter();
  }

  /**
   * Get tree style.<br>
   * Default returns <code>SWT.NONE | (super._isMultipleSelection ? SWT.MULTI : SWT.SINGLE) | SWT.BORDER</code>
   * @return the style of the {@link TreeViewer}
   */
  protected int getTreeStyle() {
    return SWT.NONE | (super._isMultipleSelection ? SWT.MULTI : SWT.SINGLE) | SWT.BORDER;
  }
}
