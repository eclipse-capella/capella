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
import org.polarsys.capella.viatra.common.data.activity.surrogate.ActivityExchange__realizingActivityFlowsMatcher;
import org.polarsys.capella.viatra.common.data.activity.surrogate.util.ActivityExchange__realizingActivityFlowsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ActivityExchange.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ActivityExchange.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.data.activity.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ActivityExchange__realizingActivityFlows</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ActivityExchange extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ActivityExchange instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ActivityExchange();
    }
    return INSTANCE;
  }
  
  private static ActivityExchange INSTANCE;
  
  private ActivityExchange() throws ViatraQueryException {
    querySpecifications.add(ActivityExchange__realizingActivityFlowsQuerySpecification.instance());
  }
  
  public ActivityExchange__realizingActivityFlowsQuerySpecification getActivityExchange__realizingActivityFlows() throws ViatraQueryException {
    return ActivityExchange__realizingActivityFlowsQuerySpecification.instance();
  }
  
  public ActivityExchange__realizingActivityFlowsMatcher getActivityExchange__realizingActivityFlows(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActivityExchange__realizingActivityFlowsMatcher.on(engine);
  }
}
