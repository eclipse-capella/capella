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
import org.polarsys.capella.viatra.core.data.cs.surrogate.InterfaceAllocation__allocatedInterfaceMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.InterfaceAllocation__allocatingInterfaceAllocatorMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.InterfaceAllocation__allocatedInterfaceQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.InterfaceAllocation__allocatingInterfaceAllocatorQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in InterfaceAllocation.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file InterfaceAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>InterfaceAllocation__allocatedInterface</li>
 * <li>InterfaceAllocation__allocatingInterfaceAllocator</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class InterfaceAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static InterfaceAllocation instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new InterfaceAllocation();
    }
    return INSTANCE;
  }
  
  private static InterfaceAllocation INSTANCE;
  
  private InterfaceAllocation() throws ViatraQueryException {
    querySpecifications.add(InterfaceAllocation__allocatedInterfaceQuerySpecification.instance());
    querySpecifications.add(InterfaceAllocation__allocatingInterfaceAllocatorQuerySpecification.instance());
  }
  
  public InterfaceAllocation__allocatedInterfaceQuerySpecification getInterfaceAllocation__allocatedInterface() throws ViatraQueryException {
    return InterfaceAllocation__allocatedInterfaceQuerySpecification.instance();
  }
  
  public InterfaceAllocation__allocatedInterfaceMatcher getInterfaceAllocation__allocatedInterface(final ViatraQueryEngine engine) throws ViatraQueryException {
    return InterfaceAllocation__allocatedInterfaceMatcher.on(engine);
  }
  
  public InterfaceAllocation__allocatingInterfaceAllocatorQuerySpecification getInterfaceAllocation__allocatingInterfaceAllocator() throws ViatraQueryException {
    return InterfaceAllocation__allocatingInterfaceAllocatorQuerySpecification.instance();
  }
  
  public InterfaceAllocation__allocatingInterfaceAllocatorMatcher getInterfaceAllocation__allocatingInterfaceAllocator(final ViatraQueryEngine engine) throws ViatraQueryException {
    return InterfaceAllocation__allocatingInterfaceAllocatorMatcher.on(engine);
  }
}
