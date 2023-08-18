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
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.Type#getTypedElements <em>Typed Elements</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getType()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Type'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Type'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A type represents a set of values. A typed element that has this type is constrained to represent values within this set.\r\n[source:UML Superstructure v2.2]' usage\040guideline='n/a (abstract)' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface Type extends AbstractType, Namespace {





	/**
   * Returns the value of the '<em><b>Typed Elements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.TypedElement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Typed Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Typed Elements</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getType_TypedElements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='abstractTypedElements'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the set of typed elements which eAttribute type value is the owner type.\r\n[source:Capella study]' constraints='None' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<TypedElement> getTypedElements();





} // Type
