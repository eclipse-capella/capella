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
package org.polarsys.capella.viatra.core.data.epbs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.epbs.surrogate.EPBSArchitecture__allocatedPhysicalArchitectureRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.epbs.surrogate.EPBSArchitecture__allocatedPhysicalArchitecturesMatcher;
import org.polarsys.capella.viatra.core.data.epbs.surrogate.EPBSArchitecture__containedCapabilityRealizationPkgMatcher;
import org.polarsys.capella.viatra.core.data.epbs.surrogate.util.EPBSArchitecture__allocatedPhysicalArchitectureRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.epbs.surrogate.util.EPBSArchitecture__allocatedPhysicalArchitecturesQuerySpecification;
import org.polarsys.capella.viatra.core.data.epbs.surrogate.util.EPBSArchitecture__containedCapabilityRealizationPkgQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in EPBSArchitecture.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file EPBSArchitecture.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.epbs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>EPBSArchitecture__containedCapabilityRealizationPkg</li>
 * <li>EPBSArchitecture__allocatedPhysicalArchitectureRealizations</li>
 * <li>EPBSArchitecture__allocatedPhysicalArchitectures</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class EPBSArchitecture extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static EPBSArchitecture instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new EPBSArchitecture();
    }
    return INSTANCE;
  }
  
  private static EPBSArchitecture INSTANCE;
  
  private EPBSArchitecture() throws ViatraQueryException {
    querySpecifications.add(EPBSArchitecture__containedCapabilityRealizationPkgQuerySpecification.instance());
    querySpecifications.add(EPBSArchitecture__allocatedPhysicalArchitectureRealizationsQuerySpecification.instance());
    querySpecifications.add(EPBSArchitecture__allocatedPhysicalArchitecturesQuerySpecification.instance());
  }
  
  public EPBSArchitecture__containedCapabilityRealizationPkgQuerySpecification getEPBSArchitecture__containedCapabilityRealizationPkg() throws ViatraQueryException {
    return EPBSArchitecture__containedCapabilityRealizationPkgQuerySpecification.instance();
  }
  
  public EPBSArchitecture__containedCapabilityRealizationPkgMatcher getEPBSArchitecture__containedCapabilityRealizationPkg(final ViatraQueryEngine engine) throws ViatraQueryException {
    return EPBSArchitecture__containedCapabilityRealizationPkgMatcher.on(engine);
  }
  
  public EPBSArchitecture__allocatedPhysicalArchitectureRealizationsQuerySpecification getEPBSArchitecture__allocatedPhysicalArchitectureRealizations() throws ViatraQueryException {
    return EPBSArchitecture__allocatedPhysicalArchitectureRealizationsQuerySpecification.instance();
  }
  
  public EPBSArchitecture__allocatedPhysicalArchitectureRealizationsMatcher getEPBSArchitecture__allocatedPhysicalArchitectureRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return EPBSArchitecture__allocatedPhysicalArchitectureRealizationsMatcher.on(engine);
  }
  
  public EPBSArchitecture__allocatedPhysicalArchitecturesQuerySpecification getEPBSArchitecture__allocatedPhysicalArchitectures() throws ViatraQueryException {
    return EPBSArchitecture__allocatedPhysicalArchitecturesQuerySpecification.instance();
  }
  
  public EPBSArchitecture__allocatedPhysicalArchitecturesMatcher getEPBSArchitecture__allocatedPhysicalArchitectures(final ViatraQueryEngine engine) throws ViatraQueryException {
    return EPBSArchitecture__allocatedPhysicalArchitecturesMatcher.on(engine);
  }
}
