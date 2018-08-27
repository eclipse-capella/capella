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
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchangeRealization__realizedFunctionalExchangeMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalExchangeRealization__realizingFunctionalExchangeMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalExchangeRealization__realizedFunctionalExchangeQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalExchangeRealization__realizingFunctionalExchangeQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in FunctionalExchangeRealization.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionalExchangeRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionalExchangeRealization__realizedFunctionalExchange</li>
 * <li>FunctionalExchangeRealization__realizingFunctionalExchange</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionalExchangeRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionalExchangeRealization instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new FunctionalExchangeRealization();
    }
    return INSTANCE;
  }
  
  private static FunctionalExchangeRealization INSTANCE;
  
  private FunctionalExchangeRealization() throws ViatraQueryException {
    querySpecifications.add(FunctionalExchangeRealization__realizedFunctionalExchangeQuerySpecification.instance());
    querySpecifications.add(FunctionalExchangeRealization__realizingFunctionalExchangeQuerySpecification.instance());
  }
  
  public FunctionalExchangeRealization__realizedFunctionalExchangeQuerySpecification getFunctionalExchangeRealization__realizedFunctionalExchange() throws ViatraQueryException {
    return FunctionalExchangeRealization__realizedFunctionalExchangeQuerySpecification.instance();
  }
  
  public FunctionalExchangeRealization__realizedFunctionalExchangeMatcher getFunctionalExchangeRealization__realizedFunctionalExchange(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalExchangeRealization__realizedFunctionalExchangeMatcher.on(engine);
  }
  
  public FunctionalExchangeRealization__realizingFunctionalExchangeQuerySpecification getFunctionalExchangeRealization__realizingFunctionalExchange() throws ViatraQueryException {
    return FunctionalExchangeRealization__realizingFunctionalExchangeQuerySpecification.instance();
  }
  
  public FunctionalExchangeRealization__realizingFunctionalExchangeMatcher getFunctionalExchangeRealization__realizingFunctionalExchange(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalExchangeRealization__realizingFunctionalExchangeMatcher.on(engine);
  }
}
