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
package org.polarsys.capella.common.re.re2rpl.filters;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * Avoid to merge unsynchronized features for RE 2 RPL
 */
public class AvoidUnsynchronizedFeatureItem extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription(IDifference difference_p) {
    String value = "attribute";
    if (difference_p instanceof IAttributeValuePresence) {
      IAttributeValuePresence diff = (IAttributeValuePresence) difference_p;
      if (diff.getFeature() != null) {
        value = diff.getFeature().getName();
      }
    }
    return NLS.bind("The feature ''{0}'' is not synchronized with its REC", value);
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
    if (difference_p instanceof IAttributeValuePresence) {
      IAttributeValuePresence diff = (IAttributeValuePresence) difference_p;
      EObject sourceElement = diff.getElementMatch().get(role_p);

      if (sourceElement != null) {

        CatalogElement source = ReplicableElementHandlerHelper.getInstance(context_p).getSource(context_p);
        CatalogElement target = ReplicableElementHandlerHelper.getInstance(context_p).getTarget(context_p);

        CatalogElementLink linkedLink = null;
        //Unmodifiable element if linked to internal replicable elements of source / target
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

          String value = (String) context_p.get(IReConstants.COMMAND__CURRENT_VALUE);

          if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)
              || IReConstants.COMMAND__UPDATE_CURRENT_REPLICA_FROM_REPLICA.equals(value) || IReConstants.COMMAND__CREATE_REPLICABLE_ELEMENT.equals(value)) {

            if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {

              //if (update replicableElement from replica)
              if (role_p == Role.REFERENCE) {
                linkedLink = linkedLink.getOrigin();
              }

            }
          } else if (IReConstants.COMMAND__CREATE_A_REPLICA_FROM_REPLICABLE.equals(value)
                     || IReConstants.COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE.equals(value)) {
            //if (update replica from replicableElement)
            if (role_p == Role.TARGET) {
              linkedLink = linkedLink.getOrigin();
            }
          }

          if (linkedLink.getUnsynchronizedFeatures().contains(diff.getFeature().getName())) {
            return FilterAction.NO_ACTION;
          }
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

    return getDestinationRole(difference_p, role_p, context_p) != FilterAction.NO_ACTION;
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
