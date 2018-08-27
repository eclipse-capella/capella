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
package org.polarsys.capella.viatra.core.data.oa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.oa.surrogate.ActivityAllocation__activityMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.ActivityAllocation__roleMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.ActivityAllocation__activityQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.ActivityAllocation__roleQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ActivityAllocation.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ActivityAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ActivityAllocation__role</li>
 * <li>ActivityAllocation__activity</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ActivityAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ActivityAllocation instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ActivityAllocation();
    }
    return INSTANCE;
  }
  
  private static ActivityAllocation INSTANCE;
  
  private ActivityAllocation() throws ViatraQueryException {
    querySpecifications.add(ActivityAllocation__roleQuerySpecification.instance());
    querySpecifications.add(ActivityAllocation__activityQuerySpecification.instance());
  }
  
  public ActivityAllocation__roleQuerySpecification getActivityAllocation__role() throws ViatraQueryException {
    return ActivityAllocation__roleQuerySpecification.instance();
  }
  
  public ActivityAllocation__roleMatcher getActivityAllocation__role(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActivityAllocation__roleMatcher.on(engine);
  }
  
  public ActivityAllocation__activityQuerySpecification getActivityAllocation__activity() throws ViatraQueryException {
    return ActivityAllocation__activityQuerySpecification.instance();
  }
  
  public ActivityAllocation__activityMatcher getActivityAllocation__activity(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActivityAllocation__activityMatcher.on(engine);
  }
}
