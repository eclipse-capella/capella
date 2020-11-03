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
package org.polarsys.capella.viatra.core.data.la.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalArchitecture__allocatedSystemAnalyses;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalArchitecture__allocatedSystemAnalysisRealizations;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalArchitecture__allocatingPhysicalArchitectures;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalArchitecture__containedCapabilityRealizationPkg;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalArchitecture__containedLogicalFunctionPkg;

/**
 * A pattern group formed of all public patterns defined in LogicalArchitecture.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class LogicalArchitecture extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static LogicalArchitecture instance() {
    if (INSTANCE == null) {
        INSTANCE = new LogicalArchitecture();
    }
    return INSTANCE;
  }
  
  private static LogicalArchitecture INSTANCE;
  
  private LogicalArchitecture() {
    querySpecifications.add(LogicalArchitecture__containedCapabilityRealizationPkg.instance());
    querySpecifications.add(LogicalArchitecture__containedLogicalFunctionPkg.instance());
    querySpecifications.add(LogicalArchitecture__allocatedSystemAnalysisRealizations.instance());
    querySpecifications.add(LogicalArchitecture__allocatedSystemAnalyses.instance());
    querySpecifications.add(LogicalArchitecture__allocatingPhysicalArchitectures.instance());
  }
  
  public LogicalArchitecture__containedCapabilityRealizationPkg getLogicalArchitecture__containedCapabilityRealizationPkg() {
    return LogicalArchitecture__containedCapabilityRealizationPkg.instance();
  }
  
  public LogicalArchitecture__containedCapabilityRealizationPkg.Matcher getLogicalArchitecture__containedCapabilityRealizationPkg(final ViatraQueryEngine engine) {
    return LogicalArchitecture__containedCapabilityRealizationPkg.Matcher.on(engine);
  }
  
  public LogicalArchitecture__containedLogicalFunctionPkg getLogicalArchitecture__containedLogicalFunctionPkg() {
    return LogicalArchitecture__containedLogicalFunctionPkg.instance();
  }
  
  public LogicalArchitecture__containedLogicalFunctionPkg.Matcher getLogicalArchitecture__containedLogicalFunctionPkg(final ViatraQueryEngine engine) {
    return LogicalArchitecture__containedLogicalFunctionPkg.Matcher.on(engine);
  }
  
  public LogicalArchitecture__allocatedSystemAnalysisRealizations getLogicalArchitecture__allocatedSystemAnalysisRealizations() {
    return LogicalArchitecture__allocatedSystemAnalysisRealizations.instance();
  }
  
  public LogicalArchitecture__allocatedSystemAnalysisRealizations.Matcher getLogicalArchitecture__allocatedSystemAnalysisRealizations(final ViatraQueryEngine engine) {
    return LogicalArchitecture__allocatedSystemAnalysisRealizations.Matcher.on(engine);
  }
  
  public LogicalArchitecture__allocatedSystemAnalyses getLogicalArchitecture__allocatedSystemAnalyses() {
    return LogicalArchitecture__allocatedSystemAnalyses.instance();
  }
  
  public LogicalArchitecture__allocatedSystemAnalyses.Matcher getLogicalArchitecture__allocatedSystemAnalyses(final ViatraQueryEngine engine) {
    return LogicalArchitecture__allocatedSystemAnalyses.Matcher.on(engine);
  }
  
  public LogicalArchitecture__allocatingPhysicalArchitectures getLogicalArchitecture__allocatingPhysicalArchitectures() {
    return LogicalArchitecture__allocatingPhysicalArchitectures.instance();
  }
  
  public LogicalArchitecture__allocatingPhysicalArchitectures.Matcher getLogicalArchitecture__allocatingPhysicalArchitectures(final ViatraQueryEngine engine) {
    return LogicalArchitecture__allocatingPhysicalArchitectures.Matcher.on(engine);
  }
}
