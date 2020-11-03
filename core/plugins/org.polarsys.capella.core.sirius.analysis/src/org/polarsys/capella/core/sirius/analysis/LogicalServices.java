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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalComponent;

/**
 * Services for logical Architecture.
 * 
 */
public class LogicalServices {

  /**
   * used in logical.odesign
   */
  public List<TraceableElement> getRefinedRecursively(CapabilityRealization capabilityRealization_p) {
    List<TraceableElement> returnedList = new ArrayList<TraceableElement>();
    for (AbstractTrace aTrace : capabilityRealization_p.getIncomingTraces()) {
      if ((aTrace instanceof RefinementLink) && (aTrace.getSourceElement() != null)) {
        returnedList.add(aTrace.getSourceElement());
        if (aTrace.getSourceElement() instanceof CapabilityRealization) {
          returnedList.addAll(getRefinedRecursively((CapabilityRealization) aTrace.getSourceElement()));
        }
      }
    }
    return returnedList;
  }

  /**
  * used in logical.odesign
  */
  public List<RefinementLink> getRefinementTracesRecursively(CapabilityRealization capabilityRealization_p) {
    List<RefinementLink> returnedList = new ArrayList<RefinementLink>();
    for (AbstractTrace aTrace : capabilityRealization_p.getIncomingTraces()) {
      if (aTrace instanceof RefinementLink) {
        returnedList.add((RefinementLink) aTrace);
        if (aTrace.getSourceElement() instanceof CapabilityRealization) {
          returnedList.addAll(getRefinementTracesRecursively((CapabilityRealization) aTrace.getSourceElement()));
        }
      }
    }
    return returnedList;
  }

  /**
   * used in logical.odesign
   */
  public List<InvolvedElement> getSystemComponentInvolvmentOnRefinedRecursively(CapabilityRealization capabilityRealization_p) {
    List<InvolvedElement> returnedList = new ArrayList<InvolvedElement>();
    for (CapabilityRealizationInvolvement anInvolvement : capabilityRealization_p.getOwnedCapabilityRealizationInvolvements()) {
      if (anInvolvement.getInvolved() != null) {
        returnedList.add(anInvolvement.getInvolved());
      }
    }
    for (AbstractTrace aTrace : capabilityRealization_p.getIncomingTraces()) {
      if ((aTrace instanceof RefinementLink) && (aTrace.getSourceElement() != null)) {
        returnedList.addAll(getSystemComponentInvolvmentOnRefinedRecursively((CapabilityRealization) aTrace.getSourceElement()));
      }
    }
    return returnedList;
  }

  public List<CapabilityRealizationInvolvement> getInvolvmentLinkOnRefinedRecursively(CapabilityRealization capabilityRealization_p) {
    List<CapabilityRealizationInvolvement> returnedList = new ArrayList<>();
    returnedList.addAll(capabilityRealization_p.getOwnedCapabilityRealizationInvolvements());
    for (AbstractTrace aTrace : capabilityRealization_p.getIncomingTraces()) {
      if ((aTrace instanceof RefinementLink) && (aTrace.getSourceElement() != null)) {
        returnedList.addAll(getInvolvmentLinkOnRefinedRecursively((CapabilityRealization) aTrace.getSourceElement()));
      }
    }
    return returnedList;
  }
  
  public LogicalComponent createLogicalComponent(EObject container) {
	
	  LogicalComponent component = LaFactory.eINSTANCE.createLogicalComponent();
		if (component != null) {
		  if (container instanceof LogicalComponent) {
		    LogicalComponent componentContainer = (LogicalComponent) container;
		    componentContainer.getOwnedLogicalComponents().add(component);
		  }
		  CapellaServices.getService().creationService(component);
		}
		
		return component;
  }
}
