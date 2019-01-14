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
package org.polarsys.capella.viatra.core.data.capellamodeller.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.capellamodeller.surrogate.SystemEngineering__containedEPBSArchitecturesMatcher;
import org.polarsys.capella.viatra.core.data.capellamodeller.surrogate.SystemEngineering__containedLogicalArchitecturesMatcher;
import org.polarsys.capella.viatra.core.data.capellamodeller.surrogate.SystemEngineering__containedOperationalAnalysisMatcher;
import org.polarsys.capella.viatra.core.data.capellamodeller.surrogate.SystemEngineering__containedPhysicalArchitecturesMatcher;
import org.polarsys.capella.viatra.core.data.capellamodeller.surrogate.SystemEngineering__containedSystemAnalysisMatcher;
import org.polarsys.capella.viatra.core.data.capellamodeller.surrogate.util.SystemEngineering__containedEPBSArchitecturesQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellamodeller.surrogate.util.SystemEngineering__containedLogicalArchitecturesQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellamodeller.surrogate.util.SystemEngineering__containedOperationalAnalysisQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellamodeller.surrogate.util.SystemEngineering__containedPhysicalArchitecturesQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellamodeller.surrogate.util.SystemEngineering__containedSystemAnalysisQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in SystemEngineering.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
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
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class SystemEngineering extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SystemEngineering instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new SystemEngineering();
    }
    return INSTANCE;
  }
  
  private static SystemEngineering INSTANCE;
  
  private SystemEngineering() throws ViatraQueryException {
    querySpecifications.add(SystemEngineering__containedOperationalAnalysisQuerySpecification.instance());
    querySpecifications.add(SystemEngineering__containedSystemAnalysisQuerySpecification.instance());
    querySpecifications.add(SystemEngineering__containedLogicalArchitecturesQuerySpecification.instance());
    querySpecifications.add(SystemEngineering__containedPhysicalArchitecturesQuerySpecification.instance());
    querySpecifications.add(SystemEngineering__containedEPBSArchitecturesQuerySpecification.instance());
  }
  
  public SystemEngineering__containedOperationalAnalysisQuerySpecification getSystemEngineering__containedOperationalAnalysis() throws ViatraQueryException {
    return SystemEngineering__containedOperationalAnalysisQuerySpecification.instance();
  }
  
  public SystemEngineering__containedOperationalAnalysisMatcher getSystemEngineering__containedOperationalAnalysis(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemEngineering__containedOperationalAnalysisMatcher.on(engine);
  }
  
  public SystemEngineering__containedSystemAnalysisQuerySpecification getSystemEngineering__containedSystemAnalysis() throws ViatraQueryException {
    return SystemEngineering__containedSystemAnalysisQuerySpecification.instance();
  }
  
  public SystemEngineering__containedSystemAnalysisMatcher getSystemEngineering__containedSystemAnalysis(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemEngineering__containedSystemAnalysisMatcher.on(engine);
  }
  
  public SystemEngineering__containedLogicalArchitecturesQuerySpecification getSystemEngineering__containedLogicalArchitectures() throws ViatraQueryException {
    return SystemEngineering__containedLogicalArchitecturesQuerySpecification.instance();
  }
  
  public SystemEngineering__containedLogicalArchitecturesMatcher getSystemEngineering__containedLogicalArchitectures(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemEngineering__containedLogicalArchitecturesMatcher.on(engine);
  }
  
  public SystemEngineering__containedPhysicalArchitecturesQuerySpecification getSystemEngineering__containedPhysicalArchitectures() throws ViatraQueryException {
    return SystemEngineering__containedPhysicalArchitecturesQuerySpecification.instance();
  }
  
  public SystemEngineering__containedPhysicalArchitecturesMatcher getSystemEngineering__containedPhysicalArchitectures(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemEngineering__containedPhysicalArchitecturesMatcher.on(engine);
  }
  
  public SystemEngineering__containedEPBSArchitecturesQuerySpecification getSystemEngineering__containedEPBSArchitectures() throws ViatraQueryException {
    return SystemEngineering__containedEPBSArchitecturesQuerySpecification.instance();
  }
  
  public SystemEngineering__containedEPBSArchitecturesMatcher getSystemEngineering__containedEPBSArchitectures(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemEngineering__containedEPBSArchitecturesMatcher.on(engine);
  }
}
