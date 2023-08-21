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
package org.polarsys.capella.core.data.oa;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

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
 * @see org.polarsys.capella.core.data.oa.OaFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping profileName='Capella'"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='OperationalAnalysis aims at defining the system\'s ecosystem operational analysis modelling language (close to the OVs from NAF/MoDAF).\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='operational' usage\040examples='none' constraints='This package depends on the model CompositeStructure.ecore\r\nThis package depends on the model Interaction.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface OaPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "oa"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/core/oa/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.core.data.oa"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	OaPackage eINSTANCE = org.polarsys.capella.core.data.oa.impl.OaPackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.OperationalAnalysisImpl <em>Operational Analysis</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.OperationalAnalysisImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOperationalAnalysis()
   * @generated
   */
	int OPERATIONAL_ANALYSIS = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_EXTENSIONS = CsPackage.BLOCK_ARCHITECTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__ID = CsPackage.BLOCK_ARCHITECTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__SID = CsPackage.BLOCK_ARCHITECTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__CONSTRAINTS = CsPackage.BLOCK_ARCHITECTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_CONSTRAINTS = CsPackage.BLOCK_ARCHITECTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_MIGRATED_ELEMENTS = CsPackage.BLOCK_ARCHITECTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__NAME = CsPackage.BLOCK_ARCHITECTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__INCOMING_TRACES = CsPackage.BLOCK_ARCHITECTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OUTGOING_TRACES = CsPackage.BLOCK_ARCHITECTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__VISIBLE_IN_DOC = CsPackage.BLOCK_ARCHITECTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__VISIBLE_IN_LM = CsPackage.BLOCK_ARCHITECTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__SUMMARY = CsPackage.BLOCK_ARCHITECTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__DESCRIPTION = CsPackage.BLOCK_ARCHITECTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__REVIEW = CsPackage.BLOCK_ARCHITECTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_PROPERTY_VALUES = CsPackage.BLOCK_ARCHITECTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.BLOCK_ARCHITECTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__APPLIED_PROPERTY_VALUES = CsPackage.BLOCK_ARCHITECTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.BLOCK_ARCHITECTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.BLOCK_ARCHITECTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__STATUS = CsPackage.BLOCK_ARCHITECTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__FEATURES = CsPackage.BLOCK_ARCHITECTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_TRACES = CsPackage.BLOCK_ARCHITECTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__CONTAINED_GENERIC_TRACES = CsPackage.BLOCK_ARCHITECTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__NAMING_RULES = CsPackage.BLOCK_ARCHITECTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_PROPERTY_VALUE_PKGS = CsPackage.BLOCK_ARCHITECTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Function Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_FUNCTION_PKG = CsPackage.BLOCK_ARCHITECTURE__OWNED_FUNCTION_PKG;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_COMPONENT_EXCHANGES = CsPackage.BLOCK_ARCHITECTURE__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.BLOCK_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_FUNCTIONAL_LINKS = CsPackage.BLOCK_ARCHITECTURE__OWNED_FUNCTIONAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_FUNCTIONAL_ALLOCATIONS = CsPackage.BLOCK_ARCHITECTURE__OWNED_FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = CsPackage.BLOCK_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_ABSTRACT_CAPABILITY_PKG = CsPackage.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
   * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_INTERFACE_PKG = CsPackage.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG;

	/**
   * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_DATA_PKG = CsPackage.BLOCK_ARCHITECTURE__OWNED_DATA_PKG;

	/**
   * The feature id for the '<em><b>Provisioned Architecture Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__PROVISIONED_ARCHITECTURE_ALLOCATIONS = CsPackage.BLOCK_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Provisioning Architecture Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__PROVISIONING_ARCHITECTURE_ALLOCATIONS = CsPackage.BLOCK_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__ALLOCATED_ARCHITECTURES = CsPackage.BLOCK_ARCHITECTURE__ALLOCATED_ARCHITECTURES;

	/**
   * The feature id for the '<em><b>Allocating Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__ALLOCATING_ARCHITECTURES = CsPackage.BLOCK_ARCHITECTURE__ALLOCATING_ARCHITECTURES;

	/**
   * The feature id for the '<em><b>System</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__SYSTEM = CsPackage.BLOCK_ARCHITECTURE__SYSTEM;

	/**
   * The feature id for the '<em><b>Owned Role Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG = CsPackage.BLOCK_ARCHITECTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Entity Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG = CsPackage.BLOCK_ARCHITECTURE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Concept Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG = CsPackage.BLOCK_ARCHITECTURE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Contained Operational Capability Pkg</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_CAPABILITY_PKG = CsPackage.BLOCK_ARCHITECTURE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Contained Operational Activity Pkg</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_ACTIVITY_PKG = CsPackage.BLOCK_ARCHITECTURE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Allocating System Analyses</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS__ALLOCATING_SYSTEM_ANALYSES = CsPackage.BLOCK_ARCHITECTURE_FEATURE_COUNT + 5;

	/**
   * The number of structural features of the '<em>Operational Analysis</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS_FEATURE_COUNT = CsPackage.BLOCK_ARCHITECTURE_FEATURE_COUNT + 6;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.OperationalScenarioImpl <em>Operational Scenario</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.OperationalScenarioImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOperationalScenario()
   * @generated
   */
	int OPERATIONAL_SCENARIO = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Context</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__CONTEXT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Objective</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO__OBJECTIVE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Operational Scenario</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_SCENARIO_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.OperationalActivityPkgImpl <em>Operational Activity Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.OperationalActivityPkgImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOperationalActivityPkg()
   * @generated
   */
	int OPERATIONAL_ACTIVITY_PKG = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OWNED_EXTENSIONS = FaPackage.FUNCTION_PKG__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__ID = FaPackage.FUNCTION_PKG__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__SID = FaPackage.FUNCTION_PKG__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__CONSTRAINTS = FaPackage.FUNCTION_PKG__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OWNED_CONSTRAINTS = FaPackage.FUNCTION_PKG__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OWNED_MIGRATED_ELEMENTS = FaPackage.FUNCTION_PKG__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__NAME = FaPackage.FUNCTION_PKG__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__INCOMING_TRACES = FaPackage.FUNCTION_PKG__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OUTGOING_TRACES = FaPackage.FUNCTION_PKG__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__VISIBLE_IN_DOC = FaPackage.FUNCTION_PKG__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__VISIBLE_IN_LM = FaPackage.FUNCTION_PKG__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__SUMMARY = FaPackage.FUNCTION_PKG__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__DESCRIPTION = FaPackage.FUNCTION_PKG__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__REVIEW = FaPackage.FUNCTION_PKG__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OWNED_PROPERTY_VALUES = FaPackage.FUNCTION_PKG__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = FaPackage.FUNCTION_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__APPLIED_PROPERTY_VALUES = FaPackage.FUNCTION_PKG__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OWNED_PROPERTY_VALUE_GROUPS = FaPackage.FUNCTION_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__APPLIED_PROPERTY_VALUE_GROUPS = FaPackage.FUNCTION_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__STATUS = FaPackage.FUNCTION_PKG__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__FEATURES = FaPackage.FUNCTION_PKG__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OWNED_TRACES = FaPackage.FUNCTION_PKG__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__CONTAINED_GENERIC_TRACES = FaPackage.FUNCTION_PKG__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__NAMING_RULES = FaPackage.FUNCTION_PKG__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OWNED_PROPERTY_VALUE_PKGS = FaPackage.FUNCTION_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OWNED_FUNCTIONAL_LINKS = FaPackage.FUNCTION_PKG__OWNED_FUNCTIONAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OWNED_EXCHANGES = FaPackage.FUNCTION_PKG__OWNED_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Exchange Specification Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OWNED_EXCHANGE_SPECIFICATION_REALIZATIONS = FaPackage.FUNCTION_PKG__OWNED_EXCHANGE_SPECIFICATION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OWNED_CATEGORIES = FaPackage.FUNCTION_PKG__OWNED_CATEGORIES;

	/**
   * The feature id for the '<em><b>Owned Function Specifications</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OWNED_FUNCTION_SPECIFICATIONS = FaPackage.FUNCTION_PKG__OWNED_FUNCTION_SPECIFICATIONS;

	/**
   * The feature id for the '<em><b>Owned Operational Activities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES = FaPackage.FUNCTION_PKG_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Operational Activity Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITY_PKGS = FaPackage.FUNCTION_PKG_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Operational Activity Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_PKG_FEATURE_COUNT = FaPackage.FUNCTION_PKG_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.OperationalActivityImpl <em>Operational Activity</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.OperationalActivityImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOperationalActivity()
   * @generated
   */
	int OPERATIONAL_ACTIVITY = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_EXTENSIONS = FaPackage.ABSTRACT_FUNCTION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__ID = FaPackage.ABSTRACT_FUNCTION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__SID = FaPackage.ABSTRACT_FUNCTION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__CONSTRAINTS = FaPackage.ABSTRACT_FUNCTION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_CONSTRAINTS = FaPackage.ABSTRACT_FUNCTION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_MIGRATED_ELEMENTS = FaPackage.ABSTRACT_FUNCTION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__NAME = FaPackage.ABSTRACT_FUNCTION__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__INCOMING_TRACES = FaPackage.ABSTRACT_FUNCTION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OUTGOING_TRACES = FaPackage.ABSTRACT_FUNCTION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__VISIBLE_IN_DOC = FaPackage.ABSTRACT_FUNCTION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__VISIBLE_IN_LM = FaPackage.ABSTRACT_FUNCTION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__SUMMARY = FaPackage.ABSTRACT_FUNCTION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__DESCRIPTION = FaPackage.ABSTRACT_FUNCTION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__REVIEW = FaPackage.ABSTRACT_FUNCTION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_PROPERTY_VALUES = FaPackage.ABSTRACT_FUNCTION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_ENUMERATION_PROPERTY_TYPES = FaPackage.ABSTRACT_FUNCTION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__APPLIED_PROPERTY_VALUES = FaPackage.ABSTRACT_FUNCTION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_PROPERTY_VALUE_GROUPS = FaPackage.ABSTRACT_FUNCTION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__APPLIED_PROPERTY_VALUE_GROUPS = FaPackage.ABSTRACT_FUNCTION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__STATUS = FaPackage.ABSTRACT_FUNCTION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__FEATURES = FaPackage.ABSTRACT_FUNCTION__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_TRACES = FaPackage.ABSTRACT_FUNCTION__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__CONTAINED_GENERIC_TRACES = FaPackage.ABSTRACT_FUNCTION__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__NAMING_RULES = FaPackage.ABSTRACT_FUNCTION__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__INVOLVING_INVOLVEMENTS = FaPackage.ABSTRACT_FUNCTION__INVOLVING_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__IS_ABSTRACT = FaPackage.ABSTRACT_FUNCTION__IS_ABSTRACT;

	/**
   * The feature id for the '<em><b>Is Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__IS_STATIC = FaPackage.ABSTRACT_FUNCTION__IS_STATIC;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__VISIBILITY = FaPackage.ABSTRACT_FUNCTION__VISIBILITY;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__ABSTRACT_TYPE = FaPackage.ABSTRACT_FUNCTION__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__TYPE = FaPackage.ABSTRACT_FUNCTION__TYPE;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__ORDERED = FaPackage.ABSTRACT_FUNCTION__ORDERED;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__UNIQUE = FaPackage.ABSTRACT_FUNCTION__UNIQUE;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__MIN_INCLUSIVE = FaPackage.ABSTRACT_FUNCTION__MIN_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__MAX_INCLUSIVE = FaPackage.ABSTRACT_FUNCTION__MAX_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_DEFAULT_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_DEFAULT_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_MIN_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_MIN_VALUE;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_MAX_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_MAX_VALUE;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_NULL_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_NULL_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_MIN_CARD = FaPackage.ABSTRACT_FUNCTION__OWNED_MIN_CARD;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_MIN_LENGTH = FaPackage.ABSTRACT_FUNCTION__OWNED_MIN_LENGTH;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_MAX_CARD = FaPackage.ABSTRACT_FUNCTION__OWNED_MAX_CARD;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_MAX_LENGTH = FaPackage.ABSTRACT_FUNCTION__OWNED_MAX_LENGTH;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__FINAL = FaPackage.ABSTRACT_FUNCTION__FINAL;

	/**
   * The feature id for the '<em><b>Aggregation Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__AGGREGATION_KIND = FaPackage.ABSTRACT_FUNCTION__AGGREGATION_KIND;

	/**
   * The feature id for the '<em><b>Is Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__IS_DERIVED = FaPackage.ABSTRACT_FUNCTION__IS_DERIVED;

	/**
   * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__IS_READ_ONLY = FaPackage.ABSTRACT_FUNCTION__IS_READ_ONLY;

	/**
   * The feature id for the '<em><b>Is Part Of Key</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__IS_PART_OF_KEY = FaPackage.ABSTRACT_FUNCTION__IS_PART_OF_KEY;

	/**
   * The feature id for the '<em><b>Association</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__ASSOCIATION = FaPackage.ABSTRACT_FUNCTION__ASSOCIATION;

	/**
   * The feature id for the '<em><b>Representing Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__REPRESENTING_INSTANCE_ROLES = FaPackage.ABSTRACT_FUNCTION__REPRESENTING_INSTANCE_ROLES;

	/**
   * The feature id for the '<em><b>Owned Functional Chains</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_FUNCTIONAL_CHAINS = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_CHAINS;

	/**
   * The feature id for the '<em><b>In Activity Partition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__IN_ACTIVITY_PARTITION = FaPackage.ABSTRACT_FUNCTION__IN_ACTIVITY_PARTITION;

	/**
   * The feature id for the '<em><b>In Interruptible Region</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__IN_INTERRUPTIBLE_REGION = FaPackage.ABSTRACT_FUNCTION__IN_INTERRUPTIBLE_REGION;

	/**
   * The feature id for the '<em><b>In Structured Node</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__IN_STRUCTURED_NODE = FaPackage.ABSTRACT_FUNCTION__IN_STRUCTURED_NODE;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OUTGOING = FaPackage.ABSTRACT_FUNCTION__OUTGOING;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__INCOMING = FaPackage.ABSTRACT_FUNCTION__INCOMING;

	/**
   * The feature id for the '<em><b>Owned Handlers</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_HANDLERS = FaPackage.ABSTRACT_FUNCTION__OWNED_HANDLERS;

	/**
   * The feature id for the '<em><b>Local Precondition</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__LOCAL_PRECONDITION = FaPackage.ABSTRACT_FUNCTION__LOCAL_PRECONDITION;

	/**
   * The feature id for the '<em><b>Local Postcondition</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__LOCAL_POSTCONDITION = FaPackage.ABSTRACT_FUNCTION__LOCAL_POSTCONDITION;

	/**
   * The feature id for the '<em><b>Context</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__CONTEXT = FaPackage.ABSTRACT_FUNCTION__CONTEXT;

	/**
   * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__INPUTS = FaPackage.ABSTRACT_FUNCTION__INPUTS;

	/**
   * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OUTPUTS = FaPackage.ABSTRACT_FUNCTION__OUTPUTS;

	/**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__ARGUMENTS = FaPackage.ABSTRACT_FUNCTION__ARGUMENTS;

	/**
   * The feature id for the '<em><b>Results</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__RESULTS = FaPackage.ABSTRACT_FUNCTION__RESULTS;

	/**
   * The feature id for the '<em><b>Behavior</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__BEHAVIOR = FaPackage.ABSTRACT_FUNCTION__BEHAVIOR;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__ABSTRACT_TYPED_ELEMENTS = FaPackage.ABSTRACT_FUNCTION__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__KIND = FaPackage.ABSTRACT_FUNCTION__KIND;

	/**
   * The feature id for the '<em><b>Condition</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__CONDITION = FaPackage.ABSTRACT_FUNCTION__CONDITION;

	/**
   * The feature id for the '<em><b>Owned Functions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTIONS;

	/**
   * The feature id for the '<em><b>Owned Function Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_FUNCTION_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Functional Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_FUNCTIONAL_EXCHANGES = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES;

	/**
   * The feature id for the '<em><b>Sub Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__SUB_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION__SUB_FUNCTIONS;

	/**
   * The feature id for the '<em><b>Out Function Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OUT_FUNCTION_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__OUT_FUNCTION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>In Function Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__IN_FUNCTION_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__IN_FUNCTION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Component Functional Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__COMPONENT_FUNCTIONAL_ALLOCATIONS = FaPackage.ABSTRACT_FUNCTION__COMPONENT_FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocation Blocks</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__ALLOCATION_BLOCKS = FaPackage.ABSTRACT_FUNCTION__ALLOCATION_BLOCKS;

	/**
   * The feature id for the '<em><b>Available In States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__AVAILABLE_IN_STATES = FaPackage.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES;

	/**
   * The feature id for the '<em><b>Involving Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__INVOLVING_CAPABILITIES = FaPackage.ABSTRACT_FUNCTION__INVOLVING_CAPABILITIES;

	/**
   * The feature id for the '<em><b>Involving Capability Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__INVOLVING_CAPABILITY_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__INVOLVING_CAPABILITY_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Involving Functional Chains</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__INVOLVING_FUNCTIONAL_CHAINS = FaPackage.ABSTRACT_FUNCTION__INVOLVING_FUNCTIONAL_CHAINS;

	/**
   * The feature id for the '<em><b>Linked State Machine</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__LINKED_STATE_MACHINE = FaPackage.ABSTRACT_FUNCTION__LINKED_STATE_MACHINE;

	/**
   * The feature id for the '<em><b>Linked Function Specification</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__LINKED_FUNCTION_SPECIFICATION = FaPackage.ABSTRACT_FUNCTION__LINKED_FUNCTION_SPECIFICATION;

	/**
   * The feature id for the '<em><b>Owned Operational Activity Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_OPERATIONAL_ACTIVITY_PKGS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Activity Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__ACTIVITY_ALLOCATIONS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Swimlanes</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_SWIMLANES = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Process</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__OWNED_PROCESS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Allocator Entities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__ALLOCATOR_ENTITIES = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Realizing System Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__REALIZING_SYSTEM_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Allocating Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__ALLOCATING_ROLES = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Contained Operational Activities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__CONTAINED_OPERATIONAL_ACTIVITIES = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Children Operational Activities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY__CHILDREN_OPERATIONAL_ACTIVITIES = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 8;

	/**
   * The number of structural features of the '<em>Operational Activity</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ACTIVITY_FEATURE_COUNT = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 9;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.OperationalProcessImpl <em>Operational Process</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.OperationalProcessImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOperationalProcess()
   * @generated
   */
	int OPERATIONAL_PROCESS = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__OWNED_EXTENSIONS = FaPackage.FUNCTIONAL_CHAIN__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__ID = FaPackage.FUNCTIONAL_CHAIN__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__SID = FaPackage.FUNCTIONAL_CHAIN__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__CONSTRAINTS = FaPackage.FUNCTIONAL_CHAIN__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__OWNED_CONSTRAINTS = FaPackage.FUNCTIONAL_CHAIN__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__OWNED_MIGRATED_ELEMENTS = FaPackage.FUNCTIONAL_CHAIN__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__NAME = FaPackage.FUNCTIONAL_CHAIN__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__INCOMING_TRACES = FaPackage.FUNCTIONAL_CHAIN__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__OUTGOING_TRACES = FaPackage.FUNCTIONAL_CHAIN__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__VISIBLE_IN_DOC = FaPackage.FUNCTIONAL_CHAIN__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__VISIBLE_IN_LM = FaPackage.FUNCTIONAL_CHAIN__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__SUMMARY = FaPackage.FUNCTIONAL_CHAIN__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__DESCRIPTION = FaPackage.FUNCTIONAL_CHAIN__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__REVIEW = FaPackage.FUNCTIONAL_CHAIN__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__OWNED_PROPERTY_VALUES = FaPackage.FUNCTIONAL_CHAIN__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__OWNED_ENUMERATION_PROPERTY_TYPES = FaPackage.FUNCTIONAL_CHAIN__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__APPLIED_PROPERTY_VALUES = FaPackage.FUNCTIONAL_CHAIN__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__OWNED_PROPERTY_VALUE_GROUPS = FaPackage.FUNCTIONAL_CHAIN__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__APPLIED_PROPERTY_VALUE_GROUPS = FaPackage.FUNCTIONAL_CHAIN__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__STATUS = FaPackage.FUNCTIONAL_CHAIN__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__FEATURES = FaPackage.FUNCTIONAL_CHAIN__FEATURES;

	/**
   * The feature id for the '<em><b>Involved Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__INVOLVED_INVOLVEMENTS = FaPackage.FUNCTIONAL_CHAIN__INVOLVED_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__INVOLVING_INVOLVEMENTS = FaPackage.FUNCTIONAL_CHAIN__INVOLVING_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__KIND = FaPackage.FUNCTIONAL_CHAIN__KIND;

	/**
   * The feature id for the '<em><b>Owned Functional Chain Involvements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS = FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Owned Functional Chain Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS = FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Involved Functional Chain Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__INVOLVED_FUNCTIONAL_CHAIN_INVOLVEMENTS = FaPackage.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_CHAIN_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Involved Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__INVOLVED_FUNCTIONS = FaPackage.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONS;

	/**
   * The feature id for the '<em><b>Involved Functional Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__INVOLVED_FUNCTIONAL_EXCHANGES = FaPackage.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_EXCHANGES;

	/**
   * The feature id for the '<em><b>Involved Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__INVOLVED_ELEMENTS = FaPackage.FUNCTIONAL_CHAIN__INVOLVED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Enacted Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__ENACTED_FUNCTIONS = FaPackage.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONS;

	/**
   * The feature id for the '<em><b>Enacted Functional Blocks</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__ENACTED_FUNCTIONAL_BLOCKS = FaPackage.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONAL_BLOCKS;

	/**
   * The feature id for the '<em><b>Available In States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__AVAILABLE_IN_STATES = FaPackage.FUNCTIONAL_CHAIN__AVAILABLE_IN_STATES;

	/**
   * The feature id for the '<em><b>First Functional Chain Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__FIRST_FUNCTIONAL_CHAIN_INVOLVEMENTS = FaPackage.FUNCTIONAL_CHAIN__FIRST_FUNCTIONAL_CHAIN_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Involving Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__INVOLVING_CAPABILITIES = FaPackage.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITIES;

	/**
   * The feature id for the '<em><b>Involving Capability Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__INVOLVING_CAPABILITY_REALIZATIONS = FaPackage.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITY_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Functional Chains</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__REALIZED_FUNCTIONAL_CHAINS = FaPackage.FUNCTIONAL_CHAIN__REALIZED_FUNCTIONAL_CHAINS;

	/**
   * The feature id for the '<em><b>Realizing Functional Chains</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__REALIZING_FUNCTIONAL_CHAINS = FaPackage.FUNCTIONAL_CHAIN__REALIZING_FUNCTIONAL_CHAINS;

	/**
   * The feature id for the '<em><b>Pre Condition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__PRE_CONDITION = FaPackage.FUNCTIONAL_CHAIN__PRE_CONDITION;

	/**
   * The feature id for the '<em><b>Post Condition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__POST_CONDITION = FaPackage.FUNCTIONAL_CHAIN__POST_CONDITION;

	/**
   * The feature id for the '<em><b>Owned Sequence Nodes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__OWNED_SEQUENCE_NODES = FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_NODES;

	/**
   * The feature id for the '<em><b>Owned Sequence Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__OWNED_SEQUENCE_LINKS = FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_LINKS;

	/**
   * The feature id for the '<em><b>Involving Operational Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS__INVOLVING_OPERATIONAL_CAPABILITIES = FaPackage.FUNCTIONAL_CHAIN_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Operational Process</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_PROCESS_FEATURE_COUNT = FaPackage.FUNCTIONAL_CHAIN_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.SwimlaneImpl <em>Swimlane</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.SwimlaneImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getSwimlane()
   * @generated
   */
	int SWIMLANE = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Super Group</b></em>' container reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__SUPER_GROUP = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Sub Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__SUB_GROUPS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Nodes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__OWNED_NODES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Edges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__OWNED_EDGES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Is Dimension</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__IS_DIMENSION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Is External</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__IS_EXTERNAL = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Represented Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__REPRESENTED_ELEMENT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Super Partition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__SUPER_PARTITION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Sub Partitions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__SUB_PARTITIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Represented Entity</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE__REPRESENTED_ENTITY = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The number of structural features of the '<em>Swimlane</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SWIMLANE_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.OperationalCapabilityPkgImpl <em>Operational Capability Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.OperationalCapabilityPkgImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOperationalCapabilityPkg()
   * @generated
   */
	int OPERATIONAL_CAPABILITY_PKG = 6;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__OWNED_EXTENSIONS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__ID = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__SID = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__CONSTRAINTS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__OWNED_CONSTRAINTS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__OWNED_MIGRATED_ELEMENTS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__NAME = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__INCOMING_TRACES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__OUTGOING_TRACES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__VISIBLE_IN_DOC = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__VISIBLE_IN_LM = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__SUMMARY = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__DESCRIPTION = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__REVIEW = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__OWNED_PROPERTY_VALUES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__APPLIED_PROPERTY_VALUES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__STATUS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__FEATURES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__OWNED_TRACES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__CONTAINED_GENERIC_TRACES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__NAMING_RULES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Operational Capabilities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__OWNED_OPERATIONAL_CAPABILITIES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Operational Capability Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__OWNED_OPERATIONAL_CAPABILITY_PKGS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Capability Configurations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__OWNED_CAPABILITY_CONFIGURATIONS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Concept Compliances</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG__OWNED_CONCEPT_COMPLIANCES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG_FEATURE_COUNT + 3;

	/**
   * The number of structural features of the '<em>Operational Capability Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_PKG_FEATURE_COUNT = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG_FEATURE_COUNT + 4;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.OperationalCapabilityImpl <em>Operational Capability</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.OperationalCapabilityImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOperationalCapability()
   * @generated
   */
	int OPERATIONAL_CAPABILITY = 7;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OWNED_EXTENSIONS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__ID = InteractionPackage.ABSTRACT_CAPABILITY__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__SID = InteractionPackage.ABSTRACT_CAPABILITY__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__CONSTRAINTS = InteractionPackage.ABSTRACT_CAPABILITY__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OWNED_CONSTRAINTS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OWNED_MIGRATED_ELEMENTS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__NAME = InteractionPackage.ABSTRACT_CAPABILITY__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__INCOMING_TRACES = InteractionPackage.ABSTRACT_CAPABILITY__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OUTGOING_TRACES = InteractionPackage.ABSTRACT_CAPABILITY__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__VISIBLE_IN_DOC = InteractionPackage.ABSTRACT_CAPABILITY__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__VISIBLE_IN_LM = InteractionPackage.ABSTRACT_CAPABILITY__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__SUMMARY = InteractionPackage.ABSTRACT_CAPABILITY__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__DESCRIPTION = InteractionPackage.ABSTRACT_CAPABILITY__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__REVIEW = InteractionPackage.ABSTRACT_CAPABILITY__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OWNED_PROPERTY_VALUES = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OWNED_ENUMERATION_PROPERTY_TYPES = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__APPLIED_PROPERTY_VALUES = InteractionPackage.ABSTRACT_CAPABILITY__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OWNED_PROPERTY_VALUE_GROUPS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__APPLIED_PROPERTY_VALUE_GROUPS = InteractionPackage.ABSTRACT_CAPABILITY__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__STATUS = InteractionPackage.ABSTRACT_CAPABILITY__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__FEATURES = InteractionPackage.ABSTRACT_CAPABILITY__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OWNED_TRACES = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__CONTAINED_GENERIC_TRACES = InteractionPackage.ABSTRACT_CAPABILITY__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__NAMING_RULES = InteractionPackage.ABSTRACT_CAPABILITY__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OWNED_PROPERTY_VALUE_PKGS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Involved Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__INVOLVED_INVOLVEMENTS = InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Owned Functional Chains</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OWNED_FUNCTIONAL_CHAINS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAINS;

	/**
   * The feature id for the '<em><b>Pre Condition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__PRE_CONDITION = InteractionPackage.ABSTRACT_CAPABILITY__PRE_CONDITION;

	/**
   * The feature id for the '<em><b>Post Condition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__POST_CONDITION = InteractionPackage.ABSTRACT_CAPABILITY__POST_CONDITION;

	/**
   * The feature id for the '<em><b>Owned Scenarios</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OWNED_SCENARIOS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_SCENARIOS;

	/**
   * The feature id for the '<em><b>Incoming Capability Allocation</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__INCOMING_CAPABILITY_ALLOCATION = InteractionPackage.ABSTRACT_CAPABILITY__INCOMING_CAPABILITY_ALLOCATION;

	/**
   * The feature id for the '<em><b>Outgoing Capability Allocation</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OUTGOING_CAPABILITY_ALLOCATION = InteractionPackage.ABSTRACT_CAPABILITY__OUTGOING_CAPABILITY_ALLOCATION;

	/**
   * The feature id for the '<em><b>Extends</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__EXTENDS = InteractionPackage.ABSTRACT_CAPABILITY__EXTENDS;

	/**
   * The feature id for the '<em><b>Extending</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__EXTENDING = InteractionPackage.ABSTRACT_CAPABILITY__EXTENDING;

	/**
   * The feature id for the '<em><b>Abstract Capability Extension Points</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__ABSTRACT_CAPABILITY_EXTENSION_POINTS = InteractionPackage.ABSTRACT_CAPABILITY__ABSTRACT_CAPABILITY_EXTENSION_POINTS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__SUPER_GENERALIZATIONS = InteractionPackage.ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__SUB_GENERALIZATIONS = InteractionPackage.ABSTRACT_CAPABILITY__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Includes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__INCLUDES = InteractionPackage.ABSTRACT_CAPABILITY__INCLUDES;

	/**
   * The feature id for the '<em><b>Including</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__INCLUDING = InteractionPackage.ABSTRACT_CAPABILITY__INCLUDING;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__SUPER = InteractionPackage.ABSTRACT_CAPABILITY__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__SUB = InteractionPackage.ABSTRACT_CAPABILITY__SUB;

	/**
   * The feature id for the '<em><b>Included Abstract Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__INCLUDED_ABSTRACT_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY__INCLUDED_ABSTRACT_CAPABILITIES;

	/**
   * The feature id for the '<em><b>Including Abstract Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__INCLUDING_ABSTRACT_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY__INCLUDING_ABSTRACT_CAPABILITIES;

	/**
   * The feature id for the '<em><b>Extended Abstract Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES;

	/**
   * The feature id for the '<em><b>Extending Abstract Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__EXTENDING_ABSTRACT_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY__EXTENDING_ABSTRACT_CAPABILITIES;

	/**
   * The feature id for the '<em><b>Owned Functional Chain Abstract Capability Involvements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OWNED_FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENTS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Owned Abstract Function Abstract Capability Involvements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OWNED_ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENTS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Available In States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__AVAILABLE_IN_STATES = InteractionPackage.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Involved Abstract Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__INVOLVED_ABSTRACT_FUNCTIONS = InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_ABSTRACT_FUNCTIONS;

	/**
   * The feature id for the '<em><b>Involved Functional Chains</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__INVOLVED_FUNCTIONAL_CHAINS = InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_FUNCTIONAL_CHAINS;

	/**
   * The feature id for the '<em><b>Compliances</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__COMPLIANCES = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Configurations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__CONFIGURATIONS = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Entity Operational Capability Involvements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__OWNED_ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENTS = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Realizing Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__REALIZING_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Involved Entities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY__INVOLVED_ENTITIES = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 4;

	/**
   * The number of structural features of the '<em>Operational Capability</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_CAPABILITY_FEATURE_COUNT = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 5;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.ActivityAllocationImpl <em>Activity Allocation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.ActivityAllocationImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getActivityAllocation()
   * @generated
   */
	int ACTIVITY_ALLOCATION = 8;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Role</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__ROLE = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Activity</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION__ACTIVITY = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Activity Allocation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ACTIVITY_ALLOCATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.RolePkgImpl <em>Role Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.RolePkgImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getRolePkg()
   * @generated
   */
	int ROLE_PKG = 9;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__ID = CapellacorePackage.STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__SID = CapellacorePackage.STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Role Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__OWNED_ROLE_PKGS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Roles</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG__OWNED_ROLES = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Role Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_PKG_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.RoleImpl <em>Role</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.RoleImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getRole()
   * @generated
   */
	int ROLE = 10;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_EXTENSIONS = InformationPackage.ABSTRACT_INSTANCE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__ID = InformationPackage.ABSTRACT_INSTANCE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__SID = InformationPackage.ABSTRACT_INSTANCE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__CONSTRAINTS = InformationPackage.ABSTRACT_INSTANCE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_CONSTRAINTS = InformationPackage.ABSTRACT_INSTANCE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_MIGRATED_ELEMENTS = InformationPackage.ABSTRACT_INSTANCE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__NAME = InformationPackage.ABSTRACT_INSTANCE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__INCOMING_TRACES = InformationPackage.ABSTRACT_INSTANCE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OUTGOING_TRACES = InformationPackage.ABSTRACT_INSTANCE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__VISIBLE_IN_DOC = InformationPackage.ABSTRACT_INSTANCE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__VISIBLE_IN_LM = InformationPackage.ABSTRACT_INSTANCE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__SUMMARY = InformationPackage.ABSTRACT_INSTANCE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__DESCRIPTION = InformationPackage.ABSTRACT_INSTANCE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__REVIEW = InformationPackage.ABSTRACT_INSTANCE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_PROPERTY_VALUES = InformationPackage.ABSTRACT_INSTANCE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_ENUMERATION_PROPERTY_TYPES = InformationPackage.ABSTRACT_INSTANCE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__APPLIED_PROPERTY_VALUES = InformationPackage.ABSTRACT_INSTANCE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_PROPERTY_VALUE_GROUPS = InformationPackage.ABSTRACT_INSTANCE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__APPLIED_PROPERTY_VALUE_GROUPS = InformationPackage.ABSTRACT_INSTANCE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__STATUS = InformationPackage.ABSTRACT_INSTANCE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__FEATURES = InformationPackage.ABSTRACT_INSTANCE__FEATURES;

	/**
   * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__IS_ABSTRACT = InformationPackage.ABSTRACT_INSTANCE__IS_ABSTRACT;

	/**
   * The feature id for the '<em><b>Is Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__IS_STATIC = InformationPackage.ABSTRACT_INSTANCE__IS_STATIC;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__VISIBILITY = InformationPackage.ABSTRACT_INSTANCE__VISIBILITY;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__ABSTRACT_TYPE = InformationPackage.ABSTRACT_INSTANCE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__TYPE = InformationPackage.ABSTRACT_INSTANCE__TYPE;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__ORDERED = InformationPackage.ABSTRACT_INSTANCE__ORDERED;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__UNIQUE = InformationPackage.ABSTRACT_INSTANCE__UNIQUE;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__MIN_INCLUSIVE = InformationPackage.ABSTRACT_INSTANCE__MIN_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__MAX_INCLUSIVE = InformationPackage.ABSTRACT_INSTANCE__MAX_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_DEFAULT_VALUE = InformationPackage.ABSTRACT_INSTANCE__OWNED_DEFAULT_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_MIN_VALUE = InformationPackage.ABSTRACT_INSTANCE__OWNED_MIN_VALUE;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_MAX_VALUE = InformationPackage.ABSTRACT_INSTANCE__OWNED_MAX_VALUE;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_NULL_VALUE = InformationPackage.ABSTRACT_INSTANCE__OWNED_NULL_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_MIN_CARD = InformationPackage.ABSTRACT_INSTANCE__OWNED_MIN_CARD;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_MIN_LENGTH = InformationPackage.ABSTRACT_INSTANCE__OWNED_MIN_LENGTH;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_MAX_CARD = InformationPackage.ABSTRACT_INSTANCE__OWNED_MAX_CARD;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_MAX_LENGTH = InformationPackage.ABSTRACT_INSTANCE__OWNED_MAX_LENGTH;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__FINAL = InformationPackage.ABSTRACT_INSTANCE__FINAL;

	/**
   * The feature id for the '<em><b>Aggregation Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__AGGREGATION_KIND = InformationPackage.ABSTRACT_INSTANCE__AGGREGATION_KIND;

	/**
   * The feature id for the '<em><b>Is Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__IS_DERIVED = InformationPackage.ABSTRACT_INSTANCE__IS_DERIVED;

	/**
   * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__IS_READ_ONLY = InformationPackage.ABSTRACT_INSTANCE__IS_READ_ONLY;

	/**
   * The feature id for the '<em><b>Is Part Of Key</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__IS_PART_OF_KEY = InformationPackage.ABSTRACT_INSTANCE__IS_PART_OF_KEY;

	/**
   * The feature id for the '<em><b>Association</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__ASSOCIATION = InformationPackage.ABSTRACT_INSTANCE__ASSOCIATION;

	/**
   * The feature id for the '<em><b>Representing Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__REPRESENTING_INSTANCE_ROLES = InformationPackage.ABSTRACT_INSTANCE__REPRESENTING_INSTANCE_ROLES;

	/**
   * The feature id for the '<em><b>Owned Role Assembly Usages</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_ROLE_ASSEMBLY_USAGES = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Activity Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__OWNED_ACTIVITY_ALLOCATIONS = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Role Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__ROLE_ALLOCATIONS = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Activity Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__ACTIVITY_ALLOCATIONS = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Allocating Entities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__ALLOCATING_ENTITIES = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Allocated Operational Activities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE__ALLOCATED_OPERATIONAL_ACTIVITIES = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 5;

	/**
   * The number of structural features of the '<em>Role</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_FEATURE_COUNT = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 6;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.RoleAssemblyUsageImpl <em>Role Assembly Usage</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.RoleAssemblyUsageImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getRoleAssemblyUsage()
   * @generated
   */
	int ROLE_ASSEMBLY_USAGE = 11;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Child</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE__CHILD = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Role Assembly Usage</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ASSEMBLY_USAGE_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.RoleAllocationImpl <em>Role Allocation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.RoleAllocationImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getRoleAllocation()
   * @generated
   */
	int ROLE_ALLOCATION = 12;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Role</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__ROLE = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Entity</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION__ENTITY = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Role Allocation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ROLE_ALLOCATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.EntityPkgImpl <em>Entity Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.EntityPkgImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getEntityPkg()
   * @generated
   */
	int ENTITY_PKG = 13;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_EXTENSIONS = CsPackage.COMPONENT_PKG__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__ID = CsPackage.COMPONENT_PKG__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__SID = CsPackage.COMPONENT_PKG__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__CONSTRAINTS = CsPackage.COMPONENT_PKG__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_CONSTRAINTS = CsPackage.COMPONENT_PKG__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_MIGRATED_ELEMENTS = CsPackage.COMPONENT_PKG__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__NAME = CsPackage.COMPONENT_PKG__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__INCOMING_TRACES = CsPackage.COMPONENT_PKG__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OUTGOING_TRACES = CsPackage.COMPONENT_PKG__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__VISIBLE_IN_DOC = CsPackage.COMPONENT_PKG__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__VISIBLE_IN_LM = CsPackage.COMPONENT_PKG__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__SUMMARY = CsPackage.COMPONENT_PKG__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__DESCRIPTION = CsPackage.COMPONENT_PKG__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__REVIEW = CsPackage.COMPONENT_PKG__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_PKG__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__STATUS = CsPackage.COMPONENT_PKG__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__FEATURES = CsPackage.COMPONENT_PKG__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_TRACES = CsPackage.COMPONENT_PKG__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__CONTAINED_GENERIC_TRACES = CsPackage.COMPONENT_PKG__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__NAMING_RULES = CsPackage.COMPONENT_PKG__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_PROPERTY_VALUE_PKGS = CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Parts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_PARTS = CsPackage.COMPONENT_PKG__OWNED_PARTS;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_COMPONENT_EXCHANGES = CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_FUNCTIONAL_LINKS = CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_FUNCTIONAL_ALLOCATIONS = CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_PHYSICAL_LINKS = CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_PHYSICAL_LINK_CATEGORIES = CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_STATE_MACHINES = CsPackage.COMPONENT_PKG__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Owned Entities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_ENTITIES = CsPackage.COMPONENT_PKG_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Entity Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_ENTITY_PKGS = CsPackage.COMPONENT_PKG_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Locations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_LOCATIONS = CsPackage.COMPONENT_PKG_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Communication Means</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG__OWNED_COMMUNICATION_MEANS = CsPackage.COMPONENT_PKG_FEATURE_COUNT + 3;

	/**
   * The number of structural features of the '<em>Entity Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_PKG_FEATURE_COUNT = CsPackage.COMPONENT_PKG_FEATURE_COUNT + 4;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.AbstractConceptItemImpl <em>Abstract Concept Item</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.AbstractConceptItemImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getAbstractConceptItem()
   * @generated
   */
	int ABSTRACT_CONCEPT_ITEM = 19;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_EXTENSIONS = CsPackage.COMPONENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__ID = CsPackage.COMPONENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__SID = CsPackage.COMPONENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__CONSTRAINTS = CsPackage.COMPONENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_CONSTRAINTS = CsPackage.COMPONENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_MIGRATED_ELEMENTS = CsPackage.COMPONENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__NAME = CsPackage.COMPONENT__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__ABSTRACT_TYPED_ELEMENTS = CsPackage.COMPONENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__INCOMING_TRACES = CsPackage.COMPONENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OUTGOING_TRACES = CsPackage.COMPONENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__VISIBLE_IN_DOC = CsPackage.COMPONENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__VISIBLE_IN_LM = CsPackage.COMPONENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__SUMMARY = CsPackage.COMPONENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__DESCRIPTION = CsPackage.COMPONENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__REVIEW = CsPackage.COMPONENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__STATUS = CsPackage.COMPONENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__FEATURES = CsPackage.COMPONENT__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_TRACES = CsPackage.COMPONENT__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__CONTAINED_GENERIC_TRACES = CsPackage.COMPONENT__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__NAMING_RULES = CsPackage.COMPONENT__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__TYPED_ELEMENTS = CsPackage.COMPONENT__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_FUNCTIONAL_ALLOCATION = CsPackage.COMPONENT__OWNED_FUNCTIONAL_ALLOCATION;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_COMPONENT_EXCHANGES = CsPackage.COMPONENT__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__FUNCTIONAL_ALLOCATIONS = CsPackage.COMPONENT__FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__ALLOCATED_FUNCTIONS = CsPackage.COMPONENT__ALLOCATED_FUNCTIONS;

	/**
   * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__IN_EXCHANGE_LINKS = CsPackage.COMPONENT__IN_EXCHANGE_LINKS;

	/**
   * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OUT_EXCHANGE_LINKS = CsPackage.COMPONENT__OUT_EXCHANGE_LINKS;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_ABSTRACT_CAPABILITY_PKG = CsPackage.COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
   * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_PKG = CsPackage.COMPONENT__OWNED_INTERFACE_PKG;

	/**
   * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_DATA_PKG = CsPackage.COMPONENT__OWNED_DATA_PKG;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_STATE_MACHINES = CsPackage.COMPONENT__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__ABSTRACT = CsPackage.COMPONENT__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_GENERALIZATIONS = CsPackage.COMPONENT__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__SUPER_GENERALIZATIONS = CsPackage.COMPONENT__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__SUB_GENERALIZATIONS = CsPackage.COMPONENT__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__SUPER = CsPackage.COMPONENT__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__SUB = CsPackage.COMPONENT__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_FEATURES = CsPackage.COMPONENT__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__CONTAINED_PROPERTIES = CsPackage.COMPONENT__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_ALLOCATIONS = CsPackage.COMPONENT__OWNED_INTERFACE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__PROVISIONED_INTERFACE_ALLOCATIONS = CsPackage.COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__ALLOCATED_INTERFACES = CsPackage.COMPONENT__ALLOCATED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_COMMUNICATION_LINKS = CsPackage.COMPONENT__OWNED_COMMUNICATION_LINKS;

	/**
   * The feature id for the '<em><b>Produce</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__PRODUCE = CsPackage.COMPONENT__PRODUCE;

	/**
   * The feature id for the '<em><b>Consume</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__CONSUME = CsPackage.COMPONENT__CONSUME;

	/**
   * The feature id for the '<em><b>Send</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__SEND = CsPackage.COMPONENT__SEND;

	/**
   * The feature id for the '<em><b>Receive</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__RECEIVE = CsPackage.COMPONENT__RECEIVE;

	/**
   * The feature id for the '<em><b>Call</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__CALL = CsPackage.COMPONENT__CALL;

	/**
   * The feature id for the '<em><b>Execute</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__EXECUTE = CsPackage.COMPONENT__EXECUTE;

	/**
   * The feature id for the '<em><b>Write</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__WRITE = CsPackage.COMPONENT__WRITE;

	/**
   * The feature id for the '<em><b>Access</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__ACCESS = CsPackage.COMPONENT__ACCESS;

	/**
   * The feature id for the '<em><b>Acquire</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__ACQUIRE = CsPackage.COMPONENT__ACQUIRE;

	/**
   * The feature id for the '<em><b>Transmit</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__TRANSMIT = CsPackage.COMPONENT__TRANSMIT;

	/**
   * The feature id for the '<em><b>Actor</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__ACTOR = CsPackage.COMPONENT__ACTOR;

	/**
   * The feature id for the '<em><b>Human</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__HUMAN = CsPackage.COMPONENT__HUMAN;

	/**
   * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_USES = CsPackage.COMPONENT__OWNED_INTERFACE_USES;

	/**
   * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__USED_INTERFACE_LINKS = CsPackage.COMPONENT__USED_INTERFACE_LINKS;

	/**
   * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__USED_INTERFACES = CsPackage.COMPONENT__USED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_IMPLEMENTATIONS = CsPackage.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
   * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__IMPLEMENTED_INTERFACE_LINKS = CsPackage.COMPONENT__IMPLEMENTED_INTERFACE_LINKS;

	/**
   * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__IMPLEMENTED_INTERFACES = CsPackage.COMPONENT__IMPLEMENTED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Component Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_COMPONENT_REALIZATIONS = CsPackage.COMPONENT__OWNED_COMPONENT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__REALIZED_COMPONENTS = CsPackage.COMPONENT__REALIZED_COMPONENTS;

	/**
   * The feature id for the '<em><b>Realizing Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__REALIZING_COMPONENTS = CsPackage.COMPONENT__REALIZING_COMPONENTS;

	/**
   * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__PROVIDED_INTERFACES = CsPackage.COMPONENT__PROVIDED_INTERFACES;

	/**
   * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__REQUIRED_INTERFACES = CsPackage.COMPONENT__REQUIRED_INTERFACES;

	/**
   * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__CONTAINED_COMPONENT_PORTS = CsPackage.COMPONENT__CONTAINED_COMPONENT_PORTS;

	/**
   * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__CONTAINED_PARTS = CsPackage.COMPONENT__CONTAINED_PARTS;

	/**
   * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__CONTAINED_PHYSICAL_PORTS = CsPackage.COMPONENT__CONTAINED_PHYSICAL_PORTS;

	/**
   * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_PHYSICAL_PATH = CsPackage.COMPONENT__OWNED_PHYSICAL_PATH;

	/**
   * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_PHYSICAL_LINKS = CsPackage.COMPONENT__OWNED_PHYSICAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__OWNED_PHYSICAL_LINK_CATEGORIES = CsPackage.COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
   * The feature id for the '<em><b>Representing Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__REPRESENTING_PARTS = CsPackage.COMPONENT__REPRESENTING_PARTS;

	/**
   * The feature id for the '<em><b>Composing Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM__COMPOSING_LINKS = CsPackage.COMPONENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Concept Item</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT = CsPackage.COMPONENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.EntityImpl <em>Entity</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.EntityImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getEntity()
   * @generated
   */
	int ENTITY = 14;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_EXTENSIONS = ABSTRACT_CONCEPT_ITEM__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__ID = ABSTRACT_CONCEPT_ITEM__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__SID = ABSTRACT_CONCEPT_ITEM__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__CONSTRAINTS = ABSTRACT_CONCEPT_ITEM__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_CONSTRAINTS = ABSTRACT_CONCEPT_ITEM__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_MIGRATED_ELEMENTS = ABSTRACT_CONCEPT_ITEM__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__NAME = ABSTRACT_CONCEPT_ITEM__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__ABSTRACT_TYPED_ELEMENTS = ABSTRACT_CONCEPT_ITEM__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__INCOMING_TRACES = ABSTRACT_CONCEPT_ITEM__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OUTGOING_TRACES = ABSTRACT_CONCEPT_ITEM__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__VISIBLE_IN_DOC = ABSTRACT_CONCEPT_ITEM__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__VISIBLE_IN_LM = ABSTRACT_CONCEPT_ITEM__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__SUMMARY = ABSTRACT_CONCEPT_ITEM__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__DESCRIPTION = ABSTRACT_CONCEPT_ITEM__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__REVIEW = ABSTRACT_CONCEPT_ITEM__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_PROPERTY_VALUES = ABSTRACT_CONCEPT_ITEM__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_CONCEPT_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__APPLIED_PROPERTY_VALUES = ABSTRACT_CONCEPT_ITEM__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_CONCEPT_ITEM__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_CONCEPT_ITEM__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__STATUS = ABSTRACT_CONCEPT_ITEM__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__FEATURES = ABSTRACT_CONCEPT_ITEM__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_TRACES = ABSTRACT_CONCEPT_ITEM__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__CONTAINED_GENERIC_TRACES = ABSTRACT_CONCEPT_ITEM__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__NAMING_RULES = ABSTRACT_CONCEPT_ITEM__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__TYPED_ELEMENTS = ABSTRACT_CONCEPT_ITEM__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_FUNCTIONAL_ALLOCATION = ABSTRACT_CONCEPT_ITEM__OWNED_FUNCTIONAL_ALLOCATION;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_COMPONENT_EXCHANGES = ABSTRACT_CONCEPT_ITEM__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_COMPONENT_EXCHANGE_CATEGORIES = ABSTRACT_CONCEPT_ITEM__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__FUNCTIONAL_ALLOCATIONS = ABSTRACT_CONCEPT_ITEM__FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__ALLOCATED_FUNCTIONS = ABSTRACT_CONCEPT_ITEM__ALLOCATED_FUNCTIONS;

	/**
   * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__IN_EXCHANGE_LINKS = ABSTRACT_CONCEPT_ITEM__IN_EXCHANGE_LINKS;

	/**
   * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OUT_EXCHANGE_LINKS = ABSTRACT_CONCEPT_ITEM__OUT_EXCHANGE_LINKS;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_ABSTRACT_CAPABILITY_PKG = ABSTRACT_CONCEPT_ITEM__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
   * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_INTERFACE_PKG = ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_PKG;

	/**
   * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_DATA_PKG = ABSTRACT_CONCEPT_ITEM__OWNED_DATA_PKG;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_STATE_MACHINES = ABSTRACT_CONCEPT_ITEM__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__ABSTRACT = ABSTRACT_CONCEPT_ITEM__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_GENERALIZATIONS = ABSTRACT_CONCEPT_ITEM__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__SUPER_GENERALIZATIONS = ABSTRACT_CONCEPT_ITEM__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__SUB_GENERALIZATIONS = ABSTRACT_CONCEPT_ITEM__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__SUPER = ABSTRACT_CONCEPT_ITEM__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__SUB = ABSTRACT_CONCEPT_ITEM__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_FEATURES = ABSTRACT_CONCEPT_ITEM__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__CONTAINED_PROPERTIES = ABSTRACT_CONCEPT_ITEM__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_INTERFACE_ALLOCATIONS = ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__PROVISIONED_INTERFACE_ALLOCATIONS = ABSTRACT_CONCEPT_ITEM__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__ALLOCATED_INTERFACES = ABSTRACT_CONCEPT_ITEM__ALLOCATED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_COMMUNICATION_LINKS = ABSTRACT_CONCEPT_ITEM__OWNED_COMMUNICATION_LINKS;

	/**
   * The feature id for the '<em><b>Produce</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__PRODUCE = ABSTRACT_CONCEPT_ITEM__PRODUCE;

	/**
   * The feature id for the '<em><b>Consume</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__CONSUME = ABSTRACT_CONCEPT_ITEM__CONSUME;

	/**
   * The feature id for the '<em><b>Send</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__SEND = ABSTRACT_CONCEPT_ITEM__SEND;

	/**
   * The feature id for the '<em><b>Receive</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__RECEIVE = ABSTRACT_CONCEPT_ITEM__RECEIVE;

	/**
   * The feature id for the '<em><b>Call</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__CALL = ABSTRACT_CONCEPT_ITEM__CALL;

	/**
   * The feature id for the '<em><b>Execute</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__EXECUTE = ABSTRACT_CONCEPT_ITEM__EXECUTE;

	/**
   * The feature id for the '<em><b>Write</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__WRITE = ABSTRACT_CONCEPT_ITEM__WRITE;

	/**
   * The feature id for the '<em><b>Access</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__ACCESS = ABSTRACT_CONCEPT_ITEM__ACCESS;

	/**
   * The feature id for the '<em><b>Acquire</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__ACQUIRE = ABSTRACT_CONCEPT_ITEM__ACQUIRE;

	/**
   * The feature id for the '<em><b>Transmit</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__TRANSMIT = ABSTRACT_CONCEPT_ITEM__TRANSMIT;

	/**
   * The feature id for the '<em><b>Actor</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__ACTOR = ABSTRACT_CONCEPT_ITEM__ACTOR;

	/**
   * The feature id for the '<em><b>Human</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__HUMAN = ABSTRACT_CONCEPT_ITEM__HUMAN;

	/**
   * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_INTERFACE_USES = ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_USES;

	/**
   * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__USED_INTERFACE_LINKS = ABSTRACT_CONCEPT_ITEM__USED_INTERFACE_LINKS;

	/**
   * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__USED_INTERFACES = ABSTRACT_CONCEPT_ITEM__USED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_INTERFACE_IMPLEMENTATIONS = ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
   * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__IMPLEMENTED_INTERFACE_LINKS = ABSTRACT_CONCEPT_ITEM__IMPLEMENTED_INTERFACE_LINKS;

	/**
   * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__IMPLEMENTED_INTERFACES = ABSTRACT_CONCEPT_ITEM__IMPLEMENTED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Component Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_COMPONENT_REALIZATIONS = ABSTRACT_CONCEPT_ITEM__OWNED_COMPONENT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__REALIZED_COMPONENTS = ABSTRACT_CONCEPT_ITEM__REALIZED_COMPONENTS;

	/**
   * The feature id for the '<em><b>Realizing Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__REALIZING_COMPONENTS = ABSTRACT_CONCEPT_ITEM__REALIZING_COMPONENTS;

	/**
   * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__PROVIDED_INTERFACES = ABSTRACT_CONCEPT_ITEM__PROVIDED_INTERFACES;

	/**
   * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__REQUIRED_INTERFACES = ABSTRACT_CONCEPT_ITEM__REQUIRED_INTERFACES;

	/**
   * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__CONTAINED_COMPONENT_PORTS = ABSTRACT_CONCEPT_ITEM__CONTAINED_COMPONENT_PORTS;

	/**
   * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__CONTAINED_PARTS = ABSTRACT_CONCEPT_ITEM__CONTAINED_PARTS;

	/**
   * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__CONTAINED_PHYSICAL_PORTS = ABSTRACT_CONCEPT_ITEM__CONTAINED_PHYSICAL_PORTS;

	/**
   * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_PHYSICAL_PATH = ABSTRACT_CONCEPT_ITEM__OWNED_PHYSICAL_PATH;

	/**
   * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_PHYSICAL_LINKS = ABSTRACT_CONCEPT_ITEM__OWNED_PHYSICAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_PHYSICAL_LINK_CATEGORIES = ABSTRACT_CONCEPT_ITEM__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
   * The feature id for the '<em><b>Representing Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__REPRESENTING_PARTS = ABSTRACT_CONCEPT_ITEM__REPRESENTING_PARTS;

	/**
   * The feature id for the '<em><b>Composing Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__COMPOSING_LINKS = ABSTRACT_CONCEPT_ITEM__COMPOSING_LINKS;

	/**
   * The feature id for the '<em><b>Incoming Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__INCOMING_INFORMATION_FLOWS = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Outgoing Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OUTGOING_INFORMATION_FLOWS = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__INFORMATION_FLOWS = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__INVOLVING_INVOLVEMENTS = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Role Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__ROLE_ALLOCATIONS = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Organisational Unit Memberships</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__ORGANISATIONAL_UNIT_MEMBERSHIPS = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Actual Location</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__ACTUAL_LOCATION = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Sub Entities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__SUB_ENTITIES = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Entities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_ENTITIES = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Owned Communication Means</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_COMMUNICATION_MEANS = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned Role Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__OWNED_ROLE_ALLOCATIONS = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Allocated Operational Activities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__ALLOCATED_OPERATIONAL_ACTIVITIES = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Allocated Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__ALLOCATED_ROLES = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Involving Operational Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__INVOLVING_OPERATIONAL_CAPABILITIES = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Realizing System Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY__REALIZING_SYSTEM_COMPONENTS = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 14;

	/**
   * The number of structural features of the '<em>Entity</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_FEATURE_COUNT = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 15;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.ConceptPkgImpl <em>Concept Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.ConceptPkgImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getConceptPkg()
   * @generated
   */
	int CONCEPT_PKG = 15;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__ID = CapellacorePackage.STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__SID = CapellacorePackage.STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Concept Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__OWNED_CONCEPT_PKGS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Concepts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG__OWNED_CONCEPTS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Concept Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_PKG_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.ConceptImpl <em>Concept</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.ConceptImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getConcept()
   * @generated
   */
	int CONCEPT = 16;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Compliances</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__COMPLIANCES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Composite Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT__COMPOSITE_LINKS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Concept</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.ConceptComplianceImpl <em>Concept Compliance</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.ConceptComplianceImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getConceptCompliance()
   * @generated
   */
	int CONCEPT_COMPLIANCE = 17;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__OWNED_EXTENSIONS = CapellacorePackage.RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__ID = CapellacorePackage.RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__SID = CapellacorePackage.RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__CONSTRAINTS = CapellacorePackage.RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__OWNED_CONSTRAINTS = CapellacorePackage.RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__REALIZED_FLOW = CapellacorePackage.RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__INCOMING_TRACES = CapellacorePackage.RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__OUTGOING_TRACES = CapellacorePackage.RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__VISIBLE_IN_DOC = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__VISIBLE_IN_LM = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__SUMMARY = CapellacorePackage.RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__DESCRIPTION = CapellacorePackage.RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__REVIEW = CapellacorePackage.RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__OWNED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__APPLIED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__STATUS = CapellacorePackage.RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__FEATURES = CapellacorePackage.RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Comply With Concept</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__COMPLY_WITH_CONCEPT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Compliant Capability</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE__COMPLIANT_CAPABILITY = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Concept Compliance</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONCEPT_COMPLIANCE_FEATURE_COUNT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.ItemInConceptImpl <em>Item In Concept</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.ItemInConceptImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getItemInConcept()
   * @generated
   */
	int ITEM_IN_CONCEPT = 18;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Concept</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__CONCEPT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Item</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT__ITEM = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Item In Concept</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_IN_CONCEPT_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.CommunityOfInterestImpl <em>Community Of Interest</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.CommunityOfInterestImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getCommunityOfInterest()
   * @generated
   */
	int COMMUNITY_OF_INTEREST = 20;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Community Of Interest Compositions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST__COMMUNITY_OF_INTEREST_COMPOSITIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Community Of Interest</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.CommunityOfInterestCompositionImpl <em>Community Of Interest Composition</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.CommunityOfInterestCompositionImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getCommunityOfInterestComposition()
   * @generated
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION = 21;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Community Of Interest</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__COMMUNITY_OF_INTEREST = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Interested Organisation Unit</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION__INTERESTED_ORGANISATION_UNIT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Community Of Interest Composition</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNITY_OF_INTEREST_COMPOSITION_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.OrganisationalUnitImpl <em>Organisational Unit</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.OrganisationalUnitImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOrganisationalUnit()
   * @generated
   */
	int ORGANISATIONAL_UNIT = 22;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Organisational Unit Compositions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__ORGANISATIONAL_UNIT_COMPOSITIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Community Of Interest Memberships</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT__COMMUNITY_OF_INTEREST_MEMBERSHIPS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Organisational Unit</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.OrganisationalUnitCompositionImpl <em>Organisational Unit Composition</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.OrganisationalUnitCompositionImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOrganisationalUnitComposition()
   * @generated
   */
	int ORGANISATIONAL_UNIT_COMPOSITION = 23;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Organisational Unit</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__ORGANISATIONAL_UNIT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Participating Entity</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION__PARTICIPATING_ENTITY = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Organisational Unit Composition</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ORGANISATIONAL_UNIT_COMPOSITION_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.LocationImpl <em>Location</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.LocationImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getLocation()
   * @generated
   */
	int LOCATION = 24;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_EXTENSIONS = ABSTRACT_CONCEPT_ITEM__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__ID = ABSTRACT_CONCEPT_ITEM__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__SID = ABSTRACT_CONCEPT_ITEM__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__CONSTRAINTS = ABSTRACT_CONCEPT_ITEM__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_CONSTRAINTS = ABSTRACT_CONCEPT_ITEM__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_MIGRATED_ELEMENTS = ABSTRACT_CONCEPT_ITEM__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__NAME = ABSTRACT_CONCEPT_ITEM__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__ABSTRACT_TYPED_ELEMENTS = ABSTRACT_CONCEPT_ITEM__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__INCOMING_TRACES = ABSTRACT_CONCEPT_ITEM__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OUTGOING_TRACES = ABSTRACT_CONCEPT_ITEM__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__VISIBLE_IN_DOC = ABSTRACT_CONCEPT_ITEM__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__VISIBLE_IN_LM = ABSTRACT_CONCEPT_ITEM__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__SUMMARY = ABSTRACT_CONCEPT_ITEM__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__DESCRIPTION = ABSTRACT_CONCEPT_ITEM__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__REVIEW = ABSTRACT_CONCEPT_ITEM__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_PROPERTY_VALUES = ABSTRACT_CONCEPT_ITEM__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_CONCEPT_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__APPLIED_PROPERTY_VALUES = ABSTRACT_CONCEPT_ITEM__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_CONCEPT_ITEM__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_CONCEPT_ITEM__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__STATUS = ABSTRACT_CONCEPT_ITEM__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__FEATURES = ABSTRACT_CONCEPT_ITEM__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_TRACES = ABSTRACT_CONCEPT_ITEM__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__CONTAINED_GENERIC_TRACES = ABSTRACT_CONCEPT_ITEM__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__NAMING_RULES = ABSTRACT_CONCEPT_ITEM__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__TYPED_ELEMENTS = ABSTRACT_CONCEPT_ITEM__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_FUNCTIONAL_ALLOCATION = ABSTRACT_CONCEPT_ITEM__OWNED_FUNCTIONAL_ALLOCATION;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_COMPONENT_EXCHANGES = ABSTRACT_CONCEPT_ITEM__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_COMPONENT_EXCHANGE_CATEGORIES = ABSTRACT_CONCEPT_ITEM__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__FUNCTIONAL_ALLOCATIONS = ABSTRACT_CONCEPT_ITEM__FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__ALLOCATED_FUNCTIONS = ABSTRACT_CONCEPT_ITEM__ALLOCATED_FUNCTIONS;

	/**
   * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__IN_EXCHANGE_LINKS = ABSTRACT_CONCEPT_ITEM__IN_EXCHANGE_LINKS;

	/**
   * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OUT_EXCHANGE_LINKS = ABSTRACT_CONCEPT_ITEM__OUT_EXCHANGE_LINKS;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_ABSTRACT_CAPABILITY_PKG = ABSTRACT_CONCEPT_ITEM__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
   * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_INTERFACE_PKG = ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_PKG;

	/**
   * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_DATA_PKG = ABSTRACT_CONCEPT_ITEM__OWNED_DATA_PKG;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_STATE_MACHINES = ABSTRACT_CONCEPT_ITEM__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__ABSTRACT = ABSTRACT_CONCEPT_ITEM__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_GENERALIZATIONS = ABSTRACT_CONCEPT_ITEM__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__SUPER_GENERALIZATIONS = ABSTRACT_CONCEPT_ITEM__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__SUB_GENERALIZATIONS = ABSTRACT_CONCEPT_ITEM__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__SUPER = ABSTRACT_CONCEPT_ITEM__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__SUB = ABSTRACT_CONCEPT_ITEM__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_FEATURES = ABSTRACT_CONCEPT_ITEM__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__CONTAINED_PROPERTIES = ABSTRACT_CONCEPT_ITEM__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_INTERFACE_ALLOCATIONS = ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__PROVISIONED_INTERFACE_ALLOCATIONS = ABSTRACT_CONCEPT_ITEM__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__ALLOCATED_INTERFACES = ABSTRACT_CONCEPT_ITEM__ALLOCATED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_COMMUNICATION_LINKS = ABSTRACT_CONCEPT_ITEM__OWNED_COMMUNICATION_LINKS;

	/**
   * The feature id for the '<em><b>Produce</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__PRODUCE = ABSTRACT_CONCEPT_ITEM__PRODUCE;

	/**
   * The feature id for the '<em><b>Consume</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__CONSUME = ABSTRACT_CONCEPT_ITEM__CONSUME;

	/**
   * The feature id for the '<em><b>Send</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__SEND = ABSTRACT_CONCEPT_ITEM__SEND;

	/**
   * The feature id for the '<em><b>Receive</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__RECEIVE = ABSTRACT_CONCEPT_ITEM__RECEIVE;

	/**
   * The feature id for the '<em><b>Call</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__CALL = ABSTRACT_CONCEPT_ITEM__CALL;

	/**
   * The feature id for the '<em><b>Execute</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__EXECUTE = ABSTRACT_CONCEPT_ITEM__EXECUTE;

	/**
   * The feature id for the '<em><b>Write</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__WRITE = ABSTRACT_CONCEPT_ITEM__WRITE;

	/**
   * The feature id for the '<em><b>Access</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__ACCESS = ABSTRACT_CONCEPT_ITEM__ACCESS;

	/**
   * The feature id for the '<em><b>Acquire</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__ACQUIRE = ABSTRACT_CONCEPT_ITEM__ACQUIRE;

	/**
   * The feature id for the '<em><b>Transmit</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__TRANSMIT = ABSTRACT_CONCEPT_ITEM__TRANSMIT;

	/**
   * The feature id for the '<em><b>Actor</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__ACTOR = ABSTRACT_CONCEPT_ITEM__ACTOR;

	/**
   * The feature id for the '<em><b>Human</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__HUMAN = ABSTRACT_CONCEPT_ITEM__HUMAN;

	/**
   * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_INTERFACE_USES = ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_USES;

	/**
   * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__USED_INTERFACE_LINKS = ABSTRACT_CONCEPT_ITEM__USED_INTERFACE_LINKS;

	/**
   * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__USED_INTERFACES = ABSTRACT_CONCEPT_ITEM__USED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_INTERFACE_IMPLEMENTATIONS = ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
   * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__IMPLEMENTED_INTERFACE_LINKS = ABSTRACT_CONCEPT_ITEM__IMPLEMENTED_INTERFACE_LINKS;

	/**
   * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__IMPLEMENTED_INTERFACES = ABSTRACT_CONCEPT_ITEM__IMPLEMENTED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Component Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_COMPONENT_REALIZATIONS = ABSTRACT_CONCEPT_ITEM__OWNED_COMPONENT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__REALIZED_COMPONENTS = ABSTRACT_CONCEPT_ITEM__REALIZED_COMPONENTS;

	/**
   * The feature id for the '<em><b>Realizing Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__REALIZING_COMPONENTS = ABSTRACT_CONCEPT_ITEM__REALIZING_COMPONENTS;

	/**
   * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__PROVIDED_INTERFACES = ABSTRACT_CONCEPT_ITEM__PROVIDED_INTERFACES;

	/**
   * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__REQUIRED_INTERFACES = ABSTRACT_CONCEPT_ITEM__REQUIRED_INTERFACES;

	/**
   * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__CONTAINED_COMPONENT_PORTS = ABSTRACT_CONCEPT_ITEM__CONTAINED_COMPONENT_PORTS;

	/**
   * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__CONTAINED_PARTS = ABSTRACT_CONCEPT_ITEM__CONTAINED_PARTS;

	/**
   * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__CONTAINED_PHYSICAL_PORTS = ABSTRACT_CONCEPT_ITEM__CONTAINED_PHYSICAL_PORTS;

	/**
   * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_PHYSICAL_PATH = ABSTRACT_CONCEPT_ITEM__OWNED_PHYSICAL_PATH;

	/**
   * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_PHYSICAL_LINKS = ABSTRACT_CONCEPT_ITEM__OWNED_PHYSICAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__OWNED_PHYSICAL_LINK_CATEGORIES = ABSTRACT_CONCEPT_ITEM__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
   * The feature id for the '<em><b>Representing Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__REPRESENTING_PARTS = ABSTRACT_CONCEPT_ITEM__REPRESENTING_PARTS;

	/**
   * The feature id for the '<em><b>Composing Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__COMPOSING_LINKS = ABSTRACT_CONCEPT_ITEM__COMPOSING_LINKS;

	/**
   * The feature id for the '<em><b>Location Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__LOCATION_DESCRIPTION = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Located Entities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION__LOCATED_ENTITIES = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Location</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOCATION_FEATURE_COUNT = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.CapabilityConfigurationImpl <em>Capability Configuration</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.CapabilityConfigurationImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getCapabilityConfiguration()
   * @generated
   */
	int CAPABILITY_CONFIGURATION = 25;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_EXTENSIONS = ABSTRACT_CONCEPT_ITEM__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__ID = ABSTRACT_CONCEPT_ITEM__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__SID = ABSTRACT_CONCEPT_ITEM__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__CONSTRAINTS = ABSTRACT_CONCEPT_ITEM__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_CONSTRAINTS = ABSTRACT_CONCEPT_ITEM__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_MIGRATED_ELEMENTS = ABSTRACT_CONCEPT_ITEM__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__NAME = ABSTRACT_CONCEPT_ITEM__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__ABSTRACT_TYPED_ELEMENTS = ABSTRACT_CONCEPT_ITEM__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__INCOMING_TRACES = ABSTRACT_CONCEPT_ITEM__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OUTGOING_TRACES = ABSTRACT_CONCEPT_ITEM__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__VISIBLE_IN_DOC = ABSTRACT_CONCEPT_ITEM__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__VISIBLE_IN_LM = ABSTRACT_CONCEPT_ITEM__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__SUMMARY = ABSTRACT_CONCEPT_ITEM__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__DESCRIPTION = ABSTRACT_CONCEPT_ITEM__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__REVIEW = ABSTRACT_CONCEPT_ITEM__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_PROPERTY_VALUES = ABSTRACT_CONCEPT_ITEM__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_CONCEPT_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__APPLIED_PROPERTY_VALUES = ABSTRACT_CONCEPT_ITEM__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_CONCEPT_ITEM__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_CONCEPT_ITEM__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__STATUS = ABSTRACT_CONCEPT_ITEM__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__FEATURES = ABSTRACT_CONCEPT_ITEM__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_TRACES = ABSTRACT_CONCEPT_ITEM__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__CONTAINED_GENERIC_TRACES = ABSTRACT_CONCEPT_ITEM__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__NAMING_RULES = ABSTRACT_CONCEPT_ITEM__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__TYPED_ELEMENTS = ABSTRACT_CONCEPT_ITEM__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_FUNCTIONAL_ALLOCATION = ABSTRACT_CONCEPT_ITEM__OWNED_FUNCTIONAL_ALLOCATION;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_COMPONENT_EXCHANGES = ABSTRACT_CONCEPT_ITEM__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_COMPONENT_EXCHANGE_CATEGORIES = ABSTRACT_CONCEPT_ITEM__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__FUNCTIONAL_ALLOCATIONS = ABSTRACT_CONCEPT_ITEM__FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__ALLOCATED_FUNCTIONS = ABSTRACT_CONCEPT_ITEM__ALLOCATED_FUNCTIONS;

	/**
   * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__IN_EXCHANGE_LINKS = ABSTRACT_CONCEPT_ITEM__IN_EXCHANGE_LINKS;

	/**
   * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OUT_EXCHANGE_LINKS = ABSTRACT_CONCEPT_ITEM__OUT_EXCHANGE_LINKS;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_ABSTRACT_CAPABILITY_PKG = ABSTRACT_CONCEPT_ITEM__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
   * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_INTERFACE_PKG = ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_PKG;

	/**
   * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_DATA_PKG = ABSTRACT_CONCEPT_ITEM__OWNED_DATA_PKG;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_STATE_MACHINES = ABSTRACT_CONCEPT_ITEM__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__ABSTRACT = ABSTRACT_CONCEPT_ITEM__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_GENERALIZATIONS = ABSTRACT_CONCEPT_ITEM__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__SUPER_GENERALIZATIONS = ABSTRACT_CONCEPT_ITEM__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__SUB_GENERALIZATIONS = ABSTRACT_CONCEPT_ITEM__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__SUPER = ABSTRACT_CONCEPT_ITEM__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__SUB = ABSTRACT_CONCEPT_ITEM__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_FEATURES = ABSTRACT_CONCEPT_ITEM__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__CONTAINED_PROPERTIES = ABSTRACT_CONCEPT_ITEM__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_INTERFACE_ALLOCATIONS = ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__PROVISIONED_INTERFACE_ALLOCATIONS = ABSTRACT_CONCEPT_ITEM__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__ALLOCATED_INTERFACES = ABSTRACT_CONCEPT_ITEM__ALLOCATED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_COMMUNICATION_LINKS = ABSTRACT_CONCEPT_ITEM__OWNED_COMMUNICATION_LINKS;

	/**
   * The feature id for the '<em><b>Produce</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__PRODUCE = ABSTRACT_CONCEPT_ITEM__PRODUCE;

	/**
   * The feature id for the '<em><b>Consume</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__CONSUME = ABSTRACT_CONCEPT_ITEM__CONSUME;

	/**
   * The feature id for the '<em><b>Send</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__SEND = ABSTRACT_CONCEPT_ITEM__SEND;

	/**
   * The feature id for the '<em><b>Receive</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__RECEIVE = ABSTRACT_CONCEPT_ITEM__RECEIVE;

	/**
   * The feature id for the '<em><b>Call</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__CALL = ABSTRACT_CONCEPT_ITEM__CALL;

	/**
   * The feature id for the '<em><b>Execute</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__EXECUTE = ABSTRACT_CONCEPT_ITEM__EXECUTE;

	/**
   * The feature id for the '<em><b>Write</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__WRITE = ABSTRACT_CONCEPT_ITEM__WRITE;

	/**
   * The feature id for the '<em><b>Access</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__ACCESS = ABSTRACT_CONCEPT_ITEM__ACCESS;

	/**
   * The feature id for the '<em><b>Acquire</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__ACQUIRE = ABSTRACT_CONCEPT_ITEM__ACQUIRE;

	/**
   * The feature id for the '<em><b>Transmit</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__TRANSMIT = ABSTRACT_CONCEPT_ITEM__TRANSMIT;

	/**
   * The feature id for the '<em><b>Actor</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__ACTOR = ABSTRACT_CONCEPT_ITEM__ACTOR;

	/**
   * The feature id for the '<em><b>Human</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__HUMAN = ABSTRACT_CONCEPT_ITEM__HUMAN;

	/**
   * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_INTERFACE_USES = ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_USES;

	/**
   * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__USED_INTERFACE_LINKS = ABSTRACT_CONCEPT_ITEM__USED_INTERFACE_LINKS;

	/**
   * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__USED_INTERFACES = ABSTRACT_CONCEPT_ITEM__USED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_INTERFACE_IMPLEMENTATIONS = ABSTRACT_CONCEPT_ITEM__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
   * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__IMPLEMENTED_INTERFACE_LINKS = ABSTRACT_CONCEPT_ITEM__IMPLEMENTED_INTERFACE_LINKS;

	/**
   * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__IMPLEMENTED_INTERFACES = ABSTRACT_CONCEPT_ITEM__IMPLEMENTED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Component Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_COMPONENT_REALIZATIONS = ABSTRACT_CONCEPT_ITEM__OWNED_COMPONENT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__REALIZED_COMPONENTS = ABSTRACT_CONCEPT_ITEM__REALIZED_COMPONENTS;

	/**
   * The feature id for the '<em><b>Realizing Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__REALIZING_COMPONENTS = ABSTRACT_CONCEPT_ITEM__REALIZING_COMPONENTS;

	/**
   * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__PROVIDED_INTERFACES = ABSTRACT_CONCEPT_ITEM__PROVIDED_INTERFACES;

	/**
   * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__REQUIRED_INTERFACES = ABSTRACT_CONCEPT_ITEM__REQUIRED_INTERFACES;

	/**
   * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__CONTAINED_COMPONENT_PORTS = ABSTRACT_CONCEPT_ITEM__CONTAINED_COMPONENT_PORTS;

	/**
   * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__CONTAINED_PARTS = ABSTRACT_CONCEPT_ITEM__CONTAINED_PARTS;

	/**
   * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__CONTAINED_PHYSICAL_PORTS = ABSTRACT_CONCEPT_ITEM__CONTAINED_PHYSICAL_PORTS;

	/**
   * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_PHYSICAL_PATH = ABSTRACT_CONCEPT_ITEM__OWNED_PHYSICAL_PATH;

	/**
   * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_PHYSICAL_LINKS = ABSTRACT_CONCEPT_ITEM__OWNED_PHYSICAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__OWNED_PHYSICAL_LINK_CATEGORIES = ABSTRACT_CONCEPT_ITEM__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
   * The feature id for the '<em><b>Representing Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__REPRESENTING_PARTS = ABSTRACT_CONCEPT_ITEM__REPRESENTING_PARTS;

	/**
   * The feature id for the '<em><b>Composing Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__COMPOSING_LINKS = ABSTRACT_CONCEPT_ITEM__COMPOSING_LINKS;

	/**
   * The feature id for the '<em><b>Configured Capability</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION__CONFIGURED_CAPABILITY = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Capability Configuration</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_CONFIGURATION_FEATURE_COUNT = ABSTRACT_CONCEPT_ITEM_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.CommunicationMeanImpl <em>Communication Mean</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.CommunicationMeanImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getCommunicationMean()
   * @generated
   */
	int COMMUNICATION_MEAN = 26;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__OWNED_EXTENSIONS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__ID = CapellacorePackage.NAMED_RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__SID = CapellacorePackage.NAMED_RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__CONSTRAINTS = CapellacorePackage.NAMED_RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__REALIZED_FLOW = CapellacorePackage.NAMED_RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__INCOMING_TRACES = CapellacorePackage.NAMED_RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__OUTGOING_TRACES = CapellacorePackage.NAMED_RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__VISIBLE_IN_DOC = CapellacorePackage.NAMED_RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__VISIBLE_IN_LM = CapellacorePackage.NAMED_RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__SUMMARY = CapellacorePackage.NAMED_RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__DESCRIPTION = CapellacorePackage.NAMED_RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__REVIEW = CapellacorePackage.NAMED_RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__STATUS = CapellacorePackage.NAMED_RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__FEATURES = CapellacorePackage.NAMED_RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__NAME = CapellacorePackage.NAMED_RELATIONSHIP__NAME;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__NAMING_RULES = CapellacorePackage.NAMED_RELATIONSHIP__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__ABSTRACT_TYPED_ELEMENTS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Invoking Sequence Messages</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__INVOKING_SEQUENCE_MESSAGES = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__REALIZATIONS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Convoyed Informations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__CONVOYED_INFORMATIONS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__SOURCE = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__TARGET = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Realizing Activity Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__REALIZING_ACTIVITY_FLOWS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Containing Link</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__CONTAINING_LINK = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Link</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__LINK = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Outgoing Exchange Specification Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__OUTGOING_EXCHANGE_SPECIFICATION_REALIZATIONS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Incoming Exchange Specification Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__INCOMING_EXCHANGE_SPECIFICATION_REALIZATIONS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__KIND = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Oriented</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__ORIENTED = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Allocated Functional Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__ALLOCATED_FUNCTIONAL_EXCHANGES = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Incoming Component Exchange Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__INCOMING_COMPONENT_EXCHANGE_REALIZATIONS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Outgoing Component Exchange Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__OUTGOING_COMPONENT_EXCHANGE_REALIZATIONS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Outgoing Component Exchange Functional Exchange Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__OUTGOING_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Functional Exchange Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Ends</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__OWNED_COMPONENT_EXCHANGE_ENDS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 19;

	/**
   * The feature id for the '<em><b>Source Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__SOURCE_PORT = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 20;

	/**
   * The feature id for the '<em><b>Source Part</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__SOURCE_PART = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 21;

	/**
   * The feature id for the '<em><b>Target Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__TARGET_PORT = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 22;

	/**
   * The feature id for the '<em><b>Target Part</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__TARGET_PART = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 23;

	/**
   * The feature id for the '<em><b>Categories</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__CATEGORIES = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 24;

	/**
   * The feature id for the '<em><b>Allocator Physical Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__ALLOCATOR_PHYSICAL_LINKS = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 25;

	/**
   * The feature id for the '<em><b>Realized Component Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__REALIZED_COMPONENT_EXCHANGES = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 26;

	/**
   * The feature id for the '<em><b>Realizing Component Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__REALIZING_COMPONENT_EXCHANGES = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 27;

	/**
   * The feature id for the '<em><b>Source Entity</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__SOURCE_ENTITY = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 28;

	/**
   * The feature id for the '<em><b>Target Entity</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN__TARGET_ENTITY = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 29;

	/**
   * The number of structural features of the '<em>Communication Mean</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMMUNICATION_MEAN_FEATURE_COUNT = CapellacorePackage.NAMED_RELATIONSHIP_FEATURE_COUNT + 30;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.oa.impl.EntityOperationalCapabilityInvolvementImpl <em>Entity Operational Capability Involvement</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.oa.impl.EntityOperationalCapabilityInvolvementImpl
   * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getEntityOperationalCapabilityInvolvement()
   * @generated
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT = 27;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__OWNED_EXTENSIONS = CapellacorePackage.INVOLVEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__ID = CapellacorePackage.INVOLVEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__SID = CapellacorePackage.INVOLVEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__CONSTRAINTS = CapellacorePackage.INVOLVEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__OWNED_CONSTRAINTS = CapellacorePackage.INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.INVOLVEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__REALIZED_FLOW = CapellacorePackage.INVOLVEMENT__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__INCOMING_TRACES = CapellacorePackage.INVOLVEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__OUTGOING_TRACES = CapellacorePackage.INVOLVEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__VISIBLE_IN_DOC = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__VISIBLE_IN_LM = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__SUMMARY = CapellacorePackage.INVOLVEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__DESCRIPTION = CapellacorePackage.INVOLVEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__REVIEW = CapellacorePackage.INVOLVEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__STATUS = CapellacorePackage.INVOLVEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__FEATURES = CapellacorePackage.INVOLVEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involver</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__INVOLVER = CapellacorePackage.INVOLVEMENT__INVOLVER;

	/**
   * The feature id for the '<em><b>Involved</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__INVOLVED = CapellacorePackage.INVOLVEMENT__INVOLVED;

	/**
   * The feature id for the '<em><b>Entity</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__ENTITY = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Capability</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__CAPABILITY = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Entity Operational Capability Involvement</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT_FEATURE_COUNT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 2;

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.OperationalAnalysis <em>Operational Analysis</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operational Analysis</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalAnalysis
   * @generated
   */
	EClass getOperationalAnalysis();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.oa.OperationalAnalysis#getOwnedRolePkg <em>Owned Role Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Role Pkg</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalAnalysis#getOwnedRolePkg()
   * @see #getOperationalAnalysis()
   * @generated
   */
	EReference getOperationalAnalysis_OwnedRolePkg();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.oa.OperationalAnalysis#getOwnedEntityPkg <em>Owned Entity Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Entity Pkg</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalAnalysis#getOwnedEntityPkg()
   * @see #getOperationalAnalysis()
   * @generated
   */
	EReference getOperationalAnalysis_OwnedEntityPkg();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.oa.OperationalAnalysis#getOwnedConceptPkg <em>Owned Concept Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Concept Pkg</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalAnalysis#getOwnedConceptPkg()
   * @see #getOperationalAnalysis()
   * @generated
   */
	EReference getOperationalAnalysis_OwnedConceptPkg();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.OperationalAnalysis#getContainedOperationalCapabilityPkg <em>Contained Operational Capability Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Contained Operational Capability Pkg</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalAnalysis#getContainedOperationalCapabilityPkg()
   * @see #getOperationalAnalysis()
   * @generated
   */
	EReference getOperationalAnalysis_ContainedOperationalCapabilityPkg();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.OperationalAnalysis#getContainedOperationalActivityPkg <em>Contained Operational Activity Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Contained Operational Activity Pkg</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalAnalysis#getContainedOperationalActivityPkg()
   * @see #getOperationalAnalysis()
   * @generated
   */
	EReference getOperationalAnalysis_ContainedOperationalActivityPkg();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.OperationalAnalysis#getAllocatingSystemAnalyses <em>Allocating System Analyses</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocating System Analyses</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalAnalysis#getAllocatingSystemAnalyses()
   * @see #getOperationalAnalysis()
   * @generated
   */
	EReference getOperationalAnalysis_AllocatingSystemAnalyses();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.OperationalScenario <em>Operational Scenario</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operational Scenario</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalScenario
   * @generated
   */
	EClass getOperationalScenario();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.oa.OperationalScenario#getContext <em>Context</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Context</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalScenario#getContext()
   * @see #getOperationalScenario()
   * @generated
   */
	EAttribute getOperationalScenario_Context();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.oa.OperationalScenario#getObjective <em>Objective</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Objective</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalScenario#getObjective()
   * @see #getOperationalScenario()
   * @generated
   */
	EAttribute getOperationalScenario_Objective();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.OperationalActivityPkg <em>Operational Activity Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operational Activity Pkg</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalActivityPkg
   * @generated
   */
	EClass getOperationalActivityPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.OperationalActivityPkg#getOwnedOperationalActivities <em>Owned Operational Activities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Operational Activities</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalActivityPkg#getOwnedOperationalActivities()
   * @see #getOperationalActivityPkg()
   * @generated
   */
	EReference getOperationalActivityPkg_OwnedOperationalActivities();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.OperationalActivityPkg#getOwnedOperationalActivityPkgs <em>Owned Operational Activity Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Operational Activity Pkgs</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalActivityPkg#getOwnedOperationalActivityPkgs()
   * @see #getOperationalActivityPkg()
   * @generated
   */
	EReference getOperationalActivityPkg_OwnedOperationalActivityPkgs();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.OperationalActivity <em>Operational Activity</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operational Activity</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalActivity
   * @generated
   */
	EClass getOperationalActivity();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.OperationalActivity#getOwnedOperationalActivityPkgs <em>Owned Operational Activity Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Operational Activity Pkgs</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalActivity#getOwnedOperationalActivityPkgs()
   * @see #getOperationalActivity()
   * @generated
   */
	EReference getOperationalActivity_OwnedOperationalActivityPkgs();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.OperationalActivity#getActivityAllocations <em>Activity Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Activity Allocations</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalActivity#getActivityAllocations()
   * @see #getOperationalActivity()
   * @generated
   */
	EReference getOperationalActivity_ActivityAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.OperationalActivity#getOwnedSwimlanes <em>Owned Swimlanes</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Owned Swimlanes</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalActivity#getOwnedSwimlanes()
   * @see #getOperationalActivity()
   * @generated
   */
	EReference getOperationalActivity_OwnedSwimlanes();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.OperationalActivity#getOwnedProcess <em>Owned Process</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Owned Process</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalActivity#getOwnedProcess()
   * @see #getOperationalActivity()
   * @generated
   */
	EReference getOperationalActivity_OwnedProcess();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.OperationalActivity#getAllocatorEntities <em>Allocator Entities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocator Entities</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalActivity#getAllocatorEntities()
   * @see #getOperationalActivity()
   * @generated
   */
	EReference getOperationalActivity_AllocatorEntities();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.OperationalActivity#getRealizingSystemFunctions <em>Realizing System Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing System Functions</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalActivity#getRealizingSystemFunctions()
   * @see #getOperationalActivity()
   * @generated
   */
	EReference getOperationalActivity_RealizingSystemFunctions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.OperationalActivity#getAllocatingRoles <em>Allocating Roles</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocating Roles</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalActivity#getAllocatingRoles()
   * @see #getOperationalActivity()
   * @generated
   */
	EReference getOperationalActivity_AllocatingRoles();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.OperationalActivity#getContainedOperationalActivities <em>Contained Operational Activities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Operational Activities</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalActivity#getContainedOperationalActivities()
   * @see #getOperationalActivity()
   * @generated
   */
	EReference getOperationalActivity_ContainedOperationalActivities();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.OperationalActivity#getChildrenOperationalActivities <em>Children Operational Activities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Children Operational Activities</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalActivity#getChildrenOperationalActivities()
   * @see #getOperationalActivity()
   * @generated
   */
	EReference getOperationalActivity_ChildrenOperationalActivities();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.OperationalProcess <em>Operational Process</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operational Process</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalProcess
   * @generated
   */
	EClass getOperationalProcess();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.OperationalProcess#getInvolvingOperationalCapabilities <em>Involving Operational Capabilities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involving Operational Capabilities</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalProcess#getInvolvingOperationalCapabilities()
   * @see #getOperationalProcess()
   * @generated
   */
	EReference getOperationalProcess_InvolvingOperationalCapabilities();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.Swimlane <em>Swimlane</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Swimlane</em>'.
   * @see org.polarsys.capella.core.data.oa.Swimlane
   * @generated
   */
	EClass getSwimlane();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.Swimlane#getRepresentedEntity <em>Represented Entity</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Represented Entity</em>'.
   * @see org.polarsys.capella.core.data.oa.Swimlane#getRepresentedEntity()
   * @see #getSwimlane()
   * @generated
   */
	EReference getSwimlane_RepresentedEntity();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.OperationalCapabilityPkg <em>Operational Capability Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operational Capability Pkg</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalCapabilityPkg
   * @generated
   */
	EClass getOperationalCapabilityPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.OperationalCapabilityPkg#getOwnedOperationalCapabilities <em>Owned Operational Capabilities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Operational Capabilities</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalCapabilityPkg#getOwnedOperationalCapabilities()
   * @see #getOperationalCapabilityPkg()
   * @generated
   */
	EReference getOperationalCapabilityPkg_OwnedOperationalCapabilities();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.OperationalCapabilityPkg#getOwnedOperationalCapabilityPkgs <em>Owned Operational Capability Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Operational Capability Pkgs</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalCapabilityPkg#getOwnedOperationalCapabilityPkgs()
   * @see #getOperationalCapabilityPkg()
   * @generated
   */
	EReference getOperationalCapabilityPkg_OwnedOperationalCapabilityPkgs();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.OperationalCapabilityPkg#getOwnedCapabilityConfigurations <em>Owned Capability Configurations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Capability Configurations</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalCapabilityPkg#getOwnedCapabilityConfigurations()
   * @see #getOperationalCapabilityPkg()
   * @generated
   */
	EReference getOperationalCapabilityPkg_OwnedCapabilityConfigurations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.OperationalCapabilityPkg#getOwnedConceptCompliances <em>Owned Concept Compliances</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Concept Compliances</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalCapabilityPkg#getOwnedConceptCompliances()
   * @see #getOperationalCapabilityPkg()
   * @generated
   */
	EReference getOperationalCapabilityPkg_OwnedConceptCompliances();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.OperationalCapability <em>Operational Capability</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operational Capability</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalCapability
   * @generated
   */
	EClass getOperationalCapability();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.OperationalCapability#getCompliances <em>Compliances</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Compliances</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalCapability#getCompliances()
   * @see #getOperationalCapability()
   * @generated
   */
	EReference getOperationalCapability_Compliances();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.OperationalCapability#getConfigurations <em>Configurations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Configurations</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalCapability#getConfigurations()
   * @see #getOperationalCapability()
   * @generated
   */
	EReference getOperationalCapability_Configurations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.OperationalCapability#getOwnedEntityOperationalCapabilityInvolvements <em>Owned Entity Operational Capability Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Entity Operational Capability Involvements</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalCapability#getOwnedEntityOperationalCapabilityInvolvements()
   * @see #getOperationalCapability()
   * @generated
   */
	EReference getOperationalCapability_OwnedEntityOperationalCapabilityInvolvements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.OperationalCapability#getRealizingCapabilities <em>Realizing Capabilities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Capabilities</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalCapability#getRealizingCapabilities()
   * @see #getOperationalCapability()
   * @generated
   */
	EReference getOperationalCapability_RealizingCapabilities();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.OperationalCapability#getInvolvedEntities <em>Involved Entities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involved Entities</em>'.
   * @see org.polarsys.capella.core.data.oa.OperationalCapability#getInvolvedEntities()
   * @see #getOperationalCapability()
   * @generated
   */
	EReference getOperationalCapability_InvolvedEntities();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.ActivityAllocation <em>Activity Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Activity Allocation</em>'.
   * @see org.polarsys.capella.core.data.oa.ActivityAllocation
   * @generated
   */
	EClass getActivityAllocation();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.ActivityAllocation#getRole <em>Role</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Role</em>'.
   * @see org.polarsys.capella.core.data.oa.ActivityAllocation#getRole()
   * @see #getActivityAllocation()
   * @generated
   */
	EReference getActivityAllocation_Role();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.ActivityAllocation#getActivity <em>Activity</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Activity</em>'.
   * @see org.polarsys.capella.core.data.oa.ActivityAllocation#getActivity()
   * @see #getActivityAllocation()
   * @generated
   */
	EReference getActivityAllocation_Activity();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.RolePkg <em>Role Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Role Pkg</em>'.
   * @see org.polarsys.capella.core.data.oa.RolePkg
   * @generated
   */
	EClass getRolePkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.RolePkg#getOwnedRolePkgs <em>Owned Role Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Role Pkgs</em>'.
   * @see org.polarsys.capella.core.data.oa.RolePkg#getOwnedRolePkgs()
   * @see #getRolePkg()
   * @generated
   */
	EReference getRolePkg_OwnedRolePkgs();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.RolePkg#getOwnedRoles <em>Owned Roles</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Roles</em>'.
   * @see org.polarsys.capella.core.data.oa.RolePkg#getOwnedRoles()
   * @see #getRolePkg()
   * @generated
   */
	EReference getRolePkg_OwnedRoles();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.Role <em>Role</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Role</em>'.
   * @see org.polarsys.capella.core.data.oa.Role
   * @generated
   */
	EClass getRole();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.Role#getOwnedRoleAssemblyUsages <em>Owned Role Assembly Usages</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Role Assembly Usages</em>'.
   * @see org.polarsys.capella.core.data.oa.Role#getOwnedRoleAssemblyUsages()
   * @see #getRole()
   * @generated
   */
	EReference getRole_OwnedRoleAssemblyUsages();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.Role#getOwnedActivityAllocations <em>Owned Activity Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Activity Allocations</em>'.
   * @see org.polarsys.capella.core.data.oa.Role#getOwnedActivityAllocations()
   * @see #getRole()
   * @generated
   */
	EReference getRole_OwnedActivityAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.Role#getRoleAllocations <em>Role Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Role Allocations</em>'.
   * @see org.polarsys.capella.core.data.oa.Role#getRoleAllocations()
   * @see #getRole()
   * @generated
   */
	EReference getRole_RoleAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.Role#getActivityAllocations <em>Activity Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Activity Allocations</em>'.
   * @see org.polarsys.capella.core.data.oa.Role#getActivityAllocations()
   * @see #getRole()
   * @generated
   */
	EReference getRole_ActivityAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.Role#getAllocatingEntities <em>Allocating Entities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocating Entities</em>'.
   * @see org.polarsys.capella.core.data.oa.Role#getAllocatingEntities()
   * @see #getRole()
   * @generated
   */
	EReference getRole_AllocatingEntities();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.Role#getAllocatedOperationalActivities <em>Allocated Operational Activities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated Operational Activities</em>'.
   * @see org.polarsys.capella.core.data.oa.Role#getAllocatedOperationalActivities()
   * @see #getRole()
   * @generated
   */
	EReference getRole_AllocatedOperationalActivities();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.RoleAssemblyUsage <em>Role Assembly Usage</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Role Assembly Usage</em>'.
   * @see org.polarsys.capella.core.data.oa.RoleAssemblyUsage
   * @generated
   */
	EClass getRoleAssemblyUsage();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.RoleAssemblyUsage#getChild <em>Child</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Child</em>'.
   * @see org.polarsys.capella.core.data.oa.RoleAssemblyUsage#getChild()
   * @see #getRoleAssemblyUsage()
   * @generated
   */
	EReference getRoleAssemblyUsage_Child();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.RoleAllocation <em>Role Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Role Allocation</em>'.
   * @see org.polarsys.capella.core.data.oa.RoleAllocation
   * @generated
   */
	EClass getRoleAllocation();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.RoleAllocation#getRole <em>Role</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Role</em>'.
   * @see org.polarsys.capella.core.data.oa.RoleAllocation#getRole()
   * @see #getRoleAllocation()
   * @generated
   */
	EReference getRoleAllocation_Role();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.RoleAllocation#getEntity <em>Entity</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Entity</em>'.
   * @see org.polarsys.capella.core.data.oa.RoleAllocation#getEntity()
   * @see #getRoleAllocation()
   * @generated
   */
	EReference getRoleAllocation_Entity();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.EntityPkg <em>Entity Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Pkg</em>'.
   * @see org.polarsys.capella.core.data.oa.EntityPkg
   * @generated
   */
	EClass getEntityPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.EntityPkg#getOwnedEntityPkgs <em>Owned Entity Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Entity Pkgs</em>'.
   * @see org.polarsys.capella.core.data.oa.EntityPkg#getOwnedEntityPkgs()
   * @see #getEntityPkg()
   * @generated
   */
	EReference getEntityPkg_OwnedEntityPkgs();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.EntityPkg#getOwnedEntities <em>Owned Entities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Entities</em>'.
   * @see org.polarsys.capella.core.data.oa.EntityPkg#getOwnedEntities()
   * @see #getEntityPkg()
   * @generated
   */
	EReference getEntityPkg_OwnedEntities();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.EntityPkg#getOwnedLocations <em>Owned Locations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Locations</em>'.
   * @see org.polarsys.capella.core.data.oa.EntityPkg#getOwnedLocations()
   * @see #getEntityPkg()
   * @generated
   */
	EReference getEntityPkg_OwnedLocations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.EntityPkg#getOwnedCommunicationMeans <em>Owned Communication Means</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Communication Means</em>'.
   * @see org.polarsys.capella.core.data.oa.EntityPkg#getOwnedCommunicationMeans()
   * @see #getEntityPkg()
   * @generated
   */
	EReference getEntityPkg_OwnedCommunicationMeans();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.Entity <em>Entity</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity</em>'.
   * @see org.polarsys.capella.core.data.oa.Entity
   * @generated
   */
	EClass getEntity();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.Entity#getRoleAllocations <em>Role Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Role Allocations</em>'.
   * @see org.polarsys.capella.core.data.oa.Entity#getRoleAllocations()
   * @see #getEntity()
   * @generated
   */
	EReference getEntity_RoleAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.Entity#getOrganisationalUnitMemberships <em>Organisational Unit Memberships</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Organisational Unit Memberships</em>'.
   * @see org.polarsys.capella.core.data.oa.Entity#getOrganisationalUnitMemberships()
   * @see #getEntity()
   * @generated
   */
	EReference getEntity_OrganisationalUnitMemberships();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.Entity#getActualLocation <em>Actual Location</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Actual Location</em>'.
   * @see org.polarsys.capella.core.data.oa.Entity#getActualLocation()
   * @see #getEntity()
   * @generated
   */
	EReference getEntity_ActualLocation();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.Entity#getSubEntities <em>Sub Entities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Sub Entities</em>'.
   * @see org.polarsys.capella.core.data.oa.Entity#getSubEntities()
   * @see #getEntity()
   * @generated
   */
	EReference getEntity_SubEntities();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.Entity#getOwnedEntities <em>Owned Entities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Entities</em>'.
   * @see org.polarsys.capella.core.data.oa.Entity#getOwnedEntities()
   * @see #getEntity()
   * @generated
   */
	EReference getEntity_OwnedEntities();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.Entity#getOwnedCommunicationMeans <em>Owned Communication Means</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Communication Means</em>'.
   * @see org.polarsys.capella.core.data.oa.Entity#getOwnedCommunicationMeans()
   * @see #getEntity()
   * @generated
   */
	EReference getEntity_OwnedCommunicationMeans();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.Entity#getOwnedRoleAllocations <em>Owned Role Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Role Allocations</em>'.
   * @see org.polarsys.capella.core.data.oa.Entity#getOwnedRoleAllocations()
   * @see #getEntity()
   * @generated
   */
	EReference getEntity_OwnedRoleAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.Entity#getAllocatedOperationalActivities <em>Allocated Operational Activities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated Operational Activities</em>'.
   * @see org.polarsys.capella.core.data.oa.Entity#getAllocatedOperationalActivities()
   * @see #getEntity()
   * @generated
   */
	EReference getEntity_AllocatedOperationalActivities();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.Entity#getAllocatedRoles <em>Allocated Roles</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated Roles</em>'.
   * @see org.polarsys.capella.core.data.oa.Entity#getAllocatedRoles()
   * @see #getEntity()
   * @generated
   */
	EReference getEntity_AllocatedRoles();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.Entity#getInvolvingOperationalCapabilities <em>Involving Operational Capabilities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involving Operational Capabilities</em>'.
   * @see org.polarsys.capella.core.data.oa.Entity#getInvolvingOperationalCapabilities()
   * @see #getEntity()
   * @generated
   */
	EReference getEntity_InvolvingOperationalCapabilities();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.Entity#getRealizingSystemComponents <em>Realizing System Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing System Components</em>'.
   * @see org.polarsys.capella.core.data.oa.Entity#getRealizingSystemComponents()
   * @see #getEntity()
   * @generated
   */
	EReference getEntity_RealizingSystemComponents();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.ConceptPkg <em>Concept Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Concept Pkg</em>'.
   * @see org.polarsys.capella.core.data.oa.ConceptPkg
   * @generated
   */
	EClass getConceptPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.ConceptPkg#getOwnedConceptPkgs <em>Owned Concept Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Concept Pkgs</em>'.
   * @see org.polarsys.capella.core.data.oa.ConceptPkg#getOwnedConceptPkgs()
   * @see #getConceptPkg()
   * @generated
   */
	EReference getConceptPkg_OwnedConceptPkgs();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.ConceptPkg#getOwnedConcepts <em>Owned Concepts</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Concepts</em>'.
   * @see org.polarsys.capella.core.data.oa.ConceptPkg#getOwnedConcepts()
   * @see #getConceptPkg()
   * @generated
   */
	EReference getConceptPkg_OwnedConcepts();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.Concept <em>Concept</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Concept</em>'.
   * @see org.polarsys.capella.core.data.oa.Concept
   * @generated
   */
	EClass getConcept();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.Concept#getCompliances <em>Compliances</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Compliances</em>'.
   * @see org.polarsys.capella.core.data.oa.Concept#getCompliances()
   * @see #getConcept()
   * @generated
   */
	EReference getConcept_Compliances();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.Concept#getCompositeLinks <em>Composite Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Composite Links</em>'.
   * @see org.polarsys.capella.core.data.oa.Concept#getCompositeLinks()
   * @see #getConcept()
   * @generated
   */
	EReference getConcept_CompositeLinks();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.ConceptCompliance <em>Concept Compliance</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Concept Compliance</em>'.
   * @see org.polarsys.capella.core.data.oa.ConceptCompliance
   * @generated
   */
	EClass getConceptCompliance();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.ConceptCompliance#getComplyWithConcept <em>Comply With Concept</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Comply With Concept</em>'.
   * @see org.polarsys.capella.core.data.oa.ConceptCompliance#getComplyWithConcept()
   * @see #getConceptCompliance()
   * @generated
   */
	EReference getConceptCompliance_ComplyWithConcept();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.ConceptCompliance#getCompliantCapability <em>Compliant Capability</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Compliant Capability</em>'.
   * @see org.polarsys.capella.core.data.oa.ConceptCompliance#getCompliantCapability()
   * @see #getConceptCompliance()
   * @generated
   */
	EReference getConceptCompliance_CompliantCapability();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.ItemInConcept <em>Item In Concept</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Item In Concept</em>'.
   * @see org.polarsys.capella.core.data.oa.ItemInConcept
   * @generated
   */
	EClass getItemInConcept();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.ItemInConcept#getConcept <em>Concept</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Concept</em>'.
   * @see org.polarsys.capella.core.data.oa.ItemInConcept#getConcept()
   * @see #getItemInConcept()
   * @generated
   */
	EReference getItemInConcept_Concept();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.ItemInConcept#getItem <em>Item</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Item</em>'.
   * @see org.polarsys.capella.core.data.oa.ItemInConcept#getItem()
   * @see #getItemInConcept()
   * @generated
   */
	EReference getItemInConcept_Item();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.AbstractConceptItem <em>Abstract Concept Item</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Concept Item</em>'.
   * @see org.polarsys.capella.core.data.oa.AbstractConceptItem
   * @generated
   */
	EClass getAbstractConceptItem();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.AbstractConceptItem#getComposingLinks <em>Composing Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Composing Links</em>'.
   * @see org.polarsys.capella.core.data.oa.AbstractConceptItem#getComposingLinks()
   * @see #getAbstractConceptItem()
   * @generated
   */
	EReference getAbstractConceptItem_ComposingLinks();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.CommunityOfInterest <em>Community Of Interest</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Community Of Interest</em>'.
   * @see org.polarsys.capella.core.data.oa.CommunityOfInterest
   * @generated
   */
	EClass getCommunityOfInterest();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.CommunityOfInterest#getCommunityOfInterestCompositions <em>Community Of Interest Compositions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Community Of Interest Compositions</em>'.
   * @see org.polarsys.capella.core.data.oa.CommunityOfInterest#getCommunityOfInterestCompositions()
   * @see #getCommunityOfInterest()
   * @generated
   */
	EReference getCommunityOfInterest_CommunityOfInterestCompositions();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.CommunityOfInterestComposition <em>Community Of Interest Composition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Community Of Interest Composition</em>'.
   * @see org.polarsys.capella.core.data.oa.CommunityOfInterestComposition
   * @generated
   */
	EClass getCommunityOfInterestComposition();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.CommunityOfInterestComposition#getCommunityOfInterest <em>Community Of Interest</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Community Of Interest</em>'.
   * @see org.polarsys.capella.core.data.oa.CommunityOfInterestComposition#getCommunityOfInterest()
   * @see #getCommunityOfInterestComposition()
   * @generated
   */
	EReference getCommunityOfInterestComposition_CommunityOfInterest();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.CommunityOfInterestComposition#getInterestedOrganisationUnit <em>Interested Organisation Unit</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Interested Organisation Unit</em>'.
   * @see org.polarsys.capella.core.data.oa.CommunityOfInterestComposition#getInterestedOrganisationUnit()
   * @see #getCommunityOfInterestComposition()
   * @generated
   */
	EReference getCommunityOfInterestComposition_InterestedOrganisationUnit();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.OrganisationalUnit <em>Organisational Unit</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Organisational Unit</em>'.
   * @see org.polarsys.capella.core.data.oa.OrganisationalUnit
   * @generated
   */
	EClass getOrganisationalUnit();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.oa.OrganisationalUnit#getOrganisationalUnitCompositions <em>Organisational Unit Compositions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Organisational Unit Compositions</em>'.
   * @see org.polarsys.capella.core.data.oa.OrganisationalUnit#getOrganisationalUnitCompositions()
   * @see #getOrganisationalUnit()
   * @generated
   */
	EReference getOrganisationalUnit_OrganisationalUnitCompositions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.OrganisationalUnit#getCommunityOfInterestMemberships <em>Community Of Interest Memberships</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Community Of Interest Memberships</em>'.
   * @see org.polarsys.capella.core.data.oa.OrganisationalUnit#getCommunityOfInterestMemberships()
   * @see #getOrganisationalUnit()
   * @generated
   */
	EReference getOrganisationalUnit_CommunityOfInterestMemberships();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.OrganisationalUnitComposition <em>Organisational Unit Composition</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Organisational Unit Composition</em>'.
   * @see org.polarsys.capella.core.data.oa.OrganisationalUnitComposition
   * @generated
   */
	EClass getOrganisationalUnitComposition();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.OrganisationalUnitComposition#getOrganisationalUnit <em>Organisational Unit</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Organisational Unit</em>'.
   * @see org.polarsys.capella.core.data.oa.OrganisationalUnitComposition#getOrganisationalUnit()
   * @see #getOrganisationalUnitComposition()
   * @generated
   */
	EReference getOrganisationalUnitComposition_OrganisationalUnit();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.OrganisationalUnitComposition#getParticipatingEntity <em>Participating Entity</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Participating Entity</em>'.
   * @see org.polarsys.capella.core.data.oa.OrganisationalUnitComposition#getParticipatingEntity()
   * @see #getOrganisationalUnitComposition()
   * @generated
   */
	EReference getOrganisationalUnitComposition_ParticipatingEntity();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.Location <em>Location</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Location</em>'.
   * @see org.polarsys.capella.core.data.oa.Location
   * @generated
   */
	EClass getLocation();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.oa.Location#getLocationDescription <em>Location Description</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Location Description</em>'.
   * @see org.polarsys.capella.core.data.oa.Location#getLocationDescription()
   * @see #getLocation()
   * @generated
   */
	EAttribute getLocation_LocationDescription();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.oa.Location#getLocatedEntities <em>Located Entities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Located Entities</em>'.
   * @see org.polarsys.capella.core.data.oa.Location#getLocatedEntities()
   * @see #getLocation()
   * @generated
   */
	EReference getLocation_LocatedEntities();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.CapabilityConfiguration <em>Capability Configuration</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Capability Configuration</em>'.
   * @see org.polarsys.capella.core.data.oa.CapabilityConfiguration
   * @generated
   */
	EClass getCapabilityConfiguration();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.CapabilityConfiguration#getConfiguredCapability <em>Configured Capability</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Configured Capability</em>'.
   * @see org.polarsys.capella.core.data.oa.CapabilityConfiguration#getConfiguredCapability()
   * @see #getCapabilityConfiguration()
   * @generated
   */
	EReference getCapabilityConfiguration_ConfiguredCapability();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.CommunicationMean <em>Communication Mean</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Communication Mean</em>'.
   * @see org.polarsys.capella.core.data.oa.CommunicationMean
   * @generated
   */
	EClass getCommunicationMean();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.CommunicationMean#getSourceEntity <em>Source Entity</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source Entity</em>'.
   * @see org.polarsys.capella.core.data.oa.CommunicationMean#getSourceEntity()
   * @see #getCommunicationMean()
   * @generated
   */
	EReference getCommunicationMean_SourceEntity();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.CommunicationMean#getTargetEntity <em>Target Entity</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target Entity</em>'.
   * @see org.polarsys.capella.core.data.oa.CommunicationMean#getTargetEntity()
   * @see #getCommunicationMean()
   * @generated
   */
	EReference getCommunicationMean_TargetEntity();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement <em>Entity Operational Capability Involvement</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Operational Capability Involvement</em>'.
   * @see org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement
   * @generated
   */
	EClass getEntityOperationalCapabilityInvolvement();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement#getEntity <em>Entity</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Entity</em>'.
   * @see org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement#getEntity()
   * @see #getEntityOperationalCapabilityInvolvement()
   * @generated
   */
	EReference getEntityOperationalCapabilityInvolvement_Entity();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement#getCapability <em>Capability</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Capability</em>'.
   * @see org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement#getCapability()
   * @see #getEntityOperationalCapabilityInvolvement()
   * @generated
   */
	EReference getEntityOperationalCapabilityInvolvement_Capability();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	OaFactory getOaFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.OperationalAnalysisImpl <em>Operational Analysis</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.OperationalAnalysisImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOperationalAnalysis()
     * @generated
     */
		EClass OPERATIONAL_ANALYSIS = eINSTANCE.getOperationalAnalysis();

		/**
     * The meta object literal for the '<em><b>Owned Role Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG = eINSTANCE.getOperationalAnalysis_OwnedRolePkg();

		/**
     * The meta object literal for the '<em><b>Owned Entity Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG = eINSTANCE.getOperationalAnalysis_OwnedEntityPkg();

		/**
     * The meta object literal for the '<em><b>Owned Concept Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG = eINSTANCE.getOperationalAnalysis_OwnedConceptPkg();

		/**
     * The meta object literal for the '<em><b>Contained Operational Capability Pkg</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_CAPABILITY_PKG = eINSTANCE.getOperationalAnalysis_ContainedOperationalCapabilityPkg();

		/**
     * The meta object literal for the '<em><b>Contained Operational Activity Pkg</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_ACTIVITY_PKG = eINSTANCE.getOperationalAnalysis_ContainedOperationalActivityPkg();

		/**
     * The meta object literal for the '<em><b>Allocating System Analyses</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ANALYSIS__ALLOCATING_SYSTEM_ANALYSES = eINSTANCE.getOperationalAnalysis_AllocatingSystemAnalyses();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.OperationalScenarioImpl <em>Operational Scenario</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.OperationalScenarioImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOperationalScenario()
     * @generated
     */
		EClass OPERATIONAL_SCENARIO = eINSTANCE.getOperationalScenario();

		/**
     * The meta object literal for the '<em><b>Context</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute OPERATIONAL_SCENARIO__CONTEXT = eINSTANCE.getOperationalScenario_Context();

		/**
     * The meta object literal for the '<em><b>Objective</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute OPERATIONAL_SCENARIO__OBJECTIVE = eINSTANCE.getOperationalScenario_Objective();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.OperationalActivityPkgImpl <em>Operational Activity Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.OperationalActivityPkgImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOperationalActivityPkg()
     * @generated
     */
		EClass OPERATIONAL_ACTIVITY_PKG = eINSTANCE.getOperationalActivityPkg();

		/**
     * The meta object literal for the '<em><b>Owned Operational Activities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES = eINSTANCE.getOperationalActivityPkg_OwnedOperationalActivities();

		/**
     * The meta object literal for the '<em><b>Owned Operational Activity Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITY_PKGS = eINSTANCE.getOperationalActivityPkg_OwnedOperationalActivityPkgs();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.OperationalActivityImpl <em>Operational Activity</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.OperationalActivityImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOperationalActivity()
     * @generated
     */
		EClass OPERATIONAL_ACTIVITY = eINSTANCE.getOperationalActivity();

		/**
     * The meta object literal for the '<em><b>Owned Operational Activity Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ACTIVITY__OWNED_OPERATIONAL_ACTIVITY_PKGS = eINSTANCE.getOperationalActivity_OwnedOperationalActivityPkgs();

		/**
     * The meta object literal for the '<em><b>Activity Allocations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ACTIVITY__ACTIVITY_ALLOCATIONS = eINSTANCE.getOperationalActivity_ActivityAllocations();

		/**
     * The meta object literal for the '<em><b>Owned Swimlanes</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ACTIVITY__OWNED_SWIMLANES = eINSTANCE.getOperationalActivity_OwnedSwimlanes();

		/**
     * The meta object literal for the '<em><b>Owned Process</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ACTIVITY__OWNED_PROCESS = eINSTANCE.getOperationalActivity_OwnedProcess();

		/**
     * The meta object literal for the '<em><b>Allocator Entities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ACTIVITY__ALLOCATOR_ENTITIES = eINSTANCE.getOperationalActivity_AllocatorEntities();

		/**
     * The meta object literal for the '<em><b>Realizing System Functions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ACTIVITY__REALIZING_SYSTEM_FUNCTIONS = eINSTANCE.getOperationalActivity_RealizingSystemFunctions();

		/**
     * The meta object literal for the '<em><b>Allocating Roles</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ACTIVITY__ALLOCATING_ROLES = eINSTANCE.getOperationalActivity_AllocatingRoles();

		/**
     * The meta object literal for the '<em><b>Contained Operational Activities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ACTIVITY__CONTAINED_OPERATIONAL_ACTIVITIES = eINSTANCE.getOperationalActivity_ContainedOperationalActivities();

		/**
     * The meta object literal for the '<em><b>Children Operational Activities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_ACTIVITY__CHILDREN_OPERATIONAL_ACTIVITIES = eINSTANCE.getOperationalActivity_ChildrenOperationalActivities();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.OperationalProcessImpl <em>Operational Process</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.OperationalProcessImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOperationalProcess()
     * @generated
     */
		EClass OPERATIONAL_PROCESS = eINSTANCE.getOperationalProcess();

		/**
     * The meta object literal for the '<em><b>Involving Operational Capabilities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_PROCESS__INVOLVING_OPERATIONAL_CAPABILITIES = eINSTANCE.getOperationalProcess_InvolvingOperationalCapabilities();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.SwimlaneImpl <em>Swimlane</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.SwimlaneImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getSwimlane()
     * @generated
     */
		EClass SWIMLANE = eINSTANCE.getSwimlane();

		/**
     * The meta object literal for the '<em><b>Represented Entity</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SWIMLANE__REPRESENTED_ENTITY = eINSTANCE.getSwimlane_RepresentedEntity();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.OperationalCapabilityPkgImpl <em>Operational Capability Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.OperationalCapabilityPkgImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOperationalCapabilityPkg()
     * @generated
     */
		EClass OPERATIONAL_CAPABILITY_PKG = eINSTANCE.getOperationalCapabilityPkg();

		/**
     * The meta object literal for the '<em><b>Owned Operational Capabilities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_CAPABILITY_PKG__OWNED_OPERATIONAL_CAPABILITIES = eINSTANCE.getOperationalCapabilityPkg_OwnedOperationalCapabilities();

		/**
     * The meta object literal for the '<em><b>Owned Operational Capability Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_CAPABILITY_PKG__OWNED_OPERATIONAL_CAPABILITY_PKGS = eINSTANCE.getOperationalCapabilityPkg_OwnedOperationalCapabilityPkgs();

		/**
     * The meta object literal for the '<em><b>Owned Capability Configurations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_CAPABILITY_PKG__OWNED_CAPABILITY_CONFIGURATIONS = eINSTANCE.getOperationalCapabilityPkg_OwnedCapabilityConfigurations();

		/**
     * The meta object literal for the '<em><b>Owned Concept Compliances</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_CAPABILITY_PKG__OWNED_CONCEPT_COMPLIANCES = eINSTANCE.getOperationalCapabilityPkg_OwnedConceptCompliances();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.OperationalCapabilityImpl <em>Operational Capability</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.OperationalCapabilityImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOperationalCapability()
     * @generated
     */
		EClass OPERATIONAL_CAPABILITY = eINSTANCE.getOperationalCapability();

		/**
     * The meta object literal for the '<em><b>Compliances</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_CAPABILITY__COMPLIANCES = eINSTANCE.getOperationalCapability_Compliances();

		/**
     * The meta object literal for the '<em><b>Configurations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_CAPABILITY__CONFIGURATIONS = eINSTANCE.getOperationalCapability_Configurations();

		/**
     * The meta object literal for the '<em><b>Owned Entity Operational Capability Involvements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_CAPABILITY__OWNED_ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENTS = eINSTANCE.getOperationalCapability_OwnedEntityOperationalCapabilityInvolvements();

		/**
     * The meta object literal for the '<em><b>Realizing Capabilities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_CAPABILITY__REALIZING_CAPABILITIES = eINSTANCE.getOperationalCapability_RealizingCapabilities();

		/**
     * The meta object literal for the '<em><b>Involved Entities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference OPERATIONAL_CAPABILITY__INVOLVED_ENTITIES = eINSTANCE.getOperationalCapability_InvolvedEntities();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.ActivityAllocationImpl <em>Activity Allocation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.ActivityAllocationImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getActivityAllocation()
     * @generated
     */
		EClass ACTIVITY_ALLOCATION = eINSTANCE.getActivityAllocation();

		/**
     * The meta object literal for the '<em><b>Role</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ACTIVITY_ALLOCATION__ROLE = eINSTANCE.getActivityAllocation_Role();

		/**
     * The meta object literal for the '<em><b>Activity</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ACTIVITY_ALLOCATION__ACTIVITY = eINSTANCE.getActivityAllocation_Activity();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.RolePkgImpl <em>Role Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.RolePkgImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getRolePkg()
     * @generated
     */
		EClass ROLE_PKG = eINSTANCE.getRolePkg();

		/**
     * The meta object literal for the '<em><b>Owned Role Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ROLE_PKG__OWNED_ROLE_PKGS = eINSTANCE.getRolePkg_OwnedRolePkgs();

		/**
     * The meta object literal for the '<em><b>Owned Roles</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ROLE_PKG__OWNED_ROLES = eINSTANCE.getRolePkg_OwnedRoles();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.RoleImpl <em>Role</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.RoleImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getRole()
     * @generated
     */
		EClass ROLE = eINSTANCE.getRole();

		/**
     * The meta object literal for the '<em><b>Owned Role Assembly Usages</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ROLE__OWNED_ROLE_ASSEMBLY_USAGES = eINSTANCE.getRole_OwnedRoleAssemblyUsages();

		/**
     * The meta object literal for the '<em><b>Owned Activity Allocations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ROLE__OWNED_ACTIVITY_ALLOCATIONS = eINSTANCE.getRole_OwnedActivityAllocations();

		/**
     * The meta object literal for the '<em><b>Role Allocations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ROLE__ROLE_ALLOCATIONS = eINSTANCE.getRole_RoleAllocations();

		/**
     * The meta object literal for the '<em><b>Activity Allocations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ROLE__ACTIVITY_ALLOCATIONS = eINSTANCE.getRole_ActivityAllocations();

		/**
     * The meta object literal for the '<em><b>Allocating Entities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ROLE__ALLOCATING_ENTITIES = eINSTANCE.getRole_AllocatingEntities();

		/**
     * The meta object literal for the '<em><b>Allocated Operational Activities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ROLE__ALLOCATED_OPERATIONAL_ACTIVITIES = eINSTANCE.getRole_AllocatedOperationalActivities();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.RoleAssemblyUsageImpl <em>Role Assembly Usage</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.RoleAssemblyUsageImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getRoleAssemblyUsage()
     * @generated
     */
		EClass ROLE_ASSEMBLY_USAGE = eINSTANCE.getRoleAssemblyUsage();

		/**
     * The meta object literal for the '<em><b>Child</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ROLE_ASSEMBLY_USAGE__CHILD = eINSTANCE.getRoleAssemblyUsage_Child();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.RoleAllocationImpl <em>Role Allocation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.RoleAllocationImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getRoleAllocation()
     * @generated
     */
		EClass ROLE_ALLOCATION = eINSTANCE.getRoleAllocation();

		/**
     * The meta object literal for the '<em><b>Role</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ROLE_ALLOCATION__ROLE = eINSTANCE.getRoleAllocation_Role();

		/**
     * The meta object literal for the '<em><b>Entity</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ROLE_ALLOCATION__ENTITY = eINSTANCE.getRoleAllocation_Entity();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.EntityPkgImpl <em>Entity Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.EntityPkgImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getEntityPkg()
     * @generated
     */
		EClass ENTITY_PKG = eINSTANCE.getEntityPkg();

		/**
     * The meta object literal for the '<em><b>Owned Entity Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY_PKG__OWNED_ENTITY_PKGS = eINSTANCE.getEntityPkg_OwnedEntityPkgs();

		/**
     * The meta object literal for the '<em><b>Owned Entities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY_PKG__OWNED_ENTITIES = eINSTANCE.getEntityPkg_OwnedEntities();

		/**
     * The meta object literal for the '<em><b>Owned Locations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY_PKG__OWNED_LOCATIONS = eINSTANCE.getEntityPkg_OwnedLocations();

		/**
     * The meta object literal for the '<em><b>Owned Communication Means</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY_PKG__OWNED_COMMUNICATION_MEANS = eINSTANCE.getEntityPkg_OwnedCommunicationMeans();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.EntityImpl <em>Entity</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.EntityImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getEntity()
     * @generated
     */
		EClass ENTITY = eINSTANCE.getEntity();

		/**
     * The meta object literal for the '<em><b>Role Allocations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY__ROLE_ALLOCATIONS = eINSTANCE.getEntity_RoleAllocations();

		/**
     * The meta object literal for the '<em><b>Organisational Unit Memberships</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY__ORGANISATIONAL_UNIT_MEMBERSHIPS = eINSTANCE.getEntity_OrganisationalUnitMemberships();

		/**
     * The meta object literal for the '<em><b>Actual Location</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY__ACTUAL_LOCATION = eINSTANCE.getEntity_ActualLocation();

		/**
     * The meta object literal for the '<em><b>Sub Entities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY__SUB_ENTITIES = eINSTANCE.getEntity_SubEntities();

		/**
     * The meta object literal for the '<em><b>Owned Entities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY__OWNED_ENTITIES = eINSTANCE.getEntity_OwnedEntities();

		/**
     * The meta object literal for the '<em><b>Owned Communication Means</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY__OWNED_COMMUNICATION_MEANS = eINSTANCE.getEntity_OwnedCommunicationMeans();

		/**
     * The meta object literal for the '<em><b>Owned Role Allocations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY__OWNED_ROLE_ALLOCATIONS = eINSTANCE.getEntity_OwnedRoleAllocations();

		/**
     * The meta object literal for the '<em><b>Allocated Operational Activities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY__ALLOCATED_OPERATIONAL_ACTIVITIES = eINSTANCE.getEntity_AllocatedOperationalActivities();

		/**
     * The meta object literal for the '<em><b>Allocated Roles</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY__ALLOCATED_ROLES = eINSTANCE.getEntity_AllocatedRoles();

		/**
     * The meta object literal for the '<em><b>Involving Operational Capabilities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY__INVOLVING_OPERATIONAL_CAPABILITIES = eINSTANCE.getEntity_InvolvingOperationalCapabilities();

		/**
     * The meta object literal for the '<em><b>Realizing System Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY__REALIZING_SYSTEM_COMPONENTS = eINSTANCE.getEntity_RealizingSystemComponents();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.ConceptPkgImpl <em>Concept Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.ConceptPkgImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getConceptPkg()
     * @generated
     */
		EClass CONCEPT_PKG = eINSTANCE.getConceptPkg();

		/**
     * The meta object literal for the '<em><b>Owned Concept Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONCEPT_PKG__OWNED_CONCEPT_PKGS = eINSTANCE.getConceptPkg_OwnedConceptPkgs();

		/**
     * The meta object literal for the '<em><b>Owned Concepts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONCEPT_PKG__OWNED_CONCEPTS = eINSTANCE.getConceptPkg_OwnedConcepts();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.ConceptImpl <em>Concept</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.ConceptImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getConcept()
     * @generated
     */
		EClass CONCEPT = eINSTANCE.getConcept();

		/**
     * The meta object literal for the '<em><b>Compliances</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONCEPT__COMPLIANCES = eINSTANCE.getConcept_Compliances();

		/**
     * The meta object literal for the '<em><b>Composite Links</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONCEPT__COMPOSITE_LINKS = eINSTANCE.getConcept_CompositeLinks();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.ConceptComplianceImpl <em>Concept Compliance</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.ConceptComplianceImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getConceptCompliance()
     * @generated
     */
		EClass CONCEPT_COMPLIANCE = eINSTANCE.getConceptCompliance();

		/**
     * The meta object literal for the '<em><b>Comply With Concept</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONCEPT_COMPLIANCE__COMPLY_WITH_CONCEPT = eINSTANCE.getConceptCompliance_ComplyWithConcept();

		/**
     * The meta object literal for the '<em><b>Compliant Capability</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONCEPT_COMPLIANCE__COMPLIANT_CAPABILITY = eINSTANCE.getConceptCompliance_CompliantCapability();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.ItemInConceptImpl <em>Item In Concept</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.ItemInConceptImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getItemInConcept()
     * @generated
     */
		EClass ITEM_IN_CONCEPT = eINSTANCE.getItemInConcept();

		/**
     * The meta object literal for the '<em><b>Concept</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ITEM_IN_CONCEPT__CONCEPT = eINSTANCE.getItemInConcept_Concept();

		/**
     * The meta object literal for the '<em><b>Item</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ITEM_IN_CONCEPT__ITEM = eINSTANCE.getItemInConcept_Item();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.AbstractConceptItemImpl <em>Abstract Concept Item</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.AbstractConceptItemImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getAbstractConceptItem()
     * @generated
     */
		EClass ABSTRACT_CONCEPT_ITEM = eINSTANCE.getAbstractConceptItem();

		/**
     * The meta object literal for the '<em><b>Composing Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_CONCEPT_ITEM__COMPOSING_LINKS = eINSTANCE.getAbstractConceptItem_ComposingLinks();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.CommunityOfInterestImpl <em>Community Of Interest</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.CommunityOfInterestImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getCommunityOfInterest()
     * @generated
     */
		EClass COMMUNITY_OF_INTEREST = eINSTANCE.getCommunityOfInterest();

		/**
     * The meta object literal for the '<em><b>Community Of Interest Compositions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNITY_OF_INTEREST__COMMUNITY_OF_INTEREST_COMPOSITIONS = eINSTANCE.getCommunityOfInterest_CommunityOfInterestCompositions();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.CommunityOfInterestCompositionImpl <em>Community Of Interest Composition</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.CommunityOfInterestCompositionImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getCommunityOfInterestComposition()
     * @generated
     */
		EClass COMMUNITY_OF_INTEREST_COMPOSITION = eINSTANCE.getCommunityOfInterestComposition();

		/**
     * The meta object literal for the '<em><b>Community Of Interest</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNITY_OF_INTEREST_COMPOSITION__COMMUNITY_OF_INTEREST = eINSTANCE.getCommunityOfInterestComposition_CommunityOfInterest();

		/**
     * The meta object literal for the '<em><b>Interested Organisation Unit</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNITY_OF_INTEREST_COMPOSITION__INTERESTED_ORGANISATION_UNIT = eINSTANCE.getCommunityOfInterestComposition_InterestedOrganisationUnit();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.OrganisationalUnitImpl <em>Organisational Unit</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.OrganisationalUnitImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOrganisationalUnit()
     * @generated
     */
		EClass ORGANISATIONAL_UNIT = eINSTANCE.getOrganisationalUnit();

		/**
     * The meta object literal for the '<em><b>Organisational Unit Compositions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ORGANISATIONAL_UNIT__ORGANISATIONAL_UNIT_COMPOSITIONS = eINSTANCE.getOrganisationalUnit_OrganisationalUnitCompositions();

		/**
     * The meta object literal for the '<em><b>Community Of Interest Memberships</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ORGANISATIONAL_UNIT__COMMUNITY_OF_INTEREST_MEMBERSHIPS = eINSTANCE.getOrganisationalUnit_CommunityOfInterestMemberships();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.OrganisationalUnitCompositionImpl <em>Organisational Unit Composition</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.OrganisationalUnitCompositionImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getOrganisationalUnitComposition()
     * @generated
     */
		EClass ORGANISATIONAL_UNIT_COMPOSITION = eINSTANCE.getOrganisationalUnitComposition();

		/**
     * The meta object literal for the '<em><b>Organisational Unit</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ORGANISATIONAL_UNIT_COMPOSITION__ORGANISATIONAL_UNIT = eINSTANCE.getOrganisationalUnitComposition_OrganisationalUnit();

		/**
     * The meta object literal for the '<em><b>Participating Entity</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ORGANISATIONAL_UNIT_COMPOSITION__PARTICIPATING_ENTITY = eINSTANCE.getOrganisationalUnitComposition_ParticipatingEntity();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.LocationImpl <em>Location</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.LocationImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getLocation()
     * @generated
     */
		EClass LOCATION = eINSTANCE.getLocation();

		/**
     * The meta object literal for the '<em><b>Location Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute LOCATION__LOCATION_DESCRIPTION = eINSTANCE.getLocation_LocationDescription();

		/**
     * The meta object literal for the '<em><b>Located Entities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOCATION__LOCATED_ENTITIES = eINSTANCE.getLocation_LocatedEntities();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.CapabilityConfigurationImpl <em>Capability Configuration</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.CapabilityConfigurationImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getCapabilityConfiguration()
     * @generated
     */
		EClass CAPABILITY_CONFIGURATION = eINSTANCE.getCapabilityConfiguration();

		/**
     * The meta object literal for the '<em><b>Configured Capability</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPABILITY_CONFIGURATION__CONFIGURED_CAPABILITY = eINSTANCE.getCapabilityConfiguration_ConfiguredCapability();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.CommunicationMeanImpl <em>Communication Mean</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.CommunicationMeanImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getCommunicationMean()
     * @generated
     */
		EClass COMMUNICATION_MEAN = eINSTANCE.getCommunicationMean();

		/**
     * The meta object literal for the '<em><b>Source Entity</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_MEAN__SOURCE_ENTITY = eINSTANCE.getCommunicationMean_SourceEntity();

		/**
     * The meta object literal for the '<em><b>Target Entity</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMMUNICATION_MEAN__TARGET_ENTITY = eINSTANCE.getCommunicationMean_TargetEntity();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.oa.impl.EntityOperationalCapabilityInvolvementImpl <em>Entity Operational Capability Involvement</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.oa.impl.EntityOperationalCapabilityInvolvementImpl
     * @see org.polarsys.capella.core.data.oa.impl.OaPackageImpl#getEntityOperationalCapabilityInvolvement()
     * @generated
     */
		EClass ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT = eINSTANCE.getEntityOperationalCapabilityInvolvement();

		/**
     * The meta object literal for the '<em><b>Entity</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__ENTITY = eINSTANCE.getEntityOperationalCapabilityInvolvement_Entity();

		/**
     * The meta object literal for the '<em><b>Capability</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT__CAPABILITY = eINSTANCE.getEntityOperationalCapabilityInvolvement_Capability();

	}

} //OaPackage
