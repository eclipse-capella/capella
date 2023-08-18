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
import org.polarsys.capella.core.data.information.datavalue.DataValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.CollectionValue#getOwnedElements <em>Owned Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.CollectionValue#getOwnedDefaultElement <em>Owned Default Element</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.InformationPackage#getCollectionValue()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Caracterizes a value that represents a collection of elements\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface CollectionValue extends AbstractCollectionValue {





	/**
   * Returns the value of the '<em><b>Owned Elements</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.datavalue.DataValue}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Elements</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getCollectionValue_OwnedElements()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<DataValue> getOwnedElements();







	/**
   * Returns the value of the '<em><b>Owned Default Element</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Default Element</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Default Element</em>' containment reference.
   * @see #setOwnedDefaultElement(DataValue)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getCollectionValue_OwnedDefaultElement()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DataValue getOwnedDefaultElement();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.CollectionValue#getOwnedDefaultElement <em>Owned Default Element</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Default Element</em>' containment reference.
   * @see #getOwnedDefaultElement()
   * @generated
   */

	void setOwnedDefaultElement(DataValue value);





} // CollectionValue
