/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.helpers.fa.services;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.cache.CacheResult;
import org.polarsys.capella.core.data.helpers.cache.CacheType;
import org.polarsys.capella.core.data.information.Port;

@CacheType
public interface IFunctionExt {

	@CacheResult
	List<AbstractFunction> getRealizedFunctions(AbstractFunction fct);

	@CacheResult
	List<AbstractFunction> getRealizingFunctions(AbstractFunction fct);

  @CacheResult
	boolean isLeaf(AbstractFunction function);

  @CacheResult
	boolean isControlNodeOneOutput(AbstractFunction function);

  @CacheResult
	boolean isControlNodeOneInput(AbstractFunction function);

  @CacheResult
	boolean isDuplicateFunction(AbstractFunction function);

  @CacheResult
	boolean isGatherFunction(AbstractFunction function);

  @CacheResult
	boolean isRouteFunction(AbstractFunction function);

  @CacheResult
	boolean isSelectFunction(AbstractFunction function);

  @CacheResult
	boolean isSplitFunction(AbstractFunction function);

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
  @CacheResult
	List<CapellaElement> getAllocatedFunctionalExchangeFiltered(AbstractFunctionalBlock sourceEntity,
			AbstractFunctionalBlock targetEntity);

	/**
	 * Gets the allocated functional exchange filtered with port.
	 * 
	 * @param sourceEntity
	 *            the given abstractFunctionalBlock
	 * @param targetEntity
	 *            the given abstractFunctionalBlock
	 * @return the allocated functional exchange filtered with port
	 */
  @CacheResult
	List<CapellaElement> getAllocatedFunctionalExchangeFilteredWithPort(AbstractFunctionalBlock sourceEntity,
			AbstractFunctionalBlock targetEntity);

	/**
	 * Returns all outgoing exchanges of the function.
	 * 
	 * @param function
	 *            the function
	 * @return all outgoing exchanges
	 */
  @CacheResult
	List<FunctionalExchange> getOutGoingExchange(AbstractFunction function);

	/**
	 * Returns all outgoing exchanges of the function including those of its
	 * sub-functions.
	 * 
	 * @param function
	 *            the function
	 * @return all outgoing exchanges
	 */
	@CacheResult
	List<FunctionalExchange> getAllOutgoingExchanges(AbstractFunction function);

	/**
	 * Returns all incoming exchanges of the function including those of its
	 * sub-functions.
	 * 
	 * @param function
	 *            the function
	 * @return all incoming exchanges
	 */
	@CacheResult
	List<FunctionalExchange> getAllIncomingExchanges(AbstractFunction function);

	/**
	 * Returns all incoming and outgoing exchanges of the function including
	 * those of its sub-functions.
	 * 
	 * @param function
	 *            the function
	 * @return all incoming and outgoing exchanges
	 */
	@CacheResult
	List<FunctionalExchange> getAllExchanges(AbstractFunction function);

	/**
	 * Returns all incoming exchanges of the function.
	 * 
	 * @param function
	 *            the function
	 * @return all incoming exchanges
	 */
	@CacheResult
	List<FunctionalExchange> getIncomingExchange(AbstractFunction function);

	/**
	 * Returns all incoming and outgoing exchanges of the function.
	 * 
	 * @param function
	 *            the function
	 * @return all incoming and outgoing exchanges
	 */
	@CacheResult
	List<FunctionalExchange> getExchanges(AbstractFunction function);

	/**
	 * Gets the owned function ports.
	 * 
	 * @param function
	 *            the given abstractFunction
	 * @return the owned function ports
	 */
	@CacheResult
	List<Port> getOwnedFunctionPorts(AbstractFunction function);

	/**
	 * Returns the outgoing abstract function of the exchange.
	 * 
	 * @param exchange
	 *            the exchange
	 * @return function
	 */
  @CacheResult
	AbstractFunction getOutGoingAbstractFunction(FunctionalExchange exchange);

	/**
	 * Returns the incoming abstract function of the exchange.
	 * 
	 * @param exchange
	 *            the exchange
	 * @return function
	 */
  @CacheResult
	AbstractFunction getIncomingAbstractFunction(FunctionalExchange exchange);

	/**
	 * Returns owned function pkgs
	 * 
	 * @param function
	 * @return
	 */
	@CacheResult
	Collection<? extends FunctionPkg> getOwnedFunctionPkgs(AbstractFunction function);

	/**
	 * @param aFunction
	 * @return
	 */
	@CacheResult
	Collection<? extends FunctionPkg> getAllFunctionPkgs(AbstractFunction aFunction);

	/**
	 * Gets the all abstract functions.
	 * 
	 * @param function
	 *            the given abstractFunction
	 * @return function and all abstractFunctions contained recursively in
	 *         function
	 */
	@CacheResult
	List<AbstractFunction> getAllAbstractFunctions(AbstractFunction function);

	/**
	 * Gets the all leaf abstract functions.
	 * 
	 * @param arch
	 *            the given blockArchitecture
	 * @return all function in arch that do not contain any subFunctions
	 */
	@CacheResult
	List<AbstractFunction> getAllLeafAbstractFunctions(AbstractFunction function);

	/**
	 * Gets the all leaf abstract functions.
	 * 
	 * @param arch
	 *            the given blockArchitecture
	 * @return all function in arch that do not contain any subFunctions
	 */
	@CacheResult
	List<AbstractFunction> getAllLeafAbstractFunctions(BlockArchitecture arch);

	/**
	 * Gets the all abstract functions.
	 * 
	 * @param blockArchitecture
	 *            the given blockArchitecture
	 * @return all abstractFunctions in blockArchitecture
	 */
	@CacheResult
	List<AbstractFunction> getAllAbstractFunctions(BlockArchitecture blockArchitecture);

	/**
	 * return the root function of the breakdown where is located function
	 * 
	 * @param function
	 * @return
	 */
	@CacheResult
	AbstractFunction getRootFunction(AbstractFunction function);

	/**
	 * Returns functions owned by the function or owned function pkg
	 * 
	 * @return
	 */
	@CacheResult
	Collection<AbstractFunction> getFirstLevelAbstractFunctions(AbstractFunction function);

	/**
	 * Returns the first parent function (a function can be contained into an
	 * internal package)
	 * 
	 * @param function
	 *            a function
	 * @return the list of parent functions
	 */
	@CacheResult
	AbstractFunction getParentFunction(AbstractFunction function);

	/**
	 * Returns the first abstract function related to the given activityNode.
	 * FunctionalExchange is an ActivityEdge which can be connected to a Port or
	 * an OperationalActivity
	 * 
	 * @param node
	 * @return
	 */
	@CacheResult
	AbstractFunction getRelatedFunction(ActivityNode node);

	/**
	 * @param function
	 *            a function
	 * @return the list of parent functions
	 */
	@CacheResult
	List<AbstractFunction> getParentFunctions(AbstractFunction function);

	/**
	 * Retrieve all functional exchanges owned by the given function
	 * 
	 * @param function
	 * @return
	 */
	@CacheResult
	Collection<FunctionalExchange> getAllOwnedFunctionalExchanges(AbstractFunction function);

	/**
	 * @param element
	 * @return
	 */
  @CacheResult
	boolean isControlNode(AbstractFunction element);

	/**
	 * is [FUNCTIONKIND = FUNCTION] and not AcotorFunction or ControlNode
	 * 
	 * @param element
	 * @return
	 */
  @CacheResult
	boolean isFunction(AbstractFunction element);

	/**
	 * is [FUNCTIONKIND = FUNCTION] with few Actor allocation and not of kind
	 * ControlNode
	 * 
	 * @param element
	 * @return
	 */
  @CacheResult
	boolean isActorFunction(AbstractFunction element);

	/**
	 * Return true if it is root function
	 * 
	 * @param element
	 * @return boolean
	 */
  @CacheResult
	boolean isRootFunction(EObject element);

}