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
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__allocatedEntityRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__allocatedSystemFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__contributedCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__contributedMissionsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__participationsInCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__participationsInMissionsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__realizedEntitiesMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__realizingLogicalComponentsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.System__allocatedEntityRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.System__allocatedSystemFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.System__contributedCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.System__contributedMissionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.System__participationsInCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.System__participationsInMissionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.System__realizedEntitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.System__realizingLogicalComponentsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in System.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file System.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>System__contributedCapabilities</li>
 * <li>System__participationsInCapabilities</li>
 * <li>System__contributedMissions</li>
 * <li>System__participationsInMissions</li>
 * <li>System__allocatedEntityRealizations</li>
 * <li>System__allocatedSystemFunctions</li>
 * <li>System__realizedEntities</li>
 * <li>System__realizingLogicalComponents</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class System extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static System instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new System();
    }
    return INSTANCE;
  }
  
  private static System INSTANCE;
  
  private System() throws ViatraQueryException {
    querySpecifications.add(System__contributedCapabilitiesQuerySpecification.instance());
    querySpecifications.add(System__participationsInCapabilitiesQuerySpecification.instance());
    querySpecifications.add(System__contributedMissionsQuerySpecification.instance());
    querySpecifications.add(System__participationsInMissionsQuerySpecification.instance());
    querySpecifications.add(System__allocatedEntityRealizationsQuerySpecification.instance());
    querySpecifications.add(System__allocatedSystemFunctionsQuerySpecification.instance());
    querySpecifications.add(System__realizedEntitiesQuerySpecification.instance());
    querySpecifications.add(System__realizingLogicalComponentsQuerySpecification.instance());
  }
  
  public System__contributedCapabilitiesQuerySpecification getSystem__contributedCapabilities() throws ViatraQueryException {
    return System__contributedCapabilitiesQuerySpecification.instance();
  }
  
  public System__contributedCapabilitiesMatcher getSystem__contributedCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return System__contributedCapabilitiesMatcher.on(engine);
  }
  
  public System__participationsInCapabilitiesQuerySpecification getSystem__participationsInCapabilities() throws ViatraQueryException {
    return System__participationsInCapabilitiesQuerySpecification.instance();
  }
  
  public System__participationsInCapabilitiesMatcher getSystem__participationsInCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return System__participationsInCapabilitiesMatcher.on(engine);
  }
  
  public System__contributedMissionsQuerySpecification getSystem__contributedMissions() throws ViatraQueryException {
    return System__contributedMissionsQuerySpecification.instance();
  }
  
  public System__contributedMissionsMatcher getSystem__contributedMissions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return System__contributedMissionsMatcher.on(engine);
  }
  
  public System__participationsInMissionsQuerySpecification getSystem__participationsInMissions() throws ViatraQueryException {
    return System__participationsInMissionsQuerySpecification.instance();
  }
  
  public System__participationsInMissionsMatcher getSystem__participationsInMissions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return System__participationsInMissionsMatcher.on(engine);
  }
  
  public System__allocatedEntityRealizationsQuerySpecification getSystem__allocatedEntityRealizations() throws ViatraQueryException {
    return System__allocatedEntityRealizationsQuerySpecification.instance();
  }
  
  public System__allocatedEntityRealizationsMatcher getSystem__allocatedEntityRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return System__allocatedEntityRealizationsMatcher.on(engine);
  }
  
  public System__allocatedSystemFunctionsQuerySpecification getSystem__allocatedSystemFunctions() throws ViatraQueryException {
    return System__allocatedSystemFunctionsQuerySpecification.instance();
  }
  
  public System__allocatedSystemFunctionsMatcher getSystem__allocatedSystemFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return System__allocatedSystemFunctionsMatcher.on(engine);
  }
  
  public System__realizedEntitiesQuerySpecification getSystem__realizedEntities() throws ViatraQueryException {
    return System__realizedEntitiesQuerySpecification.instance();
  }
  
  public System__realizedEntitiesMatcher getSystem__realizedEntities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return System__realizedEntitiesMatcher.on(engine);
  }
  
  public System__realizingLogicalComponentsQuerySpecification getSystem__realizingLogicalComponents() throws ViatraQueryException {
    return System__realizingLogicalComponentsQuerySpecification.instance();
  }
  
  public System__realizingLogicalComponentsMatcher getSystem__realizingLogicalComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return System__realizingLogicalComponentsMatcher.on(engine);
  }
}
