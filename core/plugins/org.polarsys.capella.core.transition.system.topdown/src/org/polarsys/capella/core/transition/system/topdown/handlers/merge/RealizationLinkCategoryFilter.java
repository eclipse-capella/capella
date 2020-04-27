/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.capella.core.transition.common.merge.ExtendedComparison;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * This category hides Realization Links. It also adds dependencies to IElementPresence. When we merge an element, we
 * also merge its Realization Link
 */
public class RealizationLinkCategoryFilter extends CategoryFilter {

  public static final String ID = RealizationLinkCategoryFilter.class.getCanonicalName();

  public RealizationLinkCategoryFilter(IContext context) {
    super(context, ID, Messages.RealizationLinkCategoryFilter, Messages.RealizationLinkCategoryFilter_Description, null);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
   setInFocusMode(false);
    setVisible(false);
    setActive(true);
  }

  protected boolean isTrace(EObject element, IContext context) {
    return TopDownTransformationHelper.getInstance(context).isTrace(element, context);
  }

  @Override
  public void setDependencies(IMergeableDifference difference) {
    super.setDependencies(difference);

    if (difference instanceof IElementPresence) {
      IElementPresence presence = (IElementPresence) difference;

      ExtendedComparison comparison = (ExtendedComparison) context.get(ITransitionConstants.MERGE_COMPARISON);
      EObject target = presence.getElementMatch().get(Role.REFERENCE);
      if (target instanceof AbstractTrace) {
        IMatch match = comparison.getMapping().getMatchFor(((AbstractTrace) target).getSourceElement(), Role.REFERENCE);
        IElementPresence matchPresence = match.getElementPresenceDifference();
        if (matchPresence != null) {
          ((IMergeableDifference.Editable) matchPresence).markRequires(presence, Role.TARGET);
        }
      }
    }

  }

  @Override
  public boolean covers(IDifference difference) {

    if (difference instanceof IElementRelativeDifference) {
      IElementRelativeDifference diff = (IElementRelativeDifference) difference;

      EObject target = diff.getElementMatch().get(Role.TARGET);
      if (isTrace(target, context)) {
        return true;
      }
      target = diff.getElementMatch().get(Role.REFERENCE);
      if (isTrace(target, context)) {
        return true;
      }

      if (difference instanceof IReferenceValuePresence) {
        IReferenceValuePresence ref = (IReferenceValuePresence) difference;
        target = ref.getValueMatch().get(Role.TARGET);
        if (isTrace(target, context)) {
          return true;
        }
        target = ref.getValueMatch().get(Role.REFERENCE);
        if (isTrace(target, context)) {
          return true;
        }
      }
    }

    return false;
  }

}
