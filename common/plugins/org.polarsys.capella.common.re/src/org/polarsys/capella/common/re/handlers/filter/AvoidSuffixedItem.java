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
package org.polarsys.capella.common.re.handlers.filter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * Avoid to merge unsynchronized features for RE 2 RPL
 */
public class AvoidSuffixedItem extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription(IDifference difference_p) {
    return "The feature 'name' is not synchronized with its REC because it contains a suffix";
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

        List<CatalogElementLink> links = new ArrayList<CatalogElementLink>();
        links.addAll(source.getOwnedLinks());
        links.addAll(target.getOwnedLinks());

        // Unmodifiable element if linked to internal replicable elements of source / target
        for (CatalogElementLink link : links) {
          if (sourceElement.equals(link.getTarget())) {

            String value = (String) context_p.get(IReConstants.COMMAND__CURRENT_VALUE);

            if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {

              // if (update replicableElement from replica)
              if (role_p == Role.REFERENCE) {

                CatalogElementLink linkedLink = link.getOrigin();

                if (linkedLink.isSuffixed()) {
                  return FilterAction.NO_ACTION;
                }
              }
            }
          }
        }
      }
    }

    return super.getDestinationRole(difference_p, role_p, context_p);
  }
}
