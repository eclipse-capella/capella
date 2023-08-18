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
package org.polarsys.capella.core.data.information;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.TypedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exchange Item Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.ExchangeItemElement#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.ExchangeItemElement#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.ExchangeItemElement#isComposite <em>Composite</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.ExchangeItemElement#getReferencedProperties <em>Referenced Properties</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.InformationPackage#getExchangeItemElement()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a part of a structured exchange item\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Parameter' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface ExchangeItemElement extends NamedElement, MultiplicityElement, TypedElement {





	/**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.information.ElementKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.information.ElementKind
   * @see #setKind(ElementKind)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getExchangeItemElement_Kind()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='refer to ElementKind description' constraints='none' type='refer to ElementKind definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ElementKind getKind();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.ExchangeItemElement#getKind <em>Kind</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.information.ElementKind
   * @see #getKind()
   * @generated
   */

	void setKind(ElementKind value);







	/**
   * Returns the value of the '<em><b>Direction</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.information.ParameterDirection}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Direction</em>' attribute.
   * @see org.polarsys.capella.core.data.information.ParameterDirection
   * @see #setDirection(ParameterDirection)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getExchangeItemElement_Direction()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='direction' featureOwner='Parameter'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies whether the parameter is an input, an output, or both.\r\n[source: Capella study]' constraints='none' type='see ParameterDirection definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Parameter::direction' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ParameterDirection getDirection();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.ExchangeItemElement#getDirection <em>Direction</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Direction</em>' attribute.
   * @see org.polarsys.capella.core.data.information.ParameterDirection
   * @see #getDirection()
   * @generated
   */

	void setDirection(ParameterDirection value);







	/**
   * Returns the value of the '<em><b>Composite</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Composite</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Composite</em>' attribute.
   * @see #setComposite(boolean)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getExchangeItemElement_Composite()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' constraints='none' type='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isComposite();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.ExchangeItemElement#isComposite <em>Composite</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Composite</em>' attribute.
   * @see #isComposite()
   * @generated
   */

	void setComposite(boolean value);







	/**
   * Returns the value of the '<em><b>Referenced Properties</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.Property}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Properties</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Referenced Properties</em>' reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getExchangeItemElement_ReferencedProperties()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' constraints='none' type='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Property> getReferencedProperties();





} // ExchangeItemElement
