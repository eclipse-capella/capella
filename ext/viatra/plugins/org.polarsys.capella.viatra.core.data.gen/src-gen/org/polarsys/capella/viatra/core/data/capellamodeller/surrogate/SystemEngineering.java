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
package org.polarsys.capella.viatra.core.data.capellamodeller.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in SystemEngineering.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SystemEngineering.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellamodeller.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>SystemEngineering__containedOperationalAnalysis</li>
 * <li>SystemEngineering__containedSystemAnalysis</li>
 * <li>SystemEngineering__containedLogicalArchitectures</li>
 * <li>SystemEngineering__containedPhysicalArchitectures</li>
 * <li>SystemEngineering__containedEPBSArchitectures</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class SystemEngineering extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SystemEngineering instance() {
    if (INSTANCE == null) {
        INSTANCE = new SystemEngineering();
    }
    return INSTANCE;
  }
  
  private static SystemEngineering INSTANCE;
  
  private SystemEngineering() {
    querySpecifications.add(SystemEngineering__containedOperationalAnalysis.instance());
    querySpecifications.add(SystemEngineering__containedSystemAnalysis.instance());
    querySpecifications.add(SystemEngineering__containedLogicalArchitectures.instance());
    querySpecifications.add(SystemEngineering__containedPhysicalArchitectures.instance());
    querySpecifications.add(SystemEngineering__containedEPBSArchitectures.instance());
  }
  
  public SystemEngineering__containedOperationalAnalysis getSystemEngineering__containedOperationalAnalysis() {
    return SystemEngineering__containedOperationalAnalysis.instance();
  }
  
  public SystemEngineering__containedOperationalAnalysis.Matcher getSystemEngineering__containedOperationalAnalysis(final ViatraQueryEngine engine) {
    return SystemEngineering__containedOperationalAnalysis.Matcher.on(engine);
  }
  
  public SystemEngineering__containedSystemAnalysis getSystemEngineering__containedSystemAnalysis() {
    return SystemEngineering__containedSystemAnalysis.instance();
  }
  
  public SystemEngineering__containedSystemAnalysis.Matcher getSystemEngineering__containedSystemAnalysis(final ViatraQueryEngine engine) {
    return SystemEngineering__containedSystemAnalysis.Matcher.on(engine);
  }
  
  public SystemEngineering__containedLogicalArchitectures getSystemEngineering__containedLogicalArchitectures() {
    return SystemEngineering__containedLogicalArchitectures.instance();
  }
  
  public SystemEngineering__containedLogicalArchitectures.Matcher getSystemEngineering__containedLogicalArchitectures(final ViatraQueryEngine engine) {
    return SystemEngineering__containedLogicalArchitectures.Matcher.on(engine);
  }
  
  public SystemEngineering__containedPhysicalArchitectures getSystemEngineering__containedPhysicalArchitectures() {
    return SystemEngineering__containedPhysicalArchitectures.instance();
  }
  
  public SystemEngineering__containedPhysicalArchitectures.Matcher getSystemEngineering__containedPhysicalArchitectures(final ViatraQueryEngine engine) {
    return SystemEngineering__containedPhysicalArchitectures.Matcher.on(engine);
  }
  
  public SystemEngineering__containedEPBSArchitectures getSystemEngineering__containedEPBSArchitectures() {
    return SystemEngineering__containedEPBSArchitectures.instance();
  }
  
  public SystemEngineering__containedEPBSArchitectures.Matcher getSystemEngineering__containedEPBSArchitectures(final ViatraQueryEngine engine) {
    return SystemEngineering__containedEPBSArchitectures.Matcher.on(engine);
  }
}
