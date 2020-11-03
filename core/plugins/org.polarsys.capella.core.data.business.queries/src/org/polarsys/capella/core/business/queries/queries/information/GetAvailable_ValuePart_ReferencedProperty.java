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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.ComplexValue;
import org.polarsys.capella.core.data.information.datavalue.ValuePart;

public class GetAvailable_ValuePart_ReferencedProperty extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
		if (element instanceof ValuePart) {
			ValuePart elt = (ValuePart) element;
			if (elt.eContainer() instanceof ComplexValue) {
				AbstractType type = ((ComplexValue) elt.eContainer()).getAbstractType();
				if (type instanceof GeneralizableElement) {
					List<GeneralizableElement> superTypes = new ArrayList<GeneralizableElement>();
					superTypes.addAll(((GeneralizableElement) type).getSuper());
					superTypes.add((GeneralizableElement) type);
					for (GeneralizableElement superType : superTypes) {
						if (superType instanceof Classifier) {
							for (Feature feature : ((Classifier) superType).getOwnedFeatures()) {
								if (feature instanceof Property) {
									returnValue.add(feature);
								}
							}
						}
					}
				}
			}
		}
		return returnValue;
	}

}