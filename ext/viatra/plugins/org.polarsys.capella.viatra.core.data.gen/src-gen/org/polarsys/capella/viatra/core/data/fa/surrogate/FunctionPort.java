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

/**
 * A pattern group formed of all public patterns defined in FunctionPort.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionPort.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionPort__allocatorComponentPorts</li>
 * <li>FunctionPort__realizedFunctionPorts</li>
 * <li>FunctionPort__realizingFunctionPorts</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionPort extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionPort instance() {
    if (INSTANCE == null) {
        INSTANCE = new FunctionPort();
    }
    return INSTANCE;
  }
  
  private static FunctionPort INSTANCE;
  
  private FunctionPort() {
    querySpecifications.add(FunctionPort__allocatorComponentPorts.instance());
    querySpecifications.add(FunctionPort__realizedFunctionPorts.instance());
    querySpecifications.add(FunctionPort__realizingFunctionPorts.instance());
  }
  
  public FunctionPort__allocatorComponentPorts getFunctionPort__allocatorComponentPorts() {
    return FunctionPort__allocatorComponentPorts.instance();
  }
  
  public FunctionPort__allocatorComponentPorts.Matcher getFunctionPort__allocatorComponentPorts(final ViatraQueryEngine engine) {
    return FunctionPort__allocatorComponentPorts.Matcher.on(engine);
  }
  
  public FunctionPort__realizedFunctionPorts getFunctionPort__realizedFunctionPorts() {
    return FunctionPort__realizedFunctionPorts.instance();
  }
  
  public FunctionPort__realizedFunctionPorts.Matcher getFunctionPort__realizedFunctionPorts(final ViatraQueryEngine engine) {
    return FunctionPort__realizedFunctionPorts.Matcher.on(engine);
  }
  
  public FunctionPort__realizingFunctionPorts getFunctionPort__realizingFunctionPorts() {
    return FunctionPort__realizingFunctionPorts.instance();
  }
  
  public FunctionPort__realizingFunctionPorts.Matcher getFunctionPort__realizingFunctionPorts(final ViatraQueryEngine engine) {
    return FunctionPort__realizingFunctionPorts.Matcher.on(engine);
  }
}
