/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.property;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.StringPropertyValue;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;

public class PropertiesServices {

  public static Component getComponentType(EObject element) {
    EObject current = element;

    if (current instanceof AbstractTypedElement) {
      current = ((AbstractTypedElement) current).getAbstractType();
    }
    if (current instanceof Component) {
      return (Component) current;
    }
    return null;

  }

  /**
   * Returns value of the property value  following the given path
   * @param element the element
   * @param path path of the property
   * @param defaultValue the default value which will be returned if property is invalid or not set
   */
  public static String getPropertyValue(CapellaElement element, String[] path, String defaultValue) {
    StringPropertyValue property = getRealPropertyValue(element, path, false);
    if ((property == null) || (property.getValue() == null)) {
      return defaultValue;
    }
    return property.getValue();
  }

  /**
   * Access to the StringPropertyValue presents into the element following the path
   * @param element the element
   * @param path path of the property
   * @param create if true, create path and property if no-existent.
   * @return the propertyvalue, if create equals false and property is no-existent, return null
   */
  private static StringPropertyValue getRealPropertyValue(CapellaElement element, String[] path, boolean create) {

    String propertyName = path[path.length - 1];

    CapellaElement root = element;

    for (int i = 0; i < path.length - 1; i++) {
      String pathName = path[i];

      boolean foundSubGroup = false;
      if (root == null) {
        break;
      }

      EList<PropertyValueGroup> ownedGroup = root.getOwnedPropertyValueGroups();
      for (PropertyValueGroup group : ownedGroup) {
        if (group.getName().equals(pathName)) {
          root = group;
          foundSubGroup = true;
          break;
        }
      }

      if (!foundSubGroup) {
        if (create) {
          //Create a property group named 'pathName' into the current element
          final PropertyValueGroup propertyGroup = CapellacoreFactory.eINSTANCE.createPropertyValueGroup();
          final CapellaElement toInsert = root;
          propertyGroup.setName(pathName);
          toInsert.getOwnedPropertyValueGroups().add(propertyGroup);
          root = propertyGroup;
          continue;
        }
        return null;
      }
    }

    if (root != null) {
      StringPropertyValue finalProperty = null;
      EList<AbstractPropertyValue> ownedValues = root.getOwnedPropertyValues();
      for (AbstractPropertyValue property : ownedValues) {
        if (property.getName().equals(propertyName) && (property instanceof StringPropertyValue)) {
          finalProperty = (StringPropertyValue) property;
          break;
        }
      }

      //If not found into getOwnedPropertyValues, try to access to property into 
      //getProperties for a PropertyValueGroup
      if ((finalProperty == null) && (root instanceof PropertyValueGroup)) {
        ownedValues = ((PropertyValueGroup) root).getAppliedPropertyValues();
        for (AbstractPropertyValue property : ownedValues) {
          if (property.getName().equals(propertyName) && (property instanceof StringPropertyValue)) {
            finalProperty = (StringPropertyValue) property;
            break;
          }
        }
      }

      if ((finalProperty == null) && create) {
        //Create a property group named 'pathName' into the current element
        final StringPropertyValue property = CapellacoreFactory.eINSTANCE.createStringPropertyValue();
        final CapellaElement toInsert = root;
        property.setName(propertyName);
        toInsert.getOwnedPropertyValues().add(property);
        finalProperty = property;
      }
      return finalProperty;
    }

    return null;
  }

  /**
   * Sets a property value
   * @param function  the targeted function
   * @param propertyName  the name of the property to be setting
   * @param value  the value to be setting  
   * @param create boolean which create property if not exist
   */
  public static boolean setPropertyValue(final CapellaElement element, String[] path, final String value, boolean create) {
    try {
      final StringPropertyValue property = getRealPropertyValue(element, path, create);
      property.setValue(value);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Unset a property on the element
   * @param element the element
   * @param path the path of the property
   */
  public static void unsetProperty(CapellaElement element, String[] path) {
    try {
      final StringPropertyValue property = getRealPropertyValue(element, path, false);
      if (property != null) {
        final StringPropertyValue propRemove = property;
        if (((CapellaElement) property.eContainer()).getOwnedPropertyValues().contains(propRemove)) {
          ((CapellaElement) property.eContainer()).getOwnedPropertyValues().remove(propRemove);
        }
      }
    } catch (Exception e) {
      // Do nothing
    }
  }

}
