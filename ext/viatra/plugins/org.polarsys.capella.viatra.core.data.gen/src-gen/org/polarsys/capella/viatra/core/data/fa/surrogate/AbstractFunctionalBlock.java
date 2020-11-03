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
package org.polarsys.capella.viatra.core.data.fa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunctionalBlock__allocatedFunctions;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunctionalBlock__functionalAllocations;

/**
 * A pattern group formed of all public patterns defined in AbstractFunctionalBlock.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractFunctionalBlock.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractFunctionalBlock__functionalAllocations</li>
 * <li>AbstractFunctionalBlock__allocatedFunctions</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractFunctionalBlock extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractFunctionalBlock instance() {
    if (INSTANCE == null) {
        INSTANCE = new AbstractFunctionalBlock();
    }
    return INSTANCE;
  }
  
  private static AbstractFunctionalBlock INSTANCE;
  
  private AbstractFunctionalBlock() {
    querySpecifications.add(AbstractFunctionalBlock__functionalAllocations.instance());
    querySpecifications.add(AbstractFunctionalBlock__allocatedFunctions.instance());
  }
  
  public AbstractFunctionalBlock__functionalAllocations getAbstractFunctionalBlock__functionalAllocations() {
    return AbstractFunctionalBlock__functionalAllocations.instance();
  }
  
  public AbstractFunctionalBlock__functionalAllocations.Matcher getAbstractFunctionalBlock__functionalAllocations(final ViatraQueryEngine engine) {
    return AbstractFunctionalBlock__functionalAllocations.Matcher.on(engine);
  }
  
  public AbstractFunctionalBlock__allocatedFunctions getAbstractFunctionalBlock__allocatedFunctions() {
    return AbstractFunctionalBlock__allocatedFunctions.instance();
  }
  
  public AbstractFunctionalBlock__allocatedFunctions.Matcher getAbstractFunctionalBlock__allocatedFunctions(final ViatraQueryEngine engine) {
    return AbstractFunctionalBlock__allocatedFunctions.Matcher.on(engine);
  }
}
