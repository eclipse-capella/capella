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
package org.polarsys.capella.common.re.re2rpl.filters;

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
public class SuffixedElementPropagationItem extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription(IDifference difference) {
    return "The feature 'name' is not progapaged. It's suffixable but name doesn't match";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference, Role role, IContext context) {

    // This rule is only applied when :
    // - on Create/Update RPL from REC
    // - merging from REFERENCE to TARGET
    // We want to disable propagation of name in the case where element is suffixable and doesn't contains previous name.

    if (role != Role.REFERENCE) {
      return super.getDestinationRole(difference, role, context);
    }

    String value = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
    if (!(IReConstants.COMMAND__CREATE_A_REPLICA_FROM_REPLICABLE.equals(value) || IReConstants.COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE.equals(value))) {
      return super.getDestinationRole(difference, role, context);
    }

    if (difference instanceof IAttributeValuePresence) {
      IAttributeValuePresence diff = (IAttributeValuePresence) difference;
      EObject sourceElement = diff.getElementMatch().get(role);
      EObject targetElement = diff.getElementMatch().get(role == Role.REFERENCE ? Role.TARGET : Role.REFERENCE);

      if ((sourceElement != null) && (targetElement != null)) {
        // This filter only applies on the suffixable feature
        EStructuralFeature feature = AttributesHandlerHelper.getInstance(context).getSuffixableFeature(targetElement, context);
        if ((((IAttributeValuePresence) difference).getFeature() == null) || !((IAttributeValuePresence) difference).getFeature().equals(feature)) {
          return super.getDestinationRole(difference, role, context);
        }

        CatalogElement source = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
        CatalogElement target = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);

        List<CatalogElementLink> links = new ArrayList<CatalogElementLink>();
        links.addAll(source.getOwnedLinks());
        links.addAll(target.getOwnedLinks());

        CatalogElementLink link = null;
        // Retrieve link from RPL
        for (CatalogElementLink nlink : links) {
          if (targetElement.equals(nlink.getTarget())) {
            link = nlink;
          }
        }

        if ((link != null) && link.getOrigin().isSuffixed()) {
          // if (update replicableElement from replica)
          String name = (String) link.getTarget().eGet(feature);
          String originalSuffix = (String) context.get(IReConstants.ORIGINAL_SUFFIX);
          if ((originalSuffix != null) && !name.endsWith(originalSuffix)) {
            return FilterAction.NO_ACTION;
          }

        }
      }
    }

    return super.getDestinationRole(difference, role, context);
  }
}
