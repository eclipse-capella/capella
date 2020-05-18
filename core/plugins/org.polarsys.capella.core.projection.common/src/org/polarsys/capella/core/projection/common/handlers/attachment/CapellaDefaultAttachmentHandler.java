/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.common.handlers.attachment;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class CapellaDefaultAttachmentHandler extends DefaultAttachmentHandler {

  @Override
  protected boolean shouldUpdateAttribute(EObject sourceElement_p, EObject targetElement_p, EAttribute feature_p, Object valueSource, Object valueTarget,
      IContext context_p) {

    if (ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.equals(feature_p) || CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.equals(feature_p)
        || CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.equals(feature_p)) {
      return shouldUpdateAttributeIfEmpty(sourceElement_p, targetElement_p, feature_p, valueSource, valueTarget, context_p);
    }

    return super.shouldUpdateAttribute(sourceElement_p, targetElement_p, feature_p, valueSource, valueTarget, context_p);
  }

  protected boolean shouldUpdateAttributeIfEmpty(EObject sourceElement_p, EObject targetElement_p, EAttribute feature_p, Object valueSource,
      Object valueTarget, IContext context_p) {
    return ((valueSource != null) && (valueSource != ICommonConstants.EMPTY_STRING)
            && ((valueTarget == null) || ((valueTarget instanceof String) && (((String) valueTarget).length() == 0))) && !valueSource.equals(valueTarget));
  }

}
