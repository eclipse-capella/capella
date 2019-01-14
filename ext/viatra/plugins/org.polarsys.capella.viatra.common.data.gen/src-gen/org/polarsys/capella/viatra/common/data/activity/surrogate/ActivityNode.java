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
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityNode__inActivityPartitionMatcher;
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityNode__inInterruptibleRegionMatcher;
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityNode__inStructuredNodeMatcher;
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityNode__incomingMatcher;
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityNode__outgoingMatcher;
import org.polarsys.capella.viatra.common.data.activity.surrogate.util.ActivityNode__inActivityPartitionQuerySpecification;
import org.polarsys.capella.viatra.common.data.activity.surrogate.util.ActivityNode__inInterruptibleRegionQuerySpecification;
import org.polarsys.capella.viatra.common.data.activity.surrogate.util.ActivityNode__inStructuredNodeQuerySpecification;
import org.polarsys.capella.viatra.common.data.activity.surrogate.util.ActivityNode__incomingQuerySpecification;
import org.polarsys.capella.viatra.common.data.activity.surrogate.util.ActivityNode__outgoingQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ActivityNode.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ActivityNode.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.data.activity.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ActivityNode__inActivityPartition</li>
 * <li>ActivityNode__inInterruptibleRegion</li>
 * <li>ActivityNode__inStructuredNode</li>
 * <li>ActivityNode__outgoing</li>
 * <li>ActivityNode__incoming</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ActivityNode extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ActivityNode instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ActivityNode();
    }
    return INSTANCE;
  }
  
  private static ActivityNode INSTANCE;
  
  private ActivityNode() throws ViatraQueryException {
    querySpecifications.add(ActivityNode__inActivityPartitionQuerySpecification.instance());
    querySpecifications.add(ActivityNode__inInterruptibleRegionQuerySpecification.instance());
    querySpecifications.add(ActivityNode__inStructuredNodeQuerySpecification.instance());
    querySpecifications.add(ActivityNode__outgoingQuerySpecification.instance());
    querySpecifications.add(ActivityNode__incomingQuerySpecification.instance());
  }
  
  public ActivityNode__inActivityPartitionQuerySpecification getActivityNode__inActivityPartition() throws ViatraQueryException {
    return ActivityNode__inActivityPartitionQuerySpecification.instance();
  }
  
  public ActivityNode__inActivityPartitionMatcher getActivityNode__inActivityPartition(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActivityNode__inActivityPartitionMatcher.on(engine);
  }
  
  public ActivityNode__inInterruptibleRegionQuerySpecification getActivityNode__inInterruptibleRegion() throws ViatraQueryException {
    return ActivityNode__inInterruptibleRegionQuerySpecification.instance();
  }
  
  public ActivityNode__inInterruptibleRegionMatcher getActivityNode__inInterruptibleRegion(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActivityNode__inInterruptibleRegionMatcher.on(engine);
  }
  
  public ActivityNode__inStructuredNodeQuerySpecification getActivityNode__inStructuredNode() throws ViatraQueryException {
    return ActivityNode__inStructuredNodeQuerySpecification.instance();
  }
  
  public ActivityNode__inStructuredNodeMatcher getActivityNode__inStructuredNode(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActivityNode__inStructuredNodeMatcher.on(engine);
  }
  
  public ActivityNode__outgoingQuerySpecification getActivityNode__outgoing() throws ViatraQueryException {
    return ActivityNode__outgoingQuerySpecification.instance();
  }
  
  public ActivityNode__outgoingMatcher getActivityNode__outgoing(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActivityNode__outgoingMatcher.on(engine);
  }
  
  public ActivityNode__incomingQuerySpecification getActivityNode__incoming() throws ViatraQueryException {
    return ActivityNode__incomingQuerySpecification.instance();
  }
  
  public ActivityNode__incomingMatcher getActivityNode__incoming(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActivityNode__incomingMatcher.on(engine);
  }
}
