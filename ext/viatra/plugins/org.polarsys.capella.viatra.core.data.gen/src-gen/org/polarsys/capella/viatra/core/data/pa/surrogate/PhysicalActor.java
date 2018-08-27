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
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalActor__allocatedPhysicalFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalActor__deployedPhysicalComponentsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalActor__logicalActorRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalActor__realizedLogicalActorsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalActor__allocatedPhysicalFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalActor__deployedPhysicalComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalActor__logicalActorRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalActor__realizedLogicalActorsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in PhysicalActor.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalActor.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.pa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalActor__logicalActorRealizations</li>
 * <li>PhysicalActor__allocatedPhysicalFunctions</li>
 * <li>PhysicalActor__realizedLogicalActors</li>
 * <li>PhysicalActor__deployedPhysicalComponents</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalActor extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalActor instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalActor();
    }
    return INSTANCE;
  }
  
  private static PhysicalActor INSTANCE;
  
  private PhysicalActor() throws ViatraQueryException {
    querySpecifications.add(PhysicalActor__logicalActorRealizationsQuerySpecification.instance());
    querySpecifications.add(PhysicalActor__allocatedPhysicalFunctionsQuerySpecification.instance());
    querySpecifications.add(PhysicalActor__realizedLogicalActorsQuerySpecification.instance());
    querySpecifications.add(PhysicalActor__deployedPhysicalComponentsQuerySpecification.instance());
  }
  
  public PhysicalActor__logicalActorRealizationsQuerySpecification getPhysicalActor__logicalActorRealizations() throws ViatraQueryException {
    return PhysicalActor__logicalActorRealizationsQuerySpecification.instance();
  }
  
  public PhysicalActor__logicalActorRealizationsMatcher getPhysicalActor__logicalActorRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalActor__logicalActorRealizationsMatcher.on(engine);
  }
  
  public PhysicalActor__allocatedPhysicalFunctionsQuerySpecification getPhysicalActor__allocatedPhysicalFunctions() throws ViatraQueryException {
    return PhysicalActor__allocatedPhysicalFunctionsQuerySpecification.instance();
  }
  
  public PhysicalActor__allocatedPhysicalFunctionsMatcher getPhysicalActor__allocatedPhysicalFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalActor__allocatedPhysicalFunctionsMatcher.on(engine);
  }
  
  public PhysicalActor__realizedLogicalActorsQuerySpecification getPhysicalActor__realizedLogicalActors() throws ViatraQueryException {
    return PhysicalActor__realizedLogicalActorsQuerySpecification.instance();
  }
  
  public PhysicalActor__realizedLogicalActorsMatcher getPhysicalActor__realizedLogicalActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalActor__realizedLogicalActorsMatcher.on(engine);
  }
  
  public PhysicalActor__deployedPhysicalComponentsQuerySpecification getPhysicalActor__deployedPhysicalComponents() throws ViatraQueryException {
    return PhysicalActor__deployedPhysicalComponentsQuerySpecification.instance();
  }
  
  public PhysicalActor__deployedPhysicalComponentsMatcher getPhysicalActor__deployedPhysicalComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalActor__deployedPhysicalComponentsMatcher.on(engine);
  }
}
