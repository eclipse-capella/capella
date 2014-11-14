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
package org.polarsys.capella.core.business.queries.pa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.LogicalActorRealization;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 *
 */
public class PhysicalActor_LogicalActorRealization implements IBusinessQuery {

  private List<CapellaElement> getRule_MQRY_LogicalActor_ActorRealization_11(SystemEngineering systemEng_p, PhysicalActor element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

    LogicalArchitecture arch = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEng_p);
    LogicalActorPkg ownedActorPkg = arch.getOwnedLogicalActorPkg();
    List<LogicalActor> allActors = ActorPkgExt.getAllActors(ownedActorPkg);

    if (null != element_p) {
      EList<LogicalActorRealization> ownedActorRealisations = element_p.getOwnedLogicalActorRealizations();
      for (LogicalActorRealization actRealisation : ownedActorRealisations) {
        TraceableElement targetElement = actRealisation.getTargetElement();
        if (null != targetElement) {
          if (allActors.contains(targetElement)) {
            allActors.remove(targetElement);
          } 
        }
      }
    }
    
    for (LogicalActor actor : allActors) {
      availableElements.add(actor);
    }
    
    return availableElements;
  }
  
  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
 
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

    if (null == systemEngineering) {
      return availableElements;
    }
    
    if (element_p instanceof PhysicalActor) {
      PhysicalActor actor = (PhysicalActor) element_p;
      availableElements.addAll(getRule_MQRY_LogicalActor_ActorRealization_11(systemEngineering, actor));
    } 
     
    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement, boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> returnedList = new ArrayList<CapellaElement>();
    for (LogicalActorRealization aRealisation : ((PhysicalActor) element_p).getOwnedLogicalActorRealizations()){
      returnedList.add((CapellaElement) aRealisation.getTargetElement());
    }
    return returnedList;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return PaPackage.Literals.PHYSICAL_ACTOR;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getEStructuralFeature()
   */
  public List<EReference> getEStructuralFeatures() {
    List<EReference> list = new ArrayList<EReference>(1);
    list.add(PaPackage.Literals.PHYSICAL_ACTOR__LOGICAL_ACTOR_REALIZATIONS);
    list.add(PaPackage.Literals.PHYSICAL_ACTOR__OWNED_LOGICAL_ACTOR_REALIZATIONS);
    return list;
  }

}
