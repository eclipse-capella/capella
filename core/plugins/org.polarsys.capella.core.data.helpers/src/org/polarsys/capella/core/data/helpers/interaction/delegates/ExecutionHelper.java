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
package org.polarsys.capella.core.data.helpers.interaction.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

public class ExecutionHelper {
	private static ExecutionHelper instance;

	private ExecutionHelper() {
    // do nothing
	}

	public static ExecutionHelper getInstance() {
		if (instance == null)
			instance = new ExecutionHelper();
		return instance;
	}

	public Object doSwitch(Execution element_p, EStructuralFeature feature_p) {
		Object ret = null;

    if (feature_p.equals(InteractionPackage.Literals.EXECUTION__COVERED)) {
      ret = getCovered(element_p);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

  protected InstanceRole getCovered(Execution element_p) {
    if (element_p != null) {
      InteractionFragment ifstart = element_p.getStart();
      if (ifstart != null) {
        for (InstanceRole instanceRole : ifstart.getCoveredInstanceRoles()) {
          return instanceRole;
        }
      }
      InteractionFragment iffinish = element_p.getFinish();
      if (iffinish != null) {
        for (InstanceRole instanceRole : iffinish.getCoveredInstanceRoles()) {
          return instanceRole;
        }
      }
    }
    return null;
  }
}
