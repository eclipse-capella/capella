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
package org.polarsys.capella.core.data.cs;

import org.polarsys.capella.core.data.capellacore.Relationship;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interface Implementation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.InterfaceImplementation#getInterfaceImplementor <em>Interface Implementor</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.InterfaceImplementation#getImplementedInterface <em>Implemented Interface</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getInterfaceImplementation()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='InterfaceImplementation'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='InterfaceRealization' stereotype='eng.InterfaceImplementation'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Mediator class between an Interface and its implementor (typically a Component)\r\n[source: Capella study]\r\n\r\nAn InterfaceRealization is a specialized Realization relationship between a Classifier and an Interface. This relationship\r\nsignifies that the realizing classifier conforms to the contract specified by the Interface.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::InterfaceRealization' explanation='none' constraints='none'"
 * @generated
 */
public interface InterfaceImplementation extends Relationship {





	/**
   * Returns the value of the '<em><b>Interface Implementor</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.Component#getImplementedInterfaceLinks <em>Implemented Interface Links</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface Implementor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Interface Implementor</em>' reference.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterfaceImplementation_InterfaceImplementor()
   * @see org.polarsys.capella.core.data.cs.Component#getImplementedInterfaceLinks
   * @model opposite="implementedInterfaceLinks" required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='implementingClassifier' featureOwner='InterfaceRealization'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Interface Implementor'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='ownedInterfaceImplementations'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='References the Component that owns this Interface implementation.\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	Component getInterfaceImplementor();







	/**
   * Returns the value of the '<em><b>Implemented Interface</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implemented Interface</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Implemented Interface</em>' reference.
   * @see #setImplementedInterface(Interface)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterfaceImplementation_ImplementedInterface()
   * @model required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='contract' featureOwner='InterfaceRealization'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='realizedInterface'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='References the Interface specifying the conformance contract\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::InterfaceRealization::contract' explanation='none' constraints='none'"
   * @generated
   */

	Interface getImplementedInterface();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.InterfaceImplementation#getImplementedInterface <em>Implemented Interface</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Implemented Interface</em>' reference.
   * @see #getImplementedInterface()
   * @generated
   */

	void setImplementedInterface(Interface value);





} // InterfaceImplementation
