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

package org.polarsys.capella.core.model.helpers;
import static org.polarsys.capella.core.model.helpers.ModelHelpers.ComponentExt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
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
import org.polarsys.capella.core.data.ctx.CtxPackage;
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
import org.polarsys.capella.core.data.epbs.EpbsPackage;
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
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalContext;
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
import org.polarsys.capella.core.data.oa.OperationalContext;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PaPackage;
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

  private BlockArchitectureExt() {
    // To hide the implicit public one.
  }

	public enum Type {OA, SA, LA, PA, EPBS}
	public enum FunctionType {SYSTEM_FUNCTION, DUPLICATE, GATHER, ROUTE, SELECT, SPLIT};
	public enum FunctionPortType {IN_FUNCTION_PORT, OUT_FUNCTION_PORT};
	public enum ComponentPortType {IN_FLOW_PORT, OUT_FLOW_PORT, IN_OUT_FLOW_PORT, STANDARD_PORT, PHYSICAL_PORT};
	public enum LinkDirection {SOURCE, TARGET};
	public enum ConfigurationItemType {COTS, CS, HW, INTERFACE, NDI, PRIME_ITEM, SYSTEM};
	
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
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(anArchitecture);
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
  public static Structure getActorPkg(BlockArchitecture blockArchitecture1, boolean create) {

    if (blockArchitecture1 instanceof SystemAnalysis) {
      SystemAnalysis architecture = (SystemAnalysis) blockArchitecture1;

      if ((architecture.getOwnedActorPkg() == null) && create) {
        // to externalize when constants in skeleton will be into helpers.
        ActorPkg pkg = CtxFactory.eINSTANCE.createActorPkg(NamingConstants.CreateSysAnalysisCmd_actors_pkg_name);
        architecture.setOwnedActorPkg(pkg);
      }
      return architecture.getOwnedActorPkg();

    } else if (blockArchitecture1 instanceof LogicalArchitecture) {
      LogicalArchitecture architecture = (LogicalArchitecture) blockArchitecture1;

      if ((architecture.getOwnedLogicalActorPkg() == null) && create) {
        // to externalize when constants in skeleton will be into helpers.
        LogicalActorPkg pkg = LaFactory.eINSTANCE
            .createLogicalActorPkg(NamingConstants.CreateLogicalArchCmd_actors_pkg_name);
        architecture.setOwnedLogicalActorPkg(pkg);
      }
      return architecture.getOwnedLogicalActorPkg();

    } else if (blockArchitecture1 instanceof PhysicalArchitecture) {
      PhysicalArchitecture architecture = (PhysicalArchitecture) blockArchitecture1;

      if ((architecture.getOwnedPhysicalActorPkg() == null) && create) {
        // to externalize when constants in skeleton will be into helpers.
        PhysicalActorPkg pkg = PaFactory.eINSTANCE
            .createPhysicalActorPkg(NamingConstants.CreatePhysicalArchCmd_actors_pkg_name);
        architecture.setOwnedPhysicalActorPkg(pkg);
      }
      return architecture.getOwnedPhysicalActorPkg();
    }

    return null;
  }

  /**
   * Returns whether the element use a default ActorPkg name
   */
  public static boolean isDefaultNameActorPkg(AbstractNamedElement pkg) {
    return NamingConstants.CreateSysAnalysisCmd_actors_pkg_name.equals(pkg.getName())
        || NamingConstants.CreateLogicalArchCmd_actors_pkg_name.equals(pkg.getName())
        || NamingConstants.CreatePhysicalArchCmd_actors_pkg_name.equals(pkg.getName());
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
   * Retrieve the Requirement pkg from the given architecture
   */
  public static Structure getRequirementsPkg(BlockArchitecture architecture, boolean create) {
    if (architecture.getOwnedRequirementPkgs().isEmpty() && create) {
      // to externalize when constants in skeleton will be into helpers.
      RequirementsPkg pkg = RequirementFactory.eINSTANCE
          .createRequirementsPkg(NamingConstants.CreateCommonCmd_requirements_pkg_name); // $NON-NLS-1$
      architecture.getOwnedRequirementPkgs().add(pkg);
    }
    if (architecture.getOwnedRequirementPkgs().isEmpty()) {
      return null;
    }
    return architecture.getOwnedRequirementPkgs().get(0);
  }

  /**
   * Returns whether the element use a default RequirementPkg name
   */
  public static boolean isDefaultNameRequirementsPkg(AbstractNamedElement pkg) {
    return NamingConstants.CreateCommonCmd_requirements_pkg_name.equals(pkg.getName());
  }

  /**
   * Retrieve the Requirement pkg from the given architecture
   */
  public static Structure getRequirementsPkg(BlockArchitecture architecture) {
    return getRequirementsPkg(architecture, true);
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
  public static Component getContext(BlockArchitecture architecture) {
    return getContext(architecture, true);
  }

  /**
   * Return the context related to the architecture
   * 
   * @param architecture
   *          Never null in normal phases
   * @return
   */
  public static Component getContext(BlockArchitecture architecture, boolean create) {

    Component context = null;

    if (architecture instanceof OperationalAnalysis) {
      context = ((OperationalAnalysis) architecture).getOwnedOperationalContext();
      if ((context == null) && create) {
        context = OaFactory.eINSTANCE
            .createOperationalContext(NamingConstants.CreateOpAnalysisCmd_operational_context_name);
        ((OperationalAnalysis) architecture).setOwnedOperationalContext((OperationalContext) context);
      }

    } else if (architecture instanceof SystemAnalysis) {
      context = ((SystemAnalysis) architecture).getOwnedSystemContext();
      if ((context == null) && create) {
        context = CtxFactory.eINSTANCE.createSystemContext(NamingConstants.CreateSysAnalysisCmd_system_context_name);
        ((SystemAnalysis) architecture).setOwnedSystemContext((SystemContext) context);
      }

    } else if (architecture instanceof LogicalArchitecture) {
      context = ((LogicalArchitecture) architecture).getOwnedLogicalContext();
      if ((context == null) && create) {
        context = LaFactory.eINSTANCE.createLogicalContext(NamingConstants.CreateLogicalArchCmd_logicalContext_name);
        ((LogicalArchitecture) architecture).setOwnedLogicalContext((LogicalContext) context);
      }

    } else if (architecture instanceof PhysicalArchitecture) {
      context = ((PhysicalArchitecture) architecture).getOwnedPhysicalContext();
      if ((context == null) && create) {
        context = PaFactory.eINSTANCE.createPhysicalContext(NamingConstants.CreatePhysicalArchCmd_physicalContext_name);
        ((PhysicalArchitecture) architecture).setOwnedPhysicalContext((PhysicalContext) context);
      }

    } else if (architecture instanceof EPBSArchitecture) {
      context = ((EPBSArchitecture) architecture).getOwnedEPBSContext();
      if ((context == null) && create) {
        context = EpbsFactory.eINSTANCE.createEPBSContext(NamingConstants.CreateEPBSArchCmd_epbsContext_name);
        ((EPBSArchitecture) architecture).setOwnedEPBSContext((EPBSContext) context);
      }
    }

    return context;
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
   */
  public static Component getFirstComponent(ModellingArchitecture architecture, boolean create) {
    Component first = null;

    if (architecture instanceof SystemAnalysis) {
      first = ((SystemAnalysis) architecture).getOwnedSystem();
      if ((first == null) && create) {
        first = CtxFactory.eINSTANCE.createSystem(NamingConstants.CreateSysAnalysisCmd_system_name);
        ((SystemAnalysis) architecture).setOwnedSystem((System) first);
      }

    } else if (architecture instanceof LogicalArchitecture) {
      first = ((LogicalArchitecture) architecture).getOwnedLogicalComponent();
      if ((first == null) && create) {
        first = LaFactory.eINSTANCE.createLogicalComponent(NamingConstants.CreateLogicalArchCmd_logicalComponent_name);
        ((LogicalArchitecture) architecture).setOwnedLogicalComponent((LogicalComponent) first);
      }

    } else if (architecture instanceof PhysicalArchitecture) {
      first = ((PhysicalArchitecture) architecture).getOwnedPhysicalComponent();
      if ((first == null) && create) {
        first = PaFactory.eINSTANCE
            .createPhysicalComponent(NamingConstants.CreatePhysicalArchCmd_physicalComponent_name);
        ((PhysicalArchitecture) architecture).setOwnedPhysicalComponent((PhysicalComponent) first);
      }

    } else if (architecture instanceof EPBSArchitecture) {
      first = ((EPBSArchitecture) architecture).getOwnedConfigurationItem();
      if ((first == null) && create) {
        first = EpbsFactory.eINSTANCE.createConfigurationItem(NamingConstants.CreateEPBSArchCmd_configurationItem_name);
        ((EPBSArchitecture) architecture).setOwnedConfigurationItem((ConfigurationItem) first);
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
   * Return the main component of the phase
   * 
   * @param architecture
   * @return
   */
  public static Component getFirstComponent(ModellingArchitecture architecture) {
    return getFirstComponent(architecture, true);
  }

  /**
   * Return true if the given component is contained by a block architecture
   * 
   * @param component
   * @return
   */
  public static boolean isRootComponent(Component component) {
    return component.eContainer() instanceof BlockArchitecture;
  }

  /**
   * Retrieve Components from BlockArchitecture Layer + look for available modes and states
   * 
   * @param blockArch
   * @param availableElements
   */
  public static void getAllStatesAndModesFromBlockArchitecture(BlockArchitecture blockArch,
      List<CapellaElement> availableElements) {
    if (null != blockArch) {
      if (blockArch instanceof OperationalAnalysis) {
        getAllStatesAndModesFromOperationalAnalysisLayer(blockArch, availableElements);
      } else if (blockArch instanceof SystemAnalysis) {
        getAllStatesAndModesFromSystemAnalysisLayer(blockArch, availableElements);
      } else if (blockArch instanceof LogicalArchitecture) {
        getAllStatesAndModesFromLogicalLayer(blockArch, availableElements);
      } else if (blockArch instanceof PhysicalArchitecture) {
        getAllStatesAndModesFromPhysicalLayer(blockArch, availableElements);
      } else if (blockArch instanceof EPBSArchitecture) {
        getAllStatesAndModesFromEPBSLayer(blockArch, availableElements);
      }
    }
  }

  /**
   * Retrieve Components from OperationalAnalysis Layer + look for available modes and states
   * 
   * @param blockArch
   * @param availableElements
   */
  public static void getAllStatesAndModesFromOperationalAnalysisLayer(BlockArchitecture blockArch,
      List<CapellaElement> availableElements) {
    OperationalAnalysis oa = (OperationalAnalysis) blockArch;
    // Entities
    EntityPkg ownedEntityPkg = oa.getOwnedEntityPkg();
    List<Entity> allEntity = OperationalAnalysisExt.getAllEntity(ownedEntityPkg);
    for (Entity entity : allEntity) {
      availableElements.addAll(ComponentExt.getAllStatesAndModesFromComponent(entity));
    }
  }

  /**
   * Retrieve Components from SystemAnslysis Layer + look for available modes and states
   * 
   * @param blockArch
   * @param availableElements
   */
  public static void getAllStatesAndModesFromSystemAnalysisLayer(BlockArchitecture blockArch,
      List<CapellaElement> availableElements) {
    SystemAnalysis ca = (SystemAnalysis) blockArch;
    // System Context
    SystemContext ownedSystemContext = ca.getOwnedSystemContext();
    availableElements.addAll(ComponentExt.getAllStatesAndModesFromComponent(ownedSystemContext));
    // System
    System ownedSystem = ca.getOwnedSystem();
    availableElements.addAll(ComponentExt.getAllStatesAndModesFromComponent(ownedSystem));
    // Actors
    ActorPkg ownedActorPkg = ca.getOwnedActorPkg();
    List<Actor> allActors = ActorPkgExt.getAllActors(ownedActorPkg);
    for (Actor actor : allActors) {
      availableElements.addAll(ComponentExt.getAllStatesAndModesFromComponent(actor));
    }
  }

  /**
   * Retrieve Components from Logical layer + look for available modes and states
   * 
   * @param blockArch
   * @param availableElements
   */
  public static void getAllStatesAndModesFromLogicalLayer(BlockArchitecture blockArch,
      List<CapellaElement> availableElements) {
    LogicalArchitecture logArch = (LogicalArchitecture) blockArch;
    // LogicalContext
    LogicalContext ownedLogicalContext = logArch.getOwnedLogicalContext();
    availableElements.addAll(ComponentExt.getAllStatesAndModesFromComponent(ownedLogicalContext));
    // LogicalComponent
    List<LogicalComponent> allLCsFromLogicalArchitectureLayer = LogicalArchitectureExt
        .getAllLCsFromLogicalArchitectureLayer(logArch);
    for (LogicalComponent logicalComponent : allLCsFromLogicalArchitectureLayer) {
      availableElements.addAll(ComponentExt.getAllStatesAndModesFromComponent(logicalComponent));
    }
    // LogicalActor
    List<LogicalActor> allLAsFromLAPkg = LogicalActorPkgExt.getAllLAsFromLAPkg(logArch.getOwnedLogicalActorPkg());
    for (LogicalActor logicalActor : allLAsFromLAPkg) {
      availableElements.addAll(ComponentExt.getAllStatesAndModesFromComponent(logicalActor));
    }
  }

  /**
   * Retrieve Components from Physical layer + look for available modes and states
   * 
   * @param blockArch
   * @param availableElements
   */
  public static void getAllStatesAndModesFromPhysicalLayer(BlockArchitecture blockArch,
      List<CapellaElement> availableElements) {
    PhysicalArchitecture phyArch = (PhysicalArchitecture) blockArch;
    // PhysicalContext
    PhysicalContext ownedPhysicalContext = phyArch.getOwnedPhysicalContext();
    availableElements.addAll(ComponentExt.getAllStatesAndModesFromComponent(ownedPhysicalContext));
    // PhysicalComponent
    List<PhysicalComponent> allPhysicalComponents = PhysicalArchitectureExt.getAllPhysicalComponents(phyArch);
    for (PhysicalComponent physicalComponent : allPhysicalComponents) {
      availableElements.addAll(ComponentExt.getAllStatesAndModesFromComponent(physicalComponent));
    }
    // LogicalActor
    List<PhysicalActor> allPAsFromPAPkg = PhysicalActorPkgExt.getAllPAsFromPAPkg((phyArch.getOwnedPhysicalActorPkg()));
    for (PhysicalActor physicalActor : allPAsFromPAPkg) {
      availableElements.addAll(ComponentExt.getAllStatesAndModesFromComponent(physicalActor));
    }
  }

  /**
   * Retrieve Components form EPBS Layer + look for available modes and states
   * 
   * @param blockArch
   * @param availableElements
   */
  public static void getAllStatesAndModesFromEPBSLayer(BlockArchitecture blockArch,
      List<CapellaElement> availableElements) {
    EPBSArchitecture epbsArch = (EPBSArchitecture) blockArch;
    // EPBSContext
    EPBSContext ownedEPBSContext = epbsArch.getOwnedEPBSContext();
    availableElements.addAll(ComponentExt.getAllStatesAndModesFromComponent(ownedEPBSContext));
    // Configuration Item
    List<ConfigurationItem> allConfigurationItems = SystemEngineeringExt.getAllConfigurationItems(epbsArch);
    for (ConfigurationItem configurationItem : allConfigurationItems) {
      availableElements.addAll(ComponentExt.getAllStatesAndModesFromComponent(configurationItem));
    }
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
      Set<EObject> epbsSet = EObjectExt.getAll(blockArch, CsPackage.Literals.COMPONENT);
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
   * 
   * @param blockArch
   * @param availableElements
   */
  public static void getAllComponentsFromBlockArchitecture(BlockArchitecture blockArch,
      List<CapellaElement> availableElements) {
    if (null != blockArch) {
      if (blockArch instanceof OperationalAnalysis) {
        getAllComponentsFromOA(blockArch, availableElements);
      } else if (blockArch instanceof SystemAnalysis) {
        getAllComponentsFromSA(blockArch, availableElements);
      } else if (blockArch instanceof LogicalArchitecture) {
        getAllComponentsFromLA(blockArch, availableElements);
      } else if (blockArch instanceof PhysicalArchitecture) {
        getAllComponentsFromPA(blockArch, availableElements);
      } else if (blockArch instanceof EPBSArchitecture) {
        getAllComponentsFromEPBS(blockArch, availableElements);
      }
    }
  }

  /**
   * Get all components from OperationalAnalysis
   * 
   * @param blockArch
   * @param availableElements
   */
  public static void getAllComponentsFromOA(BlockArchitecture blockArch, List<CapellaElement> availableElements) {
    if (blockArch instanceof OperationalAnalysis) {
      OperationalAnalysis arch = (OperationalAnalysis) blockArch;
      // OperationalContext
      OperationalContext context = arch.getOwnedOperationalContext();
      if (null != context) {
        availableElements.add(context);
      }
      // Entity
      availableElements.addAll(OperationalAnalysisExt.getAllEntity(arch));
    }
  }

  /**
   * Get all components from SystemAnalysis
   * 
   * @param blockArch
   * @param availableElements
   */
  public static void getAllComponentsFromSA(BlockArchitecture blockArch, List<CapellaElement> availableElements) {
    if (blockArch instanceof SystemAnalysis) {
      SystemAnalysis ca = (SystemAnalysis) blockArch;
      // System Context
      SystemContext ownedSystemContext = ca.getOwnedSystemContext();
      if (null != ownedSystemContext) {
        availableElements.add(ownedSystemContext);
      }
      // System
      System ownedSystem = ca.getOwnedSystem();
      if (null != ownedSystem) {
        availableElements.add(ownedSystem);
      }
      // Actors
      ActorPkg ownedActorPkg = ca.getOwnedActorPkg();
      availableElements.addAll(ActorPkgExt.getAllActors(ownedActorPkg));
    }
  }

  /**
   * Get all components from LogicalArchitecture
   * 
   * @param blockArch
   * @param availableElements
   */
  public static void getAllComponentsFromLA(BlockArchitecture blockArch, List<CapellaElement> availableElements) {
    if (blockArch instanceof LogicalArchitecture) {
      LogicalArchitecture logArch = (LogicalArchitecture) blockArch;
      // LogicalContext
      LogicalContext ownedLogicalContext = logArch.getOwnedLogicalContext();
      if (null != ownedLogicalContext) {
        availableElements.add(ownedLogicalContext);
      }
      // LogicalComponent
      availableElements.addAll(LogicalArchitectureExt.getAllLCsFromLogicalArchitectureLayer(logArch));
      // LogicalActor
      availableElements.addAll(LogicalActorPkgExt.getAllLAsFromLAPkg(logArch.getOwnedLogicalActorPkg()));
    }
  }

  /**
   * Get all components from PhysicalArchitecture
   * 
   * @param blockArch
   * @param availableElements
   */
  public static void getAllComponentsFromPA(BlockArchitecture blockArch, List<CapellaElement> availableElements) {
    if (blockArch instanceof PhysicalArchitecture) {
      PhysicalArchitecture phyArch = (PhysicalArchitecture) blockArch;
      // PhysicalContext
      PhysicalContext ownedPhysicalContext = phyArch.getOwnedPhysicalContext();
      if (null != ownedPhysicalContext) {
        availableElements.add(ownedPhysicalContext);
      }
      // PhysicalComponent
      availableElements.addAll(PhysicalArchitectureExt.getAllPhysicalComponents(phyArch));
      // LogicalActor
      availableElements.addAll(PhysicalActorPkgExt.getAllPAsFromPAPkg((phyArch.getOwnedPhysicalActorPkg())));
    }
  }

  /**
   * Get all components from EPBSArchitecture
   * 
   * @param blockArch
   * @param availableElements
   */
  public static void getAllComponentsFromEPBS(BlockArchitecture blockArch, List<CapellaElement> availableElements) {
    if (blockArch instanceof EPBSArchitecture) {
      EPBSArchitecture epbsArch = (EPBSArchitecture) blockArch;
      // EPBSContext
      EPBSContext ownedEPBSContext = epbsArch.getOwnedEPBSContext();
      if (null != ownedEPBSContext) {
        availableElements.add(ownedEPBSContext);
      }
      // Configuration Item
      availableElements.addAll(SystemEngineeringExt.getAllConfigurationItems(epbsArch));
    }
  }

  /**
   * Return the first {@link Component} except {@link ComponentContext}
   * 
   * @param architecture
   * @return List<Component>
   */
  public static List<Component> getFirstComponents(ModellingArchitecture architecture) {
    List<Component> result = new ArrayList<>(1);

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
   * 
   * @param anArchitecture
   *          an Architecture
   * @return return only the previous architecture of anArchitecture
   */
  public static List<BlockArchitecture> getPreviousBlockArchitecture(final BlockArchitecture anArchitecture) {
    List<BlockArchitecture> result = new ArrayList<>();

    if (null != anArchitecture) {
      // oa does not have any previous arch
      if (anArchitecture instanceof OperationalAnalysis) {
        return result;
      }

      // functional way according to model
      result.addAll(getPreviousBlockArchitectureByFunctinoalWay(anArchitecture));

      // consider classic way only if functional way result is void
      if (result.isEmpty()) {
        // classic way (consider the level as OA,SA,LA,PA,EPBS)
        result.addAll(getPreviousBlockArchitectureByClassicWay(anArchitecture));
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
  public static List<BlockArchitecture> getPreviousBlockArchitectureByFunctinoalWay(
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
  public static List<BlockArchitecture> getPreviousBlockArchitectureByClassicWay(
      final BlockArchitecture anArchitecture) {
    List<BlockArchitecture> result = new ArrayList<>();
    if (null != anArchitecture) {
      SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(anArchitecture);

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
   * Return all the constraint
   * 
   * @param anElement
   * @return
   */
  public static List<EObject> getAllConstraints(EObject anElement) {
    List<EObject> result = new ArrayList<>();
    if (null == anElement) {
      return result;
    }
    for (EObject obj : EObjectExt.getAll(anElement, CapellacorePackage.Literals.CONSTRAINT)) {
      result.add(obj);
    }
    return result;
  }
}
