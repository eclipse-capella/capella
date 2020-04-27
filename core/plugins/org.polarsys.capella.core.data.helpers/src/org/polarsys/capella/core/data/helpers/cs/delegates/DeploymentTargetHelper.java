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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;

public class DeploymentTargetHelper {
	
	private static DeploymentTargetHelper instance;
	
	private DeploymentTargetHelper() {
	}

	public static DeploymentTargetHelper getInstance() {
		if (instance == null) {
			instance = new DeploymentTargetHelper();
		}
		return instance;
	}
	
	public Object doSwitch(DeploymentTarget element, EStructuralFeature feature) {

	    Object ret = null;

	    if (feature.equals(CsPackage.Literals.DEPLOYMENT_TARGET__DEPLOYMENT_LINKS)) {
	      ret = getDeploymentLinks(element);
	    }

	    // no helper found... searching in super classes...
	    if (null == ret) {
	      ret = NamedElementHelper.getInstance().doSwitch(element, feature);
	    }

	    return ret;
	  }

	  protected List<AbstractDeploymentLink> getDeploymentLinks(DeploymentTarget element) {
	    return EObjectExt.getReferencers(element, CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK__LOCATION);
	  }
}
