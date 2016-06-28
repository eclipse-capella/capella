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

import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.ui.viewers.CategoryManager;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.edit.domain.EditingDomain;

public class MergeEMFDiffNode extends EMFDiffNode {

  private final CategoryManager _categoryManager;

  public MergeEMFDiffNode(EComparison comparison) {
    this(comparison, null);
  }

  public MergeEMFDiffNode(EComparison comparison, EditingDomain domain) {
    this(comparison, domain, true, true);
  }

  public MergeEMFDiffNode(EComparison comparison, EditingDomain domain, boolean isLeftEditionPossible,
      boolean isRightEditionPossible) {
    super(comparison, domain, isLeftEditionPossible, isRightEditionPossible);
    _categoryManager = new MergeCategoryManager(this);
  }

  @Override
  public CategoryManager getCategoryManager() {
    return _categoryManager;
  }

}
