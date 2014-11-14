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
  public IStatus validate(IValidationContext ctx_p) {
    boolean isDeep;
    Pseudostate target = (Pseudostate) ctx_p.getTarget();

    if (target instanceof DeepHistoryPseudoState) {
      isDeep = true;
    } else if (target instanceof ShallowHistoryPseudoState) {
      isDeep = false;
    } else {
      return ctx_p.createSuccessStatus();
    }

    if (target.getOutgoing().size() > 0) {
      return ctx_p.createFailureStatus(isDeep ? "DeepHistoryState" : "ShallowHistoryState", target.getName()); //$NON-NLS-1$//$NON-NLS-2$
    }

    return ctx_p.createSuccessStatus();
  }

}
