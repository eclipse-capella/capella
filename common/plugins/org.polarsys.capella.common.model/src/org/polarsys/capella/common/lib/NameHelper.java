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
   * @param object_p
   *          named element.
   * @param container_p
   *          container of named element.
   * @param attribute_p
   *          EAttribute for naming feature.
   * @param defaultString_p
   *          default string of the object_p. First parameter in the pattern {1}
   * @param pattern_p
   *          pattern that the resulting string will match.
   */
  @SuppressWarnings("boxing")
  public static void setUniqueString(EObject object_p, EObject container_p, EAttribute attribute_p, String defaultString_p, String pattern_p) {
    int counter = 0;

    List<EObject> siblings = container_p.eContents();

    // retrieving the naming attribute feature.
    if (siblings != null && !siblings.isEmpty()) {
      List<String> existingNames = new ArrayList<String>();

      // list existing names.
      if (attribute_p != null) {
        for (Object sibling : siblings) {
          EObject eSibling = (EObject) sibling;
          Object attributeValue = eSibling.eGet(attribute_p);
          if (attributeValue instanceof String) {
            String name = (String) attributeValue;
            if (null != name && !name.equals("")) { //$NON-NLS-1$
              existingNames.add(name);
            }
          }
        }

        String copiedEObjectName = defaultString_p;
        while (existingNames.contains(copiedEObjectName)) {
          copiedEObjectName = MessageFormat.format(pattern_p, new Object[] { counter++, defaultString_p });
        }

        // finally set a proper name.
        object_p.eSet(attribute_p, copiedEObjectName);
      }
    }
  }

  /**
   * Retrieve the attribute which symbolizes the name of the given object. Research is based on business specific annotations hold by a eAttribute in the
   * metaclass.
   * @param eObject_p
   * @return namingAttribute
   */
  public static EAttribute getNamingAttribute(EObject eObject_p) {
    for (EAttribute eAttribute : eObject_p.eClass().getEAllAttributes()) {
      EAnnotation businessAnnotation = eAttribute.getEAnnotation(BUSINESS_INFORMATION_SOURCE);
      if (businessAnnotation!= null && businessAnnotation.getDetails().containsKey(NAMING_ATTRIBUTE_KEY)) {
        return eAttribute;
      }
    }
    return null;
  }
}
