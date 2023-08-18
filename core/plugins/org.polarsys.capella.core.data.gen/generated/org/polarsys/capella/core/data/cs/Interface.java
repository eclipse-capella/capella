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
import org.polarsys.capella.core.data.capellacore.GeneralClass;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.ExchangeItem;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interface</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getMechanism <em>Mechanism</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#isStructural <em>Structural</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getImplementorComponents <em>Implementor Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getUserComponents <em>User Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getInterfaceImplementations <em>Interface Implementations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getInterfaceUses <em>Interface Uses</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getProvisioningInterfaceAllocations <em>Provisioning Interface Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getAllocatingInterfaces <em>Allocating Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getAllocatingComponents <em>Allocating Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getExchangeItems <em>Exchange Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getOwnedExchangeItemAllocations <em>Owned Exchange Item Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getRequiringComponents <em>Requiring Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getRequiringComponentPorts <em>Requiring Component Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getProvidingComponents <em>Providing Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getProvidingComponentPorts <em>Providing Component Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getRealizingLogicalInterfaces <em>Realizing Logical Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getRealizedContextInterfaces <em>Realized Context Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getRealizingPhysicalInterfaces <em>Realizing Physical Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Interface#getRealizedLogicalInterfaces <em>Realized Logical Interfaces</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Interface'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Interface' stereotype='eng.Interface'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An interface is a kind of classifier that represents a declaration of a set of coherent public features and obligations. An\r\ninterface specifies a contract; any instance of a classifier that realizes the interface must fulfill that contract.\r\n[source: UML superstructure v2.2]\r\n\r\nInterfaces are defined by functional and physical characteristics that exist at a common boundary with co-functioning items and allow systems, equipment, software, and system data to be compatible.\r\n[source: not precised]\r\n\r\nThat design feature of one piece of equipment that affects a design feature of another piece of equipment. \r\nAn interface can extend beyond the physical boundary between two items. (For example, the weight and center of gravity of one item can affect the interfacing item; however, the center of gravity is rarely located at the physical boundary.\r\nAn electrical interface generally extends to the first isolating element rather than terminating at a series of connector pins.)' usage\040guideline='In Capella, Interfaces are created to declare the nature of interactions between the System and external actors.' used\040in\040levels='system/logical/physical' usage\040examples='../img/usage_examples/external_interface_example.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Interface' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface Interface extends GeneralClass, InterfaceAllocator {





	/**
   * Returns the value of the '<em><b>Mechanism</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mechanism</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Mechanism</em>' attribute.
   * @see #setMechanism(String)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_Mechanism()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='mechanism' featureOwner='eng.Interface' fromStereotype='true'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='_todo_reviewed : cannot find the meaning of this attribute ? How to fill it ?' constraints='none' type='_todo_reviewed : to be precised' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getMechanism();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.Interface#getMechanism <em>Mechanism</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mechanism</em>' attribute.
   * @see #getMechanism()
   * @generated
   */

	void setMechanism(String value);







	/**
   * Returns the value of the '<em><b>Structural</b></em>' attribute.
   * The default value is <code>"true"</code>.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Structural</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Structural</em>' attribute.
   * @see #setStructural(boolean)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_Structural()
   * @model default="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' type='n/a' comment/notes='none'"
   * @generated
   */

	boolean isStructural();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.Interface#isStructural <em>Structural</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Structural</em>' attribute.
   * @see #isStructural()
   * @generated
   */

	void setStructural(boolean value);




	/**
   * Returns the value of the '<em><b>Implementor Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Component}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.Component#getImplementedInterfaces <em>Implemented Interfaces</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementor Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Implementor Components</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_ImplementorComponents()
   * @see org.polarsys.capella.core.data.cs.Component#getImplementedInterfaces
   * @model opposite="implementedInterfaces" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='implementorComponents'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='interfaceImplementations.interfaceImplementor'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='references to the components that implement this interface\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<Component> getImplementorComponents();







	/**
   * Returns the value of the '<em><b>User Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Component}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.Component#getUsedInterfaces <em>Used Interfaces</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>User Components</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_UserComponents()
   * @see org.polarsys.capella.core.data.cs.Component#getUsedInterfaces
   * @model opposite="usedInterfaces" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='userComponents'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='interfaceUses.interfaceUser'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='references to the components that use this interface\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<Component> getUserComponents();







	/**
   * Returns the value of the '<em><b>Interface Implementations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.InterfaceImplementation}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface Implementations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Interface Implementations</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_InterfaceImplementations()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='interfaceImplementations'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='implementedInterface'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='references to the InterfaceImplementation elements, that act as mediators between this interface and its implementers\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::InterfaceRealization::contract' constraints='uml::Element::ownedElement elements on which InterfaceImplementation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping umlOppositeReference='contract' umlOppositeReferenceOwner='InterfaceRealization'"
   * @generated
   */

	EList<InterfaceImplementation> getInterfaceImplementations();







	/**
   * Returns the value of the '<em><b>Interface Uses</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.InterfaceUse}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface Uses</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Interface Uses</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_InterfaceUses()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='interfaceUses'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='usedInterface'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='references to the InterfaceUse elements, that act as mediator classes between this interface and its users\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::Dependency::supplier' constraints='uml::Element::ownedElement elements on which InterfaceUse stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping umlOppositeReference='supplier' umlOppositeReferenceOwner='Dependency'"
   * @generated
   */

	EList<InterfaceUse> getInterfaceUses();







	/**
   * Returns the value of the '<em><b>Provisioning Interface Allocations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.InterfaceAllocation}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.InterfaceAllocation#getAllocatedInterface <em>Allocated Interface</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provisioning Interface Allocations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Provisioning Interface Allocations</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_ProvisioningInterfaceAllocations()
   * @see org.polarsys.capella.core.data.cs.InterfaceAllocation#getAllocatedInterface
   * @model opposite="allocatedInterface" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='incomingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='References to the InterfaceAllocation elements, acting as mediator classes between the interface and the elements to which/from which it is allocated\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<InterfaceAllocation> getProvisioningInterfaceAllocations();







	/**
   * Returns the value of the '<em><b>Allocating Interfaces</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Interface}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocating Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocating Interfaces</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_AllocatingInterfaces()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='provisioningInterfaceAllocations.allocatingInterfaceAllocator'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='References to the Interfaces that declare an allocation link to this Interface\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Interface> getAllocatingInterfaces();







	/**
   * Returns the value of the '<em><b>Allocating Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Component}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocating Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocating Components</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_AllocatingComponents()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='provisioningInterfaceAllocations.allocatingInterfaceAllocator'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='References to the components that declare an allocation link to this Interface\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Component> getAllocatingComponents();







	/**
   * Returns the value of the '<em><b>Exchange Items</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.ExchangeItem}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exchange Items</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Exchange Items</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_ExchangeItems()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedExchangeItemAllocations.allocatedItem'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='References to all exchange items allocated by the interface' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<ExchangeItem> getExchangeItems();







	/**
   * Returns the value of the '<em><b>Owned Exchange Item Allocations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.ExchangeItemAllocation}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Exchange Item Allocations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Exchange Item Allocations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_OwnedExchangeItemAllocations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='References to allocations of exchange items' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ExchangeItemAllocation> getOwnedExchangeItemAllocations();







	/**
   * Returns the value of the '<em><b>Requiring Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Component}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requiring Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Requiring Components</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_RequiringComponents()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Interface.requiringComponentPorts(self, port);\r\nComponent.containedComponentPorts(target, port);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Component> getRequiringComponents();







	/**
   * Returns the value of the '<em><b>Requiring Component Ports</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentPort}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requiring Component Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Requiring Component Ports</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_RequiringComponentPorts()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='requiredInterfaces'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentPort> getRequiringComponentPorts();







	/**
   * Returns the value of the '<em><b>Providing Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Component}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Providing Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Providing Components</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_ProvidingComponents()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Interface.providingComponentPorts(self, port);\r\nComponent.containedComponentPorts(target, port);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Component> getProvidingComponents();







	/**
   * Returns the value of the '<em><b>Providing Component Ports</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentPort}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Providing Component Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Providing Component Ports</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_ProvidingComponentPorts()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='providedInterfaces'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ComponentPort> getProvidingComponentPorts();







	/**
   * Returns the value of the '<em><b>Realizing Logical Interfaces</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Interface}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Logical Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Logical Interfaces</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_RealizingLogicalInterfaces()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='ContextInterfaceRealization.targetElement(cir, self); \r\nContextInterfaceRealization.sourceElement(cir, target); '"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Interface> getRealizingLogicalInterfaces();







	/**
   * Returns the value of the '<em><b>Realized Context Interfaces</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Interface}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Context Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Context Interfaces</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_RealizedContextInterfaces()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='ContextInterfaceRealization.sourceElement(cir, self);\r\nContextInterfaceRealization.targetElement(cir, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Interface> getRealizedContextInterfaces();







	/**
   * Returns the value of the '<em><b>Realizing Physical Interfaces</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Interface}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Physical Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Physical Interfaces</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_RealizingPhysicalInterfaces()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='LogicalInterfaceRealization.targetElement(cir, self); \r\nLogicalInterfaceRealization.sourceElement(cir, target); '"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Interface> getRealizingPhysicalInterfaces();







	/**
   * Returns the value of the '<em><b>Realized Logical Interfaces</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Interface}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Logical Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Logical Interfaces</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterface_RealizedLogicalInterfaces()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='LogicalInterfaceRealization.sourceElement(cir, self);\r\nLogicalInterfaceRealization.targetElement(cir, target); '"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Interface> getRealizedLogicalInterfaces();





} // Interface
