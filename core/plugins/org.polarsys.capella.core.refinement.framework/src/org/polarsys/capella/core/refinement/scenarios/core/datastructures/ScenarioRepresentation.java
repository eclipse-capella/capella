/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.scenarios.core.datastructures;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.ExtensionPriorityComparator;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.helpers.interaction.services.FragmentEndExt;
import org.polarsys.capella.core.data.helpers.interaction.services.FragmentEndExt.FRAGMENT_END_TYPE;
import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt;
import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt.COMPONENT_TYPE;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.communication.SignalInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.AbstractFragment;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.CreationEvent;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.ExecutionEvent;
import org.polarsys.capella.core.data.interaction.FragmentEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.model.helpers.AbstractFragmentExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.refinement.scenarios.core.Messages;
import org.polarsys.capella.core.refinement.scenarios.core.RefinementServices;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IScenarioRepresentationListener;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class ScenarioRepresentation {

  private static final String REFINEMENT_FRAMEWORK_PLUGIN_ID = "org.polarsys.capella.core.refinement.framework"; //$NON-NLS-1$

  private static final String SCENARIO_REPRESENTATION_LISTENER_EXTENSION_ID = "scenarioRepresentationListenerExtension"; //$NON-NLS-1$
  private Set<CapellaElement> clonedElements = null;
  private List<IScenarioRepresentationListener> listeners = new ArrayList<>();
  private Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);
  private Tree<InteractionFragment> messageTree = null;

  private Scenario scenario = null;

  /**
   * Constructor.
   * @param scenario
   */
  public ScenarioRepresentation(Scenario scenario) {
    if (scenario != null) {
      this.scenario = scenario;
      messageTree = new Tree<>();

      Node<InteractionFragment> rootNode = new Node<>(null);
      messageTree.setRootElement(rootNode);

      /**
       * add listeners
       */
      Comparator<IConfigurationElement> priorityComparator = new ExtensionPriorityComparator();
      List<IConfigurationElement> listenerProvider =
          Arrays.asList(ExtensionPointHelper.getConfigurationElements(REFINEMENT_FRAMEWORK_PLUGIN_ID, SCENARIO_REPRESENTATION_LISTENER_EXTENSION_ID));
      Collections.sort(listenerProvider, priorityComparator);
      for (IConfigurationElement configurationElement : listenerProvider) {
        /** logging */
        String loggedMsg = MessageFormat.format(Messages.NewListenerProvider, configurationElement.getAttribute(ExtensionPointHelper.ATT_ID));
        logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, scenario));
        /** */
        IScenarioRepresentationListener listener =
            (IScenarioRepresentationListener) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
        if (listener != null) {
          listener.scenarioCreated(messageTree);
          listeners.add(listener);
        }
      }

      fillTree(scenario.getOwnedInteractionFragments(), rootNode);
    }
  }

  /**
   * 
   */
  public void addChildAfter(Node<InteractionFragment> parent, Node<InteractionFragment> child, Node<InteractionFragment> previousNode) {
    parent.addChildAfter(child, previousNode);
    updateListeners();
  }

  /**
   * 
   */
  private void addClonedElement(CapellaElement elt) {
    if (null == clonedElements) {
      clonedElements = new HashSet<>();
    }
    clonedElements.add(elt);
  }

  /**
   * 
   */
  public void addFirstChild(Node<AbstractEnd> parent, Node<AbstractEnd> child) {
    parent.addFirstChild(child);
    updateListeners();
  }

  /**
   * 
   */
  public void addLastChild(Node<InteractionFragment> parent, Node<InteractionFragment> child) {
    parent.addLastChild(child);
    updateListeners();
  }

  /**
   * 
   */
  public void clean() {
    for (Node<InteractionFragment> node : walk()) {
      node.relatedNode = null;
    }
  }

  /**
   * 
   */
  public void cleanClonedElements() {
    if (null != clonedElements) {
      for (CapellaElement elt : clonedElements) {
        CapellaElementExt.cleanTraces(elt);
        elt.destroy();
      }
    }
  }

  /**
   * This method allows to clone a 'CombinedFragment. Before cloning the element passed as parameter, we check if it is not already itself a clone, or if
   * it is not linked to an element already cloned (via a traceability link). If this is not the case, we clone it.
   * @param srcFragment
   * @return the cloned 'CombinedFragment'
   */
  private CombinedFragment cloneCombinedFragment(CombinedFragment srcFragment) {
    CombinedFragment tgtFragment = null;

    if (srcFragment != null) {
      /** Check if 'SequenceMessage' is on the source side or on the target side */
      if (srcFragment.eContainer().equals(scenario)) {
        tgtFragment = srcFragment;
      } else {
        /** if the 'CombinedFragment' doesn't exist yet on target, we have to create it */
        List<CapellaElement> lst = RefinementLinkExt.getRefinementRelatedSourceElements(srcFragment, InteractionPackage.Literals.COMBINED_FRAGMENT);
        for (CapellaElement elt : lst) {
          if (elt.eContainer().equals(scenario)) {
            tgtFragment = (CombinedFragment) elt;
          }
        }
        if (tgtFragment == null) {
          tgtFragment = InteractionFactory.eINSTANCE.createCombinedFragment();
          tgtFragment.setName(srcFragment.getName());
          tgtFragment.setOperator(srcFragment.getOperator());

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtFragment, srcFragment, scenario);
          /** register combined fragment creation */
          addClonedElement(tgtFragment);
        }
      }
    }

    return tgtFragment;
  }

  /**
   * This method allows to clone a 'ExecutionEnd. Before cloning the element passed as parameter, we check if it is not already itself a clone, or if
   * it is not linked to an element already cloned (via a traceability link). If this is not the case, we clone it.
   * @param srcExecEnd
   * @return the cloned 'ExecutionEnd'
   */
  private ExecutionEnd cloneExecutionEnd(ExecutionEnd srcExecEnd) {
    ExecutionEnd tgtExecEnd = null;

    if (srcExecEnd != null) {
      /** Check if 'ExecutionEnd' is on the source side or on the target side */ 
      if (srcExecEnd.eContainer().equals(scenario)) {
        tgtExecEnd = srcExecEnd;
      } else {
        /** if the 'ExecutionEnd' doesn't exist yet on target, we have to create it */
        List<CapellaElement> lst = RefinementLinkExt.getRefinementRelatedSourceElements(srcExecEnd, InteractionPackage.Literals.EXECUTION_END);
        for (CapellaElement elt : lst) {
          if ((elt.eContainer() != null) && elt.eContainer().equals(scenario)) {
            tgtExecEnd = (ExecutionEnd) elt;
          }
        }
        if (tgtExecEnd == null) {
          tgtExecEnd = InteractionFactory.eINSTANCE.createExecutionEnd();
          ExecutionEvent execEvt = InteractionFactory.eINSTANCE.createExecutionEvent();
          tgtExecEnd.setEvent(execEvt);
          scenario.getOwnedEvents().add(execEvt);

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtExecEnd, srcExecEnd, scenario);
          RefinementLinkExt.createRefinementTraceabilityLink(execEvt, srcExecEnd.getEvent(), scenario);
        }
      }
    }

    return tgtExecEnd;
  }

  /**
   * @param srcTree
   */
  private void cloneExecutions(ScenarioRepresentation srcTree) {
    List<TimeLapse> srcExecLst = srcTree.getScenario().getOwnedTimeLapses();
    for (TimeLapse srcExec : srcExecLst) {
      Execution tgtExec = null;
      for (CapellaElement exec : RefinementLinkExt.getRefinementRelatedSourceElements(srcExec, InteractionPackage.Literals.EXECUTION)) {
        if (exec.eContainer().equals(scenario)) {
          tgtExec = (Execution) exec;
        }
      }

   	  /** if the 'Execution' doesn't exist yet on target, we have to create it */
      if (tgtExec == null) {
        AbstractEnd startMsgEnd = null;
        AbstractEnd finishMsgEnd = null;

        for (CapellaElement msgEnd : RefinementLinkExt.getRefinementRelatedSourceElements(srcExec.getStart(), InteractionPackage.Literals.ABSTRACT_END)) {
          if (msgEnd.eContainer().equals(scenario)) {
            startMsgEnd = (AbstractEnd) msgEnd;
          }
        }

        for (CapellaElement msgEnd : RefinementLinkExt.getRefinementRelatedSourceElements(srcExec.getFinish(), InteractionPackage.Literals.ABSTRACT_END)) {
          if (msgEnd.eContainer().equals(scenario)) {
            finishMsgEnd = (AbstractEnd) msgEnd;
          }
        }

        if ((startMsgEnd != null) && (finishMsgEnd != null)) {
          tgtExec = InteractionFactory.eINSTANCE.createExecution();
          tgtExec.setStart(startMsgEnd);
          tgtExec.setFinish(finishMsgEnd);

          scenario.getOwnedTimeLapses().add(tgtExec);

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtExec, srcExec, scenario);
        }
        else if (startMsgEnd == null && finishMsgEnd instanceof ExecutionEnd) {
          // [Refinement] Scenario containing an asynchronous message into empty Physical/EPBS
            Event execEvt = finishMsgEnd.getEvent();
            CapellaElementExt.cleanTraces(execEvt);
            execEvt.destroy();
            CapellaElementExt.cleanTraces(finishMsgEnd);
            finishMsgEnd.destroy();
        }
      }
    }
  }

  /**
   * This method allows to clone a 'FragmentEnd. Before cloning the element passed as parameter, we check if it is not already itself a clone, or if
   * it is not linked to an element already cloned (via a traceability link). If this is not the case, we clone it.
   */
  private FragmentEnd cloneFragmentEnd(FragmentEnd srcFragmentEnd, AbstractFragment tgtFragment) {
    FragmentEnd tgtFragmentEnd = null;

    if (srcFragmentEnd != null) {
      /** Check if the 'FragmentEnd' is on the source side or on the target side */
      if (srcFragmentEnd.eContainer().equals(scenario)) {
        tgtFragmentEnd = srcFragmentEnd;
      } else {
        /** if the 'FragmentEnd' doesn't exist yet on target, we have to create it */
        List<CapellaElement> lst = RefinementLinkExt.getRefinementRelatedSourceElements(srcFragmentEnd, InteractionPackage.Literals.FRAGMENT_END);
        for (CapellaElement elt : lst) {
          if ((elt.eContainer() != null) && elt.eContainer().equals(scenario)) {
            tgtFragmentEnd = (FragmentEnd) elt;
          }
        }
        if (tgtFragmentEnd == null) {
          tgtFragmentEnd = InteractionFactory.eINSTANCE.createFragmentEnd();
          tgtFragmentEnd.setName(srcFragmentEnd.getName());
          FRAGMENT_END_TYPE type = FragmentEndExt.getFragmentEndType(srcFragmentEnd);
          switch (type) {
            case FINISH:
              tgtFragment.setFinish(tgtFragmentEnd);
            break;
            case START:
              tgtFragment.setStart(tgtFragmentEnd);
            break;
            case UNDEFINED:
            break;
          }

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtFragmentEnd, srcFragmentEnd, scenario);
          /** register fragment end creation */
          addClonedElement(tgtFragmentEnd);
        }
      }
    }

    return tgtFragmentEnd;
  }

  /**
   * This method allows to clone an 'InstanceRole'. Before to clone the element given as parameter, we check if it's not already a clone , or if it's not linked
   * to an element already cloned (through a traceability link). if not, we clone it.
   * @param srcRole
   * @param component
   * @param tgtElement
   * @return the cloned 'InstanceRole'
   */
  private InstanceRole cloneInstanceRole(InstanceRole srcRole, AbstractInstance abstractInstance, NamedElement tgtElement) {
    InstanceRole tgtRole = null;

    if (srcRole != null) {
      /** we check if the 'InstanceRole' is on source or target side */
      if (srcRole.eContainer().equals(scenario)) {
        tgtRole = srcRole;
      } else {
        List<CapellaElement> rolesLst = RefinementLinkExt.getRefinementRelatedSourceElements(srcRole, InteractionPackage.Literals.INSTANCE_ROLE);
        for (CapellaElement role : rolesLst) {
          InstanceRole instRole = (InstanceRole) role;
          AbstractInstance cpntInst = instRole.getRepresentedInstance();
          if (instRole.eContainer().equals(scenario) && (abstractInstance == null || cpntInst.equals(abstractInstance) || cpntInst instanceof SignalInstance)) {
              tgtRole = instRole;
          }
        }
        if (tgtRole == null) {
          for (InstanceRole instRole : scenario.getOwnedInstanceRoles()) {
            AbstractInstance cpntInst = instRole.getRepresentedInstance();
            if ((cpntInst != null) && cpntInst.equals(abstractInstance)) {
              tgtRole = instRole;
              if (!RefinementLinkExt.isLinkedTo(tgtRole, srcRole)) {
                /** adding traceability link */
                RefinementLinkExt.createRefinementTraceabilityLink(tgtRole, srcRole, scenario);
              }
            }
          }
        }

        /** if the 'InstanceRole' doesn't exist yet on target, we have to create it */
        if ((tgtRole == null) && (abstractInstance != null)) {
          tgtRole = InteractionFactory.eINSTANCE.createInstanceRole();
          tgtRole.setRepresentedInstance(abstractInstance);
          tgtRole.setName(abstractInstance.getName());

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtRole, srcRole, scenario);
          /** register instance role creation */
          addClonedElement(tgtRole);
        }
      }
    }

    return tgtRole;
  }

  /**
   * This method allows to clone a set of 'InstanceRole'. Before to clone the element given as parameter, we check if it's not already a clone , or if it's not
   * linked to an element already cloned (through a traceability link). if not, we clone it.
   * @param srcRoles
   * @param component
   * @param tgtElement
   * @return the cloned 'InstanceRole'
   */
  private List<InstanceRole> cloneInstanceRoles(List<InstanceRole> srcRoles, AbstractInstance abstractInstance, NamedElement tgtElement) {
    List<InstanceRole> tgtRoles = new ArrayList<>();

    for (InstanceRole srcRole : srcRoles) {
      if (tgtElement instanceof Component) {
        for (Partition part : ((Component) tgtElement).getOwnedPartitions()) {
          if (part instanceof Part) {
            InstanceRole tgtRole = cloneInstanceRole(srcRole, part, tgtElement);
            if (tgtRole != null) {
              if (!tgtRoles.contains(tgtRole)) {
                tgtRoles.add(tgtRole);
              }
              if (!scenario.getOwnedInstanceRoles().contains(tgtRole)) {
                scenario.getOwnedInstanceRoles().add(tgtRole);
              }
            }
          }
        }
      }
    }

    return tgtRoles;
  }

  /**
   * This method allows to clone a 'InteractionOperand'. Before cloning the element passed as parameter, we check if it is not already itself a
   * clone, or if it is not linked to an element already cloned (via a traceability link). If this is not the case, we clone it.
   */
  private InteractionOperand cloneInteractionOperand(InteractionOperand srcOperand) {
    InteractionOperand tgtOperand = null;

    if (srcOperand != null) {
      /** Check if the 'InteractionOperand' is on the source side or on the target side */
      if (srcOperand.eContainer().equals(scenario)) {
        tgtOperand = srcOperand;
      } else {
        /** If the 'InteractionOperand' doesn't exist yet on target, we have to create it */
        List<CapellaElement> lst = RefinementLinkExt.getRefinementRelatedSourceElements(srcOperand, InteractionPackage.Literals.INTERACTION_OPERAND);
        for (CapellaElement elt : lst) {
          if ((elt.eContainer() != null) && elt.eContainer().equals(scenario)) {
            tgtOperand = (InteractionOperand) elt;
          }
        }
        if (tgtOperand == null) {
          tgtOperand = InteractionFactory.eINSTANCE.createInteractionOperand();
          tgtOperand.setName(srcOperand.getName());
          tgtOperand.setGuard(srcOperand.getGuard());

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtOperand, srcOperand, scenario);
          /** register interaction operand creation */
          addClonedElement(tgtOperand);
        }
      }
    }

    return tgtOperand;
  }

  /**
   * This method allows to clone a 'InteractionUse'. Before cloning the element passed as parameter, we check if it is not already itself a clone,
   * or if it is not linked to an element already cloned (via a traceability link). If this is not the case, we clone it.
   * @param srcUse
   * @return the cloned 'InteractionUse'
   */
  private InteractionUse cloneInteractionUse(InteractionUse srcUse) {
    InteractionUse tgtUse = null;

    if (srcUse != null) {
      /** Check if 'SequenceMessage' is on the source side or on the target side */
      if (srcUse.eContainer().equals(scenario)) {
        tgtUse = srcUse;
      } else {
        /** if the 'CombinedFragment' doesn't exist yet on target, we have to create it */
        List<CapellaElement> lst = RefinementLinkExt.getRefinementRelatedSourceElements(srcUse, InteractionPackage.Literals.INTERACTION_USE);
        for (CapellaElement elt : lst) {
          if (elt.eContainer().equals(scenario)) {
            tgtUse = (InteractionUse) elt;
          }
        }
        if (tgtUse == null) {
          tgtUse = InteractionFactory.eINSTANCE.createInteractionUse();
          tgtUse.setName(srcUse.getName());

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtUse, srcUse, scenario);
          /** register combined fragment creation */
          addClonedElement(tgtUse);
        }
      }
    }

    return tgtUse;
  }

  /**
   * This method allows to clone a 'MessageEnd'. Before cloning the element passed as parameter, we check if it is not already itself a clone, or
   * if it is not linked to an element already cloned (via a traceability link). If this is not the case, we clone it.
   */
  private MessageEnd cloneMessageEnd(MessageEnd srcMsgEnd, SequenceMessage tgtMsg) {
    MessageEnd tgtMsgEnd = null;

    if (srcMsgEnd != null) {
      /** Check if the 'MessageEnd' is on the source side or on the target side */
      if (srcMsgEnd.eContainer().equals(scenario)) {
        tgtMsgEnd = srcMsgEnd;
      } else {
        /** If the 'MessageEnd' doesn't exist yet on target, we have to create it */
        List<CapellaElement> lst = RefinementLinkExt.getRefinementRelatedSourceElements(srcMsgEnd, InteractionPackage.Literals.MESSAGE_END);
        for (CapellaElement elt : lst) {
          if ((elt.eContainer() != null) && elt.eContainer().equals(scenario)) {
            tgtMsgEnd = (MessageEnd) elt;
          }
        }
        if (tgtMsgEnd == null) {
          tgtMsgEnd = InteractionFactory.eINSTANCE.createMessageEnd();
          COMPONENT_TYPE type = MessageEndExt.getMessageEndType(srcMsgEnd);
          switch (type) {
            case RECEIVER: {
              tgtMsgEnd.setName(MessageFormat.format(Messages.MessageEndReceiverNamePattern, tgtMsg.getName(), tgtMsg.getKind()));
              tgtMsg.setReceivingEnd(tgtMsgEnd);

              Event srcEvt = srcMsgEnd.getEvent();
              if (srcEvt != null) {
                if (srcEvt instanceof EventReceiptOperation) {
                  AbstractEventOperation op = ((EventReceiptOperation) srcEvt).getOperation();
                  AbstractEventOperation srcOp = getOperation(srcMsgEnd, tgtMsg, op);
                  if (srcOp != null) {
                    EventReceiptOperation rcvOp = InteractionFactory.eINSTANCE.createEventReceiptOperation();
                    rcvOp.setOperation(srcOp);
                    tgtMsgEnd.setEvent(rcvOp);
                    rcvOp.setName(EcoreUtil2.getUniqueName(rcvOp, scenario, InteractionPackage.Literals.SCENARIO__OWNED_EVENTS,
                        ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, MessageFormat.format(Messages.EventReceiptOperationNamePattern, tgtMsg
                            .getName(), tgtMsg.getKind())));
                    scenario.getOwnedEvents().add(rcvOp);
                    /** adding traceability link */
                    RefinementLinkExt.createRefinementTraceabilityLink(rcvOp, srcMsgEnd.getEvent(), scenario);
                    /** register event creation */
                    addClonedElement(rcvOp);
                  }
                } else if (srcEvt instanceof CreationEvent) {
                  CreationEvent rcvOp = InteractionFactory.eINSTANCE.createCreationEvent();
                  tgtMsgEnd.setEvent(rcvOp);
                  rcvOp.setName(EcoreUtil2.getUniqueName(rcvOp, scenario, InteractionPackage.Literals.SCENARIO__OWNED_EVENTS,
                      ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, MessageFormat.format(Messages.CreationEventNamePattern, tgtMsg.getName(),
                          tgtMsg.getKind())));
                  scenario.getOwnedEvents().add(rcvOp);
                  /** adding traceability link */
                  RefinementLinkExt.createRefinementTraceabilityLink(rcvOp, srcMsgEnd.getEvent(), scenario);
                  /** register event creation */
                  addClonedElement(rcvOp);
                }
              }
              break;
            }
            case SENDER: {
              tgtMsgEnd.setName(MessageFormat.format(Messages.MessageEndSenderNamePattern, tgtMsg.getName(), tgtMsg.getKind()));
              EventSentOperation srcEvt = (EventSentOperation) srcMsgEnd.getEvent();
              if (srcEvt != null) {
                EventSentOperation sndOp = InteractionFactory.eINSTANCE.createEventSentOperation();
                AbstractEventOperation op = srcEvt.getOperation();
                AbstractEventOperation srcOp = getOperation(srcMsgEnd, tgtMsg, op);
                if (srcOp != null) {
                  sndOp.setOperation(srcOp);
                }
                tgtMsgEnd.setEvent(sndOp);
                tgtMsg.setSendingEnd(tgtMsgEnd);
                sndOp.setName(EcoreUtil2.getUniqueName(sndOp, scenario, InteractionPackage.Literals.SCENARIO__OWNED_EVENTS,
                    ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, MessageFormat.format(Messages.EventSentOperationNamePattern,
                        tgtMsg.getName(), tgtMsg.getKind())));
                scenario.getOwnedEvents().add(sndOp);
                /** adding traceability link */
                RefinementLinkExt.createRefinementTraceabilityLink(sndOp, srcMsgEnd.getEvent(), scenario);
                /** register event creation */
                addClonedElement(sndOp);
              }
              break;
            }
            case UNDEFINED:
            break;
          }

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtMsgEnd, srcMsgEnd, scenario);
          /** register message end creation */
          addClonedElement(tgtMsgEnd);
        }
      }
    }

    return tgtMsgEnd;
  }

  /**
   * This method allows to clone a node, and its related elements. The new elements ('InstanceRole', 'SequenceMessage' and 'AbstractEnd') are added to the
   * current scenario.
   * @param srcElt
   * @param component
   * @param tgtElement
   * @return Node<MessageEnd>
   */
  public Node<InteractionFragment> cloneNode(Node<InteractionFragment> srcElt, AbstractInstance part, NamedElement tgtElement) {
    Node<InteractionFragment> clonedElt = null;

    if (srcElt != null) {
      clonedElt = new Node<>(null);

      InteractionFragment interactionFragment = srcElt.getData();
      if (interactionFragment instanceof AbstractEnd) {
        AbstractEnd abstractEnd = (AbstractEnd) interactionFragment;

        if (part != null) {
          InstanceRole tgtRole = cloneInstanceRole(abstractEnd.getCovered(), part, tgtElement);
          if (tgtRole != null) {
            if (!scenario.getOwnedInstanceRoles().contains(tgtRole)) {
              scenario.getOwnedInstanceRoles().add(tgtRole);
            }

            if (abstractEnd instanceof MessageEnd) {
              MessageEnd srcMsgEnd = (MessageEnd) abstractEnd;
              SequenceMessage tgtMsg = cloneSequenceMessage(srcMsgEnd.getMessage());
              if ((tgtMsg != null) && (!scenario.getOwnedMessages().contains(tgtMsg))) {
                scenario.getOwnedMessages().add(tgtMsg);
              }

              MessageEnd tgtMsgEnd = cloneMessageEnd(srcMsgEnd, tgtMsg);
              if (tgtMsgEnd != null) {
                tgtMsgEnd.getCoveredInstanceRoles().add(tgtRole);
                if (!scenario.getOwnedInteractionFragments().contains(tgtMsgEnd)) {
                  scenario.getOwnedInteractionFragments().add(tgtMsgEnd);
                }
              }
              clonedElt.setData(tgtMsgEnd);
            } else if (abstractEnd instanceof ExecutionEnd) {
              ExecutionEnd srcExecEnd = (ExecutionEnd) abstractEnd;
              ExecutionEnd tgtExecEnd = cloneExecutionEnd(srcExecEnd);
              tgtExecEnd.getCoveredInstanceRoles().add(tgtRole);
              if (!scenario.getOwnedInteractionFragments().contains(tgtExecEnd)) {
                scenario.getOwnedInteractionFragments().add(tgtExecEnd);
              }
              clonedElt.setData(tgtExecEnd);
            }
          }
        }
      } else if (interactionFragment instanceof FragmentEnd) {
        FragmentEnd srcFragmentEnd = (FragmentEnd) interactionFragment;
        AbstractFragment srcFragment = srcFragmentEnd.getAbstractFragment();
        AbstractFragment tgtFragment = null;
        if (srcFragment instanceof CombinedFragment) {
          tgtFragment = cloneCombinedFragment((CombinedFragment) srcFragment);
        } else if (srcFragment instanceof InteractionUse) {
          tgtFragment = cloneInteractionUse((InteractionUse) srcFragment);
        }

        if ((tgtFragment != null) && (!scenario.getOwnedTimeLapses().contains(tgtFragment))) {
          scenario.getOwnedTimeLapses().add(tgtFragment);
        }

        FragmentEnd tgtFragmentEnd = cloneFragmentEnd(srcFragmentEnd, tgtFragment);
        if (tgtFragment instanceof InteractionUse) {
          List<InstanceRole> tgtRoles = cloneInstanceRoles(srcFragmentEnd.getCoveredInstanceRoles(), part, tgtElement);
          tgtFragmentEnd.getCoveredInstanceRoles().addAll(tgtRoles);
        }
        if (!scenario.getOwnedInteractionFragments().contains(tgtFragmentEnd)) {
          scenario.getOwnedInteractionFragments().add(tgtFragmentEnd);
        }
        clonedElt.setData(tgtFragmentEnd);
      } else if (interactionFragment instanceof InteractionOperand) {
        InteractionOperand srcOperand = (InteractionOperand) interactionFragment;
        InteractionOperand tgtOperand = cloneInteractionOperand(srcOperand);
        if (!scenario.getOwnedInteractionFragments().contains(tgtOperand)) {
          scenario.getOwnedInteractionFragments().add(tgtOperand);
        }
        clonedElt.setData(tgtOperand);
      }

      clonedElt.relatedNode = srcElt;
      srcElt.relatedNode = clonedElt;
      if (srcElt.oppositeNode != null) {
        clonedElt.oppositeNode = srcElt.oppositeNode.relatedNode;
        if (clonedElt.oppositeNode != null) {
          clonedElt.oppositeNode.oppositeNode = clonedElt;
        }
      }
    }

    return clonedElt;
  }

  /**
   * This method allows to clone a 'SequenceMessage'. Before cloning the element passed as parameter, we check if it is not already itself a clone,
   * or if it is not linked to an element already cloned (via a traceability link). If this is not the case, we clone it.
   * @param srcMsg
   * @return the cloned 'SequenceMessage'
   */
  private SequenceMessage cloneSequenceMessage(SequenceMessage srcMsg) {
    SequenceMessage tgtMsg = null;

    if (srcMsg != null) {
      /** Check if 'SequenceMessage' is on the source side or on the target side */
      if (srcMsg.eContainer().equals(scenario)) {
        tgtMsg = srcMsg;
      } else {
        /** if the 'SequenceMessage' doesn't exist yet on target, we have to create it */
        List<CapellaElement> lst = RefinementLinkExt.getRefinementRelatedSourceElements(srcMsg, InteractionPackage.Literals.SEQUENCE_MESSAGE);
        for (CapellaElement elt : lst) {
          if (elt.eContainer().equals(scenario)) {
            tgtMsg = (SequenceMessage) elt;
          }
        }
        if (tgtMsg == null) {
          tgtMsg = InteractionFactory.eINSTANCE.createSequenceMessage();
          tgtMsg.setKind(srcMsg.getKind());
          tgtMsg.setName(srcMsg.getName());

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtMsg, srcMsg, scenario);
          /** register message creation */
          addClonedElement(tgtMsg);
        }
      }
    }

    return tgtMsg;
  }

  /**
   * Export the Node given by parameter to the '_scenario'.
   * @param currentNode
   * @param list
   */
  private void export(Node<InteractionFragment> currentNode, List<InteractionFragment> list) {
    for (Node<InteractionFragment> childNode = currentNode.getFirstChildNode(); childNode != null; childNode = childNode.getNextNode()) {
      InteractionFragment interactionFragment = childNode.getData();
      if (interactionFragment instanceof MessageEnd) {
        MessageEnd messageEnd = (MessageEnd) interactionFragment;

        if (childNode.oppositeNode.getData() != null) {
          String abstractEndName = messageEnd.getName();
          if (abstractEndName != null) {
            messageEnd.setName(EcoreUtil2.getUniqueName(messageEnd, scenario, InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS,
                ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, messageEnd.getName()));
          }
          list.add(messageEnd);
        } else {
          SequenceMessage msg = messageEnd.getMessage();
          if (msg != null) {
            CapellaElementExt.cleanTraces(msg);
            msg.destroy();
          }

          Event evt = messageEnd.getEvent();
          if (evt != null) {
            CapellaElementExt.cleanTraces(evt);
            evt.destroy();
          }

          CapellaElementExt.cleanTraces(messageEnd);
          messageEnd.destroy();
        }
      } else if (interactionFragment instanceof ExecutionEnd) {
        list.add(interactionFragment);
      } else if (interactionFragment instanceof FragmentEnd && interactionFragment.eContainer() != null) {
          list.add(interactionFragment);
      } else if (interactionFragment instanceof InteractionOperand && interactionFragment.eContainer() != null) {
          list.add(interactionFragment);
      }

      export(childNode, list);
    }
  }

  /**
   * Export the Tree given by parameter to the '_scenario'.
   * @param srcTree
   */
  public void export(ScenarioRepresentation srcTree) {
    if (scenario != null) {
      List<InteractionFragment> newList = new ArrayList<>();
      export(getRootNode(), newList);
      scenario.getOwnedInteractionFragments().clear();
      scenario.getOwnedInteractionFragments().addAll(newList);
      cloneExecutions(srcTree);
      fillCombinedFragment();
    }
  }

  /**
   *
   */
  private void fillCombinedFragment() {
    List<CombinedFragment> emptyCBToBeRemoved = new ArrayList<>();

    for (TimeLapse timeLapse : scenario.getOwnedTimeLapses()) {
      if (timeLapse instanceof InteractionUse) {
        InteractionUse tgtInteractionUse = (InteractionUse) timeLapse;
        InteractionUse srcInteractionUse = (InteractionUse) CapellaElementExt.getRefinementTgtElement(tgtInteractionUse, InteractionPackage.Literals.INTERACTION_USE);
        if (srcInteractionUse != null) {
          FragmentEnd srcStart = (FragmentEnd) srcInteractionUse.getStart();
          FragmentEnd srcFinish = (FragmentEnd) srcInteractionUse.getFinish();
          FragmentEnd tgtStart = (FragmentEnd) CapellaElementExt.getRefinementSrcElement(srcStart, InteractionPackage.Literals.FRAGMENT_END, scenario);
          FragmentEnd tgtFinish = (FragmentEnd) CapellaElementExt.getRefinementSrcElement(srcFinish, InteractionPackage.Literals.FRAGMENT_END, scenario);
          if ((tgtStart != null) && (tgtFinish != null)) {
            List<InstanceRole> coveredInstanceRoles = AbstractFragmentExt.getCoveredInstanceRoles(srcInteractionUse, scenario);
            tgtStart.getCoveredInstanceRoles().clear();
            tgtStart.getCoveredInstanceRoles().addAll(coveredInstanceRoles);
            tgtFinish.getCoveredInstanceRoles().clear();
            tgtFinish.getCoveredInstanceRoles().addAll(coveredInstanceRoles);
          }
        }
      } else if (timeLapse instanceof CombinedFragment) {
        if (AbstractFragmentExt.isEmpty((CombinedFragment) timeLapse, scenario)) {
          emptyCBToBeRemoved.add((CombinedFragment) timeLapse);
        } else {
          CombinedFragment tgtCombinedFragment = (CombinedFragment) timeLapse;
          CombinedFragment srcCombinedFragment = (CombinedFragment) CapellaElementExt.getRefinementTgtElement(tgtCombinedFragment, InteractionPackage.Literals.COMBINED_FRAGMENT);
          if (srcCombinedFragment != null) {
            for (InteractionOperand srcOperand : srcCombinedFragment.getReferencedOperands()) {
              InteractionOperand tgtOperand =
                  (InteractionOperand) CapellaElementExt.getRefinementSrcElement(srcOperand, InteractionPackage.Literals.INTERACTION_OPERAND, scenario);
              if (tgtOperand != null) {
                tgtCombinedFragment.getReferencedOperands().add(tgtOperand);
              }
            }
            FragmentEnd srcStart = (FragmentEnd) srcCombinedFragment.getStart();
            FragmentEnd srcFinish = (FragmentEnd) srcCombinedFragment.getFinish();
            FragmentEnd tgtStart = (FragmentEnd) CapellaElementExt.getRefinementSrcElement(srcStart, InteractionPackage.Literals.FRAGMENT_END, scenario);
            FragmentEnd tgtFinish = (FragmentEnd) CapellaElementExt.getRefinementSrcElement(srcFinish, InteractionPackage.Literals.FRAGMENT_END, scenario);
            if ((tgtStart != null) && (tgtFinish != null)) {
              List<InstanceRole> coveredInstanceRoles = AbstractFragmentExt.getCoveredInstanceRoles(tgtStart, tgtFinish, scenario);
              for (InteractionOperand operand : tgtCombinedFragment.getReferencedOperands()) {
                operand.getCoveredInstanceRoles().clear();
                operand.getCoveredInstanceRoles().addAll(coveredInstanceRoles);
              }
              tgtStart.getCoveredInstanceRoles().clear();
              tgtStart.getCoveredInstanceRoles().addAll(coveredInstanceRoles);
              tgtFinish.getCoveredInstanceRoles().clear();
              tgtFinish.getCoveredInstanceRoles().addAll(coveredInstanceRoles);
            }
          }
        }
      }
    }

    for (CombinedFragment combinedFragment : emptyCBToBeRemoved) {
      removeEmptyCombinedFragment(combinedFragment);
    }
  }

  /**
   * @param msgList
   * @param node
   */
  private void fillTree(List<InteractionFragment> msgList, Node<InteractionFragment> node) {
    Node<InteractionFragment> currentNode = node;
    for (InteractionFragment interactionFragment : msgList) {
    	if (interactionFragment instanceof MessageEnd && currentNode != null) {
    		SequenceMessage msg = ((MessageEnd) interactionFragment).getMessage();
    		if (msg != null) {
    			MessageKind kind = msg.getKind();
    			/** logging */
    			String loggedMsg = MessageFormat.format(Messages.DebugScenarioContent, scenario.getName(), msg.getName(), kind);
    			logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, scenario));
    			
    			/** node creation */
    			Node<InteractionFragment> childNode = new Node<>(currentNode, interactionFragment);
    			addLastChild(currentNode, childNode);
    			
    			MessageEnd oppositeMsgEnd = MessageEndExt.getOppositeMessageEnd((MessageEnd) interactionFragment);
    			if (oppositeMsgEnd != null) {
    				Node<InteractionFragment> oppositeMsgEndNode = getNodeByData(oppositeMsgEnd);
    				if (oppositeMsgEndNode != null) {
    					oppositeMsgEndNode.oppositeNode = childNode;
    					childNode.oppositeNode = oppositeMsgEndNode;
    				}
    			}
    			
    			if (kind != MessageKind.REPLY) {
    				if ((kind.equals(MessageKind.CREATE) || kind.equals(MessageKind.DELETE))
    						&& MessageEndExt.getMessageEndType((MessageEnd) interactionFragment).equals(COMPONENT_TYPE.RECEIVER)) {
    					currentNode = currentNode.getParent();
    				} else {
    					currentNode = childNode;
    				}
    			} else {
    				currentNode = currentNode.getParent();
    			}
    		}
    	} else if (interactionFragment instanceof ExecutionEnd && currentNode != null) {
    		/** node creation */
    		Node<InteractionFragment> childNode = new Node<>(currentNode, interactionFragment);
    		addLastChild(currentNode, childNode);
    		
    		/** goes two steps up */
    		currentNode = currentNode.getParent();
    		if (currentNode != null) {
    			currentNode = currentNode.getParent();
    		}
    	} else if (interactionFragment instanceof FragmentEnd && currentNode != null) {
    		/** node creation */
    		Node<InteractionFragment> childNode = new Node<>(currentNode, interactionFragment);
    		
    		FRAGMENT_END_TYPE type = FragmentEndExt.getFragmentEndType((FragmentEnd) interactionFragment);
    		if (!type.equals(FRAGMENT_END_TYPE.FINISH)) {
    			addLastChild(currentNode, childNode);
    			currentNode = childNode;
    		} else {
    			Node<InteractionFragment> parentNode = currentNode.getFirstParentInstanceOf(InteractionPackage.Literals.FRAGMENT_END);
    			if (parentNode != null) {
    				addLastChild(parentNode, childNode);
    			} else {
    				addLastChild(currentNode, childNode);
    			}
    			currentNode = currentNode.getParent()/* .getParent() */;
    		}
    	} else if (interactionFragment instanceof InteractionOperand && currentNode != null) {
    		/** node creation */
    		Node<InteractionFragment> childNode = new Node<>(currentNode, interactionFragment);
    		
    		Node<InteractionFragment> parentNode = currentNode.getFirstParentInstanceOf(InteractionPackage.Literals.FRAGMENT_END);
    		if (parentNode != null) {
    			addLastChild(parentNode, childNode);
    		} else {
    			addLastChild(currentNode, childNode);
    		}
    		
    		currentNode = childNode;
    	} else if (interactionFragment instanceof InteractionState && currentNode != null) {
    		/** node creation */
    		Node<InteractionFragment> childNode = new Node<>(currentNode, interactionFragment);
    		addLastChild(currentNode, childNode);
    	}
    }
  }

  /**
   * @param commonNode
   * @param eltNode
   */
  public Node<InteractionFragment> getChildBranchContainingNode(Node<InteractionFragment> commonNode, Node<InteractionFragment> eltNode) {
    Node<InteractionFragment> childNode = null;
    Node<InteractionFragment> returnedNode = null;

    if ((commonNode != null) && (eltNode != null)) {
      for (childNode = commonNode.getFirstChildNode(); (childNode != null) && (returnedNode == null); childNode = childNode.getNextNode()) {
        if (getNodeByData(childNode, eltNode.getData()) != null) {
          returnedNode = childNode;
        }
      }
    }

    return returnedNode;
  }

  /**
   * @param nodeData
   * @return
   */
  public Node<InteractionFragment> getNodeByData(InteractionFragment nodeData) {
    for (Node<InteractionFragment> node : walk()) {
      if (node.getData() == nodeData) {
        return node;
      }
    }
    return null;
  }

  /**
   * @param currentNode
   * @param nodeData
   * @return
   */
  private Node<InteractionFragment> getNodeByData(Node<InteractionFragment> currentNode, InteractionFragment nodeData) {
    for (Node<InteractionFragment> node : messageTree.walk(currentNode)) {
      if (node.getData() == nodeData) {
        return node;
      }
    }
    return null;
  }

  /**
   * In Ctx towards LogicalArchitecture layer or LogicalArchitecture toward PhysicalArchitecture refinement cases, return the operation transitionned. In other
   * case, return the same Operation given in parameter.
   */
  private AbstractEventOperation getOperation(MessageEnd srcMsgEnd, SequenceMessage tgtMsg, AbstractEventOperation currentOp) {
    AbstractEventOperation finalOp = null;

    if (currentOp != null) {
      finalOp = RefinementServices.getDelegatedOperation(srcMsgEnd, tgtMsg, currentOp);
    }

    return finalOp;
  }

  /**
   * @return the root node
   */
  public Node<InteractionFragment> getRootNode() {
    if (messageTree != null) {
      return messageTree.getRootElement();
    }
    return null;
  }

  /**
   * @return the scenario represented by this class
   */
  public Scenario getScenario() {
    return scenario;
  }

  /**
   * 
   */
  private void removeEmptyCombinedFragment(CombinedFragment combinedFragment) {
    for (InteractionOperand operand : AbstractFragmentExt.getOwnedOperands(combinedFragment, scenario)) {
      CapellaElementExt.cleanTraces(operand);
      operand.destroy();
    }
    CapellaElementExt.cleanTraces(combinedFragment.getStart());
    combinedFragment.getStart().destroy();
    CapellaElementExt.cleanTraces(combinedFragment.getFinish());
    combinedFragment.getFinish().destroy();
    CapellaElementExt.cleanTraces(combinedFragment);
    combinedFragment.destroy();
  }

  /**
   * 
   */
  @Override
  public String toString() {
    if (messageTree != null) {
      return messageTree.toString();
    }
    return super.toString();
  }

  /**
   * @param currentNode
   */
  public void unChainCurrentNodeMessage(Node<InteractionFragment> currentNode) {
    if (messageTree != null) {
      messageTree.unChainCurrentNodeMessage(currentNode);
      updateListeners();
    }
  }

  /**
   * 
   */
  private void updateListeners() {
    for (IScenarioRepresentationListener listener : listeners) {
      listener.scenarioChanged(messageTree);
    }
  }

  /**
   * Update the source operation if have been changed still the previous refinement
   */
  public void updateOperationNode(Node<InteractionFragment> srcNode, Node<InteractionFragment> tgtNode) {
    InteractionFragment srcAbstractEnd = srcNode.getData();
    InteractionFragment tgtAbstractEnd = tgtNode.getData();
    if ((srcAbstractEnd instanceof MessageEnd) && (tgtAbstractEnd != null)) {
        MessageEnd srcMsgEnd = (MessageEnd) srcAbstractEnd;
        MessageEnd tgtMsgEnd = (MessageEnd) tgtAbstractEnd;

        COMPONENT_TYPE type = MessageEndExt.getMessageEndType(srcMsgEnd);
        switch (type) {
          case RECEIVER: {
            Event srcEvt = srcMsgEnd.getEvent();
            if (srcEvt instanceof EventReceiptOperation) {
                AbstractEventOperation targetOp = null;
                AbstractEventOperation srcOp = ((EventReceiptOperation) srcEvt).getOperation();
                AbstractEventOperation deletegatedOp = getOperation(srcMsgEnd, tgtMsgEnd.getMessage(), srcOp);

                EventReceiptOperation eventReceiptOp = (EventReceiptOperation) tgtMsgEnd.getEvent();
                if (eventReceiptOp != null) {
                  targetOp = eventReceiptOp.getOperation();
                }

                if (targetOp != deletegatedOp && eventReceiptOp != null) {
                  eventReceiptOp.setOperation(deletegatedOp);
                  SequenceMessage tgtSeqMsg = tgtMsgEnd.getMessage();
                  tgtSeqMsg.setName(srcMsgEnd.getMessage().getName());
                }
            }
            break;
          }
          case SENDER: {
            EventSentOperation srcEvt = (EventSentOperation) srcMsgEnd.getEvent();
            if (srcEvt != null) {
              AbstractEventOperation targetOp = null;
              AbstractEventOperation srcOp = srcEvt.getOperation();
              AbstractEventOperation delegatedOp = getOperation(srcMsgEnd, tgtMsgEnd.getMessage(), srcOp);

              EventSentOperation eventSentOp = (EventSentOperation) tgtMsgEnd.getEvent();
              if (eventSentOp != null) {
                targetOp = eventSentOp.getOperation();
              }

              if (targetOp != delegatedOp && eventSentOp != null) {
                eventSentOp.setOperation(delegatedOp);
                SequenceMessage tgtSeqMsg = tgtMsgEnd.getMessage();
                tgtSeqMsg.setName(srcMsgEnd.getMessage().getName());
              }

            }
            break;
          }
          case UNDEFINED:
          break;
        }
    }
  }

  /**
   * 
   */
  public List<Node<InteractionFragment>> walk() {
    if (messageTree != null) {
      return messageTree.walk(messageTree.getRootElement());
    }
    return Collections.emptyList();
  }
}
