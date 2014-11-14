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
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

public class AbstractEndHelper {
	private static AbstractEndHelper instance;

	private AbstractEndHelper() {
    // do nothing
	}

	public static AbstractEndHelper getInstance() {
		if (instance == null)
			instance = new AbstractEndHelper();
		return instance;
	}

	public Object doSwitch(AbstractEnd element_p, EStructuralFeature feature_p) {
		Object ret = null;

    if (feature_p.equals(InteractionPackage.Literals.ABSTRACT_END__COVERED)) {
      ret = getCovered(element_p);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element_p, feature_p);
		}

		return ret;
	}

  protected InstanceRole getCovered(AbstractEnd element_p) {
    if (element_p != null) {
      for (InstanceRole instanceRole : element_p.getCoveredInstanceRoles()) {
        return instanceRole;
      }
    }
    return null;
  }
}
