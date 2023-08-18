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
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.GeneralClass;
import org.polarsys.capella.core.data.information.datavalue.DataValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.Class#isIsPrimitive <em>Is Primitive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Class#getKeyParts <em>Key Parts</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Class#getOwnedStateMachines <em>Owned State Machines</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Class#getOwnedDataValues <em>Owned Data Values</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Class#getOwnedInformationRealizations <em>Owned Information Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Class#getRealizedClasses <em>Realized Classes</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Class#getRealizingClasses <em>Realizing Classes</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.InformationPackage#getClass_()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Class'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Class' stereotype='eng.Class'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A class describes a set of objects that share the same specifications of features, constraints, and semantics\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a (Abstract)' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Class' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface Class extends GeneralClass {





	/**
   * Returns the value of the '<em><b>Is Primitive</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Primitive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Primitive</em>' attribute.
   * @see #setIsPrimitive(boolean)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getClass_IsPrimitive()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='isPrimitive' featureOwner='eng.Class' fromStereotype='true'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='indicates whether or not the class inherits from a parent class.\r\n[source: Capella study]' constraints='none' type='\"true\" means that there is no super class that this class inherits from.' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsPrimitive();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.Class#isIsPrimitive <em>Is Primitive</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Primitive</em>' attribute.
   * @see #isIsPrimitive()
   * @generated
   */

	void setIsPrimitive(boolean value);







	/**
   * Returns the value of the '<em><b>Key Parts</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.KeyPart}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key Parts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Key Parts</em>' reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getClass_KeyParts()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='keyParts'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The KeyPart elements owned by this class\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::NamedElement::clientDependency' explanation='none' constraints='uml::NamedElement::clientDependency elements on which KeyPart stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<KeyPart> getKeyParts();







	/**
   * Returns the value of the '<em><b>Owned State Machines</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.StateMachine}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned State Machines</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned State Machines</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getClass_OwnedStateMachines()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the state machines associated to this class, supporting the characterization of its dynamic behavior\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Class::nestedClassifier' explanation='none' constraints='uml::Class::nestedClassifier elements on which StateMachine stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<StateMachine> getOwnedStateMachines();







	/**
   * Returns the value of the '<em><b>Owned Data Values</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.datavalue.DataValue}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Data Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Data Values</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getClass_OwnedDataValues()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of DataValue elements owned by this class\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='Elements are contained in the nearest possible parent container.' constraints='uml::NamedElement::clientDependency elements on which DataValue stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<DataValue> getOwnedDataValues();







	/**
   * Returns the value of the '<em><b>Owned Information Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.InformationRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Information Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Information Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getClass_OwnedInformationRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	EList<InformationRealization> getOwnedInformationRealizations();







	/**
   * Returns the value of the '<em><b>Realized Classes</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.Class}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.information.Class#getRealizingClasses <em>Realizing Classes</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Classes</em>' reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getClass_RealizedClasses()
   * @see org.polarsys.capella.core.data.information.Class#getRealizingClasses
   * @model opposite="realizingClasses" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Class.outgoingTraces(self, ir);\r\nInformationRealization.targetElement(ir, target); '"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='class(es) realized by this class' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Class> getRealizedClasses();







	/**
   * Returns the value of the '<em><b>Realizing Classes</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.Class}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.information.Class#getRealizedClasses <em>Realized Classes</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Classes</em>' reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getClass_RealizingClasses()
   * @see org.polarsys.capella.core.data.information.Class#getRealizedClasses
   * @model opposite="realizedClasses" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Class.incomingTraces(self, ir);\r\nInformationRealization.sourceElement(ir, target); '"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='class(es) realizing this class' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<Class> getRealizingClasses();





} // Class
