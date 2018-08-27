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
package org.polarsys.capella.viatra.common.data.core.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.common.data.core.surrogate.InformationsExchanger__incomingInformationFlowsMatcher;
import org.polarsys.capella.viatra.common.data.core.surrogate.InformationsExchanger__informationFlowsMatcher;
import org.polarsys.capella.viatra.common.data.core.surrogate.InformationsExchanger__outgoingInformationFlowsMatcher;
import org.polarsys.capella.viatra.common.data.core.surrogate.util.InformationsExchanger__incomingInformationFlowsQuerySpecification;
import org.polarsys.capella.viatra.common.data.core.surrogate.util.InformationsExchanger__informationFlowsQuerySpecification;
import org.polarsys.capella.viatra.common.data.core.surrogate.util.InformationsExchanger__outgoingInformationFlowsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in InformationsExchanger.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file InformationsExchanger.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.data.core.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>InformationsExchanger__incomingInformationFlows</li>
 * <li>InformationsExchanger__outgoingInformationFlows</li>
 * <li>InformationsExchanger__informationFlows</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class InformationsExchanger extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static InformationsExchanger instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new InformationsExchanger();
    }
    return INSTANCE;
  }
  
  private static InformationsExchanger INSTANCE;
  
  private InformationsExchanger() throws ViatraQueryException {
    querySpecifications.add(InformationsExchanger__incomingInformationFlowsQuerySpecification.instance());
    querySpecifications.add(InformationsExchanger__outgoingInformationFlowsQuerySpecification.instance());
    querySpecifications.add(InformationsExchanger__informationFlowsQuerySpecification.instance());
  }
  
  public InformationsExchanger__incomingInformationFlowsQuerySpecification getInformationsExchanger__incomingInformationFlows() throws ViatraQueryException {
    return InformationsExchanger__incomingInformationFlowsQuerySpecification.instance();
  }
  
  public InformationsExchanger__incomingInformationFlowsMatcher getInformationsExchanger__incomingInformationFlows(final ViatraQueryEngine engine) throws ViatraQueryException {
    return InformationsExchanger__incomingInformationFlowsMatcher.on(engine);
  }
  
  public InformationsExchanger__outgoingInformationFlowsQuerySpecification getInformationsExchanger__outgoingInformationFlows() throws ViatraQueryException {
    return InformationsExchanger__outgoingInformationFlowsQuerySpecification.instance();
  }
  
  public InformationsExchanger__outgoingInformationFlowsMatcher getInformationsExchanger__outgoingInformationFlows(final ViatraQueryEngine engine) throws ViatraQueryException {
    return InformationsExchanger__outgoingInformationFlowsMatcher.on(engine);
  }
  
  public InformationsExchanger__informationFlowsQuerySpecification getInformationsExchanger__informationFlows() throws ViatraQueryException {
    return InformationsExchanger__informationFlowsQuerySpecification.instance();
  }
  
  public InformationsExchanger__informationFlowsMatcher getInformationsExchanger__informationFlows(final ViatraQueryEngine engine) throws ViatraQueryException {
    return InformationsExchanger__informationFlowsMatcher.on(engine);
  }
}
