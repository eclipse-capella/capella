/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.projection.commands.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramNamingConstants;
import org.polarsys.capella.core.model.helpers.refmap.Pair;

public class FC2FSTransformation {

  private Map<EObject, EObject> mapping;

  public void execute(Collection<FunctionalChain> funcChains) {

    // Read the preference
    boolean isMsgWithReply = FC2FSHelper.isCreateMsgWithReply(funcChains.iterator().next());

    Collection<Pair<FunctionalChain, Scenario>> fc2ScenarioPairs = new ArrayList<>();

    for (FunctionalChain fc : funcChains) {
      // Functional Chain => Scenario
      Scenario scenario = toScenario(fc);

      // Create InstanceRole for each function
      for (AbstractFunction func : fc.getInvolvedFunctions()) {
        toInstanceRole(func, scenario);
      }

      // Create a sequence message for each FunctionalExchange
      if (isMsgWithReply) {
        createSequenceMessagesWithReply(fc, scenario);
      } else {
        createSequenceMessages(fc, scenario);
      }
      fc2ScenarioPairs.add(new Pair<FunctionalChain, Scenario>(fc, scenario));
    }

    // Add to the model
    FC2FSHelper.addToModel(fc2ScenarioPairs);
  }

  private void createSequenceMessagesWithReply(FunctionalChain fc, Scenario scenario) {
    for (FunctionalExchange fe : fc.getInvolvedFunctionalExchanges()) {
      // Send Message
      SequenceMessage sequenceMessage = createSequenceMessage(scenario, fe, MessageKind.ASYNCHRONOUS_CALL);
      MessageEnd sendingEnd = createSendingEnd(scenario, fe, sequenceMessage);
      MessageEnd receivingEnd = createReceivingEnd(scenario, fe, sequenceMessage);
      createEventSentOperation(scenario, fe, sendingEnd);
      createEventReceiptOperation(scenario, fe, receivingEnd);

      // Reply Message
      SequenceMessage replyMessage = createSequenceMessage(scenario, fe, MessageKind.REPLY);
      MessageEnd replySendingEnd = createSendingEnd(scenario, fe, replyMessage);
      MessageEnd replyReceivingEnd = createReceivingEnd(scenario, fe, replyMessage);
      createEventSentOperation(scenario, fe, replySendingEnd);
      createEventReceiptOperation(scenario, fe, replyReceivingEnd);

      // Execution
      createExecution(scenario, receivingEnd, replySendingEnd);
    }
  }

  private void createSequenceMessages(FunctionalChain fc, Scenario scenario) {
    for (FunctionalExchange fe : fc.getInvolvedFunctionalExchanges()) {
      SequenceMessage sequenceMessage = createSequenceMessage(scenario, fe, MessageKind.ASYNCHRONOUS_CALL);

      // SendingEnd
      MessageEnd sendingEnd = createSendingEnd(scenario, fe, sequenceMessage);

      // ReceivingEnd
      MessageEnd receivingEnd = createReceivingEnd(scenario, fe, sequenceMessage);

      // ExecutionEnd
      ExecutionEnd execEnd = crreateExecutionEnd(scenario, receivingEnd);

      // Execution
      createExecution(scenario, receivingEnd, execEnd);

      // EventSentOperation
      createEventSentOperation(scenario, fe, sendingEnd);

      // EventReceiptOperation
      createEventReceiptOperation(scenario, fe, receivingEnd);

      // ExecutionEvent
      createExecutionEvent(scenario, execEnd);
    }
  }

  private Scenario toScenario(FunctionalChain fc) {
    Scenario scenario = InteractionFactory.eINSTANCE.createScenario(getScenarioName(fc));
    scenario.setKind(getScenarioKind(fc));
    getMapping().put(fc, scenario);
    return scenario;
  }

  private String getScenarioName(FunctionalChain fc) {
    if (fc instanceof OperationalProcess) {
      return "[" + DiagramNamingConstants.ACTIVITY_SCENARIO_PREFIX + "] " + fc.getName();
    }
    return "[" + DiagramNamingConstants.FUNCTION_SCENARIO_PREFIX + "] " + fc.getName();
  }

  private ScenarioKind getScenarioKind(FunctionalChain fc) {
    if (fc instanceof OperationalProcess) {
      return ScenarioKind.INTERACTION;
    }
    return ScenarioKind.FUNCTIONAL;
  }

  private void toInstanceRole(AbstractFunction func, Scenario scenario) {
    EObject eObject = getMapping().get(func);
    if (eObject == null) {
      InstanceRole instanceRole = InteractionFactory.eINSTANCE.createInstanceRole(func.getName());
      scenario.getOwnedInstanceRoles().add(instanceRole);
      instanceRole.setRepresentedInstance(func);
      getMapping().put(func, instanceRole);
    }
  }

  private SequenceMessage createSequenceMessage(Scenario scenario, FunctionalExchange fe, MessageKind kind) {
    SequenceMessage sequenceMessage = InteractionFactory.eINSTANCE.createSequenceMessage(fe.getName());
    scenario.getOwnedMessages().add(sequenceMessage);
    sequenceMessage.setKind(kind);
    return sequenceMessage;
  }

  private MessageEnd createSendingEnd(Scenario scenario, FunctionalExchange fe, SequenceMessage sequenceMessage) {
    MessageEnd msgEnd = InteractionFactory.eINSTANCE.createMessageEnd("Send Call Message Call");
    scenario.getOwnedInteractionFragments().add(msgEnd);
    sequenceMessage.setSendingEnd(msgEnd);
    // When it's a reply message use fe's target to find the instance role otherwise use the source
    if (sequenceMessage.getKind() == MessageKind.REPLY) {
      msgEnd.getCoveredInstanceRoles().add((InstanceRole) getMapping().get(getFunctionalExchangeTarget(fe)));
    } else {
      msgEnd.getCoveredInstanceRoles().add((InstanceRole) getMapping().get(getFunctionalExchangeSource(fe)));
    }
    return msgEnd;
  }

  private MessageEnd createReceivingEnd(Scenario scenario, FunctionalExchange fe, SequenceMessage sequenceMessage) {
    MessageEnd receiveEnd = InteractionFactory.eINSTANCE.createMessageEnd("Receive Call Message Call");
    scenario.getOwnedInteractionFragments().add(receiveEnd);
    sequenceMessage.setReceivingEnd(receiveEnd);
    // When it's a reply message use fe's source to find the instance role otherwise use the target
    if (sequenceMessage.getKind() == MessageKind.REPLY) {
      receiveEnd.getCoveredInstanceRoles().add((InstanceRole) getMapping().get(getFunctionalExchangeSource(fe)));
    } else {
      receiveEnd.getCoveredInstanceRoles().add((InstanceRole) getMapping().get(getFunctionalExchangeTarget(fe)));
    }
    return receiveEnd;
  }

  private ExecutionEnd crreateExecutionEnd(Scenario scenario, MessageEnd receivingEnd) {
    ExecutionEnd execEnd = InteractionFactory.eINSTANCE.createExecutionEnd("endExec");
    scenario.getOwnedInteractionFragments().add(execEnd);
    execEnd.getCoveredInstanceRoles().add(receivingEnd.getCoveredInstanceRoles().get(0));
    return execEnd;
  }

  private void createExecution(Scenario scenario, InteractionFragment start, InteractionFragment finish) {
    Execution exec = InteractionFactory.eINSTANCE.createExecution("execution");
    scenario.getOwnedTimeLapses().add(exec);
    exec.setStart(start);
    exec.setFinish(finish);
  }

  private void createEventSentOperation(Scenario scenario, FunctionalExchange fe, MessageEnd sendingEnd) {
    EventSentOperation eventSentOp = InteractionFactory.eINSTANCE.createEventSentOperation("eventSentOp");
    scenario.getOwnedEvents().add(eventSentOp);
    sendingEnd.setEvent(eventSentOp);
    eventSentOp.setOperation(fe);
  }

  private void createEventReceiptOperation(Scenario scenario, FunctionalExchange fe, MessageEnd receivingEnd) {
    EventReceiptOperation eventReceiptOp = InteractionFactory.eINSTANCE.createEventReceiptOperation("eventReceiptOp");
    scenario.getOwnedEvents().add(eventReceiptOp);
    receivingEnd.setEvent(eventReceiptOp);
    eventReceiptOp.setOperation(fe);
  }

  private void createExecutionEvent(Scenario scenario, ExecutionEnd execEnd) {
    org.polarsys.capella.core.data.interaction.ExecutionEvent executionEvent = InteractionFactory.eINSTANCE
        .createExecutionEvent("executionEvent");
    scenario.getOwnedEvents().add(executionEvent);
    execEnd.setEvent(executionEvent);
  }

  private EObject getFunctionalExchangeSource(FunctionalExchange fe) {
    ActivityNode source = fe.getSource();
    return source instanceof AbstractFunction ? source : source.eContainer();
  }

  private EObject getFunctionalExchangeTarget(FunctionalExchange fe) {
    ActivityNode target = fe.getTarget();
    return target instanceof AbstractFunction ? target : target.eContainer();
  }

  private Map<EObject, EObject> getMapping() {
    if (mapping == null) {
      mapping = new HashMap<>();
    }
    return mapping;
  }
}
