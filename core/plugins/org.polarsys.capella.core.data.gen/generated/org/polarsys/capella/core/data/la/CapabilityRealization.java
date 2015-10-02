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
package org.polarsys.capella.core.data.la;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.interaction.AbstractCapability;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Capability Realization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealization#getOwnedActorCapabilityRealizations <em>Owned Actor Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealization#getOwnedSystemComponentCapabilityRealizations <em>Owned System Component Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealization#getParticipatingActors <em>Participating Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealization#getParticipatingSystemComponents <em>Participating System Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealization#getInvolvedActors <em>Involved Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealization#getInvolvedSystemComponents <em>Involved System Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilities <em>Realized Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilityRealizations <em>Realized Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealization#getRealizingCapabilityRealizations <em>Realizing Capability Realizations</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='CapabilityRealization'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.CapabilityRealization'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a realization of a capability of the above abstraction level\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='logical' usage\040examples='../img/usage_examples/example_capabilityrealizationlogical.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::UseCase' explanation='needs to be mapped to UseCase since its parent is mapped to UseCase...and has many references mapped to UseCase\'s references' constraints='none'"
 * @generated
 */
public interface CapabilityRealization extends AbstractCapability {





	/**
	 * Returns the value of the '<em><b>Owned Actor Capability Realizations</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Actor Capability Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Actor Capability Realizations</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization_OwnedActorCapabilityRealizations()
	 * @model containment="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedActorCapabilityRealizations'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the actor capability realization links that are contained in this CapabilityRealization\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='Elements are contained in the nearest possible parent container.' constraints='uml::Package::packagedElement elements on which ActorCapabilityRealizationInvolvement stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
	 * @generated
	 */

	EList<ActorCapabilityRealizationInvolvement> getOwnedActorCapabilityRealizations();







	/**
	 * Returns the value of the '<em><b>Owned System Component Capability Realizations</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned System Component Capability Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned System Component Capability Realizations</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization_OwnedSystemComponentCapabilityRealizations()
	 * @model containment="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedSystemComponentCapabilityRealizations'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the system component realization links that are contained/owned by this capability realization\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='Elements are contained in the nearest possible parent container.' constraints='uml::Package::packagedElement elements on which SystemComponentCapabilityRealizationInvolvement stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
	 * @generated
	 */

	EList<SystemComponentCapabilityRealizationInvolvement> getOwnedSystemComponentCapabilityRealizations();







	/**
	 * Returns the value of the '<em><b>Participating Actors</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.AbstractActor}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participating Actors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participating Actors</em>' reference list.
	 * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization_ParticipatingActors()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='participatingActors'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the actors which capabilities are being realized by this CapabilityRealization\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<AbstractActor> getParticipatingActors();







	/**
	 * Returns the value of the '<em><b>Participating System Components</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.SystemComponent}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participating System Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participating System Components</em>' reference list.
	 * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization_ParticipatingSystemComponents()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='participatingSystemComponents'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the system components being realized by this CapabilityRealization\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<SystemComponent> getParticipatingSystemComponents();







	/**
	 * Returns the value of the '<em><b>Involved Actors</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved Actors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Involved Actors</em>' reference list.
	 * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization_InvolvedActors()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='involvedActors'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) references to the involvement links in ActorCapabilityRealizations\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<ActorCapabilityRealizationInvolvement> getInvolvedActors();







	/**
	 * Returns the value of the '<em><b>Involved System Components</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved System Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Involved System Components</em>' reference list.
	 * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization_InvolvedSystemComponents()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='involvedSystemComponents'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) references to the involvement links in SystemComponentRealizations for this CapabilityRealization\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<SystemComponentCapabilityRealizationInvolvement> getInvolvedSystemComponents();







	/**
	 * Returns the value of the '<em><b>Realized Capabilities</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.Capability}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.Capability#getRealizingCapabilityRealizations <em>Realizing Capability Realizations</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realized Capabilities</em>' reference list.
	 * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization_RealizedCapabilities()
	 * @see org.polarsys.capella.core.data.ctx.Capability#getRealizingCapabilityRealizations
	 * @model opposite="realizingCapabilityRealizations" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<Capability> getRealizedCapabilities();







	/**
	 * Returns the value of the '<em><b>Realized Capability Realizations</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.la.CapabilityRealization}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.CapabilityRealization#getRealizingCapabilityRealizations <em>Realizing Capability Realizations</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Capability Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realized Capability Realizations</em>' reference list.
	 * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization_RealizedCapabilityRealizations()
	 * @see org.polarsys.capella.core.data.la.CapabilityRealization#getRealizingCapabilityRealizations
	 * @model opposite="realizingCapabilityRealizations" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<CapabilityRealization> getRealizedCapabilityRealizations();







	/**
	 * Returns the value of the '<em><b>Realizing Capability Realizations</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.la.CapabilityRealization}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilityRealizations <em>Realized Capability Realizations</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Capability Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realizing Capability Realizations</em>' reference list.
	 * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealization_RealizingCapabilityRealizations()
	 * @see org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilityRealizations
	 * @model opposite="realizedCapabilityRealizations" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<CapabilityRealization> getRealizingCapabilityRealizations();





} // CapabilityRealization
