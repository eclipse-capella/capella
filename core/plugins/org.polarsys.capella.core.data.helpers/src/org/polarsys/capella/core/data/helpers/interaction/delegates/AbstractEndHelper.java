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

	public Object doSwitch(AbstractEnd element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(InteractionPackage.Literals.ABSTRACT_END__COVERED)) {
      ret = getCovered(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected InstanceRole getCovered(AbstractEnd element) {
    if (element != null) {
      for (InstanceRole instanceRole : element.getCoveredInstanceRoles()) {
        return instanceRole;
      }
    }
    return null;
  }
}
