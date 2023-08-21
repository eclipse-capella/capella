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
package org.polarsys.capella.core.data.information.communication;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.information.InformationPackage;

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
 * @see org.polarsys.capella.core.data.information.communication.CommunicationFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='sub-package containing the elements invovled in communication between elements (messages, signals, ...)\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='system,logical,physical' usage\040examples='none' constraints='none' comment/notes='none' reference\040documentation='n/a'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface CommunicationPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "communication"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/core/information/communication/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.core.data.information.communication"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	CommunicationPackage eINSTANCE = org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationItemImpl <em>Item</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationItemImpl
   * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getCommunicationItem()
   * @generated
   */
	int COMMUNICATION_ITEM = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__OWNED_EXTENSIONS = CapellacorePackage.CLASSIFIER__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__ID = CapellacorePackage.CLASSIFIER__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__SID = CapellacorePackage.CLASSIFIER__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__CONSTRAINTS = CapellacorePackage.CLASSIFIER__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__OWNED_CONSTRAINTS = CapellacorePackage.CLASSIFIER__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CLASSIFIER__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__NAME = CapellacorePackage.CLASSIFIER__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__ABSTRACT_TYPED_ELEMENTS = CapellacorePackage.CLASSIFIER__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__INCOMING_TRACES = CapellacorePackage.CLASSIFIER__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__OUTGOING_TRACES = CapellacorePackage.CLASSIFIER__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__VISIBLE_IN_DOC = CapellacorePackage.CLASSIFIER__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__VISIBLE_IN_LM = CapellacorePackage.CLASSIFIER__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__SUMMARY = CapellacorePackage.CLASSIFIER__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__DESCRIPTION = CapellacorePackage.CLASSIFIER__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__REVIEW = CapellacorePackage.CLASSIFIER__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__OWNED_PROPERTY_VALUES = CapellacorePackage.CLASSIFIER__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CLASSIFIER__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__APPLIED_PROPERTY_VALUES = CapellacorePackage.CLASSIFIER__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CLASSIFIER__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CLASSIFIER__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__STATUS = CapellacorePackage.CLASSIFIER__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__FEATURES = CapellacorePackage.CLASSIFIER__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__OWNED_TRACES = CapellacorePackage.CLASSIFIER__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__CONTAINED_GENERIC_TRACES = CapellacorePackage.CLASSIFIER__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__NAMING_RULES = CapellacorePackage.CLASSIFIER__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__TYPED_ELEMENTS = CapellacorePackage.CLASSIFIER__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__ABSTRACT = CapellacorePackage.CLASSIFIER__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__OWNED_GENERALIZATIONS = CapellacorePackage.CLASSIFIER__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__SUPER_GENERALIZATIONS = CapellacorePackage.CLASSIFIER__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__SUB_GENERALIZATIONS = CapellacorePackage.CLASSIFIER__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__SUPER = CapellacorePackage.CLASSIFIER__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__SUB = CapellacorePackage.CLASSIFIER__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__OWNED_FEATURES = CapellacorePackage.CLASSIFIER__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__CONTAINED_PROPERTIES = CapellacorePackage.CLASSIFIER__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__OWNED_DATA_VALUES = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__VISIBILITY = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__OWNED_STATE_MACHINES = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM__PROPERTIES = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 4;

	/**
   * The number of structural features of the '<em>Item</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_ITEM_FEATURE_COUNT = CapellacorePackage.CLASSIFIER_FEATURE_COUNT + 5;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.communication.impl.ExceptionImpl <em>Exception</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.communication.impl.ExceptionImpl
   * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getException()
   * @generated
   */
	int EXCEPTION = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__OWNED_EXTENSIONS = COMMUNICATION_ITEM__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__ID = COMMUNICATION_ITEM__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__SID = COMMUNICATION_ITEM__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__CONSTRAINTS = COMMUNICATION_ITEM__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__OWNED_CONSTRAINTS = COMMUNICATION_ITEM__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__OWNED_MIGRATED_ELEMENTS = COMMUNICATION_ITEM__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__NAME = COMMUNICATION_ITEM__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__ABSTRACT_TYPED_ELEMENTS = COMMUNICATION_ITEM__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__INCOMING_TRACES = COMMUNICATION_ITEM__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__OUTGOING_TRACES = COMMUNICATION_ITEM__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__VISIBLE_IN_DOC = COMMUNICATION_ITEM__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__VISIBLE_IN_LM = COMMUNICATION_ITEM__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__SUMMARY = COMMUNICATION_ITEM__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__DESCRIPTION = COMMUNICATION_ITEM__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__REVIEW = COMMUNICATION_ITEM__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__OWNED_PROPERTY_VALUES = COMMUNICATION_ITEM__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__OWNED_ENUMERATION_PROPERTY_TYPES = COMMUNICATION_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__APPLIED_PROPERTY_VALUES = COMMUNICATION_ITEM__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__OWNED_PROPERTY_VALUE_GROUPS = COMMUNICATION_ITEM__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__APPLIED_PROPERTY_VALUE_GROUPS = COMMUNICATION_ITEM__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__STATUS = COMMUNICATION_ITEM__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__FEATURES = COMMUNICATION_ITEM__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__OWNED_TRACES = COMMUNICATION_ITEM__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__CONTAINED_GENERIC_TRACES = COMMUNICATION_ITEM__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__NAMING_RULES = COMMUNICATION_ITEM__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__TYPED_ELEMENTS = COMMUNICATION_ITEM__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__ABSTRACT = COMMUNICATION_ITEM__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__OWNED_GENERALIZATIONS = COMMUNICATION_ITEM__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__SUPER_GENERALIZATIONS = COMMUNICATION_ITEM__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__SUB_GENERALIZATIONS = COMMUNICATION_ITEM__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__SUPER = COMMUNICATION_ITEM__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__SUB = COMMUNICATION_ITEM__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__OWNED_FEATURES = COMMUNICATION_ITEM__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__CONTAINED_PROPERTIES = COMMUNICATION_ITEM__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__OWNED_PROPERTY_VALUE_PKGS = COMMUNICATION_ITEM__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__OWNED_DATA_VALUES = COMMUNICATION_ITEM__OWNED_DATA_VALUES;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__VISIBILITY = COMMUNICATION_ITEM__VISIBILITY;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__OWNED_STATE_MACHINES = COMMUNICATION_ITEM__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION__PROPERTIES = COMMUNICATION_ITEM__PROPERTIES;

	/**
   * The number of structural features of the '<em>Exception</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCEPTION_FEATURE_COUNT = COMMUNICATION_ITEM_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.communication.impl.MessageImpl <em>Message</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.communication.impl.MessageImpl
   * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getMessage()
   * @generated
   */
	int MESSAGE = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__OWNED_EXTENSIONS = COMMUNICATION_ITEM__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__ID = COMMUNICATION_ITEM__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__SID = COMMUNICATION_ITEM__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__CONSTRAINTS = COMMUNICATION_ITEM__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__OWNED_CONSTRAINTS = COMMUNICATION_ITEM__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__OWNED_MIGRATED_ELEMENTS = COMMUNICATION_ITEM__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__NAME = COMMUNICATION_ITEM__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__ABSTRACT_TYPED_ELEMENTS = COMMUNICATION_ITEM__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__INCOMING_TRACES = COMMUNICATION_ITEM__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__OUTGOING_TRACES = COMMUNICATION_ITEM__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__VISIBLE_IN_DOC = COMMUNICATION_ITEM__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__VISIBLE_IN_LM = COMMUNICATION_ITEM__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__SUMMARY = COMMUNICATION_ITEM__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__DESCRIPTION = COMMUNICATION_ITEM__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__REVIEW = COMMUNICATION_ITEM__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__OWNED_PROPERTY_VALUES = COMMUNICATION_ITEM__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__OWNED_ENUMERATION_PROPERTY_TYPES = COMMUNICATION_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__APPLIED_PROPERTY_VALUES = COMMUNICATION_ITEM__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__OWNED_PROPERTY_VALUE_GROUPS = COMMUNICATION_ITEM__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__APPLIED_PROPERTY_VALUE_GROUPS = COMMUNICATION_ITEM__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__STATUS = COMMUNICATION_ITEM__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__FEATURES = COMMUNICATION_ITEM__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__OWNED_TRACES = COMMUNICATION_ITEM__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__CONTAINED_GENERIC_TRACES = COMMUNICATION_ITEM__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__NAMING_RULES = COMMUNICATION_ITEM__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__TYPED_ELEMENTS = COMMUNICATION_ITEM__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__ABSTRACT = COMMUNICATION_ITEM__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__OWNED_GENERALIZATIONS = COMMUNICATION_ITEM__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__SUPER_GENERALIZATIONS = COMMUNICATION_ITEM__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__SUB_GENERALIZATIONS = COMMUNICATION_ITEM__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__SUPER = COMMUNICATION_ITEM__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__SUB = COMMUNICATION_ITEM__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__OWNED_FEATURES = COMMUNICATION_ITEM__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__CONTAINED_PROPERTIES = COMMUNICATION_ITEM__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__OWNED_PROPERTY_VALUE_PKGS = COMMUNICATION_ITEM__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__OWNED_DATA_VALUES = COMMUNICATION_ITEM__OWNED_DATA_VALUES;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__VISIBILITY = COMMUNICATION_ITEM__VISIBILITY;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__OWNED_STATE_MACHINES = COMMUNICATION_ITEM__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE__PROPERTIES = COMMUNICATION_ITEM__PROPERTIES;

	/**
   * The number of structural features of the '<em>Message</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_FEATURE_COUNT = COMMUNICATION_ITEM_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.communication.impl.MessageReferenceImpl <em>Message Reference</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.communication.impl.MessageReferenceImpl
   * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getMessageReference()
   * @generated
   */
	int MESSAGE_REFERENCE = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__OWNED_EXTENSIONS = CapellacorePackage.RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__ID = CapellacorePackage.RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__SID = CapellacorePackage.RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__CONSTRAINTS = CapellacorePackage.RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__OWNED_CONSTRAINTS = CapellacorePackage.RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__REALIZED_FLOW = CapellacorePackage.RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__INCOMING_TRACES = CapellacorePackage.RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__OUTGOING_TRACES = CapellacorePackage.RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__VISIBLE_IN_DOC = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__VISIBLE_IN_LM = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__SUMMARY = CapellacorePackage.RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__DESCRIPTION = CapellacorePackage.RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__REVIEW = CapellacorePackage.RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__OWNED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__APPLIED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__STATUS = CapellacorePackage.RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__FEATURES = CapellacorePackage.RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Message</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE__MESSAGE = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Message Reference</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_FEATURE_COUNT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.communication.impl.MessageReferencePkgImpl <em>Message Reference Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.communication.impl.MessageReferencePkgImpl
   * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getMessageReferencePkg()
   * @generated
   */
	int MESSAGE_REFERENCE_PKG = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__ID = CapellacorePackage.STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__SID = CapellacorePackage.STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Message References</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG__OWNED_MESSAGE_REFERENCES = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Message Reference Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_REFERENCE_PKG_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.communication.impl.SignalImpl <em>Signal</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.communication.impl.SignalImpl
   * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getSignal()
   * @generated
   */
	int SIGNAL = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__OWNED_EXTENSIONS = COMMUNICATION_ITEM__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__ID = COMMUNICATION_ITEM__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__SID = COMMUNICATION_ITEM__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__CONSTRAINTS = COMMUNICATION_ITEM__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__OWNED_CONSTRAINTS = COMMUNICATION_ITEM__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__OWNED_MIGRATED_ELEMENTS = COMMUNICATION_ITEM__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__NAME = COMMUNICATION_ITEM__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__ABSTRACT_TYPED_ELEMENTS = COMMUNICATION_ITEM__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__INCOMING_TRACES = COMMUNICATION_ITEM__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__OUTGOING_TRACES = COMMUNICATION_ITEM__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__VISIBLE_IN_DOC = COMMUNICATION_ITEM__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__VISIBLE_IN_LM = COMMUNICATION_ITEM__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__SUMMARY = COMMUNICATION_ITEM__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__DESCRIPTION = COMMUNICATION_ITEM__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__REVIEW = COMMUNICATION_ITEM__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__OWNED_PROPERTY_VALUES = COMMUNICATION_ITEM__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__OWNED_ENUMERATION_PROPERTY_TYPES = COMMUNICATION_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__APPLIED_PROPERTY_VALUES = COMMUNICATION_ITEM__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__OWNED_PROPERTY_VALUE_GROUPS = COMMUNICATION_ITEM__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__APPLIED_PROPERTY_VALUE_GROUPS = COMMUNICATION_ITEM__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__STATUS = COMMUNICATION_ITEM__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__FEATURES = COMMUNICATION_ITEM__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__OWNED_TRACES = COMMUNICATION_ITEM__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__CONTAINED_GENERIC_TRACES = COMMUNICATION_ITEM__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__NAMING_RULES = COMMUNICATION_ITEM__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__TYPED_ELEMENTS = COMMUNICATION_ITEM__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__ABSTRACT = COMMUNICATION_ITEM__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__OWNED_GENERALIZATIONS = COMMUNICATION_ITEM__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__SUPER_GENERALIZATIONS = COMMUNICATION_ITEM__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__SUB_GENERALIZATIONS = COMMUNICATION_ITEM__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__SUPER = COMMUNICATION_ITEM__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__SUB = COMMUNICATION_ITEM__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__OWNED_FEATURES = COMMUNICATION_ITEM__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__CONTAINED_PROPERTIES = COMMUNICATION_ITEM__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__OWNED_PROPERTY_VALUE_PKGS = COMMUNICATION_ITEM__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Data Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__OWNED_DATA_VALUES = COMMUNICATION_ITEM__OWNED_DATA_VALUES;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__VISIBILITY = COMMUNICATION_ITEM__VISIBILITY;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__OWNED_STATE_MACHINES = COMMUNICATION_ITEM__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__PROPERTIES = COMMUNICATION_ITEM__PROPERTIES;

	/**
   * The feature id for the '<em><b>Signal Instances</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL__SIGNAL_INSTANCES = COMMUNICATION_ITEM_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Signal</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_FEATURE_COUNT = COMMUNICATION_ITEM_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.communication.impl.SignalInstanceImpl <em>Signal Instance</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.communication.impl.SignalInstanceImpl
   * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getSignalInstance()
   * @generated
   */
	int SIGNAL_INSTANCE = 6;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__OWNED_EXTENSIONS = InformationPackage.ABSTRACT_INSTANCE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__ID = InformationPackage.ABSTRACT_INSTANCE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__SID = InformationPackage.ABSTRACT_INSTANCE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__CONSTRAINTS = InformationPackage.ABSTRACT_INSTANCE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__OWNED_CONSTRAINTS = InformationPackage.ABSTRACT_INSTANCE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__OWNED_MIGRATED_ELEMENTS = InformationPackage.ABSTRACT_INSTANCE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__NAME = InformationPackage.ABSTRACT_INSTANCE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__INCOMING_TRACES = InformationPackage.ABSTRACT_INSTANCE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__OUTGOING_TRACES = InformationPackage.ABSTRACT_INSTANCE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__VISIBLE_IN_DOC = InformationPackage.ABSTRACT_INSTANCE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__VISIBLE_IN_LM = InformationPackage.ABSTRACT_INSTANCE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__SUMMARY = InformationPackage.ABSTRACT_INSTANCE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__DESCRIPTION = InformationPackage.ABSTRACT_INSTANCE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__REVIEW = InformationPackage.ABSTRACT_INSTANCE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__OWNED_PROPERTY_VALUES = InformationPackage.ABSTRACT_INSTANCE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__OWNED_ENUMERATION_PROPERTY_TYPES = InformationPackage.ABSTRACT_INSTANCE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__APPLIED_PROPERTY_VALUES = InformationPackage.ABSTRACT_INSTANCE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__OWNED_PROPERTY_VALUE_GROUPS = InformationPackage.ABSTRACT_INSTANCE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__APPLIED_PROPERTY_VALUE_GROUPS = InformationPackage.ABSTRACT_INSTANCE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__STATUS = InformationPackage.ABSTRACT_INSTANCE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__FEATURES = InformationPackage.ABSTRACT_INSTANCE__FEATURES;

	/**
   * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__IS_ABSTRACT = InformationPackage.ABSTRACT_INSTANCE__IS_ABSTRACT;

	/**
   * The feature id for the '<em><b>Is Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__IS_STATIC = InformationPackage.ABSTRACT_INSTANCE__IS_STATIC;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__VISIBILITY = InformationPackage.ABSTRACT_INSTANCE__VISIBILITY;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__ABSTRACT_TYPE = InformationPackage.ABSTRACT_INSTANCE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__TYPE = InformationPackage.ABSTRACT_INSTANCE__TYPE;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__ORDERED = InformationPackage.ABSTRACT_INSTANCE__ORDERED;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__UNIQUE = InformationPackage.ABSTRACT_INSTANCE__UNIQUE;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__MIN_INCLUSIVE = InformationPackage.ABSTRACT_INSTANCE__MIN_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__MAX_INCLUSIVE = InformationPackage.ABSTRACT_INSTANCE__MAX_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__OWNED_DEFAULT_VALUE = InformationPackage.ABSTRACT_INSTANCE__OWNED_DEFAULT_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__OWNED_MIN_VALUE = InformationPackage.ABSTRACT_INSTANCE__OWNED_MIN_VALUE;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__OWNED_MAX_VALUE = InformationPackage.ABSTRACT_INSTANCE__OWNED_MAX_VALUE;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__OWNED_NULL_VALUE = InformationPackage.ABSTRACT_INSTANCE__OWNED_NULL_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__OWNED_MIN_CARD = InformationPackage.ABSTRACT_INSTANCE__OWNED_MIN_CARD;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__OWNED_MIN_LENGTH = InformationPackage.ABSTRACT_INSTANCE__OWNED_MIN_LENGTH;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__OWNED_MAX_CARD = InformationPackage.ABSTRACT_INSTANCE__OWNED_MAX_CARD;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__OWNED_MAX_LENGTH = InformationPackage.ABSTRACT_INSTANCE__OWNED_MAX_LENGTH;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__FINAL = InformationPackage.ABSTRACT_INSTANCE__FINAL;

	/**
   * The feature id for the '<em><b>Aggregation Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__AGGREGATION_KIND = InformationPackage.ABSTRACT_INSTANCE__AGGREGATION_KIND;

	/**
   * The feature id for the '<em><b>Is Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__IS_DERIVED = InformationPackage.ABSTRACT_INSTANCE__IS_DERIVED;

	/**
   * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__IS_READ_ONLY = InformationPackage.ABSTRACT_INSTANCE__IS_READ_ONLY;

	/**
   * The feature id for the '<em><b>Is Part Of Key</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__IS_PART_OF_KEY = InformationPackage.ABSTRACT_INSTANCE__IS_PART_OF_KEY;

	/**
   * The feature id for the '<em><b>Association</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__ASSOCIATION = InformationPackage.ABSTRACT_INSTANCE__ASSOCIATION;

	/**
   * The feature id for the '<em><b>Representing Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE__REPRESENTING_INSTANCE_ROLES = InformationPackage.ABSTRACT_INSTANCE__REPRESENTING_INSTANCE_ROLES;

	/**
   * The number of structural features of the '<em>Signal Instance</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SIGNAL_INSTANCE_FEATURE_COUNT = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkImpl <em>Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkImpl
   * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getCommunicationLink()
   * @generated
   */
	int COMMUNICATION_LINK = 7;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__KIND = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Protocol</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__PROTOCOL = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Exchange Item</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK__EXCHANGE_ITEM = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkExchangerImpl <em>Link Exchanger</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkExchangerImpl
   * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getCommunicationLinkExchanger()
   * @generated
   */
	int COMMUNICATION_LINK_EXCHANGER = 8;

	/**
   * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS = 0;

	/**
   * The feature id for the '<em><b>Produce</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK_EXCHANGER__PRODUCE = 1;

	/**
   * The feature id for the '<em><b>Consume</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK_EXCHANGER__CONSUME = 2;

	/**
   * The feature id for the '<em><b>Send</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK_EXCHANGER__SEND = 3;

	/**
   * The feature id for the '<em><b>Receive</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK_EXCHANGER__RECEIVE = 4;

	/**
   * The feature id for the '<em><b>Call</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK_EXCHANGER__CALL = 5;

	/**
   * The feature id for the '<em><b>Execute</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK_EXCHANGER__EXECUTE = 6;

	/**
   * The feature id for the '<em><b>Write</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK_EXCHANGER__WRITE = 7;

	/**
   * The feature id for the '<em><b>Access</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK_EXCHANGER__ACCESS = 8;

	/**
   * The feature id for the '<em><b>Acquire</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK_EXCHANGER__ACQUIRE = 9;

	/**
   * The feature id for the '<em><b>Transmit</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK_EXCHANGER__TRANSMIT = 10;

	/**
   * The number of structural features of the '<em>Link Exchanger</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_LINK_EXCHANGER_FEATURE_COUNT = 11;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkKind <em>Link Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkKind
   * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getCommunicationLinkKind()
   * @generated
   */
	int COMMUNICATION_LINK_KIND = 9;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol <em>Link Protocol</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol
   * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getCommunicationLinkProtocol()
   * @generated
   */
	int COMMUNICATION_LINK_PROTOCOL = 10;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.communication.CommunicationItem <em>Item</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Item</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationItem
   * @generated
   */
	EClass getCommunicationItem();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.communication.CommunicationItem#getVisibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visibility</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationItem#getVisibility()
   * @see #getCommunicationItem()
   * @generated
   */
	EAttribute getCommunicationItem_Visibility();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.communication.CommunicationItem#getOwnedStateMachines <em>Owned State Machines</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned State Machines</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationItem#getOwnedStateMachines()
   * @see #getCommunicationItem()
   * @generated
   */
	EReference getCommunicationItem_OwnedStateMachines();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.communication.CommunicationItem#getProperties <em>Properties</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Properties</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationItem#getProperties()
   * @see #getCommunicationItem()
   * @generated
   */
	EReference getCommunicationItem_Properties();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.communication.Exception <em>Exception</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exception</em>'.
   * @see org.polarsys.capella.core.data.information.communication.Exception
   * @generated
   */
	EClass getException();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.communication.Message <em>Message</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Message</em>'.
   * @see org.polarsys.capella.core.data.information.communication.Message
   * @generated
   */
	EClass getMessage();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.communication.MessageReference <em>Message Reference</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Message Reference</em>'.
   * @see org.polarsys.capella.core.data.information.communication.MessageReference
   * @generated
   */
	EClass getMessageReference();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.communication.MessageReference#getMessage <em>Message</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Message</em>'.
   * @see org.polarsys.capella.core.data.information.communication.MessageReference#getMessage()
   * @see #getMessageReference()
   * @generated
   */
	EReference getMessageReference_Message();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.communication.MessageReferencePkg <em>Message Reference Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Message Reference Pkg</em>'.
   * @see org.polarsys.capella.core.data.information.communication.MessageReferencePkg
   * @generated
   */
	EClass getMessageReferencePkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.communication.MessageReferencePkg#getOwnedMessageReferences <em>Owned Message References</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Message References</em>'.
   * @see org.polarsys.capella.core.data.information.communication.MessageReferencePkg#getOwnedMessageReferences()
   * @see #getMessageReferencePkg()
   * @generated
   */
	EReference getMessageReferencePkg_OwnedMessageReferences();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.communication.Signal <em>Signal</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Signal</em>'.
   * @see org.polarsys.capella.core.data.information.communication.Signal
   * @generated
   */
	EClass getSignal();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.communication.Signal#getSignalInstances <em>Signal Instances</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Signal Instances</em>'.
   * @see org.polarsys.capella.core.data.information.communication.Signal#getSignalInstances()
   * @see #getSignal()
   * @generated
   */
	EReference getSignal_SignalInstances();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.communication.SignalInstance <em>Signal Instance</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Signal Instance</em>'.
   * @see org.polarsys.capella.core.data.information.communication.SignalInstance
   * @generated
   */
	EClass getSignalInstance();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.communication.CommunicationLink <em>Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Link</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLink
   * @generated
   */
	EClass getCommunicationLink();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.communication.CommunicationLink#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLink#getKind()
   * @see #getCommunicationLink()
   * @generated
   */
	EAttribute getCommunicationLink_Kind();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.information.communication.CommunicationLink#getProtocol <em>Protocol</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Protocol</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLink#getProtocol()
   * @see #getCommunicationLink()
   * @generated
   */
	EAttribute getCommunicationLink_Protocol();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.information.communication.CommunicationLink#getExchangeItem <em>Exchange Item</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Exchange Item</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLink#getExchangeItem()
   * @see #getCommunicationLink()
   * @generated
   */
	EReference getCommunicationLink_ExchangeItem();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger <em>Link Exchanger</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Link Exchanger</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger
   * @generated
   */
	EClass getCommunicationLinkExchanger();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getOwnedCommunicationLinks <em>Owned Communication Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Communication Links</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getOwnedCommunicationLinks()
   * @see #getCommunicationLinkExchanger()
   * @generated
   */
	EReference getCommunicationLinkExchanger_OwnedCommunicationLinks();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getProduce <em>Produce</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Produce</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getProduce()
   * @see #getCommunicationLinkExchanger()
   * @generated
   */
	EReference getCommunicationLinkExchanger_Produce();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getConsume <em>Consume</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Consume</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getConsume()
   * @see #getCommunicationLinkExchanger()
   * @generated
   */
	EReference getCommunicationLinkExchanger_Consume();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getSend <em>Send</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Send</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getSend()
   * @see #getCommunicationLinkExchanger()
   * @generated
   */
	EReference getCommunicationLinkExchanger_Send();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getReceive <em>Receive</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Receive</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getReceive()
   * @see #getCommunicationLinkExchanger()
   * @generated
   */
	EReference getCommunicationLinkExchanger_Receive();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getCall <em>Call</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Call</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getCall()
   * @see #getCommunicationLinkExchanger()
   * @generated
   */
	EReference getCommunicationLinkExchanger_Call();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getExecute <em>Execute</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Execute</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getExecute()
   * @see #getCommunicationLinkExchanger()
   * @generated
   */
	EReference getCommunicationLinkExchanger_Execute();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getWrite <em>Write</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Write</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getWrite()
   * @see #getCommunicationLinkExchanger()
   * @generated
   */
	EReference getCommunicationLinkExchanger_Write();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getAccess <em>Access</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Access</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getAccess()
   * @see #getCommunicationLinkExchanger()
   * @generated
   */
	EReference getCommunicationLinkExchanger_Access();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getAcquire <em>Acquire</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Acquire</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getAcquire()
   * @see #getCommunicationLinkExchanger()
   * @generated
   */
	EReference getCommunicationLinkExchanger_Acquire();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getTransmit <em>Transmit</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Transmit</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger#getTransmit()
   * @see #getCommunicationLinkExchanger()
   * @generated
   */
	EReference getCommunicationLinkExchanger_Transmit();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkKind <em>Link Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Link Kind</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkKind
   * @generated
   */
	EEnum getCommunicationLinkKind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol <em>Link Protocol</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Link Protocol</em>'.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol
   * @generated
   */
	EEnum getCommunicationLinkProtocol();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	CommunicationFactory getCommunicationFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationItemImpl <em>Item</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationItemImpl
     * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getCommunicationItem()
     * @generated
     */
		EClass COMMUNICATION_ITEM = eINSTANCE.getCommunicationItem();

		/**
     * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute COMMUNICATION_ITEM__VISIBILITY = eINSTANCE.getCommunicationItem_Visibility();

		/**
     * The meta object literal for the '<em><b>Owned State Machines</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_ITEM__OWNED_STATE_MACHINES = eINSTANCE.getCommunicationItem_OwnedStateMachines();

		/**
     * The meta object literal for the '<em><b>Properties</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_ITEM__PROPERTIES = eINSTANCE.getCommunicationItem_Properties();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.communication.impl.ExceptionImpl <em>Exception</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.communication.impl.ExceptionImpl
     * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getException()
     * @generated
     */
		EClass EXCEPTION = eINSTANCE.getException();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.communication.impl.MessageImpl <em>Message</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.communication.impl.MessageImpl
     * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getMessage()
     * @generated
     */
		EClass MESSAGE = eINSTANCE.getMessage();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.communication.impl.MessageReferenceImpl <em>Message Reference</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.communication.impl.MessageReferenceImpl
     * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getMessageReference()
     * @generated
     */
		EClass MESSAGE_REFERENCE = eINSTANCE.getMessageReference();

		/**
     * The meta object literal for the '<em><b>Message</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MESSAGE_REFERENCE__MESSAGE = eINSTANCE.getMessageReference_Message();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.communication.impl.MessageReferencePkgImpl <em>Message Reference Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.communication.impl.MessageReferencePkgImpl
     * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getMessageReferencePkg()
     * @generated
     */
		EClass MESSAGE_REFERENCE_PKG = eINSTANCE.getMessageReferencePkg();

		/**
     * The meta object literal for the '<em><b>Owned Message References</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MESSAGE_REFERENCE_PKG__OWNED_MESSAGE_REFERENCES = eINSTANCE.getMessageReferencePkg_OwnedMessageReferences();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.communication.impl.SignalImpl <em>Signal</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.communication.impl.SignalImpl
     * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getSignal()
     * @generated
     */
		EClass SIGNAL = eINSTANCE.getSignal();

		/**
     * The meta object literal for the '<em><b>Signal Instances</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SIGNAL__SIGNAL_INSTANCES = eINSTANCE.getSignal_SignalInstances();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.communication.impl.SignalInstanceImpl <em>Signal Instance</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.communication.impl.SignalInstanceImpl
     * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getSignalInstance()
     * @generated
     */
		EClass SIGNAL_INSTANCE = eINSTANCE.getSignalInstance();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkImpl <em>Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkImpl
     * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getCommunicationLink()
     * @generated
     */
		EClass COMMUNICATION_LINK = eINSTANCE.getCommunicationLink();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute COMMUNICATION_LINK__KIND = eINSTANCE.getCommunicationLink_Kind();

		/**
     * The meta object literal for the '<em><b>Protocol</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute COMMUNICATION_LINK__PROTOCOL = eINSTANCE.getCommunicationLink_Protocol();

		/**
     * The meta object literal for the '<em><b>Exchange Item</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_LINK__EXCHANGE_ITEM = eINSTANCE.getCommunicationLink_ExchangeItem();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkExchangerImpl <em>Link Exchanger</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkExchangerImpl
     * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getCommunicationLinkExchanger()
     * @generated
     */
		EClass COMMUNICATION_LINK_EXCHANGER = eINSTANCE.getCommunicationLinkExchanger();

		/**
     * The meta object literal for the '<em><b>Owned Communication Links</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS = eINSTANCE.getCommunicationLinkExchanger_OwnedCommunicationLinks();

		/**
     * The meta object literal for the '<em><b>Produce</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_LINK_EXCHANGER__PRODUCE = eINSTANCE.getCommunicationLinkExchanger_Produce();

		/**
     * The meta object literal for the '<em><b>Consume</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_LINK_EXCHANGER__CONSUME = eINSTANCE.getCommunicationLinkExchanger_Consume();

		/**
     * The meta object literal for the '<em><b>Send</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_LINK_EXCHANGER__SEND = eINSTANCE.getCommunicationLinkExchanger_Send();

		/**
     * The meta object literal for the '<em><b>Receive</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_LINK_EXCHANGER__RECEIVE = eINSTANCE.getCommunicationLinkExchanger_Receive();

		/**
     * The meta object literal for the '<em><b>Call</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_LINK_EXCHANGER__CALL = eINSTANCE.getCommunicationLinkExchanger_Call();

		/**
     * The meta object literal for the '<em><b>Execute</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_LINK_EXCHANGER__EXECUTE = eINSTANCE.getCommunicationLinkExchanger_Execute();

		/**
     * The meta object literal for the '<em><b>Write</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_LINK_EXCHANGER__WRITE = eINSTANCE.getCommunicationLinkExchanger_Write();

		/**
     * The meta object literal for the '<em><b>Access</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_LINK_EXCHANGER__ACCESS = eINSTANCE.getCommunicationLinkExchanger_Access();

		/**
     * The meta object literal for the '<em><b>Acquire</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_LINK_EXCHANGER__ACQUIRE = eINSTANCE.getCommunicationLinkExchanger_Acquire();

		/**
     * The meta object literal for the '<em><b>Transmit</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_LINK_EXCHANGER__TRANSMIT = eINSTANCE.getCommunicationLinkExchanger_Transmit();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkKind <em>Link Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkKind
     * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getCommunicationLinkKind()
     * @generated
     */
		EEnum COMMUNICATION_LINK_KIND = eINSTANCE.getCommunicationLinkKind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol <em>Link Protocol</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol
     * @see org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl#getCommunicationLinkProtocol()
     * @generated
     */
		EEnum COMMUNICATION_LINK_PROTOCOL = eINSTANCE.getCommunicationLinkProtocol();

	}

} //CommunicationPackage
