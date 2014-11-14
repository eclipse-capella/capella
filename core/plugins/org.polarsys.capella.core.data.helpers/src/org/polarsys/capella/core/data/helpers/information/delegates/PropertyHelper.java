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
package org.polarsys.capella.core.data.helpers.information.delegates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.TypedElementHelper;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.SemanticEditingDomain;

public class PropertyHelper {
  private static PropertyHelper instance;
  /**
   * Cross referencing re-entrance collection.
   */
  private List<Property> _isCrossReferencing;

  private PropertyHelper() {
    _isCrossReferencing = new ArrayList<Property>(0);
  }

  public static PropertyHelper getInstance() {
    if (instance == null) {
      instance = new PropertyHelper();
    }
    return instance;
  }

  public Object doSwitch(Property element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(InformationPackage.Literals.PROPERTY__ASSOCIATION)) {
      ret = getAssociation(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = TypedElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected boolean isCrossReferencing(Property element_p) {
    return _isCrossReferencing.contains(element_p);

  }

  protected void markAsCrossReferenced(Property element_p) {
    _isCrossReferencing.add(element_p);
  }

  protected void unmarkAsCrossReferenced(Property element_p) {
    _isCrossReferencing.remove(element_p);
  }

  protected Association getAssociation(Property element_p) {
    Association ret = null;

    if (!isCrossReferencing(element_p)) {
      try {
        markAsCrossReferenced(element_p);
        TransactionalEditingDomain editingDomain = MDEAdapterFactory.getEditingDomain(element_p);
        if ((editingDomain != null) && (editingDomain instanceof SemanticEditingDomain)) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);

          for (EStructuralFeature.Setting setting : references) {
            if (InformationPackage.Literals.ASSOCIATION__NAVIGABLE_MEMBERS.equals(setting.getEStructuralFeature())) {
              ret = (Association) setting.getEObject();
            }
          }
        }
      } finally {
        unmarkAsCrossReferenced(element_p);
      }
    } else {
      System.out.println("(association) re-entrance: " + element_p.getFullLabel()); //$NON-NLS-1$
    }
    return ret;
  }
}
