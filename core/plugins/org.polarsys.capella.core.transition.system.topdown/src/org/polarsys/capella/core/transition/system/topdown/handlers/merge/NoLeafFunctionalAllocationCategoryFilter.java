/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * This category hides component allocation of function if the function is decomposed in the target architecture (indeed
 * allocations of parent functions are not semantically valid)
 */
public class NoLeafFunctionalAllocationCategoryFilter extends CategoryFilter {

  public static final String ID = NoLeafFunctionalAllocationCategoryFilter.class.getCanonicalName();

  public NoLeafFunctionalAllocationCategoryFilter(IContext context) {
    super(context, ID, Messages.NoLeafFunctionalAllocationCategoryFilter,
        Messages.NoLeafFunctionalAllocationCategoryFilter_Description, null);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(false);
    setVisible(true);
    setActive(true);
  }

  @Override
  public boolean covers(IDifference difference) {

    if (difference instanceof IElementRelativeDifference) {
      IElementRelativeDifference diff = (IElementRelativeDifference) difference;

      if (diff instanceof IElementPresence && diff.getElementMatch().coversRole(Role.REFERENCE)) {
        EObject source = diff.getElementMatch().get(Role.REFERENCE);
        if (source instanceof ComponentFunctionalAllocation) {
          ComponentFunctionalAllocation allocation = (ComponentFunctionalAllocation) source;
          if (allocation.getTargetElement() != null) {
            IMatch match = diff.getComparison().getMapping().getMatchFor(allocation.getTargetElement(), Role.REFERENCE);
            AbstractFunction target = (AbstractFunction) match.get(Role.TARGET);
            if (target != null && !FunctionExt.isLeaf(target)) {
              return true;
            }
          }
        }
      }
    }

    return false;
  }

}
