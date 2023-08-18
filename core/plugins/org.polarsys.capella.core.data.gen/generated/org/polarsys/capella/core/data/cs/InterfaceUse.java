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
 * A representation of the model object '<em><b>Interface Use</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.InterfaceUse#getInterfaceUser <em>Interface User</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.InterfaceUse#getUsedInterface <em>Used Interface</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getInterfaceUse()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='InterfaceUse'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Usage' stereotype='eng.InterfaceUse'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Mediator class between an interface and its user (typically a Component)\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Usage' explanation='none' constraints='none'"
 * @generated
 */
public interface InterfaceUse extends Relationship {





	/**
   * Returns the value of the '<em><b>Interface User</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.Component#getUsedInterfaceLinks <em>Used Interface Links</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface User</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Interface User</em>' reference.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterfaceUse_InterfaceUser()
   * @see org.polarsys.capella.core.data.cs.Component#getUsedInterfaceLinks
   * @model opposite="usedInterfaceLinks" required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='client' featureOwner='Dependency'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='interfaceUser'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='ownedInterfaceUses'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Component that uses the interface\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::client' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	Component getInterfaceUser();







	/**
   * Returns the value of the '<em><b>Used Interface</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Used Interface</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Used Interface</em>' reference.
   * @see #setUsedInterface(Interface)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getInterfaceUse_UsedInterface()
   * @model required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='supplier' featureOwner='Dependency'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='usedInterface'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Supplied interface\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::supplier' explanation='none' constraints='Multiplicity must be [1..1]'"
   * @generated
   */

	Interface getUsedInterface();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.InterfaceUse#getUsedInterface <em>Used Interface</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Used Interface</em>' reference.
   * @see #getUsedInterface()
   * @generated
   */

	void setUsedInterface(Interface value);





} // InterfaceUse
