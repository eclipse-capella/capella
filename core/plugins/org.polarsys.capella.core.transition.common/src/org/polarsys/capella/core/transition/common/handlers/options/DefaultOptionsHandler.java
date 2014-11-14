/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

  public IProperties getProperties(IContext context, String scope_p) {
    IProperties elements = (IProperties) context.get(PROPERTIES + scope_p);
    if (elements == null) {
      elements = new PropertiesLoader().getProperties(scope_p);
      context.put(PROPERTIES + scope_p, elements);
    }
    return elements;
  }

  public IPropertyContext getPropertyContext(IContext context, String scope_p) {
    IPropertyContext propertyContext = (IPropertyContext) context.get(PROPERTIES_CONTEXT + scope_p);
    if (propertyContext == null) {
      IProperties elements = getProperties(context, scope_p);
      propertyContext = new PropertyContext(elements, context);
      context.put(PROPERTIES_CONTEXT + scope_p, propertyContext);
    }
    return propertyContext;
  }

  public String getStringValue(IContext context_p, String scope, String key_p, String default_p) {
    // Retrieve value from parameters if set. Otherwise, from preferences
    Object parameters = context_p.get(ITransitionConstants.OPTIONS_PARAMETERS);
    if ((parameters != null) && (parameters instanceof ActivityParameters)) {
      GenericParameter<?> param = ((ActivityParameters) parameters).getParameter(key_p);
      if ((param != null) && String.class.isAssignableFrom(param.getParameterType())) {
        return (String) param.getValue();
      }
    }

    String optionScope = (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext propertyContext = getPropertyContext(context_p, optionScope);
    IProperty property = propertyContext.getProperties().getProperty(key_p);
    if (property != null) {
      Object value = propertyContext.getCurrentValue(property);
      if (value instanceof String) {
        return (String) value;
      }
    }
    return default_p;
  }

  public boolean getBooleanValue(IContext context_p, String scope, String key_p, boolean default_p) {
    // Retrieve value from parameters if set. Otherwise, from preferences
    Object parameters = context_p.get(ITransitionConstants.OPTIONS_PARAMETERS);
    if ((parameters != null) && (parameters instanceof ActivityParameters)) {
      GenericParameter<?> param = ((ActivityParameters) parameters).getParameter(key_p);
      if ((param != null) && Boolean.class.isAssignableFrom(param.getParameterType())) {
        return ((Boolean) param.getValue()).booleanValue();
      }
    }

    String optionScope = (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext propertyContext = getPropertyContext(context_p, optionScope);
    IProperty property = propertyContext.getProperties().getProperty(key_p);
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
    return default_p;

  }

  public Collection<EObject> getCollectionValue(IContext context_p, String scope, String key_p, Collection<EObject> default_p) {
    // Retrieve value from parameters if set. Otherwise, from preferences
    Object parameters = context_p.get(ITransitionConstants.OPTIONS_PARAMETERS);
    if ((parameters != null) && (parameters instanceof ActivityParameters)) {
      GenericParameter<?> param = ((ActivityParameters) parameters).getParameter(key_p);
      if ((param != null) && Collection.class.isAssignableFrom(param.getParameterType())) {
        return (Collection) param.getValue();
      }
    }

    String optionScope = (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext propertyContext = getPropertyContext(context_p, optionScope);
    IProperty property = propertyContext.getProperties().getProperty(key_p);
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
    return default_p;
  }

  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public Object getValue(IContext context_p, String scope_p, String key_p, Object default_p) {
    // Retrieve value from parameters if set. Otherwise, from preferences
    Object parameters = context_p.get(ITransitionConstants.OPTIONS_PARAMETERS);
    if ((parameters != null) && (parameters instanceof ActivityParameters)) {
      GenericParameter<?> param = ((ActivityParameters) parameters).getParameter(key_p);
      if ((param != null) && Object.class.isAssignableFrom(param.getParameterType())) {
        return param.getValue();
      }
    }

    String optionScope = (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext propertyContext = getPropertyContext(context_p, optionScope);
    IProperty property = propertyContext.getProperties().getProperty(key_p);
    if (property != null) {
      Object value = propertyContext.getCurrentValue(property);
      return value;
    }
    return default_p;
  }

}
