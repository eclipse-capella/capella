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
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Functional Architecture</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedFunctionPkg <em>Owned Function Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedComponentExchanges <em>Owned Component Exchanges</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedComponentExchangeCategories <em>Owned Component Exchange Categories</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedFunctionalLinks <em>Owned Functional Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedFunctionalAllocations <em>Owned Functional Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedComponentExchangeRealizations <em>Owned Component Exchange Realizations</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalArchitecture()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a base class supporting the definition of architectures stating the functional interactions between entities\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Package' constraints='none'"
 * @generated
 */
public interface AbstractFunctionalArchitecture extends ModellingArchitecture {





	/**
   * Returns the value of the '<em><b>Owned Function Pkg</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Function Pkg</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Function Pkg</em>' containment reference.
   * @see #setOwnedFunctionPkg(FunctionPkg)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalArchitecture_OwnedFunctionPkg()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedFunctionPkg'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the function packages contained in this functional architecture\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='' constraints='uml::Package::nestedPackage elements on which FunctionPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]'"
   * @generated
   */

	FunctionPkg getOwnedFunctionPkg();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture#getOwnedFunctionPkg <em>Owned Function Pkg</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Function Pkg</em>' containment reference.
   * @see #getOwnedFunctionPkg()
   * @generated
   */

	void setOwnedFunctionPkg(FunctionPkg value);







	/**
   * Returns the value of the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchange}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Component Exchanges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Component Exchanges</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalArchitecture_OwnedComponentExchanges()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='ownedConnector' featureOwner='StructuredClassifier'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the component exchanges contained directly under this functional architecture, e.g. exchanges between top level components\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which ComponentExchange stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ComponentExchange> getOwnedComponentExchanges();







	/**
   * Returns the value of the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchangeCategory}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Component Exchange Categories</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Component Exchange Categories</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalArchitecture_OwnedComponentExchangeCategories()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ComponentExchangeCategory> getOwnedComponentExchangeCategories();







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
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalArchitecture_OwnedFunctionalLinks()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Component'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedFunctionalLinks'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the exchange links contained directly under this functional architecture\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which ExchangeLink stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<ExchangeLink> getOwnedFunctionalLinks();







	/**
   * Returns the value of the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Functional Allocations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Functional Allocations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalArchitecture_OwnedFunctionalAllocations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of component &lt;=&gt; functions allocation links contained directly under this functional architecture\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which ComponentFunctionalAllocation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<ComponentFunctionalAllocation> getOwnedFunctionalAllocations();







	/**
   * Returns the value of the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ComponentExchangeRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Component Exchange Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Component Exchange Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getAbstractFunctionalArchitecture_OwnedComponentExchangeRealizations()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='ownedConnector' featureOwner='StructuredClassifier'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of component exchange realizations contained directly under this functional architecture\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which ComponentExchangeRealisation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<ComponentExchangeRealization> getOwnedComponentExchangeRealizations();





} // AbstractFunctionalArchitecture
