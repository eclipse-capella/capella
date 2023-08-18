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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Property Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.AbstractPropertyValue#getInvolvedElements <em>Involved Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.AbstractPropertyValue#getValuedElements <em>Valued Elements</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getAbstractPropertyValue()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='It is a way to define extension properties for any capella elements\r\nA property value is a named element that has a value. This value has no specific format, it is described as a string.\r\n[Capella study]\r\n' usage\040guideline='none' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Comment' constraints='none'"
 * @generated
 */
public interface AbstractPropertyValue extends NamedElement {





	/**
   * Returns the value of the '<em><b>Involved Elements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.CapellaElement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involved Elements</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getAbstractPropertyValue_InvolvedElements()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the model elements involved by this property value\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Comment:annotatedElement' explanation='none' constraints='uml::Comment::annotatedElement elements on which CapellaElement stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<CapellaElement> getInvolvedElements();







	/**
   * Returns the value of the '<em><b>Valued Elements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.CapellaElement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Valued Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Valued Elements</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getAbstractPropertyValue_ValuedElements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the model elements to which this property value is applied\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='appliedPropertyValues'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<CapellaElement> getValuedElements();





} // AbstractPropertyValue
