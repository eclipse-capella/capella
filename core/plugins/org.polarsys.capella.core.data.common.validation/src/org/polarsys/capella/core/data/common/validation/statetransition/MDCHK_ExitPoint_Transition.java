/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacommon.ExitPointPseudoState;

public class MDCHK_ExitPoint_Transition extends AbstractModelConstraint {

  @Override
  public IStatus validate(IValidationContext ctx) {
    ExitPointPseudoState target = (ExitPointPseudoState) ctx.getTarget();

    if ((target.getIncoming().size() != 1) || (target.getOutgoing().size() == 0)) {
      return ctx.createFailureStatus(target.getName());
    }
    return ctx.createSuccessStatus();
  }

}
