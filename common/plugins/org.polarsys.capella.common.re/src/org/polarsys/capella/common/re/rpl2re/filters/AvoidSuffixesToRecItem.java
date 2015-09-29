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
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
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
    if (difference instanceof IAttributeValuePresence) {
      IAttributeValuePresence diff = (IAttributeValuePresence) difference;
      EObject sourceElement = diff.getElementMatch().get(role);
      if (sourceElement != null) {

        CatalogElement source = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
        CatalogElement target = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);

        List<CatalogElementLink> links = new ArrayList<CatalogElementLink>();
        links.addAll(source.getOwnedLinks());
        links.addAll(target.getOwnedLinks());

        // Unmodifiable element if linked to internal replicable elements of source / target
        for (CatalogElementLink link : links) {
          if (sourceElement.equals(link.getTarget())) {

            String value = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);

            if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {

              // if (update replicableElement from replica)
              if (role == Role.REFERENCE) {
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

    return super.getDestinationRole(difference, role, context);
  }
}
