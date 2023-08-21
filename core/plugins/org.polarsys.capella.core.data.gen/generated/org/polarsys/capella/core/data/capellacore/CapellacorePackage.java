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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

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
 * @see org.polarsys.capella.core.data.capellacore.CapellacoreFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping profileName='Capella'"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='CapellaCore aims at defining the core concepts of the other languages.\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='none' constraints='This package depends on the model ModellingCore.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface CapellacorePackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "capellacore"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/core/core/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.core.data.capellacore"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	CapellacorePackage eINSTANCE = org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.CapellaElement <em>Capella Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getCapellaElement()
   * @generated
   */
	int CAPELLA_ELEMENT = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__OWNED_EXTENSIONS = ModellingcorePackage.TRACEABLE_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__ID = ModellingcorePackage.TRACEABLE_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__SID = ModellingcorePackage.TRACEABLE_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__CONSTRAINTS = ModellingcorePackage.TRACEABLE_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__OWNED_CONSTRAINTS = ModellingcorePackage.TRACEABLE_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS = ModellingcorePackage.TRACEABLE_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__INCOMING_TRACES = ModellingcorePackage.TRACEABLE_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__OUTGOING_TRACES = ModellingcorePackage.TRACEABLE_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__VISIBLE_IN_DOC = ModellingcorePackage.TRACEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__VISIBLE_IN_LM = ModellingcorePackage.TRACEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__SUMMARY = ModellingcorePackage.TRACEABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__DESCRIPTION = ModellingcorePackage.TRACEABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__REVIEW = ModellingcorePackage.TRACEABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES = ModellingcorePackage.TRACEABLE_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = ModellingcorePackage.TRACEABLE_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES = ModellingcorePackage.TRACEABLE_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS = ModellingcorePackage.TRACEABLE_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS = ModellingcorePackage.TRACEABLE_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__STATUS = ModellingcorePackage.TRACEABLE_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT__FEATURES = ModellingcorePackage.TRACEABLE_ELEMENT_FEATURE_COUNT + 11;

	/**
   * The number of structural features of the '<em>Capella Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPELLA_ELEMENT_FEATURE_COUNT = ModellingcorePackage.TRACEABLE_ELEMENT_FEATURE_COUNT + 12;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl <em>Named Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getNamedElement()
   * @generated
   */
	int NAMED_ELEMENT = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__OWNED_EXTENSIONS = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__ID = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__SID = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__CONSTRAINTS = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__OWNED_CONSTRAINTS = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__NAME = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__INCOMING_TRACES = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__OUTGOING_TRACES = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__VISIBLE_IN_DOC = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__VISIBLE_IN_LM = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__SUMMARY = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__DESCRIPTION = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__REVIEW = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__OWNED_PROPERTY_VALUES = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__APPLIED_PROPERTY_VALUES = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__STATUS = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT__FEATURES = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 13;

	/**
   * The number of structural features of the '<em>Named Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_ELEMENT_FEATURE_COUNT = ModellingcorePackage.ABSTRACT_NAMED_ELEMENT_FEATURE_COUNT + 14;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl <em>Relationship</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getRelationship()
   * @generated
   */
	int RELATIONSHIP = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__OWNED_EXTENSIONS = ModellingcorePackage.ABSTRACT_RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__ID = ModellingcorePackage.ABSTRACT_RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__SID = ModellingcorePackage.ABSTRACT_RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__CONSTRAINTS = ModellingcorePackage.ABSTRACT_RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__OWNED_CONSTRAINTS = ModellingcorePackage.ABSTRACT_RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__OWNED_MIGRATED_ELEMENTS = ModellingcorePackage.ABSTRACT_RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__REALIZED_FLOW = ModellingcorePackage.ABSTRACT_RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__INCOMING_TRACES = ModellingcorePackage.ABSTRACT_RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__OUTGOING_TRACES = ModellingcorePackage.ABSTRACT_RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__VISIBLE_IN_DOC = ModellingcorePackage.ABSTRACT_RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__VISIBLE_IN_LM = ModellingcorePackage.ABSTRACT_RELATIONSHIP_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__SUMMARY = ModellingcorePackage.ABSTRACT_RELATIONSHIP_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__DESCRIPTION = ModellingcorePackage.ABSTRACT_RELATIONSHIP_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__REVIEW = ModellingcorePackage.ABSTRACT_RELATIONSHIP_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__OWNED_PROPERTY_VALUES = ModellingcorePackage.ABSTRACT_RELATIONSHIP_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES = ModellingcorePackage.ABSTRACT_RELATIONSHIP_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__APPLIED_PROPERTY_VALUES = ModellingcorePackage.ABSTRACT_RELATIONSHIP_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS = ModellingcorePackage.ABSTRACT_RELATIONSHIP_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS = ModellingcorePackage.ABSTRACT_RELATIONSHIP_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__STATUS = ModellingcorePackage.ABSTRACT_RELATIONSHIP_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP__FEATURES = ModellingcorePackage.ABSTRACT_RELATIONSHIP_FEATURE_COUNT + 13;

	/**
   * The number of structural features of the '<em>Relationship</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RELATIONSHIP_FEATURE_COUNT = ModellingcorePackage.ABSTRACT_RELATIONSHIP_FEATURE_COUNT + 14;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.Namespace <em>Namespace</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.Namespace
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getNamespace()
   * @generated
   */
	int NAMESPACE = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__OWNED_EXTENSIONS = NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__ID = NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__SID = NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__CONSTRAINTS = NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__OWNED_CONSTRAINTS = NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__OWNED_MIGRATED_ELEMENTS = NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__NAME = NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__INCOMING_TRACES = NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__OUTGOING_TRACES = NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__VISIBLE_IN_DOC = NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__VISIBLE_IN_LM = NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__SUMMARY = NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__REVIEW = NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__OWNED_PROPERTY_VALUES = NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__OWNED_ENUMERATION_PROPERTY_TYPES = NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__APPLIED_PROPERTY_VALUES = NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__OWNED_PROPERTY_VALUE_GROUPS = NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__APPLIED_PROPERTY_VALUE_GROUPS = NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__STATUS = NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__FEATURES = NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__OWNED_TRACES = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__CONTAINED_GENERIC_TRACES = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE__NAMING_RULES = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Namespace</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMESPACE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.NamedRelationship <em>Named Relationship</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.NamedRelationship
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getNamedRelationship()
   * @generated
   */
	int NAMED_RELATIONSHIP = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__OWNED_EXTENSIONS = RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__ID = RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__SID = RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__CONSTRAINTS = RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__OWNED_CONSTRAINTS = RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__OWNED_MIGRATED_ELEMENTS = RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__REALIZED_FLOW = RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__INCOMING_TRACES = RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__OUTGOING_TRACES = RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__VISIBLE_IN_DOC = RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__VISIBLE_IN_LM = RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__SUMMARY = RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__DESCRIPTION = RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__REVIEW = RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__OWNED_PROPERTY_VALUES = RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES = RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__APPLIED_PROPERTY_VALUES = RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS = RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS = RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__STATUS = RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__FEATURES = RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__NAME = RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP__NAMING_RULES = RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Named Relationship</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMED_RELATIONSHIP_FEATURE_COUNT = RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.Structure <em>Structure</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.Structure
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getStructure()
   * @generated
   */
	int STRUCTURE = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__OWNED_EXTENSIONS = NAMESPACE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__ID = NAMESPACE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__SID = NAMESPACE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__CONSTRAINTS = NAMESPACE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__OWNED_CONSTRAINTS = NAMESPACE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__OWNED_MIGRATED_ELEMENTS = NAMESPACE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__NAME = NAMESPACE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__INCOMING_TRACES = NAMESPACE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__OUTGOING_TRACES = NAMESPACE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__VISIBLE_IN_DOC = NAMESPACE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__VISIBLE_IN_LM = NAMESPACE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__SUMMARY = NAMESPACE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__DESCRIPTION = NAMESPACE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__REVIEW = NAMESPACE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__OWNED_PROPERTY_VALUES = NAMESPACE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES = NAMESPACE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__APPLIED_PROPERTY_VALUES = NAMESPACE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS = NAMESPACE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS = NAMESPACE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__STATUS = NAMESPACE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__FEATURES = NAMESPACE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__OWNED_TRACES = NAMESPACE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__CONTAINED_GENERIC_TRACES = NAMESPACE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__NAMING_RULES = NAMESPACE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE__OWNED_PROPERTY_VALUE_PKGS = NAMESPACE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Structure</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRUCTURE_FEATURE_COUNT = NAMESPACE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.ReuserStructure <em>Reuser Structure</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.ReuserStructure
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getReuserStructure()
   * @generated
   */
	int REUSER_STRUCTURE = 19;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__OWNED_EXTENSIONS = STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__ID = STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__SID = STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__CONSTRAINTS = STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__OWNED_CONSTRAINTS = STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__OWNED_MIGRATED_ELEMENTS = STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__NAME = STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__INCOMING_TRACES = STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__OUTGOING_TRACES = STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__VISIBLE_IN_DOC = STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__VISIBLE_IN_LM = STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__SUMMARY = STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__DESCRIPTION = STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__REVIEW = STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__OWNED_PROPERTY_VALUES = STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES = STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__APPLIED_PROPERTY_VALUES = STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS = STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS = STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__STATUS = STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__FEATURES = STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__OWNED_TRACES = STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__CONTAINED_GENERIC_TRACES = STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__NAMING_RULES = STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__OWNED_PROPERTY_VALUE_PKGS = STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Reuse Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__REUSE_LINKS = STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Reuse Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE__OWNED_REUSE_LINKS = STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Reuser Structure</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSER_STRUCTURE_FEATURE_COUNT = STRUCTURE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.AbstractModellingStructureImpl <em>Abstract Modelling Structure</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.AbstractModellingStructureImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getAbstractModellingStructure()
   * @generated
   */
	int ABSTRACT_MODELLING_STRUCTURE = 6;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__OWNED_EXTENSIONS = REUSER_STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__ID = REUSER_STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__SID = REUSER_STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__CONSTRAINTS = REUSER_STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__OWNED_CONSTRAINTS = REUSER_STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__OWNED_MIGRATED_ELEMENTS = REUSER_STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__NAME = REUSER_STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__INCOMING_TRACES = REUSER_STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__OUTGOING_TRACES = REUSER_STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__VISIBLE_IN_DOC = REUSER_STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__VISIBLE_IN_LM = REUSER_STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__SUMMARY = REUSER_STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__DESCRIPTION = REUSER_STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__REVIEW = REUSER_STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__OWNED_PROPERTY_VALUES = REUSER_STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES = REUSER_STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__APPLIED_PROPERTY_VALUES = REUSER_STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS = REUSER_STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS = REUSER_STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__STATUS = REUSER_STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__FEATURES = REUSER_STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__OWNED_TRACES = REUSER_STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__CONTAINED_GENERIC_TRACES = REUSER_STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__NAMING_RULES = REUSER_STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__OWNED_PROPERTY_VALUE_PKGS = REUSER_STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Reuse Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__REUSE_LINKS = REUSER_STRUCTURE__REUSE_LINKS;

	/**
   * The feature id for the '<em><b>Owned Reuse Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__OWNED_REUSE_LINKS = REUSER_STRUCTURE__OWNED_REUSE_LINKS;

	/**
   * The feature id for the '<em><b>Owned Architectures</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__OWNED_ARCHITECTURES = REUSER_STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Architecture Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE__OWNED_ARCHITECTURE_PKGS = REUSER_STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Abstract Modelling Structure</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_MODELLING_STRUCTURE_FEATURE_COUNT = REUSER_STRUCTURE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.TypeImpl <em>Type</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.TypeImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getType()
   * @generated
   */
	int TYPE = 10;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__OWNED_EXTENSIONS = ModellingcorePackage.ABSTRACT_TYPE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__ID = ModellingcorePackage.ABSTRACT_TYPE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__SID = ModellingcorePackage.ABSTRACT_TYPE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__CONSTRAINTS = ModellingcorePackage.ABSTRACT_TYPE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__OWNED_CONSTRAINTS = ModellingcorePackage.ABSTRACT_TYPE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__OWNED_MIGRATED_ELEMENTS = ModellingcorePackage.ABSTRACT_TYPE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__NAME = ModellingcorePackage.ABSTRACT_TYPE__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__ABSTRACT_TYPED_ELEMENTS = ModellingcorePackage.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__INCOMING_TRACES = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__OUTGOING_TRACES = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__VISIBLE_IN_DOC = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__VISIBLE_IN_LM = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__SUMMARY = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__DESCRIPTION = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__REVIEW = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__OWNED_PROPERTY_VALUES = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__OWNED_ENUMERATION_PROPERTY_TYPES = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__APPLIED_PROPERTY_VALUES = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__OWNED_PROPERTY_VALUE_GROUPS = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__APPLIED_PROPERTY_VALUE_GROUPS = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__STATUS = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__FEATURES = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__OWNED_TRACES = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__CONTAINED_GENERIC_TRACES = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__NAMING_RULES = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE__TYPED_ELEMENTS = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 17;

	/**
   * The number of structural features of the '<em>Type</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPE_FEATURE_COUNT = ModellingcorePackage.ABSTRACT_TYPE_FEATURE_COUNT + 18;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.ModellingBlock <em>Modelling Block</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.ModellingBlock
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getModellingBlock()
   * @generated
   */
	int MODELLING_BLOCK = 7;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__OWNED_EXTENSIONS = TYPE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__ID = TYPE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__SID = TYPE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__CONSTRAINTS = TYPE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__OWNED_CONSTRAINTS = TYPE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__OWNED_MIGRATED_ELEMENTS = TYPE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__NAME = TYPE__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__ABSTRACT_TYPED_ELEMENTS = TYPE__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__INCOMING_TRACES = TYPE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__OUTGOING_TRACES = TYPE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__VISIBLE_IN_DOC = TYPE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__VISIBLE_IN_LM = TYPE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__SUMMARY = TYPE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__DESCRIPTION = TYPE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__REVIEW = TYPE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__OWNED_PROPERTY_VALUES = TYPE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__OWNED_ENUMERATION_PROPERTY_TYPES = TYPE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__APPLIED_PROPERTY_VALUES = TYPE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__OWNED_PROPERTY_VALUE_GROUPS = TYPE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__APPLIED_PROPERTY_VALUE_GROUPS = TYPE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__STATUS = TYPE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__FEATURES = TYPE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__OWNED_TRACES = TYPE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__CONTAINED_GENERIC_TRACES = TYPE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__NAMING_RULES = TYPE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK__TYPED_ELEMENTS = TYPE__TYPED_ELEMENTS;

	/**
   * The number of structural features of the '<em>Modelling Block</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_BLOCK_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.ModellingArchitecture <em>Modelling Architecture</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.ModellingArchitecture
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getModellingArchitecture()
   * @generated
   */
	int MODELLING_ARCHITECTURE = 8;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__OWNED_EXTENSIONS = STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__ID = STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__SID = STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__CONSTRAINTS = STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__OWNED_CONSTRAINTS = STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__OWNED_MIGRATED_ELEMENTS = STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__NAME = STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__INCOMING_TRACES = STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__OUTGOING_TRACES = STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__VISIBLE_IN_DOC = STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__VISIBLE_IN_LM = STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__SUMMARY = STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__DESCRIPTION = STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__REVIEW = STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__OWNED_PROPERTY_VALUES = STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__OWNED_ENUMERATION_PROPERTY_TYPES = STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__APPLIED_PROPERTY_VALUES = STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__OWNED_PROPERTY_VALUE_GROUPS = STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__APPLIED_PROPERTY_VALUE_GROUPS = STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__STATUS = STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__FEATURES = STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__OWNED_TRACES = STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__CONTAINED_GENERIC_TRACES = STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__NAMING_RULES = STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE__OWNED_PROPERTY_VALUE_PKGS = STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The number of structural features of the '<em>Modelling Architecture</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_FEATURE_COUNT = STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.ModellingArchitecturePkg <em>Modelling Architecture Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.ModellingArchitecturePkg
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getModellingArchitecturePkg()
   * @generated
   */
	int MODELLING_ARCHITECTURE_PKG = 9;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__OWNED_EXTENSIONS = STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__ID = STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__SID = STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__CONSTRAINTS = STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__OWNED_CONSTRAINTS = STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__OWNED_MIGRATED_ELEMENTS = STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__NAME = STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__INCOMING_TRACES = STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__OUTGOING_TRACES = STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__VISIBLE_IN_DOC = STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__VISIBLE_IN_LM = STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__SUMMARY = STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__DESCRIPTION = STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__REVIEW = STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUES = STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUES = STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_GROUPS = STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUE_GROUPS = STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__STATUS = STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__FEATURES = STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__OWNED_TRACES = STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__CONTAINED_GENERIC_TRACES = STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__NAMING_RULES = STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_PKGS = STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The number of structural features of the '<em>Modelling Architecture Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MODELLING_ARCHITECTURE_PKG_FEATURE_COUNT = STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.TypedElementImpl <em>Typed Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.TypedElementImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getTypedElement()
   * @generated
   */
	int TYPED_ELEMENT = 11;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__OWNED_EXTENSIONS = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__ID = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__SID = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__CONSTRAINTS = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__OWNED_CONSTRAINTS = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__OWNED_MIGRATED_ELEMENTS = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__NAME = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__ABSTRACT_TYPE = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__INCOMING_TRACES = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__OUTGOING_TRACES = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__VISIBLE_IN_DOC = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__VISIBLE_IN_LM = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__SUMMARY = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__DESCRIPTION = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__REVIEW = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__OWNED_PROPERTY_VALUES = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__APPLIED_PROPERTY_VALUES = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__STATUS = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__FEATURES = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT__TYPE = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 14;

	/**
   * The number of structural features of the '<em>Typed Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TYPED_ELEMENT_FEATURE_COUNT = ModellingcorePackage.ABSTRACT_TYPED_ELEMENT_FEATURE_COUNT + 15;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.TraceImpl <em>Trace</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.TraceImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getTrace()
   * @generated
   */
	int TRACE = 12;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__OWNED_EXTENSIONS = RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__ID = RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__SID = RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__CONSTRAINTS = RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__OWNED_CONSTRAINTS = RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__OWNED_MIGRATED_ELEMENTS = RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__REALIZED_FLOW = RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__INCOMING_TRACES = RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__OUTGOING_TRACES = RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__VISIBLE_IN_DOC = RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__VISIBLE_IN_LM = RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__SUMMARY = RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__DESCRIPTION = RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__REVIEW = RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__OWNED_PROPERTY_VALUES = RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__OWNED_ENUMERATION_PROPERTY_TYPES = RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__APPLIED_PROPERTY_VALUES = RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__OWNED_PROPERTY_VALUE_GROUPS = RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__APPLIED_PROPERTY_VALUE_GROUPS = RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__STATUS = RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__FEATURES = RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__TARGET_ELEMENT = RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE__SOURCE_ELEMENT = RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Trace</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRACE_FEATURE_COUNT = RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.AbstractAnnotation <em>Abstract Annotation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.AbstractAnnotation
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getAbstractAnnotation()
   * @generated
   */
	int ABSTRACT_ANNOTATION = 13;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__OWNED_EXTENSIONS = CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__ID = CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__SID = CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__CONSTRAINTS = CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__OWNED_CONSTRAINTS = CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__OWNED_MIGRATED_ELEMENTS = CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__INCOMING_TRACES = CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__OUTGOING_TRACES = CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__VISIBLE_IN_DOC = CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__VISIBLE_IN_LM = CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__SUMMARY = CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__DESCRIPTION = CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__REVIEW = CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__OWNED_PROPERTY_VALUES = CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__OWNED_ENUMERATION_PROPERTY_TYPES = CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__APPLIED_PROPERTY_VALUES = CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__OWNED_PROPERTY_VALUE_GROUPS = CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__APPLIED_PROPERTY_VALUE_GROUPS = CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__STATUS = CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__FEATURES = CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Content</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION__CONTENT = CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Annotation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_ANNOTATION_FEATURE_COUNT = CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.NamingRuleImpl <em>Naming Rule</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.NamingRuleImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getNamingRule()
   * @generated
   */
	int NAMING_RULE = 14;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__OWNED_EXTENSIONS = ABSTRACT_ANNOTATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__ID = ABSTRACT_ANNOTATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__SID = ABSTRACT_ANNOTATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__CONSTRAINTS = ABSTRACT_ANNOTATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__OWNED_CONSTRAINTS = ABSTRACT_ANNOTATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_ANNOTATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__INCOMING_TRACES = ABSTRACT_ANNOTATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__OUTGOING_TRACES = ABSTRACT_ANNOTATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__VISIBLE_IN_DOC = ABSTRACT_ANNOTATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__VISIBLE_IN_LM = ABSTRACT_ANNOTATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__SUMMARY = ABSTRACT_ANNOTATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__DESCRIPTION = ABSTRACT_ANNOTATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__REVIEW = ABSTRACT_ANNOTATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__OWNED_PROPERTY_VALUES = ABSTRACT_ANNOTATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_ANNOTATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__APPLIED_PROPERTY_VALUES = ABSTRACT_ANNOTATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_ANNOTATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_ANNOTATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__STATUS = ABSTRACT_ANNOTATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__FEATURES = ABSTRACT_ANNOTATION__FEATURES;

	/**
   * The feature id for the '<em><b>Content</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__CONTENT = ABSTRACT_ANNOTATION__CONTENT;

	/**
   * The feature id for the '<em><b>Target Type</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE__TARGET_TYPE = ABSTRACT_ANNOTATION_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Naming Rule</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int NAMING_RULE_FEATURE_COUNT = ABSTRACT_ANNOTATION_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.ConstraintImpl <em>Constraint</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.ConstraintImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getConstraint()
   * @generated
   */
	int CONSTRAINT = 15;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__OWNED_EXTENSIONS = NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__ID = NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__SID = NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__CONSTRAINTS = NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__OWNED_CONSTRAINTS = NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__OWNED_MIGRATED_ELEMENTS = NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__NAME = NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__INCOMING_TRACES = NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__OUTGOING_TRACES = NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__VISIBLE_IN_DOC = NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__VISIBLE_IN_LM = NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__SUMMARY = NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__REVIEW = NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__OWNED_PROPERTY_VALUES = NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__OWNED_ENUMERATION_PROPERTY_TYPES = NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__APPLIED_PROPERTY_VALUES = NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__OWNED_PROPERTY_VALUE_GROUPS = NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__APPLIED_PROPERTY_VALUE_GROUPS = NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__STATUS = NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__FEATURES = NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Constrained Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__CONSTRAINED_ELEMENTS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Specification</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__OWNED_SPECIFICATION = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Context</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT__CONTEXT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Constraint</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONSTRAINT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.KeyValueImpl <em>Key Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.KeyValueImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getKeyValue()
   * @generated
   */
	int KEY_VALUE = 16;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__OWNED_EXTENSIONS = CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__ID = CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__SID = CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__CONSTRAINTS = CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__OWNED_CONSTRAINTS = CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__OWNED_MIGRATED_ELEMENTS = CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__INCOMING_TRACES = CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__OUTGOING_TRACES = CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__VISIBLE_IN_DOC = CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__VISIBLE_IN_LM = CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__SUMMARY = CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__DESCRIPTION = CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__REVIEW = CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__OWNED_PROPERTY_VALUES = CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__APPLIED_PROPERTY_VALUES = CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__OWNED_PROPERTY_VALUE_GROUPS = CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__STATUS = CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__FEATURES = CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__KEY = CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE__VALUE = CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Key Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int KEY_VALUE_FEATURE_COUNT = CAPELLA_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.ReuseLinkImpl <em>Reuse Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.ReuseLinkImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getReuseLink()
   * @generated
   */
	int REUSE_LINK = 17;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__OWNED_EXTENSIONS = RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__ID = RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__SID = RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__CONSTRAINTS = RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__OWNED_CONSTRAINTS = RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__OWNED_MIGRATED_ELEMENTS = RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__REALIZED_FLOW = RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__INCOMING_TRACES = RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__OUTGOING_TRACES = RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__VISIBLE_IN_DOC = RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__VISIBLE_IN_LM = RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__SUMMARY = RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__DESCRIPTION = RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__REVIEW = RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__OWNED_PROPERTY_VALUES = RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__APPLIED_PROPERTY_VALUES = RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__OWNED_PROPERTY_VALUE_GROUPS = RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__APPLIED_PROPERTY_VALUE_GROUPS = RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__STATUS = RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__FEATURES = RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Reused</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__REUSED = RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Reuser</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK__REUSER = RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Reuse Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSE_LINK_FEATURE_COUNT = RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.ReuseableStructure <em>Reuseable Structure</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.ReuseableStructure
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getReuseableStructure()
   * @generated
   */
	int REUSEABLE_STRUCTURE = 18;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__OWNED_EXTENSIONS = STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__ID = STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__SID = STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__CONSTRAINTS = STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__OWNED_CONSTRAINTS = STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__OWNED_MIGRATED_ELEMENTS = STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__NAME = STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__INCOMING_TRACES = STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__OUTGOING_TRACES = STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__VISIBLE_IN_DOC = STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__VISIBLE_IN_LM = STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__SUMMARY = STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__DESCRIPTION = STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__REVIEW = STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__OWNED_PROPERTY_VALUES = STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES = STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__APPLIED_PROPERTY_VALUES = STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS = STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS = STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__STATUS = STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__FEATURES = STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__OWNED_TRACES = STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__CONTAINED_GENERIC_TRACES = STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__NAMING_RULES = STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__OWNED_PROPERTY_VALUE_PKGS = STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Reuse Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE__REUSE_LINKS = STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Reuseable Structure</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REUSEABLE_STRUCTURE_FEATURE_COUNT = STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.GeneralizableElementImpl <em>Generalizable Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.GeneralizableElementImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getGeneralizableElement()
   * @generated
   */
	int GENERALIZABLE_ELEMENT = 20;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__OWNED_EXTENSIONS = TYPE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__ID = TYPE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__SID = TYPE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__CONSTRAINTS = TYPE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__OWNED_CONSTRAINTS = TYPE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__OWNED_MIGRATED_ELEMENTS = TYPE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__NAME = TYPE__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__ABSTRACT_TYPED_ELEMENTS = TYPE__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__INCOMING_TRACES = TYPE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__OUTGOING_TRACES = TYPE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__VISIBLE_IN_DOC = TYPE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__VISIBLE_IN_LM = TYPE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__SUMMARY = TYPE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__DESCRIPTION = TYPE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__REVIEW = TYPE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__OWNED_PROPERTY_VALUES = TYPE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = TYPE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__APPLIED_PROPERTY_VALUES = TYPE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS = TYPE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS = TYPE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__STATUS = TYPE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__FEATURES = TYPE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__OWNED_TRACES = TYPE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__CONTAINED_GENERIC_TRACES = TYPE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__NAMING_RULES = TYPE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__TYPED_ELEMENTS = TYPE__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__ABSTRACT = TYPE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS = TYPE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS = TYPE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS = TYPE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__SUPER = TYPE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT__SUB = TYPE_FEATURE_COUNT + 5;

	/**
   * The number of structural features of the '<em>Generalizable Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZABLE_ELEMENT_FEATURE_COUNT = TYPE_FEATURE_COUNT + 6;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.ClassifierImpl <em>Classifier</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.ClassifierImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getClassifier()
   * @generated
   */
	int CLASSIFIER = 21;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__OWNED_EXTENSIONS = GENERALIZABLE_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__ID = GENERALIZABLE_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__SID = GENERALIZABLE_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__CONSTRAINTS = GENERALIZABLE_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__OWNED_CONSTRAINTS = GENERALIZABLE_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__OWNED_MIGRATED_ELEMENTS = GENERALIZABLE_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__NAME = GENERALIZABLE_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__ABSTRACT_TYPED_ELEMENTS = GENERALIZABLE_ELEMENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__INCOMING_TRACES = GENERALIZABLE_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__OUTGOING_TRACES = GENERALIZABLE_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__VISIBLE_IN_DOC = GENERALIZABLE_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__VISIBLE_IN_LM = GENERALIZABLE_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__SUMMARY = GENERALIZABLE_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__DESCRIPTION = GENERALIZABLE_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__REVIEW = GENERALIZABLE_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__OWNED_PROPERTY_VALUES = GENERALIZABLE_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__OWNED_ENUMERATION_PROPERTY_TYPES = GENERALIZABLE_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__APPLIED_PROPERTY_VALUES = GENERALIZABLE_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__OWNED_PROPERTY_VALUE_GROUPS = GENERALIZABLE_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__APPLIED_PROPERTY_VALUE_GROUPS = GENERALIZABLE_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__STATUS = GENERALIZABLE_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__FEATURES = GENERALIZABLE_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__OWNED_TRACES = GENERALIZABLE_ELEMENT__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__CONTAINED_GENERIC_TRACES = GENERALIZABLE_ELEMENT__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__NAMING_RULES = GENERALIZABLE_ELEMENT__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__TYPED_ELEMENTS = GENERALIZABLE_ELEMENT__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__ABSTRACT = GENERALIZABLE_ELEMENT__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__OWNED_GENERALIZATIONS = GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__SUPER_GENERALIZATIONS = GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__SUB_GENERALIZATIONS = GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__SUPER = GENERALIZABLE_ELEMENT__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__SUB = GENERALIZABLE_ELEMENT__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__OWNED_FEATURES = GENERALIZABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER__CONTAINED_PROPERTIES = GENERALIZABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Classifier</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CLASSIFIER_FEATURE_COUNT = GENERALIZABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.GeneralClassImpl <em>General Class</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.GeneralClassImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getGeneralClass()
   * @generated
   */
	int GENERAL_CLASS = 22;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__OWNED_EXTENSIONS = CLASSIFIER__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__ID = CLASSIFIER__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__SID = CLASSIFIER__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__CONSTRAINTS = CLASSIFIER__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__OWNED_CONSTRAINTS = CLASSIFIER__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__OWNED_MIGRATED_ELEMENTS = CLASSIFIER__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__NAME = CLASSIFIER__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__ABSTRACT_TYPED_ELEMENTS = CLASSIFIER__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__INCOMING_TRACES = CLASSIFIER__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__OUTGOING_TRACES = CLASSIFIER__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__VISIBLE_IN_DOC = CLASSIFIER__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__VISIBLE_IN_LM = CLASSIFIER__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__SUMMARY = CLASSIFIER__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__DESCRIPTION = CLASSIFIER__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__REVIEW = CLASSIFIER__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__OWNED_PROPERTY_VALUES = CLASSIFIER__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__OWNED_ENUMERATION_PROPERTY_TYPES = CLASSIFIER__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__APPLIED_PROPERTY_VALUES = CLASSIFIER__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__OWNED_PROPERTY_VALUE_GROUPS = CLASSIFIER__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__APPLIED_PROPERTY_VALUE_GROUPS = CLASSIFIER__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__STATUS = CLASSIFIER__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__FEATURES = CLASSIFIER__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__OWNED_TRACES = CLASSIFIER__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__CONTAINED_GENERIC_TRACES = CLASSIFIER__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__NAMING_RULES = CLASSIFIER__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__TYPED_ELEMENTS = CLASSIFIER__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__ABSTRACT = CLASSIFIER__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__OWNED_GENERALIZATIONS = CLASSIFIER__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__SUPER_GENERALIZATIONS = CLASSIFIER__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__SUB_GENERALIZATIONS = CLASSIFIER__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__SUPER = CLASSIFIER__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__SUB = CLASSIFIER__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__OWNED_FEATURES = CLASSIFIER__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__CONTAINED_PROPERTIES = CLASSIFIER__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__FINAL = CLASSIFIER_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__VISIBILITY = CLASSIFIER_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Contained Operations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__CONTAINED_OPERATIONS = CLASSIFIER_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Nested General Classes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS__NESTED_GENERAL_CLASSES = CLASSIFIER_FEATURE_COUNT + 3;

	/**
   * The number of structural features of the '<em>General Class</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERAL_CLASS_FEATURE_COUNT = CLASSIFIER_FEATURE_COUNT + 4;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.GeneralizationImpl <em>Generalization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.GeneralizationImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getGeneralization()
   * @generated
   */
	int GENERALIZATION = 23;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__OWNED_EXTENSIONS = RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__ID = RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__SID = RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__CONSTRAINTS = RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__OWNED_CONSTRAINTS = RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__OWNED_MIGRATED_ELEMENTS = RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__REALIZED_FLOW = RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__INCOMING_TRACES = RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__OUTGOING_TRACES = RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__VISIBLE_IN_DOC = RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__VISIBLE_IN_LM = RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__SUMMARY = RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__DESCRIPTION = RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__REVIEW = RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__OWNED_PROPERTY_VALUES = RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__APPLIED_PROPERTY_VALUES = RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__OWNED_PROPERTY_VALUE_GROUPS = RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__STATUS = RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__FEATURES = RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__SUPER = RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION__SUB = RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Generalization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int GENERALIZATION_FEATURE_COUNT = RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.FeatureImpl <em>Feature</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.FeatureImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getFeature()
   * @generated
   */
	int FEATURE = 24;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__OWNED_EXTENSIONS = NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__ID = NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__SID = NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__CONSTRAINTS = NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__OWNED_CONSTRAINTS = NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__OWNED_MIGRATED_ELEMENTS = NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__NAME = NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__INCOMING_TRACES = NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__OUTGOING_TRACES = NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__VISIBLE_IN_DOC = NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__VISIBLE_IN_LM = NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__SUMMARY = NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__REVIEW = NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__OWNED_PROPERTY_VALUES = NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__OWNED_ENUMERATION_PROPERTY_TYPES = NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__APPLIED_PROPERTY_VALUES = NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__OWNED_PROPERTY_VALUE_GROUPS = NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__APPLIED_PROPERTY_VALUE_GROUPS = NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__STATUS = NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__FEATURES = NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__IS_ABSTRACT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Is Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__IS_STATIC = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE__VISIBILITY = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Feature</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FEATURE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.AbstractExchangeItemPkgImpl <em>Abstract Exchange Item Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.AbstractExchangeItemPkgImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getAbstractExchangeItemPkg()
   * @generated
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG = 25;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__OWNED_EXTENSIONS = STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__ID = STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__SID = STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__CONSTRAINTS = STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__OWNED_CONSTRAINTS = STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__OWNED_MIGRATED_ELEMENTS = STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__NAME = STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__INCOMING_TRACES = STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__OUTGOING_TRACES = STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__VISIBLE_IN_DOC = STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__VISIBLE_IN_LM = STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__SUMMARY = STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__DESCRIPTION = STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__REVIEW = STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__OWNED_PROPERTY_VALUES = STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__APPLIED_PROPERTY_VALUES = STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__OWNED_PROPERTY_VALUE_GROUPS = STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__APPLIED_PROPERTY_VALUE_GROUPS = STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__STATUS = STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__FEATURES = STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__OWNED_TRACES = STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__CONTAINED_GENERIC_TRACES = STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__NAMING_RULES = STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__OWNED_PROPERTY_VALUE_PKGS = STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Exchange Items</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG__OWNED_EXCHANGE_ITEMS = STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Exchange Item Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_EXCHANGE_ITEM_PKG_FEATURE_COUNT = STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.AllocationImpl <em>Allocation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.AllocationImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getAllocation()
   * @generated
   */
	int ALLOCATION = 26;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__OWNED_EXTENSIONS = RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__ID = RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__SID = RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__CONSTRAINTS = RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__OWNED_CONSTRAINTS = RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__OWNED_MIGRATED_ELEMENTS = RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__REALIZED_FLOW = RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__INCOMING_TRACES = RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__OUTGOING_TRACES = RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__VISIBLE_IN_DOC = RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__VISIBLE_IN_LM = RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__SUMMARY = RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__DESCRIPTION = RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__REVIEW = RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__OWNED_PROPERTY_VALUES = RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES = RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__APPLIED_PROPERTY_VALUES = RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS = RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS = RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__STATUS = RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__FEATURES = RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__TARGET_ELEMENT = RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION__SOURCE_ELEMENT = RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Allocation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ALLOCATION_FEATURE_COUNT = RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.Involvement <em>Involvement</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.Involvement
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getInvolvement()
   * @generated
   */
	int INVOLVEMENT = 27;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__OWNED_EXTENSIONS = RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__ID = RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__SID = RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__CONSTRAINTS = RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__OWNED_CONSTRAINTS = RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__OWNED_MIGRATED_ELEMENTS = RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__REALIZED_FLOW = RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__INCOMING_TRACES = RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__OUTGOING_TRACES = RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__VISIBLE_IN_DOC = RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__VISIBLE_IN_LM = RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__SUMMARY = RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__DESCRIPTION = RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__REVIEW = RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__OWNED_PROPERTY_VALUES = RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__APPLIED_PROPERTY_VALUES = RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS = RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS = RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__STATUS = RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__FEATURES = RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Involver</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__INVOLVER = RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Involved</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT__INVOLVED = RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Involvement</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVEMENT_FEATURE_COUNT = RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.InvolverElement <em>Involver Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.InvolverElement
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getInvolverElement()
   * @generated
   */
	int INVOLVER_ELEMENT = 28;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__OWNED_EXTENSIONS = CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__ID = CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__SID = CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__CONSTRAINTS = CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__OWNED_CONSTRAINTS = CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__OWNED_MIGRATED_ELEMENTS = CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__INCOMING_TRACES = CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__OUTGOING_TRACES = CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__VISIBLE_IN_DOC = CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__VISIBLE_IN_LM = CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__SUMMARY = CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__DESCRIPTION = CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__REVIEW = CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__OWNED_PROPERTY_VALUES = CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__APPLIED_PROPERTY_VALUES = CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS = CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__STATUS = CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__FEATURES = CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involved Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS = CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Involver Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVER_ELEMENT_FEATURE_COUNT = CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.InvolvedElement <em>Involved Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.InvolvedElement
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getInvolvedElement()
   * @generated
   */
	int INVOLVED_ELEMENT = 29;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__OWNED_EXTENSIONS = CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__ID = CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__SID = CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__CONSTRAINTS = CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__OWNED_CONSTRAINTS = CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__OWNED_MIGRATED_ELEMENTS = CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__INCOMING_TRACES = CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__OUTGOING_TRACES = CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__VISIBLE_IN_DOC = CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__VISIBLE_IN_LM = CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__SUMMARY = CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__DESCRIPTION = CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__REVIEW = CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__OWNED_PROPERTY_VALUES = CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUES = CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS = CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__STATUS = CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__FEATURES = CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS = CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Involved Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INVOLVED_ELEMENT_FEATURE_COUNT = CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.AbstractPropertyValueImpl <em>Abstract Property Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.AbstractPropertyValueImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getAbstractPropertyValue()
   * @generated
   */
	int ABSTRACT_PROPERTY_VALUE = 30;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__OWNED_EXTENSIONS = NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__ID = NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__SID = NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__CONSTRAINTS = NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__OWNED_CONSTRAINTS = NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__OWNED_MIGRATED_ELEMENTS = NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__NAME = NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__INCOMING_TRACES = NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__OUTGOING_TRACES = NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__VISIBLE_IN_DOC = NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__VISIBLE_IN_LM = NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__SUMMARY = NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__REVIEW = NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__OWNED_PROPERTY_VALUES = NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__APPLIED_PROPERTY_VALUES = NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__OWNED_PROPERTY_VALUE_GROUPS = NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__STATUS = NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__FEATURES = NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involved Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__INVOLVED_ELEMENTS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Valued Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE__VALUED_ELEMENTS = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Abstract Property Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PROPERTY_VALUE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.StringPropertyValueImpl <em>String Property Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.StringPropertyValueImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getStringPropertyValue()
   * @generated
   */
	int STRING_PROPERTY_VALUE = 31;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__OWNED_EXTENSIONS = ABSTRACT_PROPERTY_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__ID = ABSTRACT_PROPERTY_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__SID = ABSTRACT_PROPERTY_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__CONSTRAINTS = ABSTRACT_PROPERTY_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__OWNED_CONSTRAINTS = ABSTRACT_PROPERTY_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_PROPERTY_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__NAME = ABSTRACT_PROPERTY_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__INCOMING_TRACES = ABSTRACT_PROPERTY_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__OUTGOING_TRACES = ABSTRACT_PROPERTY_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__VISIBLE_IN_DOC = ABSTRACT_PROPERTY_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__VISIBLE_IN_LM = ABSTRACT_PROPERTY_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__SUMMARY = ABSTRACT_PROPERTY_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__DESCRIPTION = ABSTRACT_PROPERTY_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__REVIEW = ABSTRACT_PROPERTY_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__OWNED_PROPERTY_VALUES = ABSTRACT_PROPERTY_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_PROPERTY_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__APPLIED_PROPERTY_VALUES = ABSTRACT_PROPERTY_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_PROPERTY_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_PROPERTY_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__STATUS = ABSTRACT_PROPERTY_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__FEATURES = ABSTRACT_PROPERTY_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Involved Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__INVOLVED_ELEMENTS = ABSTRACT_PROPERTY_VALUE__INVOLVED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Valued Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__VALUED_ELEMENTS = ABSTRACT_PROPERTY_VALUE__VALUED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE__VALUE = ABSTRACT_PROPERTY_VALUE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>String Property Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int STRING_PROPERTY_VALUE_FEATURE_COUNT = ABSTRACT_PROPERTY_VALUE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.IntegerPropertyValueImpl <em>Integer Property Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.IntegerPropertyValueImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getIntegerPropertyValue()
   * @generated
   */
	int INTEGER_PROPERTY_VALUE = 32;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__OWNED_EXTENSIONS = ABSTRACT_PROPERTY_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__ID = ABSTRACT_PROPERTY_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__SID = ABSTRACT_PROPERTY_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__CONSTRAINTS = ABSTRACT_PROPERTY_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__OWNED_CONSTRAINTS = ABSTRACT_PROPERTY_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_PROPERTY_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__NAME = ABSTRACT_PROPERTY_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__INCOMING_TRACES = ABSTRACT_PROPERTY_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__OUTGOING_TRACES = ABSTRACT_PROPERTY_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__VISIBLE_IN_DOC = ABSTRACT_PROPERTY_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__VISIBLE_IN_LM = ABSTRACT_PROPERTY_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__SUMMARY = ABSTRACT_PROPERTY_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__DESCRIPTION = ABSTRACT_PROPERTY_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__REVIEW = ABSTRACT_PROPERTY_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__OWNED_PROPERTY_VALUES = ABSTRACT_PROPERTY_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_PROPERTY_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__APPLIED_PROPERTY_VALUES = ABSTRACT_PROPERTY_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_PROPERTY_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_PROPERTY_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__STATUS = ABSTRACT_PROPERTY_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__FEATURES = ABSTRACT_PROPERTY_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Involved Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__INVOLVED_ELEMENTS = ABSTRACT_PROPERTY_VALUE__INVOLVED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Valued Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__VALUED_ELEMENTS = ABSTRACT_PROPERTY_VALUE__VALUED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE__VALUE = ABSTRACT_PROPERTY_VALUE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Integer Property Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTEGER_PROPERTY_VALUE_FEATURE_COUNT = ABSTRACT_PROPERTY_VALUE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.BooleanPropertyValueImpl <em>Boolean Property Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.BooleanPropertyValueImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getBooleanPropertyValue()
   * @generated
   */
	int BOOLEAN_PROPERTY_VALUE = 33;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__OWNED_EXTENSIONS = ABSTRACT_PROPERTY_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__ID = ABSTRACT_PROPERTY_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__SID = ABSTRACT_PROPERTY_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__CONSTRAINTS = ABSTRACT_PROPERTY_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__OWNED_CONSTRAINTS = ABSTRACT_PROPERTY_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_PROPERTY_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__NAME = ABSTRACT_PROPERTY_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__INCOMING_TRACES = ABSTRACT_PROPERTY_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__OUTGOING_TRACES = ABSTRACT_PROPERTY_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__VISIBLE_IN_DOC = ABSTRACT_PROPERTY_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__VISIBLE_IN_LM = ABSTRACT_PROPERTY_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__SUMMARY = ABSTRACT_PROPERTY_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__DESCRIPTION = ABSTRACT_PROPERTY_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__REVIEW = ABSTRACT_PROPERTY_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__OWNED_PROPERTY_VALUES = ABSTRACT_PROPERTY_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_PROPERTY_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__APPLIED_PROPERTY_VALUES = ABSTRACT_PROPERTY_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_PROPERTY_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_PROPERTY_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__STATUS = ABSTRACT_PROPERTY_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__FEATURES = ABSTRACT_PROPERTY_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Involved Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__INVOLVED_ELEMENTS = ABSTRACT_PROPERTY_VALUE__INVOLVED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Valued Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__VALUED_ELEMENTS = ABSTRACT_PROPERTY_VALUE__VALUED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE__VALUE = ABSTRACT_PROPERTY_VALUE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Boolean Property Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BOOLEAN_PROPERTY_VALUE_FEATURE_COUNT = ABSTRACT_PROPERTY_VALUE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.FloatPropertyValueImpl <em>Float Property Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.FloatPropertyValueImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getFloatPropertyValue()
   * @generated
   */
	int FLOAT_PROPERTY_VALUE = 34;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__OWNED_EXTENSIONS = ABSTRACT_PROPERTY_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__ID = ABSTRACT_PROPERTY_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__SID = ABSTRACT_PROPERTY_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__CONSTRAINTS = ABSTRACT_PROPERTY_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__OWNED_CONSTRAINTS = ABSTRACT_PROPERTY_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_PROPERTY_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__NAME = ABSTRACT_PROPERTY_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__INCOMING_TRACES = ABSTRACT_PROPERTY_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__OUTGOING_TRACES = ABSTRACT_PROPERTY_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__VISIBLE_IN_DOC = ABSTRACT_PROPERTY_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__VISIBLE_IN_LM = ABSTRACT_PROPERTY_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__SUMMARY = ABSTRACT_PROPERTY_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__DESCRIPTION = ABSTRACT_PROPERTY_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__REVIEW = ABSTRACT_PROPERTY_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__OWNED_PROPERTY_VALUES = ABSTRACT_PROPERTY_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_PROPERTY_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__APPLIED_PROPERTY_VALUES = ABSTRACT_PROPERTY_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_PROPERTY_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_PROPERTY_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__STATUS = ABSTRACT_PROPERTY_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__FEATURES = ABSTRACT_PROPERTY_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Involved Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__INVOLVED_ELEMENTS = ABSTRACT_PROPERTY_VALUE__INVOLVED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Valued Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__VALUED_ELEMENTS = ABSTRACT_PROPERTY_VALUE__VALUED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE__VALUE = ABSTRACT_PROPERTY_VALUE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Float Property Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int FLOAT_PROPERTY_VALUE_FEATURE_COUNT = ABSTRACT_PROPERTY_VALUE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.EnumerationPropertyValueImpl <em>Enumeration Property Value</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.EnumerationPropertyValueImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getEnumerationPropertyValue()
   * @generated
   */
	int ENUMERATION_PROPERTY_VALUE = 35;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__OWNED_EXTENSIONS = ABSTRACT_PROPERTY_VALUE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__ID = ABSTRACT_PROPERTY_VALUE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__SID = ABSTRACT_PROPERTY_VALUE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__CONSTRAINTS = ABSTRACT_PROPERTY_VALUE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__OWNED_CONSTRAINTS = ABSTRACT_PROPERTY_VALUE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__OWNED_MIGRATED_ELEMENTS = ABSTRACT_PROPERTY_VALUE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__NAME = ABSTRACT_PROPERTY_VALUE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__INCOMING_TRACES = ABSTRACT_PROPERTY_VALUE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__OUTGOING_TRACES = ABSTRACT_PROPERTY_VALUE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__VISIBLE_IN_DOC = ABSTRACT_PROPERTY_VALUE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__VISIBLE_IN_LM = ABSTRACT_PROPERTY_VALUE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__SUMMARY = ABSTRACT_PROPERTY_VALUE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__DESCRIPTION = ABSTRACT_PROPERTY_VALUE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__REVIEW = ABSTRACT_PROPERTY_VALUE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__OWNED_PROPERTY_VALUES = ABSTRACT_PROPERTY_VALUE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_PROPERTY_VALUE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__APPLIED_PROPERTY_VALUES = ABSTRACT_PROPERTY_VALUE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_PROPERTY_VALUE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_PROPERTY_VALUE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__STATUS = ABSTRACT_PROPERTY_VALUE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__FEATURES = ABSTRACT_PROPERTY_VALUE__FEATURES;

	/**
   * The feature id for the '<em><b>Involved Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__INVOLVED_ELEMENTS = ABSTRACT_PROPERTY_VALUE__INVOLVED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Valued Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__VALUED_ELEMENTS = ABSTRACT_PROPERTY_VALUE__VALUED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__TYPE = ABSTRACT_PROPERTY_VALUE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Value</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE__VALUE = ABSTRACT_PROPERTY_VALUE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Enumeration Property Value</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_VALUE_FEATURE_COUNT = ABSTRACT_PROPERTY_VALUE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.EnumerationPropertyTypeImpl <em>Enumeration Property Type</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.EnumerationPropertyTypeImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getEnumerationPropertyType()
   * @generated
   */
	int ENUMERATION_PROPERTY_TYPE = 36;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__OWNED_EXTENSIONS = NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__ID = NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__SID = NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__CONSTRAINTS = NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__OWNED_CONSTRAINTS = NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__OWNED_MIGRATED_ELEMENTS = NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__NAME = NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__INCOMING_TRACES = NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__OUTGOING_TRACES = NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__VISIBLE_IN_DOC = NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__VISIBLE_IN_LM = NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__SUMMARY = NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__REVIEW = NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__OWNED_PROPERTY_VALUES = NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__OWNED_ENUMERATION_PROPERTY_TYPES = NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__APPLIED_PROPERTY_VALUES = NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__OWNED_PROPERTY_VALUE_GROUPS = NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__APPLIED_PROPERTY_VALUE_GROUPS = NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__STATUS = NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__FEATURES = NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Literals</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE__OWNED_LITERALS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Enumeration Property Type</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_TYPE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.EnumerationPropertyLiteralImpl <em>Enumeration Property Literal</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.EnumerationPropertyLiteralImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getEnumerationPropertyLiteral()
   * @generated
   */
	int ENUMERATION_PROPERTY_LITERAL = 37;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__OWNED_EXTENSIONS = NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__ID = NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__SID = NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__CONSTRAINTS = NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__OWNED_CONSTRAINTS = NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__OWNED_MIGRATED_ELEMENTS = NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__NAME = NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__INCOMING_TRACES = NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__OUTGOING_TRACES = NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__VISIBLE_IN_DOC = NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__VISIBLE_IN_LM = NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__SUMMARY = NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__REVIEW = NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__OWNED_PROPERTY_VALUES = NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__OWNED_ENUMERATION_PROPERTY_TYPES = NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__APPLIED_PROPERTY_VALUES = NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__OWNED_PROPERTY_VALUE_GROUPS = NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__APPLIED_PROPERTY_VALUE_GROUPS = NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__STATUS = NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL__FEATURES = NAMED_ELEMENT__FEATURES;

	/**
   * The number of structural features of the '<em>Enumeration Property Literal</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENUMERATION_PROPERTY_LITERAL_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.PropertyValueGroupImpl <em>Property Value Group</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.PropertyValueGroupImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getPropertyValueGroup()
   * @generated
   */
	int PROPERTY_VALUE_GROUP = 38;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__OWNED_EXTENSIONS = NAMESPACE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__ID = NAMESPACE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__SID = NAMESPACE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__CONSTRAINTS = NAMESPACE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__OWNED_CONSTRAINTS = NAMESPACE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__OWNED_MIGRATED_ELEMENTS = NAMESPACE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__NAME = NAMESPACE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__INCOMING_TRACES = NAMESPACE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__OUTGOING_TRACES = NAMESPACE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__VISIBLE_IN_DOC = NAMESPACE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__VISIBLE_IN_LM = NAMESPACE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__SUMMARY = NAMESPACE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__DESCRIPTION = NAMESPACE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__REVIEW = NAMESPACE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__OWNED_PROPERTY_VALUES = NAMESPACE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__OWNED_ENUMERATION_PROPERTY_TYPES = NAMESPACE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__APPLIED_PROPERTY_VALUES = NAMESPACE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__OWNED_PROPERTY_VALUE_GROUPS = NAMESPACE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__APPLIED_PROPERTY_VALUE_GROUPS = NAMESPACE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__STATUS = NAMESPACE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__FEATURES = NAMESPACE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__OWNED_TRACES = NAMESPACE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__CONTAINED_GENERIC_TRACES = NAMESPACE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__NAMING_RULES = NAMESPACE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Valued Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP__VALUED_ELEMENTS = NAMESPACE_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Property Value Group</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_GROUP_FEATURE_COUNT = NAMESPACE_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.PropertyValuePkgImpl <em>Property Value Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.PropertyValuePkgImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getPropertyValuePkg()
   * @generated
   */
	int PROPERTY_VALUE_PKG = 39;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__OWNED_EXTENSIONS = STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__ID = STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__SID = STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__CONSTRAINTS = STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__OWNED_CONSTRAINTS = STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__OWNED_MIGRATED_ELEMENTS = STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__NAME = STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__INCOMING_TRACES = STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__OUTGOING_TRACES = STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__VISIBLE_IN_DOC = STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__VISIBLE_IN_LM = STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__SUMMARY = STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__DESCRIPTION = STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__REVIEW = STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__OWNED_PROPERTY_VALUES = STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__APPLIED_PROPERTY_VALUES = STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__OWNED_PROPERTY_VALUE_GROUPS = STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__APPLIED_PROPERTY_VALUE_GROUPS = STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__STATUS = STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__FEATURES = STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__OWNED_TRACES = STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__CONTAINED_GENERIC_TRACES = STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__NAMING_RULES = STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG__OWNED_PROPERTY_VALUE_PKGS = STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The number of structural features of the '<em>Property Value Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROPERTY_VALUE_PKG_FEATURE_COUNT = STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.impl.AbstractDependenciesPkgImpl <em>Abstract Dependencies Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.impl.AbstractDependenciesPkgImpl
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getAbstractDependenciesPkg()
   * @generated
   */
	int ABSTRACT_DEPENDENCIES_PKG = 40;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__OWNED_EXTENSIONS = STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__ID = STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__SID = STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__CONSTRAINTS = STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__OWNED_CONSTRAINTS = STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__OWNED_MIGRATED_ELEMENTS = STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__NAME = STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__INCOMING_TRACES = STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__OUTGOING_TRACES = STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__VISIBLE_IN_DOC = STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__VISIBLE_IN_LM = STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__SUMMARY = STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__DESCRIPTION = STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__REVIEW = STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__OWNED_PROPERTY_VALUES = STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__APPLIED_PROPERTY_VALUES = STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__OWNED_PROPERTY_VALUE_GROUPS = STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__APPLIED_PROPERTY_VALUE_GROUPS = STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__STATUS = STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__FEATURES = STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__OWNED_TRACES = STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__CONTAINED_GENERIC_TRACES = STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__NAMING_RULES = STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG__OWNED_PROPERTY_VALUE_PKGS = STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The number of structural features of the '<em>Abstract Dependencies Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPENDENCIES_PKG_FEATURE_COUNT = STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.capellacore.VisibilityKind <em>Visibility Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.capellacore.VisibilityKind
   * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getVisibilityKind()
   * @generated
   */
	int VISIBILITY_KIND = 41;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.CapellaElement <em>Capella Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Capella Element</em>'.
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement
   * @generated
   */
	EClass getCapellaElement();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getSummary <em>Summary</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Summary</em>'.
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement#getSummary()
   * @see #getCapellaElement()
   * @generated
   */
	EAttribute getCapellaElement_Summary();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement#getDescription()
   * @see #getCapellaElement()
   * @generated
   */
	EAttribute getCapellaElement_Description();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getReview <em>Review</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Review</em>'.
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement#getReview()
   * @see #getCapellaElement()
   * @generated
   */
	EAttribute getCapellaElement_Review();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getOwnedPropertyValues <em>Owned Property Values</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Property Values</em>'.
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement#getOwnedPropertyValues()
   * @see #getCapellaElement()
   * @generated
   */
	EReference getCapellaElement_OwnedPropertyValues();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getOwnedEnumerationPropertyTypes <em>Owned Enumeration Property Types</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Enumeration Property Types</em>'.
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement#getOwnedEnumerationPropertyTypes()
   * @see #getCapellaElement()
   * @generated
   */
	EReference getCapellaElement_OwnedEnumerationPropertyTypes();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getAppliedPropertyValues <em>Applied Property Values</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Applied Property Values</em>'.
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement#getAppliedPropertyValues()
   * @see #getCapellaElement()
   * @generated
   */
	EReference getCapellaElement_AppliedPropertyValues();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getOwnedPropertyValueGroups <em>Owned Property Value Groups</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Property Value Groups</em>'.
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement#getOwnedPropertyValueGroups()
   * @see #getCapellaElement()
   * @generated
   */
	EReference getCapellaElement_OwnedPropertyValueGroups();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getAppliedPropertyValueGroups <em>Applied Property Value Groups</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Applied Property Value Groups</em>'.
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement#getAppliedPropertyValueGroups()
   * @see #getCapellaElement()
   * @generated
   */
	EReference getCapellaElement_AppliedPropertyValueGroups();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getStatus <em>Status</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Status</em>'.
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement#getStatus()
   * @see #getCapellaElement()
   * @generated
   */
	EReference getCapellaElement_Status();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.CapellaElement#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Features</em>'.
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement#getFeatures()
   * @see #getCapellaElement()
   * @generated
   */
	EReference getCapellaElement_Features();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.NamedElement <em>Named Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Named Element</em>'.
   * @see org.polarsys.capella.core.data.capellacore.NamedElement
   * @generated
   */
	EClass getNamedElement();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.Relationship <em>Relationship</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Relationship</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Relationship
   * @generated
   */
	EClass getRelationship();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.Namespace <em>Namespace</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Namespace</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Namespace
   * @generated
   */
	EClass getNamespace();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacore.Namespace#getOwnedTraces <em>Owned Traces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Traces</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Namespace#getOwnedTraces()
   * @see #getNamespace()
   * @generated
   */
	EReference getNamespace_OwnedTraces();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.Namespace#getContainedGenericTraces <em>Contained Generic Traces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Generic Traces</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Namespace#getContainedGenericTraces()
   * @see #getNamespace()
   * @generated
   */
	EReference getNamespace_ContainedGenericTraces();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacore.Namespace#getNamingRules <em>Naming Rules</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Naming Rules</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Namespace#getNamingRules()
   * @see #getNamespace()
   * @generated
   */
	EReference getNamespace_NamingRules();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.NamedRelationship <em>Named Relationship</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Named Relationship</em>'.
   * @see org.polarsys.capella.core.data.capellacore.NamedRelationship
   * @generated
   */
	EClass getNamedRelationship();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacore.NamedRelationship#getNamingRules <em>Naming Rules</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Naming Rules</em>'.
   * @see org.polarsys.capella.core.data.capellacore.NamedRelationship#getNamingRules()
   * @see #getNamedRelationship()
   * @generated
   */
	EReference getNamedRelationship_NamingRules();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.Structure <em>Structure</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Structure</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Structure
   * @generated
   */
	EClass getStructure();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacore.Structure#getOwnedPropertyValuePkgs <em>Owned Property Value Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Property Value Pkgs</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Structure#getOwnedPropertyValuePkgs()
   * @see #getStructure()
   * @generated
   */
	EReference getStructure_OwnedPropertyValuePkgs();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.AbstractModellingStructure <em>Abstract Modelling Structure</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Modelling Structure</em>'.
   * @see org.polarsys.capella.core.data.capellacore.AbstractModellingStructure
   * @generated
   */
	EClass getAbstractModellingStructure();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacore.AbstractModellingStructure#getOwnedArchitectures <em>Owned Architectures</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Architectures</em>'.
   * @see org.polarsys.capella.core.data.capellacore.AbstractModellingStructure#getOwnedArchitectures()
   * @see #getAbstractModellingStructure()
   * @generated
   */
	EReference getAbstractModellingStructure_OwnedArchitectures();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacore.AbstractModellingStructure#getOwnedArchitecturePkgs <em>Owned Architecture Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Architecture Pkgs</em>'.
   * @see org.polarsys.capella.core.data.capellacore.AbstractModellingStructure#getOwnedArchitecturePkgs()
   * @see #getAbstractModellingStructure()
   * @generated
   */
	EReference getAbstractModellingStructure_OwnedArchitecturePkgs();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.ModellingBlock <em>Modelling Block</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Modelling Block</em>'.
   * @see org.polarsys.capella.core.data.capellacore.ModellingBlock
   * @generated
   */
	EClass getModellingBlock();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.ModellingArchitecture <em>Modelling Architecture</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Modelling Architecture</em>'.
   * @see org.polarsys.capella.core.data.capellacore.ModellingArchitecture
   * @generated
   */
	EClass getModellingArchitecture();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.ModellingArchitecturePkg <em>Modelling Architecture Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Modelling Architecture Pkg</em>'.
   * @see org.polarsys.capella.core.data.capellacore.ModellingArchitecturePkg
   * @generated
   */
	EClass getModellingArchitecturePkg();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.Type <em>Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Type
   * @generated
   */
	EClass getType();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.Type#getTypedElements <em>Typed Elements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Typed Elements</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Type#getTypedElements()
   * @see #getType()
   * @generated
   */
	EReference getType_TypedElements();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.TypedElement <em>Typed Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Typed Element</em>'.
   * @see org.polarsys.capella.core.data.capellacore.TypedElement
   * @generated
   */
	EClass getTypedElement();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacore.TypedElement#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.polarsys.capella.core.data.capellacore.TypedElement#getType()
   * @see #getTypedElement()
   * @generated
   */
	EReference getTypedElement_Type();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.Trace <em>Trace</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Trace</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Trace
   * @generated
   */
	EClass getTrace();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.AbstractAnnotation <em>Abstract Annotation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Annotation</em>'.
   * @see org.polarsys.capella.core.data.capellacore.AbstractAnnotation
   * @generated
   */
	EClass getAbstractAnnotation();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.AbstractAnnotation#getContent <em>Content</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Content</em>'.
   * @see org.polarsys.capella.core.data.capellacore.AbstractAnnotation#getContent()
   * @see #getAbstractAnnotation()
   * @generated
   */
	EAttribute getAbstractAnnotation_Content();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.NamingRule <em>Naming Rule</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Naming Rule</em>'.
   * @see org.polarsys.capella.core.data.capellacore.NamingRule
   * @generated
   */
	EClass getNamingRule();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.NamingRule#getTargetType <em>Target Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Type</em>'.
   * @see org.polarsys.capella.core.data.capellacore.NamingRule#getTargetType()
   * @see #getNamingRule()
   * @generated
   */
	EAttribute getNamingRule_TargetType();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.Constraint <em>Constraint</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Constraint</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Constraint
   * @generated
   */
	EClass getConstraint();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.KeyValue <em>Key Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Key Value</em>'.
   * @see org.polarsys.capella.core.data.capellacore.KeyValue
   * @generated
   */
	EClass getKeyValue();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.KeyValue#getKey <em>Key</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see org.polarsys.capella.core.data.capellacore.KeyValue#getKey()
   * @see #getKeyValue()
   * @generated
   */
	EAttribute getKeyValue_Key();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.KeyValue#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.polarsys.capella.core.data.capellacore.KeyValue#getValue()
   * @see #getKeyValue()
   * @generated
   */
	EAttribute getKeyValue_Value();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.ReuseLink <em>Reuse Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Reuse Link</em>'.
   * @see org.polarsys.capella.core.data.capellacore.ReuseLink
   * @generated
   */
	EClass getReuseLink();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacore.ReuseLink#getReused <em>Reused</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Reused</em>'.
   * @see org.polarsys.capella.core.data.capellacore.ReuseLink#getReused()
   * @see #getReuseLink()
   * @generated
   */
	EReference getReuseLink_Reused();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacore.ReuseLink#getReuser <em>Reuser</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Reuser</em>'.
   * @see org.polarsys.capella.core.data.capellacore.ReuseLink#getReuser()
   * @see #getReuseLink()
   * @generated
   */
	EReference getReuseLink_Reuser();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.ReuseableStructure <em>Reuseable Structure</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Reuseable Structure</em>'.
   * @see org.polarsys.capella.core.data.capellacore.ReuseableStructure
   * @generated
   */
	EClass getReuseableStructure();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.ReuseableStructure#getReuseLinks <em>Reuse Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Reuse Links</em>'.
   * @see org.polarsys.capella.core.data.capellacore.ReuseableStructure#getReuseLinks()
   * @see #getReuseableStructure()
   * @generated
   */
	EReference getReuseableStructure_ReuseLinks();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.ReuserStructure <em>Reuser Structure</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Reuser Structure</em>'.
   * @see org.polarsys.capella.core.data.capellacore.ReuserStructure
   * @generated
   */
	EClass getReuserStructure();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.ReuserStructure#getReuseLinks <em>Reuse Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Reuse Links</em>'.
   * @see org.polarsys.capella.core.data.capellacore.ReuserStructure#getReuseLinks()
   * @see #getReuserStructure()
   * @generated
   */
	EReference getReuserStructure_ReuseLinks();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacore.ReuserStructure#getOwnedReuseLinks <em>Owned Reuse Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Reuse Links</em>'.
   * @see org.polarsys.capella.core.data.capellacore.ReuserStructure#getOwnedReuseLinks()
   * @see #getReuserStructure()
   * @generated
   */
	EReference getReuserStructure_OwnedReuseLinks();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement <em>Generalizable Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Generalizable Element</em>'.
   * @see org.polarsys.capella.core.data.capellacore.GeneralizableElement
   * @generated
   */
	EClass getGeneralizableElement();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement#isAbstract <em>Abstract</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Abstract</em>'.
   * @see org.polarsys.capella.core.data.capellacore.GeneralizableElement#isAbstract()
   * @see #getGeneralizableElement()
   * @generated
   */
	EAttribute getGeneralizableElement_Abstract();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement#getOwnedGeneralizations <em>Owned Generalizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Generalizations</em>'.
   * @see org.polarsys.capella.core.data.capellacore.GeneralizableElement#getOwnedGeneralizations()
   * @see #getGeneralizableElement()
   * @generated
   */
	EReference getGeneralizableElement_OwnedGeneralizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSuperGeneralizations <em>Super Generalizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Super Generalizations</em>'.
   * @see org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSuperGeneralizations()
   * @see #getGeneralizableElement()
   * @generated
   */
	EReference getGeneralizableElement_SuperGeneralizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSubGeneralizations <em>Sub Generalizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Sub Generalizations</em>'.
   * @see org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSubGeneralizations()
   * @see #getGeneralizableElement()
   * @generated
   */
	EReference getGeneralizableElement_SubGeneralizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSuper <em>Super</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Super</em>'.
   * @see org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSuper()
   * @see #getGeneralizableElement()
   * @generated
   */
	EReference getGeneralizableElement_Super();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSub <em>Sub</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Sub</em>'.
   * @see org.polarsys.capella.core.data.capellacore.GeneralizableElement#getSub()
   * @see #getGeneralizableElement()
   * @generated
   */
	EReference getGeneralizableElement_Sub();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.Classifier <em>Classifier</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Classifier</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Classifier
   * @generated
   */
	EClass getClassifier();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacore.Classifier#getOwnedFeatures <em>Owned Features</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Features</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Classifier#getOwnedFeatures()
   * @see #getClassifier()
   * @generated
   */
	EReference getClassifier_OwnedFeatures();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.Classifier#getContainedProperties <em>Contained Properties</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Properties</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Classifier#getContainedProperties()
   * @see #getClassifier()
   * @generated
   */
	EReference getClassifier_ContainedProperties();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.GeneralClass <em>General Class</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>General Class</em>'.
   * @see org.polarsys.capella.core.data.capellacore.GeneralClass
   * @generated
   */
	EClass getGeneralClass();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.GeneralClass#getVisibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visibility</em>'.
   * @see org.polarsys.capella.core.data.capellacore.GeneralClass#getVisibility()
   * @see #getGeneralClass()
   * @generated
   */
	EAttribute getGeneralClass_Visibility();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.GeneralClass#getContainedOperations <em>Contained Operations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Operations</em>'.
   * @see org.polarsys.capella.core.data.capellacore.GeneralClass#getContainedOperations()
   * @see #getGeneralClass()
   * @generated
   */
	EReference getGeneralClass_ContainedOperations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacore.GeneralClass#getNestedGeneralClasses <em>Nested General Classes</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Nested General Classes</em>'.
   * @see org.polarsys.capella.core.data.capellacore.GeneralClass#getNestedGeneralClasses()
   * @see #getGeneralClass()
   * @generated
   */
	EReference getGeneralClass_NestedGeneralClasses();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.Generalization <em>Generalization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Generalization</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Generalization
   * @generated
   */
	EClass getGeneralization();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacore.Generalization#getSuper <em>Super</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Super</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Generalization#getSuper()
   * @see #getGeneralization()
   * @generated
   */
	EReference getGeneralization_Super();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacore.Generalization#getSub <em>Sub</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Sub</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Generalization#getSub()
   * @see #getGeneralization()
   * @generated
   */
	EReference getGeneralization_Sub();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.Feature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Feature
   * @generated
   */
	EClass getFeature();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.Feature#isIsAbstract <em>Is Abstract</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Abstract</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Feature#isIsAbstract()
   * @see #getFeature()
   * @generated
   */
	EAttribute getFeature_IsAbstract();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.Feature#isIsStatic <em>Is Static</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Static</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Feature#isIsStatic()
   * @see #getFeature()
   * @generated
   */
	EAttribute getFeature_IsStatic();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.Feature#getVisibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visibility</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Feature#getVisibility()
   * @see #getFeature()
   * @generated
   */
	EAttribute getFeature_Visibility();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg <em>Abstract Exchange Item Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Exchange Item Pkg</em>'.
   * @see org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg
   * @generated
   */
	EClass getAbstractExchangeItemPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg#getOwnedExchangeItems <em>Owned Exchange Items</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Exchange Items</em>'.
   * @see org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg#getOwnedExchangeItems()
   * @see #getAbstractExchangeItemPkg()
   * @generated
   */
	EReference getAbstractExchangeItemPkg_OwnedExchangeItems();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.Allocation <em>Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Allocation</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Allocation
   * @generated
   */
	EClass getAllocation();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.Involvement <em>Involvement</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Involvement</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Involvement
   * @generated
   */
	EClass getInvolvement();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacore.Involvement#getInvolver <em>Involver</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Involver</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Involvement#getInvolver()
   * @see #getInvolvement()
   * @generated
   */
	EReference getInvolvement_Involver();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacore.Involvement#getInvolved <em>Involved</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Involved</em>'.
   * @see org.polarsys.capella.core.data.capellacore.Involvement#getInvolved()
   * @see #getInvolvement()
   * @generated
   */
	EReference getInvolvement_Involved();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.InvolverElement <em>Involver Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Involver Element</em>'.
   * @see org.polarsys.capella.core.data.capellacore.InvolverElement
   * @generated
   */
	EClass getInvolverElement();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.InvolverElement#getInvolvedInvolvements <em>Involved Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involved Involvements</em>'.
   * @see org.polarsys.capella.core.data.capellacore.InvolverElement#getInvolvedInvolvements()
   * @see #getInvolverElement()
   * @generated
   */
	EReference getInvolverElement_InvolvedInvolvements();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.InvolvedElement <em>Involved Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Involved Element</em>'.
   * @see org.polarsys.capella.core.data.capellacore.InvolvedElement
   * @generated
   */
	EClass getInvolvedElement();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.InvolvedElement#getInvolvingInvolvements <em>Involving Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involving Involvements</em>'.
   * @see org.polarsys.capella.core.data.capellacore.InvolvedElement#getInvolvingInvolvements()
   * @see #getInvolvedElement()
   * @generated
   */
	EReference getInvolvedElement_InvolvingInvolvements();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.AbstractPropertyValue <em>Abstract Property Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Property Value</em>'.
   * @see org.polarsys.capella.core.data.capellacore.AbstractPropertyValue
   * @generated
   */
	EClass getAbstractPropertyValue();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.AbstractPropertyValue#getInvolvedElements <em>Involved Elements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involved Elements</em>'.
   * @see org.polarsys.capella.core.data.capellacore.AbstractPropertyValue#getInvolvedElements()
   * @see #getAbstractPropertyValue()
   * @generated
   */
	EReference getAbstractPropertyValue_InvolvedElements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.AbstractPropertyValue#getValuedElements <em>Valued Elements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Valued Elements</em>'.
   * @see org.polarsys.capella.core.data.capellacore.AbstractPropertyValue#getValuedElements()
   * @see #getAbstractPropertyValue()
   * @generated
   */
	EReference getAbstractPropertyValue_ValuedElements();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.StringPropertyValue <em>String Property Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Property Value</em>'.
   * @see org.polarsys.capella.core.data.capellacore.StringPropertyValue
   * @generated
   */
	EClass getStringPropertyValue();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.StringPropertyValue#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.polarsys.capella.core.data.capellacore.StringPropertyValue#getValue()
   * @see #getStringPropertyValue()
   * @generated
   */
	EAttribute getStringPropertyValue_Value();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.IntegerPropertyValue <em>Integer Property Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Integer Property Value</em>'.
   * @see org.polarsys.capella.core.data.capellacore.IntegerPropertyValue
   * @generated
   */
	EClass getIntegerPropertyValue();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.IntegerPropertyValue#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.polarsys.capella.core.data.capellacore.IntegerPropertyValue#getValue()
   * @see #getIntegerPropertyValue()
   * @generated
   */
	EAttribute getIntegerPropertyValue_Value();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.BooleanPropertyValue <em>Boolean Property Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Boolean Property Value</em>'.
   * @see org.polarsys.capella.core.data.capellacore.BooleanPropertyValue
   * @generated
   */
	EClass getBooleanPropertyValue();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.BooleanPropertyValue#isValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.polarsys.capella.core.data.capellacore.BooleanPropertyValue#isValue()
   * @see #getBooleanPropertyValue()
   * @generated
   */
	EAttribute getBooleanPropertyValue_Value();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.FloatPropertyValue <em>Float Property Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Float Property Value</em>'.
   * @see org.polarsys.capella.core.data.capellacore.FloatPropertyValue
   * @generated
   */
	EClass getFloatPropertyValue();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.capellacore.FloatPropertyValue#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.polarsys.capella.core.data.capellacore.FloatPropertyValue#getValue()
   * @see #getFloatPropertyValue()
   * @generated
   */
	EAttribute getFloatPropertyValue_Value();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue <em>Enumeration Property Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enumeration Property Value</em>'.
   * @see org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue
   * @generated
   */
	EClass getEnumerationPropertyValue();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue#getType()
   * @see #getEnumerationPropertyValue()
   * @generated
   */
	EReference getEnumerationPropertyValue_Type();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Value</em>'.
   * @see org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue#getValue()
   * @see #getEnumerationPropertyValue()
   * @generated
   */
	EReference getEnumerationPropertyValue_Value();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.EnumerationPropertyType <em>Enumeration Property Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enumeration Property Type</em>'.
   * @see org.polarsys.capella.core.data.capellacore.EnumerationPropertyType
   * @generated
   */
	EClass getEnumerationPropertyType();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.capellacore.EnumerationPropertyType#getOwnedLiterals <em>Owned Literals</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Literals</em>'.
   * @see org.polarsys.capella.core.data.capellacore.EnumerationPropertyType#getOwnedLiterals()
   * @see #getEnumerationPropertyType()
   * @generated
   */
	EReference getEnumerationPropertyType_OwnedLiterals();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral <em>Enumeration Property Literal</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enumeration Property Literal</em>'.
   * @see org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral
   * @generated
   */
	EClass getEnumerationPropertyLiteral();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.PropertyValueGroup <em>Property Value Group</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Value Group</em>'.
   * @see org.polarsys.capella.core.data.capellacore.PropertyValueGroup
   * @generated
   */
	EClass getPropertyValueGroup();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.capellacore.PropertyValueGroup#getValuedElements <em>Valued Elements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Valued Elements</em>'.
   * @see org.polarsys.capella.core.data.capellacore.PropertyValueGroup#getValuedElements()
   * @see #getPropertyValueGroup()
   * @generated
   */
	EReference getPropertyValueGroup_ValuedElements();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.PropertyValuePkg <em>Property Value Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Value Pkg</em>'.
   * @see org.polarsys.capella.core.data.capellacore.PropertyValuePkg
   * @generated
   */
	EClass getPropertyValuePkg();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg <em>Abstract Dependencies Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Dependencies Pkg</em>'.
   * @see org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg
   * @generated
   */
	EClass getAbstractDependenciesPkg();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.capellacore.VisibilityKind <em>Visibility Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Visibility Kind</em>'.
   * @see org.polarsys.capella.core.data.capellacore.VisibilityKind
   * @generated
   */
	EEnum getVisibilityKind();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	CapellacoreFactory getCapellacoreFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.CapellaElement <em>Capella Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.CapellaElement
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getCapellaElement()
     * @generated
     */
		EClass CAPELLA_ELEMENT = eINSTANCE.getCapellaElement();

		/**
     * The meta object literal for the '<em><b>Summary</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CAPELLA_ELEMENT__SUMMARY = eINSTANCE.getCapellaElement_Summary();

		/**
     * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CAPELLA_ELEMENT__DESCRIPTION = eINSTANCE.getCapellaElement_Description();

		/**
     * The meta object literal for the '<em><b>Review</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CAPELLA_ELEMENT__REVIEW = eINSTANCE.getCapellaElement_Review();

		/**
     * The meta object literal for the '<em><b>Owned Property Values</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES = eINSTANCE.getCapellaElement_OwnedPropertyValues();

		/**
     * The meta object literal for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = eINSTANCE.getCapellaElement_OwnedEnumerationPropertyTypes();

		/**
     * The meta object literal for the '<em><b>Applied Property Values</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES = eINSTANCE.getCapellaElement_AppliedPropertyValues();

		/**
     * The meta object literal for the '<em><b>Owned Property Value Groups</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS = eINSTANCE.getCapellaElement_OwnedPropertyValueGroups();

		/**
     * The meta object literal for the '<em><b>Applied Property Value Groups</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS = eINSTANCE.getCapellaElement_AppliedPropertyValueGroups();

		/**
     * The meta object literal for the '<em><b>Status</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPELLA_ELEMENT__STATUS = eINSTANCE.getCapellaElement_Status();

		/**
     * The meta object literal for the '<em><b>Features</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPELLA_ELEMENT__FEATURES = eINSTANCE.getCapellaElement_Features();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl <em>Named Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getNamedElement()
     * @generated
     */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl <em>Relationship</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getRelationship()
     * @generated
     */
		EClass RELATIONSHIP = eINSTANCE.getRelationship();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.Namespace <em>Namespace</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.Namespace
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getNamespace()
     * @generated
     */
		EClass NAMESPACE = eINSTANCE.getNamespace();

		/**
     * The meta object literal for the '<em><b>Owned Traces</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference NAMESPACE__OWNED_TRACES = eINSTANCE.getNamespace_OwnedTraces();

		/**
     * The meta object literal for the '<em><b>Contained Generic Traces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference NAMESPACE__CONTAINED_GENERIC_TRACES = eINSTANCE.getNamespace_ContainedGenericTraces();

		/**
     * The meta object literal for the '<em><b>Naming Rules</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference NAMESPACE__NAMING_RULES = eINSTANCE.getNamespace_NamingRules();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.NamedRelationship <em>Named Relationship</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.NamedRelationship
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getNamedRelationship()
     * @generated
     */
		EClass NAMED_RELATIONSHIP = eINSTANCE.getNamedRelationship();

		/**
     * The meta object literal for the '<em><b>Naming Rules</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference NAMED_RELATIONSHIP__NAMING_RULES = eINSTANCE.getNamedRelationship_NamingRules();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.Structure <em>Structure</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.Structure
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getStructure()
     * @generated
     */
		EClass STRUCTURE = eINSTANCE.getStructure();

		/**
     * The meta object literal for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference STRUCTURE__OWNED_PROPERTY_VALUE_PKGS = eINSTANCE.getStructure_OwnedPropertyValuePkgs();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.AbstractModellingStructureImpl <em>Abstract Modelling Structure</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.AbstractModellingStructureImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getAbstractModellingStructure()
     * @generated
     */
		EClass ABSTRACT_MODELLING_STRUCTURE = eINSTANCE.getAbstractModellingStructure();

		/**
     * The meta object literal for the '<em><b>Owned Architectures</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_MODELLING_STRUCTURE__OWNED_ARCHITECTURES = eINSTANCE.getAbstractModellingStructure_OwnedArchitectures();

		/**
     * The meta object literal for the '<em><b>Owned Architecture Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_MODELLING_STRUCTURE__OWNED_ARCHITECTURE_PKGS = eINSTANCE.getAbstractModellingStructure_OwnedArchitecturePkgs();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.ModellingBlock <em>Modelling Block</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.ModellingBlock
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getModellingBlock()
     * @generated
     */
		EClass MODELLING_BLOCK = eINSTANCE.getModellingBlock();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.ModellingArchitecture <em>Modelling Architecture</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.ModellingArchitecture
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getModellingArchitecture()
     * @generated
     */
		EClass MODELLING_ARCHITECTURE = eINSTANCE.getModellingArchitecture();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.ModellingArchitecturePkg <em>Modelling Architecture Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.ModellingArchitecturePkg
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getModellingArchitecturePkg()
     * @generated
     */
		EClass MODELLING_ARCHITECTURE_PKG = eINSTANCE.getModellingArchitecturePkg();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.TypeImpl <em>Type</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.TypeImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getType()
     * @generated
     */
		EClass TYPE = eINSTANCE.getType();

		/**
     * The meta object literal for the '<em><b>Typed Elements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TYPE__TYPED_ELEMENTS = eINSTANCE.getType_TypedElements();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.TypedElementImpl <em>Typed Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.TypedElementImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getTypedElement()
     * @generated
     */
		EClass TYPED_ELEMENT = eINSTANCE.getTypedElement();

		/**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference TYPED_ELEMENT__TYPE = eINSTANCE.getTypedElement_Type();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.TraceImpl <em>Trace</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.TraceImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getTrace()
     * @generated
     */
		EClass TRACE = eINSTANCE.getTrace();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.AbstractAnnotation <em>Abstract Annotation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.AbstractAnnotation
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getAbstractAnnotation()
     * @generated
     */
		EClass ABSTRACT_ANNOTATION = eINSTANCE.getAbstractAnnotation();

		/**
     * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute ABSTRACT_ANNOTATION__CONTENT = eINSTANCE.getAbstractAnnotation_Content();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.NamingRuleImpl <em>Naming Rule</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.NamingRuleImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getNamingRule()
     * @generated
     */
		EClass NAMING_RULE = eINSTANCE.getNamingRule();

		/**
     * The meta object literal for the '<em><b>Target Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute NAMING_RULE__TARGET_TYPE = eINSTANCE.getNamingRule_TargetType();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.ConstraintImpl <em>Constraint</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.ConstraintImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getConstraint()
     * @generated
     */
		EClass CONSTRAINT = eINSTANCE.getConstraint();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.KeyValueImpl <em>Key Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.KeyValueImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getKeyValue()
     * @generated
     */
		EClass KEY_VALUE = eINSTANCE.getKeyValue();

		/**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute KEY_VALUE__KEY = eINSTANCE.getKeyValue_Key();

		/**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute KEY_VALUE__VALUE = eINSTANCE.getKeyValue_Value();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.ReuseLinkImpl <em>Reuse Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.ReuseLinkImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getReuseLink()
     * @generated
     */
		EClass REUSE_LINK = eINSTANCE.getReuseLink();

		/**
     * The meta object literal for the '<em><b>Reused</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference REUSE_LINK__REUSED = eINSTANCE.getReuseLink_Reused();

		/**
     * The meta object literal for the '<em><b>Reuser</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference REUSE_LINK__REUSER = eINSTANCE.getReuseLink_Reuser();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.ReuseableStructure <em>Reuseable Structure</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.ReuseableStructure
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getReuseableStructure()
     * @generated
     */
		EClass REUSEABLE_STRUCTURE = eINSTANCE.getReuseableStructure();

		/**
     * The meta object literal for the '<em><b>Reuse Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference REUSEABLE_STRUCTURE__REUSE_LINKS = eINSTANCE.getReuseableStructure_ReuseLinks();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.ReuserStructure <em>Reuser Structure</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.ReuserStructure
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getReuserStructure()
     * @generated
     */
		EClass REUSER_STRUCTURE = eINSTANCE.getReuserStructure();

		/**
     * The meta object literal for the '<em><b>Reuse Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference REUSER_STRUCTURE__REUSE_LINKS = eINSTANCE.getReuserStructure_ReuseLinks();

		/**
     * The meta object literal for the '<em><b>Owned Reuse Links</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference REUSER_STRUCTURE__OWNED_REUSE_LINKS = eINSTANCE.getReuserStructure_OwnedReuseLinks();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.GeneralizableElementImpl <em>Generalizable Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.GeneralizableElementImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getGeneralizableElement()
     * @generated
     */
		EClass GENERALIZABLE_ELEMENT = eINSTANCE.getGeneralizableElement();

		/**
     * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute GENERALIZABLE_ELEMENT__ABSTRACT = eINSTANCE.getGeneralizableElement_Abstract();

		/**
     * The meta object literal for the '<em><b>Owned Generalizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS = eINSTANCE.getGeneralizableElement_OwnedGeneralizations();

		/**
     * The meta object literal for the '<em><b>Super Generalizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS = eINSTANCE.getGeneralizableElement_SuperGeneralizations();

		/**
     * The meta object literal for the '<em><b>Sub Generalizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS = eINSTANCE.getGeneralizableElement_SubGeneralizations();

		/**
     * The meta object literal for the '<em><b>Super</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference GENERALIZABLE_ELEMENT__SUPER = eINSTANCE.getGeneralizableElement_Super();

		/**
     * The meta object literal for the '<em><b>Sub</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference GENERALIZABLE_ELEMENT__SUB = eINSTANCE.getGeneralizableElement_Sub();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.ClassifierImpl <em>Classifier</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.ClassifierImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getClassifier()
     * @generated
     */
		EClass CLASSIFIER = eINSTANCE.getClassifier();

		/**
     * The meta object literal for the '<em><b>Owned Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CLASSIFIER__OWNED_FEATURES = eINSTANCE.getClassifier_OwnedFeatures();

		/**
     * The meta object literal for the '<em><b>Contained Properties</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CLASSIFIER__CONTAINED_PROPERTIES = eINSTANCE.getClassifier_ContainedProperties();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.GeneralClassImpl <em>General Class</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.GeneralClassImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getGeneralClass()
     * @generated
     */
		EClass GENERAL_CLASS = eINSTANCE.getGeneralClass();

		/**
     * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute GENERAL_CLASS__VISIBILITY = eINSTANCE.getGeneralClass_Visibility();

		/**
     * The meta object literal for the '<em><b>Contained Operations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference GENERAL_CLASS__CONTAINED_OPERATIONS = eINSTANCE.getGeneralClass_ContainedOperations();

		/**
     * The meta object literal for the '<em><b>Nested General Classes</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference GENERAL_CLASS__NESTED_GENERAL_CLASSES = eINSTANCE.getGeneralClass_NestedGeneralClasses();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.GeneralizationImpl <em>Generalization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.GeneralizationImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getGeneralization()
     * @generated
     */
		EClass GENERALIZATION = eINSTANCE.getGeneralization();

		/**
     * The meta object literal for the '<em><b>Super</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference GENERALIZATION__SUPER = eINSTANCE.getGeneralization_Super();

		/**
     * The meta object literal for the '<em><b>Sub</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference GENERALIZATION__SUB = eINSTANCE.getGeneralization_Sub();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.FeatureImpl <em>Feature</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.FeatureImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getFeature()
     * @generated
     */
		EClass FEATURE = eINSTANCE.getFeature();

		/**
     * The meta object literal for the '<em><b>Is Abstract</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute FEATURE__IS_ABSTRACT = eINSTANCE.getFeature_IsAbstract();

		/**
     * The meta object literal for the '<em><b>Is Static</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute FEATURE__IS_STATIC = eINSTANCE.getFeature_IsStatic();

		/**
     * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute FEATURE__VISIBILITY = eINSTANCE.getFeature_Visibility();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.AbstractExchangeItemPkgImpl <em>Abstract Exchange Item Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.AbstractExchangeItemPkgImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getAbstractExchangeItemPkg()
     * @generated
     */
		EClass ABSTRACT_EXCHANGE_ITEM_PKG = eINSTANCE.getAbstractExchangeItemPkg();

		/**
     * The meta object literal for the '<em><b>Owned Exchange Items</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_EXCHANGE_ITEM_PKG__OWNED_EXCHANGE_ITEMS = eINSTANCE.getAbstractExchangeItemPkg_OwnedExchangeItems();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.AllocationImpl <em>Allocation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.AllocationImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getAllocation()
     * @generated
     */
		EClass ALLOCATION = eINSTANCE.getAllocation();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.Involvement <em>Involvement</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.Involvement
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getInvolvement()
     * @generated
     */
		EClass INVOLVEMENT = eINSTANCE.getInvolvement();

		/**
     * The meta object literal for the '<em><b>Involver</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INVOLVEMENT__INVOLVER = eINSTANCE.getInvolvement_Involver();

		/**
     * The meta object literal for the '<em><b>Involved</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INVOLVEMENT__INVOLVED = eINSTANCE.getInvolvement_Involved();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.InvolverElement <em>Involver Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.InvolverElement
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getInvolverElement()
     * @generated
     */
		EClass INVOLVER_ELEMENT = eINSTANCE.getInvolverElement();

		/**
     * The meta object literal for the '<em><b>Involved Involvements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS = eINSTANCE.getInvolverElement_InvolvedInvolvements();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.InvolvedElement <em>Involved Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.InvolvedElement
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getInvolvedElement()
     * @generated
     */
		EClass INVOLVED_ELEMENT = eINSTANCE.getInvolvedElement();

		/**
     * The meta object literal for the '<em><b>Involving Involvements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS = eINSTANCE.getInvolvedElement_InvolvingInvolvements();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.AbstractPropertyValueImpl <em>Abstract Property Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.AbstractPropertyValueImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getAbstractPropertyValue()
     * @generated
     */
		EClass ABSTRACT_PROPERTY_VALUE = eINSTANCE.getAbstractPropertyValue();

		/**
     * The meta object literal for the '<em><b>Involved Elements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_PROPERTY_VALUE__INVOLVED_ELEMENTS = eINSTANCE.getAbstractPropertyValue_InvolvedElements();

		/**
     * The meta object literal for the '<em><b>Valued Elements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_PROPERTY_VALUE__VALUED_ELEMENTS = eINSTANCE.getAbstractPropertyValue_ValuedElements();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.StringPropertyValueImpl <em>String Property Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.StringPropertyValueImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getStringPropertyValue()
     * @generated
     */
		EClass STRING_PROPERTY_VALUE = eINSTANCE.getStringPropertyValue();

		/**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute STRING_PROPERTY_VALUE__VALUE = eINSTANCE.getStringPropertyValue_Value();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.IntegerPropertyValueImpl <em>Integer Property Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.IntegerPropertyValueImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getIntegerPropertyValue()
     * @generated
     */
		EClass INTEGER_PROPERTY_VALUE = eINSTANCE.getIntegerPropertyValue();

		/**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute INTEGER_PROPERTY_VALUE__VALUE = eINSTANCE.getIntegerPropertyValue_Value();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.BooleanPropertyValueImpl <em>Boolean Property Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.BooleanPropertyValueImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getBooleanPropertyValue()
     * @generated
     */
		EClass BOOLEAN_PROPERTY_VALUE = eINSTANCE.getBooleanPropertyValue();

		/**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute BOOLEAN_PROPERTY_VALUE__VALUE = eINSTANCE.getBooleanPropertyValue_Value();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.FloatPropertyValueImpl <em>Float Property Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.FloatPropertyValueImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getFloatPropertyValue()
     * @generated
     */
		EClass FLOAT_PROPERTY_VALUE = eINSTANCE.getFloatPropertyValue();

		/**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute FLOAT_PROPERTY_VALUE__VALUE = eINSTANCE.getFloatPropertyValue_Value();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.EnumerationPropertyValueImpl <em>Enumeration Property Value</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.EnumerationPropertyValueImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getEnumerationPropertyValue()
     * @generated
     */
		EClass ENUMERATION_PROPERTY_VALUE = eINSTANCE.getEnumerationPropertyValue();

		/**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENUMERATION_PROPERTY_VALUE__TYPE = eINSTANCE.getEnumerationPropertyValue_Type();

		/**
     * The meta object literal for the '<em><b>Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENUMERATION_PROPERTY_VALUE__VALUE = eINSTANCE.getEnumerationPropertyValue_Value();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.EnumerationPropertyTypeImpl <em>Enumeration Property Type</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.EnumerationPropertyTypeImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getEnumerationPropertyType()
     * @generated
     */
		EClass ENUMERATION_PROPERTY_TYPE = eINSTANCE.getEnumerationPropertyType();

		/**
     * The meta object literal for the '<em><b>Owned Literals</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENUMERATION_PROPERTY_TYPE__OWNED_LITERALS = eINSTANCE.getEnumerationPropertyType_OwnedLiterals();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.EnumerationPropertyLiteralImpl <em>Enumeration Property Literal</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.EnumerationPropertyLiteralImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getEnumerationPropertyLiteral()
     * @generated
     */
		EClass ENUMERATION_PROPERTY_LITERAL = eINSTANCE.getEnumerationPropertyLiteral();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.PropertyValueGroupImpl <em>Property Value Group</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.PropertyValueGroupImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getPropertyValueGroup()
     * @generated
     */
		EClass PROPERTY_VALUE_GROUP = eINSTANCE.getPropertyValueGroup();

		/**
     * The meta object literal for the '<em><b>Valued Elements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PROPERTY_VALUE_GROUP__VALUED_ELEMENTS = eINSTANCE.getPropertyValueGroup_ValuedElements();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.PropertyValuePkgImpl <em>Property Value Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.PropertyValuePkgImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getPropertyValuePkg()
     * @generated
     */
		EClass PROPERTY_VALUE_PKG = eINSTANCE.getPropertyValuePkg();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.impl.AbstractDependenciesPkgImpl <em>Abstract Dependencies Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.impl.AbstractDependenciesPkgImpl
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getAbstractDependenciesPkg()
     * @generated
     */
		EClass ABSTRACT_DEPENDENCIES_PKG = eINSTANCE.getAbstractDependenciesPkg();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.capellacore.VisibilityKind <em>Visibility Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.capellacore.VisibilityKind
     * @see org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl#getVisibilityKind()
     * @generated
     */
		EEnum VISIBILITY_KIND = eINSTANCE.getVisibilityKind();

	}

} //CapellacorePackage
