/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
 *   
 *   This program and the accompanying materials are made available under the
 *   terms of the Eclipse Public License 2.0 which is available at
 *   http://www.eclipse.org/legal/epl-2.0
 *   
 *   SPDX-License-Identifier: EPL-2.0
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.core.data.fa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__allocatingComponentExchanges;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__categories;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizations;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__incomingFunctionalExchangeRealizations;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__involvingFunctionalChains;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__outgoingFunctionalExchangeRealizations;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__realizedFunctionalExchanges;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__realizingFunctionalExchanges;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__sourceFunctionOutputPort;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchange__targetFunctionInputPort;

/**
 * A pattern group formed of all public patterns defined in FunctionalExchange.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionalExchange extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionalExchange instance() {
    if (INSTANCE == null) {
        INSTANCE = new FunctionalExchange();
    }
    return INSTANCE;
  }
  
  private static FunctionalExchange INSTANCE;
  
  private FunctionalExchange() {
    querySpecifications.add(FunctionalExchange__involvingFunctionalChains.instance());
    querySpecifications.add(FunctionalExchange__allocatingComponentExchanges.instance());
    querySpecifications.add(FunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizations.instance());
    querySpecifications.add(FunctionalExchange__incomingFunctionalExchangeRealizations.instance());
    querySpecifications.add(FunctionalExchange__outgoingFunctionalExchangeRealizations.instance());
    querySpecifications.add(FunctionalExchange__categories.instance());
    querySpecifications.add(FunctionalExchange__sourceFunctionOutputPort.instance());
    querySpecifications.add(FunctionalExchange__targetFunctionInputPort.instance());
    querySpecifications.add(FunctionalExchange__realizedFunctionalExchanges.instance());
    querySpecifications.add(FunctionalExchange__realizingFunctionalExchanges.instance());
  }
  
  public FunctionalExchange__involvingFunctionalChains getFunctionalExchange__involvingFunctionalChains() {
    return FunctionalExchange__involvingFunctionalChains.instance();
  }
  
  public FunctionalExchange__involvingFunctionalChains.Matcher getFunctionalExchange__involvingFunctionalChains(final ViatraQueryEngine engine) {
    return FunctionalExchange__involvingFunctionalChains.Matcher.on(engine);
  }
  
  public FunctionalExchange__allocatingComponentExchanges getFunctionalExchange__allocatingComponentExchanges() {
    return FunctionalExchange__allocatingComponentExchanges.instance();
  }
  
  public FunctionalExchange__allocatingComponentExchanges.Matcher getFunctionalExchange__allocatingComponentExchanges(final ViatraQueryEngine engine) {
    return FunctionalExchange__allocatingComponentExchanges.Matcher.on(engine);
  }
  
  public FunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizations getFunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizations() {
    return FunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizations.instance();
  }
  
  public FunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizations.Matcher getFunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizations(final ViatraQueryEngine engine) {
    return FunctionalExchange__incomingComponentExchangeFunctionalExchangeRealizations.Matcher.on(engine);
  }
  
  public FunctionalExchange__incomingFunctionalExchangeRealizations getFunctionalExchange__incomingFunctionalExchangeRealizations() {
    return FunctionalExchange__incomingFunctionalExchangeRealizations.instance();
  }
  
  public FunctionalExchange__incomingFunctionalExchangeRealizations.Matcher getFunctionalExchange__incomingFunctionalExchangeRealizations(final ViatraQueryEngine engine) {
    return FunctionalExchange__incomingFunctionalExchangeRealizations.Matcher.on(engine);
  }
  
  public FunctionalExchange__outgoingFunctionalExchangeRealizations getFunctionalExchange__outgoingFunctionalExchangeRealizations() {
    return FunctionalExchange__outgoingFunctionalExchangeRealizations.instance();
  }
  
  public FunctionalExchange__outgoingFunctionalExchangeRealizations.Matcher getFunctionalExchange__outgoingFunctionalExchangeRealizations(final ViatraQueryEngine engine) {
    return FunctionalExchange__outgoingFunctionalExchangeRealizations.Matcher.on(engine);
  }
  
  public FunctionalExchange__categories getFunctionalExchange__categories() {
    return FunctionalExchange__categories.instance();
  }
  
  public FunctionalExchange__categories.Matcher getFunctionalExchange__categories(final ViatraQueryEngine engine) {
    return FunctionalExchange__categories.Matcher.on(engine);
  }
  
  public FunctionalExchange__sourceFunctionOutputPort getFunctionalExchange__sourceFunctionOutputPort() {
    return FunctionalExchange__sourceFunctionOutputPort.instance();
  }
  
  public FunctionalExchange__sourceFunctionOutputPort.Matcher getFunctionalExchange__sourceFunctionOutputPort(final ViatraQueryEngine engine) {
    return FunctionalExchange__sourceFunctionOutputPort.Matcher.on(engine);
  }
  
  public FunctionalExchange__targetFunctionInputPort getFunctionalExchange__targetFunctionInputPort() {
    return FunctionalExchange__targetFunctionInputPort.instance();
  }
  
  public FunctionalExchange__targetFunctionInputPort.Matcher getFunctionalExchange__targetFunctionInputPort(final ViatraQueryEngine engine) {
    return FunctionalExchange__targetFunctionInputPort.Matcher.on(engine);
  }
  
  public FunctionalExchange__realizedFunctionalExchanges getFunctionalExchange__realizedFunctionalExchanges() {
    return FunctionalExchange__realizedFunctionalExchanges.instance();
  }
  
  public FunctionalExchange__realizedFunctionalExchanges.Matcher getFunctionalExchange__realizedFunctionalExchanges(final ViatraQueryEngine engine) {
    return FunctionalExchange__realizedFunctionalExchanges.Matcher.on(engine);
  }
  
  public FunctionalExchange__realizingFunctionalExchanges getFunctionalExchange__realizingFunctionalExchanges() {
    return FunctionalExchange__realizingFunctionalExchanges.instance();
  }
  
  public FunctionalExchange__realizingFunctionalExchanges.Matcher getFunctionalExchange__realizingFunctionalExchanges(final ViatraQueryEngine engine) {
    return FunctionalExchange__realizingFunctionalExchanges.Matcher.on(engine);
  }
}
