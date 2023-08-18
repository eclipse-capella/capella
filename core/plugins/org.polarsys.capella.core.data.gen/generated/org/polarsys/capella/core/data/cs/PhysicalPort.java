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
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Physical Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.PhysicalPort#getOwnedComponentPortAllocations <em>Owned Component Port Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.PhysicalPort#getOwnedPhysicalPortRealizations <em>Owned Physical Port Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.PhysicalPort#getAllocatedComponentPorts <em>Allocated Component Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.PhysicalPort#getRealizedPhysicalPorts <em>Realized Physical Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.PhysicalPort#getRealizingPhysicalPorts <em>Realizing Physical Ports</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPort()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A port on a physical component\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='SysML::PortAndFlows::FlowPort' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface PhysicalPort extends Port, AbstractPhysicalArtifact, InformationsExchanger, AbstractPhysicalLinkEnd, Property {





	/**
   * Returns the value of the '<em><b>Owned Component Port Allocations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentPortAllocation}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Component Port Allocations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Component Port Allocations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPort_OwnedComponentPortAllocations()
   * @model containment="true" resolveProxies="true"
   * @generated
   */

	EList<ComponentPortAllocation> getOwnedComponentPortAllocations();







	/**
   * Returns the value of the '<em><b>Owned Physical Port Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalPortRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Physical Port Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Physical Port Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPort_OwnedPhysicalPortRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='none' explanation='none' constraints='none'"
   * @generated
   */

	EList<PhysicalPortRealization> getOwnedPhysicalPortRealizations();







	/**
   * Returns the value of the '<em><b>Allocated Component Ports</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentPort}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ComponentPort#getAllocatingPhysicalPorts <em>Allocating Physical Ports</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Component Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Component Ports</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPort_AllocatedComponentPorts()
   * @see org.polarsys.capella.core.data.fa.ComponentPort#getAllocatingPhysicalPorts
   * @model opposite="allocatingPhysicalPorts" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='ComponentPortAllocation.allocatingPort(ppr, self);\r\nComponentPortAllocation.allocatedPort(ppr, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentPort> getAllocatedComponentPorts();







	/**
   * Returns the value of the '<em><b>Realized Physical Ports</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalPort}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Physical Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Physical Ports</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPort_RealizedPhysicalPorts()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='PhysicalPortRealization.sourceElement(ppr, self);\r\nPhysicalPortRealization.targetElement(ppr, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PhysicalPort> getRealizedPhysicalPorts();







	/**
   * Returns the value of the '<em><b>Realizing Physical Ports</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalPort}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Physical Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Physical Ports</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPort_RealizingPhysicalPorts()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='PhysicalPortRealization.targetElement(ppr, self);\r\nPhysicalPortRealization.sourceElement(ppr, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<PhysicalPort> getRealizingPhysicalPorts();





} // PhysicalPort
