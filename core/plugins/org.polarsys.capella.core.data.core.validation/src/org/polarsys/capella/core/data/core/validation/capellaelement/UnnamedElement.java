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
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.behavior.AbstractEvent;

public class UnnamedElement extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) 
  {
    // Get the Target
    EObject eObj = ctx.getTarget();
    // If the Target is a Named Element
    if(eObj instanceof NamedElement)
    {
      // If the Rule has to check the Target
      if(!( eObj instanceof AbstractEvent && !(eObj instanceof ExchangeItem)
          || eObj instanceof TimeLapse
          || eObj instanceof Association
          || (eObj instanceof Feature && eObj.eContainer() instanceof Association)
          || eObj instanceof InteractionFragment
          || eObj instanceof ExchangeItemAllocation 
          || eObj instanceof DataValue))
      {
        // Get the name of the element
        String currentElementName = ((NamedElement)eObj).getName();
        // If the name is empty or null
        if(
            null == currentElementName ||
            currentElementName.equalsIgnoreCase(ICommonConstants.EMPTY_STRING) ||
            currentElementName.equalsIgnoreCase("null")  //$NON-NLS-1$
        ) {
          // Failure
          return ctx.createFailureStatus(new Object[] { eObj.eClass().getName() });
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
