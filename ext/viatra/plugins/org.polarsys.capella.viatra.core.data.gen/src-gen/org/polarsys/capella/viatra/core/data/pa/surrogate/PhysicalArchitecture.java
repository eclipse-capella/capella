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
package org.polarsys.capella.viatra.core.data.pa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalArchitecture__allocatedLogicalArchitectureRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalArchitecture__allocatedLogicalArchitecturesMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalArchitecture__allocatingEpbsArchitecturesMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalArchitecture__containedCapabilityRealizationPkgMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalArchitecture__containedPhysicalFunctionPkgMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalArchitecture__allocatedLogicalArchitectureRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalArchitecture__allocatedLogicalArchitecturesQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalArchitecture__allocatingEpbsArchitecturesQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalArchitecture__containedCapabilityRealizationPkgQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalArchitecture__containedPhysicalFunctionPkgQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in PhysicalArchitecture.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
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
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalArchitecture extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalArchitecture instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalArchitecture();
    }
    return INSTANCE;
  }
  
  private static PhysicalArchitecture INSTANCE;
  
  private PhysicalArchitecture() throws ViatraQueryException {
    querySpecifications.add(PhysicalArchitecture__containedCapabilityRealizationPkgQuerySpecification.instance());
    querySpecifications.add(PhysicalArchitecture__containedPhysicalFunctionPkgQuerySpecification.instance());
    querySpecifications.add(PhysicalArchitecture__allocatedLogicalArchitectureRealizationsQuerySpecification.instance());
    querySpecifications.add(PhysicalArchitecture__allocatedLogicalArchitecturesQuerySpecification.instance());
    querySpecifications.add(PhysicalArchitecture__allocatingEpbsArchitecturesQuerySpecification.instance());
  }
  
  public PhysicalArchitecture__containedCapabilityRealizationPkgQuerySpecification getPhysicalArchitecture__containedCapabilityRealizationPkg() throws ViatraQueryException {
    return PhysicalArchitecture__containedCapabilityRealizationPkgQuerySpecification.instance();
  }
  
  public PhysicalArchitecture__containedCapabilityRealizationPkgMatcher getPhysicalArchitecture__containedCapabilityRealizationPkg(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalArchitecture__containedCapabilityRealizationPkgMatcher.on(engine);
  }
  
  public PhysicalArchitecture__containedPhysicalFunctionPkgQuerySpecification getPhysicalArchitecture__containedPhysicalFunctionPkg() throws ViatraQueryException {
    return PhysicalArchitecture__containedPhysicalFunctionPkgQuerySpecification.instance();
  }
  
  public PhysicalArchitecture__containedPhysicalFunctionPkgMatcher getPhysicalArchitecture__containedPhysicalFunctionPkg(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalArchitecture__containedPhysicalFunctionPkgMatcher.on(engine);
  }
  
  public PhysicalArchitecture__allocatedLogicalArchitectureRealizationsQuerySpecification getPhysicalArchitecture__allocatedLogicalArchitectureRealizations() throws ViatraQueryException {
    return PhysicalArchitecture__allocatedLogicalArchitectureRealizationsQuerySpecification.instance();
  }
  
  public PhysicalArchitecture__allocatedLogicalArchitectureRealizationsMatcher getPhysicalArchitecture__allocatedLogicalArchitectureRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalArchitecture__allocatedLogicalArchitectureRealizationsMatcher.on(engine);
  }
  
  public PhysicalArchitecture__allocatedLogicalArchitecturesQuerySpecification getPhysicalArchitecture__allocatedLogicalArchitectures() throws ViatraQueryException {
    return PhysicalArchitecture__allocatedLogicalArchitecturesQuerySpecification.instance();
  }
  
  public PhysicalArchitecture__allocatedLogicalArchitecturesMatcher getPhysicalArchitecture__allocatedLogicalArchitectures(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalArchitecture__allocatedLogicalArchitecturesMatcher.on(engine);
  }
  
  public PhysicalArchitecture__allocatingEpbsArchitecturesQuerySpecification getPhysicalArchitecture__allocatingEpbsArchitectures() throws ViatraQueryException {
    return PhysicalArchitecture__allocatingEpbsArchitecturesQuerySpecification.instance();
  }
  
  public PhysicalArchitecture__allocatingEpbsArchitecturesMatcher getPhysicalArchitecture__allocatingEpbsArchitectures(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalArchitecture__allocatingEpbsArchitecturesMatcher.on(engine);
  }
}
