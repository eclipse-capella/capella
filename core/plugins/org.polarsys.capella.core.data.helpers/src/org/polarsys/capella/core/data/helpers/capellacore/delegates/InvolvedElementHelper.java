/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;

public class InvolvedElementHelper {
  private static InvolvedElementHelper instance;

  private InvolvedElementHelper() {
  }

  public static InvolvedElementHelper getInstance() {
    if (instance == null) {
      instance = new InvolvedElementHelper();
    }
    return instance;
  }

  public Object doSwitch(InvolvedElement element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS)) {
      ret = getInvolvingInvolvements(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = CapellaElementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<Involvement> getInvolvingInvolvements(InvolvedElement element) {
    return EObjectExt.getReferencers(element, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED);
  }
}
