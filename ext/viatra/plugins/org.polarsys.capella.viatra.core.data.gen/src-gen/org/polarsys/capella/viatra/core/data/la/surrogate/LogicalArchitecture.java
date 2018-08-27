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
package org.polarsys.capella.viatra.core.data.la.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalArchitecture__allocatedSystemAnalysesMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalArchitecture__allocatedSystemAnalysisRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalArchitecture__allocatingPhysicalArchitecturesMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalArchitecture__containedCapabilityRealizationPkgMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalArchitecture__containedLogicalFunctionPkgMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalArchitecture__allocatedSystemAnalysesQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalArchitecture__allocatedSystemAnalysisRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalArchitecture__allocatingPhysicalArchitecturesQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalArchitecture__containedCapabilityRealizationPkgQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalArchitecture__containedLogicalFunctionPkgQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in LogicalArchitecture.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file LogicalArchitecture.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.la.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>LogicalArchitecture__containedCapabilityRealizationPkg</li>
 * <li>LogicalArchitecture__containedLogicalFunctionPkg</li>
 * <li>LogicalArchitecture__allocatedSystemAnalysisRealizations</li>
 * <li>LogicalArchitecture__allocatedSystemAnalyses</li>
 * <li>LogicalArchitecture__allocatingPhysicalArchitectures</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class LogicalArchitecture extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static LogicalArchitecture instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new LogicalArchitecture();
    }
    return INSTANCE;
  }
  
  private static LogicalArchitecture INSTANCE;
  
  private LogicalArchitecture() throws ViatraQueryException {
    querySpecifications.add(LogicalArchitecture__containedCapabilityRealizationPkgQuerySpecification.instance());
    querySpecifications.add(LogicalArchitecture__containedLogicalFunctionPkgQuerySpecification.instance());
    querySpecifications.add(LogicalArchitecture__allocatedSystemAnalysisRealizationsQuerySpecification.instance());
    querySpecifications.add(LogicalArchitecture__allocatedSystemAnalysesQuerySpecification.instance());
    querySpecifications.add(LogicalArchitecture__allocatingPhysicalArchitecturesQuerySpecification.instance());
  }
  
  public LogicalArchitecture__containedCapabilityRealizationPkgQuerySpecification getLogicalArchitecture__containedCapabilityRealizationPkg() throws ViatraQueryException {
    return LogicalArchitecture__containedCapabilityRealizationPkgQuerySpecification.instance();
  }
  
  public LogicalArchitecture__containedCapabilityRealizationPkgMatcher getLogicalArchitecture__containedCapabilityRealizationPkg(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalArchitecture__containedCapabilityRealizationPkgMatcher.on(engine);
  }
  
  public LogicalArchitecture__containedLogicalFunctionPkgQuerySpecification getLogicalArchitecture__containedLogicalFunctionPkg() throws ViatraQueryException {
    return LogicalArchitecture__containedLogicalFunctionPkgQuerySpecification.instance();
  }
  
  public LogicalArchitecture__containedLogicalFunctionPkgMatcher getLogicalArchitecture__containedLogicalFunctionPkg(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalArchitecture__containedLogicalFunctionPkgMatcher.on(engine);
  }
  
  public LogicalArchitecture__allocatedSystemAnalysisRealizationsQuerySpecification getLogicalArchitecture__allocatedSystemAnalysisRealizations() throws ViatraQueryException {
    return LogicalArchitecture__allocatedSystemAnalysisRealizationsQuerySpecification.instance();
  }
  
  public LogicalArchitecture__allocatedSystemAnalysisRealizationsMatcher getLogicalArchitecture__allocatedSystemAnalysisRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalArchitecture__allocatedSystemAnalysisRealizationsMatcher.on(engine);
  }
  
  public LogicalArchitecture__allocatedSystemAnalysesQuerySpecification getLogicalArchitecture__allocatedSystemAnalyses() throws ViatraQueryException {
    return LogicalArchitecture__allocatedSystemAnalysesQuerySpecification.instance();
  }
  
  public LogicalArchitecture__allocatedSystemAnalysesMatcher getLogicalArchitecture__allocatedSystemAnalyses(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalArchitecture__allocatedSystemAnalysesMatcher.on(engine);
  }
  
  public LogicalArchitecture__allocatingPhysicalArchitecturesQuerySpecification getLogicalArchitecture__allocatingPhysicalArchitectures() throws ViatraQueryException {
    return LogicalArchitecture__allocatingPhysicalArchitecturesQuerySpecification.instance();
  }
  
  public LogicalArchitecture__allocatingPhysicalArchitecturesMatcher getLogicalArchitecture__allocatingPhysicalArchitectures(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalArchitecture__allocatingPhysicalArchitecturesMatcher.on(engine);
  }
}
