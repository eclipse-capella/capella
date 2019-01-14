/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   which accompanies this distribution, and is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.core.data.fa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__allocatingComponentExchangesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__categoriesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__incomingFunctionalExchangeRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__involvingFunctionalChainsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__outgoingFunctionalExchangeRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__realizedFunctionalExchangesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__realizingFunctionalExchangesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__sourceFunctionOutputPortMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__targetFunctionInputPortMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalExchange__allocatingComponentExchangesQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalExchange__categoriesQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalExchange__incomingFunctionalExchangeRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalExchange__involvingFunctionalChainsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalExchange__outgoingFunctionalExchangeRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalExchange__realizedFunctionalExchangesQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalExchange__realizingFunctionalExchangesQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalExchange__sourceFunctionOutputPortQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalExchange__targetFunctionInputPortQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in FunctionalExchange.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionalExchange.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionalExchange__involvingFunctionalChains</li>
 * <li>FunctionalExchange__allocatingComponentExchanges</li>
 * <li>FunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizations</li>
 * <li>FunctionalExchange__incomingFunctionalExchangeRealizations</li>
 * <li>FunctionalExchange__outgoingFunctionalExchangeRealizations</li>
 * <li>FunctionalExchange__categories</li>
 * <li>FunctionalExchange__sourceFunctionOutputPort</li>
 * <li>FunctionalExchange__targetFunctionInputPort</li>
 * <li>FunctionalExchange__realizedFunctionalExchanges</li>
 * <li>FunctionalExchange__realizingFunctionalExchanges</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionalExchange extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionalExchange instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new FunctionalExchange();
    }
    return INSTANCE;
  }
  
  private static FunctionalExchange INSTANCE;
  
  private FunctionalExchange() throws ViatraQueryException {
    querySpecifications.add(FunctionalExchange__involvingFunctionalChainsQuerySpecification.instance());
    querySpecifications.add(FunctionalExchange__allocatingComponentExchangesQuerySpecification.instance());
    querySpecifications.add(FunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizationsQuerySpecification.instance());
    querySpecifications.add(FunctionalExchange__incomingFunctionalExchangeRealizationsQuerySpecification.instance());
    querySpecifications.add(FunctionalExchange__outgoingFunctionalExchangeRealizationsQuerySpecification.instance());
    querySpecifications.add(FunctionalExchange__categoriesQuerySpecification.instance());
    querySpecifications.add(FunctionalExchange__sourceFunctionOutputPortQuerySpecification.instance());
    querySpecifications.add(FunctionalExchange__targetFunctionInputPortQuerySpecification.instance());
    querySpecifications.add(FunctionalExchange__realizedFunctionalExchangesQuerySpecification.instance());
    querySpecifications.add(FunctionalExchange__realizingFunctionalExchangesQuerySpecification.instance());
  }
  
  public FunctionalExchange__involvingFunctionalChainsQuerySpecification getFunctionalExchange__involvingFunctionalChains() throws ViatraQueryException {
    return FunctionalExchange__involvingFunctionalChainsQuerySpecification.instance();
  }
  
  public FunctionalExchange__involvingFunctionalChainsMatcher getFunctionalExchange__involvingFunctionalChains(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalExchange__involvingFunctionalChainsMatcher.on(engine);
  }
  
  public FunctionalExchange__allocatingComponentExchangesQuerySpecification getFunctionalExchange__allocatingComponentExchanges() throws ViatraQueryException {
    return FunctionalExchange__allocatingComponentExchangesQuerySpecification.instance();
  }
  
  public FunctionalExchange__allocatingComponentExchangesMatcher getFunctionalExchange__allocatingComponentExchanges(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalExchange__allocatingComponentExchangesMatcher.on(engine);
  }
  
  public FunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizationsQuerySpecification getFunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizations() throws ViatraQueryException {
    return FunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizationsQuerySpecification.instance();
  }
  
  public FunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizationsMatcher getFunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizationsMatcher.on(engine);
  }
  
  public FunctionalExchange__incomingFunctionalExchangeRealizationsQuerySpecification getFunctionalExchange__incomingFunctionalExchangeRealizations() throws ViatraQueryException {
    return FunctionalExchange__incomingFunctionalExchangeRealizationsQuerySpecification.instance();
  }
  
  public FunctionalExchange__incomingFunctionalExchangeRealizationsMatcher getFunctionalExchange__incomingFunctionalExchangeRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalExchange__incomingFunctionalExchangeRealizationsMatcher.on(engine);
  }
  
  public FunctionalExchange__outgoingFunctionalExchangeRealizationsQuerySpecification getFunctionalExchange__outgoingFunctionalExchangeRealizations() throws ViatraQueryException {
    return FunctionalExchange__outgoingFunctionalExchangeRealizationsQuerySpecification.instance();
  }
  
  public FunctionalExchange__outgoingFunctionalExchangeRealizationsMatcher getFunctionalExchange__outgoingFunctionalExchangeRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalExchange__outgoingFunctionalExchangeRealizationsMatcher.on(engine);
  }
  
  public FunctionalExchange__categoriesQuerySpecification getFunctionalExchange__categories() throws ViatraQueryException {
    return FunctionalExchange__categoriesQuerySpecification.instance();
  }
  
  public FunctionalExchange__categoriesMatcher getFunctionalExchange__categories(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalExchange__categoriesMatcher.on(engine);
  }
  
  public FunctionalExchange__sourceFunctionOutputPortQuerySpecification getFunctionalExchange__sourceFunctionOutputPort() throws ViatraQueryException {
    return FunctionalExchange__sourceFunctionOutputPortQuerySpecification.instance();
  }
  
  public FunctionalExchange__sourceFunctionOutputPortMatcher getFunctionalExchange__sourceFunctionOutputPort(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalExchange__sourceFunctionOutputPortMatcher.on(engine);
  }
  
  public FunctionalExchange__targetFunctionInputPortQuerySpecification getFunctionalExchange__targetFunctionInputPort() throws ViatraQueryException {
    return FunctionalExchange__targetFunctionInputPortQuerySpecification.instance();
  }
  
  public FunctionalExchange__targetFunctionInputPortMatcher getFunctionalExchange__targetFunctionInputPort(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalExchange__targetFunctionInputPortMatcher.on(engine);
  }
  
  public FunctionalExchange__realizedFunctionalExchangesQuerySpecification getFunctionalExchange__realizedFunctionalExchanges() throws ViatraQueryException {
    return FunctionalExchange__realizedFunctionalExchangesQuerySpecification.instance();
  }
  
  public FunctionalExchange__realizedFunctionalExchangesMatcher getFunctionalExchange__realizedFunctionalExchanges(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalExchange__realizedFunctionalExchangesMatcher.on(engine);
  }
  
  public FunctionalExchange__realizingFunctionalExchangesQuerySpecification getFunctionalExchange__realizingFunctionalExchanges() throws ViatraQueryException {
    return FunctionalExchange__realizingFunctionalExchangesQuerySpecification.instance();
  }
  
  public FunctionalExchange__realizingFunctionalExchangesMatcher getFunctionalExchange__realizingFunctionalExchanges(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalExchange__realizingFunctionalExchangesMatcher.on(engine);
  }
}
