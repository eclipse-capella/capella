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
 * A representation of the model object '<em><b>Naming Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.NamingRule#getTargetType <em>Target Type</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getNamingRule()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='NamingRule'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Comment' stereotype='eng.NamingRule'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Naming rule to apply to instances which type is equal to targetType' usage\040guideline='this is used whenever there is a need to constraint the naming of a given type of element' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Comment' explanation='none' constraints='none'"
 * @generated
 */
public interface NamingRule extends AbstractAnnotation {





	/**
   * Returns the value of the '<em><b>Target Type</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Target Type</em>' attribute.
   * @see #setTargetType(String)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getNamingRule_TargetType()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='targetType' featureOwner='eng.NamingRule' fromStereotype='true'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Type to which instances the naming rule has to be applied' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getTargetType();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.NamingRule#getTargetType <em>Target Type</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Type</em>' attribute.
   * @see #getTargetType()
   * @generated
   */

	void setTargetType(String value);





} // NamingRule
