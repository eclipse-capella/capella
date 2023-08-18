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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link Exchanger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getOwnedCommunicationLinks <em>Owned Communication Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getProduce <em>Produce</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getConsume <em>Consume</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getSend <em>Send</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getReceive <em>Receive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getCall <em>Call</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getExecute <em>Execute</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getWrite <em>Write</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getAccess <em>Access</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getAcquire <em>Acquire</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getTransmit <em>Transmit</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkExchanger()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='describes an element which can communicate using ExchangeItems' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 * @generated
 */
public interface CommunicationLinkExchanger extends EObject {





	/**
   * Returns the value of the '<em><b>Owned Communication Links</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.communication.CommunicationLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Communication Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Communication Links</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkExchanger_OwnedCommunicationLinks()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedCommunicationLinks'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of all communication links owned by the communication exchanger' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<CommunicationLink> getOwnedCommunicationLinks();







	/**
   * Returns the value of the '<em><b>Produce</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.communication.CommunicationLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Produce</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Produce</em>' reference list.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkExchanger_Produce()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::PRODUCE);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of all production CommunicationLinks' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<CommunicationLink> getProduce();







	/**
   * Returns the value of the '<em><b>Consume</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.communication.CommunicationLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Consume</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Consume</em>' reference list.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkExchanger_Consume()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::CONSUME);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of all consumption CommunicationLinks' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<CommunicationLink> getConsume();







	/**
   * Returns the value of the '<em><b>Send</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.communication.CommunicationLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Send</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Send</em>' reference list.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkExchanger_Send()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::SEND);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of all sending CommunicationLinks' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<CommunicationLink> getSend();







	/**
   * Returns the value of the '<em><b>Receive</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.communication.CommunicationLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Receive</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Receive</em>' reference list.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkExchanger_Receive()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::RECEIVE);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of all receiving CommunicationLinks' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<CommunicationLink> getReceive();







	/**
   * Returns the value of the '<em><b>Call</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.communication.CommunicationLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Call</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Call</em>' reference list.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkExchanger_Call()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::CALL);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of all calling CommunicationLinks' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<CommunicationLink> getCall();







	/**
   * Returns the value of the '<em><b>Execute</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.communication.CommunicationLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execute</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Execute</em>' reference list.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkExchanger_Execute()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::EXECUTE);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of all execution CommunicationLinks' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<CommunicationLink> getExecute();







	/**
   * Returns the value of the '<em><b>Write</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.communication.CommunicationLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Write</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Write</em>' reference list.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkExchanger_Write()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::WRITE);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of all writing CommunicationLinks' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<CommunicationLink> getWrite();







	/**
   * Returns the value of the '<em><b>Access</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.communication.CommunicationLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Access</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Access</em>' reference list.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkExchanger_Access()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::ACCESS);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of all accessing CommunicationLinks' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<CommunicationLink> getAccess();







	/**
   * Returns the value of the '<em><b>Acquire</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.communication.CommunicationLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Acquire</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Acquire</em>' reference list.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkExchanger_Acquire()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::ACQUIRE);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of all acquiring CommunicationLinks' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<CommunicationLink> getAcquire();







	/**
   * Returns the value of the '<em><b>Transmit</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.communication.CommunicationLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transmit</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Transmit</em>' reference list.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkExchanger_Transmit()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::TRANSMIT);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of all transmitting CommunicationLinks' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<CommunicationLink> getTransmit();





} // CommunicationLinkExchanger
