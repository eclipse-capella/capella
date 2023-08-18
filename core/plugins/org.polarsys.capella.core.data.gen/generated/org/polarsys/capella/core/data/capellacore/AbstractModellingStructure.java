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
 * A representation of the model object '<em><b>Abstract Modelling Structure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.AbstractModellingStructure#getOwnedArchitectures <em>Owned Architectures</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.AbstractModellingStructure#getOwnedArchitecturePkgs <em>Owned Architecture Pkgs</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getAbstractModellingStructure()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An abstract modelling structure is a base structure for a model.\r\nFor example, a system engineering is an abstract modelling structure.\r\n[source:Capella study]' usage\040guideline='System enginering is an abstract modelling structure\r\n[source:Capella study]' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='' constraints='None' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface AbstractModellingStructure extends ReuserStructure {





	/**
   * Returns the value of the '<em><b>Owned Architectures</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.ModellingArchitecture}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Architectures</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Architectures</em>' containment reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getAbstractModellingStructure_OwnedArchitectures()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the modeling architectures contained in this structure\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which ModellingArchitecture stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<ModellingArchitecture> getOwnedArchitectures();







	/**
   * Returns the value of the '<em><b>Owned Architecture Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.ModellingArchitecturePkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Architecture Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Architecture Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getAbstractModellingStructure_OwnedArchitecturePkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the architecture packages contained in this structure\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which ModellingArchitecturePkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<ModellingArchitecturePkg> getOwnedArchitecturePkgs();





} // AbstractModellingStructure
