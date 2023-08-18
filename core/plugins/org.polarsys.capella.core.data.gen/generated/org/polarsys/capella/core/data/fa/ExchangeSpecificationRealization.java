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
 * A representation of the model object '<em><b>Exchange Specification Realization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization#getRealizedExchangeSpecification <em>Realized Exchange Specification</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization#getRealizingExchangeSpecification <em>Realizing Exchange Specification</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getExchangeSpecificationRealization()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='FunctionAllocationToLogicalComponent'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Dependency' stereotype='eng.FunctionAllocationToLogicalComponent'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Base class for deriving specific realization links between exchange specifications and the model elements that realize them.\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface ExchangeSpecificationRealization extends Allocation {





	/**
   * Returns the value of the '<em><b>Realized Exchange Specification</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ExchangeSpecification#getIncomingExchangeSpecificationRealizations <em>Incoming Exchange Specification Realizations</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Exchange Specification</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Exchange Specification</em>' reference.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getExchangeSpecificationRealization_RealizedExchangeSpecification()
   * @see org.polarsys.capella.core.data.fa.ExchangeSpecification#getIncomingExchangeSpecificationRealizations
   * @model opposite="incomingExchangeSpecificationRealizations" required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='targetElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the exchange specification that is being realized by the other (typically lower level) exchange specification involved in this link\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	ExchangeSpecification getRealizedExchangeSpecification();







	/**
   * Returns the value of the '<em><b>Realizing Exchange Specification</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ExchangeSpecification#getOutgoingExchangeSpecificationRealizations <em>Outgoing Exchange Specification Realizations</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Exchange Specification</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Exchange Specification</em>' reference.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getExchangeSpecificationRealization_RealizingExchangeSpecification()
   * @see org.polarsys.capella.core.data.fa.ExchangeSpecification#getOutgoingExchangeSpecificationRealizations
   * @model opposite="outgoingExchangeSpecificationRealizations" required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='sourceElement'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the exchange specification that performs the realization of the other exchange specification involved in this link\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	ExchangeSpecification getRealizingExchangeSpecification();





} // ExchangeSpecificationRealization
