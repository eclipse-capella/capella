package org.polarsys.capella.core.ui.search;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

public class CheckboxFilteredTree extends FilteredTree {
  public CheckboxFilteredTree(Composite parent, int style,
      PatternFilter filter) {
    super(parent, style, filter);
  }
  protected TreeViewer doCreateTreeViewer(Composite parent, int style) {
    return new CheckboxTreeViewer(parent, style);
  }
  public CheckboxTreeViewer getCheckboxTreeViewer() {
    return (CheckboxTreeViewer) getViewer();
  }
}