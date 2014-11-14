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
package org.polarsys.capella.core.business.queries.oa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.capellacore.services.CapellaElementExt;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.common.data.activity.ActivityEdge;

/**
 *
 */
public class CommunicationMean_FunctionalExchanges implements IBusinessQuery {
  
  private List<CapellaElement> getRule_MQRY_RealizableExchangesFromCommunicationMean
  (BlockArchitecture sysEng_p, CommunicationMean element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    
    Entity sourceEntity = (Entity) element_p.getSource();
    Entity targetEntity = (Entity) element_p.getTarget();
    
    // get all exchanges from roles 
    availableElements.addAll(getAllocationFunctionalExchangeByRoles (sourceEntity, targetEntity));
    
    // remove FunctionalExchanges related to element_p
    EList<FunctionalExchange> functionalExchanges = element_p.getAllocatedFunctionalExchanges();
    for (FunctionalExchange functionalExchange : functionalExchanges) {
      availableElements.remove(functionalExchange);
    }
    
    return availableElements;
  }

  /**
   * @param sourceEntity_p
   * @param targetEntity_p
   * @return
   */
  private Collection<? extends FunctionalExchange> getAllocationFunctionalExchangeByRoles(Entity sourceEntity_p, Entity targetEntity_p) {
    List<FunctionalExchange> result = new ArrayList<FunctionalExchange>();
    List<AbstractFunction> sourceActivities = new ArrayList<AbstractFunction>();
    List<AbstractFunction> targetActivities = new ArrayList<AbstractFunction>();
    
    // get all the recursive sub partition of sourceEntity 
    List<PartitionableElement> allSourceDescendants = CapellaElementExt.getAllDescendants(sourceEntity_p);
    // add current sourceEntity to the subPartition list
    allSourceDescendants.add(sourceEntity_p);
    // get all the recursive sub partition of targetEntity 
    List<PartitionableElement> allTargetDescendants = CapellaElementExt.getAllDescendants(targetEntity_p);
    // add current sourceEntity to the subPartition list
    allTargetDescendants.add(targetEntity_p);
    
    //collect all the activities form sourceEntity via Role
    for (PartitionableElement partitionableElement : allSourceDescendants) {
      if (partitionableElement instanceof Entity) {
        for (RoleAllocation ra : ((Entity)partitionableElement).getOwnedRoleAllocations()) {
          for (ActivityAllocation aa : ra.getRole().getActivityAllocations()) {
            sourceActivities.add(aa.getActivity());
          }
        }  
      }
      if (partitionableElement instanceof AbstractFunctionalBlock) {
        AbstractFunctionalBlock absFunBlock = (AbstractFunctionalBlock)partitionableElement;
        sourceActivities.addAll(absFunBlock.getAllocatedFunctions());
      }
    }
    //collect all the activities form targetEntity via Role
    for (PartitionableElement partitionableElement : allTargetDescendants) {
      if (partitionableElement instanceof Entity) {
        for (RoleAllocation ra : ((Entity)partitionableElement).getOwnedRoleAllocations()) {
          for (ActivityAllocation aa : ra.getRole().getActivityAllocations()) {
            targetActivities.add(aa.getActivity());
          }
        }
      }
      if (partitionableElement instanceof AbstractFunctionalBlock ) {
        AbstractFunctionalBlock absFunBlock = (AbstractFunctionalBlock)partitionableElement;
        targetActivities.addAll(absFunBlock.getAllocatedFunctions());
      }
    }
    
    for (AbstractFunction abstractFun : sourceActivities) {
      List<FunctionalExchange> outGoingExchange = FunctionExt.getOutGoingExchange(abstractFun);
      for (ActivityEdge trace : outGoingExchange) {
        if (trace instanceof FunctionalExchange) {
          FunctionalExchange fe = (FunctionalExchange) trace;
          if (targetActivities.contains(fe.getTarget())) {
            result.add(fe);
          }
          
        }
      } 
    }

    
    return result;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    
    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element_p);
    
    if (null == arch) {
      return availableElements;
    }

    if (element_p instanceof CommunicationMean) {
      CommunicationMean currentCapabilityUseCase = (CommunicationMean) element_p;
      availableElements.addAll(getRule_MQRY_RealizableExchangesFromCommunicationMean(arch, currentCapabilityUseCase));
    }  
    availableElements = ListExt.removeDuplicates(availableElements);
    
    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement, boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    
    if (element_p instanceof CommunicationMean) {
      CommunicationMean ele = (CommunicationMean) element_p;
      EList<FunctionalExchange> functionalExchanges = ele.getAllocatedFunctionalExchanges();
      for (FunctionalExchange functionalExchange : functionalExchanges) {
        currentElements.add(functionalExchange);
      }
    }
    
    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return OaPackage.Literals.COMMUNICATION_MEAN;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getEStructuralFeature()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS);
  }
}
