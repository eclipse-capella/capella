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
package org.polarsys.capella.core.transition.system.handlers.filter;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.transition.common.handlers.filter.AttributeStringValueFromSource;

/**
 *
 */
public class AttributeNameValueFromSource extends AttributeStringValueFromSource {

  /**
   * Returns whether the difference from attribute value should be merged or not
   * @return
   */
  @Override
  public boolean isMergeableAttribute(EAttribute attribute_p, EObject source_p, EObject target_p, Object oldValue_p, Object newValue_p) {
    // Merge name of element if name is same as EClass of the element
    if (ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.equals(attribute_p)) {
      return true;
    }
    return false;
  }

  /**
   * Returns whether the difference from attribute value should be merged or not
   * @return
   */
  @Override
  public boolean isMergeableAttributeValue(EAttribute attribute_p, EObject source_p, EObject target_p, Object oldValue_p, Object newValue_p) {
    // Merge name of element if name is same as EClass of the element
    if (ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.equals(attribute_p)) {
      if ((oldValue_p == null) || ((oldValue_p instanceof String) && (((String) oldValue_p).length() == 0))) {
        return true;

      } else if (oldValue_p.equals(target_p.eClass().getName())) {
        return true;
      }
      if (oldValue_p.equals("System State Machine")) {
        return true;
      }
      if (oldValue_p.equals("Default Region")) {
        return true;
      }
    }
    return super.isMergeableAttributeValue(attribute_p, source_p, target_p, oldValue_p, newValue_p);
  }

}
