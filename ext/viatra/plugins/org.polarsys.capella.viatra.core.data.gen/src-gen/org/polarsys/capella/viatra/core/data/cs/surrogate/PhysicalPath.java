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
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPath__firstPhysicalPathInvolvementsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPath__realizedPhysicalPathsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPath__realizingPhysicalPathsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalPath__firstPhysicalPathInvolvementsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalPath__realizedPhysicalPathsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalPath__realizingPhysicalPathsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in PhysicalPath.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalPath.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalPath__firstPhysicalPathInvolvements</li>
 * <li>PhysicalPath__realizedPhysicalPaths</li>
 * <li>PhysicalPath__realizingPhysicalPaths</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalPath extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalPath instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalPath();
    }
    return INSTANCE;
  }
  
  private static PhysicalPath INSTANCE;
  
  private PhysicalPath() throws ViatraQueryException {
    querySpecifications.add(PhysicalPath__firstPhysicalPathInvolvementsQuerySpecification.instance());
    querySpecifications.add(PhysicalPath__realizedPhysicalPathsQuerySpecification.instance());
    querySpecifications.add(PhysicalPath__realizingPhysicalPathsQuerySpecification.instance());
  }
  
  public PhysicalPath__firstPhysicalPathInvolvementsQuerySpecification getPhysicalPath__firstPhysicalPathInvolvements() throws ViatraQueryException {
    return PhysicalPath__firstPhysicalPathInvolvementsQuerySpecification.instance();
  }
  
  public PhysicalPath__firstPhysicalPathInvolvementsMatcher getPhysicalPath__firstPhysicalPathInvolvements(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalPath__firstPhysicalPathInvolvementsMatcher.on(engine);
  }
  
  public PhysicalPath__realizedPhysicalPathsQuerySpecification getPhysicalPath__realizedPhysicalPaths() throws ViatraQueryException {
    return PhysicalPath__realizedPhysicalPathsQuerySpecification.instance();
  }
  
  public PhysicalPath__realizedPhysicalPathsMatcher getPhysicalPath__realizedPhysicalPaths(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalPath__realizedPhysicalPathsMatcher.on(engine);
  }
  
  public PhysicalPath__realizingPhysicalPathsQuerySpecification getPhysicalPath__realizingPhysicalPaths() throws ViatraQueryException {
    return PhysicalPath__realizingPhysicalPathsQuerySpecification.instance();
  }
  
  public PhysicalPath__realizingPhysicalPathsMatcher getPhysicalPath__realizingPhysicalPaths(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalPath__realizingPhysicalPathsMatcher.on(engine);
  }
}
