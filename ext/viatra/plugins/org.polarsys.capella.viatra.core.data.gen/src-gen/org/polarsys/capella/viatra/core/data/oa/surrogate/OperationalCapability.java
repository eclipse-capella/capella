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
package org.polarsys.capella.viatra.core.data.oa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in OperationalCapability.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file OperationalCapability.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>OperationalCapability__realizingCapabilities</li>
 * <li>OperationalCapability__involvedEntities</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class OperationalCapability extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static OperationalCapability instance() {
    if (INSTANCE == null) {
        INSTANCE = new OperationalCapability();
    }
    return INSTANCE;
  }
  
  private static OperationalCapability INSTANCE;
  
  private OperationalCapability() {
    querySpecifications.add(OperationalCapability__realizingCapabilities.instance());
    querySpecifications.add(OperationalCapability__involvedEntities.instance());
  }
  
  public OperationalCapability__realizingCapabilities getOperationalCapability__realizingCapabilities() {
    return OperationalCapability__realizingCapabilities.instance();
  }
  
  public OperationalCapability__realizingCapabilities.Matcher getOperationalCapability__realizingCapabilities(final ViatraQueryEngine engine) {
    return OperationalCapability__realizingCapabilities.Matcher.on(engine);
  }
  
  public OperationalCapability__involvedEntities getOperationalCapability__involvedEntities() {
    return OperationalCapability__involvedEntities.instance();
  }
  
  public OperationalCapability__involvedEntities.Matcher getOperationalCapability__involvedEntities(final ViatraQueryEngine engine) {
    return OperationalCapability__involvedEntities.Matcher.on(engine);
  }
}
