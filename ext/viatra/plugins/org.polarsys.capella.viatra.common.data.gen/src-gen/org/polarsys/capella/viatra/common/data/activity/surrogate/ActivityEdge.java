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
package org.polarsys.capella.viatra.common.data.activity.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityEdge__inActivityPartition;
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityEdge__inInterruptibleRegion;
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityEdge__inStructuredNode;

/**
 * A pattern group formed of all public patterns defined in ActivityEdge.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ActivityEdge.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.data.activity.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ActivityEdge__inActivityPartition</li>
 * <li>ActivityEdge__inInterruptibleRegion</li>
 * <li>ActivityEdge__inStructuredNode</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ActivityEdge extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ActivityEdge instance() {
    if (INSTANCE == null) {
        INSTANCE = new ActivityEdge();
    }
    return INSTANCE;
  }
  
  private static ActivityEdge INSTANCE;
  
  private ActivityEdge() {
    querySpecifications.add(ActivityEdge__inActivityPartition.instance());
    querySpecifications.add(ActivityEdge__inInterruptibleRegion.instance());
    querySpecifications.add(ActivityEdge__inStructuredNode.instance());
  }
  
  public ActivityEdge__inActivityPartition getActivityEdge__inActivityPartition() {
    return ActivityEdge__inActivityPartition.instance();
  }
  
  public ActivityEdge__inActivityPartition.Matcher getActivityEdge__inActivityPartition(final ViatraQueryEngine engine) {
    return ActivityEdge__inActivityPartition.Matcher.on(engine);
  }
  
  public ActivityEdge__inInterruptibleRegion getActivityEdge__inInterruptibleRegion() {
    return ActivityEdge__inInterruptibleRegion.instance();
  }
  
  public ActivityEdge__inInterruptibleRegion.Matcher getActivityEdge__inInterruptibleRegion(final ViatraQueryEngine engine) {
    return ActivityEdge__inInterruptibleRegion.Matcher.on(engine);
  }
  
  public ActivityEdge__inStructuredNode getActivityEdge__inStructuredNode() {
    return ActivityEdge__inStructuredNode.instance();
  }
  
  public ActivityEdge__inStructuredNode.Matcher getActivityEdge__inStructuredNode(final ViatraQueryEngine engine) {
    return ActivityEdge__inStructuredNode.Matcher.on(engine);
  }
}
