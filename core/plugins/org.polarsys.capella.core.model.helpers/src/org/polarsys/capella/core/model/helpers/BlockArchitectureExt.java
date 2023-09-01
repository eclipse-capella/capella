/*******************************************************************************
 * Copyright (c) 2006, 2020, 2020 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsFactory;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.la.SystemAnalysisRealization;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;

/**
 */
public class BlockArchitectureExt {

  private BlockArchitectureExt() {
    // To hide the implicit public one.
  }

  public enum Type {
    OA, SA, LA, PA, EPBS
  }

  public enum FunctionType {
    SYSTEM_FUNCTION, DUPLICATE, GATHER, ROUTE, SELECT, SPLIT
  };

  public enum FunctionPortType {
    IN_FUNCTION_PORT, OUT_FUNCTION_PORT
  };

  public enum ComponentPortType {
    IN_FLOW_PORT, OUT_FLOW_PORT, IN_OUT_FLOW_PORT, STANDARD_PORT, PHYSICAL_PORT
  };

  public enum LinkDirection {
    SOURCE, TARGET
  };

  public enum ConfigurationItemType {
    COTS, CS, HW, INTERFACE, NDI, PRIME_ITEM, SYSTEM
  };

  public static Type getBlockArchitectureType(BlockArchitecture block) {
    if (block instanceof OperationalAnalysis)
      return Type.OA;
    else if (block instanceof SystemAnalysis)
      return Type.SA;
    else if (block instanceof LogicalArchitecture)
      return Type.LA;
    else if (block instanceof PhysicalArchitecture)
      return Type.PA;
    else if (block instanceof EPBSArchitecture)
      return Type.EPBS;
    return null;
  }

  public static BlockArchitecture getBlockArchitecture(Type type, Project project) {
    SystemEngineering system = SystemEngineeringExt.getSystemEngineering(project);
    switch (type) {
    case OA:
      return SystemEngineeringExt.getOperationalAnalysis(system);
    case SA:
      return SystemEngineeringExt.getSystemAnalysis(system);
    case LA:
      return SystemEngineeringExt.getLogicalArchitecture(system);
    case PA:
      return SystemEngineeringExt.getPhysicalArchitecture(system);
    case EPBS:
      return SystemEngineeringExt.getEPBSArchitecture(system);
    default:
      return null;
    }
  }

  /**
   * Return the block architecture from the given type in the given project
   */
  public static BlockArchitecture getBlockArchitecture(EClass clazz, Project project) {
    SystemEngineering system = SystemEngineeringExt.getSystemEngineering(project);

    if (OaPackage.Literals.OPERATIONAL_ANALYSIS.equals(clazz)) {
      return SystemEngineeringExt.getOperationalAnalysis(system);

    } else if (CtxPackage.Literals.SYSTEM_ANALYSIS.equals(clazz)) {
      return SystemEngineeringExt.getSystemAnalysis(system);

    } else if (LaPackage.Literals.LOGICAL_ARCHITECTURE.equals(clazz)) {
      return SystemEngineeringExt.getLogicalArchitecture(system);

    } else if (PaPackage.Literals.PHYSICAL_ARCHITECTURE.equals(clazz)) {
      return SystemEngineeringExt.getPhysicalArchitecture(system);

    } else if (EpbsPackage.Literals.EPBS_ARCHITECTURE.equals(clazz)) {
      return SystemEngineeringExt.getEPBSArchitecture(system);
    }
    return null;
  }

  /**
   * Returns all architectures allocated by the architecture and also the given architecture
   */
  public static Collection<BlockArchitecture> getAllAllocatedArchitectures(BlockArchitecture architecture) {
    LinkedList<BlockArchitecture> listArchitectures = new LinkedList<>();
    Collection<BlockArchitecture> allocatedArchitectures = new HashSet<>();

    if (architecture != null) {
      listArchitectures.add(architecture);

      while (!listArchitectures.isEmpty()) {
        BlockArchitecture current = listArchitectures.removeFirst();
        allocatedArchitectures.add(current);

        for (BlockArchitecture allocated : current.getAllocatedArchitectures()) {
          if ((allocated != null) && !allocatedArchitectures.contains(allocated)) {
            listArchitectures.addLast(allocated);
          }
        }
      }
    }

    return allocatedArchitectures;
  }

  /**
   * Returns all architectures allocating by the architecture and also the given architecture
   */
  public static Collection<BlockArchitecture> getAllAllocatingArchitectures(BlockArchitecture architecture) {
    LinkedList<BlockArchitecture> listArchitectures = new LinkedList<>();
    Collection<BlockArchitecture> allocatingArchitectures = new HashSet<>();

    if (architecture != null) {
      listArchitectures.add(architecture);

      while (!listArchitectures.isEmpty()) {
        BlockArchitecture current = listArchitectures.removeFirst();
        allocatingArchitectures.add(current);

        for (BlockArchitecture allocated : current.getAllocatingArchitectures()) {
          if ((allocated != null) && !allocatingArchitectures.contains(allocated)) {
            listArchitectures.addLast(allocated);
          }
        }
      }
    }

    return allocatingArchitectures;
  }

  /**
   * Seems to return all exchanges items from architecture and previous architectures
   */
  public static List<ExchangeItem> getAllExchangeItems(BlockArchitecture architecture) {
    // TO-DO Reimplements this. From AbstractScenarioHelper.
    List<ExchangeItem> result = new ArrayList<>();
    SystemEngineering se = SystemEngineeringExt.getSystemEngineering(architecture);
    TreeIterator<EObject> allContents = se.eAllContents();
    while (allContents.hasNext()) {
      EObject object = allContents.next();
      if (object instanceof ExchangeItem) {
        result.add((ExchangeItem) object);
      }
      // if the current object is a block architecture situated after the current architecture,
      // break the function, the job is done.
      if (object instanceof BlockArchitecture
          && se.getOwnedArchitectures().indexOf(object) > se.getOwnedArchitectures().indexOf(architecture)) {
        return result;
      }
    }
    return result;
  }

  public static List<ComponentExchange> getAllComponentExchanges(BlockArchitecture architecture) {
    List<ComponentExchange> instList = new ArrayList<>();
    for (EObject obj : EObjectExt.getAll(architecture, FaPackage.Literals.COMPONENT_EXCHANGE)) {
      instList.add((ComponentExchange) obj);
    }
    return instList;
  }

  /*
   * Returns the architecture that contains context, or null if the context is not contained in any architecture.
   * 
   * @param context the element to be analyze
   * 
   * @return the architecture that contains context, or null if the context is not contained in any architecture.
   */
  public static BlockArchitecture getRootBlockArchitecture(final EObject context) {
    EObject currentElement = context;
    while (currentElement != null) {
      if (currentElement instanceof BlockArchitecture) {
        return (BlockArchitecture) currentElement;
      }
      currentElement = currentElement.eContainer();
    }

    return null;
  }

  /**
   * @param anArchitecture
   *          an Architecture
   * @return all previous architectures of anArchitecture
   */
  public static List<BlockArchitecture> getPreviousBlockArchitectures(final BlockArchitecture anArchitecture) {
    List<BlockArchitecture> returnedList = new ArrayList<>();
    SystemEngineering sysEng = SystemEngineeringExt.getSystemEngineering(anArchitecture);
    OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(sysEng);
    if ((oa != null) && anArchitecture.equals(oa)) {
      return returnedList;
    }
    if (oa != null) {
      returnedList.add(oa);
    }
    SystemAnalysis sa = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
    if ((sa != null) && anArchitecture.equals(sa)) {
      return returnedList;
    }
    if (sa != null) {
      returnedList.add(sa);
    }
    LogicalArchitecture la = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);
    if ((la != null) && anArchitecture.equals(la)) {
      return returnedList;
    }
    if (la != null) {
      returnedList.add(la);
    }
    PhysicalArchitecture pa = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
    if ((pa != null) && anArchitecture.equals(pa)) {
      return returnedList;
    }
    if (pa != null) {
      returnedList.add(pa);
    }

    return returnedList;
  }

  /**
   * @param context
   * @return the current architecture that contains context and and its previous architectures, or an empty list if
   *         context is not contained in any architecture
   */
  public static List<BlockArchitecture> getRootAndPreviousBlockArchitectures(final EObject context) {
    List<BlockArchitecture> returnedList = new ArrayList<>();
    BlockArchitecture root = getRootBlockArchitecture(context);
    if (root == null) {
      return returnedList;
    }
    returnedList.add(root);
    returnedList.addAll(getPreviousBlockArchitectures(root));
    return returnedList;
  }

  public static Structure getActorPkg(BlockArchitecture architecture) {
    return getActorPkg(architecture, true);

  }

  /**
   * Retrieve the actor pkg from the given architecture
   */
  public static ComponentPkg getActorPkg(BlockArchitecture architecture, boolean create) {
    return getComponentPkg(architecture, create);
  }

  public static ComponentPkg getComponentPkg(BlockArchitecture blockArchitecture) {
    return getComponentPkg(blockArchitecture, true);
  }

  public static ComponentPkg getComponentPkg(BlockArchitecture blockArchitecture, boolean create) {
    if (blockArchitecture instanceof OperationalAnalysis) {
      OperationalAnalysis architecture = (OperationalAnalysis) blockArchitecture;

      if ((architecture.getOwnedEntityPkg() == null) && create) {
        EntityPkg pkg = OaFactory.eINSTANCE
            .createEntityPkg(NamingConstants.CreateOpAnalysisCmd_operationalEntities_pkg_name);
        architecture.setOwnedEntityPkg(pkg);
      }
      return architecture.getOwnedEntityPkg();

    } else if (blockArchitecture instanceof SystemAnalysis) {
      SystemAnalysis architecture = (SystemAnalysis) blockArchitecture;

      if ((architecture.getOwnedSystemComponentPkg() == null) && create) {
        SystemComponentPkg pkg = CtxFactory.eINSTANCE
            .createSystemComponentPkg(NamingConstants.CreateSysAnalysisCmd_actors_pkg_name);
        architecture.setOwnedSystemComponentPkg(pkg);
      }
      return architecture.getOwnedSystemComponentPkg();

    } else if (blockArchitecture instanceof LogicalArchitecture) {
      LogicalArchitecture architecture = (LogicalArchitecture) blockArchitecture;

      if ((architecture.getOwnedLogicalComponentPkg() == null) && create) {
        LogicalComponentPkg pkg = LaFactory.eINSTANCE
            .createLogicalComponentPkg(NamingConstants.CreateLogicalArchCmd_actors_pkg_name);
        architecture.setOwnedLogicalComponentPkg(pkg);
      }
      return architecture.getOwnedLogicalComponentPkg();

    } else if (blockArchitecture instanceof PhysicalArchitecture) {
      PhysicalArchitecture architecture = (PhysicalArchitecture) blockArchitecture;

      if ((architecture.getOwnedPhysicalComponentPkg() == null) && create) {
        PhysicalComponentPkg pkg = PaFactory.eINSTANCE
            .createPhysicalComponentPkg(NamingConstants.CreatePhysicalArchCmd_actors_pkg_name);
        architecture.setOwnedPhysicalComponentPkg(pkg);
      }
      return architecture.getOwnedPhysicalComponentPkg();

    } else if (blockArchitecture instanceof EPBSArchitecture) {
      EPBSArchitecture architecture = (EPBSArchitecture) blockArchitecture;

      if ((architecture.getOwnedConfigurationItemPkg() == null) && create) {
        ConfigurationItemPkg pkg = EpbsFactory.eINSTANCE
            .createConfigurationItemPkg(NamingConstants.CreateEPBSArchCmd_configurationItemPkg_name);
        architecture.setOwnedConfigurationItemPkg(pkg);
      }
      return architecture.getOwnedConfigurationItemPkg();
    }

    return null;
  }

  public static boolean isDefaultNameComponentPkg(AbstractNamedElement pkg) {
    return NamingConstants.CreateSysAnalysisCmd_actors_pkg_name.equals(pkg.getName())
        || NamingConstants.CreateLogicalArchCmd_actors_pkg_name.equals(pkg.getName())
        || NamingConstants.CreatePhysicalArchCmd_actors_pkg_name.equals(pkg.getName())
        || NamingConstants.CreateEPBSArchCmd_configurationItemPkg_name.equals(pkg.getName());
  }

  /**
   * Retrieve the data pkg from the given architecture
   */
  public static DataPkg getDataPkg(BlockArchitecture architecture, boolean create) {
    if ((architecture.getOwnedDataPkg() == null) && create) {
      // to externalize when constants in skeleton will be into helpers.
      DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg(NamingConstants.CreateCommonCmd_data_pkg_name);
      architecture.setOwnedDataPkg(pkg);
    }
    return architecture.getOwnedDataPkg();
  }

  /**
   * Returns whether the element use a default DataPkg name
   */
  public static boolean isDefaultNameDataPkg(AbstractNamedElement pkg) {
    return NamingConstants.CreateCommonCmd_data_pkg_name.equals(pkg.getName());
  }

  /**
   * Retrieve the data pkg from the given architecture
   */
  public static DataPkg getDataPkg(BlockArchitecture architecture) {
    return getDataPkg(architecture, true);
  }

  /**
   * Retrieve the capability pkg from the given architecture
   */
  public static AbstractCapabilityPkg getAbstractCapabilityPkg(BlockArchitecture architecture) {
    return getAbstractCapabilityPkg(architecture, true);
  }

  public static AbstractCapabilityPkg getAbstractCapabilityPkg(BlockArchitecture architecture, boolean create) {
    if (architecture.getOwnedAbstractCapabilityPkg() == null && create) {
      // to externalize when constants in skeleton will be into helpers.
      AbstractCapabilityPkg pkg = null;
      if (architecture instanceof OperationalAnalysis) {
        pkg = OaFactory.eINSTANCE
            .createOperationalCapabilityPkg(NamingConstants.CreateOpAnalysisCmd_operationalCapabilities_pkg_name);

      } else if (architecture instanceof SystemAnalysis) {
        pkg = CtxFactory.eINSTANCE.createCapabilityPkg(NamingConstants.CreateSysAnalysisCmd_capabilities_pkg_name);

      } else if (architecture instanceof LogicalArchitecture) {
        pkg = LaFactory.eINSTANCE
            .createCapabilityRealizationPkg(NamingConstants.CreateCommonCmd_capability_realisation_pkg_name);

      } else if (architecture instanceof PhysicalArchitecture) {
        pkg = LaFactory.eINSTANCE
            .createCapabilityRealizationPkg(NamingConstants.CreateCommonCmd_capability_realisation_pkg_name);

      } else if (architecture instanceof EPBSArchitecture) {
        pkg = LaFactory.eINSTANCE
            .createCapabilityRealizationPkg(NamingConstants.CreateCommonCmd_capability_realisation_pkg_name);
      }
      architecture.setOwnedAbstractCapabilityPkg(pkg);
    }
    return architecture.getOwnedAbstractCapabilityPkg();
  }

  /**
   * Returns whether the element use a default CapabilityPkg name
   */
  public static boolean isDefaultNameAbstractCapabilityPkg(AbstractNamedElement pkg) {
    return NamingConstants.CreateOpAnalysisCmd_operationalCapabilities_pkg_name.equals(pkg.getName())
        || NamingConstants.CreateSysAnalysisCmd_capabilities_pkg_name.equals(pkg.getName())
        || NamingConstants.CreateCommonCmd_capability_realisation_pkg_name.equals(pkg.getName());
  }

  /**
   * Retrieve the interface pkg from the given architecture Create if not exists
   */
  public static InterfacePkg getInterfacePkg(BlockArchitecture architecture) {
    return getInterfacePkg(architecture, true);
  }

  /**
   * Returns whether the element use a default InterfacePkg name
   */
  public static boolean isDefaultNameInterfacePkg(AbstractNamedElement pkg) {
    return NamingConstants.CreateCommonCmd_interfaces_pkg_name.equals(pkg.getName());
  }

  /**
   * Retrieve the interface pkg from the given architecture
   */
  public static InterfacePkg getInterfacePkg(BlockArchitecture architecture, boolean create) {
    if ((architecture.getOwnedInterfacePkg() == null) && create) {
      // to externalize when constants in skeleton will be into helpers.
      InterfacePkg pkg = CsFactory.eINSTANCE.createInterfacePkg(NamingConstants.CreateCommonCmd_interfaces_pkg_name);
      architecture.setOwnedInterfacePkg(pkg);
    }
    return architecture.getOwnedInterfacePkg();
  }

  /**
   * Return the context related to the architecture Create if not exist
   */
  public static ComponentPkg getContext(BlockArchitecture architecture) {
    return getContext(architecture, true);
  }

  /**
   * Return the context related to the architecture
   * 
   * @param architecture
   *          Never null in normal phases
   * @return
   */
  public static ComponentPkg getContext(BlockArchitecture architecture, boolean create) {
    return getComponentPkg(architecture, create);
  }

  /**
   * Retrieve the data pkg from the given architecture
   */
  public static FunctionPkg getFunctionPkg(BlockArchitecture architecture) {
    return getFunctionPkg(architecture, true);
  }

  public static FunctionPkg getFunctionPkg(BlockArchitecture architecture, boolean create) {
    if ((architecture.getOwnedFunctionPkg() == null) && create) {
      FunctionPkg pkg = null;

      // to externalize when constants in skeleton will be into helpers.
      if (architecture instanceof OperationalAnalysis) {
        pkg = OaFactory.eINSTANCE
            .createOperationalActivityPkg(NamingConstants.CreateOpAnalysisCmd_operationalActivities_pkg_name);

      } else if (architecture instanceof SystemAnalysis) {
        pkg = CtxFactory.eINSTANCE
            .createSystemFunctionPkg(NamingConstants.CreateSysAnalysisCmd_system_functions_pkg_name);

      } else if (architecture instanceof LogicalArchitecture) {
        pkg = LaFactory.eINSTANCE
            .createLogicalFunctionPkg(NamingConstants.CreateLogicalArchCmd_logicalFunctions_pkg_name);

      } else if (architecture instanceof PhysicalArchitecture) {
        pkg = PaFactory.eINSTANCE
            .createPhysicalFunctionPkg(NamingConstants.CreatePhysicalArchCmd_physicalFunctions_pkg_name);
      }

      architecture.setOwnedFunctionPkg(pkg);
    }
    return architecture.getOwnedFunctionPkg();
  }

  /**
   * Returns whether the element use a default FunctionPkg name
   */
  public static boolean isDefaultNameFunctionPkg(AbstractNamedElement pkg) {
    return NamingConstants.CreateOpAnalysisCmd_operationalActivities_pkg_name.equals(pkg.getName())
        || NamingConstants.CreateSysAnalysisCmd_system_functions_pkg_name.equals(pkg.getName())
        || NamingConstants.CreateLogicalArchCmd_logicalFunctions_pkg_name.equals(pkg.getName())
        || NamingConstants.CreatePhysicalArchCmd_physicalFunctions_pkg_name.equals(pkg.getName());
  }

  /**
   * Retrieve the root function from the given architecture
   */
  public static AbstractFunction getRootFunction(BlockArchitecture architecture) {
    return getRootFunction(architecture, true);
  }

  public static AbstractFunction getRootFunction(BlockArchitecture architecture, boolean create) {

    AbstractFunction function = null;

    if (architecture != null) {

      FunctionPkg pkg = getFunctionPkg(architecture, create);
      if (pkg == null) {
        return function;
      }

      // to externalize when constants in skeleton will be into helpers.
      if (architecture instanceof OperationalAnalysis) {
        OperationalActivityPkg spkg = ((OperationalActivityPkg) pkg);
        if (!spkg.getOwnedOperationalActivities().isEmpty()) {
          function = spkg.getOwnedOperationalActivities().get(0);
        } else if (create) {
          function = OaFactory.eINSTANCE
              .createOperationalActivity(NamingConstants.CreateOpAnalysisCmd_operationalActivity_root_name);
          spkg.getOwnedOperationalActivities().add((OperationalActivity) function);
        }

      } else if (architecture instanceof SystemAnalysis) {
        SystemFunctionPkg spkg = ((SystemFunctionPkg) pkg);
        if (!spkg.getOwnedSystemFunctions().isEmpty()) {
          function = spkg.getOwnedSystemFunctions().get(0);
        } else if (create) {
          function = CtxFactory.eINSTANCE
              .createSystemFunction(NamingConstants.CreateSysAnalysisCmd_system_function_root_name);
          spkg.getOwnedSystemFunctions().add((SystemFunction) function);
        }

      } else if (architecture instanceof LogicalArchitecture) {
        LogicalFunctionPkg spkg = ((LogicalFunctionPkg) pkg);
        if (!spkg.getOwnedLogicalFunctions().isEmpty()) {
          function = spkg.getOwnedLogicalFunctions().get(0);
        } else if (create) {
          function = LaFactory.eINSTANCE
              .createLogicalFunction(NamingConstants.CreateLogicalArchCmd_logicalFunction_root_name);
          spkg.getOwnedLogicalFunctions().add((LogicalFunction) function);
        }

      } else if (architecture instanceof PhysicalArchitecture) {
        PhysicalFunctionPkg spkg = ((PhysicalFunctionPkg) pkg);
        if (!spkg.getOwnedPhysicalFunctions().isEmpty()) {
          function = spkg.getOwnedPhysicalFunctions().get(0);
        } else if (create) {
          function = PaFactory.eINSTANCE
              .createPhysicalFunction(NamingConstants.CreatePhysicalArchCmd_physicalFunction_root_name);
          spkg.getOwnedPhysicalFunctions().add((PhysicalFunction) function);
        }
      }
    }

    return function;
  }

  /**
   * Returns whether the element use a default Function name
   */
  public static boolean isDefaultNameRootFunction(AbstractNamedElement element) {
    return NamingConstants.CreateOpAnalysisCmd_operationalActivity_root_name.equals(element.getName())
        || NamingConstants.CreateSysAnalysisCmd_system_function_root_name.equals(element.getName())
        || NamingConstants.CreateLogicalArchCmd_logicalFunction_root_name.equals(element.getName())
        || NamingConstants.CreatePhysicalArchCmd_physicalFunction_root_name.equals(element.getName());
  }

  /**
   * Return the main component of the phase
   * 
   * @param architecture
   * @param create
   * @return
   * 
   * @deprecated use {@link BlockArchitecture::getSystem} or {@link BlockArchitectureExt::getOrCreateComponent} instead.
   */
  @Deprecated
  public static Component getFirstComponent(BlockArchitecture architecture, boolean create) {
    Component first = null;

    if (architecture instanceof OperationalAnalysis) {
      EntityPkg entityPkg = (EntityPkg) getComponentPkg(architecture, true);
      EList<Entity> ownedEntities = entityPkg.getOwnedEntities();

      first = ownedEntities.stream().filter(x -> !x.isActor()).findFirst().orElse(null);

      if ((first == null) && create) {
        first = OaFactory.eINSTANCE.createEntity(NamingConstants.CreateOaAnalysisCmd_entity_name);
        ownedEntities.add((Entity) first);

        CapellaElementExt.creationService(first);
      }

    } else if (architecture instanceof SystemAnalysis) {
      first = ((SystemAnalysis) architecture).getSystem();
      if ((first == null) && create) {
        first = CtxFactory.eINSTANCE.createSystemComponent(NamingConstants.CreateSysAnalysisCmd_system_name);
        ((SystemComponentPkg) getComponentPkg(architecture, true)).getOwnedSystemComponents()
            .add((SystemComponent) first);
      }

    } else if (architecture instanceof LogicalArchitecture) {
      first = ((LogicalArchitecture) architecture).getSystem();
      if ((first == null) && create) {
        first = LaFactory.eINSTANCE.createLogicalComponent(NamingConstants.CreateLogicalArchCmd_logicalComponent_name);
        ((LogicalComponentPkg) getComponentPkg(architecture, true)).getOwnedLogicalComponents()
            .add((LogicalComponent) first);
      }

    } else if (architecture instanceof PhysicalArchitecture) {
      first = ((PhysicalArchitecture) architecture).getSystem();
      if ((first == null) && create) {
        first = PaFactory.eINSTANCE
            .createPhysicalComponent(NamingConstants.CreatePhysicalArchCmd_physicalComponent_name);
        ((PhysicalComponentPkg) getComponentPkg(architecture, true)).getOwnedPhysicalComponents()
            .add((PhysicalComponent) first);
      }

    } else if (architecture instanceof EPBSArchitecture) {
      first = ((EPBSArchitecture) architecture).getSystem();
      if ((first == null) && create) {
        first = EpbsFactory.eINSTANCE.createConfigurationItem(NamingConstants.CreateEPBSArchCmd_configurationItem_name);
        ((ConfigurationItemPkg) getComponentPkg(architecture, true)).getOwnedConfigurationItems()
            .add((ConfigurationItem) first);
      }
    }

    return first;
  }

  /**
   * Returns whether the element use a default First Component name
   */
  public static boolean isDefaultNameFirstComponent(AbstractNamedElement element) {
    return NamingConstants.CreateSysAnalysisCmd_system_name.equals(element.getName())
        || NamingConstants.CreateLogicalArchCmd_logicalComponent_name.equals(element.getName())
        || NamingConstants.CreatePhysicalArchCmd_physicalComponent_name.equals(element.getName())
        || NamingConstants.CreateEPBSArchCmd_configurationItem_name.equals(element.getName());
  }

  /**
   * Return the system of the phase or create it if not exist
   * 
   * @param architecture
   * @return
   */
  public static Component getOrCreateSystem(BlockArchitecture architecture) {
    return getFirstComponent(architecture, true);
  }

  /**
   * Return true if the given component is contained by a block architecture
   * 
   * @param component
   * @return
   */
  public static boolean isRootComponent(Component component) {
    BlockArchitecture architecture = getRootBlockArchitecture(component);
    if (architecture != null) {
      return component.equals(architecture.getSystem());
    }
    return false;
  }

  /**
   * Retrieve Components from SystemAnslysis Layer + look for available modes and states
   * 
   * @param blockArch
   * @param availableElements
   */
  public static void getAllStatesAndModes(BlockArchitecture blockArch, List<CapellaElement> availableElements) {
    availableElements.addAll((List) EObjectExt.getAll(blockArch, CapellacommonPackage.Literals.STATE));
  }

  public static List<EObject> getAllStatesAndModes(BlockArchitecture blockArch) {
    List<EObject> availableElements = new ArrayList<EObject>();
    availableElements.addAll(EObjectExt.getAll(blockArch, CapellacommonPackage.Literals.STATE));
    return availableElements;
  }

  /**
   * Returns all defined components from the given architecture
   * 
   * @param blockArch
   * @return
   */
  public static Collection<Component> getAllComponents(BlockArchitecture blockArch) {
    Collection<Component> result = new ArrayList<>();
    if (null != blockArch) {
      for (EObject cp : EObjectExt.getAll(blockArch, CsPackage.Literals.COMPONENT)) {
        result.add((Component) cp);
      }
    }
    return result;
  }

  /**
   * Return the first {@link Component} except {@link ComponentContext}
   * 
   * @param architecture
   * @return List<Component>
   */
  public static List<Component> getFirstComponents(BlockArchitecture architecture) {
    return ComponentPkgExt.getSubDefinedComponents(getComponentPkg(architecture, false));
  }

  /**
   * Return only the previous architecture of given arch
   * 
   * @param anArchitecture
   *          an Architecture
   * @return return the previous architecture of anArchitecture is exist else return null
   */
  public static BlockArchitecture getPreviousBlockArchitecture(final BlockArchitecture anArchitecture) {
    List<BlockArchitecture> architectureList = new ArrayList<>();
    BlockArchitecture result = null;

    if (null != anArchitecture) {
      // functional way according to model
      architectureList.addAll(getPreviousBlockArchitectureByFunctinoalWay(anArchitecture));

      // consider classic way only if functional way result is void
      if (architectureList.isEmpty()) {
        // classic way (consider the level as OA,SA,LA,PA,EPBS)
        architectureList.addAll(getPreviousBlockArchitectureByClassicWay(anArchitecture));
      }
      if (!architectureList.isEmpty()) {
        result = architectureList.get(0);
      }
    }
    return result;
  }

  /**
   * Return Previous Arch using functional method (that is by realization links)
   * 
   * @param anArchitecture
   * @return
   */
  private static List<BlockArchitecture> getPreviousBlockArchitectureByFunctinoalWay(
      final BlockArchitecture anArchitecture) {
    List<BlockArchitecture> result = new ArrayList<>();
    if (anArchitecture instanceof SystemAnalysis) {
      SystemAnalysis sa = (SystemAnalysis) anArchitecture;
      EList<OperationalAnalysisRealization> allocatedOperationalAnalysisRealizations = sa
          .getAllocatedOperationalAnalysisRealizations();
      for (OperationalAnalysisRealization oaRealization : allocatedOperationalAnalysisRealizations) {
        addAllocatedArchitecture(result, oaRealization);
      }
    } else if (anArchitecture instanceof LogicalArchitecture) {
      LogicalArchitecture la = (LogicalArchitecture) anArchitecture;
      EList<SystemAnalysisRealization> saRealizations = la.getAllocatedSystemAnalysisRealizations();
      for (SystemAnalysisRealization saRealization : saRealizations) {
        addAllocatedArchitecture(result, saRealization);
      }
    } else if (anArchitecture instanceof PhysicalArchitecture) {
      PhysicalArchitecture pa = (PhysicalArchitecture) anArchitecture;
      EList<LogicalArchitectureRealization> laRealizations = pa.getAllocatedLogicalArchitectureRealizations();
      for (LogicalArchitectureRealization laRealization : laRealizations) {
        addAllocatedArchitecture(result, laRealization);
      }
    } else if (anArchitecture instanceof EPBSArchitecture) {
      EPBSArchitecture pa = (EPBSArchitecture) anArchitecture;
      EList<PhysicalArchitectureRealization> paRealizations = pa.getAllocatedPhysicalArchitectureRealizations();
      for (PhysicalArchitectureRealization paRealization : paRealizations) {
        addAllocatedArchitecture(result, paRealization);
      }
    }
    return result;
  }

  /**
   * Add allocated arch
   * 
   * @param returnedList
   * @param archAllocation
   */
  private static void addAllocatedArchitecture(List<BlockArchitecture> returnedList,
      ArchitectureAllocation archAllocation) {
    BlockArchitecture allocatedArch = archAllocation.getAllocatedArchitecture();
    if (null != allocatedArch) {
      returnedList.add(allocatedArch);
    }
  }

  /**
   * Return Previous Arch using classic method (that is considering level structure as OA,SA,LA,PA,EPBS)
   * 
   * @param anArchitecture
   * @return
   */
  private static List<BlockArchitecture> getPreviousBlockArchitectureByClassicWay(
      final BlockArchitecture anArchitecture) {
    List<BlockArchitecture> result = new ArrayList<>();
    if (null != anArchitecture) {
      SystemEngineering sysEng = SystemEngineeringExt.getSystemEngineering(anArchitecture);

      if (anArchitecture instanceof SystemAnalysis) {
        OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(sysEng);
        if (null != oa) {
          result.add(oa);
        }
      } else if (anArchitecture instanceof LogicalArchitecture) {
        SystemAnalysis sa = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
        if (null != sa) {
          result.add(sa);
        }
      } else if (anArchitecture instanceof PhysicalArchitecture) {
        LogicalArchitecture la = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);
        if (null != la) {
          result.add(la);
        }
      } else if (anArchitecture instanceof EPBSArchitecture) {
        PhysicalArchitecture pa = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
        if (null != pa) {
          result.add(pa);
        }
      }
    }

    return result;
  }

  /**
   * Gets all the {@link Missions} from SystemEng
   * 
   * @param element
   *          the {@link CapellaElement}
   * @return list of CapabilitySpecificationUseCase
   */
  public static List<AbstractCapability> getAllCapabilities(BlockArchitecture element) {
    List<AbstractCapability> result = new ArrayList<AbstractCapability>();
    if (null != element) {
      Set<EObject> capabilities = EObjectExt.getAll(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY);
      for (EObject obj : capabilities) {
        result.add((AbstractCapability) obj);
      }
    }
    return result;
  }

  /**
   * Gets all the {@link Missions} from SystemEng
   * 
   * @param element
   *          the {@link CapellaElement}
   * @return list of CapabilitySpecificationUseCase
   */
  public static List<Mission> getAllMissions(SystemAnalysis element) {
    List<Mission> result = new ArrayList<Mission>();
    if (null != element) {
      Set<EObject> missions = EObjectExt.getAll(element, CtxPackage.Literals.MISSION);
      for (EObject obj : missions) {
        result.add((Mission) obj);
      }
    }
    return result;
  }

  /**
   * Return all the constraint
   * 
   * @param anElement
   * @return
   */
  public static List<Constraint> getAllConstraints(EObject anElement) {
    List<Constraint> result = new ArrayList<>();
    if (null == anElement) {
      return result;
    }
    for (EObject obj : EObjectExt.getAll(anElement, CapellacorePackage.Literals.CONSTRAINT)) {
      if (obj instanceof Constraint) {
        result.add((Constraint) obj);
      }
    }
    return result;
  }

  public static List<Interface> getAllInterfaces(BlockArchitecture anElement) {
    List<Interface> result = new ArrayList<>();
    if (null == anElement) {
      return result;
    }
    for (EObject obj : EObjectExt.getAll(anElement, CsPackage.Literals.INTERFACE)) {
      if (obj instanceof Interface) {
        result.add((Interface) obj);
      }
    }
    return result;
  }

  public static Collection<Component> getRootComponents(BlockArchitecture element) {
    if (element instanceof OperationalAnalysis) {
      EntityPkg ownedEntityPkg = ((OperationalAnalysis) element).getOwnedEntityPkg();
      if (ownedEntityPkg != null && !ownedEntityPkg.getOwnedEntities().isEmpty()) {
        return ownedEntityPkg.getOwnedEntities().stream().filter(x -> !x.isActor()).collect(Collectors.toSet());
      }
    } else if (element instanceof SystemAnalysis) {
      SystemComponentPkg ownedSystemComponentPkg = ((SystemAnalysis) element).getOwnedSystemComponentPkg();
      if (ownedSystemComponentPkg != null && !ownedSystemComponentPkg.getOwnedSystemComponents().isEmpty()) {
        return ownedSystemComponentPkg.getOwnedSystemComponents().stream().filter(x -> !x.isActor())
            .collect(Collectors.toSet());
      }
    } else if (element instanceof LogicalArchitecture) {
      LogicalComponentPkg ownedLogicalComponentPkg = ((LogicalArchitecture) element).getOwnedLogicalComponentPkg();
      if (ownedLogicalComponentPkg != null && !ownedLogicalComponentPkg.getOwnedLogicalComponents().isEmpty()) {
        return ownedLogicalComponentPkg.getOwnedLogicalComponents().stream().filter(x -> !x.isActor())
            .collect(Collectors.toSet());
      }
    } else if (element instanceof PhysicalArchitecture) {
      PhysicalComponentPkg ownedPhysicalComponentPkg = ((PhysicalArchitecture) element).getOwnedPhysicalComponentPkg();
      if (ownedPhysicalComponentPkg != null && !ownedPhysicalComponentPkg.getOwnedPhysicalComponents().isEmpty()) {
        return ownedPhysicalComponentPkg.getOwnedPhysicalComponents().stream().filter(x -> !x.isActor())
            .collect(Collectors.toSet());
      }
    } else if (element instanceof EPBSArchitecture) {
      ConfigurationItemPkg ownedConfigurationItemPkg = ((EPBSArchitecture) element).getOwnedConfigurationItemPkg();
      if (ownedConfigurationItemPkg != null && !ownedConfigurationItemPkg.getOwnedConfigurationItems().isEmpty()) {
        return ownedConfigurationItemPkg.getOwnedConfigurationItems().stream().filter(x -> !x.isActor())
            .collect(Collectors.toSet());
      }
    }
    return null;
  }
}
