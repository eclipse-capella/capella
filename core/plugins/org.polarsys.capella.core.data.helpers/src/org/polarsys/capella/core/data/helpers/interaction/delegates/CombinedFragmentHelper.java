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

	public Object doSwitch(CombinedFragment element_p, EStructuralFeature feature_p) {
		Object ret = null;

    if (feature_p.equals(InteractionPackage.Literals.COMBINED_FRAGMENT__EXPRESSION_GATES)) {
      ret = getExpressionGates(element_p);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

  protected List<Gate> getExpressionGates(CombinedFragment element_p) {
    List<Gate> ret = new ArrayList<Gate>();

    ret.addAll(element_p.getOwnedGates());

    return ret;
  }
}
