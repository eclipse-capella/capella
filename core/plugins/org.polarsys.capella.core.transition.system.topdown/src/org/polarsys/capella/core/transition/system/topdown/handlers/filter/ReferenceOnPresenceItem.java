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
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;

import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A filter for the ReferenceValuePresence when associated to a PresenceElement
 */
public class ReferenceOnPresenceItem extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMergeable(IDifference difference_p, Role role_p, IContext context_p) {
    if (difference_p instanceof IReferenceValuePresence) {
      IReferenceValuePresence ref = (IReferenceValuePresence) difference_p;

      for (IMergeableDifference difm : (ref.getImpliesDependencies(role_p))) {
        if (difm instanceof IElementPresence) {
          return false;
        }
      }
    }
    return true;
  }
}
