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
package org.polarsys.capella.viatra.common.data.core.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.common.data.core.surrogate.InformationsExchanger__incomingInformationFlows;
import org.polarsys.capella.viatra.common.data.core.surrogate.InformationsExchanger__informationFlows;
import org.polarsys.capella.viatra.common.data.core.surrogate.InformationsExchanger__outgoingInformationFlows;

/**
 * A pattern group formed of all public patterns defined in InformationsExchanger.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file InformationsExchanger.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.data.core.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>InformationsExchanger__incomingInformationFlows</li>
 * <li>InformationsExchanger__outgoingInformationFlows</li>
 * <li>InformationsExchanger__informationFlows</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class InformationsExchanger extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static InformationsExchanger instance() {
    if (INSTANCE == null) {
        INSTANCE = new InformationsExchanger();
    }
    return INSTANCE;
  }
  
  private static InformationsExchanger INSTANCE;
  
  private InformationsExchanger() {
    querySpecifications.add(InformationsExchanger__incomingInformationFlows.instance());
    querySpecifications.add(InformationsExchanger__outgoingInformationFlows.instance());
    querySpecifications.add(InformationsExchanger__informationFlows.instance());
  }
  
  public InformationsExchanger__incomingInformationFlows getInformationsExchanger__incomingInformationFlows() {
    return InformationsExchanger__incomingInformationFlows.instance();
  }
  
  public InformationsExchanger__incomingInformationFlows.Matcher getInformationsExchanger__incomingInformationFlows(final ViatraQueryEngine engine) {
    return InformationsExchanger__incomingInformationFlows.Matcher.on(engine);
  }
  
  public InformationsExchanger__outgoingInformationFlows getInformationsExchanger__outgoingInformationFlows() {
    return InformationsExchanger__outgoingInformationFlows.instance();
  }
  
  public InformationsExchanger__outgoingInformationFlows.Matcher getInformationsExchanger__outgoingInformationFlows(final ViatraQueryEngine engine) {
    return InformationsExchanger__outgoingInformationFlows.Matcher.on(engine);
  }
  
  public InformationsExchanger__informationFlows getInformationsExchanger__informationFlows() {
    return InformationsExchanger__informationFlows.instance();
  }
  
  public InformationsExchanger__informationFlows.Matcher getInformationsExchanger__informationFlows(final ViatraQueryEngine engine) {
    return InformationsExchanger__informationFlows.Matcher.on(engine);
  }
}
