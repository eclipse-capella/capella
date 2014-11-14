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
package org.polarsys.capella.core.data.helpers.cs.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvementHelper;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Type;

public class PhysicalPathInvolvementHelper {
  private static PhysicalPathInvolvementHelper instance;

  private PhysicalPathInvolvementHelper() {
    // do nothing
  }

  public static PhysicalPathInvolvementHelper getInstance() {
    if (instance == null)
      instance = new PhysicalPathInvolvementHelper();
    return instance;
  }

  public Object doSwitch(PhysicalPathInvolvement element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__INVOLVED_ELEMENT)) {
      ret = getInvolvedElement(element_p);
    } else if (feature_p.equals(CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__INVOLVED_COMPONENT)) {
      ret = getInvolvedComponent(element_p);
    } else if (feature_p.equals(CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__PREVIOUS_INVOLVEMENTS)) {
      ret = getPreviousInvolvements(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InvolvementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected AbstractPathInvolvedElement getInvolvedElement(PhysicalPathInvolvement element_p) {
    if (element_p != null) {
      InvolvedElement involvedElement = element_p.getInvolved();
      if (involvedElement instanceof AbstractPathInvolvedElement) {
        return (AbstractPathInvolvedElement) involvedElement;
      }
    }
    return null;
  }

  protected Component getInvolvedComponent(PhysicalPathInvolvement element_p) {
    if (element_p != null) {
      InvolvedElement involvedElement = element_p.getInvolved();
      if (involvedElement instanceof Part) {
        Type type = ((Part) involvedElement).getType();
        if (type instanceof Component) {
          return (Component) type;
        }
      }
    }
    return null;
  }

  protected List<PhysicalPathInvolvement> getPreviousInvolvements(PhysicalPathInvolvement element_p) {
    List<PhysicalPathInvolvement> ret = new ArrayList<PhysicalPathInvolvement>();
    EObject owner = element_p.eContainer();
    if (owner instanceof PhysicalPath) {
      for (EObject anInverseReference : EObjectExt.getReferencers(element_p, CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__NEXT_INVOLVEMENTS)) {
        if ((anInverseReference instanceof PhysicalPathInvolvement) && ((PhysicalPath) owner).getOwnedPhysicalPathInvolvements().contains(anInverseReference)) {
          ret.add((PhysicalPathInvolvement) anInverseReference);
        }
      }
    }
    return ret;
  }
}
