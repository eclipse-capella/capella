/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.handlers.location;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

/**
 * A single entry point into a default capella model structure.
 *
 * This eliminates the need to work with the varioux XYZExt
 * helper classes which are too painful to work with..
 * FIXME move somewhere else
 */
public class CapellaModelSkeleton extends AdapterImpl {

  /**
   * A helper that retrieves a skeleton adapter from the arguments root container and if
   * none is present creates and attaches one.
   *
   * @param capellaModelElement
   * @return a skeleton instance, or null if the root container object is not a capella Project
   */
  public static CapellaModelSkeleton asSkeleton(EObject capellaModelElement) {
    CapellaModelSkeleton result = null;
    EObject root = EcoreUtil.getRootContainer(capellaModelElement);
    result = (CapellaModelSkeleton) EcoreUtil.getExistingAdapter(root, CapellaModelSkeleton.class);
    if (result == null) {
      CapellaModelSkeleton sk = new CapellaModelSkeleton();
      if (sk.isAdapterForType(root)) {
        root.eAdapters().add(sk);
        result = sk;
      }
    }
    return result;
  }

  public Project getProject() {
    return (Project) getTarget();
  }

  public SystemEngineering getSystemEngineering() {
    return SystemEngineeringExt.getSystemEngineering(getProject());
  }

  public PhysicalArchitecture getPhysicalArchitecture() {
    return SystemEngineeringExt.getOwnedPhysicalArchitecture(getSystemEngineering());
  }

  public LogicalArchitecture getLogicalArchitecture() {
    return SystemEngineeringExt.getLogicalArchitecture(getSystemEngineering());
  }

  public OperationalAnalysis getOperationalAnalysis() {
    return SystemEngineeringExt.getOperationalAnalysis(getSystemEngineering());
  }

  public SystemAnalysis getSystemAnalysis() {
    return SystemEngineeringExt.getSystemAnalysis(getSystemEngineering());
  }

  @Override
  public boolean isAdapterForType(Object type) {
    return type instanceof Project;
  }

}
