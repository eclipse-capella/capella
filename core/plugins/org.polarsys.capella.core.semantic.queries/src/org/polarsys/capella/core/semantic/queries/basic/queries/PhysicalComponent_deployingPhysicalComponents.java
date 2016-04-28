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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class PhysicalComponent_deployingPhysicalComponents implements IQuery {

	/**
	 * 
	 */
	public PhysicalComponent_deployingPhysicalComponents() {
    // do nothing
	}

	/**
	 * 
	 * current.deployingLinks.location
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof PhysicalComponent) {
			PhysicalComponent c = (PhysicalComponent) object;
			EList<AbstractTypedElement> abstractTypedElements = c.getAbstractTypedElements();
			for (AbstractTypedElement abstractTypedElement : abstractTypedElements) {
			  if (abstractTypedElement instanceof Part) {
	        List<DeploymentTarget> deployingElements = PartExt.getDeployingElements((Part) abstractTypedElement);
	        for (DeploymentTarget deploymentTarget : deployingElements) {
            if (deploymentTarget instanceof Part) {
              AbstractType abstractType = ((Part) deploymentTarget).getAbstractType();
              if (null != abstractType && abstractType instanceof Component) {
                result.add(abstractType);
              }
            }
          }
        }
      }
		}
        return result;
	}

}
