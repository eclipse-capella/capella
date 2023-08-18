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

import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exchange Item Allocation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.ExchangeItemAllocation#getSendProtocol <em>Send Protocol</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.ExchangeItemAllocation#getReceiveProtocol <em>Receive Protocol</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.ExchangeItemAllocation#getAllocatedItem <em>Allocated Item</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.ExchangeItemAllocation#getAllocatingInterface <em>Allocating Interface</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getExchangeItemAllocation()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Allocation link between exchange items and interface that support them' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Realization' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface ExchangeItemAllocation extends Relationship, AbstractEventOperation, FinalizableElement {





	/**
   * Returns the value of the '<em><b>Send Protocol</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Send Protocol</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Send Protocol</em>' attribute.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol
   * @see #setSendProtocol(CommunicationLinkProtocol)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getExchangeItemAllocation_SendProtocol()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='describes the default protocol used by the sender of the exchange item. It could be overrided by the protocol used by the given communication exchanger' constraints='none' type='refer to CommunicationLinkProtocol definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	CommunicationLinkProtocol getSendProtocol();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.ExchangeItemAllocation#getSendProtocol <em>Send Protocol</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Send Protocol</em>' attribute.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol
   * @see #getSendProtocol()
   * @generated
   */

	void setSendProtocol(CommunicationLinkProtocol value);







	/**
   * Returns the value of the '<em><b>Receive Protocol</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Receive Protocol</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Receive Protocol</em>' attribute.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol
   * @see #setReceiveProtocol(CommunicationLinkProtocol)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getExchangeItemAllocation_ReceiveProtocol()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='describes the default protocol used by the receiver of the exchange item. It could be overrided by the protocol used by the given communication exchanger' constraints='none' type='refer to CommunicationLinkProtocol definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	CommunicationLinkProtocol getReceiveProtocol();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.ExchangeItemAllocation#getReceiveProtocol <em>Receive Protocol</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Receive Protocol</em>' attribute.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol
   * @see #getReceiveProtocol()
   * @generated
   */

	void setReceiveProtocol(CommunicationLinkProtocol value);







	/**
   * Returns the value of the '<em><b>Allocated Item</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Item</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Item</em>' reference.
   * @see #setAllocatedItem(ExchangeItem)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getExchangeItemAllocation_AllocatedItem()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the exchange item that is being allocated by the interface' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ExchangeItem getAllocatedItem();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.ExchangeItemAllocation#getAllocatedItem <em>Allocated Item</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Allocated Item</em>' reference.
   * @see #getAllocatedItem()
   * @generated
   */

	void setAllocatedItem(ExchangeItem value);







	/**
   * Returns the value of the '<em><b>Allocating Interface</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocating Interface</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocating Interface</em>' reference.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getExchangeItemAllocation_AllocatingInterface()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the interface that allocated the given exchange item' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='ownedExchangeItemAllocations'"
   * @generated
   */

	Interface getAllocatingInterface();





} // ExchangeItemAllocation
