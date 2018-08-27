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
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalActivity__activityAllocationsMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalActivity__allocatingRolesMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalActivity__allocatorEntitiesMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalActivity__childrenOperationalActivitiesMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalActivity__containedOperationalActivitiesMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalActivity__ownedProcessMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalActivity__realizingSystemFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.OperationalActivity__activityAllocationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.OperationalActivity__allocatingRolesQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.OperationalActivity__allocatorEntitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.OperationalActivity__childrenOperationalActivitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.OperationalActivity__containedOperationalActivitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.OperationalActivity__ownedProcessQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.OperationalActivity__realizingSystemFunctionsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in OperationalActivity.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
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
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class OperationalActivity extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static OperationalActivity instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new OperationalActivity();
    }
    return INSTANCE;
  }
  
  private static OperationalActivity INSTANCE;
  
  private OperationalActivity() throws ViatraQueryException {
    querySpecifications.add(OperationalActivity__activityAllocationsQuerySpecification.instance());
    querySpecifications.add(OperationalActivity__ownedProcessQuerySpecification.instance());
    querySpecifications.add(OperationalActivity__allocatorEntitiesQuerySpecification.instance());
    querySpecifications.add(OperationalActivity__realizingSystemFunctionsQuerySpecification.instance());
    querySpecifications.add(OperationalActivity__allocatingRolesQuerySpecification.instance());
    querySpecifications.add(OperationalActivity__containedOperationalActivitiesQuerySpecification.instance());
    querySpecifications.add(OperationalActivity__childrenOperationalActivitiesQuerySpecification.instance());
  }
  
  public OperationalActivity__activityAllocationsQuerySpecification getOperationalActivity__activityAllocations() throws ViatraQueryException {
    return OperationalActivity__activityAllocationsQuerySpecification.instance();
  }
  
  public OperationalActivity__activityAllocationsMatcher getOperationalActivity__activityAllocations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return OperationalActivity__activityAllocationsMatcher.on(engine);
  }
  
  public OperationalActivity__ownedProcessQuerySpecification getOperationalActivity__ownedProcess() throws ViatraQueryException {
    return OperationalActivity__ownedProcessQuerySpecification.instance();
  }
  
  public OperationalActivity__ownedProcessMatcher getOperationalActivity__ownedProcess(final ViatraQueryEngine engine) throws ViatraQueryException {
    return OperationalActivity__ownedProcessMatcher.on(engine);
  }
  
  public OperationalActivity__allocatorEntitiesQuerySpecification getOperationalActivity__allocatorEntities() throws ViatraQueryException {
    return OperationalActivity__allocatorEntitiesQuerySpecification.instance();
  }
  
  public OperationalActivity__allocatorEntitiesMatcher getOperationalActivity__allocatorEntities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return OperationalActivity__allocatorEntitiesMatcher.on(engine);
  }
  
  public OperationalActivity__realizingSystemFunctionsQuerySpecification getOperationalActivity__realizingSystemFunctions() throws ViatraQueryException {
    return OperationalActivity__realizingSystemFunctionsQuerySpecification.instance();
  }
  
  public OperationalActivity__realizingSystemFunctionsMatcher getOperationalActivity__realizingSystemFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return OperationalActivity__realizingSystemFunctionsMatcher.on(engine);
  }
  
  public OperationalActivity__allocatingRolesQuerySpecification getOperationalActivity__allocatingRoles() throws ViatraQueryException {
    return OperationalActivity__allocatingRolesQuerySpecification.instance();
  }
  
  public OperationalActivity__allocatingRolesMatcher getOperationalActivity__allocatingRoles(final ViatraQueryEngine engine) throws ViatraQueryException {
    return OperationalActivity__allocatingRolesMatcher.on(engine);
  }
  
  public OperationalActivity__containedOperationalActivitiesQuerySpecification getOperationalActivity__containedOperationalActivities() throws ViatraQueryException {
    return OperationalActivity__containedOperationalActivitiesQuerySpecification.instance();
  }
  
  public OperationalActivity__containedOperationalActivitiesMatcher getOperationalActivity__containedOperationalActivities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return OperationalActivity__containedOperationalActivitiesMatcher.on(engine);
  }
  
  public OperationalActivity__childrenOperationalActivitiesQuerySpecification getOperationalActivity__childrenOperationalActivities() throws ViatraQueryException {
    return OperationalActivity__childrenOperationalActivitiesQuerySpecification.instance();
  }
  
  public OperationalActivity__childrenOperationalActivitiesMatcher getOperationalActivity__childrenOperationalActivities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return OperationalActivity__childrenOperationalActivitiesMatcher.on(engine);
  }
}
