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
package org.polarsys.capella.core.data.la;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Capability Realization Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealizationPkg#getOwnedCapabilityRealizations <em>Owned Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.CapabilityRealizationPkg#getOwnedCapabilityRealizationPkgs <em>Owned Capability Realization Pkgs</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealizationPkg()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='CapabilityRealizationPkg'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.CapabilityRealizationPkg'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a container for storing CapabilityRealization elements\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='logical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface CapabilityRealizationPkg extends AbstractCapabilityPkg {





	/**
   * Returns the value of the '<em><b>Owned Capability Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.CapabilityRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Capability Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Capability Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealizationPkg_OwnedCapabilityRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='capabilityRealizations'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the CapabilityRealizations contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which CapabilityRealization stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<CapabilityRealization> getOwnedCapabilityRealizations();







	/**
   * Returns the value of the '<em><b>Owned Capability Realization Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.CapabilityRealizationPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Capability Realization Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Capability Realization Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getCapabilityRealizationPkg_OwnedCapabilityRealizationPkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedCapabilityRealizationPkgs'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the sub-packages in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which CapabilityRealizationPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<CapabilityRealizationPkg> getOwnedCapabilityRealizationPkgs();





} // CapabilityRealizationPkg
