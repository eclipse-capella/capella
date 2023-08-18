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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Union</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.Union#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Union#getDiscriminant <em>Discriminant</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Union#getDefaultProperty <em>Default Property</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Union#getContainedUnionProperties <em>Contained Union Properties</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.InformationPackage#getUnion()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Union'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Class' stereotype='eng.Union'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(not used)' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='n/a' comment/notes='not used/implemented as of Capella' reference\040documentation='n/a'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Class' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface Union extends org.polarsys.capella.core.data.information.Class {





	/**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.information.UnionKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.information.UnionKind
   * @see #setKind(UnionKind)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getUnion_Kind()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies the type of the union\r\n[source: Capella study]' constraints='none' type='see UnionKind definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	UnionKind getKind();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.Union#getKind <em>Kind</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.information.UnionKind
   * @see #getKind()
   * @generated
   */

	void setKind(UnionKind value);







	/**
   * Returns the value of the '<em><b>Discriminant</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Discriminant</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Discriminant</em>' reference.
   * @see #setDiscriminant(UnionProperty)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getUnion_Discriminant()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='discriminant'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the discriminant union property' constraints='n/a' comment/notes='n/a'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	UnionProperty getDiscriminant();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.Union#getDiscriminant <em>Discriminant</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Discriminant</em>' reference.
   * @see #getDiscriminant()
   * @generated
   */

	void setDiscriminant(UnionProperty value);







	/**
   * Returns the value of the '<em><b>Default Property</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Property</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Default Property</em>' reference.
   * @see #setDefaultProperty(UnionProperty)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getUnion_DefaultProperty()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the default union property' constraints='n/a' comment/notes='n/a'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	UnionProperty getDefaultProperty();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.Union#getDefaultProperty <em>Default Property</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Default Property</em>' reference.
   * @see #getDefaultProperty()
   * @generated
   */

	void setDefaultProperty(UnionProperty value);







	/**
   * Returns the value of the '<em><b>Contained Union Properties</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.UnionProperty}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Union Properties</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Union Properties</em>' reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getUnion_ContainedUnionProperties()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedFeatures'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedFeatures'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<UnionProperty> getContainedUnionProperties();





} // Union
