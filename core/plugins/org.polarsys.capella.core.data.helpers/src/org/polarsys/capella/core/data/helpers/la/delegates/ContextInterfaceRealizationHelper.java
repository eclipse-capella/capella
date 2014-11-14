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
    if (instance == null)
      instance = new ContextInterfaceRealizationHelper();
    return instance;
  }

  public Object doSwitch(ContextInterfaceRealization element_p, EStructuralFeature feature_p) {
    Object ret = null;
    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InterfaceAllocationHelper.getInstance().doSwitch(element_p, feature_p);
    }
    return ret;
  }
}
