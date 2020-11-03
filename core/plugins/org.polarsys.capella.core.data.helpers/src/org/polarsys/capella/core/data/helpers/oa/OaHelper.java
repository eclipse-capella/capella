/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.oa;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.cs.delegates.ComponentHelper;
import org.polarsys.capella.core.data.helpers.oa.delegates.ActivityAllocationHelper;
import org.polarsys.capella.core.data.helpers.oa.delegates.CommunicationMeanHelper;
import org.polarsys.capella.core.data.helpers.oa.delegates.EntityHelper;
import org.polarsys.capella.core.data.helpers.oa.delegates.EntityOperationalCapabilityInvolvementHelper;
import org.polarsys.capella.core.data.helpers.oa.delegates.OperationalAnalysisHelper;
import org.polarsys.capella.core.data.helpers.oa.delegates.OperationalActivityHelper;
import org.polarsys.capella.core.data.helpers.oa.delegates.OperationalCapabilityHelper;
import org.polarsys.capella.core.data.helpers.oa.delegates.OperationalProcessHelper;
import org.polarsys.capella.core.data.helpers.oa.delegates.RoleAllocationHelper;
import org.polarsys.capella.core.data.helpers.oa.delegates.RoleHelper;
import org.polarsys.capella.core.data.helpers.oa.delegates.SwimlaneHelper;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.CapabilityConfiguration;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.CommunityOfInterest;
import org.polarsys.capella.core.data.oa.CommunityOfInterestComposition;
import org.polarsys.capella.core.data.oa.Concept;
import org.polarsys.capella.core.data.oa.ConceptCompliance;
import org.polarsys.capella.core.data.oa.ConceptPkg;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.ItemInConcept;
import org.polarsys.capella.core.data.oa.Location;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.data.oa.OperationalScenario;
import org.polarsys.capella.core.data.oa.OrganisationalUnit;
import org.polarsys.capella.core.data.oa.OrganisationalUnitComposition;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.data.oa.RoleAssemblyUsage;
import org.polarsys.capella.core.data.oa.RolePkg;
import org.polarsys.capella.core.data.oa.Swimlane;


public class OaHelper implements IHelper {

	public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {

		Object ret = null;
		
		if (object instanceof OperationalAnalysis) {
			ret = OperationalAnalysisHelper.getInstance().doSwitch((OperationalAnalysis) object, feature);
		}
		else if(object instanceof OperationalActivity){
			ret = OperationalActivityHelper.getInstance().doSwitch((OperationalActivity) object, feature);
		}
		else if(object instanceof OperationalCapability){
			ret = OperationalCapabilityHelper.getInstance().doSwitch((OperationalCapability) object, feature);
		}
		else if(object instanceof Swimlane){
			ret = SwimlaneHelper.getInstance().doSwitch((Swimlane) object, feature);
		}
    else if (object instanceof Entity) {
			ret = EntityHelper.getInstance().doSwitch((Entity) object, feature);
		}
		else if (object instanceof Role) {
			ret = RoleHelper.getInstance().doSwitch((Role) object, feature);
		}
		else if (object instanceof OperationalProcess) {
			ret = OperationalProcessHelper.getInstance().doSwitch((OperationalProcess) object, feature);
		}
		else if (object instanceof ActivityAllocation) {
			ret = ActivityAllocationHelper.getInstance().doSwitch((ActivityAllocation) object, feature);
		}
		else if (object instanceof RoleAllocation) {
			ret = RoleAllocationHelper.getInstance().doSwitch((RoleAllocation) object, feature);
		}
		else if (object instanceof CommunicationMean) {
			ret = CommunicationMeanHelper.getInstance().doSwitch((CommunicationMean) object, feature);
		}
    else if(object instanceof OperationalActivityPkg) {
      ret = StructureHelper.getInstance().doSwitch((OperationalActivityPkg) object, feature);
    }
    else if(object instanceof OperationalCapabilityPkg) {
      ret = StructureHelper.getInstance().doSwitch((OperationalCapabilityPkg) object, feature);
    }
    else if(object instanceof RolePkg) {
      ret = StructureHelper.getInstance().doSwitch((RolePkg) object, feature);
    }
    else if(object instanceof EntityPkg) {
      ret = StructureHelper.getInstance().doSwitch((EntityPkg) object, feature);
    }
    else if(object instanceof CapabilityConfiguration) {
      ret = ComponentHelper.getInstance().doSwitch((CapabilityConfiguration) object, feature);
    }
    else if(object instanceof OrganisationalUnit) {
      ret = NamedElementHelper.getInstance().doSwitch((OrganisationalUnit) object, feature);
    }
    else if(object instanceof OrganisationalUnitComposition) {
      ret = NamedElementHelper.getInstance().doSwitch((OrganisationalUnitComposition) object, feature);
    }
    else if(object instanceof Concept) {
      ret = NamedElementHelper.getInstance().doSwitch((Concept) object, feature);
    }
    else if(object instanceof ConceptCompliance) {
      ret = RelationshipHelper.getInstance().doSwitch((ConceptCompliance) object, feature);
    }
    else if(object instanceof ConceptPkg) {
      ret = StructureHelper.getInstance().doSwitch((ConceptPkg) object, feature);
    }
    else if(object instanceof ItemInConcept) {
      ret = NamedElementHelper.getInstance().doSwitch((ItemInConcept) object, feature);
    }
    else if(object instanceof Location) {
      ret = ComponentHelper.getInstance().doSwitch((Location) object, feature);
    }
    else if(object instanceof CommunityOfInterest) {
      ret = NamedElementHelper.getInstance().doSwitch((CommunityOfInterest) object, feature);
    }
    else if(object instanceof CommunityOfInterestComposition) {
      ret = NamedElementHelper.getInstance().doSwitch((CommunityOfInterestComposition) object, feature);
    }
    else if(object instanceof RoleAssemblyUsage) {
      ret = NamedElementHelper.getInstance().doSwitch((RoleAssemblyUsage) object, feature);
    }
    else if(object instanceof EntityOperationalCapabilityInvolvement) {
      ret = EntityOperationalCapabilityInvolvementHelper.getInstance().doSwitch((EntityOperationalCapabilityInvolvement) object, feature);
    }
    else if(object instanceof OperationalScenario) {
      ret = NamedElementHelper.getInstance().doSwitch((OperationalScenario) object, feature);
    }

		if(null != ret || feature.getUpperBound() == 1)
			return ret;
		
		throw new HelperNotFoundException();
	}

}
