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
package org.polarsys.capella.core.data.helpers.requirement;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.requirement.delegates.AbstractRequirementHelper;
import org.polarsys.capella.core.data.helpers.requirement.delegates.RequirementsTraceHelper;
import org.polarsys.capella.core.data.requirement.RequirementsPkg;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.core.data.requirement.SystemFunctionalInterfaceRequirement;
import org.polarsys.capella.core.data.requirement.SystemFunctionalRequirement;
import org.polarsys.capella.core.data.requirement.SystemNonFunctionalInterfaceRequirement;
import org.polarsys.capella.core.data.requirement.SystemNonFunctionalRequirement;
import org.polarsys.capella.core.data.requirement.SystemUserRequirement;
import org.polarsys.capella.common.tig.model.HelperNotFoundException;
import org.polarsys.capella.common.tig.model.IHelper;

public class RequirementHelper implements IHelper {

	public Object getValue(EObject object_p, EStructuralFeature feature_p, EAnnotation annotation_p) {
		Object ret = null;

		if (object_p instanceof RequirementsPkg) {
			ret = StructureHelper.getInstance().doSwitch((RequirementsPkg) object_p, feature_p);
		}
    else if (object_p instanceof SystemFunctionalInterfaceRequirement) {
      ret = AbstractRequirementHelper.getInstance().doSwitch((SystemFunctionalInterfaceRequirement) object_p, feature_p);
    }
    else if (object_p instanceof SystemFunctionalRequirement) {
      ret = AbstractRequirementHelper.getInstance().doSwitch((SystemFunctionalRequirement) object_p, feature_p);
    }
    else if (object_p instanceof SystemNonFunctionalInterfaceRequirement) {
      ret = AbstractRequirementHelper.getInstance().doSwitch((SystemNonFunctionalInterfaceRequirement) object_p, feature_p);
    }
    else if (object_p instanceof SystemNonFunctionalRequirement) {
      ret = AbstractRequirementHelper.getInstance().doSwitch((SystemNonFunctionalRequirement) object_p, feature_p);
    }
		else if (object_p instanceof SystemUserRequirement) {
      ret = AbstractRequirementHelper.getInstance().doSwitch((SystemUserRequirement) object_p, feature_p);
    }
    else if (object_p instanceof RequirementsTrace) {
      ret = RequirementsTraceHelper.getInstance().doSwitch((RequirementsTrace) object_p, feature_p);
    }

		if (null != ret || feature_p.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}
}
