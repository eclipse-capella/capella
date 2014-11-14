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

  public PropertyContext(IProperties properties_p) {
    properties = properties_p;
    propertyValues = new HashMap<IProperty, Object>();
    shouldNotify = true;
  }

  public PropertyContext(IProperties properties_p, Object source_p) {
    this(properties_p);
    _source = source_p;
  }

  public Object getSource() {
    return _source;
  }

  public void setSource(Object source_p) {
    _source = source_p;
    propertyValues.clear();

    for (IProperty pro : getProperties().getAllItems()) {
      notifyListeners(pro);
    }
  }

  boolean shouldNotify;

  public void notifyListeners(IProperty property_p) {

    PropertyChangedEvent event = new PropertyChangedEvent(property_p, this);

    for (PropertyChangeListener listener : listeners) {
      listener.update(event);
    }

    List<IProperty> updated = new ArrayList<IProperty>();

    shouldNotify = false;
    if (properties != null) {
      for (IProperty property : properties.getAllItems()) {
        if (property instanceof ICompoundProperty) {
          ICompoundProperty cProperty = (ICompoundProperty) property;
          for (String id : cProperty.getRelatedProperties()) {
            if (id.equals(property_p.getId())) {
              cProperty.updatedValue(property_p, this);
              setCurrentValue(cProperty, cProperty.getValue(this));
              updated.add(cProperty);
              break;
            }
          }
        }
      }
    }
    shouldNotify = true;

    for (IProperty property : updated) {
      notifyListeners(property);
    }
  }

  public void registerListener(PropertyChangeListener listener_p) {
    listeners.add(listener_p);
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext#getCurrentValue(org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty)
   */
  public Object getCurrentValue(IProperty property_p) {
    if (!isModified(property_p) || !(propertyValues.containsKey(property_p))) {
      return getDefaultValue(property_p);
    }
    return propertyValues.get(property_p);
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext#getDefaultValue(org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty)
   */
  public Object getDefaultValue(IProperty property_p) {
    if (property_p == null) {
      return null;
    }
    return property_p.getValue(this);
  }

  public boolean isModified() {
    return !propertyValues.isEmpty();
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext#isModified(org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty)
   */
  public boolean isModified(IProperty property_p) {
    if (property_p instanceof IModifiedProperty) {
      return ((IModifiedProperty) property_p).isModified(this);
    }
    return propertyValues.containsKey(property_p);
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext#setCurrentValue(java.lang.Object,
   *      org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty)
   */
  public void setCurrentValue(IProperty property_p, Object value_p) {
    if (property_p == null) {
      return;
    }

    propertyValues.put(property_p, property_p.toType(value_p, this));

    if (shouldNotify) {
      notifyListeners(property_p);
    }
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext#write(org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty)
   */
  public void write(IProperty property_p) {
    if (property_p instanceof IEditableProperty) {
      ((IEditableProperty) property_p).setValue(this);
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
  public void unregisterListener(PropertyChangeListener listener_p) {
    listeners.remove(listener_p);
  }

  public Collection<Object> getSourceAsList() {
    return getSourceAsList(null);
  }

  /**
   * {@inheritDoc}
   */
  public void registerListener(PropertyChangeListener listener_p, IProperty property_p) {
    registerListener(listener_p);
  }

  /**
   * {@inheritDoc}
   */
  public Collection<Object> getSourceAsList(Class clazz_p) {
    Collection<Object> sources = new ArrayList<Object>();
    Object source = getSource();
    if (source instanceof Collection) {
      for (Object object : (Collection) source) {
        if ((clazz_p == null) || clazz_p.isInstance(object)) {
          sources.add(object);
        }
      }
    } else {
      if ((clazz_p == null) || clazz_p.isInstance(source)) {
        sources.add(source);
      }
    }
    return sources;
  }

}
