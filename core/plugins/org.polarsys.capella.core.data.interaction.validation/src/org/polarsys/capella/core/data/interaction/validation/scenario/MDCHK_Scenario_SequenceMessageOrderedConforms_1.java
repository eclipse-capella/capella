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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.helpers.interaction.services.ExecutionEndExt;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.SequenceMessageExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Consistency on a scenario with sequence messages ordered differently in upper level
 */
public class MDCHK_Scenario_SequenceMessageOrderedConforms_1 extends AbstractValidationRule {

  public enum SUBJECT {
    SOURCE, TARGET
  }

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Scenario) {
        Scenario scenario = (Scenario) eObj;

        List<AbstractEnd> srcAbstractEnds = null, tgtAbstractEnds = null;
        List<SequenceMessage> seqMessagesUnordered = new ArrayList<SequenceMessage>();
        Scenario upperScenario = ScenarioExt.getUpperScenario(scenario);

        if (upperScenario != null) {
          tgtAbstractEnds = getMessagesEndLinkedBetweenScenarios(upperScenario, scenario, SUBJECT.TARGET);
          srcAbstractEnds = getMessagesEndLinkedBetweenScenarios(scenario, upperScenario, SUBJECT.SOURCE);

          for (AbstractEnd tgtAbstractEnd : tgtAbstractEnds) {
            AbstractEnd srcAbtractEndLinked =
                getMessageEndContainInSet(RefinementLinkExt.getRefinementRelatedSourceElements(tgtAbstractEnd, InteractionPackage.Literals.ABSTRACT_END),
                    srcAbstractEnds);
            if (tgtAbstractEnds.indexOf(tgtAbstractEnd) != srcAbstractEnds.indexOf(srcAbtractEndLinked)) {
              SequenceMessage currentSeqMessage = getSequenceMessageFromAbstractEnd(srcAbtractEndLinked);
              if (!seqMessagesUnordered.contains(currentSeqMessage)
                  && !seqMessagesUnordered.contains(SequenceMessageExt.getOppositeSequenceMessage(currentSeqMessage))) {
                seqMessagesUnordered.add(currentSeqMessage);
                return ctx.createFailureStatus(new Object[] { currentSeqMessage.getName(), getSequenceMessageFromAbstractEnd(tgtAbstractEnd).getName() });
              }
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  /**
   * Return MessageEnd list contained by scenario 'scenario1_p' linked with another MessageEnd contained by another scenario 'scenario2_p'. The 'subject_p'
   * parameter indicate the navigation way from scenario 'scenario1_p'
   */
  private List<AbstractEnd> getMessagesEndLinkedBetweenScenarios(Scenario scenario1_p, Scenario scenario2_p, SUBJECT subject_p) {
    List<AbstractEnd> scenario1_AbstractEnds = null;
    List<AbstractEnd> scenario2_AbstractEnds = null;
    List<AbstractEnd> abstractEndsLinked = new ArrayList<AbstractEnd>();

    scenario1_AbstractEnds = ScenarioExt.getOwnedAbstractEnds(scenario1_p);
    scenario2_AbstractEnds = ScenarioExt.getOwnedAbstractEnds(scenario2_p);

    for (AbstractEnd sc1_AbstractEnd : scenario1_AbstractEnds) {
      List<CapellaElement> capellaEltsLinked = null;
      AbstractEnd abstractEndLinked = null;
      if (subject_p == SUBJECT.TARGET)
        capellaEltsLinked = RefinementLinkExt.getRefinementRelatedSourceElements(sc1_AbstractEnd, InteractionPackage.Literals.ABSTRACT_END);
      else
        capellaEltsLinked = RefinementLinkExt.getRefinementRelatedTargetElements(sc1_AbstractEnd, InteractionPackage.Literals.ABSTRACT_END);

      for (CapellaElement capellaElement : capellaEltsLinked) {
        if (capellaElement instanceof AbstractEnd && scenario2_AbstractEnds.size() != 0 && scenario2_AbstractEnds.contains(capellaElement))
          abstractEndLinked = (AbstractEnd) capellaElement;
      }
      if (abstractEndLinked != null)
        abstractEndsLinked.add(sc1_AbstractEnd);
    }
    return abstractEndsLinked;
  }

  private AbstractEnd getMessageEndContainInSet(List<CapellaElement> capellaElts_p, List<AbstractEnd> scenario2_AbstractEnds_p) {
    AbstractEnd abstractEndFounded = null;

    for (CapellaElement capellaElement : capellaElts_p) {
      if (capellaElement instanceof AbstractEnd) {
        if (scenario2_AbstractEnds_p.size() != 0 && scenario2_AbstractEnds_p.contains(capellaElement)) {
          abstractEndFounded = (AbstractEnd) capellaElement;
        }
      }
    }
    return abstractEndFounded;
  }

  /**
   * Return SequenceMessage from AbstractEnd object given in parameter
   */
  private SequenceMessage getSequenceMessageFromAbstractEnd(AbstractEnd abstractEnd_p) {
    SequenceMessage seqMessage = null;

    if (abstractEnd_p instanceof MessageEnd)
      seqMessage = ((MessageEnd) abstractEnd_p).getMessage();
    else if (abstractEnd_p instanceof ExecutionEnd)
      seqMessage = ExecutionEndExt.getMessage((ExecutionEnd) abstractEnd_p);

    return seqMessage;
  }

}
