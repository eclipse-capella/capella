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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Location</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.Location#getLocationDescription <em>Location Description</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.Location#getLocatedEntities <em>Located Entities</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getLocation()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a physical place where specific entities can be located.\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational' usage\040examples='n/a' constraints='none' comment/notes='not used/implemented as of Capella 1.0.3' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Class' explanation='none' constraints='none'"
 * @generated
 */
public interface Location extends AbstractConceptItem {





	/**
   * Returns the value of the '<em><b>Location Description</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Location Description</em>' attribute.
   * @see #setLocationDescription(String)
   * @see org.polarsys.capella.core.data.oa.OaPackage#getLocation_LocationDescription()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a textual description of this location\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getLocationDescription();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.oa.Location#getLocationDescription <em>Location Description</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Location Description</em>' attribute.
   * @see #getLocationDescription()
   * @generated
   */

	void setLocationDescription(String value);







	/**
   * Returns the value of the '<em><b>Located Entities</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.Entity}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Located Entities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Located Entities</em>' reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getLocation_LocatedEntities()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the operational entities assigned to this location\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<Entity> getLocatedEntities();





} // Location
