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
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPathInvolvement__involvedComponentMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPathInvolvement__involvedElementMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPathInvolvement__previousInvolvementsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalPathInvolvement__involvedComponentQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalPathInvolvement__involvedElementQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalPathInvolvement__previousInvolvementsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in PhysicalPathInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalPathInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalPathInvolvement__previousInvolvements</li>
 * <li>PhysicalPathInvolvement__involvedElement</li>
 * <li>PhysicalPathInvolvement__involvedComponent</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalPathInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalPathInvolvement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalPathInvolvement();
    }
    return INSTANCE;
  }
  
  private static PhysicalPathInvolvement INSTANCE;
  
  private PhysicalPathInvolvement() throws ViatraQueryException {
    querySpecifications.add(PhysicalPathInvolvement__previousInvolvementsQuerySpecification.instance());
    querySpecifications.add(PhysicalPathInvolvement__involvedElementQuerySpecification.instance());
    querySpecifications.add(PhysicalPathInvolvement__involvedComponentQuerySpecification.instance());
  }
  
  public PhysicalPathInvolvement__previousInvolvementsQuerySpecification getPhysicalPathInvolvement__previousInvolvements() throws ViatraQueryException {
    return PhysicalPathInvolvement__previousInvolvementsQuerySpecification.instance();
  }
  
  public PhysicalPathInvolvement__previousInvolvementsMatcher getPhysicalPathInvolvement__previousInvolvements(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalPathInvolvement__previousInvolvementsMatcher.on(engine);
  }
  
  public PhysicalPathInvolvement__involvedElementQuerySpecification getPhysicalPathInvolvement__involvedElement() throws ViatraQueryException {
    return PhysicalPathInvolvement__involvedElementQuerySpecification.instance();
  }
  
  public PhysicalPathInvolvement__involvedElementMatcher getPhysicalPathInvolvement__involvedElement(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalPathInvolvement__involvedElementMatcher.on(engine);
  }
  
  public PhysicalPathInvolvement__involvedComponentQuerySpecification getPhysicalPathInvolvement__involvedComponent() throws ViatraQueryException {
    return PhysicalPathInvolvement__involvedComponentQuerySpecification.instance();
  }
  
  public PhysicalPathInvolvement__involvedComponentMatcher getPhysicalPathInvolvement__involvedComponent(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalPathInvolvement__involvedComponentMatcher.on(engine);
  }
}
