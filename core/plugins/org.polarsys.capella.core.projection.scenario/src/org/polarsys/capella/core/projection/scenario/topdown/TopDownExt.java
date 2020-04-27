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
package org.polarsys.capella.core.projection.scenario.topdown;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.core.projection.common.TransitionHelper;
import org.polarsys.capella.core.projection.common.resolver.ResolverFinalizer;
import org.polarsys.capella.core.projection.scenario.Messages;
import org.polarsys.capella.core.projection.scenario.ScenarioTransfo;
import org.polarsys.capella.core.projection.scenario.helpers.ScenarioExt;
import org.polarsys.capella.core.projection.scenario.helpers.UnwantedObjects;
import org.polarsys.capella.core.tiger.IResolver;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 */
public class TopDownExt {

  /**
   * Retrieve related instances which will be used in the transitioned scenario of the given scenario
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static List<AbstractInstance> getTargetInstances(Scenario scenario, ITransfo transfo_p) {
    List<AbstractInstance> partBounds = new ArrayList<AbstractInstance>();

    for (InteractionFragment fragment : scenario.getOwnedInteractionFragments()) {
      if (fragment instanceof AbstractEnd) {
        AbstractEnd end = (AbstractEnd) fragment;
        AbstractEventOperation transitionedOperation = getTransitionedOperation(end, transfo_p);
        boolean isSource = ScenarioExt.isSource(end);
        AbstractInstance instance = getTargetInstance(isSource, end, transitionedOperation, transfo_p);
        if (instance != null) {
          partBounds.add(instance);
        }
      }
    }

    partBounds = (List) ListExt.removeDuplicates((List) partBounds);

    return partBounds;
  }

  /**
   * Retrieve related instances which will be used in the transitioned instance role of the given instance role
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static List<AbstractInstance> getTargetInstances(InstanceRole role, ITransfo transfo_p) {
    List<AbstractInstance> partBounds = new ArrayList<AbstractInstance>();

    for (AbstractEnd end : role.getAbstractEnds()) {
      AbstractEventOperation transitionedOperation = getTransitionedOperation(end, transfo_p);
      boolean isSource = ScenarioExt.isSource(end);
      AbstractInstance instance = getTargetInstance(isSource, end, transitionedOperation, transfo_p);
      if (instance != null) {
        partBounds.add(instance);
      } else {
        UnwantedObjects.add(end.getEvent(), transfo_p);
      }
    }

    if ((partBounds.size() == 0) && (role.getRepresentedInstance() != null)) {
      String messageTxxt = Messages.Scenario_MultipleTransitionedElements;
      List<? extends EObject> availables = Query.retrieveTransformedElements(role.getRepresentedInstance(), transfo_p,
          InformationPackage.Literals.ABSTRACT_INSTANCE);
      if ((availables.size() == 0) && (role.getRepresentedInstance() instanceof Part)) {
        for (Component element : (List<Component>) Query.retrieveTransformedElements(
            role.getRepresentedInstance().getAbstractType(), transfo_p, CsPackage.Literals.COMPONENT)) {
          availables.addAll((List) element.getRepresentingParts());
        }
      }

      if (availables.size() <= 1) {
        partBounds.addAll((List) availables);
      } else {
        IResolver resolver = ResolverFinalizer.getResolver(transfo_p);
        if (resolver != null) {
          List<EObject> res = resolver.resolve(role.getRepresentedInstance(), (List) availables,
              ScenarioTransfo.getTitle(transfo_p), messageTxxt, false, transfo_p,
              new EObject[] { role.getRepresentedInstance() });
          if ((res != null) && (res.size() > 0)) {
            partBounds.add((AbstractInstance) res.get(0));
          }
        }
      }
    }

    partBounds = (List) ListExt.removeDuplicates((List) partBounds);

    return partBounds;
  }

  /**
   * Retrieve the target instance for the given end and given operation_p
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static AbstractInstance getTargetInstance(boolean isSource, AbstractEnd sourceEnd,
      AbstractEventOperation targetOperation, ITransfo transfo_p) {
    List<AbstractInstance> partBounds = new ArrayList<AbstractInstance>();

    // If there is no operation, then there is no related instance
    if (targetOperation == null) {
      return null;
    }

    if (ScenarioExt.isFunctionalInstanceRole(sourceEnd) || ScenarioExt.isRoleInstanceRole(sourceEnd)) {
      Collection<AbstractInstance> relatedParts = getRelatedInstances(isSource, targetOperation, transfo_p);
      partBounds.addAll(relatedParts);

    } else if (ScenarioExt.isPartInstanceRole(sourceEnd)) {
      Collection<AbstractInstance> relatedParts = getRelatedInstances(isSource, targetOperation, transfo_p);

      // Restrict to components or parent components.
      for (AbstractInstance relatedPart : relatedParts) {
        for (AbstractInstance parent : getRelatedPartAndAncestors(relatedPart)) {
          for (InstanceRole relatedRole : sourceEnd.getCoveredInstanceRoles()) {
            if (RefinementLinkExt.isLinkedTo(parent, relatedRole.getRepresentedInstance())) {
              partBounds.add(relatedPart);
            }
          }
        }
      }

    } else if (ScenarioExt.isExchangeItemInstanceRole(sourceEnd)) {
      // If targetOperation is in target architecture, retrieve the ExchangeItemInstance in the target architecture
      // If targetOperation is in source architecture, retrieve the current ExchangeItemInstance
      ExchangeItemInstance instance = (ExchangeItemInstance) ScenarioExt.getExchangeItemInstanceRole(sourceEnd);
      if (targetOperation instanceof ExchangeItemAllocation) {
        if (RefinementLinkExt.isLinkedTo(((ExchangeItemAllocation) targetOperation).getAllocatedItem(),
            instance.getType())) {
          List<?> instances = Query.retrieveTransformedElements(instance, transfo_p,
              InformationPackage.Literals.ABSTRACT_INSTANCE);
          partBounds.addAll((List) instances);

        } else if (((ExchangeItemAllocation) targetOperation).getAllocatedItem().equals(instance.getType())) {
          partBounds.add(instance);
        }
      }
    }

    partBounds = (List) ListExt.removeDuplicates((List) partBounds);

    if (partBounds.size() == 0) {
      return null;
    } else if (partBounds.size() == 1) {
      return partBounds.get(0);
    }

    IResolver resolver = ResolverFinalizer.getResolver(transfo_p);
    if (resolver == null) {
      return partBounds.get(0);
    }

    SequenceMessage message = ScenarioExt.getRelatedSequenceMessage(sourceEnd);
    String messageTxt = NLS.bind((isSource ? Messages.Scenario_SourceMessage : Messages.Scenario_TargetMessage),
        new Object[] { getPosition(message), EObjectLabelProviderHelper.getText(message),
            EObjectLabelProviderHelper.getText(targetOperation),
            EObjectLabelProviderHelper.getMetaclassLabel(targetOperation, true) });

    EObject context = (isSource ? message.getSendingEnd() : message.getReceivingEnd());
    return (AbstractInstance) resolver.resolve(targetOperation, (List) partBounds, ScenarioTransfo.getTitle(transfo_p),
        messageTxt, false, transfo_p, new EObject[] { message, targetOperation, context }).get(0);
  }

  /**
   * Retrieve for an instance, all instances which can be considered to be the instance
   */
  private static Collection<AbstractInstance> getRelatedPartAndAncestors(AbstractInstance relatedPart_p) {

    List<AbstractInstance> parents = new ArrayList<AbstractInstance>();
    parents.add(relatedPart_p);

    if (relatedPart_p instanceof Part) {
      parents.addAll(ComponentExt.getPartAncestors((Part) relatedPart_p, true));
    }
    return parents;
  }

  /**
   * @param message_p
   * @return
   */
  private static String getPosition(SequenceMessage message_p) {
    EObject container = message_p.eContainer();
    EStructuralFeature reference = message_p.eContainingFeature();
    Object value = null;
    int position = 1;

    if ((container != null) && (reference != null) && reference.isMany()) {
      value = container.eGet(reference);
      if (value instanceof List<?>) {
        position = ((List<?>) value).indexOf(message_p) + 1;
      }
    }

    switch (position % 10) {
    case 1:
      return position + Messages.Scenario_First;
    case 2:
      return position + Messages.Scenario_Second;
    case 3:
      return position + Messages.Scenario_Third;
    default:
      return position + Messages.Scenario_Nth;
    }
  }

  /**
   * Retrieve for an abstract end and a related operation, instances which are related to the bound of the operation
   * according to the end of the message For a bound of a ce, returns related parts For a bound of a fe, returns related
   * functions, or part according to scenario kind
   */
  public static Collection<AbstractInstance> getRelatedInstances(boolean isSource, EObject operation,
      ITransfo transfo_p) {

    Scenario scenario = (Scenario) transfo_p.get(TransfoEngine.TRANSFO_SOURCE);
    // On functional scenario, we return directly related function, otherwise, allocating parts
    boolean shouldRetrieveAllocating = !TransitionHelper.getService().isFunctionalScenario(scenario);

    Collection<Component> componentBounds = new LinkedHashSet<Component>();
    Collection<AbstractInstance> instances = new LinkedHashSet<AbstractInstance>();

    if (operation instanceof ComponentExchange) {
      // Retrieve related parts for a bound of a connection
      // (the connected part or any representing parts of the connected type)
      ComponentExchange exchange = (ComponentExchange) operation;
      if (isSource || !exchange.isOriented()) {
        instances.addAll(ComponentExchangeExt.getSourceParts(exchange));
      }
      if (!isSource || !exchange.isOriented()) {
        instances.addAll(ComponentExchangeExt.getTargetParts(exchange));
      }

    } else if (operation instanceof FunctionalExchange) {
      // Retrieve related parts for a bound of a functional exchange
      // (any representing parts of components allocating the function)
      FunctionalExchange exchange = (FunctionalExchange) operation;
      AbstractFunction function = isSource ? FunctionalExchangeExt.getSourceFunction(exchange)
          : FunctionalExchangeExt.getTargetFunction(exchange);

      if (function != null) {
        if (shouldRetrieveAllocating) {
          componentBounds.addAll(AbstractFunctionExt.getAllocatingComponents(function));

          if (function instanceof OperationalActivity) {
            for (Role role : ((OperationalActivity) function).getAllocatingRoles()) {
              componentBounds.addAll(role.getAllocatingEntities());
            }
          }

        } else {
          instances.add(function);
        }
      }
    } else if (operation instanceof ExchangeItemAllocation) {
      // Retrieve related parts of the allocating interface.
      // (the requiring or providing interfaces)
      Interface itf = ((ExchangeItemAllocation) operation).getAllocatingInterface();
      if (itf != null) {
        if (isSource) {
          componentBounds.addAll(itf.getUserComponents());
          componentBounds.addAll(itf.getRequiringComponents());
        } else {
          componentBounds.addAll(itf.getImplementorComponents());
          componentBounds.addAll(itf.getProvidingComponents());
        }
      }
    }

    for (Component component : componentBounds) {
      if (component != null) {
        instances.addAll(component.getRepresentingParts());
      }
    }

    // For EPBS, replace the parts with their allocating ConfigurationItem ones
    EObject targetScenario = Query.retrieveTransformedElements(scenario.eContainer(), transfo_p).get(0);
    BlockArchitecture targetArchitecture = BlockArchitectureExt.getRootBlockArchitecture(targetScenario);
    if (targetArchitecture instanceof EPBSArchitecture) {
      Iterator<AbstractInstance> iterator = instances.iterator();
      while (iterator.hasNext()) {
        AbstractInstance instance = iterator.next();
        iterator.remove();
        if (instance != null && instance.getAbstractType() instanceof PhysicalComponent) {
          for (ConfigurationItem item : ((PhysicalComponent) instance.getAbstractType())
              .getAllocatorConfigurationItems()) {
            instances.addAll(item.getRepresentingParts());
          }
        }
      }
    }

    // In any case, Filter parts to the expected architecture
    instances.removeIf(x -> !targetArchitecture.eClass().isInstance(BlockArchitectureExt.getRootBlockArchitecture(x)));
    instances.remove(null);
    return instances;

  }

  /**
   * Returns the operation which should be replaced into the transitioned scenario.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static AbstractEventOperation getTransitionedOperation(EObject element_p, ITransfo transfo_p) {

    if (element_p instanceof ExecutionEnd) {
      return getTransitionedOperation(((ExecutionEnd) element_p).getExecution().getStart(), transfo_p);
    }
    if (element_p instanceof AbstractEnd) {
      return getTransitionedOperation(((AbstractEnd) element_p).getEvent(), transfo_p);
    }

    if (element_p instanceof Event) {
      SequenceMessage message = ScenarioExt.getRelatedSequenceMessage((Event) element_p);
      AbstractEventOperation operation = ScenarioExt.getOperation(element_p);

      String messageTxxt = Messages.Scenario_MultipleTransitionedElements;

      List<? extends EObject> availables = Query.retrieveTransformedElements(operation, transfo_p,
          InformationPackage.Literals.ABSTRACT_EVENT_OPERATION);

      if (availables.size() == 0) {
        // In case where the operation is an ExchangeItem and doesn't exist in the target architecture,
        // we allow to reference the original one. Indeed, since transition is done before scenario initialization,
        // user hasn't checked preference to propagate ExchangeItem but want to reference the original one.
        if (operation instanceof ExchangeItemAllocation) {
          return operation;
        }
        return null;
      }
      if (availables.size() == 1) {
        return (AbstractEventOperation) availables.get(0);
      }
      IResolver resolver = ResolverFinalizer.getResolver(transfo_p);
      if (resolver == null) {
        // If no resolver, return the first
        return (AbstractEventOperation) availables.get(0);
      }
      List<EObject> res = resolver.resolve(operation, (List) availables, ScenarioTransfo.getTitle(transfo_p),
          messageTxxt, false, transfo_p, new EObject[] { operation, message });
      if ((res != null) && (res.size() > 0)) {
        return (AbstractEventOperation) res.get(0);
      }

    }

    return null;

  }

}
