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
package org.polarsys.capella.common.data.helpers.activity.delegates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.ActivityPartition;
import org.polarsys.capella.common.data.activity.InterruptibleActivityRegion;
import org.polarsys.capella.common.data.activity.StructuredActivityNode;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

public class ActivityNodeHelper {
  private static ActivityNodeHelper instance;
  /**
   * Cross referencing re-entrance collection for outgoing edges.
   */
  private List<ActivityNode> _isCrossReferencingOutgoing;
  /**
   * Cross referencing re-entrance collection for incoming edges.
   */
  private List<ActivityNode> _isCrossReferencingIncoming;

  private ActivityNodeHelper() {
    _isCrossReferencingOutgoing = new ArrayList<ActivityNode>(0);
    _isCrossReferencingIncoming = new ArrayList<ActivityNode>(0);
  }

  public static ActivityNodeHelper getInstance() {
    if (instance == null) {
      instance = new ActivityNodeHelper();
    }
    return instance;
  }

  public Object doSwitch(ActivityNode element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(ActivityPackage.Literals.ACTIVITY_NODE__IN_ACTIVITY_PARTITION)) {
      ret = getInActivityPartition(element_p);
    } else if (feature_p.equals(ActivityPackage.Literals.ACTIVITY_NODE__IN_INTERRUPTIBLE_REGION)) {
      ret = getInInterruptibleRegion(element_p);
    } else if (feature_p.equals(ActivityPackage.Literals.ACTIVITY_NODE__IN_STRUCTURED_NODE)) {
      ret = getInStructuredNode(element_p);
    } else if (feature_p.equals(ActivityPackage.Literals.ACTIVITY_NODE__INCOMING)) {
      ret = getIncomingEdges(element_p);
    } else if (feature_p.equals(ActivityPackage.Literals.ACTIVITY_NODE__OUTGOING)) {
      ret = getOutgoingEdges(element_p);
    }

    return ret;
  }

  protected ActivityPartition getInActivityPartition(ActivityNode element_p) {
    EObject group = element_p.eContainer();
    if ((null != group) && (group instanceof ActivityPartition)) {
      return (ActivityPartition) group;
    }
    return null;
  }

  protected InterruptibleActivityRegion getInInterruptibleRegion(ActivityNode element_p) {
    EObject group = element_p.eContainer();
    if ((null != group) && (group instanceof InterruptibleActivityRegion)) {
      return (InterruptibleActivityRegion) group;
    }
    return null;
  }

  protected StructuredActivityNode getInStructuredNode(ActivityNode element_p) {
    EObject group = element_p.eContainer();
    if ((null != group) && (group instanceof StructuredActivityNode)) {
      return (StructuredActivityNode) group;
    }
    return null;
  }

  protected boolean isCrossReferencing(ActivityNode element_p, boolean incomingEdges_p) {
    return incomingEdges_p ? _isCrossReferencingIncoming.contains(element_p) : _isCrossReferencingOutgoing.contains(element_p);

  }

  protected void markAsCrossReferenced(ActivityNode element_p, boolean incomingEdges_p) {
    if (incomingEdges_p) {
      _isCrossReferencingIncoming.add(element_p);
    } else {
      _isCrossReferencingOutgoing.add(element_p);
    }
  }

  protected void unmarkAsCrossReferenced(ActivityNode element_p, boolean incomingEdges_p) {
    if (incomingEdges_p) {
      _isCrossReferencingIncoming.remove(element_p);
    } else {
      _isCrossReferencingOutgoing.remove(element_p);
    }
  }

  protected List<ActivityEdge> getIncomingEdges(ActivityNode element_p) {
    List<ActivityEdge> ret = new ArrayList<ActivityEdge>();

    if (!isCrossReferencing(element_p, true)) {
      try {
        markAsCrossReferenced(element_p, true);

        TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(element_p);
        if ((editingDomain != null) && (editingDomain instanceof SemanticEditingDomain)) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);
          for (EStructuralFeature.Setting setting : references) {
            if (ActivityPackage.Literals.ACTIVITY_EDGE__TARGET.equals(setting.getEStructuralFeature())) {
              ret.add((ActivityEdge) setting.getEObject());
            }
          }
        }
      } finally {
        unmarkAsCrossReferenced(element_p, true);
      }
    }
    return ret;
  }

  protected List<ActivityEdge> getOutgoingEdges(ActivityNode element_p) {
    List<ActivityEdge> ret = new ArrayList<ActivityEdge>();

    if (!isCrossReferencing(element_p, false)) {
      try {
        markAsCrossReferenced(element_p, false);
        TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain(element_p);

        if ((editingDomain != null) && (editingDomain instanceof SemanticEditingDomain)) {
          Collection<Setting> references = ((SemanticEditingDomain) editingDomain).getDerivedCrossReferencer().getInverseReferences(element_p, true);
          for (EStructuralFeature.Setting setting : references) {
            if (ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE.equals(setting.getEStructuralFeature())) {
              ret.add((ActivityEdge) setting.getEObject());
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
