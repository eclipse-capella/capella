/*******************************************************************************
 * Copyright (c) 2018, 2022 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.projection.scenario.fc2fs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
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
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.model.helpers.refmap.Pair;
import org.polarsys.capella.core.model.utils.NamingHelper;

public class FC2FSInitialization {

  private static final Logger logger = ReportManagerRegistry.getInstance()
      .subscribe(FC2FSInitialization.class.getName());

  private boolean isLogEnabled = false;

  private Map<EObject, EObject> mapping;

  // This list is filled while creating event sent operations
  private List<InstanceRole> orderedInstRoles = new ArrayList<>();

  public FC2FSInitialization() {
    isLogEnabled = FC2FSExt.isLogEnabled();
  }

  public void execute(Collection<FunctionalChain> funcChains) {

    Collection<Pair<FunctionalChain, Scenario>> fc2ScenarioPairs = new ArrayList<>();
    // Read the preference
    boolean isMsgWithReply = FC2FSExt.isCreateMsgWithReply(funcChains.iterator().next());

    for (FunctionalChain fc : funcChains) {
      logInfo("Looking up existing Scenarios...");
      // Look for existing scenarios and ask the user if he want to continue when found Scenarios
      Collection<Scenario> availableInitializedScenarios = FC2FSExt.getAvailableInitializedScenarios(fc);
      if (!availableInitializedScenarios.isEmpty()) {
        if (MessageDialog.openQuestion(FC2FSExt.getActiveShell(), "Confirm Initialization",
            "Are you sure you want to initialize new Scenario for " + fc.getName()
                + "? Initialized Scenario(s) found.")) {
          doExecute(fc, isMsgWithReply, fc2ScenarioPairs);
        }
      } else {
        doExecute(fc, isMsgWithReply, fc2ScenarioPairs);
      }
    }

    if (!fc2ScenarioPairs.isEmpty()) {
      // Add to the model
      logInfo("Adding created Scenario to the model");
      FC2FSExt.addToModel(fc2ScenarioPairs);
      logInfo("Initialization finished");
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  private void doExecute(FunctionalChain fc, boolean isMsgWithReply,
      Collection<Pair<FunctionalChain, Scenario>> fc2ScenarioPairs) {

    logInfo("Perform initialization from " + fc.eClass().getName() + " " + fc.getName());
    // Functional Chain => Scenario
    Scenario scenario = toScenario(fc);

    // Create InstanceRole for each function
    for (AbstractFunction func : FunctionalChainExt.getFlatFunctions(fc)) {
      toInstanceRole(func, scenario);
    }

    // Create a sequence message for each FunctionalExchange
    if (isMsgWithReply) {
      createSequenceMessagesWithReply(fc, scenario);
    } else {
      createSequenceMessages(fc, scenario);
    }
    // Reorder the InstanceRoles in the Scenario
    logInfo("Reordering InstanceRoles...");
    logInfo("Old order: " + NamingHelper.toString((List) scenario.getOwnedInstanceRoles()));
    reorderInstanceRoles(scenario);
    logInfo("New order: " + NamingHelper.toString((List) scenario.getOwnedInstanceRoles()));

    // Add the pairs collection
    fc2ScenarioPairs.add(new Pair<FunctionalChain, Scenario>(fc, scenario));
  }

  private void reorderInstanceRoles(Scenario scenario) {
    int position = 0;
    for (InstanceRole instRole : orderedInstRoles) {
      scenario.getOwnedInstanceRoles().move(position, instRole);
      position++;
    }
  }

  private void createSequenceMessagesWithReply(FunctionalChain fc, Scenario scenario) {
    logInfo("Functional Chain with return branch is enabled");
    for (FunctionalChainInvolvement fci : FunctionalChainExt.getFlatInvolvementsOf(fc,
        FaPackage.Literals.FUNCTIONAL_EXCHANGE)) {
      FunctionalExchange fe = (FunctionalExchange) fci.getInvolved();
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
    logInfo("Functional Chain without return branch is enabled");
    for (FunctionalChainInvolvement fci : FunctionalChainExt.getFlatInvolvementsOf(fc,
        FaPackage.Literals.FUNCTIONAL_EXCHANGE)) {
      FunctionalExchange fe = (FunctionalExchange) fci.getInvolved();
      SequenceMessage sequenceMessage = createSequenceMessage(scenario, fe, MessageKind.ASYNCHRONOUS_CALL);
      
      if (fci instanceof FunctionalChainInvolvementLink) {
          sequenceMessage.getExchangedItems().addAll(((FunctionalChainInvolvementLink)fci).getExchangedItems());
      }

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
    Scenario scenario = InteractionFactory.eINSTANCE.createScenario(getDefaultScenarioName(fc));
    scenario.setKind(getScenarioKind(fc));
    // Add a trace from the scenario to the functional chain
    TransfoLink trace = CapellacommonFactory.eINSTANCE.createTransfoLink();
    trace.setTargetElement(fc);
    trace.setSourceElement(scenario);
    scenario.getOwnedTraces().add(trace);
    getMapping().put(fc, scenario);
    logInfo("Create Scenario " + scenario.getName() + " of kind " + scenario.getKind());
    return scenario;
  }

  private String getDefaultScenarioName(FunctionalChain fc) {
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
      logInfo("Create InstanceRole " + instanceRole.getName());
    }
  }

  private SequenceMessage createSequenceMessage(Scenario scenario, FunctionalExchange fe, MessageKind kind) {
    SequenceMessage sequenceMessage = InteractionFactory.eINSTANCE.createSequenceMessage(fe.getName());
    scenario.getOwnedMessages().add(sequenceMessage);
    sequenceMessage.setKind(kind);
    logInfo("Create SequenceMessage " + sequenceMessage.getName() + " of kind " + kind);
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

    // Look for the InstanceRole mapped from the source of the given functional exchange
    EObject eObject = getMapping().get(getFunctionalExchangeSource(fe));
    if (eObject instanceof InstanceRole && !orderedInstRoles.contains(eObject)) {
      orderedInstRoles.add((InstanceRole) eObject);
    }
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

  private void logInfo(String msg) {
    if (isLogEnabled) {
      logger.info(msg);
    }
  }
}
