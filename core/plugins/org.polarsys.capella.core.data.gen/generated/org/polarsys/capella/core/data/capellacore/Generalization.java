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
 * A representation of the model object '<em><b>Generalization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.Generalization#getSuper <em>Super</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.Generalization#getSub <em>Sub</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getGeneralization()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Generalization'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Generalization' stereotype='eng.Generalization'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A generalization is a taxonomic relationship between a more general classifier and a more specific classifier. Each instance of the specific classifier is also an indirect instance of the general classifier. Thus, the specific classifier inherits the features of the more general classifier.\r\n[source:Capella study]' usage\040guideline='used to declare a parent/child relationship between two classes' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Generalization' explanation='none' constraints='none'"
 * @generated
 */
public interface Generalization extends Relationship {





	/**
   * Returns the value of the '<em><b>Super</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Super</em>' reference.
   * @see #setSuper(GeneralizableElement)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getGeneralization_Super()
   * @model required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='general' featureOwner='Generalization'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='super'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Same as UML Generalization general association : References the general classifier in the Generalization relationship.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Generalization::general' explanation='none' constraints='none'"
   * @generated
   */

	GeneralizableElement getSuper();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.Generalization#getSuper <em>Super</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Super</em>' reference.
   * @see #getSuper()
   * @generated
   */

	void setSuper(GeneralizableElement value);







	/**
   * Returns the value of the '<em><b>Sub</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Sub</em>' reference.
   * @see #setSub(GeneralizableElement)
   * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getGeneralization_Sub()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Same as UML Generalization specific association : References the specializing classifier in the Generalization relationship.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Generalization::specific' explanation='none' constraints='none'"
   * @generated
   */

	GeneralizableElement getSub();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.capellacore.Generalization#getSub <em>Sub</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sub</em>' reference.
   * @see #getSub()
   * @generated
   */

	void setSub(GeneralizableElement value);





} // Generalization
