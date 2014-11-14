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
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.DeepHistoryPseudoState;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.ShallowHistoryPseudoState;

public class TC02_Region_History extends AbstractModelConstraint {

  @Override
  public IStatus validate(IValidationContext ctx_p) {
    Region target = (Region) ctx_p.getTarget();

    int dh = 0;
    int sh = 0;

    for (AbstractState state : target.getOwnedStates()) {
      if (state instanceof DeepHistoryPseudoState) {
        dh++;
      }
      if (state instanceof ShallowHistoryPseudoState) {
        sh++;
      }

      if ((dh > 1) || (sh > 1)) {
        return ctx_p.createFailureStatus(target.getName());
      }
    }

    return ctx_p.createSuccessStatus();
  }
}
