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
package org.polarsys.capella.viatra.core.data.la.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.la.surrogate.CapabilityRealization__involvedActorsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.CapabilityRealization__involvedSystemComponentsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.CapabilityRealization__participatingActorsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.CapabilityRealization__participatingSystemComponentsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.CapabilityRealization__realizedCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.CapabilityRealization__realizedCapabilityRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.CapabilityRealization__realizingCapabilityRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.CapabilityRealization__involvedActorsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.CapabilityRealization__involvedSystemComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.CapabilityRealization__participatingActorsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.CapabilityRealization__participatingSystemComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.CapabilityRealization__realizedCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.CapabilityRealization__realizedCapabilityRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.CapabilityRealization__realizingCapabilityRealizationsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in CapabilityRealization.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file CapabilityRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.la.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>CapabilityRealization__participatingActors</li>
 * <li>CapabilityRealization__participatingSystemComponents</li>
 * <li>CapabilityRealization__involvedActors</li>
 * <li>CapabilityRealization__involvedSystemComponents</li>
 * <li>CapabilityRealization__realizedCapabilities</li>
 * <li>CapabilityRealization__realizedCapabilityRealizations</li>
 * <li>CapabilityRealization__realizingCapabilityRealizations</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class CapabilityRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static CapabilityRealization instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new CapabilityRealization();
    }
    return INSTANCE;
  }
  
  private static CapabilityRealization INSTANCE;
  
  private CapabilityRealization() throws ViatraQueryException {
    querySpecifications.add(CapabilityRealization__participatingActorsQuerySpecification.instance());
    querySpecifications.add(CapabilityRealization__participatingSystemComponentsQuerySpecification.instance());
    querySpecifications.add(CapabilityRealization__involvedActorsQuerySpecification.instance());
    querySpecifications.add(CapabilityRealization__involvedSystemComponentsQuerySpecification.instance());
    querySpecifications.add(CapabilityRealization__realizedCapabilitiesQuerySpecification.instance());
    querySpecifications.add(CapabilityRealization__realizedCapabilityRealizationsQuerySpecification.instance());
    querySpecifications.add(CapabilityRealization__realizingCapabilityRealizationsQuerySpecification.instance());
  }
  
  public CapabilityRealization__participatingActorsQuerySpecification getCapabilityRealization__participatingActors() throws ViatraQueryException {
    return CapabilityRealization__participatingActorsQuerySpecification.instance();
  }
  
  public CapabilityRealization__participatingActorsMatcher getCapabilityRealization__participatingActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CapabilityRealization__participatingActorsMatcher.on(engine);
  }
  
  public CapabilityRealization__participatingSystemComponentsQuerySpecification getCapabilityRealization__participatingSystemComponents() throws ViatraQueryException {
    return CapabilityRealization__participatingSystemComponentsQuerySpecification.instance();
  }
  
  public CapabilityRealization__participatingSystemComponentsMatcher getCapabilityRealization__participatingSystemComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CapabilityRealization__participatingSystemComponentsMatcher.on(engine);
  }
  
  public CapabilityRealization__involvedActorsQuerySpecification getCapabilityRealization__involvedActors() throws ViatraQueryException {
    return CapabilityRealization__involvedActorsQuerySpecification.instance();
  }
  
  public CapabilityRealization__involvedActorsMatcher getCapabilityRealization__involvedActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CapabilityRealization__involvedActorsMatcher.on(engine);
  }
  
  public CapabilityRealization__involvedSystemComponentsQuerySpecification getCapabilityRealization__involvedSystemComponents() throws ViatraQueryException {
    return CapabilityRealization__involvedSystemComponentsQuerySpecification.instance();
  }
  
  public CapabilityRealization__involvedSystemComponentsMatcher getCapabilityRealization__involvedSystemComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CapabilityRealization__involvedSystemComponentsMatcher.on(engine);
  }
  
  public CapabilityRealization__realizedCapabilitiesQuerySpecification getCapabilityRealization__realizedCapabilities() throws ViatraQueryException {
    return CapabilityRealization__realizedCapabilitiesQuerySpecification.instance();
  }
  
  public CapabilityRealization__realizedCapabilitiesMatcher getCapabilityRealization__realizedCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CapabilityRealization__realizedCapabilitiesMatcher.on(engine);
  }
  
  public CapabilityRealization__realizedCapabilityRealizationsQuerySpecification getCapabilityRealization__realizedCapabilityRealizations() throws ViatraQueryException {
    return CapabilityRealization__realizedCapabilityRealizationsQuerySpecification.instance();
  }
  
  public CapabilityRealization__realizedCapabilityRealizationsMatcher getCapabilityRealization__realizedCapabilityRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CapabilityRealization__realizedCapabilityRealizationsMatcher.on(engine);
  }
  
  public CapabilityRealization__realizingCapabilityRealizationsQuerySpecification getCapabilityRealization__realizingCapabilityRealizations() throws ViatraQueryException {
    return CapabilityRealization__realizingCapabilityRealizationsQuerySpecification.instance();
  }
  
  public CapabilityRealization__realizingCapabilityRealizationsMatcher getCapabilityRealization__realizingCapabilityRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CapabilityRealization__realizingCapabilityRealizationsMatcher.on(engine);
  }
}
