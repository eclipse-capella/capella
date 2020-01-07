/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.epbs.delegates.ConfigurationItemHelper;
import org.polarsys.capella.core.data.helpers.epbs.delegates.EPBSArchitectureHelper;
import org.polarsys.capella.core.data.helpers.epbs.delegates.PhysicalArchitectureRealizationHelper;
import org.polarsys.capella.core.data.helpers.epbs.delegates.PhysicalArtifactRealizationHelper;

public class EpbsHelper implements IHelper {

	public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
		Object ret = null;

		if (object instanceof EPBSArchitecture) {
			ret = EPBSArchitectureHelper.getInstance().doSwitch((EPBSArchitecture) object, feature);
		}
		else if (object instanceof ConfigurationItem) {
			ret = ConfigurationItemHelper.getInstance().doSwitch((ConfigurationItem) object, feature);
		}
		else if (object instanceof PhysicalArtifactRealization) {
			ret = PhysicalArtifactRealizationHelper.getInstance().doSwitch((PhysicalArtifactRealization)object, feature);
		}
		else if (object instanceof PhysicalArchitectureRealization) {
			ret = PhysicalArchitectureRealizationHelper.getInstance().doSwitch((PhysicalArchitectureRealization)object, feature);
		}
		else if (object instanceof Structure) {
			ret = StructureHelper.getInstance().doSwitch((Structure) object, feature);
		}

		if(null != ret || feature.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}
}
