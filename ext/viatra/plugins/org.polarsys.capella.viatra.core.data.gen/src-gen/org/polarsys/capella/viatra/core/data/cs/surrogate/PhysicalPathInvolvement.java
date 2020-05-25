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
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPathInvolvement__involvedComponent;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPathInvolvement__involvedElement;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPathInvolvement__previousInvolvements;

/**
 * A pattern group formed of all public patterns defined in PhysicalPathInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalPathInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalPathInvolvement__previousInvolvements</li>
 * <li>PhysicalPathInvolvement__involvedElement</li>
 * <li>PhysicalPathInvolvement__involvedComponent</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalPathInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalPathInvolvement instance() {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalPathInvolvement();
    }
    return INSTANCE;
  }
  
  private static PhysicalPathInvolvement INSTANCE;
  
  private PhysicalPathInvolvement() {
    querySpecifications.add(PhysicalPathInvolvement__previousInvolvements.instance());
    querySpecifications.add(PhysicalPathInvolvement__involvedElement.instance());
    querySpecifications.add(PhysicalPathInvolvement__involvedComponent.instance());
  }
  
  public PhysicalPathInvolvement__previousInvolvements getPhysicalPathInvolvement__previousInvolvements() {
    return PhysicalPathInvolvement__previousInvolvements.instance();
  }
  
  public PhysicalPathInvolvement__previousInvolvements.Matcher getPhysicalPathInvolvement__previousInvolvements(final ViatraQueryEngine engine) {
    return PhysicalPathInvolvement__previousInvolvements.Matcher.on(engine);
  }
  
  public PhysicalPathInvolvement__involvedElement getPhysicalPathInvolvement__involvedElement() {
    return PhysicalPathInvolvement__involvedElement.instance();
  }
  
  public PhysicalPathInvolvement__involvedElement.Matcher getPhysicalPathInvolvement__involvedElement(final ViatraQueryEngine engine) {
    return PhysicalPathInvolvement__involvedElement.Matcher.on(engine);
  }
  
  public PhysicalPathInvolvement__involvedComponent getPhysicalPathInvolvement__involvedComponent() {
    return PhysicalPathInvolvement__involvedComponent.instance();
  }
  
  public PhysicalPathInvolvement__involvedComponent.Matcher getPhysicalPathInvolvement__involvedComponent(final ViatraQueryEngine engine) {
    return PhysicalPathInvolvement__involvedComponent.Matcher.on(engine);
  }
}
