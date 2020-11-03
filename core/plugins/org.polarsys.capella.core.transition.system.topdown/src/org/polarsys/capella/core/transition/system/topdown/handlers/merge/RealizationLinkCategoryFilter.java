/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EElementPresence;
import org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
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
  public void setDependencies(IMergeableDifference<EObject> difference) {
    super.setDependencies(difference);

    if (difference instanceof EElementPresence) {
      EElementPresence presence = (EElementPresence) difference;

      EComparison comparison = (EComparison) context.get(ITransitionConstants.MERGE_COMPARISON);
      EObject target = presence.getElementMatch().get(Role.REFERENCE);
      if (target instanceof AbstractTrace) {
        EMatch match = comparison.getMapping().getMatchFor(((AbstractTrace) target).getSourceElement(), Role.REFERENCE);
        EElementPresence matchPresence = (EElementPresence) match.getElementPresenceDifference();
        if (matchPresence != null) {
          ((IMergeableDifference.Editable) matchPresence).markRequires(presence, Role.TARGET);
        }
      }
    }

  }

  @Override
  public boolean covers(IDifference<EObject> difference) {

    if (difference instanceof EElementRelativePresence) {
      EElementRelativePresence diff = (EElementRelativePresence) difference;

      EObject target = diff.getElementMatch().get(Role.TARGET);
      if (isTrace(target, context)) {
        return true;
      }
      target = diff.getElementMatch().get(Role.REFERENCE);
      if (isTrace(target, context)) {
        return true;
      }

      if (difference instanceof EReferenceValuePresence) {
        EReferenceValuePresence ref = (EReferenceValuePresence) difference;
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
