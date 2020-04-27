/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.validation.scenario;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.helpers.interaction.services.ExecutionEndExt;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check on a scenario the Branchs sequence messages ordered
 */
public class MDCHK_Scenario_BranchsSequenceMessageOrdered_1 extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Scenario) {
        Scenario scenario = (Scenario) eObj;

        for (TimeLapse execution : scenario.getOwnedTimeLapses()) {
          // Ignore executions with no start or not end.
          if ((null == execution.getStart()) || (null == execution.getFinish())) {
            continue;
          }
          // Ignore executions for which ends are not contained in the scenario (probably a DnD from another scenario).
          if ((execution.getStart().eContainer() != scenario) || (execution.getFinish().eContainer() != scenario)) {
            continue;
          }
          if (!correctOrder(execution.getStart(), execution.getFinish(), scenario.getOwnedInteractionFragments())) {
            return ctx_p.createFailureStatus(getSequenceMessageFromAbstractEnd(execution.getStart()));
          }
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }

  /***
   * Return false in case of start_p AbstractEnd parameter is after position of finish_p AbstractEnd parameter in AbstractEnd list parameter.
   */
  private boolean correctOrder(InteractionFragment start_p, InteractionFragment finish_p, EList<InteractionFragment> listAbstractEnds_p) {
    if ((null != start_p) && (null != finish_p)) {
      if (listAbstractEnds_p.indexOf(start_p) < listAbstractEnds_p.indexOf(finish_p)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return SequenceMessage from AbstractEnd object given in parameter
   */
  private SequenceMessage getSequenceMessageFromAbstractEnd(InteractionFragment abstractEnd_p) {
    SequenceMessage seqMessage = null;

    if (abstractEnd_p instanceof MessageEnd) {
      seqMessage = ((MessageEnd) abstractEnd_p).getMessage();
    } else if (abstractEnd_p instanceof ExecutionEnd) {
      seqMessage = ExecutionEndExt.getMessage((ExecutionEnd) abstractEnd_p);
    }

    return seqMessage;
  }

}
