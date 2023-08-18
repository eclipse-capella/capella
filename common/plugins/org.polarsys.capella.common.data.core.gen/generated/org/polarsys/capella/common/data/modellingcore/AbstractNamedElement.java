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
 * A representation of the model object '<em><b>Abstract Named Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractNamedElement#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractNamedElement()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='AbstractNamedElement'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='AbstractNamedElement'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A named element represents elements that may have a name. The name is used for identification of the named element\r\nwithin the namespace in which it is defined. A named element also has a qualified name that allows it to be\r\nunambiguously identified within a hierarchy of nested namespaces. NamedElement is an abstract metaclass.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a (Abstract)' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='- If there is no name, or one of the containing namespaces has no name, there is no qualified name.\r\n- When there is a name, and all of the containing namespaces have a name, the qualified name is constructed from the names of the containing namespaces.\r\n- If a NamedElement is not owned by a Namespace, it does not have a visibility.\r\n[source: Capella study]' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::NamedElement' constraints='none'"
 * @generated
 */
public interface AbstractNamedElement extends ModelElement {





	/**
   * Returns the value of the '<em><b>Name</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractNamedElement_Name()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='name' featureOwner='AbstractNamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation namingAttribute='true' Label='name'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The name of the NamedElement\r\n[source: UML superstructure v2.2]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::NamedElement::name' explanation='' constraints=''"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getName();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.AbstractNamedElement#getName <em>Name</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */

	void setName(String value);





} // AbstractNamedElement
