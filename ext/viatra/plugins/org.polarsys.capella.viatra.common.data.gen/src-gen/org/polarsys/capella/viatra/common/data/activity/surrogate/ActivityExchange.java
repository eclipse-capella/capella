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
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityExchange__realizingActivityFlows;

/**
 * A pattern group formed of all public patterns defined in ActivityExchange.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ActivityExchange.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.data.activity.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ActivityExchange__realizingActivityFlows</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ActivityExchange extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ActivityExchange instance() {
    if (INSTANCE == null) {
        INSTANCE = new ActivityExchange();
    }
    return INSTANCE;
  }
  
  private static ActivityExchange INSTANCE;
  
  private ActivityExchange() {
    querySpecifications.add(ActivityExchange__realizingActivityFlows.instance());
  }
  
  public ActivityExchange__realizingActivityFlows getActivityExchange__realizingActivityFlows() {
    return ActivityExchange__realizingActivityFlows.instance();
  }
  
  public ActivityExchange__realizingActivityFlows.Matcher getActivityExchange__realizingActivityFlows(final ViatraQueryEngine engine) {
    return ActivityExchange__realizingActivityFlows.Matcher.on(engine);
  }
}
