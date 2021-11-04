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
package org.polarsys.capella.viatra.core.data.pa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in PhysicalNode.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalNode.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.pa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalNode__subPhysicalNodes</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalNode extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalNode instance() {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalNode();
    }
    return INSTANCE;
  }
  
  private static PhysicalNode INSTANCE;
  
  private PhysicalNode() {
    querySpecifications.add(PhysicalNode__subPhysicalNodes.instance());
  }
  
  public PhysicalNode__subPhysicalNodes getPhysicalNode__subPhysicalNodes() {
    return PhysicalNode__subPhysicalNodes.instance();
  }
  
  public PhysicalNode__subPhysicalNodes.Matcher getPhysicalNode__subPhysicalNodes(final ViatraQueryEngine engine) {
    return PhysicalNode__subPhysicalNodes.Matcher.on(engine);
  }
}
