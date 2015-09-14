/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.communication;

import org.polarsys.capella.core.data.capellacore.Allocation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link Allocation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkAllocation#getAllocatingLink <em>Allocating Link</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkAllocation#getAllocatedLink <em>Allocated Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkAllocation()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specific kind of allocation link, between two CommunicationLinks\r\n' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='SysML::Allocations::Allocate' explanation='none' constraints='none'"
 * @generated
 */
public interface CommunicationLinkAllocation extends Allocation {





	/**
	 * Returns the value of the '<em><b>Allocating Link</b></em>' reference.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocating Link</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocating Link</em>' reference.
	 * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkAllocation_AllocatingLink()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the \"source\" of the allocation link : the link that is allocating the other link' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	CommunicationLink getAllocatingLink();







	/**
	 * Returns the value of the '<em><b>Allocated Link</b></em>' reference.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Link</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocated Link</em>' reference.
	 * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkAllocation_AllocatedLink()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the \"destination\" of the allocation link : the link that is being allocated by another link' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	CommunicationLink getAllocatedLink();





} // CommunicationLinkAllocation
