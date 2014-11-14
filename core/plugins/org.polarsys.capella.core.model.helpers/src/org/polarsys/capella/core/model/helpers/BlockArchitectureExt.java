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
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentContext;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemContext;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSContext;
import org.polarsys.capella.core.data.epbs.EpbsFactory;
import org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalContext;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.la.SystemAnalysisRealization;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalContext;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalContext;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.data.requirement.RequirementFactory;
import org.polarsys.capella.core.data.requirement.RequirementsPkg;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

/**
 */
public class BlockArchitectureExt {

  /**
   * Returns all architectures allocated by the architecture and also the given architecture
   */
  public static Collection<BlockArchitecture> getAllAllocatedArchitectures(BlockArchitecture architecture_p) {
    LinkedList<BlockArchitecture> listArchitectures = new LinkedList<BlockArchitecture>();
    Collection<BlockArchitecture> allocatedArchitectures = new HashSet<BlockArchitecture>();

    if (architecture_p != null) {
      listArchitectures.add(architecture_p);

      while (listArchitectures.size() > 0) {
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
   * Seems to return all exchanges items from architecture and previous architectures
   */
  public static List<ExchangeItem> getAllExchangeItems(BlockArchitecture architecture_p) {
    // TO-DO Reimplements this. From AbstractScenarioHelper.
    List<ExchangeItem> result = new ArrayList<ExchangeItem>();
    SystemEngineering se = SystemEngineeringExt.getSystemEngineering(architecture_p);
    TreeIterator<EObject> allContents = se.eAllContents();
    while (allContents.hasNext()) {
      EObject object = allContents.next();
      if (object instanceof ExchangeItem) {
        result.add((ExchangeItem) object);
      }
      // if the current object is a block architecture situated after the current architecture,
      // break the function, the job is done.
      if (object instanceof BlockArchitecture) {
        if (se.getOwnedArchitectures().indexOf(object) > se.getOwnedArchitectures().indexOf(architecture_p)) {
          return result;
        }
      }
    }
    return result;
  }

  public static List<ComponentExchange> getAllComponentExchanges(BlockArchitecture architecture_p) {
    List<ComponentExchange> instList = new ArrayList<ComponentExchange>();
    for (EObject obj : EObjectExt.getAll(architecture_p, FaPackage.Literals.COMPONENT_EXCHANGE)) {
      instList.add((ComponentExchange) obj);
    }
    return instList;
  }

  /**
   * @param context_p a Capella Element
   * @return the architecture that contains context_p, or null if context_p is not contained in any architecture
   */
  public static BlockArchitecture getRootBlockArchitecture(final EObject context_p) {
    EObject currentElement = context_p;
    while (currentElement != null) {
      if (currentElement instanceof BlockArchitecture) {
        return (BlockArchitecture) currentElement;
      }
      currentElement = currentElement.eContainer();
    }

    return null;
  }

  /**
   * @param anArchitecture_p an Architecture
   * @return all previous architectures of anArchitecture_p
   */
  public static List<BlockArchitecture> getPreviousBlockArchitectures(final BlockArchitecture anArchitecture_p) {
    List<BlockArchitecture> returnedList = new ArrayList<BlockArchitecture>();
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(anArchitecture_p);
    OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(sysEng);
    if ((oa != null) && anArchitecture_p.equals(oa)) {
      return returnedList;
    }
    if (oa != null) {
      returnedList.add(oa);
    }
    SystemAnalysis sa = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
    if ((sa != null) && anArchitecture_p.equals(sa)) {
      return returnedList;
    }
    if (sa != null) {
      returnedList.add(sa);
    }
    LogicalArchitecture la = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);
    if ((la != null) && anArchitecture_p.equals(la)) {
      return returnedList;
    }
    if (la != null) {
      returnedList.add(la);
    }
    PhysicalArchitecture pa = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
    if ((pa != null) && anArchitecture_p.equals(pa)) {
      return returnedList;
    }
    if (pa != null) {
      returnedList.add(pa);
    }

    return returnedList;
  }

  /**
   * @param context_p
   * @return the current architecture that contains context_p and and its previous architectures, or an empty list if context_p is not contained in any
   *         architecture
   */
  public static List<BlockArchitecture> getRootAndPreviousBlockArchitectures(final EObject context_p) {
    List<BlockArchitecture> returnedList = new ArrayList<BlockArchitecture>();
    BlockArchitecture root = getRootBlockArchitecture(context_p);
    if (root == null) {
      return returnedList;
    }
    returnedList.add(root);
    returnedList.addAll(getPreviousBlockArchitectures(root));
    return returnedList;
  }

  public static Structure getActorPkg(BlockArchitecture architecture_p) {
    return getActorPkg(architecture_p, true);

  }

  /**
   * Retrieve the actor pkg from the given architecture
   */
  public static Structure getActorPkg(BlockArchitecture architecture_p, boolean create_p) {

    if (architecture_p instanceof SystemAnalysis) {
      SystemAnalysis architecture = (SystemAnalysis) architecture_p;

      if ((architecture.getOwnedActorPkg() == null) && create_p) {
        // to externalize when constants in skeleton will be into helpers.
        ActorPkg pkg = CtxFactory.eINSTANCE.createActorPkg(NamingConstants.CreateSysAnalysisCmd_actors_pkg_name);
        architecture.setOwnedActorPkg(pkg);
      }
      return architecture.getOwnedActorPkg();

    } else if (architecture_p instanceof LogicalArchitecture) {
      LogicalArchitecture architecture = (LogicalArchitecture) architecture_p;

      if ((architecture.getOwnedLogicalActorPkg() == null) && create_p) {
        // to externalize when constants in skeleton will be into helpers.
        LogicalActorPkg pkg = LaFactory.eINSTANCE.createLogicalActorPkg(NamingConstants.CreateLogicalArchCmd_actors_pkg_name);
        architecture.setOwnedLogicalActorPkg(pkg);
      }
      return architecture.getOwnedLogicalActorPkg();

    } else if (architecture_p instanceof PhysicalArchitecture) {
      PhysicalArchitecture architecture = (PhysicalArchitecture) architecture_p;

      if ((architecture.getOwnedPhysicalActorPkg() == null) && create_p) {
        // to externalize when constants in skeleton will be into helpers.
        PhysicalActorPkg pkg = PaFactory.eINSTANCE.createPhysicalActorPkg(NamingConstants.CreatePhysicalArchCmd_actors_pkg_name);
        architecture.setOwnedPhysicalActorPkg(pkg);
      }
      return architecture.getOwnedPhysicalActorPkg();
    }

    return null;
  }

  /**
   * Retrieve the data pkg from the given architecture
   */
  public static DataPkg getDataPkg(BlockArchitecture architecture_p, boolean create_p) {
    if ((architecture_p.getOwnedDataPkg() == null) && create_p) {
      // to externalize when constants in skeleton will be into helpers.
      DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg(NamingConstants.CreateCommonCmd_data_pkg_name);
      architecture_p.setOwnedDataPkg(pkg);
    }
    return architecture_p.getOwnedDataPkg();
  }

  /**
   * Retrieve the data pkg from the given architecture
   */
  public static DataPkg getDataPkg(BlockArchitecture architecture_p) {
    return getDataPkg(architecture_p, true);
  }

  /**
   * Retrieve the Requirement pkg from the given architecture
   */
  public static Structure getRequirementsPkg(BlockArchitecture architecture_p, boolean create_p) {
    if ((architecture_p.getOwnedRequirementPkgs().size() == 0) && create_p) {
      // to externalize when constants in skeleton will be into helpers.
      RequirementsPkg pkg = RequirementFactory.eINSTANCE.createRequirementsPkg("Requirements"); //$NON-NLS-1$
      architecture_p.getOwnedRequirementPkgs().add(pkg);
    }
    if (architecture_p.getOwnedRequirementPkgs().isEmpty()) {
      return null;
    }
    return architecture_p.getOwnedRequirementPkgs().get(0);
  }

  /**
   * Retrieve the Requirement pkg from the given architecture
   */
  public static Structure getRequirementsPkg(BlockArchitecture architecture_p) {
    return getRequirementsPkg(architecture_p, true);
  }

  /**
   * Retrieve the capability pkg from the given architecture
   */
  public static AbstractCapabilityPkg getAbstractCapabilityPkg(BlockArchitecture architecture_p) {
    return getAbstractCapabilityPkg(architecture_p, true);
  }

  public static AbstractCapabilityPkg getAbstractCapabilityPkg(BlockArchitecture architecture_p, boolean create_p) {
    if (architecture_p.getOwnedAbstractCapabilityPkg() == null) {
      // to externalize when constants in skeleton will be into helpers.
      AbstractCapabilityPkg pkg = null;
      if (architecture_p instanceof OperationalAnalysis) {
        pkg = OaFactory.eINSTANCE.createOperationalCapabilityPkg(NamingConstants.CreateOpAnalysisCmd_operationalCapabilities_pkg_name);

      } else if (architecture_p instanceof SystemAnalysis) {
        pkg = CtxFactory.eINSTANCE.createCapabilityPkg(NamingConstants.CreateSysAnalysisCmd_capabilities_pkg_name);

      } else if (architecture_p instanceof LogicalArchitecture) {
        pkg = LaFactory.eINSTANCE.createCapabilityRealizationPkg(NamingConstants.CreateCommonCmd_capability_realisation_pkg_name);

      } else if (architecture_p instanceof PhysicalArchitecture) {
        pkg = LaFactory.eINSTANCE.createCapabilityRealizationPkg(NamingConstants.CreateCommonCmd_capability_realisation_pkg_name);

      } else if (architecture_p instanceof EPBSArchitecture) {
        pkg = LaFactory.eINSTANCE.createCapabilityRealizationPkg(NamingConstants.CreateCommonCmd_capability_realisation_pkg_name);
      }
      architecture_p.setOwnedAbstractCapabilityPkg(pkg);
    }
    return architecture_p.getOwnedAbstractCapabilityPkg();
  }

  /**
   * Retrieve the interface pkg from the given architecture
   * Create if not exists
   */
  public static InterfacePkg getInterfacePkg(BlockArchitecture architecture_p) {
    return getInterfacePkg(architecture_p, true);
  }

  /**
   * Retrieve the interface pkg from the given architecture
   */
  public static InterfacePkg getInterfacePkg(BlockArchitecture architecture_p, boolean create_p) {
    if ((architecture_p.getOwnedInterfacePkg() == null) && create_p) {
      // to externalize when constants in skeleton will be into helpers.
      InterfacePkg pkg = CsFactory.eINSTANCE.createInterfacePkg(NamingConstants.CreateCommonCmd_interfaces_pkg_name);
      architecture_p.setOwnedInterfacePkg(pkg);
    }
    return architecture_p.getOwnedInterfacePkg();
  }

  /**
   * Return the context related to the architecture
   * Create if not exist
   */
  public static Component getContext(BlockArchitecture architecture) {
    return getContext(architecture, true);
  }

  /**
   * Return the context related to the architecture
   * @param architecture Never null in normal phases
   * @return
   */
  public static Component getContext(BlockArchitecture architecture, boolean create_p) {

    Component context = null;

    if (architecture instanceof OperationalAnalysis) {
      context = ((OperationalAnalysis) architecture).getOwnedOperationalContext();
      if ((context == null) && create_p) {
        context = OaFactory.eINSTANCE.createOperationalContext(NamingConstants.CreateOpAnalysisCmd_operational_context_name);
        ((OperationalAnalysis) architecture).setOwnedOperationalContext((OperationalContext) context);
      }

    } else if (architecture instanceof SystemAnalysis) {
      context = ((SystemAnalysis) architecture).getOwnedSystemContext();
      if ((context == null) && create_p) {
        context = CtxFactory.eINSTANCE.createSystemContext(NamingConstants.CreateSysAnalysisCmd_system_context_name);
        ((SystemAnalysis) architecture).setOwnedSystemContext((SystemContext) context);
      }

    } else if (architecture instanceof LogicalArchitecture) {
      context = ((LogicalArchitecture) architecture).getOwnedLogicalContext();
      if ((context == null) && create_p) {
        context = LaFactory.eINSTANCE.createLogicalContext(NamingConstants.CreateLogicalArchCmd_logicalContext_name);
        ((LogicalArchitecture) architecture).setOwnedLogicalContext((LogicalContext) context);
      }

    } else if (architecture instanceof PhysicalArchitecture) {
      context = ((PhysicalArchitecture) architecture).getOwnedPhysicalContext();
      if ((context == null) && create_p) {
        context = PaFactory.eINSTANCE.createPhysicalContext(NamingConstants.CreatePhysicalArchCmd_physicalContext_name);
        ((PhysicalArchitecture) architecture).setOwnedPhysicalContext((PhysicalContext) context);
      }

    } else if (architecture instanceof EPBSArchitecture) {
      context = ((EPBSArchitecture) architecture).getOwnedEPBSContext();
      if ((context == null) && create_p) {
        context = EpbsFactory.eINSTANCE.createEPBSContext(NamingConstants.CreateEPBSArchCmd_epbsContext_name);
        ((EPBSArchitecture) architecture).setOwnedEPBSContext((EPBSContext) context);
      }
    }

    return context;
  }

  /**
   * Retrieve the data pkg from the given architecture
   */
  public static FunctionPkg getFunctionPkg(BlockArchitecture architecture_p) {
    return getFunctionPkg(architecture_p, true);
  }

  public static FunctionPkg getFunctionPkg(BlockArchitecture architecture_p, boolean create_p) {
    if ((architecture_p.getOwnedFunctionPkg() == null) && create_p) {
      FunctionPkg pkg = null;

      // to externalize when constants in skeleton will be into helpers.
      if (architecture_p instanceof OperationalAnalysis) {
        pkg = OaFactory.eINSTANCE.createOperationalActivityPkg(NamingConstants.CreateOpAnalysisCmd_operationalActivities_pkg_name);

      } else if (architecture_p instanceof SystemAnalysis) {
        pkg = CtxFactory.eINSTANCE.createSystemFunctionPkg(NamingConstants.CreateSysAnalysisCmd_system_functions_pkg_name);

      } else if (architecture_p instanceof LogicalArchitecture) {
        pkg = LaFactory.eINSTANCE.createLogicalFunctionPkg(NamingConstants.CreateLogicalArchCmd_logicalFunctions_pkg_name);

      } else if (architecture_p instanceof PhysicalArchitecture) {
        pkg = PaFactory.eINSTANCE.createPhysicalFunctionPkg(NamingConstants.CreatePhysicalArchCmd_physicalFunctions_pkg_name);
      }

      architecture_p.setOwnedFunctionPkg(pkg);
    }
    return architecture_p.getOwnedFunctionPkg();
  }

  /**
   * Retrieve the root function from the given architecture
   */
  public static AbstractFunction getRootFunction(BlockArchitecture architecture_p) {
    return getRootFunction(architecture_p, true);
  }

  public static AbstractFunction getRootFunction(BlockArchitecture architecture_p, boolean create_p) {

    AbstractFunction function = null;

    if (architecture_p != null) {

      FunctionPkg pkg = getFunctionPkg(architecture_p, create_p);
      if (pkg == null) {
        return function;
      }

      // to externalize when constants in skeleton will be into helpers.
      if (architecture_p instanceof OperationalAnalysis) {
        OperationalActivityPkg spkg = ((OperationalActivityPkg) pkg);
        if (!spkg.getOwnedOperationalActivities().isEmpty()) {
          function = spkg.getOwnedOperationalActivities().get(0);
        } else if (create_p) {
          function = OaFactory.eINSTANCE.createOperationalActivity(NamingConstants.CreateOpAnalysisCmd_operationalActivity_root_name);
          spkg.getOwnedOperationalActivities().add((OperationalActivity) function);
        }

      } else if (architecture_p instanceof SystemAnalysis) {
        SystemFunctionPkg spkg = ((SystemFunctionPkg) pkg);
        if (!spkg.getOwnedSystemFunctions().isEmpty()) {
          function = spkg.getOwnedSystemFunctions().get(0);
        } else if (create_p) {
          function = CtxFactory.eINSTANCE.createSystemFunction(NamingConstants.CreateSysAnalysisCmd_system_function_root_name);
          spkg.getOwnedSystemFunctions().add((SystemFunction) function);
        }

      } else if (architecture_p instanceof LogicalArchitecture) {
        LogicalFunctionPkg spkg = ((LogicalFunctionPkg) pkg);
        if (!spkg.getOwnedLogicalFunctions().isEmpty()) {
          function = spkg.getOwnedLogicalFunctions().get(0);
        } else if (create_p) {
          function = LaFactory.eINSTANCE.createLogicalFunction(NamingConstants.CreateLogicalArchCmd_logicalFunction_root_name);
          spkg.getOwnedLogicalFunctions().add((LogicalFunction) function);
        }

      } else if (architecture_p instanceof PhysicalArchitecture) {
        PhysicalFunctionPkg spkg = ((PhysicalFunctionPkg) pkg);
        if (!spkg.getOwnedPhysicalFunctions().isEmpty()) {
          function = spkg.getOwnedPhysicalFunctions().get(0);
        } else if (create_p) {
          function = PaFactory.eINSTANCE.createPhysicalFunction(NamingConstants.CreatePhysicalArchCmd_physicalFunction_root_name);
          spkg.getOwnedPhysicalFunctions().add((PhysicalFunction) function);
        }
      }
    }

    return function;
  }

  /**
   * Return the main component of the phase
   * @param parent_p
   * @return
   */
  public static Component getFirstComponent(ModellingArchitecture architecture, boolean create_p) {
    Component first = null;

    if (architecture instanceof SystemAnalysis) {
      first = ((SystemAnalysis) architecture).getOwnedSystem();
      if ((first == null) && create_p) {
        first = CtxFactory.eINSTANCE.createSystem(NamingConstants.CreateSysAnalysisCmd_system_name);
        ((SystemAnalysis) architecture).setOwnedSystem((System) first);
      }

    } else if (architecture instanceof LogicalArchitecture) {
      first = ((LogicalArchitecture) architecture).getOwnedLogicalComponent();
      if ((first == null) && create_p) {
        first = LaFactory.eINSTANCE.createLogicalComponent(NamingConstants.CreateLogicalArchCmd_logicalComponent_name);
        ((LogicalArchitecture) architecture).setOwnedLogicalComponent((LogicalComponent) first);
      }

    } else if (architecture instanceof PhysicalArchitecture) {
      first = ((PhysicalArchitecture) architecture).getOwnedPhysicalComponent();
      if ((first == null) && create_p) {
        first = PaFactory.eINSTANCE.createPhysicalComponent(NamingConstants.CreatePhysicalArchCmd_physicalComponent_name);
        ((PhysicalArchitecture) architecture).setOwnedPhysicalComponent((PhysicalComponent) first);
      }

    } else if (architecture instanceof EPBSArchitecture) {
      first = ((EPBSArchitecture) architecture).getOwnedConfigurationItem();
      if ((first == null) && create_p) {
        first = EpbsFactory.eINSTANCE.createConfigurationItem(NamingConstants.CreateEPBSArchCmd_configurationItem_name);
        ((EPBSArchitecture) architecture).setOwnedConfigurationItem((ConfigurationItem) first);
      }
    }

    return first;
  }

  /**
   * Return the main component of the phase
   * @param parent_p
   * @return
   */
  public static Component getFirstComponent(ModellingArchitecture architecture) {
    return getFirstComponent(architecture, true);
  }

  /**
   * Return true if the given component is contained by a block architecture
   * @param component_p
   * @return
   */
  public static boolean isRootComponent(Component component_p) {
	  return component_p.eContainer() instanceof BlockArchitecture;
  }

  /**
   * Retrieve Components from BlockArchitecture Layer + look for available modes and states
   * @param ele_p
   * @param blockArch_p
   * @param availableElements_p
   */
  public static void getAllStatesAndModesFromBlockArchitecture(BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
    if (null != blockArch_p) {
      if (blockArch_p instanceof OperationalAnalysis) {
        getAllStatesAndModesFromOperationalAnalysisLayer(blockArch_p, availableElements_p);
      } else if (blockArch_p instanceof SystemAnalysis) {
        getAllStatesAndModesFromSystemAnalysisLayer(blockArch_p, availableElements_p);
      } else if (blockArch_p instanceof LogicalArchitecture) {
        getAllStatesAndModesFromLogicalLayer(blockArch_p, availableElements_p);
      } else if (blockArch_p instanceof PhysicalArchitecture) {
        getAllStatesAndModesFromPhysicalLayer(blockArch_p, availableElements_p);
      } else if (blockArch_p instanceof EPBSArchitecture) {
        getAllStatesAndModesFromEPBSLayer(blockArch_p, availableElements_p);
      }
    }
  }

  /**
   * Retrieve Components from OperationalAnalysis Layer + look for available modes and states
   * @param ele_p
   * @param blockArch_p
   * @param availableElements_p
   */
  public static void getAllStatesAndModesFromOperationalAnalysisLayer(BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
    OperationalAnalysis oa = (OperationalAnalysis) blockArch_p;
    // Entities
    EntityPkg ownedEntityPkg = oa.getOwnedEntityPkg();
    List<Entity> allEntity = OperationalAnalysisExt.getAllEntity(ownedEntityPkg);
    for (Entity entity : allEntity) {
      availableElements_p.addAll(ComponentExt.getAllStatesAndModesFromComponent(entity));
    }
  }

  /**
   * Retrieve Components from SystemAnslysis Layer + look for available modes and states
   * @param ele_p
   * @param blockArch_p
   * @param availableElements_p
   */
  public static void getAllStatesAndModesFromSystemAnalysisLayer(BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
    SystemAnalysis ca = (SystemAnalysis) blockArch_p;
    // System Context
    SystemContext ownedSystemContext = ca.getOwnedSystemContext();
    availableElements_p.addAll(ComponentExt.getAllStatesAndModesFromComponent(ownedSystemContext));
    // System
    System ownedSystem = ca.getOwnedSystem();
    availableElements_p.addAll(ComponentExt.getAllStatesAndModesFromComponent(ownedSystem));
    // Actors
    ActorPkg ownedActorPkg = ca.getOwnedActorPkg();
    List<Actor> allActors = ActorPkgExt.getAllActors(ownedActorPkg);
    for (Actor actor : allActors) {
      availableElements_p.addAll(ComponentExt.getAllStatesAndModesFromComponent(actor));
    }
  }

  /**
   * Retrieve Components from Logical layer + look for available modes and states
   * @param ele_p
   * @param blockArch_p
   * @param availableElements_p
   */
  public static void getAllStatesAndModesFromLogicalLayer(BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
    LogicalArchitecture logArch = (LogicalArchitecture) blockArch_p;
    // LogicalContext
    LogicalContext ownedLogicalContext = logArch.getOwnedLogicalContext();
    availableElements_p.addAll(ComponentExt.getAllStatesAndModesFromComponent(ownedLogicalContext));
    // LogicalComponent
    List<LogicalComponent> allLCsFromLogicalArchitectureLayer = LogicalArchitectureExt.getAllLCsFromLogicalArchitectureLayer(logArch);
    for (LogicalComponent logicalComponent : allLCsFromLogicalArchitectureLayer) {
      availableElements_p.addAll(ComponentExt.getAllStatesAndModesFromComponent(logicalComponent));
    }
    // LogicalActor
    List<LogicalActor> allLAsFromLAPkg = LogicalActorPkgExt.getAllLAsFromLAPkg(logArch.getOwnedLogicalActorPkg());
    for (LogicalActor logicalActor : allLAsFromLAPkg) {
      availableElements_p.addAll(ComponentExt.getAllStatesAndModesFromComponent(logicalActor));
    }
  }

  /**
   * Retrieve Components from Physical layer + look for available modes and states
   * @param ele_p
   * @param blockArch_p
   * @param availableElements_p
   */
  public static void getAllStatesAndModesFromPhysicalLayer(BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
    PhysicalArchitecture phyArch = (PhysicalArchitecture) blockArch_p;
    // PhysicalContext
    PhysicalContext ownedPhysicalContext = phyArch.getOwnedPhysicalContext();
    availableElements_p.addAll(ComponentExt.getAllStatesAndModesFromComponent(ownedPhysicalContext));
    // PhysicalComponent
    List<PhysicalComponent> allPhysicalComponents = PhysicalArchitectureExt.getAllPhysicalComponents(phyArch);
    for (PhysicalComponent physicalComponent : allPhysicalComponents) {
      availableElements_p.addAll(ComponentExt.getAllStatesAndModesFromComponent(physicalComponent));
    }
    // LogicalActor
    List<PhysicalActor> allPAsFromPAPkg = PhysicalActorPkgExt.getAllPAsFromPAPkg((phyArch.getOwnedPhysicalActorPkg()));
    for (PhysicalActor physicalActor : allPAsFromPAPkg) {
      availableElements_p.addAll(ComponentExt.getAllStatesAndModesFromComponent(physicalActor));
    }
  }

  /**
   * Retrieve Components form EPBS Layer + look for available modes and states
   * @param ele_p a model element
   * @param blockArch_p
   * @param availableElements_p
   */
  public static void getAllStatesAndModesFromEPBSLayer(BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
    EPBSArchitecture epbsArch = (EPBSArchitecture) blockArch_p;
    // EPBSContext
    EPBSContext ownedEPBSContext = epbsArch.getOwnedEPBSContext();
    availableElements_p.addAll(ComponentExt.getAllStatesAndModesFromComponent(ownedEPBSContext));
    // Configuration Item
    List<ConfigurationItem> allConfigurationItems = SystemEngineeringExt.getAllConfigurationItems(epbsArch);
    for (ConfigurationItem configurationItem : allConfigurationItems) {
      availableElements_p.addAll(ComponentExt.getAllStatesAndModesFromComponent(configurationItem));
    }
  }

  /**
   * Returns all defined components from the given architecture
   * @param blockArch_p
   * @return
   */
  public static Collection<Component> getAllComponents(BlockArchitecture blockArch_p) {
    Collection<Component> result = new ArrayList<Component>();
    if (null != blockArch_p) {
      Set<EObject> epbsSet = EObjectExt.getAll(blockArch_p, CsPackage.Literals.COMPONENT);
      for (EObject cp : epbsSet) {
        if (cp instanceof Component) {
          result.add((Component) cp);
        }
      }
    }
    return result;
  }

  /**
   * Get all components from BlockArchitecture
   * @param blockArch_p
   * @param availableElements_p
   */
  public static void getAllComponentsFromBlockArchitecture(BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
    if (null != blockArch_p) {
      if (blockArch_p instanceof OperationalAnalysis) {
        getAllComponentsFromOA(blockArch_p, availableElements_p);
      } else if (blockArch_p instanceof SystemAnalysis) {
        getAllComponentsFromSA(blockArch_p, availableElements_p);
      } else if (blockArch_p instanceof LogicalArchitecture) {
        getAllComponentsFromLA(blockArch_p, availableElements_p);
      } else if (blockArch_p instanceof PhysicalArchitecture) {
        getAllComponentsFromPA(blockArch_p, availableElements_p);
      } else if (blockArch_p instanceof EPBSArchitecture) {
        getAllComponentsFromEPBS(blockArch_p, availableElements_p);
      }
    }
  }

  /**
   * Get all components from OperationalAnalysis
   * @param blockArch_p
   * @param availableElements_p
   */
  public static void getAllComponentsFromOA(BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
    if ((null != blockArch_p) && (blockArch_p instanceof OperationalAnalysis)) {
      OperationalAnalysis arch = (OperationalAnalysis) blockArch_p;
      // OperationalContext
      OperationalContext context = arch.getOwnedOperationalContext();
      if (null != context) {
        availableElements_p.add(context);
      }
      // Entity
      availableElements_p.addAll(OperationalAnalysisExt.getAllEntity(arch));
    }
  }

  /**
   * Get all components from SystemAnalysis
   * @param blockArch_p
   * @param availableElements_p
   */
  public static void getAllComponentsFromSA(BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
    if ((null != blockArch_p) && (blockArch_p instanceof SystemAnalysis)) {
      SystemAnalysis ca = (SystemAnalysis) blockArch_p;
      // System Context
      SystemContext ownedSystemContext = ca.getOwnedSystemContext();
      if (null != ownedSystemContext) {
        availableElements_p.add(ownedSystemContext);
      }
      // System
      System ownedSystem = ca.getOwnedSystem();
      if (null != ownedSystem) {
        availableElements_p.add(ownedSystem);
      }
      // Actors
      ActorPkg ownedActorPkg = ca.getOwnedActorPkg();
      availableElements_p.addAll(ActorPkgExt.getAllActors(ownedActorPkg));
    }
  }

  /**
   * Get all components from LogicalArchitecture
   * @param blockArch_p
   * @param availableElements_p
   */
  public static void getAllComponentsFromLA(BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
    if ((null != blockArch_p) && (blockArch_p instanceof LogicalArchitecture)) {
      LogicalArchitecture logArch = (LogicalArchitecture) blockArch_p;
      // LogicalContext
      LogicalContext ownedLogicalContext = logArch.getOwnedLogicalContext();
      if (null != ownedLogicalContext) {
        availableElements_p.add(ownedLogicalContext);
      }
      // LogicalComponent
      availableElements_p.addAll(LogicalArchitectureExt.getAllLCsFromLogicalArchitectureLayer(logArch));
      // LogicalActor
      availableElements_p.addAll(LogicalActorPkgExt.getAllLAsFromLAPkg(logArch.getOwnedLogicalActorPkg()));
    }
  }

  /**
   * Get all components from PhysicalArchitecture
   * @param blockArch_p
   * @param availableElements_p
   */
  public static void getAllComponentsFromPA(BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
    if ((null != blockArch_p) && (blockArch_p instanceof PhysicalArchitecture)) {
      PhysicalArchitecture phyArch = (PhysicalArchitecture) blockArch_p;
      // PhysicalContext
      PhysicalContext ownedPhysicalContext = phyArch.getOwnedPhysicalContext();
      if (null != ownedPhysicalContext) {
        availableElements_p.add(ownedPhysicalContext);
      }
      // PhysicalComponent
      availableElements_p.addAll(PhysicalArchitectureExt.getAllPhysicalComponents(phyArch));
      // LogicalActor
      availableElements_p.addAll(PhysicalActorPkgExt.getAllPAsFromPAPkg((phyArch.getOwnedPhysicalActorPkg())));
    }
  }

  /**
   * Get all components from EPBSArchitecture
   * @param blockArch_p
   * @param availableElements_p
   */
  public static void getAllComponentsFromEPBS(BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
    if ((null != blockArch_p) && (blockArch_p instanceof EPBSArchitecture)) {
      EPBSArchitecture epbsArch = (EPBSArchitecture) blockArch_p;
      // EPBSContext
      EPBSContext ownedEPBSContext = epbsArch.getOwnedEPBSContext();
      if (null != ownedEPBSContext) {
        availableElements_p.add(ownedEPBSContext);
      }
      // Configuration Item
      availableElements_p.addAll(SystemEngineeringExt.getAllConfigurationItems(epbsArch));
    }
  }

  /**
   * Return the first {@link Component} except {@link ComponentContext}
   * @param architecture
   * @return List<Component>
   */
  public static List<Component> getFirstComponents(ModellingArchitecture architecture) {
    List<Component> result = new ArrayList<Component>(1);

    if (architecture instanceof OperationalAnalysis) {
      OperationalAnalysis archi = (OperationalAnalysis) architecture;
      // Entity
      EntityPkg ownedEntityPkg = archi.getOwnedEntityPkg();
      result.addAll(ownedEntityPkg.getOwnedEntities());

    }
    if (architecture instanceof SystemAnalysis) {
      SystemAnalysis arch = (SystemAnalysis) architecture;
      // System
      System ownedSystem = arch.getOwnedSystem();
      if (null != ownedSystem) {
        result.add(ownedSystem);
      }
      // System Actor
      ActorPkg ownedActorPkg = arch.getOwnedActorPkg();
      if (null != ownedActorPkg) {
        result.addAll(ownedActorPkg.getOwnedActors());
      }

    } else if (architecture instanceof LogicalArchitecture) {
      LogicalArchitecture arch = (LogicalArchitecture) architecture;
      // LogicalComponent
      LogicalComponent lc = arch.getOwnedLogicalComponent();
      if (null != lc) {
        result.add(lc);
      }
      // Logical Actor
      LogicalActorPkg ownedActorPkg = arch.getOwnedLogicalActorPkg();
      if (null != ownedActorPkg) {
        result.addAll(ownedActorPkg.getOwnedLogicalActors());
      }

    } else if (architecture instanceof PhysicalArchitecture) {
      PhysicalArchitecture arch = (PhysicalArchitecture) architecture;
      // LogicalComponent
      PhysicalComponent pc = arch.getOwnedPhysicalComponent();
      if (null != pc) {
        result.add(pc);
      }
      // Logical Actor
      PhysicalActorPkg ownedActorPkg = arch.getOwnedPhysicalActorPkg();
      if (null != ownedActorPkg) {
        result.addAll(ownedActorPkg.getOwnedPhysicalActors());
      }

    } else if (architecture instanceof EPBSArchitecture) {
      EPBSArchitecture arch = (EPBSArchitecture) architecture;
      // LogicalComponent
      ConfigurationItem ci = arch.getOwnedConfigurationItem();
      if (null != ci) {
        result.add(ci);
      }

    }

    return result;
  }

  /**
   * Return only the previous architecture of given arch
   * @param anArchitecture_p an Architecture
   * @return return only the previous architecture of anArchitecture_p
   */
  public static List<BlockArchitecture> getPreviousBlockArchitecture(final BlockArchitecture anArchitecture_p) {
    List<BlockArchitecture> result = new ArrayList<BlockArchitecture>();

    if (null != anArchitecture_p) {
      // oa does not have any previous arch
      if (anArchitecture_p instanceof OperationalAnalysis) {
        return result;
      }

      // functional way according to model
      result.addAll(getPreviousBlockArchitectureByFunctinoalWay(anArchitecture_p));

      // consider classic way only if functional way result is void
      if (result.isEmpty()) {
        // classic way (consider the level as OA,SA,LA,PA,EPBS)
        result.addAll(getPreviousBlockArchitectureByClassicWay(anArchitecture_p));
      }
    }
    return result;
  }

  /**
   * Return Previous Arch using functional method (that is by realization links)
   * @param anArchitecture_p
   * @return
   */
  public static List<BlockArchitecture> getPreviousBlockArchitectureByFunctinoalWay(final BlockArchitecture anArchitecture_p) {
    List<BlockArchitecture> result = new ArrayList<BlockArchitecture>();
    if (anArchitecture_p instanceof SystemAnalysis) {
      SystemAnalysis sa = (SystemAnalysis) anArchitecture_p;
      EList<OperationalAnalysisRealization> allocatedOperationalAnalysisRealizations = sa.getAllocatedOperationalAnalysisRealizations();
      for (OperationalAnalysisRealization oaRealization : allocatedOperationalAnalysisRealizations) {
        addAllocatedArchitecture(result, oaRealization);
      }
    } else if (anArchitecture_p instanceof LogicalArchitecture) {
      LogicalArchitecture la = (LogicalArchitecture) anArchitecture_p;
      EList<SystemAnalysisRealization> saRealizations = la.getAllocatedSystemAnalysisRealizations();
      for (SystemAnalysisRealization saRealization : saRealizations) {
        addAllocatedArchitecture(result, saRealization);
      }
    } else if (anArchitecture_p instanceof PhysicalArchitecture) {
      PhysicalArchitecture pa = (PhysicalArchitecture) anArchitecture_p;
      EList<LogicalArchitectureRealization> laRealizations = pa.getAllocatedLogicalArchitectureRealizations();
      for (LogicalArchitectureRealization laRealization : laRealizations) {
        addAllocatedArchitecture(result, laRealization);
      }
    } else if (anArchitecture_p instanceof EPBSArchitecture) {
      EPBSArchitecture pa = (EPBSArchitecture) anArchitecture_p;
      EList<PhysicalArchitectureRealization> paRealizations = pa.getAllocatedPhysicalArchitectureRealizations();
      for (PhysicalArchitectureRealization paRealization : paRealizations) {
        addAllocatedArchitecture(result, paRealization);
      }
    }
    return result;
  }

  /**
   * Add allocated arch
   * @param returnedList
   * @param archAllocation
   */
  private static void addAllocatedArchitecture(List<BlockArchitecture> returnedList, ArchitectureAllocation archAllocation) {
    BlockArchitecture allocatedArch = archAllocation.getAllocatedArchitecture();
    if (null != allocatedArch) {
      returnedList.add(allocatedArch);
    }
  }

  /**
   * Return Previous Arch using classic method (that is considering level structure as OA,SA,LA,PA,EPBS)
   * @param anArchitecture_p
   * @return
   */
  public static List<BlockArchitecture> getPreviousBlockArchitectureByClassicWay(final BlockArchitecture anArchitecture_p) {
    List<BlockArchitecture> result = new ArrayList<BlockArchitecture>();
    if (null != anArchitecture_p) {
      SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(anArchitecture_p);

      if (anArchitecture_p instanceof SystemAnalysis) {
        OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(sysEng);
        if (null != oa) {
          result.add(oa);
        }
      } else if (anArchitecture_p instanceof LogicalArchitecture) {
        SystemAnalysis sa = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
        if (null != sa) {
          result.add(sa);
        }
      } else if (anArchitecture_p instanceof PhysicalArchitecture) {
        LogicalArchitecture la = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);
        if (null != la) {
          result.add(la);
        }
      } else if (anArchitecture_p instanceof EPBSArchitecture) {
        PhysicalArchitecture pa = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
        if (null != pa) {
          result.add(pa);
        }
      }
    }

    return result;
  }

  /**
   * Return all the constraint
   * @param anElement_p
   * @return
   */
  public static List<EObject> getAllConstraints(EObject anElement_p) {
    List<EObject> result = new ArrayList<EObject>();
    if (null == anElement_p) {
      return result;
    }
    for (EObject obj : EObjectExt.getAll(anElement_p, CapellacorePackage.Literals.CONSTRAINT)) {
      result.add(obj);
    }
    return result;
  }
}
