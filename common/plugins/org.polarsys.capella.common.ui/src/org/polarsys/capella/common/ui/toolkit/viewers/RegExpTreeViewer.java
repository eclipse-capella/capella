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

package org.polarsys.capella.common.ui.toolkit.viewers;

import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.ui.toolkit.widgets.filter.CapellaFilteredTree;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.CapellaPatternFilter;

public class RegExpTreeViewer extends AbstractRegExpViewer {

  /**
   * Constructor.
   * @param parent
   */
  public RegExpTreeViewer(Composite parent) {
    super(parent);
  }

  /**
   * Constructor.
   * @param parent
   * @param isMultipleSelection
   */
  public RegExpTreeViewer(Composite parent, boolean isMultipleSelection) {
    super(parent, isMultipleSelection);
  }

  /**
   * Constructor.
   * @param parent
   * @param isMultipleSelection
   * @param style
   */
  public RegExpTreeViewer(Composite parent, boolean isMultipleSelection, int style, int viewerExpandLevel) {
    super(parent, isMultipleSelection, style, viewerExpandLevel);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer#createFilterText(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void createFilterText(Composite parent) {
    // Do nothing as we use FilteredTree.
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer#createPatternFilter()
   */
  @Override
  protected CapellaPatternFilter createPatternFilter() {
    return new CapellaPatternFilter();
  }

  /**
   * Create a {@link FilteredTree} that auto expands all levels.
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer#doClientViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected TreeViewer doClientViewer(Composite parent) {
    parent.setLayoutData(new GridData(GridData.FILL_BOTH));
    // Create a filtered tree viewer that expands all systematically.
    CapellaFilteredTree filteredTree = new CapellaFilteredTree(parent, getTreeStyle(), getFilter());
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
  public CapellaPatternFilter getFilter() {
    return (CapellaPatternFilter) super.getFilter();
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
