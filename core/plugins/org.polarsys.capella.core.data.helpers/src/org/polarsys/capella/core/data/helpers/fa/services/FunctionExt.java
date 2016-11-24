/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.helpers.fa.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.pa.PhysicalFunction;

/**
 * Helper for functions.
 */
public class FunctionExt {

  /**
   * @param fct_p
   * @return
   */
  public static List<AbstractFunction> getRealizedFunctions(AbstractFunction fct_p) {
    List<AbstractFunction> result = new ArrayList<AbstractFunction>();
    for (AbstractTrace trace : fct_p.getOutgoingTraces()) {
      if (trace instanceof FunctionRealization) {
        result.add(((FunctionRealization) trace).getAllocatedFunction());
      }
    }
    return result;
  }

  /**
   * @param fct_p
   * @return
   */
  public static List<AbstractFunction> getRealizingFunctions(AbstractFunction fct_p) {
    List<AbstractFunction> result = new ArrayList<AbstractFunction>();
    for (AbstractTrace trace : fct_p.getIncomingTraces()) {
      if (trace instanceof FunctionRealization) {
        result.add(((FunctionRealization) trace).getAllocatingFunction());
      }
    }
    return result;
  }

  /**
   * Checks if is leaf.
   * 
   * @param function_p
   *          the given function
   * @return true, if is leaf
   */
  public static final boolean isLeaf(AbstractFunction function_p) {
    if (function_p == null) {
      return false;
    }

    return function_p.getSubFunctions().size() == 0;
  }

  public static boolean isControlNodeOneOutput(AbstractFunction function_p) {
    return (function_p.getKind() == FunctionKind.GATHER) || (function_p.getKind() == FunctionKind.ROUTE);
  }

  public static boolean isControlNodeOneInput(AbstractFunction function_p) {
    return (function_p.getKind() == FunctionKind.DUPLICATE) || (function_p.getKind() == FunctionKind.SPLIT) || (function_p.getKind() == FunctionKind.SELECT);
  }

  public static boolean isDuplicateFunction(AbstractFunction function_p) {
    return (function_p.getKind() == FunctionKind.DUPLICATE);
  }

  public static boolean isGatherFunction(AbstractFunction function_p) {
    return (function_p.getKind() == FunctionKind.GATHER);
  }

  public static boolean isRouteFunction(AbstractFunction function_p) {
    return (function_p.getKind() == FunctionKind.ROUTE);
  }

  public static boolean isSelectFunction(AbstractFunction function_p) {
    return (function_p.getKind() == FunctionKind.SELECT);
  }

  public static boolean isSplitFunction(AbstractFunction function_p) {
    return (function_p.getKind() == FunctionKind.SPLIT);
  }

  // used only for OperationalAnalysis Layer Elements
  /**
   * Gets the allocated functional exchange filtered.
   * 
   * @param sourceEntity
   *          the given abstractFunctionalBlock
   * @param targetEntity
   *          the given abstractFunctionalBlock
   * @return the allocated functional exchange filtered
   */
  public static List<CapellaElement> getAllocatedFunctionalExchangeFiltered(AbstractFunctionalBlock sourceEntity, AbstractFunctionalBlock targetEntity) {
    List<CapellaElement> list = new ArrayList<CapellaElement>(1);

    if ((sourceEntity != null) && (targetEntity != null)) {
      EList<AbstractFunction> srcAllocatedFuns = sourceEntity.getAllocatedFunctions();
      EList<AbstractFunction> tarAllocatedFuns = targetEntity.getAllocatedFunctions();

      for (AbstractFunction abstractFunction : srcAllocatedFuns) {
        EList<ActivityEdge> outgoing = abstractFunction.getOutgoing();
        for (ActivityEdge activityEdge : outgoing) {
          ActivityNode target = activityEdge.getTarget();
          if (null != target) {
            if (tarAllocatedFuns.contains(target)) {
              list.add((CapellaElement) activityEdge);
            }
          }
        }
      }
    }
    return list;
  }

  /**
   * Gets the allocated functional exchange filtered with port.
   * 
   * @param sourceEntity
   *          the given abstractFunctionalBlock
   * @param targetEntity
   *          the given abstractFunctionalBlock
   * @return the allocated functional exchange filtered with port
   */
  public static List<CapellaElement> getAllocatedFunctionalExchangeFilteredWithPort(AbstractFunctionalBlock sourceEntity, AbstractFunctionalBlock targetEntity) {
    List<CapellaElement> list = new ArrayList<CapellaElement>(1);

    if ((sourceEntity != null) && (targetEntity != null)) {
      EList<AbstractFunction> srcAllocatedFuns = sourceEntity.getAllocatedFunctions();
      EList<AbstractFunction> tarAllocatedFuns = targetEntity.getAllocatedFunctions();

      for (AbstractFunction abstractFunction : srcAllocatedFuns) {
        List<FunctionalExchange> outGoingExchange = getOutGoingExchange(abstractFunction);
        for (FunctionalExchange functionalExchange : outGoingExchange) {
          AbstractFunction outGoingAbstractFunction = getOutGoingAbstractFunction(functionalExchange);
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

  /**
   * Returns all outgoing exchanges of the function.
   * 
   * @param function_p
   *          the function
   * @return all outgoing exchanges
   */
  public static List<FunctionalExchange> getOutGoingExchange(AbstractFunction function_p) {
    List<FunctionalExchange> result = new BasicEList<FunctionalExchange>();
    EList<ActivityEdge> outgoing = function_p.getOutgoing();
    for (ActivityEdge activityEdge : outgoing) {
      result.add((FunctionalExchange) activityEdge);
    }

    return result;
  }

  /**
   * Returns all outgoing exchanges of the function including those of its sub-functions.
   * 
   * @param function_p
   *          the function
   * @return all outgoing exchanges
   */
  public static List<FunctionalExchange> getAllOutgoingExchanges(AbstractFunction function_p) {
    if (function_p.getOwnedFunctions().isEmpty())
      return getOutGoingExchange(function_p);

    List<FunctionalExchange> result = getOutGoingExchange(function_p);

    for (AbstractFunction abstractFunction : function_p.getOwnedFunctions()) {
      List<FunctionalExchange> outgoings = getAllOutgoingExchanges(abstractFunction);
      for (FunctionalExchange activityEdge : outgoings) {
        // If the functional exchange of the sub-function goes out of the scope of the function
        if (!EcoreUtil2.isContainedBy(activityEdge.getTarget(), function_p))
          result.add((FunctionalExchange) activityEdge);
      }
    }
    return result;
  }

  /**
   * Returns all incoming exchanges of the function including those of its sub-functions.
   * 
   * @param function_p
   *          the function
   * @return all incoming exchanges
   */
  public static List<FunctionalExchange> getAllIncomingExchanges(AbstractFunction function_p) {
    if (function_p.getOwnedFunctions().isEmpty())
      return getIncomingExchange(function_p);

    List<FunctionalExchange> result = getIncomingExchange(function_p);

    for (AbstractFunction abstractFunction : function_p.getOwnedFunctions()) {
      List<FunctionalExchange> incomings = getAllIncomingExchanges(abstractFunction);
      for (FunctionalExchange activityEdge : incomings) {
        // If the functional exchange of the sub-function goes out of the scope of the function
        if (!EcoreUtil2.isContainedBy(activityEdge.getSource(), function_p))
          result.add((FunctionalExchange) activityEdge);
      }
    }
    return result;
  }

  /**
   * Returns all incoming and outgoing exchanges of the function including those of its sub-functions.
   * 
   * @param function_p
   *          the function
   * @return all incoming and outgoing exchanges
   */
  public static List<FunctionalExchange> getAllExchanges(AbstractFunction function) {
    List<FunctionalExchange> result = new BasicEList<FunctionalExchange>();
    result.addAll(getAllIncomingExchanges(function));
    result.addAll(getAllOutgoingExchanges(function));
    return result;
  }

  /**
   * Returns all incoming exchanges of the function.
   * 
   * @param function_p
   *          the function
   * @return all incoming exchanges
   */
  public static List<FunctionalExchange> getIncomingExchange(AbstractFunction function_p) {
    List<FunctionalExchange> result = new BasicEList<FunctionalExchange>();
    List<ActivityEdge> ingoing = function_p.getIncoming();
    for (ActivityEdge activityEdge : ingoing) {
      result.add((FunctionalExchange) activityEdge);
    }

    return result;
  }

  /**
   * Returns all incoming and outgoing exchanges of the function.
   * 
   * @param function_p
   *          the function
   * @return all incoming and outgoing exchanges
   */
  public static List<FunctionalExchange> getExchanges(AbstractFunction function_p) {
    List<FunctionalExchange> result = new BasicEList<FunctionalExchange>();
    List<ActivityEdge> ingoing = function_p.getIncoming();
    for (ActivityEdge activityEdge : ingoing) {
      result.add((FunctionalExchange) activityEdge);
    }
    EList<ActivityEdge> outgoing = function_p.getOutgoing();
    for (ActivityEdge activityEdge : outgoing) {
      result.add((FunctionalExchange) activityEdge);
    }
    return result;
  }

  /**
   * Gets the owned function ports.
   * 
   * @param function_p
   *          the given abstractFunction
   * @return the owned function ports
   */
  public static List<Port> getOwnedFunctionPorts(AbstractFunction function_p) {
    List<Port> ports = new ArrayList<Port>();
    for (InputPin inpin : function_p.getInputs()) {
      if (inpin instanceof Port) {
        ports.add((Port) inpin);
      }
    }
    for (OutputPin outpin : function_p.getOutputs()) {
      if (outpin instanceof Port) {
        ports.add((Port) outpin);
      }
    }
    return ports;
  }

  /**
   * Returns the outgoing abstract function of the exchange.
   * 
   * @param exchange_p
   *          the exchange
   * @return function
   */
  public static AbstractFunction getOutGoingAbstractFunction(FunctionalExchange exchange_p) {
    ActivityNode target = exchange_p.getTarget();
    if (target instanceof FunctionInputPort) {
      EObject container = ((FunctionInputPort) target).eContainer();
      if (container != null) {
        return (AbstractFunction) container;
      }
    }
    if (target instanceof OperationalActivity) {
      return (AbstractFunction) target;
    }
    return null;
  }

  /**
   * Returns the incoming abstract function of the exchange.
   * 
   * @param exchange_p
   *          the exchange
   * @return function
   */
  public static AbstractFunction getIncomingAbstractFunction(FunctionalExchange exchange_p) {
    ActivityNode source = exchange_p.getSource();
    if (source instanceof FunctionOutputPort) {
      EObject container = ((FunctionOutputPort) source).eContainer();
      if (container != null) {
        return (AbstractFunction) container;
      }
    }
    if (source instanceof OperationalActivity) {
      return (AbstractFunction) source;
    }
    return null;
  }

  /**
   * Returns owned function pkgs
   * 
   * @param function_p
   * @return
   */
  public static Collection<? extends FunctionPkg> getOwnedFunctionPkgs(AbstractFunction function_p) {
    // Add functions contained into owned function packages
    Collection<? extends FunctionPkg> pkgs = Collections.emptyList();
    if (function_p instanceof OperationalActivity) {
      pkgs = ((OperationalActivity) function_p).getOwnedOperationalActivityPkgs();
    } else if (function_p instanceof SystemFunction) {
      pkgs = ((SystemFunction) function_p).getOwnedSystemFunctionPkgs();
    } else if (function_p instanceof LogicalFunction) {
      pkgs = ((LogicalFunction) function_p).getOwnedLogicalFunctionPkgs();
    } else if (function_p instanceof PhysicalFunction) {
      pkgs = ((PhysicalFunction) function_p).getOwnedPhysicalFunctionPkgs();
    }
    return pkgs;
  }

  /**
   * @param aFunction_p
   * @return
   */
  public static Collection<? extends FunctionPkg> getAllFunctionPkgs(AbstractFunction aFunction_p) {
    List<FunctionPkg> returnedList = new ArrayList<FunctionPkg>();
    if (aFunction_p == null) {
      return returnedList;
    }

    for (AbstractFunction aSubFunction : aFunction_p.getOwnedFunctions()) {
      returnedList.addAll(FunctionExt.getAllFunctionPkgs(aSubFunction));
    }

    for (FunctionPkg aFunctionPkg : getOwnedFunctionPkgs(aFunction_p)) {
      returnedList.addAll(FunctionPkgExt.getAllFunctionPkgs(aFunctionPkg));
    }

    return returnedList;
  }

  /**
   * Gets the all abstract functions.
   * 
   * @param function_p
   *          the given abstractFunction
   * @return function_p and all abstractFunctions contained recursively in function_p
   */
  public static List<AbstractFunction> getAllAbstractFunctions(AbstractFunction function_p) {
    List<AbstractFunction> returnedList = new ArrayList<AbstractFunction>();

    if (function_p != null) {
      returnedList.add(function_p);

      for (AbstractFunction anAbstractFunction : function_p.getOwnedFunctions()) {
        returnedList.addAll(getAllAbstractFunctions(anAbstractFunction));
      }
      for (FunctionPkg obj : getOwnedFunctionPkgs(function_p)) {
        returnedList.addAll(FunctionPkgExt.getAllAbstractFunctions(obj));
      }
    }

    return returnedList;
  }

  /**
   * Gets the all leaf abstract functions.
   * 
   * @param arch_p
   *          the given blockArchitecture
   * @return all function in arch_p that do not contain any subFunctions
   */
  public static List<AbstractFunction> getAllLeafAbstractFunctions(AbstractFunction function_p) {
    List<AbstractFunction> returnedList = new ArrayList<AbstractFunction>();
    for (AbstractFunction function : getAllAbstractFunctions(function_p)) {
      if (isLeaf(function)) {
        returnedList.add(function);
      }
    }
    return returnedList;
  }

  /**
   * Gets the all leaf abstract functions.
   * 
   * @param arch_p
   *          the given blockArchitecture
   * @return all function in arch_p that do not contain any subFunctions
   */
  public static List<AbstractFunction> getAllLeafAbstractFunctions(BlockArchitecture arch_p) {
    List<AbstractFunction> returnedList = new ArrayList<AbstractFunction>();
    for (AbstractFunction function : getAllAbstractFunctions(arch_p)) {
      if (isLeaf(function)) {
        returnedList.add(function);
      }
    }
    return returnedList;
  }

  /**
   * Gets the all abstract functions.
   * 
   * @param blockArchitecture_p
   *          the given blockArchitecture
   * @return all abstractFunctions in blockArchitecture_p
   */
  public static List<AbstractFunction> getAllAbstractFunctions(BlockArchitecture blockArchitecture_p) {
    return FunctionPkgExt.getAllAbstractFunctions(blockArchitecture_p.getOwnedFunctionPkg());
  }

  /**
   * return the root function of the breakdown where is located function_p
   * 
   * @param function_p
   * @return
   */
  public static AbstractFunction getRootFunction(AbstractFunction function_p) {
    EObject currentFunction = function_p;
    EObject currentContainer = function_p.eContainer();

    while ((currentContainer instanceof AbstractFunction)) {
      currentFunction = currentContainer;
      currentContainer = currentContainer.eContainer();
    }
    return (AbstractFunction) currentFunction;
  }

  /**
   * Returns functions owned by the function or owned function pkg
   * 
   * @return
   */
  public static Collection<AbstractFunction> getFirstLevelAbstractFunctions(AbstractFunction function_p) {
    Collection<AbstractFunction> result = new ArrayList<AbstractFunction>();

    result.addAll(function_p.getOwnedFunctions());

    for (FunctionPkg pkg : FunctionExt.getOwnedFunctionPkgs(function_p)) {
      result.addAll(FunctionPkgExt.getFirstLevelAbstractFunctions(pkg));
    }
    return result;
  }

  /**
   * Returns the first parent function (a function can be contained into an internal package)
   * 
   * @param function_p
   *          a function
   * @return the list of parent functions
   */
  public static AbstractFunction getParentFunction(AbstractFunction function_p) {
    EObject parent = EcoreUtil2.getFirstContainer(function_p, FaPackage.Literals.ABSTRACT_FUNCTION);
    if (parent != null) {
      return (AbstractFunction) parent;
    }
    return null;
  }

  /**
   * Returns the first abstract function related to the given activityNode. FunctionalExchange is an ActivityEdge which
   * can be connected to a Port or an OperationalActivity
   * 
   * @param node_p
   * @return
   */
  public static AbstractFunction getRelatedFunction(ActivityNode node_p) {
    if (node_p instanceof AbstractFunction) {
      return (AbstractFunction) node_p;
    }
    return (AbstractFunction) EcoreUtil2.getFirstContainer(node_p, FaPackage.Literals.ABSTRACT_FUNCTION);
  }

  /**
   * @param function_p
   *          a function
   * @return the list of parent functions
   */
  public static List<AbstractFunction> getParentFunctions(AbstractFunction function_p) {
    List<AbstractFunction> returnedList = new ArrayList<AbstractFunction>();
    EObject parent = function_p.eContainer();
    while ((parent != null) && (parent instanceof AbstractFunction)) {
      returnedList.add((AbstractFunction) parent);
      parent = parent.eContainer();
    }
    return returnedList;
  }

  /**
   * Retrieve all functional exchanges owned by the given function_p
   * 
   * @param function_p
   * @return
   */
  public static Collection<FunctionalExchange> getAllOwnedFunctionalExchanges(AbstractFunction function_p) {
    EList<FunctionalExchange> functionExchanges = new BasicEList<FunctionalExchange>();

    List<AbstractFunction> subFunctions = getAllAbstractFunctions(function_p);
    for (AbstractFunction abstractFunction : subFunctions) {
      functionExchanges.addAll(abstractFunction.getOwnedFunctionalExchanges());
    }
    return functionExchanges;
  }

  /**
   * @param element_p
   * @return
   */
  public static boolean isControlNode(AbstractFunction element_p) {
    return !FunctionKind.FUNCTION.equals(element_p.getKind());
  }

  /**
   * is [FUNCTIONKIND = FUNCTION] and not AcotorFunction or ControlNode
   * 
   * @param element_p
   * @return
   */
  public static boolean isFunction(AbstractFunction element_p) {
    if (null == element_p) {
      return false;
    }
    // check if not ActorFunction
    EList<AbstractFunctionalBlock> allocationBlocks = element_p.getAllocationBlocks();
    for (AbstractFunctionalBlock abstractFunctionalBlock : allocationBlocks) {
      if (abstractFunctionalBlock instanceof AbstractActor) {
        return false;
      }
    }
    return FunctionKind.FUNCTION.equals(element_p.getKind());
  }

  /**
   * is [FUNCTIONKIND = FUNCTION] with few Actor allocation and not of kind ControlNode
   * 
   * @param element_p
   * @return
   */
  public static boolean isActorFunction(AbstractFunction element_p) {
    if (null == element_p) {
      return false;
    }
    // check if ActorFunction
    EList<AbstractFunctionalBlock> allocationBlocks = element_p.getAllocationBlocks();
    for (AbstractFunctionalBlock abstractFunctionalBlock : allocationBlocks) {
      if (abstractFunctionalBlock instanceof AbstractActor) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return true if it is root function
   * 
   * @param element_p
   * @return boolean
   */
  public static boolean isRootFunction(EObject element_p) {
    if ((null != element_p) && (element_p instanceof AbstractFunction)) {
      AbstractFunction currentFunction = (AbstractFunction) element_p;
      AbstractFunction rootFunction = FunctionExt.getRootFunction(currentFunction);
      if ((null != rootFunction) && rootFunction.equals(currentFunction)) {
        return true;
      }
    }

    return false;
  }

}
