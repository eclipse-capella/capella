/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.requirement;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.requirement.delegates.RequirementsTraceHelper;
import org.polarsys.capella.core.data.requirement.RequirementsPkg;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.core.data.requirement.SystemFunctionalInterfaceRequirement;
import org.polarsys.capella.core.data.requirement.SystemFunctionalRequirement;
import org.polarsys.capella.core.data.requirement.SystemNonFunctionalInterfaceRequirement;
import org.polarsys.capella.core.data.requirement.SystemNonFunctionalRequirement;
import org.polarsys.capella.core.data.requirement.SystemUserRequirement;

public class RequirementHelper implements IHelper {

	public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
		Object ret = null;

		if (object instanceof RequirementsPkg) {
			ret = StructureHelper.getInstance().doSwitch((RequirementsPkg) object, feature);
		}
    else if (object instanceof SystemFunctionalInterfaceRequirement) {
      ret = org.polarsys.capella.core.data.helpers.requirement.delegates.RequirementHelper.getInstance().doSwitch((SystemFunctionalInterfaceRequirement) object, feature);
    }
    else if (object instanceof SystemFunctionalRequirement) {
      ret = org.polarsys.capella.core.data.helpers.requirement.delegates.RequirementHelper.getInstance().doSwitch((SystemFunctionalRequirement) object, feature);
    }
    else if (object instanceof SystemNonFunctionalInterfaceRequirement) {
      ret = org.polarsys.capella.core.data.helpers.requirement.delegates.RequirementHelper.getInstance().doSwitch((SystemNonFunctionalInterfaceRequirement) object, feature);
    }
    else if (object instanceof SystemNonFunctionalRequirement) {
      ret = org.polarsys.capella.core.data.helpers.requirement.delegates.RequirementHelper.getInstance().doSwitch((SystemNonFunctionalRequirement) object, feature);
    }
		else if (object instanceof SystemUserRequirement) {
      ret = org.polarsys.capella.core.data.helpers.requirement.delegates.RequirementHelper.getInstance().doSwitch((SystemUserRequirement) object, feature);
    }
    else if (object instanceof RequirementsTrace) {
      ret = RequirementsTraceHelper.getInstance().doSwitch((RequirementsTrace) object, feature);
    }

		if (null != ret || feature.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}
}
