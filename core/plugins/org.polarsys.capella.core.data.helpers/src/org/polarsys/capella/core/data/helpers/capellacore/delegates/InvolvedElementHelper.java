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
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.SemanticEditingDomain;

public class InvolvedElementHelper {
  private static InvolvedElementHelper instance;
  /**
   * Cross referencing re-entrance collection.
   */
  private List<InvolvedElement> _isCrossReferencing;

  private InvolvedElementHelper() {
    _isCrossReferencing = new ArrayList<InvolvedElement>(0);
  }

  public static InvolvedElementHelper getInstance() {
    if (instance == null) {
      instance = new InvolvedElementHelper();
    }
    return instance;
  }

  public Object doSwitch(InvolvedElement element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS)) {
      ret = getInvolvingInvolvements(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = CapellaElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected boolean isCrossReferencing(InvolvedElement element_p) {
    return _isCrossReferencing.contains(element_p);

  }

  protected void markAsCrossReferenced(InvolvedElement element_p) {
    _isCrossReferencing.add(element_p);
  }

  protected void unmarkAsCrossReferenced(InvolvedElement element_p) {
    _isCrossReferencing.remove(element_p);
  }

  protected List<Involvement> getInvolvingInvolvements(InvolvedElement element_p) {
    List<Involvement> ret = new ArrayList<Involvement>();

    if (!isCrossReferencing(element_p)) {
      try {
        markAsCrossReferenced(element_p);
        TransactionalEditingDomain editingDomain = MDEAdapterFactory.getEditingDomain(element_p);
        if ((editingDomain != null) && (editingDomain instanceof SemanticEditingDomain)) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);

          for (EStructuralFeature.Setting setting : references) {
            if (CapellacorePackage.Literals.INVOLVEMENT__INVOLVED.equals(setting.getEStructuralFeature())) {
              ret.add((Involvement) setting.getEObject());
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
