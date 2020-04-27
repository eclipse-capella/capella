/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.validation.functionalExchange;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 *
 */
public class ME01G_FunctionalExchange extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    if (eObj instanceof FunctionalExchange) {
      List<EObject> objects = RefinementLinkExt.getInvalidAttachedToBestElement(eObj, FaPackage.Literals.FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS);
      if (objects.size() > 0) {
        return ctx.createFailureStatus(EObjectLabelProviderHelper.getText(eObj), CapellaElementExt.getName(objects), BlockArchitectureExt.getRootBlockArchitecture(objects.get(0)).getName(), BlockArchitectureExt.getRootBlockArchitecture(eObj).getName());
      }
    }
    return ctx.createSuccessStatus();
  }

}
