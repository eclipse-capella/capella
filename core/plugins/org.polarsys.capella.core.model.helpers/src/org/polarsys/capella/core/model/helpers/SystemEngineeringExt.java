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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecturePkg;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellacore.ReuseableStructure;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSArchitecturePkg;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecturePkg;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.deployment.AbstractPhysicalInstance;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.CollectionExt;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

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
   * @param currentElement
   * @return List<AbstractLogicalComponent>
   */
  public static List<LogicalComponent> getAllAbstractLogicalComponents(CapellaElement currentElement) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement);

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
   * Get All the 'DataValue' from all Visible layer(s)
   * @param arch the 'BlockArchitecture'
   * @return List of 'DataValue'
   */
  public static List<DataValue> getAllAvailableDataValues(BlockArchitecture arch) {
    List<DataValue> availableElements = new ArrayList<DataValue>();
    if (null != arch) {
      SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(arch);
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

      if (((null != oa) && (arch instanceof LogicalArchitecture)) || (arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
        SystemAnalysis ctxArch = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
        DataPkg ownedDataPkg = ctxArch.getOwnedDataPkg();
        if (null != ownedDataPkg) {
          availableElements.addAll(DataPkgExt.getAllDataValues(ownedDataPkg));
        }
      }
      if ((arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
        LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);
        DataPkg ownedDataPkg = logArch.getOwnedDataPkg();
        if (null != ownedDataPkg) {
          availableElements.addAll(DataPkgExt.getAllDataValues(ownedDataPkg));
        }
      }
      if ((arch instanceof EPBSArchitecture)) {
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
   * @param currentElement
   * @return List<CapabilityRealization>
   */
  public static List<CapabilityRealization> getAllCapabilityRealization(CapellaElement currentElement) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement);
    Set<EObject> capabilityRealizationsSet = EObjectExt.getAll(sysEng, LaPackage.Literals.CAPABILITY_REALIZATION);
    List<CapabilityRealization> capabilityRealizationsList = new ArrayList<CapabilityRealization>();
    for (EObject obj : capabilityRealizationsSet) {
      capabilityRealizationsList.add((CapabilityRealization) obj);
    }
    return capabilityRealizationsList;
  }

  /**
   * This method retrieves all the capability realizations from the model.
   * @param currentElement
   * @return List<CapabilityRealization>
   */
  public static List<CapabilityRealization> getAllCapabilityRealizations(CapellaElement currentElement) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement);
    Set<EObject> capabilityRealizationsSet = EObjectExt.getAll(sysEng, LaPackage.Literals.CAPABILITY_REALIZATION);
    List<CapabilityRealization> capabilityRealizationsList = new ArrayList<CapabilityRealization>();
    for (EObject obj : capabilityRealizationsSet) {
      capabilityRealizationsList.add((CapabilityRealization) obj);
    }
    return capabilityRealizationsList;
  }

  
  /**
   * This method retrieves all the epbs components from the model.
   * @param currentElement
   * @return List<EPBSComponent>
   */
  public static List<ConfigurationItem> getAllConfigurationItems(CapellaElement currentElement) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement);
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
   * @param currentElement
   * @return LogicalArchitecture
   */
  public static List<LogicalArchitecture> getAllLogicalArchitecture(CapellaElement currentElement) {
    List<LogicalArchitecture> archSet = new ArrayList<LogicalArchitecture>();
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement);
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
   * @param currentElement
   * @return List<LogicalComponent>
   */
  public static List<LogicalComponent> getAllLogicalComponents(CapellaElement currentElement) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement);

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
   * @param systemEngineering the parent SystemEngineering
   * @return List of PhysicalArchitectures
   */
  static public List<PhysicalArchitecture> getAllPhysicalArchitectures(SystemEngineering systemEngineering) {
    List<PhysicalArchitecture> list = new ArrayList<PhysicalArchitecture>(1);
    if (null != systemEngineering) {
      if (null != SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering)) {
        list.add(SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering));
      }

      PhysicalArchitecturePkg pkg = getOwnedPhysicalArchitecturePkg(systemEngineering);
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
   * @param currentElement
   * @return List<PhysicalComponent>
   */
  public static List<PhysicalComponent> getAllPhysicalComponents(CapellaElement currentElement) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement);

    PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
    Set<EObject> pcSet = EObjectExt.getAll(physArch, PaPackage.Literals.PHYSICAL_COMPONENT);
    List<PhysicalComponent> pcList = new ArrayList<PhysicalComponent>();
    for (EObject obj : pcSet) {
      pcList.add((PhysicalComponent) obj);
    }
    return pcList;
  }

  /**
   * This method retrieves all the physical components from the model by nature.
   * @param currentElement
   * @return List<PhysicalComponent>
   */
  public static List<PhysicalComponent> getAllPhysicalComponentsByNature(CapellaElement currentElement, PhysicalComponentNature nature) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement);

    PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
    Set<EObject> pcSet = EObjectExt.getAll(physArch, PaPackage.Literals.PHYSICAL_COMPONENT);
    List<PhysicalComponent> pcList = new ArrayList<PhysicalComponent>();
    for (EObject obj : pcSet) {
      PhysicalComponent comp = (PhysicalComponent) obj;
      if (nature != null) {
        if (comp.getNature() == nature) {
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
   * @param currentElement
   * @return List<PhysicalObject>
   */
  public static List<AbstractPhysicalInstance> getAllPhysicalObject(CapellaElement currentElement) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement);
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
   * @param currentElement
   * @return EPBSArchitecture
   */
  public static EPBSArchitecture getEPBSArchitecture(CapellaElement currentElement) {
    EPBSArchitecture arch = null;
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement);
    if (sysEng != null) {
      arch = getEPBSArchitecture(sysEng);
    }
    return arch;
  }

  public static EPBSArchitecture getEPBSArchitecture(SystemEngineering currentElement) {
    if (currentElement != null) {
      for (ModellingArchitecture block : currentElement.getOwnedArchitectures()) {
        if (block instanceof EPBSArchitecture) {
          return (EPBSArchitecture) block;
        }
      }
    }
    return null;
  }

  public static PhysicalArchitecture getPhysicalArchitecture(SystemEngineering currentElement) {
    if (currentElement != null) {
      for (ModellingArchitecture block : currentElement.getOwnedArchitectures()) {
        if (block instanceof PhysicalArchitecture) {
          return (PhysicalArchitecture) block;
        }
      }
    }
    return null;
  }

  public static LogicalArchitecture getLogicalArchitecture(SystemEngineering currentElement) {
    if (currentElement != null) {
      for (ModellingArchitecture block : currentElement.getOwnedArchitectures()) {
        if (block instanceof LogicalArchitecture) {
          return (LogicalArchitecture) block;
        }
      }
    }
    return null;
  }

  public static SystemAnalysis getSystemAnalysis(SystemEngineering currentElement) {
    if (currentElement != null) {
      for (ModellingArchitecture block : currentElement.getOwnedArchitectures()) {
        if (block instanceof SystemAnalysis) {
          return (SystemAnalysis) block;
        }
      }
    }
    return null;
  }

  public static OperationalAnalysis getOperationalAnalysis(SystemEngineering currentElement) {
    if (currentElement != null) {
      for (ModellingArchitecture block : currentElement.getOwnedArchitectures()) {
        if (block instanceof OperationalAnalysis) {
          return (OperationalAnalysis) block;
        }
      }
    }
    return null;
  }

  public static EPBSArchitecturePkg getEPBSArchitecturePkg(SystemEngineering currentElement) {
    if (currentElement != null) {
      for (ModellingArchitecturePkg block : currentElement.getOwnedArchitecturePkgs()) {
        if (block instanceof EPBSArchitecturePkg) {
          return (EPBSArchitecturePkg) block;
        }
      }
    }
    return null;
  }

  public static List<Interface> getInterfaces(LogicalArchitecture architecture) {
    final Set<Interface> result = new LinkedHashSet<Interface>();
    if(architecture != null){
      for (final SystemAnalysis ctxArchitecture : CollectionExt.filter(((SystemEngineering) architecture.eContainer()).getOwnedArchitectures(),
          SystemAnalysis.class)) {
        result.addAll(getInterfaces(ctxArchitecture));
      }
      result.addAll(architecture.getOwnedInterfacePkg() != null ? getAllInterfaces(architecture) : Collections
          .<Interface> emptyList());
    }
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
    if(architecture != null){
      for (final LogicalArchitecture logArchitecture : CollectionExt.filter(((SystemEngineering) architecture.eContainer()).getOwnedArchitectures(),
          LogicalArchitecture.class)) {
        result.addAll(getInterfaces(logArchitecture));
      }
      result.addAll(architecture.getOwnedInterfacePkg() != null ? getAllInterfaces(architecture) : Collections
          .<Interface> emptyList());
    }
    return new ArrayList<Interface>(result);
  }


  public static List<Interface> getInterfaces(SystemAnalysis architecture) {
    return (architecture != null) && (architecture.getOwnedInterfacePkg() != null) ? getAllInterfaces(architecture) : Collections.<Interface> emptyList();
  }

  /**
   * Gets all the logical architectures from the system
   * @param systemEngineering the parent System of the PA
   * @param physicalArchitecture the PhysicalArchitecture
   * @return List of LogicalArchitectures
   */
  static public List<LogicalArchitecture> getLogicalArchitecturesFiltered(SystemEngineering systemEngineering, PhysicalArchitecture physicalArchitecture) {
    List<LogicalArchitecture> list = new ArrayList<LogicalArchitecture>(1);
    if ((null != systemEngineering) && (null != physicalArchitecture)) {

      LogicalArchitecture la = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
      if (la != null) {
        list.add(la);
      }

      LogicalArchitecturePkg laPkg = getOwnedLogicalArchitecturePkg(systemEngineering);
      if (null != laPkg) {
        for (LogicalArchitecture arch : laPkg.getOwnedLogicalArchitectures()) {
          if (null != arch) {
            list.add(arch);
          }
        }
      }
    }

    return list;
  }

  public static FunctionPkg getOwnedFunctionalAnalysis(SystemEngineering currentElement) {
    if (currentElement != null) {
      for (ModellingArchitecture block : currentElement.getOwnedArchitectures()) {
        if (block instanceof FunctionPkg) {
          return (FunctionPkg) block;
        }
      }
    }
    return null;
  }

  /**
   * This method retrieves all the Logical Architecture packages from the model.
   * @param currentElement
   * @return LogicalArchitecture
   */
  public static LogicalArchitecture getOwnedLogicalArchitecture(CapellaElement currentElement) {
    LogicalArchitecture arch = null;
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement);
    if (sysEng != null) {
      arch = getOwnedLogicalArchitecture(sysEng);
    }
    return arch;
  }

  /**
   * This method retrieves the Logical Architecture packages from the model.
   * @param currentElement
   * @return LogicalArchitecture
   */
  public static LogicalArchitecture getOwnedLogicalArchitecture(SystemEngineering currentElement) {
    if (currentElement != null) {
      for (ModellingArchitecture block : currentElement.getOwnedArchitectures()) {
        if (block instanceof LogicalArchitecture) {
          return (LogicalArchitecture) block;
        }
      }
    }
    return null;
  }

  public static LogicalArchitecturePkg getOwnedLogicalArchitecturePkg(SystemEngineering currentElement) {
    if (currentElement != null) {
      for (ModellingArchitecturePkg block : currentElement.getOwnedArchitecturePkgs()) {
        if (block instanceof LogicalArchitecturePkg) {
          return (LogicalArchitecturePkg) block;
        }
      }
    }
    return null;
  }

  /**
   * This method retrieves the Operational Analysis packages from the model.
   * @param currentElement
   * @return OperationalAnalysis
   */
  public static OperationalAnalysis getOwnedOperationalAnalysis(SystemEngineering currentElement) {
    if (currentElement != null) {
      for (ModellingArchitecture block : currentElement.getOwnedArchitectures()) {
        if (block instanceof OperationalAnalysis) {
          return (OperationalAnalysis) block;
        }
      }
    }
    return null;
  }

  /**
   * This method retrieves the Physical Architecture packages from the model.
   * @param currentElement
   * @return PhysicalArchitecture
   */
  public static PhysicalArchitecture getOwnedPhysicalArchitecture(SystemEngineering currentElement) {
    if (currentElement != null) {
      for (ModellingArchitecture block : currentElement.getOwnedArchitectures()) {
        if (block instanceof PhysicalArchitecture) {
          return (PhysicalArchitecture) block;
        }
      }
    }
    return null;
  }

  public static PhysicalArchitecturePkg getOwnedPhysicalArchitecturePkg(SystemEngineering currentElement) {
    if (currentElement != null) {
      for (ModellingArchitecturePkg block : currentElement.getOwnedArchitecturePkgs()) {
        if (block instanceof PhysicalArchitecturePkg) {
          return (PhysicalArchitecturePkg) block;
        }
      }
    }
    return null;
  }

  /**
   * This method retrieves the System Analysis packages from the model.
   * @param currentElement
   * @return SystemAnalysis
   */
  public static SystemAnalysis getOwnedSystemAnalysis(SystemEngineering currentElement) {
    if (currentElement != null) {
      for (ModellingArchitecture block : currentElement.getOwnedArchitectures()) {
        if (block instanceof SystemAnalysis) {
          return (SystemAnalysis) block;
        }
      }
    }
    return null;
  }

  /**
   * This method retrieves the Physical Architecture package from the model.
   * @param currentElement
   * @return PhysicalArchitecture
   */
  public static PhysicalArchitecture getPhysicalArchitecture(CapellaElement currentElement) {
    PhysicalArchitecture arch = null;
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement);
    if (sysEng != null) {
      arch = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
    }
    return arch;
  }

  /**
   * @param modelElement : any 'ModelElement'
   * @return : 'BlockArchitecture', value can also be null
   */
  public static BlockArchitecture getRootBlockArchitecture(ModelElement modelElement) {
	  return BlockArchitectureExt.getRootBlockArchitecture(modelElement);
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

  public static SharedPkg getSharedPkg(CapellaElement modelElement) {
    return (SharedPkg) getSharedPkgRecursively(modelElement);
  }

  private static ModelElement getSharedPkgRecursively(ModelElement modelElement) {
    ModelElement result;
    if (null == modelElement) {
      result = null;
    } else if (modelElement instanceof SharedPkg) {
      result = modelElement;
    } else {
      ModelElement container = (ModelElement) modelElement.eContainer();
      result = getSharedPkgRecursively(container);
    }
    return result;
  }

  /**
   * This method retrieves the Shared Packages used by the current SystemEngineering.
   * @param currentElement
   * @return List<SharedPkg>
   */
  public static List<SharedPkg> getSharedPkgs(CapellaElement currentElement) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement);

    return getSharedPkgs(sysEng);
  }

  /**
   * This method retrieves the Shared Packages used by the current SystemEngineering.
   * @param sysEng
   * @return List<SharedPkg>
   */
  public static List<SharedPkg> getSharedPkgs(SystemEngineering sysEng) {
    List<SharedPkg> sharedPkgs = new ArrayList<SharedPkg>();
    for (ReuseLink lnk : sysEng.getReuseLinks()) {
      ReuseableStructure struct = lnk.getReused();
      if (struct instanceof SharedPkg) {
        sharedPkgs.add((SharedPkg) struct);
      }
    }
    return sharedPkgs;
  }

  /**
   * This method retrieves the System from the model.
   * @param currentElement
   * @return System
   */
  public static Component getSystem(CapellaElement currentElement) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement);
    if (sysEng != null) {
      return getSystem(sysEng);
    }
    return null;
  }

  /**
   * This method retrieves the System from the model.
   * @param currentElement
   * @return System
   */
  public static Component getSystem(SystemEngineering currentElement) {
    if (currentElement != null) {
      SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(currentElement);
      if (ca != null) {
        return ca.getSystem();
      }
    }
    return null;
  }

  public static SystemEngineering getSystemEngineering(ExtensibleElement currentElement) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentElement);
    return sysEng;
  }

  public static SystemEngineering getSystemEngineering(Project project) {
    for (ModelRoot root : project.getOwnedModelRoots())
			if (root instanceof SystemEngineering)
				return (SystemEngineering) root;
    return null;
  }

  public static void setEPBSArchitecturePkg(SystemEngineering currentElement, EPBSArchitecturePkg epbsArchitecturePkg) {
    currentElement.getOwnedArchitecturePkgs().add(epbsArchitecturePkg);

  }

}
