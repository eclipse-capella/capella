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
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Mission__exploitedCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Mission__involvedActorsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Mission__involvedSystemMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Mission__participatingActorsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Mission__participatingSystemMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Mission__exploitedCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Mission__involvedActorsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Mission__involvedSystemQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Mission__participatingActorsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.Mission__participatingSystemQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Mission.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Mission.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Mission__participatingActors</li>
 * <li>Mission__participatingSystem</li>
 * <li>Mission__involvedActors</li>
 * <li>Mission__involvedSystem</li>
 * <li>Mission__exploitedCapabilities</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Mission extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Mission instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Mission();
    }
    return INSTANCE;
  }
  
  private static Mission INSTANCE;
  
  private Mission() throws ViatraQueryException {
    querySpecifications.add(Mission__participatingActorsQuerySpecification.instance());
    querySpecifications.add(Mission__participatingSystemQuerySpecification.instance());
    querySpecifications.add(Mission__involvedActorsQuerySpecification.instance());
    querySpecifications.add(Mission__involvedSystemQuerySpecification.instance());
    querySpecifications.add(Mission__exploitedCapabilitiesQuerySpecification.instance());
  }
  
  public Mission__participatingActorsQuerySpecification getMission__participatingActors() throws ViatraQueryException {
    return Mission__participatingActorsQuerySpecification.instance();
  }
  
  public Mission__participatingActorsMatcher getMission__participatingActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Mission__participatingActorsMatcher.on(engine);
  }
  
  public Mission__participatingSystemQuerySpecification getMission__participatingSystem() throws ViatraQueryException {
    return Mission__participatingSystemQuerySpecification.instance();
  }
  
  public Mission__participatingSystemMatcher getMission__participatingSystem(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Mission__participatingSystemMatcher.on(engine);
  }
  
  public Mission__involvedActorsQuerySpecification getMission__involvedActors() throws ViatraQueryException {
    return Mission__involvedActorsQuerySpecification.instance();
  }
  
  public Mission__involvedActorsMatcher getMission__involvedActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Mission__involvedActorsMatcher.on(engine);
  }
  
  public Mission__involvedSystemQuerySpecification getMission__involvedSystem() throws ViatraQueryException {
    return Mission__involvedSystemQuerySpecification.instance();
  }
  
  public Mission__involvedSystemMatcher getMission__involvedSystem(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Mission__involvedSystemMatcher.on(engine);
  }
  
  public Mission__exploitedCapabilitiesQuerySpecification getMission__exploitedCapabilities() throws ViatraQueryException {
    return Mission__exploitedCapabilitiesQuerySpecification.instance();
  }
  
  public Mission__exploitedCapabilitiesMatcher getMission__exploitedCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Mission__exploitedCapabilitiesMatcher.on(engine);
  }
}
