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
package org.polarsys.capella.core.projection.scenario;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 */
public class CommonScenarioHelper {

  /**
   * Retrieve the operation related to the given scenario element_p
   * @param element_p
   * @return
   */
  public static AbstractEventOperation getOperation(EObject element_p, ITransfo transfo_p) {

    AbstractEventOperation operation = null;
    if (element_p instanceof SequenceMessage) {
      operation = ((SequenceMessage) element_p).getInvokedOperation();
    }
    if (element_p instanceof EventSentOperation) {
      operation = ((EventSentOperation) element_p).getOperation();
    }
    if (element_p instanceof EventReceiptOperation) {
      operation = ((EventReceiptOperation) element_p).getOperation();
    }

    if (element_p instanceof AbstractEnd) {
      return getOperation(((AbstractEnd) element_p).getEvent(), transfo_p);
    }

    return operation;
  }

  public static InstanceRole getOppositeCoveredIR(AbstractEnd end_p) {
    MessageEnd messageEnd = null;
    if (end_p instanceof ExecutionEnd) {
      messageEnd = (MessageEnd) ((ExecutionEnd) end_p).getExecution().getStart();

    } else if (end_p instanceof MessageEnd) {
      messageEnd = (MessageEnd) end_p;
    }

    if (messageEnd != null) {
      MessageEnd opposite =
          messageEnd.getMessage().getSendingEnd() == messageEnd ? messageEnd.getMessage().getReceivingEnd() : messageEnd.getMessage().getSendingEnd();
      if (opposite != null) {
        return opposite.getCovered();
      }
    }

    return null;
  }

  public static InstanceRole getOppositeCoveredIR(ExecutionEnd executionEnd_p) {
    MessageEnd me = (MessageEnd) executionEnd_p.getExecution().getStart();
    return getOppositeCoveredIR(me);
  }

  /**
   * @param exec_p
   */
  public static InstanceRole getOppositeCoveredIR(Execution exec_p) {
    return getOppositeCoveredIR((MessageEnd) exec_p.getStart());
  }

  public static String getTitle(ITransfo transfo_p) {
    Object source = transfo_p.get(TransfoEngine.TRANSFO_SOURCE);
    if (source instanceof EObject) {
      return NLS.bind(Messages.Rule_InstanceRole_TransitionTitleDetailled, EObjectLabelProviderHelper.getText((EObject) source));
    }
    return Messages.Rule_InstanceRole_TransitionTitle;
  }

}
