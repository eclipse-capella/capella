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
 * A representation of the model object '<em><b>Key Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.KeyValue#getKey <em>Key</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.KeyValue#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getKeyValue()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='KeyValue'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Comment' stereotype='eng.KeyValue'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a generic key/value pair used to index data\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Comment' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface KeyValue extends CapellaElement {





	/**
   * Returns the value of the '<em><b>Key</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Key</em>' attribute.
   * @see #setKey(String)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getKeyValue_Key()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='key' featureOwner='eng.KeyValue' fromStereotype='true'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(textual) content representing the key\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getKey();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.KeyValue#getKey <em>Key</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Key</em>' attribute.
   * @see #getKey()
   * @generated
   */

	void setKey(String value);







	/**
   * Returns the value of the '<em><b>Value</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(String)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getKeyValue_Value()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='body' featureOwner='Comment'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='textual content representing the value associated to the key\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Comment::body' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getValue();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.KeyValue#getValue <em>Value</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */

	void setValue(String value);





} // KeyValue
