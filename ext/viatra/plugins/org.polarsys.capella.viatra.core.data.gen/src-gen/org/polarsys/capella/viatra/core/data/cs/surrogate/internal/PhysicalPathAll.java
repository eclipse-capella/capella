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
package org.polarsys.capella.viatra.core.data.cs.surrogate.internal;

import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPath__firstPhysicalPathInvolvements;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPath__realizedPhysicalPaths;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPath__realizingPhysicalPaths;
import org.polarsys.capella.viatra.core.data.cs.surrogate.internal._PreviousInvolvement;

/**
 * A pattern group formed of all patterns defined in PhysicalPath.vql.
 * 
 * <p>A private group that includes private patterns as well. Only intended use case is for pattern testing.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalPath__firstPhysicalPathInvolvements</li>
 * <li>_PreviousInvolvement</li>
 * <li>PhysicalPath__realizedPhysicalPaths</li>
 * <li>PhysicalPath__realizingPhysicalPaths</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalPathAll extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalPathAll instance() {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalPathAll();
    }
    return INSTANCE;
  }
  
  private static PhysicalPathAll INSTANCE;
  
  private PhysicalPathAll() {
    querySpecifications.add(PhysicalPath__firstPhysicalPathInvolvements.instance());
    querySpecifications.add(_PreviousInvolvement.instance());
    querySpecifications.add(PhysicalPath__realizedPhysicalPaths.instance());
    querySpecifications.add(PhysicalPath__realizingPhysicalPaths.instance());
  }
}
