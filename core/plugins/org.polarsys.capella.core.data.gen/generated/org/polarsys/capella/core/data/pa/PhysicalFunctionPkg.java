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
package org.polarsys.capella.core.data.pa;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.fa.FunctionPkg;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Physical Function Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalFunctionPkg#getOwnedPhysicalFunctions <em>Owned Physical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalFunctionPkg#getOwnedPhysicalFunctionPkgs <em>Owned Physical Function Pkgs</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalFunctionPkg()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='container for physical functions\r\n[source: Capella study]' usage\040guideline='Used to structure the storage of physical function elements inside a physical architecture\r\n' used\040in\040levels='physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface PhysicalFunctionPkg extends FunctionPkg {





	/**
   * Returns the value of the '<em><b>Owned Physical Functions</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalFunction}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Physical Functions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Physical Functions</em>' containment reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalFunctionPkg_OwnedPhysicalFunctions()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the physical functions contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which PhysicalFunction stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PhysicalFunction> getOwnedPhysicalFunctions();







	/**
   * Returns the value of the '<em><b>Owned Physical Function Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalFunctionPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Physical Function Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Physical Function Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalFunctionPkg_OwnedPhysicalFunctionPkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the sub-(physical function) packages contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which PhysicalFunctionPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PhysicalFunctionPkg> getOwnedPhysicalFunctionPkgs();





} // PhysicalFunctionPkg
