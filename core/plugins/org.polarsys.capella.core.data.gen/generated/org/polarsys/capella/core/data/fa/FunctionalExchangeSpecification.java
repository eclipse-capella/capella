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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Functional Exchange Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalExchangeSpecification#getFunctionalExchanges <em>Functional Exchanges</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalExchangeSpecification()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Functional Exchange Specification'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Connector' stereotype='eng.Exchange'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a specialized version of an exchange specification, dedicated to specify exchanges between two functions of the system\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::InformationFlow' explanation='none' constraints='none'"
 * @generated
 */
public interface FunctionalExchangeSpecification extends ExchangeSpecification {





	/**
   * Returns the value of the '<em><b>Functional Exchanges</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalExchange}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Functional Exchanges</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Functional Exchanges</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalExchangeSpecification_FunctionalExchanges()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='realizations'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the functional exchanges that fulfill this specification\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<FunctionalExchange> getFunctionalExchanges();





} // FunctionalExchangeSpecification
