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
package org.polarsys.capella.common.re.rpl2re.merge;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.Role;
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

public class AvoidSuffixesToRecCategoryFilter extends CategoryFilter {

  public AvoidSuffixesToRecCategoryFilter(IContext context) {
    super(context, Messages.AvoidSuffixesToRecCategoryFilter, Messages.AvoidSuffixesToRecCategoryFilter_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(false);
    setActive(true);
    setVisible(true);
  }

  @Override
  public boolean covers(IDifference<EObject> difference) {
    // This rule is only applied when :
    // - Update REC from RPL
    // - merging from REFERENCE to TARGET
    // We want to disable propagation of name if is suffixed. user have to check manually the propagation of name from RPL to REC
    String value = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
    if (!IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {
      return false;
    }

    if (difference instanceof EAttributeValuePresence) {
      EAttributeValuePresence diff = (EAttributeValuePresence) difference;
      EObject sourceElement = diff.getElementMatch().get(Role.REFERENCE);

      if (sourceElement != null) {
        CatalogElement source = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
        CatalogElement target = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);

        // This filter only applies on the suffixable feature
        EStructuralFeature feature = AttributesHandlerHelper.getInstance(context).getSuffixableFeature(sourceElement, context);
        if ((diff.getFeature() == null) || !(diff.getFeature().equals(feature))) {
          return false;
        }

        List<CatalogElementLink> links = new ArrayList<CatalogElementLink>();
        links.addAll(source.getOwnedLinks());
        links.addAll(target.getOwnedLinks());

        for (CatalogElementLink link : links) {
          if (sourceElement.equals(link.getTarget())) {

            // if (update replicableElement from replica)
            CatalogElementLink linkedLink = link.getOrigin();
            if (linkedLink.isSuffixed()) {
              return true;
            }
          }

        }
      }
    }
    return false;
  }
}
