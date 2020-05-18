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

package org.polarsys.capella.core.data.helpers.capellacore.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;

public class InvolverElementHelper {
  private static InvolverElementHelper instance;

  private InvolverElementHelper() {
  }

  public static InvolverElementHelper getInstance() {
    if (instance == null) {
      instance = new InvolverElementHelper();
    }
    return instance;
  }

  public Object doSwitch(InvolverElement element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CapellacorePackage.Literals.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS)) {
      ret = getInvolvedInvolvements(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = CapellaElementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<Involvement> getInvolvedInvolvements(InvolverElement element) {
    List<Involvement> result = new ArrayList<>();
    for (EObject child : element.eContents()) {
      if (child instanceof Involvement) {
        result.add((Involvement)child);
      }
    }
    return result;
  }
}
