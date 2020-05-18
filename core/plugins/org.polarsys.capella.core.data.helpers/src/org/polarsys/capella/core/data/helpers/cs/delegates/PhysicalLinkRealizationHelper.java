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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.PhysicalLinkRealization;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;

public class PhysicalLinkRealizationHelper {
	
  private static PhysicalLinkRealizationHelper instance;

  private PhysicalLinkRealizationHelper() {
    // do nothing
  }

  public static PhysicalLinkRealizationHelper getInstance() {
    if (instance == null) {
    	instance = new PhysicalLinkRealizationHelper();
    }
    return instance;
  }

  public Object doSwitch(PhysicalLinkRealization element, EStructuralFeature feature) {
    // no helper found... searching in super classes...
      return AllocationHelper.getInstance().doSwitch(element, feature);
  }
}
