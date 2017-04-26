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
package org.polarsys.capella.core.projection.interfaces;

import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;

/**
 * Preferences to steer interface generation.
 */
public class InterfaceGenerationPreferences {

  /**
   * Should the generator create a component exchange between unconnected component ports and allocate the connected
   * functional exchanges to the generated component exchange?
   */
  public boolean isGenerateComponentExchanges() {
    return new PreferenceHelper().generateInterfacesCreateComponentExchange();
  }

  /**
   * Should the generator propagate exchange items for all transformed functional exchanges to its function ports.
   */
  public boolean isPropagateExchangeItemsToFunctionPorts() {
    return new PreferenceHelper().generateInterfacesPropagateExchangeItems();
  }
  
  /**
   * Should exchange items from Component Exchanges be allocated to associated generated Interfaces
   * Currently always true.
   */
  public boolean includeExchangeItemsFromComponentExchanges(){
    return true;
  }
  
  /**
   * Should exchange items From Functional Exchanges be allocated to associated generated Interfaces
   * This is fix atm, but ppl will ask for this to be configurable.
   * Currently always true.
   */
  public boolean includeExchangeItemsFromFunctionalExchanges(){
    return true;
  }

}
