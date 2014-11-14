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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.PlatformUI;

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
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.SequenceMessageExt;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

public class SequenceDiagramServices {

  public static List<Execution> getExecutionsFromInstanceRoleOrParentExecution(EObject obj_p) {
    if (obj_p instanceof InstanceRole) {
      return getExecutionsFromInstanceRole((InstanceRole) obj_p);
    }
    return getExecutionFromExecution((Execution) obj_p);
  }

  private static Execution top(List<Execution> stack_p) {
    if (stack_p.size() == 0) {
      return null;
    }
    return (stack_p.get(stack_p.size() - 1));
  }

  /**
   * Return executions which are just under an other execution.
   * @param objP the execution
   * @return the list of result executions
   */
  private static List<Execution> getExecutionFromExecution(Execution execution) {
    return SequenceMessageExt.getExecutionFromExecution(execution);
  }

  public static List<InteractionFragment> getOrderedInteractionFragments(Scenario scenario_p) {
    if (scenario_p == null) {
      return new ArrayList<InteractionFragment>();
    }
    return scenario_p.getOwnedInteractionFragments();
  }

  private static List<Execution> getExecutionsFromInstanceRole(InstanceRole ir_p) {
    List<Execution> result = new ArrayList<Execution>();

    Scenario scenario = (Scenario) ir_p.eContainer();
    List<Execution> executionStack = new ArrayList<Execution>();
    for (InteractionFragment ifgt : getOrderedInteractionFragments(scenario)) {
      if (ifgt instanceof AbstractEnd) {
        AbstractEnd ae = (AbstractEnd) ifgt;
        if (ae.getCovered() == ir_p) {
          // if ae starts an exception, the execution is stacked
          // if ae ends an exception, the execution is unstacked
          for (TimeLapse laptime : scenario.getOwnedTimeLapses()) {
            if (laptime instanceof Execution) {
              Execution exec2 = (Execution) laptime;
              if (exec2.getCovered() == ir_p) {
                if (exec2.getStart() == ae) {
                  if (top(executionStack) == null) {
                    result.add(exec2);
                  }
                  executionStack.add(exec2);
                }
                if (exec2.getFinish() == ae) {
                  executionStack.remove(exec2);
                }
              }
            }
          }
        }
      }
    }

    return result;
  }

  public static EObject getSendingEndEvent(SequenceMessage message_p) {
    MessageEnd end = message_p.getSendingEnd();
    if (end == null) {
      return message_p.getReceivingEnd();
    }
    return end;
  }

  public static EObject getReceivingEndEvent(SequenceMessage message_p) {
    MessageEnd end = message_p.getReceivingEnd();
    if (end == null) {
      return message_p.getSendingEnd();
    }
    return end;
  }

  public static EObject getSendingEnd(SequenceMessage message_p) {
    MessageEnd end = message_p.getSendingEnd();
    if (end == null) {
      return message_p; // lost message case
    }

    Scenario sc = (Scenario) message_p.eContainer();
    for (TimeLapse exec : sc.getOwnedTimeLapses()) {
      if (exec.getFinish() == end) {
        return exec;
      }
    }
    Execution execution = getExecutionOfMessageEnd(end);

    return execution == null ? end.getCovered() : execution;
  }

  /**
   * return the execution which start the messageEnd taken in parameter.
   * @param end
   * @return
   */
  private static Execution getExecutionOfMessageEnd(MessageEnd end) {
    Scenario scenario = getScenario(end);
    InstanceRole currentIR = end.getCovered();
    List<Execution> executionStack = new ArrayList<Execution>();
    for (InteractionFragment ifgt : scenario.getOwnedInteractionFragments()) {
      if (ifgt instanceof AbstractEnd) {
        AbstractEnd ae = (AbstractEnd) ifgt;
        if (ae.getCovered() == currentIR) {
          // if ae starts an exception, the execution is stacked
          // if ae ends an exception, the execution is unstacked
          for (TimeLapse laptime : scenario.getOwnedTimeLapses()) {
            if (laptime instanceof Execution) {
              Execution execution = (Execution) laptime;
              if (execution.getCovered() == currentIR) {
                if (execution.getStart() == ae) {
                  executionStack.add(execution);
                }
                if (execution.getFinish() == ae) {
                  executionStack.remove(execution);
                }
              }
            }
          }
          if (ae == end) {
            return top(executionStack);
          }
        }
      }
    }
    return null;
  }

  public static EObject getReceivingEnd(SequenceMessage message_p) {
    MessageEnd end = message_p.getReceivingEnd();
    if (end == null) {
      return message_p; // found message case
    }
    Scenario sc = (Scenario) message_p.eContainer();
    for (TimeLapse exec : sc.getOwnedTimeLapses()) {
      if (exec.getStart() == end) {
        return exec;
      }
    }
    Execution execution = getExecutionOfMessageEnd(end);

    return execution == null ? end.getCovered() : execution;
  }

  /**
   * used in common.odesign oa.odesign, context.odesign, sequences.odesign
   * @param object_p
   * @return
   */
  public static Scenario getScenario(EObject object_p) {
    EObject result = object_p;

    if (result instanceof DSemanticDecorator) {
      result = ((DSemanticDecorator) result).getTarget();
    }

    while (!(result instanceof Scenario)) {
      result = result.eContainer();
    }

    return (Scenario) (result instanceof Scenario ? result : null);
  }

  public static SequenceMessage getInvocationMessage(SequenceMessage message_p) {
    return SequenceMessageExt.getOppositeSequenceMessage(message_p);
  }

  /**
   * Validate a reorder with the new API of sequence diagrams
   * @param object_p
   * @param startingEndPredecessorAfter
   * @param finishingEndPredecessorAfter
   * @return
   */
  public static EObject doReorder(EObject object_p, EObject startingEndPredecessorAfter, EObject finishingEndPredecessorAfter) {
    InteractionFragment newPredecessor = (InteractionFragment) startingEndPredecessorAfter;
    InteractionFragment newPredecessorOfEnd = (InteractionFragment) finishingEndPredecessorAfter;

    NamedElement ne = (NamedElement) object_p;


    if (object_p instanceof SequenceMessage) {
      SequenceMessage reorderedMessage = (SequenceMessage) object_p;
      reorderSequenceMessage(newPredecessor, newPredecessorOfEnd, reorderedMessage);
    } else if (object_p instanceof TimeLapse) {
      TimeLapse execution = (TimeLapse) object_p;
      reorderTimeLapse(newPredecessor, newPredecessorOfEnd, execution);
    } else if (object_p instanceof InteractionState) {
      InteractionState interactionState = (InteractionState) object_p;
      reorderInteractionState(newPredecessor, newPredecessorOfEnd, interactionState);
    } else if (object_p instanceof InteractionOperand) {
      InteractionOperand interactionOperand = (InteractionOperand) object_p;
      reorderInteractionOperand(newPredecessor, newPredecessorOfEnd, interactionOperand);
    }
    return object_p;
  }

  public static void validateOrdering(Scenario scenario_p) {
    if (!ScenarioExt.checkOrdering(scenario_p)) {
      orderingError(scenario_p);
    }
  }

  /**
   * @param newPredecessor_p
   * @param newPredecessorOfEnd_p
   * @param interactionState_p
   */
  private static void reorderInteractionState(InteractionFragment newPredecessor_p, InteractionFragment newPredecessorOfEnd_p,
      InteractionState interactionState_p) {
    Scenario scenario = (Scenario) interactionState_p.eContainer();
    scenario.getOwnedInteractionFragments().remove(interactionState_p);
    if (newPredecessorOfEnd_p == null) {
      scenario.getOwnedInteractionFragments().add(0, interactionState_p);
    } else {
      int position = scenario.getOwnedInteractionFragments().indexOf(newPredecessorOfEnd_p);
      scenario.getOwnedInteractionFragments().add(position + 1, interactionState_p);
    }
  }

  private static void reorderInteractionOperand(InteractionFragment newPredecessor_p, InteractionFragment newPredecessorOfEnd_p,
      InteractionOperand interactionOperand_p) {
    Scenario scenario = (Scenario) interactionOperand_p.eContainer();
    scenario.getOwnedInteractionFragments().remove(interactionOperand_p);
    if (newPredecessorOfEnd_p == null) {
      scenario.getOwnedInteractionFragments().add(0, interactionOperand_p);
    } else {
      int position = scenario.getOwnedInteractionFragments().indexOf(newPredecessor_p);
      scenario.getOwnedInteractionFragments().add(position + 1, interactionOperand_p);
    }
  }

  /**
   * @param newPredecessor
   * @param newPredecessorOfEnd
   * @param execution
   */
  private static void reorderTimeLapse(InteractionFragment newPredecessor, InteractionFragment newPredecessorOfEnd, TimeLapse execution) {
    EList<InteractionFragment> fragments = null;

    EObject container = execution.getStart().eContainer();
    fragments = ((Scenario) container).getOwnedInteractionFragments();

    fragments.remove(execution.getStart());
    fragments.remove(execution.getFinish());

    if (newPredecessor == null) {
      fragments.add(0, execution.getStart());
    } else {
      int position = fragments.indexOf(newPredecessor);
      fragments.add(position + 1, execution.getStart());
    }

    if (newPredecessorOfEnd == null) {
      fragments.add(0, execution.getFinish());
    } else {
      int position = fragments.indexOf(newPredecessorOfEnd);
      fragments.add(position + 1, execution.getFinish());
    }
  }

  /**
   * @param newPredecessor
   * @param newPredecessorOfEnd
   * @param reorderedMessage
   * @param scenario
   */
  private static void reorderSequenceMessage(InteractionFragment newPredecessor, InteractionFragment newPredecessorOfEnd, SequenceMessage reorderedMessage) {
    AbstractEnd debut = reorderedMessage.getSendingEnd();
    AbstractEnd fin = reorderedMessage.getReceivingEnd();
    EList<InteractionFragment> fragments = null;
    EList<SequenceMessage> messages = null;

    EObject container = debut != null ? debut.eContainer() : fin.eContainer();
    fragments = ((Scenario) container).getOwnedInteractionFragments();
    messages = ((Scenario) container).getOwnedMessages();

    if (newPredecessor == null) {
      // The message elements are pushed to the beginning of the diagram.
      fragments.remove(fin);
      if (fin != null) {
        fragments.add(0, fin);
      }
      fragments.remove(debut);
      if (debut != null) {
        fragments.add(0, debut);
      }

      // The message is pushed up to the top of the message list 
      messages.remove(reorderedMessage);
      messages.add(0, reorderedMessage);
    } else {
      fragments.remove(fin);
      fragments.remove(debut);
      int position2 = fragments.indexOf(newPredecessor);
      if (debut != null) {
        fragments.add(position2 + 1, debut);
      }
      int position = fragments.indexOf(newPredecessorOfEnd);
      if (fin != null) {
        fragments.add(position + 1, fin);
      }

      // Search the corresponding message to the new precedingEnd
      SequenceMessage previousMessage = findCorrespondingPreviousMessage(fragments, newPredecessor);
      if (previousMessage != null) {
        messages.remove(reorderedMessage);
        int positionMessage = messages.indexOf(previousMessage);
        messages.add(positionMessage + 1, reorderedMessage);
      } else {
        // The message is pushed up to the top of the message list 
        messages.remove(reorderedMessage);
        messages.add(0, reorderedMessage);
      }
    }
  }

  /**
   * Search the closest message corresponding to the predecessor passed in parameter. If the message can be deduced directly, go backward in the list of fragments to find the previous message.
   * Otherwise return null.
   * @param fragments_p List of fragments of the diagram
   * @param predecessor_p
   * @return
   */
  private static SequenceMessage findCorrespondingPreviousMessage(EList<InteractionFragment> fragments_p, InteractionFragment predecessor_p) {
    if (predecessor_p instanceof MessageEnd) {
      return ((MessageEnd) predecessor_p).getMessage();
    }
    // Search the previous 'interactionFragment:
    int pos = fragments_p.indexOf(predecessor_p);
    if (pos != 0) {
      return findCorrespondingPreviousMessage(fragments_p, fragments_p.get(pos - 1));
    }
    return null;
  }

  public static boolean allowCreateMessageCreation(EObject current, EObject preTarget, EObject preSource, EObject preMessageEndBefore,
      EObject preMessageEndAfter) {

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

  public static boolean allowDeleteMessageCreation(EObject current, EObject preTarget, EObject preSource, EObject preMessageEndBefore,
      EObject preMessageEndAfter) {
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
    List<InteractionFragment> fragments = new ArrayList<InteractionFragment>(scenario.getOwnedInteractionFragments().size());
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

  public static boolean allowMessageCreation(EObject current_p, EObject preSource_p, EObject preTarget_p, boolean withReturn_p, EObject endBefore,
      EObject endAfter) {
    InstanceRole sourceInstance;
    InstanceRole targetInstance;

    // If no source and no target, no need to continue.
    if (preSource_p == null) {
      return false;
    }
    if (preTarget_p == null) {
      return false;
    }

    if (preSource_p instanceof InstanceRole) {
      InstanceRole ir = (InstanceRole) preSource_p;
      sourceInstance = ir;
    } else {
      Execution exec = (Execution) preSource_p;
      sourceInstance = exec.getCovered();
    }

    if (preTarget_p instanceof InstanceRole) {
      InstanceRole ir = (InstanceRole) preTarget_p;
      targetInstance = ir;
    } else {
      Execution exec = (Execution) preTarget_p;
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
      return allowMessageCreationOnSharedData(preSource_p, preTarget_p, withReturn_p, sourceInstance, targetInstance, exchangeItemLifeLine);
    }
    // event case
    return allowMessageCreationOnEvent(preSource_p, preTarget_p, withReturn_p, sourceInstance, targetInstance, exchangeItemLifeLine);
  }

  /**
   * @param preSource_p
   * @param preTarget_p
   * @param withReturn_p
   * @param sourceInstance_p
   * @param targetInstance_p
   * @param exchangeItemLifeLine_p
   * @return
   */
  private static boolean allowMessageCreationOnEvent(EObject preSource_p, EObject preTarget_p, boolean withReturn_p, InstanceRole sourceInstance_p,
      InstanceRole targetInstance_p, InstanceRole exchangeItemLifeLine_p) {
    // in the event case, only ONE cases are allowed.
    // (1) asynch, EI->COMP source is IR
    if (!withReturn_p && (targetInstance_p.getRepresentedInstance() instanceof Part)) {
      // case 1
      return true;
    }
    return false;
  }

  /**
   * @param preSource_p
   * @param preTarget_p
   * @param withReturn_p
   * @param sourceInstance
   * @param targetInstance
   * @param exchangeItemLifeLine
   * @return
   */
  private static boolean allowMessageCreationOnSharedData(EObject preSource_p, EObject preTarget_p, boolean withReturn_p, InstanceRole sourceInstance,
      InstanceRole targetInstance, InstanceRole exchangeItemLifeLine) {
    boolean case2found = false; // <=> existing ACCEPT message found.
    boolean case3found = false; // <=> existing READ message found.
    // Go through existing SequenceMessages of the Scenario connecting sourceInstance and targetInstance.
    Scenario s = (Scenario) sourceInstance.eContainer();
    for (SequenceMessage existingSequenceMessage : s.getOwnedMessages()) {
      if (((existingSequenceMessage.getSendingEnd().getCovered() == sourceInstance) && (existingSequenceMessage.getReceivingEnd().getCovered() == targetInstance))
          || ((existingSequenceMessage.getSendingEnd().getCovered() == targetInstance) && (existingSequenceMessage.getReceivingEnd().getCovered() == sourceInstance))) {
        if (existingSequenceMessage.getReceivingEnd().getCovered() == exchangeItemLifeLine) {
          // SequenceMessage pointing to the ExchangeItem -> can be case 1 (WRITE message) or case 3 (READ message).
          if (SequenceMessageExt.getOppositeSequenceMessage(existingSequenceMessage) != null) {
            // There is a return SequenceMessage -> it's a case 3 (READ message)
            case3found = true;
          }
        }
        if ((existingSequenceMessage.getSendingEnd().getCovered() == exchangeItemLifeLine) && (existingSequenceMessage.getKind() != MessageKind.REPLY)) {
          // SequenceMessage starting from the ExchangeItem (and which is not a REPLY) -> it's an ACCEPT message.
          case2found = true;
        }
      }
    }

    // in the case of a message "to" or "from" an EI, only 3 cases are possibles:

    // (1) Asynchrone, Comp->EI, the target being an IR (write message)
    // (2) Asynchrone, EI->Comp, the source being an execution (possible from the version of 2013)
    // (3) Synchrone, Comp->EI, the target being an execution (possible from the version of 2013)

    // If the case (2) occurred it will be not possible to done the case (3) and vice versa.

    if (!withReturn_p && (sourceInstance.getRepresentedInstance() instanceof Part) && (preTarget_p instanceof InstanceRole)) {
      // case 1
      return true;
    }
    if ((!case3found) && !withReturn_p && (targetInstance.getRepresentedInstance() instanceof Part)
        && ((preSource_p instanceof Execution) || (preSource_p instanceof InstanceRole))) {
      // case 2
      return true;
    }
    if ((!case2found) && withReturn_p && (sourceInstance.getRepresentedInstance() instanceof Part)
        && ((preTarget_p instanceof Execution) || (preTarget_p instanceof InstanceRole))) {
      // case 3
      return true;
    }

    // all other case involving a EI is invalid
    return false;
  }

  public static AbstractFragment getFragmentFromContained(EObject context_p) {
    if (context_p instanceof AbstractFragment) {
      AbstractFragment af = (AbstractFragment) context_p;
      return af;
    }
    if (context_p instanceof InteractionOperand) {
      // Search the CF that contains the InteractionOperand
      Scenario scenario = (Scenario) context_p.eContainer();
      for (TimeLapse tl : scenario.getOwnedTimeLapses()) {
        if (tl instanceof CombinedFragment) {
          CombinedFragment cf = (CombinedFragment) tl;
          if (cf.getReferencedOperands().contains(context_p)) {
            return cf;
          }
        }
      }
    }
    return null;
  }

  public static void refreshOrdering(DDiagram ddiagram_p) {
    UIUtil.getInstance().refreshActiveDiagram(ddiagram_p);
  }

  public static void setCoveredIR(AbstractFragment fragment_p, List<InstanceRole> newCovered_p) {
    fragment_p.getStart().getCoveredInstanceRoles().clear();
    fragment_p.getStart().getCoveredInstanceRoles().addAll(newCovered_p);

    fragment_p.getFinish().getCoveredInstanceRoles().clear();
    fragment_p.getFinish().getCoveredInstanceRoles().addAll(newCovered_p);

    if (fragment_p instanceof CombinedFragment) {
      CombinedFragment new_name = (CombinedFragment) fragment_p;
      for (InteractionOperand op : new_name.getReferencedOperands()) {
        op.getCoveredInstanceRoles().clear();
        op.getCoveredInstanceRoles().addAll(newCovered_p);
      }
    }
  }

  public static List<String> getOperators(EObject context_p) {
    List<String> result = new ArrayList<String>(10);
    for (InteractionOperatorKind kind : InteractionOperatorKind.values()) {
      String str = kind.toString();
      if (!str.equals("") && !str.equals("ALT") && !str.equals("LOOP") && !str.equals("PAR")) {
        result.add(str);
      }
    }
    return result;
  }

  public static void removeInstanceRoles(EObject context_p, Collection<EObject> result, Collection<EObject> current) {
    Set<InstanceRole> irToRemove = new HashSet<InstanceRole>();
    for (EObject obj : current) {
      if (obj instanceof InstanceRole) {
        InstanceRole ir = (InstanceRole) obj;
        if (!result.contains(ir.getRepresentedInstance())) {
          irToRemove.add(ir);
        }
      }
    }
    CapellaDeleteCommand mdc = new CapellaDeleteCommand(MDEAdapterFactory.getExecutionManager(), irToRemove, false, false, true);
    if (mdc.canExecute()) {
      // Do execute the command !
      mdc.execute();
    }

  }

  /**
   * @param scenario_p
   */
  public static void orderingError(Scenario scenario_p) {
    OperationCanceledException exception = new OperationCanceledException(Messages.SequenceDiagramServices_3);
    IStatus errorStatus = new Status(IStatus.ERROR, SiriusViewActivator.ID, 0, Messages.SequenceDiagramServices_0, exception);
    ErrorDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.SequenceDiagramServices_1, Messages.SequenceDiagramServices_2,
        errorStatus);
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

  public static boolean isValidEntityScenario(EObject ctx_p) {
    if (ctx_p instanceof Scenario) {
      Scenario s = (Scenario) ctx_p;
      if (CapellaServices.getService().isOperationalContext(s) && (s.getKind().equals(ScenarioKind.INTERACTION) || s.getKind().equals(ScenarioKind.UNSET))) {
        return (s.getOwnedInstanceRoles().size() == 0) || !containsOaActivity(s);
      }
      return false;

    } else {
      AbstractCapability capa = (AbstractCapability) ctx_p;
      return capa instanceof OperationalCapability;
    }
  }

  public static boolean isValidActivityScenario(EObject ctx_p) {
    if (ctx_p instanceof Scenario) {
      Scenario s = (Scenario) ctx_p;
      if (CapellaServices.getService().isOperationalContext(s) && (s.getKind().equals(ScenarioKind.INTERACTION) || s.getKind().equals(ScenarioKind.UNSET))) {
        return (s.getOwnedInstanceRoles().size() == 0) || containsOaActivity(s);
      }
      return false;
    } else {
      AbstractCapability capa = (AbstractCapability) ctx_p;
      return capa instanceof OperationalCapability;
    }
  }

  public static boolean isValidIS(EObject ctx_p) {
    if (ctx_p instanceof Scenario) {
      Scenario s = (Scenario) ctx_p;
      return (!CapellaServices.getService().isOperationalContext(s)) && (s.getKind().equals(ScenarioKind.INTERFACE) || s.getKind().equals(ScenarioKind.UNSET));
    } else {
      AbstractCapability capa = (AbstractCapability) ctx_p;
      return !(capa instanceof OperationalCapability);
    }
  }

  public static boolean isValidES(EObject ctx_p) {
    if (ctx_p instanceof Scenario) {
      Scenario s = (Scenario) ctx_p;
      return (!CapellaServices.getService().isOperationalContext(s)) && (!CapellaServices.getService().isEPBSContext(s))
             && (s.getKind().equals(ScenarioKind.DATA_FLOW) || s.getKind().equals(ScenarioKind.UNSET));
    } else {
      AbstractCapability capa = (AbstractCapability) ctx_p;
      return !(capa instanceof OperationalCapability) && (!CapellaServices.getService().isEPBSContext(capa));
    }
  }

  public static boolean isValidFS(EObject ctx_p) {
    if (ctx_p instanceof Scenario) {
      Scenario s = (Scenario) ctx_p;
      return (!CapellaServices.getService().isOperationalContext(s)) && (!CapellaServices.getService().isEPBSContext(s))
             && (s.getKind().equals(ScenarioKind.FUNCTIONAL) || s.getKind().equals(ScenarioKind.UNSET));
    } else {
      AbstractCapability capa = (AbstractCapability) ctx_p;
      return !(capa instanceof OperationalCapability) && (!CapellaServices.getService().isEPBSContext(capa));
    }
  }

  private static boolean isSameInstanceRole(InteractionFragment a, InteractionFragment b) {
    InstanceRole ir1 = a.getCoveredInstanceRoles().get(0);
    InstanceRole ir2 = b.getCoveredInstanceRoles().get(0);

    return ir1 == ir2; // instance equality
  }

  /*
   * exec message --------------------------------------------------- | CASE A | CASE D | exec | begin | mess.IR | | end | end |
   * --------------------------------------------------- | CASE B | CASE C | message | begin | sending | | mess.IR | receiving |
   * ---------------------------------------------------
   */
  public static InteractionFragment getCorrespondingIFStart(EObject context_p, EObject obj_p, EObject correspondingFinish_p) {
    if (obj_p instanceof InteractionFragment) {
      InteractionFragment if_ = (InteractionFragment) obj_p;
      return if_;
    } else if (obj_p instanceof TimeLapse) {
      TimeLapse tl = (TimeLapse) obj_p;
      if (correspondingFinish_p instanceof TimeLapse) {
        // case A
        return tl.getStart();
      } else if (correspondingFinish_p instanceof SequenceMessage) {
        // case B
        return tl.getStart();
      }

    } else if (obj_p instanceof SequenceMessage) {
      SequenceMessage sm = (SequenceMessage) obj_p;
      if (correspondingFinish_p instanceof TimeLapse) {
        // case D
        return getMessageEndOnSameInstanceRole(sm, ((TimeLapse) correspondingFinish_p).getStart());
      } else if (correspondingFinish_p instanceof SequenceMessage) {
        // case C
        return sm.getSendingEnd();
      }

    }
    return null;
  }

  public static InteractionFragment getCorrespondingIFFinish(EObject context_p, EObject obj_p, EObject correspondingStart_p) {
    if (obj_p instanceof InteractionFragment) {
      InteractionFragment if_ = (InteractionFragment) obj_p;
      return if_;
    } else if (obj_p instanceof TimeLapse) {
      TimeLapse tl = (TimeLapse) obj_p;
      if (correspondingStart_p instanceof TimeLapse) {
        // case A
        return tl.getFinish();
      } else if (correspondingStart_p instanceof SequenceMessage) {
        // case B
        return tl.getFinish();
      }
    } else if (obj_p instanceof SequenceMessage) {
      SequenceMessage sm = (SequenceMessage) obj_p;
      if (correspondingStart_p instanceof TimeLapse) {
        // case D
        return getMessageEndOnSameInstanceRole(sm, ((TimeLapse) correspondingStart_p).getStart());
      } else if (correspondingStart_p instanceof SequenceMessage) {
        // case C
        return sm.getReceivingEnd();
      } else if (correspondingStart_p instanceof InteractionFragment) {
        return getMessageEndOnSameInstanceRole(sm, ((InteractionFragment) correspondingStart_p));
      }
    }
    return null;
  }

  private static InteractionFragment getMessageEndOnSameInstanceRole(SequenceMessage sm, InteractionFragment startFragment) {
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
    }
    if (sm.getSendingEnd() != null) {
      posSend = s.getOwnedInstanceRoles().indexOf(sm.getSendingEnd().getCovered());
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
   * Return available ExchangeItem for Scenario Diagmra(SD): we do this because the representation in the diagram is the owned "ExchangeItemInstance" not the
   * ExchageItem
   * @param context_p
   * @param allExchangeItemsFromModel
   * @return list of ExchangeItem Elements
   */
  public List<ExchangeItem> getAvailableExchangeItemForSD(EObject context_p, List<ExchangeItem> allExchangeItemsFromModel) {
    List<ExchangeItem> elmentAlReadyInDiagram = new ArrayList<ExchangeItem>();

    // Retrieve all the existing AbstractType element(i.e is "ExchangeItem") of ExchangeItemInstance in diagram
    if ((null != context_p) && (context_p instanceof DDiagram)) {
      DDiagram diagram = (DDiagram) context_p;
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

    // remove the existing ExchangeItem elements in diagram from allExchangeItems(kind: Shared, Event and Unset from model)
    if (!allExchangeItemsFromModel.isEmpty()) {
      if (!elmentAlReadyInDiagram.isEmpty()) {
        allExchangeItemsFromModel.removeAll(elmentAlReadyInDiagram);
      }
    }

    return allExchangeItemsFromModel;
  }

  public void reconnectDuration(EObject element, EObject source, EObject target) {
    System.out.println("element=" + element);
  }
}
