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

	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element instanceof Union) {
			Union union = (Union) element;
			availableElements.addAll(getDiscreteDatatypesProperties(union));
			List<GeneralizableElement> unionInheritanceHierarchy = GeneralizableElementExt.getAllSuperGeneralizableElements(union);
			for (CapellaElement elt : unionInheritanceHierarchy) {
				if (elt instanceof Union) {
					Union inheritedUnion = (Union) elt;
					availableElements.addAll(getDiscreteDatatypesProperties(inheritedUnion));
				}
			}
		}
		return availableElements;
	}

	/** 
	 * Gets the owned properties of the given <code>Union</code> which are both DataType and Discrete
	 * @param union the union
	 * @return a <code>List</code> containing <code>CapellaElement</code> instances
	 */
	protected List<CapellaElement> getDiscreteDatatypesProperties(Union union) {
		List<CapellaElement> properties = new ArrayList<CapellaElement>();
		for (Feature aFeature : union.getOwnedFeatures()) {
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