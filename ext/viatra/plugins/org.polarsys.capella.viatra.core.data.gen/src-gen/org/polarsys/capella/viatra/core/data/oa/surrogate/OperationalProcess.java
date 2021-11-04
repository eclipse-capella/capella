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
 * A pattern group formed of all public patterns defined in OperationalProcess.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file OperationalProcess.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>OperationalProcess__involvingOperationalCapabilities</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class OperationalProcess extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static OperationalProcess instance() {
    if (INSTANCE == null) {
        INSTANCE = new OperationalProcess();
    }
    return INSTANCE;
  }
  
  private static OperationalProcess INSTANCE;
  
  private OperationalProcess() {
    querySpecifications.add(OperationalProcess__involvingOperationalCapabilities.instance());
  }
  
  public OperationalProcess__involvingOperationalCapabilities getOperationalProcess__involvingOperationalCapabilities() {
    return OperationalProcess__involvingOperationalCapabilities.instance();
  }
  
  public OperationalProcess__involvingOperationalCapabilities.Matcher getOperationalProcess__involvingOperationalCapabilities(final ViatraQueryEngine engine) {
    return OperationalProcess__involvingOperationalCapabilities.Matcher.on(engine);
  }
}
