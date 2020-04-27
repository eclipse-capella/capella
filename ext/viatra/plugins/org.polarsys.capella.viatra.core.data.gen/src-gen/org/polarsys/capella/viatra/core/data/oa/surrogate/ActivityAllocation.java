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
package org.polarsys.capella.viatra.core.data.oa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.oa.surrogate.ActivityAllocation__activity;
import org.polarsys.capella.viatra.core.data.oa.surrogate.ActivityAllocation__role;

/**
 * A pattern group formed of all public patterns defined in ActivityAllocation.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ActivityAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ActivityAllocation__role</li>
 * <li>ActivityAllocation__activity</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ActivityAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ActivityAllocation instance() {
    if (INSTANCE == null) {
        INSTANCE = new ActivityAllocation();
    }
    return INSTANCE;
  }
  
  private static ActivityAllocation INSTANCE;
  
  private ActivityAllocation() {
    querySpecifications.add(ActivityAllocation__role.instance());
    querySpecifications.add(ActivityAllocation__activity.instance());
  }
  
  public ActivityAllocation__role getActivityAllocation__role() {
    return ActivityAllocation__role.instance();
  }
  
  public ActivityAllocation__role.Matcher getActivityAllocation__role(final ViatraQueryEngine engine) {
    return ActivityAllocation__role.Matcher.on(engine);
  }
  
  public ActivityAllocation__activity getActivityAllocation__activity() {
    return ActivityAllocation__activity.instance();
  }
  
  public ActivityAllocation__activity.Matcher getActivityAllocation__activity(final ViatraQueryEngine engine) {
    return ActivityAllocation__activity.Matcher.on(engine);
  }
}
