/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionState;

public class InteractionStateHelper {
	private static InteractionStateHelper instance;

	private InteractionStateHelper() {
    // do nothing
	}

	public static InteractionStateHelper getInstance() {
		if (instance == null)
			instance = new InteractionStateHelper();
		return instance;
	}

	public Object doSwitch(InteractionState element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(InteractionPackage.Literals.INTERACTION_STATE__COVERED)) {
      ret = getCovered(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected InstanceRole getCovered(InteractionState element) {
    if (element != null) {
      for (InstanceRole instanceRole : element.getCoveredInstanceRoles()) {
        return instanceRole;
      }
    }
    return null;
  }
}
