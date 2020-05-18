/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.interaction.Gate;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionUse;

public class InteractionUseHelper {
	private static InteractionUseHelper instance;

	private InteractionUseHelper() {
    // do nothing
	}

	public static InteractionUseHelper getInstance() {
		if (instance == null)
			instance = new InteractionUseHelper();
		return instance;
	}

	public Object doSwitch(InteractionUse element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(InteractionPackage.Literals.INTERACTION_USE__ACTUAL_GATES)) {
      ret = getActualGates(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected List<Gate> getActualGates(InteractionUse element) {
    List<Gate> ret = new ArrayList<>();

    ret.addAll(element.getOwnedGates());

    return ret;
  }
}
