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
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionInputPort__incomingFunctionalExchangesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionInputPort__incomingFunctionalExchangesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in FunctionInputPort.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionInputPort.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionInputPort__incomingFunctionalExchanges</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionInputPort extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionInputPort instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new FunctionInputPort();
    }
    return INSTANCE;
  }
  
  private static FunctionInputPort INSTANCE;
  
  private FunctionInputPort() throws ViatraQueryException {
    querySpecifications.add(FunctionInputPort__incomingFunctionalExchangesQuerySpecification.instance());
  }
  
  public FunctionInputPort__incomingFunctionalExchangesQuerySpecification getFunctionInputPort__incomingFunctionalExchanges() throws ViatraQueryException {
    return FunctionInputPort__incomingFunctionalExchangesQuerySpecification.instance();
  }
  
  public FunctionInputPort__incomingFunctionalExchangesMatcher getFunctionInputPort__incomingFunctionalExchanges(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionInputPort__incomingFunctionalExchangesMatcher.on(engine);
  }
}
