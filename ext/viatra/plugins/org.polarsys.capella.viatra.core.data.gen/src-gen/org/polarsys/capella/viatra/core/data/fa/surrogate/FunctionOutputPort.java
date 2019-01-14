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
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionOutputPort__outgoingFunctionalExchangesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionOutputPort__outgoingFunctionalExchangesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in FunctionOutputPort.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionOutputPort.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionOutputPort__outgoingFunctionalExchanges</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionOutputPort extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionOutputPort instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new FunctionOutputPort();
    }
    return INSTANCE;
  }
  
  private static FunctionOutputPort INSTANCE;
  
  private FunctionOutputPort() throws ViatraQueryException {
    querySpecifications.add(FunctionOutputPort__outgoingFunctionalExchangesQuerySpecification.instance());
  }
  
  public FunctionOutputPort__outgoingFunctionalExchangesQuerySpecification getFunctionOutputPort__outgoingFunctionalExchanges() throws ViatraQueryException {
    return FunctionOutputPort__outgoingFunctionalExchangesQuerySpecification.instance();
  }
  
  public FunctionOutputPort__outgoingFunctionalExchangesMatcher getFunctionOutputPort__outgoingFunctionalExchanges(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionOutputPort__outgoingFunctionalExchangesMatcher.on(engine);
  }
}
