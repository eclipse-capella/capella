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
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.cs.surrogate.BlockArchitecture__allocatedArchitectures;
import org.polarsys.capella.viatra.core.data.cs.surrogate.BlockArchitecture__allocatingArchitectures;
import org.polarsys.capella.viatra.core.data.cs.surrogate.BlockArchitecture__provisionedArchitectureAllocations;
import org.polarsys.capella.viatra.core.data.cs.surrogate.BlockArchitecture__provisioningArchitectureAllocations;

/**
 * A pattern group formed of all public patterns defined in BlockArchitecture.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file BlockArchitecture.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>BlockArchitecture__provisionedArchitectureAllocations</li>
 * <li>BlockArchitecture__provisioningArchitectureAllocations</li>
 * <li>BlockArchitecture__allocatedArchitectures</li>
 * <li>BlockArchitecture__allocatingArchitectures</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class BlockArchitecture extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static BlockArchitecture instance() {
    if (INSTANCE == null) {
        INSTANCE = new BlockArchitecture();
    }
    return INSTANCE;
  }
  
  private static BlockArchitecture INSTANCE;
  
  private BlockArchitecture() {
    querySpecifications.add(BlockArchitecture__provisionedArchitectureAllocations.instance());
    querySpecifications.add(BlockArchitecture__provisioningArchitectureAllocations.instance());
    querySpecifications.add(BlockArchitecture__allocatedArchitectures.instance());
    querySpecifications.add(BlockArchitecture__allocatingArchitectures.instance());
  }
  
  public BlockArchitecture__provisionedArchitectureAllocations getBlockArchitecture__provisionedArchitectureAllocations() {
    return BlockArchitecture__provisionedArchitectureAllocations.instance();
  }
  
  public BlockArchitecture__provisionedArchitectureAllocations.Matcher getBlockArchitecture__provisionedArchitectureAllocations(final ViatraQueryEngine engine) {
    return BlockArchitecture__provisionedArchitectureAllocations.Matcher.on(engine);
  }
  
  public BlockArchitecture__provisioningArchitectureAllocations getBlockArchitecture__provisioningArchitectureAllocations() {
    return BlockArchitecture__provisioningArchitectureAllocations.instance();
  }
  
  public BlockArchitecture__provisioningArchitectureAllocations.Matcher getBlockArchitecture__provisioningArchitectureAllocations(final ViatraQueryEngine engine) {
    return BlockArchitecture__provisioningArchitectureAllocations.Matcher.on(engine);
  }
  
  public BlockArchitecture__allocatedArchitectures getBlockArchitecture__allocatedArchitectures() {
    return BlockArchitecture__allocatedArchitectures.instance();
  }
  
  public BlockArchitecture__allocatedArchitectures.Matcher getBlockArchitecture__allocatedArchitectures(final ViatraQueryEngine engine) {
    return BlockArchitecture__allocatedArchitectures.Matcher.on(engine);
  }
  
  public BlockArchitecture__allocatingArchitectures getBlockArchitecture__allocatingArchitectures() {
    return BlockArchitecture__allocatingArchitectures.instance();
  }
  
  public BlockArchitecture__allocatingArchitectures.Matcher getBlockArchitecture__allocatingArchitectures(final ViatraQueryEngine engine) {
    return BlockArchitecture__allocatingArchitectures.Matcher.on(engine);
  }
}
