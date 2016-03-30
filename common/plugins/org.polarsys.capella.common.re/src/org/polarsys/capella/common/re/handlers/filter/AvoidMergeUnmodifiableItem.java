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
  public boolean isReadOnly(IDifference diff, Role role, IContext context) {

    return super.isReadOnly(diff, role, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMergeable(IDifference difference, Role role, IContext context) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference, Role role, IContext context) {
    if (difference instanceof IElementPresence) {
      IElementRelativeDifference diff = (IElementRelativeDifference) difference;
      EObject source = diff.getElementMatch().get(role);

      if (source != null) {
        if (ReplicableElementHandlerHelper.getInstance(context).isUnmodifiableElement(source, context)) {
          return FilterAction.NO_ACTION;
        }
      }
    }
    return super.getDestinationRole(difference, role, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDisplayable(IDifference difference, Role role, IContext context) {
    LinkedList<IDifference> toVisit = new LinkedList<IDifference>();
    HashSet<IDifference> visited = new HashSet<IDifference>();

    toVisit.add(difference);
    while (!toVisit.isEmpty()) {
      IDifference diff = toVisit.removeFirst();
      if (!visited.contains(diff)) {
        visited.add(diff);

        if (diff instanceof IElementPresence) {
          IElementRelativeDifference relativeDiff = (IElementRelativeDifference) diff;
          EObject source = relativeDiff.getElementMatch().get(role);
          if (source != null) {
            if (ReplicableElementHandlerHelper.getInstance(context).isUnmodifiableElement(source, context)) {
              return false;
            }
          }
        }

        //If the given difference requires a not-visible dependencies (UnmodifiableElement), it becomes not-visible too! (to avoid some unwanted merges)
        if (diff instanceof IMergeableDifference) {
          toVisit.addAll(((IMergeableDifference) diff).getDirectRequiresDependencies(Role.TARGET)); //or role?
        }
      }
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isApplicable(EClass differenceClass) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMergeable(EStructuralFeature feature, IContext context) {
    return true;
  }

}
