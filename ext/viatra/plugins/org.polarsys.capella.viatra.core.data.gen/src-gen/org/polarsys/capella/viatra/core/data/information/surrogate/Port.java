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
import org.polarsys.capella.viatra.core.data.information.surrogate.Port__incomingPortAllocations;
import org.polarsys.capella.viatra.core.data.information.surrogate.Port__incomingPortRealizations;
import org.polarsys.capella.viatra.core.data.information.surrogate.Port__outgoingPortAllocations;
import org.polarsys.capella.viatra.core.data.information.surrogate.Port__outgoingPortRealizations;

/**
 * A pattern group formed of all public patterns defined in Port.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Port extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Port instance() {
    if (INSTANCE == null) {
        INSTANCE = new Port();
    }
    return INSTANCE;
  }
  
  private static Port INSTANCE;
  
  private Port() {
    querySpecifications.add(Port__incomingPortRealizations.instance());
    querySpecifications.add(Port__outgoingPortRealizations.instance());
    querySpecifications.add(Port__incomingPortAllocations.instance());
    querySpecifications.add(Port__outgoingPortAllocations.instance());
  }
  
  public Port__incomingPortRealizations getPort__incomingPortRealizations() {
    return Port__incomingPortRealizations.instance();
  }
  
  public Port__incomingPortRealizations.Matcher getPort__incomingPortRealizations(final ViatraQueryEngine engine) {
    return Port__incomingPortRealizations.Matcher.on(engine);
  }
  
  public Port__outgoingPortRealizations getPort__outgoingPortRealizations() {
    return Port__outgoingPortRealizations.instance();
  }
  
  public Port__outgoingPortRealizations.Matcher getPort__outgoingPortRealizations(final ViatraQueryEngine engine) {
    return Port__outgoingPortRealizations.Matcher.on(engine);
  }
  
  public Port__incomingPortAllocations getPort__incomingPortAllocations() {
    return Port__incomingPortAllocations.instance();
  }
  
  public Port__incomingPortAllocations.Matcher getPort__incomingPortAllocations(final ViatraQueryEngine engine) {
    return Port__incomingPortAllocations.Matcher.on(engine);
  }
  
  public Port__outgoingPortAllocations getPort__outgoingPortAllocations() {
    return Port__outgoingPortAllocations.instance();
  }
  
  public Port__outgoingPortAllocations.Matcher getPort__outgoingPortAllocations(final ViatraQueryEngine engine) {
    return Port__outgoingPortAllocations.Matcher.on(engine);
  }
}
