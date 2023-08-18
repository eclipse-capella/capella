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
 * A representation of the model object '<em><b>Property Value Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.PropertyValueGroup#getValuedElements <em>Valued Elements</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getPropertyValueGroup()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Package that contain property values\r\n[Capella study]' usage\040guideline='none' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Comment' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface PropertyValueGroup extends Namespace {





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
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getPropertyValueGroup_ValuedElements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the model elements to which this property group is applied\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='appliedPropertyValueGroups'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<CapellaElement> getValuedElements();





} // PropertyValueGroup
