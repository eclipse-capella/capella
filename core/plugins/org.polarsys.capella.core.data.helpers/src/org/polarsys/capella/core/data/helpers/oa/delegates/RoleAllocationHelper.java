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

package org.polarsys.capella.core.data.helpers.oa.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class RoleAllocationHelper {
	private static RoleAllocationHelper instance;

	private RoleAllocationHelper() {
    // do nothing
	}

	public static RoleAllocationHelper getInstance(){
		if(instance == null)
			instance = new RoleAllocationHelper();
		return instance;
	}

	public Object doSwitch(RoleAllocation element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(OaPackage.Literals.ROLE_ALLOCATION__ENTITY)) {
			ret = getEntity(element);
		}
		else if (feature.equals(OaPackage.Literals.ROLE_ALLOCATION__ROLE)) {
			ret = getRole(element);
		}

    // no helper found... searching in super classes...
    if(null == ret) {
      ret = AllocationHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}

	protected Entity getEntity(RoleAllocation element) {
		TraceableElement ret = element.getSourceElement();

		if(ret instanceof Entity)
			return (Entity) ret;

		return null;
	}

	protected Role getRole(RoleAllocation element) {
		TraceableElement ret = element.getTargetElement();

		if(ret instanceof Role)
			return (Role) ret;

		return null;
	}
}
