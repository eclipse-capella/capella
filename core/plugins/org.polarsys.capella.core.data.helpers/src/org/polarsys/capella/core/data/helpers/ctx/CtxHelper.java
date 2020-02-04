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

package org.polarsys.capella.core.data.helpers.ctx;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionInvolvement;
import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemCommunication;
import org.polarsys.capella.core.data.ctx.SystemCommunicationHook;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.SystemComponentHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.CapabilityExploitationHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.CapabilityHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.CapabilityInvolvementHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.MissionHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.MissionInvolvementHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.OperationalAnalysisRealizationHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.SystemAnalysisHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.SystemFunctionHelper;

public class CtxHelper implements IHelper {

	public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
		Object ret = null;
		
		if (object instanceof SystemAnalysis) {
			ret = SystemAnalysisHelper.getInstance().doSwitch((SystemAnalysis) object, feature);
		}
		else if (object instanceof Capability) {
			ret = CapabilityHelper.getInstance().doSwitch((Capability) object, feature); 
		}
		else  if (object instanceof Mission) {
			ret = MissionHelper.getInstance().doSwitch((Mission) object, feature);
		}
		else if (object instanceof SystemFunction) {
			ret = SystemFunctionHelper.getInstance().doSwitch((SystemFunction) object, feature); 
		}
		else if (object instanceof OperationalAnalysisRealization) {
			ret = OperationalAnalysisRealizationHelper.getInstance().doSwitch((OperationalAnalysisRealization) object, feature);
		}
		else if (object instanceof Structure) {
			ret = StructureHelper.getInstance().doSwitch((Structure) object, feature);
		}
    else if (object instanceof CapabilityExploitation) {
      ret = CapabilityExploitationHelper.getInstance().doSwitch((CapabilityExploitation) object, feature);
    }
    else if (object instanceof SystemCommunication) {
      ret = RelationshipHelper.getInstance().doSwitch((SystemCommunication) object, feature);
    }
    else if (object instanceof SystemCommunicationHook) {
      ret = NamedElementHelper.getInstance().doSwitch((SystemCommunicationHook) object, feature);
    }
    else if (object instanceof SystemComponent) {
      ret = SystemComponentHelper.getInstance().doSwitch((SystemComponent) object, feature);
    }
    else if (object instanceof CapabilityInvolvement) {
      ret = CapabilityInvolvementHelper.getInstance().doSwitch((CapabilityInvolvement) object, feature);
    }
    else if (object instanceof MissionInvolvement) {
      ret = MissionInvolvementHelper.getInstance().doSwitch((MissionInvolvement) object, feature);
    }

		if(null != ret || feature.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}
}
