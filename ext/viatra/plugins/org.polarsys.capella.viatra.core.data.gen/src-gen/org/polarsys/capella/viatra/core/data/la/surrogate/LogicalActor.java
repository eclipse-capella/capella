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
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalActor__allocatedLogicalFunctions;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalActor__participationsInCapabilityRealizations;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalActor__realizedSystemActors;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalActor__realizingPhysicalActors;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalActor__systemActorRealizations;

/**
 * A pattern group formed of all public patterns defined in LogicalActor.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class LogicalActor extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static LogicalActor instance() {
    if (INSTANCE == null) {
        INSTANCE = new LogicalActor();
    }
    return INSTANCE;
  }
  
  private static LogicalActor INSTANCE;
  
  private LogicalActor() {
    querySpecifications.add(LogicalActor__systemActorRealizations.instance());
    querySpecifications.add(LogicalActor__participationsInCapabilityRealizations.instance());
    querySpecifications.add(LogicalActor__allocatedLogicalFunctions.instance());
    querySpecifications.add(LogicalActor__realizedSystemActors.instance());
    querySpecifications.add(LogicalActor__realizingPhysicalActors.instance());
  }
  
  public LogicalActor__systemActorRealizations getLogicalActor__systemActorRealizations() {
    return LogicalActor__systemActorRealizations.instance();
  }
  
  public LogicalActor__systemActorRealizations.Matcher getLogicalActor__systemActorRealizations(final ViatraQueryEngine engine) {
    return LogicalActor__systemActorRealizations.Matcher.on(engine);
  }
  
  public LogicalActor__participationsInCapabilityRealizations getLogicalActor__participationsInCapabilityRealizations() {
    return LogicalActor__participationsInCapabilityRealizations.instance();
  }
  
  public LogicalActor__participationsInCapabilityRealizations.Matcher getLogicalActor__participationsInCapabilityRealizations(final ViatraQueryEngine engine) {
    return LogicalActor__participationsInCapabilityRealizations.Matcher.on(engine);
  }
  
  public LogicalActor__allocatedLogicalFunctions getLogicalActor__allocatedLogicalFunctions() {
    return LogicalActor__allocatedLogicalFunctions.instance();
  }
  
  public LogicalActor__allocatedLogicalFunctions.Matcher getLogicalActor__allocatedLogicalFunctions(final ViatraQueryEngine engine) {
    return LogicalActor__allocatedLogicalFunctions.Matcher.on(engine);
  }
  
  public LogicalActor__realizedSystemActors getLogicalActor__realizedSystemActors() {
    return LogicalActor__realizedSystemActors.instance();
  }
  
  public LogicalActor__realizedSystemActors.Matcher getLogicalActor__realizedSystemActors(final ViatraQueryEngine engine) {
    return LogicalActor__realizedSystemActors.Matcher.on(engine);
  }
  
  public LogicalActor__realizingPhysicalActors getLogicalActor__realizingPhysicalActors() {
    return LogicalActor__realizingPhysicalActors.instance();
  }
  
  public LogicalActor__realizingPhysicalActors.Matcher getLogicalActor__realizingPhysicalActors(final ViatraQueryEngine engine) {
    return LogicalActor__realizingPhysicalActors.Matcher.on(engine);
  }
}
