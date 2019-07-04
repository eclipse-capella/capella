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
package org.polarsys.capella.core.platform.sirius.ui.navigator.internal.navigate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractStringValue;
import org.polarsys.capella.core.data.information.datavalue.BooleanReference;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.EnumerationReference;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.information.datavalue.StringReference;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioRealization;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.model.handler.AbstractModelElementRunnable;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;

/**
 * Provides services to navigation from a {@link ModelElement} to other {@link ModelElement} according to semantic
 * rules.
 */
public class NavigationAdvisor {
  /**
   * Map of handled navigations.<br>
   * The {@link AbstractModelElementRunnable} always returns a {@link List<ModelElement>} as result.
   */
  private static Map<Class<?>, AbstractModelElementRunnable> __handledNavigations;
  /**
   * Singleton instance.
   */
  private static NavigationAdvisor __singleton;

  /**
   * Constructor.
   */
  private NavigationAdvisor() {
    initializeNavigations();
  }

  /**
   * Get navigable elements for given element.
   * 
   * @param element
   * @return a not <code>null</code> set, empty if nothing found. Returned set can't contain <code>null</code> value.
   */
  public Set<EObject> getNavigableElements(Object receiver) {
    HashSet<EObject> navigableElements = new HashSet<EObject>(0);

    if (receiver instanceof IMarker) {
      navigableElements.addAll(MarkerViewHelper.getModelElementsFromMarker((IMarker) receiver));

    } else {
      EObject element = CapellaAdapterHelper.resolveSemanticObject(receiver, true);
      if (element != null) {
        List<AbstractModelElementRunnable> navigationHandlers = getNavigationHandler(element);
        if (!navigationHandlers.isEmpty()) {
          for (AbstractModelElementRunnable modelElementRunnable : navigationHandlers) {
            // Set to the navigation handler the current selection.
            modelElementRunnable.setElement(element);
            // Run it.
            modelElementRunnable.run();
            // Get the result.
            navigableElements.addAll(modelElementRunnable.getResult());
          }
        }
      }
    }

    // Trim potential null value added by navigation handlers.
    navigableElements.remove(null);
    return navigableElements;
  }

  /**
   * Get Navigation Handler.
   * 
   * @param element
   * @return An empty list if nothing found. One element list, if a perfect match was found. Multiple elements if
   *         inheritance was used to retrieve results.
   */
  private List<AbstractModelElementRunnable> getNavigationHandler(EObject element) {
    List<AbstractModelElementRunnable> result = new ArrayList<AbstractModelElementRunnable>(0);
    // 1st Search exact type.
    Class<? extends EObject> elementClass = element.getClass();
    AbstractModelElementRunnable navigationHandler = __handledNavigations.get(elementClass);
    if (null == navigationHandler) {
      // Search something registered that given element is an instance of.
      Iterator<Entry<Class<?>, AbstractModelElementRunnable>> handlerEntries = __handledNavigations.entrySet()
          .iterator();
      // Iterate over registered navigation handlers.
      while (handlerEntries.hasNext()) {
        Map.Entry<Class<?>, AbstractModelElementRunnable> entry = handlerEntries.next();
        // Is given element an instance of something registered ?
        if (entry.getKey().isInstance(element)) {
          // Add all handlers according this rule.
          result.add(entry.getValue());
        }
      }
    } else {
      // Only one handler is returned matching perfectly the element class.
      result.add(navigationHandler);
    }
    return result;
  }

  /**
   * Handle {@link AbstractFunctionalBlock} navigation.
   * 
   * @param block
   * @return a list mapped to {@link AbstractFunctionalBlock#getAllocatedFunctions()}.
   */
  List<EObject> handleAbstractFunctionalBlockNavigation(AbstractFunctionalBlock block) {
    return new ArrayList<EObject>(block.getAllocatedFunctions());
  }

  /**
   * Handle {@link AbstractFunction} navigation.
   * 
   * @param function
   * @return a list mapped to {@link AbstractFunction#getAllocationBlocks()} and realized functions.
   */
  List<EObject> handleAbstractFunctionNavigation(AbstractFunction function) {
    List<EObject> allocationBlocks = new ArrayList<>();

    if (FunctionExt.isLeaf(function)) {
      allocationBlocks.addAll(function.getAllocationBlocks());
    } else {
      allocationBlocks.addAll(AbstractFunctionExt.getMotherFunctionAllocation(function));
    }

    return allocationBlocks;
  }

  /**
   * Handle {@link AbstractTrace} navigation.
   * 
   * @param trace
   * @return a list mapped to {@link AbstractTrace#getTargetElement()}
   */
  List<EObject> handleAbstractTraceNavigation(AbstractTrace trace) {
    ArrayList<EObject> nagivations = new ArrayList<EObject>(1);
    TraceableElement targetElement = trace.getTargetElement();
    if (null != targetElement) {
      nagivations.add(targetElement);
    }
    return nagivations;
  }

  /**
   * Handle {@link AbstractTypedElement} navigation.
   * 
   * @param typedElement
   * @return <code>null</code> if no {@link AbstractType} found in given typed element.
   */
  List<EObject> handleAbstractTypedElementNavigation(AbstractTypedElement typedElement) {
    List<EObject> navigateTowardsElement = new ArrayList<EObject>(0);
    AbstractType abstractType = typedElement.getAbstractType();
    if (null != abstractType) {
      navigateTowardsElement.add(abstractType);
    }
    return navigateTowardsElement;
  }

  /**
   * Handle {@link BooleanReference} navigation.
   * 
   * @param reference
   * @return a list mapped to {@link BooleanReference#getReferencedValue()},
   *         {@link BooleanReference#getReferencedProperty()}
   */
  List<EObject> handleBooleanReferenceNavigation(BooleanReference reference) {
    ArrayList<EObject> nagivations = new ArrayList<EObject>(1);
    AbstractBooleanValue referencedValue = reference.getReferencedValue();
    if (null != referencedValue) {
      nagivations.add(referencedValue);
    }
    Property referencedProperty = reference.getReferencedProperty();
    if (null != referencedProperty) {
      nagivations.add(referencedProperty);
    }
    return nagivations;
  }

  /**
   * Handle {@link Capability} navigation.
   * 
   * @param capability
   * @return a list mapped to {@link Capability#getParticipatingActors()} & {@link Capability#getPurposeMissions()}
   */
  List<EObject> handleCapabilityNavigation(Capability capability) {
    ArrayList<EObject> nagivations = new ArrayList<EObject>(1);
    nagivations.addAll(capability.getInvolvedSystemComponents());
    nagivations.addAll(capability.getPurposeMissions());
    return nagivations;
  }

  /**
   * Handle {@link Collection} navigation.
   * 
   * @param collection
   * @return a list mapped to {@link Collection#getType()}
   */
  List<EObject> handleCollectionNavigation(Collection collection) {
    ArrayList<EObject> nagivations = new ArrayList<EObject>(1);
    Type type = collection.getType();
    if (null != type) {
      nagivations.add(type);
    }
    return nagivations;
  }

  /**
   * Handle {@link CommunicationLink} navigation.
   * 
   * @param communicationLink
   * @return An empty list if no {@link AbstractType} was found.
   */
  List<EObject> handleCommunicationLinkNavigation(CommunicationLink communicationLink) {

    List<EObject> navigateTowardsElement = new ArrayList<EObject>(0);
    ExchangeItem exchangeItem = communicationLink.getExchangeItem();
    if (null != exchangeItem) {
      navigateTowardsElement.add(exchangeItem);
    }
    return navigateTowardsElement;
  }

  /**
   * Handle {@link Component} navigation.
   * 
   * @param component
   * @return a list mapped to {@link Component#getImplementedInterfaces()}, {@link Component#getProvidedInterfaces()},
   *         {@link Component#getUsedInterfaces()}, {@link Component#getRequiredInterfaces()} and realized components
   */
  List<EObject> handleComponentNavigation(Component component) {
    ArrayList<EObject> nagivations = new ArrayList<EObject>();
    nagivations.addAll(component.getImplementedInterfaces());
    nagivations.addAll(component.getProvidedInterfaces());
    nagivations.addAll(component.getUsedInterfaces());
    nagivations.addAll(component.getRequiredInterfaces());
    if (component instanceof SystemComponent) {
      nagivations.addAll(((SystemComponent) component).getInvolvingCapabilities());
      nagivations.addAll(((SystemComponent) component).getInvolvingMissions());
    }
    nagivations.addAll(component.getSub());
    nagivations.addAll(component.getSuper());
    nagivations.addAll(component.getRealizedComponents());
    return nagivations;
  }

  /**
   * Handle {@link EnumerationReference} navigation.
   * 
   * @param reference
   * @return a list mapped to {@link EnumerationReference#getReferencedValue()},
   *         {@link EnumerationReference#getReferencedProperty()}
   */
  List<EObject> handleEnumerationReferenceNavigation(EnumerationReference reference) {
    ArrayList<EObject> nagivations = new ArrayList<EObject>(1);
    AbstractEnumerationValue referencedValue = reference.getReferencedValue();
    if (null != referencedValue) {
      nagivations.add(referencedValue);
    }
    Property referencedProperty = reference.getReferencedProperty();
    if (null != referencedProperty) {
      nagivations.add(referencedProperty);
    }
    return nagivations;
  }

  /**
   * Handle {@link ExchangeItemAllocation} navigation.
   * 
   * @param exchItemAllocation
   * @return An empty list if no {@link ExchangeItem} was found as allocated item.
   */
  List<EObject> handleExchangeItemAllocationNavigation(ExchangeItemAllocation exchItemAllocation) {
    List<EObject> navigateTowardsElement = new ArrayList<EObject>(0);
    ExchangeItem allocatedItem = exchItemAllocation.getAllocatedItem();
    if (null != allocatedItem) {
      navigateTowardsElement.add(allocatedItem);
    }
    return navigateTowardsElement;
  }

  /**
   * Handle {@link ExchangeItemElement} navigation.
   * 
   * @param exchangeItemElement
   * @return An empty list if no {@link AbstractType} was found.
   */
  List<EObject> handleExchangeItemElementNavigation(ExchangeItemElement exchangeItemElement) {

    List<EObject> navigateTowardsElement = new ArrayList<EObject>(0);
    AbstractType type = exchangeItemElement.getAbstractType();
    if (null != type) {
      navigateTowardsElement.add(type);
    }
    return navigateTowardsElement;
  }

  /**
   * Handle {@link FunctionalExchange} navigation.
   * 
   * @param exchange
   * @return a list mapped to {@link FunctionalExchange#getExchangedItems(), @link FunctionalExchange#getCategories()}
   */
  List<EObject> handleFunctionalExchangeNavigation(FunctionalExchange exchange) {
    ArrayList<EObject> nagivations = new ArrayList<EObject>(1);
    EList<ExchangeItem> exchangedItems = exchange.getExchangedItems();
    EList<ExchangeCategory> categories = exchange.getCategories();
    if (!exchangedItems.isEmpty()) {
      nagivations.addAll(exchangedItems);
    }
    if (!categories.isEmpty()) {
      nagivations.addAll(categories);
    }
    return nagivations;
  }

  /**
   * Handle {@link GeneralizableElement} navigation.
   * 
   * @param element
   * @return a list mapped to {@link GeneralizableElement#getSubGeneralizableElements()} & @link
   *         {@link GeneralizableElement#getSuperGeneralizableElements()}
   */
  List<EObject> handleGeneralizableElementNavigation(GeneralizableElement element) {
    ArrayList<EObject> nagivations = new ArrayList<EObject>(1);
    nagivations.addAll(element.getSub());
    nagivations.addAll(element.getSuper());
    return nagivations;
  }

  /**
   * Handle {@link InstanceRole} navigation.
   * 
   * @param instanceRole
   * @return An empty list if no {@link AbstractType} found as representation instance of given instance role.
   */
  List<EObject> handleInstanceRoleNavigation(InstanceRole instanceRole) {
    List<EObject> navigateTowardsElement = new ArrayList<EObject>(0);
    AbstractInstance instance = instanceRole.getRepresentedInstance();
    if (null != instance) {
      if (instance instanceof AbstractFunction) {
        navigateTowardsElement.add(instance);
      }
      if (instance instanceof Role) {
        navigateTowardsElement.add(instance);
      } else {
        AbstractType type = instance.getAbstractType();
        if (null != type) {
          navigateTowardsElement.add(type);
          navigateTowardsElement.add(instance);
        }
      }
    }
    return navigateTowardsElement;
  }

  /**
   * Handle {@link StateFragment} navigation.
   * 
   * @param state
   * @return a list mapped to {@link StateFragment#getRelatedAbstractFunction()},
   *         {@link StateFragment#getRelatedAbstractState()}
   */
  List<EObject> handleStateFragmentNavigation(StateFragment state) {
    ArrayList<EObject> navigations = new ArrayList<EObject>(1);
    if (state.getRelatedAbstractFunction() != null) {
      navigations.add(state.getRelatedAbstractFunction());
    }
    if (state.getRelatedAbstractState() != null) {
      navigations.add(state.getRelatedAbstractState());
    }
    return navigations;
  }

  /**
   * Handle {@link InteractionUse} navigation.
   * 
   * @param use
   * @return a list mapped to {@link InteractionUse#getReferencedScenario()}
   */
  List<EObject> handleInteractionUseNavigation(InteractionUse use) {
    ArrayList<EObject> nagivations = new ArrayList<EObject>(1);
    if (null != use.getReferencedScenario()) {
      nagivations.add(use.getReferencedScenario());
    }
    return nagivations;
  }

  /**
   * Handle {@link InterfaceImplementation} navigation.
   * 
   * @param interfacep
   * @return a list mapped to {@link InterfaceImplementation#getImplementedInterface()}
   */
  List<EObject> handleInterfaceImplementationNavigation(InterfaceImplementation interfacep) {
    ArrayList<EObject> nagivations = new ArrayList<EObject>(1);
    Interface implementedInterface = interfacep.getImplementedInterface();
    if (null != implementedInterface) {
      nagivations.add(implementedInterface);
    }
    return nagivations;
  }

  /**
   * Handle {@link InterfaceUse} navigation.
   * 
   * @param interfacep
   * @return a list mapped to {@link InterfaceUse#getUsedInterface()}
   */
  List<EObject> handleInterfaceUseNavigation(InterfaceUse interfacep) {
    ArrayList<EObject> nagivations = new ArrayList<EObject>(1);
    Interface usedInterface = interfacep.getUsedInterface();
    if (null != usedInterface) {
      nagivations.add(usedInterface);
    }
    return nagivations;
  }

  /**
   * Handle {@link Mission} navigation.
   * 
   * @param mission
   * @return a list mapped to {@link Mission#getParticipatingActors()}, {@link Mission#getExploitedCapabilities()}
   */
  List<EObject> handleMissionNavigation(Mission mission) {
    ArrayList<EObject> nagivations = new ArrayList<EObject>(1);
    nagivations.addAll(mission.getInvolvedSystemComponents());
    nagivations.addAll(mission.getExploitedCapabilities());
    return nagivations;
  }

  /**
   * Handle {@link NumericReference} navigation.
   * 
   * @param reference
   * @return a list mapped to {@link NumericReference#getReferencedValue()},
   *         {@link NumericReference#getReferencedProperty()}
   */
  List<EObject> handleNumericReferenceNavigation(NumericReference reference) {
    ArrayList<EObject> nagivations = new ArrayList<EObject>(1);
    NumericValue referencedValue = reference.getReferencedValue();
    if (null != referencedValue) {
      nagivations.add(referencedValue);
    }
    Property referencedProperty = reference.getReferencedProperty();
    if (null != referencedProperty) {
      nagivations.add(referencedProperty);
    }
    return nagivations;
  }

  /**
   * Handle {@link Port} navigation.
   * 
   * @param port
   * @return a list mapped to {@link Port#getProvidedInterfaces()} & {@link Port#getRequiredInterfaces()}
   */
  List<EObject> handlePortNavigation(Port port) {
    ArrayList<EObject> nagivations = new ArrayList<EObject>(1);
    nagivations.addAll(port.getProvidedInterfaces());
    nagivations.addAll(port.getRequiredInterfaces());

    for (AbstractTrace trace : port.getOutgoingTraces()) {
      if ((trace instanceof PortAllocation) || (trace instanceof PortRealization)) {
        if (null != trace.getTargetElement()) {
          nagivations.add(trace.getTargetElement());
        }
      }
    }
    return nagivations;
  }

  /**
   * Handle {@link Property} navigation.
   * 
   * @param property
   * @return <code>null</code> if no {@link Association} found in given property.
   */
  List<EObject> handlePropertyNavigation(Property property) {
    List<EObject> navigateTowardsElement = new ArrayList<EObject>(0);

    if (property.eContainer() instanceof Association) {
      navigateTowardsElement.add(property.eContainer());
    } else if (property.getAssociation() != null) {
      navigateTowardsElement.add(property.getAssociation());
    }

    navigateTowardsElement.addAll(handleAbstractTypedElementNavigation(property));

    return navigateTowardsElement;
  }

  /**
   * Handle {@link Scenario} navigation.
   * 
   * @param scenario
   * @return a list mapped to realized scenario}
   */
  List<EObject> handleScenarioNavigation(Scenario scenario) {
    ArrayList<EObject> navigations = new ArrayList<EObject>(1);

    for (AbstractTrace trace : scenario.getOutgoingTraces()) {
      if (trace instanceof ScenarioRealization) {
        if (null != trace.getTargetElement()) {
          navigations.add(trace.getTargetElement());
        }
      }
    }
    return navigations;
  }

  /**
   * Handle {@link SequenceMessage} navigation.
   * 
   * @param sequenceMessage
   * @return An empty list if no {@link Operation} found in the sequence message receiving message end.
   */
  List<EObject> handleSequenceMessageNavigation(SequenceMessage sequenceMessage) {
    List<EObject> navigateTowardsElement = new ArrayList<EObject>(0);
    MessageEnd messageEnd = sequenceMessage.getReceivingEnd();
    if (null != messageEnd) {
      Event event = messageEnd.getEvent();
      if ((null != event) && (event instanceof EventReceiptOperation)) {
        EObject navigationElement = null;
        navigationElement = ((EventReceiptOperation) event).getOperation();
        if (null != navigationElement) {
          navigateTowardsElement.add(navigationElement);
        }
      }
    }
    if (sequenceMessage.getExchangeContext() != null) {
      navigateTowardsElement.add(sequenceMessage.getExchangeContext());
    }
    return navigateTowardsElement;
  }

  /**
   * Handle {@link StringReference} navigation.
   * 
   * @param reference
   * @return a list mapped to {@link StringReference#getReferencedValue()},
   *         {@link StringReference#getReferencedProperty()}
   */
  List<EObject> handleStringReferenceNavigation(StringReference reference) {
    ArrayList<EObject> nagivations = new ArrayList<EObject>(1);
    AbstractStringValue referencedValue = reference.getReferencedValue();
    if (null != referencedValue) {
      nagivations.add(referencedValue);
    }
    Property referencedProperty = reference.getReferencedProperty();
    if (null != referencedProperty) {
      nagivations.add(referencedProperty);
    }
    return nagivations;
  }

  /**
   * Handle {@link Association} navigation.
   * 
   * @param association
   * @return a list mapped to {@link Association#getNavigableMembers()}, {@link Association#getOwnedMembers()}
   */
  List<EObject> handleAssociationNavigation(Association association) {
    ArrayList<EObject> navigations = new ArrayList<EObject>(2);
    navigations.addAll(association.getNavigableMembers());
    navigations.addAll(association.getOwnedMembers());
    return navigations;
  }

  /**
   * Handle {@link FunctionalChainInvolvement} navigation.
   * 
   * @param involvement
   * @return a list mapped to {@link FunctionalChainInvolvement#getInvolved()}
   */
  List<EObject> handleFunctionalChainInvolvementNavigation(FunctionalChainInvolvement involvement) {
    ArrayList<EObject> navigations = new ArrayList<EObject>(1);
    InvolvedElement involved = involvement.getInvolved();
    if (null != involved) {
      navigations.add(involved);
    }
    return navigations;
  }

  /**
   * Handle {@link PhysicalPathInvolvement} navigation.
   * 
   * @param involvement
   * @return a list mapped to {@link PhysicalPathInvolvement#getInvolvedElement()}
   */
  List<EObject> handlePhysicalPathInvolvementNavigation(PhysicalPathInvolvement involvement) {
    ArrayList<EObject> navigations = new ArrayList<EObject>(1);
    AbstractPathInvolvedElement involved = involvement.getInvolvedElement();
    if (null != involved) {
      navigations.add(involved);
    }
    return navigations;
  }

  /**
   * Handle {@link StateTransition} navigation.
   * 
   * @param transition
   * @return a list of elements that can be navigated to, starting from the argument state transition.
   */
  List<EObject> handleStateTransitionNavigation(StateTransition transition) {
    if (transition.getGuard() != null) {
      return Collections.<EObject> singletonList(transition.getGuard());
    }
    return Collections.emptyList();
  }

  /**
   * Initialize navigations handlers at first instantiation only.
   */
  private void initializeNavigations() {
    // Initialize the map at first instantiation only.
    if (null == __handledNavigations) {
      __handledNavigations = new HashMap<Class<?>, AbstractModelElementRunnable>();

      // CommunicationLink
      __handledNavigations.put(CommunicationLink.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleCommunicationLinkNavigation((CommunicationLink) getElement()));
        }
      });
      // ExchangeItemAllocation
      __handledNavigations.put(ExchangeItemAllocation.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleExchangeItemAllocationNavigation((ExchangeItemAllocation) getElement()));
        }
      });
      // ExchangeItemElement
      __handledNavigations.put(ExchangeItemElement.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleExchangeItemElementNavigation((ExchangeItemElement) getElement()));
        }
      });
      // SequenceMessage.
      __handledNavigations.put(SequenceMessage.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleSequenceMessageNavigation((SequenceMessage) getElement()));
        }
      });
      // InstanceRole.
      __handledNavigations.put(InstanceRole.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleInstanceRoleNavigation((InstanceRole) getElement()));
        }
      });
      // Property.
      __handledNavigations.put(Property.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handlePropertyNavigation((Property) getElement()));
        }
      });
      // Parameter.
      __handledNavigations.put(Parameter.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleAbstractTypedElementNavigation((AbstractTypedElement) getElement()));
        }
      });
      // DataValue.
      __handledNavigations.put(DataValue.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleAbstractTypedElementNavigation((AbstractTypedElement) getElement()));
        }
      });
      // Collection.
      __handledNavigations.put(Collection.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleCollectionNavigation((Collection) getElement()));
        }
      });
      // Function / Activity to Implementing Component
      __handledNavigations.put(AbstractFunction.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleAbstractFunctionNavigation((AbstractFunction) getElement()));
        }
      });
      // Component navigations.
      __handledNavigations.put(AbstractFunctionalBlock.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleAbstractFunctionalBlockNavigation((AbstractFunctionalBlock) getElement()));
        }
      });
      // Implemented Interfaces to Interface.
      __handledNavigations.put(InterfaceImplementation.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleInterfaceImplementationNavigation((InterfaceImplementation) getElement()));
        }
      });
      // Used Interfaces to Interface.
      __handledNavigations.put(InterfaceUse.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleInterfaceUseNavigation((InterfaceUse) getElement()));
        }
      });
      // Class / Datatype to super /sub relationships.
      __handledNavigations.put(GeneralizableElement.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleGeneralizableElementNavigation((GeneralizableElement) getElement()));
        }
      });
      // Component navigations.
      __handledNavigations.put(Component.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleComponentNavigation((Component) getElement()));
        }
      });
      // Port navigations.
      __handledNavigations.put(Port.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handlePortNavigation((Port) getElement()));
        }
      });
      // Capability navigations.
      __handledNavigations.put(Capability.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleCapabilityNavigation((Capability) getElement()));
        }
      });
      // Mission navigations.
      __handledNavigations.put(Mission.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleMissionNavigation((Mission) getElement()));
        }
      });
      // StringReference navigations.
      __handledNavigations.put(StringReference.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleStringReferenceNavigation((StringReference) getElement()));
        }
      });
      // BooleanReference navigations.
      __handledNavigations.put(BooleanReference.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleBooleanReferenceNavigation((BooleanReference) getElement()));
        }
      });
      // NumericReference navigations.
      __handledNavigations.put(NumericReference.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleNumericReferenceNavigation((NumericReference) getElement()));
        }
      });
      // EnumerationReference navigations.
      __handledNavigations.put(EnumerationReference.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleEnumerationReferenceNavigation((EnumerationReference) getElement()));
        }
      });
      // FunctionalExchange navigations.
      __handledNavigations.put(FunctionalExchange.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleFunctionalExchangeNavigation((FunctionalExchange) getElement()));
        }
      });

      // StateFragment navigations.
      __handledNavigations.put(StateFragment.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleStateFragmentNavigation((StateFragment) getElement()));
        }
      });
      // InteractionUse navigations.
      __handledNavigations.put(InteractionUse.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleInteractionUseNavigation((InteractionUse) getElement()));
        }
      });
      // Scenario navigations.
      __handledNavigations.put(Scenario.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleScenarioNavigation((Scenario) getElement()));
        }
      });
      // AbstractTrace navigations.
      __handledNavigations.put(AbstractTrace.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleAbstractTraceNavigation((AbstractTrace) getElement()));
        }
      });
      // Association navigations
      __handledNavigations.put(Association.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleAssociationNavigation((Association) getElement()));
        }
      });
      // FunctionalChainInvolvement navigations
      __handledNavigations.put(FunctionalChainInvolvement.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleFunctionalChainInvolvementNavigation((FunctionalChainInvolvement) getElement()));
        }
      });
      // PhysicalPathInvolvement navigations
      __handledNavigations.put(PhysicalPathInvolvement.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handlePhysicalPathInvolvementNavigation((PhysicalPathInvolvement) getElement()));
        }
      });
      // StateTransition navigations
      __handledNavigations.put(StateTransition.class, new AbstractModelElementRunnable() {
        public void run() {
          setResult(handleStateTransitionNavigation((StateTransition) getElement()));
        }
      });
    }
  }

  /**
   * Get the singleton instance
   * 
   * @return a not <code>null</code>.
   */
  public static NavigationAdvisor getInstance() {
    if (null == __singleton) {
      __singleton = new NavigationAdvisor();
    }
    return __singleton;
  }
}
