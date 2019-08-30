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
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionRealization__allocatedFunctionMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionRealization__allocatingFunctionMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionRealization__allocatedFunctionQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionRealization__allocatingFunctionQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in FunctionRealization.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionRealization__allocatedFunction</li>
 * <li>FunctionRealization__allocatingFunction</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionRealization instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new FunctionRealization();
    }
    return INSTANCE;
  }
  
  private static FunctionRealization INSTANCE;
  
  private FunctionRealization() throws ViatraQueryException {
    querySpecifications.add(FunctionRealization__allocatedFunctionQuerySpecification.instance());
    querySpecifications.add(FunctionRealization__allocatingFunctionQuerySpecification.instance());
  }
  
  public FunctionRealization__allocatedFunctionQuerySpecification getFunctionRealization__allocatedFunction() throws ViatraQueryException {
    return FunctionRealization__allocatedFunctionQuerySpecification.instance();
  }
  
  public FunctionRealization__allocatedFunctionMatcher getFunctionRealization__allocatedFunction(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionRealization__allocatedFunctionMatcher.on(engine);
  }
  
  public FunctionRealization__allocatingFunctionQuerySpecification getFunctionRealization__allocatingFunction() throws ViatraQueryException {
    return FunctionRealization__allocatingFunctionQuerySpecification.instance();
  }
  
  public FunctionRealization__allocatingFunctionMatcher getFunctionRealization__allocatingFunction(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionRealization__allocatingFunctionMatcher.on(engine);
  }
}
