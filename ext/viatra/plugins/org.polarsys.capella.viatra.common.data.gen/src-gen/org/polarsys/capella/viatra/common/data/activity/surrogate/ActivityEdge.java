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
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityEdge__inActivityPartitionMatcher;
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityEdge__inInterruptibleRegionMatcher;
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityEdge__inStructuredNodeMatcher;
import org.polarsys.capella.viatra.common.data.activity.surrogate.util.ActivityEdge__inActivityPartitionQuerySpecification;
import org.polarsys.capella.viatra.common.data.activity.surrogate.util.ActivityEdge__inInterruptibleRegionQuerySpecification;
import org.polarsys.capella.viatra.common.data.activity.surrogate.util.ActivityEdge__inStructuredNodeQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ActivityEdge.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ActivityEdge.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.data.activity.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ActivityEdge__inActivityPartition</li>
 * <li>ActivityEdge__inInterruptibleRegion</li>
 * <li>ActivityEdge__inStructuredNode</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ActivityEdge extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ActivityEdge instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ActivityEdge();
    }
    return INSTANCE;
  }
  
  private static ActivityEdge INSTANCE;
  
  private ActivityEdge() throws ViatraQueryException {
    querySpecifications.add(ActivityEdge__inActivityPartitionQuerySpecification.instance());
    querySpecifications.add(ActivityEdge__inInterruptibleRegionQuerySpecification.instance());
    querySpecifications.add(ActivityEdge__inStructuredNodeQuerySpecification.instance());
  }
  
  public ActivityEdge__inActivityPartitionQuerySpecification getActivityEdge__inActivityPartition() throws ViatraQueryException {
    return ActivityEdge__inActivityPartitionQuerySpecification.instance();
  }
  
  public ActivityEdge__inActivityPartitionMatcher getActivityEdge__inActivityPartition(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActivityEdge__inActivityPartitionMatcher.on(engine);
  }
  
  public ActivityEdge__inInterruptibleRegionQuerySpecification getActivityEdge__inInterruptibleRegion() throws ViatraQueryException {
    return ActivityEdge__inInterruptibleRegionQuerySpecification.instance();
  }
  
  public ActivityEdge__inInterruptibleRegionMatcher getActivityEdge__inInterruptibleRegion(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActivityEdge__inInterruptibleRegionMatcher.on(engine);
  }
  
  public ActivityEdge__inStructuredNodeQuerySpecification getActivityEdge__inStructuredNode() throws ViatraQueryException {
    return ActivityEdge__inStructuredNodeQuerySpecification.instance();
  }
  
  public ActivityEdge__inStructuredNodeMatcher getActivityEdge__inStructuredNode(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActivityEdge__inStructuredNodeMatcher.on(engine);
  }
}
