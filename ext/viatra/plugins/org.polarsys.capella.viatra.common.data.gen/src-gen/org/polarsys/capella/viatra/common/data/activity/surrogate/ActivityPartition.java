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
package org.polarsys.capella.viatra.common.data.activity.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityPartition__subPartitionsMatcher;
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityPartition__superPartitionMatcher;
import org.polarsys.capella.viatra.common.data.activity.surrogate.util.ActivityPartition__subPartitionsQuerySpecification;
import org.polarsys.capella.viatra.common.data.activity.surrogate.util.ActivityPartition__superPartitionQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ActivityPartition.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ActivityPartition.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.data.activity.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ActivityPartition__superPartition</li>
 * <li>ActivityPartition__subPartitions</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ActivityPartition extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ActivityPartition instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ActivityPartition();
    }
    return INSTANCE;
  }
  
  private static ActivityPartition INSTANCE;
  
  private ActivityPartition() throws ViatraQueryException {
    querySpecifications.add(ActivityPartition__superPartitionQuerySpecification.instance());
    querySpecifications.add(ActivityPartition__subPartitionsQuerySpecification.instance());
  }
  
  public ActivityPartition__superPartitionQuerySpecification getActivityPartition__superPartition() throws ViatraQueryException {
    return ActivityPartition__superPartitionQuerySpecification.instance();
  }
  
  public ActivityPartition__superPartitionMatcher getActivityPartition__superPartition(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActivityPartition__superPartitionMatcher.on(engine);
  }
  
  public ActivityPartition__subPartitionsQuerySpecification getActivityPartition__subPartitions() throws ViatraQueryException {
    return ActivityPartition__subPartitionsQuerySpecification.instance();
  }
  
  public ActivityPartition__subPartitionsMatcher getActivityPartition__subPartitions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActivityPartition__subPartitionsMatcher.on(engine);
  }
}
