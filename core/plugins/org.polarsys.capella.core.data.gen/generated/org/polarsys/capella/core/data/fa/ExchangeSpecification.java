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
import org.polarsys.capella.common.data.activity.ActivityExchange;
import org.polarsys.capella.core.data.capellacore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exchange Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.ExchangeSpecification#getContainingLink <em>Containing Link</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ExchangeSpecification#getLink <em>Link</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ExchangeSpecification#getOutgoingExchangeSpecificationRealizations <em>Outgoing Exchange Specification Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ExchangeSpecification#getIncomingExchangeSpecificationRealizations <em>Incoming Exchange Specification Realizations</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getExchangeSpecification()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a high-level abstract class specifying a set of constraints that concrete exchanges might fulfill (e.g. implement this specification)\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::InformationFlow' constraints='none'"
 * @generated
 */
public interface ExchangeSpecification extends NamedElement, ActivityExchange {





	/**
   * Returns the value of the '<em><b>Containing Link</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containing Link</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Containing Link</em>' reference.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getExchangeSpecification_ContainingLink()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='containingLink'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='link.link'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the exchange link associated with this exchange specification\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	ExchangeLink getContainingLink();







	/**
   * Returns the value of the '<em><b>Link</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ExchangeContainment#getExchange <em>Exchange</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Link</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Link</em>' reference.
   * @see #setLink(ExchangeContainment)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getExchangeSpecification_Link()
   * @see org.polarsys.capella.core.data.fa.ExchangeContainment#getExchange
   * @model opposite="exchange"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping umlOppositeReference='supplier' umlOppositeReferenceOwner='Dependency'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='link'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the exchange containment associated with this exchange specification\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::Dependency::supplier' constraints='none'"
   * @generated
   */

	ExchangeContainment getLink();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.ExchangeSpecification#getLink <em>Link</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Link</em>' reference.
   * @see #getLink()
   * @generated
   */

	void setLink(ExchangeContainment value);







	/**
   * Returns the value of the '<em><b>Outgoing Exchange Specification Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization#getRealizingExchangeSpecification <em>Realizing Exchange Specification</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Exchange Specification Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Outgoing Exchange Specification Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getExchangeSpecification_OutgoingExchangeSpecificationRealizations()
   * @see org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization#getRealizingExchangeSpecification
   * @model opposite="realizingExchangeSpecification" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outgoingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the realization links between exchange specifications, for which this exchange specification is the origin of the link\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ExchangeSpecificationRealization> getOutgoingExchangeSpecificationRealizations();







	/**
   * Returns the value of the '<em><b>Incoming Exchange Specification Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization#getRealizedExchangeSpecification <em>Realized Exchange Specification</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Exchange Specification Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Incoming Exchange Specification Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getExchangeSpecification_IncomingExchangeSpecificationRealizations()
   * @see org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization#getRealizedExchangeSpecification
   * @model opposite="realizedExchangeSpecification" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='incomingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the realization links between exchange specifications, for which this exchange specification is the destination of the link\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ExchangeSpecificationRealization> getIncomingExchangeSpecificationRealizations();





} // ExchangeSpecification
