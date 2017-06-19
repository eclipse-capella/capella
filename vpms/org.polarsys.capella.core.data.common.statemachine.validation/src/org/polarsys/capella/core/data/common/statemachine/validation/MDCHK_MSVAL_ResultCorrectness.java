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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.capellacommon.ChoicePseudoState;
import org.polarsys.capella.vp.ms.AndOperation;
import org.polarsys.capella.vp.ms.BooleanExpression;
import org.polarsys.capella.vp.ms.BooleanOperation;
import org.polarsys.capella.vp.ms.Comparison;
import org.polarsys.capella.vp.ms.NotOperation;
import org.polarsys.capella.vp.ms.OrOperation;
import org.polarsys.capella.vp.ms.Result;
import org.polarsys.capella.vp.ms.Situation;

import ms.configuration.services.cs.CalculatedConfiguration;
import ms.configuration.services.cs.CalculatedConfiguration.BooleanExpr;
import ms.configuration.services.cs.CalculatedConfiguration.ConfList;

// check the consistency of the boolean operation between M&S, trigger warning and error messages
// validate the construction of the situation (ex: 2 or more operand for AND oeprator, etc.)
// check if result(s) is linked to this situation and then compute a calculated configuration for the result
public class MDCHK_MSVAL_ResultCorrectness extends AbstractModelConstraint {
  /*
   * (non-Javadoc)
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
 public IStatus validate(IValidationContext ctx) {
    Collection<IStatus> objectsIrregularList = new ArrayList<IStatus>();
    ConfList conflist = null;
    ArrayList<String> irregFault = new ArrayList<String>();
    EObject eObj = ctx.getTarget();
    if (eObj instanceof Result) {
      Result result = (Result)eObj;
      for (Situation situ : result.getSituation()){
          CalculatedConfiguration cC = new CalculatedConfiguration(result); // JVS
          conflist = cC.Calculate(); // JVS
          irregFault = cC.getErrorListText();
      }
      if(irregFault.size() > 0)
      {
        for(String errorToShow : irregFault)
          objectsIrregularList.add(ctx.createFailureStatus(result.getName(),errorToShow));
          return ConstraintStatus.createMultiStatus(ctx, objectsIrregularList);
      }
      else
        return ctx.createSuccessStatus();
    }
    return ctx.createSuccessStatus();
  }
}
