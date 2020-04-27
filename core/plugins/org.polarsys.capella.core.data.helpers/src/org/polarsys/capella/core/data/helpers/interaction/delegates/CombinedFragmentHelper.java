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
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.Gate;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

public class CombinedFragmentHelper {
	private static CombinedFragmentHelper instance;

	private CombinedFragmentHelper() {
    // do nothing
	}

	public static CombinedFragmentHelper getInstance() {
		if (instance == null)
			instance = new CombinedFragmentHelper();
		return instance;
	}

	public Object doSwitch(CombinedFragment element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(InteractionPackage.Literals.COMBINED_FRAGMENT__EXPRESSION_GATES)) {
      ret = getExpressionGates(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected List<Gate> getExpressionGates(CombinedFragment element) {
    List<Gate> ret = new ArrayList<>();

    ret.addAll(element.getOwnedGates());

    return ret;
  }
}
