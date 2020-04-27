/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.toolkit.widgets.filter;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.progress.WorkbenchJob;

public class CapellaFilteredTree extends FilteredTree {
  int levelOfExpandByDefault = AbstractTreeViewer.ALL_LEVELS;

  public CapellaFilteredTree(Composite parent, int treeStyle, CapellaPatternFilter filter) {
    super(parent, treeStyle, filter, true);
  }

  @Override
  protected WorkbenchJob doCreateRefreshJob() {
    WorkbenchJob refreshJob = super.doCreateRefreshJob();
    CapellaFilteredTree tree = this;
    refreshJob.addJobChangeListener(new IJobChangeListener() {

      ISelection selectionBeforeRefresh;

      @Override
      public void sleeping(IJobChangeEvent event) {
        // Nothing needs to be handled
      }

      @Override
      public void scheduled(IJobChangeEvent event) {
        // Just after the job is scheduled, we keep track the current selection of the tree
        selectionBeforeRefresh = tree.getViewer().getSelection();
      }

      @Override
      public void running(IJobChangeEvent event) {
        // Nothing needs to be handled
      }

      @Override
      public void done(IJobChangeEvent event) {
        if (tree.getFilterString().isEmpty()) {
          // If the pattern is cleared by user
          // Expand the tree to default level
          tree.getViewer().expandToLevel(tree.levelOfExpandByDefault, true);
          // And set the selection as same as before refreshing.
          // It supports the use case:
          // 1. User searches for some thing
          // 2. User finds the wanted element and select it on the tree.
          // 3. User clears the search bar
          // 4. The tree is refresh and the selected element is preserved so user can directly do something with it
          // instead of manually expanding the tree level by level to re-find the wanted element.
          if (selectionBeforeRefresh != null) {
            tree.getViewer().setSelection(selectionBeforeRefresh);
          }
        }
      }

      @Override
      public void awake(IJobChangeEvent event) {
        // Nothing needs to be handled
      }

      @Override
      public void aboutToRun(IJobChangeEvent event) {
        // Nothing needs to be handled
      }
    });
    return refreshJob;
  }

  @Override
  protected long getRefreshJobDelay() {
    // As we removed the modify listener, we schedule the job immediately after the Enter is hit.
    return 0L;
  }

  @Override
  protected void createFilterText(Composite parent) {
    super.createFilterText(parent);

    // Remove the default modify listener which triggers the view refresh on each key pressed.
    Listener[] modifyListeners = filterText.getListeners(SWT.Modify);
    for (int i = 0; i < modifyListeners.length; i++) {
      filterText.removeListener(SWT.Modify, modifyListeners[i]);
    }

    // Remove the default "ENTER" listener which set the tree selection to the first matched element
    Listener[] traverseListeners = filterText.getListeners(SWT.Traverse);
    for (int i = 0; i < traverseListeners.length; i++) {
      filterText.removeListener(SWT.Traverse, traverseListeners[i]);
    }

    // Add our own "ENTER" listener which will filter the tree based on pattern text
    // Use the KeyListener instead of Traverse in order to support RCPTT test
    filterText.addKeyListener(new KeyListener() {
      
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.keyCode == SWT.CR ||e.keyCode == SWT.KEYPAD_CR) {
          textChanged();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        
      }
    });
  }

  public void setLevelOfExpandByDefault(int levelOfExpandByDefault) {
    this.levelOfExpandByDefault = levelOfExpandByDefault;
  }

  public int getLevelOfExpandByDefault() {
    return levelOfExpandByDefault;
  }

  public void refresh() {
    // Only refresh if the filter string is not empty
    if (!getFilterString().isEmpty()) {
      textChanged();
    }
  }

  @Override
  public void setInitialText(String text) {
    super.setInitialText(Messages.CapellaFilteredTree_FILTER_TEXT_PLACEHOLDER);
  }

  public void setCaseSensitiveEnabled(boolean isSearchCaseSensitiveEnabled) {
    ((CapellaPatternFilter) getPatternFilter()).setCaseSensitiveEnabled(isSearchCaseSensitiveEnabled);
  }
}