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

import org.polarsys.capella.core.data.capellacore.Involvement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Mission Involvement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemMissionInvolvement#getMission <em>Mission</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemMissionInvolvement#getSystem <em>System</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemMissionInvolvement()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='MissionSupplierLink'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Dependency' stereotype='eng.MissionSupplierLink'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link between a system and a mission meaning that the system is involved in the mission\r\n[source:Capella study]' usage\040guideline='n/a' used\040in\040levels='system' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Dependency' explanation='none' constraints='none'"
 * @generated
 */
public interface SystemMissionInvolvement extends Involvement {





	/**
	 * Returns the value of the '<em><b>Mission</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.Mission#getInvolvedSystem <em>Involved System</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mission</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mission</em>' reference.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemMissionInvolvement_Mission()
	 * @see org.polarsys.capella.core.data.ctx.Mission#getInvolvedSystem
	 * @model opposite="involvedSystem" required="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='client' featureOwner='Dependency'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='mission'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to a Mission that the System is involved in\r\n[source:Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	Mission getMission();







	/**
	 * Returns the value of the '<em><b>System</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.System#getParticipationsInMissions <em>Participations In Missions</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System</em>' reference.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemMissionInvolvement_System()
	 * @see org.polarsys.capella.core.data.ctx.System#getParticipationsInMissions
	 * @model opposite="participationsInMissions" required="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='supplier' featureOwner='Dependency'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='system'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to a system that is involved in the system mission\r\n[source:Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	org.polarsys.capella.core.data.ctx.System getSystem();





} // SystemMissionInvolvement
