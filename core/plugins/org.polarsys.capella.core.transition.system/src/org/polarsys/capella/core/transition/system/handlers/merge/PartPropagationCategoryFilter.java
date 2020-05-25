/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.handlers.merge;

import static org.polarsys.capella.core.data.helpers.cache.ModelCache.getCache;

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.capella.core.transition.common.merge.ExtendedComparison;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class PartPropagationCategoryFilter extends CategoryFilter {

  public PartPropagationCategoryFilter(IContext context) {
    super(context, Messages.PartPropagationCategoryFilter, Messages.PartPropagationCategoryFilter_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(true);
    setVisible(false);
    setActive(false);
  }

  @Override
  public void setDependencies(IMergeableDifference difference) {
    super.setDependencies(difference);

    if (difference instanceof IElementPresence) {
      IElementPresence presence = (IElementPresence) difference;

      ExtendedComparison comparison = (ExtendedComparison) context.get(ITransitionConstants.MERGE_COMPARISON);
      EObject target = presence.getElementMatch().get(Role.REFERENCE);
      if (target instanceof Component) {
        for (Part part : getCache(ComponentExt::getRepresentingParts, (Component) target)) {
          IMatch match = comparison.getMapping().getMatchFor(part, Role.REFERENCE);
          if (match != null) {
            IElementPresence matchPresence = match.getElementPresenceDifference();
            if (matchPresence != null) {
              ((IMergeableDifference.Editable) matchPresence).markRequires(presence, Role.TARGET);
              ((IMergeableDifference.Editable) presence).markRequires(matchPresence, Role.TARGET);
            }
          }
        }
      }
    }
  }

  @Override
  public boolean covers(IDifference difference) {
    if (difference instanceof IElementPresence) {
      EObject target = ((IElementPresence) difference).getElementMatch().get(Role.REFERENCE);
      if (target instanceof Component || target instanceof Part) {
        return true;
      }
    }
    return false;
  }

}
