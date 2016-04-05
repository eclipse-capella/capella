/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.transition.common.handlers.filter;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class UnmatchableElementFilterItem extends AbstractFilterItem {

  public boolean isUnmatchable(IDifference diff, Role role, IContext context) {

    if (diff instanceof IElementRelativeDifference) {
      IElementRelativeDifference presence = (IElementRelativeDifference) diff;
      EObject target = presence.getElementMatch().get(Role.TARGET);

      if (target != null) {
        Object id = (diff.getComparison()).getLastMatchPolicy().getMatchID(target, diff.getComparison().getScope(Role.TARGET));
        if (id.toString().startsWith("UNMATCHABLE")) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDisplayable(IDifference diff, Role role, IContext context) {
    return !isUnmatchable(diff, role, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference, Role role, IContext context) {
    if (isUnmatchable(difference, role, context)) {
      return FilterAction.TARGET;
    }
    return null;
  }

}
