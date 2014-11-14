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
package org.polarsys.capella.core.data.helpers.sharedmodel;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.common.tig.model.HelperNotFoundException;
import org.polarsys.capella.common.tig.model.IHelper;

public class SharedModelHelper implements IHelper {

	public Object getValue(EObject object_p, EStructuralFeature feature_p, EAnnotation annotation_p) {
		Object ret = null;

		if (object_p instanceof SharedPkg) {
      ret = StructureHelper.getInstance().doSwitch((SharedPkg) object_p, feature_p);
    }
		else if (object_p instanceof GenericPkg) {
      ret = StructureHelper.getInstance().doSwitch((GenericPkg) object_p, feature_p);
    }

		if (null != ret || feature_p.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}
}
