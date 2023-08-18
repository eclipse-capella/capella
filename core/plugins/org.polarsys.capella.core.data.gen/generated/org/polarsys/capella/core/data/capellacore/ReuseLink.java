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
package org.polarsys.capella.core.data.capellacore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reuse Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.ReuseLink#getReused <em>Reused</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.ReuseLink#getReuser <em>Reuser</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getReuseLink()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ReuseLink'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Dependency' stereotype='eng.ReuseLink'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link of reusability between a reuser and a reused structure\r\n[Capella study]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Dependency' explanation='none' constraints='none'"
 * @generated
 */
public interface ReuseLink extends Relationship {





	/**
   * Returns the value of the '<em><b>Reused</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reused</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Reused</em>' reference.
   * @see #setReused(ReuseableStructure)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getReuseLink_Reused()
   * @model resolveProxies="false" required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='supplier' featureOwner='Dependency'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='sharedPkg'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the structure that is reused\r\n[Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::supplier' explanation='none' constraints='Multiplicity must be [1..1]'"
   * @generated
   */

	ReuseableStructure getReused();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.ReuseLink#getReused <em>Reused</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reused</em>' reference.
   * @see #getReused()
   * @generated
   */

	void setReused(ReuseableStructure value);







	/**
   * Returns the value of the '<em><b>Reuser</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reuser</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Reuser</em>' reference.
   * @see #setReuser(ReuserStructure)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getReuseLink_Reuser()
   * @model required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='client' featureOwner='Dependency'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='systemEngineering'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation constraints='none' description='Link to the structure that reuses\r\n[Capella study]' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::client' explanation='none' constraints='Multiplicity must be [1..1]'"
   * @generated
   */

	ReuserStructure getReuser();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.ReuseLink#getReuser <em>Reuser</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reuser</em>' reference.
   * @see #getReuser()
   * @generated
   */

	void setReuser(ReuserStructure value);





} // ReuseLink
