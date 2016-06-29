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

import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.ui.viewers.CategoryManager;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory;

public class MergeCategoryManager extends CategoryManager {

  public MergeCategoryManager(EMFDiffNode node) {
    super(node);
  }

  public boolean isFiltered(IDifference difference) {
    boolean focused = false;
    boolean excluded = false;

    for (IDifferenceCategory category : _activeCategories) {
      if (category.isInFocusMode()) {
        focused = focused || category.covers(difference, _node);
      } else {
        excluded = excluded || category.covers(difference, _node);
      }
    }
    if (excluded)
      return true;

    if (focused)
      return false;

    return true;
  }
}
