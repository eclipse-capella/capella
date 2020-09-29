/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis;

import static org.polarsys.capella.core.sirius.analysis.refresh.extension.InteractionRefreshExtension.getExecutionElementToContainerCache;
import static org.polarsys.capella.core.sirius.analysis.refresh.extension.InteractionRefreshExtension.getExecutionElementToSingleContainerCache;
import static org.polarsys.capella.core.sirius.analysis.refresh.extension.InteractionRefreshExtension.getInteractionCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.services.UIUtil;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.AbstractFragment;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionOperatorKind;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.SequenceMessageExt;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;

public class SequenceDiagramServices {

  public static InstanceRole currentInstanceRole(EObject self) {
    if (self instanceof InstanceRole) {
      return (InstanceRole) self;
    } else if (self instanceof Execution) {
      return getInteractionCache(Execution::getCovered, (Execution) self);
    } else if (self instanceof AbstractEnd) {
      return getInteractionCache(AbstractEnd::getCovered, (AbstractEnd) self);
    } else {
      return null;
    }
  }

  public static Collection<EObject> getExecutionsFromInstanceRoleOrParentExecution(EObject eObject) {
    // get executions from cache
    Collection<EObject> executions = getExecutionElementToContainerCache(eObject);
    // if not present, compute it and put it in cache
    if (executions == null) {
      executions = EventContextServices.getDirectEvents(eObject, currentInstanceRole(eObject));
    }
    return executions;
  }

  public static List<InteractionFragment> getOrderedInteractionFragments(Scenario scenario) {
    if (scenario == null) {
      return new ArrayList<InteractionFragment>();
    }
    return scenario.getOwnedInteractionFragments();
  }

  public static EObject getSendingEndEvent(SequenceMessage message) {
    return message.getSendingEnd();
  }

  public static EObject getReceivingEndEvent(SequenceMessage message) {
    return message.getReceivingEnd();
  }

  public static EObject getSendingEnd(SequenceMessage message) {
    return getInteractionCache(SequenceDiagramServices::computeSendingEnd, message);
  }

  public static EObject computeSendingEnd(SequenceMessage message) {
    MessageEnd end = message.getSendingEnd();
    if (end == null) {
      return message; // found message case
    }

    Optional<EObject> execution = getExecutionElementToSingleContainerCache(end);
    if (execution.isPresent()) {
      return execution.get();
    }

    Scenario sc = (Scenario) message.eContainer();
    // Execution execution2 = getExecutionOfMessageEnd(end, sc);
    return getExecutionOrCoveredOfMessageEnd(end);
  }

  public static EObject getReceivingEnd(SequenceMessage message) {
    return getInteractionCache(SequenceDiagramServices::computeReceivingEnd, message);
  }

  public static EObject computeReceivingEnd(SequenceMessage message) {
    MessageEnd end = message.getReceivingEnd();
    if (end == null) {
      return message; // lost message case
    }
    Optional<EObject> execution = getExecutionElementToSingleContainerCache(end);
    if (execution.isPresent()) {
      return execution.get();
    }

    Scenario sc = (Scenario) message.eContainer();
    // Execution execution2 = getExecutionOfMessageEnd(end, sc);
    return getExecutionOrCoveredOfMessageEnd(end);
  }

  private static EObject getExecutionOrCoveredOfMessageEnd(MessageEnd end) {
    EventContextServices.getDirectEvents(end, currentInstanceRole(end));
    Optional<EObject> execution = getExecutionElementToSingleContainerCache(end);
    // null never happen
    return execution.isPresent() ? execution.get() : null;
  }

  /**
   * used in common.odesign oa.odesign, context.odesign, sequences.odesign
   * 
   * @param object
   * @return
   */
  public static Scenario getScenario(EObject object) {
    EObject result = object;

    if (result instanceof DSemanticDecorator) {
      result = ((DSemanticDecorator) result).getTarget();
    }

    while (!(result instanceof Scenario)) {
      result = result.eContainer();
    }

    return (Scenario) (result instanceof Scenario ? result : null);
  }

  public static SequenceMessage getInvocationMessage(SequenceMessage message) {
    return SequenceMessageExt.getOppositeSequenceMessage(message);
  }

  /**
   * Validate a reorder with the new API of sequence diagrams
   * 
   * @param object
   * @param startingEndPredecessorAfter
   * @param finishingEndPredecessorAfter
   * @return
   */
  public static EObject doReorder(EObject object, EObject startingEndPredecessorAfter,
      EObject finishingEndPredecessorAfter) {
    InteractionFragment newPredecessor = (InteractionFragment) startingEndPredecessorAfter;
    InteractionFragment newPredecessorOfEnd = (InteractionFragment) finishingEndPredecessorAfter;
    Scenario scenario = (Scenario) object.eContainer();

    if (object instanceof SequenceMessage) {
      SequenceMessage reorderedMessage = (SequenceMessage) object;
      reorderSequenceMessage(scenario, newPredecessor, newPredecessorOfEnd, reorderedMessage);
    } else if (object instanceof TimeLapse) {
      TimeLapse execution = (TimeLapse) object;
      reorderTimeLapse(scenario, newPredecessor, newPredecessorOfEnd, execution);
    } else if (object instanceof InteractionState) {
      InteractionState interactionState = (InteractionState) object;
      reorderInteractionState(scenario, newPredecessor, newPredecessorOfEnd, interactionState);
    } else if (object instanceof InteractionOperand) {
      InteractionOperand interactionOperand = (InteractionOperand) object;
      reorderInteractionOperand(scenario, newPredecessor, newPredecessorOfEnd, interactionOperand);
    }
    return object;
  }

  public static void validateOrdering(Scenario scenario) {
    if (!ScenarioExt.checkOrdering(scenario)) {
      orderingError(scenario);
    }
  }

  /**
   * @param newPredecessor
   * @param currentFragment
   */
  private static void reorderInteractionFragment(Scenario scenario, InteractionFragment newPredecessor,
      InteractionFragment currentFragment) {

    int currentFragmentIndex = -1;
    if (currentFragment != null) {
      currentFragmentIndex = scenario.getOwnedInteractionFragments().indexOf(currentFragment);
      int currentIndexNewPredecessor = -1;
      if (newPredecessor != null) {
        currentIndexNewPredecessor = scenario.getOwnedInteractionFragments().indexOf(newPredecessor);
      }

      // Reorder shall use move method instead of remove/add
      if (currentFragmentIndex < currentIndexNewPredecessor) {
        scenario.getOwnedInteractionFragments().move(currentIndexNewPredecessor, currentFragmentIndex);
      } else if (currentFragmentIndex > currentIndexNewPredecessor) {
        scenario.getOwnedInteractionFragments().move(currentIndexNewPredecessor + 1, currentFragmentIndex);
      }
    }
  }

  /**
   * @param scenario
   * @param newPredecessor
   * @param newPredecessorOfEnd
   * @param interactionState
   */
  private static void reorderInteractionState(Scenario scenario, InteractionFragment newPredecessor,
      InteractionFragment newPredecessorOfEnd, InteractionState interactionState) {
    reorderInteractionFragment(scenario, newPredecessor, interactionState);
  }

  private static void reorderInteractionOperand(Scenario scenario, InteractionFragment newPredecessor,
      InteractionFragment newPredecessorOfEnd, InteractionOperand interactionOperand) {
    reorderInteractionFragment(scenario, newPredecessor, interactionOperand);
  }

  /**
   * @param scenario
   * @param newPredecessor
   * @param newPredecessorOfEnd
   * @param execution
   */
  private static void reorderTimeLapse(Scenario scenario, InteractionFragment newPredecessor,
      InteractionFragment newPredecessorOfEnd, TimeLapse execution) {
    reorderInteractionFragment(scenario, newPredecessor, execution.getStart());
    reorderInteractionFragment(scenario, newPredecessorOfEnd, execution.getFinish());
  }

  /**
   * @param scenario
   * @param newPredecessor
   * @param newPredecessorOfEnd
   * @param reorderedMessage
   * @param scenario
   */
  private static void reorderSequenceMessage(Scenario scenario, InteractionFragment newPredecessor,
      InteractionFragment newPredecessorOfEnd, SequenceMessage reorderedMessage) {
    AbstractEnd begin = reorderedMessage.getSendingEnd();
    AbstractEnd end = reorderedMessage.getReceivingEnd();

    if (begin != null) {
      reorderInteractionFragment(scenario, newPredecessor, begin);
    }
    if (end != null) {
      reorderInteractionFragment(scenario, newPredecessorOfEnd, end);
    }

    EList<InteractionFragment> fragments = null;
    EList<SequenceMessage> messages = null;

    fragments = scenario.getOwnedInteractionFragments();
    messages = scenario.getOwnedMessages();

    // Reorder shall use move instead of remove/add
    if (newPredecessor == null) {
      // The message is pushed up to the top of the message list
      messages.move(0, reorderedMessage);
    } else {
      // Search the corresponding message to the new precedingEnd
      SequenceMessage previousMessage = findCorrespondingPreviousMessage(fragments, newPredecessor);
      if (previousMessage != null) {
        int positionMessage = messages.indexOf(previousMessage);
        if (positionMessage + 1 < messages.size()) {
          messages.move(positionMessage + 1, reorderedMessage);
        } else {
          messages.move(positionMessage, reorderedMessage);
        }
      } else {
        // The message is pushed up to the top of the message list
        messages.move(0, reorderedMessage);
      }
    }
  }

  /**
   * Search the closest message corresponding to the predecessor passed in parameter. If the message can be deduced
   * directly, go backward in the list of fragments to find the previous message. Otherwise return null.
   * 
   * @param fragments
   *          List of fragments of the diagram
   * @param predecessor
   * @return
   */
  private static SequenceMessage findCorrespondingPreviousMessage(EList<InteractionFragment> fragments,
      InteractionFragment predecessor) {
    if (predecessor instanceof MessageEnd) {
      return ((MessageEnd) predecessor).getMessage();
    }
    // Search the previous 'interactionFragment:
    int pos = fragments.indexOf(predecessor);
    if (pos != 0) {
      return findCorrespondingPreviousMessage(fragments, fragments.get(pos - 1));
    }
    return null;
  }

  // for acceleo2aql
  public static boolean allowCreateMessageCreation2(EObject current, EObject preTarget, EObject preSource,
      EObject eventEndBefore, EObject eventEndAfter) {
    EObject preMessageEndBefore = null;
    EventEnd eventEndBefore_ = (EventEnd) eventEndBefore;
    if (eventEndBefore_ != null)
      preMessageEndBefore = eventEndBefore_.getSemanticEnd();
    EObject preMessageEndAfter = null;
    EventEnd eventEndAfter_ = (EventEnd) eventEndAfter;
    if (eventEndAfter_ != null)
      preMessageEndAfter = eventEndAfter_.getSemanticEnd();
    return allowCreateMessageCreation(current, preTarget, preSource, preMessageEndBefore, preMessageEndAfter);
  }

  public static boolean allowCreateMessageCreation(EObject current, EObject preTarget, EObject preSource,
      EObject preMessageEndBefore, EObject preMessageEndAfter) {

    if (preSource.equals(preTarget)) {
      return false;
    }
    Scenario scenario = (Scenario) current.eContainer();
    for (SequenceMessage message : scenario.getOwnedMessages()) {
      if (message.getKind() == MessageKind.CREATE) {
        InstanceRole ir = message.getReceivingEnd().getCovered();
        if (ir == preTarget) {
          return false;
        }
      }
    }
    // create message must not have a fragment before for the targetIR
    if (preMessageEndBefore == null) {
      return true; // first fragment in the diagram
    }
    for (InteractionFragment fragment : scenario.getOwnedInteractionFragments()) {
      if (fragment == preMessageEndBefore) {
        break;
      }
      if (fragment == preMessageEndAfter) {
        break;
      }
      if (fragment.getCoveredInstanceRoles().contains(preTarget)) {
        return false;
      }
    }
    return true;
  }

  // for acceleo2aql
  public static boolean allowDeleteMessageCreation2(EObject current, EObject preTarget, EObject preSource,
      EObject eventEndBefore, EObject eventEndAfter) {
    EObject preMessageEndBefore = null;
    EventEnd eventEndBefore_ = (EventEnd) eventEndBefore;
    if (eventEndBefore_ != null)
      preMessageEndBefore = eventEndBefore_.getSemanticEnd();
    EObject preMessageEndAfter = null;
    EventEnd eventEndAfter_ = (EventEnd) eventEndAfter;
    if (eventEndAfter_ != null)
      preMessageEndAfter = eventEndAfter_.getSemanticEnd();
    return allowDeleteMessageCreation(current, preTarget, preSource, preMessageEndBefore, preMessageEndAfter);
  }

  public static boolean allowDeleteMessageCreation(EObject current, EObject preTarget, EObject preSource,
      EObject preMessageEndBefore, EObject preMessageEndAfter) {
    if (preSource.equals(preTarget)) {
      return false;
    }
    Scenario scenario = (Scenario) current.eContainer();
    for (SequenceMessage message : scenario.getOwnedMessages()) {
      if (message.getKind() == MessageKind.DELETE) {
        InstanceRole ir = message.getReceivingEnd().getCovered();
        if (ir == preTarget) {
          return false;
        }
      }
    }
    // create message must not have a fragment after for the targetIR
    List<InteractionFragment> fragments = new ArrayList<InteractionFragment>(
        scenario.getOwnedInteractionFragments().size());
    for (InteractionFragment fragment : scenario.getOwnedInteractionFragments()) {
      fragments.add(0, fragment);
    }
    for (InteractionFragment fragment : fragments) {
      if (fragment == preMessageEndBefore) {
        break;
      }
      if (fragment == preMessageEndAfter) {
        break;
      }
      if (fragment.getCoveredInstanceRoles().contains(preTarget)) {
        return false;
      }
    }
    return true;
  }

  // for acceleo2aql wrapper
  public static boolean allowMessageCreation2(EObject current, EObject preSource, EObject preTarget, boolean withReturn,
      EObject eventEndBefore, EObject eventEndAfter) {
    EObject preMessageEndBefore = null;
    EventEnd eventEndBefore_ = (EventEnd) eventEndBefore;
    if (eventEndBefore_ != null)
      preMessageEndBefore = eventEndBefore_.getSemanticEnd();
    EObject preMessageEndAfter = null;
    EventEnd eventEndAfter_ = (EventEnd) eventEndAfter;
    if (eventEndAfter_ != null)
      preMessageEndAfter = eventEndAfter_.getSemanticEnd();
    return allowMessageCreation(current, preSource, preTarget, withReturn, preMessageEndBefore, preMessageEndAfter);
  }

  public static boolean allowMessageCreation(EObject current, EObject preSource, EObject preTarget, boolean withReturn,
      EObject endBefore, EObject endAfter) {
    InstanceRole sourceInstance;
    InstanceRole targetInstance;

    // If no source and no target, no need to continue.
    if (preSource == null) {
      return false;
    }
    if (preTarget == null) {
      return false;
    }

    if (preSource instanceof InstanceRole) {
      InstanceRole ir = (InstanceRole) preSource;
      sourceInstance = ir;
    } else {
      Execution exec = (Execution) preSource;
      sourceInstance = exec.getCovered();
    }

    if (preTarget instanceof InstanceRole) {
      InstanceRole ir = (InstanceRole) preTarget;
      targetInstance = ir;
    } else {
      Execution exec = (Execution) preTarget;
      targetInstance = exec.getCovered();
    }

    InstanceRole exchangeItemLifeLine = null;
    if (sourceInstance.getRepresentedInstance() instanceof ExchangeItemInstance) {
      exchangeItemLifeLine = sourceInstance;
    }
    if (targetInstance.getRepresentedInstance() instanceof ExchangeItemInstance) {
      exchangeItemLifeLine = targetInstance;
    }

    if (sourceInstance.equals(targetInstance)) {
      // in such a case, it is illegal to have an operand on the same IR
      // between the two endsBefore/After in the ordering
      Scenario s = (Scenario) sourceInstance.eContainer();
      boolean betweenElement = endBefore == null ? true : false;
      for (InteractionFragment interactionFragment : s.getOwnedInteractionFragments()) {
        if ((interactionFragment instanceof InteractionOperand) && betweenElement) {
          return false;
        }
        if (interactionFragment.equals(endAfter)) {
          break;
        }
        if (interactionFragment.equals(endBefore)) {
          betweenElement = true;
        }
      }
    }

    // no constraint if there is no ExchangeItem as source nor destination
    if (exchangeItemLifeLine == null) {
      return true;
    }

    ExchangeItem exchangeItem = (ExchangeItem) exchangeItemLifeLine.getRepresentedInstance().getAbstractType();

    if (exchangeItem.getExchangeMechanism() == ExchangeMechanism.SHARED_DATA) {
      return allowMessageCreationOnSharedData(preSource, preTarget, withReturn, sourceInstance, targetInstance,
          exchangeItemLifeLine);
    }
    // event case
    return allowMessageCreationOnEvent(preSource, preTarget, withReturn, sourceInstance, targetInstance,
        exchangeItemLifeLine);
  }

  /**
   * @param preSource
   * @param preTarget
   * @param withReturn
   * @param sourceInstance
   * @param targetInstance
   * @param exchangeItemLifeLine
   * @return
   */
  private static boolean allowMessageCreationOnEvent(EObject preSource, EObject preTarget, boolean withReturn,
      InstanceRole sourceInstance, InstanceRole targetInstance, InstanceRole exchangeItemLifeLine) {
    // in the event case, only ONE cases are allowed.
    // (1) asynch, EI->COMP source is IR
    if (!withReturn && (targetInstance.getRepresentedInstance() instanceof Part)) {
      // case 1
      return true;
    }
    return false;
  }

  /**
   * @param preSource
   * @param preTarget
   * @param withReturn
   * @param sourceInstance
   * @param targetInstance
   * @param exchangeItemLifeLine
   * @return
   */
  private static boolean allowMessageCreationOnSharedData(EObject preSource, EObject preTarget, boolean withReturn,
      InstanceRole sourceInstance, InstanceRole targetInstance, InstanceRole exchangeItemLifeLine) {
    boolean case2found = false; // <=> existing ACCEPT message found.
    boolean case3found = false; // <=> existing READ message found.
    // Go through existing SequenceMessages of the Scenario connecting sourceInstance and targetInstance.
    Scenario s = (Scenario) sourceInstance.eContainer();
    for (SequenceMessage existingSequenceMessage : s.getOwnedMessages()) {
      if (((existingSequenceMessage.getSendingEnd().getCovered() == sourceInstance)
          && (existingSequenceMessage.getReceivingEnd().getCovered() == targetInstance))
          || ((existingSequenceMessage.getSendingEnd().getCovered() == targetInstance)
              && (existingSequenceMessage.getReceivingEnd().getCovered() == sourceInstance))) {
        if (existingSequenceMessage.getReceivingEnd().getCovered() == exchangeItemLifeLine) {
          // SequenceMessage pointing to the ExchangeItem -> can be case 1 (WRITE message) or case 3 (READ
          // message).
          if (SequenceMessageExt.getOppositeSequenceMessage(existingSequenceMessage) != null) {
            // There is a return SequenceMessage -> it's a case 3 (READ message)
            case3found = true;
          }
        }
        if ((existingSequenceMessage.getSendingEnd().getCovered() == exchangeItemLifeLine)
            && (existingSequenceMessage.getKind() != MessageKind.REPLY)) {
          // SequenceMessage starting from the ExchangeItem (and which is not a REPLY) -> it's an ACCEPT
          // message.
          case2found = true;
        }
      }
    }

    // in the case of a message "to" or "from" an EI, only 3 cases are possibles:

    // (1) Asynchrone, Comp->EI, the target being an IR (write message)
    // (2) Asynchrone, EI->Comp, the source being an execution (possible from the version of 2013)
    // (3) Synchrone, Comp->EI, the target being an execution (possible from the version of 2013)

    // If the case (2) occurred it will be not possible to done the case (3) and vice versa.

    if (!withReturn && (sourceInstance.getRepresentedInstance() instanceof Part)
        && (preTarget instanceof InstanceRole)) {
      // case 1
      return true;
    }
    if ((!case3found) && !withReturn && (targetInstance.getRepresentedInstance() instanceof Part)
        && ((preSource instanceof Execution) || (preSource instanceof InstanceRole))) {
      // case 2
      return true;
    }
    if ((!case2found) && withReturn && (sourceInstance.getRepresentedInstance() instanceof Part)
        && ((preTarget instanceof Execution) || (preTarget instanceof InstanceRole))) {
      // case 3
      return true;
    }

    // all other case involving a EI is invalid
    return false;
  }

  public static AbstractFragment getFragmentFromContained(EObject context) {
    if (context instanceof AbstractFragment) {
      AbstractFragment af = (AbstractFragment) context;
      return af;
    }
    if (context instanceof InteractionOperand) {
      // Search the CF that contains the InteractionOperand
      Scenario scenario = (Scenario) context.eContainer();
      for (TimeLapse tl : scenario.getOwnedTimeLapses()) {
        if (tl instanceof CombinedFragment) {
          CombinedFragment cf = (CombinedFragment) tl;
          if (cf.getReferencedOperands().contains(context)) {
            return cf;
          }
        }
      }
    }
    return null;
  }

  public static void refreshOrdering(DDiagram ddiagram) {
    UIUtil.getInstance().refreshActiveDiagram(ddiagram);
  }

  public static void setCoveredIR(AbstractFragment fragment, List<InstanceRole> newCovered) {
    fragment.getStart().getCoveredInstanceRoles().clear();
    fragment.getStart().getCoveredInstanceRoles().addAll(newCovered);

    fragment.getFinish().getCoveredInstanceRoles().clear();
    fragment.getFinish().getCoveredInstanceRoles().addAll(newCovered);

    if (fragment instanceof CombinedFragment) {
      CombinedFragment new_name = (CombinedFragment) fragment;
      for (InteractionOperand op : new_name.getReferencedOperands()) {
        op.getCoveredInstanceRoles().clear();
        op.getCoveredInstanceRoles().addAll(newCovered);
      }
    }
  }

  public static List<String> getOperators(EObject context) {
    List<String> result = new ArrayList<String>(10);
    for (InteractionOperatorKind kind : InteractionOperatorKind.values()) {
      String str = kind.toString();
      if (!str.equals("") && !str.equals("ALT") && !str.equals("LOOP") && !str.equals("PAR")) {
        result.add(str);
      }
    }
    return result;
  }

  public static void removeInstanceRoles(EObject context, Collection<EObject> result, Collection<EObject> current) {
    Set<InstanceRole> irToRemove = new HashSet<InstanceRole>();
    for (EObject obj : current) {
      if (obj instanceof InstanceRole) {
        InstanceRole ir = (InstanceRole) obj;
        if (!result.contains(ir.getRepresentedInstance())) {
          irToRemove.add(ir);
        }
      }
    }
    CapellaDeleteCommand mdc = new CapellaDeleteCommand(TransactionHelper.getExecutionManager(context), irToRemove,
        false, false, true);
    if (mdc.canExecute()) {
      // Do execute the command !
      mdc.execute();
    }

  }

  /**
   * @param scenario
   */
  public static void orderingError(Scenario scenario) {
    OperationCanceledException exception = new OperationCanceledException(Messages.SequenceDiagramServices_3);
    IStatus errorStatus = new Status(IStatus.ERROR, SiriusViewActivator.ID, 0, Messages.SequenceDiagramServices_0,
        exception);
    ErrorDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.SequenceDiagramServices_1,
        Messages.SequenceDiagramServices_2, errorStatus);
    throw exception;
  }

  private static boolean containsOaActivity(Scenario s) {
    for (InstanceRole ir : s.getOwnedInstanceRoles()) {
      if (ir.getRepresentedInstance() instanceof OperationalActivity) {
        return true;
      }
    }
    return false;
  }

  public static boolean isValidEntityScenario(EObject ctx) {
    if (ctx instanceof Scenario) {
      Scenario s = (Scenario) ctx;
      if (CapellaServices.getService().isOperationalContext(s)
          && (s.getKind().equals(ScenarioKind.INTERACTION) || s.getKind().equals(ScenarioKind.UNSET))) {
        return (s.getOwnedInstanceRoles().size() == 0) || !containsOaActivity(s);
      }
      return false;

    } else {
      AbstractCapability capa = (AbstractCapability) ctx;
      return capa instanceof OperationalCapability;
    }
  }

  public static boolean isValidActivityScenario(EObject ctx) {
    if (ctx instanceof Scenario) {
      Scenario s = (Scenario) ctx;
      if (CapellaServices.getService().isOperationalContext(s)
          && (s.getKind().equals(ScenarioKind.INTERACTION) || s.getKind().equals(ScenarioKind.UNSET))) {
        return (s.getOwnedInstanceRoles().size() == 0) || containsOaActivity(s);
      }
      return false;
    } else {
      AbstractCapability capa = (AbstractCapability) ctx;
      return capa instanceof OperationalCapability;
    }
  }

  public static boolean isValidIS(EObject ctx) {
    if (ctx instanceof Scenario) {
      Scenario s = (Scenario) ctx;
      return (!CapellaServices.getService().isOperationalContext(s))
          && (s.getKind().equals(ScenarioKind.INTERFACE) || s.getKind().equals(ScenarioKind.UNSET));
    } else {
      AbstractCapability capa = (AbstractCapability) ctx;
      return !(capa instanceof OperationalCapability);
    }
  }

  public static boolean isValidES(EObject ctx) {
    if (ctx instanceof Scenario) {
      Scenario s = (Scenario) ctx;
      return (!CapellaServices.getService().isOperationalContext(s)) && (!CapellaServices.getService().isEPBSContext(s))
          && (s.getKind().equals(ScenarioKind.DATA_FLOW) || s.getKind().equals(ScenarioKind.UNSET));
    } else {
      AbstractCapability capa = (AbstractCapability) ctx;
      return !(capa instanceof OperationalCapability) && (!CapellaServices.getService().isEPBSContext(capa));
    }
  }

  public static boolean isValidFS(EObject ctx) {
    if (ctx instanceof Scenario) {
      Scenario s = (Scenario) ctx;
      return (!CapellaServices.getService().isOperationalContext(s)) && (!CapellaServices.getService().isEPBSContext(s))
          && (s.getKind().equals(ScenarioKind.FUNCTIONAL) || s.getKind().equals(ScenarioKind.UNSET));
    } else {
      AbstractCapability capa = (AbstractCapability) ctx;
      return !(capa instanceof OperationalCapability) && (!CapellaServices.getService().isEPBSContext(capa));
    }
  }

  private static boolean isSameInstanceRole(InteractionFragment a, InteractionFragment b) {
    InstanceRole ir1 = a.getCoveredInstanceRoles().get(0);
    InstanceRole ir2 = b.getCoveredInstanceRoles().get(0);

    return ir1 == ir2; // instance equality
  }

  /*
   * exec message --------------------------------------------------- | CASE A | CASE D | exec | begin | mess.IR | | end
   * | end | --------------------------------------------------- | CASE B | CASE C | message | begin | sending | |
   * mess.IR | receiving | ---------------------------------------------------
   */
  public static InteractionFragment getCorrespondingIFStart(EObject context, EObject obj, EObject correspondingFinish) {
    if (obj instanceof InteractionFragment) {
      InteractionFragment if_ = (InteractionFragment) obj;
      return if_;
    } else if (obj instanceof TimeLapse) {
      TimeLapse tl = (TimeLapse) obj;
      if (correspondingFinish instanceof TimeLapse) {
        // case A
        return tl.getStart();
      } else if (correspondingFinish instanceof SequenceMessage) {
        // case B
        return tl.getStart();
      }

    } else if (obj instanceof SequenceMessage) {
      SequenceMessage sm = (SequenceMessage) obj;
      if (correspondingFinish instanceof TimeLapse) {
        // case D
        return getMessageEndOnSameInstanceRole(sm, ((TimeLapse) correspondingFinish).getStart());
      } else if (correspondingFinish instanceof SequenceMessage && sm.getSendingEnd() != null) {
        // case C
        return sm.getSendingEnd();
      } else if (correspondingFinish instanceof SequenceMessage && sm.getSendingEnd() == null) {
        // case E
        return sm.getReceivingEnd();
      }

    }
    return null;
  }

  public static InteractionFragment getCorrespondingIFFinish(EObject context, EObject obj, EObject correspondingStart) {
    if (obj instanceof InteractionFragment) {
      InteractionFragment if_ = (InteractionFragment) obj;
      return if_;
    } else if (obj instanceof TimeLapse) {
      TimeLapse tl = (TimeLapse) obj;
      if (correspondingStart instanceof TimeLapse) {
        // case A
        return tl.getFinish();
      } else if (correspondingStart instanceof SequenceMessage) {
        // case B

        return tl.getFinish();
      }
    } else if (obj instanceof SequenceMessage) {
      SequenceMessage sm = (SequenceMessage) obj;
      if (correspondingStart instanceof TimeLapse) {
        // case D
        return getMessageEndOnSameInstanceRole(sm, ((TimeLapse) correspondingStart).getStart());
      } else if (correspondingStart instanceof SequenceMessage && !(sm.getReceivingEnd() == null)) {
        // case C
        return sm.getReceivingEnd();
      } else if (correspondingStart instanceof SequenceMessage && (sm.getReceivingEnd() == null)) {
        // case E
        return sm.getSendingEnd();
      } else if (correspondingStart instanceof InteractionFragment) {
        return getMessageEndOnSameInstanceRole(sm, ((InteractionFragment) correspondingStart));
      }
    }
    return null;
  }

  private static InteractionFragment getMessageEndOnSameInstanceRole(SequenceMessage sm,
      InteractionFragment startFragment) {
    InstanceRole ir1 = startFragment.getCoveredInstanceRoles().get(0);
    if ((sm.getSendingEnd() != null) && sm.getSendingEnd().getCovered().equals(ir1)) {
      return sm.getSendingEnd();
    }
    if ((sm.getReceivingEnd() != null) && sm.getReceivingEnd().getCovered().equals(ir1)) {
      return sm.getReceivingEnd();
    }

    // looking for the nearest IR

    return getNearestIR(sm, ir1);
  }

  private static InteractionFragment getNearestIR(SequenceMessage sm, InstanceRole ir1) {
    Scenario s = (Scenario) ir1.eContainer();
    int refPos = s.getOwnedInstanceRoles().indexOf(ir1);
    int posSend = 999;
    int posReceive = 999;

    if (sm.getReceivingEnd() != null) {
      posReceive = s.getOwnedInstanceRoles().indexOf(sm.getReceivingEnd().getCovered());
    } else {
      posReceive = s.getOwnedInstanceRoles().indexOf(sm.getSendingEnd().getCovered());
    }
    if (sm.getSendingEnd() != null) {
      posSend = s.getOwnedInstanceRoles().indexOf(sm.getSendingEnd().getCovered());
    } else {
      posSend = s.getOwnedInstanceRoles().indexOf(sm.getReceivingEnd().getCovered());
    }

    // For not using an absolute value.
    int dist1 = (refPos - posSend) * (refPos - posSend);
    int dist2 = (refPos - posReceive) * (refPos - posReceive);
    if (dist1 < dist2) {
      return sm.getSendingEnd();
    } else {
      return sm.getReceivingEnd();
    }
  }

  /**
   * Return available ExchangeItem for Scenario Diagmra(SD): we do this because the representation in the diagram is the
   * owned "ExchangeItemInstance" not the ExchageItem
   * 
   * @param context
   * @param allExchangeItemsFromModel
   * @return list of ExchangeItem Elements
   */
  public List<ExchangeItem> getAvailableExchangeItemForSD(EObject context,
      List<ExchangeItem> allExchangeItemsFromModel) {
    List<ExchangeItem> elmentAlReadyInDiagram = new ArrayList<ExchangeItem>();

    // Retrieve all the existing AbstractType element(i.e is "ExchangeItem") of ExchangeItemInstance in diagram
    if ((null != context) && (context instanceof DDiagram)) {
      DDiagram diagram = (DDiagram) context;
      Iterable<DDiagramElement> diagramElements = DiagramServices.getDiagramServices().getDiagramElements(diagram);
      for (DDiagramElement dDiagramElement : diagramElements) {
        EObject target = dDiagramElement.getTarget();
        if ((null != target) && (target instanceof InstanceRole)) {
          InstanceRole role = (InstanceRole) target;
          AbstractInstance representedInstance = role.getRepresentedInstance();
          if ((null != representedInstance) && (representedInstance instanceof ExchangeItemInstance)) {
            ExchangeItemInstance etInstance = (ExchangeItemInstance) representedInstance;
            AbstractType abstractType = etInstance.getAbstractType();
            if ((null != abstractType) && (abstractType instanceof ExchangeItem)) {
              elmentAlReadyInDiagram.add((ExchangeItem) abstractType);
            }
          }
        }
      }
    }

    // remove the existing ExchangeItem elements in diagram from allExchangeItems(kind: Shared, Event and Unset from
    // model)
    if (!allExchangeItemsFromModel.isEmpty()) {
      if (!elmentAlReadyInDiagram.isEmpty()) {
        allExchangeItemsFromModel.removeAll(elmentAlReadyInDiagram);
      }
    }

    return allExchangeItemsFromModel;
  }

}
