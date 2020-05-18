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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.helpers.information.services.CommunicationLinkExt;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;

/**
 * Scenario helpers
 */
public class ScenarioExt {

  /**
   * Return functions that can be used as StateFragment for a given instanceRole
   */
  public static Collection<AbstractFunction> getAvailableFunctionsStateFragment(AbstractInstance instance) {
    if (instance instanceof Role) {
      Role role = (Role) instance;
      List<AbstractFunction> result = new ArrayList<>();
      for (ActivityAllocation alloc : role.getActivityAllocations()) {
        result.add(alloc.getActivity());
      }
      return result;
    }

    else if (instance.getAbstractType() instanceof Component) {
      Component component = (Component) instance.getAbstractType();
      return getAvailableFunctionsStateFragment(component);
    }

    return Collections.emptyList();
  }

  /**
   * Return functions that can be used as StateFragment for a given component
   */
  public static Collection<AbstractFunction> getAvailableFunctionsStateFragment(Component component) {
    List<AbstractFunction> result = new ArrayList<>();
    Collection<AbstractFunction> functions = new java.util.HashSet<>();

    List<Component> baseComponents = new ArrayList<>();
    baseComponents.add(component);
    // adding all sub Components
    baseComponents.addAll(ComponentExt.getAllSubUsedAndDeployedComponents(component));

    for (Component baseComponent : baseComponents) {
      List<GeneralizableElement> elements = GeneralizableElementExt.getAllSuperGeneralizableElements(baseComponent);
      elements.add(baseComponent);
      for (GeneralizableElement element : elements) {
        if (element instanceof AbstractFunctionalBlock) {
          functions.addAll(((AbstractFunctionalBlock) element).getAllocatedFunctions());
        }
      }
    }
    //

    result.addAll(functions);

    ArrayList<AbstractFunction> newElements = new ArrayList<>();

    // Allow whole function for which all sub decompositions are already in result, recursively.
    do {
      newElements.clear();

      for (AbstractFunction abstractFunction : result) {
        EObject container = abstractFunction.eContainer();
        if (container instanceof AbstractFunction) {
          AbstractFunction af = (AbstractFunction) container;
          if (!result.contains(af)) {
            boolean allInnerAreInResult = true;
            for (EObject inner : af.eContents()) {
              if (inner instanceof AbstractFunction) {
                AbstractFunction innerAf = (AbstractFunction) inner;
                if (!result.contains(innerAf)) {
                  allInnerAreInResult = false;
                }
              }
            }
            if (allInnerAreInResult) {
              newElements.add(af);
            }
          }
        }
      }

      result.addAll(newElements);
    } while (newElements.size() > 0);

    return result;
  }

  public static Collection<AbstractState> getAvailableStateModeStateFragment(AbstractInstance instance) {
    Collection<AbstractState> result = new java.util.HashSet<>();
    Collection<StateMachine> stateMachinas = new java.util.HashSet<>();

    if (instance instanceof Part) {
      Collection<Part> parts = ComponentExt.getPartAncestors((Part) instance, true);
      parts.add((Part) instance);

      for (Part part : parts) {
        if (part.getAbstractType() != null) {
          Component component = (Component) part.getAbstractType();
          List<GeneralizableElement> elements = GeneralizableElementExt.getAllSuperGeneralizableElements(component);
          elements.add(component);
          for (GeneralizableElement element : elements) {
            if (element instanceof Block) {
              stateMachinas.addAll(((Block) element).getOwnedStateMachines());
            }
          }
        }
      }

      for (StateMachine machina : stateMachinas) {
        // Retrieve all AbstractState from the StateMachine
        TreeIterator<EObject> childs = machina.eAllContents();
        while (childs.hasNext()) {
          EObject child = childs.next();
          if (child instanceof AbstractState) {
            result.add((AbstractState) child);
          }
        }
      }

    } else if (instance instanceof AbstractFunction) {
      // functional scenario can use states by relationship
      // function:availableInStateModes
      AbstractFunction af = (AbstractFunction) instance;
      for (State asm : af.getAvailableInStates()) {
        result.add(asm);
      }
    }

    return result;
  }

  /**
   * Retrieve scenarios in which the Event given in parameter is manipulated
   */
  public static List<Scenario> getScenariosFromEvent(Event event) {
    List<Scenario> listScenario = new ArrayList<>();

    for (Object objectRef : EObjectExt.getReferencers(event, InteractionPackage.Literals.ABSTRACT_END__EVENT)) {
      if (objectRef instanceof MessageEnd) {
        Scenario scenario = (Scenario) ((MessageEnd) objectRef).eContainer();
        if (!listScenario.contains(scenario)) {
          listScenario.add(scenario);
        }
      }
    }
    return listScenario;
  }

  /**
   * This method returns TRUE is the current scenario has been created from a refinement
   * 
   * @param scenario
   * @return
   */
  public static boolean createdByRefinement(Scenario scenario) {
    return (getUpperScenario(scenario) != null);
  }

  /**
   * This method returns the parent scenario of the current scenario
   * 
   * @param scenario
   * @return
   */
  public static Scenario getUpperScenario(Scenario scenario) {
    Scenario upperScenario = null;

    for (CapellaElement target : RefinementLinkExt.getRefinementRelatedTargetElements(scenario,
        InteractionPackage.Literals.SCENARIO)) {
      upperScenario = (Scenario) target;
    }

    return upperScenario;
  }

  /**
   * This method returns the sub-scenario(s) of the current scenario
   * 
   * @param scenario
   * @return
   */
  public static List<Scenario> getSubScenarios(Scenario scenario) {
    List<Scenario> subScenarios = new ArrayList<>();

    for (CapellaElement source : RefinementLinkExt.getRefinementRelatedSourceElements(scenario,
        InteractionPackage.Literals.SCENARIO)) {
      subScenarios.add((Scenario) source);
    }

    return subScenarios;
  }

  /**
   * @param scenario
   * @param cpnt
   * @return
   */
  public static boolean contains(Scenario scenario, Component cpnt) {
    return getOwnedComponents(scenario).contains(cpnt);
  }

  /**
   * @param scenario
   * @param cpnt
   * @return
   */
  public static boolean contains(Scenario scenario, Part part) {
    return getOwnedParts(scenario).contains(part);
  }

  /**
   * @param scenario
   * @return
   */
  public static List<LogicalComponent> getOwnedLogicalComponents(Scenario scenario) {
    List<LogicalComponent> logicalComponentSet = new ArrayList<>();

    for (Component cpnt : getOwnedComponents(scenario)) {
      if (cpnt instanceof LogicalComponent) {
        logicalComponentSet.add((LogicalComponent) cpnt);
      }
    }

    return logicalComponentSet;
  }

  /**
   * @param scenario
   * @return
   */
  public static List<PhysicalComponent> getOwnedPhysicalComponents(Scenario scenario) {
    List<PhysicalComponent> physicalComponentSet = new ArrayList<>();

    for (Component cpnt : getOwnedComponents(scenario)) {
      if (cpnt instanceof PhysicalComponent) {
        physicalComponentSet.add((PhysicalComponent) cpnt);
      }
    }

    return physicalComponentSet;
  }

  /**
   * @param scenario
   * @return
   */
  public static List<ConfigurationItem> getOwnedConfigurationItems(Scenario scenario) {
    List<ConfigurationItem> configurationItemSet = new ArrayList<>();

    for (Component cpnt : getOwnedComponents(scenario)) {
      if (cpnt instanceof ConfigurationItem) {
        configurationItemSet.add((ConfigurationItem) cpnt);
      }
    }

    return configurationItemSet;
  }

  public static List<Component> getOwnedComponents(Scenario scenario) {
    List<Component> cpntSet = new ArrayList<>();

    for (Part part : getOwnedParts(scenario)) {
      Component cpnt = (Component) part.getType();
      if (cpnt != null) {
        cpntSet.add(cpnt);
      }
    }

    List<Component> allocatedComponents = getFunctionAllocatedComponents(scenario);
    if (!allocatedComponents.isEmpty()) {
      cpntSet.addAll(allocatedComponents);
    }
    return cpntSet;
  }

  public static List<Part> getOwnedParts(Scenario scenario) {
    List<Part> partSet = new ArrayList<>();

    for (InstanceRole role : scenario.getOwnedInstanceRoles()) {
      AbstractInstance inst = role.getRepresentedInstance();
      if (inst instanceof Part) {
        partSet.add((Part) inst);
      }
    }

    return partSet;
  }

  /**
   * @param scenario
   * @return list of components which have allocated functions involved in the given scenario
   */
  public static List<Component> getFunctionAllocatedComponents(Scenario scenario) {
    List<Component> result = new ArrayList<>();

    EList<InstanceRole> ownedInstances = scenario.getOwnedInstanceRoles();
    for (InstanceRole instance : ownedInstances) {
      AbstractInstance representedInstance = instance.getRepresentedInstance();
      if (representedInstance instanceof AbstractFunction) {
        List<Component> componentsFunc = AbstractFunctionExt
            .getMotherAllFunctionAllocation((AbstractFunction) representedInstance);
        if (!componentsFunc.isEmpty()) {
          result.addAll(componentsFunc);
        }
      }
    }

    return result;
  }

  /**
   * @param scenario
   * @return
   */
  public static List<AbstractEnd> getOwnedAbstractEnds(Scenario scenario) {
    List<AbstractEnd> ownedMsgEnd = new ArrayList<>();

    for (InteractionFragment abs : scenario.getOwnedInteractionFragments()) {
      if (abs instanceof AbstractEnd) {
        ownedMsgEnd.add((AbstractEnd) abs);
      }
    }

    return ownedMsgEnd;
  }

  /**
   * @param scenario
   * @return
   */
  public static List<MessageEnd> getOwnedMessagesEnds(Scenario scenario) {
    List<MessageEnd> ownedMsgEnd = new ArrayList<>();

    for (InteractionFragment abs : scenario.getOwnedInteractionFragments()) {
      if (abs instanceof MessageEnd) {
        ownedMsgEnd.add((MessageEnd) abs);
      }
    }

    return ownedMsgEnd;
  }

  /**
   * This method returns the capability related to the given scenario
   * 
   * @param scenario
   * @return AbstractCapability
   */
  public static AbstractCapability getRelatedCapability(Scenario scenario) {
    if (scenario != null) {
      return (AbstractCapability) scenario.eContainer();
    }
    return null;
  }

  /**
   * This method returns the container of the given scenario
   * 
   * @param scenario
   * @return CapellaElement
   */
  public static NamedElement getContainer(Scenario scenario) {
    return getRecursiveContainer(scenario);
  }

  /**
   * This method recursively returns the container of the given scenario
   * 
   * @param elt
   * @return CapellaElement
   */
  private static NamedElement getRecursiveContainer(NamedElement elt) {
    NamedElement result = null;

    if (elt != null) {
      NamedElement container = (NamedElement) elt.eContainer();

      if (container instanceof SystemEngineering) {
        result = container;
      } else if (container instanceof LogicalArchitecture) {
        result = container;
      } else if (container instanceof LogicalComponent) {
        result = container;
      } else if (container instanceof PhysicalArchitecture) {
        result = container;
      } else if (container instanceof PhysicalComponent) {
        result = container;
      } else if (container instanceof EPBSArchitecture) {
        result = container;
      } else {
        result = getRecursiveContainer(container);
      }
    }

    return result;
  }

  /**
   * Moves the end <code>toMove</code> on the beginning of the scenario. used in common.odesign, oa.odesign,
   * sequences.odesign
   * 
   * @param toMove
   *          the end to move
   * @return the moved end.
   */
  public static EObject moveEndOnBeginingOfScenario(final InteractionFragment toMove) {
    EList<InteractionFragment> ownedAbstractEnds = ((Scenario) toMove.eContainer()).getOwnedInteractionFragments();
    ownedAbstractEnds.move(0, toMove);
    return toMove;
  }

  /**
   * Moves the end <code>toMove</code> just after the end <code>previousEnd</code>. used in common.odesign, oa.odesign,
   * sequences.odesign
   * 
   * @param toMove
   *          the end to move
   * @param previousEnd
   *          the previous end.
   * @return the moved end.
   */
  public static EObject moveEndOnScenario(final InteractionFragment toMove, final InteractionFragment previousEnd) {
    EObject previousEndContainer = previousEnd.eContainer();
    EList<InteractionFragment> ownedAbstractEnds = null;

    if (previousEndContainer instanceof Scenario) {
      ownedAbstractEnds = ((Scenario) previousEndContainer).getOwnedInteractionFragments();
    }

    /*
     * First of all: move the messageEnd to the end of the list if apply. if not, just add the messageEnd at the end of
     * the list
     */
    if (ownedAbstractEnds != null && ownedAbstractEnds.contains(toMove)) {
      ownedAbstractEnds.move(ownedAbstractEnds.size() - 1, toMove);
    } else if (ownedAbstractEnds != null) {
      ownedAbstractEnds.add(toMove);
    }

    /*
     * Compute the new index and move !
     */
    if (ownedAbstractEnds != null) {
      int newIndex = ownedAbstractEnds.indexOf(previousEnd) + 1;
      if (newIndex >= ownedAbstractEnds.size()) {
        newIndex = ownedAbstractEnds.size() - 1;
      }
      ownedAbstractEnds.move(newIndex, toMove);
    }
    return toMove;
  }

  public static EObject moveInteractionFragmentAfter(InteractionFragment element,
      InteractionFragment elementReference) {
    if (elementReference == null) {
      return moveEndOnBeginingOfScenario(element);
    }
    return moveEndOnScenario(element, elementReference);
  }

  /**
   * @param sendingInstance
   * @param receivingInstance
   * @param messageKind
   * @return
   */
  public static List<CapellaElement> getAvailableExchangeItemsBetweenInstances(AbstractInstance sendingInstance,
      AbstractInstance receivingInstance, MessageKind messageKind) {
    ExchangeItem involvedExchangeItem = null;
    if (sendingInstance instanceof ExchangeItemInstance) {
      involvedExchangeItem = (ExchangeItem) sendingInstance.getAbstractType();
    }
    if (receivingInstance instanceof ExchangeItemInstance) {
      involvedExchangeItem = (ExchangeItem) receivingInstance.getAbstractType();
    }

    BlockArchitecture architecture = ComponentExt.getRootBlockArchitecture(sendingInstance);
    List<CapellaElement> result = new ArrayList<>();

    boolean isSharedDataAccess = (sendingInstance instanceof ExchangeItemInstance)
        || (receivingInstance instanceof ExchangeItemInstance);
    Collection<ExchangeItem> items = QueryInterpretor
        .executeQuery(QueryIdentifierConstants.GET_ALL_EXCHANGE_ITEMS_FOR_LIB, architecture, new QueryContext());
    for (ExchangeItem item : items) {
      if (isSharedDataAccess) {
        if (item == involvedExchangeItem) {
          result.add(item);
          result.addAll(ExchangeItemExt.getRelatedExchangeItemAllocations(item));
        }
      } else {
        if ((messageKind == MessageKind.SYNCHRONOUS_CALL) && ((item.getExchangeMechanism() == ExchangeMechanism.FLOW)
            || (item.getExchangeMechanism() == ExchangeMechanism.OPERATION))) {
          result.add(item);
          result.addAll(ExchangeItemExt.getRelatedExchangeItemAllocations(item));
        } else if ((messageKind == MessageKind.ASYNCHRONOUS_CALL)
            && (item.getExchangeMechanism() != ExchangeMechanism.UNSET)) {
          result.add(item);
          result.addAll(ExchangeItemExt.getRelatedExchangeItemAllocations(item));
        } else if ((messageKind == MessageKind.DELETE) || (messageKind == MessageKind.CREATE)) {
          // create and delete message can use every kind of EI et EIA
          result.add(item);
          result.addAll(ExchangeItemExt.getRelatedExchangeItemAllocations(item));
        }
      }
    }
    return result;
  }

  public static SequenceMessage getReply(SequenceMessage message) {
    MessageEnd end = message.getReceivingEnd();
    List<Execution> allocations = (List) EObjectExt.getReferencers(end, InteractionPackage.Literals.TIME_LAPSE__START);
    for (Execution obj : allocations) {
      if (obj.getFinish() instanceof MessageEnd) {
        return ((MessageEnd) obj.getFinish()).getMessage();
      }
    }
    return null;
  }

  public static boolean hasReply(SequenceMessage message) {
    return getReply(message) != null;
  }

  /**
   * Resolves ExchangeItemAllocatons using CommunicationLinks.
   * 
   * @param message_p
   * @return
   */
  public static List<CapellaElement> getRestrictedExchangeItems(InstanceRole source, InstanceRole target,
      boolean isSynchronous) {
    List<CapellaElement> result = new ArrayList<>();

    // Gets the client and the provider of the sequence message
    Component client = null;
    Component provider = null;
    AbstractExchangeItem eiClient = null;

    if (source != null) {
      if (source.getRepresentedInstance().getAbstractType() instanceof Component) {
        client = (Component) source.getRepresentedInstance().getAbstractType();
      } else {
        eiClient = (AbstractExchangeItem) source.getRepresentedInstance().getAbstractType();
      }
    } else {
      client = null;
    }

    if (target != null) {
      if (target.getRepresentedInstance().getAbstractType() instanceof Component) {
        provider = (Component) target.getRepresentedInstance().getAbstractType();
      } else {
        eiClient = (AbstractExchangeItem) target.getRepresentedInstance().getAbstractType();
      }
    } else {
      provider = null;
    }

    // message synchrone vers un SD : on inverse client/provider car il s'agit d'un READ.
    if (((client == null) || (provider == null)) && isSynchronous) {
      Component temp = client;
      client = provider;
      provider = temp;
    }
    // Find potential ExchangeItems using communication links.
    List<AbstractExchangeItem> potentialEI = new ArrayList<>();
    if (provider != null) {
      for (CommunicationLink cl : CommunicationLinkExt.getAllCommunicationLinks(provider)) {
        if ((cl.getKind() == CommunicationLinkKind.RECEIVE) || (cl.getKind() == CommunicationLinkKind.CONSUME)
            || (cl.getKind() == CommunicationLinkKind.EXECUTE) || (cl.getKind() == CommunicationLinkKind.ACCESS)) {
          if (CommunicationLinkKind.ACCESS == cl.getKind()) {
            CommunicationLinkProtocol protocol = cl.getProtocol();
            if (isSynchronous && (CommunicationLinkProtocol.READ == protocol)) {
              potentialEI.add(cl.getExchangeItem());
            }
            if (!isSynchronous && (CommunicationLinkProtocol.ACCEPT == protocol)) {
              potentialEI.add(cl.getExchangeItem());
            }
            if (CommunicationLinkProtocol.UNSET == protocol) {
              potentialEI.add(cl.getExchangeItem());
            }
          } else {
            potentialEI.add(cl.getExchangeItem());
          }
        }

      }
    } else if (eiClient != null) {
      // the provider is a ExchangeItem, so the potentiel is himself
      potentialEI.add((AbstractExchangeItem) target.getRepresentedInstance().getAbstractType());
    }

    if (client != null) {
      for (CommunicationLink cl : CommunicationLinkExt.getAllCommunicationLinks(client)) {
        if ((cl.getKind() == CommunicationLinkKind.CALL) || (cl.getKind() == CommunicationLinkKind.PRODUCE)
            || (cl.getKind() == CommunicationLinkKind.SEND) || (cl.getKind() == CommunicationLinkKind.WRITE)) {
          if (potentialEI.contains(cl.getExchangeItem())) {
            CommunicationLinkProtocol protocol = cl.getProtocol();
            if (cl.getKind() == CommunicationLinkKind.CALL) {
              // filtrage complementaire synchronous/asynchronous au niveau du communication link
              if (isSynchronous && (protocol == CommunicationLinkProtocol.SYNCHRONOUS)) {
                result.add(cl.getExchangeItem());
              }
              if (!isSynchronous && (protocol == CommunicationLinkProtocol.ASYNCHRONOUS)) {
                result.add(cl.getExchangeItem());
              }
              if (protocol == CommunicationLinkProtocol.UNSET) {
                result.add(cl.getExchangeItem());
              }
            } else {
              result.add(cl.getExchangeItem());
            }
          }
        }
      }
    } else {
      // the client is an exchaneItem, it must be the same than potentialEI
      for (AbstractExchangeItem abstractExchangeItem : potentialEI) {
        if (abstractExchangeItem == eiClient) {
          result.add((CapellaElement) eiClient);
          // communication pattern : on rajoute les EIA qui pointent vers cet EI
          List<EObject> eiaPotentials = EObjectExt.getReferencers(abstractExchangeItem,
              CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM);
          for (EObject eObject : eiaPotentials) {
            if (eObject instanceof ExchangeItemAllocation) {
              ExchangeItemAllocation eia = (ExchangeItemAllocation) eObject;
              result.add(eia);
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * Returns whether the scenario is a Functional Scenario (ie involves Functions)
   * 
   * @param scenario
   * @return
   */
  public static boolean isFunctionalScenario(Scenario scenario) {
    for (InstanceRole ir : scenario.getOwnedInstanceRoles()) {
      if (ir.getRepresentedInstance() instanceof AbstractFunction) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns whether the scenario is a Data Flow behavioural Scenario (ie a DataFlow or Interaction scenario with
   * messages use ComponentExchanges)
   * 
   * @param scenario
   * @return
   */
  public static boolean isDataFlowBehaviouralScenario(Scenario scenario) {
    if ((scenario.getKind() == ScenarioKind.INTERACTION) || (scenario.getKind() == ScenarioKind.DATA_FLOW)) {
      for (SequenceMessage message : scenario.getOwnedMessages()) {
        if (message.getInvokedOperation() instanceof ComponentExchange) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns whether the scenario is a Data Flow behavioural Scenario (ie a DataFlow or Interaction scenario with
   * messages use FunctionalExchanges)
   * 
   * @param scenario
   * @return
   */
  public static boolean isDataFlowFunctionalScenario(Scenario scenario) {
    if ((scenario.getKind() == ScenarioKind.INTERACTION) || (scenario.getKind() == ScenarioKind.DATA_FLOW)) {
      for (SequenceMessage message : scenario.getOwnedMessages()) {
        if (message.getInvokedOperation() instanceof FunctionalExchange) {
          return true;
        }
      }
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  public static Object getTargetOnExchangeItem(Object object) {
    List<EObject> objs = (List<EObject>) object;

    EObject result = null;
    if (objs == null) {
      return null;
    }

    for (EObject eObject : objs) {
      if (eObject instanceof Execution) {
        Execution exec = (Execution) eObject;
        if (exec.getCovered().getRepresentedInstance() instanceof ExchangeItemInstance) {
          result = exec;
        }

      }
      if (eObject instanceof InstanceRole) {
        InstanceRole ir = (InstanceRole) eObject;
        if (ir.getRepresentedInstance() instanceof ExchangeItemInstance) {
          result = ir;
        }
      }
    }

    return result;
  }

  /**
   * Returns whether scenario can realize the given scenario2
   * 
   * @param scenario
   * @param eobject
   * @return
   */
  public static boolean canRealize(Scenario scenario, Scenario scenario2) {
    ScenarioKind sourceKind = scenario.getKind();
    ScenarioKind targetKind = scenario2.getKind();

    if (scenario2.isMerged()) { // not merged one
      return false;

    } else if (targetKind.equals(sourceKind)) { // same kind, valid if
      // different architecture or
      // if they are dataflow
      BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(scenario);
      BlockArchitecture targetArchitecture = BlockArchitectureExt.getRootBlockArchitecture(scenario2);

      if (((sourceArchitecture == null) || sourceArchitecture.equals(targetArchitecture))) {
        return ScenarioKind.DATA_FLOW.equals(sourceKind) || (ScenarioKind.INTERACTION.equals(sourceKind)
            && (isFunctionalScenario(scenario2) || (scenario2.getOwnedInstanceRoles().isEmpty())));
      }

      return true;

    } else if (ScenarioKind.DATA_FLOW.equals(sourceKind)) {
      return ScenarioKind.FUNCTIONAL.equals(targetKind) || (ScenarioKind.INTERACTION.equals(targetKind));

    } else if (ScenarioKind.INTERFACE.equals(sourceKind)) {
      return ScenarioKind.DATA_FLOW.equals(targetKind);

    } else if (ScenarioKind.FUNCTIONAL.equals(sourceKind)) {
      return ScenarioKind.INTERACTION.equals(targetKind)
          && (isFunctionalScenario(scenario2) || (scenario2.getOwnedInstanceRoles().isEmpty()));
    }

    return false;
  }

  /**
   * Look for possible part for creation/update of an instanceROle in a scenario
   * 
   * @param any
   *          current object from the current scenario
   * @param filter
   *          the parts we don't want to see.
   * @return the owned part of the first ownerComponent or rootComponent
   */
  public static Collection<Part> getAllAvailableParts(final EObject any, final Collection<Part> filter) {
    Collection<Part> result = new ArrayList<>();
    EObject component = EcoreUtil2.getFirstContainer(any, CsPackage.Literals.COMPONENT);
    if (component != null) {
      Component comp = (Component) component;
      getAllOwnedPart(result, comp, filter);

    } else {
      EObject arch = EcoreUtil2.getFirstContainer(any, CsPackage.Literals.BLOCK_ARCHITECTURE);
      if (arch != null) {
        BlockArchitecture architecture = (BlockArchitecture) arch;
        Component rootComponent = architecture.getSystem();
        if (rootComponent != null) {
          getAllOwnedPart(result, rootComponent, filter);
        }
      }
    }

    return result;
  }

  /**
   * @param result
   *          owned part of rootComponent
   * @param rootComponent
   *          component
   * @param filter
   *          the parts we don't want to see.
   */
  private static void getAllOwnedPart(Collection<Part> result, Component rootComponent, Collection<Part> filter) {
    EList<Part> ownedParts = rootComponent.getContainedParts();
    for (Part part : ownedParts) {
      if (!filter.contains(part)) {
        result.add((Part) part);
        if (part.getAbstractType() instanceof Component) {
          getAllOwnedPart(result, (Component) part.getAbstractType(), filter);
        }
      }
    }
  }

  public static List<Component> getAllActors(final EObject any) {
    ModellingArchitecture architecture = (ModellingArchitecture) EcoreUtil2.getFirstContainer(any,
        CapellacorePackage.Literals.MODELLING_ARCHITECTURE);
    final List<Component> result = new LinkedList<>();
    // in the case of an epbs architecture, we must use the physical actors
    if (architecture instanceof EPBSArchitecture) {
      SystemEngineering se = (SystemEngineering) architecture.eContainer();
      for (ModellingArchitecture ma : se.getOwnedArchitectures()) {
        if (ma instanceof PhysicalArchitecture) {
          architecture = ma;
        }
      }
    }
    final Iterator<EObject> iterContents = architecture.eAllContents();
    while (iterContents.hasNext()) {
      final EObject next = iterContents.next();
      if (next instanceof Component && ((Component) next).isActor()) {
        result.add((Component) next);
      }
    }

    return result;
  }

  /**
   * Returns all components for the architecture that owns the given object. used in oa.odesign, sequences.odesign
   * 
   * @param any
   *          any object.
   * @param filter
   *          the parts we don't want to see.
   * @return all components for the architecture that owns the given object.
   */
  public static Collection<Part> getAllComponents(final EObject any, final Collection<Part> filter) {
    ModellingArchitecture architecture = (ModellingArchitecture) EcoreUtil2.getFirstContainer(any,
        CapellacorePackage.Literals.MODELLING_ARCHITECTURE);
    Component rootComponent = null;
    if (architecture instanceof LogicalArchitecture || architecture instanceof PhysicalArchitecture) {
      rootComponent = ((BlockArchitecture) architecture).getSystem();
    }

    final List<Part> result = new LinkedList<>();
    if (architecture != null) {
      final Iterator<EObject> iterContents = architecture.eAllContents();
      while (iterContents.hasNext()) {
        final EObject next = iterContents.next();

        if (CsPackage.eINSTANCE.getPart().isInstance(next)) {
          final Part currentPart = (Part) next;
          if (CsPackage.eINSTANCE.getComponent().isInstance(currentPart.getAbstractType())
              && (!filter.contains(currentPart)) && (!currentPart.getAbstractType().equals(rootComponent))) {
            result.add(currentPart);
          }
        }

      }
    }

    return result;
  }

  /**
   * @param eObj_p
   * @return
   */
  public static boolean checkOrdering(Scenario scenario) {
    // The starting must be absolutely before the end in the relation for each execution and fragments.
    for (TimeLapse tl : scenario.getOwnedTimeLapses()) {
      InteractionFragment beginIf = tl.getStart();
      InteractionFragment finishIf = tl.getFinish();

      EList<InteractionFragment> ownedInteractionFragments = scenario.getOwnedInteractionFragments();
      int beginIndex = ownedInteractionFragments.indexOf(beginIf);
      int endIndex = ownedInteractionFragments.indexOf(finishIf);

      if (endIndex < beginIndex) {
        return false;
      }
    }
    return true;
  }

  public static void reorderTimeLapseFragments(Scenario scenario) {
    for (TimeLapse tl : scenario.getOwnedTimeLapses()) {
      InteractionFragment beginIf = tl.getStart();
      InteractionFragment finishIf = tl.getFinish();
      EList<InteractionFragment> ownedInteractionFragments = scenario.getOwnedInteractionFragments();
      int beginIndex = ownedInteractionFragments.indexOf(beginIf);
      int endIndex = ownedInteractionFragments.indexOf(finishIf);

      // permutation of start and finish
      ownedInteractionFragments.remove(beginIf);
      ownedInteractionFragments.add(endIndex, beginIf);

      ownedInteractionFragments.remove(finishIf);
      ownedInteractionFragments.add(beginIndex, finishIf);

    }

  }

  /**
   * @param scenario
   * @return
   */
  public static boolean isInterfaceScenario(Scenario scenario) {
    return scenario.getKind() == ScenarioKind.INTERFACE;
  }

  public static StateFragment getFragment(InteractionState state) {
    List<TimeLapse> allocations = (List) EObjectExt.getReferencers(state,
        InteractionPackage.Literals.TIME_LAPSE__START);
    if ((allocations.size() == 1) && (allocations.get(0) instanceof StateFragment)) {
      return (StateFragment) allocations.get(0);
    }
    allocations = (List) EObjectExt.getReferencers(state, InteractionPackage.Literals.TIME_LAPSE__FINISH);
    if ((allocations.size() == 1) && (allocations.get(0) instanceof StateFragment)) {
      return (StateFragment) allocations.get(0);
    }
    return null;
  }

  public static Collection<InstanceRole> getCoveredInstanceRoles(StateFragment state) {
    Collection<InstanceRole> roles = new ArrayList<>();
    for (InstanceRole role : state.getStart().getCoveredInstanceRoles()) {
      if (!roles.contains(role)) {
        roles.add(role);
      }
    }
    for (InstanceRole role : state.getFinish().getCoveredInstanceRoles()) {
      if (!roles.contains(role)) {
        roles.add(role);
      }
    }
    return roles;
  }

  public static void reorderScenario(Scenario scenario) {

    EList<SequenceMessage> messages = scenario.getOwnedMessages();
    List<SequenceMessage> messagesOrdered = new ArrayList<>(messages.size());

    for (SequenceMessage sequenceMessage : messages) {
      messagesOrdered.add(sequenceMessage);
    }

    int index = 0;
    for (InteractionFragment if_ : scenario.getOwnedInteractionFragments()) {
      if (if_ instanceof MessageEnd) {
        MessageEnd me = (MessageEnd) if_;
        SequenceMessage message = me.getMessage();
        if (message != null) {
          if ((message.getSendingEnd() != null) && message.getSendingEnd().equals(me)) {
            // reposition the message in the list.
            messagesOrdered.remove(message);
            messagesOrdered.add(index, message);
            index++;
          }
          if ((message.getSendingEnd() == null) && (message.getReceivingEnd() != null)
              && message.getReceivingEnd().equals(me)) {
            // found message case
            // reposition the message in the list.
            messagesOrdered.remove(message);
            messagesOrdered.add(index, message);
            index++;
          }
        }
      }
    }
    if (!messages.equals(messagesOrdered)) {
      // There was a change.
      scenario.getOwnedMessages().clear();
      scenario.getOwnedMessages().addAll(messagesOrdered);
    }
  }

  /**
   * Does the given Scenario contain more than one InstanceRole with identical represented instances?
   */
  public static boolean isMultiInstanceRole(Scenario scenario) {
    Collection<AbstractInstance> is = new HashSet<>();
    for (InstanceRole ir : scenario.getOwnedInstanceRoles()) {
      if (ir.getRepresentedInstance() != null && !is.add(ir.getRepresentedInstance())) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param obj
   * @return AbstractType
   */
  public static AbstractType getAbstractType(EObject obj) {
    AbstractType type = null;
    if (obj instanceof InstanceRole) {
      type = ((InstanceRole) obj).getRepresentedInstance().getAbstractType();
    }
    return type;
  }
}
