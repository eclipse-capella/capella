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
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.System#getContributedCapabilities <em>Contributed Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.System#getParticipationsInCapabilities <em>Participations In Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.System#getContributedMissions <em>Contributed Missions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.System#getParticipationsInMissions <em>Participations In Missions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.System#getExternalCommunication <em>External Communication</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.System#getOwnedEntityRealizations <em>Owned Entity Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.System#getAllocatedEntityRealizations <em>Allocated Entity Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.System#getAllocatedSystemFunctions <em>Allocated System Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.System#getRealizedEntities <em>Realized Entities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.System#getRealizingLogicalComponents <em>Realizing Logical Components</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystem()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='System'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Component' stereotype='eng.System'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An organized set of elements functioning as a unit.\r\n[source:SysML Glossary for SysML v1.0]\r\n\r\nAn element, with structure, that exhibits observable properties and behaviors.\r\n[source:UML for System Engineering RFP]\r\n\r\nSee UML-SysML block, part, component, item\r\n[source:Capella study]\r\n' usage\040guideline='none' used\040in\040levels='system' usage\040examples='none' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='SysML::Blocks::Block' explanation='uml::Component is not part of UML4SysML, and should therefore not be used \r\nfor implementing a SysML profile' constraints='none'"
 * @generated
 */
public interface System extends Component, CapabilityRealizationInvolvedElement {





	/**
	 * Returns the value of the '<em><b>Contributed Capabilities</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.Capability}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.Capability#getParticipatingSystem <em>Participating System</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contributed Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contributed Capabilities</em>' reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystem_ContributedCapabilities()
	 * @see org.polarsys.capella.core.data.ctx.Capability#getParticipatingSystem
	 * @model opposite="participatingSystem" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='contributedCapabilitySpecificationUseCases'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Capabilities to which this System contributes\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
	 * @generated
	 */

	EList<Capability> getContributedCapabilities();







	/**
	 * Returns the value of the '<em><b>Participations In Capabilities</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement#getSystem <em>System</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participations In Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participations In Capabilities</em>' reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystem_ParticipationsInCapabilities()
	 * @see org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement#getSystem
	 * @model opposite="system" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping umlOppositeReference='supplier' umlOppositeReferenceOwner='Dependency'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='participationsInCapabilities'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the links to Capabilities to which this System contributes\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<SystemCapabilityInvolvement> getParticipationsInCapabilities();







	/**
	 * Returns the value of the '<em><b>Contributed Missions</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.Mission}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.Mission#getParticipatingSystem <em>Participating System</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contributed Missions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contributed Missions</em>' reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystem_ContributedMissions()
	 * @see org.polarsys.capella.core.data.ctx.Mission#getParticipatingSystem
	 * @model opposite="participatingSystem" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='contributedMissions'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the Missions to which this System contributes\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
	 * @generated
	 */

	EList<Mission> getContributedMissions();







	/**
	 * Returns the value of the '<em><b>Participations In Missions</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemMissionInvolvement}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.SystemMissionInvolvement#getSystem <em>System</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participations In Missions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participations In Missions</em>' reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystem_ParticipationsInMissions()
	 * @see org.polarsys.capella.core.data.ctx.SystemMissionInvolvement#getSystem
	 * @model opposite="system" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping umlOppositeReference='supplier' umlOppositeReferenceOwner='Dependency'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='participationsInMissions'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the links to the Missions to which this System contributes\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<SystemMissionInvolvement> getParticipationsInMissions();







	/**
	 * Returns the value of the '<em><b>External Communication</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemCommunicationHook}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Communication</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Communication</em>' reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystem_ExternalCommunication()
	 * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='ownedAttribute' featureOwner='StructuredClassifier'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='externalCommunications'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of communication links endpoints that are attached to this System\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
	 * @generated
	 */

	EList<SystemCommunicationHook> getExternalCommunication();







	/**
	 * Returns the value of the '<em><b>Owned Entity Realizations</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.OperationalEntityRealization}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Entity Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Entity Realizations</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystem_OwnedEntityRealizations()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the realization links from Operational entities to System entities, being owned by this System\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='Blocks cannot contain Realizations : store them in the nearest available package' constraints='Order is not preserved in the UML model.'"
	 * @generated
	 */

	EList<OperationalEntityRealization> getOwnedEntityRealizations();







	/**
	 * Returns the value of the '<em><b>Allocated Entity Realizations</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.OperationalEntityRealization}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Entity Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocated Entity Realizations</em>' reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystem_AllocatedEntityRealizations()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the links from operational entities being realized by this System\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<OperationalEntityRealization> getAllocatedEntityRealizations();







	/**
	 * Returns the value of the '<em><b>Allocated System Functions</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemFunction}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.SystemFunction#getAllocatorSystems <em>Allocator Systems</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated System Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocated System Functions</em>' reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystem_AllocatedSystemFunctions()
	 * @see org.polarsys.capella.core.data.ctx.SystemFunction#getAllocatorSystems
	 * @model opposite="allocatorSystems" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<SystemFunction> getAllocatedSystemFunctions();







	/**
	 * Returns the value of the '<em><b>Realized Entities</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.oa.Entity}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Entities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realized Entities</em>' reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystem_RealizedEntities()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<Entity> getRealizedEntities();







	/**
	 * Returns the value of the '<em><b>Realizing Logical Components</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalComponent}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.LogicalComponent#getRealizedSystems <em>Realized Systems</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Logical Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realizing Logical Components</em>' reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystem_RealizingLogicalComponents()
	 * @see org.polarsys.capella.core.data.la.LogicalComponent#getRealizedSystems
	 * @model opposite="realizedSystems" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<LogicalComponent> getRealizingLogicalComponents();





} // System
