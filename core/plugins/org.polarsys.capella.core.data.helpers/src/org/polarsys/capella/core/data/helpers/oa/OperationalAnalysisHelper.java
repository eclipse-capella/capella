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
import org.polarsys.capella.core.data.helpers.oa.delegates.OpAnalysisHelper;
import org.polarsys.capella.core.data.helpers.oa.delegates.OperationalActivityHelper;
import org.polarsys.capella.core.data.helpers.oa.delegates.OperationalActorHelper;
import org.polarsys.capella.core.data.helpers.oa.delegates.OperationalCapabilityHelper;
import org.polarsys.capella.core.data.helpers.oa.delegates.OperationalContextHelper;
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
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.oa.OperationalContext;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.data.oa.OperationalScenario;
import org.polarsys.capella.core.data.oa.OrganisationalUnit;
import org.polarsys.capella.core.data.oa.OrganisationalUnitComposition;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.data.oa.RoleAssemblyUsage;
import org.polarsys.capella.core.data.oa.RolePkg;
import org.polarsys.capella.core.data.oa.Swimlane;


public class OperationalAnalysisHelper implements IHelper {

	public Object getValue(EObject object_p, EStructuralFeature feature_p, EAnnotation annotation_p) {

		Object ret = null;
		
		if (object_p instanceof OperationalAnalysis) {
			ret = OpAnalysisHelper.getInstance().doSwitch((OperationalAnalysis) object_p, feature_p);
		}
		else if(object_p instanceof OperationalActivity){
			ret = OperationalActivityHelper.getInstance().doSwitch((OperationalActivity) object_p, feature_p);
		}
		else if(object_p instanceof OperationalCapability){
			ret = OperationalCapabilityHelper.getInstance().doSwitch((OperationalCapability) object_p, feature_p);
		}
		else if(object_p instanceof Swimlane){
			ret = SwimlaneHelper.getInstance().doSwitch((Swimlane) object_p, feature_p);
		}
    else if (object_p instanceof OperationalActor) {
      ret = OperationalActorHelper.getInstance().doSwitch((OperationalActor) object_p, feature_p);
    }
		else if (object_p instanceof Entity) {
			ret = EntityHelper.getInstance().doSwitch((Entity) object_p, feature_p);
		}
		else if (object_p instanceof Role) {
			ret = RoleHelper.getInstance().doSwitch((Role) object_p, feature_p);
		}
		else if (object_p instanceof OperationalProcess) {
			ret = OperationalProcessHelper.getInstance().doSwitch((OperationalProcess) object_p, feature_p);
		}
		else if (object_p instanceof ActivityAllocation) {
			ret = ActivityAllocationHelper.getInstance().doSwitch((ActivityAllocation) object_p, feature_p);
		}
		else if (object_p instanceof RoleAllocation) {
			ret = RoleAllocationHelper.getInstance().doSwitch((RoleAllocation) object_p, feature_p);
		}
		else if (object_p instanceof CommunicationMean) {
			ret = CommunicationMeanHelper.getInstance().doSwitch((CommunicationMean) object_p, feature_p);
		}
    else if(object_p instanceof OperationalActivityPkg) {
      ret = StructureHelper.getInstance().doSwitch((OperationalActivityPkg) object_p, feature_p);
    }
    else if(object_p instanceof OperationalCapabilityPkg) {
      ret = StructureHelper.getInstance().doSwitch((OperationalCapabilityPkg) object_p, feature_p);
    }
    else if(object_p instanceof RolePkg) {
      ret = StructureHelper.getInstance().doSwitch((RolePkg) object_p, feature_p);
    }
    else if(object_p instanceof EntityPkg) {
      ret = StructureHelper.getInstance().doSwitch((EntityPkg) object_p, feature_p);
    }
    else if(object_p instanceof CapabilityConfiguration) {
      ret = ComponentHelper.getInstance().doSwitch((CapabilityConfiguration) object_p, feature_p);
    }
    else if(object_p instanceof OrganisationalUnit) {
      ret = NamedElementHelper.getInstance().doSwitch((OrganisationalUnit) object_p, feature_p);
    }
    else if(object_p instanceof OrganisationalUnitComposition) {
      ret = NamedElementHelper.getInstance().doSwitch((OrganisationalUnitComposition) object_p, feature_p);
    }
    else if(object_p instanceof Concept) {
      ret = NamedElementHelper.getInstance().doSwitch((Concept) object_p, feature_p);
    }
    else if(object_p instanceof ConceptCompliance) {
      ret = RelationshipHelper.getInstance().doSwitch((ConceptCompliance) object_p, feature_p);
    }
    else if(object_p instanceof ConceptPkg) {
      ret = StructureHelper.getInstance().doSwitch((ConceptPkg) object_p, feature_p);
    }
    else if(object_p instanceof ItemInConcept) {
      ret = NamedElementHelper.getInstance().doSwitch((ItemInConcept) object_p, feature_p);
    }
    else if(object_p instanceof Location) {
      ret = ComponentHelper.getInstance().doSwitch((Location) object_p, feature_p);
    }
    else if(object_p instanceof CommunityOfInterest) {
      ret = NamedElementHelper.getInstance().doSwitch((CommunityOfInterest) object_p, feature_p);
    }
    else if(object_p instanceof CommunityOfInterestComposition) {
      ret = NamedElementHelper.getInstance().doSwitch((CommunityOfInterestComposition) object_p, feature_p);
    }
    else if(object_p instanceof RoleAssemblyUsage) {
      ret = NamedElementHelper.getInstance().doSwitch((RoleAssemblyUsage) object_p, feature_p);
    }
    else if(object_p instanceof EntityOperationalCapabilityInvolvement) {
      ret = EntityOperationalCapabilityInvolvementHelper.getInstance().doSwitch((EntityOperationalCapabilityInvolvement) object_p, feature_p);
    }
    else if(object_p instanceof OperationalContext) {
      ret = OperationalContextHelper.getInstance().doSwitch((OperationalContext) object_p, feature_p);
    }
    else if(object_p instanceof OperationalScenario) {
      ret = NamedElementHelper.getInstance().doSwitch((OperationalScenario) object_p, feature_p);
    }

		if(null != ret || feature_p.getUpperBound() == 1)
			return ret;
		
		throw new HelperNotFoundException();
	}

}
