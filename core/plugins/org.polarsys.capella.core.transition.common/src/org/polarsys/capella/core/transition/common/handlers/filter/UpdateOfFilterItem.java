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
package org.polarsys.capella.core.transition.common.handlers.filter;

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.diffmerge.api.diff.IPresenceDifference;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class UpdateOfFilterItem extends AbstractFilterItem {

  EClass filteredClass;

  public UpdateOfFilterItem(EClass filteredClass_p) {
    filteredClass = filteredClass_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMergeable(IDifference difference_p, Role role_p, IContext context_p) {
    if (isUpdateOf(difference_p, filteredClass)) {
      return false;
    }
    return true;
  }

  protected boolean isUpdateOf(IDifference diff_p, EClass clazz_p) {
    // any update of clazz_p is disabled
    if (diff_p instanceof IElementRelativeDifference) {
      IElementRelativeDifference presence = (IElementRelativeDifference) diff_p;
      EObject source = presence.getElementMatch().get(Role.REFERENCE);

      if (source != null) {
        if (clazz_p.isInstance(source)) {
          if ((presence instanceof IPresenceDifference) && (presence.getElementMatch().get(Role.TARGET) != null)) {
            return true;
          }
        } else if (EcoreUtil2.isContainedBy(source, clazz_p)) {
          EObject container = EcoreUtil2.getFirstContainer(source, clazz_p);
          if ((presence instanceof IPresenceDifference) && (presence.getElementMatch().get(Role.TARGET) != null)) {
            return true;
          } else if (presence instanceof IElementPresence) {
            IElementPresence element = (IElementPresence) presence;
            IMatch match = getMatch(element, container, Role.REFERENCE);
            if (match != null) {
              return match.get(Role.TARGET) != null;
            }
          }
        }
      }
    }

    return false;
  }

  protected IMatch getMatch(IElementPresence presence, EObject object, Role role_p) {
    IMatch match = presence.getOwnerMatch();
    if (match.get(role_p) == object) {
      return match;
    }
    if (match.getElementPresenceDifference() != null) {
      return getMatch(match.getElementPresenceDifference(), object, role_p);
    }
    return null;
  }

}
