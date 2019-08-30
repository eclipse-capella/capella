/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
