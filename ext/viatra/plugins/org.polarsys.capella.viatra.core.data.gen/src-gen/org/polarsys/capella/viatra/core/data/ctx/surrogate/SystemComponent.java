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
package org.polarsys.capella.viatra.core.data.ctx.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in SystemComponent.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SystemComponent.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>SystemComponent__involvingMissions</li>
 * <li>SystemComponent__involvingCapabilities</li>
 * <li>SystemComponent__missionInvolvements</li>
 * <li>SystemComponent__realizingLogicalComponents</li>
 * <li>SystemComponent__realizedEntities</li>
 * <li>SystemComponent__capabilityInvolvements</li>
 * <li>SystemComponent__allocatedSystemFunctions</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class SystemComponent extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SystemComponent instance() {
    if (INSTANCE == null) {
        INSTANCE = new SystemComponent();
    }
    return INSTANCE;
  }
  
  private static SystemComponent INSTANCE;
  
  private SystemComponent() {
    querySpecifications.add(SystemComponent__involvingMissions.instance());
    querySpecifications.add(SystemComponent__involvingCapabilities.instance());
    querySpecifications.add(SystemComponent__missionInvolvements.instance());
    querySpecifications.add(SystemComponent__realizingLogicalComponents.instance());
    querySpecifications.add(SystemComponent__realizedEntities.instance());
    querySpecifications.add(SystemComponent__capabilityInvolvements.instance());
    querySpecifications.add(SystemComponent__allocatedSystemFunctions.instance());
  }
  
  public SystemComponent__involvingMissions getSystemComponent__involvingMissions() {
    return SystemComponent__involvingMissions.instance();
  }
  
  public SystemComponent__involvingMissions.Matcher getSystemComponent__involvingMissions(final ViatraQueryEngine engine) {
    return SystemComponent__involvingMissions.Matcher.on(engine);
  }
  
  public SystemComponent__involvingCapabilities getSystemComponent__involvingCapabilities() {
    return SystemComponent__involvingCapabilities.instance();
  }
  
  public SystemComponent__involvingCapabilities.Matcher getSystemComponent__involvingCapabilities(final ViatraQueryEngine engine) {
    return SystemComponent__involvingCapabilities.Matcher.on(engine);
  }
  
  public SystemComponent__missionInvolvements getSystemComponent__missionInvolvements() {
    return SystemComponent__missionInvolvements.instance();
  }
  
  public SystemComponent__missionInvolvements.Matcher getSystemComponent__missionInvolvements(final ViatraQueryEngine engine) {
    return SystemComponent__missionInvolvements.Matcher.on(engine);
  }
  
  public SystemComponent__realizingLogicalComponents getSystemComponent__realizingLogicalComponents() {
    return SystemComponent__realizingLogicalComponents.instance();
  }
  
  public SystemComponent__realizingLogicalComponents.Matcher getSystemComponent__realizingLogicalComponents(final ViatraQueryEngine engine) {
    return SystemComponent__realizingLogicalComponents.Matcher.on(engine);
  }
  
  public SystemComponent__realizedEntities getSystemComponent__realizedEntities() {
    return SystemComponent__realizedEntities.instance();
  }
  
  public SystemComponent__realizedEntities.Matcher getSystemComponent__realizedEntities(final ViatraQueryEngine engine) {
    return SystemComponent__realizedEntities.Matcher.on(engine);
  }
  
  public SystemComponent__capabilityInvolvements getSystemComponent__capabilityInvolvements() {
    return SystemComponent__capabilityInvolvements.instance();
  }
  
  public SystemComponent__capabilityInvolvements.Matcher getSystemComponent__capabilityInvolvements(final ViatraQueryEngine engine) {
    return SystemComponent__capabilityInvolvements.Matcher.on(engine);
  }
  
  public SystemComponent__allocatedSystemFunctions getSystemComponent__allocatedSystemFunctions() {
    return SystemComponent__allocatedSystemFunctions.instance();
  }
  
  public SystemComponent__allocatedSystemFunctions.Matcher getSystemComponent__allocatedSystemFunctions(final ViatraQueryEngine engine) {
    return SystemComponent__allocatedSystemFunctions.Matcher.on(engine);
  }
}
