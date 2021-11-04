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
package org.polarsys.capella.viatra.core.data.information.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in PortRealization.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PortRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PortRealization__realizedPort</li>
 * <li>PortRealization__realizingPort</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class PortRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PortRealization instance() {
    if (INSTANCE == null) {
        INSTANCE = new PortRealization();
    }
    return INSTANCE;
  }
  
  private static PortRealization INSTANCE;
  
  private PortRealization() {
    querySpecifications.add(PortRealization__realizedPort.instance());
    querySpecifications.add(PortRealization__realizingPort.instance());
  }
  
  public PortRealization__realizedPort getPortRealization__realizedPort() {
    return PortRealization__realizedPort.instance();
  }
  
  public PortRealization__realizedPort.Matcher getPortRealization__realizedPort(final ViatraQueryEngine engine) {
    return PortRealization__realizedPort.Matcher.on(engine);
  }
  
  public PortRealization__realizingPort getPortRealization__realizingPort() {
    return PortRealization__realizingPort.instance();
  }
  
  public PortRealization__realizingPort.Matcher getPortRealization__realizingPort(final ViatraQueryEngine engine) {
    return PortRealization__realizingPort.Matcher.on(engine);
  }
}
