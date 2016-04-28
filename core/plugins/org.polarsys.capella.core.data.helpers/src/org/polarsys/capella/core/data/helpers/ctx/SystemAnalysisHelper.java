/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

	public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
		Object ret = null;
		
		if (object instanceof SystemAnalysis) {
			ret = SysAnalysisHelper.getInstance().doSwitch((SystemAnalysis) object, feature);
		}
		else if (object instanceof Capability) {
			ret = CapabilityHelper.getInstance().doSwitch((Capability) object, feature); 
		}
		else  if (object instanceof Mission) {
			ret = MissionHelper.getInstance().doSwitch((Mission) object, feature);
		}
		else if (object instanceof Actor) {
			ret = ActorHelper.getInstance().doSwitch((Actor) object, feature);
		}
		else if (object instanceof SystemContext) {
			ret = SystemContextHelper.getInstance().doSwitch((SystemContext) object, feature);
		}
		else if (object instanceof System) {
			ret = SystemHelper.getInstance().doSwitch((System) object, feature); 
		}
		else if (object instanceof SystemFunction) {
			ret = SystemFunctionHelper.getInstance().doSwitch((SystemFunction) object, feature); 
		}
    else if (object instanceof OperationalActorRealization) {
      ret = OperationalActorRealizationHelper.getInstance().doSwitch((OperationalActorRealization) object, feature);
    }
		else if (object instanceof OperationalAnalysisRealization) {
			ret = OperationalAnalysisRealizationHelper.getInstance().doSwitch((OperationalAnalysisRealization) object, feature);
		}
		else if (object instanceof OperationalEntityRealization) {
			ret = OperationalEntityRealizationHelper.getInstance().doSwitch((OperationalEntityRealization) object, feature);
		}
		else if (object instanceof SystemCapabilityInvolvement) {
			ret = SystemCapabilityInvolvementHelper.getInstance().doSwitch((SystemCapabilityInvolvement) object, feature);
		}
		else if (object instanceof SystemMissionInvolvement) {
			ret = SystemMissionInvolvementHelper.getInstance().doSwitch((SystemMissionInvolvement) object, feature);
		}
		else if (object instanceof Structure) {
			ret = StructureHelper.getInstance().doSwitch((Structure) object, feature);
		}
		else if (object instanceof ActorCapabilityInvolvement) {
			ret = ActorCapabilityInvolvementHelper.getInstance().doSwitch((ActorCapabilityInvolvement) object, feature);
		}
		else if (object instanceof ActorMissionInvolvement) {
			ret = ActorMissionInvolvementHelper.getInstance().doSwitch((ActorMissionInvolvement) object, feature);
		}
    else if (object instanceof CapabilityExploitation) {
      ret = RelationshipHelper.getInstance().doSwitch((CapabilityExploitation) object, feature);
    }
    else if (object instanceof SystemCommunication) {
      ret = RelationshipHelper.getInstance().doSwitch((SystemCommunication) object, feature);
    }
    else if (object instanceof SystemCommunicationHook) {
      ret = NamedElementHelper.getInstance().doSwitch((SystemCommunicationHook) object, feature);
    }

		if(null != ret || feature.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}
}
