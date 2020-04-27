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

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/*
 * DWF_DF_16 - Sequence Link has empty condition
 */
public class SequenceLinkHasEmptyCondition extends AbstractValidationRule {
  @Override
  public IStatus validate(IValidationContext ctx) {

    if ((ctx.getEventType() == EMFEventType.NULL) && (ctx.getTarget() instanceof SequenceLink)) {
      SequenceLink seqLink = (SequenceLink) ctx.getTarget();

      if (!hasCondition(seqLink)) {
        return ctx.createFailureStatus(ArrayUtils.addAll(SequenceLinkEndStatusHelper.getStatusInfo(seqLink.getSource()),
            SequenceLinkEndStatusHelper.getStatusInfo(seqLink.getTarget())));
      }
    }
    return ctx.createSuccessStatus();
  }

  private boolean hasCondition(SequenceLink seqLink) {
    boolean hasCondition = false;
    if (seqLink.getCondition() != null) {
      String constraint = CapellaServices.getService().getConstraintLabel(seqLink.getCondition());
      hasCondition = !(constraint.isEmpty());
    }
    return hasCondition;
  }
}
