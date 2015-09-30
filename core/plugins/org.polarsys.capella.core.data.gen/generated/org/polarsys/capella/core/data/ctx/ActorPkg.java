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
import org.polarsys.capella.core.data.capellacore.Structure;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Actor Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.ActorPkg#getOwnedActors <em>Owned Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.ActorPkg#getOwnedActorPkgs <em>Owned Actor Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.ActorPkg#getOwnedSystemCommunication <em>Owned System Communication</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getActorPkg()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ActorPkg'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.ActorPkg'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Package that contains system actors\r\n[source:Capella study]' usage\040guideline='n/a' used\040in\040levels='system' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 * @generated
 */
public interface ActorPkg extends Structure {





	/**
	 * Returns the value of the '<em><b>Owned Actors</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.Actor}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Actors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Actors</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getActorPkg_OwnedActors()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedActors'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Set of system actors that are defined at that level of package\r\n[Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which Actor stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
	 * @generated
	 */

	EList<Actor> getOwnedActors();







	/**
	 * Returns the value of the '<em><b>Owned Actor Pkgs</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.ActorPkg}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Actor Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Actor Pkgs</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getActorPkg_OwnedActorPkgs()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='subActorPkgs'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Sub pakages that contain system actors\r\n[Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which ActorPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
	 * @generated
	 */

	EList<ActorPkg> getOwnedActorPkgs();







	/**
	 * Returns the value of the '<em><b>Owned System Communication</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemCommunication}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned System Communication</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned System Communication</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getActorPkg_OwnedSystemCommunication()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedSystemCommunication'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the SystemCommunication links contained in this Actor package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='Order must be computed'"
	 * @generated
	 */

	EList<SystemCommunication> getOwnedSystemCommunication();





} // ActorPkg
