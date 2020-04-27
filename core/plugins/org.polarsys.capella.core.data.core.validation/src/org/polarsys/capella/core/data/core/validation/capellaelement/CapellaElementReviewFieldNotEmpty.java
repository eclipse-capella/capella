/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.capellaelement;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Review field should be empty
 */
public class CapellaElementReviewFieldNotEmpty extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    if (eObj instanceof CapellaElement) {
      CapellaElement capellaElement = (CapellaElement) eObj;
      String review = capellaElement.getReview();
      if (null != review) {
        if (!review.trim().equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
          return ctx.createFailureStatus("Review field is not empty"); //$NON-NLS-1$
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
