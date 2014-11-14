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
package org.polarsys.capella.core.data.helpers.la;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.CapabilityRealizationHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.ContextInterfaceRealizationHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.LaArchitectureHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.LogicalActorHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.LogicalComponentHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.LogicalContextHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.LogicalFunctionHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.SystemActorRealizationHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.SystemAnalysisRealizationHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.SystemRealizationHelper;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.ContextInterfaceRealization;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalContext;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.SystemActorRealization;
import org.polarsys.capella.core.data.la.SystemAnalysisRealization;
import org.polarsys.capella.core.data.la.SystemRealization;

public class LogicalArchitectureHelper implements IHelper {

	public Object getValue(EObject object_p, EStructuralFeature feature_p, EAnnotation annotation_p) {
		Object ret = null;

		if (object_p instanceof LogicalArchitecture) {
			ret = LaArchitectureHelper.getInstance().doSwitch((LogicalArchitecture) object_p, feature_p);
		}
		else if (object_p instanceof LogicalContext) {
			ret = LogicalContextHelper.getInstance().doSwitch((LogicalContext) object_p, feature_p);
		}
		else if (object_p instanceof LogicalComponent) {
			ret = LogicalComponentHelper.getInstance().doSwitch((LogicalComponent) object_p, feature_p);
		}
		else if (object_p instanceof LogicalFunction) {
			ret = LogicalFunctionHelper.getInstance().doSwitch((LogicalFunction) object_p, feature_p); 
		}
		else if (object_p instanceof SystemAnalysisRealization) {
			ret = SystemAnalysisRealizationHelper.getInstance().doSwitch((SystemAnalysisRealization) object_p, feature_p);
		}
		else if (object_p instanceof ContextInterfaceRealization) {
			ret = ContextInterfaceRealizationHelper.getInstance().doSwitch((ContextInterfaceRealization) object_p, feature_p);
		}
		else if (object_p instanceof SystemRealization) {
			ret = SystemRealizationHelper.getInstance().doSwitch((SystemRealization) object_p, feature_p);
		}
		else if (object_p instanceof LogicalActor) {
			ret = LogicalActorHelper.getInstance().doSwitch((LogicalActor) object_p, feature_p);
		}
		else if (object_p instanceof SystemActorRealization) {
			ret = SystemActorRealizationHelper.getInstance().doSwitch((SystemActorRealization) object_p, feature_p);
		}
		else if (object_p instanceof CapabilityRealization) {
			ret = CapabilityRealizationHelper.getInstance().doSwitch((CapabilityRealization) object_p, feature_p);
		}
		else if (object_p instanceof Structure) {
			ret = StructureHelper.getInstance().doSwitch((Structure) object_p, feature_p);
		}

		if(null != ret || feature_p.getUpperBound() == 1)
			return ret;
		
		throw new HelperNotFoundException();
	}
}
