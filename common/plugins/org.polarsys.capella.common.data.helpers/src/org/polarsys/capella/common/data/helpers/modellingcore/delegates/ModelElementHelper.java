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
package org.polarsys.capella.common.data.helpers.modellingcore.delegates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

public class ModelElementHelper {
  private static ModelElementHelper instance;
  /**
   * Cross referencing re-entrance collection for typed elements.
   */
  private List<ModelElement> _isCrossReferencingConstraints;

  private ModelElementHelper() {
    _isCrossReferencingConstraints = new ArrayList<ModelElement>(0);
  }

  public static ModelElementHelper getInstance() {
    if (instance == null)
      instance = new ModelElementHelper();
    return instance;
  }

  public Object doSwitch(ModelElement element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(ModellingcorePackage.Literals.MODEL_ELEMENT__CONSTRAINTS)) {
      ret = getConstraints(element_p);
    }

    return ret;
  }

  protected boolean isCrossReferencing(ModelElement element_p) {
    return _isCrossReferencingConstraints.contains(element_p);
  }

  protected void markAsCrossReferenced(ModelElement element_p) {
    _isCrossReferencingConstraints.add(element_p);
  }

  protected void unmarkAsCrossReferenced(ModelElement element_p) {
    _isCrossReferencingConstraints.remove(element_p);
  }

  protected List<AbstractConstraint> getConstraints(ModelElement element_p) {
    List<AbstractConstraint> ret = new ArrayList<AbstractConstraint>();

    if (!isCrossReferencing(element_p)) {
      try {
        markAsCrossReferenced(element_p);

        TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(element_p);
        if (editingDomain instanceof SemanticEditingDomain) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);
          for (EStructuralFeature.Setting setting : references) {
            if (ModellingcorePackage.Literals.ABSTRACT_CONSTRAINT__CONSTRAINED_ELEMENTS.equals(setting.getEStructuralFeature())) {
              ret.add((AbstractConstraint) setting.getEObject());
            }
          }
        }
      } finally {
        unmarkAsCrossReferenced(element_p);
      }
    }
    return ret;
  }
}
