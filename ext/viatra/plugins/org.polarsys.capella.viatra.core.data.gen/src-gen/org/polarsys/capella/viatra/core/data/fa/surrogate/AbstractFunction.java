/**
 * 
 *   Copyright (c) 2006, 2020 THALES DMS FRANCE.
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
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__allocationBlocks;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__componentFunctionalAllocations;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__inFunctionRealizations;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__involvingCapabilities;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__involvingCapabilityRealizations;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__involvingFunctionalChains;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__linkedFunctionSpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__linkedStateMachine;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__outFunctionRealizations;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__subFunctions;

/**
 * A pattern group formed of all public patterns defined in AbstractFunction.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractFunction.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractFunction__subFunctions</li>
 * <li>AbstractFunction__outFunctionRealizations</li>
 * <li>AbstractFunction__inFunctionRealizations</li>
 * <li>AbstractFunction__componentFunctionalAllocations</li>
 * <li>AbstractFunction__allocationBlocks</li>
 * <li>AbstractFunction__involvingCapabilities</li>
 * <li>AbstractFunction__involvingCapabilityRealizations</li>
 * <li>AbstractFunction__involvingFunctionalChains</li>
 * <li>AbstractFunction__linkedStateMachine</li>
 * <li>AbstractFunction__linkedFunctionSpecification</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractFunction extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractFunction instance() {
    if (INSTANCE == null) {
        INSTANCE = new AbstractFunction();
    }
    return INSTANCE;
  }
  
  private static AbstractFunction INSTANCE;
  
  private AbstractFunction() {
    querySpecifications.add(AbstractFunction__subFunctions.instance());
    querySpecifications.add(AbstractFunction__outFunctionRealizations.instance());
    querySpecifications.add(AbstractFunction__inFunctionRealizations.instance());
    querySpecifications.add(AbstractFunction__componentFunctionalAllocations.instance());
    querySpecifications.add(AbstractFunction__allocationBlocks.instance());
    querySpecifications.add(AbstractFunction__involvingCapabilities.instance());
    querySpecifications.add(AbstractFunction__involvingCapabilityRealizations.instance());
    querySpecifications.add(AbstractFunction__involvingFunctionalChains.instance());
    querySpecifications.add(AbstractFunction__linkedStateMachine.instance());
    querySpecifications.add(AbstractFunction__linkedFunctionSpecification.instance());
  }
  
  public AbstractFunction__subFunctions getAbstractFunction__subFunctions() {
    return AbstractFunction__subFunctions.instance();
  }
  
  public AbstractFunction__subFunctions.Matcher getAbstractFunction__subFunctions(final ViatraQueryEngine engine) {
    return AbstractFunction__subFunctions.Matcher.on(engine);
  }
  
  public AbstractFunction__outFunctionRealizations getAbstractFunction__outFunctionRealizations() {
    return AbstractFunction__outFunctionRealizations.instance();
  }
  
  public AbstractFunction__outFunctionRealizations.Matcher getAbstractFunction__outFunctionRealizations(final ViatraQueryEngine engine) {
    return AbstractFunction__outFunctionRealizations.Matcher.on(engine);
  }
  
  public AbstractFunction__inFunctionRealizations getAbstractFunction__inFunctionRealizations() {
    return AbstractFunction__inFunctionRealizations.instance();
  }
  
  public AbstractFunction__inFunctionRealizations.Matcher getAbstractFunction__inFunctionRealizations(final ViatraQueryEngine engine) {
    return AbstractFunction__inFunctionRealizations.Matcher.on(engine);
  }
  
  public AbstractFunction__componentFunctionalAllocations getAbstractFunction__componentFunctionalAllocations() {
    return AbstractFunction__componentFunctionalAllocations.instance();
  }
  
  public AbstractFunction__componentFunctionalAllocations.Matcher getAbstractFunction__componentFunctionalAllocations(final ViatraQueryEngine engine) {
    return AbstractFunction__componentFunctionalAllocations.Matcher.on(engine);
  }
  
  public AbstractFunction__allocationBlocks getAbstractFunction__allocationBlocks() {
    return AbstractFunction__allocationBlocks.instance();
  }
  
  public AbstractFunction__allocationBlocks.Matcher getAbstractFunction__allocationBlocks(final ViatraQueryEngine engine) {
    return AbstractFunction__allocationBlocks.Matcher.on(engine);
  }
  
  public AbstractFunction__involvingCapabilities getAbstractFunction__involvingCapabilities() {
    return AbstractFunction__involvingCapabilities.instance();
  }
  
  public AbstractFunction__involvingCapabilities.Matcher getAbstractFunction__involvingCapabilities(final ViatraQueryEngine engine) {
    return AbstractFunction__involvingCapabilities.Matcher.on(engine);
  }
  
  public AbstractFunction__involvingCapabilityRealizations getAbstractFunction__involvingCapabilityRealizations() {
    return AbstractFunction__involvingCapabilityRealizations.instance();
  }
  
  public AbstractFunction__involvingCapabilityRealizations.Matcher getAbstractFunction__involvingCapabilityRealizations(final ViatraQueryEngine engine) {
    return AbstractFunction__involvingCapabilityRealizations.Matcher.on(engine);
  }
  
  public AbstractFunction__involvingFunctionalChains getAbstractFunction__involvingFunctionalChains() {
    return AbstractFunction__involvingFunctionalChains.instance();
  }
  
  public AbstractFunction__involvingFunctionalChains.Matcher getAbstractFunction__involvingFunctionalChains(final ViatraQueryEngine engine) {
    return AbstractFunction__involvingFunctionalChains.Matcher.on(engine);
  }
  
  public AbstractFunction__linkedStateMachine getAbstractFunction__linkedStateMachine() {
    return AbstractFunction__linkedStateMachine.instance();
  }
  
  public AbstractFunction__linkedStateMachine.Matcher getAbstractFunction__linkedStateMachine(final ViatraQueryEngine engine) {
    return AbstractFunction__linkedStateMachine.Matcher.on(engine);
  }
  
  public AbstractFunction__linkedFunctionSpecification getAbstractFunction__linkedFunctionSpecification() {
    return AbstractFunction__linkedFunctionSpecification.instance();
  }
  
  public AbstractFunction__linkedFunctionSpecification.Matcher getAbstractFunction__linkedFunctionSpecification(final ViatraQueryEngine engine) {
    return AbstractFunction__linkedFunctionSpecification.Matcher.on(engine);
  }
}
