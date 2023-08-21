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
package org.polarsys.capella.core.data.information.datavalue;

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
 * @see org.polarsys.capella.core.data.information.datavalue.DatavalueFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='sub-package containing the definition of all predefined kinds of data values\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='system,logical,physical' usage\040examples='none' constraints='none' comment/notes='none' reference\040documentation='n/a'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface DatavaluePackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "datavalue"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/core/information/datavalue/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.core.data.information.datavalue"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	DatavaluePackage eINSTANCE = org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.DataValueImpl <em>Data Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DataValueImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getDataValue()
   * @generated
   */
	int DATA_VALUE = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__ABSTRACT_TYPE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__ABSTRACT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE__TYPE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Data Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.DataValueContainerImpl <em>Data Value Container</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DataValueContainerImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getDataValueContainer()
   * @generated
   */
	int DATA_VALUE_CONTAINER = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__ID = CapellacorePackage.STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__SID = CapellacorePackage.STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER__OWNED_DATA_VALUES = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Data Value Container</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_VALUE_CONTAINER_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractBooleanValueImpl <em>Abstract Boolean Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.AbstractBooleanValueImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getAbstractBooleanValue()
   * @generated
   */
	int ABSTRACT_BOOLEAN_VALUE = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__OWNED_EXTENSIONS = DATA_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__ID = DATA_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__SID = DATA_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__CONSTRAINTS = DATA_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__OWNED_CONSTRAINTS = DATA_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__OWNED_MIGRATED_ELEMENTS = DATA_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__NAME = DATA_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__INCOMING_TRACES = DATA_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__OUTGOING_TRACES = DATA_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__VISIBLE_IN_DOC = DATA_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__VISIBLE_IN_LM = DATA_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__SUMMARY = DATA_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__DESCRIPTION = DATA_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__REVIEW = DATA_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__OWNED_PROPERTY_VALUES = DATA_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = DATA_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__APPLIED_PROPERTY_VALUES = DATA_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__OWNED_PROPERTY_VALUE_GROUPS = DATA_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = DATA_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__STATUS = DATA_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__FEATURES = DATA_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__ABSTRACT_TYPE = DATA_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__ABSTRACT = DATA_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__TYPE = DATA_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Boolean Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE__BOOLEAN_TYPE = DATA_VALUE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Boolean Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_BOOLEAN_VALUE_FEATURE_COUNT = DATA_VALUE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.LiteralBooleanValueImpl <em>Literal Boolean Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.LiteralBooleanValueImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getLiteralBooleanValue()
   * @generated
   */
	int LITERAL_BOOLEAN_VALUE = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__OWNED_EXTENSIONS = ABSTRACT_BOOLEAN_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__ID = ABSTRACT_BOOLEAN_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__SID = ABSTRACT_BOOLEAN_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__CONSTRAINTS = ABSTRACT_BOOLEAN_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__OWNED_CONSTRAINTS = ABSTRACT_BOOLEAN_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_BOOLEAN_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__NAME = ABSTRACT_BOOLEAN_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__INCOMING_TRACES = ABSTRACT_BOOLEAN_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__OUTGOING_TRACES = ABSTRACT_BOOLEAN_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__VISIBLE_IN_DOC = ABSTRACT_BOOLEAN_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__VISIBLE_IN_LM = ABSTRACT_BOOLEAN_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__SUMMARY = ABSTRACT_BOOLEAN_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__DESCRIPTION = ABSTRACT_BOOLEAN_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__REVIEW = ABSTRACT_BOOLEAN_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__OWNED_PROPERTY_VALUES = ABSTRACT_BOOLEAN_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_BOOLEAN_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__APPLIED_PROPERTY_VALUES = ABSTRACT_BOOLEAN_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_BOOLEAN_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_BOOLEAN_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__STATUS = ABSTRACT_BOOLEAN_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__FEATURES = ABSTRACT_BOOLEAN_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__ABSTRACT_TYPE = ABSTRACT_BOOLEAN_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__ABSTRACT = ABSTRACT_BOOLEAN_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__TYPE = ABSTRACT_BOOLEAN_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Boolean Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__BOOLEAN_TYPE = ABSTRACT_BOOLEAN_VALUE__BOOLEAN_TYPE;

	/**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE__VALUE = ABSTRACT_BOOLEAN_VALUE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Literal Boolean Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_BOOLEAN_VALUE_FEATURE_COUNT = ABSTRACT_BOOLEAN_VALUE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.BooleanReferenceImpl <em>Boolean Reference</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.BooleanReferenceImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getBooleanReference()
   * @generated
   */
	int BOOLEAN_REFERENCE = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__OWNED_EXTENSIONS = ABSTRACT_BOOLEAN_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__ID = ABSTRACT_BOOLEAN_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__SID = ABSTRACT_BOOLEAN_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__CONSTRAINTS = ABSTRACT_BOOLEAN_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__OWNED_CONSTRAINTS = ABSTRACT_BOOLEAN_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_BOOLEAN_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__NAME = ABSTRACT_BOOLEAN_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__INCOMING_TRACES = ABSTRACT_BOOLEAN_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__OUTGOING_TRACES = ABSTRACT_BOOLEAN_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__VISIBLE_IN_DOC = ABSTRACT_BOOLEAN_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__VISIBLE_IN_LM = ABSTRACT_BOOLEAN_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__SUMMARY = ABSTRACT_BOOLEAN_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__DESCRIPTION = ABSTRACT_BOOLEAN_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__REVIEW = ABSTRACT_BOOLEAN_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__OWNED_PROPERTY_VALUES = ABSTRACT_BOOLEAN_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_BOOLEAN_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__APPLIED_PROPERTY_VALUES = ABSTRACT_BOOLEAN_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_BOOLEAN_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_BOOLEAN_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__STATUS = ABSTRACT_BOOLEAN_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__FEATURES = ABSTRACT_BOOLEAN_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__ABSTRACT_TYPE = ABSTRACT_BOOLEAN_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__ABSTRACT = ABSTRACT_BOOLEAN_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__TYPE = ABSTRACT_BOOLEAN_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Boolean Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__BOOLEAN_TYPE = ABSTRACT_BOOLEAN_VALUE__BOOLEAN_TYPE;

	/**
   * The feature id for the '<em><b>Referenced Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__REFERENCED_VALUE = ABSTRACT_BOOLEAN_VALUE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Referenced Property</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE__REFERENCED_PROPERTY = ABSTRACT_BOOLEAN_VALUE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Boolean Reference</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_REFERENCE_FEATURE_COUNT = ABSTRACT_BOOLEAN_VALUE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractEnumerationValueImpl <em>Abstract Enumeration Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.AbstractEnumerationValueImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getAbstractEnumerationValue()
   * @generated
   */
	int ABSTRACT_ENUMERATION_VALUE = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__OWNED_EXTENSIONS = DATA_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__ID = DATA_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__SID = DATA_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__CONSTRAINTS = DATA_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__OWNED_CONSTRAINTS = DATA_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__OWNED_MIGRATED_ELEMENTS = DATA_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__NAME = DATA_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__INCOMING_TRACES = DATA_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__OUTGOING_TRACES = DATA_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__VISIBLE_IN_DOC = DATA_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__VISIBLE_IN_LM = DATA_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__SUMMARY = DATA_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__DESCRIPTION = DATA_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__REVIEW = DATA_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__OWNED_PROPERTY_VALUES = DATA_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = DATA_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__APPLIED_PROPERTY_VALUES = DATA_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__OWNED_PROPERTY_VALUE_GROUPS = DATA_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = DATA_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__STATUS = DATA_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__FEATURES = DATA_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__ABSTRACT_TYPE = DATA_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__ABSTRACT = DATA_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__TYPE = DATA_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Enumeration Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE__ENUMERATION_TYPE = DATA_VALUE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Enumeration Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ENUMERATION_VALUE_FEATURE_COUNT = DATA_VALUE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.EnumerationLiteralImpl <em>Enumeration Literal</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.EnumerationLiteralImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getEnumerationLiteral()
   * @generated
   */
	int ENUMERATION_LITERAL = 6;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__OWNED_EXTENSIONS = ABSTRACT_ENUMERATION_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__ID = ABSTRACT_ENUMERATION_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__SID = ABSTRACT_ENUMERATION_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__CONSTRAINTS = ABSTRACT_ENUMERATION_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__OWNED_CONSTRAINTS = ABSTRACT_ENUMERATION_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__OWNED_MIGRATED_ELEMENTS = ABSTRACT_ENUMERATION_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__NAME = ABSTRACT_ENUMERATION_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__INCOMING_TRACES = ABSTRACT_ENUMERATION_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__OUTGOING_TRACES = ABSTRACT_ENUMERATION_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__VISIBLE_IN_DOC = ABSTRACT_ENUMERATION_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__VISIBLE_IN_LM = ABSTRACT_ENUMERATION_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__SUMMARY = ABSTRACT_ENUMERATION_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__DESCRIPTION = ABSTRACT_ENUMERATION_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__REVIEW = ABSTRACT_ENUMERATION_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__OWNED_PROPERTY_VALUES = ABSTRACT_ENUMERATION_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_ENUMERATION_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__APPLIED_PROPERTY_VALUES = ABSTRACT_ENUMERATION_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_ENUMERATION_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_ENUMERATION_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__STATUS = ABSTRACT_ENUMERATION_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__FEATURES = ABSTRACT_ENUMERATION_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__ABSTRACT_TYPE = ABSTRACT_ENUMERATION_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__ABSTRACT = ABSTRACT_ENUMERATION_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__TYPE = ABSTRACT_ENUMERATION_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Enumeration Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__ENUMERATION_TYPE = ABSTRACT_ENUMERATION_VALUE__ENUMERATION_TYPE;

	/**
   * The feature id for the '<em><b>Domain Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL__DOMAIN_VALUE = ABSTRACT_ENUMERATION_VALUE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Enumeration Literal</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_LITERAL_FEATURE_COUNT = ABSTRACT_ENUMERATION_VALUE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.EnumerationReferenceImpl <em>Enumeration Reference</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.EnumerationReferenceImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getEnumerationReference()
   * @generated
   */
	int ENUMERATION_REFERENCE = 7;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__OWNED_EXTENSIONS = ABSTRACT_ENUMERATION_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__ID = ABSTRACT_ENUMERATION_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__SID = ABSTRACT_ENUMERATION_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__CONSTRAINTS = ABSTRACT_ENUMERATION_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__OWNED_CONSTRAINTS = ABSTRACT_ENUMERATION_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_ENUMERATION_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__NAME = ABSTRACT_ENUMERATION_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__INCOMING_TRACES = ABSTRACT_ENUMERATION_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__OUTGOING_TRACES = ABSTRACT_ENUMERATION_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__VISIBLE_IN_DOC = ABSTRACT_ENUMERATION_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__VISIBLE_IN_LM = ABSTRACT_ENUMERATION_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__SUMMARY = ABSTRACT_ENUMERATION_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__DESCRIPTION = ABSTRACT_ENUMERATION_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__REVIEW = ABSTRACT_ENUMERATION_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__OWNED_PROPERTY_VALUES = ABSTRACT_ENUMERATION_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_ENUMERATION_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__APPLIED_PROPERTY_VALUES = ABSTRACT_ENUMERATION_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_ENUMERATION_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_ENUMERATION_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__STATUS = ABSTRACT_ENUMERATION_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__FEATURES = ABSTRACT_ENUMERATION_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__ABSTRACT_TYPE = ABSTRACT_ENUMERATION_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__ABSTRACT = ABSTRACT_ENUMERATION_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__TYPE = ABSTRACT_ENUMERATION_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Enumeration Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__ENUMERATION_TYPE = ABSTRACT_ENUMERATION_VALUE__ENUMERATION_TYPE;

	/**
   * The feature id for the '<em><b>Referenced Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__REFERENCED_VALUE = ABSTRACT_ENUMERATION_VALUE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Referenced Property</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE__REFERENCED_PROPERTY = ABSTRACT_ENUMERATION_VALUE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Enumeration Reference</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_REFERENCE_FEATURE_COUNT = ABSTRACT_ENUMERATION_VALUE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractStringValueImpl <em>Abstract String Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.AbstractStringValueImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getAbstractStringValue()
   * @generated
   */
	int ABSTRACT_STRING_VALUE = 8;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__OWNED_EXTENSIONS = DATA_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__ID = DATA_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__SID = DATA_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__CONSTRAINTS = DATA_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__OWNED_CONSTRAINTS = DATA_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__OWNED_MIGRATED_ELEMENTS = DATA_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__NAME = DATA_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__INCOMING_TRACES = DATA_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__OUTGOING_TRACES = DATA_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__VISIBLE_IN_DOC = DATA_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__VISIBLE_IN_LM = DATA_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__SUMMARY = DATA_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__DESCRIPTION = DATA_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__REVIEW = DATA_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__OWNED_PROPERTY_VALUES = DATA_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = DATA_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__APPLIED_PROPERTY_VALUES = DATA_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__OWNED_PROPERTY_VALUE_GROUPS = DATA_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = DATA_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__STATUS = DATA_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__FEATURES = DATA_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__ABSTRACT_TYPE = DATA_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__ABSTRACT = DATA_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__TYPE = DATA_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>String Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE__STRING_TYPE = DATA_VALUE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract String Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_STRING_VALUE_FEATURE_COUNT = DATA_VALUE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.LiteralStringValueImpl <em>Literal String Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.LiteralStringValueImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getLiteralStringValue()
   * @generated
   */
	int LITERAL_STRING_VALUE = 9;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__OWNED_EXTENSIONS = ABSTRACT_STRING_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__ID = ABSTRACT_STRING_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__SID = ABSTRACT_STRING_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__CONSTRAINTS = ABSTRACT_STRING_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__OWNED_CONSTRAINTS = ABSTRACT_STRING_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_STRING_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__NAME = ABSTRACT_STRING_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__INCOMING_TRACES = ABSTRACT_STRING_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__OUTGOING_TRACES = ABSTRACT_STRING_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__VISIBLE_IN_DOC = ABSTRACT_STRING_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__VISIBLE_IN_LM = ABSTRACT_STRING_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__SUMMARY = ABSTRACT_STRING_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__DESCRIPTION = ABSTRACT_STRING_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__REVIEW = ABSTRACT_STRING_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__OWNED_PROPERTY_VALUES = ABSTRACT_STRING_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_STRING_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__APPLIED_PROPERTY_VALUES = ABSTRACT_STRING_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_STRING_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_STRING_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__STATUS = ABSTRACT_STRING_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__FEATURES = ABSTRACT_STRING_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__ABSTRACT_TYPE = ABSTRACT_STRING_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__ABSTRACT = ABSTRACT_STRING_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__TYPE = ABSTRACT_STRING_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>String Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__STRING_TYPE = ABSTRACT_STRING_VALUE__STRING_TYPE;

	/**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE__VALUE = ABSTRACT_STRING_VALUE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Literal String Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_STRING_VALUE_FEATURE_COUNT = ABSTRACT_STRING_VALUE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.StringReferenceImpl <em>String Reference</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.StringReferenceImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getStringReference()
   * @generated
   */
	int STRING_REFERENCE = 10;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__OWNED_EXTENSIONS = ABSTRACT_STRING_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__ID = ABSTRACT_STRING_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__SID = ABSTRACT_STRING_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__CONSTRAINTS = ABSTRACT_STRING_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__OWNED_CONSTRAINTS = ABSTRACT_STRING_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_STRING_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__NAME = ABSTRACT_STRING_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__INCOMING_TRACES = ABSTRACT_STRING_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__OUTGOING_TRACES = ABSTRACT_STRING_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__VISIBLE_IN_DOC = ABSTRACT_STRING_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__VISIBLE_IN_LM = ABSTRACT_STRING_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__SUMMARY = ABSTRACT_STRING_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__DESCRIPTION = ABSTRACT_STRING_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__REVIEW = ABSTRACT_STRING_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__OWNED_PROPERTY_VALUES = ABSTRACT_STRING_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_STRING_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__APPLIED_PROPERTY_VALUES = ABSTRACT_STRING_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_STRING_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_STRING_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__STATUS = ABSTRACT_STRING_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__FEATURES = ABSTRACT_STRING_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__ABSTRACT_TYPE = ABSTRACT_STRING_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__ABSTRACT = ABSTRACT_STRING_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__TYPE = ABSTRACT_STRING_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>String Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__STRING_TYPE = ABSTRACT_STRING_VALUE__STRING_TYPE;

	/**
   * The feature id for the '<em><b>Referenced Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__REFERENCED_VALUE = ABSTRACT_STRING_VALUE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Referenced Property</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE__REFERENCED_PROPERTY = ABSTRACT_STRING_VALUE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>String Reference</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_REFERENCE_FEATURE_COUNT = ABSTRACT_STRING_VALUE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.NumericValueImpl <em>Numeric Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.NumericValueImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getNumericValue()
   * @generated
   */
	int NUMERIC_VALUE = 11;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__OWNED_EXTENSIONS = DATA_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__ID = DATA_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__SID = DATA_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__CONSTRAINTS = DATA_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__OWNED_CONSTRAINTS = DATA_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__OWNED_MIGRATED_ELEMENTS = DATA_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__NAME = DATA_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__INCOMING_TRACES = DATA_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__OUTGOING_TRACES = DATA_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__VISIBLE_IN_DOC = DATA_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__VISIBLE_IN_LM = DATA_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__SUMMARY = DATA_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__DESCRIPTION = DATA_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__REVIEW = DATA_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__OWNED_PROPERTY_VALUES = DATA_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = DATA_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__APPLIED_PROPERTY_VALUES = DATA_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__OWNED_PROPERTY_VALUE_GROUPS = DATA_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = DATA_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__STATUS = DATA_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__FEATURES = DATA_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__ABSTRACT_TYPE = DATA_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__ABSTRACT = DATA_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__TYPE = DATA_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Unit</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__UNIT = DATA_VALUE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Numeric Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE__NUMERIC_TYPE = DATA_VALUE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Numeric Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_VALUE_FEATURE_COUNT = DATA_VALUE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.LiteralNumericValueImpl <em>Literal Numeric Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.LiteralNumericValueImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getLiteralNumericValue()
   * @generated
   */
	int LITERAL_NUMERIC_VALUE = 12;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__OWNED_EXTENSIONS = NUMERIC_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__ID = NUMERIC_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__SID = NUMERIC_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__CONSTRAINTS = NUMERIC_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__OWNED_CONSTRAINTS = NUMERIC_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__OWNED_MIGRATED_ELEMENTS = NUMERIC_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__NAME = NUMERIC_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__INCOMING_TRACES = NUMERIC_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__OUTGOING_TRACES = NUMERIC_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__VISIBLE_IN_DOC = NUMERIC_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__VISIBLE_IN_LM = NUMERIC_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__SUMMARY = NUMERIC_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__DESCRIPTION = NUMERIC_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__REVIEW = NUMERIC_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__OWNED_PROPERTY_VALUES = NUMERIC_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = NUMERIC_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__APPLIED_PROPERTY_VALUES = NUMERIC_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__OWNED_PROPERTY_VALUE_GROUPS = NUMERIC_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = NUMERIC_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__STATUS = NUMERIC_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__FEATURES = NUMERIC_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__ABSTRACT_TYPE = NUMERIC_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__ABSTRACT = NUMERIC_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__TYPE = NUMERIC_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Unit</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__UNIT = NUMERIC_VALUE__UNIT;

	/**
   * The feature id for the '<em><b>Numeric Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__NUMERIC_TYPE = NUMERIC_VALUE__NUMERIC_TYPE;

	/**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE__VALUE = NUMERIC_VALUE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Literal Numeric Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LITERAL_NUMERIC_VALUE_FEATURE_COUNT = NUMERIC_VALUE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.NumericReferenceImpl <em>Numeric Reference</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.NumericReferenceImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getNumericReference()
   * @generated
   */
	int NUMERIC_REFERENCE = 13;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__OWNED_EXTENSIONS = NUMERIC_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__ID = NUMERIC_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__SID = NUMERIC_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__CONSTRAINTS = NUMERIC_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__OWNED_CONSTRAINTS = NUMERIC_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__OWNED_MIGRATED_ELEMENTS = NUMERIC_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__NAME = NUMERIC_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__INCOMING_TRACES = NUMERIC_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__OUTGOING_TRACES = NUMERIC_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__VISIBLE_IN_DOC = NUMERIC_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__VISIBLE_IN_LM = NUMERIC_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__SUMMARY = NUMERIC_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__DESCRIPTION = NUMERIC_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__REVIEW = NUMERIC_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__OWNED_PROPERTY_VALUES = NUMERIC_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__OWNED_ENUMERATION_PROPERTY_TYPES = NUMERIC_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__APPLIED_PROPERTY_VALUES = NUMERIC_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__OWNED_PROPERTY_VALUE_GROUPS = NUMERIC_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__APPLIED_PROPERTY_VALUE_GROUPS = NUMERIC_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__STATUS = NUMERIC_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__FEATURES = NUMERIC_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__ABSTRACT_TYPE = NUMERIC_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__ABSTRACT = NUMERIC_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__TYPE = NUMERIC_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Unit</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__UNIT = NUMERIC_VALUE__UNIT;

	/**
   * The feature id for the '<em><b>Numeric Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__NUMERIC_TYPE = NUMERIC_VALUE__NUMERIC_TYPE;

	/**
   * The feature id for the '<em><b>Referenced Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__REFERENCED_VALUE = NUMERIC_VALUE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Referenced Property</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE__REFERENCED_PROPERTY = NUMERIC_VALUE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Numeric Reference</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NUMERIC_REFERENCE_FEATURE_COUNT = NUMERIC_VALUE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractComplexValueImpl <em>Abstract Complex Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.AbstractComplexValueImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getAbstractComplexValue()
   * @generated
   */
	int ABSTRACT_COMPLEX_VALUE = 14;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__OWNED_EXTENSIONS = DATA_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__ID = DATA_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__SID = DATA_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__CONSTRAINTS = DATA_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__OWNED_CONSTRAINTS = DATA_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__OWNED_MIGRATED_ELEMENTS = DATA_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__NAME = DATA_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__INCOMING_TRACES = DATA_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__OUTGOING_TRACES = DATA_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__VISIBLE_IN_DOC = DATA_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__VISIBLE_IN_LM = DATA_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__SUMMARY = DATA_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__DESCRIPTION = DATA_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__REVIEW = DATA_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__OWNED_PROPERTY_VALUES = DATA_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = DATA_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__APPLIED_PROPERTY_VALUES = DATA_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__OWNED_PROPERTY_VALUE_GROUPS = DATA_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = DATA_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__STATUS = DATA_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__FEATURES = DATA_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__ABSTRACT_TYPE = DATA_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__ABSTRACT = DATA_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__TYPE = DATA_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Complex Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE__COMPLEX_TYPE = DATA_VALUE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Complex Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_COMPLEX_VALUE_FEATURE_COUNT = DATA_VALUE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.ComplexValueImpl <em>Complex Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.ComplexValueImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getComplexValue()
   * @generated
   */
	int COMPLEX_VALUE = 15;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__OWNED_EXTENSIONS = ABSTRACT_COMPLEX_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__ID = ABSTRACT_COMPLEX_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__SID = ABSTRACT_COMPLEX_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__CONSTRAINTS = ABSTRACT_COMPLEX_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__OWNED_CONSTRAINTS = ABSTRACT_COMPLEX_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_COMPLEX_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__NAME = ABSTRACT_COMPLEX_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__INCOMING_TRACES = ABSTRACT_COMPLEX_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__OUTGOING_TRACES = ABSTRACT_COMPLEX_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__VISIBLE_IN_DOC = ABSTRACT_COMPLEX_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__VISIBLE_IN_LM = ABSTRACT_COMPLEX_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__SUMMARY = ABSTRACT_COMPLEX_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__DESCRIPTION = ABSTRACT_COMPLEX_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__REVIEW = ABSTRACT_COMPLEX_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__OWNED_PROPERTY_VALUES = ABSTRACT_COMPLEX_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_COMPLEX_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__APPLIED_PROPERTY_VALUES = ABSTRACT_COMPLEX_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_COMPLEX_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_COMPLEX_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__STATUS = ABSTRACT_COMPLEX_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__FEATURES = ABSTRACT_COMPLEX_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__ABSTRACT_TYPE = ABSTRACT_COMPLEX_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__ABSTRACT = ABSTRACT_COMPLEX_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__TYPE = ABSTRACT_COMPLEX_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Complex Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__COMPLEX_TYPE = ABSTRACT_COMPLEX_VALUE__COMPLEX_TYPE;

	/**
   * The feature id for the '<em><b>Owned Parts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE__OWNED_PARTS = ABSTRACT_COMPLEX_VALUE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Complex Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_FEATURE_COUNT = ABSTRACT_COMPLEX_VALUE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.ComplexValueReferenceImpl <em>Complex Value Reference</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.ComplexValueReferenceImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getComplexValueReference()
   * @generated
   */
	int COMPLEX_VALUE_REFERENCE = 16;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__OWNED_EXTENSIONS = ABSTRACT_COMPLEX_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__ID = ABSTRACT_COMPLEX_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__SID = ABSTRACT_COMPLEX_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__CONSTRAINTS = ABSTRACT_COMPLEX_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__OWNED_CONSTRAINTS = ABSTRACT_COMPLEX_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_COMPLEX_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__NAME = ABSTRACT_COMPLEX_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__INCOMING_TRACES = ABSTRACT_COMPLEX_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__OUTGOING_TRACES = ABSTRACT_COMPLEX_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__VISIBLE_IN_DOC = ABSTRACT_COMPLEX_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__VISIBLE_IN_LM = ABSTRACT_COMPLEX_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__SUMMARY = ABSTRACT_COMPLEX_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__DESCRIPTION = ABSTRACT_COMPLEX_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__REVIEW = ABSTRACT_COMPLEX_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__OWNED_PROPERTY_VALUES = ABSTRACT_COMPLEX_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_COMPLEX_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__APPLIED_PROPERTY_VALUES = ABSTRACT_COMPLEX_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_COMPLEX_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_COMPLEX_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__STATUS = ABSTRACT_COMPLEX_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__FEATURES = ABSTRACT_COMPLEX_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__ABSTRACT_TYPE = ABSTRACT_COMPLEX_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__ABSTRACT = ABSTRACT_COMPLEX_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__TYPE = ABSTRACT_COMPLEX_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Complex Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__COMPLEX_TYPE = ABSTRACT_COMPLEX_VALUE__COMPLEX_TYPE;

	/**
   * The feature id for the '<em><b>Referenced Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__REFERENCED_VALUE = ABSTRACT_COMPLEX_VALUE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Referenced Property</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE__REFERENCED_PROPERTY = ABSTRACT_COMPLEX_VALUE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Complex Value Reference</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPLEX_VALUE_REFERENCE_FEATURE_COUNT = ABSTRACT_COMPLEX_VALUE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.ValuePartImpl <em>Value Part</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.ValuePartImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getValuePart()
   * @generated
   */
	int VALUE_PART = 17;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Referenced Property</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__REFERENCED_PROPERTY = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART__OWNED_VALUE = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Value Part</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int VALUE_PART_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractExpressionValueImpl <em>Abstract Expression Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.AbstractExpressionValueImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getAbstractExpressionValue()
   * @generated
   */
	int ABSTRACT_EXPRESSION_VALUE = 18;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__OWNED_EXTENSIONS = ABSTRACT_BOOLEAN_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__ID = ABSTRACT_BOOLEAN_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__SID = ABSTRACT_BOOLEAN_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__CONSTRAINTS = ABSTRACT_BOOLEAN_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__OWNED_CONSTRAINTS = ABSTRACT_BOOLEAN_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_BOOLEAN_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__NAME = ABSTRACT_BOOLEAN_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__INCOMING_TRACES = ABSTRACT_BOOLEAN_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__OUTGOING_TRACES = ABSTRACT_BOOLEAN_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__VISIBLE_IN_DOC = ABSTRACT_BOOLEAN_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__VISIBLE_IN_LM = ABSTRACT_BOOLEAN_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__SUMMARY = ABSTRACT_BOOLEAN_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__DESCRIPTION = ABSTRACT_BOOLEAN_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__REVIEW = ABSTRACT_BOOLEAN_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__OWNED_PROPERTY_VALUES = ABSTRACT_BOOLEAN_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_BOOLEAN_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__APPLIED_PROPERTY_VALUES = ABSTRACT_BOOLEAN_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_BOOLEAN_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_BOOLEAN_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__STATUS = ABSTRACT_BOOLEAN_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__FEATURES = ABSTRACT_BOOLEAN_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__ABSTRACT_TYPE = ABSTRACT_BOOLEAN_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__ABSTRACT = ABSTRACT_BOOLEAN_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__TYPE = ABSTRACT_BOOLEAN_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Boolean Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__BOOLEAN_TYPE = ABSTRACT_BOOLEAN_VALUE__BOOLEAN_TYPE;

	/**
   * The feature id for the '<em><b>Complex Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__COMPLEX_TYPE = ABSTRACT_BOOLEAN_VALUE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Enumeration Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__ENUMERATION_TYPE = ABSTRACT_BOOLEAN_VALUE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Unit</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__UNIT = ABSTRACT_BOOLEAN_VALUE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Numeric Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__NUMERIC_TYPE = ABSTRACT_BOOLEAN_VALUE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>String Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__STRING_TYPE = ABSTRACT_BOOLEAN_VALUE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Expression</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__EXPRESSION = ABSTRACT_BOOLEAN_VALUE_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Unparsed Expression</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__UNPARSED_EXPRESSION = ABSTRACT_BOOLEAN_VALUE_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Expression Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE__EXPRESSION_TYPE = ABSTRACT_BOOLEAN_VALUE_FEATURE_COUNT + 7;

	/**
   * The number of structural features of the '<em>Abstract Expression Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXPRESSION_VALUE_FEATURE_COUNT = ABSTRACT_BOOLEAN_VALUE_FEATURE_COUNT + 8;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.BinaryExpressionImpl <em>Binary Expression</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.BinaryExpressionImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getBinaryExpression()
   * @generated
   */
	int BINARY_EXPRESSION = 19;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__OWNED_EXTENSIONS = ABSTRACT_EXPRESSION_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__ID = ABSTRACT_EXPRESSION_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__SID = ABSTRACT_EXPRESSION_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__CONSTRAINTS = ABSTRACT_EXPRESSION_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__OWNED_CONSTRAINTS = ABSTRACT_EXPRESSION_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__OWNED_MIGRATED_ELEMENTS = ABSTRACT_EXPRESSION_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__NAME = ABSTRACT_EXPRESSION_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__INCOMING_TRACES = ABSTRACT_EXPRESSION_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__OUTGOING_TRACES = ABSTRACT_EXPRESSION_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__VISIBLE_IN_DOC = ABSTRACT_EXPRESSION_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__VISIBLE_IN_LM = ABSTRACT_EXPRESSION_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__SUMMARY = ABSTRACT_EXPRESSION_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__DESCRIPTION = ABSTRACT_EXPRESSION_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__REVIEW = ABSTRACT_EXPRESSION_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__OWNED_PROPERTY_VALUES = ABSTRACT_EXPRESSION_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_EXPRESSION_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__APPLIED_PROPERTY_VALUES = ABSTRACT_EXPRESSION_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_EXPRESSION_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_EXPRESSION_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__STATUS = ABSTRACT_EXPRESSION_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__FEATURES = ABSTRACT_EXPRESSION_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__ABSTRACT_TYPE = ABSTRACT_EXPRESSION_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__ABSTRACT = ABSTRACT_EXPRESSION_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__TYPE = ABSTRACT_EXPRESSION_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Boolean Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__BOOLEAN_TYPE = ABSTRACT_EXPRESSION_VALUE__BOOLEAN_TYPE;

	/**
   * The feature id for the '<em><b>Complex Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__COMPLEX_TYPE = ABSTRACT_EXPRESSION_VALUE__COMPLEX_TYPE;

	/**
   * The feature id for the '<em><b>Enumeration Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__ENUMERATION_TYPE = ABSTRACT_EXPRESSION_VALUE__ENUMERATION_TYPE;

	/**
   * The feature id for the '<em><b>Unit</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__UNIT = ABSTRACT_EXPRESSION_VALUE__UNIT;

	/**
   * The feature id for the '<em><b>Numeric Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__NUMERIC_TYPE = ABSTRACT_EXPRESSION_VALUE__NUMERIC_TYPE;

	/**
   * The feature id for the '<em><b>String Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__STRING_TYPE = ABSTRACT_EXPRESSION_VALUE__STRING_TYPE;

	/**
   * The feature id for the '<em><b>Expression</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__EXPRESSION = ABSTRACT_EXPRESSION_VALUE__EXPRESSION;

	/**
   * The feature id for the '<em><b>Unparsed Expression</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__UNPARSED_EXPRESSION = ABSTRACT_EXPRESSION_VALUE__UNPARSED_EXPRESSION;

	/**
   * The feature id for the '<em><b>Expression Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__EXPRESSION_TYPE = ABSTRACT_EXPRESSION_VALUE__EXPRESSION_TYPE;

	/**
   * The feature id for the '<em><b>Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__OPERATOR = ABSTRACT_EXPRESSION_VALUE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Left Operand</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__OWNED_LEFT_OPERAND = ABSTRACT_EXPRESSION_VALUE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Right Operand</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION__OWNED_RIGHT_OPERAND = ABSTRACT_EXPRESSION_VALUE_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Binary Expression</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BINARY_EXPRESSION_FEATURE_COUNT = ABSTRACT_EXPRESSION_VALUE_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.UnaryExpressionImpl <em>Unary Expression</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.UnaryExpressionImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getUnaryExpression()
   * @generated
   */
	int UNARY_EXPRESSION = 20;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__OWNED_EXTENSIONS = ABSTRACT_EXPRESSION_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__ID = ABSTRACT_EXPRESSION_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__SID = ABSTRACT_EXPRESSION_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__CONSTRAINTS = ABSTRACT_EXPRESSION_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__OWNED_CONSTRAINTS = ABSTRACT_EXPRESSION_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__OWNED_MIGRATED_ELEMENTS = ABSTRACT_EXPRESSION_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__NAME = ABSTRACT_EXPRESSION_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__INCOMING_TRACES = ABSTRACT_EXPRESSION_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__OUTGOING_TRACES = ABSTRACT_EXPRESSION_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__VISIBLE_IN_DOC = ABSTRACT_EXPRESSION_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__VISIBLE_IN_LM = ABSTRACT_EXPRESSION_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__SUMMARY = ABSTRACT_EXPRESSION_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__DESCRIPTION = ABSTRACT_EXPRESSION_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__REVIEW = ABSTRACT_EXPRESSION_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__OWNED_PROPERTY_VALUES = ABSTRACT_EXPRESSION_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_EXPRESSION_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__APPLIED_PROPERTY_VALUES = ABSTRACT_EXPRESSION_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_EXPRESSION_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_EXPRESSION_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__STATUS = ABSTRACT_EXPRESSION_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__FEATURES = ABSTRACT_EXPRESSION_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__ABSTRACT_TYPE = ABSTRACT_EXPRESSION_VALUE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__ABSTRACT = ABSTRACT_EXPRESSION_VALUE__ABSTRACT;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__TYPE = ABSTRACT_EXPRESSION_VALUE__TYPE;

	/**
   * The feature id for the '<em><b>Boolean Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__BOOLEAN_TYPE = ABSTRACT_EXPRESSION_VALUE__BOOLEAN_TYPE;

	/**
   * The feature id for the '<em><b>Complex Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__COMPLEX_TYPE = ABSTRACT_EXPRESSION_VALUE__COMPLEX_TYPE;

	/**
   * The feature id for the '<em><b>Enumeration Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__ENUMERATION_TYPE = ABSTRACT_EXPRESSION_VALUE__ENUMERATION_TYPE;

	/**
   * The feature id for the '<em><b>Unit</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__UNIT = ABSTRACT_EXPRESSION_VALUE__UNIT;

	/**
   * The feature id for the '<em><b>Numeric Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__NUMERIC_TYPE = ABSTRACT_EXPRESSION_VALUE__NUMERIC_TYPE;

	/**
   * The feature id for the '<em><b>String Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__STRING_TYPE = ABSTRACT_EXPRESSION_VALUE__STRING_TYPE;

	/**
   * The feature id for the '<em><b>Expression</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__EXPRESSION = ABSTRACT_EXPRESSION_VALUE__EXPRESSION;

	/**
   * The feature id for the '<em><b>Unparsed Expression</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__UNPARSED_EXPRESSION = ABSTRACT_EXPRESSION_VALUE__UNPARSED_EXPRESSION;

	/**
   * The feature id for the '<em><b>Expression Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__EXPRESSION_TYPE = ABSTRACT_EXPRESSION_VALUE__EXPRESSION_TYPE;

	/**
   * The feature id for the '<em><b>Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__OPERATOR = ABSTRACT_EXPRESSION_VALUE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Operand</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION__OWNED_OPERAND = ABSTRACT_EXPRESSION_VALUE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Unary Expression</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int UNARY_EXPRESSION_FEATURE_COUNT = ABSTRACT_EXPRESSION_VALUE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.OpaqueExpressionImpl <em>Opaque Expression</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.impl.OpaqueExpressionImpl
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getOpaqueExpression()
   * @generated
   */
	int OPAQUE_EXPRESSION = 21;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__NAME = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__ABSTRACT_TYPE = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Bodies</b></em>' attribute list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__BODIES = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Languages</b></em>' attribute list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION__LANGUAGES = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The number of structural features of the '<em>Opaque Expression</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPAQUE_EXPRESSION_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.BinaryOperator <em>Binary Operator</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.BinaryOperator
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getBinaryOperator()
   * @generated
   */
	int BINARY_OPERATOR = 22;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.datavalue.UnaryOperator <em>Unary Operator</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.datavalue.UnaryOperator
   * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getUnaryOperator()
   * @generated
   */
	int UNARY_OPERATOR = 23;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.DataValue <em>Data Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.DataValue
   * @generated
   */
	EClass getDataValue();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.datavalue.DataValue#isAbstract <em>Abstract</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Abstract</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.DataValue#isAbstract()
   * @see #getDataValue()
   * @generated
   */
	EAttribute getDataValue_Abstract();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.DataValue#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.DataValue#getType()
   * @see #getDataValue()
   * @generated
   */
	EReference getDataValue_Type();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.DataValueContainer <em>Data Value Container</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Value Container</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.DataValueContainer
   * @generated
   */
	EClass getDataValueContainer();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.datavalue.DataValueContainer#getOwnedDataValues <em>Owned Data Values</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Data Values</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.DataValueContainer#getOwnedDataValues()
   * @see #getDataValueContainer()
   * @generated
   */
	EReference getDataValueContainer_OwnedDataValues();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue <em>Abstract Boolean Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Boolean Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue
   * @generated
   */
	EClass getAbstractBooleanValue();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue#getBooleanType <em>Boolean Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Boolean Type</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue#getBooleanType()
   * @see #getAbstractBooleanValue()
   * @generated
   */
	EReference getAbstractBooleanValue_BooleanType();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue <em>Literal Boolean Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Literal Boolean Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue
   * @generated
   */
	EClass getLiteralBooleanValue();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue#isValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue#isValue()
   * @see #getLiteralBooleanValue()
   * @generated
   */
	EAttribute getLiteralBooleanValue_Value();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.BooleanReference <em>Boolean Reference</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Boolean Reference</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.BooleanReference
   * @generated
   */
	EClass getBooleanReference();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.BooleanReference#getReferencedValue <em>Referenced Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.BooleanReference#getReferencedValue()
   * @see #getBooleanReference()
   * @generated
   */
	EReference getBooleanReference_ReferencedValue();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.BooleanReference#getReferencedProperty <em>Referenced Property</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Property</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.BooleanReference#getReferencedProperty()
   * @see #getBooleanReference()
   * @generated
   */
	EReference getBooleanReference_ReferencedProperty();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue <em>Abstract Enumeration Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Enumeration Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue
   * @generated
   */
	EClass getAbstractEnumerationValue();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue#getEnumerationType <em>Enumeration Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Enumeration Type</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue#getEnumerationType()
   * @see #getAbstractEnumerationValue()
   * @generated
   */
	EReference getAbstractEnumerationValue_EnumerationType();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral <em>Enumeration Literal</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enumeration Literal</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral
   * @generated
   */
	EClass getEnumerationLiteral();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral#getDomainValue <em>Domain Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Domain Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral#getDomainValue()
   * @see #getEnumerationLiteral()
   * @generated
   */
	EReference getEnumerationLiteral_DomainValue();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.EnumerationReference <em>Enumeration Reference</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enumeration Reference</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.EnumerationReference
   * @generated
   */
	EClass getEnumerationReference();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.EnumerationReference#getReferencedValue <em>Referenced Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.EnumerationReference#getReferencedValue()
   * @see #getEnumerationReference()
   * @generated
   */
	EReference getEnumerationReference_ReferencedValue();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.EnumerationReference#getReferencedProperty <em>Referenced Property</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Property</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.EnumerationReference#getReferencedProperty()
   * @see #getEnumerationReference()
   * @generated
   */
	EReference getEnumerationReference_ReferencedProperty();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.AbstractStringValue <em>Abstract String Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract String Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.AbstractStringValue
   * @generated
   */
	EClass getAbstractStringValue();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.AbstractStringValue#getStringType <em>String Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>String Type</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.AbstractStringValue#getStringType()
   * @see #getAbstractStringValue()
   * @generated
   */
	EReference getAbstractStringValue_StringType();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.LiteralStringValue <em>Literal String Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Literal String Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.LiteralStringValue
   * @generated
   */
	EClass getLiteralStringValue();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.datavalue.LiteralStringValue#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.LiteralStringValue#getValue()
   * @see #getLiteralStringValue()
   * @generated
   */
	EAttribute getLiteralStringValue_Value();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.StringReference <em>String Reference</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Reference</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.StringReference
   * @generated
   */
	EClass getStringReference();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.StringReference#getReferencedValue <em>Referenced Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.StringReference#getReferencedValue()
   * @see #getStringReference()
   * @generated
   */
	EReference getStringReference_ReferencedValue();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.StringReference#getReferencedProperty <em>Referenced Property</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Property</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.StringReference#getReferencedProperty()
   * @see #getStringReference()
   * @generated
   */
	EReference getStringReference_ReferencedProperty();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.NumericValue <em>Numeric Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Numeric Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.NumericValue
   * @generated
   */
	EClass getNumericValue();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.NumericValue#getUnit <em>Unit</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Unit</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.NumericValue#getUnit()
   * @see #getNumericValue()
   * @generated
   */
	EReference getNumericValue_Unit();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.NumericValue#getNumericType <em>Numeric Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Numeric Type</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.NumericValue#getNumericType()
   * @see #getNumericValue()
   * @generated
   */
	EReference getNumericValue_NumericType();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue <em>Literal Numeric Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Literal Numeric Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue
   * @generated
   */
	EClass getLiteralNumericValue();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue#getValue()
   * @see #getLiteralNumericValue()
   * @generated
   */
	EAttribute getLiteralNumericValue_Value();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.NumericReference <em>Numeric Reference</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Numeric Reference</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.NumericReference
   * @generated
   */
	EClass getNumericReference();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.NumericReference#getReferencedValue <em>Referenced Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.NumericReference#getReferencedValue()
   * @see #getNumericReference()
   * @generated
   */
	EReference getNumericReference_ReferencedValue();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.NumericReference#getReferencedProperty <em>Referenced Property</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Property</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.NumericReference#getReferencedProperty()
   * @see #getNumericReference()
   * @generated
   */
	EReference getNumericReference_ReferencedProperty();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.AbstractComplexValue <em>Abstract Complex Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Complex Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.AbstractComplexValue
   * @generated
   */
	EClass getAbstractComplexValue();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.AbstractComplexValue#getComplexType <em>Complex Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Complex Type</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.AbstractComplexValue#getComplexType()
   * @see #getAbstractComplexValue()
   * @generated
   */
	EReference getAbstractComplexValue_ComplexType();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.ComplexValue <em>Complex Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Complex Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.ComplexValue
   * @generated
   */
	EClass getComplexValue();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.datavalue.ComplexValue#getOwnedParts <em>Owned Parts</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Parts</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.ComplexValue#getOwnedParts()
   * @see #getComplexValue()
   * @generated
   */
	EReference getComplexValue_OwnedParts();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.ComplexValueReference <em>Complex Value Reference</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Complex Value Reference</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.ComplexValueReference
   * @generated
   */
	EClass getComplexValueReference();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.ComplexValueReference#getReferencedValue <em>Referenced Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.ComplexValueReference#getReferencedValue()
   * @see #getComplexValueReference()
   * @generated
   */
	EReference getComplexValueReference_ReferencedValue();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.ComplexValueReference#getReferencedProperty <em>Referenced Property</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Property</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.ComplexValueReference#getReferencedProperty()
   * @see #getComplexValueReference()
   * @generated
   */
	EReference getComplexValueReference_ReferencedProperty();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.ValuePart <em>Value Part</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Value Part</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.ValuePart
   * @generated
   */
	EClass getValuePart();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.ValuePart#getReferencedProperty <em>Referenced Property</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Property</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.ValuePart#getReferencedProperty()
   * @see #getValuePart()
   * @generated
   */
	EReference getValuePart_ReferencedProperty();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datavalue.ValuePart#getOwnedValue <em>Owned Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.ValuePart#getOwnedValue()
   * @see #getValuePart()
   * @generated
   */
	EReference getValuePart_OwnedValue();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue <em>Abstract Expression Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Expression Value</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue
   * @generated
   */
	EClass getAbstractExpressionValue();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Expression</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue#getExpression()
   * @see #getAbstractExpressionValue()
   * @generated
   */
	EAttribute getAbstractExpressionValue_Expression();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue#getUnparsedExpression <em>Unparsed Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Unparsed Expression</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue#getUnparsedExpression()
   * @see #getAbstractExpressionValue()
   * @generated
   */
	EAttribute getAbstractExpressionValue_UnparsedExpression();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue#getExpressionType <em>Expression Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Expression Type</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue#getExpressionType()
   * @see #getAbstractExpressionValue()
   * @generated
   */
	EReference getAbstractExpressionValue_ExpressionType();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.BinaryExpression <em>Binary Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Binary Expression</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.BinaryExpression
   * @generated
   */
	EClass getBinaryExpression();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.datavalue.BinaryExpression#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operator</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.BinaryExpression#getOperator()
   * @see #getBinaryExpression()
   * @generated
   */
	EAttribute getBinaryExpression_Operator();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datavalue.BinaryExpression#getOwnedLeftOperand <em>Owned Left Operand</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Left Operand</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.BinaryExpression#getOwnedLeftOperand()
   * @see #getBinaryExpression()
   * @generated
   */
	EReference getBinaryExpression_OwnedLeftOperand();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datavalue.BinaryExpression#getOwnedRightOperand <em>Owned Right Operand</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Right Operand</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.BinaryExpression#getOwnedRightOperand()
   * @see #getBinaryExpression()
   * @generated
   */
	EReference getBinaryExpression_OwnedRightOperand();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.UnaryExpression <em>Unary Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unary Expression</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.UnaryExpression
   * @generated
   */
	EClass getUnaryExpression();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.datavalue.UnaryExpression#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operator</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.UnaryExpression#getOperator()
   * @see #getUnaryExpression()
   * @generated
   */
	EAttribute getUnaryExpression_Operator();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.information.datavalue.UnaryExpression#getOwnedOperand <em>Owned Operand</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Operand</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.UnaryExpression#getOwnedOperand()
   * @see #getUnaryExpression()
   * @generated
   */
	EReference getUnaryExpression_OwnedOperand();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.datavalue.OpaqueExpression <em>Opaque Expression</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Opaque Expression</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.OpaqueExpression
   * @generated
   */
	EClass getOpaqueExpression();

	/**
   * Returns the meta object for the attribute list '{@link org.polarsys.capella.core.data.information.datavalue.OpaqueExpression#getBodies <em>Bodies</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Bodies</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.OpaqueExpression#getBodies()
   * @see #getOpaqueExpression()
   * @generated
   */
	EAttribute getOpaqueExpression_Bodies();

	/**
   * Returns the meta object for the attribute list '{@link org.polarsys.capella.core.data.information.datavalue.OpaqueExpression#getLanguages <em>Languages</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Languages</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.OpaqueExpression#getLanguages()
   * @see #getOpaqueExpression()
   * @generated
   */
	EAttribute getOpaqueExpression_Languages();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.information.datavalue.BinaryOperator <em>Binary Operator</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Binary Operator</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.BinaryOperator
   * @generated
   */
	EEnum getBinaryOperator();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.information.datavalue.UnaryOperator <em>Unary Operator</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Unary Operator</em>'.
   * @see org.polarsys.capella.core.data.information.datavalue.UnaryOperator
   * @generated
   */
	EEnum getUnaryOperator();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	DatavalueFactory getDatavalueFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.DataValueImpl <em>Data Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DataValueImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getDataValue()
     * @generated
     */
		EClass DATA_VALUE = eINSTANCE.getDataValue();

		/**
     * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute DATA_VALUE__ABSTRACT = eINSTANCE.getDataValue_Abstract();

		/**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_VALUE__TYPE = eINSTANCE.getDataValue_Type();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.DataValueContainerImpl <em>Data Value Container</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DataValueContainerImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getDataValueContainer()
     * @generated
     */
		EClass DATA_VALUE_CONTAINER = eINSTANCE.getDataValueContainer();

		/**
     * The meta object literal for the '<em><b>Owned Data Values</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DATA_VALUE_CONTAINER__OWNED_DATA_VALUES = eINSTANCE.getDataValueContainer_OwnedDataValues();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractBooleanValueImpl <em>Abstract Boolean Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.AbstractBooleanValueImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getAbstractBooleanValue()
     * @generated
     */
		EClass ABSTRACT_BOOLEAN_VALUE = eINSTANCE.getAbstractBooleanValue();

		/**
     * The meta object literal for the '<em><b>Boolean Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_BOOLEAN_VALUE__BOOLEAN_TYPE = eINSTANCE.getAbstractBooleanValue_BooleanType();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.LiteralBooleanValueImpl <em>Literal Boolean Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.LiteralBooleanValueImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getLiteralBooleanValue()
     * @generated
     */
		EClass LITERAL_BOOLEAN_VALUE = eINSTANCE.getLiteralBooleanValue();

		/**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute LITERAL_BOOLEAN_VALUE__VALUE = eINSTANCE.getLiteralBooleanValue_Value();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.BooleanReferenceImpl <em>Boolean Reference</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.BooleanReferenceImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getBooleanReference()
     * @generated
     */
		EClass BOOLEAN_REFERENCE = eINSTANCE.getBooleanReference();

		/**
     * The meta object literal for the '<em><b>Referenced Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BOOLEAN_REFERENCE__REFERENCED_VALUE = eINSTANCE.getBooleanReference_ReferencedValue();

		/**
     * The meta object literal for the '<em><b>Referenced Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BOOLEAN_REFERENCE__REFERENCED_PROPERTY = eINSTANCE.getBooleanReference_ReferencedProperty();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractEnumerationValueImpl <em>Abstract Enumeration Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.AbstractEnumerationValueImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getAbstractEnumerationValue()
     * @generated
     */
		EClass ABSTRACT_ENUMERATION_VALUE = eINSTANCE.getAbstractEnumerationValue();

		/**
     * The meta object literal for the '<em><b>Enumeration Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_ENUMERATION_VALUE__ENUMERATION_TYPE = eINSTANCE.getAbstractEnumerationValue_EnumerationType();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.EnumerationLiteralImpl <em>Enumeration Literal</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.EnumerationLiteralImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getEnumerationLiteral()
     * @generated
     */
		EClass ENUMERATION_LITERAL = eINSTANCE.getEnumerationLiteral();

		/**
     * The meta object literal for the '<em><b>Domain Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENUMERATION_LITERAL__DOMAIN_VALUE = eINSTANCE.getEnumerationLiteral_DomainValue();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.EnumerationReferenceImpl <em>Enumeration Reference</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.EnumerationReferenceImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getEnumerationReference()
     * @generated
     */
		EClass ENUMERATION_REFERENCE = eINSTANCE.getEnumerationReference();

		/**
     * The meta object literal for the '<em><b>Referenced Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENUMERATION_REFERENCE__REFERENCED_VALUE = eINSTANCE.getEnumerationReference_ReferencedValue();

		/**
     * The meta object literal for the '<em><b>Referenced Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENUMERATION_REFERENCE__REFERENCED_PROPERTY = eINSTANCE.getEnumerationReference_ReferencedProperty();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractStringValueImpl <em>Abstract String Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.AbstractStringValueImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getAbstractStringValue()
     * @generated
     */
		EClass ABSTRACT_STRING_VALUE = eINSTANCE.getAbstractStringValue();

		/**
     * The meta object literal for the '<em><b>String Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_STRING_VALUE__STRING_TYPE = eINSTANCE.getAbstractStringValue_StringType();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.LiteralStringValueImpl <em>Literal String Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.LiteralStringValueImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getLiteralStringValue()
     * @generated
     */
		EClass LITERAL_STRING_VALUE = eINSTANCE.getLiteralStringValue();

		/**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute LITERAL_STRING_VALUE__VALUE = eINSTANCE.getLiteralStringValue_Value();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.StringReferenceImpl <em>String Reference</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.StringReferenceImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getStringReference()
     * @generated
     */
		EClass STRING_REFERENCE = eINSTANCE.getStringReference();

		/**
     * The meta object literal for the '<em><b>Referenced Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STRING_REFERENCE__REFERENCED_VALUE = eINSTANCE.getStringReference_ReferencedValue();

		/**
     * The meta object literal for the '<em><b>Referenced Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STRING_REFERENCE__REFERENCED_PROPERTY = eINSTANCE.getStringReference_ReferencedProperty();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.NumericValueImpl <em>Numeric Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.NumericValueImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getNumericValue()
     * @generated
     */
		EClass NUMERIC_VALUE = eINSTANCE.getNumericValue();

		/**
     * The meta object literal for the '<em><b>Unit</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference NUMERIC_VALUE__UNIT = eINSTANCE.getNumericValue_Unit();

		/**
     * The meta object literal for the '<em><b>Numeric Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference NUMERIC_VALUE__NUMERIC_TYPE = eINSTANCE.getNumericValue_NumericType();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.LiteralNumericValueImpl <em>Literal Numeric Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.LiteralNumericValueImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getLiteralNumericValue()
     * @generated
     */
		EClass LITERAL_NUMERIC_VALUE = eINSTANCE.getLiteralNumericValue();

		/**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute LITERAL_NUMERIC_VALUE__VALUE = eINSTANCE.getLiteralNumericValue_Value();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.NumericReferenceImpl <em>Numeric Reference</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.NumericReferenceImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getNumericReference()
     * @generated
     */
		EClass NUMERIC_REFERENCE = eINSTANCE.getNumericReference();

		/**
     * The meta object literal for the '<em><b>Referenced Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference NUMERIC_REFERENCE__REFERENCED_VALUE = eINSTANCE.getNumericReference_ReferencedValue();

		/**
     * The meta object literal for the '<em><b>Referenced Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference NUMERIC_REFERENCE__REFERENCED_PROPERTY = eINSTANCE.getNumericReference_ReferencedProperty();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractComplexValueImpl <em>Abstract Complex Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.AbstractComplexValueImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getAbstractComplexValue()
     * @generated
     */
		EClass ABSTRACT_COMPLEX_VALUE = eINSTANCE.getAbstractComplexValue();

		/**
     * The meta object literal for the '<em><b>Complex Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_COMPLEX_VALUE__COMPLEX_TYPE = eINSTANCE.getAbstractComplexValue_ComplexType();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.ComplexValueImpl <em>Complex Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.ComplexValueImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getComplexValue()
     * @generated
     */
		EClass COMPLEX_VALUE = eINSTANCE.getComplexValue();

		/**
     * The meta object literal for the '<em><b>Owned Parts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPLEX_VALUE__OWNED_PARTS = eINSTANCE.getComplexValue_OwnedParts();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.ComplexValueReferenceImpl <em>Complex Value Reference</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.ComplexValueReferenceImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getComplexValueReference()
     * @generated
     */
		EClass COMPLEX_VALUE_REFERENCE = eINSTANCE.getComplexValueReference();

		/**
     * The meta object literal for the '<em><b>Referenced Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPLEX_VALUE_REFERENCE__REFERENCED_VALUE = eINSTANCE.getComplexValueReference_ReferencedValue();

		/**
     * The meta object literal for the '<em><b>Referenced Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPLEX_VALUE_REFERENCE__REFERENCED_PROPERTY = eINSTANCE.getComplexValueReference_ReferencedProperty();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.ValuePartImpl <em>Value Part</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.ValuePartImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getValuePart()
     * @generated
     */
		EClass VALUE_PART = eINSTANCE.getValuePart();

		/**
     * The meta object literal for the '<em><b>Referenced Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference VALUE_PART__REFERENCED_PROPERTY = eINSTANCE.getValuePart_ReferencedProperty();

		/**
     * The meta object literal for the '<em><b>Owned Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference VALUE_PART__OWNED_VALUE = eINSTANCE.getValuePart_OwnedValue();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractExpressionValueImpl <em>Abstract Expression Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.AbstractExpressionValueImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getAbstractExpressionValue()
     * @generated
     */
		EClass ABSTRACT_EXPRESSION_VALUE = eINSTANCE.getAbstractExpressionValue();

		/**
     * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ABSTRACT_EXPRESSION_VALUE__EXPRESSION = eINSTANCE.getAbstractExpressionValue_Expression();

		/**
     * The meta object literal for the '<em><b>Unparsed Expression</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ABSTRACT_EXPRESSION_VALUE__UNPARSED_EXPRESSION = eINSTANCE.getAbstractExpressionValue_UnparsedExpression();

		/**
     * The meta object literal for the '<em><b>Expression Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_EXPRESSION_VALUE__EXPRESSION_TYPE = eINSTANCE.getAbstractExpressionValue_ExpressionType();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.BinaryExpressionImpl <em>Binary Expression</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.BinaryExpressionImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getBinaryExpression()
     * @generated
     */
		EClass BINARY_EXPRESSION = eINSTANCE.getBinaryExpression();

		/**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute BINARY_EXPRESSION__OPERATOR = eINSTANCE.getBinaryExpression_Operator();

		/**
     * The meta object literal for the '<em><b>Owned Left Operand</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BINARY_EXPRESSION__OWNED_LEFT_OPERAND = eINSTANCE.getBinaryExpression_OwnedLeftOperand();

		/**
     * The meta object literal for the '<em><b>Owned Right Operand</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BINARY_EXPRESSION__OWNED_RIGHT_OPERAND = eINSTANCE.getBinaryExpression_OwnedRightOperand();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.UnaryExpressionImpl <em>Unary Expression</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.UnaryExpressionImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getUnaryExpression()
     * @generated
     */
		EClass UNARY_EXPRESSION = eINSTANCE.getUnaryExpression();

		/**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute UNARY_EXPRESSION__OPERATOR = eINSTANCE.getUnaryExpression_Operator();

		/**
     * The meta object literal for the '<em><b>Owned Operand</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference UNARY_EXPRESSION__OWNED_OPERAND = eINSTANCE.getUnaryExpression_OwnedOperand();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.impl.OpaqueExpressionImpl <em>Opaque Expression</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.impl.OpaqueExpressionImpl
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getOpaqueExpression()
     * @generated
     */
		EClass OPAQUE_EXPRESSION = eINSTANCE.getOpaqueExpression();

		/**
     * The meta object literal for the '<em><b>Bodies</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute OPAQUE_EXPRESSION__BODIES = eINSTANCE.getOpaqueExpression_Bodies();

		/**
     * The meta object literal for the '<em><b>Languages</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute OPAQUE_EXPRESSION__LANGUAGES = eINSTANCE.getOpaqueExpression_Languages();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.BinaryOperator <em>Binary Operator</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.BinaryOperator
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getBinaryOperator()
     * @generated
     */
		EEnum BINARY_OPERATOR = eINSTANCE.getBinaryOperator();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.datavalue.UnaryOperator <em>Unary Operator</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.datavalue.UnaryOperator
     * @see org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl#getUnaryOperator()
     * @generated
     */
		EEnum UNARY_OPERATOR = eINSTANCE.getUnaryOperator();

	}

} //DatavaluePackage
