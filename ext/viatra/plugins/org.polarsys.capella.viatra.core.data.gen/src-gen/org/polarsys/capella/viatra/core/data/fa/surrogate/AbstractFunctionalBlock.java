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
package org.polarsys.capella.viatra.core.data.fa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunctionalBlock__allocatedFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunctionalBlock__functionalAllocationsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunctionalBlock__allocatedFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunctionalBlock__functionalAllocationsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractFunctionalBlock.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractFunctionalBlock.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractFunctionalBlock__functionalAllocations</li>
 * <li>AbstractFunctionalBlock__allocatedFunctions</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractFunctionalBlock extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractFunctionalBlock instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractFunctionalBlock();
    }
    return INSTANCE;
  }
  
  private static AbstractFunctionalBlock INSTANCE;
  
  private AbstractFunctionalBlock() throws ViatraQueryException {
    querySpecifications.add(AbstractFunctionalBlock__functionalAllocationsQuerySpecification.instance());
    querySpecifications.add(AbstractFunctionalBlock__allocatedFunctionsQuerySpecification.instance());
  }
  
  public AbstractFunctionalBlock__functionalAllocationsQuerySpecification getAbstractFunctionalBlock__functionalAllocations() throws ViatraQueryException {
    return AbstractFunctionalBlock__functionalAllocationsQuerySpecification.instance();
  }
  
  public AbstractFunctionalBlock__functionalAllocationsMatcher getAbstractFunctionalBlock__functionalAllocations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractFunctionalBlock__functionalAllocationsMatcher.on(engine);
  }
  
  public AbstractFunctionalBlock__allocatedFunctionsQuerySpecification getAbstractFunctionalBlock__allocatedFunctions() throws ViatraQueryException {
    return AbstractFunctionalBlock__allocatedFunctionsQuerySpecification.instance();
  }
  
  public AbstractFunctionalBlock__allocatedFunctionsMatcher getAbstractFunctionalBlock__allocatedFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractFunctionalBlock__allocatedFunctionsMatcher.on(engine);
  }
}
