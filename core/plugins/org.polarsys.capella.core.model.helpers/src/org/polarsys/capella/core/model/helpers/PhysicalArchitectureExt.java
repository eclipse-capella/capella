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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

/**
 * PhysicalArchitecture helpers
 */
public class PhysicalArchitectureExt {

  /**
   * Gets all the interfaces used by PCs in Physical Architecture Gets all the interfaces in InterfacePkg (and sub pkgs) of PCs in physical Architecture Gets
   * all the interfaces used by PCs in PCPkg (and sub PC pkgs) of physical architecture Gets all the interfaces in InterfacePkg (and sub pkgs) of PCs in PCPkg
   * (and sub PC pkgs) of physical architecture
   * @param physicalArchitecture_p the physical architecture
   * @param currentPC
   * @param usedFlag
   * @return list of Interfaces
   */
  static public List<CapellaElement> getAllInterfaces(BlockArchitecture blockArchitecture, PhysicalComponent currentPC, boolean usedFlag) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != blockArchitecture) {
      list.addAll(InterfacePkgExt.getAllInterfacesFiltered(blockArchitecture.getOwnedInterfacePkg(), currentPC, usedFlag));
    }
    return list;
  }

  /**
   * This method retrieves all the physical links from the model.
   * @param currentElement_p
   * @return List<PhysicalComponent>
   */
  public static List<PhysicalLink> getAllPhysicalLinks(PhysicalArchitecture architecture) {
    List<PhysicalLink> instList = new ArrayList<PhysicalLink>();
    for (EObject obj : EObjectExt.getAll(architecture, CsPackage.Literals.PHYSICAL_LINK)) {
      instList.add((PhysicalLink) obj);
    }
    return instList;
  }

  /**
   * Gets all the CapabilityRealizationUseCases from the PhysicalArchitecture Packages of System
   * @param systemEngineering the {@link System}
   * @param currentPC the current PhysicalComponent
   * @param isFilterRequired flag to check for filters
   * @return list of CapabilityRealizationUseCase
   */
  public static List<CapellaElement> getCapabilityRealizationUseCasesFromPhysicalLayerFiltered(SystemEngineering systemEngineering,
      PhysicalComponent currentPC, boolean isFilterRequired) {
    List<CapellaElement> list = new ArrayList<CapellaElement>(1);
    PhysicalArchitecturePkg physicalArchPkg = SystemEngineeringExt.getOwnedPhysicalArchitecturePkg(systemEngineering);
    if (null != physicalArchPkg) {
      for (PhysicalArchitecture physicalArch : physicalArchPkg.getOwnedPhysicalArchitectures()) {
        if (null != physicalArch) {
          if (isFilterRequired) {
            list.addAll(ComponentExt.getCapabilityRealizationUseCasesFiltered(currentPC, physicalArch));
          } else {
            list.addAll(CapellaElementExt.getAllCapabilityRealizationInvolvedWith(physicalArch));
          }
        }
      }
    }
    PhysicalArchitecture physicalArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
    if (null != physicalArch) {
      if (isFilterRequired) {
        list.addAll(ComponentExt.getCapabilityRealizationUseCasesFiltered(currentPC, physicalArch));
      } else {
        list.addAll(CapellaElementExt.getAllCapabilityRealizationInvolvedWith(physicalArch));
      }
    }
    return list;
  }
}
