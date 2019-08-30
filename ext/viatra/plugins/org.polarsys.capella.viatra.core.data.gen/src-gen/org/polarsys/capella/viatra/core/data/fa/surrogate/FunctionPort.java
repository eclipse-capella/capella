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
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionPort__allocatorComponentPortsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionPort__realizedFunctionPortsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionPort__realizingFunctionPortsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionPort__allocatorComponentPortsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionPort__realizedFunctionPortsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionPort__realizingFunctionPortsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in FunctionPort.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionPort.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionPort__allocatorComponentPorts</li>
 * <li>FunctionPort__realizedFunctionPorts</li>
 * <li>FunctionPort__realizingFunctionPorts</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionPort extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionPort instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new FunctionPort();
    }
    return INSTANCE;
  }
  
  private static FunctionPort INSTANCE;
  
  private FunctionPort() throws ViatraQueryException {
    querySpecifications.add(FunctionPort__allocatorComponentPortsQuerySpecification.instance());
    querySpecifications.add(FunctionPort__realizedFunctionPortsQuerySpecification.instance());
    querySpecifications.add(FunctionPort__realizingFunctionPortsQuerySpecification.instance());
  }
  
  public FunctionPort__allocatorComponentPortsQuerySpecification getFunctionPort__allocatorComponentPorts() throws ViatraQueryException {
    return FunctionPort__allocatorComponentPortsQuerySpecification.instance();
  }
  
  public FunctionPort__allocatorComponentPortsMatcher getFunctionPort__allocatorComponentPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionPort__allocatorComponentPortsMatcher.on(engine);
  }
  
  public FunctionPort__realizedFunctionPortsQuerySpecification getFunctionPort__realizedFunctionPorts() throws ViatraQueryException {
    return FunctionPort__realizedFunctionPortsQuerySpecification.instance();
  }
  
  public FunctionPort__realizedFunctionPortsMatcher getFunctionPort__realizedFunctionPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionPort__realizedFunctionPortsMatcher.on(engine);
  }
  
  public FunctionPort__realizingFunctionPortsQuerySpecification getFunctionPort__realizingFunctionPorts() throws ViatraQueryException {
    return FunctionPort__realizingFunctionPortsQuerySpecification.instance();
  }
  
  public FunctionPort__realizingFunctionPortsMatcher getFunctionPort__realizingFunctionPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionPort__realizingFunctionPortsMatcher.on(engine);
  }
}
