/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.validation.sequenceLink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.model.helpers.SequenceLinkExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/*
 * DWF_DF_19 - SequenceLink with inconsistent associated FunctionalChainInvolvementLinks
 */
public class SequenceLinkWithInconsistentAssociatedLinks extends AbstractValidationRule {
  public static final String SequenceLink_Inconsistent_Associated_FCIL = "(SequenceLink) between {0}({1}) and {2}({3}) has inconsistent associated {4}(FunctionalChainInvolvementLink).";

  @Override
  public IStatus validate(IValidationContext ctx) {
    Collection<IStatus> statuses = new ArrayList<IStatus>();

    if ((ctx.getEventType() == EMFEventType.NULL) && (ctx.getTarget() instanceof SequenceLink)) {
      SequenceLink seqLink = (SequenceLink) ctx.getTarget();

      Collection<FunctionalChainInvolvementLink> associatedLinks = seqLink.getLinks();

      if (!associatedLinks.isEmpty()) {
        /*
         * for the current seqLink, calculate the closest functions as source/targets
         */
        Set<FunctionalChainInvolvementFunction> slClosestFCIFSources = SequenceLinkExt
            .findClosestSemanticFCIFunctionsAsSources(seqLink);
        Set<FunctionalChainInvolvementFunction> slClosestFCIFTargets = SequenceLinkExt
            .findClosestSemanticFCIFunctionsAsTargets(seqLink);

        /*
         * OK: if source and target of SL are found in closest FCIFSources/Targets. Display warning: if the source and
         * target of SL is not found in closestFCIFSources/Targets
         */
        for (FunctionalChainInvolvementLink link : associatedLinks) {
          if (link.getInvolved() instanceof FunctionalExchange) {

            if (!isValid(link, slClosestFCIFSources, slClosestFCIFTargets)) {
              Object[] argMessages = ArrayUtils.addAll(SequenceLinkEndStatusHelper.getStatusInfo(seqLink.getSource()),
                  SequenceLinkEndStatusHelper.getStatusInfo(seqLink.getTarget()));
              argMessages = ArrayUtils.add(argMessages, ((FunctionalExchange) (link.getInvolved())).getName());
              statuses.add(ConstraintStatus.createStatus(ctx, seqLink, Arrays.asList(seqLink, link),
                  getMessagePattern(), argMessages));
            }
          }
        }

        if (statuses.size() > 0) {
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }

    }
    return ctx.createSuccessStatus();
  }

  protected boolean isValid(FunctionalChainInvolvementLink link,
      Set<FunctionalChainInvolvementFunction> slClosestFCIFSources,
      Set<FunctionalChainInvolvementFunction> slClosestFCIFTargets) {
    // we don't show the Warning message if they source or target are not found in FCIFSource/Target or if
    // SL is in opposite direction as we don't cover this case in this rule, we cover it in DWF_DF_20
    return (slClosestFCIFSources.contains(link.getSource()) && slClosestFCIFTargets.contains(link.getTarget()))
        || checkConditionOppositeDirection(link, slClosestFCIFSources, slClosestFCIFTargets);
  }

  protected boolean checkConditionOppositeDirection(FunctionalChainInvolvementLink link,
      Set<FunctionalChainInvolvementFunction> slClosestFCIFSources,
      Set<FunctionalChainInvolvementFunction> slClosestFCIFTargets) {
    return (slClosestFCIFSources.contains(link.getTarget()) && slClosestFCIFTargets.contains(link.getSource()));
  }

  protected String getMessagePattern() {
    return SequenceLink_Inconsistent_Associated_FCIL;
  }
}
