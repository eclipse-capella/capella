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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;

public class BlockArchitectureHelper {
  private static BlockArchitectureHelper instance;

  private BlockArchitectureHelper() {
    // do nothing
  }

  public static BlockArchitectureHelper getInstance() {
    if (instance == null)
      instance = new BlockArchitectureHelper();
    return instance;
  }

  public Object doSwitch(BlockArchitecture element, EStructuralFeature feature) {

    Object ret = null;

    if (feature.equals(CsPackage.Literals.BLOCK_ARCHITECTURE__ALLOCATED_ARCHITECTURES)) {
      ret = getAllocatedArchitectures(element);
    } else if (feature.equals(CsPackage.Literals.BLOCK_ARCHITECTURE__ALLOCATING_ARCHITECTURES)) {
      ret = getAllocatingArchitectures(element);
    } else if (feature.equals(CsPackage.Literals.BLOCK_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS)) {
      ret = getProvisionedArchitectureAllocations(element);
    } else if (feature.equals(CsPackage.Literals.BLOCK_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS)) {
      ret = getProvisioningArchitectureAllocations(element);
    } else if (feature.equals(CsPackage.Literals.BLOCK_ARCHITECTURE__SYSTEM)) {
      ret = getSystem(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = StructureHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<ArchitectureAllocation> getProvisionedArchitectureAllocations(BlockArchitecture element) {
    List<AbstractTrace> traces = element.getOutgoingTraces();
    List<ArchitectureAllocation> ret = new ArrayList<>();

    for (AbstractTrace trace : traces) {

      if (trace instanceof ArchitectureAllocation) {
        ret.add((ArchitectureAllocation) trace);
      }
    }

    return ret;
  }

  protected List<ArchitectureAllocation> getProvisioningArchitectureAllocations(BlockArchitecture element) {
    List<AbstractTrace> traces = element.getIncomingTraces();
    List<ArchitectureAllocation> ret = new ArrayList<>();

    for (AbstractTrace trace : traces) {

      if (trace instanceof ArchitectureAllocation) {
        ret.add((ArchitectureAllocation) trace);
      }
    }

    return ret;
  }

  protected List<BlockArchitecture> getAllocatedArchitectures(BlockArchitecture element) {
    List<ArchitectureAllocation> allocs = element.getProvisionedArchitectureAllocations();
    List<BlockArchitecture> ret = new ArrayList<>();

    for (ArchitectureAllocation architectureAllocation : allocs) {
      BlockArchitecture allocatedArchitecture = architectureAllocation.getAllocatedArchitecture();
      if (allocatedArchitecture != null) {
        ret.add(allocatedArchitecture);
      }
    }
    return ret;
  }

  protected List<BlockArchitecture> getAllocatingArchitectures(BlockArchitecture element) {
    List<ArchitectureAllocation> allocs = element.getProvisioningArchitectureAllocations();
    List<BlockArchitecture> ret = new ArrayList<>();

    for (ArchitectureAllocation architectureAllocation : allocs) {
      BlockArchitecture allocatingArchitecture = architectureAllocation.getAllocatingArchitecture();
      if (allocatingArchitecture != null) {
        ret.add(allocatingArchitecture);
      }
    }
    return ret;
  }

  /**
   * Returns the System Component for the the current Block Architecture. Note that the Operation Analysis Architecture
   * does not have a System Component.
   * 
   * @param architecture
   *          the Block Architecture
   * @return null for Operational Analysis, or the System Component for the remaining Block Architectures.
   */
  protected Component getSystem(BlockArchitecture architecture) {
    if (architecture instanceof SystemAnalysis) {
      SystemComponentPkg ownedSystemComponentPkg = ((SystemAnalysis) architecture).getOwnedSystemComponentPkg();
      if (ownedSystemComponentPkg != null && !ownedSystemComponentPkg.getOwnedSystemComponents().isEmpty()) {
        return ownedSystemComponentPkg.getOwnedSystemComponents().stream().filter(x -> !x.isActor()).findFirst()
            .orElse(null);
      }
    } else if (architecture instanceof LogicalArchitecture) {
      LogicalComponentPkg ownedLogicalComponentPkg = ((LogicalArchitecture) architecture).getOwnedLogicalComponentPkg();
      if (ownedLogicalComponentPkg != null && !ownedLogicalComponentPkg.getOwnedLogicalComponents().isEmpty()) {
        return ownedLogicalComponentPkg.getOwnedLogicalComponents().stream().filter(x -> !x.isActor()).findFirst()
            .orElse(null);
      }
    } else if (architecture instanceof PhysicalArchitecture) {
      PhysicalComponentPkg ownedPhysicalComponentPkg = ((PhysicalArchitecture) architecture)
          .getOwnedPhysicalComponentPkg();
      if (ownedPhysicalComponentPkg != null && !ownedPhysicalComponentPkg.getOwnedPhysicalComponents().isEmpty()) {
        return ownedPhysicalComponentPkg.getOwnedPhysicalComponents().stream().filter(x -> !x.isActor()).findFirst()
            .orElse(null);
      }
    } else if (architecture instanceof EPBSArchitecture) {
      ConfigurationItemPkg ownedConfigurationItemPkg = ((EPBSArchitecture) architecture).getOwnedConfigurationItemPkg();
      if (ownedConfigurationItemPkg != null && !ownedConfigurationItemPkg.getOwnedConfigurationItems().isEmpty()) {
        return ownedConfigurationItemPkg.getOwnedConfigurationItems().stream().filter(x -> !x.isActor()).findFirst()
            .orElse(null);
      }
    }
    return null;
  }

}
