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
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalActor__allocatedLogicalFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalActor__participationsInCapabilityRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalActor__realizedSystemActorsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalActor__realizingPhysicalActorsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalActor__systemActorRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalActor__allocatedLogicalFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalActor__participationsInCapabilityRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalActor__realizedSystemActorsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalActor__realizingPhysicalActorsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalActor__systemActorRealizationsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in LogicalActor.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file LogicalActor.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.la.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>LogicalActor__systemActorRealizations</li>
 * <li>LogicalActor__participationsInCapabilityRealizations</li>
 * <li>LogicalActor__allocatedLogicalFunctions</li>
 * <li>LogicalActor__realizedSystemActors</li>
 * <li>LogicalActor__realizingPhysicalActors</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class LogicalActor extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static LogicalActor instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new LogicalActor();
    }
    return INSTANCE;
  }
  
  private static LogicalActor INSTANCE;
  
  private LogicalActor() throws ViatraQueryException {
    querySpecifications.add(LogicalActor__systemActorRealizationsQuerySpecification.instance());
    querySpecifications.add(LogicalActor__participationsInCapabilityRealizationsQuerySpecification.instance());
    querySpecifications.add(LogicalActor__allocatedLogicalFunctionsQuerySpecification.instance());
    querySpecifications.add(LogicalActor__realizedSystemActorsQuerySpecification.instance());
    querySpecifications.add(LogicalActor__realizingPhysicalActorsQuerySpecification.instance());
  }
  
  public LogicalActor__systemActorRealizationsQuerySpecification getLogicalActor__systemActorRealizations() throws ViatraQueryException {
    return LogicalActor__systemActorRealizationsQuerySpecification.instance();
  }
  
  public LogicalActor__systemActorRealizationsMatcher getLogicalActor__systemActorRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalActor__systemActorRealizationsMatcher.on(engine);
  }
  
  public LogicalActor__participationsInCapabilityRealizationsQuerySpecification getLogicalActor__participationsInCapabilityRealizations() throws ViatraQueryException {
    return LogicalActor__participationsInCapabilityRealizationsQuerySpecification.instance();
  }
  
  public LogicalActor__participationsInCapabilityRealizationsMatcher getLogicalActor__participationsInCapabilityRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalActor__participationsInCapabilityRealizationsMatcher.on(engine);
  }
  
  public LogicalActor__allocatedLogicalFunctionsQuerySpecification getLogicalActor__allocatedLogicalFunctions() throws ViatraQueryException {
    return LogicalActor__allocatedLogicalFunctionsQuerySpecification.instance();
  }
  
  public LogicalActor__allocatedLogicalFunctionsMatcher getLogicalActor__allocatedLogicalFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalActor__allocatedLogicalFunctionsMatcher.on(engine);
  }
  
  public LogicalActor__realizedSystemActorsQuerySpecification getLogicalActor__realizedSystemActors() throws ViatraQueryException {
    return LogicalActor__realizedSystemActorsQuerySpecification.instance();
  }
  
  public LogicalActor__realizedSystemActorsMatcher getLogicalActor__realizedSystemActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalActor__realizedSystemActorsMatcher.on(engine);
  }
  
  public LogicalActor__realizingPhysicalActorsQuerySpecification getLogicalActor__realizingPhysicalActors() throws ViatraQueryException {
    return LogicalActor__realizingPhysicalActorsQuerySpecification.instance();
  }
  
  public LogicalActor__realizingPhysicalActorsMatcher getLogicalActor__realizingPhysicalActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalActor__realizingPhysicalActorsMatcher.on(engine);
  }
}
