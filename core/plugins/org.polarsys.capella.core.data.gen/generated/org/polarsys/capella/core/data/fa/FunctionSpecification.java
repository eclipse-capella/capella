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
import org.polarsys.capella.common.data.activity.AbstractActivity;
import org.polarsys.capella.core.data.capellacore.Namespace;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionSpecification#getInExchangeLinks <em>In Exchange Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionSpecification#getOutExchangeLinks <em>Out Exchange Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionSpecification#getOwnedFunctionPorts <em>Owned Function Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionSpecification#getSubFunctionSpecifications <em>Sub Function Specifications</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionSpecification()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Function Specification'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Component'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a function specification is to a function what a classifier is to an instance : it characterizes the common properties that all function instances will share\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Activity' explanation='cannot be mapped to uml::Component since it is not part of UML4SysML' constraints='none'"
 * @generated
 */
public interface FunctionSpecification extends Namespace, AbstractActivity {





	/**
   * Returns the value of the '<em><b>In Exchange Links</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ExchangeLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Exchange Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>In Exchange Links</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionSpecification_InExchangeLinks()
   * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='inFunctionalLinks'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='inbound exchange links\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<ExchangeLink> getInExchangeLinks();







	/**
   * Returns the value of the '<em><b>Out Exchange Links</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.ExchangeLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Exchange Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Out Exchange Links</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionSpecification_OutExchangeLinks()
   * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='outFunctionalLinks'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='outbound exchange links\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<ExchangeLink> getOutExchangeLinks();







	/**
   * Returns the value of the '<em><b>Owned Function Ports</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionPort}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Function Ports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Function Ports</em>' containment reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionSpecification_OwnedFunctionPorts()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='flow ports owned by functions instanciating this function specification\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::StructuredClassifier::ownedAttribute' explanation='none' constraints='uml::StructuredClassifier::ownedAttribute elements on which FlowPort stereotype or any stereotype that inherits from it is applied\r\nOrder will not be preserved'"
   * @generated
   */

	EList<FunctionPort> getOwnedFunctionPorts();







	/**
   * Returns the value of the '<em><b>Sub Function Specifications</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionSpecification}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Function Specifications</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Sub Function Specifications</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionSpecification_SubFunctionSpecifications()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Component'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='subFunctions'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='FunctionSpecification.ownedNodes(self, af);\r\nAbstractFunction.linkedFunctionSpecification(af, target); '"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of sub-specifications of this function specification \r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<FunctionSpecification> getSubFunctionSpecifications();





} // FunctionSpecification
