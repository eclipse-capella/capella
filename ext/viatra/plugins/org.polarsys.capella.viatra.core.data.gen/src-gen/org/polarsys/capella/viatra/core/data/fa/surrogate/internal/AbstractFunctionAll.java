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
package org.polarsys.capella.viatra.core.data.fa.surrogate.internal;

import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__allocationBlocks;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__componentFunctionalAllocations;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__inFunctionRealizations;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__involvingCapabilities;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__involvingCapabilityRealizations;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__involvingFunctionalChains;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__linkedFunctionSpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__linkedStateMachine;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__outFunctionRealizations;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__subFunctions;
import org.polarsys.capella.viatra.core.data.fa.surrogate.internal._AbstractFunction__ownedFunctionPkgs;
import org.polarsys.capella.viatra.core.data.fa.surrogate.internal._FunctionPkg__ownedFunctionPkgs;
import org.polarsys.capella.viatra.core.data.fa.surrogate.internal._FunctionPkg__ownedFunctions;

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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractFunctionAll extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractFunctionAll instance() {
    if (INSTANCE == null) {
        INSTANCE = new AbstractFunctionAll();
    }
    return INSTANCE;
  }
  
  private static AbstractFunctionAll INSTANCE;
  
  private AbstractFunctionAll() {
    querySpecifications.add(AbstractFunction__subFunctions.instance());
    querySpecifications.add(_AbstractFunction__ownedFunctionPkgs.instance());
    querySpecifications.add(_FunctionPkg__ownedFunctionPkgs.instance());
    querySpecifications.add(_FunctionPkg__ownedFunctions.instance());
    querySpecifications.add(AbstractFunction__outFunctionRealizations.instance());
    querySpecifications.add(AbstractFunction__inFunctionRealizations.instance());
    querySpecifications.add(AbstractFunction__componentFunctionalAllocations.instance());
    querySpecifications.add(AbstractFunction__allocationBlocks.instance());
    querySpecifications.add(AbstractFunction__involvingCapabilities.instance());
    querySpecifications.add(AbstractFunction__involvingCapabilityRealizations.instance());
    querySpecifications.add(AbstractFunction__involvingFunctionalChains.instance());
    querySpecifications.add(AbstractFunction__linkedStateMachine.instance());
    querySpecifications.add(AbstractFunction__linkedFunctionSpecification.instance());
  }
}
