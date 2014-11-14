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
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

public class GeneralizableElementHelper {
  private static GeneralizableElementHelper instance;
  /**
   * Cross referencing re-entrance collection for sub generalizations.
   */
  private List<GeneralizableElement> _isCrossReferencingSub;
  /**
   * Cross referencing re-entrance collection for super generalizations.
   */
  private List<GeneralizableElement> _isCrossReferencingSuper;

  private GeneralizableElementHelper() {
    _isCrossReferencingSub = new ArrayList<GeneralizableElement>(0);
    _isCrossReferencingSuper = new ArrayList<GeneralizableElement>(0);
  }

  public static GeneralizableElementHelper getInstance() {
    if (instance == null) {
      instance = new GeneralizableElementHelper();
    }
    return instance;
  }

  public Object doSwitch(GeneralizableElement element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUB)) {
      ret = getSub(element_p);
    } else if (feature_p.equals(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER)) {
      ret = getSuper(element_p);
    } else if (feature_p.equals(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS)) {
      ret = getSubGeneralizations(element_p);
    } else if (feature_p.equals(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS)) {
      ret = getSuperGeneralizations(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = TypeHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected boolean isCrossReferencing(GeneralizableElement element_p, boolean subGeneralizableElements_p) {
    return subGeneralizableElements_p ? _isCrossReferencingSub.contains(element_p) : _isCrossReferencingSuper.contains(element_p);

  }

  protected void markAsCrossReferenced(GeneralizableElement element_p, boolean subGeneralizableElements_p) {
    if (subGeneralizableElements_p) {
      _isCrossReferencingSub.add(element_p);
    } else {
      _isCrossReferencingSuper.add(element_p);
    }
  }

  protected void unmarkAsCrossReferenced(GeneralizableElement element_p, boolean subGeneralizableElements_p) {
    if (subGeneralizableElements_p) {
      _isCrossReferencingSub.remove(element_p);
    } else {
      _isCrossReferencingSuper.remove(element_p);
    }
  }

  protected List<GeneralizableElement> getSub(GeneralizableElement element_p) {
    List<GeneralizableElement> ret = new ArrayList<GeneralizableElement>();
    for (Generalization generalization : element_p.getSubGeneralizations()) {
      GeneralizableElement sub = generalization.getSub();
      if (sub != null) {
        ret.add(sub);
      }
    }
    return ret;
  }

  protected List<GeneralizableElement> getSuper(GeneralizableElement element_p) {
    List<GeneralizableElement> ret = new ArrayList<GeneralizableElement>();
    for (Generalization generalization : element_p.getSuperGeneralizations()) {
      GeneralizableElement _super = generalization.getSuper();
      if (_super != null) {
        ret.add(_super);
      }
    }
    return ret;
  }

  protected List<Generalization> getSubGeneralizations(GeneralizableElement element_p) {
    List<Generalization> ret = new ArrayList<Generalization>();

    if (!isCrossReferencing(element_p, true)) {
      try {
        markAsCrossReferenced(element_p, true);

        TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(element_p);
        if ((editingDomain != null) && (editingDomain instanceof SemanticEditingDomain)) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);
          for (EStructuralFeature.Setting setting : references) {
            if (CapellacorePackage.Literals.GENERALIZATION__SUPER.equals(setting.getEStructuralFeature())) {
              ret.add((Generalization) setting.getEObject());
            }
          }
        }
      } finally {
        unmarkAsCrossReferenced(element_p, true);
      }
    }
    return ret;
  }

  protected List<Generalization> getSuperGeneralizations(GeneralizableElement element_p) {
    List<Generalization> ret = new ArrayList<Generalization>();

    if (!isCrossReferencing(element_p, false)) {
      try {
        markAsCrossReferenced(element_p, false);

        TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(element_p);
        if ((editingDomain != null) && (editingDomain instanceof SemanticEditingDomain)) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);
          for (EStructuralFeature.Setting setting : references) {
            if (CapellacorePackage.Literals.GENERALIZATION__SUB.equals(setting.getEStructuralFeature())) {
              ret.add((Generalization) setting.getEObject());
            }
          }
        }
      } finally {
        unmarkAsCrossReferenced(element_p, false);
      }
    }
    return ret;
  }
}
