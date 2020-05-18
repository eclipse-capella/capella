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
package org.polarsys.capella.core.data.fa.validation.controlNode;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.fa.ControlNode;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.model.helpers.SequenceLinkEndExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/*
 * DWF_DF_21 - Control Nodes inconsistent operations
 */
public class ControlNodeInconsistentOperations extends AbstractValidationRule {
  public static final String ControlNode_Inconsistency_No_InOut_Sequence_Links = "has no incoming and outgoing Sequence Links";
  public static final String ControlNode_Inconsistency_No_In_Sequence_Links = "has no incoming Sequence Links";
  public static final String ControlNode_Inconsistency_No_Out_Sequence_Links = "has no outgoing Sequence Links";
  public static final String ControlNode_Inconsistency_Minimum_InOut_Sequence_Links = "has only one incoming and one outgoing Sequence Link";

  @Override
  public IStatus validate(IValidationContext ctx) {

    if ((ctx.getEventType() == EMFEventType.NULL) && (ctx.getTarget() instanceof ControlNode)) {
      String className = "ControlNode";
      ControlNode controlNode = (ControlNode) ctx.getTarget();

      // control node without in or out links
      List<SequenceLink> inLinks = SequenceLinkEndExt.getIncomingSequenceLinks(controlNode);
      List<SequenceLink> outLinks = SequenceLinkEndExt.getOutgoingSequenceLinks(controlNode);
      int inCount = inLinks.size();
      int outCount = outLinks.size();
      if (inCount == 0 && outCount == 0) {
        return ctx.createFailureStatus(className, ControlNode_Inconsistency_No_InOut_Sequence_Links);
      }
      if (inCount == 0) {
        return ctx.createFailureStatus(className, ControlNode_Inconsistency_No_In_Sequence_Links);
      }
      if (outCount == 0) {
        return ctx.createFailureStatus(className, ControlNode_Inconsistency_No_Out_Sequence_Links);
      }
      if (inCount == 1 && outCount == 1) {
        return ctx.createFailureStatus(className, ControlNode_Inconsistency_Minimum_InOut_Sequence_Links);
      }
      // check that node is closed - graph todo
    }
    return ctx.createSuccessStatus();
  }
}
