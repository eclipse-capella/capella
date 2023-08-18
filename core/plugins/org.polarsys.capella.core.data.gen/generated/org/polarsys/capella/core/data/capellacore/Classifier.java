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
import org.polarsys.capella.core.data.information.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Classifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.Classifier#getOwnedFeatures <em>Owned Features</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.Classifier#getContainedProperties <em>Contained Properties</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getClassifier()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Classifier'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Classifier'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A classifier is a namespace whose members can include features.\r\nA classifier is an abstract metaclass.\r\nA classifier is a type.\r\nA classifier is a redefinable element, meaning that it is possible to redefine nested classifiers.\r\n[source:UML Superstructure v2.2]' usage\040guideline='n/a (abstract)' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Classifier' constraints='none'"
 * @generated
 */
public interface Classifier extends GeneralizableElement {





	/**
   * Returns the value of the '<em><b>Owned Features</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.Feature}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Features</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Features</em>' containment reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getClassifier_OwnedFeatures()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the features contained in this classifier\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Classifier::feature#keyword::none' explanation='none' constraints='none'"
   * @generated
   */

	EList<Feature> getOwnedFeatures();







	/**
   * Returns the value of the '<em><b>Contained Properties</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.Property}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Properties</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Properties</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getClassifier_ContainedProperties()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedFeatures'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedFeatures'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Property> getContainedProperties();





} // Classifier
