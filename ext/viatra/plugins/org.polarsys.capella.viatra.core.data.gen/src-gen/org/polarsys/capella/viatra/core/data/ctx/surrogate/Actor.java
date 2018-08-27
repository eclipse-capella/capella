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
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__allocatedSystemFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__contributedCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__contributedMissionsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__participationsInCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__participationsInCapabilityRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__participationsInMissionsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__realizedEntitiesMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__realizedOperationalActorsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__realizingLogicalActorsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Actor__allocatedSystemFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Actor__contributedCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Actor__contributedMissionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Actor__participationsInCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Actor__participationsInCapabilityRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Actor__participationsInMissionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Actor__realizedEntitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Actor__realizedOperationalActorsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Actor__realizingLogicalActorsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Actor.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Actor.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Actor__participationsInMissions</li>
 * <li>Actor__participationsInCapabilities</li>
 * <li>Actor__participationsInCapabilityRealizations</li>
 * <li>Actor__contributedMissions</li>
 * <li>Actor__contributedCapabilities</li>
 * <li>Actor__allocatedSystemFunctions</li>
 * <li>Actor__realizedEntities</li>
 * <li>Actor__realizedOperationalActors</li>
 * <li>Actor__realizingLogicalActors</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Actor extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Actor instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Actor();
    }
    return INSTANCE;
  }
  
  private static Actor INSTANCE;
  
  private Actor() throws ViatraQueryException {
    querySpecifications.add(Actor__participationsInMissionsQuerySpecification.instance());
    querySpecifications.add(Actor__participationsInCapabilitiesQuerySpecification.instance());
    querySpecifications.add(Actor__participationsInCapabilityRealizationsQuerySpecification.instance());
    querySpecifications.add(Actor__contributedMissionsQuerySpecification.instance());
    querySpecifications.add(Actor__contributedCapabilitiesQuerySpecification.instance());
    querySpecifications.add(Actor__allocatedSystemFunctionsQuerySpecification.instance());
    querySpecifications.add(Actor__realizedEntitiesQuerySpecification.instance());
    querySpecifications.add(Actor__realizedOperationalActorsQuerySpecification.instance());
    querySpecifications.add(Actor__realizingLogicalActorsQuerySpecification.instance());
  }
  
  public Actor__participationsInMissionsQuerySpecification getActor__participationsInMissions() throws ViatraQueryException {
    return Actor__participationsInMissionsQuerySpecification.instance();
  }
  
  public Actor__participationsInMissionsMatcher getActor__participationsInMissions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Actor__participationsInMissionsMatcher.on(engine);
  }
  
  public Actor__participationsInCapabilitiesQuerySpecification getActor__participationsInCapabilities() throws ViatraQueryException {
    return Actor__participationsInCapabilitiesQuerySpecification.instance();
  }
  
  public Actor__participationsInCapabilitiesMatcher getActor__participationsInCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Actor__participationsInCapabilitiesMatcher.on(engine);
  }
  
  public Actor__participationsInCapabilityRealizationsQuerySpecification getActor__participationsInCapabilityRealizations() throws ViatraQueryException {
    return Actor__participationsInCapabilityRealizationsQuerySpecification.instance();
  }
  
  public Actor__participationsInCapabilityRealizationsMatcher getActor__participationsInCapabilityRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Actor__participationsInCapabilityRealizationsMatcher.on(engine);
  }
  
  public Actor__contributedMissionsQuerySpecification getActor__contributedMissions() throws ViatraQueryException {
    return Actor__contributedMissionsQuerySpecification.instance();
  }
  
  public Actor__contributedMissionsMatcher getActor__contributedMissions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Actor__contributedMissionsMatcher.on(engine);
  }
  
  public Actor__contributedCapabilitiesQuerySpecification getActor__contributedCapabilities() throws ViatraQueryException {
    return Actor__contributedCapabilitiesQuerySpecification.instance();
  }
  
  public Actor__contributedCapabilitiesMatcher getActor__contributedCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Actor__contributedCapabilitiesMatcher.on(engine);
  }
  
  public Actor__allocatedSystemFunctionsQuerySpecification getActor__allocatedSystemFunctions() throws ViatraQueryException {
    return Actor__allocatedSystemFunctionsQuerySpecification.instance();
  }
  
  public Actor__allocatedSystemFunctionsMatcher getActor__allocatedSystemFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Actor__allocatedSystemFunctionsMatcher.on(engine);
  }
  
  public Actor__realizedEntitiesQuerySpecification getActor__realizedEntities() throws ViatraQueryException {
    return Actor__realizedEntitiesQuerySpecification.instance();
  }
  
  public Actor__realizedEntitiesMatcher getActor__realizedEntities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Actor__realizedEntitiesMatcher.on(engine);
  }
  
  public Actor__realizedOperationalActorsQuerySpecification getActor__realizedOperationalActors() throws ViatraQueryException {
    return Actor__realizedOperationalActorsQuerySpecification.instance();
  }
  
  public Actor__realizedOperationalActorsMatcher getActor__realizedOperationalActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Actor__realizedOperationalActorsMatcher.on(engine);
  }
  
  public Actor__realizingLogicalActorsQuerySpecification getActor__realizingLogicalActors() throws ViatraQueryException {
    return Actor__realizingLogicalActorsQuerySpecification.instance();
  }
  
  public Actor__realizingLogicalActorsMatcher getActor__realizingLogicalActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Actor__realizingLogicalActorsMatcher.on(engine);
  }
}
