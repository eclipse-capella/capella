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
package org.polarsys.capella.core.data.oa;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.ctx.Actor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operational Actor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalActor#getRealizingSystemActors <em>Realizing System Actors</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalActor()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A person or organization playing a role within an operational process.' usage\040guideline='n/a' arcadia_description='An actor is a [usually human] non-decomposable operational Entity. Example: User of a radio set; radio station...' used\040in\040levels='operational' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='SysML ::Blocks ::Block' constraints='none'"
 * @generated
 */
public interface OperationalActor extends Entity {





	/**
	 * Returns the value of the '<em><b>Realizing System Actors</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.Actor}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.Actor#getRealizedOperationalActors <em>Realized Operational Actors</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing System Actors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realizing System Actors</em>' reference list.
	 * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalActor_RealizingSystemActors()
	 * @see org.polarsys.capella.core.data.ctx.Actor#getRealizedOperationalActors
	 * @model opposite="realizedOperationalActors" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<Actor> getRealizingSystemActors();





} // OperationalActor
