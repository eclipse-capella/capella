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
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalAnalysis__allocatingSystemAnalyses;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalAnalysis__containedOperationalActivityPkg;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalAnalysis__containedOperationalCapabilityPkg;

/**
 * A pattern group formed of all public patterns defined in OperationalAnalysis.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file OperationalAnalysis.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>OperationalAnalysis__containedOperationalCapabilityPkg</li>
 * <li>OperationalAnalysis__containedOperationalActivityPkg</li>
 * <li>OperationalAnalysis__allocatingSystemAnalyses</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class OperationalAnalysis extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static OperationalAnalysis instance() {
    if (INSTANCE == null) {
        INSTANCE = new OperationalAnalysis();
    }
    return INSTANCE;
  }
  
  private static OperationalAnalysis INSTANCE;
  
  private OperationalAnalysis() {
    querySpecifications.add(OperationalAnalysis__containedOperationalCapabilityPkg.instance());
    querySpecifications.add(OperationalAnalysis__containedOperationalActivityPkg.instance());
    querySpecifications.add(OperationalAnalysis__allocatingSystemAnalyses.instance());
  }
  
  public OperationalAnalysis__containedOperationalCapabilityPkg getOperationalAnalysis__containedOperationalCapabilityPkg() {
    return OperationalAnalysis__containedOperationalCapabilityPkg.instance();
  }
  
  public OperationalAnalysis__containedOperationalCapabilityPkg.Matcher getOperationalAnalysis__containedOperationalCapabilityPkg(final ViatraQueryEngine engine) {
    return OperationalAnalysis__containedOperationalCapabilityPkg.Matcher.on(engine);
  }
  
  public OperationalAnalysis__containedOperationalActivityPkg getOperationalAnalysis__containedOperationalActivityPkg() {
    return OperationalAnalysis__containedOperationalActivityPkg.instance();
  }
  
  public OperationalAnalysis__containedOperationalActivityPkg.Matcher getOperationalAnalysis__containedOperationalActivityPkg(final ViatraQueryEngine engine) {
    return OperationalAnalysis__containedOperationalActivityPkg.Matcher.on(engine);
  }
  
  public OperationalAnalysis__allocatingSystemAnalyses getOperationalAnalysis__allocatingSystemAnalyses() {
    return OperationalAnalysis__allocatingSystemAnalyses.instance();
  }
  
  public OperationalAnalysis__allocatingSystemAnalyses.Matcher getOperationalAnalysis__allocatingSystemAnalyses(final ViatraQueryEngine engine) {
    return OperationalAnalysis__allocatingSystemAnalyses.Matcher.on(engine);
  }
}
