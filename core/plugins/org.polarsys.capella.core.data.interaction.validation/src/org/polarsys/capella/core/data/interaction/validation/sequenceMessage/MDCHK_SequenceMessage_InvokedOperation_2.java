/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.validation.sequenceMessage;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that sequence messages have a correct invoked operation (sender shall use operation's interface and receiver shall implement operation's
 * interface).
 */
public class MDCHK_SequenceMessage_InvokedOperation_2 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof SequenceMessage) {
        Component sndCpnt = null;
        Component rcvCpnt = null;
        AbstractExchangeItem sndItem = null;
        AbstractExchangeItem rcvItem = null;

        SequenceMessage seqMsg = (SequenceMessage) eObj;
        // This rule is available only on Interface Scenarios.
        if (!(seqMsg.eContainer() instanceof Scenario) || (ScenarioKind.INTERFACE != ((Scenario) seqMsg.eContainer()).getKind())) {
          return ctx.createSuccessStatus();
        }
        // This rule is not available at OA level (note: we don't have INTERFACE scenarios at OA level...).
        BlockArchitecture rootBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(seqMsg);
        if ((rootBlockArchitecture instanceof OperationalAnalysis)) {
          return ctx.createSuccessStatus();
        }
        // Rule not available for REPLY messages.
        if (seqMsg.getKind() == MessageKind.REPLY) {
          return ctx.createSuccessStatus();
        }
        // Rule not available with a null invokedOperation.
        AbstractEventOperation invokedOperation = seqMsg.getInvokedOperation();
        if (null == invokedOperation) {
          return ctx.createSuccessStatus();
        }

        // calculate receiving interface
        MessageEnd rcvMsgEnd = seqMsg.getReceivingEnd();
        if (rcvMsgEnd != null) {
          Event event = rcvMsgEnd.getEvent();
          if (event instanceof EventReceiptOperation) { // Avoid CreationEvent/DestructionEvent kind message
            EventReceiptOperation evt = (EventReceiptOperation) event;
            AbstractEventOperation rcvOp = evt.getOperation();
            rcvItem = getExchagneItemFromEvent(rcvOp);
          }
          // calculate receiving component
          InstanceRole instanceRole = rcvMsgEnd.getCovered();
          if (instanceRole != null) {
            AbstractInstance abstractInstance = instanceRole.getRepresentedInstance();
            if (abstractInstance != null) {
              AbstractType rcvElt = abstractInstance.getAbstractType();
              if (rcvElt instanceof Component) {
                rcvCpnt = (Component) rcvElt;
              }
            }
          }
        }

        // calculate sender interface
        MessageEnd sntMsgEnd = seqMsg.getSendingEnd();
        if (sntMsgEnd != null) {
          Event event = sntMsgEnd.getEvent();
          if (event instanceof EventSentOperation) { // Avoid CreationEvent/DestructionEvent kind message
            EventSentOperation evt = (EventSentOperation) event;
            AbstractEventOperation sedOp = evt.getOperation();
            sndItem = getExchagneItemFromEvent(sedOp);
          }
          // calculate sender component
          InstanceRole instanceRole = sntMsgEnd.getCovered();
          if (instanceRole != null) {
            AbstractInstance abstractInstance = instanceRole.getRepresentedInstance();
            if (abstractInstance != null) {
              AbstractType sndElt = abstractInstance.getAbstractType();
              if (sndElt instanceof Component) {
                sndCpnt = (Component) sndElt;
              }
            }
          }
        }
        if ((sndCpnt == null) || (rcvCpnt == null)) {
          // sender or receiver impossible to identify : communication pattern or L&F
          return ctx.createSuccessStatus();
        }
        if (!ensureImplementationAndUsage(sndCpnt, sndItem, rcvCpnt, rcvItem)) {
          String sequenceMessageName = EObjectLabelProviderHelper.getText(seqMsg);
          String sequenceMessageMetaClass = EObjectLabelProviderHelper.getMetaclassLabel(seqMsg, false);
          String scenarioName = EObjectLabelProviderHelper.getText(seqMsg.eContainer());
          String scenarioMetaClass = EObjectLabelProviderHelper.getMetaclassLabel(seqMsg.eContainer(), false);
          return ctx.createFailureStatus(sequenceMessageName, sequenceMessageMetaClass, scenarioName, scenarioMetaClass);
        }

      }
    }
    return ctx.createSuccessStatus();
  }

  /**
   * @param rcvItem
   * @param evt
   * @return
   */
  private AbstractExchangeItem getExchagneItemFromEvent(AbstractEventOperation evt) {
    AbstractExchangeItem item = null;
    if (evt instanceof ExchangeItemAllocation) {
      ExchangeItemAllocation itemAllocation = (ExchangeItemAllocation) evt;
      AbstractExchangeItem allocatedItem = itemAllocation.getAllocatedItem();
      if (allocatedItem != null) {
        item = allocatedItem;
      }
    }
    return item;
  }

  /**
   * ensure implementation and usage.
   * @param sndCpnt_p the given component
   * @param sndItf_p the given abstractExchangeItem
   * @param rcvCpnt_p the given component
   * @param rcvItf_p the given abstractExchangeItem
   * @return true, if successful
   */
  private boolean ensureImplementationAndUsage(Component sndCpnt_p, AbstractExchangeItem sndItf_p, Component rcvCpnt_p, AbstractExchangeItem rcvItf_p) {
    return ensureImplementation(rcvCpnt_p, rcvItf_p) && ensureUsage(sndCpnt_p, sndItf_p);
  }

  /**
   * ensure implementation.
   * @param cpnt_p the given component
   * @param itf_p the given abstractExchangeItem
   * @return true, if successful
   */
  private boolean ensureImplementation(Component cpnt_p, AbstractExchangeItem itf_p) {
    for (Interface itf : ComponentExt.getAllImplementedAndProvidedInterfaces(cpnt_p)) {
      if (itf.getExchangeItems().contains(itf_p)) {
        return true;
      }
    }
    return false;
  }

  /**
   * ensure usage.
   * @param cpnt_p the given component
   * @param itf_p the given abstractExchangeItem
   * @return true, if successful
   */
  private boolean ensureUsage(Component cpnt_p, AbstractExchangeItem itf_p) {
    for (Interface itf : ComponentExt.getAllUsedAndRequiredInterfaces(cpnt_p)) {
      if (itf.getExchangeItems().contains(itf_p)) {
        return true;
      }
    }
    return false;
  }
}
