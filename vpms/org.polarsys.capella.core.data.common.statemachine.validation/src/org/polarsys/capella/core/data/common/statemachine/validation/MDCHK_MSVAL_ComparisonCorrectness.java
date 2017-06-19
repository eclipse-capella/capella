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
import org.polarsys.capella.vp.ms.Comparison;
import org.polarsys.capella.vp.ms.Result;
import org.polarsys.capella.vp.ms.Situation;
// 61
public class MDCHK_MSVAL_ComparisonCorrectness extends AbstractModelConstraint {
  /*
   * (non-Javadoc)
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    if (eObj instanceof Comparison) {
      Comparison comparison = (Comparison)eObj;
      // check that only two config refered for comparison, or one config and one situation
      if  (((comparison.getConfiguration2().size() == 1)&&(comparison.getSituation().size() == 1)) // error in this case
        ||(comparison.getConfiguration1().size() > 1) // only one configuration as configuration 1
        ||(comparison.getConfiguration2().size() > 1) // only one configuration as configuration 2
        ||(comparison.getSituation().size() > 1))      // only one situation
      {
        return ctx.createFailureStatus(comparison.getName());
      }
      // in case of comparison configuration vs situation, a result element that refers to this situation must exist
      if((comparison.getConfiguration1().size() == 1)&&(comparison.getConfiguration2().size() == 0)&&(comparison.getSituation().size() == 1))
      {
        Boolean result_found = false;
             /*
        // try to find a result element that refers to the situation used for comparison
        BlockArchitecture archi = BlockArchitectureExt.getRootBlockArchitecture(s);
        TreeIterator<EObject> it  = archi.eAllContents();
        while(it.hasNext())
        {
          System.out.println(result.getName() + " ---------------");
          EObject eo = it.next();
          if(eo instanceof Result)
          {
            System.out.println(result.getName() + " ------------------  found result = =");
            if (((Result)eo).getSituation().equals(s)){
              result = (Result)eo;
              System.out.println(result.getName() + " ------------------  found correct result = =");
              result_found = true;
            }
          }
        }
    */
        if(!result_found) {
          return ctx.createFailureStatus(comparison.getName());
        }
      }
      return ctx.createSuccessStatus();
    }
    return null;
  }
}
