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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class PhysicalComponent_deployedPhysicalComponents implements IQuery {

	/**
	 * 
	 */
	public PhysicalComponent_deployedPhysicalComponents() {
    // do nothing
	}

	/**
	 * 
	 * current.deployments.deployedElement
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof PhysicalComponent) {
		  EList<AbstractTypedElement> abstractTypedElements = ((PhysicalComponent) object).getAbstractTypedElements();
		  for (AbstractTypedElement abstractTypedElement : abstractTypedElements) {
        if (abstractTypedElement instanceof Part) {
          List<DeployableElement> deployedElements = PartExt.getDeployedElements((Part) abstractTypedElement);
          for (DeployableElement deployableElement : deployedElements) {
            if (deployableElement instanceof Part) {
              Part part = (Part) deployableElement;
              AbstractType abstractType = part.getAbstractType();
              if(null != abstractType && abstractType instanceof Component) 
                result.add(abstractType); 
            }
          }
        }
      }
		}
        return result;
	}

}
