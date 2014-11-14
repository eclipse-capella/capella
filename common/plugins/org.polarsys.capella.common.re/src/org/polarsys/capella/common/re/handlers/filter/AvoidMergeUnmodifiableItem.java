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
package org.polarsys.capella.common.re.handlers.filter;

import java.util.HashSet;
import java.util.LinkedList;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * When update/create a replicableElement from a selection/replica, we need to hide
 * all elements from internal replicableElement, BUT we need to have them into source scope to allow adding related elements.
 * 
 * That why elements are hidden if they are listed as UNMODIFIABLE_ELEMENTS. In ReScope, we don't add a traceability link to such elements.
 * 
 * isMergeable must be true on such elements, to avoid difference on related elements to be unchecked.
 * 
 */
public class AvoidMergeUnmodifiableItem extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isReadOnly(IDifference diff_p, Role role_p, IContext context_p) {

    return super.isReadOnly(diff_p, role_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMergeable(IDifference difference_p, Role role_p, IContext context_p) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference_p, Role role_p, IContext context_p) {
    if (difference_p instanceof IElementPresence) {
      IElementRelativeDifference diff = (IElementRelativeDifference) difference_p;
      EObject source = diff.getElementMatch().get(role_p);

      if (source != null) {
        if (ReplicableElementHandlerHelper.getInstance(context_p).isUnmodifiableElement(source, context_p)) {
          return FilterAction.NO_ACTION;
        }
      }
    }
    return super.getDestinationRole(difference_p, role_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDisplayable(IDifference difference_p, Role role_p, IContext context_p) {
    LinkedList<IDifference> toVisit = new LinkedList<IDifference>();
    HashSet<IDifference> visited = new HashSet<IDifference>();

    toVisit.add(difference_p);
    while (!toVisit.isEmpty()) {
      IDifference difference = toVisit.removeFirst();
      if (!visited.contains(difference)) {
        visited.add(difference);

        if (difference instanceof IElementPresence) {
          IElementRelativeDifference diff = (IElementRelativeDifference) difference;
          EObject source = diff.getElementMatch().get(role_p);
          if (source != null) {
            if (ReplicableElementHandlerHelper.getInstance(context_p).isUnmodifiableElement(source, context_p)) {
              return false;
            }
          }
        }

        //If the given difference requires a not-visible dependencies (UnmodifiableElement), it becomes not-visible too! (to avoid some unwanted merges)
        if (difference instanceof IMergeableDifference) {
          toVisit.addAll(((IMergeableDifference) difference).getDirectRequiresDependencies(Role.TARGET)); //or role_p?
        }
      }
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isApplicable(EClass differenceClass_p) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMergeable(EStructuralFeature feature_p, IContext context_p) {
    return true;
  }

}
