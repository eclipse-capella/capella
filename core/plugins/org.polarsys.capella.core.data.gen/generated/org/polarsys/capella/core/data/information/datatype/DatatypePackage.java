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
package org.polarsys.capella.core.data.information.datatype;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.information.datatype.DatatypeFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='sub-package containing the definition of the predefined data types\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='system,logical,physical' usage\040examples='none' constraints='none' comment/notes='none' reference\040documentation='n/a'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface DatatypePackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "datatype"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/core/information/datatype/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.core.data.information.datatype"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	DatatypePackage eINSTANCE = org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datatype.impl.DataTypeImpl <em>Data Type</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datatype.impl.DataTypeImpl
   * @see org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl#getDataType()
   * @generated
   */
	int DATA_TYPE = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__OWNED_EXTENSIONS = CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__ID = CapellacorePackage.GENERALIZABLE_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__SID = CapellacorePackage.GENERALIZABLE_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__CONSTRAINTS = CapellacorePackage.GENERALIZABLE_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__OWNED_CONSTRAINTS = CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__NAME = CapellacorePackage.GENERALIZABLE_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__ABSTRACT_TYPED_ELEMENTS = CapellacorePackage.GENERALIZABLE_ELEMENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__INCOMING_TRACES = CapellacorePackage.GENERALIZABLE_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__OUTGOING_TRACES = CapellacorePackage.GENERALIZABLE_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__VISIBLE_IN_DOC = CapellacorePackage.GENERALIZABLE_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__VISIBLE_IN_LM = CapellacorePackage.GENERALIZABLE_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__SUMMARY = CapellacorePackage.GENERALIZABLE_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__DESCRIPTION = CapellacorePackage.GENERALIZABLE_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__REVIEW = CapellacorePackage.GENERALIZABLE_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__OWNED_PROPERTY_VALUES = CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__APPLIED_PROPERTY_VALUES = CapellacorePackage.GENERALIZABLE_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.GENERALIZABLE_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__STATUS = CapellacorePackage.GENERALIZABLE_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__FEATURES = CapellacorePackage.GENERALIZABLE_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__OWNED_TRACES = CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__CONTAINED_GENERIC_TRACES = CapellacorePackage.GENERALIZABLE_ELEMENT__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__NAMING_RULES = CapellacorePackage.GENERALIZABLE_ELEMENT__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__TYPED_ELEMENTS = CapellacorePackage.GENERALIZABLE_ELEMENT__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__ABSTRACT = CapellacorePackage.GENERALIZABLE_ELEMENT__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__OWNED_GENERALIZATIONS = CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__SUPER_GENERALIZATIONS = CapellacorePackage.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__SUB_GENERALIZATIONS = CapellacorePackage.GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__SUPER = CapellacorePackage.GENERALIZABLE_ELEMENT__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__SUB = CapellacorePackage.GENERALIZABLE_ELEMENT__SUB;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.GENERALIZABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__OWNED_DATA_VALUES = CapellacorePackage.GENERALIZABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__FINAL = CapellacorePackage.GENERALIZABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Discrete</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__DISCRETE = CapellacorePackage.GENERALIZABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__MIN_INCLUSIVE = CapellacorePackage.GENERALIZABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__MAX_INCLUSIVE = CapellacorePackage.GENERALIZABLE_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__PATTERN = CapellacorePackage.GENERALIZABLE_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__VISIBILITY = CapellacorePackage.GENERALIZABLE_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Default Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__DEFAULT_VALUE = CapellacorePackage.GENERALIZABLE_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Null Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__NULL_VALUE = CapellacorePackage.GENERALIZABLE_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Information Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__OWNED_INFORMATION_REALIZATIONS = CapellacorePackage.GENERALIZABLE_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Realized Data Types</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__REALIZED_DATA_TYPES = CapellacorePackage.GENERALIZABLE_ELEMENT_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Realizing Data Types</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE__REALIZING_DATA_TYPES = CapellacorePackage.GENERALIZABLE_ELEMENT_FEATURE_COUNT + 12;

	/**
   * The number of structural features of the '<em>Data Type</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_TYPE_FEATURE_COUNT = CapellacorePackage.GENERALIZABLE_ELEMENT_FEATURE_COUNT + 13;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datatype.impl.BooleanTypeImpl <em>Boolean Type</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datatype.impl.BooleanTypeImpl
   * @see org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl#getBooleanType()
   * @generated
   */
	int BOOLEAN_TYPE = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__OWNED_EXTENSIONS = DATA_TYPE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__ID = DATA_TYPE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__SID = DATA_TYPE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__CONSTRAINTS = DATA_TYPE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__OWNED_CONSTRAINTS = DATA_TYPE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__OWNED_MIGRATED_ELEMENTS = DATA_TYPE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__NAME = DATA_TYPE__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__ABSTRACT_TYPED_ELEMENTS = DATA_TYPE__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__INCOMING_TRACES = DATA_TYPE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__OUTGOING_TRACES = DATA_TYPE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__VISIBLE_IN_DOC = DATA_TYPE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__VISIBLE_IN_LM = DATA_TYPE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__SUMMARY = DATA_TYPE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__DESCRIPTION = DATA_TYPE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__REVIEW = DATA_TYPE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__OWNED_PROPERTY_VALUES = DATA_TYPE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__OWNED_ENUMERATION_PROPERTY_TYPES = DATA_TYPE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__APPLIED_PROPERTY_VALUES = DATA_TYPE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__OWNED_PROPERTY_VALUE_GROUPS = DATA_TYPE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__APPLIED_PROPERTY_VALUE_GROUPS = DATA_TYPE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__STATUS = DATA_TYPE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__FEATURES = DATA_TYPE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__OWNED_TRACES = DATA_TYPE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__CONTAINED_GENERIC_TRACES = DATA_TYPE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__NAMING_RULES = DATA_TYPE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__TYPED_ELEMENTS = DATA_TYPE__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__ABSTRACT = DATA_TYPE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__OWNED_GENERALIZATIONS = DATA_TYPE__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__SUPER_GENERALIZATIONS = DATA_TYPE__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__SUB_GENERALIZATIONS = DATA_TYPE__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__SUPER = DATA_TYPE__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__SUB = DATA_TYPE__SUB;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__OWNED_PROPERTY_VALUE_PKGS = DATA_TYPE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__OWNED_DATA_VALUES = DATA_TYPE__OWNED_DATA_VALUES;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__FINAL = DATA_TYPE__FINAL;

	/**
   * The feature id for the '<em><b>Discrete</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__DISCRETE = DATA_TYPE__DISCRETE;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__MIN_INCLUSIVE = DATA_TYPE__MIN_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__MAX_INCLUSIVE = DATA_TYPE__MAX_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__PATTERN = DATA_TYPE__PATTERN;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__VISIBILITY = DATA_TYPE__VISIBILITY;

	/**
   * The feature id for the '<em><b>Default Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__DEFAULT_VALUE = DATA_TYPE__DEFAULT_VALUE;

	/**
   * The feature id for the '<em><b>Null Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__NULL_VALUE = DATA_TYPE__NULL_VALUE;

	/**
   * The feature id for the '<em><b>Owned Information Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__OWNED_INFORMATION_REALIZATIONS = DATA_TYPE__OWNED_INFORMATION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Data Types</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__REALIZED_DATA_TYPES = DATA_TYPE__REALIZED_DATA_TYPES;

	/**
   * The feature id for the '<em><b>Realizing Data Types</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__REALIZING_DATA_TYPES = DATA_TYPE__REALIZING_DATA_TYPES;

	/**
   * The feature id for the '<em><b>Owned Literals</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__OWNED_LITERALS = DATA_TYPE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE__OWNED_DEFAULT_VALUE = DATA_TYPE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Boolean Type</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datatype.impl.EnumerationImpl <em>Enumeration</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datatype.impl.EnumerationImpl
   * @see org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl#getEnumeration()
   * @generated
   */
	int ENUMERATION = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_EXTENSIONS = DATA_TYPE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__ID = DATA_TYPE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__SID = DATA_TYPE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__CONSTRAINTS = DATA_TYPE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_CONSTRAINTS = DATA_TYPE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_MIGRATED_ELEMENTS = DATA_TYPE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__NAME = DATA_TYPE__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__ABSTRACT_TYPED_ELEMENTS = DATA_TYPE__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__INCOMING_TRACES = DATA_TYPE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OUTGOING_TRACES = DATA_TYPE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__VISIBLE_IN_DOC = DATA_TYPE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__VISIBLE_IN_LM = DATA_TYPE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__SUMMARY = DATA_TYPE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__DESCRIPTION = DATA_TYPE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__REVIEW = DATA_TYPE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_PROPERTY_VALUES = DATA_TYPE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_ENUMERATION_PROPERTY_TYPES = DATA_TYPE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__APPLIED_PROPERTY_VALUES = DATA_TYPE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_PROPERTY_VALUE_GROUPS = DATA_TYPE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__APPLIED_PROPERTY_VALUE_GROUPS = DATA_TYPE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__STATUS = DATA_TYPE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__FEATURES = DATA_TYPE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_TRACES = DATA_TYPE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__CONTAINED_GENERIC_TRACES = DATA_TYPE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__NAMING_RULES = DATA_TYPE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__TYPED_ELEMENTS = DATA_TYPE__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__ABSTRACT = DATA_TYPE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_GENERALIZATIONS = DATA_TYPE__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__SUPER_GENERALIZATIONS = DATA_TYPE__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__SUB_GENERALIZATIONS = DATA_TYPE__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__SUPER = DATA_TYPE__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__SUB = DATA_TYPE__SUB;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_PROPERTY_VALUE_PKGS = DATA_TYPE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_DATA_VALUES = DATA_TYPE__OWNED_DATA_VALUES;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__FINAL = DATA_TYPE__FINAL;

	/**
   * The feature id for the '<em><b>Discrete</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__DISCRETE = DATA_TYPE__DISCRETE;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__MIN_INCLUSIVE = DATA_TYPE__MIN_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__MAX_INCLUSIVE = DATA_TYPE__MAX_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__PATTERN = DATA_TYPE__PATTERN;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__VISIBILITY = DATA_TYPE__VISIBILITY;

	/**
   * The feature id for the '<em><b>Default Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__DEFAULT_VALUE = DATA_TYPE__DEFAULT_VALUE;

	/**
   * The feature id for the '<em><b>Null Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__NULL_VALUE = DATA_TYPE__NULL_VALUE;

	/**
   * The feature id for the '<em><b>Owned Information Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_INFORMATION_REALIZATIONS = DATA_TYPE__OWNED_INFORMATION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Data Types</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__REALIZED_DATA_TYPES = DATA_TYPE__REALIZED_DATA_TYPES;

	/**
   * The feature id for the '<em><b>Realizing Data Types</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__REALIZING_DATA_TYPES = DATA_TYPE__REALIZING_DATA_TYPES;

	/**
   * The feature id for the '<em><b>Owned Literals</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_LITERALS = DATA_TYPE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_DEFAULT_VALUE = DATA_TYPE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_NULL_VALUE = DATA_TYPE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_MIN_VALUE = DATA_TYPE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__OWNED_MAX_VALUE = DATA_TYPE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Domain Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION__DOMAIN_TYPE = DATA_TYPE_FEATURE_COUNT + 5;

	/**
   * The number of structural features of the '<em>Enumeration</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 6;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datatype.impl.StringTypeImpl <em>String Type</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datatype.impl.StringTypeImpl
   * @see org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl#getStringType()
   * @generated
   */
	int STRING_TYPE = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OWNED_EXTENSIONS = DATA_TYPE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__ID = DATA_TYPE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__SID = DATA_TYPE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__CONSTRAINTS = DATA_TYPE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OWNED_CONSTRAINTS = DATA_TYPE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OWNED_MIGRATED_ELEMENTS = DATA_TYPE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__NAME = DATA_TYPE__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__ABSTRACT_TYPED_ELEMENTS = DATA_TYPE__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__INCOMING_TRACES = DATA_TYPE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OUTGOING_TRACES = DATA_TYPE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__VISIBLE_IN_DOC = DATA_TYPE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__VISIBLE_IN_LM = DATA_TYPE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__SUMMARY = DATA_TYPE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__DESCRIPTION = DATA_TYPE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__REVIEW = DATA_TYPE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OWNED_PROPERTY_VALUES = DATA_TYPE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OWNED_ENUMERATION_PROPERTY_TYPES = DATA_TYPE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__APPLIED_PROPERTY_VALUES = DATA_TYPE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OWNED_PROPERTY_VALUE_GROUPS = DATA_TYPE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__APPLIED_PROPERTY_VALUE_GROUPS = DATA_TYPE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__STATUS = DATA_TYPE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__FEATURES = DATA_TYPE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OWNED_TRACES = DATA_TYPE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__CONTAINED_GENERIC_TRACES = DATA_TYPE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__NAMING_RULES = DATA_TYPE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__TYPED_ELEMENTS = DATA_TYPE__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__ABSTRACT = DATA_TYPE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OWNED_GENERALIZATIONS = DATA_TYPE__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__SUPER_GENERALIZATIONS = DATA_TYPE__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__SUB_GENERALIZATIONS = DATA_TYPE__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__SUPER = DATA_TYPE__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__SUB = DATA_TYPE__SUB;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OWNED_PROPERTY_VALUE_PKGS = DATA_TYPE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OWNED_DATA_VALUES = DATA_TYPE__OWNED_DATA_VALUES;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__FINAL = DATA_TYPE__FINAL;

	/**
   * The feature id for the '<em><b>Discrete</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__DISCRETE = DATA_TYPE__DISCRETE;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__MIN_INCLUSIVE = DATA_TYPE__MIN_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__MAX_INCLUSIVE = DATA_TYPE__MAX_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__PATTERN = DATA_TYPE__PATTERN;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__VISIBILITY = DATA_TYPE__VISIBILITY;

	/**
   * The feature id for the '<em><b>Default Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__DEFAULT_VALUE = DATA_TYPE__DEFAULT_VALUE;

	/**
   * The feature id for the '<em><b>Null Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__NULL_VALUE = DATA_TYPE__NULL_VALUE;

	/**
   * The feature id for the '<em><b>Owned Information Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OWNED_INFORMATION_REALIZATIONS = DATA_TYPE__OWNED_INFORMATION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Data Types</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__REALIZED_DATA_TYPES = DATA_TYPE__REALIZED_DATA_TYPES;

	/**
   * The feature id for the '<em><b>Realizing Data Types</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__REALIZING_DATA_TYPES = DATA_TYPE__REALIZING_DATA_TYPES;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OWNED_DEFAULT_VALUE = DATA_TYPE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OWNED_NULL_VALUE = DATA_TYPE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OWNED_MIN_LENGTH = DATA_TYPE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE__OWNED_MAX_LENGTH = DATA_TYPE_FEATURE_COUNT + 3;

	/**
   * The number of structural features of the '<em>String Type</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 4;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datatype.impl.NumericTypeImpl <em>Numeric Type</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datatype.impl.NumericTypeImpl
   * @see org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl#getNumericType()
   * @generated
   */
	int NUMERIC_TYPE = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OWNED_EXTENSIONS = DATA_TYPE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__ID = DATA_TYPE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__SID = DATA_TYPE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__CONSTRAINTS = DATA_TYPE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OWNED_CONSTRAINTS = DATA_TYPE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OWNED_MIGRATED_ELEMENTS = DATA_TYPE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__NAME = DATA_TYPE__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__ABSTRACT_TYPED_ELEMENTS = DATA_TYPE__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__INCOMING_TRACES = DATA_TYPE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OUTGOING_TRACES = DATA_TYPE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__VISIBLE_IN_DOC = DATA_TYPE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__VISIBLE_IN_LM = DATA_TYPE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__SUMMARY = DATA_TYPE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__DESCRIPTION = DATA_TYPE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__REVIEW = DATA_TYPE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OWNED_PROPERTY_VALUES = DATA_TYPE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OWNED_ENUMERATION_PROPERTY_TYPES = DATA_TYPE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__APPLIED_PROPERTY_VALUES = DATA_TYPE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OWNED_PROPERTY_VALUE_GROUPS = DATA_TYPE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__APPLIED_PROPERTY_VALUE_GROUPS = DATA_TYPE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__STATUS = DATA_TYPE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__FEATURES = DATA_TYPE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OWNED_TRACES = DATA_TYPE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__CONTAINED_GENERIC_TRACES = DATA_TYPE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__NAMING_RULES = DATA_TYPE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__TYPED_ELEMENTS = DATA_TYPE__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__ABSTRACT = DATA_TYPE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OWNED_GENERALIZATIONS = DATA_TYPE__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__SUPER_GENERALIZATIONS = DATA_TYPE__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__SUB_GENERALIZATIONS = DATA_TYPE__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__SUPER = DATA_TYPE__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__SUB = DATA_TYPE__SUB;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OWNED_PROPERTY_VALUE_PKGS = DATA_TYPE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OWNED_DATA_VALUES = DATA_TYPE__OWNED_DATA_VALUES;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__FINAL = DATA_TYPE__FINAL;

	/**
   * The feature id for the '<em><b>Discrete</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__DISCRETE = DATA_TYPE__DISCRETE;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__MIN_INCLUSIVE = DATA_TYPE__MIN_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__MAX_INCLUSIVE = DATA_TYPE__MAX_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__PATTERN = DATA_TYPE__PATTERN;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__VISIBILITY = DATA_TYPE__VISIBILITY;

	/**
   * The feature id for the '<em><b>Default Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__DEFAULT_VALUE = DATA_TYPE__DEFAULT_VALUE;

	/**
   * The feature id for the '<em><b>Null Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__NULL_VALUE = DATA_TYPE__NULL_VALUE;

	/**
   * The feature id for the '<em><b>Owned Information Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OWNED_INFORMATION_REALIZATIONS = DATA_TYPE__OWNED_INFORMATION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Data Types</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__REALIZED_DATA_TYPES = DATA_TYPE__REALIZED_DATA_TYPES;

	/**
   * The feature id for the '<em><b>Realizing Data Types</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__REALIZING_DATA_TYPES = DATA_TYPE__REALIZING_DATA_TYPES;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__KIND = DATA_TYPE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OWNED_DEFAULT_VALUE = DATA_TYPE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OWNED_NULL_VALUE = DATA_TYPE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OWNED_MIN_VALUE = DATA_TYPE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE__OWNED_MAX_VALUE = DATA_TYPE_FEATURE_COUNT + 4;

	/**
   * The number of structural features of the '<em>Numeric Type</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 5;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datatype.impl.PhysicalQuantityImpl <em>Physical Quantity</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datatype.impl.PhysicalQuantityImpl
   * @see org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl#getPhysicalQuantity()
   * @generated
   */
	int PHYSICAL_QUANTITY = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OWNED_EXTENSIONS = NUMERIC_TYPE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__ID = NUMERIC_TYPE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__SID = NUMERIC_TYPE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__CONSTRAINTS = NUMERIC_TYPE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OWNED_CONSTRAINTS = NUMERIC_TYPE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OWNED_MIGRATED_ELEMENTS = NUMERIC_TYPE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__NAME = NUMERIC_TYPE__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__ABSTRACT_TYPED_ELEMENTS = NUMERIC_TYPE__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__INCOMING_TRACES = NUMERIC_TYPE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OUTGOING_TRACES = NUMERIC_TYPE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__VISIBLE_IN_DOC = NUMERIC_TYPE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__VISIBLE_IN_LM = NUMERIC_TYPE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__SUMMARY = NUMERIC_TYPE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__DESCRIPTION = NUMERIC_TYPE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__REVIEW = NUMERIC_TYPE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OWNED_PROPERTY_VALUES = NUMERIC_TYPE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OWNED_ENUMERATION_PROPERTY_TYPES = NUMERIC_TYPE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__APPLIED_PROPERTY_VALUES = NUMERIC_TYPE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OWNED_PROPERTY_VALUE_GROUPS = NUMERIC_TYPE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__APPLIED_PROPERTY_VALUE_GROUPS = NUMERIC_TYPE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__STATUS = NUMERIC_TYPE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__FEATURES = NUMERIC_TYPE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OWNED_TRACES = NUMERIC_TYPE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__CONTAINED_GENERIC_TRACES = NUMERIC_TYPE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__NAMING_RULES = NUMERIC_TYPE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__TYPED_ELEMENTS = NUMERIC_TYPE__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__ABSTRACT = NUMERIC_TYPE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OWNED_GENERALIZATIONS = NUMERIC_TYPE__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__SUPER_GENERALIZATIONS = NUMERIC_TYPE__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__SUB_GENERALIZATIONS = NUMERIC_TYPE__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__SUPER = NUMERIC_TYPE__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__SUB = NUMERIC_TYPE__SUB;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OWNED_PROPERTY_VALUE_PKGS = NUMERIC_TYPE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OWNED_DATA_VALUES = NUMERIC_TYPE__OWNED_DATA_VALUES;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__FINAL = NUMERIC_TYPE__FINAL;

	/**
   * The feature id for the '<em><b>Discrete</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__DISCRETE = NUMERIC_TYPE__DISCRETE;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__MIN_INCLUSIVE = NUMERIC_TYPE__MIN_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__MAX_INCLUSIVE = NUMERIC_TYPE__MAX_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__PATTERN = NUMERIC_TYPE__PATTERN;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__VISIBILITY = NUMERIC_TYPE__VISIBILITY;

	/**
   * The feature id for the '<em><b>Default Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__DEFAULT_VALUE = NUMERIC_TYPE__DEFAULT_VALUE;

	/**
   * The feature id for the '<em><b>Null Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__NULL_VALUE = NUMERIC_TYPE__NULL_VALUE;

	/**
   * The feature id for the '<em><b>Owned Information Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OWNED_INFORMATION_REALIZATIONS = NUMERIC_TYPE__OWNED_INFORMATION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Data Types</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__REALIZED_DATA_TYPES = NUMERIC_TYPE__REALIZED_DATA_TYPES;

	/**
   * The feature id for the '<em><b>Realizing Data Types</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__REALIZING_DATA_TYPES = NUMERIC_TYPE__REALIZING_DATA_TYPES;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__KIND = NUMERIC_TYPE__KIND;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OWNED_DEFAULT_VALUE = NUMERIC_TYPE__OWNED_DEFAULT_VALUE;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OWNED_NULL_VALUE = NUMERIC_TYPE__OWNED_NULL_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OWNED_MIN_VALUE = NUMERIC_TYPE__OWNED_MIN_VALUE;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__OWNED_MAX_VALUE = NUMERIC_TYPE__OWNED_MAX_VALUE;

	/**
   * The feature id for the '<em><b>Unit</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY__UNIT = NUMERIC_TYPE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Physical Quantity</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_QUANTITY_FEATURE_COUNT = NUMERIC_TYPE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datatype.NumericTypeKind <em>Numeric Type Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datatype.NumericTypeKind
   * @see org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl#getNumericTypeKind()
   * @generated
   */
	int NUMERIC_TYPE_KIND = 6;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datatype.DataType <em>Data Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Type</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.DataType
   * @generated
   */
	EClass getDataType();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.datatype.DataType#isDiscrete <em>Discrete</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Discrete</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.DataType#isDiscrete()
   * @see #getDataType()
   * @generated
   */
	EAttribute getDataType_Discrete();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.datatype.DataType#isMinInclusive <em>Min Inclusive</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Min Inclusive</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.DataType#isMinInclusive()
   * @see #getDataType()
   * @generated
   */
	EAttribute getDataType_MinInclusive();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.datatype.DataType#isMaxInclusive <em>Max Inclusive</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Max Inclusive</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.DataType#isMaxInclusive()
   * @see #getDataType()
   * @generated
   */
	EAttribute getDataType_MaxInclusive();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.datatype.DataType#getPattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pattern</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.DataType#getPattern()
   * @see #getDataType()
   * @generated
   */
	EAttribute getDataType_Pattern();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.datatype.DataType#getVisibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visibility</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.DataType#getVisibility()
   * @see #getDataType()
   * @generated
   */
	EAttribute getDataType_Visibility();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datatype.DataType#getDefaultValue <em>Default Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Default Value</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.DataType#getDefaultValue()
   * @see #getDataType()
   * @generated
   */
	EReference getDataType_DefaultValue();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datatype.DataType#getNullValue <em>Null Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Null Value</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.DataType#getNullValue()
   * @see #getDataType()
   * @generated
   */
	EReference getDataType_NullValue();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.datatype.DataType#getOwnedInformationRealizations <em>Owned Information Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Information Realizations</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.DataType#getOwnedInformationRealizations()
   * @see #getDataType()
   * @generated
   */
	EReference getDataType_OwnedInformationRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.datatype.DataType#getRealizedDataTypes <em>Realized Data Types</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Data Types</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.DataType#getRealizedDataTypes()
   * @see #getDataType()
   * @generated
   */
	EReference getDataType_RealizedDataTypes();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.datatype.DataType#getRealizingDataTypes <em>Realizing Data Types</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Data Types</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.DataType#getRealizingDataTypes()
   * @see #getDataType()
   * @generated
   */
	EReference getDataType_RealizingDataTypes();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datatype.BooleanType <em>Boolean Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Boolean Type</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.BooleanType
   * @generated
   */
	EClass getBooleanType();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.datatype.BooleanType#getOwnedLiterals <em>Owned Literals</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Literals</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.BooleanType#getOwnedLiterals()
   * @see #getBooleanType()
   * @generated
   */
	EReference getBooleanType_OwnedLiterals();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datatype.BooleanType#getOwnedDefaultValue <em>Owned Default Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Default Value</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.BooleanType#getOwnedDefaultValue()
   * @see #getBooleanType()
   * @generated
   */
	EReference getBooleanType_OwnedDefaultValue();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datatype.Enumeration <em>Enumeration</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enumeration</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.Enumeration
   * @generated
   */
	EClass getEnumeration();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedLiterals <em>Owned Literals</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Literals</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedLiterals()
   * @see #getEnumeration()
   * @generated
   */
	EReference getEnumeration_OwnedLiterals();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedDefaultValue <em>Owned Default Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Default Value</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedDefaultValue()
   * @see #getEnumeration()
   * @generated
   */
	EReference getEnumeration_OwnedDefaultValue();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedNullValue <em>Owned Null Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Null Value</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedNullValue()
   * @see #getEnumeration()
   * @generated
   */
	EReference getEnumeration_OwnedNullValue();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedMinValue <em>Owned Min Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Min Value</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedMinValue()
   * @see #getEnumeration()
   * @generated
   */
	EReference getEnumeration_OwnedMinValue();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedMaxValue <em>Owned Max Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Max Value</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.Enumeration#getOwnedMaxValue()
   * @see #getEnumeration()
   * @generated
   */
	EReference getEnumeration_OwnedMaxValue();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datatype.Enumeration#getDomainType <em>Domain Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Domain Type</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.Enumeration#getDomainType()
   * @see #getEnumeration()
   * @generated
   */
	EReference getEnumeration_DomainType();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datatype.StringType <em>String Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Type</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.StringType
   * @generated
   */
	EClass getStringType();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datatype.StringType#getOwnedDefaultValue <em>Owned Default Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Default Value</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.StringType#getOwnedDefaultValue()
   * @see #getStringType()
   * @generated
   */
	EReference getStringType_OwnedDefaultValue();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datatype.StringType#getOwnedNullValue <em>Owned Null Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Null Value</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.StringType#getOwnedNullValue()
   * @see #getStringType()
   * @generated
   */
	EReference getStringType_OwnedNullValue();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datatype.StringType#getOwnedMinLength <em>Owned Min Length</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Min Length</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.StringType#getOwnedMinLength()
   * @see #getStringType()
   * @generated
   */
	EReference getStringType_OwnedMinLength();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datatype.StringType#getOwnedMaxLength <em>Owned Max Length</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Max Length</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.StringType#getOwnedMaxLength()
   * @see #getStringType()
   * @generated
   */
	EReference getStringType_OwnedMaxLength();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datatype.NumericType <em>Numeric Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Numeric Type</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.NumericType
   * @generated
   */
	EClass getNumericType();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.datatype.NumericType#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.NumericType#getKind()
   * @see #getNumericType()
   * @generated
   */
	EAttribute getNumericType_Kind();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedDefaultValue <em>Owned Default Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Default Value</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedDefaultValue()
   * @see #getNumericType()
   * @generated
   */
	EReference getNumericType_OwnedDefaultValue();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedNullValue <em>Owned Null Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Null Value</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedNullValue()
   * @see #getNumericType()
   * @generated
   */
	EReference getNumericType_OwnedNullValue();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedMinValue <em>Owned Min Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Min Value</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedMinValue()
   * @see #getNumericType()
   * @generated
   */
	EReference getNumericType_OwnedMinValue();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedMaxValue <em>Owned Max Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Max Value</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.NumericType#getOwnedMaxValue()
   * @see #getNumericType()
   * @generated
   */
	EReference getNumericType_OwnedMaxValue();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datatype.PhysicalQuantity <em>Physical Quantity</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Physical Quantity</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.PhysicalQuantity
   * @generated
   */
	EClass getPhysicalQuantity();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datatype.PhysicalQuantity#getUnit <em>Unit</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Unit</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.PhysicalQuantity#getUnit()
   * @see #getPhysicalQuantity()
   * @generated
   */
	EReference getPhysicalQuantity_Unit();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.information.datatype.NumericTypeKind <em>Numeric Type Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Numeric Type Kind</em>'.
   * @see org.polarsys.capella.core.data.information.datatype.NumericTypeKind
   * @generated
   */
	EEnum getNumericTypeKind();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	DatatypeFactory getDatatypeFactory();

	/**
   * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
   * @generated
   */
	interface Literals {
		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datatype.impl.DataTypeImpl <em>Data Type</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datatype.impl.DataTypeImpl
     * @see org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl#getDataType()
     * @generated
     */
		EClass DATA_TYPE = eINSTANCE.getDataType();

		/**
     * The meta object literal for the '<em><b>Discrete</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute DATA_TYPE__DISCRETE = eINSTANCE.getDataType_Discrete();

		/**
     * The meta object literal for the '<em><b>Min Inclusive</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute DATA_TYPE__MIN_INCLUSIVE = eINSTANCE.getDataType_MinInclusive();

		/**
     * The meta object literal for the '<em><b>Max Inclusive</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute DATA_TYPE__MAX_INCLUSIVE = eINSTANCE.getDataType_MaxInclusive();

		/**
     * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute DATA_TYPE__PATTERN = eINSTANCE.getDataType_Pattern();

		/**
     * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute DATA_TYPE__VISIBILITY = eINSTANCE.getDataType_Visibility();

		/**
     * The meta object literal for the '<em><b>Default Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_TYPE__DEFAULT_VALUE = eINSTANCE.getDataType_DefaultValue();

		/**
     * The meta object literal for the '<em><b>Null Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_TYPE__NULL_VALUE = eINSTANCE.getDataType_NullValue();

		/**
     * The meta object literal for the '<em><b>Owned Information Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_TYPE__OWNED_INFORMATION_REALIZATIONS = eINSTANCE.getDataType_OwnedInformationRealizations();

		/**
     * The meta object literal for the '<em><b>Realized Data Types</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_TYPE__REALIZED_DATA_TYPES = eINSTANCE.getDataType_RealizedDataTypes();

		/**
     * The meta object literal for the '<em><b>Realizing Data Types</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_TYPE__REALIZING_DATA_TYPES = eINSTANCE.getDataType_RealizingDataTypes();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datatype.impl.BooleanTypeImpl <em>Boolean Type</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datatype.impl.BooleanTypeImpl
     * @see org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl#getBooleanType()
     * @generated
     */
		EClass BOOLEAN_TYPE = eINSTANCE.getBooleanType();

		/**
     * The meta object literal for the '<em><b>Owned Literals</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BOOLEAN_TYPE__OWNED_LITERALS = eINSTANCE.getBooleanType_OwnedLiterals();

		/**
     * The meta object literal for the '<em><b>Owned Default Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BOOLEAN_TYPE__OWNED_DEFAULT_VALUE = eINSTANCE.getBooleanType_OwnedDefaultValue();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datatype.impl.EnumerationImpl <em>Enumeration</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datatype.impl.EnumerationImpl
     * @see org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl#getEnumeration()
     * @generated
     */
		EClass ENUMERATION = eINSTANCE.getEnumeration();

		/**
     * The meta object literal for the '<em><b>Owned Literals</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENUMERATION__OWNED_LITERALS = eINSTANCE.getEnumeration_OwnedLiterals();

		/**
     * The meta object literal for the '<em><b>Owned Default Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENUMERATION__OWNED_DEFAULT_VALUE = eINSTANCE.getEnumeration_OwnedDefaultValue();

		/**
     * The meta object literal for the '<em><b>Owned Null Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENUMERATION__OWNED_NULL_VALUE = eINSTANCE.getEnumeration_OwnedNullValue();

		/**
     * The meta object literal for the '<em><b>Owned Min Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENUMERATION__OWNED_MIN_VALUE = eINSTANCE.getEnumeration_OwnedMinValue();

		/**
     * The meta object literal for the '<em><b>Owned Max Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENUMERATION__OWNED_MAX_VALUE = eINSTANCE.getEnumeration_OwnedMaxValue();

		/**
     * The meta object literal for the '<em><b>Domain Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENUMERATION__DOMAIN_TYPE = eINSTANCE.getEnumeration_DomainType();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datatype.impl.StringTypeImpl <em>String Type</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datatype.impl.StringTypeImpl
     * @see org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl#getStringType()
     * @generated
     */
		EClass STRING_TYPE = eINSTANCE.getStringType();

		/**
     * The meta object literal for the '<em><b>Owned Default Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STRING_TYPE__OWNED_DEFAULT_VALUE = eINSTANCE.getStringType_OwnedDefaultValue();

		/**
     * The meta object literal for the '<em><b>Owned Null Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STRING_TYPE__OWNED_NULL_VALUE = eINSTANCE.getStringType_OwnedNullValue();

		/**
     * The meta object literal for the '<em><b>Owned Min Length</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STRING_TYPE__OWNED_MIN_LENGTH = eINSTANCE.getStringType_OwnedMinLength();

		/**
     * The meta object literal for the '<em><b>Owned Max Length</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STRING_TYPE__OWNED_MAX_LENGTH = eINSTANCE.getStringType_OwnedMaxLength();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datatype.impl.NumericTypeImpl <em>Numeric Type</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datatype.impl.NumericTypeImpl
     * @see org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl#getNumericType()
     * @generated
     */
		EClass NUMERIC_TYPE = eINSTANCE.getNumericType();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute NUMERIC_TYPE__KIND = eINSTANCE.getNumericType_Kind();

		/**
     * The meta object literal for the '<em><b>Owned Default Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference NUMERIC_TYPE__OWNED_DEFAULT_VALUE = eINSTANCE.getNumericType_OwnedDefaultValue();

		/**
     * The meta object literal for the '<em><b>Owned Null Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference NUMERIC_TYPE__OWNED_NULL_VALUE = eINSTANCE.getNumericType_OwnedNullValue();

		/**
     * The meta object literal for the '<em><b>Owned Min Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference NUMERIC_TYPE__OWNED_MIN_VALUE = eINSTANCE.getNumericType_OwnedMinValue();

		/**
     * The meta object literal for the '<em><b>Owned Max Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference NUMERIC_TYPE__OWNED_MAX_VALUE = eINSTANCE.getNumericType_OwnedMaxValue();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datatype.impl.PhysicalQuantityImpl <em>Physical Quantity</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datatype.impl.PhysicalQuantityImpl
     * @see org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl#getPhysicalQuantity()
     * @generated
     */
		EClass PHYSICAL_QUANTITY = eINSTANCE.getPhysicalQuantity();

		/**
     * The meta object literal for the '<em><b>Unit</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_QUANTITY__UNIT = eINSTANCE.getPhysicalQuantity_Unit();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datatype.NumericTypeKind <em>Numeric Type Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datatype.NumericTypeKind
     * @see org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl#getNumericTypeKind()
     * @generated
     */
		EEnum NUMERIC_TYPE_KIND = eINSTANCE.getNumericTypeKind();

	}

} //DatatypePackage
