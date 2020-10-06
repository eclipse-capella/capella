/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.topdown.preferences;

import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.common.flexibility.properties.loader.PropertiesLoader;
import org.polarsys.capella.common.flexibility.properties.property.PropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;

/**
 */
public class PreferenceHelper {

  private IProperties properties;
  private IPropertyContext propertyContext;
  
  private static PreferenceHelper instance;
  
  private PreferenceHelper() {

    properties = new PropertiesLoader().getProperties(ITopDownConstants.OPTIONS_SCOPE__PREFERENCES);
    propertyContext = new PropertyContext(properties);
  }
  
  // Singleton
  public static PreferenceHelper getInstance() {
    if (null == instance) {
      instance = new PreferenceHelper();
    }    
    return instance;
  }
  
  
  public IPropertyContext getPropertyContext() {
    return this.propertyContext;
  }
  
  public IProperties getProperties() {
    return this.properties;
  }

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
  
  protected String getStringValue(String id, String defaultValue) {
    IProperty property = properties.getProperty(id);
    if (property != null) {
      Object value = property.getValue(propertyContext);
      if(value != null) {
        return value.toString();
      }
    }
    return defaultValue;
  }
  
  public void setBooleanValue(String id, Boolean value) {
    
    IProperty property = properties.getProperty(id);
    
    propertyContext.setCurrentValue(property, value);
    
    if (property instanceof BooleanPropertyPreference) {
      BooleanPropertyPreference booleanPreference = (BooleanPropertyPreference) property;
      booleanPreference.setValue(this.propertyContext);
    }
  }

  /**
   * Returns whether the LC2PC strategy is set by the user to Leaf-Strategy
   */
  public boolean isLC2PCLeafStrategy() {
    return getBooleanValue(ITopDownConstants.OPTIONS_TRANSITION__LCPC);
  }

  /**
   * Returns whether the exchange item should be transitioned while transition of interfaces
   */ 
  public boolean transitionExchangeItemWhileInterfaceTransition() {
    return getBooleanValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM);
  }

  /**
   * Returns whether the functional elements should be transitioned while transition of component
   */
  public boolean transitionFunctionalElementWhileComponentTransition() {
    return getBooleanValue(ITopDownConstants.OPTIONS_TRANSITION__FUNCTIONAL);
  }

  /**
   * Returns whether the exchange item should be transitioned while transition of functional elements (FunctionPort, FunctionalExchange, Connection)
   */
  public boolean transitionExchangeItemWhileFunctionalTransition() {
    return getBooleanValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM);
  }

  /**
   * Returns whether the exchange item should be transitioned while transition of communicationLinks
   */
  public boolean transitionExchangeItemWhileComponentTransition() {
    return getBooleanValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM);
  }

  /**
   * Returns whether the datatype should be transitioned while transition of exchange items
   */
  public boolean transitionDatatypeWhileExchangeItemTransition() {
    return getBooleanValue(ITopDownConstants.OPTIONS_TRANSITION__DATATYPE);
  }

  /**
   * Returns whether the state machine should be transitioned while transition of component
   */
  public boolean transitionStateMachineWhileComponentTransition() {
    return getBooleanValue(ITopDownConstants.OPTIONS_TRANSITION__STATE_MACHINE);
  }

  /**
   * Returns whether the interface should be transitioned while transition of component
   */
  public boolean transitionInterfaceWhileComponentTransition() {
    return getBooleanValue(ITopDownConstants.OPTIONS_TRANSITION__INTERFACE);
  }

  /**
   * Returns whether the interface generation should propagate exchange items form Functional
   * Exchanges to its function ports.
   */
  public boolean generateInterfacesPropagateExchangeItems(){
    return getBooleanValue(ITopDownConstants.OPTIONS_INTERFACEGEN_PROPAGATE_EXCHANGE_ITEMS);
  }
  
  /**
   * Returns whether the interface generation should create a component exchange between provider/requirer 
   * component ports.
   */
  public boolean generateInterfacesCreateComponentExchange(){
    return getBooleanValue(ITopDownConstants.OPTIONS_INTERFACEGEN_CREATE_COMPONENT_EXCHANGE);
  }
  
  public boolean isFC2FSCreateMsgWithReply() {
    return ITopDownConstants.OPTIONS_FC2FS_MESSAGE_WITH_REPLY
        .equals(getStringValue(ITopDownConstants.OPTIONS_FC2FS_SEQUENCE_MESSAGE_STRATEGY,
            ITopDownConstants.OPTIONS_FC2FS_SEQUENCE_MESSAGE_STRATEGY_DEFAULT));
  }

  public boolean isOP2OASCreateMsgWithReply() {
    return ITopDownConstants.OPTIONS_OP2OAS_MESSAGE_WITH_REPLY
        .equals(getStringValue(ITopDownConstants.OPTIONS_OP2OAS_SEQUENCE_MESSAGE_STRATEGY,
            ITopDownConstants.OPTIONS_OP2OAS_SEQUENCE_MESSAGE_STRATEGY_DEFAULT));
  }
  
  /**
   * Returns whether the scenario shall be initialized after a transition
   */
  public boolean transitionInitializeTransitionedScenario() {
    return getBooleanValue(ITopDownConstants.OPTIONS_TRANSITION__SCENARIO_INITIALIZE);
  }
  
  public boolean isFC2FSLogEnabled() {
    return getBooleanValue(ITopDownConstants.OPTIONS_LOG);
  }
  
  public boolean transitionPC2CIWhileScenarioTransition() {
    return getBooleanValue(ITopDownConstants.OPTIONS_TRANSITION__PCCI_ENABLED);
  }
  
  public String getConfigurationItemKind() {
    return getStringValue(ITopDownConstants.OPTIONS_TRANSITION__PCCI_KIND,
        ITopDownConstants.OPTIONS_TRANSITION__PCCI_KIND_DEFAULT);
  }
}
