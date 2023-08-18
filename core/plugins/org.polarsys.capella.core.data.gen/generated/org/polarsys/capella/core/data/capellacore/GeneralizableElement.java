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
 * A representation of the model object '<em><b>Generalizable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement#getOwnedGeneralizations <em>Owned Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSuperGeneralizations <em>Super Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSubGeneralizations <em>Sub Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSuper <em>Super</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSub <em>Sub</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getGeneralizableElement()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A generalizable element is an abstract metaclass.\r\nA generalizable element is a type and can own generalizations, thereby making it possible to define generalization relationships to\r\nother generalizable elements.\r\nA generalizable element can specify a generalization hierarchy by referencing its general classifiers.\r\nA generalizable element is a redefinable element, meaning that it is possible to redefine nested classifiers.\r\n[source:UML Superstructure v2.2]' usage\040guideline='n/a (abstract)' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::RedefinableElement' constraints='none'"
 * @generated
 */
public interface GeneralizableElement extends Type {





	/**
   * Returns the value of the '<em><b>Abstract</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Abstract</em>' attribute.
   * @see #setAbstract(boolean)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getGeneralizableElement_Abstract()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='isAbstract' featureOwner='Classifier'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies whether this classifier is abstract or concrete\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Classifier::isAbstract' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isAbstract();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement#isAbstract <em>Abstract</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Abstract</em>' attribute.
   * @see #isAbstract()
   * @generated
   */

	void setAbstract(boolean value);







	/**
   * Returns the value of the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.Generalization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Generalizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Generalizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getGeneralizableElement_OwnedGeneralizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='generalization' featureOwner='Classifier'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='superGeneralization'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the links to this classifier\'s parent(s)\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Classifier::generalization' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<Generalization> getOwnedGeneralizations();







	/**
   * Returns the value of the '<em><b>Super Generalizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.Generalization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Generalizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Super Generalizations</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getGeneralizableElement_SuperGeneralizations()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='generalization' featureOwner='Classifier'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='superGeneralization'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the links to this classifier\'s parent(s)\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Classifier::generalization' explanation='none' constraints='Order must be computed'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='sub'"
   * @generated
   */

	EList<Generalization> getSuperGeneralizations();







	/**
   * Returns the value of the '<em><b>Sub Generalizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.Generalization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Generalizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Sub Generalizations</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getGeneralizableElement_SubGeneralizations()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the links to this classifier\'s child(ren)\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::Generalization::general' constraints='Order must be computed'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='^super'"
   * @generated
   */

	EList<Generalization> getSubGeneralizations();







	/**
   * Returns the value of the '<em><b>Super</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.GeneralizableElement}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSub <em>Sub</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Super</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getGeneralizableElement_Super()
   * @see org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSub
   * @model opposite="sub" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='superGeneralizations.^super'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) parent classifiers\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<GeneralizableElement> getSuper();







	/**
   * Returns the value of the '<em><b>Sub</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.GeneralizableElement}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSuper <em>Super</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Sub</em>' reference list.
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getGeneralizableElement_Sub()
   * @see org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSuper
   * @model opposite="super" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='subGeneralizations.sub'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) children classifiers\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<GeneralizableElement> getSub();





} // GeneralizableElement
