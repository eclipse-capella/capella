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

package org.polarsys.capella.core.data.helpers.pa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalNode;

public class PhysicalNodeHelper {
  private static PhysicalNodeHelper instance;

  private PhysicalNodeHelper() {
    // do nothing
  }

  public static PhysicalNodeHelper getInstance() {
    if (instance == null) {
      instance = new PhysicalNodeHelper();
    }
    return instance;
  }

  public Object doSwitch(PhysicalNode element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(PaPackage.Literals.PHYSICAL_NODE__SUB_PHYSICAL_NODES)) {
      ret = getSubPhysicalNodes(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = PhysicalComponentHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<PhysicalNode> getSubPhysicalNodes(PhysicalNode element) {
    List<PhysicalNode> ret = new ArrayList<>();
    for (Feature feature : element.getOwnedFeatures()) {
      if (feature instanceof Part) {
        Type type = ((Part) feature).getType();
        // we need to be invariant
        if ((null != type) && type.eClass().equals(PaPackage.Literals.PHYSICAL_NODE)) {
          ret.add((PhysicalNode) type);
        }
      }
    }
    return ret;
  }
}
