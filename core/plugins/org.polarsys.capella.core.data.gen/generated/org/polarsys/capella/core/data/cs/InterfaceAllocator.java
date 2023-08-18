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
package org.polarsys.capella.core.data.cs;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interface Allocator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.InterfaceAllocator#getOwnedInterfaceAllocations <em>Owned Interface Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.InterfaceAllocator#getProvisionedInterfaceAllocations <em>Provisioned Interface Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.InterfaceAllocator#getAllocatedInterfaces <em>Allocated Interfaces</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getInterfaceAllocator()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Base class for elements that need to be involved in an allocation link to/from an Interface\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Classifier' constraints='none'"
 * @generated
 */
public interface InterfaceAllocator extends CapellaElement {





	/**
   * Returns the value of the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.InterfaceAllocation}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Interface Allocations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Interface Allocations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterfaceAllocator_OwnedInterfaceAllocations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the interface allocation links that are stored/owned under this interface allocator\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='none' constraints='Some elements on which InterfaceAllocation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<InterfaceAllocation> getOwnedInterfaceAllocations();







	/**
   * Returns the value of the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.InterfaceAllocation}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.InterfaceAllocation#getAllocatingInterfaceAllocator <em>Allocating Interface Allocator</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provisioned Interface Allocations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Provisioned Interface Allocations</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterfaceAllocator_ProvisionedInterfaceAllocations()
   * @see org.polarsys.capella.core.data.cs.InterfaceAllocation#getAllocatingInterfaceAllocator
   * @model opposite="allocatingInterfaceAllocator" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outgoingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the interface allocation links involving this interface allocator\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<InterfaceAllocation> getProvisionedInterfaceAllocations();







	/**
   * Returns the value of the '<em><b>Allocated Interfaces</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Interface}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Interfaces</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterfaceAllocator_AllocatedInterfaces()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='provisionedInterfaceAllocations.allocatedInterface'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the Interfaces being allocated by this interface allocator\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Interface> getAllocatedInterfaces();





} // InterfaceAllocator
