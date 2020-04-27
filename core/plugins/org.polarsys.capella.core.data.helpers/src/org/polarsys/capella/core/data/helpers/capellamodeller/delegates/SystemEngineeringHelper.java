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

package org.polarsys.capella.core.data.helpers.capellamodeller.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;

public class SystemEngineeringHelper {
  private static SystemEngineeringHelper instance;

  private SystemEngineeringHelper() {
    // do nothing
  }

  public static SystemEngineeringHelper getInstance() {
    if (instance == null)
      instance = new SystemEngineeringHelper();
    return instance;
  }

  public Object doSwitch(SystemEngineering element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_OPERATIONAL_ANALYSIS)) {
      ret = getContainedOperationalAnalysis(element);
    } else if (feature.equals(CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_SYSTEM_ANALYSIS)) {
      ret = getContainedSystemAnalysis(element);
    } else if (feature.equals(CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_LOGICAL_ARCHITECTURES)) {
      ret = getContainedLogicalArchitectures(element);
    } else if (feature.equals(CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_PHYSICAL_ARCHITECTURES)) {
      ret = getContainedPhysicalArchitectures(element);
    } else if (feature.equals(CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_EPBS_ARCHITECTURES)) {
      ret = getContainedEPBSArchitectures(element);
    } else if (feature.equals(CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_SHARED_PKGS)) {
      ret = getContainedSharedPkgs(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = StructureHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<OperationalAnalysis> getContainedOperationalAnalysis(SystemEngineering element) {
    List<OperationalAnalysis> res = new ArrayList<>();
    for (ModellingArchitecture architecture : element.getOwnedArchitectures()) {
      if (architecture instanceof OperationalAnalysis) {
        res.add((OperationalAnalysis) architecture);
      }
    }
    return res;
  }

  protected List<SystemAnalysis> getContainedSystemAnalysis(SystemEngineering element) {
    List<SystemAnalysis> res = new ArrayList<>();
    for (ModellingArchitecture architecture : element.getOwnedArchitectures()) {
      if (architecture instanceof SystemAnalysis) {
        res.add((SystemAnalysis) architecture);
      }
    }
    return res;
  }

  protected List<LogicalArchitecture> getContainedLogicalArchitectures(SystemEngineering element) {
    List<LogicalArchitecture> res = new ArrayList<>();
    for (ModellingArchitecture architecture : element.getOwnedArchitectures()) {
      if (architecture instanceof LogicalArchitecture) {
        res.add((LogicalArchitecture) architecture);
      }
    }
    return res;
  }

  protected List<PhysicalArchitecture> getContainedPhysicalArchitectures(SystemEngineering element) {
    List<PhysicalArchitecture> res = new ArrayList<>();
    for (ModellingArchitecture architecture : element.getOwnedArchitectures()) {
      if (architecture instanceof PhysicalArchitecture) {
        res.add((PhysicalArchitecture) architecture);
      }
    }
    return res;
  }

  protected List<EPBSArchitecture> getContainedEPBSArchitectures(SystemEngineering element) {
    List<EPBSArchitecture> res = new ArrayList<>();
    for (ModellingArchitecture architecture : element.getOwnedArchitectures()) {
      if (architecture instanceof EPBSArchitecture) {
        res.add((EPBSArchitecture) architecture);
      }
    }
    return res;
  }

  protected List<SharedPkg> getContainedSharedPkgs(SystemEngineering element) {
    List<SharedPkg> res = new ArrayList<>();
    for (ModellingArchitecture architecture : element.getOwnedArchitectures()) {
      if (architecture instanceof SharedPkg) {
        res.add((SharedPkg) architecture);
      }
    }
    return res;
  }
}
