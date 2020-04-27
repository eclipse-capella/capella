/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
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
package org.polarsys.capella.viatra.core.data.pa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalArchitecture__allocatedLogicalArchitectureRealizations;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalArchitecture__allocatedLogicalArchitectures;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalArchitecture__allocatingEpbsArchitectures;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalArchitecture__containedCapabilityRealizationPkg;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalArchitecture__containedPhysicalFunctionPkg;

/**
 * A pattern group formed of all public patterns defined in PhysicalArchitecture.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalArchitecture.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.pa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalArchitecture__containedCapabilityRealizationPkg</li>
 * <li>PhysicalArchitecture__containedPhysicalFunctionPkg</li>
 * <li>PhysicalArchitecture__allocatedLogicalArchitectureRealizations</li>
 * <li>PhysicalArchitecture__allocatedLogicalArchitectures</li>
 * <li>PhysicalArchitecture__allocatingEpbsArchitectures</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalArchitecture extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalArchitecture instance() {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalArchitecture();
    }
    return INSTANCE;
  }
  
  private static PhysicalArchitecture INSTANCE;
  
  private PhysicalArchitecture() {
    querySpecifications.add(PhysicalArchitecture__containedCapabilityRealizationPkg.instance());
    querySpecifications.add(PhysicalArchitecture__containedPhysicalFunctionPkg.instance());
    querySpecifications.add(PhysicalArchitecture__allocatedLogicalArchitectureRealizations.instance());
    querySpecifications.add(PhysicalArchitecture__allocatedLogicalArchitectures.instance());
    querySpecifications.add(PhysicalArchitecture__allocatingEpbsArchitectures.instance());
  }
  
  public PhysicalArchitecture__containedCapabilityRealizationPkg getPhysicalArchitecture__containedCapabilityRealizationPkg() {
    return PhysicalArchitecture__containedCapabilityRealizationPkg.instance();
  }
  
  public PhysicalArchitecture__containedCapabilityRealizationPkg.Matcher getPhysicalArchitecture__containedCapabilityRealizationPkg(final ViatraQueryEngine engine) {
    return PhysicalArchitecture__containedCapabilityRealizationPkg.Matcher.on(engine);
  }
  
  public PhysicalArchitecture__containedPhysicalFunctionPkg getPhysicalArchitecture__containedPhysicalFunctionPkg() {
    return PhysicalArchitecture__containedPhysicalFunctionPkg.instance();
  }
  
  public PhysicalArchitecture__containedPhysicalFunctionPkg.Matcher getPhysicalArchitecture__containedPhysicalFunctionPkg(final ViatraQueryEngine engine) {
    return PhysicalArchitecture__containedPhysicalFunctionPkg.Matcher.on(engine);
  }
  
  public PhysicalArchitecture__allocatedLogicalArchitectureRealizations getPhysicalArchitecture__allocatedLogicalArchitectureRealizations() {
    return PhysicalArchitecture__allocatedLogicalArchitectureRealizations.instance();
  }
  
  public PhysicalArchitecture__allocatedLogicalArchitectureRealizations.Matcher getPhysicalArchitecture__allocatedLogicalArchitectureRealizations(final ViatraQueryEngine engine) {
    return PhysicalArchitecture__allocatedLogicalArchitectureRealizations.Matcher.on(engine);
  }
  
  public PhysicalArchitecture__allocatedLogicalArchitectures getPhysicalArchitecture__allocatedLogicalArchitectures() {
    return PhysicalArchitecture__allocatedLogicalArchitectures.instance();
  }
  
  public PhysicalArchitecture__allocatedLogicalArchitectures.Matcher getPhysicalArchitecture__allocatedLogicalArchitectures(final ViatraQueryEngine engine) {
    return PhysicalArchitecture__allocatedLogicalArchitectures.Matcher.on(engine);
  }
  
  public PhysicalArchitecture__allocatingEpbsArchitectures getPhysicalArchitecture__allocatingEpbsArchitectures() {
    return PhysicalArchitecture__allocatingEpbsArchitectures.instance();
  }
  
  public PhysicalArchitecture__allocatingEpbsArchitectures.Matcher getPhysicalArchitecture__allocatingEpbsArchitectures(final ViatraQueryEngine engine) {
    return PhysicalArchitecture__allocatingEpbsArchitectures.Matcher.on(engine);
  }
}
