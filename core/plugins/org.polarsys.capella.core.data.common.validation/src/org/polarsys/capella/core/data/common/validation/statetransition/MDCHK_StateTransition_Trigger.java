/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.business.queries.queries.capellacommon.GetAvailable_StateTransitionTrigger;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateTransition;

public class MDCHK_StateTransition_Trigger extends AbstractModelConstraint {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EList<AbstractEvent> triggers = ((StateTransition) ctx.getTarget()).getTriggers();
    QueryContext context = new QueryContext();
    context.putValue(QueryConstants.ECLASS_PARAMETER, CapellacommonPackage.Literals.STATE_TRANSITION);
    List<Object> elements = GetAvailable_StateTransitionTrigger.getAvailableElements(ctx.getTarget(), context);
    for (AbstractEvent trigger : triggers) {
      if (!elements.contains(trigger)) {
        return ctx
            .createFailureStatus(new Object[] { ((StateTransition) ctx.getTarget()).getTarget().getName(), trigger });
      }
    }
    return ctx.createSuccessStatus();
  }
}
