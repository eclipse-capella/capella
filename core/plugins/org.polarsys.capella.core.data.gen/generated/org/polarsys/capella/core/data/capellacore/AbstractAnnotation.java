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
 * A representation of the model object '<em><b>Abstract Annotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.AbstractAnnotation#getContent <em>Content</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getAbstractAnnotation()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='AbstractAnnotation'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An abstract Annotation can be compared to an UML comment : A comment gives the ability to attach various remarks to elements. A comment carries no semantic force, but may contain information that is useful to a modeler.\r\n[source:UML Superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Comment' constraints='none'"
 * @generated
 */
public interface AbstractAnnotation extends CapellaElement {





	/**
   * Returns the value of the '<em><b>Content</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Content</em>' attribute.
   * @see #setContent(String)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getAbstractAnnotation_Content()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='body' featureOwner='Comment'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The textual content of the annotation (free format)\r\n[source:Capella study]' constraints='None' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='uml::Comment::body or creation of an Expression as specification of a uml::Constraint' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getContent();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.AbstractAnnotation#getContent <em>Content</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Content</em>' attribute.
   * @see #getContent()
   * @generated
   */

	void setContent(String value);





} // AbstractAnnotation
