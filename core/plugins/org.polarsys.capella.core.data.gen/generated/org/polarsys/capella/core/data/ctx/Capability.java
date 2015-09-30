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
package org.polarsys.capella.core.data.ctx;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.oa.OperationalCapability;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Capability</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getOwnedActorCapabilityInvolvements <em>Owned Actor Capability Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getOwnedSystemCapabilityInvolvement <em>Owned System Capability Involvement</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getInvolvedActors <em>Involved Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getInvolvedSystem <em>Involved System</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getParticipatingActors <em>Participating Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getParticipatingSystem <em>Participating System</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getPurposes <em>Purposes</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getPurposeMissions <em>Purpose Missions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getRealizedOperationalCapabilities <em>Realized Operational Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.Capability#getRealizingCapabilityRealizations <em>Realizing Capability Realizations</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Capability'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.Capability'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Ability of an organisation, system or process to provide a service that supports the achievement of high-level operational goals' usage\040guideline='n/a' used\040in\040levels='system' usage\040examples='../img/usage_examples/example_actor_capability.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::UseCase' explanation='none' constraints='none'"
 * @generated
 */
public interface Capability extends AbstractCapability {





	/**
	 * Returns the value of the '<em><b>Owned Actor Capability Involvements</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Actor Capability Involvements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Actor Capability Involvements</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_OwnedActorCapabilityInvolvements()
	 * @model containment="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedActorCapabilityInvolvements'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the links between Actors and Capabilities that are owned by this Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='none' constraints='uml::Package::packagedElement elements on which ActorCapabilityInvolvement stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
	 * @generated
	 */

	EList<ActorCapabilityInvolvement> getOwnedActorCapabilityInvolvements();







	/**
	 * Returns the value of the '<em><b>Owned System Capability Involvement</b></em>' containment reference.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned System Capability Involvement</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned System Capability Involvement</em>' containment reference.
	 * @see #setOwnedSystemCapabilityInvolvement(SystemCapabilityInvolvement)
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_OwnedSystemCapabilityInvolvement()
	 * @model containment="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedCapabilitySupplierLinks'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the links between the System and Capabilities, that are owned by this Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='none' constraints='uml::Package::packagedElement elements on which SystemCapabilityInvolvement stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]'"
	 * @generated
	 */

	SystemCapabilityInvolvement getOwnedSystemCapabilityInvolvement();




	/**
	 * Sets the value of the '{@link org.polarsys.capella.core.data.ctx.Capability#getOwnedSystemCapabilityInvolvement <em>Owned System Capability Involvement</em>}' containment reference.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned System Capability Involvement</em>' containment reference.
	 * @see #getOwnedSystemCapabilityInvolvement()
	 * @generated
	 */

	void setOwnedSystemCapabilityInvolvement(SystemCapabilityInvolvement value);







	/**
	 * Returns the value of the '<em><b>Involved Actors</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement#getCapability <em>Capability</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved Actors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Involved Actors</em>' reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_InvolvedActors()
	 * @see org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement#getCapability
	 * @model opposite="capability" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='involvedActors'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the links between Actors and this Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<ActorCapabilityInvolvement> getInvolvedActors();







	/**
	 * Returns the value of the '<em><b>Involved System</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement#getCapability <em>Capability</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved System</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Involved System</em>' reference.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_InvolvedSystem()
	 * @see org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement#getCapability
	 * @model opposite="capability" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='capabilitySupplierLinks'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the link to the System being involved in this Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	SystemCapabilityInvolvement getInvolvedSystem();







	/**
	 * Returns the value of the '<em><b>Participating Actors</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.Actor}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.Actor#getContributedCapabilities <em>Contributed Capabilities</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participating Actors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participating Actors</em>' reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_ParticipatingActors()
	 * @see org.polarsys.capella.core.data.ctx.Actor#getContributedCapabilities
	 * @model opposite="contributedCapabilities" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='participatingActors'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the Actors involved with this Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<Actor> getParticipatingActors();







	/**
	 * Returns the value of the '<em><b>Participating System</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.System#getContributedCapabilities <em>Contributed Capabilities</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participating System</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participating System</em>' reference.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_ParticipatingSystem()
	 * @see org.polarsys.capella.core.data.ctx.System#getContributedCapabilities
	 * @model opposite="contributedCapabilities" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='participatingSystems'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the System involved in this Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	org.polarsys.capella.core.data.ctx.System getParticipatingSystem();







	/**
	 * Returns the value of the '<em><b>Purposes</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.CapabilityExploitation}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.CapabilityExploitation#getCapability <em>Capability</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Purposes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Purposes</em>' reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_Purposes()
	 * @see org.polarsys.capella.core.data.ctx.CapabilityExploitation#getCapability
	 * @model opposite="capability"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping umlOppositeReference='addition' umlOppositeReferenceOwner='Include'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='purposes'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the links to the Mission(s) that this Capability supports\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::Include::addition' constraints='uml::NamedElement::clientDependency elements on which CapabilityExploitation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
	 * @generated
	 */

	EList<CapabilityExploitation> getPurposes();







	/**
	 * Returns the value of the '<em><b>Purpose Missions</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.Mission}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.Mission#getExploitedCapabilities <em>Exploited Capabilities</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Purpose Missions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Purpose Missions</em>' reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_PurposeMissions()
	 * @see org.polarsys.capella.core.data.ctx.Mission#getExploitedCapabilities
	 * @model opposite="exploitedCapabilities" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='purposeMissions'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the Mission(s) that this Capability supports\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
	 * @generated
	 */

	EList<Mission> getPurposeMissions();







	/**
	 * Returns the value of the '<em><b>Realized Operational Capabilities</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.oa.OperationalCapability}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.oa.OperationalCapability#getRealizingCapabilities <em>Realizing Capabilities</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Operational Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realized Operational Capabilities</em>' reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_RealizedOperationalCapabilities()
	 * @see org.polarsys.capella.core.data.oa.OperationalCapability#getRealizingCapabilities
	 * @model opposite="realizingCapabilities" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<OperationalCapability> getRealizedOperationalCapabilities();







	/**
	 * Returns the value of the '<em><b>Realizing Capability Realizations</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.la.CapabilityRealization}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilities <em>Realized Capabilities</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Capability Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realizing Capability Realizations</em>' reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getCapability_RealizingCapabilityRealizations()
	 * @see org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilities
	 * @model opposite="realizedCapabilities" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<CapabilityRealization> getRealizingCapabilityRealizations();





} // Capability
