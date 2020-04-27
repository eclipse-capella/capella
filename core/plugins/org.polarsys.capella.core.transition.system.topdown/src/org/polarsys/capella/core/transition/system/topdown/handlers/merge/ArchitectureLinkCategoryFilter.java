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
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * This category hides differences of RealizationLinks towards ModellingArchitectures
 */
public class ArchitectureLinkCategoryFilter extends CategoryFilter {

  public ArchitectureLinkCategoryFilter(IContext context) {
    super(context, Messages.ArchitectureLinkCategoryFilter, Messages.ArchitectureLinkCategoryFilter_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(false);
    setVisible(false);
    setActive(true);
  }

  protected boolean isTrace(EObject element, IContext context) {
    return TopDownTransformationHelper.getInstance(context).isTrace(element, context);
  }

  @Override
  public boolean covers(IDifference difference) {

    if (difference instanceof IElementRelativeDifference) {
      IElementRelativeDifference diff = (IElementRelativeDifference) difference;

      EObject target = diff.getElementMatch().get(Role.TARGET);

      if (isTrace(target, context)) {
        AbstractTrace trace = (AbstractTrace) target;
        return (trace.getSourceElement() instanceof ModellingArchitecture);
      }
      target = diff.getElementMatch().get(Role.REFERENCE);
      if (isTrace(target, context)) {
        AbstractTrace trace = (AbstractTrace) target;
        return (trace.getSourceElement() instanceof ModellingArchitecture);
      }

    }

    return false;
  }

}
