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

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Capella Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getSummary <em>Summary</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getDescription <em>Description</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getReview <em>Review</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getOwnedPropertyValues <em>Owned Property Values</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getOwnedEnumerationPropertyTypes <em>Owned Enumeration Property Types</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getAppliedPropertyValues <em>Applied Property Values</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getOwnedPropertyValueGroups <em>Owned Property Value Groups</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getAppliedPropertyValueGroups <em>Applied Property Value Groups</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getStatus <em>Status</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getFeatures <em>Features</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getCapellaElement()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='CapellaElement'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Element' stereotype='eng.CapellaElement'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A Capella element is a model element that is lockable, has a version and has incoming and outgoing traces, it has a summary and a description.\r\n[source:Capella study]\r\n\r\nA capella element can be compared to an UML element : An element is a constituent of a model.\r\n[source:UML Superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='n/a'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Element' constraints='none'"
 * @generated
 */
public interface CapellaElement extends TraceableElement, PublishableElement {





	/**
   * Returns the value of the '<em><b>Summary</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Summary</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Summary</em>' attribute.
   * @see #setSummary(String)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getCapellaElement_Summary()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Summary of the element\r\n[Capella study]' constraints='None' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getSummary();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getSummary <em>Summary</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Summary</em>' attribute.
   * @see #getSummary()
   * @generated
   */

	void setSummary(String value);







	/**
   * Returns the value of the '<em><b>Description</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Description</em>' attribute.
   * @see #setDescription(String)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getCapellaElement_Description()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Description of the Capella element\r\n[Capella study]' constraints='None' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getDescription();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getDescription <em>Description</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' attribute.
   * @see #getDescription()
   * @generated
   */

	void setDescription(String value);







	/**
   * Returns the value of the '<em><b>Review</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Review</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Review</em>' attribute.
   * @see #setReview(String)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getCapellaElement_Review()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Review description on the Capella element' constraints='None' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getReview();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getReview <em>Review</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Review</em>' attribute.
   * @see #getReview()
   * @generated
   */

	void setReview(String value);







	/**
   * Returns the value of the '<em><b>Owned Property Values</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.AbstractPropertyValue}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Property Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Property Values</em>' containment reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getCapellaElement_OwnedPropertyValues()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the property values that are contained in this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Element::ownedComment' explanation='none' constraints='uml::Element::ownedComment elements on which AbstractPropertyValue stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractPropertyValue> getOwnedPropertyValues();







	/**
   * Returns the value of the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.EnumerationPropertyType}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Enumeration Property Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Enumeration Property Types</em>' containment reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getCapellaElement_OwnedEnumerationPropertyTypes()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the enumeration property types that are contained in this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='none' constraints='elements on which EnumerationPropertyType stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<EnumerationPropertyType> getOwnedEnumerationPropertyTypes();







	/**
   * Returns the value of the '<em><b>Applied Property Values</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.AbstractPropertyValue}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applied Property Values</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Applied Property Values</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getCapellaElement_AppliedPropertyValues()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the property values that are applied on this element (whether they are actually stored under this element or not)\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractPropertyValue> getAppliedPropertyValues();







	/**
   * Returns the value of the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.PropertyValueGroup}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Property Value Groups</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Property Value Groups</em>' containment reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getCapellaElement_OwnedPropertyValueGroups()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the property value groups that are stored/contained in this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Element::ownedComment' explanation='none' constraints='uml::Element::ownedComment elements on which PropertyValueGroup stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PropertyValueGroup> getOwnedPropertyValueGroups();







	/**
   * Returns the value of the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.PropertyValueGroup}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applied Property Value Groups</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Applied Property Value Groups</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getCapellaElement_AppliedPropertyValueGroups()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the property value groups that apply to this element (whether or not they are actually stored under this element)\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PropertyValueGroup> getAppliedPropertyValueGroups();







	/**
   * Returns the value of the '<em><b>Status</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Status</em>' reference.
   * @see #setStatus(EnumerationPropertyLiteral)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getCapellaElement_Status()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the enumeration property literal that applies to this element' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EnumerationPropertyLiteral getStatus();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getStatus <em>Status</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Status</em>' reference.
   * @see #getStatus()
   * @generated
   */

	void setStatus(EnumerationPropertyLiteral value);







	/**
   * Returns the value of the '<em><b>Features</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Features</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Features</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getCapellaElement_Features()
   * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<EnumerationPropertyLiteral> getFeatures();





} // CapellaElement
