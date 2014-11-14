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
package org.polarsys.capella.core.data.helpers.information.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkAllocation;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 *
 */
public class CommunicationLinkAllocationHelper {
  private static CommunicationLinkAllocationHelper instance;

  private CommunicationLinkAllocationHelper() {
    //
  }

  public static CommunicationLinkAllocationHelper getInstance(){
    if(instance == null)
      instance = new CommunicationLinkAllocationHelper();
    return instance;
  }

  public Object doSwitch(CommunicationLinkAllocation element_p, EStructuralFeature feature_p){
    Object ret = null;

    // no helper found... searching in super classes...
    if (feature_p.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_ALLOCATION__ALLOCATED_LINK)) {
      ret = getRealizedLink(element_p);
    }
    else if (feature_p.equals(CommunicationPackage.Literals.COMMUNICATION_LINK_ALLOCATION__ALLOCATING_LINK)) {
      ret = getRealizingLink(element_p);
    } 

    // no helper found... searching in super classes...
    if(null == ret) {
      ret = AllocationHelper.getInstance().doSwitch(element_p, feature_p);
    }
    
    return ret;
  }
  
  protected CommunicationLink getRealizedLink(CommunicationLinkAllocation element_p) {
    TraceableElement ret = element_p.getTargetElement();
    if(null != ret && ret instanceof CommunicationLink)
      return (CommunicationLink) ret;
    return null;
  }

  protected CommunicationLink getRealizingLink(CommunicationLinkAllocation element_p) {
    TraceableElement ret = element_p.getSourceElement();
    if(null != ret && ret instanceof CommunicationLink)
      return (CommunicationLink) ret;
    return null;
  }

}
