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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateTransition;

public class MDCHK_StateTransiton_Region extends AbstractModelConstraint {

  public MDCHK_StateTransiton_Region() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public IStatus validate(IValidationContext ctx) {
    AbstractState source = ((StateTransition) ctx.getTarget()).getSource();
    AbstractState target = ((StateTransition) ctx.getTarget()).getTarget();

    EObject commonAncestor = EcoreUtil2.getCommonAncestor(source, target);
    if ((commonAncestor instanceof Region) || (commonAncestor == source) || (commonAncestor == target)) {
      return ctx.createSuccessStatus();
    }

    return ctx.createFailureStatus(target.getName());
  }
}
