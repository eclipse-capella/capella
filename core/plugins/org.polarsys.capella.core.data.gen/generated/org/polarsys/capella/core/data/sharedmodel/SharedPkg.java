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

import org.polarsys.capella.core.data.capellacore.ReuseableStructure;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.information.DataPkg;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Shared Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.sharedmodel.SharedPkg#getOwnedDataPkg <em>Owned Data Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.sharedmodel.SharedPkg#getOwnedGenericPkg <em>Owned Generic Pkg</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage#getSharedPkg()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='SharedPkg'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.SharedPkg'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a container specialized to hold elements intended to be shared across projects/analysis\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface SharedPkg extends ReuseableStructure, ModelRoot {





	/**
   * Returns the value of the '<em><b>Owned Data Pkg</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Data Pkg</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Data Pkg</em>' containment reference.
   * @see #setOwnedDataPkg(DataPkg)
   * @see org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage#getSharedPkg_OwnedDataPkg()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedDataPkg'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the data packages contained in this shared package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which DataPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]'"
   * @generated
   */

	DataPkg getOwnedDataPkg();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.sharedmodel.SharedPkg#getOwnedDataPkg <em>Owned Data Pkg</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Data Pkg</em>' containment reference.
   * @see #getOwnedDataPkg()
   * @generated
   */

	void setOwnedDataPkg(DataPkg value);







	/**
   * Returns the value of the '<em><b>Owned Generic Pkg</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Generic Pkg</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Generic Pkg</em>' containment reference.
   * @see #setOwnedGenericPkg(GenericPkg)
   * @see org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage#getSharedPkg_OwnedGenericPkg()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='genericPkg'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the generic packages contained in this shared package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which GenericPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]'"
   * @generated
   */

	GenericPkg getOwnedGenericPkg();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.sharedmodel.SharedPkg#getOwnedGenericPkg <em>Owned Generic Pkg</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Generic Pkg</em>' containment reference.
   * @see #getOwnedGenericPkg()
   * @generated
   */

	void setOwnedGenericPkg(GenericPkg value);





} // SharedPkg
