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
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalActivity__activityAllocations;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalActivity__allocatingRoles;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalActivity__allocatorEntities;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalActivity__childrenOperationalActivities;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalActivity__containedOperationalActivities;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalActivity__ownedProcess;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalActivity__realizingSystemFunctions;

/**
 * A pattern group formed of all public patterns defined in OperationalActivity.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file OperationalActivity.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>OperationalActivity__activityAllocations</li>
 * <li>OperationalActivity__ownedProcess</li>
 * <li>OperationalActivity__allocatorEntities</li>
 * <li>OperationalActivity__realizingSystemFunctions</li>
 * <li>OperationalActivity__allocatingRoles</li>
 * <li>OperationalActivity__containedOperationalActivities</li>
 * <li>OperationalActivity__childrenOperationalActivities</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class OperationalActivity extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static OperationalActivity instance() {
    if (INSTANCE == null) {
        INSTANCE = new OperationalActivity();
    }
    return INSTANCE;
  }
  
  private static OperationalActivity INSTANCE;
  
  private OperationalActivity() {
    querySpecifications.add(OperationalActivity__activityAllocations.instance());
    querySpecifications.add(OperationalActivity__ownedProcess.instance());
    querySpecifications.add(OperationalActivity__allocatorEntities.instance());
    querySpecifications.add(OperationalActivity__realizingSystemFunctions.instance());
    querySpecifications.add(OperationalActivity__allocatingRoles.instance());
    querySpecifications.add(OperationalActivity__containedOperationalActivities.instance());
    querySpecifications.add(OperationalActivity__childrenOperationalActivities.instance());
  }
  
  public OperationalActivity__activityAllocations getOperationalActivity__activityAllocations() {
    return OperationalActivity__activityAllocations.instance();
  }
  
  public OperationalActivity__activityAllocations.Matcher getOperationalActivity__activityAllocations(final ViatraQueryEngine engine) {
    return OperationalActivity__activityAllocations.Matcher.on(engine);
  }
  
  public OperationalActivity__ownedProcess getOperationalActivity__ownedProcess() {
    return OperationalActivity__ownedProcess.instance();
  }
  
  public OperationalActivity__ownedProcess.Matcher getOperationalActivity__ownedProcess(final ViatraQueryEngine engine) {
    return OperationalActivity__ownedProcess.Matcher.on(engine);
  }
  
  public OperationalActivity__allocatorEntities getOperationalActivity__allocatorEntities() {
    return OperationalActivity__allocatorEntities.instance();
  }
  
  public OperationalActivity__allocatorEntities.Matcher getOperationalActivity__allocatorEntities(final ViatraQueryEngine engine) {
    return OperationalActivity__allocatorEntities.Matcher.on(engine);
  }
  
  public OperationalActivity__realizingSystemFunctions getOperationalActivity__realizingSystemFunctions() {
    return OperationalActivity__realizingSystemFunctions.instance();
  }
  
  public OperationalActivity__realizingSystemFunctions.Matcher getOperationalActivity__realizingSystemFunctions(final ViatraQueryEngine engine) {
    return OperationalActivity__realizingSystemFunctions.Matcher.on(engine);
  }
  
  public OperationalActivity__allocatingRoles getOperationalActivity__allocatingRoles() {
    return OperationalActivity__allocatingRoles.instance();
  }
  
  public OperationalActivity__allocatingRoles.Matcher getOperationalActivity__allocatingRoles(final ViatraQueryEngine engine) {
    return OperationalActivity__allocatingRoles.Matcher.on(engine);
  }
  
  public OperationalActivity__containedOperationalActivities getOperationalActivity__containedOperationalActivities() {
    return OperationalActivity__containedOperationalActivities.instance();
  }
  
  public OperationalActivity__containedOperationalActivities.Matcher getOperationalActivity__containedOperationalActivities(final ViatraQueryEngine engine) {
    return OperationalActivity__containedOperationalActivities.Matcher.on(engine);
  }
  
  public OperationalActivity__childrenOperationalActivities getOperationalActivity__childrenOperationalActivities() {
    return OperationalActivity__childrenOperationalActivities.instance();
  }
  
  public OperationalActivity__childrenOperationalActivities.Matcher getOperationalActivity__childrenOperationalActivities(final ViatraQueryEngine engine) {
    return OperationalActivity__childrenOperationalActivities.Matcher.on(engine);
  }
}
