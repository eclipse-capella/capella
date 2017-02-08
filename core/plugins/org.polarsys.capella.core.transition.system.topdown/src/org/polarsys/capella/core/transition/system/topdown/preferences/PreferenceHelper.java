/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.topdown.preferences;

import org.polarsys.capella.core.model.preferences.IProjectionPreferences;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.common.flexibility.properties.loader.PropertiesLoader;
import org.polarsys.capella.common.flexibility.properties.property.PropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;

/**
 */
public class PreferenceHelper {

  IProperties properties = new PropertiesLoader().getProperties(ITopDownConstants.OPTIONS_SCOPE__PREFERENCES);
  IPropertyContext propertyContext = new PropertyContext(properties);

  protected boolean getBooleanValue(String id) {
    IProperty property = properties.getProperty(id);
    if (property != null) {
      Object value = property.getValue(propertyContext);
      if (value instanceof String) {
        try {
          return Boolean.valueOf((String) value).booleanValue();
        } catch (Exception e) {
          //Nothing here
        }
      } else if (value instanceof Boolean) {
        return ((Boolean) value).booleanValue();
      }
    }
    return false;
  }

  /**
   * Returns whether the LC2PC strategy is set by the user to Leaf-Strategy
   */
  public boolean isLC2PCLeafStrategy() {
    return getBooleanValue(IProjectionPreferences.PREFS_LCPC_PROJECTION_MODE);
  }

  /**
   * Returns whether the exchange item should be transitioned while transition of interfaces
   */
  public boolean transitionExchangeItemWhileInterfaceTransition() {
    return getBooleanValue(IProjectionPreferences.EXCHANGE_ITEM_PROJECTION);
  }

  /**
   * Returns whether the functional elements should be transitioned while transition of component
   */
  public boolean transitionFunctionalElementWhileComponentTransition() {
    return getBooleanValue(IProjectionPreferences.FUNCTIONAL_ELEMENT_PROJECTION);
  }

  /**
   * Returns whether the exchange item should be transitioned while transition of functional elements (FunctionPort, FunctionalExchange, Connection)
   */
  public boolean transitionExchangeItemWhileFunctionalTransition() {
    return getBooleanValue(IProjectionPreferences.EXCHANGE_ITEM_PROJECTION);
  }

  /**
   * Returns whether the exchange item should be transitioned while transition of communicationLinks
   */
  public boolean transitionExchangeItemWhileComponentTransition() {
    return getBooleanValue(IProjectionPreferences.EXCHANGE_ITEM_PROJECTION);
  }

  /**
   * Returns whether the datatype should be transitioned while transition of exchange items
   */
  public boolean transitionDatatypeWhileExchangeItemTransition() {
    return getBooleanValue(IProjectionPreferences.DATATYPE_PROJECTION);
  }

  /**
   * Returns whether the state machine should be transitioned while transition of component
   */
  public boolean transitionStateMachineWhileComponentTransition() {
    return getBooleanValue(IProjectionPreferences.STATE_MACHINE_PROJECTION);
  }

  /**
   * Returns whether the interface should be transitioned while transition of component
   */
  public boolean transitionInterfaceWhileComponentTransition() {
    return getBooleanValue(IProjectionPreferences.PREFS_INTERFACE_PROJECTION);
  }

  /**
   * Returns whether the interface generation should propagate exchange items form Functional
   * Exchanges to its function ports.
   */
  public boolean generateInterfacesPropagateExchangeItems(){
    return getBooleanValue(IProjectionPreferences.PREFS_INTERFACEGEN_PROPAGATE_EXCHANGE_ITEMS);
  }
  
  /**
   * Returns whether the interface generation should create a component exchange between provider/requirer 
   * component ports.
   */
  public boolean generateInterfacesCreateComponentExchange(){
    return getBooleanValue(IProjectionPreferences.PREFS_INTERFACEGEN_CREATE_COMPONENT_EXCHANGE);
  }
  
  /**
   * Returns whether the interface generation should create standard port instead of flow ports
   */
  @Deprecated
  public boolean generateStandardPortRatherThanFlowPort() {
    return getBooleanValue(IProjectionPreferences.PREFS_USE_STANDARDPORT_INSTEAD_FLOWPORT);
  }

  /**
   * Returns whether the interface generation should generate ports and allocate interface to ports
   */
  @Deprecated
  public boolean generateComponentPort() {
    return getBooleanValue(IProjectionPreferences.PREFS_GENERATE_COMPONENTPORT);
  }
}
