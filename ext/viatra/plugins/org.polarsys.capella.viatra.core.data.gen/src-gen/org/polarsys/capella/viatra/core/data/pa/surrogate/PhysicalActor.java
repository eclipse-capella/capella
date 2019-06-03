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
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalActor__allocatedPhysicalFunctions;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalActor__deployedPhysicalComponents;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalActor__logicalActorRealizations;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalActor__realizedLogicalActors;

/**
 * A pattern group formed of all public patterns defined in PhysicalActor.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalActor extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalActor instance() {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalActor();
    }
    return INSTANCE;
  }
  
  private static PhysicalActor INSTANCE;
  
  private PhysicalActor() {
    querySpecifications.add(PhysicalActor__logicalActorRealizations.instance());
    querySpecifications.add(PhysicalActor__allocatedPhysicalFunctions.instance());
    querySpecifications.add(PhysicalActor__realizedLogicalActors.instance());
    querySpecifications.add(PhysicalActor__deployedPhysicalComponents.instance());
  }
  
  public PhysicalActor__logicalActorRealizations getPhysicalActor__logicalActorRealizations() {
    return PhysicalActor__logicalActorRealizations.instance();
  }
  
  public PhysicalActor__logicalActorRealizations.Matcher getPhysicalActor__logicalActorRealizations(final ViatraQueryEngine engine) {
    return PhysicalActor__logicalActorRealizations.Matcher.on(engine);
  }
  
  public PhysicalActor__allocatedPhysicalFunctions getPhysicalActor__allocatedPhysicalFunctions() {
    return PhysicalActor__allocatedPhysicalFunctions.instance();
  }
  
  public PhysicalActor__allocatedPhysicalFunctions.Matcher getPhysicalActor__allocatedPhysicalFunctions(final ViatraQueryEngine engine) {
    return PhysicalActor__allocatedPhysicalFunctions.Matcher.on(engine);
  }
  
  public PhysicalActor__realizedLogicalActors getPhysicalActor__realizedLogicalActors() {
    return PhysicalActor__realizedLogicalActors.instance();
  }
  
  public PhysicalActor__realizedLogicalActors.Matcher getPhysicalActor__realizedLogicalActors(final ViatraQueryEngine engine) {
    return PhysicalActor__realizedLogicalActors.Matcher.on(engine);
  }
  
  public PhysicalActor__deployedPhysicalComponents getPhysicalActor__deployedPhysicalComponents() {
    return PhysicalActor__deployedPhysicalComponents.instance();
  }
  
  public PhysicalActor__deployedPhysicalComponents.Matcher getPhysicalActor__deployedPhysicalComponents(final ViatraQueryEngine engine) {
    return PhysicalActor__deployedPhysicalComponents.Matcher.on(engine);
  }
}
