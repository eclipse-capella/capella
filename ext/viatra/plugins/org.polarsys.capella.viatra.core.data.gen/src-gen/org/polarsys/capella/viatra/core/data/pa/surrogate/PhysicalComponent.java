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
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__allocatedPhysicalFunctions;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__deployedPhysicalComponents;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__deployingPhysicalActors;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__deployingPhysicalComponents;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__logicalComponentRealizations;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__logicalInterfaceRealizations;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__realizedLogicalComponents;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__subPhysicalComponents;

/**
 * A pattern group formed of all public patterns defined in PhysicalComponent.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalComponent extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalComponent instance() {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalComponent();
    }
    return INSTANCE;
  }
  
  private static PhysicalComponent INSTANCE;
  
  private PhysicalComponent() {
    querySpecifications.add(PhysicalComponent__logicalComponentRealizations.instance());
    querySpecifications.add(PhysicalComponent__logicalInterfaceRealizations.instance());
    querySpecifications.add(PhysicalComponent__subPhysicalComponents.instance());
    querySpecifications.add(PhysicalComponent__realizedLogicalComponents.instance());
    querySpecifications.add(PhysicalComponent__allocatedPhysicalFunctions.instance());
    querySpecifications.add(PhysicalComponent__deployedPhysicalComponents.instance());
    querySpecifications.add(PhysicalComponent__deployingPhysicalComponents.instance());
    querySpecifications.add(PhysicalComponent__deployingPhysicalActors.instance());
  }
  
  public PhysicalComponent__logicalComponentRealizations getPhysicalComponent__logicalComponentRealizations() {
    return PhysicalComponent__logicalComponentRealizations.instance();
  }
  
  public PhysicalComponent__logicalComponentRealizations.Matcher getPhysicalComponent__logicalComponentRealizations(final ViatraQueryEngine engine) {
    return PhysicalComponent__logicalComponentRealizations.Matcher.on(engine);
  }
  
  public PhysicalComponent__logicalInterfaceRealizations getPhysicalComponent__logicalInterfaceRealizations() {
    return PhysicalComponent__logicalInterfaceRealizations.instance();
  }
  
  public PhysicalComponent__logicalInterfaceRealizations.Matcher getPhysicalComponent__logicalInterfaceRealizations(final ViatraQueryEngine engine) {
    return PhysicalComponent__logicalInterfaceRealizations.Matcher.on(engine);
  }
  
  public PhysicalComponent__subPhysicalComponents getPhysicalComponent__subPhysicalComponents() {
    return PhysicalComponent__subPhysicalComponents.instance();
  }
  
  public PhysicalComponent__subPhysicalComponents.Matcher getPhysicalComponent__subPhysicalComponents(final ViatraQueryEngine engine) {
    return PhysicalComponent__subPhysicalComponents.Matcher.on(engine);
  }
  
  public PhysicalComponent__realizedLogicalComponents getPhysicalComponent__realizedLogicalComponents() {
    return PhysicalComponent__realizedLogicalComponents.instance();
  }
  
  public PhysicalComponent__realizedLogicalComponents.Matcher getPhysicalComponent__realizedLogicalComponents(final ViatraQueryEngine engine) {
    return PhysicalComponent__realizedLogicalComponents.Matcher.on(engine);
  }
  
  public PhysicalComponent__allocatedPhysicalFunctions getPhysicalComponent__allocatedPhysicalFunctions() {
    return PhysicalComponent__allocatedPhysicalFunctions.instance();
  }
  
  public PhysicalComponent__allocatedPhysicalFunctions.Matcher getPhysicalComponent__allocatedPhysicalFunctions(final ViatraQueryEngine engine) {
    return PhysicalComponent__allocatedPhysicalFunctions.Matcher.on(engine);
  }
  
  public PhysicalComponent__deployedPhysicalComponents getPhysicalComponent__deployedPhysicalComponents() {
    return PhysicalComponent__deployedPhysicalComponents.instance();
  }
  
  public PhysicalComponent__deployedPhysicalComponents.Matcher getPhysicalComponent__deployedPhysicalComponents(final ViatraQueryEngine engine) {
    return PhysicalComponent__deployedPhysicalComponents.Matcher.on(engine);
  }
  
  public PhysicalComponent__deployingPhysicalComponents getPhysicalComponent__deployingPhysicalComponents() {
    return PhysicalComponent__deployingPhysicalComponents.instance();
  }
  
  public PhysicalComponent__deployingPhysicalComponents.Matcher getPhysicalComponent__deployingPhysicalComponents(final ViatraQueryEngine engine) {
    return PhysicalComponent__deployingPhysicalComponents.Matcher.on(engine);
  }
  
  public PhysicalComponent__deployingPhysicalActors getPhysicalComponent__deployingPhysicalActors() {
    return PhysicalComponent__deployingPhysicalActors.instance();
  }
  
  public PhysicalComponent__deployingPhysicalActors.Matcher getPhysicalComponent__deployingPhysicalActors(final ViatraQueryEngine engine) {
    return PhysicalComponent__deployingPhysicalActors.Matcher.on(engine);
  }
}
