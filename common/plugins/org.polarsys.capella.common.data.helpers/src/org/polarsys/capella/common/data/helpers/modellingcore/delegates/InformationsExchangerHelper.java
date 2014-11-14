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
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

public class InformationsExchangerHelper {
  private static InformationsExchangerHelper instance;
  /**
   * Cross referencing re-entrance collection for outgoing flows.
   */
  private List<InformationsExchanger> _isCrossReferencingOutgoing;
  /**
   * Cross referencing re-entrance collection for incoming flows.
   */
  private List<InformationsExchanger> _isCrossReferencingIncoming;

  private InformationsExchangerHelper() {
    _isCrossReferencingOutgoing = new ArrayList<InformationsExchanger>(0);
    _isCrossReferencingIncoming = new ArrayList<InformationsExchanger>(0);
  }

  public static InformationsExchangerHelper getInstance() {
    if (instance == null) {
      instance = new InformationsExchangerHelper();
    }
    return instance;
  }

  public Object doSwitch(InformationsExchanger element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS)) {
      ret = getIncomingFlows(element_p);
    } else if (feature_p.equals(ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS)) {
      ret = getOutgoingFlows(element_p);
    } else if (feature_p.equals(ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS)) {
      ret = getInformationFlows(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = ModelElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected boolean isCrossReferencing(InformationsExchanger element_p, boolean incomingFlows_p) {
    return incomingFlows_p ? _isCrossReferencingIncoming.contains(element_p) : _isCrossReferencingOutgoing.contains(element_p);

  }

  protected void markAsCrossReferenced(InformationsExchanger element_p, boolean incomingFlows_p) {
    if (incomingFlows_p) {
      _isCrossReferencingIncoming.add(element_p);
    } else {
      _isCrossReferencingOutgoing.add(element_p);
    }
  }

  protected void unmarkAsCrossReferenced(InformationsExchanger element_p, boolean incomingFlows_p) {
    if (incomingFlows_p) {
      _isCrossReferencingIncoming.remove(element_p);
    } else {
      _isCrossReferencingOutgoing.remove(element_p);
    }
  }

  protected List<AbstractInformationFlow> getIncomingFlows(InformationsExchanger element_p) {
    List<AbstractInformationFlow> ret = new ArrayList<AbstractInformationFlow>();

    if (!isCrossReferencing(element_p, true)) {
      try {
        markAsCrossReferenced(element_p, true);

        TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(element_p);
        if ((editingDomain != null) && (editingDomain instanceof SemanticEditingDomain)) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);
          for (EStructuralFeature.Setting setting : references) {
            if (ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET.equals(setting.getEStructuralFeature())) {
              ret.add((AbstractInformationFlow) setting.getEObject());
            }
          }
        }
      } finally {
        unmarkAsCrossReferenced(element_p, true);
      }
    }
    return ret;
  }

  protected List<AbstractInformationFlow> getOutgoingFlows(InformationsExchanger element_p) {
    List<AbstractInformationFlow> ret = new ArrayList<AbstractInformationFlow>();

    if (!isCrossReferencing(element_p, false)) {
      try {
        markAsCrossReferenced(element_p, false);
        TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(element_p);
        if ((editingDomain != null) && (editingDomain instanceof SemanticEditingDomain)) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);
          for (EStructuralFeature.Setting setting : references) {
            if (ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE.equals(setting.getEStructuralFeature())) {
              ret.add((AbstractInformationFlow) setting.getEObject());
            }
          }
        }
      } finally {
        unmarkAsCrossReferenced(element_p, false);
      }
    }
    return ret;
  }

  /**
   * @param element_p
   * @return
   */
  private Object getInformationFlows(InformationsExchanger element_p) {
    List<AbstractInformationFlow> ret = new ArrayList<AbstractInformationFlow>();

    if (!isCrossReferencing(element_p, false) || !isCrossReferencing(element_p, true)) {
      try {
        markAsCrossReferenced(element_p, false);
        markAsCrossReferenced(element_p, true);

        TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(element_p);
        if ((editingDomain != null) && (editingDomain instanceof SemanticEditingDomain)) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);
          for (EStructuralFeature.Setting setting : references) {
            if (ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE.equals(setting.getEStructuralFeature())) {
              ret.add((AbstractInformationFlow) setting.getEObject());
            }
            if (ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET.equals(setting.getEStructuralFeature())) {
              ret.add((AbstractInformationFlow) setting.getEObject());
            }
          }
        }
      } finally {
        unmarkAsCrossReferenced(element_p, false);
        unmarkAsCrossReferenced(element_p, true);
      }
    }
    return ret;
  }
}
