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
package org.polarsys.capella.core.data.interaction;

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
 * @see org.polarsys.capella.core.data.interaction.InteractionFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Interaction aims at defining the components interaction language (close from the UML Sequence diagram, partially).\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='operational,system,logical' usage\040examples='none' constraints='This package depends on the model FunctionalAnalysis.ecore\r\nThis package depends on the model Behavior.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface InteractionPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "interaction"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/core/interaction/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.core.data.interaction"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	InteractionPackage eINSTANCE = org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.SequenceMessageImpl <em>Sequence Message</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.SequenceMessageImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getSequenceMessage()
   * @generated
   */
	int SEQUENCE_MESSAGE = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__KIND = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Exchange Context</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__EXCHANGE_CONTEXT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Sending End</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__SENDING_END = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Receiving End</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__RECEIVING_END = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Invoked Operation</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__INVOKED_OPERATION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Exchanged Items</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__EXCHANGED_ITEMS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Sending Part</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__SENDING_PART = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Receiving Part</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__RECEIVING_PART = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Sending Function</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__SENDING_FUNCTION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Receiving Function</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__RECEIVING_FUNCTION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Sequence Message Valuations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE__OWNED_SEQUENCE_MESSAGE_VALUATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The number of structural features of the '<em>Sequence Message</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 11;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.ScenarioImpl <em>Scenario</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.ScenarioImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getScenario()
   * @generated
   */
	int SCENARIO = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_EXTENSIONS = CapellacorePackage.NAMESPACE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__ID = CapellacorePackage.NAMESPACE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__SID = CapellacorePackage.NAMESPACE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__CONSTRAINTS = CapellacorePackage.NAMESPACE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_CONSTRAINTS = CapellacorePackage.NAMESPACE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMESPACE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__NAME = CapellacorePackage.NAMESPACE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__INCOMING_TRACES = CapellacorePackage.NAMESPACE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OUTGOING_TRACES = CapellacorePackage.NAMESPACE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__VISIBLE_IN_DOC = CapellacorePackage.NAMESPACE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__VISIBLE_IN_LM = CapellacorePackage.NAMESPACE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__SUMMARY = CapellacorePackage.NAMESPACE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__DESCRIPTION = CapellacorePackage.NAMESPACE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__REVIEW = CapellacorePackage.NAMESPACE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMESPACE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMESPACE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMESPACE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMESPACE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMESPACE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__STATUS = CapellacorePackage.NAMESPACE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__FEATURES = CapellacorePackage.NAMESPACE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_TRACES = CapellacorePackage.NAMESPACE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__CONTAINED_GENERIC_TRACES = CapellacorePackage.NAMESPACE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__NAMING_RULES = CapellacorePackage.NAMESPACE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Is Control Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__IS_CONTROL_OPERATOR = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Parameter Set</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_PARAMETER_SET = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Parameter</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_PARAMETER = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__KIND = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Merged</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__MERGED = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Pre Condition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__PRE_CONDITION = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Post Condition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__POST_CONDITION = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Instance Roles</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_INSTANCE_ROLES = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Messages</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_MESSAGES = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Owned Interaction Fragments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_INTERACTION_FRAGMENTS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Time Lapses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_TIME_LAPSES = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Owned Events</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_EVENTS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Owned Formal Gates</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_FORMAL_GATES = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Owned Scenario Realization</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_SCENARIO_REALIZATION = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Owned Constraint Durations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__OWNED_CONSTRAINT_DURATIONS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Contained Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__CONTAINED_FUNCTIONS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__CONTAINED_PARTS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Referenced Scenarios</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__REFERENCED_SCENARIOS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Realized Scenarios</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__REALIZED_SCENARIOS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Realizing Scenarios</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO__REALIZING_SCENARIOS = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 19;

	/**
   * The number of structural features of the '<em>Scenario</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_FEATURE_COUNT = CapellacorePackage.NAMESPACE_FEATURE_COUNT + 20;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.InteractionFragmentImpl <em>Fragment</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionFragmentImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getInteractionFragment()
   * @generated
   */
	int INTERACTION_FRAGMENT = 21;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Covered Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Fragment</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_FRAGMENT_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractEndImpl <em>Abstract End</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.AbstractEndImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractEnd()
   * @generated
   */
	int ABSTRACT_END = 9;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__OWNED_EXTENSIONS = INTERACTION_FRAGMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__ID = INTERACTION_FRAGMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__SID = INTERACTION_FRAGMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__CONSTRAINTS = INTERACTION_FRAGMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__OWNED_CONSTRAINTS = INTERACTION_FRAGMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__OWNED_MIGRATED_ELEMENTS = INTERACTION_FRAGMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__NAME = INTERACTION_FRAGMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__INCOMING_TRACES = INTERACTION_FRAGMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__OUTGOING_TRACES = INTERACTION_FRAGMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__VISIBLE_IN_DOC = INTERACTION_FRAGMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__VISIBLE_IN_LM = INTERACTION_FRAGMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__SUMMARY = INTERACTION_FRAGMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__DESCRIPTION = INTERACTION_FRAGMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__REVIEW = INTERACTION_FRAGMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__OWNED_PROPERTY_VALUES = INTERACTION_FRAGMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__OWNED_ENUMERATION_PROPERTY_TYPES = INTERACTION_FRAGMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__APPLIED_PROPERTY_VALUES = INTERACTION_FRAGMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__OWNED_PROPERTY_VALUE_GROUPS = INTERACTION_FRAGMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__APPLIED_PROPERTY_VALUE_GROUPS = INTERACTION_FRAGMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__STATUS = INTERACTION_FRAGMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__FEATURES = INTERACTION_FRAGMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Covered Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__COVERED_INSTANCE_ROLES = INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES;

	/**
   * The feature id for the '<em><b>Event</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__EVENT = INTERACTION_FRAGMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Covered</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END__COVERED = INTERACTION_FRAGMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Abstract End</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_END_FEATURE_COUNT = INTERACTION_FRAGMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.MessageEndImpl <em>Message End</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.MessageEndImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getMessageEnd()
   * @generated
   */
	int MESSAGE_END = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__OWNED_EXTENSIONS = ABSTRACT_END__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__ID = ABSTRACT_END__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__SID = ABSTRACT_END__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__CONSTRAINTS = ABSTRACT_END__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__OWNED_CONSTRAINTS = ABSTRACT_END__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__OWNED_MIGRATED_ELEMENTS = ABSTRACT_END__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__NAME = ABSTRACT_END__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__INCOMING_TRACES = ABSTRACT_END__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__OUTGOING_TRACES = ABSTRACT_END__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__VISIBLE_IN_DOC = ABSTRACT_END__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__VISIBLE_IN_LM = ABSTRACT_END__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__SUMMARY = ABSTRACT_END__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__DESCRIPTION = ABSTRACT_END__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__REVIEW = ABSTRACT_END__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__OWNED_PROPERTY_VALUES = ABSTRACT_END__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_END__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__APPLIED_PROPERTY_VALUES = ABSTRACT_END__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_END__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_END__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__STATUS = ABSTRACT_END__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__FEATURES = ABSTRACT_END__FEATURES;

	/**
   * The feature id for the '<em><b>Covered Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__COVERED_INSTANCE_ROLES = ABSTRACT_END__COVERED_INSTANCE_ROLES;

	/**
   * The feature id for the '<em><b>Event</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__EVENT = ABSTRACT_END__EVENT;

	/**
   * The feature id for the '<em><b>Covered</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__COVERED = ABSTRACT_END__COVERED;

	/**
   * The feature id for the '<em><b>Message</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END__MESSAGE = ABSTRACT_END_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Message End</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MESSAGE_END_FEATURE_COUNT = ABSTRACT_END_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.TimeLapseImpl <em>Time Lapse</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.TimeLapseImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getTimeLapse()
   * @generated
   */
	int TIME_LAPSE = 27;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Start</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__START = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Finish</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE__FINISH = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Time Lapse</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TIME_LAPSE_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.ExecutionImpl <em>Execution</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.ExecutionImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getExecution()
   * @generated
   */
	int EXECUTION = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__OWNED_EXTENSIONS = TIME_LAPSE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__ID = TIME_LAPSE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__SID = TIME_LAPSE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__CONSTRAINTS = TIME_LAPSE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__OWNED_CONSTRAINTS = TIME_LAPSE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__OWNED_MIGRATED_ELEMENTS = TIME_LAPSE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__NAME = TIME_LAPSE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__INCOMING_TRACES = TIME_LAPSE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__OUTGOING_TRACES = TIME_LAPSE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__VISIBLE_IN_DOC = TIME_LAPSE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__VISIBLE_IN_LM = TIME_LAPSE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__SUMMARY = TIME_LAPSE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__DESCRIPTION = TIME_LAPSE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__REVIEW = TIME_LAPSE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__OWNED_PROPERTY_VALUES = TIME_LAPSE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__OWNED_ENUMERATION_PROPERTY_TYPES = TIME_LAPSE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__APPLIED_PROPERTY_VALUES = TIME_LAPSE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__OWNED_PROPERTY_VALUE_GROUPS = TIME_LAPSE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__APPLIED_PROPERTY_VALUE_GROUPS = TIME_LAPSE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__STATUS = TIME_LAPSE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__FEATURES = TIME_LAPSE__FEATURES;

	/**
   * The feature id for the '<em><b>Start</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__START = TIME_LAPSE__START;

	/**
   * The feature id for the '<em><b>Finish</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__FINISH = TIME_LAPSE__FINISH;

	/**
   * The feature id for the '<em><b>Covered</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION__COVERED = TIME_LAPSE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Execution</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_FEATURE_COUNT = TIME_LAPSE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.ExecutionEndImpl <em>Execution End</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.ExecutionEndImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getExecutionEnd()
   * @generated
   */
	int EXECUTION_END = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__OWNED_EXTENSIONS = ABSTRACT_END__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__ID = ABSTRACT_END__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__SID = ABSTRACT_END__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__CONSTRAINTS = ABSTRACT_END__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__OWNED_CONSTRAINTS = ABSTRACT_END__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__OWNED_MIGRATED_ELEMENTS = ABSTRACT_END__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__NAME = ABSTRACT_END__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__INCOMING_TRACES = ABSTRACT_END__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__OUTGOING_TRACES = ABSTRACT_END__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__VISIBLE_IN_DOC = ABSTRACT_END__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__VISIBLE_IN_LM = ABSTRACT_END__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__SUMMARY = ABSTRACT_END__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__DESCRIPTION = ABSTRACT_END__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__REVIEW = ABSTRACT_END__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__OWNED_PROPERTY_VALUES = ABSTRACT_END__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_END__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__APPLIED_PROPERTY_VALUES = ABSTRACT_END__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_END__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_END__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__STATUS = ABSTRACT_END__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__FEATURES = ABSTRACT_END__FEATURES;

	/**
   * The feature id for the '<em><b>Covered Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__COVERED_INSTANCE_ROLES = ABSTRACT_END__COVERED_INSTANCE_ROLES;

	/**
   * The feature id for the '<em><b>Event</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__EVENT = ABSTRACT_END__EVENT;

	/**
   * The feature id for the '<em><b>Covered</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__COVERED = ABSTRACT_END__COVERED;

	/**
   * The feature id for the '<em><b>Execution</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END__EXECUTION = ABSTRACT_END_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Execution End</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_END_FEATURE_COUNT = ABSTRACT_END_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.EventImpl <em>Event</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.EventImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getEvent()
   * @generated
   */
	int EVENT = 10;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT__ABSTRACT_TYPED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Event</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.CreationEventImpl <em>Creation Event</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.CreationEventImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getCreationEvent()
   * @generated
   */
	int CREATION_EVENT = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__OWNED_EXTENSIONS = EVENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__ID = EVENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__SID = EVENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__CONSTRAINTS = EVENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__OWNED_CONSTRAINTS = EVENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__OWNED_MIGRATED_ELEMENTS = EVENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__NAME = EVENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__INCOMING_TRACES = EVENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__OUTGOING_TRACES = EVENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__VISIBLE_IN_DOC = EVENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__VISIBLE_IN_LM = EVENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__SUMMARY = EVENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__DESCRIPTION = EVENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__REVIEW = EVENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__OWNED_PROPERTY_VALUES = EVENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__OWNED_ENUMERATION_PROPERTY_TYPES = EVENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__APPLIED_PROPERTY_VALUES = EVENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__OWNED_PROPERTY_VALUE_GROUPS = EVENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__APPLIED_PROPERTY_VALUE_GROUPS = EVENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__STATUS = EVENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__FEATURES = EVENT__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT__ABSTRACT_TYPED_ELEMENTS = EVENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The number of structural features of the '<em>Creation Event</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CREATION_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.DestructionEventImpl <em>Destruction Event</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.DestructionEventImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getDestructionEvent()
   * @generated
   */
	int DESTRUCTION_EVENT = 6;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__OWNED_EXTENSIONS = EVENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__ID = EVENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__SID = EVENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__CONSTRAINTS = EVENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__OWNED_CONSTRAINTS = EVENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__OWNED_MIGRATED_ELEMENTS = EVENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__NAME = EVENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__INCOMING_TRACES = EVENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__OUTGOING_TRACES = EVENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__VISIBLE_IN_DOC = EVENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__VISIBLE_IN_LM = EVENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__SUMMARY = EVENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__DESCRIPTION = EVENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__REVIEW = EVENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__OWNED_PROPERTY_VALUES = EVENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__OWNED_ENUMERATION_PROPERTY_TYPES = EVENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__APPLIED_PROPERTY_VALUES = EVENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__OWNED_PROPERTY_VALUE_GROUPS = EVENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__APPLIED_PROPERTY_VALUE_GROUPS = EVENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__STATUS = EVENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__FEATURES = EVENT__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT__ABSTRACT_TYPED_ELEMENTS = EVENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The number of structural features of the '<em>Destruction Event</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DESTRUCTION_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.ExecutionEventImpl <em>Execution Event</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.ExecutionEventImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getExecutionEvent()
   * @generated
   */
	int EXECUTION_EVENT = 7;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__OWNED_EXTENSIONS = EVENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__ID = EVENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__SID = EVENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__CONSTRAINTS = EVENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__OWNED_CONSTRAINTS = EVENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__OWNED_MIGRATED_ELEMENTS = EVENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__NAME = EVENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__INCOMING_TRACES = EVENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__OUTGOING_TRACES = EVENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__VISIBLE_IN_DOC = EVENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__VISIBLE_IN_LM = EVENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__SUMMARY = EVENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__DESCRIPTION = EVENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__REVIEW = EVENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__OWNED_PROPERTY_VALUES = EVENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__OWNED_ENUMERATION_PROPERTY_TYPES = EVENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__APPLIED_PROPERTY_VALUES = EVENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__OWNED_PROPERTY_VALUE_GROUPS = EVENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__APPLIED_PROPERTY_VALUE_GROUPS = EVENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__STATUS = EVENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__FEATURES = EVENT__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT__ABSTRACT_TYPED_ELEMENTS = EVENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The number of structural features of the '<em>Execution Event</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXECUTION_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.InstanceRoleImpl <em>Instance Role</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.InstanceRoleImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getInstanceRole()
   * @generated
   */
	int INSTANCE_ROLE = 8;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Ends</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__ABSTRACT_ENDS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Represented Instance</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE__REPRESENTED_INSTANCE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Instance Role</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INSTANCE_ROLE_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.EventReceiptOperationImpl <em>Event Receipt Operation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.EventReceiptOperationImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getEventReceiptOperation()
   * @generated
   */
	int EVENT_RECEIPT_OPERATION = 11;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__OWNED_EXTENSIONS = EVENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__ID = EVENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__SID = EVENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__CONSTRAINTS = EVENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__OWNED_CONSTRAINTS = EVENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__OWNED_MIGRATED_ELEMENTS = EVENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__NAME = EVENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__INCOMING_TRACES = EVENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__OUTGOING_TRACES = EVENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__VISIBLE_IN_DOC = EVENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__VISIBLE_IN_LM = EVENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__SUMMARY = EVENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__DESCRIPTION = EVENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__REVIEW = EVENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__OWNED_PROPERTY_VALUES = EVENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__OWNED_ENUMERATION_PROPERTY_TYPES = EVENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__APPLIED_PROPERTY_VALUES = EVENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__OWNED_PROPERTY_VALUE_GROUPS = EVENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__APPLIED_PROPERTY_VALUE_GROUPS = EVENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__STATUS = EVENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__FEATURES = EVENT__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__ABSTRACT_TYPED_ELEMENTS = EVENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Operation</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION__OPERATION = EVENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Event Receipt Operation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_RECEIPT_OPERATION_FEATURE_COUNT = EVENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.EventSentOperationImpl <em>Event Sent Operation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.EventSentOperationImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getEventSentOperation()
   * @generated
   */
	int EVENT_SENT_OPERATION = 12;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__OWNED_EXTENSIONS = EVENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__ID = EVENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__SID = EVENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__CONSTRAINTS = EVENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__OWNED_CONSTRAINTS = EVENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__OWNED_MIGRATED_ELEMENTS = EVENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__NAME = EVENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__INCOMING_TRACES = EVENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__OUTGOING_TRACES = EVENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__VISIBLE_IN_DOC = EVENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__VISIBLE_IN_LM = EVENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__SUMMARY = EVENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__DESCRIPTION = EVENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__REVIEW = EVENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__OWNED_PROPERTY_VALUES = EVENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__OWNED_ENUMERATION_PROPERTY_TYPES = EVENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__APPLIED_PROPERTY_VALUES = EVENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__OWNED_PROPERTY_VALUE_GROUPS = EVENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__APPLIED_PROPERTY_VALUE_GROUPS = EVENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__STATUS = EVENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__FEATURES = EVENT__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__ABSTRACT_TYPED_ELEMENTS = EVENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Operation</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION__OPERATION = EVENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Event Sent Operation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EVENT_SENT_OPERATION_FEATURE_COUNT = EVENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.MergeLinkImpl <em>Merge Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.MergeLinkImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getMergeLink()
   * @generated
   */
	int MERGE_LINK = 13;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__OWNED_EXTENSIONS = CapellacorePackage.TRACE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__ID = CapellacorePackage.TRACE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__SID = CapellacorePackage.TRACE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__CONSTRAINTS = CapellacorePackage.TRACE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__OWNED_CONSTRAINTS = CapellacorePackage.TRACE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.TRACE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__REALIZED_FLOW = CapellacorePackage.TRACE__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__INCOMING_TRACES = CapellacorePackage.TRACE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__OUTGOING_TRACES = CapellacorePackage.TRACE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__VISIBLE_IN_DOC = CapellacorePackage.TRACE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__VISIBLE_IN_LM = CapellacorePackage.TRACE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__SUMMARY = CapellacorePackage.TRACE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__DESCRIPTION = CapellacorePackage.TRACE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__REVIEW = CapellacorePackage.TRACE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__OWNED_PROPERTY_VALUES = CapellacorePackage.TRACE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.TRACE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__APPLIED_PROPERTY_VALUES = CapellacorePackage.TRACE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.TRACE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.TRACE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__STATUS = CapellacorePackage.TRACE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__FEATURES = CapellacorePackage.TRACE__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__TARGET_ELEMENT = CapellacorePackage.TRACE__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK__SOURCE_ELEMENT = CapellacorePackage.TRACE__SOURCE_ELEMENT;

	/**
   * The number of structural features of the '<em>Merge Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MERGE_LINK_FEATURE_COUNT = CapellacorePackage.TRACE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.RefinementLinkImpl <em>Refinement Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.RefinementLinkImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getRefinementLink()
   * @generated
   */
	int REFINEMENT_LINK = 14;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__OWNED_EXTENSIONS = CapellacorePackage.TRACE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__ID = CapellacorePackage.TRACE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__SID = CapellacorePackage.TRACE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__CONSTRAINTS = CapellacorePackage.TRACE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__OWNED_CONSTRAINTS = CapellacorePackage.TRACE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.TRACE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__REALIZED_FLOW = CapellacorePackage.TRACE__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__INCOMING_TRACES = CapellacorePackage.TRACE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__OUTGOING_TRACES = CapellacorePackage.TRACE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__VISIBLE_IN_DOC = CapellacorePackage.TRACE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__VISIBLE_IN_LM = CapellacorePackage.TRACE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__SUMMARY = CapellacorePackage.TRACE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__DESCRIPTION = CapellacorePackage.TRACE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__REVIEW = CapellacorePackage.TRACE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__OWNED_PROPERTY_VALUES = CapellacorePackage.TRACE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.TRACE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__APPLIED_PROPERTY_VALUES = CapellacorePackage.TRACE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.TRACE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.TRACE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__STATUS = CapellacorePackage.TRACE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__FEATURES = CapellacorePackage.TRACE__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__TARGET_ELEMENT = CapellacorePackage.TRACE__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK__SOURCE_ELEMENT = CapellacorePackage.TRACE__SOURCE_ELEMENT;

	/**
   * The number of structural features of the '<em>Refinement Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REFINEMENT_LINK_FEATURE_COUNT = CapellacorePackage.TRACE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityRealizationImpl <em>Abstract Capability Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityRealizationImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractCapabilityRealization()
   * @generated
   */
	int ABSTRACT_CAPABILITY_REALIZATION = 15;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Realized Capability</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__REALIZED_CAPABILITY = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Realizing Capability</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION__REALIZING_CAPABILITY = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Abstract Capability Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl <em>Abstract Capability</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractCapability()
   * @generated
   */
	int ABSTRACT_CAPABILITY = 16;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__ID = CapellacorePackage.STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__SID = CapellacorePackage.STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Involved Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__INVOLVED_INVOLVEMENTS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Functional Chains</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAINS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Pre Condition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__PRE_CONDITION = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Post Condition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__POST_CONDITION = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Scenarios</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__OWNED_SCENARIOS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Incoming Capability Allocation</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__INCOMING_CAPABILITY_ALLOCATION = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Outgoing Capability Allocation</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__OUTGOING_CAPABILITY_ALLOCATION = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Extends</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__EXTENDS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Extending</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__EXTENDING = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Abstract Capability Extension Points</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__ABSTRACT_CAPABILITY_EXTENSION_POINTS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__SUB_GENERALIZATIONS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Includes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__INCLUDES = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Including</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__INCLUDING = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__SUPER = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__SUB = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Included Abstract Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__INCLUDED_ABSTRACT_CAPABILITIES = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Including Abstract Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__INCLUDING_ABSTRACT_CAPABILITIES = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Extended Abstract Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Extending Abstract Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__EXTENDING_ABSTRACT_CAPABILITIES = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 19;

	/**
   * The feature id for the '<em><b>Owned Functional Chain Abstract Capability Involvements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENTS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 20;

	/**
   * The feature id for the '<em><b>Owned Abstract Function Abstract Capability Involvements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__OWNED_ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENTS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 21;

	/**
   * The feature id for the '<em><b>Available In States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 22;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 23;

	/**
   * The feature id for the '<em><b>Involved Abstract Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__INVOLVED_ABSTRACT_FUNCTIONS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 24;

	/**
   * The feature id for the '<em><b>Involved Functional Chains</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY__INVOLVED_FUNCTIONAL_CHAINS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 25;

	/**
   * The number of structural features of the '<em>Abstract Capability</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 26;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtendImpl <em>Abstract Capability Extend</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtendImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractCapabilityExtend()
   * @generated
   */
	int ABSTRACT_CAPABILITY_EXTEND = 17;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__OWNED_EXTENSIONS = CapellacorePackage.RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__ID = CapellacorePackage.RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__SID = CapellacorePackage.RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__CONSTRAINTS = CapellacorePackage.RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__OWNED_CONSTRAINTS = CapellacorePackage.RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__REALIZED_FLOW = CapellacorePackage.RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__INCOMING_TRACES = CapellacorePackage.RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__OUTGOING_TRACES = CapellacorePackage.RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__VISIBLE_IN_DOC = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__VISIBLE_IN_LM = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__SUMMARY = CapellacorePackage.RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__DESCRIPTION = CapellacorePackage.RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__REVIEW = CapellacorePackage.RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__OWNED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__APPLIED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__STATUS = CapellacorePackage.RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__FEATURES = CapellacorePackage.RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Extended</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__EXTENDED = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Extension</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__EXTENSION = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Extension Location</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Abstract Capability Extend</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTEND_FEATURE_COUNT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtensionPointImpl <em>Abstract Capability Extension Point</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtensionPointImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractCapabilityExtensionPoint()
   * @generated
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT = 18;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__OWNED_EXTENSIONS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__ID = CapellacorePackage.NAMED_RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__SID = CapellacorePackage.NAMED_RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__CONSTRAINTS = CapellacorePackage.NAMED_RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__REALIZED_FLOW = CapellacorePackage.NAMED_RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__INCOMING_TRACES = CapellacorePackage.NAMED_RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__OUTGOING_TRACES = CapellacorePackage.NAMED_RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__VISIBLE_IN_DOC = CapellacorePackage.NAMED_RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__VISIBLE_IN_LM = CapellacorePackage.NAMED_RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__SUMMARY = CapellacorePackage.NAMED_RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__DESCRIPTION = CapellacorePackage.NAMED_RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__REVIEW = CapellacorePackage.NAMED_RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__STATUS = CapellacorePackage.NAMED_RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__FEATURES = CapellacorePackage.NAMED_RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__NAME = CapellacorePackage.NAMED_RELATIONSHIP__NAME;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__NAMING_RULES = CapellacorePackage.NAMED_RELATIONSHIP__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Abstract Capability</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__ABSTRACT_CAPABILITY = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Extend Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Abstract Capability Extension Point</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_EXTENSION_POINT_FEATURE_COUNT = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityGeneralizationImpl <em>Abstract Capability Generalization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityGeneralizationImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractCapabilityGeneralization()
   * @generated
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION = 19;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__OWNED_EXTENSIONS = CapellacorePackage.RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__ID = CapellacorePackage.RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__SID = CapellacorePackage.RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__CONSTRAINTS = CapellacorePackage.RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__REALIZED_FLOW = CapellacorePackage.RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__INCOMING_TRACES = CapellacorePackage.RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__OUTGOING_TRACES = CapellacorePackage.RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__VISIBLE_IN_DOC = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__VISIBLE_IN_LM = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__SUMMARY = CapellacorePackage.RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__DESCRIPTION = CapellacorePackage.RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__REVIEW = CapellacorePackage.RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__STATUS = CapellacorePackage.RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__FEATURES = CapellacorePackage.RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__SUPER = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION__SUB = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Abstract Capability Generalization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_GENERALIZATION_FEATURE_COUNT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityIncludeImpl <em>Abstract Capability Include</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityIncludeImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractCapabilityInclude()
   * @generated
   */
	int ABSTRACT_CAPABILITY_INCLUDE = 20;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__OWNED_EXTENSIONS = CapellacorePackage.RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__ID = CapellacorePackage.RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__SID = CapellacorePackage.RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__CONSTRAINTS = CapellacorePackage.RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__OWNED_CONSTRAINTS = CapellacorePackage.RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__REALIZED_FLOW = CapellacorePackage.RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__INCOMING_TRACES = CapellacorePackage.RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__OUTGOING_TRACES = CapellacorePackage.RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__VISIBLE_IN_DOC = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__VISIBLE_IN_LM = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__SUMMARY = CapellacorePackage.RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__DESCRIPTION = CapellacorePackage.RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__REVIEW = CapellacorePackage.RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__OWNED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__APPLIED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__STATUS = CapellacorePackage.RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__FEATURES = CapellacorePackage.RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Included</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__INCLUDED = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Inclusion</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE__INCLUSION = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Abstract Capability Include</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CAPABILITY_INCLUDE_FEATURE_COUNT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.InteractionStateImpl <em>State</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionStateImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getInteractionState()
   * @generated
   */
	int INTERACTION_STATE = 22;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__OWNED_EXTENSIONS = INTERACTION_FRAGMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__ID = INTERACTION_FRAGMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__SID = INTERACTION_FRAGMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__CONSTRAINTS = INTERACTION_FRAGMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__OWNED_CONSTRAINTS = INTERACTION_FRAGMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__OWNED_MIGRATED_ELEMENTS = INTERACTION_FRAGMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__NAME = INTERACTION_FRAGMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__INCOMING_TRACES = INTERACTION_FRAGMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__OUTGOING_TRACES = INTERACTION_FRAGMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__VISIBLE_IN_DOC = INTERACTION_FRAGMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__VISIBLE_IN_LM = INTERACTION_FRAGMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__SUMMARY = INTERACTION_FRAGMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__DESCRIPTION = INTERACTION_FRAGMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__REVIEW = INTERACTION_FRAGMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__OWNED_PROPERTY_VALUES = INTERACTION_FRAGMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__OWNED_ENUMERATION_PROPERTY_TYPES = INTERACTION_FRAGMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__APPLIED_PROPERTY_VALUES = INTERACTION_FRAGMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__OWNED_PROPERTY_VALUE_GROUPS = INTERACTION_FRAGMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__APPLIED_PROPERTY_VALUE_GROUPS = INTERACTION_FRAGMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__STATUS = INTERACTION_FRAGMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__FEATURES = INTERACTION_FRAGMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Covered Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__COVERED_INSTANCE_ROLES = INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES;

	/**
   * The feature id for the '<em><b>Related Abstract State</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated See {@link org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractState() model documentation} for details.
   * @generated
   * @ordered
   */
	@Deprecated
	int INTERACTION_STATE__RELATED_ABSTRACT_STATE = INTERACTION_FRAGMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Related Abstract Function</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated See {@link org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractFunction() model documentation} for details.
   * @generated
   * @ordered
   */
	@Deprecated
	int INTERACTION_STATE__RELATED_ABSTRACT_FUNCTION = INTERACTION_FRAGMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Covered</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE__COVERED = INTERACTION_FRAGMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>State</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_STATE_FEATURE_COUNT = INTERACTION_FRAGMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractFragmentImpl <em>Abstract Fragment</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.AbstractFragmentImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractFragment()
   * @generated
   */
	int ABSTRACT_FRAGMENT = 28;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__OWNED_EXTENSIONS = TIME_LAPSE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__ID = TIME_LAPSE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__SID = TIME_LAPSE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__CONSTRAINTS = TIME_LAPSE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__OWNED_CONSTRAINTS = TIME_LAPSE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__OWNED_MIGRATED_ELEMENTS = TIME_LAPSE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__NAME = TIME_LAPSE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__INCOMING_TRACES = TIME_LAPSE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__OUTGOING_TRACES = TIME_LAPSE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__VISIBLE_IN_DOC = TIME_LAPSE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__VISIBLE_IN_LM = TIME_LAPSE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__SUMMARY = TIME_LAPSE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__DESCRIPTION = TIME_LAPSE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__REVIEW = TIME_LAPSE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__OWNED_PROPERTY_VALUES = TIME_LAPSE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__OWNED_ENUMERATION_PROPERTY_TYPES = TIME_LAPSE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__APPLIED_PROPERTY_VALUES = TIME_LAPSE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__OWNED_PROPERTY_VALUE_GROUPS = TIME_LAPSE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__APPLIED_PROPERTY_VALUE_GROUPS = TIME_LAPSE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__STATUS = TIME_LAPSE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__FEATURES = TIME_LAPSE__FEATURES;

	/**
   * The feature id for the '<em><b>Start</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__START = TIME_LAPSE__START;

	/**
   * The feature id for the '<em><b>Finish</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__FINISH = TIME_LAPSE__FINISH;

	/**
   * The feature id for the '<em><b>Owned Gates</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT__OWNED_GATES = TIME_LAPSE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Fragment</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FRAGMENT_FEATURE_COUNT = TIME_LAPSE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.InteractionUseImpl <em>Use</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionUseImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getInteractionUse()
   * @generated
   */
	int INTERACTION_USE = 23;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__OWNED_EXTENSIONS = ABSTRACT_FRAGMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__ID = ABSTRACT_FRAGMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__SID = ABSTRACT_FRAGMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__CONSTRAINTS = ABSTRACT_FRAGMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__OWNED_CONSTRAINTS = ABSTRACT_FRAGMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_FRAGMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__NAME = ABSTRACT_FRAGMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__INCOMING_TRACES = ABSTRACT_FRAGMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__OUTGOING_TRACES = ABSTRACT_FRAGMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__VISIBLE_IN_DOC = ABSTRACT_FRAGMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__VISIBLE_IN_LM = ABSTRACT_FRAGMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__SUMMARY = ABSTRACT_FRAGMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__DESCRIPTION = ABSTRACT_FRAGMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__REVIEW = ABSTRACT_FRAGMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__OWNED_PROPERTY_VALUES = ABSTRACT_FRAGMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_FRAGMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__APPLIED_PROPERTY_VALUES = ABSTRACT_FRAGMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_FRAGMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_FRAGMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__STATUS = ABSTRACT_FRAGMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__FEATURES = ABSTRACT_FRAGMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Start</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__START = ABSTRACT_FRAGMENT__START;

	/**
   * The feature id for the '<em><b>Finish</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__FINISH = ABSTRACT_FRAGMENT__FINISH;

	/**
   * The feature id for the '<em><b>Owned Gates</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__OWNED_GATES = ABSTRACT_FRAGMENT__OWNED_GATES;

	/**
   * The feature id for the '<em><b>Referenced Scenario</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__REFERENCED_SCENARIO = ABSTRACT_FRAGMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Actual Gates</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE__ACTUAL_GATES = ABSTRACT_FRAGMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Use</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_USE_FEATURE_COUNT = ABSTRACT_FRAGMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.CombinedFragmentImpl <em>Combined Fragment</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.CombinedFragmentImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getCombinedFragment()
   * @generated
   */
	int COMBINED_FRAGMENT = 24;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__OWNED_EXTENSIONS = ABSTRACT_FRAGMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__ID = ABSTRACT_FRAGMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__SID = ABSTRACT_FRAGMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__CONSTRAINTS = ABSTRACT_FRAGMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__OWNED_CONSTRAINTS = ABSTRACT_FRAGMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__OWNED_MIGRATED_ELEMENTS = ABSTRACT_FRAGMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__NAME = ABSTRACT_FRAGMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__INCOMING_TRACES = ABSTRACT_FRAGMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__OUTGOING_TRACES = ABSTRACT_FRAGMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__VISIBLE_IN_DOC = ABSTRACT_FRAGMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__VISIBLE_IN_LM = ABSTRACT_FRAGMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__SUMMARY = ABSTRACT_FRAGMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__DESCRIPTION = ABSTRACT_FRAGMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__REVIEW = ABSTRACT_FRAGMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__OWNED_PROPERTY_VALUES = ABSTRACT_FRAGMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_FRAGMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__APPLIED_PROPERTY_VALUES = ABSTRACT_FRAGMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_FRAGMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_FRAGMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__STATUS = ABSTRACT_FRAGMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__FEATURES = ABSTRACT_FRAGMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Start</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__START = ABSTRACT_FRAGMENT__START;

	/**
   * The feature id for the '<em><b>Finish</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__FINISH = ABSTRACT_FRAGMENT__FINISH;

	/**
   * The feature id for the '<em><b>Owned Gates</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__OWNED_GATES = ABSTRACT_FRAGMENT__OWNED_GATES;

	/**
   * The feature id for the '<em><b>Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__OPERATOR = ABSTRACT_FRAGMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Referenced Operands</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__REFERENCED_OPERANDS = ABSTRACT_FRAGMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Expression Gates</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT__EXPRESSION_GATES = ABSTRACT_FRAGMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Combined Fragment</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMBINED_FRAGMENT_FEATURE_COUNT = ABSTRACT_FRAGMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.GateImpl <em>Gate</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.GateImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getGate()
   * @generated
   */
	int GATE = 25;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__OWNED_EXTENSIONS = MESSAGE_END__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__ID = MESSAGE_END__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__SID = MESSAGE_END__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__CONSTRAINTS = MESSAGE_END__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__OWNED_CONSTRAINTS = MESSAGE_END__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__OWNED_MIGRATED_ELEMENTS = MESSAGE_END__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__NAME = MESSAGE_END__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__INCOMING_TRACES = MESSAGE_END__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__OUTGOING_TRACES = MESSAGE_END__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__VISIBLE_IN_DOC = MESSAGE_END__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__VISIBLE_IN_LM = MESSAGE_END__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__SUMMARY = MESSAGE_END__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__DESCRIPTION = MESSAGE_END__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__REVIEW = MESSAGE_END__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__OWNED_PROPERTY_VALUES = MESSAGE_END__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__OWNED_ENUMERATION_PROPERTY_TYPES = MESSAGE_END__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__APPLIED_PROPERTY_VALUES = MESSAGE_END__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__OWNED_PROPERTY_VALUE_GROUPS = MESSAGE_END__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__APPLIED_PROPERTY_VALUE_GROUPS = MESSAGE_END__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__STATUS = MESSAGE_END__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__FEATURES = MESSAGE_END__FEATURES;

	/**
   * The feature id for the '<em><b>Covered Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__COVERED_INSTANCE_ROLES = MESSAGE_END__COVERED_INSTANCE_ROLES;

	/**
   * The feature id for the '<em><b>Event</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__EVENT = MESSAGE_END__EVENT;

	/**
   * The feature id for the '<em><b>Covered</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__COVERED = MESSAGE_END__COVERED;

	/**
   * The feature id for the '<em><b>Message</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE__MESSAGE = MESSAGE_END__MESSAGE;

	/**
   * The number of structural features of the '<em>Gate</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GATE_FEATURE_COUNT = MESSAGE_END_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.InteractionOperandImpl <em>Operand</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionOperandImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getInteractionOperand()
   * @generated
   */
	int INTERACTION_OPERAND = 26;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__OWNED_EXTENSIONS = INTERACTION_FRAGMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__ID = INTERACTION_FRAGMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__SID = INTERACTION_FRAGMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__CONSTRAINTS = INTERACTION_FRAGMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__OWNED_CONSTRAINTS = INTERACTION_FRAGMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__OWNED_MIGRATED_ELEMENTS = INTERACTION_FRAGMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__NAME = INTERACTION_FRAGMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__INCOMING_TRACES = INTERACTION_FRAGMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__OUTGOING_TRACES = INTERACTION_FRAGMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__VISIBLE_IN_DOC = INTERACTION_FRAGMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__VISIBLE_IN_LM = INTERACTION_FRAGMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__SUMMARY = INTERACTION_FRAGMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__DESCRIPTION = INTERACTION_FRAGMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__REVIEW = INTERACTION_FRAGMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__OWNED_PROPERTY_VALUES = INTERACTION_FRAGMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__OWNED_ENUMERATION_PROPERTY_TYPES = INTERACTION_FRAGMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__APPLIED_PROPERTY_VALUES = INTERACTION_FRAGMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__OWNED_PROPERTY_VALUE_GROUPS = INTERACTION_FRAGMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__APPLIED_PROPERTY_VALUE_GROUPS = INTERACTION_FRAGMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__STATUS = INTERACTION_FRAGMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__FEATURES = INTERACTION_FRAGMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Covered Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__COVERED_INSTANCE_ROLES = INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES;

	/**
   * The feature id for the '<em><b>Referenced Interaction Fragments</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__REFERENCED_INTERACTION_FRAGMENTS = INTERACTION_FRAGMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Guard</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND__GUARD = INTERACTION_FRAGMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Operand</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERACTION_OPERAND_FEATURE_COUNT = INTERACTION_FRAGMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.FragmentEndImpl <em>Fragment End</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.FragmentEndImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getFragmentEnd()
   * @generated
   */
	int FRAGMENT_END = 29;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__OWNED_EXTENSIONS = INTERACTION_FRAGMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__ID = INTERACTION_FRAGMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__SID = INTERACTION_FRAGMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__CONSTRAINTS = INTERACTION_FRAGMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__OWNED_CONSTRAINTS = INTERACTION_FRAGMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__OWNED_MIGRATED_ELEMENTS = INTERACTION_FRAGMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__NAME = INTERACTION_FRAGMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__INCOMING_TRACES = INTERACTION_FRAGMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__OUTGOING_TRACES = INTERACTION_FRAGMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__VISIBLE_IN_DOC = INTERACTION_FRAGMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__VISIBLE_IN_LM = INTERACTION_FRAGMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__SUMMARY = INTERACTION_FRAGMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__DESCRIPTION = INTERACTION_FRAGMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__REVIEW = INTERACTION_FRAGMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__OWNED_PROPERTY_VALUES = INTERACTION_FRAGMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__OWNED_ENUMERATION_PROPERTY_TYPES = INTERACTION_FRAGMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__APPLIED_PROPERTY_VALUES = INTERACTION_FRAGMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__OWNED_PROPERTY_VALUE_GROUPS = INTERACTION_FRAGMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__APPLIED_PROPERTY_VALUE_GROUPS = INTERACTION_FRAGMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__STATUS = INTERACTION_FRAGMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__FEATURES = INTERACTION_FRAGMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Covered Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__COVERED_INSTANCE_ROLES = INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES;

	/**
   * The feature id for the '<em><b>Abstract Fragment</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END__ABSTRACT_FRAGMENT = INTERACTION_FRAGMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Fragment End</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FRAGMENT_END_FEATURE_COUNT = INTERACTION_FRAGMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.FunctionalChainAbstractCapabilityInvolvementImpl <em>Functional Chain Abstract Capability Involvement</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.FunctionalChainAbstractCapabilityInvolvementImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getFunctionalChainAbstractCapabilityInvolvement()
   * @generated
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT = 30;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__OWNED_EXTENSIONS = CapellacorePackage.INVOLVEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__ID = CapellacorePackage.INVOLVEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__SID = CapellacorePackage.INVOLVEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__CONSTRAINTS = CapellacorePackage.INVOLVEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__OWNED_CONSTRAINTS = CapellacorePackage.INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.INVOLVEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__REALIZED_FLOW = CapellacorePackage.INVOLVEMENT__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__INCOMING_TRACES = CapellacorePackage.INVOLVEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__OUTGOING_TRACES = CapellacorePackage.INVOLVEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__VISIBLE_IN_DOC = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__VISIBLE_IN_LM = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__SUMMARY = CapellacorePackage.INVOLVEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__DESCRIPTION = CapellacorePackage.INVOLVEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__REVIEW = CapellacorePackage.INVOLVEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__STATUS = CapellacorePackage.INVOLVEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__FEATURES = CapellacorePackage.INVOLVEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involver</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__INVOLVER = CapellacorePackage.INVOLVEMENT__INVOLVER;

	/**
   * The feature id for the '<em><b>Involved</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__INVOLVED = CapellacorePackage.INVOLVEMENT__INVOLVED;

	/**
   * The feature id for the '<em><b>Capability</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__CAPABILITY = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Functional Chain</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__FUNCTIONAL_CHAIN = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Functional Chain Abstract Capability Involvement</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT_FEATURE_COUNT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractFunctionAbstractCapabilityInvolvementImpl <em>Abstract Function Abstract Capability Involvement</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.AbstractFunctionAbstractCapabilityInvolvementImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractFunctionAbstractCapabilityInvolvement()
   * @generated
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT = 31;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__OWNED_EXTENSIONS = CapellacorePackage.INVOLVEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__ID = CapellacorePackage.INVOLVEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__SID = CapellacorePackage.INVOLVEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__CONSTRAINTS = CapellacorePackage.INVOLVEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__OWNED_CONSTRAINTS = CapellacorePackage.INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.INVOLVEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__REALIZED_FLOW = CapellacorePackage.INVOLVEMENT__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__INCOMING_TRACES = CapellacorePackage.INVOLVEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__OUTGOING_TRACES = CapellacorePackage.INVOLVEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__VISIBLE_IN_DOC = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__VISIBLE_IN_LM = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__SUMMARY = CapellacorePackage.INVOLVEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__DESCRIPTION = CapellacorePackage.INVOLVEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__REVIEW = CapellacorePackage.INVOLVEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__STATUS = CapellacorePackage.INVOLVEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__FEATURES = CapellacorePackage.INVOLVEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involver</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__INVOLVER = CapellacorePackage.INVOLVEMENT__INVOLVER;

	/**
   * The feature id for the '<em><b>Involved</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__INVOLVED = CapellacorePackage.INVOLVEMENT__INVOLVED;

	/**
   * The feature id for the '<em><b>Capability</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__CAPABILITY = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Function</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__FUNCTION = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Abstract Function Abstract Capability Involvement</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT_FEATURE_COUNT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.ScenarioRealizationImpl <em>Scenario Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.ScenarioRealizationImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getScenarioRealization()
   * @generated
   */
	int SCENARIO_REALIZATION = 32;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Realized Scenario</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__REALIZED_SCENARIO = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Realizing Scenario</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION__REALIZING_SCENARIO = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Scenario Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SCENARIO_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.StateFragmentImpl <em>State Fragment</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.StateFragmentImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getStateFragment()
   * @generated
   */
	int STATE_FRAGMENT = 33;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__OWNED_EXTENSIONS = TIME_LAPSE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__ID = TIME_LAPSE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__SID = TIME_LAPSE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__CONSTRAINTS = TIME_LAPSE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__OWNED_CONSTRAINTS = TIME_LAPSE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__OWNED_MIGRATED_ELEMENTS = TIME_LAPSE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__NAME = TIME_LAPSE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__INCOMING_TRACES = TIME_LAPSE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__OUTGOING_TRACES = TIME_LAPSE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__VISIBLE_IN_DOC = TIME_LAPSE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__VISIBLE_IN_LM = TIME_LAPSE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__SUMMARY = TIME_LAPSE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__DESCRIPTION = TIME_LAPSE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__REVIEW = TIME_LAPSE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__OWNED_PROPERTY_VALUES = TIME_LAPSE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__OWNED_ENUMERATION_PROPERTY_TYPES = TIME_LAPSE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__APPLIED_PROPERTY_VALUES = TIME_LAPSE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__OWNED_PROPERTY_VALUE_GROUPS = TIME_LAPSE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__APPLIED_PROPERTY_VALUE_GROUPS = TIME_LAPSE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__STATUS = TIME_LAPSE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__FEATURES = TIME_LAPSE__FEATURES;

	/**
   * The feature id for the '<em><b>Start</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__START = TIME_LAPSE__START;

	/**
   * The feature id for the '<em><b>Finish</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__FINISH = TIME_LAPSE__FINISH;

	/**
   * The feature id for the '<em><b>Related Abstract State</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__RELATED_ABSTRACT_STATE = TIME_LAPSE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Related Abstract Function</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION = TIME_LAPSE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>State Fragment</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STATE_FRAGMENT_FEATURE_COUNT = TIME_LAPSE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.ArmTimerEventImpl <em>Arm Timer Event</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.ArmTimerEventImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getArmTimerEvent()
   * @generated
   */
	int ARM_TIMER_EVENT = 34;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__OWNED_EXTENSIONS = EVENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__ID = EVENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__SID = EVENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__CONSTRAINTS = EVENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__OWNED_CONSTRAINTS = EVENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__OWNED_MIGRATED_ELEMENTS = EVENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__NAME = EVENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__INCOMING_TRACES = EVENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__OUTGOING_TRACES = EVENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__VISIBLE_IN_DOC = EVENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__VISIBLE_IN_LM = EVENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__SUMMARY = EVENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__DESCRIPTION = EVENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__REVIEW = EVENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__OWNED_PROPERTY_VALUES = EVENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__OWNED_ENUMERATION_PROPERTY_TYPES = EVENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__APPLIED_PROPERTY_VALUES = EVENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__OWNED_PROPERTY_VALUE_GROUPS = EVENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__APPLIED_PROPERTY_VALUE_GROUPS = EVENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__STATUS = EVENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__FEATURES = EVENT__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT__ABSTRACT_TYPED_ELEMENTS = EVENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The number of structural features of the '<em>Arm Timer Event</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARM_TIMER_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.CancelTimerEventImpl <em>Cancel Timer Event</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.CancelTimerEventImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getCancelTimerEvent()
   * @generated
   */
	int CANCEL_TIMER_EVENT = 35;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__OWNED_EXTENSIONS = EVENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__ID = EVENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__SID = EVENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__CONSTRAINTS = EVENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__OWNED_CONSTRAINTS = EVENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__OWNED_MIGRATED_ELEMENTS = EVENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__NAME = EVENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__INCOMING_TRACES = EVENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__OUTGOING_TRACES = EVENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__VISIBLE_IN_DOC = EVENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__VISIBLE_IN_LM = EVENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__SUMMARY = EVENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__DESCRIPTION = EVENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__REVIEW = EVENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__OWNED_PROPERTY_VALUES = EVENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__OWNED_ENUMERATION_PROPERTY_TYPES = EVENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__APPLIED_PROPERTY_VALUES = EVENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__OWNED_PROPERTY_VALUE_GROUPS = EVENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__APPLIED_PROPERTY_VALUE_GROUPS = EVENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__STATUS = EVENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__FEATURES = EVENT__FEATURES;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT__ABSTRACT_TYPED_ELEMENTS = EVENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The number of structural features of the '<em>Cancel Timer Event</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CANCEL_TIMER_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.ConstraintDurationImpl <em>Constraint Duration</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.ConstraintDurationImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getConstraintDuration()
   * @generated
   */
	int CONSTRAINT_DURATION = 36;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Duration</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__DURATION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Start</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__START = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Finish</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION__FINISH = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Constraint Duration</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_DURATION_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.impl.SequenceMessageValuationImpl <em>Sequence Message Valuation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.impl.SequenceMessageValuationImpl
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getSequenceMessageValuation()
   * @generated
   */
	int SEQUENCE_MESSAGE_VALUATION = 37;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Exchange Item Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__EXCHANGE_ITEM_ELEMENT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION__VALUE = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Sequence Message Valuation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SEQUENCE_MESSAGE_VALUATION_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.MessageKind <em>Message Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.MessageKind
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getMessageKind()
   * @generated
   */
	int MESSAGE_KIND = 38;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.ScenarioKind <em>Scenario Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.ScenarioKind
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getScenarioKind()
   * @generated
   */
	int SCENARIO_KIND = 39;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.interaction.InteractionOperatorKind <em>Operator Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.interaction.InteractionOperatorKind
   * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getInteractionOperatorKind()
   * @generated
   */
	int INTERACTION_OPERATOR_KIND = 40;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.SequenceMessage <em>Sequence Message</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Sequence Message</em>'.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessage
   * @generated
   */
	EClass getSequenceMessage();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.interaction.SequenceMessage#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessage#getKind()
   * @see #getSequenceMessage()
   * @generated
   */
	EAttribute getSequenceMessage_Kind();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.SequenceMessage#getExchangeContext <em>Exchange Context</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Exchange Context</em>'.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessage#getExchangeContext()
   * @see #getSequenceMessage()
   * @generated
   */
	EReference getSequenceMessage_ExchangeContext();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.SequenceMessage#getSendingEnd <em>Sending End</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Sending End</em>'.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessage#getSendingEnd()
   * @see #getSequenceMessage()
   * @generated
   */
	EReference getSequenceMessage_SendingEnd();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.SequenceMessage#getReceivingEnd <em>Receiving End</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Receiving End</em>'.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessage#getReceivingEnd()
   * @see #getSequenceMessage()
   * @generated
   */
	EReference getSequenceMessage_ReceivingEnd();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.SequenceMessage#getInvokedOperation <em>Invoked Operation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Invoked Operation</em>'.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessage#getInvokedOperation()
   * @see #getSequenceMessage()
   * @generated
   */
	EReference getSequenceMessage_InvokedOperation();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.SequenceMessage#getExchangedItems <em>Exchanged Items</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Exchanged Items</em>'.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessage#getExchangedItems()
   * @see #getSequenceMessage()
   * @generated
   */
	EReference getSequenceMessage_ExchangedItems();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.SequenceMessage#getSendingPart <em>Sending Part</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Sending Part</em>'.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessage#getSendingPart()
   * @see #getSequenceMessage()
   * @generated
   */
	EReference getSequenceMessage_SendingPart();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.SequenceMessage#getReceivingPart <em>Receiving Part</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Receiving Part</em>'.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessage#getReceivingPart()
   * @see #getSequenceMessage()
   * @generated
   */
	EReference getSequenceMessage_ReceivingPart();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.SequenceMessage#getSendingFunction <em>Sending Function</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Sending Function</em>'.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessage#getSendingFunction()
   * @see #getSequenceMessage()
   * @generated
   */
	EReference getSequenceMessage_SendingFunction();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.SequenceMessage#getReceivingFunction <em>Receiving Function</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Receiving Function</em>'.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessage#getReceivingFunction()
   * @see #getSequenceMessage()
   * @generated
   */
	EReference getSequenceMessage_ReceivingFunction();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.SequenceMessage#getOwnedSequenceMessageValuations <em>Owned Sequence Message Valuations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Sequence Message Valuations</em>'.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessage#getOwnedSequenceMessageValuations()
   * @see #getSequenceMessage()
   * @generated
   */
	EReference getSequenceMessage_OwnedSequenceMessageValuations();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.Scenario <em>Scenario</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Scenario</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario
   * @generated
   */
	EClass getScenario();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.Scenario#getPreCondition <em>Pre Condition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Pre Condition</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getPreCondition()
   * @see #getScenario()
   * @generated
   */
	EReference getScenario_PreCondition();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.Scenario#getPostCondition <em>Post Condition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Post Condition</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getPostCondition()
   * @see #getScenario()
   * @generated
   */
	EReference getScenario_PostCondition();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.interaction.Scenario#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getKind()
   * @see #getScenario()
   * @generated
   */
	EAttribute getScenario_Kind();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.interaction.Scenario#isMerged <em>Merged</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Merged</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#isMerged()
   * @see #getScenario()
   * @generated
   */
	EAttribute getScenario_Merged();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedInstanceRoles <em>Owned Instance Roles</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Instance Roles</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getOwnedInstanceRoles()
   * @see #getScenario()
   * @generated
   */
	EReference getScenario_OwnedInstanceRoles();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedMessages <em>Owned Messages</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Messages</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getOwnedMessages()
   * @see #getScenario()
   * @generated
   */
	EReference getScenario_OwnedMessages();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedInteractionFragments <em>Owned Interaction Fragments</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Interaction Fragments</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getOwnedInteractionFragments()
   * @see #getScenario()
   * @generated
   */
	EReference getScenario_OwnedInteractionFragments();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedTimeLapses <em>Owned Time Lapses</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Time Lapses</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getOwnedTimeLapses()
   * @see #getScenario()
   * @generated
   */
	EReference getScenario_OwnedTimeLapses();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedEvents <em>Owned Events</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Events</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getOwnedEvents()
   * @see #getScenario()
   * @generated
   */
	EReference getScenario_OwnedEvents();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedFormalGates <em>Owned Formal Gates</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Formal Gates</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getOwnedFormalGates()
   * @see #getScenario()
   * @generated
   */
	EReference getScenario_OwnedFormalGates();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedScenarioRealization <em>Owned Scenario Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Scenario Realization</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getOwnedScenarioRealization()
   * @see #getScenario()
   * @generated
   */
	EReference getScenario_OwnedScenarioRealization();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.Scenario#getOwnedConstraintDurations <em>Owned Constraint Durations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Constraint Durations</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getOwnedConstraintDurations()
   * @see #getScenario()
   * @generated
   */
	EReference getScenario_OwnedConstraintDurations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.Scenario#getContainedFunctions <em>Contained Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Functions</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getContainedFunctions()
   * @see #getScenario()
   * @generated
   */
	EReference getScenario_ContainedFunctions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.Scenario#getContainedParts <em>Contained Parts</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Parts</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getContainedParts()
   * @see #getScenario()
   * @generated
   */
	EReference getScenario_ContainedParts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.Scenario#getReferencedScenarios <em>Referenced Scenarios</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Referenced Scenarios</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getReferencedScenarios()
   * @see #getScenario()
   * @generated
   */
	EReference getScenario_ReferencedScenarios();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.Scenario#getRealizedScenarios <em>Realized Scenarios</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Scenarios</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getRealizedScenarios()
   * @see #getScenario()
   * @generated
   */
	EReference getScenario_RealizedScenarios();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.Scenario#getRealizingScenarios <em>Realizing Scenarios</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Scenarios</em>'.
   * @see org.polarsys.capella.core.data.interaction.Scenario#getRealizingScenarios()
   * @see #getScenario()
   * @generated
   */
	EReference getScenario_RealizingScenarios();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.MessageEnd <em>Message End</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Message End</em>'.
   * @see org.polarsys.capella.core.data.interaction.MessageEnd
   * @generated
   */
	EClass getMessageEnd();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.MessageEnd#getMessage <em>Message</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Message</em>'.
   * @see org.polarsys.capella.core.data.interaction.MessageEnd#getMessage()
   * @see #getMessageEnd()
   * @generated
   */
	EReference getMessageEnd_Message();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.Execution <em>Execution</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Execution</em>'.
   * @see org.polarsys.capella.core.data.interaction.Execution
   * @generated
   */
	EClass getExecution();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.Execution#getCovered <em>Covered</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Covered</em>'.
   * @see org.polarsys.capella.core.data.interaction.Execution#getCovered()
   * @see #getExecution()
   * @generated
   */
	EReference getExecution_Covered();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.ExecutionEnd <em>Execution End</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Execution End</em>'.
   * @see org.polarsys.capella.core.data.interaction.ExecutionEnd
   * @generated
   */
	EClass getExecutionEnd();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.ExecutionEnd#getExecution <em>Execution</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Execution</em>'.
   * @see org.polarsys.capella.core.data.interaction.ExecutionEnd#getExecution()
   * @see #getExecutionEnd()
   * @generated
   */
	EReference getExecutionEnd_Execution();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.CreationEvent <em>Creation Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Creation Event</em>'.
   * @see org.polarsys.capella.core.data.interaction.CreationEvent
   * @generated
   */
	EClass getCreationEvent();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.DestructionEvent <em>Destruction Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Destruction Event</em>'.
   * @see org.polarsys.capella.core.data.interaction.DestructionEvent
   * @generated
   */
	EClass getDestructionEvent();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.ExecutionEvent <em>Execution Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Execution Event</em>'.
   * @see org.polarsys.capella.core.data.interaction.ExecutionEvent
   * @generated
   */
	EClass getExecutionEvent();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.InstanceRole <em>Instance Role</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Instance Role</em>'.
   * @see org.polarsys.capella.core.data.interaction.InstanceRole
   * @generated
   */
	EClass getInstanceRole();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.InstanceRole#getAbstractEnds <em>Abstract Ends</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Abstract Ends</em>'.
   * @see org.polarsys.capella.core.data.interaction.InstanceRole#getAbstractEnds()
   * @see #getInstanceRole()
   * @generated
   */
	EReference getInstanceRole_AbstractEnds();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.InstanceRole#getRepresentedInstance <em>Represented Instance</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Represented Instance</em>'.
   * @see org.polarsys.capella.core.data.interaction.InstanceRole#getRepresentedInstance()
   * @see #getInstanceRole()
   * @generated
   */
	EReference getInstanceRole_RepresentedInstance();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.AbstractEnd <em>Abstract End</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract End</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractEnd
   * @generated
   */
	EClass getAbstractEnd();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractEnd#getEvent <em>Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Event</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractEnd#getEvent()
   * @see #getAbstractEnd()
   * @generated
   */
	EReference getAbstractEnd_Event();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractEnd#getCovered <em>Covered</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Covered</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractEnd#getCovered()
   * @see #getAbstractEnd()
   * @generated
   */
	EReference getAbstractEnd_Covered();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.Event <em>Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Event</em>'.
   * @see org.polarsys.capella.core.data.interaction.Event
   * @generated
   */
	EClass getEvent();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.EventReceiptOperation <em>Event Receipt Operation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Event Receipt Operation</em>'.
   * @see org.polarsys.capella.core.data.interaction.EventReceiptOperation
   * @generated
   */
	EClass getEventReceiptOperation();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.EventReceiptOperation#getOperation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Operation</em>'.
   * @see org.polarsys.capella.core.data.interaction.EventReceiptOperation#getOperation()
   * @see #getEventReceiptOperation()
   * @generated
   */
	EReference getEventReceiptOperation_Operation();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.EventSentOperation <em>Event Sent Operation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Event Sent Operation</em>'.
   * @see org.polarsys.capella.core.data.interaction.EventSentOperation
   * @generated
   */
	EClass getEventSentOperation();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.EventSentOperation#getOperation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Operation</em>'.
   * @see org.polarsys.capella.core.data.interaction.EventSentOperation#getOperation()
   * @see #getEventSentOperation()
   * @generated
   */
	EReference getEventSentOperation_Operation();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.MergeLink <em>Merge Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Merge Link</em>'.
   * @see org.polarsys.capella.core.data.interaction.MergeLink
   * @generated
   */
	EClass getMergeLink();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.RefinementLink <em>Refinement Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Refinement Link</em>'.
   * @see org.polarsys.capella.core.data.interaction.RefinementLink
   * @generated
   */
	EClass getRefinementLink();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization <em>Abstract Capability Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Capability Realization</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization
   * @generated
   */
	EClass getAbstractCapabilityRealization();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization#getRealizedCapability <em>Realized Capability</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realized Capability</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization#getRealizedCapability()
   * @see #getAbstractCapabilityRealization()
   * @generated
   */
	EReference getAbstractCapabilityRealization_RealizedCapability();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization#getRealizingCapability <em>Realizing Capability</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realizing Capability</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization#getRealizingCapability()
   * @see #getAbstractCapabilityRealization()
   * @generated
   */
	EReference getAbstractCapabilityRealization_RealizingCapability();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.AbstractCapability <em>Abstract Capability</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Capability</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability
   * @generated
   */
	EClass getAbstractCapability();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getPreCondition <em>Pre Condition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Pre Condition</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getPreCondition()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_PreCondition();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getPostCondition <em>Post Condition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Post Condition</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getPostCondition()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_PostCondition();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getOwnedScenarios <em>Owned Scenarios</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Scenarios</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getOwnedScenarios()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_OwnedScenarios();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getIncomingCapabilityAllocation <em>Incoming Capability Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Incoming Capability Allocation</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getIncomingCapabilityAllocation()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_IncomingCapabilityAllocation();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getOutgoingCapabilityAllocation <em>Outgoing Capability Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Outgoing Capability Allocation</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getOutgoingCapabilityAllocation()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_OutgoingCapabilityAllocation();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getExtends <em>Extends</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Extends</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getExtends()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_Extends();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getExtending <em>Extending</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Extending</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getExtending()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_Extending();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getAbstractCapabilityExtensionPoints <em>Abstract Capability Extension Points</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Abstract Capability Extension Points</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getAbstractCapabilityExtensionPoints()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_AbstractCapabilityExtensionPoints();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getSuperGeneralizations <em>Super Generalizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Super Generalizations</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getSuperGeneralizations()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_SuperGeneralizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getSubGeneralizations <em>Sub Generalizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Sub Generalizations</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getSubGeneralizations()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_SubGeneralizations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getIncludes <em>Includes</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Includes</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getIncludes()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_Includes();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getIncluding <em>Including</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Including</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getIncluding()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_Including();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getSuper <em>Super</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Super</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getSuper()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_Super();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getSub <em>Sub</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Sub</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getSub()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_Sub();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getIncludedAbstractCapabilities <em>Included Abstract Capabilities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Included Abstract Capabilities</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getIncludedAbstractCapabilities()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_IncludedAbstractCapabilities();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getIncludingAbstractCapabilities <em>Including Abstract Capabilities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Including Abstract Capabilities</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getIncludingAbstractCapabilities()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_IncludingAbstractCapabilities();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getExtendedAbstractCapabilities <em>Extended Abstract Capabilities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Extended Abstract Capabilities</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getExtendedAbstractCapabilities()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_ExtendedAbstractCapabilities();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getExtendingAbstractCapabilities <em>Extending Abstract Capabilities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Extending Abstract Capabilities</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getExtendingAbstractCapabilities()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_ExtendingAbstractCapabilities();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getOwnedFunctionalChainAbstractCapabilityInvolvements <em>Owned Functional Chain Abstract Capability Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Functional Chain Abstract Capability Involvements</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getOwnedFunctionalChainAbstractCapabilityInvolvements()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_OwnedFunctionalChainAbstractCapabilityInvolvements();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getOwnedAbstractFunctionAbstractCapabilityInvolvements <em>Owned Abstract Function Abstract Capability Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Abstract Function Abstract Capability Involvements</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getOwnedAbstractFunctionAbstractCapabilityInvolvements()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_OwnedAbstractFunctionAbstractCapabilityInvolvements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getAvailableInStates <em>Available In States</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Available In States</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getAvailableInStates()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_AvailableInStates();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getOwnedAbstractCapabilityRealizations <em>Owned Abstract Capability Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Abstract Capability Realizations</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getOwnedAbstractCapabilityRealizations()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_OwnedAbstractCapabilityRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getInvolvedAbstractFunctions <em>Involved Abstract Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involved Abstract Functions</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getInvolvedAbstractFunctions()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_InvolvedAbstractFunctions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapability#getInvolvedFunctionalChains <em>Involved Functional Chains</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involved Functional Chains</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapability#getInvolvedFunctionalChains()
   * @see #getAbstractCapability()
   * @generated
   */
	EReference getAbstractCapability_InvolvedFunctionalChains();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend <em>Abstract Capability Extend</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Capability Extend</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend
   * @generated
   */
	EClass getAbstractCapabilityExtend();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend#getExtended <em>Extended</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Extended</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend#getExtended()
   * @see #getAbstractCapabilityExtend()
   * @generated
   */
	EReference getAbstractCapabilityExtend_Extended();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend#getExtension <em>Extension</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Extension</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend#getExtension()
   * @see #getAbstractCapabilityExtend()
   * @generated
   */
	EReference getAbstractCapabilityExtend_Extension();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend#getExtensionLocation <em>Extension Location</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Extension Location</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend#getExtensionLocation()
   * @see #getAbstractCapabilityExtend()
   * @generated
   */
	EReference getAbstractCapabilityExtend_ExtensionLocation();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint <em>Abstract Capability Extension Point</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Capability Extension Point</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint
   * @generated
   */
	EClass getAbstractCapabilityExtensionPoint();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint#getAbstractCapability <em>Abstract Capability</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Abstract Capability</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint#getAbstractCapability()
   * @see #getAbstractCapabilityExtensionPoint()
   * @generated
   */
	EReference getAbstractCapabilityExtensionPoint_AbstractCapability();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint#getExtendLinks <em>Extend Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Extend Links</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint#getExtendLinks()
   * @see #getAbstractCapabilityExtensionPoint()
   * @generated
   */
	EReference getAbstractCapabilityExtensionPoint_ExtendLinks();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization <em>Abstract Capability Generalization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Capability Generalization</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization
   * @generated
   */
	EClass getAbstractCapabilityGeneralization();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization#getSuper <em>Super</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Super</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization#getSuper()
   * @see #getAbstractCapabilityGeneralization()
   * @generated
   */
	EReference getAbstractCapabilityGeneralization_Super();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization#getSub <em>Sub</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Sub</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization#getSub()
   * @see #getAbstractCapabilityGeneralization()
   * @generated
   */
	EReference getAbstractCapabilityGeneralization_Sub();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude <em>Abstract Capability Include</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Capability Include</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude
   * @generated
   */
	EClass getAbstractCapabilityInclude();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude#getIncluded <em>Included</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Included</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude#getIncluded()
   * @see #getAbstractCapabilityInclude()
   * @generated
   */
	EReference getAbstractCapabilityInclude_Included();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude#getInclusion <em>Inclusion</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Inclusion</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude#getInclusion()
   * @see #getAbstractCapabilityInclude()
   * @generated
   */
	EReference getAbstractCapabilityInclude_Inclusion();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.InteractionFragment <em>Fragment</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fragment</em>'.
   * @see org.polarsys.capella.core.data.interaction.InteractionFragment
   * @generated
   */
	EClass getInteractionFragment();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.InteractionFragment#getCoveredInstanceRoles <em>Covered Instance Roles</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Covered Instance Roles</em>'.
   * @see org.polarsys.capella.core.data.interaction.InteractionFragment#getCoveredInstanceRoles()
   * @see #getInteractionFragment()
   * @generated
   */
	EReference getInteractionFragment_CoveredInstanceRoles();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.InteractionState <em>State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>State</em>'.
   * @see org.polarsys.capella.core.data.interaction.InteractionState
   * @generated
   */
	EClass getInteractionState();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractState <em>Related Abstract State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Related Abstract State</em>'.
   * @see org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractState()
   * @see #getInteractionState()
   * @deprecated See {@link org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractState() model documentation} for details.
   * @generated
   */
	@Deprecated
	EReference getInteractionState_RelatedAbstractState();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractFunction <em>Related Abstract Function</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Related Abstract Function</em>'.
   * @see org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractFunction()
   * @see #getInteractionState()
   * @deprecated See {@link org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractFunction() model documentation} for details.
   * @generated
   */
	@Deprecated
	EReference getInteractionState_RelatedAbstractFunction();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.InteractionState#getCovered <em>Covered</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Covered</em>'.
   * @see org.polarsys.capella.core.data.interaction.InteractionState#getCovered()
   * @see #getInteractionState()
   * @generated
   */
	EReference getInteractionState_Covered();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.InteractionUse <em>Use</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Use</em>'.
   * @see org.polarsys.capella.core.data.interaction.InteractionUse
   * @generated
   */
	EClass getInteractionUse();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.InteractionUse#getReferencedScenario <em>Referenced Scenario</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Scenario</em>'.
   * @see org.polarsys.capella.core.data.interaction.InteractionUse#getReferencedScenario()
   * @see #getInteractionUse()
   * @generated
   */
	EReference getInteractionUse_ReferencedScenario();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.InteractionUse#getActualGates <em>Actual Gates</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Actual Gates</em>'.
   * @see org.polarsys.capella.core.data.interaction.InteractionUse#getActualGates()
   * @see #getInteractionUse()
   * @generated
   */
	EReference getInteractionUse_ActualGates();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.CombinedFragment <em>Combined Fragment</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Combined Fragment</em>'.
   * @see org.polarsys.capella.core.data.interaction.CombinedFragment
   * @generated
   */
	EClass getCombinedFragment();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.interaction.CombinedFragment#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operator</em>'.
   * @see org.polarsys.capella.core.data.interaction.CombinedFragment#getOperator()
   * @see #getCombinedFragment()
   * @generated
   */
	EAttribute getCombinedFragment_Operator();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.CombinedFragment#getReferencedOperands <em>Referenced Operands</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Referenced Operands</em>'.
   * @see org.polarsys.capella.core.data.interaction.CombinedFragment#getReferencedOperands()
   * @see #getCombinedFragment()
   * @generated
   */
	EReference getCombinedFragment_ReferencedOperands();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.CombinedFragment#getExpressionGates <em>Expression Gates</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Expression Gates</em>'.
   * @see org.polarsys.capella.core.data.interaction.CombinedFragment#getExpressionGates()
   * @see #getCombinedFragment()
   * @generated
   */
	EReference getCombinedFragment_ExpressionGates();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.Gate <em>Gate</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Gate</em>'.
   * @see org.polarsys.capella.core.data.interaction.Gate
   * @generated
   */
	EClass getGate();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.InteractionOperand <em>Operand</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operand</em>'.
   * @see org.polarsys.capella.core.data.interaction.InteractionOperand
   * @generated
   */
	EClass getInteractionOperand();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.InteractionOperand#getGuard <em>Guard</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Guard</em>'.
   * @see org.polarsys.capella.core.data.interaction.InteractionOperand#getGuard()
   * @see #getInteractionOperand()
   * @generated
   */
	EReference getInteractionOperand_Guard();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.interaction.InteractionOperand#getReferencedInteractionFragments <em>Referenced Interaction Fragments</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Referenced Interaction Fragments</em>'.
   * @see org.polarsys.capella.core.data.interaction.InteractionOperand#getReferencedInteractionFragments()
   * @see #getInteractionOperand()
   * @generated
   */
	EReference getInteractionOperand_ReferencedInteractionFragments();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.TimeLapse <em>Time Lapse</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Time Lapse</em>'.
   * @see org.polarsys.capella.core.data.interaction.TimeLapse
   * @generated
   */
	EClass getTimeLapse();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.TimeLapse#getStart <em>Start</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Start</em>'.
   * @see org.polarsys.capella.core.data.interaction.TimeLapse#getStart()
   * @see #getTimeLapse()
   * @generated
   */
	EReference getTimeLapse_Start();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.TimeLapse#getFinish <em>Finish</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Finish</em>'.
   * @see org.polarsys.capella.core.data.interaction.TimeLapse#getFinish()
   * @see #getTimeLapse()
   * @generated
   */
	EReference getTimeLapse_Finish();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.AbstractFragment <em>Abstract Fragment</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Fragment</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractFragment
   * @generated
   */
	EClass getAbstractFragment();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.interaction.AbstractFragment#getOwnedGates <em>Owned Gates</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Gates</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractFragment#getOwnedGates()
   * @see #getAbstractFragment()
   * @generated
   */
	EReference getAbstractFragment_OwnedGates();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.FragmentEnd <em>Fragment End</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fragment End</em>'.
   * @see org.polarsys.capella.core.data.interaction.FragmentEnd
   * @generated
   */
	EClass getFragmentEnd();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.FragmentEnd#getAbstractFragment <em>Abstract Fragment</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Abstract Fragment</em>'.
   * @see org.polarsys.capella.core.data.interaction.FragmentEnd#getAbstractFragment()
   * @see #getFragmentEnd()
   * @generated
   */
	EReference getFragmentEnd_AbstractFragment();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement <em>Functional Chain Abstract Capability Involvement</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Functional Chain Abstract Capability Involvement</em>'.
   * @see org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement
   * @generated
   */
	EClass getFunctionalChainAbstractCapabilityInvolvement();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement#getCapability <em>Capability</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Capability</em>'.
   * @see org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement#getCapability()
   * @see #getFunctionalChainAbstractCapabilityInvolvement()
   * @generated
   */
	EReference getFunctionalChainAbstractCapabilityInvolvement_Capability();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement#getFunctionalChain <em>Functional Chain</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Functional Chain</em>'.
   * @see org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement#getFunctionalChain()
   * @see #getFunctionalChainAbstractCapabilityInvolvement()
   * @generated
   */
	EReference getFunctionalChainAbstractCapabilityInvolvement_FunctionalChain();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement <em>Abstract Function Abstract Capability Involvement</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Function Abstract Capability Involvement</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement
   * @generated
   */
	EClass getAbstractFunctionAbstractCapabilityInvolvement();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement#getCapability <em>Capability</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Capability</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement#getCapability()
   * @see #getAbstractFunctionAbstractCapabilityInvolvement()
   * @generated
   */
	EReference getAbstractFunctionAbstractCapabilityInvolvement_Capability();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement#getFunction <em>Function</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Function</em>'.
   * @see org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement#getFunction()
   * @see #getAbstractFunctionAbstractCapabilityInvolvement()
   * @generated
   */
	EReference getAbstractFunctionAbstractCapabilityInvolvement_Function();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.ScenarioRealization <em>Scenario Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Scenario Realization</em>'.
   * @see org.polarsys.capella.core.data.interaction.ScenarioRealization
   * @generated
   */
	EClass getScenarioRealization();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.ScenarioRealization#getRealizedScenario <em>Realized Scenario</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realized Scenario</em>'.
   * @see org.polarsys.capella.core.data.interaction.ScenarioRealization#getRealizedScenario()
   * @see #getScenarioRealization()
   * @generated
   */
	EReference getScenarioRealization_RealizedScenario();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.ScenarioRealization#getRealizingScenario <em>Realizing Scenario</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realizing Scenario</em>'.
   * @see org.polarsys.capella.core.data.interaction.ScenarioRealization#getRealizingScenario()
   * @see #getScenarioRealization()
   * @generated
   */
	EReference getScenarioRealization_RealizingScenario();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.StateFragment <em>State Fragment</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>State Fragment</em>'.
   * @see org.polarsys.capella.core.data.interaction.StateFragment
   * @generated
   */
	EClass getStateFragment();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.StateFragment#getRelatedAbstractState <em>Related Abstract State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Related Abstract State</em>'.
   * @see org.polarsys.capella.core.data.interaction.StateFragment#getRelatedAbstractState()
   * @see #getStateFragment()
   * @generated
   */
	EReference getStateFragment_RelatedAbstractState();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.StateFragment#getRelatedAbstractFunction <em>Related Abstract Function</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Related Abstract Function</em>'.
   * @see org.polarsys.capella.core.data.interaction.StateFragment#getRelatedAbstractFunction()
   * @see #getStateFragment()
   * @generated
   */
	EReference getStateFragment_RelatedAbstractFunction();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.ArmTimerEvent <em>Arm Timer Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Arm Timer Event</em>'.
   * @see org.polarsys.capella.core.data.interaction.ArmTimerEvent
   * @generated
   */
	EClass getArmTimerEvent();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.CancelTimerEvent <em>Cancel Timer Event</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Cancel Timer Event</em>'.
   * @see org.polarsys.capella.core.data.interaction.CancelTimerEvent
   * @generated
   */
	EClass getCancelTimerEvent();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.ConstraintDuration <em>Constraint Duration</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Constraint Duration</em>'.
   * @see org.polarsys.capella.core.data.interaction.ConstraintDuration
   * @generated
   */
	EClass getConstraintDuration();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.interaction.ConstraintDuration#getDuration <em>Duration</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Duration</em>'.
   * @see org.polarsys.capella.core.data.interaction.ConstraintDuration#getDuration()
   * @see #getConstraintDuration()
   * @generated
   */
	EAttribute getConstraintDuration_Duration();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.ConstraintDuration#getStart <em>Start</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Start</em>'.
   * @see org.polarsys.capella.core.data.interaction.ConstraintDuration#getStart()
   * @see #getConstraintDuration()
   * @generated
   */
	EReference getConstraintDuration_Start();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.ConstraintDuration#getFinish <em>Finish</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Finish</em>'.
   * @see org.polarsys.capella.core.data.interaction.ConstraintDuration#getFinish()
   * @see #getConstraintDuration()
   * @generated
   */
	EReference getConstraintDuration_Finish();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.interaction.SequenceMessageValuation <em>Sequence Message Valuation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Sequence Message Valuation</em>'.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessageValuation
   * @generated
   */
	EClass getSequenceMessageValuation();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.SequenceMessageValuation#getExchangeItemElement <em>Exchange Item Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Exchange Item Element</em>'.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessageValuation#getExchangeItemElement()
   * @see #getSequenceMessageValuation()
   * @generated
   */
	EReference getSequenceMessageValuation_ExchangeItemElement();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.interaction.SequenceMessageValuation#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Value</em>'.
   * @see org.polarsys.capella.core.data.interaction.SequenceMessageValuation#getValue()
   * @see #getSequenceMessageValuation()
   * @generated
   */
	EReference getSequenceMessageValuation_Value();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.interaction.MessageKind <em>Message Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Message Kind</em>'.
   * @see org.polarsys.capella.core.data.interaction.MessageKind
   * @generated
   */
	EEnum getMessageKind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.interaction.ScenarioKind <em>Scenario Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Scenario Kind</em>'.
   * @see org.polarsys.capella.core.data.interaction.ScenarioKind
   * @generated
   */
	EEnum getScenarioKind();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.interaction.InteractionOperatorKind <em>Operator Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Operator Kind</em>'.
   * @see org.polarsys.capella.core.data.interaction.InteractionOperatorKind
   * @generated
   */
	EEnum getInteractionOperatorKind();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	InteractionFactory getInteractionFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.SequenceMessageImpl <em>Sequence Message</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.SequenceMessageImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getSequenceMessage()
     * @generated
     */
		EClass SEQUENCE_MESSAGE = eINSTANCE.getSequenceMessage();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute SEQUENCE_MESSAGE__KIND = eINSTANCE.getSequenceMessage_Kind();

		/**
     * The meta object literal for the '<em><b>Exchange Context</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_MESSAGE__EXCHANGE_CONTEXT = eINSTANCE.getSequenceMessage_ExchangeContext();

		/**
     * The meta object literal for the '<em><b>Sending End</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_MESSAGE__SENDING_END = eINSTANCE.getSequenceMessage_SendingEnd();

		/**
     * The meta object literal for the '<em><b>Receiving End</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_MESSAGE__RECEIVING_END = eINSTANCE.getSequenceMessage_ReceivingEnd();

		/**
     * The meta object literal for the '<em><b>Invoked Operation</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_MESSAGE__INVOKED_OPERATION = eINSTANCE.getSequenceMessage_InvokedOperation();

		/**
     * The meta object literal for the '<em><b>Exchanged Items</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_MESSAGE__EXCHANGED_ITEMS = eINSTANCE.getSequenceMessage_ExchangedItems();

		/**
     * The meta object literal for the '<em><b>Sending Part</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_MESSAGE__SENDING_PART = eINSTANCE.getSequenceMessage_SendingPart();

		/**
     * The meta object literal for the '<em><b>Receiving Part</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_MESSAGE__RECEIVING_PART = eINSTANCE.getSequenceMessage_ReceivingPart();

		/**
     * The meta object literal for the '<em><b>Sending Function</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_MESSAGE__SENDING_FUNCTION = eINSTANCE.getSequenceMessage_SendingFunction();

		/**
     * The meta object literal for the '<em><b>Receiving Function</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_MESSAGE__RECEIVING_FUNCTION = eINSTANCE.getSequenceMessage_ReceivingFunction();

		/**
     * The meta object literal for the '<em><b>Owned Sequence Message Valuations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_MESSAGE__OWNED_SEQUENCE_MESSAGE_VALUATIONS = eINSTANCE.getSequenceMessage_OwnedSequenceMessageValuations();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.ScenarioImpl <em>Scenario</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.ScenarioImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getScenario()
     * @generated
     */
		EClass SCENARIO = eINSTANCE.getScenario();

		/**
     * The meta object literal for the '<em><b>Pre Condition</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO__PRE_CONDITION = eINSTANCE.getScenario_PreCondition();

		/**
     * The meta object literal for the '<em><b>Post Condition</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO__POST_CONDITION = eINSTANCE.getScenario_PostCondition();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute SCENARIO__KIND = eINSTANCE.getScenario_Kind();

		/**
     * The meta object literal for the '<em><b>Merged</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute SCENARIO__MERGED = eINSTANCE.getScenario_Merged();

		/**
     * The meta object literal for the '<em><b>Owned Instance Roles</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO__OWNED_INSTANCE_ROLES = eINSTANCE.getScenario_OwnedInstanceRoles();

		/**
     * The meta object literal for the '<em><b>Owned Messages</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO__OWNED_MESSAGES = eINSTANCE.getScenario_OwnedMessages();

		/**
     * The meta object literal for the '<em><b>Owned Interaction Fragments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO__OWNED_INTERACTION_FRAGMENTS = eINSTANCE.getScenario_OwnedInteractionFragments();

		/**
     * The meta object literal for the '<em><b>Owned Time Lapses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO__OWNED_TIME_LAPSES = eINSTANCE.getScenario_OwnedTimeLapses();

		/**
     * The meta object literal for the '<em><b>Owned Events</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO__OWNED_EVENTS = eINSTANCE.getScenario_OwnedEvents();

		/**
     * The meta object literal for the '<em><b>Owned Formal Gates</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO__OWNED_FORMAL_GATES = eINSTANCE.getScenario_OwnedFormalGates();

		/**
     * The meta object literal for the '<em><b>Owned Scenario Realization</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO__OWNED_SCENARIO_REALIZATION = eINSTANCE.getScenario_OwnedScenarioRealization();

		/**
     * The meta object literal for the '<em><b>Owned Constraint Durations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO__OWNED_CONSTRAINT_DURATIONS = eINSTANCE.getScenario_OwnedConstraintDurations();

		/**
     * The meta object literal for the '<em><b>Contained Functions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO__CONTAINED_FUNCTIONS = eINSTANCE.getScenario_ContainedFunctions();

		/**
     * The meta object literal for the '<em><b>Contained Parts</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO__CONTAINED_PARTS = eINSTANCE.getScenario_ContainedParts();

		/**
     * The meta object literal for the '<em><b>Referenced Scenarios</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO__REFERENCED_SCENARIOS = eINSTANCE.getScenario_ReferencedScenarios();

		/**
     * The meta object literal for the '<em><b>Realized Scenarios</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO__REALIZED_SCENARIOS = eINSTANCE.getScenario_RealizedScenarios();

		/**
     * The meta object literal for the '<em><b>Realizing Scenarios</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO__REALIZING_SCENARIOS = eINSTANCE.getScenario_RealizingScenarios();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.MessageEndImpl <em>Message End</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.MessageEndImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getMessageEnd()
     * @generated
     */
		EClass MESSAGE_END = eINSTANCE.getMessageEnd();

		/**
     * The meta object literal for the '<em><b>Message</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MESSAGE_END__MESSAGE = eINSTANCE.getMessageEnd_Message();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.ExecutionImpl <em>Execution</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.ExecutionImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getExecution()
     * @generated
     */
		EClass EXECUTION = eINSTANCE.getExecution();

		/**
     * The meta object literal for the '<em><b>Covered</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXECUTION__COVERED = eINSTANCE.getExecution_Covered();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.ExecutionEndImpl <em>Execution End</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.ExecutionEndImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getExecutionEnd()
     * @generated
     */
		EClass EXECUTION_END = eINSTANCE.getExecutionEnd();

		/**
     * The meta object literal for the '<em><b>Execution</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXECUTION_END__EXECUTION = eINSTANCE.getExecutionEnd_Execution();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.CreationEventImpl <em>Creation Event</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.CreationEventImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getCreationEvent()
     * @generated
     */
		EClass CREATION_EVENT = eINSTANCE.getCreationEvent();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.DestructionEventImpl <em>Destruction Event</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.DestructionEventImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getDestructionEvent()
     * @generated
     */
		EClass DESTRUCTION_EVENT = eINSTANCE.getDestructionEvent();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.ExecutionEventImpl <em>Execution Event</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.ExecutionEventImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getExecutionEvent()
     * @generated
     */
		EClass EXECUTION_EVENT = eINSTANCE.getExecutionEvent();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.InstanceRoleImpl <em>Instance Role</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.InstanceRoleImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getInstanceRole()
     * @generated
     */
		EClass INSTANCE_ROLE = eINSTANCE.getInstanceRole();

		/**
     * The meta object literal for the '<em><b>Abstract Ends</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INSTANCE_ROLE__ABSTRACT_ENDS = eINSTANCE.getInstanceRole_AbstractEnds();

		/**
     * The meta object literal for the '<em><b>Represented Instance</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INSTANCE_ROLE__REPRESENTED_INSTANCE = eINSTANCE.getInstanceRole_RepresentedInstance();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractEndImpl <em>Abstract End</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.AbstractEndImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractEnd()
     * @generated
     */
		EClass ABSTRACT_END = eINSTANCE.getAbstractEnd();

		/**
     * The meta object literal for the '<em><b>Event</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_END__EVENT = eINSTANCE.getAbstractEnd_Event();

		/**
     * The meta object literal for the '<em><b>Covered</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_END__COVERED = eINSTANCE.getAbstractEnd_Covered();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.EventImpl <em>Event</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.EventImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getEvent()
     * @generated
     */
		EClass EVENT = eINSTANCE.getEvent();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.EventReceiptOperationImpl <em>Event Receipt Operation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.EventReceiptOperationImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getEventReceiptOperation()
     * @generated
     */
		EClass EVENT_RECEIPT_OPERATION = eINSTANCE.getEventReceiptOperation();

		/**
     * The meta object literal for the '<em><b>Operation</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EVENT_RECEIPT_OPERATION__OPERATION = eINSTANCE.getEventReceiptOperation_Operation();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.EventSentOperationImpl <em>Event Sent Operation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.EventSentOperationImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getEventSentOperation()
     * @generated
     */
		EClass EVENT_SENT_OPERATION = eINSTANCE.getEventSentOperation();

		/**
     * The meta object literal for the '<em><b>Operation</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EVENT_SENT_OPERATION__OPERATION = eINSTANCE.getEventSentOperation_Operation();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.MergeLinkImpl <em>Merge Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.MergeLinkImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getMergeLink()
     * @generated
     */
		EClass MERGE_LINK = eINSTANCE.getMergeLink();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.RefinementLinkImpl <em>Refinement Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.RefinementLinkImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getRefinementLink()
     * @generated
     */
		EClass REFINEMENT_LINK = eINSTANCE.getRefinementLink();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityRealizationImpl <em>Abstract Capability Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityRealizationImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractCapabilityRealization()
     * @generated
     */
		EClass ABSTRACT_CAPABILITY_REALIZATION = eINSTANCE.getAbstractCapabilityRealization();

		/**
     * The meta object literal for the '<em><b>Realized Capability</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY_REALIZATION__REALIZED_CAPABILITY = eINSTANCE.getAbstractCapabilityRealization_RealizedCapability();

		/**
     * The meta object literal for the '<em><b>Realizing Capability</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY_REALIZATION__REALIZING_CAPABILITY = eINSTANCE.getAbstractCapabilityRealization_RealizingCapability();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl <em>Abstract Capability</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractCapability()
     * @generated
     */
		EClass ABSTRACT_CAPABILITY = eINSTANCE.getAbstractCapability();

		/**
     * The meta object literal for the '<em><b>Pre Condition</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__PRE_CONDITION = eINSTANCE.getAbstractCapability_PreCondition();

		/**
     * The meta object literal for the '<em><b>Post Condition</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__POST_CONDITION = eINSTANCE.getAbstractCapability_PostCondition();

		/**
     * The meta object literal for the '<em><b>Owned Scenarios</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__OWNED_SCENARIOS = eINSTANCE.getAbstractCapability_OwnedScenarios();

		/**
     * The meta object literal for the '<em><b>Incoming Capability Allocation</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__INCOMING_CAPABILITY_ALLOCATION = eINSTANCE.getAbstractCapability_IncomingCapabilityAllocation();

		/**
     * The meta object literal for the '<em><b>Outgoing Capability Allocation</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__OUTGOING_CAPABILITY_ALLOCATION = eINSTANCE.getAbstractCapability_OutgoingCapabilityAllocation();

		/**
     * The meta object literal for the '<em><b>Extends</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__EXTENDS = eINSTANCE.getAbstractCapability_Extends();

		/**
     * The meta object literal for the '<em><b>Extending</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__EXTENDING = eINSTANCE.getAbstractCapability_Extending();

		/**
     * The meta object literal for the '<em><b>Abstract Capability Extension Points</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__ABSTRACT_CAPABILITY_EXTENSION_POINTS = eINSTANCE.getAbstractCapability_AbstractCapabilityExtensionPoints();

		/**
     * The meta object literal for the '<em><b>Super Generalizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS = eINSTANCE.getAbstractCapability_SuperGeneralizations();

		/**
     * The meta object literal for the '<em><b>Sub Generalizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__SUB_GENERALIZATIONS = eINSTANCE.getAbstractCapability_SubGeneralizations();

		/**
     * The meta object literal for the '<em><b>Includes</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__INCLUDES = eINSTANCE.getAbstractCapability_Includes();

		/**
     * The meta object literal for the '<em><b>Including</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__INCLUDING = eINSTANCE.getAbstractCapability_Including();

		/**
     * The meta object literal for the '<em><b>Super</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__SUPER = eINSTANCE.getAbstractCapability_Super();

		/**
     * The meta object literal for the '<em><b>Sub</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__SUB = eINSTANCE.getAbstractCapability_Sub();

		/**
     * The meta object literal for the '<em><b>Included Abstract Capabilities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__INCLUDED_ABSTRACT_CAPABILITIES = eINSTANCE.getAbstractCapability_IncludedAbstractCapabilities();

		/**
     * The meta object literal for the '<em><b>Including Abstract Capabilities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__INCLUDING_ABSTRACT_CAPABILITIES = eINSTANCE.getAbstractCapability_IncludingAbstractCapabilities();

		/**
     * The meta object literal for the '<em><b>Extended Abstract Capabilities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES = eINSTANCE.getAbstractCapability_ExtendedAbstractCapabilities();

		/**
     * The meta object literal for the '<em><b>Extending Abstract Capabilities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__EXTENDING_ABSTRACT_CAPABILITIES = eINSTANCE.getAbstractCapability_ExtendingAbstractCapabilities();

		/**
     * The meta object literal for the '<em><b>Owned Functional Chain Abstract Capability Involvements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENTS = eINSTANCE.getAbstractCapability_OwnedFunctionalChainAbstractCapabilityInvolvements();

		/**
     * The meta object literal for the '<em><b>Owned Abstract Function Abstract Capability Involvements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__OWNED_ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENTS = eINSTANCE.getAbstractCapability_OwnedAbstractFunctionAbstractCapabilityInvolvements();

		/**
     * The meta object literal for the '<em><b>Available In States</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES = eINSTANCE.getAbstractCapability_AvailableInStates();

		/**
     * The meta object literal for the '<em><b>Owned Abstract Capability Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS = eINSTANCE.getAbstractCapability_OwnedAbstractCapabilityRealizations();

		/**
     * The meta object literal for the '<em><b>Involved Abstract Functions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__INVOLVED_ABSTRACT_FUNCTIONS = eINSTANCE.getAbstractCapability_InvolvedAbstractFunctions();

		/**
     * The meta object literal for the '<em><b>Involved Functional Chains</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY__INVOLVED_FUNCTIONAL_CHAINS = eINSTANCE.getAbstractCapability_InvolvedFunctionalChains();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtendImpl <em>Abstract Capability Extend</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtendImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractCapabilityExtend()
     * @generated
     */
		EClass ABSTRACT_CAPABILITY_EXTEND = eINSTANCE.getAbstractCapabilityExtend();

		/**
     * The meta object literal for the '<em><b>Extended</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY_EXTEND__EXTENDED = eINSTANCE.getAbstractCapabilityExtend_Extended();

		/**
     * The meta object literal for the '<em><b>Extension</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY_EXTEND__EXTENSION = eINSTANCE.getAbstractCapabilityExtend_Extension();

		/**
     * The meta object literal for the '<em><b>Extension Location</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION = eINSTANCE.getAbstractCapabilityExtend_ExtensionLocation();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtensionPointImpl <em>Abstract Capability Extension Point</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtensionPointImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractCapabilityExtensionPoint()
     * @generated
     */
		EClass ABSTRACT_CAPABILITY_EXTENSION_POINT = eINSTANCE.getAbstractCapabilityExtensionPoint();

		/**
     * The meta object literal for the '<em><b>Abstract Capability</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY_EXTENSION_POINT__ABSTRACT_CAPABILITY = eINSTANCE.getAbstractCapabilityExtensionPoint_AbstractCapability();

		/**
     * The meta object literal for the '<em><b>Extend Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS = eINSTANCE.getAbstractCapabilityExtensionPoint_ExtendLinks();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityGeneralizationImpl <em>Abstract Capability Generalization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityGeneralizationImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractCapabilityGeneralization()
     * @generated
     */
		EClass ABSTRACT_CAPABILITY_GENERALIZATION = eINSTANCE.getAbstractCapabilityGeneralization();

		/**
     * The meta object literal for the '<em><b>Super</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY_GENERALIZATION__SUPER = eINSTANCE.getAbstractCapabilityGeneralization_Super();

		/**
     * The meta object literal for the '<em><b>Sub</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY_GENERALIZATION__SUB = eINSTANCE.getAbstractCapabilityGeneralization_Sub();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityIncludeImpl <em>Abstract Capability Include</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityIncludeImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractCapabilityInclude()
     * @generated
     */
		EClass ABSTRACT_CAPABILITY_INCLUDE = eINSTANCE.getAbstractCapabilityInclude();

		/**
     * The meta object literal for the '<em><b>Included</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY_INCLUDE__INCLUDED = eINSTANCE.getAbstractCapabilityInclude_Included();

		/**
     * The meta object literal for the '<em><b>Inclusion</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CAPABILITY_INCLUDE__INCLUSION = eINSTANCE.getAbstractCapabilityInclude_Inclusion();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.InteractionFragmentImpl <em>Fragment</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionFragmentImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getInteractionFragment()
     * @generated
     */
		EClass INTERACTION_FRAGMENT = eINSTANCE.getInteractionFragment();

		/**
     * The meta object literal for the '<em><b>Covered Instance Roles</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES = eINSTANCE.getInteractionFragment_CoveredInstanceRoles();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.InteractionStateImpl <em>State</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionStateImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getInteractionState()
     * @generated
     */
		EClass INTERACTION_STATE = eINSTANCE.getInteractionState();

		/**
     * The meta object literal for the '<em><b>Related Abstract State</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @deprecated See {@link org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractState() model documentation} for details.
     * @generated
     */
		@Deprecated
		EReference INTERACTION_STATE__RELATED_ABSTRACT_STATE = eINSTANCE.getInteractionState_RelatedAbstractState();

		/**
     * The meta object literal for the '<em><b>Related Abstract Function</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @deprecated See {@link org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractFunction() model documentation} for details.
     * @generated
     */
		@Deprecated
		EReference INTERACTION_STATE__RELATED_ABSTRACT_FUNCTION = eINSTANCE.getInteractionState_RelatedAbstractFunction();

		/**
     * The meta object literal for the '<em><b>Covered</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERACTION_STATE__COVERED = eINSTANCE.getInteractionState_Covered();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.InteractionUseImpl <em>Use</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionUseImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getInteractionUse()
     * @generated
     */
		EClass INTERACTION_USE = eINSTANCE.getInteractionUse();

		/**
     * The meta object literal for the '<em><b>Referenced Scenario</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERACTION_USE__REFERENCED_SCENARIO = eINSTANCE.getInteractionUse_ReferencedScenario();

		/**
     * The meta object literal for the '<em><b>Actual Gates</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERACTION_USE__ACTUAL_GATES = eINSTANCE.getInteractionUse_ActualGates();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.CombinedFragmentImpl <em>Combined Fragment</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.CombinedFragmentImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getCombinedFragment()
     * @generated
     */
		EClass COMBINED_FRAGMENT = eINSTANCE.getCombinedFragment();

		/**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute COMBINED_FRAGMENT__OPERATOR = eINSTANCE.getCombinedFragment_Operator();

		/**
     * The meta object literal for the '<em><b>Referenced Operands</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMBINED_FRAGMENT__REFERENCED_OPERANDS = eINSTANCE.getCombinedFragment_ReferencedOperands();

		/**
     * The meta object literal for the '<em><b>Expression Gates</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMBINED_FRAGMENT__EXPRESSION_GATES = eINSTANCE.getCombinedFragment_ExpressionGates();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.GateImpl <em>Gate</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.GateImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getGate()
     * @generated
     */
		EClass GATE = eINSTANCE.getGate();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.InteractionOperandImpl <em>Operand</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionOperandImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getInteractionOperand()
     * @generated
     */
		EClass INTERACTION_OPERAND = eINSTANCE.getInteractionOperand();

		/**
     * The meta object literal for the '<em><b>Guard</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERACTION_OPERAND__GUARD = eINSTANCE.getInteractionOperand_Guard();

		/**
     * The meta object literal for the '<em><b>Referenced Interaction Fragments</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERACTION_OPERAND__REFERENCED_INTERACTION_FRAGMENTS = eINSTANCE.getInteractionOperand_ReferencedInteractionFragments();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.TimeLapseImpl <em>Time Lapse</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.TimeLapseImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getTimeLapse()
     * @generated
     */
		EClass TIME_LAPSE = eINSTANCE.getTimeLapse();

		/**
     * The meta object literal for the '<em><b>Start</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TIME_LAPSE__START = eINSTANCE.getTimeLapse_Start();

		/**
     * The meta object literal for the '<em><b>Finish</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TIME_LAPSE__FINISH = eINSTANCE.getTimeLapse_Finish();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractFragmentImpl <em>Abstract Fragment</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.AbstractFragmentImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractFragment()
     * @generated
     */
		EClass ABSTRACT_FRAGMENT = eINSTANCE.getAbstractFragment();

		/**
     * The meta object literal for the '<em><b>Owned Gates</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FRAGMENT__OWNED_GATES = eINSTANCE.getAbstractFragment_OwnedGates();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.FragmentEndImpl <em>Fragment End</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.FragmentEndImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getFragmentEnd()
     * @generated
     */
		EClass FRAGMENT_END = eINSTANCE.getFragmentEnd();

		/**
     * The meta object literal for the '<em><b>Abstract Fragment</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FRAGMENT_END__ABSTRACT_FRAGMENT = eINSTANCE.getFragmentEnd_AbstractFragment();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.FunctionalChainAbstractCapabilityInvolvementImpl <em>Functional Chain Abstract Capability Involvement</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.FunctionalChainAbstractCapabilityInvolvementImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getFunctionalChainAbstractCapabilityInvolvement()
     * @generated
     */
		EClass FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT = eINSTANCE.getFunctionalChainAbstractCapabilityInvolvement();

		/**
     * The meta object literal for the '<em><b>Capability</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__CAPABILITY = eINSTANCE.getFunctionalChainAbstractCapabilityInvolvement_Capability();

		/**
     * The meta object literal for the '<em><b>Functional Chain</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__FUNCTIONAL_CHAIN = eINSTANCE.getFunctionalChainAbstractCapabilityInvolvement_FunctionalChain();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.AbstractFunctionAbstractCapabilityInvolvementImpl <em>Abstract Function Abstract Capability Involvement</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.AbstractFunctionAbstractCapabilityInvolvementImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getAbstractFunctionAbstractCapabilityInvolvement()
     * @generated
     */
		EClass ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT = eINSTANCE.getAbstractFunctionAbstractCapabilityInvolvement();

		/**
     * The meta object literal for the '<em><b>Capability</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__CAPABILITY = eINSTANCE.getAbstractFunctionAbstractCapabilityInvolvement_Capability();

		/**
     * The meta object literal for the '<em><b>Function</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__FUNCTION = eINSTANCE.getAbstractFunctionAbstractCapabilityInvolvement_Function();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.ScenarioRealizationImpl <em>Scenario Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.ScenarioRealizationImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getScenarioRealization()
     * @generated
     */
		EClass SCENARIO_REALIZATION = eINSTANCE.getScenarioRealization();

		/**
     * The meta object literal for the '<em><b>Realized Scenario</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO_REALIZATION__REALIZED_SCENARIO = eINSTANCE.getScenarioRealization_RealizedScenario();

		/**
     * The meta object literal for the '<em><b>Realizing Scenario</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SCENARIO_REALIZATION__REALIZING_SCENARIO = eINSTANCE.getScenarioRealization_RealizingScenario();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.StateFragmentImpl <em>State Fragment</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.StateFragmentImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getStateFragment()
     * @generated
     */
		EClass STATE_FRAGMENT = eINSTANCE.getStateFragment();

		/**
     * The meta object literal for the '<em><b>Related Abstract State</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_FRAGMENT__RELATED_ABSTRACT_STATE = eINSTANCE.getStateFragment_RelatedAbstractState();

		/**
     * The meta object literal for the '<em><b>Related Abstract Function</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION = eINSTANCE.getStateFragment_RelatedAbstractFunction();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.ArmTimerEventImpl <em>Arm Timer Event</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.ArmTimerEventImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getArmTimerEvent()
     * @generated
     */
		EClass ARM_TIMER_EVENT = eINSTANCE.getArmTimerEvent();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.CancelTimerEventImpl <em>Cancel Timer Event</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.CancelTimerEventImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getCancelTimerEvent()
     * @generated
     */
		EClass CANCEL_TIMER_EVENT = eINSTANCE.getCancelTimerEvent();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.ConstraintDurationImpl <em>Constraint Duration</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.ConstraintDurationImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getConstraintDuration()
     * @generated
     */
		EClass CONSTRAINT_DURATION = eINSTANCE.getConstraintDuration();

		/**
     * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CONSTRAINT_DURATION__DURATION = eINSTANCE.getConstraintDuration_Duration();

		/**
     * The meta object literal for the '<em><b>Start</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONSTRAINT_DURATION__START = eINSTANCE.getConstraintDuration_Start();

		/**
     * The meta object literal for the '<em><b>Finish</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONSTRAINT_DURATION__FINISH = eINSTANCE.getConstraintDuration_Finish();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.impl.SequenceMessageValuationImpl <em>Sequence Message Valuation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.impl.SequenceMessageValuationImpl
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getSequenceMessageValuation()
     * @generated
     */
		EClass SEQUENCE_MESSAGE_VALUATION = eINSTANCE.getSequenceMessageValuation();

		/**
     * The meta object literal for the '<em><b>Exchange Item Element</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_MESSAGE_VALUATION__EXCHANGE_ITEM_ELEMENT = eINSTANCE.getSequenceMessageValuation_ExchangeItemElement();

		/**
     * The meta object literal for the '<em><b>Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SEQUENCE_MESSAGE_VALUATION__VALUE = eINSTANCE.getSequenceMessageValuation_Value();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.MessageKind <em>Message Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.MessageKind
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getMessageKind()
     * @generated
     */
		EEnum MESSAGE_KIND = eINSTANCE.getMessageKind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.ScenarioKind <em>Scenario Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.ScenarioKind
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getScenarioKind()
     * @generated
     */
		EEnum SCENARIO_KIND = eINSTANCE.getScenarioKind();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.interaction.InteractionOperatorKind <em>Operator Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.interaction.InteractionOperatorKind
     * @see org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl#getInteractionOperatorKind()
     * @generated
     */
		EEnum INTERACTION_OPERATOR_KIND = eINSTANCE.getInteractionOperatorKind();

	}

} //InteractionPackage
