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
package org.polarsys.capella.viatra.core.data.information.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.information.surrogate.OperationAllocation__allocatedOperation;
import org.polarsys.capella.viatra.core.data.information.surrogate.OperationAllocation__allocatingOperation;

/**
 * A pattern group formed of all public patterns defined in OperationAllocation.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file OperationAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>OperationAllocation__allocatedOperation</li>
 * <li>OperationAllocation__allocatingOperation</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class OperationAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static OperationAllocation instance() {
    if (INSTANCE == null) {
        INSTANCE = new OperationAllocation();
    }
    return INSTANCE;
  }
  
  private static OperationAllocation INSTANCE;
  
  private OperationAllocation() {
    querySpecifications.add(OperationAllocation__allocatedOperation.instance());
    querySpecifications.add(OperationAllocation__allocatingOperation.instance());
  }
  
  public OperationAllocation__allocatedOperation getOperationAllocation__allocatedOperation() {
    return OperationAllocation__allocatedOperation.instance();
  }
  
  public OperationAllocation__allocatedOperation.Matcher getOperationAllocation__allocatedOperation(final ViatraQueryEngine engine) {
    return OperationAllocation__allocatedOperation.Matcher.on(engine);
  }
  
  public OperationAllocation__allocatingOperation getOperationAllocation__allocatingOperation() {
    return OperationAllocation__allocatingOperation.instance();
  }
  
  public OperationAllocation__allocatingOperation.Matcher getOperationAllocation__allocatingOperation(final ViatraQueryEngine engine) {
    return OperationAllocation__allocatingOperation.Matcher.on(engine);
  }
}
