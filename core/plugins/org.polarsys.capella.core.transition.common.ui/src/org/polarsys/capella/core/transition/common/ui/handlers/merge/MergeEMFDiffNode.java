/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A EMFDiffNode with mergeAll options on left and right and a custom CategoryManager
 */
public class MergeEMFDiffNode extends EMFDiffNode {

  private final MergeCategoryManager _categoryManager;

  private boolean mergeAllOnLeft;

  private boolean mergeAllOnRight;

  public boolean isMergeAllOnLeft() {
    return mergeAllOnLeft;
  }

  public void setMergeAllOnLeft(boolean mergeAllOnLeft) {
    this.mergeAllOnLeft = mergeAllOnLeft;
  }

  public boolean isMergeAllOnRight() {
    return mergeAllOnRight;
  }

  public boolean isMergeAllEnabled(boolean onLeft) {
    // enable merge all button if the other model is editable and there are differences to merge
    if (onLeft) {
      return isMergeAllOnLeft() && isEditable(false) && !isEmpty();
    }
    return isMergeAllOnRight() && isEditable(true) && !isEmpty();
  }

  public void setMergeAllOnRight(boolean mergeAllOnRight) {
    this.mergeAllOnRight = mergeAllOnRight;
  }

  public MergeEMFDiffNode(IContext context) {
    this(context, null);
  }

  public MergeEMFDiffNode(IContext context, EditingDomain domain) {
    this(context, domain, true, true);
  }

  public MergeEMFDiffNode(IContext context, EditingDomain domain, boolean isLeftEditionPossible,
      boolean isRightEditionPossible) {
    super((EComparison) context.get(ITransitionConstants.MERGE_COMPARISON), domain, isLeftEditionPossible,
        isRightEditionPossible);
    _categoryManager = new MergeCategoryManager(this, context);
  }

  @Override
  public MergeCategoryManager getCategoryManager() {
    return _categoryManager;
  }

}
