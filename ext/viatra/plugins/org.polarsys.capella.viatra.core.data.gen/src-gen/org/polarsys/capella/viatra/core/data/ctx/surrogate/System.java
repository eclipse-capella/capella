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
package org.polarsys.capella.viatra.core.data.ctx.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__allocatedEntityRealizations;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__allocatedSystemFunctions;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__contributedCapabilities;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__contributedMissions;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__participationsInCapabilities;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__participationsInMissions;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__realizedEntities;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.System__realizingLogicalComponents;

/**
 * A pattern group formed of all public patterns defined in System.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file System.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>System__contributedCapabilities</li>
 * <li>System__participationsInCapabilities</li>
 * <li>System__contributedMissions</li>
 * <li>System__participationsInMissions</li>
 * <li>System__allocatedEntityRealizations</li>
 * <li>System__allocatedSystemFunctions</li>
 * <li>System__realizedEntities</li>
 * <li>System__realizingLogicalComponents</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class System extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static System instance() {
    if (INSTANCE == null) {
        INSTANCE = new System();
    }
    return INSTANCE;
  }
  
  private static System INSTANCE;
  
  private System() {
    querySpecifications.add(System__contributedCapabilities.instance());
    querySpecifications.add(System__participationsInCapabilities.instance());
    querySpecifications.add(System__contributedMissions.instance());
    querySpecifications.add(System__participationsInMissions.instance());
    querySpecifications.add(System__allocatedEntityRealizations.instance());
    querySpecifications.add(System__allocatedSystemFunctions.instance());
    querySpecifications.add(System__realizedEntities.instance());
    querySpecifications.add(System__realizingLogicalComponents.instance());
  }
  
  public System__contributedCapabilities getSystem__contributedCapabilities() {
    return System__contributedCapabilities.instance();
  }
  
  public System__contributedCapabilities.Matcher getSystem__contributedCapabilities(final ViatraQueryEngine engine) {
    return System__contributedCapabilities.Matcher.on(engine);
  }
  
  public System__participationsInCapabilities getSystem__participationsInCapabilities() {
    return System__participationsInCapabilities.instance();
  }
  
  public System__participationsInCapabilities.Matcher getSystem__participationsInCapabilities(final ViatraQueryEngine engine) {
    return System__participationsInCapabilities.Matcher.on(engine);
  }
  
  public System__contributedMissions getSystem__contributedMissions() {
    return System__contributedMissions.instance();
  }
  
  public System__contributedMissions.Matcher getSystem__contributedMissions(final ViatraQueryEngine engine) {
    return System__contributedMissions.Matcher.on(engine);
  }
  
  public System__participationsInMissions getSystem__participationsInMissions() {
    return System__participationsInMissions.instance();
  }
  
  public System__participationsInMissions.Matcher getSystem__participationsInMissions(final ViatraQueryEngine engine) {
    return System__participationsInMissions.Matcher.on(engine);
  }
  
  public System__allocatedEntityRealizations getSystem__allocatedEntityRealizations() {
    return System__allocatedEntityRealizations.instance();
  }
  
  public System__allocatedEntityRealizations.Matcher getSystem__allocatedEntityRealizations(final ViatraQueryEngine engine) {
    return System__allocatedEntityRealizations.Matcher.on(engine);
  }
  
  public System__allocatedSystemFunctions getSystem__allocatedSystemFunctions() {
    return System__allocatedSystemFunctions.instance();
  }
  
  public System__allocatedSystemFunctions.Matcher getSystem__allocatedSystemFunctions(final ViatraQueryEngine engine) {
    return System__allocatedSystemFunctions.Matcher.on(engine);
  }
  
  public System__realizedEntities getSystem__realizedEntities() {
    return System__realizedEntities.instance();
  }
  
  public System__realizedEntities.Matcher getSystem__realizedEntities(final ViatraQueryEngine engine) {
    return System__realizedEntities.Matcher.on(engine);
  }
  
  public System__realizingLogicalComponents getSystem__realizingLogicalComponents() {
    return System__realizingLogicalComponents.instance();
  }
  
  public System__realizingLogicalComponents.Matcher getSystem__realizingLogicalComponents(final ViatraQueryEngine engine) {
    return System__realizingLogicalComponents.Matcher.on(engine);
  }
}
