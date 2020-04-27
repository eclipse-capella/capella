/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.lib;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

public class NameHelper {
  private static final String BUSINESS_INFORMATION_SOURCE = "http://www.polarsys.org/capella/2007/BusinessInformation"; //$NON-NLS-1$

  private static final String NAMING_ATTRIBUTE_KEY = "namingAttribute"; //$NON-NLS-1$

  /**
   * Set string to the given object that is unique in the given container. The string is set to the place of the given attribute. The string set will be based
   * on the default one and associated with an digit. First parameter in the pattern will be an int : {0} Second parameter in the pattern will be the default
   * string : {1}
   * @param object
   *          named element.
   * @param container
   *          container of named element.
   * @param attribute
   *          EAttribute for naming feature.
   * @param defaultString
   *          default string of the object. First parameter in the pattern {1}
   * @param pattern
   *          pattern that the resulting string will match.
   */
  @SuppressWarnings("boxing")
  public static void setUniqueString(EObject object, EObject container, EAttribute attribute, String defaultString, String pattern) {
    int counter = 0;

    List<EObject> siblings = container.eContents();

    // retrieving the naming attribute feature.
    if (siblings != null && !siblings.isEmpty()) {
      List<String> existingNames = new ArrayList<String>();

      // list existing names.
      if (attribute != null) {
        for (Object sibling : siblings) {
          EObject eSibling = (EObject) sibling;
          Object attributeValue = eSibling.eGet(attribute);
          if (attributeValue instanceof String) {
            String name = (String) attributeValue;
            if (!name.equals("")) { //$NON-NLS-1$
              existingNames.add(name);
            }
          }
        }

        String copiedEObjectName = defaultString;
        while (existingNames.contains(copiedEObjectName)) {
          copiedEObjectName = MessageFormat.format(pattern, new Object[] { counter++, defaultString });
        }

        // finally set a proper name.
        object.eSet(attribute, copiedEObjectName);
      }
    }
  }

  /**
   * Retrieve the attribute which symbolizes the name of the given object. Research is based on business specific annotations hold by a eAttribute in the
   * meta-class.
   * @param eObject
   * @return namingAttribute
   */
  public static EAttribute getNamingAttribute(EObject eObject) {
    for (EAttribute eAttribute : eObject.eClass().getEAllAttributes()) {
      EAnnotation businessAnnotation = eAttribute.getEAnnotation(BUSINESS_INFORMATION_SOURCE);
      if (businessAnnotation!= null && businessAnnotation.getDetails().containsKey(NAMING_ATTRIBUTE_KEY)) {
        return eAttribute;
      }
    }
    return null;
  }
}
