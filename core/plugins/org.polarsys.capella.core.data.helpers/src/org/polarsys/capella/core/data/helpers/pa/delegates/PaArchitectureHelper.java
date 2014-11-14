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

public class PaArchitectureHelper {
  private static PaArchitectureHelper instance;

  private PaArchitectureHelper() {
    // do nothing
  }

  public static PaArchitectureHelper getInstance() {
    if (instance == null)
      instance = new PaArchitectureHelper();
    return instance;
  }

  public Object doSwitch(PhysicalArchitecture element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURE_REALIZATIONS)) {
      ret = getAllocatedLogicalArchitectureRealizations(element_p);
    } else if (feature_p.equals(PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG)) {
      ret = getContainedCapabilityRealizationPkg(element_p);
    } else if (feature_p.equals(PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_PHYSICAL_FUNCTION_PKG)) {
      ret = getContainedPhysicalFunctionPkg(element_p);
    } else if (feature_p.equals(PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURES)) {
      ret = getAllocatedLogicalArchitectures(element_p);
    } else if (feature_p.equals(PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATING_EPBS_ARCHITECTURES)) {
      ret = getAllocatingEpbsArchitectures(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = BlockArchitectureHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<LogicalArchitectureRealization> getAllocatedLogicalArchitectureRealizations(PhysicalArchitecture element_p) {
    List<LogicalArchitectureRealization> ret = new ArrayList<LogicalArchitectureRealization>();

    for (ArchitectureAllocation architectureAllocation : element_p.getProvisionedArchitectureAllocations()) {
      if (architectureAllocation instanceof LogicalArchitectureRealization) {
        ret.add((LogicalArchitectureRealization) architectureAllocation);
      }
    }
    return ret;
  }

  protected CapabilityRealizationPkg getContainedCapabilityRealizationPkg(PhysicalArchitecture element_p) {
    AbstractCapabilityPkg abstractCapabilityPkg = element_p.getOwnedAbstractCapabilityPkg();
    if (abstractCapabilityPkg instanceof CapabilityRealizationPkg) {
      return (CapabilityRealizationPkg) abstractCapabilityPkg;
    }
    return null;
  }

  protected PhysicalFunctionPkg getContainedPhysicalFunctionPkg(PhysicalArchitecture element_p) {
    FunctionPkg functionPkg = element_p.getOwnedFunctionPkg();
    if (functionPkg instanceof PhysicalFunctionPkg) {
      return (PhysicalFunctionPkg) functionPkg;
    }
    return null;
  }

  protected List<LogicalArchitecture> getAllocatedLogicalArchitectures(PhysicalArchitecture element_p){
    List <LogicalArchitecture> ret = new ArrayList<LogicalArchitecture>();
    for (BlockArchitecture architecture : element_p.getAllocatedArchitectures()) {
      if (architecture instanceof LogicalArchitecture) {
        ret.add((LogicalArchitecture) architecture);
      }
    }
    return ret;
  }

  protected List <EPBSArchitecture> getAllocatingEpbsArchitectures(PhysicalArchitecture element_p) {
    List <EPBSArchitecture> ret = new ArrayList<EPBSArchitecture>();
    for (BlockArchitecture architecture : element_p.getAllocatingArchitectures()) {
      if (architecture instanceof EPBSArchitecture) {
        ret.add((EPBSArchitecture) architecture);
      }
    }
    return ret;
  }
}
