/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

public class EPBSArchitectureHelper {
  private static EPBSArchitectureHelper instance;

  private EPBSArchitectureHelper() {
    // do nothing
  }

  public static EPBSArchitectureHelper getInstance() {
    if (instance == null)
      instance = new EPBSArchitectureHelper();
    return instance;
  }

  public Object doSwitch(EPBSArchitecture element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(EpbsPackage.Literals.EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURE_REALIZATIONS)) {
      ret = getAllocatedPhysicalArchitectureRealizations(element);
    } else if (feature.equals(EpbsPackage.Literals.EPBS_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG)) {
      ret = getContainedCapabilityRealizationPkg(element);
    } else if (feature.equals(EpbsPackage.Literals.EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURES)) {
      ret = getAllocatedPhysicalArchitectures(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = BlockArchitectureHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<PhysicalArchitectureRealization> getAllocatedPhysicalArchitectureRealizations(EPBSArchitecture element) {
    List<PhysicalArchitectureRealization> ret = new ArrayList<>();

    for (ArchitectureAllocation architectureAllocation : element.getProvisionedArchitectureAllocations()) {
      if (architectureAllocation instanceof PhysicalArchitectureRealization) {
        ret.add((PhysicalArchitectureRealization) architectureAllocation);
      }
    }
    return ret;
  }

  protected CapabilityRealizationPkg getContainedCapabilityRealizationPkg(EPBSArchitecture element) {
    AbstractCapabilityPkg abstractCapabilityPkg = element.getOwnedAbstractCapabilityPkg();
    if (abstractCapabilityPkg instanceof CapabilityRealizationPkg) {
      return (CapabilityRealizationPkg) abstractCapabilityPkg;
    }
    return null;
  }

  protected List<PhysicalArchitecture> getAllocatedPhysicalArchitectures(EPBSArchitecture element){
    List <PhysicalArchitecture> ret = new ArrayList<>();
    for (BlockArchitecture architecture : element.getAllocatedArchitectures()) {
      if (architecture instanceof PhysicalArchitecture) {
        ret.add((PhysicalArchitecture) architecture);
      }
    }
    return ret;
  }
}
