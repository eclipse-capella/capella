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
package org.polarsys.capella.core.ui.search.searchfor;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

public class CheckboxFilteredTree extends FilteredTree {
  public CheckboxFilteredTree(Composite parent, int style,
      PatternFilter filter) {
    super(parent, style, filter, true);
  }
  protected TreeViewer doCreateTreeViewer(Composite parent, int style) {
    return new CheckboxTreeViewer(parent, style);
  }
  public CheckboxTreeViewer getCheckboxTreeViewer() {
    return (CheckboxTreeViewer) getViewer();
  }
}
