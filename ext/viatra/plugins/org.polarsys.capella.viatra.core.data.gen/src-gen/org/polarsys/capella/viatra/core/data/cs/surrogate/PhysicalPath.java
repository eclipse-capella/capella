/**
 * 
 *   Copyright (c) 2006, 2020 THALES DMS FRANCE.
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
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPath__firstPhysicalPathInvolvements;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPath__realizedPhysicalPaths;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPath__realizingPhysicalPaths;

/**
 * A pattern group formed of all public patterns defined in PhysicalPath.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalPath.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalPath__firstPhysicalPathInvolvements</li>
 * <li>PhysicalPath__realizedPhysicalPaths</li>
 * <li>PhysicalPath__realizingPhysicalPaths</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalPath extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalPath instance() {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalPath();
    }
    return INSTANCE;
  }
  
  private static PhysicalPath INSTANCE;
  
  private PhysicalPath() {
    querySpecifications.add(PhysicalPath__firstPhysicalPathInvolvements.instance());
    querySpecifications.add(PhysicalPath__realizedPhysicalPaths.instance());
    querySpecifications.add(PhysicalPath__realizingPhysicalPaths.instance());
  }
  
  public PhysicalPath__firstPhysicalPathInvolvements getPhysicalPath__firstPhysicalPathInvolvements() {
    return PhysicalPath__firstPhysicalPathInvolvements.instance();
  }
  
  public PhysicalPath__firstPhysicalPathInvolvements.Matcher getPhysicalPath__firstPhysicalPathInvolvements(final ViatraQueryEngine engine) {
    return PhysicalPath__firstPhysicalPathInvolvements.Matcher.on(engine);
  }
  
  public PhysicalPath__realizedPhysicalPaths getPhysicalPath__realizedPhysicalPaths() {
    return PhysicalPath__realizedPhysicalPaths.instance();
  }
  
  public PhysicalPath__realizedPhysicalPaths.Matcher getPhysicalPath__realizedPhysicalPaths(final ViatraQueryEngine engine) {
    return PhysicalPath__realizedPhysicalPaths.Matcher.on(engine);
  }
  
  public PhysicalPath__realizingPhysicalPaths getPhysicalPath__realizingPhysicalPaths() {
    return PhysicalPath__realizingPhysicalPaths.instance();
  }
  
  public PhysicalPath__realizingPhysicalPaths.Matcher getPhysicalPath__realizingPhysicalPaths(final ViatraQueryEngine engine) {
    return PhysicalPath__realizingPhysicalPaths.Matcher.on(engine);
  }
}
