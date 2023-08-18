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

import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.TypedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.Property#getAggregationKind <em>Aggregation Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Property#isIsDerived <em>Is Derived</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Property#isIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Property#isIsPartOfKey <em>Is Part Of Key</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Property#getAssociation <em>Association</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.InformationPackage#getProperty()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Property'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Property' stereotype='eng.Property'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A property is a structural feature.\r\nA property related to a classifier by ownedAttribute represents an attribute, and it may also represent an association end.\r\nIt relates an instance of the class to a value or collection of values of the type of the attribute.\r\nA property related to an Association by memberEnd or its specializations represents an end of the association. The type\r\nof property is the type of the end of the association.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='- If this property is owned by a class associated with a binary association, and the other end of the association is also owned by a class, then opposite gives the other end.\r\n- A multiplicity on an aggregate end of a composite aggregation must not have an upper bound greater than 1.\r\n- Subsetting may only occur when the context of the subsetting property conforms to the context of the subsetted property.\r\n- A redefined property must be inherited from a more general classifier containing the redefining property.\r\n- A subsetting property may strengthen the type of the subsetted property, and its upper bound may be less.\r\n- Only a navigable property can be marked as readOnly.\r\n- A derived union is derived.\r\n- A derived union is read only.\r\n- The value of isComposite is true only if aggregation is composite.\r\n-  A Property cannot be subset by a Property with the same name\r\n[source: UML superstructure v2.2]' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Property' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface Property extends Feature, TypedElement, MultiplicityElement, FinalizableElement {





	/**
   * Returns the value of the '<em><b>Aggregation Kind</b></em>' attribute.
   * The default value is <code>"UNSET"</code>.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.information.AggregationKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aggregation Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Aggregation Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.information.AggregationKind
   * @see #setAggregationKind(AggregationKind)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getProperty_AggregationKind()
   * @model default="UNSET"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='aggregation' featureOwner='Property'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the kind of aggregation that applies to the Property\r\n[source: UML superstructure v2.2]' constraints='none' type='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Property::aggregation' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	AggregationKind getAggregationKind();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.Property#getAggregationKind <em>Aggregation Kind</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Aggregation Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.information.AggregationKind
   * @see #getAggregationKind()
   * @generated
   */

	void setAggregationKind(AggregationKind value);







	/**
   * Returns the value of the '<em><b>Is Derived</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Derived</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Derived</em>' attribute.
   * @see #setIsDerived(boolean)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getProperty_IsDerived()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies whether the Property is derived, i.e., whether its value or values can be computed from other information\r\n[source: UML superstructure v2.2]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Property::isDerived' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsDerived();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.Property#isIsDerived <em>Is Derived</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Derived</em>' attribute.
   * @see #isIsDerived()
   * @generated
   */

	void setIsDerived(boolean value);







	/**
   * Returns the value of the '<em><b>Is Read Only</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Read Only</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Read Only</em>' attribute.
   * @see #setIsReadOnly(boolean)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getProperty_IsReadOnly()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='isReadOnly' featureOwner='StructuralFeature'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='If true, the attribute may only be read, and not written\r\n[source: UML superstructure v2.2]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Property::isReadOnly' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsReadOnly();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.Property#isIsReadOnly <em>Is Read Only</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Read Only</em>' attribute.
   * @see #isIsReadOnly()
   * @generated
   */

	void setIsReadOnly(boolean value);







	/**
   * Returns the value of the '<em><b>Is Part Of Key</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Part Of Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Part Of Key</em>' attribute.
   * @see #setIsPartOfKey(boolean)
   * @see org.polarsys.capella.core.data.information.InformationPackage#getProperty_IsPartOfKey()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='isPartOfKey' featureOwner='eng.Property' fromStereotype='true'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies whether this Property is involved as a key to a table of values\r\n[source: Capella study]' constraints='none' type='\"true\" if the Property is used as a key' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsPartOfKey();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.Property#isIsPartOfKey <em>Is Part Of Key</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Part Of Key</em>' attribute.
   * @see #isIsPartOfKey()
   * @generated
   */

	void setIsPartOfKey(boolean value);







	/**
   * Returns the value of the '<em><b>Association</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Association</em>' reference.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getProperty_Association()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='association' featureOwner='Property'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='association'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='an association relationship related to this Property\r\n[source: Capella study]\r\n\r\nReferences the association of which this property is a member, if any.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Property::owningAssociation, uml::Property::association' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='navigableMembers'"
   * @generated
   */

	Association getAssociation();





} // Property
