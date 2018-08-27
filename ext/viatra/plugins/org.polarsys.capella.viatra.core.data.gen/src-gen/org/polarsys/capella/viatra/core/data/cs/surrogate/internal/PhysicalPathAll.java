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
package org.polarsys.capella.viatra.core.data.cs.surrogate.internal;

import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.cs.surrogate.internal._PreviousInvolvementQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalPath__firstPhysicalPathInvolvementsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalPath__realizedPhysicalPathsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalPath__realizingPhysicalPathsQuerySpecification;

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
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalPathAll extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalPathAll instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalPathAll();
    }
    return INSTANCE;
  }
  
  private static PhysicalPathAll INSTANCE;
  
  private PhysicalPathAll() throws ViatraQueryException {
    querySpecifications.add(PhysicalPath__firstPhysicalPathInvolvementsQuerySpecification.instance());
    querySpecifications.add(_PreviousInvolvementQuerySpecification.instance());
    querySpecifications.add(PhysicalPath__realizedPhysicalPathsQuerySpecification.instance());
    querySpecifications.add(PhysicalPath__realizingPhysicalPathsQuerySpecification.instance());
  }
}
