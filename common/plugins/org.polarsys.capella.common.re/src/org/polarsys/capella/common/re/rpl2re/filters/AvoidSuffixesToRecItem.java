/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.re.rpl2re.filters;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.attributes.AttributesHandlerHelper;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * Avoid to merge unsynchronized features for RE 2 RPL
 */
public class AvoidSuffixesToRecItem extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription(IDifference difference) {
    return "The feature 'name' is not synchronized with its REC because it contains a suffix";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference, Role role, IContext context) {

    // This rule is only applied when :
    // - Update REC from RPL
    // - merging from REFERENCE to TARGET
    // We want to disable propagation of name if is suffixed. user have to check manually the propagation of name from RPL to REC

    String value = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
    if (!IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {
      return super.getDestinationRole(difference, role, context);
    }
    if (role != Role.REFERENCE) {
      return super.getDestinationRole(difference, role, context);
    }

    if (difference instanceof IAttributeValuePresence) {
      IAttributeValuePresence diff = (IAttributeValuePresence) difference;
      EObject sourceElement = diff.getElementMatch().get(role);

      if (sourceElement != null) {
        CatalogElement source = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
        CatalogElement target = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);

        // This filter only applies on the suffixable feature
        EStructuralFeature feature = AttributesHandlerHelper.getInstance(context).getSuffixableFeature(sourceElement, context);
        if ((((IAttributeValuePresence) difference).getFeature() == null) || !((IAttributeValuePresence) difference).getFeature().equals(feature)) {
          return super.getDestinationRole(difference, role, context);
        }

        List<CatalogElementLink> links = new ArrayList<CatalogElementLink>();
        links.addAll(source.getOwnedLinks());
        links.addAll(target.getOwnedLinks());

        for (CatalogElementLink link : links) {
          if (sourceElement.equals(link.getTarget())) {

            // if (update replicableElement from replica)
            CatalogElementLink linkedLink = link.getOrigin();
            if (linkedLink.isSuffixed()) {
              return FilterAction.NO_ACTION;
            }
          }

        }
      }
    }

    return super.getDestinationRole(difference, role, context);
  }
}
