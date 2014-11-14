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

import org.eclipse.emf.common.util.EList;
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
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Get all properties available in the given block architecture and its allocated architectures
	 * </p>
	 * <p>
	 * Except The current properties itself
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element_p instanceof ExchangeItemElement) {
			availableElements.addAll(getRule_MQRY_ExchangeItemElement_AvailableProperties_11((ExchangeItemElement) element_p));
			availableElements = ListExt.removeDuplicates(availableElements);
		}
		return availableElements;
	}

	/** 
	 * same level Visibility Layer
	 * @param exchange_p
	 */
	private List<CapellaElement> getRule_MQRY_ExchangeItemElement_AvailableProperties_11(ExchangeItemElement exchange_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(exchange_p);
		if (null != currentBlockArchitecture) {
			availableElements.addAll(getElementsFromBlockArchitecture(currentBlockArchitecture, exchange_p));
			for (BlockArchitecture previousBlockArchitecture : BlockArchitectureExt.getPreviousBlockArchitectures(currentBlockArchitecture)) {
				availableElements.addAll(getElementsFromBlockArchitecture(previousBlockArchitecture, exchange_p));
			}
		}
		return availableElements;
	}

	/** 
	 * @param arch_p
	 * @param exchangeItemEle_p
	 * @return
	 */
	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p, ExchangeItemElement exchangeItemEle_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		AbstractType abstractType = exchangeItemEle_p.getAbstractType();
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
		for (CapellaElement elt : getCurrentElements(exchangeItemEle_p, false)) {
			availableElements.remove(elt);
		}
		availableElements.remove(exchangeItemEle_p);
		return availableElements;
	}

	/** 
	 * <p>
	 * Gets the type of current ExchangeItemElement
	 * </p>
	 * <p>
	 * Refer MQRY_ExchangeItemElement_ReferencedProperties_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element_p instanceof ExchangeItemElement) {
			ExchangeItemElement exchange = (ExchangeItemElement) element_p;
			currentElements.addAll(exchange.getReferencedProperties());
		}
		return currentElements;
	}

}