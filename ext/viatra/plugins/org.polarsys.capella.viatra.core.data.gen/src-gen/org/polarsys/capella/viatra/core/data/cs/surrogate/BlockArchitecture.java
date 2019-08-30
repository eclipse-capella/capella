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
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.cs.surrogate.BlockArchitecture__allocatedArchitecturesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.BlockArchitecture__allocatingArchitecturesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.BlockArchitecture__provisionedArchitectureAllocationsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.BlockArchitecture__provisioningArchitectureAllocationsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.BlockArchitecture__allocatedArchitecturesQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.BlockArchitecture__allocatingArchitecturesQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.BlockArchitecture__provisionedArchitectureAllocationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.BlockArchitecture__provisioningArchitectureAllocationsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in BlockArchitecture.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
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
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class BlockArchitecture extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static BlockArchitecture instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new BlockArchitecture();
    }
    return INSTANCE;
  }
  
  private static BlockArchitecture INSTANCE;
  
  private BlockArchitecture() throws ViatraQueryException {
    querySpecifications.add(BlockArchitecture__provisionedArchitectureAllocationsQuerySpecification.instance());
    querySpecifications.add(BlockArchitecture__provisioningArchitectureAllocationsQuerySpecification.instance());
    querySpecifications.add(BlockArchitecture__allocatedArchitecturesQuerySpecification.instance());
    querySpecifications.add(BlockArchitecture__allocatingArchitecturesQuerySpecification.instance());
  }
  
  public BlockArchitecture__provisionedArchitectureAllocationsQuerySpecification getBlockArchitecture__provisionedArchitectureAllocations() throws ViatraQueryException {
    return BlockArchitecture__provisionedArchitectureAllocationsQuerySpecification.instance();
  }
  
  public BlockArchitecture__provisionedArchitectureAllocationsMatcher getBlockArchitecture__provisionedArchitectureAllocations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return BlockArchitecture__provisionedArchitectureAllocationsMatcher.on(engine);
  }
  
  public BlockArchitecture__provisioningArchitectureAllocationsQuerySpecification getBlockArchitecture__provisioningArchitectureAllocations() throws ViatraQueryException {
    return BlockArchitecture__provisioningArchitectureAllocationsQuerySpecification.instance();
  }
  
  public BlockArchitecture__provisioningArchitectureAllocationsMatcher getBlockArchitecture__provisioningArchitectureAllocations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return BlockArchitecture__provisioningArchitectureAllocationsMatcher.on(engine);
  }
  
  public BlockArchitecture__allocatedArchitecturesQuerySpecification getBlockArchitecture__allocatedArchitectures() throws ViatraQueryException {
    return BlockArchitecture__allocatedArchitecturesQuerySpecification.instance();
  }
  
  public BlockArchitecture__allocatedArchitecturesMatcher getBlockArchitecture__allocatedArchitectures(final ViatraQueryEngine engine) throws ViatraQueryException {
    return BlockArchitecture__allocatedArchitecturesMatcher.on(engine);
  }
  
  public BlockArchitecture__allocatingArchitecturesQuerySpecification getBlockArchitecture__allocatingArchitectures() throws ViatraQueryException {
    return BlockArchitecture__allocatingArchitecturesQuerySpecification.instance();
  }
  
  public BlockArchitecture__allocatingArchitecturesMatcher getBlockArchitecture__allocatingArchitectures(final ViatraQueryEngine engine) throws ViatraQueryException {
    return BlockArchitecture__allocatingArchitecturesMatcher.on(engine);
  }
}
