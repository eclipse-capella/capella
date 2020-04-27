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
package org.polarsys.capella.core.data.common.validation.statetransition;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacommon.EntryPointPseudoState;

public class MDCHK_EntryPoint_Transition extends AbstractModelConstraint {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EntryPointPseudoState target = (EntryPointPseudoState) ctx.getTarget();

    if ((target.getOutgoing().size() != 1) || (target.getIncoming().size() == 0)) {
      return ctx.createFailureStatus(target.getName());
    }
    return ctx.createSuccessStatus();
  }

}
