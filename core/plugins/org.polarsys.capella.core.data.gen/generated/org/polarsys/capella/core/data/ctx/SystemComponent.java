/**
 *
 *  Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
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
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#isDataComponent <em>Data Component</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getInvolvingCapabilities <em>Involving Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getCapabilityInvolvements <em>Capability Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getInvolvingMissions <em>Involving Missions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getMissionInvolvements <em>Mission Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getRealizedEntities <em>Realized Entities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponent#getRealizingLogicalComponents <em>Realizing Logical Components</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponent()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='SystemComponent'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Component'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An entity, with discrete structure within the system, that interacts with other Components of the system, thereby contributing at its lowest level to the system properties and characteristics.\r\n[source: Sys EM , ISO/IEC CD 15288]' usage\040guideline='n/a' used\040in\040levels='n/a (abstract)' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */

public interface SystemComponent extends Component, InvolvedElement {





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
	 * @generated
	 */

	EList<LogicalComponent> getRealizingLogicalComponents();





} // SystemComponent
