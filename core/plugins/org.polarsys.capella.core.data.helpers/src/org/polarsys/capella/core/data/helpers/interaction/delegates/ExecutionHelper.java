/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

	public Object doSwitch(Execution element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(InteractionPackage.Literals.EXECUTION__COVERED)) {
      ret = getCovered(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected InstanceRole getCovered(Execution element) {
    if (element != null) {
      InteractionFragment ifstart = element.getStart();
      if (ifstart != null) {
        for (InstanceRole instanceRole : ifstart.getCoveredInstanceRoles()) {
          return instanceRole;
        }
      }
      InteractionFragment iffinish = element.getFinish();
      if (iffinish != null) {
        for (InstanceRole instanceRole : iffinish.getCoveredInstanceRoles()) {
          return instanceRole;
        }
      }
    }
    return null;
  }
}
