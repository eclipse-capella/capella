/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.ctx.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.ctx.OperationalActorRealization;
import org.polarsys.capella.core.data.helpers.cs.delegates.ComponentAllocationHelper;

public class OperationalActorRealizationHelper {
  private static OperationalActorRealizationHelper instance;

  private OperationalActorRealizationHelper() {
    // do nothing
  }

  public static OperationalActorRealizationHelper getInstance() {
    if (instance == null)
      instance = new OperationalActorRealizationHelper();
    return instance;
  }

  public Object doSwitch(OperationalActorRealization element, EStructuralFeature feature) {
    Object ret = null;

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = ComponentAllocationHelper.getInstance().doSwitch(element, feature);
    }
    return ret;
  }
}
