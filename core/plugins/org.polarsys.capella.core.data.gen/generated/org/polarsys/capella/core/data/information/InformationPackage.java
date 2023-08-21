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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

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
 * @see org.polarsys.capella.core.data.information.InformationFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Information aims at defining the data transmission language (named Information due to the namespacing strange effects if it would have been named Data). It includes the notion of data as well as the different data communication means.\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='none' constraints='This package depends on the model CapellaCore.ecore' comment/notes='none' reference\040documentation='n/a'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface InformationPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "information"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/core/information/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.core.data.information"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	InformationPackage eINSTANCE = org.polarsys.capella.core.data.information.impl.InformationPackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.PropertyImpl <em>Property</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.PropertyImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getProperty()
   * @generated
   */
	int PROPERTY = 15;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__OWNED_EXTENSIONS = CapellacorePackage.FEATURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__ID = CapellacorePackage.FEATURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__SID = CapellacorePackage.FEATURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__CONSTRAINTS = CapellacorePackage.FEATURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__OWNED_CONSTRAINTS = CapellacorePackage.FEATURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.FEATURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__NAME = CapellacorePackage.FEATURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__INCOMING_TRACES = CapellacorePackage.FEATURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__OUTGOING_TRACES = CapellacorePackage.FEATURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__VISIBLE_IN_DOC = CapellacorePackage.FEATURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__VISIBLE_IN_LM = CapellacorePackage.FEATURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__SUMMARY = CapellacorePackage.FEATURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__DESCRIPTION = CapellacorePackage.FEATURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__REVIEW = CapellacorePackage.FEATURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__OWNED_PROPERTY_VALUES = CapellacorePackage.FEATURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.FEATURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__APPLIED_PROPERTY_VALUES = CapellacorePackage.FEATURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.FEATURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.FEATURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__STATUS = CapellacorePackage.FEATURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__FEATURES = CapellacorePackage.FEATURE__FEATURES;

	/**
   * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__IS_ABSTRACT = CapellacorePackage.FEATURE__IS_ABSTRACT;

	/**
   * The feature id for the '<em><b>Is Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__IS_STATIC = CapellacorePackage.FEATURE__IS_STATIC;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__VISIBILITY = CapellacorePackage.FEATURE__VISIBILITY;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__ABSTRACT_TYPE = CapellacorePackage.FEATURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__TYPE = CapellacorePackage.FEATURE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__ORDERED = CapellacorePackage.FEATURE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__UNIQUE = CapellacorePackage.FEATURE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__MIN_INCLUSIVE = CapellacorePackage.FEATURE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__MAX_INCLUSIVE = CapellacorePackage.FEATURE_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__OWNED_DEFAULT_VALUE = CapellacorePackage.FEATURE_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__OWNED_MIN_VALUE = CapellacorePackage.FEATURE_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__OWNED_MAX_VALUE = CapellacorePackage.FEATURE_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__OWNED_NULL_VALUE = CapellacorePackage.FEATURE_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__OWNED_MIN_CARD = CapellacorePackage.FEATURE_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__OWNED_MIN_LENGTH = CapellacorePackage.FEATURE_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__OWNED_MAX_CARD = CapellacorePackage.FEATURE_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__OWNED_MAX_LENGTH = CapellacorePackage.FEATURE_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__FINAL = CapellacorePackage.FEATURE_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Aggregation Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__AGGREGATION_KIND = CapellacorePackage.FEATURE_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Is Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__IS_DERIVED = CapellacorePackage.FEATURE_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__IS_READ_ONLY = CapellacorePackage.FEATURE_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Is Part Of Key</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__IS_PART_OF_KEY = CapellacorePackage.FEATURE_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Association</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY__ASSOCIATION = CapellacorePackage.FEATURE_FEATURE_COUNT + 19;

	/**
   * The number of structural features of the '<em>Property</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_FEATURE_COUNT = CapellacorePackage.FEATURE_FEATURE_COUNT + 20;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.AbstractInstanceImpl <em>Abstract Instance</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.AbstractInstanceImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getAbstractInstance()
   * @generated
   */
	int ABSTRACT_INSTANCE = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__OWNED_EXTENSIONS = PROPERTY__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__ID = PROPERTY__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__SID = PROPERTY__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__CONSTRAINTS = PROPERTY__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__OWNED_CONSTRAINTS = PROPERTY__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__OWNED_MIGRATED_ELEMENTS = PROPERTY__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__NAME = PROPERTY__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__INCOMING_TRACES = PROPERTY__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__OUTGOING_TRACES = PROPERTY__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__VISIBLE_IN_DOC = PROPERTY__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__VISIBLE_IN_LM = PROPERTY__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__SUMMARY = PROPERTY__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__DESCRIPTION = PROPERTY__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__REVIEW = PROPERTY__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__OWNED_PROPERTY_VALUES = PROPERTY__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__OWNED_ENUMERATION_PROPERTY_TYPES = PROPERTY__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__APPLIED_PROPERTY_VALUES = PROPERTY__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__OWNED_PROPERTY_VALUE_GROUPS = PROPERTY__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__APPLIED_PROPERTY_VALUE_GROUPS = PROPERTY__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__STATUS = PROPERTY__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__FEATURES = PROPERTY__FEATURES;

	/**
   * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__IS_ABSTRACT = PROPERTY__IS_ABSTRACT;

	/**
   * The feature id for the '<em><b>Is Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__IS_STATIC = PROPERTY__IS_STATIC;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__VISIBILITY = PROPERTY__VISIBILITY;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__ABSTRACT_TYPE = PROPERTY__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__TYPE = PROPERTY__TYPE;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__ORDERED = PROPERTY__ORDERED;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__UNIQUE = PROPERTY__UNIQUE;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__MIN_INCLUSIVE = PROPERTY__MIN_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__MAX_INCLUSIVE = PROPERTY__MAX_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__OWNED_DEFAULT_VALUE = PROPERTY__OWNED_DEFAULT_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__OWNED_MIN_VALUE = PROPERTY__OWNED_MIN_VALUE;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__OWNED_MAX_VALUE = PROPERTY__OWNED_MAX_VALUE;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__OWNED_NULL_VALUE = PROPERTY__OWNED_NULL_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__OWNED_MIN_CARD = PROPERTY__OWNED_MIN_CARD;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__OWNED_MIN_LENGTH = PROPERTY__OWNED_MIN_LENGTH;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__OWNED_MAX_CARD = PROPERTY__OWNED_MAX_CARD;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__OWNED_MAX_LENGTH = PROPERTY__OWNED_MAX_LENGTH;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__FINAL = PROPERTY__FINAL;

	/**
   * The feature id for the '<em><b>Aggregation Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__AGGREGATION_KIND = PROPERTY__AGGREGATION_KIND;

	/**
   * The feature id for the '<em><b>Is Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__IS_DERIVED = PROPERTY__IS_DERIVED;

	/**
   * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__IS_READ_ONLY = PROPERTY__IS_READ_ONLY;

	/**
   * The feature id for the '<em><b>Is Part Of Key</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__IS_PART_OF_KEY = PROPERTY__IS_PART_OF_KEY;

	/**
   * The feature id for the '<em><b>Association</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__ASSOCIATION = PROPERTY__ASSOCIATION;

	/**
   * The feature id for the '<em><b>Representing Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE__REPRESENTING_INSTANCE_ROLES = PROPERTY_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Instance</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_INSTANCE_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.AssociationPkgImpl <em>Association Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.AssociationPkgImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getAssociationPkg()
   * @generated
   */
	int ASSOCIATION_PKG = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__ID = CapellacorePackage.STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__SID = CapellacorePackage.STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__VISIBILITY = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Associations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG__OWNED_ASSOCIATIONS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Association Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_PKG_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.AssociationImpl <em>Association</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.AssociationImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getAssociation()
   * @generated
   */
	int ASSOCIATION = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__OWNED_EXTENSIONS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__ID = CapellacorePackage.NAMED_RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__SID = CapellacorePackage.NAMED_RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__CONSTRAINTS = CapellacorePackage.NAMED_RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__REALIZED_FLOW = CapellacorePackage.NAMED_RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__INCOMING_TRACES = CapellacorePackage.NAMED_RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__OUTGOING_TRACES = CapellacorePackage.NAMED_RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__VISIBLE_IN_DOC = CapellacorePackage.NAMED_RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__VISIBLE_IN_LM = CapellacorePackage.NAMED_RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__SUMMARY = CapellacorePackage.NAMED_RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__DESCRIPTION = CapellacorePackage.NAMED_RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__REVIEW = CapellacorePackage.NAMED_RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__STATUS = CapellacorePackage.NAMED_RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__FEATURES = CapellacorePackage.NAMED_RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__NAME = CapellacorePackage.NAMED_RELATIONSHIP__NAME;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__NAMING_RULES = CapellacorePackage.NAMED_RELATIONSHIP__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Members</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__OWNED_MEMBERS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Navigable Members</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION__NAVIGABLE_MEMBERS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Association</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ASSOCIATION_FEATURE_COUNT = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.ClassImpl <em>Class</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.ClassImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getClass_()
   * @generated
   */
	int CLASS = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__OWNED_EXTENSIONS = CapellacorePackage.GENERAL_CLASS__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__ID = CapellacorePackage.GENERAL_CLASS__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__SID = CapellacorePackage.GENERAL_CLASS__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__CONSTRAINTS = CapellacorePackage.GENERAL_CLASS__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__OWNED_CONSTRAINTS = CapellacorePackage.GENERAL_CLASS__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.GENERAL_CLASS__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__NAME = CapellacorePackage.GENERAL_CLASS__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__ABSTRACT_TYPED_ELEMENTS = CapellacorePackage.GENERAL_CLASS__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__INCOMING_TRACES = CapellacorePackage.GENERAL_CLASS__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__OUTGOING_TRACES = CapellacorePackage.GENERAL_CLASS__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__VISIBLE_IN_DOC = CapellacorePackage.GENERAL_CLASS__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__VISIBLE_IN_LM = CapellacorePackage.GENERAL_CLASS__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__SUMMARY = CapellacorePackage.GENERAL_CLASS__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__DESCRIPTION = CapellacorePackage.GENERAL_CLASS__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__REVIEW = CapellacorePackage.GENERAL_CLASS__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__OWNED_PROPERTY_VALUES = CapellacorePackage.GENERAL_CLASS__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.GENERAL_CLASS__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__APPLIED_PROPERTY_VALUES = CapellacorePackage.GENERAL_CLASS__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.GENERAL_CLASS__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.GENERAL_CLASS__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__STATUS = CapellacorePackage.GENERAL_CLASS__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__FEATURES = CapellacorePackage.GENERAL_CLASS__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__OWNED_TRACES = CapellacorePackage.GENERAL_CLASS__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__CONTAINED_GENERIC_TRACES = CapellacorePackage.GENERAL_CLASS__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__NAMING_RULES = CapellacorePackage.GENERAL_CLASS__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__TYPED_ELEMENTS = CapellacorePackage.GENERAL_CLASS__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__ABSTRACT = CapellacorePackage.GENERAL_CLASS__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__OWNED_GENERALIZATIONS = CapellacorePackage.GENERAL_CLASS__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__SUPER_GENERALIZATIONS = CapellacorePackage.GENERAL_CLASS__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__SUB_GENERALIZATIONS = CapellacorePackage.GENERAL_CLASS__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__SUPER = CapellacorePackage.GENERAL_CLASS__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__SUB = CapellacorePackage.GENERAL_CLASS__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__OWNED_FEATURES = CapellacorePackage.GENERAL_CLASS__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__CONTAINED_PROPERTIES = CapellacorePackage.GENERAL_CLASS__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__FINAL = CapellacorePackage.GENERAL_CLASS__FINAL;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__VISIBILITY = CapellacorePackage.GENERAL_CLASS__VISIBILITY;

	/**
   * The feature id for the '<em><b>Contained Operations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__CONTAINED_OPERATIONS = CapellacorePackage.GENERAL_CLASS__CONTAINED_OPERATIONS;

	/**
   * The feature id for the '<em><b>Nested General Classes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__NESTED_GENERAL_CLASSES = CapellacorePackage.GENERAL_CLASS__NESTED_GENERAL_CLASSES;

	/**
   * The feature id for the '<em><b>Is Primitive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__IS_PRIMITIVE = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Key Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__KEY_PARTS = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__OWNED_STATE_MACHINES = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__OWNED_DATA_VALUES = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Information Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__OWNED_INFORMATION_REALIZATIONS = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Realized Classes</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__REALIZED_CLASSES = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Realizing Classes</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS__REALIZING_CLASSES = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 6;

	/**
   * The number of structural features of the '<em>Class</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASS_FEATURE_COUNT = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 7;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.CollectionImpl <em>Collection</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.CollectionImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getCollection()
   * @generated
   */
	int COLLECTION = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_EXTENSIONS = CapellacorePackage.CLASSIFIER__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__ID = CapellacorePackage.CLASSIFIER__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__SID = CapellacorePackage.CLASSIFIER__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__CONSTRAINTS = CapellacorePackage.CLASSIFIER__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_CONSTRAINTS = CapellacorePackage.CLASSIFIER__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CLASSIFIER__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__NAME = CapellacorePackage.CLASSIFIER__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__ABSTRACT_TYPED_ELEMENTS = CapellacorePackage.CLASSIFIER__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__INCOMING_TRACES = CapellacorePackage.CLASSIFIER__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OUTGOING_TRACES = CapellacorePackage.CLASSIFIER__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__VISIBLE_IN_DOC = CapellacorePackage.CLASSIFIER__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__VISIBLE_IN_LM = CapellacorePackage.CLASSIFIER__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__SUMMARY = CapellacorePackage.CLASSIFIER__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__DESCRIPTION = CapellacorePackage.CLASSIFIER__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__REVIEW = CapellacorePackage.CLASSIFIER__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_PROPERTY_VALUES = CapellacorePackage.CLASSIFIER__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CLASSIFIER__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__APPLIED_PROPERTY_VALUES = CapellacorePackage.CLASSIFIER__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CLASSIFIER__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CLASSIFIER__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__STATUS = CapellacorePackage.CLASSIFIER__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__FEATURES = CapellacorePackage.CLASSIFIER__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_TRACES = CapellacorePackage.CLASSIFIER__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__CONTAINED_GENERIC_TRACES = CapellacorePackage.CLASSIFIER__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__NAMING_RULES = CapellacorePackage.CLASSIFIER__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__TYPED_ELEMENTS = CapellacorePackage.CLASSIFIER__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__ABSTRACT = CapellacorePackage.CLASSIFIER__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_GENERALIZATIONS = CapellacorePackage.CLASSIFIER__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__SUPER_GENERALIZATIONS = CapellacorePackage.CLASSIFIER__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__SUB_GENERALIZATIONS = CapellacorePackage.CLASSIFIER__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__SUPER = CapellacorePackage.CLASSIFIER__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__SUB = CapellacorePackage.CLASSIFIER__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_FEATURES = CapellacorePackage.CLASSIFIER__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__CONTAINED_PROPERTIES = CapellacorePackage.CLASSIFIER__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__ORDERED = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__UNIQUE = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__MIN_INCLUSIVE = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__MAX_INCLUSIVE = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_DEFAULT_VALUE = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_MIN_VALUE = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_MAX_VALUE = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_NULL_VALUE = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_MIN_CARD = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_MIN_LENGTH = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_MAX_CARD = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_MAX_LENGTH = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__OWNED_DATA_VALUES = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__FINAL = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Is Primitive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__IS_PRIMITIVE = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__VISIBILITY = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__KIND = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Aggregation Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__AGGREGATION_KIND = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__TYPE = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 19;

	/**
   * The feature id for the '<em><b>Index</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__INDEX = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 20;

	/**
   * The feature id for the '<em><b>Contained Operations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION__CONTAINED_OPERATIONS = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 21;

	/**
   * The number of structural features of the '<em>Collection</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_FEATURE_COUNT = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 22;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.AbstractCollectionValueImpl <em>Abstract Collection Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.AbstractCollectionValueImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getAbstractCollectionValue()
   * @generated
   */
	int ABSTRACT_COLLECTION_VALUE = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__OWNED_EXTENSIONS = DatavaluePackage.DATA_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__ID = DatavaluePackage.DATA_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__SID = DatavaluePackage.DATA_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__CONSTRAINTS = DatavaluePackage.DATA_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__OWNED_CONSTRAINTS = DatavaluePackage.DATA_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__OWNED_MIGRATED_ELEMENTS = DatavaluePackage.DATA_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__NAME = DatavaluePackage.DATA_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__INCOMING_TRACES = DatavaluePackage.DATA_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__OUTGOING_TRACES = DatavaluePackage.DATA_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__VISIBLE_IN_DOC = DatavaluePackage.DATA_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__VISIBLE_IN_LM = DatavaluePackage.DATA_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__SUMMARY = DatavaluePackage.DATA_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__DESCRIPTION = DatavaluePackage.DATA_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__REVIEW = DatavaluePackage.DATA_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__OWNED_PROPERTY_VALUES = DatavaluePackage.DATA_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = DatavaluePackage.DATA_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__APPLIED_PROPERTY_VALUES = DatavaluePackage.DATA_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__OWNED_PROPERTY_VALUE_GROUPS = DatavaluePackage.DATA_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = DatavaluePackage.DATA_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__STATUS = DatavaluePackage.DATA_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__FEATURES = DatavaluePackage.DATA_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__ABSTRACT_TYPE = DatavaluePackage.DATA_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__ABSTRACT = DatavaluePackage.DATA_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE__TYPE = DatavaluePackage.DATA_VALUE__TYPE;

	/**
   * The number of structural features of the '<em>Abstract Collection Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COLLECTION_VALUE_FEATURE_COUNT = DatavaluePackage.DATA_VALUE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.CollectionValueImpl <em>Collection Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.CollectionValueImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getCollectionValue()
   * @generated
   */
	int COLLECTION_VALUE = 6;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__OWNED_EXTENSIONS = ABSTRACT_COLLECTION_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__ID = ABSTRACT_COLLECTION_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__SID = ABSTRACT_COLLECTION_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__CONSTRAINTS = ABSTRACT_COLLECTION_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__OWNED_CONSTRAINTS = ABSTRACT_COLLECTION_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_COLLECTION_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__NAME = ABSTRACT_COLLECTION_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__INCOMING_TRACES = ABSTRACT_COLLECTION_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__OUTGOING_TRACES = ABSTRACT_COLLECTION_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__VISIBLE_IN_DOC = ABSTRACT_COLLECTION_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__VISIBLE_IN_LM = ABSTRACT_COLLECTION_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__SUMMARY = ABSTRACT_COLLECTION_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__DESCRIPTION = ABSTRACT_COLLECTION_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__REVIEW = ABSTRACT_COLLECTION_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__OWNED_PROPERTY_VALUES = ABSTRACT_COLLECTION_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_COLLECTION_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__APPLIED_PROPERTY_VALUES = ABSTRACT_COLLECTION_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_COLLECTION_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_COLLECTION_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__STATUS = ABSTRACT_COLLECTION_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__FEATURES = ABSTRACT_COLLECTION_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__ABSTRACT_TYPE = ABSTRACT_COLLECTION_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__ABSTRACT = ABSTRACT_COLLECTION_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__TYPE = ABSTRACT_COLLECTION_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Owned Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__OWNED_ELEMENTS = ABSTRACT_COLLECTION_VALUE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Default Element</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT = ABSTRACT_COLLECTION_VALUE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Collection Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_FEATURE_COUNT = ABSTRACT_COLLECTION_VALUE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.CollectionValueReferenceImpl <em>Collection Value Reference</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.CollectionValueReferenceImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getCollectionValueReference()
   * @generated
   */
	int COLLECTION_VALUE_REFERENCE = 7;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__OWNED_EXTENSIONS = ABSTRACT_COLLECTION_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__ID = ABSTRACT_COLLECTION_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__SID = ABSTRACT_COLLECTION_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__CONSTRAINTS = ABSTRACT_COLLECTION_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__OWNED_CONSTRAINTS = ABSTRACT_COLLECTION_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_COLLECTION_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__NAME = ABSTRACT_COLLECTION_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__INCOMING_TRACES = ABSTRACT_COLLECTION_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__OUTGOING_TRACES = ABSTRACT_COLLECTION_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__VISIBLE_IN_DOC = ABSTRACT_COLLECTION_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__VISIBLE_IN_LM = ABSTRACT_COLLECTION_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__SUMMARY = ABSTRACT_COLLECTION_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__DESCRIPTION = ABSTRACT_COLLECTION_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__REVIEW = ABSTRACT_COLLECTION_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__OWNED_PROPERTY_VALUES = ABSTRACT_COLLECTION_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_COLLECTION_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__APPLIED_PROPERTY_VALUES = ABSTRACT_COLLECTION_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_COLLECTION_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_COLLECTION_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__STATUS = ABSTRACT_COLLECTION_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__FEATURES = ABSTRACT_COLLECTION_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__ABSTRACT_TYPE = ABSTRACT_COLLECTION_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__ABSTRACT = ABSTRACT_COLLECTION_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__TYPE = ABSTRACT_COLLECTION_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Referenced Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__REFERENCED_VALUE = ABSTRACT_COLLECTION_VALUE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Referenced Property</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE__REFERENCED_PROPERTY = ABSTRACT_COLLECTION_VALUE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Collection Value Reference</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COLLECTION_VALUE_REFERENCE_FEATURE_COUNT = ABSTRACT_COLLECTION_VALUE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.DataPkgImpl <em>Data Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.DataPkgImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getDataPkg()
   * @generated
   */
	int DATA_PKG = 8;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_EXTENSIONS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__ID = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__SID = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__CONSTRAINTS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_CONSTRAINTS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__NAME = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__INCOMING_TRACES = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OUTGOING_TRACES = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__VISIBLE_IN_DOC = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__VISIBLE_IN_LM = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__SUMMARY = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__DESCRIPTION = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__REVIEW = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__STATUS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__FEATURES = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_TRACES = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__NAMING_RULES = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Exchange Items</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_EXCHANGE_ITEMS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__VISIBILITY = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Associations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_ASSOCIATIONS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_DATA_VALUES = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Message References</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_MESSAGE_REFERENCES = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Owned Data Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_DATA_PKGS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Owned Classes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_CLASSES = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Key Parts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_KEY_PARTS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Collections</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_COLLECTIONS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Owned Units</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_UNITS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Data Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_DATA_TYPES = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Owned Signals</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_SIGNALS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Owned Messages</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_MESSAGES = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Owned Exceptions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_EXCEPTIONS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Owned State Events</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG__OWNED_STATE_EVENTS = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 14;

	/**
   * The number of structural features of the '<em>Data Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_PKG_FEATURE_COUNT = CapellacorePackage.ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT + 15;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.DomainElementImpl <em>Domain Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.DomainElementImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getDomainElement()
   * @generated
   */
	int DOMAIN_ELEMENT = 9;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__OWNED_EXTENSIONS = CLASS__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__ID = CLASS__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__SID = CLASS__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__CONSTRAINTS = CLASS__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__OWNED_CONSTRAINTS = CLASS__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__OWNED_MIGRATED_ELEMENTS = CLASS__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__NAME = CLASS__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__ABSTRACT_TYPED_ELEMENTS = CLASS__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__INCOMING_TRACES = CLASS__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__OUTGOING_TRACES = CLASS__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__VISIBLE_IN_DOC = CLASS__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__VISIBLE_IN_LM = CLASS__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__SUMMARY = CLASS__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__DESCRIPTION = CLASS__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__REVIEW = CLASS__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__OWNED_PROPERTY_VALUES = CLASS__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CLASS__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__APPLIED_PROPERTY_VALUES = CLASS__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS = CLASS__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CLASS__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__STATUS = CLASS__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__FEATURES = CLASS__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__OWNED_TRACES = CLASS__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__CONTAINED_GENERIC_TRACES = CLASS__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__NAMING_RULES = CLASS__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__TYPED_ELEMENTS = CLASS__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__ABSTRACT = CLASS__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__OWNED_GENERALIZATIONS = CLASS__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__SUPER_GENERALIZATIONS = CLASS__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__SUB_GENERALIZATIONS = CLASS__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__SUPER = CLASS__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__SUB = CLASS__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__OWNED_FEATURES = CLASS__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__CONTAINED_PROPERTIES = CLASS__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__FINAL = CLASS__FINAL;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__VISIBILITY = CLASS__VISIBILITY;

	/**
   * The feature id for the '<em><b>Contained Operations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__CONTAINED_OPERATIONS = CLASS__CONTAINED_OPERATIONS;

	/**
   * The feature id for the '<em><b>Nested General Classes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__NESTED_GENERAL_CLASSES = CLASS__NESTED_GENERAL_CLASSES;

	/**
   * The feature id for the '<em><b>Is Primitive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__IS_PRIMITIVE = CLASS__IS_PRIMITIVE;

	/**
   * The feature id for the '<em><b>Key Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__KEY_PARTS = CLASS__KEY_PARTS;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__OWNED_STATE_MACHINES = CLASS__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__OWNED_DATA_VALUES = CLASS__OWNED_DATA_VALUES;

	/**
   * The feature id for the '<em><b>Owned Information Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__OWNED_INFORMATION_REALIZATIONS = CLASS__OWNED_INFORMATION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Classes</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__REALIZED_CLASSES = CLASS__REALIZED_CLASSES;

	/**
   * The feature id for the '<em><b>Realizing Classes</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT__REALIZING_CLASSES = CLASS__REALIZING_CLASSES;

	/**
   * The number of structural features of the '<em>Domain Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DOMAIN_ELEMENT_FEATURE_COUNT = CLASS_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.KeyPartImpl <em>Key Part</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.KeyPartImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getKeyPart()
   * @generated
   */
	int KEY_PART = 10;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__OWNED_EXTENSIONS = CapellacorePackage.RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__ID = CapellacorePackage.RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__SID = CapellacorePackage.RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__CONSTRAINTS = CapellacorePackage.RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__OWNED_CONSTRAINTS = CapellacorePackage.RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__REALIZED_FLOW = CapellacorePackage.RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__INCOMING_TRACES = CapellacorePackage.RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__OUTGOING_TRACES = CapellacorePackage.RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__VISIBLE_IN_DOC = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__VISIBLE_IN_LM = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__SUMMARY = CapellacorePackage.RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__DESCRIPTION = CapellacorePackage.RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__REVIEW = CapellacorePackage.RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__OWNED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__APPLIED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__STATUS = CapellacorePackage.RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__FEATURES = CapellacorePackage.RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Property</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART__PROPERTY = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Key Part</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_PART_FEATURE_COUNT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.MultiplicityElementImpl <em>Multiplicity Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.MultiplicityElementImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getMultiplicityElement()
   * @generated
   */
	int MULTIPLICITY_ELEMENT = 11;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__ORDERED = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__UNIQUE = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__MIN_INCLUSIVE = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__MAX_INCLUSIVE = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__OWNED_MIN_CARD = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__OWNED_MAX_CARD = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 11;

	/**
   * The number of structural features of the '<em>Multiplicity Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MULTIPLICITY_ELEMENT_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 12;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.OperationImpl <em>Operation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.OperationImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getOperation()
   * @generated
   */
	int OPERATION = 12;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__OWNED_EXTENSIONS = CapellacorePackage.FEATURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__ID = CapellacorePackage.FEATURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__SID = CapellacorePackage.FEATURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__CONSTRAINTS = CapellacorePackage.FEATURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__OWNED_CONSTRAINTS = CapellacorePackage.FEATURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.FEATURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__NAME = CapellacorePackage.FEATURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__INCOMING_TRACES = CapellacorePackage.FEATURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__OUTGOING_TRACES = CapellacorePackage.FEATURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__VISIBLE_IN_DOC = CapellacorePackage.FEATURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__VISIBLE_IN_LM = CapellacorePackage.FEATURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__SUMMARY = CapellacorePackage.FEATURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__DESCRIPTION = CapellacorePackage.FEATURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__REVIEW = CapellacorePackage.FEATURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__OWNED_PROPERTY_VALUES = CapellacorePackage.FEATURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.FEATURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.FEATURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.FEATURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.FEATURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__STATUS = CapellacorePackage.FEATURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__FEATURES = CapellacorePackage.FEATURE__FEATURES;

	/**
   * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__IS_ABSTRACT = CapellacorePackage.FEATURE__IS_ABSTRACT;

	/**
   * The feature id for the '<em><b>Is Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__IS_STATIC = CapellacorePackage.FEATURE__IS_STATIC;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__VISIBILITY = CapellacorePackage.FEATURE__VISIBILITY;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__ABSTRACT_TYPED_ELEMENTS = CapellacorePackage.FEATURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Invoking Sequence Messages</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__INVOKING_SEQUENCE_MESSAGES = CapellacorePackage.FEATURE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__OWNED_PARAMETERS = CapellacorePackage.FEATURE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Allocating Operations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__ALLOCATING_OPERATIONS = CapellacorePackage.FEATURE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Allocated Operations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__ALLOCATED_OPERATIONS = CapellacorePackage.FEATURE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Owned Operation Allocation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__OWNED_OPERATION_ALLOCATION = CapellacorePackage.FEATURE_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Owned Exchange Item Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__OWNED_EXCHANGE_ITEM_REALIZATIONS = CapellacorePackage.FEATURE_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Realized Exchange Items</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION__REALIZED_EXCHANGE_ITEMS = CapellacorePackage.FEATURE_FEATURE_COUNT + 7;

	/**
   * The number of structural features of the '<em>Operation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_FEATURE_COUNT = CapellacorePackage.FEATURE_FEATURE_COUNT + 8;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.OperationAllocationImpl <em>Operation Allocation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.OperationAllocationImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getOperationAllocation()
   * @generated
   */
	int OPERATION_ALLOCATION = 13;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Allocated Operation</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__ALLOCATED_OPERATION = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Allocating Operation</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION__ALLOCATING_OPERATION = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Operation Allocation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATION_ALLOCATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.ParameterImpl <em>Parameter</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.ParameterImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getParameter()
   * @generated
   */
	int PARAMETER = 14;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__OWNED_EXTENSIONS = CapellacorePackage.TYPED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__ID = CapellacorePackage.TYPED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__SID = CapellacorePackage.TYPED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__CONSTRAINTS = CapellacorePackage.TYPED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__OWNED_CONSTRAINTS = CapellacorePackage.TYPED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.TYPED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__NAME = CapellacorePackage.TYPED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__ABSTRACT_TYPE = CapellacorePackage.TYPED_ELEMENT__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__INCOMING_TRACES = CapellacorePackage.TYPED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__OUTGOING_TRACES = CapellacorePackage.TYPED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__VISIBLE_IN_DOC = CapellacorePackage.TYPED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__VISIBLE_IN_LM = CapellacorePackage.TYPED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__SUMMARY = CapellacorePackage.TYPED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__DESCRIPTION = CapellacorePackage.TYPED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__REVIEW = CapellacorePackage.TYPED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__OWNED_PROPERTY_VALUES = CapellacorePackage.TYPED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.TYPED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__APPLIED_PROPERTY_VALUES = CapellacorePackage.TYPED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.TYPED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.TYPED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__STATUS = CapellacorePackage.TYPED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__FEATURES = CapellacorePackage.TYPED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__TYPE = CapellacorePackage.TYPED_ELEMENT__TYPE;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__ORDERED = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__UNIQUE = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__MIN_INCLUSIVE = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__MAX_INCLUSIVE = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__OWNED_DEFAULT_VALUE = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__OWNED_MIN_VALUE = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__OWNED_MAX_VALUE = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__OWNED_NULL_VALUE = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__OWNED_MIN_CARD = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__OWNED_MIN_LENGTH = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__OWNED_MAX_CARD = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__OWNED_MAX_LENGTH = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Is Exception</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__IS_EXCEPTION = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Is Stream</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__IS_STREAM = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Is Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__IS_OPTIONAL = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Kind Of Rate</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__KIND_OF_RATE = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Effect</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__EFFECT = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Rate</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__RATE = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Probability</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__PROBABILITY = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Parameter Set</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__PARAMETER_SET = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 19;

	/**
   * The feature id for the '<em><b>Direction</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__DIRECTION = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 20;

	/**
   * The feature id for the '<em><b>Passing Mode</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER__PASSING_MODE = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 21;

	/**
   * The number of structural features of the '<em>Parameter</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PARAMETER_FEATURE_COUNT = CapellacorePackage.TYPED_ELEMENT_FEATURE_COUNT + 22;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.ServiceImpl <em>Service</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.ServiceImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getService()
   * @generated
   */
	int SERVICE = 16;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__OWNED_EXTENSIONS = OPERATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__ID = OPERATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__SID = OPERATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__CONSTRAINTS = OPERATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__OWNED_CONSTRAINTS = OPERATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__OWNED_MIGRATED_ELEMENTS = OPERATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__NAME = OPERATION__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__INCOMING_TRACES = OPERATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__OUTGOING_TRACES = OPERATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__VISIBLE_IN_DOC = OPERATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__VISIBLE_IN_LM = OPERATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__SUMMARY = OPERATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__DESCRIPTION = OPERATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__REVIEW = OPERATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__OWNED_PROPERTY_VALUES = OPERATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__OWNED_ENUMERATION_PROPERTY_TYPES = OPERATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__APPLIED_PROPERTY_VALUES = OPERATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__OWNED_PROPERTY_VALUE_GROUPS = OPERATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__APPLIED_PROPERTY_VALUE_GROUPS = OPERATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__STATUS = OPERATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__FEATURES = OPERATION__FEATURES;

	/**
   * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__IS_ABSTRACT = OPERATION__IS_ABSTRACT;

	/**
   * The feature id for the '<em><b>Is Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__IS_STATIC = OPERATION__IS_STATIC;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__VISIBILITY = OPERATION__VISIBILITY;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__ABSTRACT_TYPED_ELEMENTS = OPERATION__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Invoking Sequence Messages</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__INVOKING_SEQUENCE_MESSAGES = OPERATION__INVOKING_SEQUENCE_MESSAGES;

	/**
   * The feature id for the '<em><b>Owned Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__OWNED_PARAMETERS = OPERATION__OWNED_PARAMETERS;

	/**
   * The feature id for the '<em><b>Allocating Operations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__ALLOCATING_OPERATIONS = OPERATION__ALLOCATING_OPERATIONS;

	/**
   * The feature id for the '<em><b>Allocated Operations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__ALLOCATED_OPERATIONS = OPERATION__ALLOCATED_OPERATIONS;

	/**
   * The feature id for the '<em><b>Owned Operation Allocation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__OWNED_OPERATION_ALLOCATION = OPERATION__OWNED_OPERATION_ALLOCATION;

	/**
   * The feature id for the '<em><b>Owned Exchange Item Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__OWNED_EXCHANGE_ITEM_REALIZATIONS = OPERATION__OWNED_EXCHANGE_ITEM_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Exchange Items</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__REALIZED_EXCHANGE_ITEMS = OPERATION__REALIZED_EXCHANGE_ITEMS;

	/**
   * The feature id for the '<em><b>Synchronism Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__SYNCHRONISM_KIND = OPERATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Thrown Exceptions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__THROWN_EXCEPTIONS = OPERATION_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Messages</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__MESSAGES = OPERATION_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Message References</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE__MESSAGE_REFERENCES = OPERATION_FEATURE_COUNT + 3;

	/**
   * The number of structural features of the '<em>Service</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SERVICE_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 4;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.UnionImpl <em>Union</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.UnionImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getUnion()
   * @generated
   */
	int UNION = 17;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__OWNED_EXTENSIONS = CLASS__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__ID = CLASS__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__SID = CLASS__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__CONSTRAINTS = CLASS__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__OWNED_CONSTRAINTS = CLASS__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__OWNED_MIGRATED_ELEMENTS = CLASS__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__NAME = CLASS__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__ABSTRACT_TYPED_ELEMENTS = CLASS__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__INCOMING_TRACES = CLASS__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__OUTGOING_TRACES = CLASS__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__VISIBLE_IN_DOC = CLASS__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__VISIBLE_IN_LM = CLASS__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__SUMMARY = CLASS__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__DESCRIPTION = CLASS__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__REVIEW = CLASS__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__OWNED_PROPERTY_VALUES = CLASS__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__OWNED_ENUMERATION_PROPERTY_TYPES = CLASS__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__APPLIED_PROPERTY_VALUES = CLASS__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__OWNED_PROPERTY_VALUE_GROUPS = CLASS__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__APPLIED_PROPERTY_VALUE_GROUPS = CLASS__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__STATUS = CLASS__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__FEATURES = CLASS__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__OWNED_TRACES = CLASS__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__CONTAINED_GENERIC_TRACES = CLASS__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__NAMING_RULES = CLASS__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__TYPED_ELEMENTS = CLASS__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__ABSTRACT = CLASS__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__OWNED_GENERALIZATIONS = CLASS__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__SUPER_GENERALIZATIONS = CLASS__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__SUB_GENERALIZATIONS = CLASS__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__SUPER = CLASS__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__SUB = CLASS__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__OWNED_FEATURES = CLASS__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__CONTAINED_PROPERTIES = CLASS__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__FINAL = CLASS__FINAL;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__VISIBILITY = CLASS__VISIBILITY;

	/**
   * The feature id for the '<em><b>Contained Operations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__CONTAINED_OPERATIONS = CLASS__CONTAINED_OPERATIONS;

	/**
   * The feature id for the '<em><b>Nested General Classes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__NESTED_GENERAL_CLASSES = CLASS__NESTED_GENERAL_CLASSES;

	/**
   * The feature id for the '<em><b>Is Primitive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__IS_PRIMITIVE = CLASS__IS_PRIMITIVE;

	/**
   * The feature id for the '<em><b>Key Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__KEY_PARTS = CLASS__KEY_PARTS;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__OWNED_STATE_MACHINES = CLASS__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__OWNED_DATA_VALUES = CLASS__OWNED_DATA_VALUES;

	/**
   * The feature id for the '<em><b>Owned Information Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__OWNED_INFORMATION_REALIZATIONS = CLASS__OWNED_INFORMATION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Classes</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__REALIZED_CLASSES = CLASS__REALIZED_CLASSES;

	/**
   * The feature id for the '<em><b>Realizing Classes</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__REALIZING_CLASSES = CLASS__REALIZING_CLASSES;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__KIND = CLASS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Discriminant</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__DISCRIMINANT = CLASS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Default Property</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__DEFAULT_PROPERTY = CLASS_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Contained Union Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION__CONTAINED_UNION_PROPERTIES = CLASS_FEATURE_COUNT + 3;

	/**
   * The number of structural features of the '<em>Union</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_FEATURE_COUNT = CLASS_FEATURE_COUNT + 4;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.UnionPropertyImpl <em>Union Property</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.UnionPropertyImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getUnionProperty()
   * @generated
   */
	int UNION_PROPERTY = 18;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__OWNED_EXTENSIONS = PROPERTY__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__ID = PROPERTY__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__SID = PROPERTY__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__CONSTRAINTS = PROPERTY__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__OWNED_CONSTRAINTS = PROPERTY__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__OWNED_MIGRATED_ELEMENTS = PROPERTY__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__NAME = PROPERTY__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__INCOMING_TRACES = PROPERTY__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__OUTGOING_TRACES = PROPERTY__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__VISIBLE_IN_DOC = PROPERTY__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__VISIBLE_IN_LM = PROPERTY__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__SUMMARY = PROPERTY__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__DESCRIPTION = PROPERTY__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__REVIEW = PROPERTY__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__OWNED_PROPERTY_VALUES = PROPERTY__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__OWNED_ENUMERATION_PROPERTY_TYPES = PROPERTY__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__APPLIED_PROPERTY_VALUES = PROPERTY__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__OWNED_PROPERTY_VALUE_GROUPS = PROPERTY__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__APPLIED_PROPERTY_VALUE_GROUPS = PROPERTY__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__STATUS = PROPERTY__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__FEATURES = PROPERTY__FEATURES;

	/**
   * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__IS_ABSTRACT = PROPERTY__IS_ABSTRACT;

	/**
   * The feature id for the '<em><b>Is Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__IS_STATIC = PROPERTY__IS_STATIC;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__VISIBILITY = PROPERTY__VISIBILITY;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__ABSTRACT_TYPE = PROPERTY__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__TYPE = PROPERTY__TYPE;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__ORDERED = PROPERTY__ORDERED;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__UNIQUE = PROPERTY__UNIQUE;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__MIN_INCLUSIVE = PROPERTY__MIN_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__MAX_INCLUSIVE = PROPERTY__MAX_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__OWNED_DEFAULT_VALUE = PROPERTY__OWNED_DEFAULT_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__OWNED_MIN_VALUE = PROPERTY__OWNED_MIN_VALUE;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__OWNED_MAX_VALUE = PROPERTY__OWNED_MAX_VALUE;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__OWNED_NULL_VALUE = PROPERTY__OWNED_NULL_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__OWNED_MIN_CARD = PROPERTY__OWNED_MIN_CARD;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__OWNED_MIN_LENGTH = PROPERTY__OWNED_MIN_LENGTH;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__OWNED_MAX_CARD = PROPERTY__OWNED_MAX_CARD;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__OWNED_MAX_LENGTH = PROPERTY__OWNED_MAX_LENGTH;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__FINAL = PROPERTY__FINAL;

	/**
   * The feature id for the '<em><b>Aggregation Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__AGGREGATION_KIND = PROPERTY__AGGREGATION_KIND;

	/**
   * The feature id for the '<em><b>Is Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__IS_DERIVED = PROPERTY__IS_DERIVED;

	/**
   * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__IS_READ_ONLY = PROPERTY__IS_READ_ONLY;

	/**
   * The feature id for the '<em><b>Is Part Of Key</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__IS_PART_OF_KEY = PROPERTY__IS_PART_OF_KEY;

	/**
   * The feature id for the '<em><b>Association</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__ASSOCIATION = PROPERTY__ASSOCIATION;

	/**
   * The feature id for the '<em><b>Qualifier</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY__QUALIFIER = PROPERTY_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Union Property</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNION_PROPERTY_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.UnitImpl <em>Unit</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.UnitImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getUnit()
   * @generated
   */
	int UNIT = 19;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The number of structural features of the '<em>Unit</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNIT_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.PortImpl <em>Port</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.PortImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getPort()
   * @generated
   */
	int PORT = 20;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Incoming Port Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__INCOMING_PORT_REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Outgoing Port Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__OUTGOING_PORT_REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Protocols</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__OWNED_PROTOCOLS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Incoming Port Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__INCOMING_PORT_ALLOCATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Outgoing Port Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__OUTGOING_PORT_ALLOCATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__PROVIDED_INTERFACES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__REQUIRED_INTERFACES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Port Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__OWNED_PORT_REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Port Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT__OWNED_PORT_ALLOCATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The number of structural features of the '<em>Port</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.PortRealizationImpl <em>Port Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.PortRealizationImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getPortRealization()
   * @generated
   */
	int PORT_REALIZATION = 21;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Realized Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__REALIZED_PORT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Realizing Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION__REALIZING_PORT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Port Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.PortAllocationImpl <em>Port Allocation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.PortAllocationImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getPortAllocation()
   * @generated
   */
	int PORT_ALLOCATION = 22;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Allocated Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__ALLOCATED_PORT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Allocating Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION__ALLOCATING_PORT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Port Allocation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PORT_ALLOCATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl <em>Exchange Item</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.ExchangeItemImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getExchangeItem()
   * @generated
   */
	int EXCHANGE_ITEM = 23;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__OWNED_EXTENSIONS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__ID = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__SID = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__CONSTRAINTS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__OWNED_CONSTRAINTS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__OWNED_MIGRATED_ELEMENTS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__NAME = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__ABSTRACT_TYPED_ELEMENTS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__FINAL = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__INCOMING_TRACES = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__OUTGOING_TRACES = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__VISIBLE_IN_DOC = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__VISIBLE_IN_LM = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__SUMMARY = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__DESCRIPTION = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__REVIEW = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__OWNED_PROPERTY_VALUES = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__APPLIED_PROPERTY_VALUES = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__OWNED_PROPERTY_VALUE_GROUPS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__APPLIED_PROPERTY_VALUE_GROUPS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__STATUS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__FEATURES = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__OWNED_TRACES = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__CONTAINED_GENERIC_TRACES = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__NAMING_RULES = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__TYPED_ELEMENTS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__ABSTRACT = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 19;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__OWNED_GENERALIZATIONS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 20;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__SUPER_GENERALIZATIONS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 21;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__SUB_GENERALIZATIONS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 22;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__SUPER = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 23;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__SUB = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 24;

	/**
   * The feature id for the '<em><b>Exchange Mechanism</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__EXCHANGE_MECHANISM = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 25;

	/**
   * The feature id for the '<em><b>Owned Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__OWNED_ELEMENTS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 26;

	/**
   * The feature id for the '<em><b>Owned Information Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 27;

	/**
   * The feature id for the '<em><b>Owned Exchange Item Instances</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__OWNED_EXCHANGE_ITEM_INSTANCES = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 28;

	/**
   * The feature id for the '<em><b>Allocator Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__ALLOCATOR_INTERFACES = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 29;

	/**
   * The feature id for the '<em><b>Realized Exchange Items</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__REALIZED_EXCHANGE_ITEMS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 30;

	/**
   * The feature id for the '<em><b>Realizing Exchange Items</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__REALIZING_EXCHANGE_ITEMS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 31;

	/**
   * The feature id for the '<em><b>Realizing Operations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM__REALIZING_OPERATIONS = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 32;

	/**
   * The number of structural features of the '<em>Exchange Item</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_FEATURE_COUNT = ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM_FEATURE_COUNT + 33;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl <em>Exchange Item Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getExchangeItemElement()
   * @generated
   */
	int EXCHANGE_ITEM_ELEMENT = 24;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__ORDERED = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__UNIQUE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__MIN_INCLUSIVE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__MAX_INCLUSIVE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__OWNED_DEFAULT_VALUE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__OWNED_MIN_VALUE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__OWNED_MAX_VALUE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__OWNED_NULL_VALUE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__OWNED_MIN_CARD = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__OWNED_MIN_LENGTH = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__OWNED_MAX_CARD = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__OWNED_MAX_LENGTH = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__ABSTRACT_TYPE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__TYPE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__KIND = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Direction</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__DIRECTION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Composite</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__COMPOSITE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Referenced Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT__REFERENCED_PROPERTIES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 17;

	/**
   * The number of structural features of the '<em>Exchange Item Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ELEMENT_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 18;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.ExchangeItemInstanceImpl <em>Exchange Item Instance</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.ExchangeItemInstanceImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getExchangeItemInstance()
   * @generated
   */
	int EXCHANGE_ITEM_INSTANCE = 25;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__OWNED_EXTENSIONS = ABSTRACT_INSTANCE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__ID = ABSTRACT_INSTANCE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__SID = ABSTRACT_INSTANCE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__CONSTRAINTS = ABSTRACT_INSTANCE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__OWNED_CONSTRAINTS = ABSTRACT_INSTANCE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_INSTANCE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__NAME = ABSTRACT_INSTANCE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__INCOMING_TRACES = ABSTRACT_INSTANCE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__OUTGOING_TRACES = ABSTRACT_INSTANCE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__VISIBLE_IN_DOC = ABSTRACT_INSTANCE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__VISIBLE_IN_LM = ABSTRACT_INSTANCE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__SUMMARY = ABSTRACT_INSTANCE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__DESCRIPTION = ABSTRACT_INSTANCE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__REVIEW = ABSTRACT_INSTANCE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__OWNED_PROPERTY_VALUES = ABSTRACT_INSTANCE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_INSTANCE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__APPLIED_PROPERTY_VALUES = ABSTRACT_INSTANCE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_INSTANCE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_INSTANCE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__STATUS = ABSTRACT_INSTANCE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__FEATURES = ABSTRACT_INSTANCE__FEATURES;

	/**
   * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__IS_ABSTRACT = ABSTRACT_INSTANCE__IS_ABSTRACT;

	/**
   * The feature id for the '<em><b>Is Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__IS_STATIC = ABSTRACT_INSTANCE__IS_STATIC;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__VISIBILITY = ABSTRACT_INSTANCE__VISIBILITY;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__ABSTRACT_TYPE = ABSTRACT_INSTANCE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__TYPE = ABSTRACT_INSTANCE__TYPE;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__ORDERED = ABSTRACT_INSTANCE__ORDERED;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__UNIQUE = ABSTRACT_INSTANCE__UNIQUE;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__MIN_INCLUSIVE = ABSTRACT_INSTANCE__MIN_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__MAX_INCLUSIVE = ABSTRACT_INSTANCE__MAX_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__OWNED_DEFAULT_VALUE = ABSTRACT_INSTANCE__OWNED_DEFAULT_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__OWNED_MIN_VALUE = ABSTRACT_INSTANCE__OWNED_MIN_VALUE;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__OWNED_MAX_VALUE = ABSTRACT_INSTANCE__OWNED_MAX_VALUE;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__OWNED_NULL_VALUE = ABSTRACT_INSTANCE__OWNED_NULL_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__OWNED_MIN_CARD = ABSTRACT_INSTANCE__OWNED_MIN_CARD;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__OWNED_MIN_LENGTH = ABSTRACT_INSTANCE__OWNED_MIN_LENGTH;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__OWNED_MAX_CARD = ABSTRACT_INSTANCE__OWNED_MAX_CARD;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__OWNED_MAX_LENGTH = ABSTRACT_INSTANCE__OWNED_MAX_LENGTH;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__FINAL = ABSTRACT_INSTANCE__FINAL;

	/**
   * The feature id for the '<em><b>Aggregation Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__AGGREGATION_KIND = ABSTRACT_INSTANCE__AGGREGATION_KIND;

	/**
   * The feature id for the '<em><b>Is Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__IS_DERIVED = ABSTRACT_INSTANCE__IS_DERIVED;

	/**
   * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__IS_READ_ONLY = ABSTRACT_INSTANCE__IS_READ_ONLY;

	/**
   * The feature id for the '<em><b>Is Part Of Key</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__IS_PART_OF_KEY = ABSTRACT_INSTANCE__IS_PART_OF_KEY;

	/**
   * The feature id for the '<em><b>Association</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__ASSOCIATION = ABSTRACT_INSTANCE__ASSOCIATION;

	/**
   * The feature id for the '<em><b>Representing Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE__REPRESENTING_INSTANCE_ROLES = ABSTRACT_INSTANCE__REPRESENTING_INSTANCE_ROLES;

	/**
   * The number of structural features of the '<em>Exchange Item Instance</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_INSTANCE_FEATURE_COUNT = ABSTRACT_INSTANCE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.InformationRealizationImpl <em>Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.InformationRealizationImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getInformationRealization()
   * @generated
   */
	int INFORMATION_REALIZATION = 26;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The number of structural features of the '<em>Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INFORMATION_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.ExchangeItemRealizationImpl <em>Exchange Item Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.ExchangeItemRealizationImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getExchangeItemRealization()
   * @generated
   */
	int EXCHANGE_ITEM_REALIZATION = 27;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Realized Item</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__REALIZED_ITEM = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Realizing Operation</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION__REALIZING_OPERATION = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Exchange Item Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.impl.AbstractEventOperationImpl <em>Abstract Event Operation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.impl.AbstractEventOperationImpl
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getAbstractEventOperation()
   * @generated
   */
	int ABSTRACT_EVENT_OPERATION = 28;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Invoking Sequence Messages</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION__INVOKING_SEQUENCE_MESSAGES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Event Operation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EVENT_OPERATION_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.AggregationKind <em>Aggregation Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.AggregationKind
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getAggregationKind()
   * @generated
   */
	int AGGREGATION_KIND = 29;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.ParameterDirection <em>Parameter Direction</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.ParameterDirection
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getParameterDirection()
   * @generated
   */
	int PARAMETER_DIRECTION = 30;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.PassingMode <em>Passing Mode</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.PassingMode
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getPassingMode()
   * @generated
   */
	int PASSING_MODE = 31;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.SynchronismKind <em>Synchronism Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.SynchronismKind
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getSynchronismKind()
   * @generated
   */
	int SYNCHRONISM_KIND = 32;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.UnionKind <em>Union Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.UnionKind
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getUnionKind()
   * @generated
   */
	int UNION_KIND = 33;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.ExchangeMechanism <em>Exchange Mechanism</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.ExchangeMechanism
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getExchangeMechanism()
   * @generated
   */
	int EXCHANGE_MECHANISM = 34;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.ElementKind <em>Element Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.ElementKind
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getElementKind()
   * @generated
   */
	int ELEMENT_KIND = 35;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.CollectionKind <em>Collection Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.CollectionKind
   * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getCollectionKind()
   * @generated
   */
	int COLLECTION_KIND = 36;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.AbstractInstance <em>Abstract Instance</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Instance</em>'.
   * @see org.polarsys.capella.core.data.information.AbstractInstance
   * @generated
   */
	EClass getAbstractInstance();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.AbstractInstance#getRepresentingInstanceRoles <em>Representing Instance Roles</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Representing Instance Roles</em>'.
   * @see org.polarsys.capella.core.data.information.AbstractInstance#getRepresentingInstanceRoles()
   * @see #getAbstractInstance()
   * @generated
   */
	EReference getAbstractInstance_RepresentingInstanceRoles();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.AssociationPkg <em>Association Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Association Pkg</em>'.
   * @see org.polarsys.capella.core.data.information.AssociationPkg
   * @generated
   */
	EClass getAssociationPkg();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.AssociationPkg#getVisibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visibility</em>'.
   * @see org.polarsys.capella.core.data.information.AssociationPkg#getVisibility()
   * @see #getAssociationPkg()
   * @generated
   */
	EAttribute getAssociationPkg_Visibility();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.AssociationPkg#getOwnedAssociations <em>Owned Associations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Associations</em>'.
   * @see org.polarsys.capella.core.data.information.AssociationPkg#getOwnedAssociations()
   * @see #getAssociationPkg()
   * @generated
   */
	EReference getAssociationPkg_OwnedAssociations();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.Association <em>Association</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Association</em>'.
   * @see org.polarsys.capella.core.data.information.Association
   * @generated
   */
	EClass getAssociation();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.Association#getOwnedMembers <em>Owned Members</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Members</em>'.
   * @see org.polarsys.capella.core.data.information.Association#getOwnedMembers()
   * @see #getAssociation()
   * @generated
   */
	EReference getAssociation_OwnedMembers();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Association#getNavigableMembers <em>Navigable Members</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Navigable Members</em>'.
   * @see org.polarsys.capella.core.data.information.Association#getNavigableMembers()
   * @see #getAssociation()
   * @generated
   */
	EReference getAssociation_NavigableMembers();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.Class <em>Class</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Class</em>'.
   * @see org.polarsys.capella.core.data.information.Class
   * @generated
   */
	EClass getClass_();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.Class#isIsPrimitive <em>Is Primitive</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Primitive</em>'.
   * @see org.polarsys.capella.core.data.information.Class#isIsPrimitive()
   * @see #getClass_()
   * @generated
   */
	EAttribute getClass_IsPrimitive();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Class#getKeyParts <em>Key Parts</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Key Parts</em>'.
   * @see org.polarsys.capella.core.data.information.Class#getKeyParts()
   * @see #getClass_()
   * @generated
   */
	EReference getClass_KeyParts();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.Class#getOwnedStateMachines <em>Owned State Machines</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned State Machines</em>'.
   * @see org.polarsys.capella.core.data.information.Class#getOwnedStateMachines()
   * @see #getClass_()
   * @generated
   */
	EReference getClass_OwnedStateMachines();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.Class#getOwnedDataValues <em>Owned Data Values</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Data Values</em>'.
   * @see org.polarsys.capella.core.data.information.Class#getOwnedDataValues()
   * @see #getClass_()
   * @generated
   */
	EReference getClass_OwnedDataValues();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.Class#getOwnedInformationRealizations <em>Owned Information Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Information Realizations</em>'.
   * @see org.polarsys.capella.core.data.information.Class#getOwnedInformationRealizations()
   * @see #getClass_()
   * @generated
   */
	EReference getClass_OwnedInformationRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Class#getRealizedClasses <em>Realized Classes</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Classes</em>'.
   * @see org.polarsys.capella.core.data.information.Class#getRealizedClasses()
   * @see #getClass_()
   * @generated
   */
	EReference getClass_RealizedClasses();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Class#getRealizingClasses <em>Realizing Classes</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Classes</em>'.
   * @see org.polarsys.capella.core.data.information.Class#getRealizingClasses()
   * @see #getClass_()
   * @generated
   */
	EReference getClass_RealizingClasses();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.Collection <em>Collection</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Collection</em>'.
   * @see org.polarsys.capella.core.data.information.Collection
   * @generated
   */
	EClass getCollection();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.Collection#isIsPrimitive <em>Is Primitive</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Primitive</em>'.
   * @see org.polarsys.capella.core.data.information.Collection#isIsPrimitive()
   * @see #getCollection()
   * @generated
   */
	EAttribute getCollection_IsPrimitive();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.Collection#getVisibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visibility</em>'.
   * @see org.polarsys.capella.core.data.information.Collection#getVisibility()
   * @see #getCollection()
   * @generated
   */
	EAttribute getCollection_Visibility();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.Collection#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.information.Collection#getKind()
   * @see #getCollection()
   * @generated
   */
	EAttribute getCollection_Kind();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.Collection#getAggregationKind <em>Aggregation Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Aggregation Kind</em>'.
   * @see org.polarsys.capella.core.data.information.Collection#getAggregationKind()
   * @see #getCollection()
   * @generated
   */
	EAttribute getCollection_AggregationKind();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.Collection#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.polarsys.capella.core.data.information.Collection#getType()
   * @see #getCollection()
   * @generated
   */
	EReference getCollection_Type();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Collection#getIndex <em>Index</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Index</em>'.
   * @see org.polarsys.capella.core.data.information.Collection#getIndex()
   * @see #getCollection()
   * @generated
   */
	EReference getCollection_Index();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Collection#getContainedOperations <em>Contained Operations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Operations</em>'.
   * @see org.polarsys.capella.core.data.information.Collection#getContainedOperations()
   * @see #getCollection()
   * @generated
   */
	EReference getCollection_ContainedOperations();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.AbstractCollectionValue <em>Abstract Collection Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Collection Value</em>'.
   * @see org.polarsys.capella.core.data.information.AbstractCollectionValue
   * @generated
   */
	EClass getAbstractCollectionValue();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.CollectionValue <em>Collection Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Collection Value</em>'.
   * @see org.polarsys.capella.core.data.information.CollectionValue
   * @generated
   */
	EClass getCollectionValue();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.CollectionValue#getOwnedElements <em>Owned Elements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Elements</em>'.
   * @see org.polarsys.capella.core.data.information.CollectionValue#getOwnedElements()
   * @see #getCollectionValue()
   * @generated
   */
	EReference getCollectionValue_OwnedElements();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.CollectionValue#getOwnedDefaultElement <em>Owned Default Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Default Element</em>'.
   * @see org.polarsys.capella.core.data.information.CollectionValue#getOwnedDefaultElement()
   * @see #getCollectionValue()
   * @generated
   */
	EReference getCollectionValue_OwnedDefaultElement();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.CollectionValueReference <em>Collection Value Reference</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Collection Value Reference</em>'.
   * @see org.polarsys.capella.core.data.information.CollectionValueReference
   * @generated
   */
	EClass getCollectionValueReference();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.CollectionValueReference#getReferencedValue <em>Referenced Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Value</em>'.
   * @see org.polarsys.capella.core.data.information.CollectionValueReference#getReferencedValue()
   * @see #getCollectionValueReference()
   * @generated
   */
	EReference getCollectionValueReference_ReferencedValue();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.CollectionValueReference#getReferencedProperty <em>Referenced Property</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Property</em>'.
   * @see org.polarsys.capella.core.data.information.CollectionValueReference#getReferencedProperty()
   * @see #getCollectionValueReference()
   * @generated
   */
	EReference getCollectionValueReference_ReferencedProperty();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.DataPkg <em>Data Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Pkg</em>'.
   * @see org.polarsys.capella.core.data.information.DataPkg
   * @generated
   */
	EClass getDataPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedDataPkgs <em>Owned Data Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Data Pkgs</em>'.
   * @see org.polarsys.capella.core.data.information.DataPkg#getOwnedDataPkgs()
   * @see #getDataPkg()
   * @generated
   */
	EReference getDataPkg_OwnedDataPkgs();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedClasses <em>Owned Classes</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Classes</em>'.
   * @see org.polarsys.capella.core.data.information.DataPkg#getOwnedClasses()
   * @see #getDataPkg()
   * @generated
   */
	EReference getDataPkg_OwnedClasses();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedKeyParts <em>Owned Key Parts</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Key Parts</em>'.
   * @see org.polarsys.capella.core.data.information.DataPkg#getOwnedKeyParts()
   * @see #getDataPkg()
   * @generated
   */
	EReference getDataPkg_OwnedKeyParts();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedCollections <em>Owned Collections</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Collections</em>'.
   * @see org.polarsys.capella.core.data.information.DataPkg#getOwnedCollections()
   * @see #getDataPkg()
   * @generated
   */
	EReference getDataPkg_OwnedCollections();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedUnits <em>Owned Units</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Units</em>'.
   * @see org.polarsys.capella.core.data.information.DataPkg#getOwnedUnits()
   * @see #getDataPkg()
   * @generated
   */
	EReference getDataPkg_OwnedUnits();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedDataTypes <em>Owned Data Types</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Data Types</em>'.
   * @see org.polarsys.capella.core.data.information.DataPkg#getOwnedDataTypes()
   * @see #getDataPkg()
   * @generated
   */
	EReference getDataPkg_OwnedDataTypes();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedSignals <em>Owned Signals</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Signals</em>'.
   * @see org.polarsys.capella.core.data.information.DataPkg#getOwnedSignals()
   * @see #getDataPkg()
   * @generated
   */
	EReference getDataPkg_OwnedSignals();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedMessages <em>Owned Messages</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Messages</em>'.
   * @see org.polarsys.capella.core.data.information.DataPkg#getOwnedMessages()
   * @see #getDataPkg()
   * @generated
   */
	EReference getDataPkg_OwnedMessages();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedExceptions <em>Owned Exceptions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Exceptions</em>'.
   * @see org.polarsys.capella.core.data.information.DataPkg#getOwnedExceptions()
   * @see #getDataPkg()
   * @generated
   */
	EReference getDataPkg_OwnedExceptions();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedStateEvents <em>Owned State Events</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned State Events</em>'.
   * @see org.polarsys.capella.core.data.information.DataPkg#getOwnedStateEvents()
   * @see #getDataPkg()
   * @generated
   */
	EReference getDataPkg_OwnedStateEvents();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.DomainElement <em>Domain Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Domain Element</em>'.
   * @see org.polarsys.capella.core.data.information.DomainElement
   * @generated
   */
	EClass getDomainElement();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.KeyPart <em>Key Part</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Key Part</em>'.
   * @see org.polarsys.capella.core.data.information.KeyPart
   * @generated
   */
	EClass getKeyPart();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.KeyPart#getProperty <em>Property</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Property</em>'.
   * @see org.polarsys.capella.core.data.information.KeyPart#getProperty()
   * @see #getKeyPart()
   * @generated
   */
	EReference getKeyPart_Property();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.MultiplicityElement <em>Multiplicity Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Multiplicity Element</em>'.
   * @see org.polarsys.capella.core.data.information.MultiplicityElement
   * @generated
   */
	EClass getMultiplicityElement();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.MultiplicityElement#isOrdered <em>Ordered</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ordered</em>'.
   * @see org.polarsys.capella.core.data.information.MultiplicityElement#isOrdered()
   * @see #getMultiplicityElement()
   * @generated
   */
	EAttribute getMultiplicityElement_Ordered();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.MultiplicityElement#isUnique <em>Unique</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Unique</em>'.
   * @see org.polarsys.capella.core.data.information.MultiplicityElement#isUnique()
   * @see #getMultiplicityElement()
   * @generated
   */
	EAttribute getMultiplicityElement_Unique();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.MultiplicityElement#isMinInclusive <em>Min Inclusive</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Min Inclusive</em>'.
   * @see org.polarsys.capella.core.data.information.MultiplicityElement#isMinInclusive()
   * @see #getMultiplicityElement()
   * @generated
   */
	EAttribute getMultiplicityElement_MinInclusive();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.MultiplicityElement#isMaxInclusive <em>Max Inclusive</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Max Inclusive</em>'.
   * @see org.polarsys.capella.core.data.information.MultiplicityElement#isMaxInclusive()
   * @see #getMultiplicityElement()
   * @generated
   */
	EAttribute getMultiplicityElement_MaxInclusive();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedDefaultValue <em>Owned Default Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Default Value</em>'.
   * @see org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedDefaultValue()
   * @see #getMultiplicityElement()
   * @generated
   */
	EReference getMultiplicityElement_OwnedDefaultValue();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMinValue <em>Owned Min Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Min Value</em>'.
   * @see org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMinValue()
   * @see #getMultiplicityElement()
   * @generated
   */
	EReference getMultiplicityElement_OwnedMinValue();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMaxValue <em>Owned Max Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Max Value</em>'.
   * @see org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMaxValue()
   * @see #getMultiplicityElement()
   * @generated
   */
	EReference getMultiplicityElement_OwnedMaxValue();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedNullValue <em>Owned Null Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Null Value</em>'.
   * @see org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedNullValue()
   * @see #getMultiplicityElement()
   * @generated
   */
	EReference getMultiplicityElement_OwnedNullValue();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMinCard <em>Owned Min Card</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Min Card</em>'.
   * @see org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMinCard()
   * @see #getMultiplicityElement()
   * @generated
   */
	EReference getMultiplicityElement_OwnedMinCard();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMinLength <em>Owned Min Length</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Min Length</em>'.
   * @see org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMinLength()
   * @see #getMultiplicityElement()
   * @generated
   */
	EReference getMultiplicityElement_OwnedMinLength();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMaxCard <em>Owned Max Card</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Max Card</em>'.
   * @see org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMaxCard()
   * @see #getMultiplicityElement()
   * @generated
   */
	EReference getMultiplicityElement_OwnedMaxCard();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMaxLength <em>Owned Max Length</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Max Length</em>'.
   * @see org.polarsys.capella.core.data.information.MultiplicityElement#getOwnedMaxLength()
   * @see #getMultiplicityElement()
   * @generated
   */
	EReference getMultiplicityElement_OwnedMaxLength();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.Operation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation</em>'.
   * @see org.polarsys.capella.core.data.information.Operation
   * @generated
   */
	EClass getOperation();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.Operation#getOwnedParameters <em>Owned Parameters</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Parameters</em>'.
   * @see org.polarsys.capella.core.data.information.Operation#getOwnedParameters()
   * @see #getOperation()
   * @generated
   */
	EReference getOperation_OwnedParameters();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Operation#getAllocatingOperations <em>Allocating Operations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocating Operations</em>'.
   * @see org.polarsys.capella.core.data.information.Operation#getAllocatingOperations()
   * @see #getOperation()
   * @generated
   */
	EReference getOperation_AllocatingOperations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Operation#getAllocatedOperations <em>Allocated Operations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated Operations</em>'.
   * @see org.polarsys.capella.core.data.information.Operation#getAllocatedOperations()
   * @see #getOperation()
   * @generated
   */
	EReference getOperation_AllocatedOperations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.Operation#getOwnedOperationAllocation <em>Owned Operation Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Operation Allocation</em>'.
   * @see org.polarsys.capella.core.data.information.Operation#getOwnedOperationAllocation()
   * @see #getOperation()
   * @generated
   */
	EReference getOperation_OwnedOperationAllocation();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.Operation#getOwnedExchangeItemRealizations <em>Owned Exchange Item Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Exchange Item Realizations</em>'.
   * @see org.polarsys.capella.core.data.information.Operation#getOwnedExchangeItemRealizations()
   * @see #getOperation()
   * @generated
   */
	EReference getOperation_OwnedExchangeItemRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Operation#getRealizedExchangeItems <em>Realized Exchange Items</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Exchange Items</em>'.
   * @see org.polarsys.capella.core.data.information.Operation#getRealizedExchangeItems()
   * @see #getOperation()
   * @generated
   */
	EReference getOperation_RealizedExchangeItems();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.OperationAllocation <em>Operation Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Allocation</em>'.
   * @see org.polarsys.capella.core.data.information.OperationAllocation
   * @generated
   */
	EClass getOperationAllocation();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.OperationAllocation#getAllocatedOperation <em>Allocated Operation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocated Operation</em>'.
   * @see org.polarsys.capella.core.data.information.OperationAllocation#getAllocatedOperation()
   * @see #getOperationAllocation()
   * @generated
   */
	EReference getOperationAllocation_AllocatedOperation();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.OperationAllocation#getAllocatingOperation <em>Allocating Operation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocating Operation</em>'.
   * @see org.polarsys.capella.core.data.information.OperationAllocation#getAllocatingOperation()
   * @see #getOperationAllocation()
   * @generated
   */
	EReference getOperationAllocation_AllocatingOperation();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.Parameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter</em>'.
   * @see org.polarsys.capella.core.data.information.Parameter
   * @generated
   */
	EClass getParameter();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.Parameter#getDirection <em>Direction</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Direction</em>'.
   * @see org.polarsys.capella.core.data.information.Parameter#getDirection()
   * @see #getParameter()
   * @generated
   */
	EAttribute getParameter_Direction();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.Parameter#getPassingMode <em>Passing Mode</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Passing Mode</em>'.
   * @see org.polarsys.capella.core.data.information.Parameter#getPassingMode()
   * @see #getParameter()
   * @generated
   */
	EAttribute getParameter_PassingMode();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.Property <em>Property</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property</em>'.
   * @see org.polarsys.capella.core.data.information.Property
   * @generated
   */
	EClass getProperty();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.Property#getAggregationKind <em>Aggregation Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Aggregation Kind</em>'.
   * @see org.polarsys.capella.core.data.information.Property#getAggregationKind()
   * @see #getProperty()
   * @generated
   */
	EAttribute getProperty_AggregationKind();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.Property#isIsDerived <em>Is Derived</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Derived</em>'.
   * @see org.polarsys.capella.core.data.information.Property#isIsDerived()
   * @see #getProperty()
   * @generated
   */
	EAttribute getProperty_IsDerived();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.Property#isIsReadOnly <em>Is Read Only</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Read Only</em>'.
   * @see org.polarsys.capella.core.data.information.Property#isIsReadOnly()
   * @see #getProperty()
   * @generated
   */
	EAttribute getProperty_IsReadOnly();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.Property#isIsPartOfKey <em>Is Part Of Key</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Part Of Key</em>'.
   * @see org.polarsys.capella.core.data.information.Property#isIsPartOfKey()
   * @see #getProperty()
   * @generated
   */
	EAttribute getProperty_IsPartOfKey();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.Property#getAssociation <em>Association</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Association</em>'.
   * @see org.polarsys.capella.core.data.information.Property#getAssociation()
   * @see #getProperty()
   * @generated
   */
	EReference getProperty_Association();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.Service <em>Service</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Service</em>'.
   * @see org.polarsys.capella.core.data.information.Service
   * @generated
   */
	EClass getService();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.Service#getSynchronismKind <em>Synchronism Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Synchronism Kind</em>'.
   * @see org.polarsys.capella.core.data.information.Service#getSynchronismKind()
   * @see #getService()
   * @generated
   */
	EAttribute getService_SynchronismKind();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Service#getThrownExceptions <em>Thrown Exceptions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Thrown Exceptions</em>'.
   * @see org.polarsys.capella.core.data.information.Service#getThrownExceptions()
   * @see #getService()
   * @generated
   */
	EReference getService_ThrownExceptions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Service#getMessages <em>Messages</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Messages</em>'.
   * @see org.polarsys.capella.core.data.information.Service#getMessages()
   * @see #getService()
   * @generated
   */
	EReference getService_Messages();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Service#getMessageReferences <em>Message References</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Message References</em>'.
   * @see org.polarsys.capella.core.data.information.Service#getMessageReferences()
   * @see #getService()
   * @generated
   */
	EReference getService_MessageReferences();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.Union <em>Union</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Union</em>'.
   * @see org.polarsys.capella.core.data.information.Union
   * @generated
   */
	EClass getUnion();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.Union#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.information.Union#getKind()
   * @see #getUnion()
   * @generated
   */
	EAttribute getUnion_Kind();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.Union#getDiscriminant <em>Discriminant</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Discriminant</em>'.
   * @see org.polarsys.capella.core.data.information.Union#getDiscriminant()
   * @see #getUnion()
   * @generated
   */
	EReference getUnion_Discriminant();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.Union#getDefaultProperty <em>Default Property</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Default Property</em>'.
   * @see org.polarsys.capella.core.data.information.Union#getDefaultProperty()
   * @see #getUnion()
   * @generated
   */
	EReference getUnion_DefaultProperty();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Union#getContainedUnionProperties <em>Contained Union Properties</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Union Properties</em>'.
   * @see org.polarsys.capella.core.data.information.Union#getContainedUnionProperties()
   * @see #getUnion()
   * @generated
   */
	EReference getUnion_ContainedUnionProperties();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.UnionProperty <em>Union Property</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Union Property</em>'.
   * @see org.polarsys.capella.core.data.information.UnionProperty
   * @generated
   */
	EClass getUnionProperty();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.UnionProperty#getQualifier <em>Qualifier</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Qualifier</em>'.
   * @see org.polarsys.capella.core.data.information.UnionProperty#getQualifier()
   * @see #getUnionProperty()
   * @generated
   */
	EReference getUnionProperty_Qualifier();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.Unit <em>Unit</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unit</em>'.
   * @see org.polarsys.capella.core.data.information.Unit
   * @generated
   */
	EClass getUnit();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.Port <em>Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Port</em>'.
   * @see org.polarsys.capella.core.data.information.Port
   * @generated
   */
	EClass getPort();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Port#getIncomingPortRealizations <em>Incoming Port Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Incoming Port Realizations</em>'.
   * @see org.polarsys.capella.core.data.information.Port#getIncomingPortRealizations()
   * @see #getPort()
   * @generated
   */
	EReference getPort_IncomingPortRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Port#getOutgoingPortRealizations <em>Outgoing Port Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Outgoing Port Realizations</em>'.
   * @see org.polarsys.capella.core.data.information.Port#getOutgoingPortRealizations()
   * @see #getPort()
   * @generated
   */
	EReference getPort_OutgoingPortRealizations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.Port#getOwnedProtocols <em>Owned Protocols</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Protocols</em>'.
   * @see org.polarsys.capella.core.data.information.Port#getOwnedProtocols()
   * @see #getPort()
   * @generated
   */
	EReference getPort_OwnedProtocols();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Port#getIncomingPortAllocations <em>Incoming Port Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Incoming Port Allocations</em>'.
   * @see org.polarsys.capella.core.data.information.Port#getIncomingPortAllocations()
   * @see #getPort()
   * @generated
   */
	EReference getPort_IncomingPortAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Port#getOutgoingPortAllocations <em>Outgoing Port Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Outgoing Port Allocations</em>'.
   * @see org.polarsys.capella.core.data.information.Port#getOutgoingPortAllocations()
   * @see #getPort()
   * @generated
   */
	EReference getPort_OutgoingPortAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Port#getProvidedInterfaces <em>Provided Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Provided Interfaces</em>'.
   * @see org.polarsys.capella.core.data.information.Port#getProvidedInterfaces()
   * @see #getPort()
   * @generated
   */
	EReference getPort_ProvidedInterfaces();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.Port#getRequiredInterfaces <em>Required Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Required Interfaces</em>'.
   * @see org.polarsys.capella.core.data.information.Port#getRequiredInterfaces()
   * @see #getPort()
   * @generated
   */
	EReference getPort_RequiredInterfaces();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.Port#getOwnedPortRealizations <em>Owned Port Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Port Realizations</em>'.
   * @see org.polarsys.capella.core.data.information.Port#getOwnedPortRealizations()
   * @see #getPort()
   * @generated
   */
	EReference getPort_OwnedPortRealizations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.Port#getOwnedPortAllocations <em>Owned Port Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Port Allocations</em>'.
   * @see org.polarsys.capella.core.data.information.Port#getOwnedPortAllocations()
   * @see #getPort()
   * @generated
   */
	EReference getPort_OwnedPortAllocations();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.PortRealization <em>Port Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Port Realization</em>'.
   * @see org.polarsys.capella.core.data.information.PortRealization
   * @generated
   */
	EClass getPortRealization();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.PortRealization#getRealizedPort <em>Realized Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realized Port</em>'.
   * @see org.polarsys.capella.core.data.information.PortRealization#getRealizedPort()
   * @see #getPortRealization()
   * @generated
   */
	EReference getPortRealization_RealizedPort();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.PortRealization#getRealizingPort <em>Realizing Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realizing Port</em>'.
   * @see org.polarsys.capella.core.data.information.PortRealization#getRealizingPort()
   * @see #getPortRealization()
   * @generated
   */
	EReference getPortRealization_RealizingPort();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.PortAllocation <em>Port Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Port Allocation</em>'.
   * @see org.polarsys.capella.core.data.information.PortAllocation
   * @generated
   */
	EClass getPortAllocation();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.PortAllocation#getAllocatedPort <em>Allocated Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocated Port</em>'.
   * @see org.polarsys.capella.core.data.information.PortAllocation#getAllocatedPort()
   * @see #getPortAllocation()
   * @generated
   */
	EReference getPortAllocation_AllocatedPort();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.PortAllocation#getAllocatingPort <em>Allocating Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocating Port</em>'.
   * @see org.polarsys.capella.core.data.information.PortAllocation#getAllocatingPort()
   * @see #getPortAllocation()
   * @generated
   */
	EReference getPortAllocation_AllocatingPort();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.ExchangeItem <em>Exchange Item</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exchange Item</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItem
   * @generated
   */
	EClass getExchangeItem();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.ExchangeItem#getExchangeMechanism <em>Exchange Mechanism</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Exchange Mechanism</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItem#getExchangeMechanism()
   * @see #getExchangeItem()
   * @generated
   */
	EAttribute getExchangeItem_ExchangeMechanism();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.ExchangeItem#getOwnedElements <em>Owned Elements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Elements</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItem#getOwnedElements()
   * @see #getExchangeItem()
   * @generated
   */
	EReference getExchangeItem_OwnedElements();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.ExchangeItem#getOwnedInformationRealizations <em>Owned Information Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Information Realizations</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItem#getOwnedInformationRealizations()
   * @see #getExchangeItem()
   * @generated
   */
	EReference getExchangeItem_OwnedInformationRealizations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.ExchangeItem#getOwnedExchangeItemInstances <em>Owned Exchange Item Instances</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Exchange Item Instances</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItem#getOwnedExchangeItemInstances()
   * @see #getExchangeItem()
   * @generated
   */
	EReference getExchangeItem_OwnedExchangeItemInstances();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.ExchangeItem#getAllocatorInterfaces <em>Allocator Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocator Interfaces</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItem#getAllocatorInterfaces()
   * @see #getExchangeItem()
   * @generated
   */
	EReference getExchangeItem_AllocatorInterfaces();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.ExchangeItem#getRealizedExchangeItems <em>Realized Exchange Items</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Exchange Items</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItem#getRealizedExchangeItems()
   * @see #getExchangeItem()
   * @generated
   */
	EReference getExchangeItem_RealizedExchangeItems();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.ExchangeItem#getRealizingExchangeItems <em>Realizing Exchange Items</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Exchange Items</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItem#getRealizingExchangeItems()
   * @see #getExchangeItem()
   * @generated
   */
	EReference getExchangeItem_RealizingExchangeItems();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.ExchangeItem#getRealizingOperations <em>Realizing Operations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Operations</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItem#getRealizingOperations()
   * @see #getExchangeItem()
   * @generated
   */
	EReference getExchangeItem_RealizingOperations();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.ExchangeItemElement <em>Exchange Item Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exchange Item Element</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItemElement
   * @generated
   */
	EClass getExchangeItemElement();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.ExchangeItemElement#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItemElement#getKind()
   * @see #getExchangeItemElement()
   * @generated
   */
	EAttribute getExchangeItemElement_Kind();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.ExchangeItemElement#getDirection <em>Direction</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Direction</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItemElement#getDirection()
   * @see #getExchangeItemElement()
   * @generated
   */
	EAttribute getExchangeItemElement_Direction();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.ExchangeItemElement#isComposite <em>Composite</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Composite</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItemElement#isComposite()
   * @see #getExchangeItemElement()
   * @generated
   */
	EAttribute getExchangeItemElement_Composite();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.ExchangeItemElement#getReferencedProperties <em>Referenced Properties</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Referenced Properties</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItemElement#getReferencedProperties()
   * @see #getExchangeItemElement()
   * @generated
   */
	EReference getExchangeItemElement_ReferencedProperties();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.ExchangeItemInstance <em>Exchange Item Instance</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exchange Item Instance</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItemInstance
   * @generated
   */
	EClass getExchangeItemInstance();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.InformationRealization <em>Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Realization</em>'.
   * @see org.polarsys.capella.core.data.information.InformationRealization
   * @generated
   */
	EClass getInformationRealization();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.ExchangeItemRealization <em>Exchange Item Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exchange Item Realization</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItemRealization
   * @generated
   */
	EClass getExchangeItemRealization();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.ExchangeItemRealization#getRealizedItem <em>Realized Item</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realized Item</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItemRealization#getRealizedItem()
   * @see #getExchangeItemRealization()
   * @generated
   */
	EReference getExchangeItemRealization_RealizedItem();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.ExchangeItemRealization#getRealizingOperation <em>Realizing Operation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realizing Operation</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeItemRealization#getRealizingOperation()
   * @see #getExchangeItemRealization()
   * @generated
   */
	EReference getExchangeItemRealization_RealizingOperation();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.AbstractEventOperation <em>Abstract Event Operation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Event Operation</em>'.
   * @see org.polarsys.capella.core.data.information.AbstractEventOperation
   * @generated
   */
	EClass getAbstractEventOperation();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.AbstractEventOperation#getInvokingSequenceMessages <em>Invoking Sequence Messages</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Invoking Sequence Messages</em>'.
   * @see org.polarsys.capella.core.data.information.AbstractEventOperation#getInvokingSequenceMessages()
   * @see #getAbstractEventOperation()
   * @generated
   */
	EReference getAbstractEventOperation_InvokingSequenceMessages();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.information.AggregationKind <em>Aggregation Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Aggregation Kind</em>'.
   * @see org.polarsys.capella.core.data.information.AggregationKind
   * @generated
   */
	EEnum getAggregationKind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.information.ParameterDirection <em>Parameter Direction</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Parameter Direction</em>'.
   * @see org.polarsys.capella.core.data.information.ParameterDirection
   * @generated
   */
	EEnum getParameterDirection();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.information.PassingMode <em>Passing Mode</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Passing Mode</em>'.
   * @see org.polarsys.capella.core.data.information.PassingMode
   * @generated
   */
	EEnum getPassingMode();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.information.SynchronismKind <em>Synchronism Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Synchronism Kind</em>'.
   * @see org.polarsys.capella.core.data.information.SynchronismKind
   * @generated
   */
	EEnum getSynchronismKind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.information.UnionKind <em>Union Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Union Kind</em>'.
   * @see org.polarsys.capella.core.data.information.UnionKind
   * @generated
   */
	EEnum getUnionKind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.information.ExchangeMechanism <em>Exchange Mechanism</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Exchange Mechanism</em>'.
   * @see org.polarsys.capella.core.data.information.ExchangeMechanism
   * @generated
   */
	EEnum getExchangeMechanism();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.information.ElementKind <em>Element Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Element Kind</em>'.
   * @see org.polarsys.capella.core.data.information.ElementKind
   * @generated
   */
	EEnum getElementKind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.information.CollectionKind <em>Collection Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Collection Kind</em>'.
   * @see org.polarsys.capella.core.data.information.CollectionKind
   * @generated
   */
	EEnum getCollectionKind();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	InformationFactory getInformationFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.AbstractInstanceImpl <em>Abstract Instance</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.AbstractInstanceImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getAbstractInstance()
     * @generated
     */
		EClass ABSTRACT_INSTANCE = eINSTANCE.getAbstractInstance();

		/**
     * The meta object literal for the '<em><b>Representing Instance Roles</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_INSTANCE__REPRESENTING_INSTANCE_ROLES = eINSTANCE.getAbstractInstance_RepresentingInstanceRoles();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.AssociationPkgImpl <em>Association Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.AssociationPkgImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getAssociationPkg()
     * @generated
     */
		EClass ASSOCIATION_PKG = eINSTANCE.getAssociationPkg();

		/**
     * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ASSOCIATION_PKG__VISIBILITY = eINSTANCE.getAssociationPkg_Visibility();

		/**
     * The meta object literal for the '<em><b>Owned Associations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ASSOCIATION_PKG__OWNED_ASSOCIATIONS = eINSTANCE.getAssociationPkg_OwnedAssociations();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.AssociationImpl <em>Association</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.AssociationImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getAssociation()
     * @generated
     */
		EClass ASSOCIATION = eINSTANCE.getAssociation();

		/**
     * The meta object literal for the '<em><b>Owned Members</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ASSOCIATION__OWNED_MEMBERS = eINSTANCE.getAssociation_OwnedMembers();

		/**
     * The meta object literal for the '<em><b>Navigable Members</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ASSOCIATION__NAVIGABLE_MEMBERS = eINSTANCE.getAssociation_NavigableMembers();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.ClassImpl <em>Class</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.ClassImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getClass_()
     * @generated
     */
		EClass CLASS = eINSTANCE.getClass_();

		/**
     * The meta object literal for the '<em><b>Is Primitive</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CLASS__IS_PRIMITIVE = eINSTANCE.getClass_IsPrimitive();

		/**
     * The meta object literal for the '<em><b>Key Parts</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CLASS__KEY_PARTS = eINSTANCE.getClass_KeyParts();

		/**
     * The meta object literal for the '<em><b>Owned State Machines</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CLASS__OWNED_STATE_MACHINES = eINSTANCE.getClass_OwnedStateMachines();

		/**
     * The meta object literal for the '<em><b>Owned Data Values</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CLASS__OWNED_DATA_VALUES = eINSTANCE.getClass_OwnedDataValues();

		/**
     * The meta object literal for the '<em><b>Owned Information Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CLASS__OWNED_INFORMATION_REALIZATIONS = eINSTANCE.getClass_OwnedInformationRealizations();

		/**
     * The meta object literal for the '<em><b>Realized Classes</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CLASS__REALIZED_CLASSES = eINSTANCE.getClass_RealizedClasses();

		/**
     * The meta object literal for the '<em><b>Realizing Classes</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CLASS__REALIZING_CLASSES = eINSTANCE.getClass_RealizingClasses();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.CollectionImpl <em>Collection</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.CollectionImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getCollection()
     * @generated
     */
		EClass COLLECTION = eINSTANCE.getCollection();

		/**
     * The meta object literal for the '<em><b>Is Primitive</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute COLLECTION__IS_PRIMITIVE = eINSTANCE.getCollection_IsPrimitive();

		/**
     * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute COLLECTION__VISIBILITY = eINSTANCE.getCollection_Visibility();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute COLLECTION__KIND = eINSTANCE.getCollection_Kind();

		/**
     * The meta object literal for the '<em><b>Aggregation Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute COLLECTION__AGGREGATION_KIND = eINSTANCE.getCollection_AggregationKind();

		/**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COLLECTION__TYPE = eINSTANCE.getCollection_Type();

		/**
     * The meta object literal for the '<em><b>Index</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COLLECTION__INDEX = eINSTANCE.getCollection_Index();

		/**
     * The meta object literal for the '<em><b>Contained Operations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COLLECTION__CONTAINED_OPERATIONS = eINSTANCE.getCollection_ContainedOperations();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.AbstractCollectionValueImpl <em>Abstract Collection Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.AbstractCollectionValueImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getAbstractCollectionValue()
     * @generated
     */
		EClass ABSTRACT_COLLECTION_VALUE = eINSTANCE.getAbstractCollectionValue();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.CollectionValueImpl <em>Collection Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.CollectionValueImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getCollectionValue()
     * @generated
     */
		EClass COLLECTION_VALUE = eINSTANCE.getCollectionValue();

		/**
     * The meta object literal for the '<em><b>Owned Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COLLECTION_VALUE__OWNED_ELEMENTS = eINSTANCE.getCollectionValue_OwnedElements();

		/**
     * The meta object literal for the '<em><b>Owned Default Element</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT = eINSTANCE.getCollectionValue_OwnedDefaultElement();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.CollectionValueReferenceImpl <em>Collection Value Reference</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.CollectionValueReferenceImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getCollectionValueReference()
     * @generated
     */
		EClass COLLECTION_VALUE_REFERENCE = eINSTANCE.getCollectionValueReference();

		/**
     * The meta object literal for the '<em><b>Referenced Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COLLECTION_VALUE_REFERENCE__REFERENCED_VALUE = eINSTANCE.getCollectionValueReference_ReferencedValue();

		/**
     * The meta object literal for the '<em><b>Referenced Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COLLECTION_VALUE_REFERENCE__REFERENCED_PROPERTY = eINSTANCE.getCollectionValueReference_ReferencedProperty();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.DataPkgImpl <em>Data Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.DataPkgImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getDataPkg()
     * @generated
     */
		EClass DATA_PKG = eINSTANCE.getDataPkg();

		/**
     * The meta object literal for the '<em><b>Owned Data Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_PKG__OWNED_DATA_PKGS = eINSTANCE.getDataPkg_OwnedDataPkgs();

		/**
     * The meta object literal for the '<em><b>Owned Classes</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_PKG__OWNED_CLASSES = eINSTANCE.getDataPkg_OwnedClasses();

		/**
     * The meta object literal for the '<em><b>Owned Key Parts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_PKG__OWNED_KEY_PARTS = eINSTANCE.getDataPkg_OwnedKeyParts();

		/**
     * The meta object literal for the '<em><b>Owned Collections</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_PKG__OWNED_COLLECTIONS = eINSTANCE.getDataPkg_OwnedCollections();

		/**
     * The meta object literal for the '<em><b>Owned Units</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_PKG__OWNED_UNITS = eINSTANCE.getDataPkg_OwnedUnits();

		/**
     * The meta object literal for the '<em><b>Owned Data Types</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_PKG__OWNED_DATA_TYPES = eINSTANCE.getDataPkg_OwnedDataTypes();

		/**
     * The meta object literal for the '<em><b>Owned Signals</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_PKG__OWNED_SIGNALS = eINSTANCE.getDataPkg_OwnedSignals();

		/**
     * The meta object literal for the '<em><b>Owned Messages</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_PKG__OWNED_MESSAGES = eINSTANCE.getDataPkg_OwnedMessages();

		/**
     * The meta object literal for the '<em><b>Owned Exceptions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_PKG__OWNED_EXCEPTIONS = eINSTANCE.getDataPkg_OwnedExceptions();

		/**
     * The meta object literal for the '<em><b>Owned State Events</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_PKG__OWNED_STATE_EVENTS = eINSTANCE.getDataPkg_OwnedStateEvents();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.DomainElementImpl <em>Domain Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.DomainElementImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getDomainElement()
     * @generated
     */
		EClass DOMAIN_ELEMENT = eINSTANCE.getDomainElement();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.KeyPartImpl <em>Key Part</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.KeyPartImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getKeyPart()
     * @generated
     */
		EClass KEY_PART = eINSTANCE.getKeyPart();

		/**
     * The meta object literal for the '<em><b>Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference KEY_PART__PROPERTY = eINSTANCE.getKeyPart_Property();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.MultiplicityElementImpl <em>Multiplicity Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.MultiplicityElementImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getMultiplicityElement()
     * @generated
     */
		EClass MULTIPLICITY_ELEMENT = eINSTANCE.getMultiplicityElement();

		/**
     * The meta object literal for the '<em><b>Ordered</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute MULTIPLICITY_ELEMENT__ORDERED = eINSTANCE.getMultiplicityElement_Ordered();

		/**
     * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute MULTIPLICITY_ELEMENT__UNIQUE = eINSTANCE.getMultiplicityElement_Unique();

		/**
     * The meta object literal for the '<em><b>Min Inclusive</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute MULTIPLICITY_ELEMENT__MIN_INCLUSIVE = eINSTANCE.getMultiplicityElement_MinInclusive();

		/**
     * The meta object literal for the '<em><b>Max Inclusive</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute MULTIPLICITY_ELEMENT__MAX_INCLUSIVE = eINSTANCE.getMultiplicityElement_MaxInclusive();

		/**
     * The meta object literal for the '<em><b>Owned Default Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE = eINSTANCE.getMultiplicityElement_OwnedDefaultValue();

		/**
     * The meta object literal for the '<em><b>Owned Min Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE = eINSTANCE.getMultiplicityElement_OwnedMinValue();

		/**
     * The meta object literal for the '<em><b>Owned Max Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE = eINSTANCE.getMultiplicityElement_OwnedMaxValue();

		/**
     * The meta object literal for the '<em><b>Owned Null Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE = eINSTANCE.getMultiplicityElement_OwnedNullValue();

		/**
     * The meta object literal for the '<em><b>Owned Min Card</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MULTIPLICITY_ELEMENT__OWNED_MIN_CARD = eINSTANCE.getMultiplicityElement_OwnedMinCard();

		/**
     * The meta object literal for the '<em><b>Owned Min Length</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH = eINSTANCE.getMultiplicityElement_OwnedMinLength();

		/**
     * The meta object literal for the '<em><b>Owned Max Card</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MULTIPLICITY_ELEMENT__OWNED_MAX_CARD = eINSTANCE.getMultiplicityElement_OwnedMaxCard();

		/**
     * The meta object literal for the '<em><b>Owned Max Length</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH = eINSTANCE.getMultiplicityElement_OwnedMaxLength();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.OperationImpl <em>Operation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.OperationImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getOperation()
     * @generated
     */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
     * The meta object literal for the '<em><b>Owned Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION__OWNED_PARAMETERS = eINSTANCE.getOperation_OwnedParameters();

		/**
     * The meta object literal for the '<em><b>Allocating Operations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION__ALLOCATING_OPERATIONS = eINSTANCE.getOperation_AllocatingOperations();

		/**
     * The meta object literal for the '<em><b>Allocated Operations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION__ALLOCATED_OPERATIONS = eINSTANCE.getOperation_AllocatedOperations();

		/**
     * The meta object literal for the '<em><b>Owned Operation Allocation</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION__OWNED_OPERATION_ALLOCATION = eINSTANCE.getOperation_OwnedOperationAllocation();

		/**
     * The meta object literal for the '<em><b>Owned Exchange Item Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION__OWNED_EXCHANGE_ITEM_REALIZATIONS = eINSTANCE.getOperation_OwnedExchangeItemRealizations();

		/**
     * The meta object literal for the '<em><b>Realized Exchange Items</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION__REALIZED_EXCHANGE_ITEMS = eINSTANCE.getOperation_RealizedExchangeItems();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.OperationAllocationImpl <em>Operation Allocation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.OperationAllocationImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getOperationAllocation()
     * @generated
     */
		EClass OPERATION_ALLOCATION = eINSTANCE.getOperationAllocation();

		/**
     * The meta object literal for the '<em><b>Allocated Operation</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION_ALLOCATION__ALLOCATED_OPERATION = eINSTANCE.getOperationAllocation_AllocatedOperation();

		/**
     * The meta object literal for the '<em><b>Allocating Operation</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATION_ALLOCATION__ALLOCATING_OPERATION = eINSTANCE.getOperationAllocation_AllocatingOperation();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.ParameterImpl <em>Parameter</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.ParameterImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getParameter()
     * @generated
     */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
     * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute PARAMETER__DIRECTION = eINSTANCE.getParameter_Direction();

		/**
     * The meta object literal for the '<em><b>Passing Mode</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute PARAMETER__PASSING_MODE = eINSTANCE.getParameter_PassingMode();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.PropertyImpl <em>Property</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.PropertyImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getProperty()
     * @generated
     */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
     * The meta object literal for the '<em><b>Aggregation Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute PROPERTY__AGGREGATION_KIND = eINSTANCE.getProperty_AggregationKind();

		/**
     * The meta object literal for the '<em><b>Is Derived</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute PROPERTY__IS_DERIVED = eINSTANCE.getProperty_IsDerived();

		/**
     * The meta object literal for the '<em><b>Is Read Only</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute PROPERTY__IS_READ_ONLY = eINSTANCE.getProperty_IsReadOnly();

		/**
     * The meta object literal for the '<em><b>Is Part Of Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute PROPERTY__IS_PART_OF_KEY = eINSTANCE.getProperty_IsPartOfKey();

		/**
     * The meta object literal for the '<em><b>Association</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PROPERTY__ASSOCIATION = eINSTANCE.getProperty_Association();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.ServiceImpl <em>Service</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.ServiceImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getService()
     * @generated
     */
		EClass SERVICE = eINSTANCE.getService();

		/**
     * The meta object literal for the '<em><b>Synchronism Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute SERVICE__SYNCHRONISM_KIND = eINSTANCE.getService_SynchronismKind();

		/**
     * The meta object literal for the '<em><b>Thrown Exceptions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SERVICE__THROWN_EXCEPTIONS = eINSTANCE.getService_ThrownExceptions();

		/**
     * The meta object literal for the '<em><b>Messages</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SERVICE__MESSAGES = eINSTANCE.getService_Messages();

		/**
     * The meta object literal for the '<em><b>Message References</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SERVICE__MESSAGE_REFERENCES = eINSTANCE.getService_MessageReferences();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.UnionImpl <em>Union</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.UnionImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getUnion()
     * @generated
     */
		EClass UNION = eINSTANCE.getUnion();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute UNION__KIND = eINSTANCE.getUnion_Kind();

		/**
     * The meta object literal for the '<em><b>Discriminant</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference UNION__DISCRIMINANT = eINSTANCE.getUnion_Discriminant();

		/**
     * The meta object literal for the '<em><b>Default Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference UNION__DEFAULT_PROPERTY = eINSTANCE.getUnion_DefaultProperty();

		/**
     * The meta object literal for the '<em><b>Contained Union Properties</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference UNION__CONTAINED_UNION_PROPERTIES = eINSTANCE.getUnion_ContainedUnionProperties();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.UnionPropertyImpl <em>Union Property</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.UnionPropertyImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getUnionProperty()
     * @generated
     */
		EClass UNION_PROPERTY = eINSTANCE.getUnionProperty();

		/**
     * The meta object literal for the '<em><b>Qualifier</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference UNION_PROPERTY__QUALIFIER = eINSTANCE.getUnionProperty_Qualifier();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.UnitImpl <em>Unit</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.UnitImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getUnit()
     * @generated
     */
		EClass UNIT = eINSTANCE.getUnit();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.PortImpl <em>Port</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.PortImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getPort()
     * @generated
     */
		EClass PORT = eINSTANCE.getPort();

		/**
     * The meta object literal for the '<em><b>Incoming Port Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT__INCOMING_PORT_REALIZATIONS = eINSTANCE.getPort_IncomingPortRealizations();

		/**
     * The meta object literal for the '<em><b>Outgoing Port Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT__OUTGOING_PORT_REALIZATIONS = eINSTANCE.getPort_OutgoingPortRealizations();

		/**
     * The meta object literal for the '<em><b>Owned Protocols</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT__OWNED_PROTOCOLS = eINSTANCE.getPort_OwnedProtocols();

		/**
     * The meta object literal for the '<em><b>Incoming Port Allocations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT__INCOMING_PORT_ALLOCATIONS = eINSTANCE.getPort_IncomingPortAllocations();

		/**
     * The meta object literal for the '<em><b>Outgoing Port Allocations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT__OUTGOING_PORT_ALLOCATIONS = eINSTANCE.getPort_OutgoingPortAllocations();

		/**
     * The meta object literal for the '<em><b>Provided Interfaces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT__PROVIDED_INTERFACES = eINSTANCE.getPort_ProvidedInterfaces();

		/**
     * The meta object literal for the '<em><b>Required Interfaces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT__REQUIRED_INTERFACES = eINSTANCE.getPort_RequiredInterfaces();

		/**
     * The meta object literal for the '<em><b>Owned Port Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT__OWNED_PORT_REALIZATIONS = eINSTANCE.getPort_OwnedPortRealizations();

		/**
     * The meta object literal for the '<em><b>Owned Port Allocations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT__OWNED_PORT_ALLOCATIONS = eINSTANCE.getPort_OwnedPortAllocations();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.PortRealizationImpl <em>Port Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.PortRealizationImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getPortRealization()
     * @generated
     */
		EClass PORT_REALIZATION = eINSTANCE.getPortRealization();

		/**
     * The meta object literal for the '<em><b>Realized Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT_REALIZATION__REALIZED_PORT = eINSTANCE.getPortRealization_RealizedPort();

		/**
     * The meta object literal for the '<em><b>Realizing Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT_REALIZATION__REALIZING_PORT = eINSTANCE.getPortRealization_RealizingPort();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.PortAllocationImpl <em>Port Allocation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.PortAllocationImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getPortAllocation()
     * @generated
     */
		EClass PORT_ALLOCATION = eINSTANCE.getPortAllocation();

		/**
     * The meta object literal for the '<em><b>Allocated Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT_ALLOCATION__ALLOCATED_PORT = eINSTANCE.getPortAllocation_AllocatedPort();

		/**
     * The meta object literal for the '<em><b>Allocating Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PORT_ALLOCATION__ALLOCATING_PORT = eINSTANCE.getPortAllocation_AllocatingPort();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl <em>Exchange Item</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.ExchangeItemImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getExchangeItem()
     * @generated
     */
		EClass EXCHANGE_ITEM = eINSTANCE.getExchangeItem();

		/**
     * The meta object literal for the '<em><b>Exchange Mechanism</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute EXCHANGE_ITEM__EXCHANGE_MECHANISM = eINSTANCE.getExchangeItem_ExchangeMechanism();

		/**
     * The meta object literal for the '<em><b>Owned Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_ITEM__OWNED_ELEMENTS = eINSTANCE.getExchangeItem_OwnedElements();

		/**
     * The meta object literal for the '<em><b>Owned Information Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS = eINSTANCE.getExchangeItem_OwnedInformationRealizations();

		/**
     * The meta object literal for the '<em><b>Owned Exchange Item Instances</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_ITEM__OWNED_EXCHANGE_ITEM_INSTANCES = eINSTANCE.getExchangeItem_OwnedExchangeItemInstances();

		/**
     * The meta object literal for the '<em><b>Allocator Interfaces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_ITEM__ALLOCATOR_INTERFACES = eINSTANCE.getExchangeItem_AllocatorInterfaces();

		/**
     * The meta object literal for the '<em><b>Realized Exchange Items</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_ITEM__REALIZED_EXCHANGE_ITEMS = eINSTANCE.getExchangeItem_RealizedExchangeItems();

		/**
     * The meta object literal for the '<em><b>Realizing Exchange Items</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_ITEM__REALIZING_EXCHANGE_ITEMS = eINSTANCE.getExchangeItem_RealizingExchangeItems();

		/**
     * The meta object literal for the '<em><b>Realizing Operations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_ITEM__REALIZING_OPERATIONS = eINSTANCE.getExchangeItem_RealizingOperations();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl <em>Exchange Item Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getExchangeItemElement()
     * @generated
     */
		EClass EXCHANGE_ITEM_ELEMENT = eINSTANCE.getExchangeItemElement();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute EXCHANGE_ITEM_ELEMENT__KIND = eINSTANCE.getExchangeItemElement_Kind();

		/**
     * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute EXCHANGE_ITEM_ELEMENT__DIRECTION = eINSTANCE.getExchangeItemElement_Direction();

		/**
     * The meta object literal for the '<em><b>Composite</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute EXCHANGE_ITEM_ELEMENT__COMPOSITE = eINSTANCE.getExchangeItemElement_Composite();

		/**
     * The meta object literal for the '<em><b>Referenced Properties</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_ITEM_ELEMENT__REFERENCED_PROPERTIES = eINSTANCE.getExchangeItemElement_ReferencedProperties();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.ExchangeItemInstanceImpl <em>Exchange Item Instance</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.ExchangeItemInstanceImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getExchangeItemInstance()
     * @generated
     */
		EClass EXCHANGE_ITEM_INSTANCE = eINSTANCE.getExchangeItemInstance();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.InformationRealizationImpl <em>Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.InformationRealizationImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getInformationRealization()
     * @generated
     */
		EClass INFORMATION_REALIZATION = eINSTANCE.getInformationRealization();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.ExchangeItemRealizationImpl <em>Exchange Item Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.ExchangeItemRealizationImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getExchangeItemRealization()
     * @generated
     */
		EClass EXCHANGE_ITEM_REALIZATION = eINSTANCE.getExchangeItemRealization();

		/**
     * The meta object literal for the '<em><b>Realized Item</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_ITEM_REALIZATION__REALIZED_ITEM = eINSTANCE.getExchangeItemRealization_RealizedItem();

		/**
     * The meta object literal for the '<em><b>Realizing Operation</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_ITEM_REALIZATION__REALIZING_OPERATION = eINSTANCE.getExchangeItemRealization_RealizingOperation();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.impl.AbstractEventOperationImpl <em>Abstract Event Operation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.impl.AbstractEventOperationImpl
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getAbstractEventOperation()
     * @generated
     */
		EClass ABSTRACT_EVENT_OPERATION = eINSTANCE.getAbstractEventOperation();

		/**
     * The meta object literal for the '<em><b>Invoking Sequence Messages</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_EVENT_OPERATION__INVOKING_SEQUENCE_MESSAGES = eINSTANCE.getAbstractEventOperation_InvokingSequenceMessages();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.AggregationKind <em>Aggregation Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.AggregationKind
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getAggregationKind()
     * @generated
     */
		EEnum AGGREGATION_KIND = eINSTANCE.getAggregationKind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.ParameterDirection <em>Parameter Direction</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.ParameterDirection
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getParameterDirection()
     * @generated
     */
		EEnum PARAMETER_DIRECTION = eINSTANCE.getParameterDirection();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.PassingMode <em>Passing Mode</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.PassingMode
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getPassingMode()
     * @generated
     */
		EEnum PASSING_MODE = eINSTANCE.getPassingMode();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.SynchronismKind <em>Synchronism Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.SynchronismKind
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getSynchronismKind()
     * @generated
     */
		EEnum SYNCHRONISM_KIND = eINSTANCE.getSynchronismKind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.UnionKind <em>Union Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.UnionKind
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getUnionKind()
     * @generated
     */
		EEnum UNION_KIND = eINSTANCE.getUnionKind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.ExchangeMechanism <em>Exchange Mechanism</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.ExchangeMechanism
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getExchangeMechanism()
     * @generated
     */
		EEnum EXCHANGE_MECHANISM = eINSTANCE.getExchangeMechanism();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.ElementKind <em>Element Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.ElementKind
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getElementKind()
     * @generated
     */
		EEnum ELEMENT_KIND = eINSTANCE.getElementKind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.CollectionKind <em>Collection Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.CollectionKind
     * @see org.polarsys.capella.core.data.information.impl.InformationPackageImpl#getCollectionKind()
     * @generated
     */
		EEnum COLLECTION_KIND = eINSTANCE.getCollectionKind();

	}

} //InformationPackage
