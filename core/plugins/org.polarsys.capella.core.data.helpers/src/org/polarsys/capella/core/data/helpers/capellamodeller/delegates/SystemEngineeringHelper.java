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

  public Object doSwitch(SystemEngineering element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_OPERATIONAL_ANALYSIS)) {
      ret = getContainedOperationalAnalysis(element_p);
    } else if (feature_p.equals(CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_SYSTEM_ANALYSIS)) {
      ret = getContainedSystemAnalysis(element_p);
    } else if (feature_p.equals(CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_LOGICAL_ARCHITECTURES)) {
      ret = getContainedLogicalArchitectures(element_p);
    } else if (feature_p.equals(CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_PHYSICAL_ARCHITECTURES)) {
      ret = getContainedPhysicalArchitectures(element_p);
    } else if (feature_p.equals(CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_EPBS_ARCHITECTURES)) {
      ret = getContainedEPBSArchitectures(element_p);
    } else if (feature_p.equals(CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_SHARED_PKGS)) {
      ret = getContainedSharedPkgs(element_p);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = StructureHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<OperationalAnalysis> getContainedOperationalAnalysis(SystemEngineering element_p) {
    List<OperationalAnalysis> res = new ArrayList<OperationalAnalysis>();
    for (ModellingArchitecture architecture : element_p.getOwnedArchitectures()) {
      if (architecture instanceof OperationalAnalysis) {
        res.add((OperationalAnalysis) architecture);
      }
    }
    return res;
  }

  protected List<SystemAnalysis> getContainedSystemAnalysis(SystemEngineering element_p) {
    List<SystemAnalysis> res = new ArrayList<SystemAnalysis>();
    for (ModellingArchitecture architecture : element_p.getOwnedArchitectures()) {
      if (architecture instanceof SystemAnalysis) {
        res.add((SystemAnalysis) architecture);
      }
    }
    return res;
  }

  protected List<LogicalArchitecture> getContainedLogicalArchitectures(SystemEngineering element_p) {
    List<LogicalArchitecture> res = new ArrayList<LogicalArchitecture>();
    for (ModellingArchitecture architecture : element_p.getOwnedArchitectures()) {
      if (architecture instanceof LogicalArchitecture) {
        res.add((LogicalArchitecture) architecture);
      }
    }
    return res;
  }

  protected List<PhysicalArchitecture> getContainedPhysicalArchitectures(SystemEngineering element_p) {
    List<PhysicalArchitecture> res = new ArrayList<PhysicalArchitecture>();
    for (ModellingArchitecture architecture : element_p.getOwnedArchitectures()) {
      if (architecture instanceof PhysicalArchitecture) {
        res.add((PhysicalArchitecture) architecture);
      }
    }
    return res;
  }

  protected List<EPBSArchitecture> getContainedEPBSArchitectures(SystemEngineering element_p) {
    List<EPBSArchitecture> res = new ArrayList<EPBSArchitecture>();
    for (ModellingArchitecture architecture : element_p.getOwnedArchitectures()) {
      if (architecture instanceof EPBSArchitecture) {
        res.add((EPBSArchitecture) architecture);
      }
    }
    return res;
  }

  protected List<SharedPkg> getContainedSharedPkgs(SystemEngineering element_p) {
    List<SharedPkg> res = new ArrayList<SharedPkg>();
    for (ModellingArchitecture architecture : element_p.getOwnedArchitectures()) {
      if (architecture instanceof SharedPkg) {
        res.add((SharedPkg) architecture);
      }
    }
    return res;
  }
}
