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

	public Object doSwitch(RoleAllocation element_p, EStructuralFeature feature_p) {
		Object ret = null;

		if (feature_p.equals(OaPackage.Literals.ROLE_ALLOCATION__ENTITY)) {
			ret = getEntity(element_p);
		}
		else if (feature_p.equals(OaPackage.Literals.ROLE_ALLOCATION__ROLE)) {
			ret = getRole(element_p);
		}

    // no helper found... searching in super classes...
    if(null == ret) {
      ret = AllocationHelper.getInstance().doSwitch(element_p, feature_p);
    }

		return ret;
	}

	protected Entity getEntity(RoleAllocation element_p) {
		TraceableElement ret = element_p.getSourceElement();

		if(null != ret && ret instanceof Entity)
			return (Entity) ret;

		return null;
	}

	protected Role getRole(RoleAllocation element_p) {
		TraceableElement ret = element_p.getTargetElement();

		if(null != ret && ret instanceof Role)
			return (Role) ret;

		return null;
	}
}
