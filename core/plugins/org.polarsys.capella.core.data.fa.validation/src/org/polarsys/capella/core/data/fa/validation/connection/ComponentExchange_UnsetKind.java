/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.validation.connection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class ComponentExchange_UnsetKind extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext context) {
    EObject target = context.getTarget();
    if (target instanceof ComponentExchange && !(target instanceof CommunicationMean)) {
      ComponentExchange exchange = (ComponentExchange) target;
      if (ComponentExchangeKind.UNSET.equals(exchange.getKind())) {
        return context.createFailureStatus(exchange.getName());
      }
    }
    return context.createSuccessStatus();
  }
  
  

}
