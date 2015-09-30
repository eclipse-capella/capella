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
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.pa.PhysicalActor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Logical Actor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalActor#getOwnedSystemActorRealizations <em>Owned System Actor Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalActor#getSystemActorRealizations <em>System Actor Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalActor#getParticipationsInCapabilityRealizations <em>Participations In Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalActor#getAllocatedLogicalFunctions <em>Allocated Logical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalActor#getRealizedSystemActors <em>Realized System Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalActor#getRealizingPhysicalActors <em>Realizing Physical Actors</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalActor()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='External actor interacting with the system via logical interfaces' usage\040guideline='logical actors are typically created automatically when performing a transition of system-level actors. Additional logical actors can then be created manually.' used\040in\040levels='logical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='SysML::Blocks::Block' explanation='none' constraints='none'"
 * @generated
 */
public interface LogicalActor extends AbstractActor {





	/**
	 * Returns the value of the '<em><b>Owned System Actor Realizations</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.la.SystemActorRealization}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned System Actor Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned System Actor Realizations</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalActor_OwnedSystemActorRealizations()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of realisation links to/from system-level actor(s) that this actor hosts/contains\r\n[source: Capella study]\r\n' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::BehavioredClassifier::interfaceRealization' explanation='Elements are contained in the nearest possible parent container.' constraints='uml::BehavioredClassifier::interfaceRealization elements on which ActorRealization stereotype or any stereotype that inherits from it is applied'"
	 * @generated
	 */

	EList<SystemActorRealization> getOwnedSystemActorRealizations();







	/**
	 * Returns the value of the '<em><b>System Actor Realizations</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.la.SystemActorRealization}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Actor Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Actor Realizations</em>' reference list.
	 * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalActor_SystemActorRealizations()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the list of actor realization links where this logical actor is involved\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<SystemActorRealization> getSystemActorRealizations();







	/**
	 * Returns the value of the '<em><b>Participations In Capability Realizations</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participations In Capability Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participations In Capability Realizations</em>' reference list.
	 * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalActor_ParticipationsInCapabilityRealizations()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the involvement links between this logical actor and actor capability realizations\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<ActorCapabilityRealizationInvolvement> getParticipationsInCapabilityRealizations();







	/**
	 * Returns the value of the '<em><b>Allocated Logical Functions</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalFunction}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.LogicalFunction#getAllocatorLogicalActors <em>Allocator Logical Actors</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Logical Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocated Logical Functions</em>' reference list.
	 * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalActor_AllocatedLogicalFunctions()
	 * @see org.polarsys.capella.core.data.la.LogicalFunction#getAllocatorLogicalActors
	 * @model opposite="allocatorLogicalActors" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<LogicalFunction> getAllocatedLogicalFunctions();







	/**
	 * Returns the value of the '<em><b>Realized System Actors</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.Actor}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.Actor#getRealizingLogicalActors <em>Realizing Logical Actors</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized System Actors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realized System Actors</em>' reference list.
	 * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalActor_RealizedSystemActors()
	 * @see org.polarsys.capella.core.data.ctx.Actor#getRealizingLogicalActors
	 * @model opposite="realizingLogicalActors" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<Actor> getRealizedSystemActors();







	/**
	 * Returns the value of the '<em><b>Realizing Physical Actors</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalActor}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.pa.PhysicalActor#getRealizedLogicalActors <em>Realized Logical Actors</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Physical Actors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realizing Physical Actors</em>' reference list.
	 * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalActor_RealizingPhysicalActors()
	 * @see org.polarsys.capella.core.data.pa.PhysicalActor#getRealizedLogicalActors
	 * @model opposite="realizedLogicalActors" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<PhysicalActor> getRealizingPhysicalActors();





} // LogicalActor
