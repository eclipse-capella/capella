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
package org.polarsys.capella.viatra.core.data.epbs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in EPBSArchitecture.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file EPBSArchitecture.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.epbs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>EPBSArchitecture__containedCapabilityRealizationPkg</li>
 * <li>EPBSArchitecture__allocatedPhysicalArchitectureRealizations</li>
 * <li>EPBSArchitecture__allocatedPhysicalArchitectures</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class EPBSArchitecture extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static EPBSArchitecture instance() {
    if (INSTANCE == null) {
        INSTANCE = new EPBSArchitecture();
    }
    return INSTANCE;
  }
  
  private static EPBSArchitecture INSTANCE;
  
  private EPBSArchitecture() {
    querySpecifications.add(EPBSArchitecture__containedCapabilityRealizationPkg.instance());
    querySpecifications.add(EPBSArchitecture__allocatedPhysicalArchitectureRealizations.instance());
    querySpecifications.add(EPBSArchitecture__allocatedPhysicalArchitectures.instance());
  }
  
  public EPBSArchitecture__containedCapabilityRealizationPkg getEPBSArchitecture__containedCapabilityRealizationPkg() {
    return EPBSArchitecture__containedCapabilityRealizationPkg.instance();
  }
  
  public EPBSArchitecture__containedCapabilityRealizationPkg.Matcher getEPBSArchitecture__containedCapabilityRealizationPkg(final ViatraQueryEngine engine) {
    return EPBSArchitecture__containedCapabilityRealizationPkg.Matcher.on(engine);
  }
  
  public EPBSArchitecture__allocatedPhysicalArchitectureRealizations getEPBSArchitecture__allocatedPhysicalArchitectureRealizations() {
    return EPBSArchitecture__allocatedPhysicalArchitectureRealizations.instance();
  }
  
  public EPBSArchitecture__allocatedPhysicalArchitectureRealizations.Matcher getEPBSArchitecture__allocatedPhysicalArchitectureRealizations(final ViatraQueryEngine engine) {
    return EPBSArchitecture__allocatedPhysicalArchitectureRealizations.Matcher.on(engine);
  }
  
  public EPBSArchitecture__allocatedPhysicalArchitectures getEPBSArchitecture__allocatedPhysicalArchitectures() {
    return EPBSArchitecture__allocatedPhysicalArchitectures.instance();
  }
  
  public EPBSArchitecture__allocatedPhysicalArchitectures.Matcher getEPBSArchitecture__allocatedPhysicalArchitectures(final ViatraQueryEngine engine) {
    return EPBSArchitecture__allocatedPhysicalArchitectures.Matcher.on(engine);
  }
}
