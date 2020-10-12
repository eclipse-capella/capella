/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.re2rpl.merge;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.attributes.AttributesHandlerHelper;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;


public class SuffixedElementPropagationCategoryFilter extends CategoryFilter {

  public SuffixedElementPropagationCategoryFilter(IContext context) {
    super(context, Messages.SuffixedElementPropagationCategoryFilter, Messages.SuffixedElementPropagationCategoryFilter_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(false);
    setActive(false);
    setVisible(true);
  }

  @Override
  public boolean covers(IDifference<EObject> difference) {

    String value = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
    if (!(IReConstants.COMMAND__CREATE_A_REPLICA_FROM_REPLICABLE.equals(value) || IReConstants.COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE.equals(value))) {
      return false;
    }

    if (difference instanceof EAttributeValuePresence) {
      EAttributeValuePresence diff = (EAttributeValuePresence) difference;

      EObject sourceElement = diff.getElementMatch().get(Role.REFERENCE);
      EObject targetElement = diff.getElementMatch().get(Role.TARGET);

      if ((sourceElement != null) && (targetElement != null)) {
        // This filter only applies on the suffixable feature
        EStructuralFeature feature = AttributesHandlerHelper.getInstance(context).getSuffixableFeature(targetElement, context);
        if ((((IAttributeValuePresence) difference).getFeature() == null) || !((IAttributeValuePresence) difference).getFeature().equals(feature)) {
          return false;
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
            return true;
          }

        }
      }
    }
    return false;
  }
}
