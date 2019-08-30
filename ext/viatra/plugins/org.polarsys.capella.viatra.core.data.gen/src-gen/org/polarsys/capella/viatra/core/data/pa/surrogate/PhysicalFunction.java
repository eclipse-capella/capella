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
package org.polarsys.capella.viatra.core.data.pa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalFunction__allocatorPhysicalActorsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalFunction__allocatorPhysicalComponentsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalFunction__childrenPhysicalFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalFunction__containedPhysicalFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalFunction__realizedLogicalFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalFunction__allocatorPhysicalActorsQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalFunction__allocatorPhysicalComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalFunction__childrenPhysicalFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalFunction__containedPhysicalFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.pa.surrogate.util.PhysicalFunction__realizedLogicalFunctionsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in PhysicalFunction.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalFunction.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.pa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalFunction__allocatorPhysicalActors</li>
 * <li>PhysicalFunction__allocatorPhysicalComponents</li>
 * <li>PhysicalFunction__realizedLogicalFunctions</li>
 * <li>PhysicalFunction__containedPhysicalFunctions</li>
 * <li>PhysicalFunction__childrenPhysicalFunctions</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalFunction extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalFunction instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalFunction();
    }
    return INSTANCE;
  }
  
  private static PhysicalFunction INSTANCE;
  
  private PhysicalFunction() throws ViatraQueryException {
    querySpecifications.add(PhysicalFunction__allocatorPhysicalActorsQuerySpecification.instance());
    querySpecifications.add(PhysicalFunction__allocatorPhysicalComponentsQuerySpecification.instance());
    querySpecifications.add(PhysicalFunction__realizedLogicalFunctionsQuerySpecification.instance());
    querySpecifications.add(PhysicalFunction__containedPhysicalFunctionsQuerySpecification.instance());
    querySpecifications.add(PhysicalFunction__childrenPhysicalFunctionsQuerySpecification.instance());
  }
  
  public PhysicalFunction__allocatorPhysicalActorsQuerySpecification getPhysicalFunction__allocatorPhysicalActors() throws ViatraQueryException {
    return PhysicalFunction__allocatorPhysicalActorsQuerySpecification.instance();
  }
  
  public PhysicalFunction__allocatorPhysicalActorsMatcher getPhysicalFunction__allocatorPhysicalActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalFunction__allocatorPhysicalActorsMatcher.on(engine);
  }
  
  public PhysicalFunction__allocatorPhysicalComponentsQuerySpecification getPhysicalFunction__allocatorPhysicalComponents() throws ViatraQueryException {
    return PhysicalFunction__allocatorPhysicalComponentsQuerySpecification.instance();
  }
  
  public PhysicalFunction__allocatorPhysicalComponentsMatcher getPhysicalFunction__allocatorPhysicalComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalFunction__allocatorPhysicalComponentsMatcher.on(engine);
  }
  
  public PhysicalFunction__realizedLogicalFunctionsQuerySpecification getPhysicalFunction__realizedLogicalFunctions() throws ViatraQueryException {
    return PhysicalFunction__realizedLogicalFunctionsQuerySpecification.instance();
  }
  
  public PhysicalFunction__realizedLogicalFunctionsMatcher getPhysicalFunction__realizedLogicalFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalFunction__realizedLogicalFunctionsMatcher.on(engine);
  }
  
  public PhysicalFunction__containedPhysicalFunctionsQuerySpecification getPhysicalFunction__containedPhysicalFunctions() throws ViatraQueryException {
    return PhysicalFunction__containedPhysicalFunctionsQuerySpecification.instance();
  }
  
  public PhysicalFunction__containedPhysicalFunctionsMatcher getPhysicalFunction__containedPhysicalFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalFunction__containedPhysicalFunctionsMatcher.on(engine);
  }
  
  public PhysicalFunction__childrenPhysicalFunctionsQuerySpecification getPhysicalFunction__childrenPhysicalFunctions() throws ViatraQueryException {
    return PhysicalFunction__childrenPhysicalFunctionsQuerySpecification.instance();
  }
  
  public PhysicalFunction__childrenPhysicalFunctionsMatcher getPhysicalFunction__childrenPhysicalFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalFunction__childrenPhysicalFunctionsMatcher.on(engine);
  }
}
