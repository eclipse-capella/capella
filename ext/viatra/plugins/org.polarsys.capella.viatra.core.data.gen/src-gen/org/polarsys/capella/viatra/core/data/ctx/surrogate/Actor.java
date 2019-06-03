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
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__allocatedSystemFunctions;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__contributedCapabilities;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__contributedMissions;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__participationsInCapabilities;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__participationsInCapabilityRealizations;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__participationsInMissions;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__realizedEntities;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__realizedOperationalActors;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Actor__realizingLogicalActors;

/**
 * A pattern group formed of all public patterns defined in Actor.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Actor extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Actor instance() {
    if (INSTANCE == null) {
        INSTANCE = new Actor();
    }
    return INSTANCE;
  }
  
  private static Actor INSTANCE;
  
  private Actor() {
    querySpecifications.add(Actor__participationsInMissions.instance());
    querySpecifications.add(Actor__participationsInCapabilities.instance());
    querySpecifications.add(Actor__participationsInCapabilityRealizations.instance());
    querySpecifications.add(Actor__contributedMissions.instance());
    querySpecifications.add(Actor__contributedCapabilities.instance());
    querySpecifications.add(Actor__allocatedSystemFunctions.instance());
    querySpecifications.add(Actor__realizedEntities.instance());
    querySpecifications.add(Actor__realizedOperationalActors.instance());
    querySpecifications.add(Actor__realizingLogicalActors.instance());
  }
  
  public Actor__participationsInMissions getActor__participationsInMissions() {
    return Actor__participationsInMissions.instance();
  }
  
  public Actor__participationsInMissions.Matcher getActor__participationsInMissions(final ViatraQueryEngine engine) {
    return Actor__participationsInMissions.Matcher.on(engine);
  }
  
  public Actor__participationsInCapabilities getActor__participationsInCapabilities() {
    return Actor__participationsInCapabilities.instance();
  }
  
  public Actor__participationsInCapabilities.Matcher getActor__participationsInCapabilities(final ViatraQueryEngine engine) {
    return Actor__participationsInCapabilities.Matcher.on(engine);
  }
  
  public Actor__participationsInCapabilityRealizations getActor__participationsInCapabilityRealizations() {
    return Actor__participationsInCapabilityRealizations.instance();
  }
  
  public Actor__participationsInCapabilityRealizations.Matcher getActor__participationsInCapabilityRealizations(final ViatraQueryEngine engine) {
    return Actor__participationsInCapabilityRealizations.Matcher.on(engine);
  }
  
  public Actor__contributedMissions getActor__contributedMissions() {
    return Actor__contributedMissions.instance();
  }
  
  public Actor__contributedMissions.Matcher getActor__contributedMissions(final ViatraQueryEngine engine) {
    return Actor__contributedMissions.Matcher.on(engine);
  }
  
  public Actor__contributedCapabilities getActor__contributedCapabilities() {
    return Actor__contributedCapabilities.instance();
  }
  
  public Actor__contributedCapabilities.Matcher getActor__contributedCapabilities(final ViatraQueryEngine engine) {
    return Actor__contributedCapabilities.Matcher.on(engine);
  }
  
  public Actor__allocatedSystemFunctions getActor__allocatedSystemFunctions() {
    return Actor__allocatedSystemFunctions.instance();
  }
  
  public Actor__allocatedSystemFunctions.Matcher getActor__allocatedSystemFunctions(final ViatraQueryEngine engine) {
    return Actor__allocatedSystemFunctions.Matcher.on(engine);
  }
  
  public Actor__realizedEntities getActor__realizedEntities() {
    return Actor__realizedEntities.instance();
  }
  
  public Actor__realizedEntities.Matcher getActor__realizedEntities(final ViatraQueryEngine engine) {
    return Actor__realizedEntities.Matcher.on(engine);
  }
  
  public Actor__realizedOperationalActors getActor__realizedOperationalActors() {
    return Actor__realizedOperationalActors.instance();
  }
  
  public Actor__realizedOperationalActors.Matcher getActor__realizedOperationalActors(final ViatraQueryEngine engine) {
    return Actor__realizedOperationalActors.Matcher.on(engine);
  }
  
  public Actor__realizingLogicalActors getActor__realizingLogicalActors() {
    return Actor__realizingLogicalActors.instance();
  }
  
  public Actor__realizingLogicalActors.Matcher getActor__realizingLogicalActors(final ViatraQueryEngine engine) {
    return Actor__realizingLogicalActors.Matcher.on(engine);
  }
}
