/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.validation.sequenceLink;

import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.model.helpers.SequenceLinkExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/*
 * DWF_DF_15 - SequenceLinks between the referenced Involvement Functions/OperationalActivities have no Involvement Links referencing them
 */
public class SequenceLinksBetweenNodesWithNoConnectingFE extends AbstractValidationRule {
  @Override
  public IStatus validate(IValidationContext ctx) {

    if ((ctx.getEventType() == EMFEventType.NULL) && (ctx.getTarget() instanceof SequenceLink)) {
      SequenceLink seqLink = (SequenceLink) ctx.getTarget();

      Set<FunctionalChainInvolvementLink> links = SequenceLinkExt.getAllFCILBetweenClosestFunctionGroups(seqLink);

      if (links.isEmpty()) {
        return ctx.createFailureStatus(ArrayUtils.addAll(SequenceLinkEndStatusHelper.getStatusInfo(seqLink.getSource()),
            SequenceLinkEndStatusHelper.getStatusInfo(seqLink.getTarget())));
      }
    }
    return ctx.createSuccessStatus();
  }
}
