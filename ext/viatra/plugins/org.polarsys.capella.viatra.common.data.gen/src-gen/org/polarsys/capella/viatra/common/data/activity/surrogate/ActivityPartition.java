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
package org.polarsys.capella.viatra.common.data.activity.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityPartition__subPartitions;
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityPartition__superPartition;

/**
 * A pattern group formed of all public patterns defined in ActivityPartition.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ActivityPartition.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.data.activity.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ActivityPartition__superPartition</li>
 * <li>ActivityPartition__subPartitions</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ActivityPartition extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ActivityPartition instance() {
    if (INSTANCE == null) {
        INSTANCE = new ActivityPartition();
    }
    return INSTANCE;
  }
  
  private static ActivityPartition INSTANCE;
  
  private ActivityPartition() {
    querySpecifications.add(ActivityPartition__superPartition.instance());
    querySpecifications.add(ActivityPartition__subPartitions.instance());
  }
  
  public ActivityPartition__superPartition getActivityPartition__superPartition() {
    return ActivityPartition__superPartition.instance();
  }
  
  public ActivityPartition__superPartition.Matcher getActivityPartition__superPartition(final ViatraQueryEngine engine) {
    return ActivityPartition__superPartition.Matcher.on(engine);
  }
  
  public ActivityPartition__subPartitions getActivityPartition__subPartitions() {
    return ActivityPartition__subPartitions.instance();
  }
  
  public ActivityPartition__subPartitions.Matcher getActivityPartition__subPartitions(final ViatraQueryEngine engine) {
    return ActivityPartition__subPartitions.Matcher.on(engine);
  }
}
