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
package org.polarsys.capella.core.data.capellacore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Structure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.Structure#getOwnedPropertyValuePkgs <em>Owned Property Value Pkgs</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getStructure()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Structure'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Element' stereotype='eng.CapellaElement'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The relationships between the components that contribute to the properties of the whole, and enable them to interact (inter-relate).\r\n[source: SysML glossary for SysML v1.0]' usage\040guideline='n/a (abstract)' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface Structure extends Namespace {





	/**
   * Returns the value of the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.PropertyValuePkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Property Value Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Property Value Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getStructure_OwnedPropertyValuePkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to packages that contain light extensions property values\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which PropertyValuePkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PropertyValuePkg> getOwnedPropertyValuePkgs();





} // Structure
