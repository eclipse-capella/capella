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
package org.polarsys.capella.core.re.handlers.filters;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class PartOwnedTypeFilter extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMergeable(IDifference difference_p, Role role_p, IContext context_p) {
    if (difference_p instanceof IElementRelativeDifference) {
      IElementRelativeDifference diff = (IElementRelativeDifference) difference_p;
      if (diff instanceof IReferenceValuePresence) {
        if (CsPackage.Literals.PART__OWNED_ABSTRACT_TYPE.equals(((IReferenceValuePresence) diff).getFeature())) {
          return false;
        }
      }
    }

    return super.isMergeable(difference_p, role_p, context_p);
  }

  @Override
  public FilterAction getDestinationRole(IDifference difference_p, Role role_p, IContext context_p) {

    if (difference_p instanceof IReferenceValuePresence) {
      if (CsPackage.Literals.PART__OWNED_ABSTRACT_TYPE.equals(((IReferenceValuePresence) difference_p).getFeature())) {
        return FilterAction.NO_ACTION;
      }
    }

    return super.getDestinationRole(difference_p, role_p, context_p);
  }

}
