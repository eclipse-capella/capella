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
 * A representation of the model object '<em><b>Actor Mission Involvement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.ActorMissionInvolvement#getActor <em>Actor</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.ActorMissionInvolvement#getMission <em>Mission</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getActorMissionInvolvement()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ActorMissionInvolvement'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Dependency' stereotype='eng.ActorMissionInvolvement'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link between a system actor and a system mission that means the actor is involved in the mission\r\n[source:Capella study]' usage\040guideline='n/a' used\040in\040levels='system' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Dependency' explanation='none' constraints='none'"
 * @generated
 */
public interface ActorMissionInvolvement extends Involvement {





	/**
	 * Returns the value of the '<em><b>Actor</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.Actor#getParticipationsInMissions <em>Participations In Missions</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actor</em>' reference.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getActorMissionInvolvement_Actor()
	 * @see org.polarsys.capella.core.data.ctx.Actor#getParticipationsInMissions
	 * @model opposite="participationsInMissions" required="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='supplier' featureOwner='Dependency'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='actor'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to a system actor that is involved in the system mission\r\n[source:Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	Actor getActor();







	/**
	 * Returns the value of the '<em><b>Mission</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.Mission#getInvolvedActors <em>Involved Actors</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mission</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mission</em>' reference.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getActorMissionInvolvement_Mission()
	 * @see org.polarsys.capella.core.data.ctx.Mission#getInvolvedActors
	 * @model opposite="involvedActors" required="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='client' featureOwner='Dependency'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='mission'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the system mission related to the actor\r\n[source:Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	Mission getMission();





} // ActorMissionInvolvement
