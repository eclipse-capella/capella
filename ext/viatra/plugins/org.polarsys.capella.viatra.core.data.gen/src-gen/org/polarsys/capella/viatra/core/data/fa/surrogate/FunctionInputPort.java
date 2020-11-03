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
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionInputPort__incomingFunctionalExchanges;

/**
 * A pattern group formed of all public patterns defined in FunctionInputPort.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionInputPort.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionInputPort__incomingFunctionalExchanges</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionInputPort extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionInputPort instance() {
    if (INSTANCE == null) {
        INSTANCE = new FunctionInputPort();
    }
    return INSTANCE;
  }
  
  private static FunctionInputPort INSTANCE;
  
  private FunctionInputPort() {
    querySpecifications.add(FunctionInputPort__incomingFunctionalExchanges.instance());
  }
  
  public FunctionInputPort__incomingFunctionalExchanges getFunctionInputPort__incomingFunctionalExchanges() {
    return FunctionInputPort__incomingFunctionalExchanges.instance();
  }
  
  public FunctionInputPort__incomingFunctionalExchanges.Matcher getFunctionInputPort__incomingFunctionalExchanges(final ViatraQueryEngine engine) {
    return FunctionInputPort__incomingFunctionalExchanges.Matcher.on(engine);
  }
}
