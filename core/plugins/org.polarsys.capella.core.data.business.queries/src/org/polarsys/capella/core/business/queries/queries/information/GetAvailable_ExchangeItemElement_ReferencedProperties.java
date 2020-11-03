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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_ExchangeItemElement_ReferencedProperties extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Get all properties available in the given block architecture and its allocated architectures
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		if (element instanceof ExchangeItemElement) {
			availableElements.addAll(getRule_MQRY_ExchangeItemElement_AvailableProperties_11((ExchangeItemElement) element));
			availableElements = ListExt.removeDuplicates(availableElements);
		}
		return availableElements;
	}

	/** 
	 * same level Visibility Layer
	 * @param exchange
	 */
	private List<CapellaElement> getRule_MQRY_ExchangeItemElement_AvailableProperties_11(ExchangeItemElement exchange) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(exchange);
		if (null != currentBlockArchitecture) {
			availableElements.addAll(getElementsFromBlockArchitecture(currentBlockArchitecture, exchange));
			for (BlockArchitecture previousBlockArchitecture : BlockArchitectureExt.getPreviousBlockArchitectures(currentBlockArchitecture)) {
				availableElements.addAll(getElementsFromBlockArchitecture(previousBlockArchitecture, exchange));
			}
		}
		return availableElements;
	}

	/** 
	 * @param arch
	 * @param exchangeItemEle
	 * @return
	 */
	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch, ExchangeItemElement exchangeItemEle) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		AbstractType abstractType = exchangeItemEle.getAbstractType();
		if (abstractType instanceof Class) {
			Class cls = (Class) abstractType;
			List<GeneralizableElement> allSuperGeneralizableElements = GeneralizableElementExt.getAllSuperGeneralizableElements(cls);
			allSuperGeneralizableElements.add(cls);
			for (GeneralizableElement generalizableElement : allSuperGeneralizableElements) {
				if (generalizableElement instanceof Class) {
					Class genEleClass = (Class) generalizableElement;
					EList<Property> properties = genEleClass.getContainedProperties();
					if (!properties.isEmpty()) {
						availableElements.addAll(properties);
					}
				}
			}
		}
		availableElements.remove(exchangeItemEle);
		return availableElements;
	}

	/** 
	 * <p>
	 * Gets the type of current ExchangeItemElement
	 * </p>
	 * <p>
	 * Refer MQRY_ExchangeItemElement_ReferencedProperties_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(EObject,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element instanceof ExchangeItemElement) {
			ExchangeItemElement exchange = (ExchangeItemElement) element;
			currentElements.addAll(exchange.getReferencedProperties());
		}
		return currentElements;
	}

}