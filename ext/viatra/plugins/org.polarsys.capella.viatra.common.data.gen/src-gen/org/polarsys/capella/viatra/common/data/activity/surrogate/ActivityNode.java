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

/**
 * A pattern group formed of all public patterns defined in ActivityNode.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ActivityNode extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ActivityNode instance() {
    if (INSTANCE == null) {
        INSTANCE = new ActivityNode();
    }
    return INSTANCE;
  }
  
  private static ActivityNode INSTANCE;
  
  private ActivityNode() {
    querySpecifications.add(ActivityNode__inActivityPartition.instance());
    querySpecifications.add(ActivityNode__inInterruptibleRegion.instance());
    querySpecifications.add(ActivityNode__inStructuredNode.instance());
    querySpecifications.add(ActivityNode__outgoing.instance());
    querySpecifications.add(ActivityNode__incoming.instance());
  }
  
  public ActivityNode__inActivityPartition getActivityNode__inActivityPartition() {
    return ActivityNode__inActivityPartition.instance();
  }
  
  public ActivityNode__inActivityPartition.Matcher getActivityNode__inActivityPartition(final ViatraQueryEngine engine) {
    return ActivityNode__inActivityPartition.Matcher.on(engine);
  }
  
  public ActivityNode__inInterruptibleRegion getActivityNode__inInterruptibleRegion() {
    return ActivityNode__inInterruptibleRegion.instance();
  }
  
  public ActivityNode__inInterruptibleRegion.Matcher getActivityNode__inInterruptibleRegion(final ViatraQueryEngine engine) {
    return ActivityNode__inInterruptibleRegion.Matcher.on(engine);
  }
  
  public ActivityNode__inStructuredNode getActivityNode__inStructuredNode() {
    return ActivityNode__inStructuredNode.instance();
  }
  
  public ActivityNode__inStructuredNode.Matcher getActivityNode__inStructuredNode(final ViatraQueryEngine engine) {
    return ActivityNode__inStructuredNode.Matcher.on(engine);
  }
  
  public ActivityNode__outgoing getActivityNode__outgoing() {
    return ActivityNode__outgoing.instance();
  }
  
  public ActivityNode__outgoing.Matcher getActivityNode__outgoing(final ViatraQueryEngine engine) {
    return ActivityNode__outgoing.Matcher.on(engine);
  }
  
  public ActivityNode__incoming getActivityNode__incoming() {
    return ActivityNode__incoming.instance();
  }
  
  public ActivityNode__incoming.Matcher getActivityNode__incoming(final ViatraQueryEngine engine) {
    return ActivityNode__incoming.Matcher.on(engine);
  }
}
