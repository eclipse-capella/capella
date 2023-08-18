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
package org.polarsys.capella.core.data.sharedmodel;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Structure;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.sharedmodel.GenericPkg#getSubGenericPkgs <em>Sub Generic Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.sharedmodel.GenericPkg#getCapellaElements <em>Capella Elements</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage#getGenericPkg()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='GenericPkg'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.GenericPkg'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a generic container for Capella elements and sub packages\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 * @generated
 */
public interface GenericPkg extends Structure {





	/**
   * Returns the value of the '<em><b>Sub Generic Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.sharedmodel.GenericPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Generic Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Sub Generic Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage#getGenericPkg_SubGenericPkgs()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='subGenericPkgs'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='sub-packages contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which GenericPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<GenericPkg> getSubGenericPkgs();







	/**
   * Returns the value of the '<em><b>Capella Elements</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.CapellaElement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capella Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Capella Elements</em>' containment reference list.
   * @see org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage#getGenericPkg_CapellaElements()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='capellaElements'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Capella model elements stored directly under this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which CapellaElement stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<CapellaElement> getCapellaElements();





} // GenericPkg
