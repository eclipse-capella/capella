/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.topdown.handlers.filter;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 * Hide differences on RealizationLink
 *
 */
public class RealizationLinkFilterItem extends AbstractFilterItem {

  /**
   * @param target_p
   * @return
   */
  private boolean isTrace(EObject element_p, IContext context_p) {
    return TopDownTransformationHelper.getInstance(context_p).isTrace(element_p, context_p);
  }

  @Override
  public boolean isDisplayable(IDifference difference_p, Role role_p, IContext context_p) {

    if (difference_p instanceof IElementRelativeDifference) {
      IElementRelativeDifference diff = (IElementRelativeDifference) difference_p;
      EObject target = diff.getElementMatch().get(Role.TARGET);

      if (difference_p instanceof IReferenceValuePresence) {
        if (isTrace(target, context_p)) {
          return false;
        }
        target = diff.getElementMatch().get(Role.REFERENCE);
        if (isTrace(target, context_p)) {
          return false;
        }
        IReferenceValuePresence ref = (IReferenceValuePresence) difference_p;
        target = ref.getValue().get(Role.TARGET);
        if (isTrace(target, context_p)) {
          return false;
        }
        target = ref.getValue().get(Role.REFERENCE);
        if (isTrace(target, context_p)) {
          return false;
        }
      }
    }

    return true;
  }
}
