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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.datatype.DataType;

public class GetAvailable_Union_Discriminant extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element_p instanceof Union) {
			Union union = (Union) element_p;
			availableElements.addAll(getDiscreteDatatypesProperties(union));
			List<GeneralizableElement> unionInheritanceHierarchy = GeneralizableElementExt.getAllSuperGeneralizableElements(union);
			for (CapellaElement element : unionInheritanceHierarchy) {
				if (element instanceof Union) {
					Union inheritedUnion = (Union) element;
					availableElements.addAll(getDiscreteDatatypesProperties(inheritedUnion));
				}
			}
		}
		return availableElements;
	}

	/** 
	 * Gets the owned properties of the given <code>Union</code> which are both DataType and Discrete
	 * @param union_p the union
	 * @return a <code>List</code> containing <code>CapellaElement</code> instances
	 */
	protected List<CapellaElement> getDiscreteDatatypesProperties(Union union_p) {
		List<CapellaElement> properties = new ArrayList<CapellaElement>();
		for (Feature aFeature : union_p.getOwnedFeatures()) {
			if (aFeature instanceof UnionProperty) {
				Type type = ((UnionProperty) aFeature).getType();
				if (((type instanceof DataType) && ((DataType) type).isDiscrete()) || ((type instanceof Class) && ((Class) type).isIsPrimitive())) {
					properties.add(aFeature);
				}
			}
		}
		return properties;
	}

}