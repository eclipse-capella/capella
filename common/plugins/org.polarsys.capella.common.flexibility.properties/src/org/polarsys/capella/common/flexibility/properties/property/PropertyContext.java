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

package org.polarsys.capella.common.flexibility.properties.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

  /**
   * This is the key to register a listener which is not mapped to a property.
   */
  private static final String GLOBAL_LISTENER = "global_listener";

  Object source;
  boolean shouldNotify;

  IProperties properties;
  HashMap<IProperty, Object> propertyValues;
  Map<String, LinkedList<PropertyChangeListener>> listenersMap;

  public PropertyContext(IProperties properties) {
    this.properties = properties;
    shouldNotify = true;
    propertyValues = new HashMap<IProperty, Object>();
    listenersMap = new HashMap<String, LinkedList<PropertyChangeListener>>();
  }

  public PropertyContext(IProperties properties, Object source) {
    this(properties);
    this.source = source;
  }

  public Object getSource() {
    return source;
  }

  public void setSource(Object source) {
    this.source = source;
    propertyValues.clear();

    for (IProperty property : getProperties().getAllItems()) {
      notifyListeners(property);
    }
  }

  public void notifyListeners(IProperty property) {

    PropertyChangedEvent event = new PropertyChangedEvent(property, this);

    // Temporary listeners list
    List<PropertyChangeListener> propertyChangeListener = new LinkedList<PropertyChangeListener>();

    // Get the listeners of the property
    LinkedList<PropertyChangeListener> propetyListeners = listenersMap.get(property.getId());
    if (propetyListeners != null) {
      propertyChangeListener.addAll(propetyListeners);
    }

    // Get the global listeners
    LinkedList<PropertyChangeListener> globalListeners = listenersMap.get(GLOBAL_LISTENER);
    if (globalListeners != null) {
      propertyChangeListener.addAll(globalListeners);
    }

    // Notify listeners
    for (PropertyChangeListener listener : propertyChangeListener) {
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
    for(List<PropertyChangeListener> listenersList : listenersMap.values()){
        listenersList.remove(listener);
    }
  }

  public Collection<Object> getSourceAsList() {
    return getSourceAsList(null);
  }

  public void registerListener(PropertyChangeListener listener) {
    registerListener(GLOBAL_LISTENER, listener);
  }

  /**
   * {@inheritDoc}
   */
  public void registerListener(PropertyChangeListener listener, IProperty property) {
    registerListener(property.getId(), listener);
  }

  private void registerListener(String id, PropertyChangeListener listener) {
    LinkedList<PropertyChangeListener> listenersList = listenersMap.get(id);
    if(listenersList == null){
      listenersList = new LinkedList<PropertyChangeListener>();
      listenersMap.put(id, listenersList);
    }
    listenersList.add(listener);
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
