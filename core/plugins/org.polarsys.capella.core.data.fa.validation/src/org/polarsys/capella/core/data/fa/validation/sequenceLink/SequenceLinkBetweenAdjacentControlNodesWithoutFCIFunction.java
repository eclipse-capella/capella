/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.fa.ControlNode;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/*
 * DWF_DF_17 - SequenceLink between two adjacent Control Nodes without a FunctionalChainInvolvementFunction
 */
public class SequenceLinkBetweenAdjacentControlNodesWithoutFCIFunction extends AbstractValidationRule {
  @Override
  public IStatus validate(IValidationContext ctx) {

    if ((ctx.getEventType() == EMFEventType.NULL) && (ctx.getTarget() instanceof SequenceLink)) {
      SequenceLink seqLink = (SequenceLink) ctx.getTarget();

      // check if the seqLink is beteween 2 Control Nodes
      if (seqLink.getSource() instanceof ControlNode && seqLink.getTarget() instanceof ControlNode) {
        return ctx.createFailureStatus();
      }
    }
    return ctx.createSuccessStatus();
  }
}
