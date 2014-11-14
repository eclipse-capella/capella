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
package org.polarsys.capella.core.data.helpers.capellamodeller;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.capellamodeller.delegates.SystemEngineeringHelper;
import org.polarsys.capella.core.data.capellamodeller.Folder;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineeringPkg;
import org.polarsys.capella.common.tig.model.HelperNotFoundException;
import org.polarsys.capella.common.tig.model.IHelper;

public class CapellaModellerHelper implements IHelper {

	public Object getValue(EObject object_p, EStructuralFeature feature_p, EAnnotation annotation_p) {
		Object ret = null;

		if (object_p instanceof Project) {
			ret = StructureHelper.getInstance().doSwitch((Project) object_p, feature_p);
		}
		else if (object_p instanceof Folder) {
			ret = StructureHelper.getInstance().doSwitch((Folder) object_p, feature_p);
		}
		else if (object_p instanceof SystemEngineering) {
			ret = SystemEngineeringHelper.getInstance().doSwitch((SystemEngineering) object_p, feature_p);
		}
		else if (object_p instanceof SystemEngineeringPkg) {
			ret = StructureHelper.getInstance().doSwitch((SystemEngineeringPkg) object_p, feature_p);
		}

		if (null != ret || feature_p.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}
}
