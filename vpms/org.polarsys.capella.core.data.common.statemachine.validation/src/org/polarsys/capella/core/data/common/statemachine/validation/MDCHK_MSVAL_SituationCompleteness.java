/*******************************************************************************
 * Copyright (c) 2017 ALTRAN.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Altran - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.common.statemachine.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacommon.ChoicePseudoState;
import org.polarsys.capella.vp.ms.Comparison;
import org.polarsys.capella.vp.ms.Situation;

public class MDCHK_MSVAL_SituationCompleteness extends AbstractModelConstraint {
  /*
   * (non-Javadoc)
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    if (eObj instanceof Situation) {
      Situation situation = (Situation)eObj;
      // check that the comparison elements have references to two configuration elements, or one config and one situation
      if (situation.getExpression() == null)
      {
        return ctx.createFailureStatus(situation.getName());
      }
      else
        return ctx.createSuccessStatus();
    }
    return null;
  }
}
