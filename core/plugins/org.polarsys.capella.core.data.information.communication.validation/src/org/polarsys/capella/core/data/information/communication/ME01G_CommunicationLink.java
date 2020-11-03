/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.communication;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 *
 */
public class ME01G_CommunicationLink extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    if (eObj instanceof CommunicationLink) {
      List<EObject> objects = RefinementLinkExt.getInvalidAttachedToBestElement(eObj, CommunicationPackage.Literals.COMMUNICATION_LINK__EXCHANGE_ITEM);
      if (objects.size() > 0) {
        return ctx.createFailureStatus(new Object[] { ((AbstractNamedElement) eObj.eContainer()).getName(), CapellaElementExt.getName(objects), BlockArchitectureExt.getRootBlockArchitecture(objects.get(0)).getName(), BlockArchitectureExt.getRootBlockArchitecture(eObj).getName() });
      }
    }
    return ctx.createSuccessStatus();
  }

}
