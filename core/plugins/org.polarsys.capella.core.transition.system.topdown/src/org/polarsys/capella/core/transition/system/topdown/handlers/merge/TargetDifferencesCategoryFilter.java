/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.handlers.merge;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.viewers.categories.UnmatchedElementCategory;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * This category hides any elements from the Target Scope (show only the result of the Transition)
 */
public class TargetDifferencesCategoryFilter extends CategoryFilter {

  public TargetDifferencesCategoryFilter(IContext context) {
    super(context, Messages.TargetDifferencesCategoryFilter, Messages.TargetDifferencesCategoryFilter_Description);
    id = UnmatchedElementCategory.ID_RIGHT;
    setCategorySet(org.eclipse.emf.diffmerge.ui.Messages.AbstractComparisonViewer_CatSetTextBasic);
    image = EMFDiffMergeUIPlugin.getDefault().getImage(ImageID.INC_ADD_STAT);
    setInFocusMode(false);
    setVisible(true);
    setActive(true);
  }

  @Override
  public boolean covers(IDifference difference) {

    if (difference instanceof IElementRelativeDifference) {
      IElementRelativeDifference diff = (IElementRelativeDifference) difference;
      EObject target = diff.getElementMatch().get(Role.TARGET);

      // We hide elements presence from TARGET
      if (diff instanceof IElementPresence) {
        if (target != null) {
          return true;
        }
      }
    }

    return false;
  }

}
