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

import org.polarsys.capella.core.data.capellacore.Relationship;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exchange Containment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.ExchangeContainment#getExchange <em>Exchange</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ExchangeContainment#getLink <em>Link</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getExchangeContainment()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ExchangeContainment'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Dependency' stereotype='eng.ExchangeContainment'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a mediator class allowing to implement a referencing between an Exchange and an ExchangeLink\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Dependency' explanation='none' constraints='none'"
 * @generated
 */
public interface ExchangeContainment extends Relationship {





	/**
   * Returns the value of the '<em><b>Exchange</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ExchangeSpecification#getLink <em>Link</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exchange</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Exchange</em>' reference.
   * @see #setExchange(ExchangeSpecification)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getExchangeContainment_Exchange()
   * @see org.polarsys.capella.core.data.fa.ExchangeSpecification#getLink
   * @model opposite="link" required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='supplier' featureOwner='Dependency'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='exchange'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the exchange (specification) involved in this relationship\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::supplier' explanation='none' constraints='Multiplicity must be [1..1]'"
   * @generated
   */

	ExchangeSpecification getExchange();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.ExchangeContainment#getExchange <em>Exchange</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Exchange</em>' reference.
   * @see #getExchange()
   * @generated
   */

	void setExchange(ExchangeSpecification value);







	/**
   * Returns the value of the '<em><b>Link</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ExchangeLink#getExchangeContainmentLinks <em>Exchange Containment Links</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Link</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Link</em>' reference.
   * @see #setLink(ExchangeLink)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getExchangeContainment_Link()
   * @see org.polarsys.capella.core.data.fa.ExchangeLink#getExchangeContainmentLinks
   * @model opposite="exchangeContainmentLinks" required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='client' featureOwner='Dependency'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='link'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the exchange link involved in this relationship\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::client' explanation='none' constraints='Multiplicity must be [1..1]'"
   * @generated
   */

	ExchangeLink getLink();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.ExchangeContainment#getLink <em>Link</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Link</em>' reference.
   * @see #getLink()
   * @generated
   */

	void setLink(ExchangeLink value);





} // ExchangeContainment
