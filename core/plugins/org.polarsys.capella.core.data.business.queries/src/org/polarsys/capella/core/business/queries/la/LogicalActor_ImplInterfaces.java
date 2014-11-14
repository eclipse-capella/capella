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
package org.polarsys.capella.core.business.queries.la;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.business.abstractqueries.ExtendedBusinessQueryForLib;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.LogicalActorPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

/**
 */
public class LogicalActor_ImplInterfaces implements ExtendedBusinessQueryForLib, RefactoredBusinessQuery, IBusinessQuery {

  /**
   * get all the Interface categories from 'functionalExchange_p' parent Block Architecture
   * @param currentProperty_p_p 
   * @param oa_p
   * @return list of ExchangeCategories
   */
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p, LogicalActor element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    
    //current BlockArchitecture Basic Interfaces
    availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(arch_p.getOwnedInterfacePkg(),
                                                                      element_p, true));
    
    //Interface from InterfacePkg of all the LogicalActor
    if (arch_p instanceof LogicalArchitecture) {
      LogicalActorPkg ownedLogicalActorPkg = ((LogicalArchitecture)arch_p).getOwnedLogicalActorPkg();
      availableElements.addAll(LogicalActorPkgExt.getAllInterfacesFromLogicalActorPkg(ownedLogicalActorPkg));
    }
    
    //remove Interfaces related to LogicalActor
    List<Interface> usedInterfaces = ComponentExt.getImplementedInterfaces(element_p);
    for (Interface interface1 : usedInterfaces) {
      availableElements.remove(interface1);
    }
    
    return availableElements;
  }
  
  /**
   * <p>
   * Gets all the interfaces contained in the interface package and all of its
   * sub packages of the Shared Package.
   * </p>
   * <p>
   * Except the interfaces that are already used by the current actor
   * </p>
   * <p>
   * Refer MQRY_Actor_UsedInterfaces_12
   * </p>
   * 
   * @param currentActor_p
   *            the current {@link Actor}
   * @param systemEngineering_p
   *            the {@link SystemEngineering}
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_Actor_UsedInterfaces12(Component currentActor_p, SystemEngineering systemEngineering_p) {
    // The list of Capella elements to return.
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    List<SharedPkg> sharedPkgs = SystemEngineeringExt.getSharedPkgs(currentActor_p);
    for (SharedPkg sharedPkg : sharedPkgs) {
      
      GenericPkg genericPkg = sharedPkg.getOwnedGenericPkg();
      if (genericPkg != null) {
        availableElements.addAll(InterfacePkgExt.getAllInterfacesFiltered(genericPkg, currentActor_p, true));
      }
    }
    return availableElements;
  }
  
  // same level
  private List<CapellaElement> getRule_MQRY_Service_ItemRealization_11(SystemEngineering systemEng_p, LogicalActor actor_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = null;
    arch = SystemEngineeringExt.getRootBlockArchitecture(actor_p);
    
    if (null != arch) {
      availableElements.addAll(getElementsFromBlockArchitecture(arch,actor_p));      
    }
    
    // For the layer visibility
    if (!(arch instanceof OperationalAnalysis)) {
      availableElements.addAll(getRule_MQRY_Service_ItemRealization_11_1(systemEng_p, actor_p));      
    }

    return availableElements;
  }
  
  /*
   * For the layer visibility
   */
  private List<CapellaElement> getRule_MQRY_Service_ItemRealization_11_1(SystemEngineering systemEng_p, LogicalActor currentProperty_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = null;
    arch = SystemEngineeringExt.getRootBlockArchitecture(currentProperty_p);
    
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentProperty_p);
    OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(sysEng);
    if (null != oa) {
      availableElements.addAll(getElementsFromBlockArchitecture(oa,currentProperty_p));
    }else{
      SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
      availableElements.addAll(getElementsFromBlockArchitecture(ca,currentProperty_p));
    }

    if (null != arch) {
      if (null != oa && (arch instanceof LogicalArchitecture) || (arch instanceof PhysicalArchitecture)) {
        SystemAnalysis ctxArch = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
        availableElements.addAll(getElementsFromBlockArchitecture(ctxArch,currentProperty_p));
      }
    }
    return availableElements;
  }
  


  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
 
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

    if (null == systemEngineering) {
      return availableElements;
    }
    
    if (element_p instanceof LogicalActor) {
      LogicalActor ele = (LogicalActor) element_p;
      availableElements.addAll(getRule_MQRY_Service_ItemRealization_11(systemEngineering, ele));
      availableElements.addAll(getRule_MQRY_Actor_UsedInterfaces12(ele, systemEngineering));
    } 
     
    return availableElements;
  }
	/**
	 * <p>
	 * Gets all the interfaces used by the current Logical Component
	 * </p>
	 * <p>
	 * Refer MQRY_LogicalComponent_UsedInterfaces_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getOldCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {

		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		if (null == systemEngineering) {
			return currentElements;
		}

		if (element_p instanceof Component) {
		  Component currentLC = (Component) element_p;
			currentElements.addAll(ComponentExt.getImplementedInterfaces(currentLC));
		}
		return currentElements;
	}

	public EClass getEClass() {
		return LaPackage.Literals.LOGICAL_ACTOR;
	}

	public List<EReference> getEStructuralFeatures() {
	  List<EReference> list = new ArrayList<EReference>(1);
	  list.add(CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACES);
	  list.add(CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS);
    return list;
	}

	@Override
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
	  return RefactorDebugger.callAndTestQuery("GetAvailable_LogicalActor_ImplInterfaces__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
	}

	@Override
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
	  return RefactorDebugger.callAndTestQuery("GetCurrent_LogicalActor_ImplInterfaces", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
	}
}
