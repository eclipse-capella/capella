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
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operational Capability Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalCapabilityPkg#getOwnedOperationalCapabilities <em>Owned Operational Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalCapabilityPkg#getOwnedOperationalCapabilityPkgs <em>Owned Operational Capability Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalCapabilityPkg#getOwnedCapabilityConfigurations <em>Owned Capability Configurations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.OperationalCapabilityPkg#getOwnedConceptCompliances <em>Owned Concept Compliances</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalCapabilityPkg()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='container for operational capabilities\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational' usage\040examples='n/a' constraints='none' comment/notes='not used/implemented as of Capella 1.0.3' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface OperationalCapabilityPkg extends AbstractCapabilityPkg {





	/**
   * Returns the value of the '<em><b>Owned Operational Capabilities</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.OperationalCapability}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Operational Capabilities</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Operational Capabilities</em>' containment reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalCapabilityPkg_OwnedOperationalCapabilities()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='operational capabilities contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which OperationalCapability stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<OperationalCapability> getOwnedOperationalCapabilities();







	/**
   * Returns the value of the '<em><b>Owned Operational Capability Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.OperationalCapabilityPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Operational Capability Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Operational Capability Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalCapabilityPkg_OwnedOperationalCapabilityPkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='sub-packages of operational capabilities contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which OperationalCapabilityPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<OperationalCapabilityPkg> getOwnedOperationalCapabilityPkgs();







	/**
   * Returns the value of the '<em><b>Owned Capability Configurations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.CapabilityConfiguration}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Capability Configurations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Capability Configurations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalCapabilityPkg_OwnedCapabilityConfigurations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Capability Configurations contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='not used/implemented as of Capella 1.0.3'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which CapabilityConfiguration stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<CapabilityConfiguration> getOwnedCapabilityConfigurations();







	/**
   * Returns the value of the '<em><b>Owned Concept Compliances</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.ConceptCompliance}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Concept Compliances</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Concept Compliances</em>' containment reference list.
   * @see org.polarsys.capella.core.data.oa.OaPackage#getOperationalCapabilityPkg_OwnedConceptCompliances()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='ConceptCompliance elements contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='not used/implemented as of Capella'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which ConceptCompliance stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<ConceptCompliance> getOwnedConceptCompliances();





} // OperationalCapabilityPkg
