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
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.fa.ComponentPort;
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
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#isActor <em>Actor</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#isHuman <em>Human</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getOwnedInterfaceUses <em>Owned Interface Uses</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getUsedInterfaceLinks <em>Used Interface Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getUsedInterfaces <em>Used Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getOwnedInterfaceImplementations <em>Owned Interface Implementations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getImplementedInterfaceLinks <em>Implemented Interface Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getImplementedInterfaces <em>Implemented Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getOwnedComponentRealizations <em>Owned Component Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getRealizedComponents <em>Realized Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getRealizingComponents <em>Realizing Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getProvidedInterfaces <em>Provided Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getRequiredInterfaces <em>Required Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getContainedComponentPorts <em>Contained Component Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getContainedParts <em>Contained Parts</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getContainedPhysicalPorts <em>Contained Physical Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getOwnedPhysicalPath <em>Owned Physical Path</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getOwnedPhysicalLinks <em>Owned Physical Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getOwnedPhysicalLinkCategories <em>Owned Physical Link Categories</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Component#getRepresentingParts <em>Representing Parts</em>}</li>
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
public interface Component extends Block, Classifier, InterfaceAllocator, CommunicationLinkExchanger {





	/**
   * Returns the value of the '<em><b>Actor</b></em>' attribute.
   * The default value is <code>"false"</code>.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Actor</em>' attribute.
   * @see #setActor(boolean)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_Actor()
   * @model default="false" required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Indicates if the Component is an Actor' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isActor();







	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.Component#isActor <em>Actor</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Actor</em>' attribute.
   * @see #isActor()
   * @generated
   */

	void setActor(boolean value);







	/**
   * Returns the value of the '<em><b>Human</b></em>' attribute.
   * The default value is <code>"false"</code>.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Human</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Human</em>' attribute.
   * @see #setHuman(boolean)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_Human()
   * @model default="false" required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Indicates whether the Component is a Human' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isHuman();







	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.Component#isHuman <em>Human</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Human</em>' attribute.
   * @see #isHuman()
   * @generated
   */

	void setHuman(boolean value);







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
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
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
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedInterfaceUses'"
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
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='usedInterfaceLinks.usedInterface'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the Interfaces being used by this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
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
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedInterfaceImplementations'"
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
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='implementedInterfaceLinks.implementedInterface'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the Interfaces being implemented by this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Interface> getImplementedInterfaces();







	/**
   * Returns the value of the '<em><b>Owned Component Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.ComponentRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Component Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Component Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_OwnedComponentRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Component Realization links owned by this Component' constraints='none' comment/notes='none'"
   * @generated
   */

	EList<ComponentRealization> getOwnedComponentRealizations();







	/**
   * Returns the value of the '<em><b>Realized Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Component}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Components</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_RealizedComponents()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Component.outgoingTraces(self, outgoingTraces);\r\nComponentRealization.realizedComponent(outgoingTraces, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the components being allocated from this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Component> getRealizedComponents();







	/**
   * Returns the value of the '<em><b>Realizing Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Component}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Components</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_RealizingComponents()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Component.incomingTraces(self, incomingTraces);\r\nComponentRealization.realizingComponent(incomingTraces, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the components allocating this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<Component> getRealizingComponents();







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
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='containedComponentPorts.providedInterfaces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the Interfaces being provided by this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
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
   * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_RequiredInterfaces()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='requiredInterfaces'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='containedComponentPorts.requiredInterfaces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the Interfaces being required by this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
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
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedFeatures'"
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
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedFeatures'"
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
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedFeatures'"
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
   *        annotation="http://www.polarsys.org/capella/semantic"
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
   *        annotation="http://www.polarsys.org/capella/semantic"
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
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PhysicalLinkCategory> getOwnedPhysicalLinkCategories();







	/**
   * Returns the value of the '<em><b>Representing Parts</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Part}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Representing Parts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Representing Parts</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getComponent_RepresentingParts()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Parts that represent this Component' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='typedElements'"
   * @generated
   */

	EList<Part> getRepresentingParts();





} // Component
