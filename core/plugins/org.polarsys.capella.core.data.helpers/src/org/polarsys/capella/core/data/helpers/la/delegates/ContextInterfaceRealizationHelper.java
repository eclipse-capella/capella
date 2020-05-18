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

package org.polarsys.capella.core.data.helpers.la.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.cs.delegates.InterfaceAllocationHelper;
import org.polarsys.capella.core.data.la.ContextInterfaceRealization;

public class ContextInterfaceRealizationHelper {
  private static ContextInterfaceRealizationHelper instance;

  private ContextInterfaceRealizationHelper() {
    // do nothing
  }

  public static ContextInterfaceRealizationHelper getInstance() {
    if (instance == null) {
    	instance = new ContextInterfaceRealizationHelper();
    }
    return instance;
  }

  public Object doSwitch(ContextInterfaceRealization element, EStructuralFeature feature) {
	  // no helper found... searching in super classes...
      return InterfaceAllocationHelper.getInstance().doSwitch(element, feature);
  }
}
