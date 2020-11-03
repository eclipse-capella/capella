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
package org.polarsys.capella.core.data.fa.validation.functionalExchange;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 *This check insures that a Functional Exchange is realized by at least one Functional Exchange.
 */
public class MDCHK_FunctionalExchange_Realization_1 extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    if (eObj instanceof FunctionalExchange) {
      FunctionalExchange functionalExchagne  = (FunctionalExchange) eObj;
      EList<AbstractTrace> incomingTraces = functionalExchagne.getIncomingTraces();
      for (AbstractTrace trace : incomingTraces) {
        TraceableElement sourceElement = trace.getSourceElement();
        if (trace instanceof FunctionalExchangeRealization && sourceElement instanceof FunctionalExchange) {
          return ctx.createSuccessStatus();
        }
      }
      // get more info for warning/err message
      String currentArch = ICommonConstants.EMPTY_STRING;
      String currentNext = ICommonConstants.EMPTY_STRING;
      BlockArchitecture rootBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(functionalExchagne);
      if (null != rootBlockArchitecture) 
      {
        if(rootBlockArchitecture instanceof PhysicalArchitecture)
        {
          return ctx.createSuccessStatus();
        }
        currentArch = rootBlockArchitecture.getName();
        EList<BlockArchitecture> allocatingArchitectures = rootBlockArchitecture.getAllocatingArchitectures();
        Iterator<BlockArchitecture> iterator = allocatingArchitectures.iterator();
        StringBuilder currentNextSB = new StringBuilder();
        while (iterator.hasNext()) {
          BlockArchitecture blockArchitecture = iterator.next();
          currentNextSB.append(blockArchitecture.getName());
          if (iterator.hasNext()) {
            currentNextSB.append(", ");  //$NON-NLS-1$
          }
        }
        currentNext = currentNextSB.toString();
      }
      
      // create failure status
      return ctx.createFailureStatus(EObjectLabelProviderHelper.getText(functionalExchagne), currentArch, currentNext);
      
    }

    return ctx.createSuccessStatus();
  }

}
