/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.pa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.helpers.cs.delegates.BlockArchitectureHelper;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;

public class PhysicalArchitectureHelper {
  private static PhysicalArchitectureHelper instance;

  private PhysicalArchitectureHelper() {
    // do nothing
  }

  public static PhysicalArchitectureHelper getInstance() {
    if (instance == null)
      instance = new PhysicalArchitectureHelper();
    return instance;
  }

  public Object doSwitch(PhysicalArchitecture element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURE_REALIZATIONS)) {
      ret = getAllocatedLogicalArchitectureRealizations(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG)) {
      ret = getContainedCapabilityRealizationPkg(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_PHYSICAL_FUNCTION_PKG)) {
      ret = getContainedPhysicalFunctionPkg(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURES)) {
      ret = getAllocatedLogicalArchitectures(element);
    } else if (feature.equals(PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATING_EPBS_ARCHITECTURES)) {
      ret = getAllocatingEpbsArchitectures(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = BlockArchitectureHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<LogicalArchitectureRealization> getAllocatedLogicalArchitectureRealizations(PhysicalArchitecture element) {
    List<LogicalArchitectureRealization> ret = new ArrayList<>();

    for (ArchitectureAllocation architectureAllocation : element.getProvisionedArchitectureAllocations()) {
      if (architectureAllocation instanceof LogicalArchitectureRealization) {
        ret.add((LogicalArchitectureRealization) architectureAllocation);
      }
    }
    return ret;
  }

  protected CapabilityRealizationPkg getContainedCapabilityRealizationPkg(PhysicalArchitecture element) {
    AbstractCapabilityPkg abstractCapabilityPkg = element.getOwnedAbstractCapabilityPkg();
    if (abstractCapabilityPkg instanceof CapabilityRealizationPkg) {
      return (CapabilityRealizationPkg) abstractCapabilityPkg;
    }
    return null;
  }

  protected PhysicalFunctionPkg getContainedPhysicalFunctionPkg(PhysicalArchitecture element) {
    FunctionPkg functionPkg = element.getOwnedFunctionPkg();
    if (functionPkg instanceof PhysicalFunctionPkg) {
      return (PhysicalFunctionPkg) functionPkg;
    }
    return null;
  }

  protected List<LogicalArchitecture> getAllocatedLogicalArchitectures(PhysicalArchitecture element){
    List <LogicalArchitecture> ret = new ArrayList<>();
    for (BlockArchitecture architecture : element.getAllocatedArchitectures()) {
      if (architecture instanceof LogicalArchitecture) {
        ret.add((LogicalArchitecture) architecture);
      }
    }
    return ret;
  }

  protected List <EPBSArchitecture> getAllocatingEpbsArchitectures(PhysicalArchitecture element) {
    List <EPBSArchitecture> ret = new ArrayList<>();
    for (BlockArchitecture architecture : element.getAllocatingArchitectures()) {
      if (architecture instanceof EPBSArchitecture) {
        ret.add((EPBSArchitecture) architecture);
      }
    }
    return ret;
  }
}
