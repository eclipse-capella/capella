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
package org.polarsys.capella.core.data.information.communication;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.ExchangeItem;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLink#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLink#getProtocol <em>Protocol</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLink#getExchangeItem <em>Exchange Item</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLink()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='describes a link of communication using exchange items' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface CommunicationLink extends CapellaElement {





	/**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.information.communication.CommunicationLinkKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkKind
   * @see #setKind(CommunicationLinkKind)
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLink_Kind()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='refer to CommunicationLinkKind description' constraints='none' type='refer to CommunicationLinkKind definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	CommunicationLinkKind getKind();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.communication.CommunicationLink#getKind <em>Kind</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkKind
   * @see #getKind()
   * @generated
   */

	void setKind(CommunicationLinkKind value);







	/**
   * Returns the value of the '<em><b>Protocol</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Protocol</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Protocol</em>' attribute.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol
   * @see #setProtocol(CommunicationLinkProtocol)
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLink_Protocol()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='refer to CommunicationLinkProtocol description' constraints='none' type='refer to CommunicationLinkProtocol definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	CommunicationLinkProtocol getProtocol();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.communication.CommunicationLink#getProtocol <em>Protocol</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Protocol</em>' attribute.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol
   * @see #getProtocol()
   * @generated
   */

	void setProtocol(CommunicationLinkProtocol value);







	/**
   * Returns the value of the '<em><b>Exchange Item</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exchange Item</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Exchange Item</em>' reference.
   * @see #setExchangeItem(ExchangeItem)
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLink_ExchangeItem()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='describes the exchange item related to the link' constraints='none' type='refer to CommunicationLinkProtocol definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ExchangeItem getExchangeItem();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.communication.CommunicationLink#getExchangeItem <em>Exchange Item</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Exchange Item</em>' reference.
   * @see #getExchangeItem()
   * @generated
   */

	void setExchangeItem(ExchangeItem value);





} // CommunicationLink
