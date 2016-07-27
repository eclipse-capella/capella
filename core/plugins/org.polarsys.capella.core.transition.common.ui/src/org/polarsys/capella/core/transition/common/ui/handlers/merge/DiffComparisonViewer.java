/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.ui.handlers.merge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;

public class DiffComparisonViewer extends ComparisonViewer {

  public DiffComparisonViewer(Composite parent) {
    super(parent);
  }

  public DiffComparisonViewer(Composite parent, IActionBars actionBars) {
    super(parent, actionBars);
  }

  @Override
  protected void inputChanged(final Object input, Object oldInput) {
    super.inputChanged(input, oldInput);

    // We expand all expected matches
    if (input instanceof EMFDiffNode) {
      BusyIndicator.showWhile(getShell().getDisplay(), new Runnable() {

        public void run() {
          for (IMatch o : getExpandedElements((EMFDiffNode) input)) {
            _viewerSynthesisMain.getInnerViewer().expandToLevel(o, 0);
          }
        }
      });
    }
  }

  /**
   * Return the first matches
   */
  protected Collection<IMatch> getExpandedElements(EMFDiffNode node) {
    LinkedList<IMatch> matches = new LinkedList<IMatch>();
    Collection<IMatch> result = new ArrayList<IMatch>();
    EComparison comparison = node.getActualComparison();
    matches.addAll(comparison.getContents());

    while (!matches.isEmpty()) {
      IMatch match = matches.removeFirst();
      if (match != null) {
        if (node.getCategoryManager().representAsUserDifference(match)) {
          result.add(match);
        } else {
          matches.addAll(comparison.getContentsOf(match));
        }
      }
    }
    return result;
  }

}
