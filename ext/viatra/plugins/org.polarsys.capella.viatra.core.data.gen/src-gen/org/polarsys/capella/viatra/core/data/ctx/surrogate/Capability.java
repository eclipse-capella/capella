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
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Capability__involvedSystemComponents;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Capability__purposeMissions;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Capability__purposes;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Capability__realizedOperationalCapabilities;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.Capability__realizingCapabilityRealizations;

/**
 * A pattern group formed of all public patterns defined in Capability.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Capability.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Capability__purposes</li>
 * <li>Capability__purposeMissions</li>
 * <li>Capability__realizedOperationalCapabilities</li>
 * <li>Capability__realizingCapabilityRealizations</li>
 * <li>Capability__involvedSystemComponents</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Capability extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Capability instance() {
    if (INSTANCE == null) {
        INSTANCE = new Capability();
    }
    return INSTANCE;
  }
  
  private static Capability INSTANCE;
  
  private Capability() {
    querySpecifications.add(Capability__purposes.instance());
    querySpecifications.add(Capability__purposeMissions.instance());
    querySpecifications.add(Capability__realizedOperationalCapabilities.instance());
    querySpecifications.add(Capability__realizingCapabilityRealizations.instance());
    querySpecifications.add(Capability__involvedSystemComponents.instance());
  }
  
  public Capability__purposes getCapability__purposes() {
    return Capability__purposes.instance();
  }
  
  public Capability__purposes.Matcher getCapability__purposes(final ViatraQueryEngine engine) {
    return Capability__purposes.Matcher.on(engine);
  }
  
  public Capability__purposeMissions getCapability__purposeMissions() {
    return Capability__purposeMissions.instance();
  }
  
  public Capability__purposeMissions.Matcher getCapability__purposeMissions(final ViatraQueryEngine engine) {
    return Capability__purposeMissions.Matcher.on(engine);
  }
  
  public Capability__realizedOperationalCapabilities getCapability__realizedOperationalCapabilities() {
    return Capability__realizedOperationalCapabilities.instance();
  }
  
  public Capability__realizedOperationalCapabilities.Matcher getCapability__realizedOperationalCapabilities(final ViatraQueryEngine engine) {
    return Capability__realizedOperationalCapabilities.Matcher.on(engine);
  }
  
  public Capability__realizingCapabilityRealizations getCapability__realizingCapabilityRealizations() {
    return Capability__realizingCapabilityRealizations.instance();
  }
  
  public Capability__realizingCapabilityRealizations.Matcher getCapability__realizingCapabilityRealizations(final ViatraQueryEngine engine) {
    return Capability__realizingCapabilityRealizations.Matcher.on(engine);
  }
  
  public Capability__involvedSystemComponents getCapability__involvedSystemComponents() {
    return Capability__involvedSystemComponents.instance();
  }
  
  public Capability__involvedSystemComponents.Matcher getCapability__involvedSystemComponents(final ViatraQueryEngine engine) {
    return Capability__involvedSystemComponents.Matcher.on(engine);
  }
}
