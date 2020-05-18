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

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.oa.RolePkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;

/**
 * Utility method on models
 * change from singleton to static method
 */
public class ModelQueryHelper {

  static public CapabilityPkg getCapabilityPkg(Project project) {
    return (CapabilityPkg) getCapabilityPkgFrom(project, getSystemAnalysis(project));
  }

  static public AbstractCapabilityPkg getCapabilityPkgFrom(Project project, ComponentArchitecture architecture) {
    if (architecture != null) {
      AbstractCapabilityPkg abstractCapabilityPkg = architecture.getOwnedAbstractCapabilityPkg();
      return abstractCapabilityPkg;
    }
    return null;
  }

  static private CapabilityRealizationPkg getCapabilityRealizationPkg(BlockArchitecture blockArchitecture) {
    if (blockArchitecture != null) {
      AbstractCapabilityPkg abstractCapabilityPkg = blockArchitecture.getOwnedAbstractCapabilityPkg();
      if (abstractCapabilityPkg instanceof CapabilityRealizationPkg) {
        return (CapabilityRealizationPkg) abstractCapabilityPkg;
      }
    }
    return null;
  }

  static private DataPkg getDataPkg(BlockArchitecture blockArchitecture) {
    return blockArchitecture.getOwnedDataPkg();
  }

  static public EPBSArchitecture getEPBSArchitecture(Project project) {
    SystemEngineering systemEngineering = getSystemEngineering(project);
    if (systemEngineering != null) {
      for (ModellingArchitecture arch : systemEngineering.getOwnedArchitectures()) {
        if (arch instanceof EPBSArchitecture) {
          return (EPBSArchitecture) arch;
        }
      }
    }
    return null;
  }

  static public ConfigurationItem getEpbsSystem(Project project) {
    EPBSArchitecture epbsArchitecture = getEPBSArchitecture(project);
    if (epbsArchitecture != null) {
      return (ConfigurationItem)epbsArchitecture.getSystem();
    }
    return null;
  }

  static public CapabilityRealizationPkg getLACapabilityRealizationPkg(Project project) {
    LogicalArchitecture logicalArchitecture = getLogicalArchitecture(project);
    if (logicalArchitecture != null) {
      return getCapabilityRealizationPkg(logicalArchitecture);
    }
    return null;
  }

  static public DataPkg getLADataPkg(Project project) {
    LogicalArchitecture logicalArchitecture = getLogicalArchitecture(project);
    if (logicalArchitecture != null) {
      return getDataPkg(logicalArchitecture);
    }
    return null;
  }

  static public LogicalComponentPkg getLogicalComponentPkg(Project project) {
    LogicalArchitecture logicalArchitecture = getLogicalArchitecture(project);
    if (logicalArchitecture != null) {
      return logicalArchitecture.getOwnedLogicalComponentPkg();
    }
    return null;
  }

  static public LogicalArchitecture getLogicalArchitecture(Project project) {
    SystemEngineering systemEngineering = getSystemEngineering(project);
    if (systemEngineering != null) {
      for (ModellingArchitecture arch : systemEngineering.getOwnedArchitectures()) {
        if (arch instanceof LogicalArchitecture) {
          return (LogicalArchitecture) arch;
        }
      }
    }
    return null;
  }

  static public Region getLogicalStateMachineRegion(Project project) {
    LogicalComponent lc = getLogicalSystem(project);
    if (lc != null) {
      return getStateMachineRegion(lc);
    }
    return null;
  }

  static public LogicalComponent getLogicalSystem(Project project) {
    LogicalArchitecture logicalArchitecture = getLogicalArchitecture(project);
    if (logicalArchitecture != null) {
      return (LogicalComponent)logicalArchitecture.getSystem();
    }
    return null;
  }

  /**
   * Get Operation Analysis class package.
   * @return can be <code>null</code>.
   */
  static public DataPkg getOADataPkg(Project project) {
    DataPkg result = null;
    OperationalAnalysis operationalAnalysis = getOperationalAnalysis(project);
    if (null != operationalAnalysis) {
      result = getDataPkg(operationalAnalysis);
    }
    return result;
  }

  static public OperationalAnalysis getOperationalAnalysis(Project project) {
    SystemEngineering systemEngineering = getSystemEngineering(project);
    if (systemEngineering != null) {
      for (ModellingArchitecture arch : systemEngineering.getOwnedArchitectures()) {
        if (arch instanceof OperationalAnalysis) {
          return (OperationalAnalysis) arch;
        }
      }
    }
    return null;
  }

  static public OperationalCapabilityPkg getOperationalCapabilityPkg(Project project) {
    OperationalAnalysis operationalAnalysis = getOperationalAnalysis(project);
    if (operationalAnalysis != null) {
      AbstractCapabilityPkg abstractCapabilityPkg = operationalAnalysis.getOwnedAbstractCapabilityPkg();
      if (abstractCapabilityPkg instanceof OperationalCapabilityPkg) {
        return (OperationalCapabilityPkg) abstractCapabilityPkg;
      }
    }
    return null;
  }

  static public EntityPkg getOperationalEntityPkg(Project project) {
    EntityPkg result = null;
    OperationalAnalysis operationalAnalysis = getOperationalAnalysis(project);
    if (operationalAnalysis != null) {
      result = operationalAnalysis.getOwnedEntityPkg();
    }
    return result;
  }

  static public RolePkg getOperationalRolePkg(Project project) {
    RolePkg result = null;
    OperationalAnalysis operationalAnalysis = getOperationalAnalysis(project);
    if (operationalAnalysis != null) {
      result = operationalAnalysis.getOwnedRolePkg();
    }
    return result;
  }

  static public CapabilityRealizationPkg getPACapabilityRealizationPkg(Project project) {
    PhysicalArchitecture physicalArchitecture = getPhysicalArchitecture(project);
    if (physicalArchitecture != null) {
      return getCapabilityRealizationPkg(physicalArchitecture);
    }
    return null;
  }

  static public DataPkg getPADataPkg(Project project) {
    PhysicalArchitecture physicalArchitecture = getPhysicalArchitecture(project);
    if (physicalArchitecture != null) {
      return getDataPkg(physicalArchitecture);
    }
    return null;
  }

  static public PhysicalComponentPkg getPhysicalComponentPkg(Project project) {
    PhysicalArchitecture physicalArchitecture = getPhysicalArchitecture(project);
    if (physicalArchitecture != null) {
      return physicalArchitecture.getOwnedPhysicalComponentPkg();
    }
    return null;
  }

  static public PhysicalArchitecture getPhysicalArchitecture(Project project) {
    SystemEngineering systemEngineering = getSystemEngineering(project);
    if (systemEngineering != null) {
      for (ModellingArchitecture arch : systemEngineering.getOwnedArchitectures()) {
        if (arch instanceof PhysicalArchitecture) {
          return (PhysicalArchitecture) arch;
        }
      }
    }
    return null;
  }

  static public Region getPhysicalStateMachineRegion(Project project) {
    PhysicalComponent pc = getPhysicalSystem(project);
    if (pc != null) {
      return getStateMachineRegion(pc);
    }
    return null;
  }

  static public PhysicalComponent getPhysicalSystem(Project project) {
    PhysicalArchitecture physicalArchitecture = getPhysicalArchitecture(project);
    if (physicalArchitecture != null) {
      return (PhysicalComponent)physicalArchitecture.getSystem();
    }
    return null;
  }

  static public ConfigurationItem getRootConfigurationItem(Project project) {
    EPBSArchitecture epbsArchitecture = getEPBSArchitecture(project);
    if (epbsArchitecture != null) {
      return (ConfigurationItem)epbsArchitecture.getSystem();
    }
    return null;
  }

  static public ConfigurationItemPkg getRootConfigurationItemPkg(Project project) {
    EPBSArchitecture epbsArchitecture = getEPBSArchitecture(project);
    if (epbsArchitecture != null) {
      return epbsArchitecture.getOwnedConfigurationItemPkg();
    }
    return null;
  }

  static public Entity getRootEntity(Project project) {
    OperationalAnalysis operationalAnalysis = getOperationalAnalysis(project);
    if (operationalAnalysis != null) {
      EntityPkg pkg = operationalAnalysis.getOwnedEntityPkg();
      for (Entity entity : pkg.getOwnedEntities()) {
        return entity;
      }
    }
    return null;
  }

  static public LogicalFunction getRootLogicalFunction(Project project) {
    LogicalArchitecture logicalArchitecture = getLogicalArchitecture(project);
    if (logicalArchitecture != null) {
      FunctionPkg pkg = logicalArchitecture.getOwnedFunctionPkg();
      if (pkg instanceof LogicalFunctionPkg) {
        for (LogicalFunction fct : ((LogicalFunctionPkg) pkg).getOwnedLogicalFunctions()) {
          return fct;
        }
      }
    }
    return null;
  }

  static public OperationalActivity getRootOperationalActivity(Project project) {
    OperationalAnalysis operationalAnalysis = getOperationalAnalysis(project);
    if (operationalAnalysis != null) {
      FunctionPkg pkg = operationalAnalysis.getOwnedFunctionPkg();
      if (pkg instanceof OperationalActivityPkg) {
        for (OperationalActivity fct : ((OperationalActivityPkg) pkg).getOwnedOperationalActivities()) {
          return fct;
        }
      }
    }
    return null;
  }

  static public OperationalActivity getAnOperationalActivity(String operationalActivityName, Project project) {
    OperationalActivity result = null;
    OperationalActivity rootOperationalActivity = getRootOperationalActivity(project);
    if (rootOperationalActivity != null) {
      EList<OperationalActivity> subOpActivities = rootOperationalActivity.getChildrenOperationalActivities();
      for (OperationalActivity op : subOpActivities) {
        if (op.getName().equalsIgnoreCase(operationalActivityName)) {
          result = op;
        }
      }
    }
    return result;
  }

  static public OperationalCapabilityPkg getRootOperationalCapability(Project project) {
    OperationalAnalysis operationalAnalysis = getOperationalAnalysis(project);
    if (operationalAnalysis != null) {
      AbstractCapabilityPkg pkg = operationalAnalysis.getOwnedAbstractCapabilityPkg();
      if (pkg instanceof OperationalCapabilityPkg) {
        return (OperationalCapabilityPkg) pkg;
      }
    }
    return null;
  }

  static public PhysicalFunction getRootPhysicalFunction(Project project) {
    PhysicalArchitecture physicalArchitecture = getPhysicalArchitecture(project);
    if (physicalArchitecture != null) {
      FunctionPkg pkg = physicalArchitecture.getOwnedFunctionPkg();
      if (pkg instanceof PhysicalFunctionPkg) {
        for (PhysicalFunction fct : ((PhysicalFunctionPkg) pkg).getOwnedPhysicalFunctions()) {
          return fct;
        }
      }
    }
    return null;
  }

  static public SystemFunction getRootSystemFunction(Project project) {
    SystemAnalysis systemAnalysis = getSystemAnalysis(project);
    if (systemAnalysis != null) {
      FunctionPkg pkg = systemAnalysis.getOwnedFunctionPkg();
      if (pkg instanceof SystemFunctionPkg) {
        for (SystemFunction fct : ((SystemFunctionPkg) pkg).getOwnedSystemFunctions()) {
          return fct;
        }
      }
    }
    return null;
  }

  static public AbstractCapabilityPkg getSACapabilityPkg(Project project) {
    SystemAnalysis systemAnalysis = getSystemAnalysis(project);
    if (systemAnalysis != null) {
      return systemAnalysis.getOwnedAbstractCapabilityPkg();
    }
    return null;
  }

  static public DataPkg getSADataPkg(Project project) {
    SystemAnalysis systemAnalysis = getSystemAnalysis(project);
    if (systemAnalysis != null) {
      return getDataPkg(systemAnalysis);
    }
    return null;
  }

  static public MissionPkg getSAMissionPkg(Project project) {
    SystemAnalysis systemAnalysis = getSystemAnalysis(project);
    if (systemAnalysis != null) {
      return systemAnalysis.getOwnedMissionPkg();
    }
    return null;
  }

  static public Region getStateMachineRegion(Block block) {
    for (StateMachine sm : block.getOwnedStateMachines()) {
      for (Region region : sm.getOwnedRegions()) {
        return region;
      }
    }
    return null;
  }

  static public SystemComponent getSystem(Project project) {
    SystemAnalysis systemAnalysis = getSystemAnalysis(project);
    if (systemAnalysis != null) {
      return (SystemComponent)systemAnalysis.getSystem();
    }
    return null;
  }

  static public ComponentPkg getSystemComponentPkg(Project project) {
    SystemAnalysis systemAnalysis = getSystemAnalysis(project);
    if (systemAnalysis != null) {
      return systemAnalysis.getOwnedSystemComponentPkg();
    }
    return null;
  }

  /**
   * Get the Interface Package from the System layer for a given Project
   * @param project a given Project
   * @return Interface Package from the System layer
   */
  public static InterfacePkg getSystemInterfacePkg(Project project) {
    InterfacePkg resultInterfacePkg = null;
    SystemAnalysis systemAnalysis = getSystemAnalysis(project);
    if (systemAnalysis != null) {
      resultInterfacePkg = systemAnalysis.getOwnedInterfacePkg();
    }
    return resultInterfacePkg;
  }

  /**
   * Get the Interface Package from the Logical layer for a given Project
   * @param project a given Project
   * @return Interface Package from the Logical layer
   */
  public static InterfacePkg getLogicalInterfacePkg(Project project) {
    InterfacePkg resultInterfacePkg = null;
    LogicalArchitecture logicalArchitecture = getLogicalArchitecture(project);
    if (logicalArchitecture != null) {
      resultInterfacePkg = logicalArchitecture.getOwnedInterfacePkg();
    }
    return resultInterfacePkg;
  }

  /**
   * Get the Data Package from the System layer for a given Project
   * @param project a given Project
   * @return the Data Package from the System layer
   */
  public static DataPkg getSystemDataPkg(Project project) {
    DataPkg resultDataPkg = null;
    SystemAnalysis systemAnalysis = getSystemAnalysis(project);
    if (systemAnalysis != null) {
      resultDataPkg = systemAnalysis.getOwnedDataPkg();
    }
    return resultDataPkg;
  }

  /**
   * Find the sub Package Predefined Types in Data Package in a System Layer
   * @param project a given Project
   * @return the Predefined Types Package from Data Package
   */
  public static DataPkg getSystemDataPredefinedTypesPkg(Project project) {
    DataPkg result = null;
    DataPkg systemDataPkg = getSystemDataPkg(project);
    EList<DataPkg> ownedDataPkgs = systemDataPkg.getOwnedDataPkgs();
    for (DataPkg dataPkg : ownedDataPkgs) {
      if (NamingConstants.PredefinedTypesCmd_predefinedDataTypePkg_name.equalsIgnoreCase(dataPkg.getName())) {
        result = dataPkg;
      }
    }
    return result;
  }

  /**
   * getBooleanTypeFromPredefinedTypesPkg
   * @param predefinedTypesPkg
   * @return BooleanType From PredefinedTypes Pkg (System -> Data -> Predefined Types)
   */
  public static DataType getBooleanTypeFromPredefinedTypesPkg(DataPkg predefinedTypesPkg) {
    EList<DataType> ownedDataTypes = predefinedTypesPkg.getOwnedDataTypes();
    DataType resultDataType = null;
    for (DataType dataType : ownedDataTypes) {
      if (dataType instanceof BooleanType) {
        resultDataType = dataType;
      }
    }
    return resultDataType;
  }

  static public SystemAnalysis getSystemAnalysis(Project project) {
    SystemEngineering systemEngineering = getSystemEngineering(project);
    if (systemEngineering != null) {
      for (ModellingArchitecture arch : systemEngineering.getOwnedArchitectures()) {
        if (arch instanceof SystemAnalysis) {
          return (SystemAnalysis) arch;
        }
      }
    }
    return null;
  }

  static public SystemEngineering getSystemEngineering(Project project) {
    if (project != null) {
      for (ModelRoot root : project.getOwnedModelRoots()) {
        if (root instanceof SystemEngineering) {
          return (SystemEngineering) root;
        }
      }
    }
    return null;
  }

  static public Region getSystemStateMachineRegion(Project project) {
    SystemComponent system = getSystem(project);
    if (system != null) {
      return getStateMachineRegion(system);
    }
    return null;
  }

}
