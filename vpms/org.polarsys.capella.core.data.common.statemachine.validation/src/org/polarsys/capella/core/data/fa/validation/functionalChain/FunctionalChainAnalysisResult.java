/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.validation.functionalChain;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.vp.ms.CSConfiguration;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;


public final class FunctionalChainAnalysisResult {
  private FunctionalChain fc;
  private State state;
  private Component component;
  private CSConfiguration configuration;
  private EList<AbstractFunction> missingFunctions;

  public FunctionalChainAnalysisResult(FunctionalChain fc, State state, Component component, CSConfiguration configuration,
      EList<AbstractFunction> commonFunctions) {
    this.fc = fc;
    this.state = state;
    this.component = component;
    this.configuration = configuration;
    this.missingFunctions = commonFunctions;
  }

  /**
   * @return the fc
   */
  public FunctionalChain getFc() {
    return fc;
  }

  /**
   * @param fc the fc to set
   */
  public void setFc(FunctionalChain fc) {
    this.fc = fc;
  }

  /**
   * @return the state
   */
  public State getState() {
    return state;
  }

  /**
   * @param state the state to set
   */
  public void setState(State state) {
    this.state = state;
  }

  /**
   * @return the component
   */
  public Component getComponent() {
    return component;
  }

  /**
   * @param component the component to set
   */
  public void setComponent(Component component) {
    this.component = component;
  }

  /**
   * @return the configuration
   */
  public CSConfiguration getConfiguration() {
    return configuration;
  }

  /**
   * @param configuration the configuration to set
   */
  public void setConfiguration(CSConfiguration configuration) {
    this.configuration = configuration;
  }

  /**
   * @return the missingFunctions
   */
  public EList<AbstractFunction> getMissingFunctions() {
    return missingFunctions;
  }

  /**
   * @param missingFunctions the missingFunctions to set
   */
  public void setMissingFunctions(EList<AbstractFunction> missingFunctions) {
    this.missingFunctions = missingFunctions;
  }

  /**
   * Returns a human readable string for this result.
   */
  public String toReadableString() {
    if (getMissingFunctions() != null && getMissingFunctions().size() > 0) {
      return "The functional chain '" + getFc().getName() + "' is not assured in the State/Mode '" //$NON-NLS-1$ //$NON-NLS-2$
             + getState().getName() + "' of the Component '" //$NON-NLS-1$
             + getComponent().getName() + "' because the functions '" //$NON-NLS-1$
             + Joiner.on(", ").join(Collections2.transform(getMissingFunctions(), new Function<NamedElement, String>() {
               public String apply(NamedElement o) {
                 return o.getName();
               }
             })) + "' are not available in the configuration '" // $NON-NLS-2$
             + getConfiguration().getName() + "'."; //$NON-NLS-1$
    } else {
      return "The functional chain '" + getFc().getName() + "' is not assured in the State/Mode '" //$NON-NLS-1$ //$NON-NLS-2$
             + getState().getName() + "' of the Component '" //$NON-NLS-1$
             + getComponent().getName() + "' because no configuration is selected in that State/Mode."; //$NON-NLS-1$
    }
  }

}
