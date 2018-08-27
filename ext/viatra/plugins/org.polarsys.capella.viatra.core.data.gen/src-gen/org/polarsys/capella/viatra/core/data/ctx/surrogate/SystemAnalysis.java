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
package org.polarsys.capella.viatra.core.data.ctx.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemAnalysis__allocatedOperationalAnalysesMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemAnalysis__allocatedOperationalAnalysisRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemAnalysis__allocatingLogicalArchitecturesMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemAnalysis__containedCapabilityPkgMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemAnalysis__containedSystemFunctionPkgMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.SystemAnalysis__allocatedOperationalAnalysesQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.SystemAnalysis__allocatedOperationalAnalysisRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.SystemAnalysis__allocatingLogicalArchitecturesQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.SystemAnalysis__containedCapabilityPkgQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.SystemAnalysis__containedSystemFunctionPkgQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in SystemAnalysis.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SystemAnalysis.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>SystemAnalysis__containedCapabilityPkg</li>
 * <li>SystemAnalysis__containedSystemFunctionPkg</li>
 * <li>SystemAnalysis__allocatedOperationalAnalysisRealizations</li>
 * <li>SystemAnalysis__allocatedOperationalAnalyses</li>
 * <li>SystemAnalysis__allocatingLogicalArchitectures</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class SystemAnalysis extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SystemAnalysis instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new SystemAnalysis();
    }
    return INSTANCE;
  }
  
  private static SystemAnalysis INSTANCE;
  
  private SystemAnalysis() throws ViatraQueryException {
    querySpecifications.add(SystemAnalysis__containedCapabilityPkgQuerySpecification.instance());
    querySpecifications.add(SystemAnalysis__containedSystemFunctionPkgQuerySpecification.instance());
    querySpecifications.add(SystemAnalysis__allocatedOperationalAnalysisRealizationsQuerySpecification.instance());
    querySpecifications.add(SystemAnalysis__allocatedOperationalAnalysesQuerySpecification.instance());
    querySpecifications.add(SystemAnalysis__allocatingLogicalArchitecturesQuerySpecification.instance());
  }
  
  public SystemAnalysis__containedCapabilityPkgQuerySpecification getSystemAnalysis__containedCapabilityPkg() throws ViatraQueryException {
    return SystemAnalysis__containedCapabilityPkgQuerySpecification.instance();
  }
  
  public SystemAnalysis__containedCapabilityPkgMatcher getSystemAnalysis__containedCapabilityPkg(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemAnalysis__containedCapabilityPkgMatcher.on(engine);
  }
  
  public SystemAnalysis__containedSystemFunctionPkgQuerySpecification getSystemAnalysis__containedSystemFunctionPkg() throws ViatraQueryException {
    return SystemAnalysis__containedSystemFunctionPkgQuerySpecification.instance();
  }
  
  public SystemAnalysis__containedSystemFunctionPkgMatcher getSystemAnalysis__containedSystemFunctionPkg(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemAnalysis__containedSystemFunctionPkgMatcher.on(engine);
  }
  
  public SystemAnalysis__allocatedOperationalAnalysisRealizationsQuerySpecification getSystemAnalysis__allocatedOperationalAnalysisRealizations() throws ViatraQueryException {
    return SystemAnalysis__allocatedOperationalAnalysisRealizationsQuerySpecification.instance();
  }
  
  public SystemAnalysis__allocatedOperationalAnalysisRealizationsMatcher getSystemAnalysis__allocatedOperationalAnalysisRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemAnalysis__allocatedOperationalAnalysisRealizationsMatcher.on(engine);
  }
  
  public SystemAnalysis__allocatedOperationalAnalysesQuerySpecification getSystemAnalysis__allocatedOperationalAnalyses() throws ViatraQueryException {
    return SystemAnalysis__allocatedOperationalAnalysesQuerySpecification.instance();
  }
  
  public SystemAnalysis__allocatedOperationalAnalysesMatcher getSystemAnalysis__allocatedOperationalAnalyses(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemAnalysis__allocatedOperationalAnalysesMatcher.on(engine);
  }
  
  public SystemAnalysis__allocatingLogicalArchitecturesQuerySpecification getSystemAnalysis__allocatingLogicalArchitectures() throws ViatraQueryException {
    return SystemAnalysis__allocatingLogicalArchitecturesQuerySpecification.instance();
  }
  
  public SystemAnalysis__allocatingLogicalArchitecturesMatcher getSystemAnalysis__allocatingLogicalArchitectures(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemAnalysis__allocatingLogicalArchitecturesMatcher.on(engine);
  }
}
