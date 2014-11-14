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
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSArchitecturePkg;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecturePkg;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecturePkg;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellacore.ReuseableStructure;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.AbstractPhysicalComponent;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalContext;
import org.polarsys.capella.core.data.pa.deployment.AbstractPhysicalInstance;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.CollectionExt;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * SystemEngineering helpers
 */
public class SystemEngineeringExt {

  /**
   * Returns the modelling architecture that owns the given element.
   * @param ownedElement an element.
   * @return the modelling architecture that owns the given element.
   */
  public static ModellingArchitecture findArchitecture(final EObject ownedElement) {
    ModellingArchitecture result = null;
    EObject current = ownedElement;
    while ((result == null) && (current != null)) {
      if (CapellacorePackage.eINSTANCE.getModellingArchitecture().isInstance(current)) {
        result = (ModellingArchitecture) current;
      }
      current = current.eContainer();
    }
    return result;
  }

  /**
   * This method retrieves all the abstract logical components from the model.
   * @param currentElement_p
   * @return List<AbstractLogicalComponent>
   */
  public static List<LogicalComponent> getAllAbstractLogicalComponents(CapellaElement currentElement_p) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);

    LogicalArchitecture la = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);

    Set<EObject> lcSet;

    if (null != la) {
      lcSet = EObjectExt.getAll(la, LaPackage.Literals.LOGICAL_COMPONENT);
    } else {
      LogicalArchitecturePkg laPkg = getOwnedLogicalArchitecturePkg(sysEng);
      lcSet = EObjectExt.getAll(laPkg, LaPackage.Literals.LOGICAL_COMPONENT);
    }

    List<LogicalComponent> lcList = new ArrayList<LogicalComponent>();
    for (EObject obj : lcSet) {
      lcList.add((LogicalComponent) obj);
    }
    return lcList;
  }

  /**
   * This method retrieves all the physical components from the model.
   * @param currentElement_p
   * @return List<PhysicalComponent>
   */
  public static List<AbstractPhysicalComponent> getAllAbstractPhysicalComponents(CapellaElement currentElement_p) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);

    PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
    Set<EObject> pcSet = EObjectExt.getAll(physArch, PaPackage.Literals.ABSTRACT_PHYSICAL_COMPONENT);
    List<AbstractPhysicalComponent> pcList = new ArrayList<AbstractPhysicalComponent>();
    for (EObject obj : pcSet) {
      pcList.add((AbstractPhysicalComponent) obj);
    }
    return pcList;
  }

  /**
   * This method retrieves all the actors from the model.
   * @param currentElement_p
   * @return List<Actor>
   */
  public static List<Actor> getAllActors(CapellaElement currentElement_p) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);
    Set<EObject> actorsSet = EObjectExt.getAll(sysEng, CtxPackage.Literals.ACTOR);
    List<Actor> actorsList = new ArrayList<Actor>();
    for (EObject obj : actorsSet) {
      actorsList.add((Actor) obj);
    }
    return actorsList;
  }

  /**
   * Get All the 'DataValue' from all Visible layer(s)
   * @param arch_p the 'BlockArchitecture'
   * @return List of 'DataValue'
   */
  public static List<DataValue> getAllAvailableDataValues(BlockArchitecture arch_p) {
    List<DataValue> availableElements = new ArrayList<DataValue>();
    if (null != arch_p) {
      SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(arch_p);
      OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(sysEng);
      if (null != oa) {
        DataPkg ownedDataPkg = oa.getOwnedDataPkg();
        if (null != ownedDataPkg) {
          availableElements.addAll(DataPkgExt.getAllDataValues(ownedDataPkg));
        }
      } else {
        SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
        DataPkg ownedDataPkg = ca.getOwnedDataPkg();
        if (null != ownedDataPkg) {
          availableElements.addAll(DataPkgExt.getAllDataValues(ownedDataPkg));
        }
      }

      if (((null != oa) && (arch_p instanceof LogicalArchitecture)) || (arch_p instanceof PhysicalArchitecture) || (arch_p instanceof EPBSArchitecture)) {
        SystemAnalysis ctxArch = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
        DataPkg ownedDataPkg = ctxArch.getOwnedDataPkg();
        if (null != ownedDataPkg) {
          availableElements.addAll(DataPkgExt.getAllDataValues(ownedDataPkg));
        }
      }
      if ((arch_p instanceof PhysicalArchitecture) || (arch_p instanceof EPBSArchitecture)) {
        LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);
        DataPkg ownedDataPkg = logArch.getOwnedDataPkg();
        if (null != ownedDataPkg) {
          availableElements.addAll(DataPkgExt.getAllDataValues(ownedDataPkg));
        }
      }
      if ((arch_p instanceof EPBSArchitecture)) {
        PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
        DataPkg ownedDataPkg = physArch.getOwnedDataPkg();
        if (null != ownedDataPkg) {
          availableElements.addAll(DataPkgExt.getAllDataValues(ownedDataPkg));
        }
      }
    }

    return availableElements;
  }

  /**
   * This method retrieves all the capability realizations from the model.
   * @param currentElement_p
   * @return List<CapabilityRealization>
   */
  public static List<CapabilityRealization> getAllCapabilityRealization(CapellaElement currentElement_p) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);
    Set<EObject> capabilityRealizationsSet = EObjectExt.getAll(sysEng, LaPackage.Literals.CAPABILITY_REALIZATION);
    List<CapabilityRealization> capabilityRealizationsList = new ArrayList<CapabilityRealization>();
    for (EObject obj : capabilityRealizationsSet) {
      capabilityRealizationsList.add((CapabilityRealization) obj);
    }
    return capabilityRealizationsList;
  }

  /**
   * This method retrieves all the capability realizations from the model.
   * @param currentElement_p
   * @return List<CapabilityRealization>
   */
  public static List<CapabilityRealization> getAllCapabilityRealizations(CapellaElement currentElement_p) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);
    Set<EObject> capabilityRealizationsSet = EObjectExt.getAll(sysEng, LaPackage.Literals.CAPABILITY_REALIZATION);
    List<CapabilityRealization> capabilityRealizationsList = new ArrayList<CapabilityRealization>();
    for (EObject obj : capabilityRealizationsSet) {
      capabilityRealizationsList.add((CapabilityRealization) obj);
    }
    return capabilityRealizationsList;
  }

  /**
   * This method retrieves all the epbs components from the model.
   * @param currentElement_p
   * @return List<EPBSComponent>
   */
  public static List<ConfigurationItem> getAllConfigurationItems(CapellaElement currentElement_p) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);
    EPBSArchitecture epbsArch = getEPBSArchitecture(sysEng);
    Set<EObject> epbsSet = EObjectExt.getAll(epbsArch, EpbsPackage.Literals.CONFIGURATION_ITEM);
    List<ConfigurationItem> epbsList = new ArrayList<ConfigurationItem>();
    for (EObject obj : epbsSet) {
      epbsList.add((ConfigurationItem) obj);
    }
    return epbsList;
  }

  public static List<InterfacePkg> getAllInterfacePkgs(InterfacePkg pkg) {
    final Set<InterfacePkg> result = new LinkedHashSet<InterfacePkg>();
    if (pkg.getOwnedInterfacePkgs() != null) {
      result.addAll(pkg.getOwnedInterfacePkgs());
      for (InterfacePkg itf : pkg.getOwnedInterfacePkgs()) {
        result.addAll(getAllInterfacePkgs(itf));
      }
    }

    return new ArrayList<InterfacePkg>(result);
  }

  public static List<Interface> getAllInterfaces(ModellingArchitecture architecture) {
    final Set<Interface> result = new LinkedHashSet<Interface>();
    final Set<InterfacePkg> itfPkgs = new LinkedHashSet<InterfacePkg>();
    
    if(null == architecture) return (new ArrayList<Interface>());
    
    if ((architecture instanceof SystemAnalysis) && (((SystemAnalysis) architecture).getOwnedInterfacePkg() != null)) {
      itfPkgs.add(((SystemAnalysis) architecture).getOwnedInterfacePkg());
      itfPkgs.addAll(getAllInterfacePkgs(((SystemAnalysis) architecture).getOwnedInterfacePkg()));
      // (TODO 1) : for all InterfacePkg inside Actor and System
      // GetAllActorsPkg
      // GetAllIntefaces
      // GetSystem
      // GetAllInterface
      // Alternative to above (TODO 1) option
      TreeIterator<EObject> allContents = ((SystemAnalysis) architecture).eAllContents();
      while (allContents.hasNext()) {
        EObject object = allContents.next();
        if (object instanceof Interface) {
          result.add((Interface) object);
        }
      }
    }

    if ((architecture instanceof LogicalArchitecture) && (((LogicalArchitecture) architecture).getOwnedInterfacePkg() != null)) {
      itfPkgs.add(((LogicalArchitecture) architecture).getOwnedInterfacePkg());
      itfPkgs.addAll(getAllInterfacePkgs(((LogicalArchitecture) architecture).getOwnedInterfacePkg()));
      // (TODO 2) : for InterfacePkg inside all LCs
      // getAllLCs
      // getAllIntPkgs
      // getAllInterfaces
      // Alternative to below (TODO 2) option
      TreeIterator<EObject> allContents = ((LogicalArchitecture) architecture).eAllContents();
      while (allContents.hasNext()) {
        EObject object = allContents.next();
        if (object instanceof Interface) {
          result.add((Interface) object);
        }
      }
    }

    if ((architecture instanceof PhysicalArchitecture) && (((PhysicalArchitecture) architecture).getOwnedInterfacePkg() != null)) {
      itfPkgs.add(((PhysicalArchitecture) architecture).getOwnedInterfacePkg());
      itfPkgs.addAll(getAllInterfacePkgs(((PhysicalArchitecture) architecture).getOwnedInterfacePkg()));

      // Alternative option
      TreeIterator<EObject> allContents = ((PhysicalArchitecture) architecture).eAllContents();
      while (allContents.hasNext()) {
        EObject object = allContents.next();
        if (object instanceof Interface) {
          result.add((Interface) object);
        }
      }
    }

    return new ArrayList<Interface>(result);
  }

  /**
   * This method retrieves all the Logical Architecture packages from the model.
   * @param currentElement_p
   * @return LogicalArchitecture
   */
  public static List<LogicalArchitecture> getAllLogicalArchitecture(CapellaElement currentElement_p) {
    List<LogicalArchitecture> archSet = new ArrayList<LogicalArchitecture>();
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);
    if (sysEng != null) {

      LogicalArchitecture arch = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);
      if (arch != null) {
        archSet.add(arch);
      }

      LogicalArchitecturePkg archPkg = getOwnedLogicalArchitecturePkg(sysEng);
      if (archPkg != null) {
        for (LogicalArchitecture la : archPkg.getOwnedLogicalArchitectures()) {
          archSet.add(la);
        }
      }
    }
    return archSet;
  }

  /**
   * This method retrieves all the logical components from the model.
   * @param currentElement_p
   * @return List<LogicalComponent>
   */
  public static List<LogicalComponent> getAllLogicalComponents(CapellaElement currentElement_p) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);

    LogicalArchitecture la = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);

    Set<EObject> lcSet;

    if (null != la) {
      lcSet = EObjectExt.getAll(la, LaPackage.Literals.LOGICAL_COMPONENT);
    } else {
      LogicalArchitecturePkg laPkg = getOwnedLogicalArchitecturePkg(sysEng);
      lcSet = EObjectExt.getAll(laPkg, LaPackage.Literals.LOGICAL_COMPONENT);
    }
    List<LogicalComponent> lcList = new ArrayList<LogicalComponent>();
    for (EObject obj : lcSet) {
      lcList.add((LogicalComponent) obj);
    }
    return lcList;
  }

  /**
   * Gets all the Physical architectures from the SystemEngineering
   * @param systemEngineering_p the parent SystemEngineering
   * @return List of PhysicalArchitectures
   */
  static public List<PhysicalArchitecture> getAllPhysicalArchitectures(SystemEngineering systemEngineering_p) {
    List<PhysicalArchitecture> list = new ArrayList<PhysicalArchitecture>(1);
    if (null != systemEngineering_p) {
      if (null != SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering_p)) {
        list.add(SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering_p));
      }

      PhysicalArchitecturePkg pkg = getOwnedPhysicalArchitecturePkg(systemEngineering_p);
      if (null != pkg) {
        for (PhysicalArchitecture arch : pkg.getOwnedPhysicalArchitectures()) {
          if (null == arch) {
            continue;
          }
          list.add(arch);
        }
      }
    }

    return list;
  }

  /**
   * This method retrieves all the physical components from the model.
   * @param currentElement_p
   * @return List<PhysicalComponent>
   */
  public static List<PhysicalComponent> getAllPhysicalComponents(CapellaElement currentElement_p) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);

    PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
    Set<EObject> pcSet = EObjectExt.getAll(physArch, PaPackage.Literals.PHYSICAL_COMPONENT);
    List<PhysicalComponent> pcList = new ArrayList<PhysicalComponent>();
    for (EObject obj : pcSet) {
      pcList.add((PhysicalComponent) obj);
    }
    return pcList;
  }

  /**
   * This method retrieves all the physical components from the model.
   * @param currentElement_p
   * @return List<PhysicalComponent>
   */
  public static List<PhysicalActor> getAllPhysicalActors(CapellaElement currentElement_p) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);

    PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
    Set<EObject> pcSet = EObjectExt.getAll(physArch, PaPackage.Literals.PHYSICAL_ACTOR);
    List<PhysicalActor> pcList = new ArrayList<PhysicalActor>();
    for (EObject obj : pcSet) {
      pcList.add((PhysicalActor) obj);
    }
    return pcList;
  }
  /**
   * This method retrieves all the physical components from the model by nature.
   * @param currentElement_p
   * @return List<PhysicalComponent>
   */
  public static List<PhysicalComponent> getAllPhysicalComponentsByNature(CapellaElement currentElement_p, PhysicalComponentNature nature_p) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);

    PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
    Set<EObject> pcSet = EObjectExt.getAll(physArch, PaPackage.Literals.PHYSICAL_COMPONENT);
    List<PhysicalComponent> pcList = new ArrayList<PhysicalComponent>();
    for (EObject obj : pcSet) {
      PhysicalComponent comp = (PhysicalComponent) obj;
      if (nature_p != null) {
        if (comp.getNature() == nature_p) {
          pcList.add(comp);
        }
      } else {
        pcList.add(comp);
      }
    }
    return pcList;
  }

  /**
   * This method retrieves all the physical objects from the model.
   * @param currentElement_p
   * @return List<PhysicalObject>
   */
  public static List<AbstractPhysicalInstance> getAllPhysicalObject(CapellaElement currentElement_p) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);
    PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
    Set<EObject> poSet = EObjectExt.getAll(physArch, DeploymentPackage.Literals.ABSTRACT_PHYSICAL_INSTANCE);
    List<AbstractPhysicalInstance> poList = new ArrayList<AbstractPhysicalInstance>();
    for (EObject obj : poSet) {
      poList.add((AbstractPhysicalInstance) obj);
    }
    return poList;
  }

  /**
   * This method retrieves the EPBS Architecture package from the model.
   * @param currentElement_p
   * @return EPBSArchitecture
   */
  public static EPBSArchitecture getEPBSArchitecture(CapellaElement currentElement_p) {
    EPBSArchitecture arch = null;
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);
    if (sysEng != null) {
      arch = getEPBSArchitecture(sysEng);
    }
    return arch;
  }

  public static EPBSArchitecture getEPBSArchitecture(SystemEngineering currentElement_p) {
    if (currentElement_p != null) {
      for (ModellingArchitecture block : currentElement_p.getOwnedArchitectures()) {
        if (block instanceof EPBSArchitecture) {
          return (EPBSArchitecture) block;
        }
      }
    }
    return null;
  }

  public static EPBSArchitecturePkg getEPBSArchitecturePkg(SystemEngineering currentElement_p) {
    if (currentElement_p != null) {
      for (ModellingArchitecturePkg block : currentElement_p.getOwnedArchitecturePkgs()) {
        if (block instanceof EPBSArchitecturePkg) {
          return (EPBSArchitecturePkg) block;
        }
      }
    }
    return null;
  }

  public static List<Interface> getInterfaces(LogicalArchitecture architecture) {
    final Set<Interface> result = new LinkedHashSet<Interface>();
    for (final SystemAnalysis ctxArchitecture : CollectionExt.filter(((SystemEngineering) architecture.eContainer()).getOwnedArchitectures(),
        SystemAnalysis.class)) {
      result.addAll(getInterfaces(ctxArchitecture));
    }
    result.addAll((architecture != null) && (architecture.getOwnedInterfacePkg() != null) ? getAllInterfaces(architecture) : Collections
        .<Interface> emptyList());
    return new ArrayList<Interface>(result);
  }

  public static List<Interface> getInterfaces(ModellingArchitecture architecture) {
    if (architecture instanceof SystemAnalysis) {
      return getInterfaces((SystemAnalysis) architecture);
    } else if (architecture instanceof LogicalArchitecture) {
      return getInterfaces((LogicalArchitecture) architecture);
    } else if (architecture instanceof PhysicalArchitecture) {
      return getInterfaces((PhysicalArchitecture) architecture);
    }
    return Collections.<Interface> emptyList();
  }

  public static List<Interface> getInterfaces(PhysicalArchitecture architecture) {
    final Set<Interface> result = new LinkedHashSet<Interface>();
    for (final LogicalArchitecture logArchitecture : CollectionExt.filter(((SystemEngineering) architecture.eContainer()).getOwnedArchitectures(),
        LogicalArchitecture.class)) {
      result.addAll(getInterfaces(logArchitecture));
    }
    result.addAll((architecture != null) && (architecture.getOwnedInterfacePkg() != null) ? getAllInterfaces(architecture) : Collections
        .<Interface> emptyList());
    return new ArrayList<Interface>(result);
  }


  public static List<Interface> getInterfaces(SystemAnalysis architecture) {
    return (architecture != null) && (architecture.getOwnedInterfacePkg() != null) ? getAllInterfaces(architecture) : Collections.<Interface> emptyList();
  }

  /**
   * Gets all the logical architectures from the system, filtering out the allocated LAs of the current Physical Architecture
   * @param systemEngineering_p the parent System of the PA
   * @param physicalArchitecture_p the PhysicalArchitecture
   * @return List of LogicalArchitectures
   */
  static public List<LogicalArchitecture> getLogicalArchitecturesFiltered(SystemEngineering systemEngineering_p, PhysicalArchitecture physicalArchitecture_p) {
    List<LogicalArchitecture> list = new ArrayList<LogicalArchitecture>(1);
    if ((null != systemEngineering_p) && (null != physicalArchitecture_p)) {
      List<BlockArchitecture> allocated = physicalArchitecture_p.getAllocatedArchitectures();

      LogicalArchitecture la = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering_p);
      if ((la != null) && !allocated.contains(la)) {
        list.add(la);
      }

      LogicalArchitecturePkg laPkg = getOwnedLogicalArchitecturePkg(systemEngineering_p);
      if (null != laPkg) {
        for (LogicalArchitecture arch : laPkg.getOwnedLogicalArchitectures()) {
          if ((null != arch) && !allocated.contains(arch)) {
            list.add(arch);
          }
        }
      }
    }

    return list;
  }

  public static FunctionPkg getOwnedFunctionalAnalysis(SystemEngineering currentElement_p) {
    if (currentElement_p != null) {
      for (ModellingArchitecture block : currentElement_p.getOwnedArchitectures()) {
        if (block instanceof FunctionPkg) {
          return (FunctionPkg) block;
        }
      }
    }
    return null;
  }

  /**
   * This method retrieves all the Logical Architecture packages from the model.
   * @param currentElement_p
   * @return LogicalArchitecture
   */
  public static LogicalArchitecture getOwnedLogicalArchitecture(CapellaElement currentElement_p) {
    LogicalArchitecture arch = null;
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);
    if (sysEng != null) {
      arch = getOwnedLogicalArchitecture(sysEng);
    }
    return arch;
  }

  /**
   * This method retrieves the Logical Architecture packages from the model.
   * @param currentElement_p
   * @return LogicalArchitecture
   */
  public static LogicalArchitecture getOwnedLogicalArchitecture(SystemEngineering currentElement_p) {
    if (currentElement_p != null) {
      for (ModellingArchitecture block : currentElement_p.getOwnedArchitectures()) {
        if (block instanceof LogicalArchitecture) {
          return (LogicalArchitecture) block;
        }
      }
    }
    return null;
  }

  public static LogicalArchitecturePkg getOwnedLogicalArchitecturePkg(SystemEngineering currentElement_p) {
    if (currentElement_p != null) {
      for (ModellingArchitecturePkg block : currentElement_p.getOwnedArchitecturePkgs()) {
        if (block instanceof LogicalArchitecturePkg) {
          return (LogicalArchitecturePkg) block;
        }
      }
    }
    return null;
  }

  /**
   * This method retrieves the Operational Analysis packages from the model.
   * @param currentElement_p
   * @return OperationalAnalysis
   */
  public static OperationalAnalysis getOwnedOperationalAnalysis(SystemEngineering currentElement_p) {
    if (currentElement_p != null) {
      for (ModellingArchitecture block : currentElement_p.getOwnedArchitectures()) {
        if (block instanceof OperationalAnalysis) {
          return (OperationalAnalysis) block;
        }
      }
    }
    return null;
  }

  /**
   * This method retrieves the Physical Architecture packages from the model.
   * @param currentElement_p
   * @return PhysicalArchitecture
   */
  public static PhysicalArchitecture getOwnedPhysicalArchitecture(SystemEngineering currentElement_p) {
    if (currentElement_p != null) {
      for (ModellingArchitecture block : currentElement_p.getOwnedArchitectures()) {
        if (block instanceof PhysicalArchitecture) {
          return (PhysicalArchitecture) block;
        }
      }
    }
    return null;
  }

  public static PhysicalArchitecturePkg getOwnedPhysicalArchitecturePkg(SystemEngineering currentElement_p) {
    if (currentElement_p != null) {
      for (ModellingArchitecturePkg block : currentElement_p.getOwnedArchitecturePkgs()) {
        if (block instanceof PhysicalArchitecturePkg) {
          return (PhysicalArchitecturePkg) block;
        }
      }
    }
    return null;
  }

  /**
   * This method retrieves the System Analysis packages from the model.
   * @param currentElement_p
   * @return SystemAnalysis
   */
  public static SystemAnalysis getOwnedSystemAnalysis(SystemEngineering currentElement_p) {
    if (currentElement_p != null) {
      for (ModellingArchitecture block : currentElement_p.getOwnedArchitectures()) {
        if (block instanceof SystemAnalysis) {
          return (SystemAnalysis) block;
        }
      }
    }
    return null;
  }

  /**
   * This method retrieves the Physical Architecture package from the model.
   * @param currentElement_p
   * @return PhysicalArchitecture
   */
  public static PhysicalArchitecture getPhysicalArchitecture(CapellaElement currentElement_p) {
    PhysicalArchitecture arch = null;
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);
    if (sysEng != null) {
      arch = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
    }
    return arch;
  }

  /**
   * @param modelElement_p : any 'ModelElement'
   * @return : 'BlockArchitecture', value can also be null
   */
  public static BlockArchitecture getRootBlockArchitecture(ModelElement modelElement_p) {
	  return BlockArchitectureExt.getRootBlockArchitecture(modelElement_p);
  }

  /**
   * Return the 'RootLogicalComponent' for 'LogicalArchitecture' Layer given in parameter
   */
  public static LogicalComponent getRootLogicalComponent(LogicalArchitecture LA_p) {
    System system = getSystem(LA_p);

    for (LogicalComponent rootComponent : getRootLogicalComponentsForCurrentSystem(system)) {
      if (EcoreUtil2.isContainedBy(rootComponent, LA_p)) {
        return rootComponent;
      }
    }

    return null;
  }
  
  
  /**
   * @param systemEn SystemEngineering
   * @return all DataPkgs which are in SystemEngineering
   */
  public static List<DataPkg> getAllDataPkgs(SystemEngineering systemEn) {
    List<DataPkg> allPackages = new ArrayList<DataPkg>();
    EList<ModellingArchitecture> allArchitectures = systemEn.getOwnedArchitectures();
    for (ModellingArchitecture anArchitecture : allArchitectures) {
      for (EObject aDataPkg : EObjectExt.getAll(anArchitecture, InformationPackage.Literals.DATA_PKG)) {
        allPackages.add((DataPkg) aDataPkg);
      }
    }
    return allPackages;
  }

  /**
   * Return All 'RootLogicalComponent' existing in all LogicalArchitecture layer(s) defined for the 'System' given in parameter
   */
  public static List<LogicalComponent> getRootLogicalComponentsForCurrentSystem(System system_p) {
    List<LogicalComponent> rootComponentList = new ArrayList<LogicalComponent>();
    for (ComponentAllocation componentAllocation : system_p.getProvisioningComponentAllocations()) {
      Component cpnt = componentAllocation.getAllocatingComponent();
      if (cpnt instanceof LogicalComponent) {
        rootComponentList.add((LogicalComponent) cpnt);
      }
    }
    return rootComponentList;
  }

  /**
   * Return the 'RootPhysicalComponent' for 'PhysicalArchitecture' Layer given in parameter
   */
  public static PhysicalComponent getRootPhysicalComponent(PhysicalArchitecture PA_p) {
    PhysicalContext context = PA_p.getOwnedPhysicalContext();
    if (context != null) {
      for (Partition partition : context.getOwnedPartitions()) {
        AbstractType type = partition.getAbstractType();
        if (type instanceof PhysicalComponent) {
          return (PhysicalComponent) type;
        }
      }
    }
    return null;
  }

  public static SharedPkg getSharedPkg(CapellaElement modelElement_p) {
    return (SharedPkg) getSharedPkgRecursively(modelElement_p);
  }

  private static ModelElement getSharedPkgRecursively(ModelElement modelElement_p) {
    ModelElement result;
    if (null == modelElement_p) {
      result = null;
    } else if (modelElement_p instanceof SharedPkg) {
      result = modelElement_p;
    } else {
      ModelElement container = (ModelElement) modelElement_p.eContainer();
      result = getSharedPkgRecursively(container);
    }
    return result;
  }

  /**
   * This method retrieves the Shared Packages used by the current SystemEngineering.
   * @param currentElement_p
   * @return List<SharedPkg>
   */
  public static List<SharedPkg> getSharedPkgs(CapellaElement currentElement_p) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);

    return getSharedPkgs(sysEng);
  }

  /**
   * This method retrieves the Shared Packages used by the current SystemEngineering.
   * @param sysEng_p
   * @return List<SharedPkg>
   */
  public static List<SharedPkg> getSharedPkgs(SystemEngineering sysEng_p) {
    List<SharedPkg> sharedPkgs = new ArrayList<SharedPkg>();
    for (ReuseLink lnk : sysEng_p.getReuseLinks()) {
      ReuseableStructure struct = lnk.getReused();
      if (struct instanceof SharedPkg) {
        sharedPkgs.add((SharedPkg) struct);
      }
    }
    return sharedPkgs;
  }

  /**
   * This method retrieves the System from the model.
   * @param currentElement_p
   * @return System
   */
  public static System getSystem(CapellaElement currentElement_p) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);
    if (sysEng != null) {
      return getSystem(sysEng);
    }
    return null;
  }

  /**
   * This method retrieves the System from the model.
   * @param currentElement_p
   * @return System
   */
  public static System getSystem(SystemEngineering currentElement_p) {
    if (currentElement_p != null) {
      SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(currentElement_p);
      if (ca != null) {
        return ca.getOwnedSystem();
      }
    }
    return null;
  }

  public static SystemEngineering getSystemEngineering(CapellaElement currentElement_p) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement_p);

    return sysEng;
  }

  public static void setEPBSArchitecturePkg(SystemEngineering currentElement_p, EPBSArchitecturePkg epbsArchitecturePkg) {
    currentElement_p.getOwnedArchitecturePkgs().add(epbsArchitecturePkg);

  }

}
