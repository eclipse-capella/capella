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
package org.polarsys.capella.core.business.queries.information;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * The Query return all the properties of the typed Class(including its ancestor) of the current ExchangeItemElement 
 */
public class ExchangeItemElement_ReferencedProperties implements IBusinessQuery {

  /**
   * @param arch_p
   * @param exchangeItemEle_p
   * @return
   */
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p, ExchangeItemElement exchangeItemEle_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

    // Get the Abstract Type of EIE
    AbstractType abstractType = exchangeItemEle_p.getAbstractType();
    // filter Class
    if (abstractType instanceof Class) {
      Class cls = (Class) abstractType;
      // Collect all super Classes
      List<GeneralizableElement> allSuperGeneralizableElements = GeneralizableElementExt.getAllSuperGeneralizableElements(cls);
      // add current class
      allSuperGeneralizableElements.add(cls);
      // iterate over all the collected classes
      for (GeneralizableElement generalizableElement : allSuperGeneralizableElements) {
        if (generalizableElement instanceof Class) {
          Class genEleClass = (Class) generalizableElement;
          // get properties of the class
          EList<Property> properties = genEleClass.getContainedProperties();
          if (!properties.isEmpty()) {
            // add to the list
            availableElements.addAll(properties);
          }
        }
      }
    }
    
   

    // remove existing from the availableElements
    for (CapellaElement elt : getCurrentElements(exchangeItemEle_p, false)) {
      availableElements.remove(elt);
    }
    availableElements.remove(exchangeItemEle_p);

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
	 * <p>
	 * Get all properties available in the given block architecture and its allocated architectures
	 * </p>
	 * <p>
	 * Except The current properties itself
	 * </p>
	 * 
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
	 * <p>
	 * Gets the type of current ExchangeItemElement
	 * </p>
	 * <p>
	 * Refer MQRY_ExchangeItemElement_ReferencedProperties_1
	 * </p>
	 * 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element_p instanceof ExchangeItemElement) {
		  ExchangeItemElement exchange = (ExchangeItemElement) element_p;
			currentElements.addAll(exchange.getReferencedProperties());
		}
		return currentElements;
	}

	public EClass getEClass() {
		return InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT__REFERENCED_PROPERTIES);
	}
}
