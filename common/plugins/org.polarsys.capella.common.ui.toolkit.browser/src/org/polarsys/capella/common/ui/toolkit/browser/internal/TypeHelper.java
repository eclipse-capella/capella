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
package org.polarsys.capella.common.ui.toolkit.browser.internal;

import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.ClassUtils;
import org.eclipse.emf.ecore.EObject;

/**
 * 
 */
public class TypeHelper {
  /**
   * Singleton.
   */
  protected static TypeHelper typeHelper = null;

  /**
   * Constructor.
   */
  private TypeHelper() {
  }

  public static TypeHelper getInstance() {
    if (typeHelper == null) {
      typeHelper = new TypeHelper();
    }
    return typeHelper;
  }

  /**
   * 
   * @param element
   * @param type
   * @return
   */
  public boolean isInstanceOf(Object element, String type) {
    // null isn't an instanceof of anything.
    if (element == null)
      return false;
    return isSubtype(element.getClass(), type);
  }

  /**
   * 
   * @param clazz
   * @param type
   * @return
   */
  @SuppressWarnings("unchecked")
  private boolean isSubtype(Class clazz, String type) {
    if (clazz.getName().equals(type))
      return true;
    Class superClass = clazz.getSuperclass();
    if (superClass != null && isSubtype(superClass, type))
      return true;
    Class[] interfaces = clazz.getInterfaces();
    for (int i = 0; i < interfaces.length; i++) {
      if (isSubtype(interfaces[i], type))
        return true;
    }
    return false;
  }
  
  /**
   * Check in the super interface hierarchy of a given object, classFQN1 is a sub-type of classFQN2
   * 
   * @param classFQN1
   * @param classFQN2
   * @param current
   * @return
   */
  @SuppressWarnings("unchecked")
  public boolean isSubtype(String classFQN1, String classFQN2, EObject current) {
    Class<? extends EObject> clazz = current.getClass();
    Map<String, Class> name2Interface = (Map<String, Class>) ClassUtils.getAllInterfaces(clazz).stream()
        .collect(Collectors.toMap(i -> ((Class) i).getName(), i -> i));
    if (name2Interface.containsKey(classFQN1) && name2Interface.containsKey(classFQN2)) {
      return name2Interface.get(classFQN2).isAssignableFrom(name2Interface.get(classFQN1));
    }
    return false;
  }
}
