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

package org.polarsys.capella.core.data.helpers.capellacommon.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.AbstractTypeHelper;
import org.polarsys.capella.core.data.capellacommon.StateEvent;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;

public class StateEventHelper {
  private static StateEventHelper instance;

  private StateEventHelper() {
    // Do nothing
  }

  public static StateEventHelper getInstance() {
    if (instance == null) {
      instance = new StateEventHelper();
    }
    return instance;
  }

  public Object doSwitch(StateEvent element, EStructuralFeature feature) {
    // no helper found... searching in super classes...
	Object ret = NamedElementHelper.getInstance().doSwitch(element, feature);
    if (null == ret) {
      ret = AbstractTypeHelper.getInstance().doSwitch(element, feature);
    }
    return ret;
  }
}
