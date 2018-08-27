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
package org.polarsys.capella.viatra.core.data.information.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.information.surrogate.Port__incomingPortAllocationsMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.Port__incomingPortRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.Port__outgoingPortAllocationsMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.Port__outgoingPortRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.Port__incomingPortAllocationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.Port__incomingPortRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.Port__outgoingPortAllocationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.Port__outgoingPortRealizationsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Port.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Port.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Port__incomingPortRealizations</li>
 * <li>Port__outgoingPortRealizations</li>
 * <li>Port__incomingPortAllocations</li>
 * <li>Port__outgoingPortAllocations</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Port extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Port instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Port();
    }
    return INSTANCE;
  }
  
  private static Port INSTANCE;
  
  private Port() throws ViatraQueryException {
    querySpecifications.add(Port__incomingPortRealizationsQuerySpecification.instance());
    querySpecifications.add(Port__outgoingPortRealizationsQuerySpecification.instance());
    querySpecifications.add(Port__incomingPortAllocationsQuerySpecification.instance());
    querySpecifications.add(Port__outgoingPortAllocationsQuerySpecification.instance());
  }
  
  public Port__incomingPortRealizationsQuerySpecification getPort__incomingPortRealizations() throws ViatraQueryException {
    return Port__incomingPortRealizationsQuerySpecification.instance();
  }
  
  public Port__incomingPortRealizationsMatcher getPort__incomingPortRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Port__incomingPortRealizationsMatcher.on(engine);
  }
  
  public Port__outgoingPortRealizationsQuerySpecification getPort__outgoingPortRealizations() throws ViatraQueryException {
    return Port__outgoingPortRealizationsQuerySpecification.instance();
  }
  
  public Port__outgoingPortRealizationsMatcher getPort__outgoingPortRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Port__outgoingPortRealizationsMatcher.on(engine);
  }
  
  public Port__incomingPortAllocationsQuerySpecification getPort__incomingPortAllocations() throws ViatraQueryException {
    return Port__incomingPortAllocationsQuerySpecification.instance();
  }
  
  public Port__incomingPortAllocationsMatcher getPort__incomingPortAllocations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Port__incomingPortAllocationsMatcher.on(engine);
  }
  
  public Port__outgoingPortAllocationsQuerySpecification getPort__outgoingPortAllocations() throws ViatraQueryException {
    return Port__outgoingPortAllocationsQuerySpecification.instance();
  }
  
  public Port__outgoingPortAllocationsMatcher getPort__outgoingPortAllocations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Port__outgoingPortAllocationsMatcher.on(engine);
  }
}
