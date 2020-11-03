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
package org.polarsys.capella.common.re.handlers.merge;

import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class AvoidUnsynchronizedFeatureCategoryFilter extends CategoryFilter {

  public AvoidUnsynchronizedFeatureCategoryFilter(IContext context) {
    super(context, Messages.AvoidUnsynchronizedFeatureCategoryFilter, Messages.AvoidUnsynchronizedFeatureCategoryFilter_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(false);
    setActive(true);
    setVisible(true);
  }

  @Override
  public boolean covers(IDifference<EObject> difference) {
    return getDestinationRole(difference, Role.REFERENCE, context)
        || getDestinationRole(difference, Role.TARGET, context);
  }

  public boolean getDestinationRole(IDifference<EObject> difference, Role role, IContext context) {
    if (difference instanceof EAttributeValuePresence) {
      EAttributeValuePresence diff = (EAttributeValuePresence) difference;
      EObject sourceElement = diff.getElementMatch().get(role);

      if (sourceElement != null) {

        CatalogElement source = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
        CatalogElement target = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);

        CatalogElementLink linkedLink = null;
        // Unmodifiable element if linked to internal replicable elements of source / target
        for (CatalogElementLink link : source.getOwnedLinks()) {
          if (sourceElement.equals(link.getTarget())) {
            linkedLink = link;
            break;
          }
        }
        for (CatalogElementLink link : target.getOwnedLinks()) {
          if (sourceElement.equals(link.getTarget())) {
            linkedLink = link;
            break;
          }
        }

        if (linkedLink != null) {

          String value = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);

          if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)
              || IReConstants.COMMAND__UPDATE_CURRENT_REPLICA_FROM_REPLICA.equals(value)
              || IReConstants.COMMAND__CREATE_REPLICABLE_ELEMENT.equals(value)) {

            if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {

              // if (update replicableElement from replica)
              if (role == Role.REFERENCE) {
                linkedLink = linkedLink.getOrigin();
              }

            }
          } else if (IReConstants.COMMAND__CREATE_A_REPLICA_FROM_REPLICABLE.equals(value)
              || IReConstants.COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE.equals(value)) {
            // if (update replica from replicableElement)
            if (role == Role.TARGET) {
              linkedLink = linkedLink.getOrigin();
            }
          }

          if (linkedLink.getUnsynchronizedFeatures().contains(diff.getFeature().getName())) {
            return true;
          }
        }
      }
    }
    return false;
  }

}
