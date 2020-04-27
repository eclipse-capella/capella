/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
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

  public Object doSwitch(PhysicalPathInvolvement element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__INVOLVED_ELEMENT)) {
      ret = getInvolvedElement(element);
    } else if (feature.equals(CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__INVOLVED_COMPONENT)) {
      ret = getInvolvedComponent(element);
    } else if (feature.equals(CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__PREVIOUS_INVOLVEMENTS)) {
      ret = getPreviousInvolvements(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InvolvementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected AbstractPathInvolvedElement getInvolvedElement(PhysicalPathInvolvement element) {
    if (element != null) {
      InvolvedElement involvedElement = element.getInvolved();
      if (involvedElement instanceof AbstractPathInvolvedElement) {
        return (AbstractPathInvolvedElement) involvedElement;
      }
    }
    return null;
  }

  protected Component getInvolvedComponent(PhysicalPathInvolvement element) {
    if (element != null) {
      InvolvedElement involvedElement = element.getInvolved();
      if (involvedElement instanceof Part) {
        Type type = ((Part) involvedElement).getType();
        if (type instanceof Component) {
          return (Component) type;
        }
      }
    }
    return null;
  }

  protected List<PhysicalPathInvolvement> getPreviousInvolvements(PhysicalPathInvolvement element) {
    List<PhysicalPathInvolvement> ret = new ArrayList<>();
    EObject owner = element.eContainer();
    if (owner instanceof PhysicalPath) {
      for (EObject anInverseReference : EObjectExt.getReferencers(element, CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__NEXT_INVOLVEMENTS)) {
        if ((anInverseReference instanceof PhysicalPathInvolvement) && ((PhysicalPath) owner).getOwnedPhysicalPathInvolvements().contains(anInverseReference)) {
          ret.add((PhysicalPathInvolvement) anInverseReference);
        }
      }
    }
    return ret;
  }
}
