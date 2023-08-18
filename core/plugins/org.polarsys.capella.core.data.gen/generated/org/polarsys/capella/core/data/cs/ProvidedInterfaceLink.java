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
 * A representation of the model object '<em><b>Provided Interface Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.ProvidedInterfaceLink#getInterface <em>Interface</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getProvidedInterfaceLink()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ProvidedInterfaceLink'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='InterfaceRealization' stereotype='eng.ProvidedInterfaceLink'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(not used)' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='n/a' comment/notes='not used/implemented as of Capella 1.0.3' reference\040documentation='n/a'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::InterfaceRealization' constraints='none'"
 * @generated
 */
public interface ProvidedInterfaceLink extends Relationship {





	/**
   * Returns the value of the '<em><b>Interface</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Interface</em>' reference.
   * @see #setInterface(Interface)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getProvidedInterfaceLink_Interface()
   * @model required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='contract' featureOwner='InterfaceRealization'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='interface'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='References the Interface specifying the conformance contract\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::InterfaceRealization::contract' explanation='none' constraints='none'"
   * @generated
   */

	Interface getInterface();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.ProvidedInterfaceLink#getInterface <em>Interface</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Interface</em>' reference.
   * @see #getInterface()
   * @generated
   */

	void setInterface(Interface value);





} // ProvidedInterfaceLink
