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
package org.polarsys.capella.core.model.helpers;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
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
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;

/**
 */
public class AbstractFunctionExt {

  /**
   * @param component
   * @param allocatedFunctions
   *          (first call : all leaves functions allocated to component)
   * @param functionsToCheck
   *          (first call : all leaves functions allocated to component
   * @return recursively all functions (and function containers) allocated to component
   */
  public static Set<AbstractFunction> getRecursiveAllocatedFunctions(Set<AbstractFunction> allocatedFunctions,
      final Set<AbstractFunction> functionsToCheck) {
    Set<AbstractFunction> newfunctionsToCheck = new HashSet<AbstractFunction>();
    Set<AbstractFunction> returnedAllocatedFunctions = new HashSet<AbstractFunction>(allocatedFunctions);
    for (AbstractFunction aFunction : functionsToCheck) {
      EObject container = aFunction.eContainer();
      if (container instanceof AbstractFunction) {
        boolean toAdd = true;
        for (AbstractFunction aSubFunction : ((AbstractFunction) container).getSubFunctions()) {
          if (!allocatedFunctions.contains(aSubFunction)) {
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
      if (!allocatedFunctions.contains(aFunction)) {
        checkParents = true;
        break;
      }
    }
    // if the returned list is the same as the list given in parameter =>
    // stop
    if (checkParents) {
      return getRecursiveAllocatedFunctions(returnedAllocatedFunctions, newfunctionsToCheck);
    }
    return returnedAllocatedFunctions;
  }

  /**
   * @param component
   * @return all functions (leaves and parents) allocated to component
   */
  public static List<AbstractFunction> getAllocatedFunctions(Component component) {
    List<AbstractFunction> returnedList = new ArrayList<AbstractFunction>();
    Set<AbstractFunction> allocatedFunctions = new HashSet<AbstractFunction>();
    allocatedFunctions.addAll(component.getAllocatedFunctions());
    returnedList.addAll(getRecursiveAllocatedFunctions(allocatedFunctions, allocatedFunctions));
    return returnedList;
  }

  /**
   * @param role
   * @return all operational activities (leaves and parents) allocated to role
   */
  public static List<AbstractFunction> getAllocatedOperationalActivities(Role role) {
    List<AbstractFunction> returnedList = new ArrayList<AbstractFunction>();
    Set<AbstractFunction> allocatedFunctions = new HashSet<AbstractFunction>();
    for (ActivityAllocation anAllocation : role.getOwnedActivityAllocations()) {
      if ((anAllocation.getTargetElement() != null)
          && (anAllocation.getTargetElement() instanceof OperationalActivity)) {
        allocatedFunctions.add((OperationalActivity) anAllocation.getTargetElement());
      }
    }
    returnedList.addAll(getRecursiveAllocatedFunctions(allocatedFunctions, allocatedFunctions));
    return returnedList;
  }

  /**
   * Gets the allocated functional exchange filtered for current and all its sub partitions and deployed elements
   * recursively
   * 
   * @param sourceBlock
   *          the given abstractFunctionalBlock
   * @param targetBlock
   *          the given abstractFunctionalBlock
   * @return the allocated functional exchange filtered
   */
  public static List<CapellaElement> getAllAllocatedFunctionalExchangeFiltered(AbstractFunctionalBlock sourceBlock,
      AbstractFunctionalBlock targetBlock) {
    List<CapellaElement> list = new ArrayList<CapellaElement>(1);
    List<AbstractFunction> srcAllocatedFuns = new ArrayList<AbstractFunction>(1);
    List<AbstractFunction> tarAllocatedFuns = new ArrayList<AbstractFunction>(1);

    if ((sourceBlock instanceof Component) && (targetBlock instanceof Component)) {
      // get allocated function for current source and target
      srcAllocatedFuns.addAll(sourceBlock.getAllocatedFunctions());
      tarAllocatedFuns.addAll(targetBlock.getAllocatedFunctions());

      // get all recursive sub Components
      // in this process the sourceBlock and targetBlock are also
      // added to the lists
      Collection<Component> allSourceRelatedEles = ComponentExt.getAllSubUsedComponents((Component) sourceBlock);
      Collection<Component> allTargetRelatedEles = ComponentExt.getAllSubUsedComponents((Component) targetBlock);

      // get All Super GeneralizableElement of both sourceBlock and
      // targetBlock
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

      // now find the exchanges and add to result list [making sure
      // that the target is contained in 'tarAllocatedFuns']
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
    return list;
  }

  private static void getSuperGElesFilterAsComponent(AbstractFunctionalBlock sourceBlock,
      Collection<Component> allSourceRelatedEles) {
    List<GeneralizableElement> allSuperGEs = GeneralizableElementExt
        .getAllSuperGeneralizableElements((GeneralizableElement) sourceBlock);
    for (GeneralizableElement generalizableElement : allSuperGEs) {
      if (generalizableElement instanceof Component) {
        allSourceRelatedEles.add((Component) generalizableElement);
      }
    }
  }

  /**
   * Return all the allocation blocks of the current and roles of the current function.
   * 
   * @param object
   *          the target function
   * @return all the allocation blocks of the current and roles of the current function.
   * 
   *         TODO This function should return a Set and have a more meaningful name such as getAllocationBlocksAndRoles,
   *         this should be fixed in a non-patch version.
   */
  public static List<Object> getAllocationBlocks(Object object) {
    if (null == object) {
      return Collections.emptyList();
    }

    List<Object> result = new ArrayList<>();

    if (object instanceof AbstractFunction) {
      AbstractFunction abstractFunction = (AbstractFunction) object;
      EList<AbstractFunctionalBlock> allocationBlocks = abstractFunction.getAllocationBlocks();

      if (allocationBlocks != null) {
        result.addAll(allocationBlocks);
      }
    }
    // On top of what is done before, you have to check the entities where
    // roles are allocated for
    // operational activities:
    if (object instanceof OperationalActivity) {
      OperationalActivity operationActivity = (OperationalActivity) object;
      List<Role> allocatingRoles = operationActivity.getAllocatingRoles();

      if (allocatingRoles != null) {
        result.addAll(allocatingRoles);
      }
    }
    return result;
  }

  /**
   * Checks if is leaf.
   * 
   * @param function
   *          the given abstract function
   * @return true, if is leaf
   */
  public static final boolean isLeaf(AbstractFunction function) {
    if (function == null) {
      return false;
    }
    return FunctionExt.isLeaf(function);
  }

  /**
   * check if available for allocation
   * 
   * @param element
   *          a model element
   * @return true, if available for allocation
   */
  public static boolean isAbstractFunctionAvailableForAllocation(EObject element) {
    boolean isAllocated = false;
    if ((element == null) || !(element instanceof AbstractFunction)) {
      return false;
    }

    AbstractFunction fun = (AbstractFunction) element;
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
   * 
   * @param element
   *          a model element
   * @return true, if available for allocation
   */
  public static boolean isFunctionExchangeAvailableForAllocation(EObject element) {
    if ((element == null) || !(element instanceof FunctionalExchange)) {
      return false;
    }

    FunctionalExchange funExc = (FunctionalExchange) element;
    if (funExc.getAllocatingComponentExchanges().size() == 0) {
      return true;
    }

    return false;
  }

  /**
   * check if available for allocation
   * 
   * @param element
   *          a model element
   * @return true, if available for allocation
   */
  public static boolean isComponentExchangeAvailableForAllocation(EObject element) {
    if ((element == null) || !(element instanceof ComponentExchange)) {
      return false;
    }

    ComponentExchange compExc = (ComponentExchange) element;
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
   * @param object
   * @return
   */
  public static boolean isPCPartAvailableForDeployment(EObject object) {
    if ((null != object) && (object instanceof Part)) {
      Part part = (Part) object;
      if (part.getDeployingLinks().size() == 0) {
        return true;
      }
    }
    return false;
  }

  public static List<CapellaElement> getExchangeSourceAndTargetPorts(FunctionalExchange exchange) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(0);
    if (exchange != null) {
      ActivityNode source = exchange.getSource();
      if ((source != null) && (source instanceof FunctionPort)) {
        result.add((CapellaElement) source);
      }
      ActivityNode target = exchange.getTarget();
      if ((target != null) && (target instanceof FunctionPort)) {
        result.add((CapellaElement) target);
      }
    }
    return result;
  }

  /**
   * @param abstractFunction
   *          a Capella Element
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

  /**
   * Given a function, returns the components it is allocated to.
   * 
   * @param function
   *          the function considered
   * @return the components the function is allocated to
   */
  public static List<Component> getAllocatingComponents(AbstractFunction function) {
    List<Component> result = new ArrayList<>();
    if (null != function.getAllocationBlocks()) {
      for (AbstractFunctionalBlock block : function.getAllocationBlocks()) {
        if (block instanceof Component) {
          result.add((Component) block);
        }
      }
    }
    return result;
  }

  /**
   * Given an activity, returns the roles it is allocated to.
   * 
   * @param function
   *          the function considered
   * @return the components the function is allocated to
   */
  protected static Collection<Role> getAllocatingRoles(OperationalActivity activity) {
    Collection<Role> result = new ArrayList<Role>();
    EList<Role> roles = activity.getAllocatingRoles();
    if (null != roles && !roles.isEmpty()) {
      result.addAll(roles);
    }
    return result;
  }

  /**
   * Given a mother function/activity that has sub functions, this method <b>calculates</b> the allocation of the mother
   * function to a component/actor/entity.
   * 
   * The rule is that <b>a mother function/activity is allocated to a component/actor/entity if and only if all of its
   * leaf functions are allocated to this component/actor/entity or children of them</b>.
   * 
   * <b>Note</b> that in case the mother function/activity is already allocated to a component/actor/entity or in case
   * the mother function/activity is a leaf function, the method returns its allocation(s).
   * 
   * <b>Note</b> also that the helper works also in the case the sub functions are allocated to different children of
   * the same component/actor/entity.
   * 
   * @param motherFunction
   *          the mother function/activity to calculate component/actor/entity allocation on.
   * @return the <b>calculated</b> list of components/actors/entities the mother function is allocated to.
   */
  public static List<Component> getMotherFunctionAllocation(AbstractFunction motherFunction) {
    // If motherFunction is already allocated to a component/actor
    List<Component> motherFunctionAllocatingComponents = getAllocatingComponents(motherFunction);
    if (!motherFunctionAllocatingComponents.isEmpty()) {
      return motherFunctionAllocatingComponents;
    }
    // Get all leaves functions of the motherFunction
    List<AbstractFunction> leaves = getCache(FunctionExt::getAllLeafAbstractFunctions, motherFunction);
    if (null == leaves || leaves.isEmpty()) {
      return Collections.emptyList();
    }
    // Gather all allocating Components of all leaves
    List<Component> allAllocatingComponents = new ArrayList<>();
    for (AbstractFunction leaf : leaves) {
      Collection<Component> allocatingComponents = getAllocatingComponents(leaf);
      if (allocatingComponents.isEmpty()) {
        // A leaf is not allocated -> stop here
        return Collections.emptyList();
      }
      allAllocatingComponents.addAll(allocatingComponents);
    }
    // Get common ancestor of all Components allocation leaves.
    Component commonAncestor = ComponentExt.getFirstCommonComponentAncestor(allAllocatingComponents);
    if (commonAncestor == null) {
      return Collections.emptyList();
    }
    return Collections.singletonList(commonAncestor);
  }

  /**
   * Returns all components that have as allocated functions the given function or to its sub-functions
   * 
   * @param motherFunction
   *          the parent function
   */
  public static List<Component> getMotherAllFunctionAllocation(AbstractFunction motherFunction) {
    // If motherFunction is already allocated to a component/actor
    List<Component> motherFunctionAllocatingComponents = getAllocatingComponents(motherFunction);
    if (!motherFunctionAllocatingComponents.isEmpty()) {
      return motherFunctionAllocatingComponents;
    }
    // Get all owned functions of the motherFunction
    List<AbstractFunction> owned = getCache(FunctionExt::getAllAbstractFunctions, motherFunction);
    if (null == owned || owned.isEmpty()) {
      return Collections.emptyList();
    }
    // Gather all allocating Components of all owned functions
    List<Component> allAllocatingComponents = new ArrayList<>();
    for (AbstractFunction func : owned) {
      Collection<Component> allocatingComponents = getAllocatingComponents(func);
      if (!allocatingComponents.isEmpty()) {
        allAllocatingComponents.addAll(allocatingComponents);
      }
    }

    return allAllocatingComponents;
  }

  /**
   * Given a mother activity that has sub activities, this method <b>calculates</b> the allocation of the mother
   * activity to a role.
   * 
   * The rule is that <b>a mother activity is allocated to a role if and only if all of its sub activities are allocated
   * to this role</b>.
   * 
   * Note that in case the mother activity is already allocated to a role or in case the mother activity is a leaf
   * activity, the method returns its allocation(s).
   * 
   * @param motherFunction
   *          the mother activity to calculate role allocation on
   * @return the <b>calculated</b> list of roles the mother activity is allocated to
   */
  public static List<Role> getMotherActivityRoleAllocation(AbstractFunction motherFunction) {
    List<Role> result = new ArrayList<Role>();

    // Consider only Operational Activity
    if (motherFunction instanceof OperationalActivity) {
      OperationalActivity motherActivity = (OperationalActivity) motherFunction;

      // If motherActivity is already allocated to a role
      Collection<Role> roles = getAllocatingRoles(motherActivity);
      if (!roles.isEmpty()) {
        result.addAll(roles);
      } else {
        // Get all leaves activities of the motherActivity
        List<OperationalActivity> leaves = getAllLeafOperationalActivities(motherActivity);
        if (null != leaves && !leaves.isEmpty()) {
          Iterator<OperationalActivity> it = leaves.iterator();

          // Get first leaf role allocation to initialize the
          // result
          result.addAll(getAllocatingRoles(it.next()));

          // Iterate over leaves and do the intersection with result
          while (it.hasNext()) {
            result.retainAll(getAllocatingRoles(it.next()));
          }
        }
      }
    }

    return result;
  }

  /**
   * Given an Operational Activity, returns all leaves of type Operational Activity
   * 
   * @param activity
   *          The mother Operational Activity
   * @return the list of leaves of type Operational Activity
   */
  public static List<OperationalActivity> getAllLeafOperationalActivities(OperationalActivity activity) {
    List<OperationalActivity> result = new ArrayList<>();
    for (AbstractFunction abstractFunction : getCache(FunctionExt::getAllAbstractFunctions, activity)) {
      if (org.polarsys.capella.core.data.helpers.fa.services.FunctionExt.isLeaf(abstractFunction)
          && (abstractFunction instanceof OperationalActivity)) {
        result.add((OperationalActivity) abstractFunction);
      }
    }
    return result;
  }

}
