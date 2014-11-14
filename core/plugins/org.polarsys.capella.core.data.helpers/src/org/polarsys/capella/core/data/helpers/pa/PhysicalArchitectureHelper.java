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
package org.polarsys.capella.core.data.helpers.pa;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.ComponentInstanceHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.LogicalActorRealizationHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.LogicalArchitectureRealizationHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.LogicalComponentRealizationHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.LogicalInterfaceRealizationHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.PaArchitectureHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.PhysicalActorHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.PhysicalComponentHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.PhysicalContextHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.PhysicalFunctionHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.PhysicalNodeHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.PortInstanceHelper;
import org.polarsys.capella.core.data.pa.LogicalActorRealization;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalContext;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.data.pa.PhysicalNode;
import org.polarsys.capella.core.data.pa.deployment.ComponentInstance;
import org.polarsys.capella.core.data.pa.deployment.ConnectionInstance;
import org.polarsys.capella.core.data.pa.deployment.DeploymentAspect;
import org.polarsys.capella.core.data.pa.deployment.DeploymentConfiguration;
import org.polarsys.capella.core.data.pa.deployment.InstanceDeploymentLink;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.data.pa.deployment.PortInstance;
import org.polarsys.capella.core.data.pa.deployment.TypeDeploymentLink;
import org.polarsys.capella.common.tig.model.HelperNotFoundException;
import org.polarsys.capella.common.tig.model.IHelper;

public class PhysicalArchitectureHelper implements IHelper {

	public Object getValue(EObject object_p, EStructuralFeature feature_p, EAnnotation annotation_p) {
		Object ret = null;

		if (object_p instanceof PhysicalArchitecture) {
			ret = PaArchitectureHelper.getInstance().doSwitch((PhysicalArchitecture) object_p, feature_p);
		}
		else if (object_p instanceof PhysicalNode) {
			ret = PhysicalNodeHelper.getInstance().doSwitch((PhysicalNode) object_p, feature_p);
		}
		else if (object_p instanceof PhysicalComponent) {
			ret = PhysicalComponentHelper.getInstance().doSwitch((PhysicalComponent) object_p, feature_p);
		}
		else if (object_p instanceof PhysicalContext) {
			ret = PhysicalContextHelper.getInstance().doSwitch((PhysicalContext) object_p, feature_p);
		}
		else if (object_p instanceof PhysicalActor) {
			ret = PhysicalActorHelper.getInstance().doSwitch((PhysicalActor) object_p, feature_p);
		}
		else if (object_p instanceof PhysicalFunction) {
			ret = PhysicalFunctionHelper.getInstance().doSwitch((PhysicalFunction) object_p, feature_p);
		}
		else if (object_p instanceof LogicalArchitectureRealization) {
			ret = LogicalArchitectureRealizationHelper.getInstance().doSwitch((LogicalArchitectureRealization) object_p, feature_p);
		}
		else if (object_p instanceof LogicalComponentRealization) {
			ret = LogicalComponentRealizationHelper.getInstance().doSwitch((LogicalComponentRealization) object_p, feature_p);
		}
		else if (object_p instanceof LogicalInterfaceRealization) {
			ret = LogicalInterfaceRealizationHelper.getInstance().doSwitch((LogicalInterfaceRealization) object_p, feature_p);
		}
		else if (object_p instanceof LogicalActorRealization) {
			ret = LogicalActorRealizationHelper.getInstance().doSwitch((LogicalActorRealization) object_p, feature_p);
		}
    else if (object_p instanceof PhysicalFunctionPkg) {
      ret = StructureHelper.getInstance().doSwitch((PhysicalFunctionPkg) object_p, feature_p);
    }
    else if (object_p instanceof PhysicalComponentPkg) {
      ret = StructureHelper.getInstance().doSwitch((PhysicalComponentPkg) object_p, feature_p);
    }
    else if (object_p instanceof PhysicalActorPkg) {
      ret = StructureHelper.getInstance().doSwitch((PhysicalActorPkg) object_p, feature_p);
    }
    else if (object_p instanceof TypeDeploymentLink) {
      ret = RelationshipHelper.getInstance().doSwitch((TypeDeploymentLink) object_p, feature_p);
    }
    else if (object_p instanceof PartDeploymentLink) {
      ret = RelationshipHelper.getInstance().doSwitch((PartDeploymentLink) object_p, feature_p);
    }
    else if (object_p instanceof InstanceDeploymentLink) {
      ret = RelationshipHelper.getInstance().doSwitch((InstanceDeploymentLink) object_p, feature_p);
    }
    else if (object_p instanceof DeploymentAspect) {
      ret = StructureHelper.getInstance().doSwitch((DeploymentAspect) object_p, feature_p);
    }
    else if (object_p instanceof PhysicalArchitecturePkg) {
      ret = StructureHelper.getInstance().doSwitch((PhysicalArchitecturePkg) object_p, feature_p);
    }
    else if (object_p instanceof DeploymentConfiguration) {
      ret = NamedElementHelper.getInstance().doSwitch((DeploymentConfiguration) object_p, feature_p);
    }
    else if (object_p instanceof PortInstance) {
      ret = PortInstanceHelper.getInstance().doSwitch((PortInstance) object_p, feature_p);
    }
    else if (object_p instanceof ComponentInstance) {
      ret = ComponentInstanceHelper.getInstance().doSwitch((ComponentInstance) object_p, feature_p);
    }
    else if (object_p instanceof ConnectionInstance) {
      ret = CapellaElementHelper.getInstance().doSwitch((ConnectionInstance) object_p, feature_p);
    }

		if (null != ret || feature_p.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}
}
