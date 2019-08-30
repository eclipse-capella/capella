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
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__allocatedPhysicalFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__deployedPhysicalComponentsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__deployingPhysicalActorsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__deployingPhysicalComponentsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__logicalComponentRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__logicalInterfaceRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__realizedLogicalComponentsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__subPhysicalComponentsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalComponent__allocatedPhysicalFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalComponent__deployedPhysicalComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalComponent__deployingPhysicalActorsQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalComponent__deployingPhysicalComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalComponent__logicalComponentRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalComponent__logicalInterfaceRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalComponent__realizedLogicalComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalComponent__subPhysicalComponentsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in PhysicalComponent.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalComponent.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.pa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalComponent__logicalComponentRealizations</li>
 * <li>PhysicalComponent__logicalInterfaceRealizations</li>
 * <li>PhysicalComponent__subPhysicalComponents</li>
 * <li>PhysicalComponent__realizedLogicalComponents</li>
 * <li>PhysicalComponent__allocatedPhysicalFunctions</li>
 * <li>PhysicalComponent__deployedPhysicalComponents</li>
 * <li>PhysicalComponent__deployingPhysicalComponents</li>
 * <li>PhysicalComponent__deployingPhysicalActors</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalComponent extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalComponent instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalComponent();
    }
    return INSTANCE;
  }
  
  private static PhysicalComponent INSTANCE;
  
  private PhysicalComponent() throws ViatraQueryException {
    querySpecifications.add(PhysicalComponent__logicalComponentRealizationsQuerySpecification.instance());
    querySpecifications.add(PhysicalComponent__logicalInterfaceRealizationsQuerySpecification.instance());
    querySpecifications.add(PhysicalComponent__subPhysicalComponentsQuerySpecification.instance());
    querySpecifications.add(PhysicalComponent__realizedLogicalComponentsQuerySpecification.instance());
    querySpecifications.add(PhysicalComponent__allocatedPhysicalFunctionsQuerySpecification.instance());
    querySpecifications.add(PhysicalComponent__deployedPhysicalComponentsQuerySpecification.instance());
    querySpecifications.add(PhysicalComponent__deployingPhysicalComponentsQuerySpecification.instance());
    querySpecifications.add(PhysicalComponent__deployingPhysicalActorsQuerySpecification.instance());
  }
  
  public PhysicalComponent__logicalComponentRealizationsQuerySpecification getPhysicalComponent__logicalComponentRealizations() throws ViatraQueryException {
    return PhysicalComponent__logicalComponentRealizationsQuerySpecification.instance();
  }
  
  public PhysicalComponent__logicalComponentRealizationsMatcher getPhysicalComponent__logicalComponentRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalComponent__logicalComponentRealizationsMatcher.on(engine);
  }
  
  public PhysicalComponent__logicalInterfaceRealizationsQuerySpecification getPhysicalComponent__logicalInterfaceRealizations() throws ViatraQueryException {
    return PhysicalComponent__logicalInterfaceRealizationsQuerySpecification.instance();
  }
  
  public PhysicalComponent__logicalInterfaceRealizationsMatcher getPhysicalComponent__logicalInterfaceRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalComponent__logicalInterfaceRealizationsMatcher.on(engine);
  }
  
  public PhysicalComponent__subPhysicalComponentsQuerySpecification getPhysicalComponent__subPhysicalComponents() throws ViatraQueryException {
    return PhysicalComponent__subPhysicalComponentsQuerySpecification.instance();
  }
  
  public PhysicalComponent__subPhysicalComponentsMatcher getPhysicalComponent__subPhysicalComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalComponent__subPhysicalComponentsMatcher.on(engine);
  }
  
  public PhysicalComponent__realizedLogicalComponentsQuerySpecification getPhysicalComponent__realizedLogicalComponents() throws ViatraQueryException {
    return PhysicalComponent__realizedLogicalComponentsQuerySpecification.instance();
  }
  
  public PhysicalComponent__realizedLogicalComponentsMatcher getPhysicalComponent__realizedLogicalComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalComponent__realizedLogicalComponentsMatcher.on(engine);
  }
  
  public PhysicalComponent__allocatedPhysicalFunctionsQuerySpecification getPhysicalComponent__allocatedPhysicalFunctions() throws ViatraQueryException {
    return PhysicalComponent__allocatedPhysicalFunctionsQuerySpecification.instance();
  }
  
  public PhysicalComponent__allocatedPhysicalFunctionsMatcher getPhysicalComponent__allocatedPhysicalFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalComponent__allocatedPhysicalFunctionsMatcher.on(engine);
  }
  
  public PhysicalComponent__deployedPhysicalComponentsQuerySpecification getPhysicalComponent__deployedPhysicalComponents() throws ViatraQueryException {
    return PhysicalComponent__deployedPhysicalComponentsQuerySpecification.instance();
  }
  
  public PhysicalComponent__deployedPhysicalComponentsMatcher getPhysicalComponent__deployedPhysicalComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalComponent__deployedPhysicalComponentsMatcher.on(engine);
  }
  
  public PhysicalComponent__deployingPhysicalComponentsQuerySpecification getPhysicalComponent__deployingPhysicalComponents() throws ViatraQueryException {
    return PhysicalComponent__deployingPhysicalComponentsQuerySpecification.instance();
  }
  
  public PhysicalComponent__deployingPhysicalComponentsMatcher getPhysicalComponent__deployingPhysicalComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalComponent__deployingPhysicalComponentsMatcher.on(engine);
  }
  
  public PhysicalComponent__deployingPhysicalActorsQuerySpecification getPhysicalComponent__deployingPhysicalActors() throws ViatraQueryException {
    return PhysicalComponent__deployingPhysicalActorsQuerySpecification.instance();
  }
  
  public PhysicalComponent__deployingPhysicalActorsMatcher getPhysicalComponent__deployingPhysicalActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalComponent__deployingPhysicalActorsMatcher.on(engine);
  }
}
