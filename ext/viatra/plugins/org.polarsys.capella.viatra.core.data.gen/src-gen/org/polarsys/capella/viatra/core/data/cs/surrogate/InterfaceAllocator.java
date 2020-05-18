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
import org.polarsys.capella.viatra.core.data.cs.surrogate.InterfaceAllocator__allocatedInterfaces;
import org.polarsys.capella.viatra.core.data.cs.surrogate.InterfaceAllocator__provisionedInterfaceAllocations;

/**
 * A pattern group formed of all public patterns defined in InterfaceAllocator.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file InterfaceAllocator.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>InterfaceAllocator__provisionedInterfaceAllocations</li>
 * <li>InterfaceAllocator__allocatedInterfaces</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class InterfaceAllocator extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static InterfaceAllocator instance() {
    if (INSTANCE == null) {
        INSTANCE = new InterfaceAllocator();
    }
    return INSTANCE;
  }
  
  private static InterfaceAllocator INSTANCE;
  
  private InterfaceAllocator() {
    querySpecifications.add(InterfaceAllocator__provisionedInterfaceAllocations.instance());
    querySpecifications.add(InterfaceAllocator__allocatedInterfaces.instance());
  }
  
  public InterfaceAllocator__provisionedInterfaceAllocations getInterfaceAllocator__provisionedInterfaceAllocations() {
    return InterfaceAllocator__provisionedInterfaceAllocations.instance();
  }
  
  public InterfaceAllocator__provisionedInterfaceAllocations.Matcher getInterfaceAllocator__provisionedInterfaceAllocations(final ViatraQueryEngine engine) {
    return InterfaceAllocator__provisionedInterfaceAllocations.Matcher.on(engine);
  }
  
  public InterfaceAllocator__allocatedInterfaces getInterfaceAllocator__allocatedInterfaces() {
    return InterfaceAllocator__allocatedInterfaces.instance();
  }
  
  public InterfaceAllocator__allocatedInterfaces.Matcher getInterfaceAllocator__allocatedInterfaces(final ViatraQueryEngine engine) {
    return InterfaceAllocator__allocatedInterfaces.Matcher.on(engine);
  }
}
