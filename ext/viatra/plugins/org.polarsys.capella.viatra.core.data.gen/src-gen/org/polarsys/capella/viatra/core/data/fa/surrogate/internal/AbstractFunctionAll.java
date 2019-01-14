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
package org.polarsys.capella.viatra.core.data.fa.surrogate.internal;

import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.fa.surrogate.internal._AbstractFunction__ownedFunctionPkgsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.internal._FunctionPkg__ownedFunctionPkgsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.internal._FunctionPkg__ownedFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__allocationBlocksQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__componentFunctionalAllocationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__inFunctionRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__involvingCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__involvingCapabilityRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__involvingFunctionalChainsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__linkedFunctionSpecificationQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__linkedStateMachineQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__outFunctionRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__subFunctionsQuerySpecification;

/**
 * A pattern group formed of all patterns defined in AbstractFunction.vql.
 * 
 * <p>A private group that includes private patterns as well. Only intended use case is for pattern testing.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractFunction__subFunctions</li>
 * <li>_AbstractFunction__ownedFunctionPkgs</li>
 * <li>_FunctionPkg__ownedFunctionPkgs</li>
 * <li>_FunctionPkg__ownedFunctions</li>
 * <li>AbstractFunction__outFunctionRealizations</li>
 * <li>AbstractFunction__inFunctionRealizations</li>
 * <li>AbstractFunction__componentFunctionalAllocations</li>
 * <li>AbstractFunction__allocationBlocks</li>
 * <li>AbstractFunction__involvingCapabilities</li>
 * <li>AbstractFunction__involvingCapabilityRealizations</li>
 * <li>AbstractFunction__involvingFunctionalChains</li>
 * <li>AbstractFunction__linkedStateMachine</li>
 * <li>AbstractFunction__linkedFunctionSpecification</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractFunctionAll extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractFunctionAll instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractFunctionAll();
    }
    return INSTANCE;
  }
  
  private static AbstractFunctionAll INSTANCE;
  
  private AbstractFunctionAll() throws ViatraQueryException {
    querySpecifications.add(AbstractFunction__subFunctionsQuerySpecification.instance());
    querySpecifications.add(_AbstractFunction__ownedFunctionPkgsQuerySpecification.instance());
    querySpecifications.add(_FunctionPkg__ownedFunctionPkgsQuerySpecification.instance());
    querySpecifications.add(_FunctionPkg__ownedFunctionsQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__outFunctionRealizationsQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__inFunctionRealizationsQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__componentFunctionalAllocationsQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__allocationBlocksQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__involvingCapabilitiesQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__involvingCapabilityRealizationsQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__involvingFunctionalChainsQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__linkedStateMachineQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__linkedFunctionSpecificationQuerySpecification.instance());
  }
}
