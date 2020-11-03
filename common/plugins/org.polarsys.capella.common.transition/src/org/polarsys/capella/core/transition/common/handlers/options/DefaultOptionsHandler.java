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

package org.polarsys.capella.core.transition.common.handlers.options;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.common.flexibility.properties.loader.PropertiesLoader;
import org.polarsys.capella.common.flexibility.properties.property.PropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class DefaultOptionsHandler implements IOptionsHandler, IPropertyHandler {

  public static final String PROPERTIES = "PROPERTIES_"; //$NON-NLS-1$

  public static final String PROPERTIES_CONTEXT = "PROPERTIES_CONTEXT"; //$NON-NLS-1$

  public IProperties getProperties(IContext context, String scope) {
    IProperties elements = (IProperties) context.get(PROPERTIES + scope);
    if (elements == null) {
      elements = new PropertiesLoader().getProperties(scope);
      context.put(PROPERTIES + scope, elements);
    }
    return elements;
  }

  public IPropertyContext getPropertyContext(IContext context, String scope) {
    IPropertyContext propertyContext = (IPropertyContext) context.get(PROPERTIES_CONTEXT + scope);
    if (propertyContext == null) {
      IProperties elements = getProperties(context, scope);
      propertyContext = new PropertyContext(elements, context);
      context.put(PROPERTIES_CONTEXT + scope, propertyContext);
    }
    return propertyContext;
  }

  public String getStringValue(IContext context, String scope, String key, String string1) {
    // Retrieve value from parameters if set. Otherwise, from preferences
    Object parameters = context.get(ITransitionConstants.OPTIONS_PARAMETERS);
    if ((parameters != null) && (parameters instanceof ActivityParameters)) {
      GenericParameter<?> param = ((ActivityParameters) parameters).getParameter(key);
      if ((param != null) && String.class.isAssignableFrom(param.getParameterType())) {
        return (String) param.getValue();
      }
    }

    String optionScope = (String) context.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext propertyContext = getPropertyContext(context, optionScope);
    IProperty property = propertyContext.getProperties().getProperty(key);
    if (property != null) {
      Object value = propertyContext.getCurrentValue(property);
      if (value instanceof String) {
        return (String) value;
      }
    }
    return string1;
  }

  public boolean getBooleanValue(IContext context, String scope, String key, boolean default1) {
    // Retrieve value from parameters if set. Otherwise, from preferences
    Object parameters = context.get(ITransitionConstants.OPTIONS_PARAMETERS);
    if ((parameters != null) && (parameters instanceof ActivityParameters)) {
      GenericParameter<?> param = ((ActivityParameters) parameters).getParameter(key);
      if ((param != null) && Boolean.class.isAssignableFrom(param.getParameterType())) {
        return ((Boolean) param.getValue()).booleanValue();
      }
    }

    String optionScope = (String) context.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext propertyContext = getPropertyContext(context, optionScope);
    IProperty property = propertyContext.getProperties().getProperty(key);
    if (property != null) {
      Object value = propertyContext.getCurrentValue(property);
      if (value instanceof String) {
        try {
          return Boolean.valueOf((String) value).booleanValue();
        } catch (Exception e) {
          // Nothing here
        }
      } else if (value instanceof Boolean) {
        return ((Boolean) value).booleanValue();
      }
    }
    return default1;

  }

  public Collection<EObject> getCollectionValue(IContext context, String scope, String key, Collection<EObject> default1) {
    // Retrieve value from parameters if set. Otherwise, from preferences
    Object parameters = context.get(ITransitionConstants.OPTIONS_PARAMETERS);
    if ((parameters != null) && (parameters instanceof ActivityParameters)) {
      GenericParameter<?> param = ((ActivityParameters) parameters).getParameter(key);
      if ((param != null) && Collection.class.isAssignableFrom(param.getParameterType())) {
        return (Collection) param.getValue();
      }
    }

    String optionScope = (String) context.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext propertyContext = getPropertyContext(context, optionScope);
    IProperty property = propertyContext.getProperties().getProperty(key);
    if (property != null) {
      Object value = propertyContext.getCurrentValue(property);
      if (value instanceof Collection) {
        return (Collection) value;
      } else if (value instanceof EObject) {
        return Collections.singletonList((EObject) value);
      }
      if ((value != null) && (value instanceof EObject)) {
        return Collections.singletonList((EObject) value);
      }

    }
    return default1;
  }

  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public Object getValue(IContext context, String string1, String key, Object object1) {
    // Retrieve value from parameters if set. Otherwise, from preferences
    Object parameters = context.get(ITransitionConstants.OPTIONS_PARAMETERS);
    if ((parameters != null) && (parameters instanceof ActivityParameters)) {
      GenericParameter<?> param = ((ActivityParameters) parameters).getParameter(key);
      if ((param != null) && Object.class.isAssignableFrom(param.getParameterType())) {
        return param.getValue();
      }
    }

    String optionScope = (String) context.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext propertyContext = getPropertyContext(context, optionScope);
    IProperty property = propertyContext.getProperties().getProperty(key);
    if (property != null) {
      Object value = propertyContext.getCurrentValue(property);
      return value;
    }
    return object1;
  }

}
