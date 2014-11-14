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
package org.polarsys.capella.core.data.helpers.ctx;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.ActorMissionInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.OperationalActorRealization;
import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.ctx.OperationalEntityRealization;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.SystemCommunication;
import org.polarsys.capella.core.data.ctx.SystemCommunicationHook;
import org.polarsys.capella.core.data.ctx.SystemContext;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemMissionInvolvement;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.ActorCapabilityInvolvementHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.ActorHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.ActorMissionInvolvementHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.CapabilityHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.MissionHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.OperationalActorRealizationHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.OperationalAnalysisRealizationHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.OperationalEntityRealizationHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.SysAnalysisHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.SystemCapabilityInvolvementHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.SystemContextHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.SystemFunctionHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.SystemHelper;
import org.polarsys.capella.core.data.helpers.ctx.delegates.SystemMissionInvolvementHelper;

public class SystemAnalysisHelper implements IHelper {

	public Object getValue(EObject object_p, EStructuralFeature feature_p, EAnnotation annotation_p) {
		Object ret = null;
		
		if (object_p instanceof SystemAnalysis) {
			ret = SysAnalysisHelper.getInstance().doSwitch((SystemAnalysis) object_p, feature_p);
		}
		else if (object_p instanceof Capability) {
			ret = CapabilityHelper.getInstance().doSwitch((Capability) object_p, feature_p); 
		}
		else  if (object_p instanceof Mission) {
			ret = MissionHelper.getInstance().doSwitch((Mission) object_p, feature_p);
		}
		else if (object_p instanceof Actor) {
			ret = ActorHelper.getInstance().doSwitch((Actor) object_p, feature_p);
		}
		else if (object_p instanceof SystemContext) {
			ret = SystemContextHelper.getInstance().doSwitch((SystemContext) object_p, feature_p);
		}
		else if (object_p instanceof System) {
			ret = SystemHelper.getInstance().doSwitch((System) object_p, feature_p); 
		}
		else if (object_p instanceof SystemFunction) {
			ret = SystemFunctionHelper.getInstance().doSwitch((SystemFunction) object_p, feature_p); 
		}
    else if (object_p instanceof OperationalActorRealization) {
      ret = OperationalActorRealizationHelper.getInstance().doSwitch((OperationalActorRealization) object_p, feature_p);
    }
		else if (object_p instanceof OperationalAnalysisRealization) {
			ret = OperationalAnalysisRealizationHelper.getInstance().doSwitch((OperationalAnalysisRealization) object_p, feature_p);
		}
		else if (object_p instanceof OperationalEntityRealization) {
			ret = OperationalEntityRealizationHelper.getInstance().doSwitch((OperationalEntityRealization) object_p, feature_p);
		}
		else if (object_p instanceof SystemCapabilityInvolvement) {
			ret = SystemCapabilityInvolvementHelper.getInstance().doSwitch((SystemCapabilityInvolvement) object_p, feature_p);
		}
		else if (object_p instanceof SystemMissionInvolvement) {
			ret = SystemMissionInvolvementHelper.getInstance().doSwitch((SystemMissionInvolvement) object_p, feature_p);
		}
		else if (object_p instanceof Structure) {
			ret = StructureHelper.getInstance().doSwitch((Structure) object_p, feature_p);
		}
		else if (object_p instanceof ActorCapabilityInvolvement) {
			ret = ActorCapabilityInvolvementHelper.getInstance().doSwitch((ActorCapabilityInvolvement) object_p, feature_p);
		}
		else if (object_p instanceof ActorMissionInvolvement) {
			ret = ActorMissionInvolvementHelper.getInstance().doSwitch((ActorMissionInvolvement) object_p, feature_p);
		}
    else if (object_p instanceof CapabilityExploitation) {
      ret = RelationshipHelper.getInstance().doSwitch((CapabilityExploitation) object_p, feature_p);
    }
    else if (object_p instanceof SystemCommunication) {
      ret = RelationshipHelper.getInstance().doSwitch((SystemCommunication) object_p, feature_p);
    }
    else if (object_p instanceof SystemCommunicationHook) {
      ret = NamedElementHelper.getInstance().doSwitch((SystemCommunicationHook) object_p, feature_p);
    }

		if(null != ret || feature_p.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}
}
