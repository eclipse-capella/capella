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
package org.polarsys.capella.common.re.handlers.merge;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
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
public class AvoidMergeUnmodifiableCategoryFilter extends CategoryFilter {

  public AvoidMergeUnmodifiableCategoryFilter(IContext context) {
    super(context, Messages.AvoidMergeUnmodifiableCategoryFilter, Messages.AvoidMergeUnmodifiableCategoryFilter_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(false);
    setActive(true);
    setVisible(false);
  }

  @Override
  public boolean covers(IDifference difference) {
    if (difference instanceof IElementPresence) {
      IElementRelativeDifference diff = (IElementRelativeDifference) difference;
      EObject source = diff.getElementMatch().get(Role.REFERENCE);
      if (source != null) {
        if (ReplicableElementHandlerHelper.getInstance(context).isUnmodifiableElement(source, context)) {
          return true;
        }
      }
      EObject target = diff.getElementMatch().get(Role.TARGET);
      if (target != null) {
        if (ReplicableElementHandlerHelper.getInstance(context).isUnmodifiableElement(target, context)) {
          return true;
        }
      }
    }
    return false;
  }
}
