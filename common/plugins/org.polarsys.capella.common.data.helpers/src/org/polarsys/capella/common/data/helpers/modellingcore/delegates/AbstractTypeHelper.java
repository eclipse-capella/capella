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
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.SemanticEditingDomain;

public class AbstractTypeHelper {
  private static AbstractTypeHelper instance;
  /**
   * Cross referencing re-entrance collection for typed elements.
   */
  private List<AbstractType> _isCrossReferencingTypedElements;

  private AbstractTypeHelper() {
    _isCrossReferencingTypedElements = new ArrayList<AbstractType>(0);
  }

  public static AbstractTypeHelper getInstance() {
    if (instance == null)
      instance = new AbstractTypeHelper();
    return instance;
  }

  public Object doSwitch(AbstractType element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(ModellingcorePackage.Literals.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS)) {
      ret = getAbstractTypedElements(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = ModelElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected boolean isCrossReferencing(AbstractType element_p) {
    return _isCrossReferencingTypedElements.contains(element_p);
  }

  protected void markAsCrossReferenced(AbstractType element_p) {
    _isCrossReferencingTypedElements.add(element_p);
  }

  protected void unmarkAsCrossReferenced(AbstractType element_p) {
    _isCrossReferencingTypedElements.remove(element_p);
  }

  protected List<AbstractTypedElement> getAbstractTypedElements(AbstractType element_p) {
    List<AbstractTypedElement> ret = new ArrayList<AbstractTypedElement>();

    if (!isCrossReferencing(element_p)) {
      try {
        markAsCrossReferenced(element_p);
        SemanticEditingDomain editingDomain = (SemanticEditingDomain) AdapterFactoryEditingDomain.getEditingDomainFor(element_p);
        if (editingDomain != null) {
          Collection<Setting> references = editingDomain.getDerivedCrossReferencer().getInverseReferences(element_p, true);
          for (EStructuralFeature.Setting setting : references) {
            if (ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE.equals(setting.getEStructuralFeature())) {
              ret.add((AbstractTypedElement) setting.getEObject());
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
