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

package org.polarsys.capella.core.data.helpers.information.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

public class AbstractInstanceHelper {
	private static AbstractInstanceHelper instance;

	private AbstractInstanceHelper() {
    // do nothing
	}

	public static AbstractInstanceHelper getInstance(){
		if(instance == null)
			instance = new AbstractInstanceHelper();
		return instance;
	}

	public Object doSwitch(AbstractInstance element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(InformationPackage.Literals.ABSTRACT_INSTANCE__REPRESENTING_INSTANCE_ROLES)) {
			ret = getRepresentingInstanceRoles(element);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = PropertyHelper.getInstance().doSwitch(element, feature);
		}		

		return ret;
	}

	/**
	 * 
	 */
	protected List<InstanceRole> getRepresentingInstanceRoles(AbstractInstance element) {
		List <InstanceRole> ret = new ArrayList <>();
    for (EObject obj : EObjectExt.getReferencers(element, InteractionPackage.Literals.INSTANCE_ROLE__REPRESENTED_INSTANCE)) {
      if (obj instanceof InstanceRole) {
				ret.add((InstanceRole) obj);
			}
		}
		return ret;
	}
}
