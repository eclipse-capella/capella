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
package org.polarsys.capella.core.data.cs;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getOwnedInterfaceUses <em>Owned Interface Uses</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getUsedInterfaceLinks <em>Used Interface Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getUsedInterfaces <em>Used Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getOwnedInterfaceImplementations <em>Owned Interface Implementations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getImplementedInterfaceLinks <em>Implemented Interface Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getImplementedInterfaces <em>Implemented Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getProvisionedComponentAllocations <em>Provisioned Component Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getProvisioningComponentAllocations <em>Provisioning Component Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getAllocatedComponents <em>Allocated Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getAllocatingComponents <em>Allocating Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getProvidedInterfaces <em>Provided Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getRequiredInterfaces <em>Required Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getContainedComponentPorts <em>Contained Component Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getContainedParts <em>Contained Parts</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getContainedPhysicalPorts <em>Contained Physical Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getOwnedPhysicalPath <em>Owned Physical Path</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getOwnedPhysicalLinks <em>Owned Physical Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getOwnedPhysicalLinkCategories <em>Owned Physical Link Categories</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Component'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Component'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An entity, with discrete structure within the system, that interacts with other Components of the system, thereby contributing at its lowest level to the system properties and characteristics.\r\n[source: Sys EM , ISO/IEC CD 15288]' arcadia_description='A component is a constituent part of the system, contributing to its behaviour, by interacting with other components and external actors, thereby contributing at its lowest level to the system properties and characteristics. Example: radio receiver, graphical user interface...\r\nDifferent kinds of components exist: see below (logical component, physical component...).' usage\040guideline='none' used\040in\040levels='n/a (abstract)' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Class' constraints='none'"
 * @generated
 */
public interface Component extends Block, PartitionableElement, InterfaceAllocator, CommunicationLinkExchanger {





	/**
	 * Returns the value of the '<em><b>Owned Interface Uses</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.InterfaceUse}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Interface Uses</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Interface Uses</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_OwnedInterfaceUses()
	 * @model containment="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Component'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedInterfaceUses'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='InterfaceUse relationships that are stored/owned under this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='none' constraints='uml::NamedElement::clientDependency elements on which InterfaceUse stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
	 * @generated
	 */

	EList<InterfaceUse> getOwnedInterfaceUses();







	/**
	 * Returns the value of the '<em><b>Used Interface Links</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.InterfaceUse}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.InterfaceUse#getInterfaceUser <em>Interface User</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Used Interface Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Used Interface Links</em>' reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_UsedInterfaceLinks()
	 * @see org.polarsys.capella.core.data.cs.InterfaceUse#getInterfaceUser
	 * @model opposite="interfaceUser" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='usedInterfaceLinks'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) interfaceUse relationships that involve this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<InterfaceUse> getUsedInterfaceLinks();







	/**
	 * Returns the value of the '<em><b>Used Interfaces</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.Interface}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.Interface#getUserComponents <em>User Components</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Used Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Used Interfaces</em>' reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_UsedInterfaces()
	 * @see org.polarsys.capella.core.data.cs.Interface#getUserComponents
	 * @model opposite="userComponents" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='usedInterfaces'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the Interfaces being used by this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<Interface> getUsedInterfaces();







	/**
	 * Returns the value of the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.InterfaceImplementation}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Interface Implementations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Interface Implementations</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_OwnedInterfaceImplementations()
	 * @model containment="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Interface implementation relationships that are stored/owned under this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::BehavioredClassifier::interfaceRealization' explanation='none' constraints='Order must be computed'"
	 * @generated
	 */

	EList<InterfaceImplementation> getOwnedInterfaceImplementations();







	/**
	 * Returns the value of the '<em><b>Implemented Interface Links</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.InterfaceImplementation}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.InterfaceImplementation#getInterfaceImplementor <em>Interface Implementor</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implemented Interface Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implemented Interface Links</em>' reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_ImplementedInterfaceLinks()
	 * @see org.polarsys.capella.core.data.cs.InterfaceImplementation#getInterfaceImplementor
	 * @model opposite="interfaceImplementor" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='interfaceRealization' featureOwner='BehavioredClassifier'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='realizedInterfaceLinks'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of InterfaceImplementation links that involve this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<InterfaceImplementation> getImplementedInterfaceLinks();







	/**
	 * Returns the value of the '<em><b>Implemented Interfaces</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.Interface}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.Interface#getImplementorComponents <em>Implementor Components</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implemented Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implemented Interfaces</em>' reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_ImplementedInterfaces()
	 * @see org.polarsys.capella.core.data.cs.Interface#getImplementorComponents
	 * @model opposite="implementorComponents" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='implementedInterfaces'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the Interfaces being implemented by this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<Interface> getImplementedInterfaces();







	/**
	 * Returns the value of the '<em><b>Provisioned Component Allocations</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.ComponentAllocation}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.ComponentAllocation#getAllocatingComponent <em>Allocating Component</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provisioned Component Allocations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provisioned Component Allocations</em>' reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_ProvisionedComponentAllocations()
	 * @see org.polarsys.capella.core.data.cs.ComponentAllocation#getAllocatingComponent
	 * @model opposite="allocatingComponent" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of allocation links made from this component to other components\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<ComponentAllocation> getProvisionedComponentAllocations();







	/**
	 * Returns the value of the '<em><b>Provisioning Component Allocations</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.ComponentAllocation}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.ComponentAllocation#getAllocatedComponent <em>Allocated Component</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provisioning Component Allocations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provisioning Component Allocations</em>' reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_ProvisioningComponentAllocations()
	 * @see org.polarsys.capella.core.data.cs.ComponentAllocation#getAllocatedComponent
	 * @model opposite="allocatedComponent" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of allocation links from other components, to this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<ComponentAllocation> getProvisioningComponentAllocations();







	/**
	 * Returns the value of the '<em><b>Allocated Components</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.Component}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocated Components</em>' reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_AllocatedComponents()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the components being allocated from this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<Component> getAllocatedComponents();







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
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_AllocatingComponents()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the components allocating this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
	 * @generated
	 */

	EList<Component> getAllocatingComponents();







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
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_ProvidedInterfaces()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='providedInterfaces'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the Interfaces being provided by this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
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
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_RequiredInterfaces()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='requiredInterfaces'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the Interfaces being required by this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<Interface> getRequiredInterfaces();







	/**
	 * Returns the value of the '<em><b>Contained Component Ports</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentPort}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Component Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contained Component Ports</em>' reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_ContainedComponentPorts()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/semantic feature='ownedFeatures'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<ComponentPort> getContainedComponentPorts();







	/**
	 * Returns the value of the '<em><b>Contained Parts</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.Part}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Parts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contained Parts</em>' reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_ContainedParts()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/semantic feature='ownedFeatures'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<Part> getContainedParts();







	/**
	 * Returns the value of the '<em><b>Contained Physical Ports</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalPort}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Physical Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contained Physical Ports</em>' reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_ContainedPhysicalPorts()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/semantic feature='ownedFeatures'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<PhysicalPort> getContainedPhysicalPorts();







	/**
	 * Returns the value of the '<em><b>Owned Physical Path</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalPath}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Physical Path</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Physical Path</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_OwnedPhysicalPath()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the PhysicalPaths that are stored/owned by this physical component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='SysML::Blocks::Block cannot contain PhysicalPath\'s equivalent, hence we find the nearest available package to store them.' constraints='none'"
	 * @generated
	 */

	EList<PhysicalPath> getOwnedPhysicalPath();







	/**
	 * Returns the value of the '<em><b>Owned Physical Links</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalLink}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Physical Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Physical Links</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_OwnedPhysicalLinks()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Physical links contained in / owned by this Physical component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::StructuredClassifier::ownedConnector' explanation='since PhysicalLink is mapped to uml::Connector' constraints='none'"
	 * @generated
	 */

	EList<PhysicalLink> getOwnedPhysicalLinks();







	/**
	 * Returns the value of the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalLinkCategory}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Physical Link Categories</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Physical Link Categories</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_OwnedPhysicalLinkCategories()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
	 * @generated
	 */

	EList<PhysicalLinkCategory> getOwnedPhysicalLinkCategories();





} // Component
