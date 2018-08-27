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
package org.polarsys.capella.viatra.core.data.pa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalNode__subPhysicalNodesMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalNode__subPhysicalNodesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in PhysicalNode.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalNode.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.pa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalNode__subPhysicalNodes</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalNode extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalNode instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalNode();
    }
    return INSTANCE;
  }
  
  private static PhysicalNode INSTANCE;
  
  private PhysicalNode() throws ViatraQueryException {
    querySpecifications.add(PhysicalNode__subPhysicalNodesQuerySpecification.instance());
  }
  
  public PhysicalNode__subPhysicalNodesQuerySpecification getPhysicalNode__subPhysicalNodes() throws ViatraQueryException {
    return PhysicalNode__subPhysicalNodesQuerySpecification.instance();
  }
  
  public PhysicalNode__subPhysicalNodesMatcher getPhysicalNode__subPhysicalNodes(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalNode__subPhysicalNodesMatcher.on(engine);
  }
}
