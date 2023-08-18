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
package org.polarsys.capella.common.data.modellingcore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Typed Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractTypedElement#getAbstractType <em>Abstract Type</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractTypedElement()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a (named) model element to which a specific type is associated\r\n[source: Capella study]' usage\040guideline='n/a (Abstract)' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::TypedElement' constraints='none'"
 * @generated
 */
public interface AbstractTypedElement extends AbstractNamedElement {





	/**
   * Returns the value of the '<em><b>Abstract Type</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Abstract Type</em>' reference.
   * @see #setAbstractType(AbstractType)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractTypedElement_AbstractType()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='type' featureOwner='TypedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='type'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the type associated to this model element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::TypedElement::type' explanation='' constraints=''"
   * @generated
   */

	AbstractType getAbstractType();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.AbstractTypedElement#getAbstractType <em>Abstract Type</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Abstract Type</em>' reference.
   * @see #getAbstractType()
   * @generated
   */

	void setAbstractType(AbstractType value);





} // AbstractTypedElement
