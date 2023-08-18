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
package org.polarsys.capella.core.data.epbs;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.cs.ComponentPkg;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configuration Item Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.epbs.ConfigurationItemPkg#getOwnedConfigurationItems <em>Owned Configuration Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.epbs.ConfigurationItemPkg#getOwnedConfigurationItemPkgs <em>Owned Configuration Item Pkgs</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.epbs.EpbsPackage#getConfigurationItemPkg()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ConfigurationItemPkg'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.sys.ConfigurationItemPkg'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Package that contains configuration item elements\r\n[source:Capella study]' usage\040guideline='this element is provided as a utility to better structure configuration items, if needed' used\040in\040levels='epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface ConfigurationItemPkg extends ComponentPkg {





	/**
   * Returns the value of the '<em><b>Owned Configuration Items</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.epbs.ConfigurationItem}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Configuration Items</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Configuration Items</em>' containment reference list.
   * @see org.polarsys.capella.core.data.epbs.EpbsPackage#getConfigurationItemPkg_OwnedConfigurationItems()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedConfigurationItems'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Set of configuration items that are stored in the package\r\n[source:Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which ConfigurationItem stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ConfigurationItem> getOwnedConfigurationItems();







	/**
   * Returns the value of the '<em><b>Owned Configuration Item Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.epbs.ConfigurationItemPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Configuration Item Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Configuration Item Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.epbs.EpbsPackage#getConfigurationItemPkg_OwnedConfigurationItemPkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedConfigurationItemPkgs'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Set of owned packages containing configuration items\r\n[source:Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which ConfigurationItemPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ConfigurationItemPkg> getOwnedConfigurationItemPkgs();





} // ConfigurationItemPkg
