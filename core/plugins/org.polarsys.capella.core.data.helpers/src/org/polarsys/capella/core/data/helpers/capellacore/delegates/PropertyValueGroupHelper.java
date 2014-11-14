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
package org.polarsys.capella.core.data.helpers.capellacore.delegates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.SemanticEditingDomain;

public class PropertyValueGroupHelper {
  private static PropertyValueGroupHelper instance;
  /**
   * Cross referencing re-entrance collection.
   */
  private List<PropertyValueGroup> _isCrossReferencing;

  private PropertyValueGroupHelper() {
    _isCrossReferencing = new ArrayList<PropertyValueGroup>(0);
  }

  public static PropertyValueGroupHelper getInstance() {
    if (instance == null) {
      instance = new PropertyValueGroupHelper();
    }
    return instance;
  }

  public Object doSwitch(PropertyValueGroup element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(CapellacorePackage.Literals.PROPERTY_VALUE_GROUP__VALUED_ELEMENTS)) {
      ret = getValuedElements(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = NamespaceHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected boolean isCrossReferencing(PropertyValueGroup element_p) {
    return _isCrossReferencing.contains(element_p);

  }

  protected void markAsCrossReferenced(PropertyValueGroup element_p) {
    _isCrossReferencing.add(element_p);
  }

  protected void unmarkAsCrossReferenced(PropertyValueGroup element_p) {
    _isCrossReferencing.remove(element_p);
  }

  protected List<CapellaElement> getValuedElements(PropertyValueGroup element_p) {
    List<CapellaElement> ret = new ArrayList<CapellaElement>();

    if (!isCrossReferencing(element_p)) {
      try {
        markAsCrossReferenced(element_p);
        TransactionalEditingDomain editingDomain = MDEAdapterFactory.getEditingDomain(element_p);
        if ((editingDomain != null) && (editingDomain instanceof SemanticEditingDomain)) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);

          for (EStructuralFeature.Setting setting : references) {
            if (CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS.equals(setting.getEStructuralFeature())) {
              ret.add((CapellaElement) setting.getEObject());
            }
          }
        }
      } finally {
        unmarkAsCrossReferenced(element_p);
      }
    } else {
      System.out.println("(involved) re-entrance: " + element_p.getFullLabel()); //$NON-NLS-1$
    }
    return ret;
  }
}
