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
package org.polarsys.capella.core.data.information;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.Interface;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.Port#getIncomingPortRealizations <em>Incoming Port Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Port#getOutgoingPortRealizations <em>Outgoing Port Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Port#getOwnedProtocols <em>Owned Protocols</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Port#getIncomingPortAllocations <em>Incoming Port Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Port#getOutgoingPortAllocations <em>Outgoing Port Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Port#getProvidedInterfaces <em>Provided Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Port#getRequiredInterfaces <em>Required Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Port#getOwnedPortRealizations <em>Owned Port Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Port#getOwnedPortAllocations <em>Owned Port Allocations</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.InformationPackage#getPort()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Port'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Port'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A port is an interaction point between a block or part and its environment that\r\nis connected with other ports via connectors\r\n[source: SysML specification v1.1]' usage\040guideline='n/a (Abstract)' used\040in\040levels='operational,system,logical,physical' arcardia_description='The connection point of an exchange on an entity is called a port.' usage\040examples='../img/usage_examples/ports_exchanges.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Port' constraints='none'"
 * @generated
 */
public interface Port extends NamedElement {





	/**
   * Returns the value of the '<em><b>Incoming Port Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.PortRealization}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.information.PortRealization#getRealizedPort <em>Realized Port</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Port Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Incoming Port Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getPort_IncomingPortRealizations()
   * @see org.polarsys.capella.core.data.information.PortRealization#getRealizedPort
   * @model opposite="realizedPort" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='incomingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='contains the list of port realization link(s) pointing from other (typically lower level) port(s) to this port\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PortRealization> getIncomingPortRealizations();







	/**
   * Returns the value of the '<em><b>Outgoing Port Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.PortRealization}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.information.PortRealization#getRealizingPort <em>Realizing Port</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Port Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Outgoing Port Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getPort_OutgoingPortRealizations()
   * @see org.polarsys.capella.core.data.information.PortRealization#getRealizingPort
   * @model opposite="realizingPort" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outgoingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of port realization links starting from this port, and pointing to other (typically higher-level) ports.\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PortRealization> getOutgoingPortRealizations();







	/**
   * Returns the value of the '<em><b>Owned Protocols</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.StateMachine}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Protocols</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Protocols</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getPort_OwnedProtocols()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='allows to associate state machines to this port, specifying the communication protocol of incoming data\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='Elements are contained in the nearest possible parent container.' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<StateMachine> getOwnedProtocols();







	/**
   * Returns the value of the '<em><b>Incoming Port Allocations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.PortAllocation}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.information.PortAllocation#getAllocatedPort <em>Allocated Port</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Port Allocations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Incoming Port Allocations</em>' reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getPort_IncomingPortAllocations()
   * @see org.polarsys.capella.core.data.information.PortAllocation#getAllocatedPort
   * @model opposite="allocatedPort" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='incomingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of allocation links pointing from other model elements, to this port\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PortAllocation> getIncomingPortAllocations();







	/**
   * Returns the value of the '<em><b>Outgoing Port Allocations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.PortAllocation}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.information.PortAllocation#getAllocatingPort <em>Allocating Port</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Port Allocations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Outgoing Port Allocations</em>' reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getPort_OutgoingPortAllocations()
   * @see org.polarsys.capella.core.data.information.PortAllocation#getAllocatingPort
   * @model opposite="allocatingPort" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outgoingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of allocations links, starting from this port towards other model elements to which this port needs to be allocated\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PortAllocation> getOutgoingPortAllocations();







	/**
   * Returns the value of the '<em><b>Provided Interfaces</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Interface}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provided Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Provided Interfaces</em>' reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getPort_ProvidedInterfaces()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='lists the Interfaces that are provided through this port\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Interface> getProvidedInterfaces();







	/**
   * Returns the value of the '<em><b>Required Interfaces</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Interface}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Required Interfaces</em>' reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getPort_RequiredInterfaces()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='lists the Interfaces that are required by this port\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Interface> getRequiredInterfaces();







	/**
   * Returns the value of the '<em><b>Owned Port Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.PortRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Port Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Port Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getPort_OwnedPortRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the port realizations links that are owned/contained in this Port\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='Elements are contained in the nearest possible parent container.' constraints='Some elements on which PortRealization stereotype or any stereotype that inherits from it is applied'"
   * @generated
   */

	EList<PortRealization> getOwnedPortRealizations();







	/**
   * Returns the value of the '<em><b>Owned Port Allocations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.PortAllocation}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Port Allocations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Port Allocations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getPort_OwnedPortAllocations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the port allocation links that are owned/contained in this Port\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='Elements are contained in the nearest possible parent container.' constraints='Some elements on which PortAllocation stereotype or any stereotype that inherits from it is applied'"
   * @generated
   */

	EList<PortAllocation> getOwnedPortAllocations();





} // Port
