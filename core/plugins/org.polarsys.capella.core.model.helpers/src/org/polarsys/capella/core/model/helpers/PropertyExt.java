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
package org.polarsys.capella.core.model.helpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 */
public class PropertyExt {

  /**
   * Returns the regarding association, i.e the association the current property is bound to (since the current query is applied to properties)
   * @param elem_p the property (must be a <code>Property</code> instance
   * @return the <code>Association</code> or <code>null</code> if the property is not bound to an association
   */
  public static Association getRegardingAssociation(CapellaElement elem_p) {
    SemanticEditingDomain semEditDomain = (SemanticEditingDomain) MDEAdapterFactory.getEditingDomain();
    if (semEditDomain != null) {
      ECrossReferenceAdapter crossReferencer = semEditDomain.getCrossReferencer();
      if (crossReferencer != null) {
        for (Setting setting : crossReferencer.getInverseReferences(elem_p)) {
          EObject eObject = setting.getEObject();
          if (eObject instanceof Association) {
            return (Association) eObject;
          }
        }
      }
    }
    // There is no such association, so returns <code>null</code>
    return null;
  }

  /**
   * @return
   */
  public static boolean isTyped(Property ppt_p) {
    return (null != ppt_p.getAbstractType());
  }
}
