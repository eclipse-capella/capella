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

/**
 * A pattern group formed of all public patterns defined in InterfaceImplementation.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file InterfaceImplementation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>InterfaceImplementation__interfaceImplementor</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class InterfaceImplementation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static InterfaceImplementation instance() {
    if (INSTANCE == null) {
        INSTANCE = new InterfaceImplementation();
    }
    return INSTANCE;
  }
  
  private static InterfaceImplementation INSTANCE;
  
  private InterfaceImplementation() {
    querySpecifications.add(InterfaceImplementation__interfaceImplementor.instance());
  }
  
  public InterfaceImplementation__interfaceImplementor getInterfaceImplementation__interfaceImplementor() {
    return InterfaceImplementation__interfaceImplementor.instance();
  }
  
  public InterfaceImplementation__interfaceImplementor.Matcher getInterfaceImplementation__interfaceImplementor(final ViatraQueryEngine engine) {
    return InterfaceImplementation__interfaceImplementor.Matcher.on(engine);
  }
}
