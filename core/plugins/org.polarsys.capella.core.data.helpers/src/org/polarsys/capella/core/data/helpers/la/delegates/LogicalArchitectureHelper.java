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

package org.polarsys.capella.core.data.helpers.la.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.helpers.cs.delegates.BlockArchitectureHelper;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.la.SystemAnalysisRealization;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;

public class LogicalArchitectureHelper {
  private static LogicalArchitectureHelper instance;

  private LogicalArchitectureHelper() {
    // do nothing
  }

  public static LogicalArchitectureHelper getInstance() {
    if (instance == null)
      instance = new LogicalArchitectureHelper();
    return instance;
  }

  public Object doSwitch(LogicalArchitecture element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(LaPackage.Literals.LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSIS_REALIZATIONS)) {
      ret = getAllocatedSystemAnalysisRealizations(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG)) {
      ret = getContainedCapabilityRealizationPkg(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_ARCHITECTURE__CONTAINED_LOGICAL_FUNCTION_PKG)) {
      ret = getContainedLogicalFunctionPkg(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSES)) {
      ret = getAllocatedSystemAnalyses(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_ARCHITECTURE__ALLOCATING_PHYSICAL_ARCHITECTURES)) {
      ret = getAllocatingPhysicalArchitectures(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = BlockArchitectureHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<SystemAnalysisRealization> getAllocatedSystemAnalysisRealizations(LogicalArchitecture element) {
    List<SystemAnalysisRealization> ret = new ArrayList<>();

    for (ArchitectureAllocation architectureAllocation : element.getProvisionedArchitectureAllocations()) {
      if (architectureAllocation instanceof SystemAnalysisRealization) {
        ret.add((SystemAnalysisRealization) architectureAllocation);
      }
    }
    return ret;
  }

  protected CapabilityRealizationPkg getContainedCapabilityRealizationPkg(LogicalArchitecture element) {
    AbstractCapabilityPkg abstractCapabilityPkg = element.getOwnedAbstractCapabilityPkg();
    if (abstractCapabilityPkg instanceof CapabilityRealizationPkg) {
      return (CapabilityRealizationPkg) abstractCapabilityPkg;
    }
    return null;
  }

  protected LogicalFunctionPkg getContainedLogicalFunctionPkg(LogicalArchitecture element) {
    FunctionPkg functionPkg = element.getOwnedFunctionPkg();
    if (functionPkg instanceof LogicalFunctionPkg) {
      return (LogicalFunctionPkg) functionPkg;
    }
    return null;
  }

  protected List<SystemAnalysis> getAllocatedSystemAnalyses(LogicalArchitecture element){
    List <SystemAnalysis> ret = new ArrayList<>();
    for (BlockArchitecture architecture : element.getAllocatedArchitectures()) {
      if (architecture instanceof SystemAnalysis) {
        ret.add((SystemAnalysis) architecture);
      }
    }
    return ret;
  }

  protected List <PhysicalArchitecture> getAllocatingPhysicalArchitectures(LogicalArchitecture element) {
    List <PhysicalArchitecture> ret = new ArrayList<>();
    for (BlockArchitecture architecture : element.getAllocatingArchitectures()) {
      if (architecture instanceof PhysicalArchitecture) {
        ret.add((PhysicalArchitecture) architecture);
      }
    }
    return ret;
  }
}
