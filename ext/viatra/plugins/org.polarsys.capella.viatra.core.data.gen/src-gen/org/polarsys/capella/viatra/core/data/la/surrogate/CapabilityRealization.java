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
package org.polarsys.capella.viatra.core.data.la.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in CapabilityRealization.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file CapabilityRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.la.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>CapabilityRealization__realizedCapabilities</li>
 * <li>CapabilityRealization__realizedCapabilityRealizations</li>
 * <li>CapabilityRealization__realizingCapabilityRealizations</li>
 * <li>CapabilityInvolvement__systemComponent</li>
 * <li>CapabilityInvolvement__capability</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class CapabilityRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static CapabilityRealization instance() {
    if (INSTANCE == null) {
        INSTANCE = new CapabilityRealization();
    }
    return INSTANCE;
  }
  
  private static CapabilityRealization INSTANCE;
  
  private CapabilityRealization() {
    querySpecifications.add(CapabilityRealization__realizedCapabilities.instance());
    querySpecifications.add(CapabilityRealization__realizedCapabilityRealizations.instance());
    querySpecifications.add(CapabilityRealization__realizingCapabilityRealizations.instance());
    querySpecifications.add(CapabilityInvolvement__systemComponent.instance());
    querySpecifications.add(CapabilityInvolvement__capability.instance());
  }
  
  public CapabilityRealization__realizedCapabilities getCapabilityRealization__realizedCapabilities() {
    return CapabilityRealization__realizedCapabilities.instance();
  }
  
  public CapabilityRealization__realizedCapabilities.Matcher getCapabilityRealization__realizedCapabilities(final ViatraQueryEngine engine) {
    return CapabilityRealization__realizedCapabilities.Matcher.on(engine);
  }
  
  public CapabilityRealization__realizedCapabilityRealizations getCapabilityRealization__realizedCapabilityRealizations() {
    return CapabilityRealization__realizedCapabilityRealizations.instance();
  }
  
  public CapabilityRealization__realizedCapabilityRealizations.Matcher getCapabilityRealization__realizedCapabilityRealizations(final ViatraQueryEngine engine) {
    return CapabilityRealization__realizedCapabilityRealizations.Matcher.on(engine);
  }
  
  public CapabilityRealization__realizingCapabilityRealizations getCapabilityRealization__realizingCapabilityRealizations() {
    return CapabilityRealization__realizingCapabilityRealizations.instance();
  }
  
  public CapabilityRealization__realizingCapabilityRealizations.Matcher getCapabilityRealization__realizingCapabilityRealizations(final ViatraQueryEngine engine) {
    return CapabilityRealization__realizingCapabilityRealizations.Matcher.on(engine);
  }
  
  public CapabilityInvolvement__systemComponent getCapabilityInvolvement__systemComponent() {
    return CapabilityInvolvement__systemComponent.instance();
  }
  
  public CapabilityInvolvement__systemComponent.Matcher getCapabilityInvolvement__systemComponent(final ViatraQueryEngine engine) {
    return CapabilityInvolvement__systemComponent.Matcher.on(engine);
  }
  
  public CapabilityInvolvement__capability getCapabilityInvolvement__capability() {
    return CapabilityInvolvement__capability.instance();
  }
  
  public CapabilityInvolvement__capability.Matcher getCapabilityInvolvement__capability(final ViatraQueryEngine engine) {
    return CapabilityInvolvement__capability.Matcher.on(engine);
  }
}
