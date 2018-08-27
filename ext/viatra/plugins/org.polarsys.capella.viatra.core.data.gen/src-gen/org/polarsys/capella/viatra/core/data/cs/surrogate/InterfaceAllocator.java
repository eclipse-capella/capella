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
import org.polarsys.capella.viatra.core.data.cs.surrogate.InterfaceAllocator__allocatedInterfacesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.InterfaceAllocator__provisionedInterfaceAllocationsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.InterfaceAllocator__allocatedInterfacesQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.InterfaceAllocator__provisionedInterfaceAllocationsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in InterfaceAllocator.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file InterfaceAllocator.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>InterfaceAllocator__provisionedInterfaceAllocations</li>
 * <li>InterfaceAllocator__allocatedInterfaces</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class InterfaceAllocator extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static InterfaceAllocator instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new InterfaceAllocator();
    }
    return INSTANCE;
  }
  
  private static InterfaceAllocator INSTANCE;
  
  private InterfaceAllocator() throws ViatraQueryException {
    querySpecifications.add(InterfaceAllocator__provisionedInterfaceAllocationsQuerySpecification.instance());
    querySpecifications.add(InterfaceAllocator__allocatedInterfacesQuerySpecification.instance());
  }
  
  public InterfaceAllocator__provisionedInterfaceAllocationsQuerySpecification getInterfaceAllocator__provisionedInterfaceAllocations() throws ViatraQueryException {
    return InterfaceAllocator__provisionedInterfaceAllocationsQuerySpecification.instance();
  }
  
  public InterfaceAllocator__provisionedInterfaceAllocationsMatcher getInterfaceAllocator__provisionedInterfaceAllocations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return InterfaceAllocator__provisionedInterfaceAllocationsMatcher.on(engine);
  }
  
  public InterfaceAllocator__allocatedInterfacesQuerySpecification getInterfaceAllocator__allocatedInterfaces() throws ViatraQueryException {
    return InterfaceAllocator__allocatedInterfacesQuerySpecification.instance();
  }
  
  public InterfaceAllocator__allocatedInterfacesMatcher getInterfaceAllocator__allocatedInterfaces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return InterfaceAllocator__allocatedInterfacesMatcher.on(engine);
  }
}
