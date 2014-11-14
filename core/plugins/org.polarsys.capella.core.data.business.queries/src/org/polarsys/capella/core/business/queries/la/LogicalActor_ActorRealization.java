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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.SystemActorRealization;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 *
 */
public class LogicalActor_ActorRealization implements IBusinessQuery {

  private List<CapellaElement> getRule_MQRY_LogicalActor_ActorRealization_11(SystemEngineering systemEng_p, LogicalActor element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

    SystemAnalysis arch = SystemEngineeringExt.getOwnedSystemAnalysis(systemEng_p);
    ActorPkg ownedActorPkg = arch.getOwnedActorPkg();
    List<Actor> allActors = ActorPkgExt.getAllActors(ownedActorPkg);

    if (null != element_p) {
      EList<SystemActorRealization> ownedActorRealisations = element_p.getOwnedSystemActorRealizations();
      for (SystemActorRealization actRealisation : ownedActorRealisations) {
        TraceableElement targetElement = actRealisation.getTargetElement();
        if (null != targetElement) {
          if (allActors.contains(targetElement)) {
            allActors.remove(targetElement);
          } 
        }
      }
    }
    
    for (Actor function : allActors) {
      availableElements.add(function);
    }
    
    return availableElements;
  }
  
  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
 
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);

    if (null == systemEngineering) {
      return availableElements;
    }
    
    if (element_p instanceof LogicalActor) {
      LogicalActor logicalActor = (LogicalActor) element_p;
      availableElements.addAll(getRule_MQRY_LogicalActor_ActorRealization_11(systemEngineering, logicalActor));
    } 
     
    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement, boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> returnedList = new ArrayList<CapellaElement>();
    for (SystemActorRealization aRealisation : ((LogicalActor) element_p).getOwnedSystemActorRealizations()){
      returnedList.add((CapellaElement) aRealisation.getTargetElement());
    }
    return returnedList;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return LaPackage.Literals.LOGICAL_ACTOR;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getEStructuralFeature()
   */
  public List<EReference> getEStructuralFeatures() {
    List<EReference> list = new ArrayList<EReference>(1);
    list.add(ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT);
    list.add(LaPackage.Literals.LOGICAL_ACTOR__OWNED_SYSTEM_ACTOR_REALIZATIONS);
    return list;
  }

}
