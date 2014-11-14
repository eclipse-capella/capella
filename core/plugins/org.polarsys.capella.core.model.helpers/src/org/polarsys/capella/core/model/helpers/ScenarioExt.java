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
package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;

/**
 * Scenario helpers
 */
public class ScenarioExt {

  /**
   * Retrieve scenarios in which the Event given in parameter is manipulated
   */
  public static List<Scenario> getScenariosFromEvent(Event event_p) {
    List<Scenario> listScenario = new ArrayList<Scenario>();

    for (Object objectRef : EObjectExt.getReferencers(event_p, InteractionPackage.Literals.ABSTRACT_END__EVENT)) {
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
   * @param scenario_p
   * @return
   */
  public static boolean createdByRefinement(Scenario scenario_p) {
    return (getUpperScenario(scenario_p) != null);
  }

  /**
   * This method returns the parent scenario of the current scenario
   * @param scenario_p
   * @return
   */
  public static Scenario getUpperScenario(Scenario scenario_p) {
    Scenario upperScenario = null;

    for (CapellaElement target : RefinementLinkExt.getRefinementRelatedTargetElements(scenario_p, InteractionPackage.Literals.SCENARIO)) {
      upperScenario = (Scenario) target;
    }

    return upperScenario;
  }

  /**
   * This method returns the sub-scenario(s) of the current scenario
   * @param scenario_p
   * @return
   */
  public static List<Scenario> getSubScenarios(Scenario scenario_p) {
    List<Scenario> subScenarios = new ArrayList<Scenario>();

    for (CapellaElement source : RefinementLinkExt.getRefinementRelatedSourceElements(scenario_p, InteractionPackage.Literals.SCENARIO)) {
      subScenarios.add((Scenario) source);
    }

    return subScenarios;
  }

  /**
   * @param scenario_p
   * @param cpnt_p
   * @return
   */
  public static boolean contains(Scenario scenario_p, Component cpnt_p) {
    return getOwnedComponents(scenario_p).contains(cpnt_p);
  }

  /**
   * @param scenario_p
   * @param cpnt_p
   * @return
   */
  public static boolean contains(Scenario scenario_p, Part part_p) {
    return getOwnedParts(scenario_p).contains(part_p);
  }

  /**
   * @param scenario_p
   * @return
   */
  public static System getOwnedSystem(Scenario scenario_p) {
    System system = null;

    for (Component cpnt : getOwnedComponents(scenario_p)) {
      if (cpnt instanceof System) {
        system = (System) cpnt;
      }
    }

    return system;
  }

  /**
   * @param scenario_p
   * @return
   */
  public static List<Actor> getOwnedActors(Scenario scenario_p) {
    List<Actor> actorSet = new ArrayList<Actor>();

    for (Component cpnt : getOwnedComponents(scenario_p)) {
      if (cpnt instanceof Actor) {
        actorSet.add((Actor) cpnt);
      }
    }

    return actorSet;
  }

  /**
   * @param scenario_p
   * @return
   */
  public static List<LogicalComponent> getOwnedLogicalComponents(Scenario scenario_p) {
    List<LogicalComponent> logicalComponentSet = new ArrayList<LogicalComponent>();

    for (Component cpnt : getOwnedComponents(scenario_p)) {
      if (cpnt instanceof LogicalComponent) {
        logicalComponentSet.add((LogicalComponent) cpnt);
      }
    }

    return logicalComponentSet;
  }

  /**
   * @param scenario_p
   * @return
   */
  public static List<PhysicalComponent> getOwnedPhysicalComponents(Scenario scenario_p) {
    List<PhysicalComponent> physicalComponentSet = new ArrayList<PhysicalComponent>();

    for (Component cpnt : getOwnedComponents(scenario_p)) {
      if (cpnt instanceof PhysicalComponent) {
        physicalComponentSet.add((PhysicalComponent) cpnt);
      }
    }

    return physicalComponentSet;
  }

  /**
   * @param scenario_p
   * @return
   */
  public static List<ConfigurationItem> getOwnedConfigurationItems(Scenario scenario_p) {
    List<ConfigurationItem> configurationItemSet = new ArrayList<ConfigurationItem>();

    for (Component cpnt : getOwnedComponents(scenario_p)) {
      if (cpnt instanceof ConfigurationItem) {
        configurationItemSet.add((ConfigurationItem) cpnt);
      }
    }

    return configurationItemSet;
  }

  /**
   * @param scenario_p
   * @return
   */
  public static List<SystemComponent> getOwnedSystemComponents(Scenario scenario_p) {
    List<SystemComponent> systemComponentSet = new ArrayList<SystemComponent>();

    for (Component cpnt : getOwnedComponents(scenario_p)) {
      if (cpnt instanceof SystemComponent) {
        systemComponentSet.add((SystemComponent) cpnt);
      }
    }

    return systemComponentSet;
  }

  public static List<Component> getOwnedComponents(Scenario scenario_p) {
    List<Component> cpntSet = new ArrayList<Component>();

    for (Part part : getOwnedParts(scenario_p)) {
      Component cpnt = (Component) part.getType();
      if (cpnt != null) {
        cpntSet.add(cpnt);
      }
    }

    return cpntSet;
  }

  public static List<Part> getOwnedParts(Scenario scenario_p) {
    List<Part> partSet = new ArrayList<Part>();

    for (InstanceRole role : scenario_p.getOwnedInstanceRoles()) {
      AbstractInstance inst = role.getRepresentedInstance();
      if (inst instanceof Part) {
        partSet.add((Part) inst);
      }
    }

    return partSet;
  }

  /**
   * @param scenario_p
   * @return
   */
  public static List<AbstractEnd> getOwnedAbstractEnds(Scenario scenario_p) {
    List<AbstractEnd> ownedMsgEnd = new ArrayList<AbstractEnd>();

    for (InteractionFragment abs : scenario_p.getOwnedInteractionFragments()) {
      if (abs instanceof AbstractEnd) {
        ownedMsgEnd.add((AbstractEnd) abs);
      }
    }

    return ownedMsgEnd;
  }

  /**
   * @param scenario_p
   * @return
   */
  public static List<MessageEnd> getOwnedMessagesEnds(Scenario scenario_p) {
    List<MessageEnd> ownedMsgEnd = new ArrayList<MessageEnd>();

    for (InteractionFragment abs : scenario_p.getOwnedInteractionFragments()) {
      if (abs instanceof MessageEnd) {
        ownedMsgEnd.add((MessageEnd) abs);
      }
    }

    return ownedMsgEnd;
  }

  /**
   * This method returns the capability related to the given scenario
   * @param scenario_p
   * @return AbstractCapability
   */
  public static AbstractCapability getRelatedCapability(Scenario scenario_p) {
    if (scenario_p != null) {
      return (AbstractCapability) scenario_p.eContainer();
    }
    return null;
  }

  /**
   * This method returns the container of the given scenario
   * @param scenario_p
   * @return CapellaElement
   */
  public static NamedElement getContainer(Scenario scenario_p) {
    return getRecursiveContainer(scenario_p);
  }

  /**
   * This method recursively returns the container of the given scenario
   * @param elt_p
   * @return CapellaElement
   */
  private static NamedElement getRecursiveContainer(NamedElement elt_p) {
    NamedElement elt = null;

    if (elt_p != null) {
      NamedElement container = (NamedElement) elt_p.eContainer();

      if (container instanceof SystemEngineering) {
        elt = container;
      } else if (container instanceof LogicalArchitecture) {
        elt = container;
      } else if (container instanceof LogicalComponent) {
        elt = container;
      } else if (container instanceof PhysicalArchitecture) {
        elt = container;
      } else if (container instanceof PhysicalComponent) {
        elt = container;
      } else if (container instanceof EPBSArchitecture) {
        elt = container;
      } else {
        elt = getRecursiveContainer(container);
      }
    }

    return elt;
  }

  /**
   * Moves the end <code>toMove</code> on the beginning of the scenario. used in common.odesign, oa.odesign, sequences.odesign
   * @param toMove the end to move
   * @return the moved end.
   */
  public static EObject moveEndOnBeginingOfScenario(final InteractionFragment toMove) {
    EList<InteractionFragment> ownedAbstractEnds = ((Scenario) toMove.eContainer()).getOwnedInteractionFragments();
    ownedAbstractEnds.move(0, toMove);
    return toMove;
  }

  /**
   * Moves the end <code>toMove</code> just after the end <code>previousEnd</code>. used in common.odesign, oa.odesign, sequences.odesign
   * @param toMove the end to move
   * @param previousEnd the previous end.
   * @return the moved end.
   */
  public static EObject moveEndOnScenario(final InteractionFragment toMove, final InteractionFragment previousEnd) {
    EObject previousEndContainer = previousEnd.eContainer();
    EList<InteractionFragment> ownedAbstractEnds = null;

    if (previousEndContainer instanceof Scenario) {
      ownedAbstractEnds = ((Scenario) previousEndContainer).getOwnedInteractionFragments();
    }

    /*
     * First of all: move the messageEnd to the end of the list if apply. if not, just add the messageEnd at the end of the list
     */
    if (ownedAbstractEnds.contains(toMove)) {
      ownedAbstractEnds.move(ownedAbstractEnds.size() - 1, toMove);
    } else {
      ownedAbstractEnds.add(toMove);
    }

    /*
     * Compute the new index and move !
     */
    int newIndex = ownedAbstractEnds.indexOf(previousEnd) + 1;
    if (newIndex >= ownedAbstractEnds.size()) {
      newIndex = ownedAbstractEnds.size() - 1;
    }
    ownedAbstractEnds.move(newIndex, toMove);
    return toMove;
  }

  public static EObject moveInteractionFragmentAfter(InteractionFragment element_p, InteractionFragment elementReference_p) {
    if (elementReference_p == null) {
      return moveEndOnBeginingOfScenario(element_p);
    }
    return moveEndOnScenario(element_p, elementReference_p);
  }

  /**
   * @param sendingInstance
   * @param receivingInstance
   * @param messageKind
   * @return
   */
  public static List<CapellaElement> getAvailableExchangeItemsBetweenInstances(AbstractInstance sendingInstance, AbstractInstance receivingInstance,
      MessageKind messageKind) {
    ExchangeItem involvedExchangeItem = null;
    if (sendingInstance instanceof ExchangeItemInstance) {
      involvedExchangeItem = (ExchangeItem) sendingInstance.getAbstractType();
    }
    if (receivingInstance instanceof ExchangeItemInstance) {
      involvedExchangeItem = (ExchangeItem) receivingInstance.getAbstractType();
    }

    BlockArchitecture architecture = ComponentExt.getRootBlockArchitecture(sendingInstance);
    List<CapellaElement> result = new ArrayList<CapellaElement>();

    boolean isSharedDataAccess = (sendingInstance instanceof ExchangeItemInstance) || (receivingInstance instanceof ExchangeItemInstance);
    Collection<ExchangeItem> items = QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_EXCHANGE_ITEMS_FOR_LIB, architecture, new QueryContext());
    for (ExchangeItem item : items) {
      if (isSharedDataAccess) {
        if (item == involvedExchangeItem) {
          result.add(item);
          result.addAll(ExchangeItemExt.getRelatedExchangeItemAllocations(item));
        }
      } else {
        if ((messageKind == MessageKind.SYNCHRONOUS_CALL)
            && ((item.getExchangeMechanism() == ExchangeMechanism.FLOW) || (item.getExchangeMechanism() == ExchangeMechanism.OPERATION))) {
          result.add(item);
          result.addAll(ExchangeItemExt.getRelatedExchangeItemAllocations(item));
        } else if ((messageKind == MessageKind.ASYNCHRONOUS_CALL) && (item.getExchangeMechanism() != ExchangeMechanism.UNSET)) {
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

  /**
   * Returns whether the scenario is a Functional Scenario (ie involves Functions)
   * @param scenario_p
   * @return
   */
  public static boolean isFunctionalScenario(Scenario scenario_p) {
    for (InstanceRole ir : scenario_p.getOwnedInstanceRoles()) {
      if (ir.getRepresentedInstance() instanceof AbstractFunction) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns whether the scenario is a Data Flow behavioural Scenario (ie a DataFlow or Interaction scenario with messages use ComponentExchanges)
   * @param scenario_p
   * @return
   */
  public static boolean isDataFlowBehaviouralScenario(Scenario scenario_p) {
    if ((scenario_p.getKind() == ScenarioKind.INTERACTION) || (scenario_p.getKind() == ScenarioKind.DATA_FLOW)) {
      for (SequenceMessage message : scenario_p.getOwnedMessages()) {
        if ((message.getInvokedOperation() != null) && (message.getInvokedOperation() instanceof ComponentExchange)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns whether the scenario is a Data Flow behavioural Scenario (ie a DataFlow or Interaction scenario with messages use FunctionalExchanges)
   * @param scenario_p
   * @return
   */
  public static boolean isDataFlowFunctionalScenario(Scenario scenario_p) {
    if ((scenario_p.getKind() == ScenarioKind.INTERACTION) || (scenario_p.getKind() == ScenarioKind.DATA_FLOW)) {
      for (SequenceMessage message : scenario_p.getOwnedMessages()) {
        if ((message.getInvokedOperation() != null) && (message.getInvokedOperation() instanceof FunctionalExchange)) {
          return true;
        }
      }
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  public static Object getTargetOnExchangeItem(Object object_p) {
    List<EObject> objs = (List<EObject>) object_p;

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
   * Returns whether scenario_p can realize the given scenario2_p
   * @param scenario_p
   * @param eobject_p
   * @return
   */
  public static boolean canRealize(Scenario scenario_p, Scenario scenario2_p) {
    ScenarioKind sourceKind = scenario_p.getKind();
    ScenarioKind targetKind = scenario2_p.getKind();

    if (scenario2_p.isMerged()) { // not merged one
      return false;

    } else if (targetKind.equals(sourceKind)) { // same kind, valid if
      // different architecture or
      // if they are dataflow
      BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(scenario_p);
      BlockArchitecture targetArchitecture = BlockArchitectureExt.getRootBlockArchitecture(scenario2_p);

      if (((sourceArchitecture == null) || sourceArchitecture.equals(targetArchitecture))) {
        return ScenarioKind.DATA_FLOW.equals(sourceKind)
               || (ScenarioKind.INTERACTION.equals(sourceKind) && (isFunctionalScenario(scenario2_p) || (scenario2_p.getOwnedInstanceRoles().size() == 0)));
      }

      return true;

    } else if (ScenarioKind.DATA_FLOW.equals(sourceKind)) {
      return ScenarioKind.FUNCTIONAL.equals(targetKind) || (ScenarioKind.INTERACTION.equals(targetKind));

    } else if (ScenarioKind.INTERFACE.equals(sourceKind)) {
      return ScenarioKind.DATA_FLOW.equals(targetKind);

    } else if (ScenarioKind.FUNCTIONAL.equals(sourceKind)) {
      return ScenarioKind.INTERACTION.equals(targetKind) && (isFunctionalScenario(scenario2_p) || (scenario2_p.getOwnedInstanceRoles().size() == 0));
    }

    return false;
  }

  /**
   * Look for possible part for creation/update of an instanceROle in a scenario exchange. Retrieves System in the logical architecture level
   * @param any current object from the current scenario
   * @param filter the parts we don't want to see.
   * @return the owned part of the first ownerComponent or rootComponent
   */
  public static Collection<Part> getAllAvailablePartsIncludingSystem(final EObject any, final Collection<Part> filter) {
    Collection<Part> result = new ArrayList<Part>();
    EObject component = EcoreUtil2.getFirstContainer(any, CsPackage.Literals.COMPONENT);
    if (component != null) {
      Component comp = (Component) component;
      getAllOwnedPart(result, comp, filter);
    } else {
      EObject arch = EcoreUtil2.getFirstContainer(any, CsPackage.Literals.BLOCK_ARCHITECTURE);
      Component rootComponent = null;
      if (arch != null) {
        BlockArchitecture architecture = (BlockArchitecture) arch;
        if (architecture instanceof LogicalArchitecture) {
          rootComponent = ((LogicalArchitecture) architecture).getOwnedLogicalComponent();
          if (rootComponent != null) {
            getAllOwnedPart(result, rootComponent, filter);
            if (!rootComponent.getRepresentingPartitions().isEmpty()) {
              result.add(((Part) rootComponent.getRepresentingPartitions().get(0)));
            }
            return result;
          }
        } else if (architecture instanceof PhysicalArchitecture) {
          rootComponent = ((PhysicalArchitecture) architecture).getOwnedPhysicalComponent();
        } else if (architecture instanceof EPBSArchitecture) {
          rootComponent = ((EPBSArchitecture) architecture).getOwnedConfigurationItem();
        }
        if (rootComponent != null) {
          getAllOwnedPart(result, rootComponent, filter);
        }
      }
    }

    return result;
  }

  /**
   * Look for possible part for creation/update of an instanceROle in a scenario
   * @param any current object from the current scenario
   * @param filter the parts we don't want to see.
   * @return the owned part of the first ownerComponent or rootComponent
   */
  public static Collection<Part> getAllAvailableParts(final EObject any, final Collection<Part> filter) {
    Collection<Part> result = new ArrayList<Part>();
    EObject component = EcoreUtil2.getFirstContainer(any, CsPackage.Literals.COMPONENT);
    if (component != null) {
      Component comp = (Component) component;
      getAllOwnedPart(result, comp, filter);
    } else {
      EObject arch = EcoreUtil2.getFirstContainer(any, CsPackage.Literals.BLOCK_ARCHITECTURE);
      Component rootComponent = null;
      if (arch != null) {
        BlockArchitecture architecture = (BlockArchitecture) arch;
        if (architecture instanceof LogicalArchitecture) {
          rootComponent = ((LogicalArchitecture) architecture).getOwnedLogicalComponent();
        } else if (architecture instanceof PhysicalArchitecture) {
          rootComponent = ((PhysicalArchitecture) architecture).getOwnedPhysicalComponent();
        } else if (architecture instanceof EPBSArchitecture) {
          rootComponent = ((EPBSArchitecture) architecture).getOwnedConfigurationItem();
        }
        if (rootComponent != null) {
          getAllOwnedPart(result, rootComponent, filter);
        }
      }
    }

    return result;
  }

  /**
   * @param result owned part of rootComponent
   * @param rootComponent component
   * @param filter the parts we don't want to see.
   */
  private static void getAllOwnedPart(Collection<Part> result, Component rootComponent, Collection<Part> filter) {
    EList<Partition> ownedPartitions = rootComponent.getOwnedPartitions();
    for (Partition partition : ownedPartitions) {
      if (partition instanceof Part) {
        if (!filter.contains(partition)) {
          result.add((Part) partition);
          if ((partition.getAbstractType() != null) && (partition.getAbstractType() instanceof Component)) {
            getAllOwnedPart(result, (Component) partition.getAbstractType(), filter);
          }
        }
      }
    }
  }

  public static List<AbstractActor> getAllActors(final EObject any) {
    ModellingArchitecture architecture = (ModellingArchitecture) EcoreUtil2.getFirstContainer(any, CapellacorePackage.Literals.MODELLING_ARCHITECTURE);
    final List<AbstractActor> result = new LinkedList<AbstractActor>();
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
      if (next instanceof AbstractActor) {
        result.add((AbstractActor) next);
      }
    }

    return result;
  }

  /**
   * Returns all components for the architecture that owns the given object. used in oa.odesign, sequences.odesign
   * @param any any object.
   * @param filter the parts we don't want to see.
   * @return all components for the architecture that owns the given object.
   */
  public static Collection<Part> getAllComponents(final EObject any, final Collection<Part> filter) {
    ModellingArchitecture architecture = (ModellingArchitecture) EcoreUtil2.getFirstContainer(any, CapellacorePackage.Literals.MODELLING_ARCHITECTURE);
    Component rootComponent = null;
    if (architecture instanceof LogicalArchitecture) {
      rootComponent = ((LogicalArchitecture) architecture).getOwnedLogicalComponent();
    }
    if (architecture instanceof PhysicalArchitecture) {
      rootComponent = ((PhysicalArchitecture) architecture).getOwnedPhysicalComponent();
    }
    final List<Part> result = new LinkedList<Part>();
    if (architecture != null) {
      final Iterator<EObject> iterContents = architecture.eAllContents();
      while (iterContents.hasNext()) {
        final EObject next = iterContents.next();

        if (CsPackage.eINSTANCE.getPart().isInstance(next)) {
          final Part currentPart = (Part) next;
          if (CsPackage.eINSTANCE.getComponent().isInstance(currentPart.getAbstractType()) && (!filter.contains(currentPart))
              && (!currentPart.getAbstractType().equals(rootComponent))) {
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
  public static boolean checkOrdering(Scenario scenario_p) {
    // The starting must be absolutely before the end in the relation for each execution and fragments.
    for (TimeLapse tl : scenario_p.getOwnedTimeLapses()) {
      InteractionFragment beginIf = tl.getStart();
      InteractionFragment finishIf = tl.getFinish();

      EList<InteractionFragment> ownedInteractionFragments = scenario_p.getOwnedInteractionFragments();
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
   * @param scenario_p
   * @return
   */
  public static boolean isInterfaceScenario(Scenario scenario_p) {
    return scenario_p.getKind() == ScenarioKind.INTERFACE;
  }

  public static void reorderScenario(Scenario scenario) {

    EList<SequenceMessage> messages = scenario.getOwnedMessages();
    List<SequenceMessage> messagesOrdered = new ArrayList<SequenceMessage>(messages.size());

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
          if ((message.getSendingEnd() == null) && (message.getReceivingEnd() != null) && message.getReceivingEnd().equals(me)) {
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

}
