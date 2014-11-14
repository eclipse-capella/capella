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
package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.query.MDEQueries;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;

/**
 * PhysicalArchitecture helpers
 */
public class PhysicalArchitectureExt {

  /**
   * Gets all the interfaces used by PCs in Physical Architecture Gets all the interfaces in InterfacePkg (and sub pkgs) of PCs in physical Architecture Gets
   * all the interfaces used by PCs in PCPkg (and sub PC pkgs) of physical architecture Gets all the interfaces in InterfacePkg (and sub pkgs) of PCs in PCPkg
   * (and sub PC pkgs) of physical architecture
   * @param physicalArchitecture_p the physical architecture
   * @param currentPC_p
   * @param usedFlag_p
   * @return list of Interfaces
   */
  static public List<CapellaElement> getAllInterfaces(BlockArchitecture blockArchitecture_p, PhysicalComponent currentPC_p, boolean usedFlag_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>();
    if (null != blockArchitecture_p) {
      list.addAll(InterfacePkgExt.getAllInterfacesFiltered(blockArchitecture_p.getOwnedInterfacePkg(), currentPC_p, usedFlag_p));
    }
    return list;
  }

  /**
   * This method retrieves all the physical links from the model.
   * @param currentElement_p
   * @return List<PhysicalComponent>
   */
  public static List<PhysicalLink> getAllPhysicalLinks(PhysicalArchitecture architecture_p) {
    List<PhysicalLink> instList = new ArrayList<PhysicalLink>();
    for (EObject obj : EObjectExt.getAll(architecture_p, CsPackage.Literals.PHYSICAL_LINK)) {
      instList.add((PhysicalLink) obj);
    }
    return instList;
  }

  /**
   * This method retrieves all the physical components from the model.
   * @param currentElement_p
   * @return List<PhysicalComponent>
   */
  public static List<PhysicalComponent> getAllPhysicalComponents(PhysicalArchitecture currentElement_p) {
    Set<EObject> pcSet = MDEQueries.getInstance().getAllQueries().getAll(currentElement_p, PhysicalComponent.class);
    List<PhysicalComponent> pcList = new ArrayList<PhysicalComponent>();
    for (EObject obj : pcSet) {
      pcList.add((PhysicalComponent) obj);
    }
    return pcList;
  }

  /**
   * Gets all the interfaces from the PhysicalArchitecture Packages of System
   * @param systemEngineering_p the {@link System}
   * @param currentPC_p the current PhysicalComponent
   * @param usedFlag_p flag to check interface is used/implemented
   * @return list of interfaces
   */
  @Deprecated
  // use BlockArchitectureExt.getAllInterfaces(...) instead
  public static List<CapellaElement> getOwnedInterfacesFromPhysicalLayerFiltered(SystemEngineering systemEngineering_p, PhysicalComponent currentPC_p,
      boolean usedFlag_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>(1);
    PhysicalArchitecturePkg physicalArchitecturePkg = SystemEngineeringExt.getOwnedPhysicalArchitecturePkg(systemEngineering_p);
    if (null != physicalArchitecturePkg) {
      for (PhysicalArchitecture physicalArchitecture : physicalArchitecturePkg.getOwnedPhysicalArchitectures()) {
        if (null != physicalArchitecture) {
          list.addAll(getAllInterfaces(physicalArchitecture, currentPC_p, usedFlag_p));
        }
      }
    }
    PhysicalArchitecture physicalArchitecture = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering_p);
    if (null != physicalArchitecture) {
      list.addAll(getAllInterfaces(physicalArchitecture, currentPC_p, usedFlag_p));
    }
    return list;
  }

  /**
   * Gets all the CapabilityRealizationUseCases from the PhysicalArchitecture Packages of System
   * @param systemEngineering_p the {@link System}
   * @param currentPC_p the current PhysicalComponent
   * @param isFilterRequired_p flag to check for filters
   * @return list of CapabilityRealizationUseCase
   */
  public static List<CapellaElement> getCapabilityRealizationUseCasesFromPhysicalLayerFiltered(SystemEngineering systemEngineering_p,
      PhysicalComponent currentPC_p, boolean isFilterRequired_p) {
    List<CapellaElement> list = new ArrayList<CapellaElement>(1);
    PhysicalArchitecturePkg physicalArchPkg = SystemEngineeringExt.getOwnedPhysicalArchitecturePkg(systemEngineering_p);
    if (null != physicalArchPkg) {
      for (PhysicalArchitecture physicalArch : physicalArchPkg.getOwnedPhysicalArchitectures()) {
        if (null != physicalArch) {
          if (isFilterRequired_p) {
            list.addAll(SystemComponentExt.getCapabilityRealizationUseCasesFiltered(currentPC_p, physicalArch));
          } else {
            list.addAll(CapellaElementExt.getAllCapabilityRealizationInvolvedWith(physicalArch));
          }
        }
      }
    }
    PhysicalArchitecture physicalArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering_p);
    if (null != physicalArch) {
      if (isFilterRequired_p) {
        list.addAll(SystemComponentExt.getCapabilityRealizationUseCasesFiltered(currentPC_p, physicalArch));
      } else {
        list.addAll(CapellaElementExt.getAllCapabilityRealizationInvolvedWith(physicalArch));
      }
    }
    return list;
  }

  /**
   * Gets the parent SystemEngineering of a Physical Architecture
   * @param currentPhysicalArchitecture_p
   * @return the parent {@link SystemEngineering}
   */
  public static SystemEngineering getParentSystemEngineering(PhysicalArchitecture currentPhysicalArchitecture_p) {
    if (null != currentPhysicalArchitecture_p) {
      Object container = currentPhysicalArchitecture_p.eContainer();
      if (container instanceof PhysicalArchitecturePkg) {
        container = ((PhysicalArchitecturePkg) container).eContainer();
      }
      if (container instanceof SystemEngineering) {
        return (SystemEngineering) container;
      }
    }
    return null;
  }

  static public List<AbstractFunction> getAllFunctions(BlockArchitecture arch_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != arch_p) {
      FunctionPkg functionPkg = arch_p.getOwnedFunctionPkg();
      if ((functionPkg != null) && (functionPkg instanceof PhysicalFunctionPkg)) {
        list = getAllFunctionsFromFunctionPkg((PhysicalFunctionPkg) functionPkg);
      }
    }
    return list;
  }

  static public List<AbstractFunction> getAllAbstractFunctions(BlockArchitecture arch_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != arch_p) {
      FunctionPkg functionPkg = arch_p.getOwnedFunctionPkg();
      if ((functionPkg != null) && (functionPkg instanceof PhysicalFunctionPkg)) {
        list = getAllFunctionsFromAbstractFunctionPkg((PhysicalFunctionPkg) functionPkg);
      }

    }

    return list;
  }

  static public List<AbstractFunction> getAllFunctionsFromFunctionPkg(PhysicalFunctionPkg sysFunPkg_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != sysFunPkg_p) {
      EList<PhysicalFunction> ownedFunctions = sysFunPkg_p.getOwnedPhysicalFunctions();
      // owned function of SystemFunctionPkg
      list.addAll(ownedFunctions);
      // owned function of Function
      for (AbstractFunction function : ownedFunctions) {
        list.addAll(getAllFunctionsFromFunction(function));
      }
      // owned function of (subPkg of sysFunPkg_p) SystemFunctionPkg
      for (PhysicalFunctionPkg ownedFunctionPkg : sysFunPkg_p.getOwnedPhysicalFunctionPkgs()) {
        list.addAll(getAllFunctionsFromFunctionPkg(ownedFunctionPkg));
      }
    }
    return list;
  }

  static public List<AbstractFunction> getAllFunctionsFromAbstractFunctionPkg(PhysicalFunctionPkg sysFunPkg_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != sysFunPkg_p) {
      EList<PhysicalFunction> ownedFunctions = sysFunPkg_p.getOwnedPhysicalFunctions();
      // owned function of SystemFunctionPkg
      list.addAll(ownedFunctions);
      // owned function of Function
      for (AbstractFunction function : ownedFunctions) {
        list.addAll(getAllFunctionsFromAbstracrtFunction(function));
      }
      // owned function of (subPkg of sysFunPkg_p) SystemFunctionPkg
      for (PhysicalFunctionPkg ownedFunctionPkg : sysFunPkg_p.getOwnedPhysicalFunctionPkgs()) {
        list.addAll(getAllFunctionsFromAbstractFunctionPkg(ownedFunctionPkg));
      }
    }
    return list;
  }

  static public List<AbstractFunction> getAllFunctionsFromFunction(AbstractFunction fun_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != fun_p) {
      EList<AbstractFunction> ownedSystemFunctions = fun_p.getOwnedFunctions();
      for (AbstractFunction abstractFunction : ownedSystemFunctions) {
        list.add(abstractFunction);
      }
      // owned function of function
      for (AbstractFunction function : ownedSystemFunctions) {
        list.addAll(getAllFunctionsFromFunction(function));
      }
    }
    return list;
  }

  static public List<AbstractFunction> getAllFunctionsFromAbstracrtFunction(AbstractFunction fun_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != fun_p) {
      EList<AbstractFunction> ownedSystemFunctions = fun_p.getOwnedFunctions();
      for (AbstractFunction abstractFunction : ownedSystemFunctions) {
        list.add(abstractFunction);
      }
      // owned function of function
      for (AbstractFunction function : ownedSystemFunctions) {
        list.addAll(getAllFunctionsFromAbstracrtFunction(function));
      }
    }
    return list;
  }
}
