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
package org.polarsys.capella.core.re.handlers.filters;

import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.core.transition.common.handlers.filter.AttributeStringValueFromSource;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 *
 */
public class ReNameValueFromSource extends AttributeStringValueFromSource {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription(IDifference difference_p) {
    String attribute = "attribute";
    if (difference_p != null) {
      if (difference_p instanceof IAttributeValuePresence) {
        IAttributeValuePresence o = (IAttributeValuePresence) difference_p;
        if (o.getFeature() != null) {
          attribute = o.getFeature().getName();
        }
      }
    }
    return NLS.bind("Propagation of ''{0}'' only if feature is unsynchronized", attribute);
  }

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
    return false;
  }

}
