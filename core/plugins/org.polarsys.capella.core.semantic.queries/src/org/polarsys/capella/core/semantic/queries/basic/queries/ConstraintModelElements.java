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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.sirius.analysis.CsServices;

/**
 * Return the contained model element of current constraint
 *
 */
public class ConstraintModelElements implements IQuery {

	/**
	 * 
	 */
	public ConstraintModelElements() {
	  // do nothing
	}

	/** 
	 *  
	 * current.eContainer
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof Constraint) {
			Constraint current = (Constraint) object;
			EList<ModelElement> constrainedElements = current.getConstrainedElements();
			if (!constrainedElements.isEmpty()) {
        if (CsServices.getService().isMultipartMode(constrainedElements.get(0))) {
          result.addAll(constrainedElements);
        } else {
          // in monopart mode, if element is part, we display the related component
          List<ModelElement> constrainedElementsForMonopart = constrainedElements.stream()
              .map(element -> (element instanceof Part) ? ((Part) element).getAbstractType() : element).distinct()
              .collect(Collectors.toList());
          result.addAll(constrainedElementsForMonopart);
        }
			}
		}
		return result;
	}
}
