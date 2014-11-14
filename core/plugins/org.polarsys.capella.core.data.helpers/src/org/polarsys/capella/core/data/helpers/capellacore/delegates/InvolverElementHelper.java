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
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

public class InvolverElementHelper {
  private static InvolverElementHelper instance;
  /**
   * Cross referencing re-entrance collection.
   */
  private List<InvolverElement> _isCrossReferencing;

  private InvolverElementHelper() {
    _isCrossReferencing = new ArrayList<InvolverElement>(0);
  }

  public static InvolverElementHelper getInstance() {
    if (instance == null) {
      instance = new InvolverElementHelper();
    }
    return instance;
  }

  public Object doSwitch(InvolverElement element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(CapellacorePackage.Literals.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS)) {
      ret = getInvolvedInvolvements(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = CapellaElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected boolean isCrossReferencing(InvolverElement element_p) {
    return _isCrossReferencing.contains(element_p);

  }

  protected void markAsCrossReferenced(InvolverElement element_p) {
    _isCrossReferencing.add(element_p);
  }

  protected void unmarkAsCrossReferenced(InvolverElement element_p) {
    _isCrossReferencing.remove(element_p);
  }

  protected List<Involvement> getInvolvedInvolvements(InvolverElement element_p) {
    List<Involvement> ret = new ArrayList<Involvement>();

    if (!isCrossReferencing(element_p)) {
      try {
        markAsCrossReferenced(element_p);
        TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(element_p);
        if ((editingDomain != null) && (editingDomain instanceof SemanticEditingDomain)) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);

          for (EStructuralFeature.Setting setting : references) {
            if (CapellacorePackage.Literals.INVOLVEMENT__INVOLVER.equals(setting.getEStructuralFeature())) {
              ret.add((Involvement) setting.getEObject());
            }
          }
        }
      } finally {
        unmarkAsCrossReferenced(element_p);
      }
    } else {
      System.out.println("(involver) re-entrance: " + element_p.getFullLabel()); //$NON-NLS-1$
    }
    return ret;
  }
}
