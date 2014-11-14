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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

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
    for (SystemComponentCapabilityRealizationInvolvement anInvolvement : capabilityRealization_p.getInvolvedSystemComponents()) {
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

  public List<SystemComponentCapabilityRealizationInvolvement> getInvolvmentLinkOnRefinedRecursively(CapabilityRealization capabilityRealization_p) {
    List<SystemComponentCapabilityRealizationInvolvement> returnedList = new ArrayList<SystemComponentCapabilityRealizationInvolvement>();
    returnedList.addAll(capabilityRealization_p.getInvolvedSystemComponents());
    for (AbstractTrace aTrace : capabilityRealization_p.getIncomingTraces()) {
      if ((aTrace instanceof RefinementLink) && (aTrace.getSourceElement() != null)) {
        returnedList.addAll(getInvolvmentLinkOnRefinedRecursively((CapabilityRealization) aTrace.getSourceElement()));
      }
    }
    return returnedList;
  }
}
