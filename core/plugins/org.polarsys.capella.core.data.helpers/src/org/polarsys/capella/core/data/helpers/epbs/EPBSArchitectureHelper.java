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
package org.polarsys.capella.core.data.helpers.epbs;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSContext;
import org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.epbs.delegates.ConfigurationItemHelper;
import org.polarsys.capella.core.data.helpers.epbs.delegates.EPBSContextHelper;
import org.polarsys.capella.core.data.helpers.epbs.delegates.EpbsArchitectureHelper;
import org.polarsys.capella.core.data.helpers.epbs.delegates.PhysicalArchitectureRealizationHelper;
import org.polarsys.capella.core.data.helpers.epbs.delegates.PhysicalArtifactRealizationHelper;

public class EPBSArchitectureHelper implements IHelper {

	public Object getValue(EObject object_p, EStructuralFeature feature_p, EAnnotation annotation_p) {
		Object ret = null;

		if (object_p instanceof EPBSArchitecture) {
			ret = EpbsArchitectureHelper.getInstance().doSwitch((EPBSArchitecture) object_p, feature_p);
		}
		else if (object_p instanceof ConfigurationItem) {
			ret = ConfigurationItemHelper.getInstance().doSwitch((ConfigurationItem) object_p, feature_p);
		}
    else if (object_p instanceof EPBSContext) {
      ret = EPBSContextHelper.getInstance().doSwitch((EPBSContext) object_p, feature_p);
    }
		else if (object_p instanceof PhysicalArtifactRealization) {
			ret = PhysicalArtifactRealizationHelper.getInstance().doSwitch((PhysicalArtifactRealization)object_p, feature_p);
		}
		else if (object_p instanceof PhysicalArchitectureRealization) {
			ret = PhysicalArchitectureRealizationHelper.getInstance().doSwitch((PhysicalArchitectureRealization)object_p, feature_p);
		}
		else if (object_p instanceof Structure) {
			ret = StructureHelper.getInstance().doSwitch((Structure) object_p, feature_p);
		}

		if(null != ret || feature_p.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}
}
