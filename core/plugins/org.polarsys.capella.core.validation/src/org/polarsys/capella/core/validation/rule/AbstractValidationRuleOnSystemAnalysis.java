/*******************************************************************************
 * Copyright (c) 2016, 2024 Thales LAS France SAS.
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
package org.polarsys.capella.core.validation.rule;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;

public abstract class AbstractValidationRuleOnSystemAnalysis extends AbstractValidationRule {

  @Override
  public final IStatus validate(final IValidationContext ctx) {

    if (belongsToSA(ctx.getTarget())) {
      return validateOnSA(ctx);
    }
    return ctx.createSuccessStatus();
  }

  protected abstract IStatus validateOnSA(final IValidationContext ctx);

  public static boolean belongsToSA(final EObject currentModelElement) {
    if (currentModelElement == null) {
      return false;
    } else if (currentModelElement instanceof SystemAnalysis) {
      return true;
    } else {
      return belongsToSA(currentModelElement.eContainer());
    }
  }

}
