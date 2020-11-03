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
package org.polarsys.capella.viatra.core.data.ctx.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemAnalysis__allocatedOperationalAnalyses;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemAnalysis__allocatedOperationalAnalysisRealizations;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemAnalysis__allocatingLogicalArchitectures;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemAnalysis__containedCapabilityPkg;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemAnalysis__containedSystemFunctionPkg;

/**
 * A pattern group formed of all public patterns defined in SystemAnalysis.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class SystemAnalysis extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SystemAnalysis instance() {
    if (INSTANCE == null) {
        INSTANCE = new SystemAnalysis();
    }
    return INSTANCE;
  }
  
  private static SystemAnalysis INSTANCE;
  
  private SystemAnalysis() {
    querySpecifications.add(SystemAnalysis__containedCapabilityPkg.instance());
    querySpecifications.add(SystemAnalysis__containedSystemFunctionPkg.instance());
    querySpecifications.add(SystemAnalysis__allocatedOperationalAnalysisRealizations.instance());
    querySpecifications.add(SystemAnalysis__allocatedOperationalAnalyses.instance());
    querySpecifications.add(SystemAnalysis__allocatingLogicalArchitectures.instance());
  }
  
  public SystemAnalysis__containedCapabilityPkg getSystemAnalysis__containedCapabilityPkg() {
    return SystemAnalysis__containedCapabilityPkg.instance();
  }
  
  public SystemAnalysis__containedCapabilityPkg.Matcher getSystemAnalysis__containedCapabilityPkg(final ViatraQueryEngine engine) {
    return SystemAnalysis__containedCapabilityPkg.Matcher.on(engine);
  }
  
  public SystemAnalysis__containedSystemFunctionPkg getSystemAnalysis__containedSystemFunctionPkg() {
    return SystemAnalysis__containedSystemFunctionPkg.instance();
  }
  
  public SystemAnalysis__containedSystemFunctionPkg.Matcher getSystemAnalysis__containedSystemFunctionPkg(final ViatraQueryEngine engine) {
    return SystemAnalysis__containedSystemFunctionPkg.Matcher.on(engine);
  }
  
  public SystemAnalysis__allocatedOperationalAnalysisRealizations getSystemAnalysis__allocatedOperationalAnalysisRealizations() {
    return SystemAnalysis__allocatedOperationalAnalysisRealizations.instance();
  }
  
  public SystemAnalysis__allocatedOperationalAnalysisRealizations.Matcher getSystemAnalysis__allocatedOperationalAnalysisRealizations(final ViatraQueryEngine engine) {
    return SystemAnalysis__allocatedOperationalAnalysisRealizations.Matcher.on(engine);
  }
  
  public SystemAnalysis__allocatedOperationalAnalyses getSystemAnalysis__allocatedOperationalAnalyses() {
    return SystemAnalysis__allocatedOperationalAnalyses.instance();
  }
  
  public SystemAnalysis__allocatedOperationalAnalyses.Matcher getSystemAnalysis__allocatedOperationalAnalyses(final ViatraQueryEngine engine) {
    return SystemAnalysis__allocatedOperationalAnalyses.Matcher.on(engine);
  }
  
  public SystemAnalysis__allocatingLogicalArchitectures getSystemAnalysis__allocatingLogicalArchitectures() {
    return SystemAnalysis__allocatingLogicalArchitectures.instance();
  }
  
  public SystemAnalysis__allocatingLogicalArchitectures.Matcher getSystemAnalysis__allocatingLogicalArchitectures(final ViatraQueryEngine engine) {
    return SystemAnalysis__allocatingLogicalArchitectures.Matcher.on(engine);
  }
}
