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
package org.polarsys.capella.core.data.helpers.epbs.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization;
import org.polarsys.capella.core.data.helpers.cs.delegates.BlockArchitectureHelper;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;

public class EpbsArchitectureHelper {
  private static EpbsArchitectureHelper instance;

  private EpbsArchitectureHelper() {
    // do nothing
  }

  public static EpbsArchitectureHelper getInstance() {
    if (instance == null)
      instance = new EpbsArchitectureHelper();
    return instance;
  }

  public Object doSwitch(EPBSArchitecture element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(EpbsPackage.Literals.EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURE_REALIZATIONS)) {
      ret = getAllocatedPhysicalArchitectureRealizations(element_p);
    } else if (feature_p.equals(EpbsPackage.Literals.EPBS_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG)) {
      ret = getContainedCapabilityRealizationPkg(element_p);
    } else if (feature_p.equals(EpbsPackage.Literals.EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURES)) {
      ret = getAllocatedPhysicalArchitectures(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = BlockArchitectureHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<PhysicalArchitectureRealization> getAllocatedPhysicalArchitectureRealizations(EPBSArchitecture element_p) {
    List<PhysicalArchitectureRealization> ret = new ArrayList<PhysicalArchitectureRealization>();

    for (ArchitectureAllocation architectureAllocation : element_p.getProvisionedArchitectureAllocations()) {
      if (architectureAllocation instanceof PhysicalArchitectureRealization) {
        ret.add((PhysicalArchitectureRealization) architectureAllocation);
      }
    }
    return ret;
  }

  protected CapabilityRealizationPkg getContainedCapabilityRealizationPkg(EPBSArchitecture element_p) {
    AbstractCapabilityPkg abstractCapabilityPkg = element_p.getOwnedAbstractCapabilityPkg();
    if (abstractCapabilityPkg instanceof CapabilityRealizationPkg) {
      return (CapabilityRealizationPkg) abstractCapabilityPkg;
    }
    return null;
  }

  protected List<PhysicalArchitecture> getAllocatedPhysicalArchitectures(EPBSArchitecture element_p){
    List <PhysicalArchitecture> ret = new ArrayList<PhysicalArchitecture>();
    for (BlockArchitecture architecture : element_p.getAllocatedArchitectures()) {
      if (architecture instanceof PhysicalArchitecture) {
        ret.add((PhysicalArchitecture) architecture);
      }
    }
    return ret;
  }
}
