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

import org.polarsys.capella.core.data.capellacore.Allocation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Functional Exchange Realization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalExchangeRealization#getRealizedFunctionalExchange <em>Realized Functional Exchange</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalExchangeRealization#getRealizingFunctionalExchange <em>Realizing Functional Exchange</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalExchangeRealization()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Functional Exchange Realization'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a realization link between a functional exchange, and the (typically higher level) functional exchange that it realizes\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Realization' explanation='none' constraints='none'"
 * @generated
 */
public interface FunctionalExchangeRealization extends Allocation {





	/**
   * Returns the value of the '<em><b>Realized Functional Exchange</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getIncomingFunctionalExchangeRealizations <em>Incoming Functional Exchange Realizations</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Functional Exchange</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Functional Exchange</em>' reference.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalExchangeRealization_RealizedFunctionalExchange()
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getIncomingFunctionalExchangeRealizations
   * @model opposite="incomingFunctionalExchangeRealizations" required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='targetElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the functional exchange that is being realized by the other functional exchange involved in this relationship\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	FunctionalExchange getRealizedFunctionalExchange();







	/**
   * Returns the value of the '<em><b>Realizing Functional Exchange</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.FunctionalExchange#getOutgoingFunctionalExchangeRealizations <em>Outgoing Functional Exchange Realizations</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Functional Exchange</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Functional Exchange</em>' reference.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalExchangeRealization_RealizingFunctionalExchange()
   * @see org.polarsys.capella.core.data.fa.FunctionalExchange#getOutgoingFunctionalExchangeRealizations
   * @model opposite="outgoingFunctionalExchangeRealizations" required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='sourceElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the functional exchange that is realising the other functional exchange involved in this relationship\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	FunctionalExchange getRealizingFunctionalExchange();





} // FunctionalExchangeRealization
