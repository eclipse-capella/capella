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

package org.polarsys.capella.core.model.helpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 */
public class PropertyExt {

  /**
   * Returns the regarding association, i.e the association the current property is bound to (since the current query is applied to properties)
   * @param elem the property (must be a <code>Property</code> instance
   * @return the <code>Association</code> or <code>null</code> if the property is not bound to an association
   */
  public static Association getRegardingAssociation(CapellaElement elem) {
    SemanticEditingDomain semEditDomain = (SemanticEditingDomain) TransactionHelper.getEditingDomain(elem);
    if (semEditDomain != null) {
      ECrossReferenceAdapter crossReferencer = semEditDomain.getCrossReferencer();
      if (crossReferencer != null) {
        for (Setting setting : crossReferencer.getInverseReferences(elem)) {
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
  public static boolean isTyped(Property ppt) {
    return (null != ppt.getAbstractType());
  }
}
