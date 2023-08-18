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
import org.polarsys.capella.core.data.cs.BlockArchitecturePkg;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Physical Architecture Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg#getOwnedPhysicalArchitecturePkgs <em>Owned Physical Architecture Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg#getOwnedPhysicalArchitectures <em>Owned Physical Architectures</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalArchitecturePkg()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='PhysicalArchitecturePkg'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.PhysicalArchitecturePkg'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='container for physical architecture elements\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 * @generated
 */
public interface PhysicalArchitecturePkg extends BlockArchitecturePkg {





	/**
   * Returns the value of the '<em><b>Owned Physical Architecture Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Physical Architecture Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Physical Architecture Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalArchitecturePkg_OwnedPhysicalArchitecturePkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='subPhysicalArchitecturePkgs'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='sub-(physical architecture) packages contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which PhysicalArchitecturePkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<PhysicalArchitecturePkg> getOwnedPhysicalArchitecturePkgs();







	/**
   * Returns the value of the '<em><b>Owned Physical Architectures</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalArchitecture}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Physical Architectures</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Physical Architectures</em>' containment reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalArchitecturePkg_OwnedPhysicalArchitectures()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedPhysicalArchitectures'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the physical architecture elements contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which PhysicalArchitecture stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<PhysicalArchitecture> getOwnedPhysicalArchitectures();





} // PhysicalArchitecturePkg
