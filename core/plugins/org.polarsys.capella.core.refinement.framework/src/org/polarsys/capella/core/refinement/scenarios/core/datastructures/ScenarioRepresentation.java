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
  private Set<CapellaElement> _clonedElements = null;
  private List<IScenarioRepresentationListener> _listeners = new ArrayList<IScenarioRepresentationListener>();
  private Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);
  private Tree<InteractionFragment> _messageTree = null;

  private Scenario _scenario = null;

  /**
   * Constructor.
   * @param scenario_p
   */
  public ScenarioRepresentation(Scenario scenario_p) {
    if (scenario_p != null) {
      _scenario = scenario_p;
      _messageTree = new Tree<InteractionFragment>();

      Node<InteractionFragment> rootNode = new Node<InteractionFragment>(null);
      _messageTree.setRootElement(rootNode);

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
        _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _scenario));
        /** */
        IScenarioRepresentationListener listener =
            (IScenarioRepresentationListener) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
        if (listener != null) {
          listener.scenarioCreated(_messageTree);
          _listeners.add(listener);
        }
      }

      fillTree(scenario_p.getOwnedInteractionFragments(), rootNode);
    }
  }

  /**
   * 
   */
  public void addChildAfter(Node<InteractionFragment> parent_p, Node<InteractionFragment> child_p, Node<InteractionFragment> previousNode_p) {
    parent_p.addChildAfter(child_p, previousNode_p);
    updateListeners();
  }

  /**
   * 
   */
  private void addClonedElement(CapellaElement elt_p) {
    if (null == _clonedElements) {
      _clonedElements = new HashSet<CapellaElement>();
    }
    _clonedElements.add(elt_p);
  }

  /**
   * 
   */
  public void addFirstChild(Node<AbstractEnd> parent_p, Node<AbstractEnd> child_p) {
    parent_p.addFirstChild(child_p);
    updateListeners();
  }

  /**
   * 
   */
  public void addLastChild(Node<InteractionFragment> parent_p, Node<InteractionFragment> child_p) {
    parent_p.addLastChild(child_p);
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
    if (null != _clonedElements) {
      for (CapellaElement elt : _clonedElements) {
        CapellaElementExt.cleanTraces(elt);
        elt.destroy();
      }
    }
  }

  /**
   * This method allows to clone a 'CombinedFragment. Before cloning the element passed as parameter, we check if it is not already itself a clone, or if
   * it is not linked to an element already cloned (via a traceability link). If this is not the case, we clone it.
   * @param srcFragment_p
   * @return the cloned 'CombinedFragment'
   */
  private CombinedFragment cloneCombinedFragment(CombinedFragment srcFragment_p) {
    CombinedFragment tgtFragment = null;

    if (srcFragment_p != null) {
      /** Check if 'SequenceMessage' is on the source side or on the target side */
      if (srcFragment_p.eContainer().equals(_scenario)) {
        tgtFragment = srcFragment_p;
      } else {
        /** if the 'CombinedFragment' doesn't exist yet on target, we have to create it */
        List<CapellaElement> lst = RefinementLinkExt.getRefinementRelatedSourceElements(srcFragment_p, InteractionPackage.Literals.COMBINED_FRAGMENT);
        for (CapellaElement elt : lst) {
          if (elt.eContainer().equals(_scenario)) {
            tgtFragment = (CombinedFragment) elt;
          }
        }
        if (tgtFragment == null) {
          tgtFragment = InteractionFactory.eINSTANCE.createCombinedFragment();
          tgtFragment.setName(srcFragment_p.getName());
          tgtFragment.setOperator(srcFragment_p.getOperator());

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtFragment, srcFragment_p, _scenario);
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
   * @param srcExecEnd_p
   * @return the cloned 'ExecutionEnd'
   */
  private ExecutionEnd cloneExecutionEnd(ExecutionEnd srcExecEnd_p) {
    ExecutionEnd tgtExecEnd = null;

    if (srcExecEnd_p != null) {
      /** Check if 'ExecutionEnd' is on the source side or on the target side */ 
      if (srcExecEnd_p.eContainer().equals(_scenario)) {
        tgtExecEnd = srcExecEnd_p;
      } else {
        /** if the 'ExecutionEnd' doesn't exist yet on target, we have to create it */
        List<CapellaElement> lst = RefinementLinkExt.getRefinementRelatedSourceElements(srcExecEnd_p, InteractionPackage.Literals.EXECUTION_END);
        for (CapellaElement elt : lst) {
          if ((elt.eContainer() != null) && elt.eContainer().equals(_scenario)) {
            tgtExecEnd = (ExecutionEnd) elt;
          }
        }
        if (tgtExecEnd == null) {
          tgtExecEnd = InteractionFactory.eINSTANCE.createExecutionEnd();
          ExecutionEvent execEvt = InteractionFactory.eINSTANCE.createExecutionEvent();
          tgtExecEnd.setEvent(execEvt);
          _scenario.getOwnedEvents().add(execEvt);

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtExecEnd, srcExecEnd_p, _scenario);
          RefinementLinkExt.createRefinementTraceabilityLink(execEvt, srcExecEnd_p.getEvent(), _scenario);
        }
      }
    }

    return tgtExecEnd;
  }

  /**
   * @param srcTree_p
   */
  private void cloneExecutions(ScenarioRepresentation srcTree_p) {
    List<TimeLapse> srcExecLst = srcTree_p.getScenario().getOwnedTimeLapses();
    for (TimeLapse srcExec : srcExecLst) {
      Execution tgtExec = null;
      for (CapellaElement exec : RefinementLinkExt.getRefinementRelatedSourceElements(srcExec, InteractionPackage.Literals.EXECUTION)) {
        if (exec.eContainer().equals(_scenario)) {
          tgtExec = (Execution) exec;
        }
      }

   	  /** if the 'Execution' doesn't exist yet on target, we have to create it */
      if (tgtExec == null) {
        AbstractEnd startMsgEnd = null;
        AbstractEnd finishMsgEnd = null;

        for (CapellaElement msgEnd : RefinementLinkExt.getRefinementRelatedSourceElements(srcExec.getStart(), InteractionPackage.Literals.ABSTRACT_END)) {
          if (msgEnd.eContainer().equals(_scenario)) {
            startMsgEnd = (AbstractEnd) msgEnd;
          }
        }

        for (CapellaElement msgEnd : RefinementLinkExt.getRefinementRelatedSourceElements(srcExec.getFinish(), InteractionPackage.Literals.ABSTRACT_END)) {
          if (msgEnd.eContainer().equals(_scenario)) {
            finishMsgEnd = (AbstractEnd) msgEnd;
          }
        }

        if ((startMsgEnd != null) && (finishMsgEnd != null)) {
          tgtExec = InteractionFactory.eINSTANCE.createExecution();
          tgtExec.setStart(startMsgEnd);
          tgtExec.setFinish(finishMsgEnd);

          _scenario.getOwnedTimeLapses().add(tgtExec);

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtExec, srcExec, _scenario);
        }
        else if (startMsgEnd == null) {
          // [Refinement] Scenario containing an asynchronous message into empty Physical/EPBS
          if (finishMsgEnd instanceof ExecutionEnd) {
            Event execEvt = finishMsgEnd.getEvent();
            CapellaElementExt.cleanTraces(execEvt);
            execEvt.destroy();
            CapellaElementExt.cleanTraces(finishMsgEnd);
            finishMsgEnd.destroy();
          }
        }
      }
    }
  }

  /**
   * This method allows to clone a 'FragmentEnd. Before cloning the element passed as parameter, we check if it is not already itself a clone, or if
   * it is not linked to an element already cloned (via a traceability link). If this is not the case, we clone it.
   */
  private FragmentEnd cloneFragmentEnd(FragmentEnd srcFragmentEnd_p, AbstractFragment tgtFragment_p) {
    FragmentEnd tgtFragmentEnd = null;

    if (srcFragmentEnd_p != null) {
      /** Check if the 'FragmentEnd' is on the source side or on the target side */
      if (srcFragmentEnd_p.eContainer().equals(_scenario)) {
        tgtFragmentEnd = srcFragmentEnd_p;
      } else {
        /** if the 'FragmentEnd' doesn't exist yet on target, we have to create it */
        List<CapellaElement> lst = RefinementLinkExt.getRefinementRelatedSourceElements(srcFragmentEnd_p, InteractionPackage.Literals.FRAGMENT_END);
        for (CapellaElement elt : lst) {
          if ((elt.eContainer() != null) && elt.eContainer().equals(_scenario)) {
            tgtFragmentEnd = (FragmentEnd) elt;
          }
        }
        if (tgtFragmentEnd == null) {
          tgtFragmentEnd = InteractionFactory.eINSTANCE.createFragmentEnd();
          tgtFragmentEnd.setName(srcFragmentEnd_p.getName());
          FRAGMENT_END_TYPE type = FragmentEndExt.getFragmentEndType(srcFragmentEnd_p);
          switch (type) {
            case FINISH:
              tgtFragment_p.setFinish(tgtFragmentEnd);
            break;
            case START:
              tgtFragment_p.setStart(tgtFragmentEnd);
            break;
            case UNDEFINED:
            break;
          }

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtFragmentEnd, srcFragmentEnd_p, _scenario);
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
   * @param srcRole_p
   * @param component_p
   * @param tgtElement_p
   * @return the cloned 'InstanceRole'
   */
  private InstanceRole cloneInstanceRole(InstanceRole srcRole_p, AbstractInstance abstractInstance_p, NamedElement tgtElement_p) {
    InstanceRole tgtRole = null;

    if (srcRole_p != null) {
      /** we check if the 'InstanceRole' is on source or target side */
      if (srcRole_p.eContainer().equals(_scenario)) {
        tgtRole = srcRole_p;
      } else {
        List<CapellaElement> rolesLst = RefinementLinkExt.getRefinementRelatedSourceElements(srcRole_p, InteractionPackage.Literals.INSTANCE_ROLE);
        for (CapellaElement role : rolesLst) {
          InstanceRole instRole = (InstanceRole) role;
          AbstractInstance cpntInst = instRole.getRepresentedInstance();
          if (instRole.eContainer().equals(_scenario)) {
            if ((abstractInstance_p == null) || (cpntInst.equals(abstractInstance_p))) {
              tgtRole = instRole;
            } else if (cpntInst instanceof SignalInstance) {
              tgtRole = instRole;
            }
          }
        }
        if (tgtRole == null) {
          for (InstanceRole instRole : _scenario.getOwnedInstanceRoles()) {
            AbstractInstance cpntInst = instRole.getRepresentedInstance();
            if ((cpntInst != null) && cpntInst.equals(abstractInstance_p)) {
              tgtRole = instRole;
              if (!RefinementLinkExt.isLinkedTo(tgtRole, srcRole_p)) {
                /** adding traceability link */
                RefinementLinkExt.createRefinementTraceabilityLink(tgtRole, srcRole_p, _scenario);
              }
            }
          }
        }

        /** if the 'InstanceRole' doesn't exist yet on target, we have to create it */
        if ((tgtRole == null) && (abstractInstance_p != null)) {
          tgtRole = InteractionFactory.eINSTANCE.createInstanceRole();
          tgtRole.setRepresentedInstance(abstractInstance_p);
          tgtRole.setName(abstractInstance_p.getName());

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtRole, srcRole_p, _scenario);
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
   * @param srcRoles_p
   * @param component_p
   * @param tgtElement_p
   * @return the cloned 'InstanceRole'
   */
  private List<InstanceRole> cloneInstanceRoles(List<InstanceRole> srcRoles_p, AbstractInstance abstractInstance_p, NamedElement tgtElement_p) {
    List<InstanceRole> tgtRoles = new ArrayList<InstanceRole>();

    for (InstanceRole srcRole_p : srcRoles_p) {
      if (tgtElement_p instanceof Component) {
        for (Partition part : ((Component) tgtElement_p).getOwnedPartitions()) {
          if (part instanceof Part) {
            InstanceRole tgtRole = cloneInstanceRole(srcRole_p, part, tgtElement_p);
            if (tgtRole != null) {
              if (!tgtRoles.contains(tgtRole)) {
                tgtRoles.add(tgtRole);
              }
              if (!_scenario.getOwnedInstanceRoles().contains(tgtRole)) {
                _scenario.getOwnedInstanceRoles().add(tgtRole);
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
  private InteractionOperand cloneInteractionOperand(InteractionOperand srcOperand_p) {
    InteractionOperand tgtOperand = null;

    if (srcOperand_p != null) {
      /** Check if the 'InteractionOperand' is on the source side or on the target side */
      if (srcOperand_p.eContainer().equals(_scenario)) {
        tgtOperand = srcOperand_p;
      } else {
        /** If the 'InteractionOperand' doesn't exist yet on target, we have to create it */
        List<CapellaElement> lst = RefinementLinkExt.getRefinementRelatedSourceElements(srcOperand_p, InteractionPackage.Literals.INTERACTION_OPERAND);
        for (CapellaElement elt : lst) {
          if ((elt.eContainer() != null) && elt.eContainer().equals(_scenario)) {
            tgtOperand = (InteractionOperand) elt;
          }
        }
        if (tgtOperand == null) {
          tgtOperand = InteractionFactory.eINSTANCE.createInteractionOperand();
          tgtOperand.setName(srcOperand_p.getName());
          tgtOperand.setGuard(srcOperand_p.getGuard());

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtOperand, srcOperand_p, _scenario);
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
   * @param srcUse_p
   * @return the cloned 'InteractionUse'
   */
  private InteractionUse cloneInteractionUse(InteractionUse srcUse_p) {
    InteractionUse tgtUse = null;

    if (srcUse_p != null) {
      /** Check if 'SequenceMessage' is on the source side or on the target side */
      if (srcUse_p.eContainer().equals(_scenario)) {
        tgtUse = srcUse_p;
      } else {
        /** if the 'CombinedFragment' doesn't exist yet on target, we have to create it */
        List<CapellaElement> lst = RefinementLinkExt.getRefinementRelatedSourceElements(srcUse_p, InteractionPackage.Literals.INTERACTION_USE);
        for (CapellaElement elt : lst) {
          if (elt.eContainer().equals(_scenario)) {
            tgtUse = (InteractionUse) elt;
          }
        }
        if (tgtUse == null) {
          tgtUse = InteractionFactory.eINSTANCE.createInteractionUse();
          tgtUse.setName(srcUse_p.getName());

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtUse, srcUse_p, _scenario);
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
  private MessageEnd cloneMessageEnd(MessageEnd srcMsgEnd_p, SequenceMessage tgtMsg_p) {
    MessageEnd tgtMsgEnd = null;

    if (srcMsgEnd_p != null) {
      /** Check if the 'MessageEnd' is on the source side or on the target side */
      if (srcMsgEnd_p.eContainer().equals(_scenario)) {
        tgtMsgEnd = srcMsgEnd_p;
      } else {
        /** If the 'MessageEnd' doesn't exist yet on target, we have to create it */
        List<CapellaElement> lst = RefinementLinkExt.getRefinementRelatedSourceElements(srcMsgEnd_p, InteractionPackage.Literals.MESSAGE_END);
        for (CapellaElement elt : lst) {
          if ((elt.eContainer() != null) && elt.eContainer().equals(_scenario)) {
            tgtMsgEnd = (MessageEnd) elt;
          }
        }
        if (tgtMsgEnd == null) {
          tgtMsgEnd = InteractionFactory.eINSTANCE.createMessageEnd();
          COMPONENT_TYPE type = MessageEndExt.getMessageEndType(srcMsgEnd_p);
          switch (type) {
            case RECEIVER: {
              tgtMsgEnd.setName(MessageFormat.format(Messages.MessageEndReceiverNamePattern, tgtMsg_p.getName(), tgtMsg_p.getKind()));
              tgtMsg_p.setReceivingEnd(tgtMsgEnd);

              Event srcEvt = srcMsgEnd_p.getEvent();
              if (srcEvt != null) {
                if (srcEvt instanceof EventReceiptOperation) {
                  AbstractEventOperation op = ((EventReceiptOperation) srcEvt).getOperation();
                  AbstractEventOperation srcOp = getOperation(srcMsgEnd_p, tgtMsg_p, op);
                  if (srcOp != null) {
                    EventReceiptOperation rcvOp = InteractionFactory.eINSTANCE.createEventReceiptOperation();
                    rcvOp.setOperation(srcOp);
                    tgtMsgEnd.setEvent(rcvOp);
                    rcvOp.setName(EcoreUtil2.getUniqueName(rcvOp, _scenario, InteractionPackage.Literals.SCENARIO__OWNED_EVENTS,
                        ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, MessageFormat.format(Messages.EventReceiptOperationNamePattern, tgtMsg_p
                            .getName(), tgtMsg_p.getKind())));
                    _scenario.getOwnedEvents().add(rcvOp);
                    /** adding traceability link */
                    RefinementLinkExt.createRefinementTraceabilityLink(rcvOp, srcMsgEnd_p.getEvent(), _scenario);
                    /** register event creation */
                    addClonedElement(rcvOp);
                  }
                } else if (srcEvt instanceof CreationEvent) {
                  CreationEvent rcvOp = InteractionFactory.eINSTANCE.createCreationEvent();
                  tgtMsgEnd.setEvent(rcvOp);
                  rcvOp.setName(EcoreUtil2.getUniqueName(rcvOp, _scenario, InteractionPackage.Literals.SCENARIO__OWNED_EVENTS,
                      ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, MessageFormat.format(Messages.CreationEventNamePattern, tgtMsg_p.getName(),
                          tgtMsg_p.getKind())));
                  _scenario.getOwnedEvents().add(rcvOp);
                  /** adding traceability link */
                  RefinementLinkExt.createRefinementTraceabilityLink(rcvOp, srcMsgEnd_p.getEvent(), _scenario);
                  /** register event creation */
                  addClonedElement(rcvOp);
                }
              }
              break;
            }
            case SENDER: {
              tgtMsgEnd.setName(MessageFormat.format(Messages.MessageEndSenderNamePattern, tgtMsg_p.getName(), tgtMsg_p.getKind()));
              EventSentOperation srcEvt = (EventSentOperation) srcMsgEnd_p.getEvent();
              if (srcEvt != null) {
                EventSentOperation sndOp = InteractionFactory.eINSTANCE.createEventSentOperation();
                AbstractEventOperation op = srcEvt.getOperation();
                AbstractEventOperation srcOp = getOperation(srcMsgEnd_p, tgtMsg_p, op);
                if (srcOp != null) {
                  sndOp.setOperation(srcOp);
                }
                tgtMsgEnd.setEvent(sndOp);
                tgtMsg_p.setSendingEnd(tgtMsgEnd);
                sndOp.setName(EcoreUtil2.getUniqueName(sndOp, _scenario, InteractionPackage.Literals.SCENARIO__OWNED_EVENTS,
                    ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, MessageFormat.format(Messages.EventSentOperationNamePattern,
                        tgtMsg_p.getName(), tgtMsg_p.getKind())));
                _scenario.getOwnedEvents().add(sndOp);
                /** adding traceability link */
                RefinementLinkExt.createRefinementTraceabilityLink(sndOp, srcMsgEnd_p.getEvent(), _scenario);
                /** register event creation */
                addClonedElement(sndOp);
              }
              break;
            }
            case UNDEFINED:
            break;
          }

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtMsgEnd, srcMsgEnd_p, _scenario);
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
   * @param srcElt_p
   * @param component_p
   * @param tgtElement_p
   * @return Node<MessageEnd>
   */
  public Node<InteractionFragment> cloneNode(Node<InteractionFragment> srcElt_p, AbstractInstance part_p, NamedElement tgtElement_p) {
    Node<InteractionFragment> clonedElt = null;

    if (srcElt_p != null) {
      clonedElt = new Node<InteractionFragment>(null);

      InteractionFragment interactionFragment = srcElt_p.getData();
      if (interactionFragment instanceof AbstractEnd) {
        AbstractEnd abstractEnd = (AbstractEnd) interactionFragment;

        if (part_p != null) {
          InstanceRole tgtRole = cloneInstanceRole(abstractEnd.getCovered(), part_p, tgtElement_p);
          if (tgtRole != null) {
            if (!_scenario.getOwnedInstanceRoles().contains(tgtRole)) {
              _scenario.getOwnedInstanceRoles().add(tgtRole);
            }

            if (abstractEnd instanceof MessageEnd) {
              MessageEnd srcMsgEnd = (MessageEnd) abstractEnd;
              SequenceMessage tgtMsg = cloneSequenceMessage(srcMsgEnd.getMessage());
              if ((tgtMsg != null) && (!_scenario.getOwnedMessages().contains(tgtMsg))) {
                _scenario.getOwnedMessages().add(tgtMsg);
              }

              MessageEnd tgtMsgEnd = cloneMessageEnd(srcMsgEnd, tgtMsg);
              if (tgtMsgEnd != null) {
                tgtMsgEnd.getCoveredInstanceRoles().add(tgtRole);
                if (!_scenario.getOwnedInteractionFragments().contains(tgtMsgEnd)) {
                  _scenario.getOwnedInteractionFragments().add(tgtMsgEnd);
                }
              }
              clonedElt.setData(tgtMsgEnd);
            } else if (abstractEnd instanceof ExecutionEnd) {
              ExecutionEnd srcExecEnd = (ExecutionEnd) abstractEnd;
              ExecutionEnd tgtExecEnd = cloneExecutionEnd(srcExecEnd);
              if (tgtExecEnd != null) {
                tgtExecEnd.getCoveredInstanceRoles().add(tgtRole);
                if (!_scenario.getOwnedInteractionFragments().contains(tgtExecEnd)) {
                  _scenario.getOwnedInteractionFragments().add(tgtExecEnd);
                }
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

        if ((tgtFragment != null) && (!_scenario.getOwnedTimeLapses().contains(tgtFragment))) {
          _scenario.getOwnedTimeLapses().add(tgtFragment);
        }

        FragmentEnd tgtFragmentEnd = cloneFragmentEnd(srcFragmentEnd, tgtFragment);
        if (tgtFragmentEnd != null) {
          if (tgtFragment instanceof InteractionUse) {
            List<InstanceRole> tgtRoles = cloneInstanceRoles(srcFragmentEnd.getCoveredInstanceRoles(), part_p, tgtElement_p);
            tgtFragmentEnd.getCoveredInstanceRoles().addAll(tgtRoles);
          }
          if (!_scenario.getOwnedInteractionFragments().contains(tgtFragmentEnd)) {
            _scenario.getOwnedInteractionFragments().add(tgtFragmentEnd);
          }
        }
        clonedElt.setData(tgtFragmentEnd);
      } else if (interactionFragment instanceof InteractionOperand) {
        InteractionOperand srcOperand = (InteractionOperand) interactionFragment;
        InteractionOperand tgtOperand = cloneInteractionOperand(srcOperand);
        if (tgtOperand != null) {
          if (!_scenario.getOwnedInteractionFragments().contains(tgtOperand)) {
            _scenario.getOwnedInteractionFragments().add(tgtOperand);
          }
        }
        clonedElt.setData(tgtOperand);
      }

      clonedElt.relatedNode = srcElt_p;
      srcElt_p.relatedNode = clonedElt;
      if (srcElt_p.oppositeNode != null) {
        clonedElt.oppositeNode = srcElt_p.oppositeNode.relatedNode;
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
   * @param srcMsg_p
   * @return the cloned 'SequenceMessage'
   */
  private SequenceMessage cloneSequenceMessage(SequenceMessage srcMsg_p) {
    SequenceMessage tgtMsg = null;

    if (srcMsg_p != null) {
      /** Check if 'SequenceMessage' is on the source side or on the target side */
      if (srcMsg_p.eContainer().equals(_scenario)) {
        tgtMsg = srcMsg_p;
      } else {
        /** if the 'SequenceMessage' doesn't exist yet on target, we have to create it */
        List<CapellaElement> lst = RefinementLinkExt.getRefinementRelatedSourceElements(srcMsg_p, InteractionPackage.Literals.SEQUENCE_MESSAGE);
        for (CapellaElement elt : lst) {
          if (elt.eContainer().equals(_scenario)) {
            tgtMsg = (SequenceMessage) elt;
          }
        }
        if (tgtMsg == null) {
          tgtMsg = InteractionFactory.eINSTANCE.createSequenceMessage();
          tgtMsg.setKind(srcMsg_p.getKind());
          tgtMsg.setName(srcMsg_p.getName());

          /** adding traceability link */
          RefinementLinkExt.createRefinementTraceabilityLink(tgtMsg, srcMsg_p, _scenario);
          /** register message creation */
          addClonedElement(tgtMsg);
        }
      }
    }

    return tgtMsg;
  }

  /**
   * Export the Node given by parameter to the '_scenario'.
   * @param currentNode_p
   * @param list_p
   */
  private void export(Node<InteractionFragment> currentNode_p, List<InteractionFragment> list_p) {
    for (Node<InteractionFragment> childNode = currentNode_p.getFirstChildNode(); childNode != null; childNode = childNode.getNextNode()) {
      InteractionFragment interactionFragment = childNode.getData();
      if (interactionFragment instanceof MessageEnd) {
        MessageEnd messageEnd = (MessageEnd) interactionFragment;

        if (childNode.oppositeNode.getData() != null) {
          String abstractEndName = messageEnd.getName();
          if (abstractEndName != null) {
            messageEnd.setName(EcoreUtil2.getUniqueName(messageEnd, _scenario, InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS,
                ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, messageEnd.getName()));
          }
          list_p.add(messageEnd);
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
        list_p.add(interactionFragment);
      } else if (interactionFragment instanceof FragmentEnd) {
        if (interactionFragment.eContainer() != null) {
          list_p.add(interactionFragment);
        }
      } else if (interactionFragment instanceof InteractionOperand) {
        if (interactionFragment.eContainer() != null) {
          list_p.add(interactionFragment);
        }
      }

      export(childNode, list_p);
    }
  }

  /**
   * Export the Tree given by parameter to the '_scenario'.
   * @param srcTree_p
   */
  public void export(ScenarioRepresentation srcTree_p) {
    if (_scenario != null) {
      List<InteractionFragment> newList = new ArrayList<InteractionFragment>();
      export(getRootNode(), newList);
      _scenario.getOwnedInteractionFragments().clear();
      _scenario.getOwnedInteractionFragments().addAll(newList);
      cloneExecutions(srcTree_p);
      fillCombinedFragment();
    }
  }

  /**
   *
   */
  private void fillCombinedFragment() {
    List<CombinedFragment> emptyCBToBeRemoved = new ArrayList<CombinedFragment>();

    for (TimeLapse timeLapse : _scenario.getOwnedTimeLapses()) {
      if (timeLapse instanceof InteractionUse) {
        InteractionUse tgtInteractionUse = (InteractionUse) timeLapse;
        InteractionUse srcInteractionUse = (InteractionUse) CapellaElementExt.getRefinementTgtElement(tgtInteractionUse, InteractionPackage.Literals.INTERACTION_USE);
        if (srcInteractionUse != null) {
          FragmentEnd srcStart = (FragmentEnd) srcInteractionUse.getStart();
          FragmentEnd srcFinish = (FragmentEnd) srcInteractionUse.getFinish();
          FragmentEnd tgtStart = (FragmentEnd) CapellaElementExt.getRefinementSrcElement(srcStart, InteractionPackage.Literals.FRAGMENT_END, _scenario);
          FragmentEnd tgtFinish = (FragmentEnd) CapellaElementExt.getRefinementSrcElement(srcFinish, InteractionPackage.Literals.FRAGMENT_END, _scenario);
          if ((tgtStart != null) && (tgtFinish != null)) {
            List<InstanceRole> coveredInstanceRoles = AbstractFragmentExt.getCoveredInstanceRoles(srcInteractionUse, _scenario);
            tgtStart.getCoveredInstanceRoles().clear();
            tgtStart.getCoveredInstanceRoles().addAll(coveredInstanceRoles);
            tgtFinish.getCoveredInstanceRoles().clear();
            tgtFinish.getCoveredInstanceRoles().addAll(coveredInstanceRoles);
          }
        }
      } else if (timeLapse instanceof CombinedFragment) {
        if (AbstractFragmentExt.isEmpty((CombinedFragment) timeLapse, _scenario)) {
          emptyCBToBeRemoved.add((CombinedFragment) timeLapse);
        } else {
          CombinedFragment tgtCombinedFragment = (CombinedFragment) timeLapse;
          CombinedFragment srcCombinedFragment = (CombinedFragment) CapellaElementExt.getRefinementTgtElement(tgtCombinedFragment, InteractionPackage.Literals.COMBINED_FRAGMENT);
          if (srcCombinedFragment != null) {
            for (InteractionOperand srcOperand : srcCombinedFragment.getReferencedOperands()) {
              InteractionOperand tgtOperand =
                  (InteractionOperand) CapellaElementExt.getRefinementSrcElement(srcOperand, InteractionPackage.Literals.INTERACTION_OPERAND, _scenario);
              if (tgtOperand != null) {
                tgtCombinedFragment.getReferencedOperands().add(tgtOperand);
              }
            }
            FragmentEnd srcStart = (FragmentEnd) srcCombinedFragment.getStart();
            FragmentEnd srcFinish = (FragmentEnd) srcCombinedFragment.getFinish();
            FragmentEnd tgtStart = (FragmentEnd) CapellaElementExt.getRefinementSrcElement(srcStart, InteractionPackage.Literals.FRAGMENT_END, _scenario);
            FragmentEnd tgtFinish = (FragmentEnd) CapellaElementExt.getRefinementSrcElement(srcFinish, InteractionPackage.Literals.FRAGMENT_END, _scenario);
            if ((tgtStart != null) && (tgtFinish != null)) {
              List<InstanceRole> coveredInstanceRoles = AbstractFragmentExt.getCoveredInstanceRoles(tgtStart, tgtFinish, _scenario);
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
      if (interactionFragment instanceof MessageEnd) {
        SequenceMessage msg = ((MessageEnd) interactionFragment).getMessage();
        if (msg != null) {
          MessageKind kind = msg.getKind();
          /** logging */
          String loggedMsg = MessageFormat.format(Messages.DebugScenarioContent, _scenario.getName(), msg.getName(), kind);
          _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _scenario));

          /** node creation */
          Node<InteractionFragment> childNode = new Node<InteractionFragment>(currentNode, interactionFragment);
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
      } else if (interactionFragment instanceof ExecutionEnd) {
        /** node creation */
        Node<InteractionFragment> childNode = new Node<InteractionFragment>(currentNode, interactionFragment);
        addLastChild(currentNode, childNode);

        /** goes two steps up */
        currentNode = currentNode.getParent();
        if (currentNode != null) {
          currentNode = currentNode.getParent();
        }
      } else if (interactionFragment instanceof FragmentEnd) {
        /** node creation */
        Node<InteractionFragment> childNode = new Node<InteractionFragment>(currentNode, interactionFragment);

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
      } else if (interactionFragment instanceof InteractionOperand) {
        /** node creation */
        Node<InteractionFragment> childNode = new Node<InteractionFragment>(currentNode, interactionFragment);

        Node<InteractionFragment> parentNode = currentNode.getFirstParentInstanceOf(InteractionPackage.Literals.FRAGMENT_END);
        if (parentNode != null) {
          addLastChild(parentNode, childNode);
        } else {
          addLastChild(currentNode, childNode);
        }

        currentNode = childNode;
      } else if (interactionFragment instanceof InteractionState) {
        /** node creation */
        Node<InteractionFragment> childNode = new Node<InteractionFragment>(currentNode, interactionFragment);
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
    for (Node<InteractionFragment> node : _messageTree.walk(currentNode)) {
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
  private AbstractEventOperation getOperation(MessageEnd srcMsgEnd_p, SequenceMessage tgtMsg_p, AbstractEventOperation currentOp_p) {
    AbstractEventOperation finalOp = null;

    if (currentOp_p != null) {
      finalOp = RefinementServices.getDelegatedOperation(srcMsgEnd_p, tgtMsg_p, currentOp_p);
    }

    return finalOp;
  }

  /**
   * @return the root node
   */
  public Node<InteractionFragment> getRootNode() {
    if (_messageTree != null) {
      return _messageTree.getRootElement();
    }
    return null;
  }

  /**
   * @return the scenario represented by this class
   */
  public Scenario getScenario() {
    return _scenario;
  }

  /**
   * 
   */
  private void removeEmptyCombinedFragment(CombinedFragment combinedFragment_p) {
    for (InteractionOperand operand : AbstractFragmentExt.getOwnedOperands(combinedFragment_p, _scenario)) {
      CapellaElementExt.cleanTraces(operand);
      operand.destroy();
    }
    CapellaElementExt.cleanTraces(combinedFragment_p.getStart());
    combinedFragment_p.getStart().destroy();
    CapellaElementExt.cleanTraces(combinedFragment_p.getFinish());
    combinedFragment_p.getFinish().destroy();
    CapellaElementExt.cleanTraces(combinedFragment_p);
    combinedFragment_p.destroy();
  }

  /**
   * 
   */
  @Override
  public String toString() {
    if (_messageTree != null) {
      return _messageTree.toString();
    }
    return super.toString();
  }

  /**
   * @param currentNode
   */
  public void unChainCurrentNodeMessage(Node<InteractionFragment> currentNode) {
    if (_messageTree != null) {
      _messageTree.unChainCurrentNodeMessage(currentNode);
      updateListeners();
    }
  }

  /**
   * 
   */
  private void updateListeners() {
    for (IScenarioRepresentationListener listener : _listeners) {
      listener.scenarioChanged(_messageTree);
    }
  }

  /**
   * Update the source operation if have been changed still the previous refinement
   */
  public void updateOperationNode(Node<InteractionFragment> srcNode_p, Node<InteractionFragment> tgtNode_p) {
    InteractionFragment srcAbstractEnd = srcNode_p.getData();
    InteractionFragment tgtAbstractEnd = tgtNode_p.getData();
    if ((srcAbstractEnd != null) && (tgtAbstractEnd != null)) {
      if (srcAbstractEnd instanceof MessageEnd) {
        MessageEnd srcMsgEnd = (MessageEnd) srcAbstractEnd;
        MessageEnd tgtMsgEnd = (MessageEnd) tgtAbstractEnd;

        COMPONENT_TYPE type = MessageEndExt.getMessageEndType(srcMsgEnd);
        switch (type) {
          case RECEIVER: {
            Event srcEvt = srcMsgEnd.getEvent();
            if (srcEvt != null) {
              if (srcEvt instanceof EventReceiptOperation) {
                AbstractEventOperation targetOp = null;
                AbstractEventOperation srcOp = ((EventReceiptOperation) srcEvt).getOperation();
                AbstractEventOperation deletegatedOp = getOperation(srcMsgEnd, tgtMsgEnd.getMessage(), srcOp);

                EventReceiptOperation eventReceiptOp = (EventReceiptOperation) tgtMsgEnd.getEvent();
                if (eventReceiptOp != null) {
                  targetOp = eventReceiptOp.getOperation();
                }

                if (targetOp != deletegatedOp) {
                  eventReceiptOp.setOperation(deletegatedOp);
                  SequenceMessage tgtSeqMsg = tgtMsgEnd.getMessage();
                  tgtSeqMsg.setName(srcMsgEnd.getMessage().getName());
                }
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

              if (targetOp != delegatedOp) {
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
  }

  /**
   * 
   */
  public List<Node<InteractionFragment>> walk() {
    if (_messageTree != null) {
      return _messageTree.walk(_messageTree.getRootElement());
    }
    return null;
  }

  /**
   * Check if the Interface source operation have been changed still the previous refinement
   */
  // private boolean checkInterfaceReafectation(Node<AbstractEnd> srcNode_p, Node<AbstractEnd> tgtNode_p) {
  // AbstractEnd srcAbstractEnd = srcNode_p.getData();
  // AbstractEnd tgtAbstractEnd = tgtNode_p.getData();
  // if (srcAbstractEnd != null) {
  // if (srcAbstractEnd instanceof MessageEnd) {
  // MessageEnd srcMsgEnd = (MessageEnd) srcAbstractEnd;
  // MessageEnd tgtMsgEnd = (MessageEnd) tgtAbstractEnd;
  //
  // COMPONENT_TYPE type = MessageEndExt.getMessageEndType(srcMsgEnd);
  // switch (type) {
  // case RECEIVER: {
  // Event srcEvt = srcMsgEnd.getEvent();
  // if (srcEvt != null) {
  // if (srcEvt instanceof EventReceiptOperation) {
  // AbstractEventOperation targetOp = null;
  // AbstractEventOperation srcOp = ((EventReceiptOperation) srcEvt).getOperation();
  // AbstractEventOperation deletegatedOp = getOperation(srcMsgEnd, tgtMsgEnd.getMessage(), srcOp);
  //
  // EventReceiptOperation eventReceiptOp = (EventReceiptOperation) tgtMsgEnd.getEvent();
  // if (eventReceiptOp != null)
  // targetOp = eventReceiptOp.getOperation();
  //
  // // Check Operation's Interface definition
  // if (targetOp.eContainer() != deletegatedOp.eContainer()) {
  // return true;
  // }
  // }
  // }
  // break;
  // }
  // case SENDER: {
  // EventSentOperation srcEvt = (EventSentOperation) srcMsgEnd.getEvent();
  // if (srcEvt != null) {
  // AbstractEventOperation targetOp = null;
  // AbstractEventOperation srcOp = srcEvt.getOperation();
  // AbstractEventOperation delegatedOp = getOperation(srcMsgEnd, tgtMsgEnd.getMessage(), srcOp);
  //
  // EventSentOperation eventSentOp = (EventSentOperation) tgtMsgEnd.getEvent();
  // if (eventSentOp != null)
  // targetOp = eventSentOp.getOperation();
  //
  // if (targetOp.eContainer() != delegatedOp.eContainer()) {
  // return true;
  // }
  // }
  // break;
  // }
  // case UNDEFINED:
  // break;
  // }
  // }
  // }
  // return false;
  // }

  /**
   *
   */
  // private void removeRefinementLinkFromNode(Node<AbstractEnd> srcNode_p, Node<AbstractEnd> tgtNode_p) {
  // AbstractEnd srcAbstractEnd = srcNode_p.getData();
  // AbstractEnd tgtAbstractEnd = tgtNode_p.getData();
  // if (srcAbstractEnd != null && srcAbstractEnd instanceof MessageEnd) {
  // removeRefinementLinkFromMessageEnd((MessageEnd) srcAbstractEnd, (MessageEnd) tgtAbstractEnd);
  // removeRefinementLinkFromMessageEnd(MessageEndExt.getOppositeMessageEnd((MessageEnd) srcAbstractEnd),
  // MessageEndExt.getOppositeMessageEnd((MessageEnd)tgtAbstractEnd));
  // }
  //	  
  // // For opposite Branch
  // SequenceMessage srcOppositeBranch = SequenceMessageExt.getOppositeSequenceMessage(((MessageEnd) srcAbstractEnd).getMessage());
  // SequenceMessage tgtOppositeBranch = SequenceMessageExt.getOppositeSequenceMessage(((MessageEnd) tgtAbstractEnd).getMessage());
  // AbstractEnd srcOppositeAbstractEnd = srcOppositeBranch.getSendingEnd();
  // AbstractEnd tgtOppositeAbstractEnd = tgtOppositeBranch.getSendingEnd();
  // if (srcOppositeAbstractEnd != null && srcOppositeAbstractEnd instanceof MessageEnd) {
  // removeRefinementLinkFromMessageEnd((MessageEnd) srcOppositeAbstractEnd, (MessageEnd) tgtOppositeAbstractEnd);
  // removeRefinementLinkFromMessageEnd(MessageEndExt.getOppositeMessageEnd((MessageEnd) srcOppositeAbstractEnd),
  // MessageEndExt.getOppositeMessageEnd((MessageEnd)tgtOppositeAbstractEnd));
  // }
  // }

  /**
   *
   */
  // private void removeRefinementLinkFromMessageEnd(MessageEnd srcMsgEnd_p, MessageEnd tgtMsgEnd_p) {
  // Event srcEvt = srcMsgEnd_p.getEvent();
  // Event tgtEvt = tgtMsgEnd_p.getEvent();
  // SequenceMessage srcSeqMsg = srcMsgEnd_p.getMessage();
  // SequenceMessage tgtSeqMsg = tgtMsgEnd_p.getMessage();
  //
  // removeRefinementLink(srcMsgEnd_p, tgtMsgEnd_p);
  // removeRefinementLink(srcEvt, tgtEvt);
  // removeRefinementLink(srcSeqMsg, tgtSeqMsg);
  // }

  /**
   *
   */
  // private void removeRefinementLink(CapellaElement srcElt, CapellaElement tgtElt) {
  // for (AbstractTrace lnk : srcElt.getIncomingTraces()) {
  // if (lnk instanceof RefinementLink) {
  // TraceableElement elt = lnk.getSourceElement();
  // if (elt == tgtElt) {
  // lnk.setSourceElement(null);
  // lnk.setTargetElement(null);
  // Structure ownerStructElt = (Structure) EObjectExt.getFirstContainer(_scenario, CapellacorePackage.Literals.STRUCTURE);
  // ownerStructElt.getOwnedTraces().remove(lnk);
  // break;
  // }
  // }
  // }
  // }
}
