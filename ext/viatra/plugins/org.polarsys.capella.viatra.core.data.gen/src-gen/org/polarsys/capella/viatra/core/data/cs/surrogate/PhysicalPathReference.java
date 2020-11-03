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
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPathReference__referencedPhysicalPath;

/**
 * A pattern group formed of all public patterns defined in PhysicalPathReference.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalPathReference.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalPathReference__referencedPhysicalPath</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalPathReference extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalPathReference instance() {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalPathReference();
    }
    return INSTANCE;
  }
  
  private static PhysicalPathReference INSTANCE;
  
  private PhysicalPathReference() {
    querySpecifications.add(PhysicalPathReference__referencedPhysicalPath.instance());
  }
  
  public PhysicalPathReference__referencedPhysicalPath getPhysicalPathReference__referencedPhysicalPath() {
    return PhysicalPathReference__referencedPhysicalPath.instance();
  }
  
  public PhysicalPathReference__referencedPhysicalPath.Matcher getPhysicalPathReference__referencedPhysicalPath(final ViatraQueryEngine engine) {
    return PhysicalPathReference__referencedPhysicalPath.Matcher.on(engine);
  }
}
