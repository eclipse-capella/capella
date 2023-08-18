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
package org.polarsys.capella.core.data.fa;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Exchange Allocator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchangeAllocator#getOwnedComponentExchangeAllocations <em>Owned Component Exchange Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentExchangeAllocator#getAllocatedComponentExchanges <em>Allocated Component Exchanges</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchangeAllocator()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Base class for elements that are intended to allocate to/from connections\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface ComponentExchangeAllocator extends NamedElement {





	/**
   * Returns the value of the '<em><b>Owned Component Exchange Allocations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchangeAllocation}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Component Exchange Allocations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Component Exchange Allocations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchangeAllocator_OwnedComponentExchangeAllocations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the component exchanges allocations contained in this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='Elements are contained in the nearest possible parent container.' constraints='uml::NamedElement::clientDependency elements on which ConnectionAllocation stereotype or any stereotype that inherits from it is applied'"
   * @generated
   */

	EList<ComponentExchangeAllocation> getOwnedComponentExchangeAllocations();







	/**
   * Returns the value of the '<em><b>Allocated Component Exchanges</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchange}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Component Exchanges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Component Exchanges</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchangeAllocator_AllocatedComponentExchanges()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='ComponentExchangeAllocator.outgoingTraces(self, cea);\r\nComponentExchangeAllocation.componentExchangeAllocated(cea, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the component exchanges being allocated by this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ComponentExchange> getAllocatedComponentExchanges();





} // ComponentExchangeAllocator
