/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.flexibility.properties.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.polarsys.capella.common.flexibility.properties.PropertyChangeListener;
import org.polarsys.capella.common.flexibility.properties.PropertyChangedEvent;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IModifiedProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;

/**
 */
public class PropertyContext implements IPropertyContext {

  IProperties properties;

  Object _source;

  HashMap<IProperty, Object> propertyValues;

  Collection<PropertyChangeListener> listeners = new LinkedList<PropertyChangeListener>();

  public PropertyContext(IProperties properties) {
    this.properties = properties;
    propertyValues = new HashMap<IProperty, Object>();
    shouldNotify = true;
  }

  public PropertyContext(IProperties properties, Object source) {
    this(properties);
    _source = source;
  }

  public Object getSource() {
    return _source;
  }

  public void setSource(Object source) {
    _source = source;
    propertyValues.clear();

    for (IProperty pro : getProperties().getAllItems()) {
      notifyListeners(pro);
    }
  }

  boolean shouldNotify;

  public void notifyListeners(IProperty property) {

    PropertyChangedEvent event = new PropertyChangedEvent(property, this);

    for (PropertyChangeListener listener : listeners) {
      listener.update(event);
    }

    List<IProperty> updated = new ArrayList<IProperty>();

    shouldNotify = false;
    if (properties != null) {
      for (IProperty prop : properties.getAllItems()) {
        if (prop instanceof ICompoundProperty) {
          ICompoundProperty cProperty = (ICompoundProperty) prop;
          for (String id : cProperty.getRelatedProperties()) {
            if (id.equals(property.getId())) {
              cProperty.updatedValue(property, this);
              setCurrentValue(cProperty, cProperty.getValue(this));
              updated.add(cProperty);
              break;
            }
          }
        }
      }
    }
    shouldNotify = true;

    for (IProperty prop : updated) {
      notifyListeners(prop);
    }
  }

  public void registerListener(PropertyChangeListener listener) {
    listeners.add(listener);
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext#getCurrentValue(org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty)
   */
  public Object getCurrentValue(IProperty property) {
    if (!isModified(property) || !(propertyValues.containsKey(property))) {
      return getDefaultValue(property);
    }
    return propertyValues.get(property);
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext#getDefaultValue(org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty)
   */
  public Object getDefaultValue(IProperty property) {
    if (property == null) {
      return null;
    }
    return property.getValue(this);
  }

  public boolean isModified() {
    return !propertyValues.isEmpty();
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext#isModified(org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty)
   */
  public boolean isModified(IProperty property) {
    if (property instanceof IModifiedProperty) {
      return ((IModifiedProperty) property).isModified(this);
    }
    return propertyValues.containsKey(property);
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext#setCurrentValue(java.lang.Object,
   *      org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty)
   */
  public void setCurrentValue(IProperty property, Object value) {
    if (property == null) {
      return;
    }

    propertyValues.put(property, property.toType(value, this));

    if (shouldNotify) {
      notifyListeners(property);
    }
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext#write(org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty)
   */
  public void write(IProperty property) {
    if (property instanceof IEditableProperty) {
      ((IEditableProperty) property).setValue(this);
    }
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext#writeAll()
   */
  public void writeAll() {
    if (properties != null) {
      for (IProperty property : properties.getAllItems()) {
        if (isModified(property)) {
          if (property instanceof IEditableProperty) {
            ((IEditableProperty) property).setValue(this);
          }
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public IProperties getProperties() {
    return properties;
  }

  /**
   * {@inheritDoc}
   */
  public void setDefaults() {
    if (properties != null) {
      for (IProperty property : properties.getAllItems()) {
        if (property instanceof IEditableProperty) {
          if (property instanceof IDefaultValueProperty) {
            setCurrentValue(property, ((IDefaultValueProperty) property).getDefaultValue(this));
          }
        }
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  public void unregisterListener(PropertyChangeListener listener) {
    listeners.remove(listener);
  }

  public Collection<Object> getSourceAsList() {
    return getSourceAsList(null);
  }

  /**
   * {@inheritDoc}
   */
  public void registerListener(PropertyChangeListener listener, IProperty property) {
    registerListener(listener);
  }

  /**
   * {@inheritDoc}
   */
  public Collection<Object> getSourceAsList(Class clazz) {
    Collection<Object> sources = new ArrayList<Object>();
    Object source = getSource();
    if (source instanceof Collection) {
      for (Object object : (Collection) source) {
        if ((clazz == null) || clazz.isInstance(object)) {
          sources.add(object);
        }
      }
    } else {
      if ((clazz == null) || clazz.isInstance(source)) {
        sources.add(source);
      }
    }
    return sources;
  }

}
