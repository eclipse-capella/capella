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

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.cs.PhysicalPathRealization;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentExchangeAllocatorHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolverElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class PhysicalPathHelper {
  private static PhysicalPathHelper instance;

  private PhysicalPathHelper() {
    // do nothing
  }

  public static PhysicalPathHelper getInstance() {
    if (instance == null) {
      instance = new PhysicalPathHelper();
    }
    return instance;
  }

  public Object doSwitch(PhysicalPath element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(CsPackage.Literals.PHYSICAL_PATH__FIRST_PHYSICAL_PATH_INVOLVEMENTS)) {
      ret = getFirstPhysicalPathInvolvements(element_p);
    } else if (feature_p.equals(CsPackage.Literals.PHYSICAL_PATH__REALIZED_PHYSICAL_PATHS)) {
      ret = getRealizedPhysicalPaths(element_p);
    } else if (feature_p.equals(CsPackage.Literals.PHYSICAL_PATH__REALIZING_PHYSICAL_PATHS)) {
      ret = getRealizingPhysicalPaths(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = NamedElementHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = ComponentExchangeAllocatorHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = AbstractPathInvolvedElementHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (null == ret) {
      ret = InvolverElementHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<PhysicalPathInvolvement> getFirstPhysicalPathInvolvements(PhysicalPath element_p) {
    List<PhysicalPathInvolvement> ret = new ArrayList<PhysicalPathInvolvement>();
    for (PhysicalPathInvolvement inv : element_p.getOwnedPhysicalPathInvolvements()) {
      if ((inv.getInvolved() != null) && inv.getPreviousInvolvements().isEmpty()) {
        ret.add(inv);
      }
    }
    return ret;
  }

  protected List<PhysicalPath> getRealizedPhysicalPaths(PhysicalPath element_p) {
    List<PhysicalPath> paths = new ArrayList<PhysicalPath>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof PhysicalPathRealization) {
        TraceableElement path = ((PhysicalPathRealization) trace).getTargetElement();
        if (path instanceof PhysicalPath) {
          paths.add((PhysicalPath) path);
        }
      }
    }
    return paths;
  }

  protected List<PhysicalPath> getRealizingPhysicalPaths(PhysicalPath element_p) {
    List<PhysicalPath> paths = new ArrayList<PhysicalPath>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof PhysicalPathRealization) {
        TraceableElement path = ((PhysicalPathRealization) trace).getSourceElement();
        if (path instanceof PhysicalPath) {
          paths.add((PhysicalPath) path);
        }
      }
    }
    return paths;
  }
}
