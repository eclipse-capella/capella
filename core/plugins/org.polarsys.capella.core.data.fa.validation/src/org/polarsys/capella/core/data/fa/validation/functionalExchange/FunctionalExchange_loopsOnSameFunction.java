/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that there are no Functional Exchanges defined with input and output port on the same function
 */
public class FunctionalExchange_loopsOnSameFunction extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL && eObj instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) eObj;
      EObject source = fe.getSource();
      EObject target = fe.getTarget();

      if (source instanceof FunctionPort && target instanceof FunctionPort) {
        // get the AbstractFunction
        source = source.eContainer();
        target = target.eContainer();
      }

      String elementType = "(Functional Exchange)";

      if (BlockArchitectureExt.getRootBlockArchitecture(target) instanceof OperationalAnalysis) {
        elementType = "(Interaction)";
      }

      if (source.equals(target)) {
        return ctx.createFailureStatus(EObjectLabelProviderHelper.getText(fe), elementType,
            EObjectLabelProviderHelper.getText(source),
            EObjectLabelProviderHelper.getMetaclassLabel(eObj.eContainer(), true));
      }
    }

    return ctx.createSuccessStatus();
  }
}
