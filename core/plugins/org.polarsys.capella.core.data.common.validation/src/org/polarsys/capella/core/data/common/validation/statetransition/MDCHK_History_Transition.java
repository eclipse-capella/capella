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
import org.polarsys.capella.core.data.capellacommon.DeepHistoryPseudoState;
import org.polarsys.capella.core.data.capellacommon.Pseudostate;
import org.polarsys.capella.core.data.capellacommon.ShallowHistoryPseudoState;

public class MDCHK_History_Transition extends AbstractModelConstraint {

  public MDCHK_History_Transition() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public IStatus validate(IValidationContext ctx) {
    boolean isDeep;
    Pseudostate target = (Pseudostate) ctx.getTarget();

    if (target instanceof DeepHistoryPseudoState) {
      isDeep = true;
    } else if (target instanceof ShallowHistoryPseudoState) {
      isDeep = false;
    } else {
      return ctx.createSuccessStatus();
    }

    if (target.getOutgoing().size() > 0) {
      return ctx.createFailureStatus(isDeep ? "DeepHistoryState" : "ShallowHistoryState", target.getName()); //$NON-NLS-1$//$NON-NLS-2$
    }

    return ctx.createSuccessStatus();
  }

}
