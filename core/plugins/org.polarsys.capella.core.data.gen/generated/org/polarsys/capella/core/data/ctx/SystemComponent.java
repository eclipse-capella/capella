/**
 *
 *  Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.ctx;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getOwnedSystemComponents <em>Owned System Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getOwnedSystemComponentPkgs <em>Owned System Component Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#isDataComponent <em>Data Component</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getInvolvingCapabilities <em>Involving Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getCapabilityInvolvements <em>Capability Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getInvolvingMissions <em>Involving Missions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getMissionInvolvements <em>Mission Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getRealizedEntities <em>Realized Entities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getRealizingLogicalComponents <em>Realizing Logical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getAllocatedSystemFunctions <em>Allocated System Functions</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponent()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='SystemComponent'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Component'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An entity, with discrete structure within the system, that interacts with other Components of the system, thereby contributing at its lowest level to the system properties and characteristics.\r\n[source: Sys EM , ISO/IEC CD 15288]' usage\040guideline='n/a' used\040in\040levels='n/a (abstract)' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */

public interface SystemComponent extends Component, InvolvedElement {





	/**
   * Returns the value of the '<em><b>Owned System Components</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemComponent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned System Components</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned System Components</em>' containment reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponent_OwnedSystemComponents()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the System Components included in this System Component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints=''"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<SystemComponent> getOwnedSystemComponents();




	/**
   * Returns the value of the '<em><b>Owned System Component Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemComponentPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned System Component Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned System Component Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponent_OwnedSystemComponentPkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='sub-packages of this System Component' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints=''"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<SystemComponentPkg> getOwnedSystemComponentPkgs();




	/**
   * Returns the value of the '<em><b>Data Component</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Component</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Data Component</em>' attribute.
   * @see #setDataComponent(boolean)
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponent_DataComponent()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies whether or not this is a data component\r\n[source: Capella light-light study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isDataComponent();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.ctx.SystemComponent#isDataComponent <em>Data Component</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Component</em>' attribute.
   * @see #isDataComponent()
   * @generated
   */

	void setDataComponent(boolean value);







	/**
   * Returns the value of the '<em><b>Data Type</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.Classifier}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Type</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Data Type</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponent_DataType()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='data type(s) associated to this component\r\n[source: Capella light-light study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<Classifier> getDataType();







	/**
   * Returns the value of the '<em><b>Involving Capabilities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.Capability}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involving Capabilities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involving Capabilities</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponent_InvolvingCapabilities()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Capabilities that involve this System Component' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='SystemComponent.capabilityInvolvements(self, capabilityInvolvements);\r\nCapabilityInvolvement.involver(capabilityInvolvements, target);'"
   * @generated
   */

	EList<Capability> getInvolvingCapabilities();







	/**
   * Returns the value of the '<em><b>Capability Involvements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.CapabilityInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capability Involvements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Capability Involvements</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponent_CapabilityInvolvements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The Capability Involvement relationships in which this element is referenced' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/derived"
   * @generated
   */

	EList<CapabilityInvolvement> getCapabilityInvolvements();







	/**
   * Returns the value of the '<em><b>Involving Missions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.Mission}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involving Missions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involving Missions</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponent_InvolvingMissions()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Missions that involve this System Component' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='SystemComponent.missionInvolvements(self, missionInvolvements);\r\nMissionInvolvement.involver(missionInvolvements, target);'"
   * @generated
   */

	EList<Mission> getInvolvingMissions();







	/**
   * Returns the value of the '<em><b>Mission Involvements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.MissionInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mission Involvements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Mission Involvements</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponent_MissionInvolvements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The Mission Involvement relationships in which this element is referenced' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='involvingInvolvements'"
   * @generated
   */

	EList<MissionInvolvement> getMissionInvolvements();




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
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponent_RealizedEntities()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Entities that are realized by this System Component' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='realizingComponents'"
   * @generated
   */

	EList<Entity> getRealizedEntities();




	/**
   * Returns the value of the '<em><b>Realizing Logical Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalComponent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Logical Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Logical Components</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponent_RealizingLogicalComponents()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Logical Components that realize this System Components' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='realizingComponents'"
   * @generated
   */

	EList<LogicalComponent> getRealizingLogicalComponents();




	/**
   * Returns the value of the '<em><b>Allocated System Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemFunction}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated System Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated System Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponent_AllocatedSystemFunctions()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='allocatedFunctions'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<SystemFunction> getAllocatedSystemFunctions();





} // SystemComponent
