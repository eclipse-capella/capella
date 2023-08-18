/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.oa;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.cs.ComponentPkg;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.EntityPkg#getOwnedEntities <em>Owned Entities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.EntityPkg#getOwnedEntityPkgs <em>Owned Entity Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.EntityPkg#getOwnedLocations <em>Owned Locations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.EntityPkg#getOwnedCommunicationMeans <em>Owned Communication Means</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getEntityPkg()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Container for operational entities\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface EntityPkg extends ComponentPkg {





	/**
   * Returns the value of the '<em><b>Owned Entity Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.EntityPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Entity Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Entity Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getEntityPkg_OwnedEntityPkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='sub-(Entity)packages contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which EntityPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<EntityPkg> getOwnedEntityPkgs();







	/**
   * Returns the value of the '<em><b>Owned Entities</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.Entity}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Entities</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Entities</em>' containment reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getEntityPkg_OwnedEntities()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Entity elements contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which Entity stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Entity> getOwnedEntities();







	/**
   * Returns the value of the '<em><b>Owned Locations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.Location}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Locations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Locations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getEntityPkg_OwnedLocations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Location elements contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which Location stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<Location> getOwnedLocations();







	/**
   * Returns the value of the '<em><b>Owned Communication Means</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.CommunicationMean}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Communication Means</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Communication Means</em>' containment reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getEntityPkg_OwnedCommunicationMeans()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the CommunicationMean elements contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which CommunicationMean stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<CommunicationMean> getOwnedCommunicationMeans();





} // EntityPkg
