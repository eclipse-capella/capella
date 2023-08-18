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
 * A representation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.Feature#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.Feature#isIsStatic <em>Is Static</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.Feature#getVisibility <em>Visibility</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getFeature()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Feature'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A feature declares a behavioral or structural characteristic of instances of classifiers.\r\n[source:UML Superstructure v2.2]' usage\040guideline='n/a (abstract)' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Feature' constraints='none'"
 * @generated
 */
public interface Feature extends NamedElement {





	/**
   * Returns the value of the '<em><b>Is Abstract</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Abstract</em>' attribute.
   * @see #setIsAbstract(boolean)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getFeature_IsAbstract()
   * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::BehavioralFeature::isAbstract' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies whether the Feature is abstract or concrete\r\n[source: Capella study]' constraints='none' type='true is Feature is abstract' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsAbstract();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.Feature#isIsAbstract <em>Is Abstract</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Abstract</em>' attribute.
   * @see #isIsAbstract()
   * @generated
   */

	void setIsAbstract(boolean value);







	/**
   * Returns the value of the '<em><b>Is Static</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Static</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Static</em>' attribute.
   * @see #setIsStatic(boolean)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getFeature_IsStatic()
   * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Feature::isStatic' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies whether the Feature is static\r\n[source: Capella study]' constraints='none' type='true if Feature is static' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsStatic();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.Feature#isIsStatic <em>Is Static</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Static</em>' attribute.
   * @see #isIsStatic()
   * @generated
   */

	void setIsStatic(boolean value);







	/**
   * Returns the value of the '<em><b>Visibility</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.capellacore.VisibilityKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visibility</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Visibility</em>' attribute.
   * @see org.polarsys.capella.core.data.capellacore.VisibilityKind
   * @see #setVisibility(VisibilityKind)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getFeature_Visibility()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the type of visibility of this feature\r\n[source: Capella study]' constraints='none' type='refer to VisibilityKind' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::NamedElement::visibility' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	VisibilityKind getVisibility();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.Feature#getVisibility <em>Visibility</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Visibility</em>' attribute.
   * @see org.polarsys.capella.core.data.capellacore.VisibilityKind
   * @see #getVisibility()
   * @generated
   */

	void setVisibility(VisibilityKind value);





} // Feature
