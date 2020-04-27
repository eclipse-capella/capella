/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.information.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;

/**
 *
 */
public class CommunicationLinkHelper {
  private static CommunicationLinkHelper instance;

  private CommunicationLinkHelper() {
    //
  }

  public static CommunicationLinkHelper getInstance(){
    if(instance == null) {
    	instance = new CommunicationLinkHelper();    	
    }
    return instance;
  }

  public Object doSwitch(CommunicationLink element, EStructuralFeature feature){

    // no helper found... searching in super classes...
      return CapellaElementHelper.getInstance().doSwitch(element, feature);
  }
}
