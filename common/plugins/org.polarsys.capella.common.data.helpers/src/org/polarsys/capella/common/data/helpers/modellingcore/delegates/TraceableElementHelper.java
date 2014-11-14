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
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

public class TraceableElementHelper {
  private static TraceableElementHelper instance;
  /**
   * Cross referencing re-entrance collection for outgoing traces.
   */
  private List<TraceableElement> _isCrossReferencingOutgoing;
  /**
   * Cross referencing re-entrance collection for incoming traces.
   */
  private List<TraceableElement> _isCrossReferencingIncoming;

  private TraceableElementHelper() {
    _isCrossReferencingOutgoing = new ArrayList<TraceableElement>(0);
    _isCrossReferencingIncoming = new ArrayList<TraceableElement>(0);
  }

  public static TraceableElementHelper getInstance() {
    if (instance == null) {
      instance = new TraceableElementHelper();
    }
    return instance;
  }

  public Object doSwitch(TraceableElement element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(ModellingcorePackage.Literals.TRACEABLE_ELEMENT__INCOMING_TRACES)) {
      ret = getIncomingTraces(element_p);
    } else if (feature_p.equals(ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES)) {
      ret = getOutgoingTraces(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = ModelElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected boolean isCrossReferencing(TraceableElement element_p, boolean incomingTraces_p) {
    return incomingTraces_p ? _isCrossReferencingIncoming.contains(element_p) : _isCrossReferencingOutgoing.contains(element_p);

  }

  protected void markAsCrossReferenced(TraceableElement element_p, boolean incomingTraces_p) {
    if (incomingTraces_p) {
      _isCrossReferencingIncoming.add(element_p);
    } else {
      _isCrossReferencingOutgoing.add(element_p);
    }
  }

  protected void unmarkAsCrossReferenced(TraceableElement element_p, boolean incomingTraces_p) {
    if (incomingTraces_p) {
      _isCrossReferencingIncoming.remove(element_p);
    } else {
      _isCrossReferencingOutgoing.remove(element_p);
    }
  }

  protected List<AbstractTrace> getIncomingTraces(TraceableElement element_p) {
    List<AbstractTrace> ret = new ArrayList<AbstractTrace>();

    if (!isCrossReferencing(element_p, true)) {
      try {
        markAsCrossReferenced(element_p, true);

        TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(element_p);
        if (editingDomain instanceof SemanticEditingDomain) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);

          for (EStructuralFeature.Setting setting : references) {
            if (ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT.equals(setting.getEStructuralFeature())) {
              ret.add((AbstractTrace) setting.getEObject());
            }
          }
        }
      } finally {
        unmarkAsCrossReferenced(element_p, true);
      }
    }
    return ret;
  }

  protected List<AbstractTrace> getOutgoingTraces(TraceableElement element_p) {
    List<AbstractTrace> ret = new ArrayList<AbstractTrace>();

    if (!isCrossReferencing(element_p, false)) {
      try {
        markAsCrossReferenced(element_p, false);
        TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(element_p);
        if ((editingDomain != null) && (editingDomain instanceof SemanticEditingDomain)) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);
          for (EStructuralFeature.Setting setting : references) {
            if (ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT.equals(setting.getEStructuralFeature())) {
              ret.add((AbstractTrace) setting.getEObject());
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
