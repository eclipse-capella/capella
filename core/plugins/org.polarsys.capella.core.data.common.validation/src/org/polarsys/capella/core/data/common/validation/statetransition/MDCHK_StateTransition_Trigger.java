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
package org.polarsys.capella.core.data.common.validation.statetransition;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.fa.AbstractFunction;

public class MDCHK_StateTransition_Trigger extends AbstractModelConstraint {

  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EList<AbstractEvent> triggers = ((StateTransition) ctx_p.getTarget()).getTriggers();

    for (AbstractEvent trigger : triggers) {
      if (trigger instanceof AbstractFunction) {

        return ctx_p.createFailureStatus(new Object[] { ((StateTransition) ctx_p.getTarget()).getTarget().getName() });
      }
    }
    return ctx_p.createSuccessStatus();
  }
}
