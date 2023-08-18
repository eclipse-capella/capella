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
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentPort#getOrientation <em>Orientation</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentPort#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentPort#getComponentExchanges <em>Component Exchanges</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentPort#getAllocatedFunctionPorts <em>Allocated Function Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentPort#getDelegatedComponentPorts <em>Delegated Component Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentPort#getDelegatingComponentPorts <em>Delegating Component Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentPort#getAllocatingPhysicalPorts <em>Allocating Physical Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentPort#getRealizedComponentPorts <em>Realized Component Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentPort#getRealizingComponentPorts <em>Realizing Component Ports</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentPort()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A component port is the unification of the standard port and the flow port.' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface ComponentPort extends Port, InformationsExchanger, Property {





	/**
   * Returns the value of the '<em><b>Orientation</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.fa.OrientationPortKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orientation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Orientation</em>' attribute.
   * @see org.polarsys.capella.core.data.fa.OrientationPortKind
   * @see #setOrientation(OrientationPortKind)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentPort_Orientation()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the orientation of a component port. ' constraints='should be set only when the component port is a flow port' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	OrientationPortKind getOrientation();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.ComponentPort#getOrientation <em>Orientation</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Orientation</em>' attribute.
   * @see org.polarsys.capella.core.data.fa.OrientationPortKind
   * @see #getOrientation()
   * @generated
   */

	void setOrientation(OrientationPortKind value);







	/**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.fa.ComponentPortKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.fa.ComponentPortKind
   * @see #setKind(ComponentPortKind)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentPort_Kind()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A component port is the unification of the standard port and the flow port.\r\nsee the ComponentPortKind enumeration.\r\n' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ComponentPortKind getKind();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.ComponentPort#getKind <em>Kind</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.fa.ComponentPortKind
   * @see #getKind()
   * @generated
   */

	void setKind(ComponentPortKind value);







	/**
   * Returns the value of the '<em><b>Component Exchanges</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchange}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Exchanges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Component Exchanges</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentPort_ComponentExchanges()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='ComponentPort.informationFlows(self, target);\r\n} or {\r\n\tComponentExchangeEnd.port(cee, self);\r\n\tComponentExchange.ownedComponentExchangeEnds(target, cee);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentExchange> getComponentExchanges();







	/**
   * Returns the value of the '<em><b>Allocated Function Ports</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionPort}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.FunctionPort#getAllocatorComponentPorts <em>Allocator Component Ports</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Function Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Function Ports</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentPort_AllocatedFunctionPorts()
   * @see org.polarsys.capella.core.data.fa.FunctionPort#getAllocatorComponentPorts
   * @model opposite="allocatorComponentPorts" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outgoingPortAllocations.allocatedPort'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<FunctionPort> getAllocatedFunctionPorts();







	/**
   * Returns the value of the '<em><b>Delegated Component Ports</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentPort}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ComponentPort#getDelegatingComponentPorts <em>Delegating Component Ports</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delegated Component Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Delegated Component Ports</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentPort_DelegatedComponentPorts()
   * @see org.polarsys.capella.core.data.fa.ComponentPort#getDelegatingComponentPorts
   * @model opposite="delegatingComponentPorts" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='ComponentPort.outgoingInformationFlows(self, ce);\r\nComponentExchange.kind(ce, ::DELEGATION);\r\nComponentExchange.targetPort(ce, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentPort> getDelegatedComponentPorts();







	/**
   * Returns the value of the '<em><b>Delegating Component Ports</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentPort}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ComponentPort#getDelegatedComponentPorts <em>Delegated Component Ports</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delegating Component Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Delegating Component Ports</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentPort_DelegatingComponentPorts()
   * @see org.polarsys.capella.core.data.fa.ComponentPort#getDelegatedComponentPorts
   * @model opposite="delegatedComponentPorts" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='ComponentPort.incomingInformationFlows(self, ce);\r\nComponentExchange.kind(ce, ::DELEGATION);\r\nComponentExchange.sourcePort(ce, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentPort> getDelegatingComponentPorts();







	/**
   * Returns the value of the '<em><b>Allocating Physical Ports</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalPort}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.PhysicalPort#getAllocatedComponentPorts <em>Allocated Component Ports</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocating Physical Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocating Physical Ports</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentPort_AllocatingPhysicalPorts()
   * @see org.polarsys.capella.core.data.cs.PhysicalPort#getAllocatedComponentPorts
   * @model opposite="allocatedComponentPorts" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='ComponentPort.incomingTraces(self, cpa);\r\nComponentPortAllocation.allocatingPort(cpa, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PhysicalPort> getAllocatingPhysicalPorts();







	/**
   * Returns the value of the '<em><b>Realized Component Ports</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentPort}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ComponentPort#getRealizingComponentPorts <em>Realizing Component Ports</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Component Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Component Ports</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentPort_RealizedComponentPorts()
   * @see org.polarsys.capella.core.data.fa.ComponentPort#getRealizingComponentPorts
   * @model opposite="realizingComponentPorts" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outgoingPortRealizations.realizedPort'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentPort> getRealizedComponentPorts();







	/**
   * Returns the value of the '<em><b>Realizing Component Ports</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentPort}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ComponentPort#getRealizedComponentPorts <em>Realized Component Ports</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Component Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Component Ports</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentPort_RealizingComponentPorts()
   * @see org.polarsys.capella.core.data.fa.ComponentPort#getRealizedComponentPorts
   * @model opposite="realizedComponentPorts" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='incomingPortRealizations.realizingPort'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentPort> getRealizingComponentPorts();





} // ComponentPort
