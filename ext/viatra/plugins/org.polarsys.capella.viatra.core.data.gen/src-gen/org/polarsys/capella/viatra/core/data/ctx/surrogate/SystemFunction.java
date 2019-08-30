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
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemFunction__allocatorActorsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemFunction__allocatorSystemsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemFunction__childrenSystemFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemFunction__containedSystemFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemFunction__realizedOperationalActivitiesMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemFunction__realizingLogicalFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.SystemFunction__allocatorActorsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.SystemFunction__allocatorSystemsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.SystemFunction__childrenSystemFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.SystemFunction__containedSystemFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.SystemFunction__realizedOperationalActivitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.SystemFunction__realizingLogicalFunctionsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in SystemFunction.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SystemFunction.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>SystemFunction__allocatorActors</li>
 * <li>SystemFunction__allocatorSystems</li>
 * <li>SystemFunction__realizedOperationalActivities</li>
 * <li>SystemFunction__realizingLogicalFunctions</li>
 * <li>SystemFunction__containedSystemFunctions</li>
 * <li>SystemFunction__childrenSystemFunctions</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class SystemFunction extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SystemFunction instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new SystemFunction();
    }
    return INSTANCE;
  }
  
  private static SystemFunction INSTANCE;
  
  private SystemFunction() throws ViatraQueryException {
    querySpecifications.add(SystemFunction__allocatorActorsQuerySpecification.instance());
    querySpecifications.add(SystemFunction__allocatorSystemsQuerySpecification.instance());
    querySpecifications.add(SystemFunction__realizedOperationalActivitiesQuerySpecification.instance());
    querySpecifications.add(SystemFunction__realizingLogicalFunctionsQuerySpecification.instance());
    querySpecifications.add(SystemFunction__containedSystemFunctionsQuerySpecification.instance());
    querySpecifications.add(SystemFunction__childrenSystemFunctionsQuerySpecification.instance());
  }
  
  public SystemFunction__allocatorActorsQuerySpecification getSystemFunction__allocatorActors() throws ViatraQueryException {
    return SystemFunction__allocatorActorsQuerySpecification.instance();
  }
  
  public SystemFunction__allocatorActorsMatcher getSystemFunction__allocatorActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemFunction__allocatorActorsMatcher.on(engine);
  }
  
  public SystemFunction__allocatorSystemsQuerySpecification getSystemFunction__allocatorSystems() throws ViatraQueryException {
    return SystemFunction__allocatorSystemsQuerySpecification.instance();
  }
  
  public SystemFunction__allocatorSystemsMatcher getSystemFunction__allocatorSystems(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemFunction__allocatorSystemsMatcher.on(engine);
  }
  
  public SystemFunction__realizedOperationalActivitiesQuerySpecification getSystemFunction__realizedOperationalActivities() throws ViatraQueryException {
    return SystemFunction__realizedOperationalActivitiesQuerySpecification.instance();
  }
  
  public SystemFunction__realizedOperationalActivitiesMatcher getSystemFunction__realizedOperationalActivities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemFunction__realizedOperationalActivitiesMatcher.on(engine);
  }
  
  public SystemFunction__realizingLogicalFunctionsQuerySpecification getSystemFunction__realizingLogicalFunctions() throws ViatraQueryException {
    return SystemFunction__realizingLogicalFunctionsQuerySpecification.instance();
  }
  
  public SystemFunction__realizingLogicalFunctionsMatcher getSystemFunction__realizingLogicalFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemFunction__realizingLogicalFunctionsMatcher.on(engine);
  }
  
  public SystemFunction__containedSystemFunctionsQuerySpecification getSystemFunction__containedSystemFunctions() throws ViatraQueryException {
    return SystemFunction__containedSystemFunctionsQuerySpecification.instance();
  }
  
  public SystemFunction__containedSystemFunctionsMatcher getSystemFunction__containedSystemFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemFunction__containedSystemFunctionsMatcher.on(engine);
  }
  
  public SystemFunction__childrenSystemFunctionsQuerySpecification getSystemFunction__childrenSystemFunctions() throws ViatraQueryException {
    return SystemFunction__childrenSystemFunctionsQuerySpecification.instance();
  }
  
  public SystemFunction__childrenSystemFunctionsMatcher getSystemFunction__childrenSystemFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemFunction__childrenSystemFunctionsMatcher.on(engine);
  }
}
