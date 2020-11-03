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
package org.polarsys.capella.test.transition.ju;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;

/**
 * Utility class for projection tests
 */
public final class ProjectionTestUtils {

  public static final Component getAllocatedComponent(Component component_p) {
    List<Component> allocatedComponents = component_p.getRealizedComponents();
    final int size = allocatedComponents.size();
    if (size > 0) {
      return allocatedComponents.get(size - 1);
    }
    return null;
  }

  public static final ModelElement getAllocatedElement(TraceableElement function_p) {
    List<AbstractTrace> allocatedFunctions = function_p.getOutgoingTraces();
    final int size = allocatedFunctions.size();
    if (size > 0) {
      return allocatedFunctions.get(size - 1).getTargetElement();
    }
    return null;
  }

  public static final AbstractFunction getAllocatedFunction(AbstractFunction function_p) {
    List<FunctionRealization> allocatedFunctions = function_p.getOutFunctionRealizations();
    final int size = allocatedFunctions.size();
    if (size > 0) {
      return allocatedFunctions.get(size - 1).getAllocatedFunction();
    }
    return null;
  }

  public static final AbstractFunction getAllocatedFunction(SystemComponent system_p) {
    EList<AbstractFunction> allocatedFunctions = system_p.getAllocatedFunctions();
    final int size = allocatedFunctions.size();
    if (size > 0) {
      return allocatedFunctions.get(size - 1);
    }
    return null;
  }

  public static final FunctionalChain getAllocatedFunctionalChain(FunctionalChain function_p) {
    List<AbstractTrace> allocatedFunctions = function_p.getOutgoingTraces();
    final int size = allocatedFunctions.size();
    if (size > 0) {
      return (FunctionalChain) allocatedFunctions.get(size - 1).getTargetElement();
    }
    return null;
  }

  public static final FunctionalExchange getAllocatedFunctionalExchange(FunctionalExchange function_p) {
    List<FunctionalExchangeRealization> allocatedFunctions = function_p.getOutgoingFunctionalExchangeRealizations();
    final int size = allocatedFunctions.size();
    if (size > 0) {
      return allocatedFunctions.get(size - 1).getRealizedFunctionalExchange();
    }
    return null;
  }

  public static final Port getAllocatedPort(Port function_p) {
    List<PortRealization> allocatedFunctions = function_p.getOutgoingPortRealizations();
    final int size = allocatedFunctions.size();
    if (size > 0) {
      return allocatedFunctions.get(size - 1).getRealizedPort();
    }
    return null;
  }

  public static final Component getAllocatingComponent(Component component_p) {
    List<Component> allocatingComponents = component_p.getRealizingComponents();
    final int size = allocatingComponents.size();
    return allocatingComponents.get(size - 1);
  }

  public static final ComponentExchange getAllocatingComponentExchange(ComponentExchange connection_p) {
    List<ComponentExchangeRealization> allocatingConnections = connection_p.getIncomingComponentExchangeRealizations();
    final int size = allocatingConnections.size();
    return (size == 0) ? null : allocatingConnections.get(size - 1).getAllocatingComponentExchange();
  }

  public static final ModelElement getAllocatingElement(TraceableElement function_p) {
    List<AbstractTrace> allocatingFunctions = function_p.getIncomingTraces();
    final int size = allocatingFunctions.size();
    if (size > 0) {
      return allocatingFunctions.get(size - 1).getSourceElement();
    }
    return null;
  }

  public static final List<ModelElement> getAllocatingElements(TraceableElement function_p) {
    return getAllocatingElements(function_p, null);
  }

  public static final List<ModelElement> getAllocatingElements(TraceableElement function_p, EObject container) {
    List<ModelElement> result = new ArrayList<ModelElement>();

    List<AbstractTrace> allocatingFunctions = function_p.getIncomingTraces();
    for (AbstractTrace trace : allocatingFunctions) {
      if ((container == null) || EcoreUtil2.isOrIsContainedBy(trace.getSourceElement(), container)) {
        result.add(trace.getSourceElement());
      }
    }
    return result;
  }

  public static final ExchangeItem getAllocatingExchangeItem(ExchangeItem exchangeItem_p) {
    List<AbstractTrace> incoming = exchangeItem_p.getIncomingTraces();
    final int size = (incoming != null) ? incoming.size() : 0;
    return (ExchangeItem) ((size == 0) ? null : incoming.get(size - 1).getSourceElement());
  }

  public static final AbstractFunction getAllocatingFunction(AbstractFunction function_p) {
    List<FunctionRealization> allocatingFunctions = function_p.getInFunctionRealizations();
    final int size = allocatingFunctions.size();
    if (size > 0) {
      return allocatingFunctions.get(size - 1).getAllocatingFunction();
    }
    return null;
  }

  public static final FunctionalChain getAllocatingFunctionalChain(FunctionalChain function_p) {
    List<AbstractTrace> allocatingFunctions = function_p.getIncomingTraces();
    final int size = allocatingFunctions.size();
    if (size > 0) {
      return (FunctionalChain) allocatingFunctions.get(size - 1).getSourceElement();
    }
    return null;
  }

  public static final FunctionalExchange getAllocatingFunctionalExchange(FunctionalExchange function_p) {
    List<FunctionalExchangeRealization> allocatingFunctions = function_p.getIncomingFunctionalExchangeRealizations();
    final int size = allocatingFunctions.size();
    if (size > 0) {
      return allocatingFunctions.get(size - 1).getRealizingFunctionalExchange();
    }
    return null;
  }

  public static final Interface getAllocatingInterface(Interface interface_p) {
    List<InterfaceAllocation> allocatingInterfaces = interface_p.getProvisioningInterfaceAllocations();
    final int size = allocatingInterfaces.size();
    return (Interface) ((size == 0) ? null : allocatingInterfaces.get(size - 1).getSourceElement());
  }

  public static final Port getAllocatingPort(Port function_p) {
    List<PortRealization> allocatingFunctions = function_p.getIncomingPortRealizations();
    final int size = allocatingFunctions.size();
    if (size > 0) {
      return allocatingFunctions.get(size - 1).getRealizingPort();
    }
    return null;
  }

  public static final SystemComponent getCreatedActor(Entity entity_p) {
    return (SystemComponent) getAllocatingComponent(entity_p);
  }

  public static final Capability getProjectedCapability(OperationalCapability operationalCapability_p) {
    List<AbstractCapabilityRealization> allocations = operationalCapability_p.getIncomingCapabilityAllocation();
    final int size = allocations.size();
    return (Capability) allocations.get(size - 1).getRealizingCapability();
  }

  public static final Mission getProjectedMission(OperationalCapability operationalCapability_p) {
    List<AbstractTrace> allocations = operationalCapability_p.getIncomingTraces();
    final int size = allocations.size();
    return (Mission) allocations.get(size - 1).getSourceElement();
  }

  /**
   * This happens when LC-PC transition occurs in Leaf Logical Components Propagation mode
   * 
   * @param logicalComponent_p
   * @return
   */
  public static final PhysicalComponentPkg getProjectedPCPkg(TraceableElement logicalComponent_p) {
    List<AbstractTrace> incoming = logicalComponent_p.getIncomingTraces();
    final int size = (incoming != null) ? incoming.size() : 0;
    return (PhysicalComponentPkg) ((size == 0) ? null : incoming.get(size - 1).getSourceElement());
  }

  public static final Entity getRealizedEntity(SystemComponent actor_p) {
    return (Entity) getAllocatedComponent(actor_p);
  }

  public static final TraceableElement getRealizedTargetElement(TraceableElement source_p) {
    List<AbstractTrace> outgoing = source_p.getOutgoingTraces();
    for (int i = outgoing.size() - 1; i >= 0; i--) {
      AbstractTrace trace = outgoing.get(i);
      if (!(trace instanceof ComponentFunctionalAllocation || trace instanceof ExchangeItemAllocation
          || trace instanceof PortAllocation)) {
        return trace.getTargetElement();
      }
    }
    return null;
  }

  public static final Class getRecentlyAddedClass(DataPkg classPkg_p) {
    List<Class> classes = classPkg_p.getOwnedClasses();
    final int size = classes.size();
    return classes.get(size - 1);
  }

  public static final ComponentPort getRecentlyAddedComponentPort(PhysicalComponent parentElt_p) {
    List<ComponentPort> partitions = parentElt_p.getContainedComponentPorts();
    ComponentPort latest = partitions.get(partitions.size() - 1);
    return (ComponentPort) ((latest instanceof ComponentPort) ? latest : null);
  }

  public static final ComponentPort getRecentlyAddedComponentPortInFeatures(PhysicalComponent parentElt_p) {
    List<Feature> partitions = parentElt_p.getOwnedFeatures();
    Feature latest = partitions.get(partitions.size() - 1);
    return (ComponentPort) ((latest instanceof ComponentPort) ? latest : null);
  }

  public static final AbstractFunction getRecentlyAddedFunction(AbstractFunction function_p) {
    List<AbstractFunction> functions = function_p.getOwnedFunctions();
    return functions.get(functions.size() - 1);
  }

  public static final AbstractTrace getRecentlyAddedOutgoingTransfoLink(TraceableElement sourceElement_p) {
    List<AbstractTrace> outgoing = sourceElement_p.getOutgoingTraces();
    if (outgoing == null) {
      return null;
    }
    final int size = outgoing.size();
    return (size == 0) ? null : outgoing.get(size - 1);
  }

  public static final StateMachine getRecentlyAddedStateMachine(Component component_p) {
    List<StateMachine> machines = component_p.getOwnedStateMachines();
    final int size = machines.size();
    return machines.get(size - 1);
  }

  public static final SystemComponent getRecentlyCreatedActor(SystemComponentPkg parentPkg_p) {
    List<SystemComponent> actors = parentPkg_p.getOwnedSystemComponents();
    final int size = actors.size();
    return actors.get(size - 1);
  }

  public static final LogicalComponent getRecentlyCreatedLogicalActor(LogicalComponentPkg parentPkg_p) {
    List<LogicalComponent> actors = parentPkg_p.getOwnedLogicalComponents();
    final int size = actors.size();
    return actors.get(size - 1);
  }

  public static final PhysicalComponent getRecentlyCreatedPhysicalActor(PhysicalComponentPkg parentPkg_p) {
    List<PhysicalComponent> actors = parentPkg_p.getOwnedPhysicalComponents();
    final int size = actors.size();
    return actors.get(size - 1);
  }

  /**
   * @param e3t_p
   * @param oa3_p
   * @return
   */
  public static boolean isAllocatingFunction(Component e3t_p, AbstractFunction oa3_p) {
    for (ComponentFunctionalAllocation alloc : e3t_p.getOwnedFunctionalAllocation()) {
      if (oa3_p.equals(alloc.getTargetElement())) {
        return true;
      }
    }
    return false;
  }
}
