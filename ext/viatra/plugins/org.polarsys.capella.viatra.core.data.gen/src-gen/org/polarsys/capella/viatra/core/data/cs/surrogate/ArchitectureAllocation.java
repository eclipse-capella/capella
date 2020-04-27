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
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.cs.surrogate.ArchitectureAllocation__allocatedArchitecture;
import org.polarsys.capella.viatra.core.data.cs.surrogate.ArchitectureAllocation__allocatingArchitecture;

/**
 * A pattern group formed of all public patterns defined in ArchitectureAllocation.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ArchitectureAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ArchitectureAllocation__allocatedArchitecture</li>
 * <li>ArchitectureAllocation__allocatingArchitecture</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ArchitectureAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ArchitectureAllocation instance() {
    if (INSTANCE == null) {
        INSTANCE = new ArchitectureAllocation();
    }
    return INSTANCE;
  }
  
  private static ArchitectureAllocation INSTANCE;
  
  private ArchitectureAllocation() {
    querySpecifications.add(ArchitectureAllocation__allocatedArchitecture.instance());
    querySpecifications.add(ArchitectureAllocation__allocatingArchitecture.instance());
  }
  
  public ArchitectureAllocation__allocatedArchitecture getArchitectureAllocation__allocatedArchitecture() {
    return ArchitectureAllocation__allocatedArchitecture.instance();
  }
  
  public ArchitectureAllocation__allocatedArchitecture.Matcher getArchitectureAllocation__allocatedArchitecture(final ViatraQueryEngine engine) {
    return ArchitectureAllocation__allocatedArchitecture.Matcher.on(engine);
  }
  
  public ArchitectureAllocation__allocatingArchitecture getArchitectureAllocation__allocatingArchitecture() {
    return ArchitectureAllocation__allocatingArchitecture.instance();
  }
  
  public ArchitectureAllocation__allocatingArchitecture.Matcher getArchitectureAllocation__allocatingArchitecture(final ViatraQueryEngine engine) {
    return ArchitectureAllocation__allocatingArchitecture.Matcher.on(engine);
  }
}
