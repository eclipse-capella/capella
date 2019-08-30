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
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Capability__involvedActorsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Capability__involvedSystemMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Capability__participatingActorsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Capability__participatingSystemMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Capability__purposeMissionsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Capability__purposesMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Capability__realizedOperationalCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Capability__realizingCapabilityRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Capability__involvedActorsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Capability__involvedSystemQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Capability__participatingActorsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Capability__participatingSystemQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Capability__purposeMissionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Capability__purposesQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Capability__realizedOperationalCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Capability__realizingCapabilityRealizationsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Capability.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Capability.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Capability__involvedActors</li>
 * <li>Capability__involvedSystem</li>
 * <li>Capability__participatingActors</li>
 * <li>Capability__participatingSystem</li>
 * <li>Capability__purposes</li>
 * <li>Capability__purposeMissions</li>
 * <li>Capability__realizedOperationalCapabilities</li>
 * <li>Capability__realizingCapabilityRealizations</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Capability extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Capability instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Capability();
    }
    return INSTANCE;
  }
  
  private static Capability INSTANCE;
  
  private Capability() throws ViatraQueryException {
    querySpecifications.add(Capability__involvedActorsQuerySpecification.instance());
    querySpecifications.add(Capability__involvedSystemQuerySpecification.instance());
    querySpecifications.add(Capability__participatingActorsQuerySpecification.instance());
    querySpecifications.add(Capability__participatingSystemQuerySpecification.instance());
    querySpecifications.add(Capability__purposesQuerySpecification.instance());
    querySpecifications.add(Capability__purposeMissionsQuerySpecification.instance());
    querySpecifications.add(Capability__realizedOperationalCapabilitiesQuerySpecification.instance());
    querySpecifications.add(Capability__realizingCapabilityRealizationsQuerySpecification.instance());
  }
  
  public Capability__involvedActorsQuerySpecification getCapability__involvedActors() throws ViatraQueryException {
    return Capability__involvedActorsQuerySpecification.instance();
  }
  
  public Capability__involvedActorsMatcher getCapability__involvedActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Capability__involvedActorsMatcher.on(engine);
  }
  
  public Capability__involvedSystemQuerySpecification getCapability__involvedSystem() throws ViatraQueryException {
    return Capability__involvedSystemQuerySpecification.instance();
  }
  
  public Capability__involvedSystemMatcher getCapability__involvedSystem(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Capability__involvedSystemMatcher.on(engine);
  }
  
  public Capability__participatingActorsQuerySpecification getCapability__participatingActors() throws ViatraQueryException {
    return Capability__participatingActorsQuerySpecification.instance();
  }
  
  public Capability__participatingActorsMatcher getCapability__participatingActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Capability__participatingActorsMatcher.on(engine);
  }
  
  public Capability__participatingSystemQuerySpecification getCapability__participatingSystem() throws ViatraQueryException {
    return Capability__participatingSystemQuerySpecification.instance();
  }
  
  public Capability__participatingSystemMatcher getCapability__participatingSystem(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Capability__participatingSystemMatcher.on(engine);
  }
  
  public Capability__purposesQuerySpecification getCapability__purposes() throws ViatraQueryException {
    return Capability__purposesQuerySpecification.instance();
  }
  
  public Capability__purposesMatcher getCapability__purposes(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Capability__purposesMatcher.on(engine);
  }
  
  public Capability__purposeMissionsQuerySpecification getCapability__purposeMissions() throws ViatraQueryException {
    return Capability__purposeMissionsQuerySpecification.instance();
  }
  
  public Capability__purposeMissionsMatcher getCapability__purposeMissions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Capability__purposeMissionsMatcher.on(engine);
  }
  
  public Capability__realizedOperationalCapabilitiesQuerySpecification getCapability__realizedOperationalCapabilities() throws ViatraQueryException {
    return Capability__realizedOperationalCapabilitiesQuerySpecification.instance();
  }
  
  public Capability__realizedOperationalCapabilitiesMatcher getCapability__realizedOperationalCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Capability__realizedOperationalCapabilitiesMatcher.on(engine);
  }
  
  public Capability__realizingCapabilityRealizationsQuerySpecification getCapability__realizingCapabilityRealizations() throws ViatraQueryException {
    return Capability__realizingCapabilityRealizationsQuerySpecification.instance();
  }
  
  public Capability__realizingCapabilityRealizationsMatcher getCapability__realizingCapabilityRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Capability__realizingCapabilityRealizationsMatcher.on(engine);
  }
}
