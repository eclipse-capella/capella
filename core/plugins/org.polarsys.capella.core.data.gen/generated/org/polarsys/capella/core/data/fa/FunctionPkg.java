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
package org.polarsys.capella.core.data.fa;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.Structure;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionPkg#getOwnedFunctionalLinks <em>Owned Functional Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionPkg#getOwnedExchanges <em>Owned Exchanges</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionPkg#getOwnedExchangeSpecificationRealizations <em>Owned Exchange Specification Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionPkg#getOwnedCategories <em>Owned Categories</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionPkg#getOwnedFunctionSpecifications <em>Owned Function Specifications</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionPkg()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='FunctionalAnalysis'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.sys.FunctionalAnalysis'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a base class for deriving packages aimed at containing functional entities (functions, exchanges between functions, ...)\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface FunctionPkg extends Structure {





	/**
   * Returns the value of the '<em><b>Owned Functional Links</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ExchangeLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Functional Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Functional Links</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionPkg_OwnedFunctionalLinks()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedFunctionalLinks'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the (functional) exchange links contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which ExchangeLink stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<ExchangeLink> getOwnedFunctionalLinks();







	/**
   * Returns the value of the '<em><b>Owned Exchanges</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalExchangeSpecification}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Exchanges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Exchanges</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionPkg_OwnedExchanges()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the exchanges specifications contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which FunctionalExchangeSpecification stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<FunctionalExchangeSpecification> getOwnedExchanges();







	/**
   * Returns the value of the '<em><b>Owned Exchange Specification Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Exchange Specification Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Exchange Specification Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionPkg_OwnedExchangeSpecificationRealizations()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the exchange realization links contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which ExchangeSpecificationRealisation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<ExchangeSpecificationRealization> getOwnedExchangeSpecificationRealizations();







	/**
   * Returns the value of the '<em><b>Owned Categories</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ExchangeCategory}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Categories</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Categories</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionPkg_OwnedCategories()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the exchange categories (families) contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which ExchangeCategory stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ExchangeCategory> getOwnedCategories();







	/**
   * Returns the value of the '<em><b>Owned Function Specifications</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionSpecification}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Function Specifications</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Function Specifications</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionPkg_OwnedFunctionSpecifications()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the functions (specifications) included in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which FunctionSpecification stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<FunctionSpecification> getOwnedFunctionSpecifications();





} // FunctionPkg
