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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocator;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 */
public class AbstractFunctionExt {

  /**
   * @param component_p
   * @param allocatedFunctions_p (first call : all leaves functions allocated to component_p)
   * @param functionsToCheck_p (first call : all leaves functions allocated to component_p
   * @return recursively all functions (and function containers) allocated to component_p
   */
  public static Set<AbstractFunction> getRecursiveAllocatedFunctions(Set<AbstractFunction> allocatedFunctions_p, final Set<AbstractFunction> functionsToCheck_p) {
    Set<AbstractFunction> newfunctionsToCheck = new HashSet<AbstractFunction>();
    Set<AbstractFunction> returnedAllocatedFunctions = new HashSet<AbstractFunction>(allocatedFunctions_p);
    for (AbstractFunction aFunction : functionsToCheck_p) {
      EObject container = aFunction.eContainer();
      if (container instanceof AbstractFunction) {
        boolean toAdd = true;
        for (AbstractFunction aSubFunction : ((AbstractFunction) container).getSubFunctions()) {
          if (!allocatedFunctions_p.contains(aSubFunction)) {
            toAdd = false;
            newfunctionsToCheck.add(aFunction);
            break;
          }
        }
        if (toAdd) {
          returnedAllocatedFunctions.add((AbstractFunction) container);
          newfunctionsToCheck.add((AbstractFunction) container);
        }
      }
    }
    boolean checkParents = false;

    for (AbstractFunction aFunction : returnedAllocatedFunctions) {
      if (!allocatedFunctions_p.contains(aFunction)) {
        checkParents = true;
        break;
      }
    }
    // if the returned list is the same as the list given in parameter => stop
    if (checkParents) {
      return getRecursiveAllocatedFunctions(returnedAllocatedFunctions, newfunctionsToCheck);
    }
    return returnedAllocatedFunctions;
  }

  /**
   * @param component_p
   * @return all functions (leaves and parents) allocated to component_p
   */
  public static List<AbstractFunction> getAllocatedFunctions(Component component_p) {
    List<AbstractFunction> returnedList = new ArrayList<AbstractFunction>();
    Set<AbstractFunction> allocatedFunctions = new HashSet<AbstractFunction>();
    allocatedFunctions.addAll(component_p.getAllocatedFunctions());
    returnedList.addAll(getRecursiveAllocatedFunctions(allocatedFunctions, allocatedFunctions));
    return returnedList;
  }

  /**
   * @param role_p
   * @return all operational activities (leaves and parents) allocated to role_p
   */
  public static List<AbstractFunction> getAllocatedOperationalActivities(Role role_p) {
    List<AbstractFunction> returnedList = new ArrayList<AbstractFunction>();
    Set<AbstractFunction> allocatedFunctions = new HashSet<AbstractFunction>();
    for (ActivityAllocation anAllocation : role_p.getOwnedActivityAllocations()) {
      if ((anAllocation.getTargetElement() != null) && (anAllocation.getTargetElement() instanceof OperationalActivity)) {
        allocatedFunctions.add((OperationalActivity) anAllocation.getTargetElement());
      }
    }
    returnedList.addAll(getRecursiveAllocatedFunctions(allocatedFunctions, allocatedFunctions));
    return returnedList;
  }

  /**
   * Gets the allocated functional exchange filtered for current and all its sub partitions and deployed elements recursively
   * @param sourceBlock the given abstractFunctionalBlock
   * @param targetBlock the given abstractFunctionalBlock
   * @return the allocated functional exchange filtered
   */
  public static List<CapellaElement> getAllAllocatedFunctionalExchangeFiltered(AbstractFunctionalBlock sourceBlock, AbstractFunctionalBlock targetBlock) {
    List<CapellaElement> list = new ArrayList<CapellaElement>(1);
    List<AbstractFunction> srcAllocatedFuns = new ArrayList<AbstractFunction>(1);
    List<AbstractFunction> tarAllocatedFuns = new ArrayList<AbstractFunction>(1);

    if ((sourceBlock != null) && (targetBlock != null)) {
      if ((sourceBlock instanceof PartitionableElement) && (targetBlock instanceof PartitionableElement)) {
        // get allocated function for current source and target
        srcAllocatedFuns.addAll(sourceBlock.getAllocatedFunctions());
        tarAllocatedFuns.addAll(targetBlock.getAllocatedFunctions());

        // get all recursive sub Components
        // in this process the sourceBlock and targetBlock are also added to the lists
        Collection<Component> allSourceRelatedEles = ComponentExt.getAllSubUsedComponents((Component) sourceBlock);
        Collection<Component> allTargetRelatedEles = ComponentExt.getAllSubUsedComponents((Component) targetBlock);

        // get All Super GeneralizableElement of both sourceBlock and targetBlock
        if (sourceBlock instanceof GeneralizableElement) {
          getSuperGElesFilterAsComponent(sourceBlock, allSourceRelatedEles);
          getSuperGElesFilterAsComponent(targetBlock, allTargetRelatedEles);
        }

        // get all deployedElement
        allSourceRelatedEles.addAll(PartExt.getAllDeployableComponents((Component) sourceBlock));
        allTargetRelatedEles.addAll(PartExt.getAllDeployableComponents((Component) targetBlock));

        // get allocated function of recursive sub sourcePartition
        for (Component partitionableElement : allSourceRelatedEles) {
          srcAllocatedFuns.addAll(partitionableElement.getAllocatedFunctions());
        }
        // get allocated function of recursive sub targetPartition
        for (Component partitionableElement : allTargetRelatedEles) {
          tarAllocatedFuns.addAll(partitionableElement.getAllocatedFunctions());
        }

        // now find the exchanges and add to result list [making sure that the target is contained in 'tarAllocatedFuns']
        for (AbstractFunction abstractFunction : srcAllocatedFuns) {
          List<FunctionalExchange> outGoingExchange = FunctionExt.getOutGoingExchange(abstractFunction);
          for (FunctionalExchange functionalExchange : outGoingExchange) {
            AbstractFunction outGoingAbstractFunction = FunctionExt.getOutGoingAbstractFunction(functionalExchange);
            if (null != outGoingAbstractFunction) {
              if (tarAllocatedFuns.contains(outGoingAbstractFunction)) {
                list.add(functionalExchange);
              }
            }
          }
        }

      }

    }
    return list;
  }

  private static void getSuperGElesFilterAsComponent(AbstractFunctionalBlock sourceBlock, Collection<Component> allSourceRelatedEles) {
    List<GeneralizableElement> allSuperGEs = GeneralizableElementExt.getAllSuperGeneralizableElements((GeneralizableElement) sourceBlock);
    for (GeneralizableElement generalizableElement : allSuperGEs) {
      if (generalizableElement instanceof Component) {
        allSourceRelatedEles.add((Component) generalizableElement);
      }
    }
  }

  /**
   * Return all the allocation blocks of the current functions. Also considering the roles allocated in operational activities
   * @param object_p
   * @return
   */
  public static List<Object> getAllocationBlocks(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (null == object_p) {
      return result;
    }

    if (object_p instanceof AbstractFunction) {
      AbstractFunction sf = (AbstractFunction) object_p;
      result.addAll(sf.getAllocationBlocks());
    }
    // On top of what is done before, you have to check the entities where roles are allocated for
    // operational activities:
    if (object_p instanceof OperationalActivity) {
      OperationalActivity oa = (OperationalActivity) object_p;
      for (ActivityAllocation allocation : oa.getActivityAllocations()) {
        Role role = allocation.getRole();
        if (role != null) {
          result.add(role);
        }
      }
    }
    return result;
  }

  /**
   * Checks if is leaf.
   * @param function_p the given abstract function
   * @return true, if is leaf
   */
  public static final boolean isLeaf(AbstractFunction function_p) {
    if (function_p == null) {
      return false;
    }
    return FunctionExt.isLeaf(function_p);
  }

  /**
   * check if available for allocation
   * @param element_p a model element
   * @return true, if available for allocation
   */
  public static boolean isAbstractFunctionAvailableForAllocation(EObject element_p) {
    boolean isAllocated = false;
    if ((element_p == null) || !(element_p instanceof AbstractFunction)) {
      return false;
    }

    AbstractFunction fun = (AbstractFunction) element_p;
    EList<AbstractTrace> incomingTraces = fun.getIncomingTraces();
    for (AbstractTrace abstractTrace : incomingTraces) {
      if ((abstractTrace instanceof ComponentFunctionalAllocation) || (abstractTrace instanceof ActivityAllocation)) {
        isAllocated = true;
        break;
      }
    }

    if (!isAllocated && isLeaf(fun)) {
      return true;
    }

    return false;
  }

  /**
   * check if available for allocation
   * @param element_p a model element
   * @return true, if available for allocation
   */
  public static boolean isFunctionExchangeAvailableForAllocation(EObject element_p) {
    if ((element_p == null) || !(element_p instanceof FunctionalExchange)) {
      return false;
    }

    FunctionalExchange funExc = (FunctionalExchange) element_p;
    if (funExc.getAllocatingComponentExchanges().size() == 0) {
      return true;
    }

    return false;
  }

  /**
   * check if available for allocation
   * @param element_p a model element
   * @return true, if available for allocation
   */
  public static boolean isComponentExchangeAvailableForAllocation(EObject element_p) {
    if ((element_p == null) || !(element_p instanceof ComponentExchange)) {
      return false;
    }

    ComponentExchange compExc = (ComponentExchange) element_p;
    EList<AbstractTrace> incomingTraces = compExc.getIncomingTraces();

    if (incomingTraces.isEmpty()) {
      return true;
    }

    for (AbstractTrace abstractTrace : incomingTraces) {
      if (abstractTrace instanceof ComponentExchangeAllocation) {
        ComponentExchangeAllocation allocation = (ComponentExchangeAllocation) abstractTrace;
        ComponentExchangeAllocator allocator = allocation.getComponentExchangeAllocator();
        if ((null != allocator) && (allocator instanceof PhysicalLink)) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * @param object_p
   * @return
   */
  public static boolean isPCPartAvailableForDeployment(EObject object_p) {
    if ((null != object_p) && (object_p instanceof Part)) {
      Part part = (Part) object_p;
      if (part.getDeployingLinks().size() == 0) {
        return true;
      }
    }
    return false;
  }

  public static List<CapellaElement> getExchangeSourceAndTargetPorts(FunctionalExchange exchange_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(0);
    if (exchange_p != null) {
      ActivityNode source = exchange_p.getSource();
      if ((source != null) && (source instanceof FunctionPort)) {
        result.add((CapellaElement) source);
      }
      ActivityNode target = exchange_p.getTarget();
      if ((target != null) && (target instanceof FunctionPort)) {
        result.add((CapellaElement) target);
      }
    }
    return result;
  }
  
  /**
   * @param abstractFunction a Capella Element
   * @return all the exchange items contained in the the given Function
   */
  public static Collection<AbstractExchangeItem> getAllExchangeItems(final AbstractFunction abstractFunction) {
	  Set<AbstractExchangeItem> returnedItems = new HashSet<AbstractExchangeItem>();
	  EList<InputPin> inputs = abstractFunction.getInputs();
	  EList<OutputPin> outputs = abstractFunction.getOutputs();
	  
	  for (Iterator<InputPin> iterator = inputs.iterator(); iterator.hasNext();) {
		  FunctionInputPort inputPin = (FunctionInputPort) iterator.next();
		  EList<ExchangeItem> exchangeItems = inputPin.getIncomingExchangeItems();
		  returnedItems.addAll(exchangeItems);
	  }
	  
	  for (Iterator<OutputPin> iterator = outputs.iterator(); iterator.hasNext();) {
		  FunctionOutputPort outputPin = (FunctionOutputPort) iterator.next();
		  EList<ExchangeItem> exchangeItems = outputPin.getOutgoingExchangeItems();
		  returnedItems.addAll(exchangeItems);
	  }
	  
    
    return returnedItems;
  }
  
}
