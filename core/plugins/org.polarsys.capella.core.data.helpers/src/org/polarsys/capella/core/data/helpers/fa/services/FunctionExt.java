/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.helpers.fa.services;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

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
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionPort;
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
	 * @param fct
	 * @return
	 */
	public static List<AbstractFunction> getRealizedFunctions(AbstractFunction fct) {
		List<AbstractFunction> result = new ArrayList<>();
		for (AbstractTrace trace : fct.getOutgoingTraces()) {
			if (trace instanceof FunctionRealization) {
				result.add(((FunctionRealization) trace).getAllocatedFunction());
			}
		}
		return result;
	}

	/**
	 * @param fct
	 * @return
	 */
	public static List<AbstractFunction> getRealizingFunctions(AbstractFunction fct) {
		List<AbstractFunction> result = new ArrayList<>();
		for (AbstractTrace trace : fct.getIncomingTraces()) {
			if (trace instanceof FunctionRealization) {
				result.add(((FunctionRealization) trace).getAllocatingFunction());
			}
		}
		return result;
	}

	/**
	 * Checks if is leaf.
	 * 
	 * @param function
	 *            the given function
	 * @return true, if is leaf
	 */
	public static final boolean isLeaf(AbstractFunction function) {
		if (function == null) {
			return false;
		}

		return function.getSubFunctions().isEmpty();
	}

	public static boolean isControlNodeOneOutput(AbstractFunction function) {
		return (function.getKind() == FunctionKind.GATHER) || (function.getKind() == FunctionKind.ROUTE);
	}

	public static boolean isControlNodeOneInput(AbstractFunction function) {
		return (function.getKind() == FunctionKind.DUPLICATE) || (function.getKind() == FunctionKind.SPLIT)
				|| (function.getKind() == FunctionKind.SELECT);
	}

	public static boolean isDuplicateFunction(AbstractFunction function) {
		return (function.getKind() == FunctionKind.DUPLICATE);
	}

	public static boolean isGatherFunction(AbstractFunction function) {
		return (function.getKind() == FunctionKind.GATHER);
	}

	public static boolean isRouteFunction(AbstractFunction function) {
		return (function.getKind() == FunctionKind.ROUTE);
	}

	public static boolean isSelectFunction(AbstractFunction function) {
		return (function.getKind() == FunctionKind.SELECT);
	}

	public static boolean isSplitFunction(AbstractFunction function) {
		return (function.getKind() == FunctionKind.SPLIT);
	}

	// used only for OperationalAnalysis Layer Elements
	/**
	 * Gets the allocated functional exchange filtered.
	 * 
	 * @param sourceEntity
	 *            the given abstractFunctionalBlock
	 * @param targetEntity
	 *            the given abstractFunctionalBlock
	 * @return the allocated functional exchange filtered
	 */
	public static List<CapellaElement> getAllocatedFunctionalExchangeFiltered(AbstractFunctionalBlock sourceEntity,
			AbstractFunctionalBlock targetEntity) {
		List<CapellaElement> list = new ArrayList<>(1);

		if ((sourceEntity != null) && (targetEntity != null)) {
			EList<AbstractFunction> srcAllocatedFuns = sourceEntity.getAllocatedFunctions();
			EList<AbstractFunction> tarAllocatedFuns = targetEntity.getAllocatedFunctions();

			for (AbstractFunction abstractFunction : srcAllocatedFuns) {
				EList<ActivityEdge> outgoing = abstractFunction.getOutgoing();
				for (ActivityEdge activityEdge : outgoing) {
					ActivityNode target = activityEdge.getTarget();
					if (null != target && tarAllocatedFuns.contains(target)) {
						list.add((CapellaElement) activityEdge);
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
	 *            the given abstractFunctionalBlock
	 * @param targetEntity
	 *            the given abstractFunctionalBlock
	 * @return the allocated functional exchange filtered with port
	 */
	public static List<CapellaElement> getAllocatedFunctionalExchangeFilteredWithPort(
			AbstractFunctionalBlock sourceEntity, AbstractFunctionalBlock targetEntity) {
		List<CapellaElement> list = new ArrayList<>(1);

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
	 * @param function
	 *            the function
	 * @return all outgoing exchanges
	 */
	public static List<FunctionalExchange> getOutGoingExchange(AbstractFunction function) {
		List<FunctionalExchange> result = new BasicEList<>();
		EList<ActivityEdge> outgoing = function.getOutgoing();
		for (ActivityEdge activityEdge : outgoing) {
			result.add((FunctionalExchange) activityEdge);
		}

		return result;
	}

	/**
	 * Returns all outgoing exchanges of the function including those of its
	 * sub-functions.
	 * 
	 * @param function
	 *            the function
	 * @return all outgoing exchanges
	 */
	public static List<FunctionalExchange> getAllOutgoingExchanges(AbstractFunction function) {
		if (function.getOwnedFunctions().isEmpty())
			return getOutGoingExchange(function);

		List<FunctionalExchange> result = getOutGoingExchange(function);

		for (AbstractFunction abstractFunction : function.getOwnedFunctions()) {
			List<FunctionalExchange> outgoings = getCache(FunctionExt::getAllOutgoingExchanges, abstractFunction);
			for (FunctionalExchange activityEdge : outgoings) {
				// If the functional exchange of the sub-function goes out of
				// the scope of the function
				if (!EcoreUtil2.isContainedBy(activityEdge.getTarget(), function))
					result.add(activityEdge);
			}
		}
		return result;
	}

	/**
	 * Returns all incoming exchanges of the function including those of its
	 * sub-functions.
	 * 
	 * @param function
	 *            the function
	 * @return all incoming exchanges
	 */
	public static List<FunctionalExchange> getAllIncomingExchanges(AbstractFunction function) {
		if (function.getOwnedFunctions().isEmpty())
			return getIncomingExchange(function);

		List<FunctionalExchange> result = getIncomingExchange(function);

		for (AbstractFunction abstractFunction : function.getOwnedFunctions()) {
			List<FunctionalExchange> incomings = getCache(FunctionExt::getAllIncomingExchanges, abstractFunction);
			for (FunctionalExchange activityEdge : incomings) {
				// If the functional exchange of the sub-function goes out of
				// the scope of the function
				if (!EcoreUtil2.isContainedBy(activityEdge.getSource(), function))
					result.add(activityEdge);
			}
		}
		return result;
	}

	/**
	 * Returns all incoming and outgoing exchanges of the function including
	 * those of its sub-functions.
	 * 
	 * @param function
	 *            the function
	 * @return all incoming and outgoing exchanges
	 */
	public static List<FunctionalExchange> getAllExchanges(AbstractFunction function) {
		List<FunctionalExchange> result = new BasicEList<>();
    result.addAll(getCache(FunctionExt::getAllIncomingExchanges, function));
    result.addAll(getCache(FunctionExt::getAllOutgoingExchanges, function));
		return result;
	}

	/**
	 * Returns all incoming exchanges of the function.
	 * 
	 * @param function
	 *            the function
	 * @return all incoming exchanges
	 */
	public static List<FunctionalExchange> getIncomingExchange(AbstractFunction function) {
		List<FunctionalExchange> result = new BasicEList<>();
		List<ActivityEdge> ingoing = function.getIncoming();
		for (ActivityEdge activityEdge : ingoing) {
			result.add((FunctionalExchange) activityEdge);
		}

		return result;
	}

	/**
	 * Returns all incoming and outgoing exchanges of the function.
	 * 
	 * @param function
	 *            the function
	 * @return all incoming and outgoing exchanges
	 */
	public static List<FunctionalExchange> getExchanges(AbstractFunction function) {
		List<FunctionalExchange> result = new BasicEList<>();
		List<ActivityEdge> ingoing = function.getIncoming();
		for (ActivityEdge activityEdge : ingoing) {
			result.add((FunctionalExchange) activityEdge);
		}
		EList<ActivityEdge> outgoing = function.getOutgoing();
		for (ActivityEdge activityEdge : outgoing) {
			result.add((FunctionalExchange) activityEdge);
		}
		return result;
	}

	/**
	 * Gets the owned function ports.
	 * 
	 * @param function
	 *            the given abstractFunction
	 * @return the owned function ports
	 */
	public static List<Port> getOwnedFunctionPorts(AbstractFunction function) {
		List<Port> ports = new ArrayList<>();
		for (InputPin inpin : function.getInputs()) {
			if (inpin instanceof Port) {
				ports.add((Port) inpin);
			}
		}
		for (OutputPin outpin : function.getOutputs()) {
			if (outpin instanceof Port) {
				ports.add((Port) outpin);
			}
		}
		return ports;
	}

	/**
	 * Returns the outgoing abstract function of the exchange.
	 * 
	 * @param exchange
	 *            the exchange
	 * @return function
	 */
	public static AbstractFunction getOutGoingAbstractFunction(FunctionalExchange exchange) {
		ActivityNode target = exchange.getTarget();
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
	 * @param exchange
	 *            the exchange
	 * @return function
	 */
	public static AbstractFunction getIncomingAbstractFunction(FunctionalExchange exchange) {
		ActivityNode source = exchange.getSource();
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
	 * @param function
	 * @return
	 */
	public static Collection<? extends FunctionPkg> getOwnedFunctionPkgs(AbstractFunction function) {
		// Add functions contained into owned function packages
		Collection<? extends FunctionPkg> pkgs = Collections.emptyList();
		if (function instanceof OperationalActivity) {
			pkgs = ((OperationalActivity) function).getOwnedOperationalActivityPkgs();
		} else if (function instanceof SystemFunction) {
			pkgs = ((SystemFunction) function).getOwnedSystemFunctionPkgs();
		} else if (function instanceof LogicalFunction) {
			pkgs = ((LogicalFunction) function).getOwnedLogicalFunctionPkgs();
		} else if (function instanceof PhysicalFunction) {
			pkgs = ((PhysicalFunction) function).getOwnedPhysicalFunctionPkgs();
		}
		return pkgs;
	}

	/**
	 * @param aFunction
	 * @return
	 */
	public static Collection<? extends FunctionPkg> getAllFunctionPkgs(AbstractFunction aFunction) {
		List<FunctionPkg> returnedList = new ArrayList<>();
		if (aFunction == null) {
			return returnedList;
		}

		for (AbstractFunction aSubFunction : aFunction.getOwnedFunctions()) {
			returnedList.addAll(getCache(FunctionExt::getAllFunctionPkgs, aSubFunction));
		}

		for (FunctionPkg aFunctionPkg : getOwnedFunctionPkgs(aFunction)) {
			returnedList.addAll(FunctionPkgExt.getAllFunctionPkgs(aFunctionPkg));
		}

		return returnedList;
	}

	/**
	 * Gets the all abstract functions.
	 * 
	 * @param function
	 *            the given abstractFunction
	 * @return function and all abstractFunctions contained recursively in
	 *         function
	 */
	public static List<AbstractFunction> getAllAbstractFunctions(AbstractFunction function) {
		List<AbstractFunction> returnedList = new ArrayList<>();

		if (function != null) {
			returnedList.add(function);

			for (AbstractFunction anAbstractFunction : function.getOwnedFunctions()) {
				returnedList.addAll(getAllAbstractFunctions(anAbstractFunction));
			}
			for (FunctionPkg obj : getOwnedFunctionPkgs(function)) {
				returnedList.addAll(FunctionPkgExt.getAllAbstractFunctions(obj));
			}
		}

		return returnedList;
	}

	/**
	 * Gets the all leaf abstract functions.
	 * 
	 * @param arch
	 *            the given blockArchitecture
	 * @return all function in arch that do not contain any subFunctions
	 */
	public static List<AbstractFunction> getAllLeafAbstractFunctions(AbstractFunction function) {
		List<AbstractFunction> returnedList = new ArrayList<>();
		for (AbstractFunction abstractFunction : getCache(FunctionExt::getAllAbstractFunctions, function)) {
			if (isLeaf(abstractFunction)) {
				returnedList.add(abstractFunction);
			}
		}
		return returnedList;
	}

	/**
	 * Gets the all leaf abstract functions.
	 * 
	 * @param arch
	 *            the given blockArchitecture
	 * @return all function in arch that do not contain any subFunctions
	 */
	public static List<AbstractFunction> getAllLeafAbstractFunctions(BlockArchitecture arch) {
		List<AbstractFunction> returnedList = new ArrayList<>();
		for (AbstractFunction function : getAllAbstractFunctions(arch)) {
			if (isLeaf(function)) {
				returnedList.add(function);
			}
		}
		return returnedList;
	}

	/**
	 * Gets the all abstract functions.
	 * 
	 * @param blockArchitecture
	 *            the given blockArchitecture
	 * @return all abstractFunctions in blockArchitecture
	 */
	public static List<AbstractFunction> getAllAbstractFunctions(BlockArchitecture blockArchitecture) {
		return FunctionPkgExt.getAllAbstractFunctions(blockArchitecture.getOwnedFunctionPkg());
	}

	/**
	 * return the root function of the breakdown where is located function
	 * 
	 * @param function
	 * @return
	 */
	public static AbstractFunction getRootFunction(AbstractFunction function) {
		EObject currentFunction = function;
		EObject currentContainer = function.eContainer();

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
	public static Collection<AbstractFunction> getFirstLevelAbstractFunctions(AbstractFunction function) {
		Collection<AbstractFunction> result = new ArrayList<>();

		result.addAll(function.getOwnedFunctions());

		for (FunctionPkg pkg : FunctionExt.getOwnedFunctionPkgs(function)) {
			result.addAll(FunctionPkgExt.getFirstLevelAbstractFunctions(pkg));
		}
		return result;
	}

	/**
	 * Returns the first parent function (a function can be contained into an
	 * internal package)
	 * 
	 * @param function
	 *            a function
	 * @return the list of parent functions
	 */
	public static AbstractFunction getParentFunction(AbstractFunction function) {
		EObject parent = EcoreUtil2.getFirstContainer(function, FaPackage.Literals.ABSTRACT_FUNCTION);
		if (parent != null) {
			return (AbstractFunction) parent;
		}
		return null;
	}

	/**
	 * Returns the first abstract function related to the given activityNode.
	 * FunctionalExchange is an ActivityEdge which can be connected to a Port or
	 * an OperationalActivity
	 * 
	 * @param node
	 * @return
	 */
	public static AbstractFunction getRelatedFunction(ActivityNode node) {
		if (node instanceof AbstractFunction) {
			return (AbstractFunction) node;
		}
		return (AbstractFunction) EcoreUtil2.getFirstContainer(node, FaPackage.Literals.ABSTRACT_FUNCTION);
	}

	/**
	 * @param function
	 *            a function
	 * @return the list of parent functions
	 */
	public static List<AbstractFunction> getParentFunctions(AbstractFunction function) {
		List<AbstractFunction> returnedList = new ArrayList<>();
		EObject parent = function.eContainer();
		while (parent instanceof AbstractFunction) {
			returnedList.add((AbstractFunction) parent);
			parent = parent.eContainer();
		}
		return returnedList;
	}

	/**
	 * Retrieve all functional exchanges owned by the given function
	 * 
	 * @param function
	 * @return
	 */
	public static Collection<FunctionalExchange> getAllOwnedFunctionalExchanges(AbstractFunction function) {
		EList<FunctionalExchange> functionExchanges = new BasicEList<>();

		List<AbstractFunction> subFunctions = getCache(FunctionExt::getAllAbstractFunctions, function);
		for (AbstractFunction abstractFunction : subFunctions) {
			functionExchanges.addAll(abstractFunction.getOwnedFunctionalExchanges());
		}
		return functionExchanges;
	}

	/**
	 * @param element
	 * @return
	 */
	public static boolean isControlNode(AbstractFunction element) {
		return !FunctionKind.FUNCTION.equals(element.getKind());
	}

	/**
	 * is [FUNCTIONKIND = FUNCTION] and not AcotorFunction or ControlNode
	 * 
	 * @param element
	 * @return
	 */
	public static boolean isFunction(AbstractFunction element) {
		if (null == element) {
			return false;
		}
    // check if not ActorFunction
    EList<AbstractFunctionalBlock> allocationBlocks = element.getAllocationBlocks();
    for (AbstractFunctionalBlock abstractFunctionalBlock : allocationBlocks) {
      if (abstractFunctionalBlock instanceof Component && ((Component) abstractFunctionalBlock).isActor()) {
        return false;
      }
    }
    return FunctionKind.FUNCTION.equals(element.getKind());
  }

	/**
	 * is [FUNCTIONKIND = FUNCTION] with few Actor allocation and not of kind
	 * ControlNode
	 * 
	 * @param element
	 * @return
	 */
	public static boolean isActorFunction(AbstractFunction element) {
		if (null == element) {
      return false;
    }
    // check if ActorFunction
    EList<AbstractFunctionalBlock> allocationBlocks = element.getAllocationBlocks();
    for (AbstractFunctionalBlock abstractFunctionalBlock : allocationBlocks) {
      if (abstractFunctionalBlock instanceof Component && ((Component) abstractFunctionalBlock).isActor()) {
        return true;
      }
    }
		return false;
	}

	/**
	 * Return true if it is root function
	 * 
	 * @param element
	 * @return boolean
	 */
	public static boolean isRootFunction(EObject element) {
		if (element instanceof AbstractFunction) {
			AbstractFunction currentFunction = (AbstractFunction) element;
			AbstractFunction rootFunction = FunctionExt.getRootFunction(currentFunction);
			if ((null != rootFunction) && rootFunction.equals(currentFunction)) {
				return true;
			}
		}

		return false;
	}

  /**
   * Return true if element is part of any Functional Exchanges defined on the targetFunction
   * 
   * @param element
   * @param targetFunction
   * @return boolean
   */
  public static boolean isFlowPortInAnyFunctionalExchange(FunctionPort element, AbstractFunction targetFunction) {
    // do not allow to move an output port to a target that has a FE defined with the same output port
    if (element instanceof FunctionOutputPort) {
      List<FunctionalExchange> exchanges = FunctionExt.getIncomingExchange(targetFunction);
      for (FunctionalExchange ex : exchanges) {
        // check for a matching FE where element is used as output port
        if (ex.getSourceFunctionOutputPort().equals(element)) {
          return true;
        }
      }
    }

    // do not allow to move an input port to a target that has a FE defined with the same input port
    if (element instanceof FunctionInputPort) {
      List<FunctionalExchange> exchanges = FunctionExt.getOutGoingExchange(targetFunction);
      for (FunctionalExchange ex : exchanges) {
        if (ex.getTargetFunctionInputPort().equals(element)) {
          return true;
        }
      }
    }
    return false;
  }
}
