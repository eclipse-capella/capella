/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
 *   
 *   This program and the accompanying materials are made available under the
 *   terms of the Eclipse Public License 2.0 which is available at
 *   http://www.eclipse.org/legal/epl-2.0
 *   
 *   SPDX-License-Identifier: EPL-2.0
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.core.data.pa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalFunction__allocatingPhysicalComponents;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalFunction__childrenPhysicalFunctions;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalFunction__containedPhysicalFunctions;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalFunction__realizedLogicalFunctions;

/**
 * A pattern group formed of all public patterns defined in PhysicalFunction.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalFunction.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.pa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalFunction__realizedLogicalFunctions</li>
 * <li>PhysicalFunction__containedPhysicalFunctions</li>
 * <li>PhysicalFunction__childrenPhysicalFunctions</li>
 * <li>PhysicalFunction__allocatingPhysicalComponents</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalFunction extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalFunction instance() {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalFunction();
    }
    return INSTANCE;
  }
  
  private static PhysicalFunction INSTANCE;
  
  private PhysicalFunction() {
    querySpecifications.add(PhysicalFunction__realizedLogicalFunctions.instance());
    querySpecifications.add(PhysicalFunction__containedPhysicalFunctions.instance());
    querySpecifications.add(PhysicalFunction__childrenPhysicalFunctions.instance());
    querySpecifications.add(PhysicalFunction__allocatingPhysicalComponents.instance());
  }
  
  public PhysicalFunction__realizedLogicalFunctions getPhysicalFunction__realizedLogicalFunctions() {
    return PhysicalFunction__realizedLogicalFunctions.instance();
  }
  
  public PhysicalFunction__realizedLogicalFunctions.Matcher getPhysicalFunction__realizedLogicalFunctions(final ViatraQueryEngine engine) {
    return PhysicalFunction__realizedLogicalFunctions.Matcher.on(engine);
  }
  
  public PhysicalFunction__containedPhysicalFunctions getPhysicalFunction__containedPhysicalFunctions() {
    return PhysicalFunction__containedPhysicalFunctions.instance();
  }
  
  public PhysicalFunction__containedPhysicalFunctions.Matcher getPhysicalFunction__containedPhysicalFunctions(final ViatraQueryEngine engine) {
    return PhysicalFunction__containedPhysicalFunctions.Matcher.on(engine);
  }
  
  public PhysicalFunction__childrenPhysicalFunctions getPhysicalFunction__childrenPhysicalFunctions() {
    return PhysicalFunction__childrenPhysicalFunctions.instance();
  }
  
  public PhysicalFunction__childrenPhysicalFunctions.Matcher getPhysicalFunction__childrenPhysicalFunctions(final ViatraQueryEngine engine) {
    return PhysicalFunction__childrenPhysicalFunctions.Matcher.on(engine);
  }
  
  public PhysicalFunction__allocatingPhysicalComponents getPhysicalFunction__allocatingPhysicalComponents() {
    return PhysicalFunction__allocatingPhysicalComponents.instance();
  }
  
  public PhysicalFunction__allocatingPhysicalComponents.Matcher getPhysicalFunction__allocatingPhysicalComponents(final ViatraQueryEngine engine) {
    return PhysicalFunction__allocatingPhysicalComponents.Matcher.on(engine);
  }
}
