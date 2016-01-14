/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.ctx;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
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
 * @see org.polarsys.capella.core.data.ctx.CtxFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/dsl/2007/dslfactory trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='SystemAnalysis aims at defining the system context analysis modelling language. It is named ContextArchitecture due to MDSysE naming inheritance.\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='system' usage\040examples='none' constraints='This package depends on the model CompositeStructure.ecore\r\nThis package depends on the model Interaction.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 * @generated
 */
public interface CtxPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ctx"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.polarsys.org/capella/core/ctx/1.0.0"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.polarsys.capella.core.data.ctx"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CtxPackage eINSTANCE = org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemAnalysisImpl <em>System Analysis</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.SystemAnalysisImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemAnalysis()
	 * @generated
	 */
	int SYSTEM_ANALYSIS = 0;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_EXTENSIONS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__ID = CsPackage.COMPONENT_ARCHITECTURE__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__SID = CsPackage.COMPONENT_ARCHITECTURE__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__CONSTRAINTS = CsPackage.COMPONENT_ARCHITECTURE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_CONSTRAINTS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__NAME = CsPackage.COMPONENT_ARCHITECTURE__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__INCOMING_TRACES = CsPackage.COMPONENT_ARCHITECTURE__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OUTGOING_TRACES = CsPackage.COMPONENT_ARCHITECTURE__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__VISIBLE_IN_DOC = CsPackage.COMPONENT_ARCHITECTURE__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__VISIBLE_IN_LM = CsPackage.COMPONENT_ARCHITECTURE__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__SUMMARY = CsPackage.COMPONENT_ARCHITECTURE__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__DESCRIPTION = CsPackage.COMPONENT_ARCHITECTURE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__REVIEW = CsPackage.COMPONENT_ARCHITECTURE__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_ARCHITECTURE__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ARCHITECTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__STATUS = CsPackage.COMPONENT_ARCHITECTURE__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__FEATURES = CsPackage.COMPONENT_ARCHITECTURE__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__APPLIED_REQUIREMENTS = CsPackage.COMPONENT_ARCHITECTURE__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_TRACES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__CONTAINED_GENERIC_TRACES = CsPackage.COMPONENT_ARCHITECTURE__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__CONTAINED_REQUIREMENTS_TRACES = CsPackage.COMPONENT_ARCHITECTURE__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__NAMING_RULES = CsPackage.COMPONENT_ARCHITECTURE__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_PROPERTY_VALUE_PKGS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
	 * The feature id for the '<em><b>Owned Function Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_FUNCTION_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_FUNCTION_PKG;

	/**
	 * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_COMPONENT_EXCHANGES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_COMPONENT_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_FUNCTIONAL_LINKS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_FUNCTIONAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_FUNCTIONAL_ALLOCATIONS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Owned Requirement Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_REQUIREMENT_PKGS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_REQUIREMENT_PKGS;

	/**
	 * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_ABSTRACT_CAPABILITY_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
	 * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_INTERFACE_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_INTERFACE_PKG;

	/**
	 * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_DATA_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_DATA_PKG;

	/**
	 * The feature id for the '<em><b>Provisioned Architecture Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__PROVISIONED_ARCHITECTURE_ALLOCATIONS = CsPackage.COMPONENT_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioning Architecture Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__PROVISIONING_ARCHITECTURE_ALLOCATIONS = CsPackage.COMPONENT_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Architectures</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__ALLOCATED_ARCHITECTURES = CsPackage.COMPONENT_ARCHITECTURE__ALLOCATED_ARCHITECTURES;

	/**
	 * The feature id for the '<em><b>Allocating Architectures</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__ALLOCATING_ARCHITECTURES = CsPackage.COMPONENT_ARCHITECTURE__ALLOCATING_ARCHITECTURES;

	/**
	 * The feature id for the '<em><b>Owned System Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned System</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_SYSTEM = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Owned Actor Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_ACTOR_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Owned Mission Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_MISSION_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Contained Capability Pkg</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__CONTAINED_CAPABILITY_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Contained System Function Pkg</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__CONTAINED_SYSTEM_FUNCTION_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Owned Operational Analysis Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Allocated Operational Analysis Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSIS_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Allocated Operational Analyses</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSES = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Allocating Logical Architectures</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS__ALLOCATING_LOGICAL_ARCHITECTURES = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>System Analysis</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_ANALYSIS_FEATURE_COUNT = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemImpl <em>System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.SystemImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystem()
	 * @generated
	 */
	int SYSTEM = 1;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_EXTENSIONS = CsPackage.COMPONENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__ID = CsPackage.COMPONENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__SID = CsPackage.COMPONENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__CONSTRAINTS = CsPackage.COMPONENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_CONSTRAINTS = CsPackage.COMPONENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__NAME = CsPackage.COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__ABSTRACT_TYPED_ELEMENTS = CsPackage.COMPONENT__ABSTRACT_TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__INCOMING_TRACES = CsPackage.COMPONENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OUTGOING_TRACES = CsPackage.COMPONENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__VISIBLE_IN_DOC = CsPackage.COMPONENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__VISIBLE_IN_LM = CsPackage.COMPONENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__SUMMARY = CsPackage.COMPONENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__DESCRIPTION = CsPackage.COMPONENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__REVIEW = CsPackage.COMPONENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__STATUS = CsPackage.COMPONENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__FEATURES = CsPackage.COMPONENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__APPLIED_REQUIREMENTS = CsPackage.COMPONENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_TRACES = CsPackage.COMPONENT__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__CONTAINED_GENERIC_TRACES = CsPackage.COMPONENT__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__CONTAINED_REQUIREMENTS_TRACES = CsPackage.COMPONENT__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__NAMING_RULES = CsPackage.COMPONENT__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__TYPED_ELEMENTS = CsPackage.COMPONENT__TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_FUNCTIONAL_ALLOCATION = CsPackage.COMPONENT__OWNED_FUNCTIONAL_ALLOCATION;

	/**
	 * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_COMPONENT_EXCHANGES = CsPackage.COMPONENT__OWNED_COMPONENT_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__FUNCTIONAL_ALLOCATIONS = CsPackage.COMPONENT__FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__ALLOCATED_FUNCTIONS = CsPackage.COMPONENT__ALLOCATED_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__IN_EXCHANGE_LINKS = CsPackage.COMPONENT__IN_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OUT_EXCHANGE_LINKS = CsPackage.COMPONENT__OUT_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_ABSTRACT_CAPABILITY_PKG = CsPackage.COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
	 * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_INTERFACE_PKG = CsPackage.COMPONENT__OWNED_INTERFACE_PKG;

	/**
	 * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_DATA_PKG = CsPackage.COMPONENT__OWNED_DATA_PKG;

	/**
	 * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_STATE_MACHINES = CsPackage.COMPONENT__OWNED_STATE_MACHINES;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__ABSTRACT = CsPackage.COMPONENT__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_GENERALIZATIONS = CsPackage.COMPONENT__OWNED_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__SUPER_GENERALIZATIONS = CsPackage.COMPONENT__SUPER_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__SUB_GENERALIZATIONS = CsPackage.COMPONENT__SUB_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__SUPER = CsPackage.COMPONENT__SUPER;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__SUB = CsPackage.COMPONENT__SUB;

	/**
	 * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_FEATURES = CsPackage.COMPONENT__OWNED_FEATURES;

	/**
	 * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__CONTAINED_PROPERTIES = CsPackage.COMPONENT__CONTAINED_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Owned Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_PARTITIONS = CsPackage.COMPONENT__OWNED_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Representing Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__REPRESENTING_PARTITIONS = CsPackage.COMPONENT__REPRESENTING_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_INTERFACE_ALLOCATIONS = CsPackage.COMPONENT__OWNED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__PROVISIONED_INTERFACE_ALLOCATIONS = CsPackage.COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__ALLOCATED_INTERFACES = CsPackage.COMPONENT__ALLOCATED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_COMMUNICATION_LINKS = CsPackage.COMPONENT__OWNED_COMMUNICATION_LINKS;

	/**
	 * The feature id for the '<em><b>Produce</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__PRODUCE = CsPackage.COMPONENT__PRODUCE;

	/**
	 * The feature id for the '<em><b>Consume</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__CONSUME = CsPackage.COMPONENT__CONSUME;

	/**
	 * The feature id for the '<em><b>Send</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__SEND = CsPackage.COMPONENT__SEND;

	/**
	 * The feature id for the '<em><b>Receive</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__RECEIVE = CsPackage.COMPONENT__RECEIVE;

	/**
	 * The feature id for the '<em><b>Call</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__CALL = CsPackage.COMPONENT__CALL;

	/**
	 * The feature id for the '<em><b>Execute</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__EXECUTE = CsPackage.COMPONENT__EXECUTE;

	/**
	 * The feature id for the '<em><b>Write</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__WRITE = CsPackage.COMPONENT__WRITE;

	/**
	 * The feature id for the '<em><b>Access</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__ACCESS = CsPackage.COMPONENT__ACCESS;

	/**
	 * The feature id for the '<em><b>Acquire</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__ACQUIRE = CsPackage.COMPONENT__ACQUIRE;

	/**
	 * The feature id for the '<em><b>Transmit</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__TRANSMIT = CsPackage.COMPONENT__TRANSMIT;

	/**
	 * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_INTERFACE_USES = CsPackage.COMPONENT__OWNED_INTERFACE_USES;

	/**
	 * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__USED_INTERFACE_LINKS = CsPackage.COMPONENT__USED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__USED_INTERFACES = CsPackage.COMPONENT__USED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_INTERFACE_IMPLEMENTATIONS = CsPackage.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
	 * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__IMPLEMENTED_INTERFACE_LINKS = CsPackage.COMPONENT__IMPLEMENTED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__IMPLEMENTED_INTERFACES = CsPackage.COMPONENT__IMPLEMENTED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Provisioned Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__PROVISIONED_COMPONENT_ALLOCATIONS = CsPackage.COMPONENT__PROVISIONED_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioning Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__PROVISIONING_COMPONENT_ALLOCATIONS = CsPackage.COMPONENT__PROVISIONING_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__ALLOCATED_COMPONENTS = CsPackage.COMPONENT__ALLOCATED_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Allocating Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__ALLOCATING_COMPONENTS = CsPackage.COMPONENT__ALLOCATING_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__PROVIDED_INTERFACES = CsPackage.COMPONENT__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__REQUIRED_INTERFACES = CsPackage.COMPONENT__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__CONTAINED_COMPONENT_PORTS = CsPackage.COMPONENT__CONTAINED_COMPONENT_PORTS;

	/**
	 * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__CONTAINED_PARTS = CsPackage.COMPONENT__CONTAINED_PARTS;

	/**
	 * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__CONTAINED_PHYSICAL_PORTS = CsPackage.COMPONENT__CONTAINED_PHYSICAL_PORTS;

	/**
	 * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_PHYSICAL_PATH = CsPackage.COMPONENT__OWNED_PHYSICAL_PATH;

	/**
	 * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_PHYSICAL_LINKS = CsPackage.COMPONENT__OWNED_PHYSICAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_PHYSICAL_LINK_CATEGORIES = CsPackage.COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__INVOLVING_INVOLVEMENTS = CsPackage.COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Involving Capability Realization Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS = CsPackage.COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Contributed Capabilities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__CONTRIBUTED_CAPABILITIES = CsPackage.COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Participations In Capabilities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__PARTICIPATIONS_IN_CAPABILITIES = CsPackage.COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Contributed Missions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__CONTRIBUTED_MISSIONS = CsPackage.COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Participations In Missions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__PARTICIPATIONS_IN_MISSIONS = CsPackage.COMPONENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>External Communication</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__EXTERNAL_COMMUNICATION = CsPackage.COMPONENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Owned Entity Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_ENTITY_REALIZATIONS = CsPackage.COMPONENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Allocated Entity Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__ALLOCATED_ENTITY_REALIZATIONS = CsPackage.COMPONENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Allocated System Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__ALLOCATED_SYSTEM_FUNCTIONS = CsPackage.COMPONENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Realized Entities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__REALIZED_ENTITIES = CsPackage.COMPONENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Realizing Logical Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__REALIZING_LOGICAL_COMPONENTS = CsPackage.COMPONENT_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FEATURE_COUNT = CsPackage.COMPONENT_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemFunctionImpl <em>System Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.SystemFunctionImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemFunction()
	 * @generated
	 */
	int SYSTEM_FUNCTION = 2;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_EXTENSIONS = FaPackage.ABSTRACT_FUNCTION__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__ID = FaPackage.ABSTRACT_FUNCTION__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__SID = FaPackage.ABSTRACT_FUNCTION__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__CONSTRAINTS = FaPackage.ABSTRACT_FUNCTION__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_CONSTRAINTS = FaPackage.ABSTRACT_FUNCTION__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__NAME = FaPackage.ABSTRACT_FUNCTION__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__INCOMING_TRACES = FaPackage.ABSTRACT_FUNCTION__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OUTGOING_TRACES = FaPackage.ABSTRACT_FUNCTION__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__VISIBLE_IN_DOC = FaPackage.ABSTRACT_FUNCTION__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__VISIBLE_IN_LM = FaPackage.ABSTRACT_FUNCTION__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__SUMMARY = FaPackage.ABSTRACT_FUNCTION__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__DESCRIPTION = FaPackage.ABSTRACT_FUNCTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__REVIEW = FaPackage.ABSTRACT_FUNCTION__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_PROPERTY_VALUES = FaPackage.ABSTRACT_FUNCTION__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_ENUMERATION_PROPERTY_TYPES = FaPackage.ABSTRACT_FUNCTION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__APPLIED_PROPERTY_VALUES = FaPackage.ABSTRACT_FUNCTION__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_PROPERTY_VALUE_GROUPS = FaPackage.ABSTRACT_FUNCTION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__APPLIED_PROPERTY_VALUE_GROUPS = FaPackage.ABSTRACT_FUNCTION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__STATUS = FaPackage.ABSTRACT_FUNCTION__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__FEATURES = FaPackage.ABSTRACT_FUNCTION__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__APPLIED_REQUIREMENTS = FaPackage.ABSTRACT_FUNCTION__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_TRACES = FaPackage.ABSTRACT_FUNCTION__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__CONTAINED_GENERIC_TRACES = FaPackage.ABSTRACT_FUNCTION__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__CONTAINED_REQUIREMENTS_TRACES = FaPackage.ABSTRACT_FUNCTION__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__NAMING_RULES = FaPackage.ABSTRACT_FUNCTION__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__INVOLVING_INVOLVEMENTS = FaPackage.ABSTRACT_FUNCTION__INVOLVING_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__IS_ABSTRACT = FaPackage.ABSTRACT_FUNCTION__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Is Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__IS_STATIC = FaPackage.ABSTRACT_FUNCTION__IS_STATIC;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__VISIBILITY = FaPackage.ABSTRACT_FUNCTION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Abstract Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__ABSTRACT_TYPE = FaPackage.ABSTRACT_FUNCTION__ABSTRACT_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__TYPE = FaPackage.ABSTRACT_FUNCTION__TYPE;

	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__ORDERED = FaPackage.ABSTRACT_FUNCTION__ORDERED;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__UNIQUE = FaPackage.ABSTRACT_FUNCTION__UNIQUE;

	/**
	 * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__MIN_INCLUSIVE = FaPackage.ABSTRACT_FUNCTION__MIN_INCLUSIVE;

	/**
	 * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__MAX_INCLUSIVE = FaPackage.ABSTRACT_FUNCTION__MAX_INCLUSIVE;

	/**
	 * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_DEFAULT_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_MIN_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_MIN_VALUE;

	/**
	 * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_MAX_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_MAX_VALUE;

	/**
	 * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_NULL_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_NULL_VALUE;

	/**
	 * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_MIN_CARD = FaPackage.ABSTRACT_FUNCTION__OWNED_MIN_CARD;

	/**
	 * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_MIN_LENGTH = FaPackage.ABSTRACT_FUNCTION__OWNED_MIN_LENGTH;

	/**
	 * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_MAX_CARD = FaPackage.ABSTRACT_FUNCTION__OWNED_MAX_CARD;

	/**
	 * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_MAX_LENGTH = FaPackage.ABSTRACT_FUNCTION__OWNED_MAX_LENGTH;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__FINAL = FaPackage.ABSTRACT_FUNCTION__FINAL;

	/**
	 * The feature id for the '<em><b>Aggregation Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__AGGREGATION_KIND = FaPackage.ABSTRACT_FUNCTION__AGGREGATION_KIND;

	/**
	 * The feature id for the '<em><b>Is Derived</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__IS_DERIVED = FaPackage.ABSTRACT_FUNCTION__IS_DERIVED;

	/**
	 * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__IS_READ_ONLY = FaPackage.ABSTRACT_FUNCTION__IS_READ_ONLY;

	/**
	 * The feature id for the '<em><b>Is Part Of Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__IS_PART_OF_KEY = FaPackage.ABSTRACT_FUNCTION__IS_PART_OF_KEY;

	/**
	 * The feature id for the '<em><b>Association</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__ASSOCIATION = FaPackage.ABSTRACT_FUNCTION__ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Representing Instance Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__REPRESENTING_INSTANCE_ROLES = FaPackage.ABSTRACT_FUNCTION__REPRESENTING_INSTANCE_ROLES;

	/**
	 * The feature id for the '<em><b>Owned Functional Chains</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_FUNCTIONAL_CHAINS = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_CHAINS;

	/**
	 * The feature id for the '<em><b>In Activity Partition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__IN_ACTIVITY_PARTITION = FaPackage.ABSTRACT_FUNCTION__IN_ACTIVITY_PARTITION;

	/**
	 * The feature id for the '<em><b>In Interruptible Region</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__IN_INTERRUPTIBLE_REGION = FaPackage.ABSTRACT_FUNCTION__IN_INTERRUPTIBLE_REGION;

	/**
	 * The feature id for the '<em><b>In Structured Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__IN_STRUCTURED_NODE = FaPackage.ABSTRACT_FUNCTION__IN_STRUCTURED_NODE;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OUTGOING = FaPackage.ABSTRACT_FUNCTION__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__INCOMING = FaPackage.ABSTRACT_FUNCTION__INCOMING;

	/**
	 * The feature id for the '<em><b>Owned Handlers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_HANDLERS = FaPackage.ABSTRACT_FUNCTION__OWNED_HANDLERS;

	/**
	 * The feature id for the '<em><b>Local Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__LOCAL_PRECONDITION = FaPackage.ABSTRACT_FUNCTION__LOCAL_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Local Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__LOCAL_POSTCONDITION = FaPackage.ABSTRACT_FUNCTION__LOCAL_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__CONTEXT = FaPackage.ABSTRACT_FUNCTION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__INPUTS = FaPackage.ABSTRACT_FUNCTION__INPUTS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OUTPUTS = FaPackage.ABSTRACT_FUNCTION__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__ARGUMENTS = FaPackage.ABSTRACT_FUNCTION__ARGUMENTS;

	/**
	 * The feature id for the '<em><b>Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__RESULTS = FaPackage.ABSTRACT_FUNCTION__RESULTS;

	/**
	 * The feature id for the '<em><b>Behavior</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__BEHAVIOR = FaPackage.ABSTRACT_FUNCTION__BEHAVIOR;

	/**
	 * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__ABSTRACT_TYPED_ELEMENTS = FaPackage.ABSTRACT_FUNCTION__ABSTRACT_TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__KIND = FaPackage.ABSTRACT_FUNCTION__KIND;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__CONDITION = FaPackage.ABSTRACT_FUNCTION__CONDITION;

	/**
	 * The feature id for the '<em><b>Owned Functions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>Owned Function Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_FUNCTION_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Owned Functional Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Sub Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__SUB_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION__SUB_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>Out Function Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OUT_FUNCTION_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__OUT_FUNCTION_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>In Function Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__IN_FUNCTION_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__IN_FUNCTION_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Component Functional Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__COMPONENT_FUNCTIONAL_ALLOCATIONS = FaPackage.ABSTRACT_FUNCTION__COMPONENT_FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocation Blocks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__ALLOCATION_BLOCKS = FaPackage.ABSTRACT_FUNCTION__ALLOCATION_BLOCKS;

	/**
	 * The feature id for the '<em><b>Available In States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__AVAILABLE_IN_STATES = FaPackage.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES;

	/**
	 * The feature id for the '<em><b>Involving Capabilities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__INVOLVING_CAPABILITIES = FaPackage.ABSTRACT_FUNCTION__INVOLVING_CAPABILITIES;

	/**
	 * The feature id for the '<em><b>Involving Capability Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__INVOLVING_CAPABILITY_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__INVOLVING_CAPABILITY_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Involving Functional Chains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__INVOLVING_FUNCTIONAL_CHAINS = FaPackage.ABSTRACT_FUNCTION__INVOLVING_FUNCTIONAL_CHAINS;

	/**
	 * The feature id for the '<em><b>Linked State Machine</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__LINKED_STATE_MACHINE = FaPackage.ABSTRACT_FUNCTION__LINKED_STATE_MACHINE;

	/**
	 * The feature id for the '<em><b>Linked Function Specification</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__LINKED_FUNCTION_SPECIFICATION = FaPackage.ABSTRACT_FUNCTION__LINKED_FUNCTION_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Owned System Function Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__OWNED_SYSTEM_FUNCTION_PKGS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Allocator Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__ALLOCATOR_ACTORS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Allocator Systems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__ALLOCATOR_SYSTEMS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Realized Operational Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__REALIZED_OPERATIONAL_ACTIVITIES = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Realizing Logical Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__REALIZING_LOGICAL_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Contained System Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__CONTAINED_SYSTEM_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Children System Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION__CHILDREN_SYSTEM_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>System Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_FEATURE_COUNT = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemFunctionPkgImpl <em>System Function Pkg</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.SystemFunctionPkgImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemFunctionPkg()
	 * @generated
	 */
	int SYSTEM_FUNCTION_PKG = 3;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__OWNED_EXTENSIONS = FaPackage.FUNCTION_PKG__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__ID = FaPackage.FUNCTION_PKG__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__SID = FaPackage.FUNCTION_PKG__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__CONSTRAINTS = FaPackage.FUNCTION_PKG__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__OWNED_CONSTRAINTS = FaPackage.FUNCTION_PKG__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__NAME = FaPackage.FUNCTION_PKG__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__INCOMING_TRACES = FaPackage.FUNCTION_PKG__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__OUTGOING_TRACES = FaPackage.FUNCTION_PKG__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__VISIBLE_IN_DOC = FaPackage.FUNCTION_PKG__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__VISIBLE_IN_LM = FaPackage.FUNCTION_PKG__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__SUMMARY = FaPackage.FUNCTION_PKG__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__DESCRIPTION = FaPackage.FUNCTION_PKG__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__REVIEW = FaPackage.FUNCTION_PKG__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__OWNED_PROPERTY_VALUES = FaPackage.FUNCTION_PKG__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = FaPackage.FUNCTION_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__APPLIED_PROPERTY_VALUES = FaPackage.FUNCTION_PKG__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__OWNED_PROPERTY_VALUE_GROUPS = FaPackage.FUNCTION_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__APPLIED_PROPERTY_VALUE_GROUPS = FaPackage.FUNCTION_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__STATUS = FaPackage.FUNCTION_PKG__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__FEATURES = FaPackage.FUNCTION_PKG__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__APPLIED_REQUIREMENTS = FaPackage.FUNCTION_PKG__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__OWNED_TRACES = FaPackage.FUNCTION_PKG__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__CONTAINED_GENERIC_TRACES = FaPackage.FUNCTION_PKG__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__CONTAINED_REQUIREMENTS_TRACES = FaPackage.FUNCTION_PKG__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__NAMING_RULES = FaPackage.FUNCTION_PKG__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__OWNED_PROPERTY_VALUE_PKGS = FaPackage.FUNCTION_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
	 * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__OWNED_FUNCTIONAL_LINKS = FaPackage.FUNCTION_PKG__OWNED_FUNCTIONAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__OWNED_EXCHANGES = FaPackage.FUNCTION_PKG__OWNED_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Exchange Specification Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__OWNED_EXCHANGE_SPECIFICATION_REALIZATIONS = FaPackage.FUNCTION_PKG__OWNED_EXCHANGE_SPECIFICATION_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Owned Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__OWNED_CATEGORIES = FaPackage.FUNCTION_PKG__OWNED_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Owned Function Specifications</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__OWNED_FUNCTION_SPECIFICATIONS = FaPackage.FUNCTION_PKG__OWNED_FUNCTION_SPECIFICATIONS;

	/**
	 * The feature id for the '<em><b>Owned System Functions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTIONS = FaPackage.FUNCTION_PKG_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned System Function Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTION_PKGS = FaPackage.FUNCTION_PKG_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>System Function Pkg</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FUNCTION_PKG_FEATURE_COUNT = FaPackage.FUNCTION_PKG_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemCommunicationHookImpl <em>System Communication Hook</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.SystemCommunicationHookImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemCommunicationHook()
	 * @generated
	 */
	int SYSTEM_COMMUNICATION_HOOK = 4;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__APPLIED_REQUIREMENTS = CapellacorePackage.NAMED_ELEMENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Communication</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__COMMUNICATION = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK__TYPE = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>System Communication Hook</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_HOOK_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemCommunicationImpl <em>System Communication</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.SystemCommunicationImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemCommunication()
	 * @generated
	 */
	int SYSTEM_COMMUNICATION = 5;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__OWNED_EXTENSIONS = CapellacorePackage.RELATIONSHIP__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__ID = CapellacorePackage.RELATIONSHIP__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__SID = CapellacorePackage.RELATIONSHIP__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__CONSTRAINTS = CapellacorePackage.RELATIONSHIP__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__OWNED_CONSTRAINTS = CapellacorePackage.RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Realized Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__REALIZED_FLOW = CapellacorePackage.RELATIONSHIP__REALIZED_FLOW;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__INCOMING_TRACES = CapellacorePackage.RELATIONSHIP__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__OUTGOING_TRACES = CapellacorePackage.RELATIONSHIP__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__VISIBLE_IN_DOC = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__VISIBLE_IN_LM = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__SUMMARY = CapellacorePackage.RELATIONSHIP__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__DESCRIPTION = CapellacorePackage.RELATIONSHIP__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__REVIEW = CapellacorePackage.RELATIONSHIP__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__OWNED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__STATUS = CapellacorePackage.RELATIONSHIP__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__FEATURES = CapellacorePackage.RELATIONSHIP__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__APPLIED_REQUIREMENTS = CapellacorePackage.RELATIONSHIP__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION__ENDS = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>System Communication</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_COMMUNICATION_FEATURE_COUNT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.ActorImpl <em>Actor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.ActorImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getActor()
	 * @generated
	 */
	int ACTOR = 6;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_EXTENSIONS = CsPackage.ABSTRACT_ACTOR__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__ID = CsPackage.ABSTRACT_ACTOR__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__SID = CsPackage.ABSTRACT_ACTOR__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__CONSTRAINTS = CsPackage.ABSTRACT_ACTOR__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_CONSTRAINTS = CsPackage.ABSTRACT_ACTOR__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__NAME = CsPackage.ABSTRACT_ACTOR__NAME;

	/**
	 * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__ABSTRACT_TYPED_ELEMENTS = CsPackage.ABSTRACT_ACTOR__ABSTRACT_TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__INCOMING_TRACES = CsPackage.ABSTRACT_ACTOR__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OUTGOING_TRACES = CsPackage.ABSTRACT_ACTOR__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__VISIBLE_IN_DOC = CsPackage.ABSTRACT_ACTOR__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__VISIBLE_IN_LM = CsPackage.ABSTRACT_ACTOR__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__SUMMARY = CsPackage.ABSTRACT_ACTOR__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__DESCRIPTION = CsPackage.ABSTRACT_ACTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__REVIEW = CsPackage.ABSTRACT_ACTOR__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_PROPERTY_VALUES = CsPackage.ABSTRACT_ACTOR__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.ABSTRACT_ACTOR__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__APPLIED_PROPERTY_VALUES = CsPackage.ABSTRACT_ACTOR__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.ABSTRACT_ACTOR__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.ABSTRACT_ACTOR__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__STATUS = CsPackage.ABSTRACT_ACTOR__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__FEATURES = CsPackage.ABSTRACT_ACTOR__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__APPLIED_REQUIREMENTS = CsPackage.ABSTRACT_ACTOR__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_TRACES = CsPackage.ABSTRACT_ACTOR__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__CONTAINED_GENERIC_TRACES = CsPackage.ABSTRACT_ACTOR__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__CONTAINED_REQUIREMENTS_TRACES = CsPackage.ABSTRACT_ACTOR__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__NAMING_RULES = CsPackage.ABSTRACT_ACTOR__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__TYPED_ELEMENTS = CsPackage.ABSTRACT_ACTOR__TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_FUNCTIONAL_ALLOCATION = CsPackage.ABSTRACT_ACTOR__OWNED_FUNCTIONAL_ALLOCATION;

	/**
	 * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_COMPONENT_EXCHANGES = CsPackage.ABSTRACT_ACTOR__OWNED_COMPONENT_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.ABSTRACT_ACTOR__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__FUNCTIONAL_ALLOCATIONS = CsPackage.ABSTRACT_ACTOR__FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__ALLOCATED_FUNCTIONS = CsPackage.ABSTRACT_ACTOR__ALLOCATED_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__IN_EXCHANGE_LINKS = CsPackage.ABSTRACT_ACTOR__IN_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OUT_EXCHANGE_LINKS = CsPackage.ABSTRACT_ACTOR__OUT_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_ABSTRACT_CAPABILITY_PKG = CsPackage.ABSTRACT_ACTOR__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
	 * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_INTERFACE_PKG = CsPackage.ABSTRACT_ACTOR__OWNED_INTERFACE_PKG;

	/**
	 * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_DATA_PKG = CsPackage.ABSTRACT_ACTOR__OWNED_DATA_PKG;

	/**
	 * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_STATE_MACHINES = CsPackage.ABSTRACT_ACTOR__OWNED_STATE_MACHINES;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__ABSTRACT = CsPackage.ABSTRACT_ACTOR__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_GENERALIZATIONS = CsPackage.ABSTRACT_ACTOR__OWNED_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__SUPER_GENERALIZATIONS = CsPackage.ABSTRACT_ACTOR__SUPER_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__SUB_GENERALIZATIONS = CsPackage.ABSTRACT_ACTOR__SUB_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__SUPER = CsPackage.ABSTRACT_ACTOR__SUPER;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__SUB = CsPackage.ABSTRACT_ACTOR__SUB;

	/**
	 * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_FEATURES = CsPackage.ABSTRACT_ACTOR__OWNED_FEATURES;

	/**
	 * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__CONTAINED_PROPERTIES = CsPackage.ABSTRACT_ACTOR__CONTAINED_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Owned Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_PARTITIONS = CsPackage.ABSTRACT_ACTOR__OWNED_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Representing Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__REPRESENTING_PARTITIONS = CsPackage.ABSTRACT_ACTOR__REPRESENTING_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_INTERFACE_ALLOCATIONS = CsPackage.ABSTRACT_ACTOR__OWNED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__PROVISIONED_INTERFACE_ALLOCATIONS = CsPackage.ABSTRACT_ACTOR__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__ALLOCATED_INTERFACES = CsPackage.ABSTRACT_ACTOR__ALLOCATED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_COMMUNICATION_LINKS = CsPackage.ABSTRACT_ACTOR__OWNED_COMMUNICATION_LINKS;

	/**
	 * The feature id for the '<em><b>Produce</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__PRODUCE = CsPackage.ABSTRACT_ACTOR__PRODUCE;

	/**
	 * The feature id for the '<em><b>Consume</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__CONSUME = CsPackage.ABSTRACT_ACTOR__CONSUME;

	/**
	 * The feature id for the '<em><b>Send</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__SEND = CsPackage.ABSTRACT_ACTOR__SEND;

	/**
	 * The feature id for the '<em><b>Receive</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__RECEIVE = CsPackage.ABSTRACT_ACTOR__RECEIVE;

	/**
	 * The feature id for the '<em><b>Call</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__CALL = CsPackage.ABSTRACT_ACTOR__CALL;

	/**
	 * The feature id for the '<em><b>Execute</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__EXECUTE = CsPackage.ABSTRACT_ACTOR__EXECUTE;

	/**
	 * The feature id for the '<em><b>Write</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__WRITE = CsPackage.ABSTRACT_ACTOR__WRITE;

	/**
	 * The feature id for the '<em><b>Access</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__ACCESS = CsPackage.ABSTRACT_ACTOR__ACCESS;

	/**
	 * The feature id for the '<em><b>Acquire</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__ACQUIRE = CsPackage.ABSTRACT_ACTOR__ACQUIRE;

	/**
	 * The feature id for the '<em><b>Transmit</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__TRANSMIT = CsPackage.ABSTRACT_ACTOR__TRANSMIT;

	/**
	 * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_INTERFACE_USES = CsPackage.ABSTRACT_ACTOR__OWNED_INTERFACE_USES;

	/**
	 * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__USED_INTERFACE_LINKS = CsPackage.ABSTRACT_ACTOR__USED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__USED_INTERFACES = CsPackage.ABSTRACT_ACTOR__USED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_INTERFACE_IMPLEMENTATIONS = CsPackage.ABSTRACT_ACTOR__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
	 * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__IMPLEMENTED_INTERFACE_LINKS = CsPackage.ABSTRACT_ACTOR__IMPLEMENTED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__IMPLEMENTED_INTERFACES = CsPackage.ABSTRACT_ACTOR__IMPLEMENTED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Provisioned Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__PROVISIONED_COMPONENT_ALLOCATIONS = CsPackage.ABSTRACT_ACTOR__PROVISIONED_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioning Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__PROVISIONING_COMPONENT_ALLOCATIONS = CsPackage.ABSTRACT_ACTOR__PROVISIONING_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__ALLOCATED_COMPONENTS = CsPackage.ABSTRACT_ACTOR__ALLOCATED_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Allocating Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__ALLOCATING_COMPONENTS = CsPackage.ABSTRACT_ACTOR__ALLOCATING_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__PROVIDED_INTERFACES = CsPackage.ABSTRACT_ACTOR__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__REQUIRED_INTERFACES = CsPackage.ABSTRACT_ACTOR__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__CONTAINED_COMPONENT_PORTS = CsPackage.ABSTRACT_ACTOR__CONTAINED_COMPONENT_PORTS;

	/**
	 * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__CONTAINED_PARTS = CsPackage.ABSTRACT_ACTOR__CONTAINED_PARTS;

	/**
	 * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__CONTAINED_PHYSICAL_PORTS = CsPackage.ABSTRACT_ACTOR__CONTAINED_PHYSICAL_PORTS;

	/**
	 * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_PHYSICAL_PATH = CsPackage.ABSTRACT_ACTOR__OWNED_PHYSICAL_PATH;

	/**
	 * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_PHYSICAL_LINKS = CsPackage.ABSTRACT_ACTOR__OWNED_PHYSICAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_PHYSICAL_LINK_CATEGORIES = CsPackage.ABSTRACT_ACTOR__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__INVOLVING_INVOLVEMENTS = CsPackage.ABSTRACT_ACTOR__INVOLVING_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Involving Capability Realization Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS = CsPackage.ABSTRACT_ACTOR__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Participations In Missions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__PARTICIPATIONS_IN_MISSIONS = CsPackage.ABSTRACT_ACTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Participations In Capabilities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__PARTICIPATIONS_IN_CAPABILITIES = CsPackage.ABSTRACT_ACTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Participations In Capability Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS = CsPackage.ABSTRACT_ACTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Contributed Missions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__CONTRIBUTED_MISSIONS = CsPackage.ABSTRACT_ACTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Contributed Capabilities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__CONTRIBUTED_CAPABILITIES = CsPackage.ABSTRACT_ACTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>System Communication</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__SYSTEM_COMMUNICATION = CsPackage.ABSTRACT_ACTOR_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Owned Operational Actor Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_OPERATIONAL_ACTOR_REALIZATIONS = CsPackage.ABSTRACT_ACTOR_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Owned Operational Entity Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OWNED_OPERATIONAL_ENTITY_REALIZATIONS = CsPackage.ABSTRACT_ACTOR_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Allocated System Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__ALLOCATED_SYSTEM_FUNCTIONS = CsPackage.ABSTRACT_ACTOR_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Realized Entities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__REALIZED_ENTITIES = CsPackage.ABSTRACT_ACTOR_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Realized Operational Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__REALIZED_OPERATIONAL_ACTORS = CsPackage.ABSTRACT_ACTOR_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Realizing Logical Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__REALIZING_LOGICAL_ACTORS = CsPackage.ABSTRACT_ACTOR_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Actor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_FEATURE_COUNT = CsPackage.ABSTRACT_ACTOR_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.ActorCapabilityInvolvementImpl <em>Actor Capability Involvement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.ActorCapabilityInvolvementImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getActorCapabilityInvolvement()
	 * @generated
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT = 7;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__OWNED_EXTENSIONS = CapellacorePackage.INVOLVEMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__ID = CapellacorePackage.INVOLVEMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__SID = CapellacorePackage.INVOLVEMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__CONSTRAINTS = CapellacorePackage.INVOLVEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__OWNED_CONSTRAINTS = CapellacorePackage.INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Realized Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__REALIZED_FLOW = CapellacorePackage.INVOLVEMENT__REALIZED_FLOW;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__INCOMING_TRACES = CapellacorePackage.INVOLVEMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__OUTGOING_TRACES = CapellacorePackage.INVOLVEMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__VISIBLE_IN_DOC = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__VISIBLE_IN_LM = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__SUMMARY = CapellacorePackage.INVOLVEMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__DESCRIPTION = CapellacorePackage.INVOLVEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__REVIEW = CapellacorePackage.INVOLVEMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__STATUS = CapellacorePackage.INVOLVEMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__FEATURES = CapellacorePackage.INVOLVEMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__APPLIED_REQUIREMENTS = CapellacorePackage.INVOLVEMENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Involver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__INVOLVER = CapellacorePackage.INVOLVEMENT__INVOLVER;

	/**
	 * The feature id for the '<em><b>Involved</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__INVOLVED = CapellacorePackage.INVOLVEMENT__INVOLVED;

	/**
	 * The feature id for the '<em><b>Actor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__ACTOR = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Capability</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT__CAPABILITY = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Actor Capability Involvement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_CAPABILITY_INVOLVEMENT_FEATURE_COUNT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.ActorMissionInvolvementImpl <em>Actor Mission Involvement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.ActorMissionInvolvementImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getActorMissionInvolvement()
	 * @generated
	 */
	int ACTOR_MISSION_INVOLVEMENT = 8;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__OWNED_EXTENSIONS = CapellacorePackage.INVOLVEMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__ID = CapellacorePackage.INVOLVEMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__SID = CapellacorePackage.INVOLVEMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__CONSTRAINTS = CapellacorePackage.INVOLVEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__OWNED_CONSTRAINTS = CapellacorePackage.INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Realized Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__REALIZED_FLOW = CapellacorePackage.INVOLVEMENT__REALIZED_FLOW;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__INCOMING_TRACES = CapellacorePackage.INVOLVEMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__OUTGOING_TRACES = CapellacorePackage.INVOLVEMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__VISIBLE_IN_DOC = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__VISIBLE_IN_LM = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__SUMMARY = CapellacorePackage.INVOLVEMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__DESCRIPTION = CapellacorePackage.INVOLVEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__REVIEW = CapellacorePackage.INVOLVEMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__STATUS = CapellacorePackage.INVOLVEMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__FEATURES = CapellacorePackage.INVOLVEMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__APPLIED_REQUIREMENTS = CapellacorePackage.INVOLVEMENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Involver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__INVOLVER = CapellacorePackage.INVOLVEMENT__INVOLVER;

	/**
	 * The feature id for the '<em><b>Involved</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__INVOLVED = CapellacorePackage.INVOLVEMENT__INVOLVED;

	/**
	 * The feature id for the '<em><b>Actor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__ACTOR = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Mission</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT__MISSION = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Actor Mission Involvement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_MISSION_INVOLVEMENT_FEATURE_COUNT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.ActorPkgImpl <em>Actor Pkg</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.ActorPkgImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getActorPkg()
	 * @generated
	 */
	int ACTOR_PKG = 9;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__ID = CapellacorePackage.STRUCTURE__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__SID = CapellacorePackage.STRUCTURE__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__APPLIED_REQUIREMENTS = CapellacorePackage.STRUCTURE__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__CONTAINED_REQUIREMENTS_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
	 * The feature id for the '<em><b>Owned Actors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__OWNED_ACTORS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Actor Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__OWNED_ACTOR_PKGS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Owned System Communication</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG__OWNED_SYSTEM_COMMUNICATION = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Actor Pkg</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_PKG_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.MissionImpl <em>Mission</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.MissionImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getMission()
	 * @generated
	 */
	int MISSION = 10;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__APPLIED_REQUIREMENTS = CapellacorePackage.NAMED_ELEMENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Involved Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__INVOLVED_INVOLVEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Actor Mission Involvements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__OWNED_ACTOR_MISSION_INVOLVEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Owned System Mission Involvement</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Owned Capability Exploitations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__OWNED_CAPABILITY_EXPLOITATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Participating Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__PARTICIPATING_ACTORS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Participating System</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__PARTICIPATING_SYSTEM = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Involved Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__INVOLVED_ACTORS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Involved System</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__INVOLVED_SYSTEM = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Exploited Capabilities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION__EXPLOITED_CAPABILITIES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Mission</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.MissionPkgImpl <em>Mission Pkg</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.MissionPkgImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getMissionPkg()
	 * @generated
	 */
	int MISSION_PKG = 11;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__ID = CapellacorePackage.STRUCTURE__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__SID = CapellacorePackage.STRUCTURE__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__APPLIED_REQUIREMENTS = CapellacorePackage.STRUCTURE__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__CONTAINED_REQUIREMENTS_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
	 * The feature id for the '<em><b>Owned Mission Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__OWNED_MISSION_PKGS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Missions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG__OWNED_MISSIONS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Mission Pkg</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MISSION_PKG_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemMissionInvolvementImpl <em>System Mission Involvement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.SystemMissionInvolvementImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemMissionInvolvement()
	 * @generated
	 */
	int SYSTEM_MISSION_INVOLVEMENT = 12;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__OWNED_EXTENSIONS = CapellacorePackage.INVOLVEMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__ID = CapellacorePackage.INVOLVEMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__SID = CapellacorePackage.INVOLVEMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__CONSTRAINTS = CapellacorePackage.INVOLVEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__OWNED_CONSTRAINTS = CapellacorePackage.INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Realized Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__REALIZED_FLOW = CapellacorePackage.INVOLVEMENT__REALIZED_FLOW;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__INCOMING_TRACES = CapellacorePackage.INVOLVEMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__OUTGOING_TRACES = CapellacorePackage.INVOLVEMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__VISIBLE_IN_DOC = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__VISIBLE_IN_LM = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__SUMMARY = CapellacorePackage.INVOLVEMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__DESCRIPTION = CapellacorePackage.INVOLVEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__REVIEW = CapellacorePackage.INVOLVEMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__STATUS = CapellacorePackage.INVOLVEMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__FEATURES = CapellacorePackage.INVOLVEMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__APPLIED_REQUIREMENTS = CapellacorePackage.INVOLVEMENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Involver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__INVOLVER = CapellacorePackage.INVOLVEMENT__INVOLVER;

	/**
	 * The feature id for the '<em><b>Involved</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__INVOLVED = CapellacorePackage.INVOLVEMENT__INVOLVED;

	/**
	 * The feature id for the '<em><b>Mission</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__MISSION = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>System</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT__SYSTEM = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>System Mission Involvement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_MISSION_INVOLVEMENT_FEATURE_COUNT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl <em>Capability</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.CapabilityImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getCapability()
	 * @generated
	 */
	int CAPABILITY = 13;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OWNED_EXTENSIONS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__ID = InteractionPackage.ABSTRACT_CAPABILITY__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__SID = InteractionPackage.ABSTRACT_CAPABILITY__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__CONSTRAINTS = InteractionPackage.ABSTRACT_CAPABILITY__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OWNED_CONSTRAINTS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__NAME = InteractionPackage.ABSTRACT_CAPABILITY__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__INCOMING_TRACES = InteractionPackage.ABSTRACT_CAPABILITY__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OUTGOING_TRACES = InteractionPackage.ABSTRACT_CAPABILITY__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__VISIBLE_IN_DOC = InteractionPackage.ABSTRACT_CAPABILITY__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__VISIBLE_IN_LM = InteractionPackage.ABSTRACT_CAPABILITY__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__SUMMARY = InteractionPackage.ABSTRACT_CAPABILITY__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__DESCRIPTION = InteractionPackage.ABSTRACT_CAPABILITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__REVIEW = InteractionPackage.ABSTRACT_CAPABILITY__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OWNED_PROPERTY_VALUES = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OWNED_ENUMERATION_PROPERTY_TYPES = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__APPLIED_PROPERTY_VALUES = InteractionPackage.ABSTRACT_CAPABILITY__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OWNED_PROPERTY_VALUE_GROUPS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__APPLIED_PROPERTY_VALUE_GROUPS = InteractionPackage.ABSTRACT_CAPABILITY__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__STATUS = InteractionPackage.ABSTRACT_CAPABILITY__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__FEATURES = InteractionPackage.ABSTRACT_CAPABILITY__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__APPLIED_REQUIREMENTS = InteractionPackage.ABSTRACT_CAPABILITY__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OWNED_TRACES = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__CONTAINED_GENERIC_TRACES = InteractionPackage.ABSTRACT_CAPABILITY__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__CONTAINED_REQUIREMENTS_TRACES = InteractionPackage.ABSTRACT_CAPABILITY__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__NAMING_RULES = InteractionPackage.ABSTRACT_CAPABILITY__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OWNED_PROPERTY_VALUE_PKGS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUE_PKGS;

	/**
	 * The feature id for the '<em><b>Involved Involvements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__INVOLVED_INVOLVEMENTS = InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Owned Functional Chains</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OWNED_FUNCTIONAL_CHAINS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAINS;

	/**
	 * The feature id for the '<em><b>Pre Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__PRE_CONDITION = InteractionPackage.ABSTRACT_CAPABILITY__PRE_CONDITION;

	/**
	 * The feature id for the '<em><b>Post Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__POST_CONDITION = InteractionPackage.ABSTRACT_CAPABILITY__POST_CONDITION;

	/**
	 * The feature id for the '<em><b>Owned Scenarios</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OWNED_SCENARIOS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_SCENARIOS;

	/**
	 * The feature id for the '<em><b>Incoming Capability Allocation</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__INCOMING_CAPABILITY_ALLOCATION = InteractionPackage.ABSTRACT_CAPABILITY__INCOMING_CAPABILITY_ALLOCATION;

	/**
	 * The feature id for the '<em><b>Outgoing Capability Allocation</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OUTGOING_CAPABILITY_ALLOCATION = InteractionPackage.ABSTRACT_CAPABILITY__OUTGOING_CAPABILITY_ALLOCATION;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__EXTENDS = InteractionPackage.ABSTRACT_CAPABILITY__EXTENDS;

	/**
	 * The feature id for the '<em><b>Extending</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__EXTENDING = InteractionPackage.ABSTRACT_CAPABILITY__EXTENDING;

	/**
	 * The feature id for the '<em><b>Abstract Capability Extension Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__ABSTRACT_CAPABILITY_EXTENSION_POINTS = InteractionPackage.ABSTRACT_CAPABILITY__ABSTRACT_CAPABILITY_EXTENSION_POINTS;

	/**
	 * The feature id for the '<em><b>Super Generalizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__SUPER_GENERALIZATIONS = InteractionPackage.ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__SUB_GENERALIZATIONS = InteractionPackage.ABSTRACT_CAPABILITY__SUB_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Includes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__INCLUDES = InteractionPackage.ABSTRACT_CAPABILITY__INCLUDES;

	/**
	 * The feature id for the '<em><b>Including</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__INCLUDING = InteractionPackage.ABSTRACT_CAPABILITY__INCLUDING;

	/**
	 * The feature id for the '<em><b>Super</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__SUPER = InteractionPackage.ABSTRACT_CAPABILITY__SUPER;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__SUB = InteractionPackage.ABSTRACT_CAPABILITY__SUB;

	/**
	 * The feature id for the '<em><b>Included Abstract Capabilities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__INCLUDED_ABSTRACT_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY__INCLUDED_ABSTRACT_CAPABILITIES;

	/**
	 * The feature id for the '<em><b>Including Abstract Capabilities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__INCLUDING_ABSTRACT_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY__INCLUDING_ABSTRACT_CAPABILITIES;

	/**
	 * The feature id for the '<em><b>Extended Abstract Capabilities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES;

	/**
	 * The feature id for the '<em><b>Extending Abstract Capabilities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__EXTENDING_ABSTRACT_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY__EXTENDING_ABSTRACT_CAPABILITIES;

	/**
	 * The feature id for the '<em><b>Owned Functional Chain Abstract Capability Involvements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OWNED_FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENTS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Owned Abstract Function Abstract Capability Involvements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OWNED_ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENTS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENTS;

	/**
	 * The feature id for the '<em><b>Available In States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__AVAILABLE_IN_STATES = InteractionPackage.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES;

	/**
	 * The feature id for the '<em><b>Owned Abstract Capability Realizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS;

	/**
	 * The feature id for the '<em><b>Involved Abstract Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__INVOLVED_ABSTRACT_FUNCTIONS = InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_ABSTRACT_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>Involved Functional Chains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__INVOLVED_FUNCTIONAL_CHAINS = InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_FUNCTIONAL_CHAINS;

	/**
	 * The feature id for the '<em><b>Owned Actor Capability Involvements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OWNED_ACTOR_CAPABILITY_INVOLVEMENTS = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned System Capability Involvement</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__OWNED_SYSTEM_CAPABILITY_INVOLVEMENT = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Involved Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__INVOLVED_ACTORS = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Involved System</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__INVOLVED_SYSTEM = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Participating Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__PARTICIPATING_ACTORS = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Participating System</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__PARTICIPATING_SYSTEM = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Purposes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__PURPOSES = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Purpose Missions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__PURPOSE_MISSIONS = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Realized Operational Capabilities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__REALIZED_OPERATIONAL_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Realizing Capability Realizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY__REALIZING_CAPABILITY_REALIZATIONS = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Capability</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_FEATURE_COUNT = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.CapabilityExploitationImpl <em>Capability Exploitation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.CapabilityExploitationImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getCapabilityExploitation()
	 * @generated
	 */
	int CAPABILITY_EXPLOITATION = 14;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__OWNED_EXTENSIONS = CapellacorePackage.RELATIONSHIP__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__ID = CapellacorePackage.RELATIONSHIP__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__SID = CapellacorePackage.RELATIONSHIP__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__CONSTRAINTS = CapellacorePackage.RELATIONSHIP__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__OWNED_CONSTRAINTS = CapellacorePackage.RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Realized Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__REALIZED_FLOW = CapellacorePackage.RELATIONSHIP__REALIZED_FLOW;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__INCOMING_TRACES = CapellacorePackage.RELATIONSHIP__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__OUTGOING_TRACES = CapellacorePackage.RELATIONSHIP__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__VISIBLE_IN_DOC = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__VISIBLE_IN_LM = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__SUMMARY = CapellacorePackage.RELATIONSHIP__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__DESCRIPTION = CapellacorePackage.RELATIONSHIP__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__REVIEW = CapellacorePackage.RELATIONSHIP__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__OWNED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__STATUS = CapellacorePackage.RELATIONSHIP__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__FEATURES = CapellacorePackage.RELATIONSHIP__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__APPLIED_REQUIREMENTS = CapellacorePackage.RELATIONSHIP__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Mission</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__MISSION = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Capability</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION__CAPABILITY = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Capability Exploitation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_EXPLOITATION_FEATURE_COUNT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.CapabilityPkgImpl <em>Capability Pkg</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.CapabilityPkgImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getCapabilityPkg()
	 * @generated
	 */
	int CAPABILITY_PKG = 15;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__OWNED_EXTENSIONS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__ID = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__SID = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__CONSTRAINTS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__OWNED_CONSTRAINTS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__NAME = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__NAME;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__INCOMING_TRACES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__OUTGOING_TRACES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__VISIBLE_IN_DOC = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__VISIBLE_IN_LM = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__SUMMARY = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__DESCRIPTION = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__REVIEW = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__OWNED_PROPERTY_VALUES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__APPLIED_PROPERTY_VALUES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__STATUS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__FEATURES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__APPLIED_REQUIREMENTS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__OWNED_TRACES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__CONTAINED_GENERIC_TRACES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__CONTAINED_REQUIREMENTS_TRACES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__NAMING_RULES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
	 * The feature id for the '<em><b>Owned Capabilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__OWNED_CAPABILITIES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Capability Pkgs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG__OWNED_CAPABILITY_PKGS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Capability Pkg</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPABILITY_PKG_FEATURE_COUNT = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemCapabilityInvolvementImpl <em>System Capability Involvement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.SystemCapabilityInvolvementImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemCapabilityInvolvement()
	 * @generated
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT = 16;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__OWNED_EXTENSIONS = CapellacorePackage.INVOLVEMENT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__ID = CapellacorePackage.INVOLVEMENT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__SID = CapellacorePackage.INVOLVEMENT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__CONSTRAINTS = CapellacorePackage.INVOLVEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__OWNED_CONSTRAINTS = CapellacorePackage.INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Realized Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__REALIZED_FLOW = CapellacorePackage.INVOLVEMENT__REALIZED_FLOW;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__INCOMING_TRACES = CapellacorePackage.INVOLVEMENT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__OUTGOING_TRACES = CapellacorePackage.INVOLVEMENT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__VISIBLE_IN_DOC = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__VISIBLE_IN_LM = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__SUMMARY = CapellacorePackage.INVOLVEMENT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__DESCRIPTION = CapellacorePackage.INVOLVEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__REVIEW = CapellacorePackage.INVOLVEMENT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__STATUS = CapellacorePackage.INVOLVEMENT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__FEATURES = CapellacorePackage.INVOLVEMENT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__APPLIED_REQUIREMENTS = CapellacorePackage.INVOLVEMENT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Involver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__INVOLVER = CapellacorePackage.INVOLVEMENT__INVOLVER;

	/**
	 * The feature id for the '<em><b>Involved</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__INVOLVED = CapellacorePackage.INVOLVEMENT__INVOLVED;

	/**
	 * The feature id for the '<em><b>Capability</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__CAPABILITY = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>System</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT__SYSTEM = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>System Capability Involvement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CAPABILITY_INVOLVEMENT_FEATURE_COUNT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.OperationalActorRealizationImpl <em>Operational Actor Realization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.OperationalActorRealizationImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getOperationalActorRealization()
	 * @generated
	 */
	int OPERATIONAL_ACTOR_REALIZATION = 17;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__OWNED_EXTENSIONS = CsPackage.COMPONENT_ALLOCATION__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__ID = CsPackage.COMPONENT_ALLOCATION__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__SID = CsPackage.COMPONENT_ALLOCATION__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__CONSTRAINTS = CsPackage.COMPONENT_ALLOCATION__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__OWNED_CONSTRAINTS = CsPackage.COMPONENT_ALLOCATION__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Realized Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__REALIZED_FLOW = CsPackage.COMPONENT_ALLOCATION__REALIZED_FLOW;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__INCOMING_TRACES = CsPackage.COMPONENT_ALLOCATION__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__OUTGOING_TRACES = CsPackage.COMPONENT_ALLOCATION__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__VISIBLE_IN_DOC = CsPackage.COMPONENT_ALLOCATION__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__VISIBLE_IN_LM = CsPackage.COMPONENT_ALLOCATION__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__SUMMARY = CsPackage.COMPONENT_ALLOCATION__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__DESCRIPTION = CsPackage.COMPONENT_ALLOCATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__REVIEW = CsPackage.COMPONENT_ALLOCATION__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__STATUS = CsPackage.COMPONENT_ALLOCATION__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__FEATURES = CsPackage.COMPONENT_ALLOCATION__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__APPLIED_REQUIREMENTS = CsPackage.COMPONENT_ALLOCATION__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Target Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__TARGET_ELEMENT = CsPackage.COMPONENT_ALLOCATION__TARGET_ELEMENT;

	/**
	 * The feature id for the '<em><b>Source Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__SOURCE_ELEMENT = CsPackage.COMPONENT_ALLOCATION__SOURCE_ELEMENT;

	/**
	 * The feature id for the '<em><b>Allocated Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__ALLOCATED_COMPONENT = CsPackage.COMPONENT_ALLOCATION__ALLOCATED_COMPONENT;

	/**
	 * The feature id for the '<em><b>Allocating Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION__ALLOCATING_COMPONENT = CsPackage.COMPONENT_ALLOCATION__ALLOCATING_COMPONENT;

	/**
	 * The number of structural features of the '<em>Operational Actor Realization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ACTOR_REALIZATION_FEATURE_COUNT = CsPackage.COMPONENT_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.OperationalAnalysisRealizationImpl <em>Operational Analysis Realization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.OperationalAnalysisRealizationImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getOperationalAnalysisRealization()
	 * @generated
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION = 18;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__OWNED_EXTENSIONS = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__ID = CsPackage.ARCHITECTURE_ALLOCATION__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__SID = CsPackage.ARCHITECTURE_ALLOCATION__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__CONSTRAINTS = CsPackage.ARCHITECTURE_ALLOCATION__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__OWNED_CONSTRAINTS = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Realized Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__REALIZED_FLOW = CsPackage.ARCHITECTURE_ALLOCATION__REALIZED_FLOW;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__INCOMING_TRACES = CsPackage.ARCHITECTURE_ALLOCATION__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__OUTGOING_TRACES = CsPackage.ARCHITECTURE_ALLOCATION__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__VISIBLE_IN_DOC = CsPackage.ARCHITECTURE_ALLOCATION__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__VISIBLE_IN_LM = CsPackage.ARCHITECTURE_ALLOCATION__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__SUMMARY = CsPackage.ARCHITECTURE_ALLOCATION__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__DESCRIPTION = CsPackage.ARCHITECTURE_ALLOCATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__REVIEW = CsPackage.ARCHITECTURE_ALLOCATION__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__OWNED_PROPERTY_VALUES = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__APPLIED_PROPERTY_VALUES = CsPackage.ARCHITECTURE_ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.ARCHITECTURE_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__STATUS = CsPackage.ARCHITECTURE_ALLOCATION__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__FEATURES = CsPackage.ARCHITECTURE_ALLOCATION__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__APPLIED_REQUIREMENTS = CsPackage.ARCHITECTURE_ALLOCATION__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Target Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__TARGET_ELEMENT = CsPackage.ARCHITECTURE_ALLOCATION__TARGET_ELEMENT;

	/**
	 * The feature id for the '<em><b>Source Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__SOURCE_ELEMENT = CsPackage.ARCHITECTURE_ALLOCATION__SOURCE_ELEMENT;

	/**
	 * The feature id for the '<em><b>Allocated Architecture</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__ALLOCATED_ARCHITECTURE = CsPackage.ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE;

	/**
	 * The feature id for the '<em><b>Allocating Architecture</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION__ALLOCATING_ARCHITECTURE = CsPackage.ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE;

	/**
	 * The number of structural features of the '<em>Operational Analysis Realization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ANALYSIS_REALIZATION_FEATURE_COUNT = CsPackage.ARCHITECTURE_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.OperationalEntityRealizationImpl <em>Operational Entity Realization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.OperationalEntityRealizationImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getOperationalEntityRealization()
	 * @generated
	 */
	int OPERATIONAL_ENTITY_REALIZATION = 19;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__OWNED_EXTENSIONS = CsPackage.COMPONENT_ALLOCATION__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__ID = CsPackage.COMPONENT_ALLOCATION__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__SID = CsPackage.COMPONENT_ALLOCATION__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__CONSTRAINTS = CsPackage.COMPONENT_ALLOCATION__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__OWNED_CONSTRAINTS = CsPackage.COMPONENT_ALLOCATION__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Realized Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__REALIZED_FLOW = CsPackage.COMPONENT_ALLOCATION__REALIZED_FLOW;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__INCOMING_TRACES = CsPackage.COMPONENT_ALLOCATION__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__OUTGOING_TRACES = CsPackage.COMPONENT_ALLOCATION__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__VISIBLE_IN_DOC = CsPackage.COMPONENT_ALLOCATION__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__VISIBLE_IN_LM = CsPackage.COMPONENT_ALLOCATION__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__SUMMARY = CsPackage.COMPONENT_ALLOCATION__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__DESCRIPTION = CsPackage.COMPONENT_ALLOCATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__REVIEW = CsPackage.COMPONENT_ALLOCATION__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__STATUS = CsPackage.COMPONENT_ALLOCATION__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__FEATURES = CsPackage.COMPONENT_ALLOCATION__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__APPLIED_REQUIREMENTS = CsPackage.COMPONENT_ALLOCATION__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Target Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__TARGET_ELEMENT = CsPackage.COMPONENT_ALLOCATION__TARGET_ELEMENT;

	/**
	 * The feature id for the '<em><b>Source Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__SOURCE_ELEMENT = CsPackage.COMPONENT_ALLOCATION__SOURCE_ELEMENT;

	/**
	 * The feature id for the '<em><b>Allocated Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__ALLOCATED_COMPONENT = CsPackage.COMPONENT_ALLOCATION__ALLOCATED_COMPONENT;

	/**
	 * The feature id for the '<em><b>Allocating Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION__ALLOCATING_COMPONENT = CsPackage.COMPONENT_ALLOCATION__ALLOCATING_COMPONENT;

	/**
	 * The number of structural features of the '<em>Operational Entity Realization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_ENTITY_REALIZATION_FEATURE_COUNT = CsPackage.COMPONENT_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemContextImpl <em>System Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.polarsys.capella.core.data.ctx.impl.SystemContextImpl
	 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemContext()
	 * @generated
	 */
	int SYSTEM_CONTEXT = 20;

	/**
	 * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_EXTENSIONS = CsPackage.COMPONENT_CONTEXT__OWNED_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__ID = CsPackage.COMPONENT_CONTEXT__ID;

	/**
	 * The feature id for the '<em><b>Sid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__SID = CsPackage.COMPONENT_CONTEXT__SID;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__CONSTRAINTS = CsPackage.COMPONENT_CONTEXT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_CONSTRAINTS = CsPackage.COMPONENT_CONTEXT__OWNED_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__NAME = CsPackage.COMPONENT_CONTEXT__NAME;

	/**
	 * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__ABSTRACT_TYPED_ELEMENTS = CsPackage.COMPONENT_CONTEXT__ABSTRACT_TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__INCOMING_TRACES = CsPackage.COMPONENT_CONTEXT__INCOMING_TRACES;

	/**
	 * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OUTGOING_TRACES = CsPackage.COMPONENT_CONTEXT__OUTGOING_TRACES;

	/**
	 * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__VISIBLE_IN_DOC = CsPackage.COMPONENT_CONTEXT__VISIBLE_IN_DOC;

	/**
	 * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__VISIBLE_IN_LM = CsPackage.COMPONENT_CONTEXT__VISIBLE_IN_LM;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__SUMMARY = CsPackage.COMPONENT_CONTEXT__SUMMARY;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__DESCRIPTION = CsPackage.COMPONENT_CONTEXT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Review</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__REVIEW = CsPackage.COMPONENT_CONTEXT__REVIEW;

	/**
	 * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_CONTEXT__OWNED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_CONTEXT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
	 * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_CONTEXT__APPLIED_PROPERTY_VALUES;

	/**
	 * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_CONTEXT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_CONTEXT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
	 * The feature id for the '<em><b>Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__STATUS = CsPackage.COMPONENT_CONTEXT__STATUS;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__FEATURES = CsPackage.COMPONENT_CONTEXT__FEATURES;

	/**
	 * The feature id for the '<em><b>Applied Requirements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__APPLIED_REQUIREMENTS = CsPackage.COMPONENT_CONTEXT__APPLIED_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_TRACES = CsPackage.COMPONENT_CONTEXT__OWNED_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__CONTAINED_GENERIC_TRACES = CsPackage.COMPONENT_CONTEXT__CONTAINED_GENERIC_TRACES;

	/**
	 * The feature id for the '<em><b>Contained Requirements Traces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__CONTAINED_REQUIREMENTS_TRACES = CsPackage.COMPONENT_CONTEXT__CONTAINED_REQUIREMENTS_TRACES;

	/**
	 * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__NAMING_RULES = CsPackage.COMPONENT_CONTEXT__NAMING_RULES;

	/**
	 * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__TYPED_ELEMENTS = CsPackage.COMPONENT_CONTEXT__TYPED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_FUNCTIONAL_ALLOCATION = CsPackage.COMPONENT_CONTEXT__OWNED_FUNCTIONAL_ALLOCATION;

	/**
	 * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_COMPONENT_EXCHANGES = CsPackage.COMPONENT_CONTEXT__OWNED_COMPONENT_EXCHANGES;

	/**
	 * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.COMPONENT_CONTEXT__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
	 * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__FUNCTIONAL_ALLOCATIONS = CsPackage.COMPONENT_CONTEXT__FUNCTIONAL_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__ALLOCATED_FUNCTIONS = CsPackage.COMPONENT_CONTEXT__ALLOCATED_FUNCTIONS;

	/**
	 * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__IN_EXCHANGE_LINKS = CsPackage.COMPONENT_CONTEXT__IN_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OUT_EXCHANGE_LINKS = CsPackage.COMPONENT_CONTEXT__OUT_EXCHANGE_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_ABSTRACT_CAPABILITY_PKG = CsPackage.COMPONENT_CONTEXT__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
	 * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_INTERFACE_PKG = CsPackage.COMPONENT_CONTEXT__OWNED_INTERFACE_PKG;

	/**
	 * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_DATA_PKG = CsPackage.COMPONENT_CONTEXT__OWNED_DATA_PKG;

	/**
	 * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_STATE_MACHINES = CsPackage.COMPONENT_CONTEXT__OWNED_STATE_MACHINES;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__ABSTRACT = CsPackage.COMPONENT_CONTEXT__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_GENERALIZATIONS = CsPackage.COMPONENT_CONTEXT__OWNED_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__SUPER_GENERALIZATIONS = CsPackage.COMPONENT_CONTEXT__SUPER_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__SUB_GENERALIZATIONS = CsPackage.COMPONENT_CONTEXT__SUB_GENERALIZATIONS;

	/**
	 * The feature id for the '<em><b>Super</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__SUPER = CsPackage.COMPONENT_CONTEXT__SUPER;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__SUB = CsPackage.COMPONENT_CONTEXT__SUB;

	/**
	 * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_FEATURES = CsPackage.COMPONENT_CONTEXT__OWNED_FEATURES;

	/**
	 * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__CONTAINED_PROPERTIES = CsPackage.COMPONENT_CONTEXT__CONTAINED_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Owned Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_PARTITIONS = CsPackage.COMPONENT_CONTEXT__OWNED_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Representing Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__REPRESENTING_PARTITIONS = CsPackage.COMPONENT_CONTEXT__REPRESENTING_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_INTERFACE_ALLOCATIONS = CsPackage.COMPONENT_CONTEXT__OWNED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__PROVISIONED_INTERFACE_ALLOCATIONS = CsPackage.COMPONENT_CONTEXT__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__ALLOCATED_INTERFACES = CsPackage.COMPONENT_CONTEXT__ALLOCATED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_COMMUNICATION_LINKS = CsPackage.COMPONENT_CONTEXT__OWNED_COMMUNICATION_LINKS;

	/**
	 * The feature id for the '<em><b>Produce</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__PRODUCE = CsPackage.COMPONENT_CONTEXT__PRODUCE;

	/**
	 * The feature id for the '<em><b>Consume</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__CONSUME = CsPackage.COMPONENT_CONTEXT__CONSUME;

	/**
	 * The feature id for the '<em><b>Send</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__SEND = CsPackage.COMPONENT_CONTEXT__SEND;

	/**
	 * The feature id for the '<em><b>Receive</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__RECEIVE = CsPackage.COMPONENT_CONTEXT__RECEIVE;

	/**
	 * The feature id for the '<em><b>Call</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__CALL = CsPackage.COMPONENT_CONTEXT__CALL;

	/**
	 * The feature id for the '<em><b>Execute</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__EXECUTE = CsPackage.COMPONENT_CONTEXT__EXECUTE;

	/**
	 * The feature id for the '<em><b>Write</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__WRITE = CsPackage.COMPONENT_CONTEXT__WRITE;

	/**
	 * The feature id for the '<em><b>Access</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__ACCESS = CsPackage.COMPONENT_CONTEXT__ACCESS;

	/**
	 * The feature id for the '<em><b>Acquire</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__ACQUIRE = CsPackage.COMPONENT_CONTEXT__ACQUIRE;

	/**
	 * The feature id for the '<em><b>Transmit</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__TRANSMIT = CsPackage.COMPONENT_CONTEXT__TRANSMIT;

	/**
	 * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_INTERFACE_USES = CsPackage.COMPONENT_CONTEXT__OWNED_INTERFACE_USES;

	/**
	 * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__USED_INTERFACE_LINKS = CsPackage.COMPONENT_CONTEXT__USED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__USED_INTERFACES = CsPackage.COMPONENT_CONTEXT__USED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_INTERFACE_IMPLEMENTATIONS = CsPackage.COMPONENT_CONTEXT__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
	 * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__IMPLEMENTED_INTERFACE_LINKS = CsPackage.COMPONENT_CONTEXT__IMPLEMENTED_INTERFACE_LINKS;

	/**
	 * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__IMPLEMENTED_INTERFACES = CsPackage.COMPONENT_CONTEXT__IMPLEMENTED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Provisioned Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__PROVISIONED_COMPONENT_ALLOCATIONS = CsPackage.COMPONENT_CONTEXT__PROVISIONED_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Provisioning Component Allocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__PROVISIONING_COMPONENT_ALLOCATIONS = CsPackage.COMPONENT_CONTEXT__PROVISIONING_COMPONENT_ALLOCATIONS;

	/**
	 * The feature id for the '<em><b>Allocated Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__ALLOCATED_COMPONENTS = CsPackage.COMPONENT_CONTEXT__ALLOCATED_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Allocating Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__ALLOCATING_COMPONENTS = CsPackage.COMPONENT_CONTEXT__ALLOCATING_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__PROVIDED_INTERFACES = CsPackage.COMPONENT_CONTEXT__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__REQUIRED_INTERFACES = CsPackage.COMPONENT_CONTEXT__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__CONTAINED_COMPONENT_PORTS = CsPackage.COMPONENT_CONTEXT__CONTAINED_COMPONENT_PORTS;

	/**
	 * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__CONTAINED_PARTS = CsPackage.COMPONENT_CONTEXT__CONTAINED_PARTS;

	/**
	 * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__CONTAINED_PHYSICAL_PORTS = CsPackage.COMPONENT_CONTEXT__CONTAINED_PHYSICAL_PORTS;

	/**
	 * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_PHYSICAL_PATH = CsPackage.COMPONENT_CONTEXT__OWNED_PHYSICAL_PATH;

	/**
	 * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_PHYSICAL_LINKS = CsPackage.COMPONENT_CONTEXT__OWNED_PHYSICAL_LINKS;

	/**
	 * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT__OWNED_PHYSICAL_LINK_CATEGORIES = CsPackage.COMPONENT_CONTEXT__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
	 * The number of structural features of the '<em>System Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_CONTEXT_FEATURE_COUNT = CsPackage.COMPONENT_CONTEXT_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.SystemAnalysis <em>System Analysis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Analysis</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemAnalysis
	 * @generated
	 */
	EClass getSystemAnalysis();

	/**
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedSystemContext <em>Owned System Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned System Context</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedSystemContext()
	 * @see #getSystemAnalysis()
	 * @generated
	 */
	EReference getSystemAnalysis_OwnedSystemContext();

	/**
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedSystem <em>Owned System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned System</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedSystem()
	 * @see #getSystemAnalysis()
	 * @generated
	 */
	EReference getSystemAnalysis_OwnedSystem();

	/**
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedActorPkg <em>Owned Actor Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Actor Pkg</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedActorPkg()
	 * @see #getSystemAnalysis()
	 * @generated
	 */
	EReference getSystemAnalysis_OwnedActorPkg();

	/**
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedMissionPkg <em>Owned Mission Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Mission Pkg</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedMissionPkg()
	 * @see #getSystemAnalysis()
	 * @generated
	 */
	EReference getSystemAnalysis_OwnedMissionPkg();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getContainedCapabilityPkg <em>Contained Capability Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Contained Capability Pkg</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemAnalysis#getContainedCapabilityPkg()
	 * @see #getSystemAnalysis()
	 * @generated
	 */
	EReference getSystemAnalysis_ContainedCapabilityPkg();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getContainedSystemFunctionPkg <em>Contained System Function Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Contained System Function Pkg</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemAnalysis#getContainedSystemFunctionPkg()
	 * @see #getSystemAnalysis()
	 * @generated
	 */
	EReference getSystemAnalysis_ContainedSystemFunctionPkg();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedOperationalAnalysisRealizations <em>Owned Operational Analysis Realizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Operational Analysis Realizations</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedOperationalAnalysisRealizations()
	 * @see #getSystemAnalysis()
	 * @generated
	 */
	EReference getSystemAnalysis_OwnedOperationalAnalysisRealizations();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getAllocatedOperationalAnalysisRealizations <em>Allocated Operational Analysis Realizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocated Operational Analysis Realizations</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemAnalysis#getAllocatedOperationalAnalysisRealizations()
	 * @see #getSystemAnalysis()
	 * @generated
	 */
	EReference getSystemAnalysis_AllocatedOperationalAnalysisRealizations();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getAllocatedOperationalAnalyses <em>Allocated Operational Analyses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocated Operational Analyses</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemAnalysis#getAllocatedOperationalAnalyses()
	 * @see #getSystemAnalysis()
	 * @generated
	 */
	EReference getSystemAnalysis_AllocatedOperationalAnalyses();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getAllocatingLogicalArchitectures <em>Allocating Logical Architectures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocating Logical Architectures</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemAnalysis#getAllocatingLogicalArchitectures()
	 * @see #getSystemAnalysis()
	 * @generated
	 */
	EReference getSystemAnalysis_AllocatingLogicalArchitectures();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.System <em>System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System</em>'.
	 * @see org.polarsys.capella.core.data.ctx.System
	 * @generated
	 */
	EClass getSystem();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.System#getContributedCapabilities <em>Contributed Capabilities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contributed Capabilities</em>'.
	 * @see org.polarsys.capella.core.data.ctx.System#getContributedCapabilities()
	 * @see #getSystem()
	 * @generated
	 */
	EReference getSystem_ContributedCapabilities();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.System#getParticipationsInCapabilities <em>Participations In Capabilities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Participations In Capabilities</em>'.
	 * @see org.polarsys.capella.core.data.ctx.System#getParticipationsInCapabilities()
	 * @see #getSystem()
	 * @generated
	 */
	EReference getSystem_ParticipationsInCapabilities();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.System#getContributedMissions <em>Contributed Missions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contributed Missions</em>'.
	 * @see org.polarsys.capella.core.data.ctx.System#getContributedMissions()
	 * @see #getSystem()
	 * @generated
	 */
	EReference getSystem_ContributedMissions();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.System#getParticipationsInMissions <em>Participations In Missions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Participations In Missions</em>'.
	 * @see org.polarsys.capella.core.data.ctx.System#getParticipationsInMissions()
	 * @see #getSystem()
	 * @generated
	 */
	EReference getSystem_ParticipationsInMissions();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.System#getExternalCommunication <em>External Communication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>External Communication</em>'.
	 * @see org.polarsys.capella.core.data.ctx.System#getExternalCommunication()
	 * @see #getSystem()
	 * @generated
	 */
	EReference getSystem_ExternalCommunication();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.System#getOwnedEntityRealizations <em>Owned Entity Realizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Entity Realizations</em>'.
	 * @see org.polarsys.capella.core.data.ctx.System#getOwnedEntityRealizations()
	 * @see #getSystem()
	 * @generated
	 */
	EReference getSystem_OwnedEntityRealizations();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.System#getAllocatedEntityRealizations <em>Allocated Entity Realizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocated Entity Realizations</em>'.
	 * @see org.polarsys.capella.core.data.ctx.System#getAllocatedEntityRealizations()
	 * @see #getSystem()
	 * @generated
	 */
	EReference getSystem_AllocatedEntityRealizations();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.System#getAllocatedSystemFunctions <em>Allocated System Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocated System Functions</em>'.
	 * @see org.polarsys.capella.core.data.ctx.System#getAllocatedSystemFunctions()
	 * @see #getSystem()
	 * @generated
	 */
	EReference getSystem_AllocatedSystemFunctions();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.System#getRealizedEntities <em>Realized Entities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Realized Entities</em>'.
	 * @see org.polarsys.capella.core.data.ctx.System#getRealizedEntities()
	 * @see #getSystem()
	 * @generated
	 */
	EReference getSystem_RealizedEntities();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.System#getRealizingLogicalComponents <em>Realizing Logical Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Realizing Logical Components</em>'.
	 * @see org.polarsys.capella.core.data.ctx.System#getRealizingLogicalComponents()
	 * @see #getSystem()
	 * @generated
	 */
	EReference getSystem_RealizingLogicalComponents();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.SystemFunction <em>System Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Function</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemFunction
	 * @generated
	 */
	EClass getSystemFunction();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.SystemFunction#getOwnedSystemFunctionPkgs <em>Owned System Function Pkgs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned System Function Pkgs</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemFunction#getOwnedSystemFunctionPkgs()
	 * @see #getSystemFunction()
	 * @generated
	 */
	EReference getSystemFunction_OwnedSystemFunctionPkgs();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemFunction#getAllocatorActors <em>Allocator Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocator Actors</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemFunction#getAllocatorActors()
	 * @see #getSystemFunction()
	 * @generated
	 */
	EReference getSystemFunction_AllocatorActors();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemFunction#getAllocatorSystems <em>Allocator Systems</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocator Systems</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemFunction#getAllocatorSystems()
	 * @see #getSystemFunction()
	 * @generated
	 */
	EReference getSystemFunction_AllocatorSystems();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemFunction#getRealizedOperationalActivities <em>Realized Operational Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Realized Operational Activities</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemFunction#getRealizedOperationalActivities()
	 * @see #getSystemFunction()
	 * @generated
	 */
	EReference getSystemFunction_RealizedOperationalActivities();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemFunction#getRealizingLogicalFunctions <em>Realizing Logical Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Realizing Logical Functions</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemFunction#getRealizingLogicalFunctions()
	 * @see #getSystemFunction()
	 * @generated
	 */
	EReference getSystemFunction_RealizingLogicalFunctions();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemFunction#getContainedSystemFunctions <em>Contained System Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contained System Functions</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemFunction#getContainedSystemFunctions()
	 * @see #getSystemFunction()
	 * @generated
	 */
	EReference getSystemFunction_ContainedSystemFunctions();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemFunction#getChildrenSystemFunctions <em>Children System Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Children System Functions</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemFunction#getChildrenSystemFunctions()
	 * @see #getSystemFunction()
	 * @generated
	 */
	EReference getSystemFunction_ChildrenSystemFunctions();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.SystemFunctionPkg <em>System Function Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Function Pkg</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemFunctionPkg
	 * @generated
	 */
	EClass getSystemFunctionPkg();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.SystemFunctionPkg#getOwnedSystemFunctions <em>Owned System Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned System Functions</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemFunctionPkg#getOwnedSystemFunctions()
	 * @see #getSystemFunctionPkg()
	 * @generated
	 */
	EReference getSystemFunctionPkg_OwnedSystemFunctions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.SystemFunctionPkg#getOwnedSystemFunctionPkgs <em>Owned System Function Pkgs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned System Function Pkgs</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemFunctionPkg#getOwnedSystemFunctionPkgs()
	 * @see #getSystemFunctionPkg()
	 * @generated
	 */
	EReference getSystemFunctionPkg_OwnedSystemFunctionPkgs();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.SystemCommunicationHook <em>System Communication Hook</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Communication Hook</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemCommunicationHook
	 * @generated
	 */
	EClass getSystemCommunicationHook();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.SystemCommunicationHook#getCommunication <em>Communication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Communication</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemCommunicationHook#getCommunication()
	 * @see #getSystemCommunicationHook()
	 * @generated
	 */
	EReference getSystemCommunicationHook_Communication();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.SystemCommunicationHook#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemCommunicationHook#getType()
	 * @see #getSystemCommunicationHook()
	 * @generated
	 */
	EReference getSystemCommunicationHook_Type();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.SystemCommunication <em>System Communication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Communication</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemCommunication
	 * @generated
	 */
	EClass getSystemCommunication();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.SystemCommunication#getEnds <em>Ends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ends</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemCommunication#getEnds()
	 * @see #getSystemCommunication()
	 * @generated
	 */
	EReference getSystemCommunication_Ends();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.Actor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actor</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Actor
	 * @generated
	 */
	EClass getActor();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Actor#getParticipationsInMissions <em>Participations In Missions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Participations In Missions</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Actor#getParticipationsInMissions()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_ParticipationsInMissions();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Actor#getParticipationsInCapabilities <em>Participations In Capabilities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Participations In Capabilities</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Actor#getParticipationsInCapabilities()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_ParticipationsInCapabilities();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Actor#getParticipationsInCapabilityRealizations <em>Participations In Capability Realizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Participations In Capability Realizations</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Actor#getParticipationsInCapabilityRealizations()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_ParticipationsInCapabilityRealizations();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Actor#getContributedMissions <em>Contributed Missions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contributed Missions</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Actor#getContributedMissions()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_ContributedMissions();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Actor#getContributedCapabilities <em>Contributed Capabilities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contributed Capabilities</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Actor#getContributedCapabilities()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_ContributedCapabilities();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.Actor#getSystemCommunication <em>System Communication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>System Communication</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Actor#getSystemCommunication()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_SystemCommunication();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.Actor#getOwnedOperationalActorRealizations <em>Owned Operational Actor Realizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Operational Actor Realizations</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Actor#getOwnedOperationalActorRealizations()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_OwnedOperationalActorRealizations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.Actor#getOwnedOperationalEntityRealizations <em>Owned Operational Entity Realizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Operational Entity Realizations</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Actor#getOwnedOperationalEntityRealizations()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_OwnedOperationalEntityRealizations();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Actor#getAllocatedSystemFunctions <em>Allocated System Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocated System Functions</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Actor#getAllocatedSystemFunctions()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_AllocatedSystemFunctions();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Actor#getRealizedEntities <em>Realized Entities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Realized Entities</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Actor#getRealizedEntities()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_RealizedEntities();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Actor#getRealizedOperationalActors <em>Realized Operational Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Realized Operational Actors</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Actor#getRealizedOperationalActors()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_RealizedOperationalActors();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Actor#getRealizingLogicalActors <em>Realizing Logical Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Realizing Logical Actors</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Actor#getRealizingLogicalActors()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_RealizingLogicalActors();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement <em>Actor Capability Involvement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actor Capability Involvement</em>'.
	 * @see org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement
	 * @generated
	 */
	EClass getActorCapabilityInvolvement();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement#getActor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Actor</em>'.
	 * @see org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement#getActor()
	 * @see #getActorCapabilityInvolvement()
	 * @generated
	 */
	EReference getActorCapabilityInvolvement_Actor();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement#getCapability <em>Capability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Capability</em>'.
	 * @see org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement#getCapability()
	 * @see #getActorCapabilityInvolvement()
	 * @generated
	 */
	EReference getActorCapabilityInvolvement_Capability();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.ActorMissionInvolvement <em>Actor Mission Involvement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actor Mission Involvement</em>'.
	 * @see org.polarsys.capella.core.data.ctx.ActorMissionInvolvement
	 * @generated
	 */
	EClass getActorMissionInvolvement();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.ActorMissionInvolvement#getActor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Actor</em>'.
	 * @see org.polarsys.capella.core.data.ctx.ActorMissionInvolvement#getActor()
	 * @see #getActorMissionInvolvement()
	 * @generated
	 */
	EReference getActorMissionInvolvement_Actor();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.ActorMissionInvolvement#getMission <em>Mission</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Mission</em>'.
	 * @see org.polarsys.capella.core.data.ctx.ActorMissionInvolvement#getMission()
	 * @see #getActorMissionInvolvement()
	 * @generated
	 */
	EReference getActorMissionInvolvement_Mission();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.ActorPkg <em>Actor Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actor Pkg</em>'.
	 * @see org.polarsys.capella.core.data.ctx.ActorPkg
	 * @generated
	 */
	EClass getActorPkg();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.ActorPkg#getOwnedActors <em>Owned Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Actors</em>'.
	 * @see org.polarsys.capella.core.data.ctx.ActorPkg#getOwnedActors()
	 * @see #getActorPkg()
	 * @generated
	 */
	EReference getActorPkg_OwnedActors();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.ActorPkg#getOwnedActorPkgs <em>Owned Actor Pkgs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Actor Pkgs</em>'.
	 * @see org.polarsys.capella.core.data.ctx.ActorPkg#getOwnedActorPkgs()
	 * @see #getActorPkg()
	 * @generated
	 */
	EReference getActorPkg_OwnedActorPkgs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.ActorPkg#getOwnedSystemCommunication <em>Owned System Communication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned System Communication</em>'.
	 * @see org.polarsys.capella.core.data.ctx.ActorPkg#getOwnedSystemCommunication()
	 * @see #getActorPkg()
	 * @generated
	 */
	EReference getActorPkg_OwnedSystemCommunication();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.Mission <em>Mission</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mission</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Mission
	 * @generated
	 */
	EClass getMission();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.Mission#getOwnedActorMissionInvolvements <em>Owned Actor Mission Involvements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Actor Mission Involvements</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Mission#getOwnedActorMissionInvolvements()
	 * @see #getMission()
	 * @generated
	 */
	EReference getMission_OwnedActorMissionInvolvements();

	/**
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.ctx.Mission#getOwnedSystemMissionInvolvement <em>Owned System Mission Involvement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned System Mission Involvement</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Mission#getOwnedSystemMissionInvolvement()
	 * @see #getMission()
	 * @generated
	 */
	EReference getMission_OwnedSystemMissionInvolvement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.Mission#getOwnedCapabilityExploitations <em>Owned Capability Exploitations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Capability Exploitations</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Mission#getOwnedCapabilityExploitations()
	 * @see #getMission()
	 * @generated
	 */
	EReference getMission_OwnedCapabilityExploitations();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Mission#getParticipatingActors <em>Participating Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Participating Actors</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Mission#getParticipatingActors()
	 * @see #getMission()
	 * @generated
	 */
	EReference getMission_ParticipatingActors();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.Mission#getParticipatingSystem <em>Participating System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Participating System</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Mission#getParticipatingSystem()
	 * @see #getMission()
	 * @generated
	 */
	EReference getMission_ParticipatingSystem();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Mission#getInvolvedActors <em>Involved Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Involved Actors</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Mission#getInvolvedActors()
	 * @see #getMission()
	 * @generated
	 */
	EReference getMission_InvolvedActors();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.Mission#getInvolvedSystem <em>Involved System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Involved System</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Mission#getInvolvedSystem()
	 * @see #getMission()
	 * @generated
	 */
	EReference getMission_InvolvedSystem();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Mission#getExploitedCapabilities <em>Exploited Capabilities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Exploited Capabilities</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Mission#getExploitedCapabilities()
	 * @see #getMission()
	 * @generated
	 */
	EReference getMission_ExploitedCapabilities();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.MissionPkg <em>Mission Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mission Pkg</em>'.
	 * @see org.polarsys.capella.core.data.ctx.MissionPkg
	 * @generated
	 */
	EClass getMissionPkg();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.MissionPkg#getOwnedMissionPkgs <em>Owned Mission Pkgs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Mission Pkgs</em>'.
	 * @see org.polarsys.capella.core.data.ctx.MissionPkg#getOwnedMissionPkgs()
	 * @see #getMissionPkg()
	 * @generated
	 */
	EReference getMissionPkg_OwnedMissionPkgs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.MissionPkg#getOwnedMissions <em>Owned Missions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Missions</em>'.
	 * @see org.polarsys.capella.core.data.ctx.MissionPkg#getOwnedMissions()
	 * @see #getMissionPkg()
	 * @generated
	 */
	EReference getMissionPkg_OwnedMissions();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.SystemMissionInvolvement <em>System Mission Involvement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Mission Involvement</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemMissionInvolvement
	 * @generated
	 */
	EClass getSystemMissionInvolvement();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.SystemMissionInvolvement#getMission <em>Mission</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Mission</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemMissionInvolvement#getMission()
	 * @see #getSystemMissionInvolvement()
	 * @generated
	 */
	EReference getSystemMissionInvolvement_Mission();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.SystemMissionInvolvement#getSystem <em>System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>System</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemMissionInvolvement#getSystem()
	 * @see #getSystemMissionInvolvement()
	 * @generated
	 */
	EReference getSystemMissionInvolvement_System();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.Capability <em>Capability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Capability</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Capability
	 * @generated
	 */
	EClass getCapability();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.Capability#getOwnedActorCapabilityInvolvements <em>Owned Actor Capability Involvements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Actor Capability Involvements</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Capability#getOwnedActorCapabilityInvolvements()
	 * @see #getCapability()
	 * @generated
	 */
	EReference getCapability_OwnedActorCapabilityInvolvements();

	/**
	 * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.ctx.Capability#getOwnedSystemCapabilityInvolvement <em>Owned System Capability Involvement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned System Capability Involvement</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Capability#getOwnedSystemCapabilityInvolvement()
	 * @see #getCapability()
	 * @generated
	 */
	EReference getCapability_OwnedSystemCapabilityInvolvement();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Capability#getInvolvedActors <em>Involved Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Involved Actors</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Capability#getInvolvedActors()
	 * @see #getCapability()
	 * @generated
	 */
	EReference getCapability_InvolvedActors();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.Capability#getInvolvedSystem <em>Involved System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Involved System</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Capability#getInvolvedSystem()
	 * @see #getCapability()
	 * @generated
	 */
	EReference getCapability_InvolvedSystem();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Capability#getParticipatingActors <em>Participating Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Participating Actors</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Capability#getParticipatingActors()
	 * @see #getCapability()
	 * @generated
	 */
	EReference getCapability_ParticipatingActors();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.Capability#getParticipatingSystem <em>Participating System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Participating System</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Capability#getParticipatingSystem()
	 * @see #getCapability()
	 * @generated
	 */
	EReference getCapability_ParticipatingSystem();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Capability#getPurposes <em>Purposes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Purposes</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Capability#getPurposes()
	 * @see #getCapability()
	 * @generated
	 */
	EReference getCapability_Purposes();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Capability#getPurposeMissions <em>Purpose Missions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Purpose Missions</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Capability#getPurposeMissions()
	 * @see #getCapability()
	 * @generated
	 */
	EReference getCapability_PurposeMissions();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Capability#getRealizedOperationalCapabilities <em>Realized Operational Capabilities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Realized Operational Capabilities</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Capability#getRealizedOperationalCapabilities()
	 * @see #getCapability()
	 * @generated
	 */
	EReference getCapability_RealizedOperationalCapabilities();

	/**
	 * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Capability#getRealizingCapabilityRealizations <em>Realizing Capability Realizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Realizing Capability Realizations</em>'.
	 * @see org.polarsys.capella.core.data.ctx.Capability#getRealizingCapabilityRealizations()
	 * @see #getCapability()
	 * @generated
	 */
	EReference getCapability_RealizingCapabilityRealizations();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.CapabilityExploitation <em>Capability Exploitation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Capability Exploitation</em>'.
	 * @see org.polarsys.capella.core.data.ctx.CapabilityExploitation
	 * @generated
	 */
	EClass getCapabilityExploitation();

	/**
	 * Returns the meta object for the container reference '{@link org.polarsys.capella.core.data.ctx.CapabilityExploitation#getMission <em>Mission</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Mission</em>'.
	 * @see org.polarsys.capella.core.data.ctx.CapabilityExploitation#getMission()
	 * @see #getCapabilityExploitation()
	 * @generated
	 */
	EReference getCapabilityExploitation_Mission();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.CapabilityExploitation#getCapability <em>Capability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Capability</em>'.
	 * @see org.polarsys.capella.core.data.ctx.CapabilityExploitation#getCapability()
	 * @see #getCapabilityExploitation()
	 * @generated
	 */
	EReference getCapabilityExploitation_Capability();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.CapabilityPkg <em>Capability Pkg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Capability Pkg</em>'.
	 * @see org.polarsys.capella.core.data.ctx.CapabilityPkg
	 * @generated
	 */
	EClass getCapabilityPkg();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.CapabilityPkg#getOwnedCapabilities <em>Owned Capabilities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Capabilities</em>'.
	 * @see org.polarsys.capella.core.data.ctx.CapabilityPkg#getOwnedCapabilities()
	 * @see #getCapabilityPkg()
	 * @generated
	 */
	EReference getCapabilityPkg_OwnedCapabilities();

	/**
	 * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.CapabilityPkg#getOwnedCapabilityPkgs <em>Owned Capability Pkgs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Capability Pkgs</em>'.
	 * @see org.polarsys.capella.core.data.ctx.CapabilityPkg#getOwnedCapabilityPkgs()
	 * @see #getCapabilityPkg()
	 * @generated
	 */
	EReference getCapabilityPkg_OwnedCapabilityPkgs();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement <em>System Capability Involvement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Capability Involvement</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement
	 * @generated
	 */
	EClass getSystemCapabilityInvolvement();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement#getCapability <em>Capability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Capability</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement#getCapability()
	 * @see #getSystemCapabilityInvolvement()
	 * @generated
	 */
	EReference getSystemCapabilityInvolvement_Capability();

	/**
	 * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement#getSystem <em>System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>System</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement#getSystem()
	 * @see #getSystemCapabilityInvolvement()
	 * @generated
	 */
	EReference getSystemCapabilityInvolvement_System();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.OperationalActorRealization <em>Operational Actor Realization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operational Actor Realization</em>'.
	 * @see org.polarsys.capella.core.data.ctx.OperationalActorRealization
	 * @generated
	 */
	EClass getOperationalActorRealization();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization <em>Operational Analysis Realization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operational Analysis Realization</em>'.
	 * @see org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization
	 * @generated
	 */
	EClass getOperationalAnalysisRealization();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.OperationalEntityRealization <em>Operational Entity Realization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operational Entity Realization</em>'.
	 * @see org.polarsys.capella.core.data.ctx.OperationalEntityRealization
	 * @generated
	 */
	EClass getOperationalEntityRealization();

	/**
	 * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.SystemContext <em>System Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Context</em>'.
	 * @see org.polarsys.capella.core.data.ctx.SystemContext
	 * @generated
	 */
	EClass getSystemContext();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CtxFactory getCtxFactory();

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
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemAnalysisImpl <em>System Analysis</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.SystemAnalysisImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemAnalysis()
		 * @generated
		 */
		EClass SYSTEM_ANALYSIS = eINSTANCE.getSystemAnalysis();

		/**
		 * The meta object literal for the '<em><b>Owned System Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT = eINSTANCE.getSystemAnalysis_OwnedSystemContext();

		/**
		 * The meta object literal for the '<em><b>Owned System</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_ANALYSIS__OWNED_SYSTEM = eINSTANCE.getSystemAnalysis_OwnedSystem();

		/**
		 * The meta object literal for the '<em><b>Owned Actor Pkg</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_ANALYSIS__OWNED_ACTOR_PKG = eINSTANCE.getSystemAnalysis_OwnedActorPkg();

		/**
		 * The meta object literal for the '<em><b>Owned Mission Pkg</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_ANALYSIS__OWNED_MISSION_PKG = eINSTANCE.getSystemAnalysis_OwnedMissionPkg();

		/**
		 * The meta object literal for the '<em><b>Contained Capability Pkg</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_ANALYSIS__CONTAINED_CAPABILITY_PKG = eINSTANCE.getSystemAnalysis_ContainedCapabilityPkg();

		/**
		 * The meta object literal for the '<em><b>Contained System Function Pkg</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_ANALYSIS__CONTAINED_SYSTEM_FUNCTION_PKG = eINSTANCE.getSystemAnalysis_ContainedSystemFunctionPkg();

		/**
		 * The meta object literal for the '<em><b>Owned Operational Analysis Realizations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS = eINSTANCE.getSystemAnalysis_OwnedOperationalAnalysisRealizations();

		/**
		 * The meta object literal for the '<em><b>Allocated Operational Analysis Realizations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSIS_REALIZATIONS = eINSTANCE.getSystemAnalysis_AllocatedOperationalAnalysisRealizations();

		/**
		 * The meta object literal for the '<em><b>Allocated Operational Analyses</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSES = eINSTANCE.getSystemAnalysis_AllocatedOperationalAnalyses();

		/**
		 * The meta object literal for the '<em><b>Allocating Logical Architectures</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_ANALYSIS__ALLOCATING_LOGICAL_ARCHITECTURES = eINSTANCE.getSystemAnalysis_AllocatingLogicalArchitectures();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemImpl <em>System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.SystemImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystem()
		 * @generated
		 */
		EClass SYSTEM = eINSTANCE.getSystem();

		/**
		 * The meta object literal for the '<em><b>Contributed Capabilities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM__CONTRIBUTED_CAPABILITIES = eINSTANCE.getSystem_ContributedCapabilities();

		/**
		 * The meta object literal for the '<em><b>Participations In Capabilities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM__PARTICIPATIONS_IN_CAPABILITIES = eINSTANCE.getSystem_ParticipationsInCapabilities();

		/**
		 * The meta object literal for the '<em><b>Contributed Missions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM__CONTRIBUTED_MISSIONS = eINSTANCE.getSystem_ContributedMissions();

		/**
		 * The meta object literal for the '<em><b>Participations In Missions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM__PARTICIPATIONS_IN_MISSIONS = eINSTANCE.getSystem_ParticipationsInMissions();

		/**
		 * The meta object literal for the '<em><b>External Communication</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM__EXTERNAL_COMMUNICATION = eINSTANCE.getSystem_ExternalCommunication();

		/**
		 * The meta object literal for the '<em><b>Owned Entity Realizations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM__OWNED_ENTITY_REALIZATIONS = eINSTANCE.getSystem_OwnedEntityRealizations();

		/**
		 * The meta object literal for the '<em><b>Allocated Entity Realizations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM__ALLOCATED_ENTITY_REALIZATIONS = eINSTANCE.getSystem_AllocatedEntityRealizations();

		/**
		 * The meta object literal for the '<em><b>Allocated System Functions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM__ALLOCATED_SYSTEM_FUNCTIONS = eINSTANCE.getSystem_AllocatedSystemFunctions();

		/**
		 * The meta object literal for the '<em><b>Realized Entities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM__REALIZED_ENTITIES = eINSTANCE.getSystem_RealizedEntities();

		/**
		 * The meta object literal for the '<em><b>Realizing Logical Components</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM__REALIZING_LOGICAL_COMPONENTS = eINSTANCE.getSystem_RealizingLogicalComponents();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemFunctionImpl <em>System Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.SystemFunctionImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemFunction()
		 * @generated
		 */
		EClass SYSTEM_FUNCTION = eINSTANCE.getSystemFunction();

		/**
		 * The meta object literal for the '<em><b>Owned System Function Pkgs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_FUNCTION__OWNED_SYSTEM_FUNCTION_PKGS = eINSTANCE.getSystemFunction_OwnedSystemFunctionPkgs();

		/**
		 * The meta object literal for the '<em><b>Allocator Actors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_FUNCTION__ALLOCATOR_ACTORS = eINSTANCE.getSystemFunction_AllocatorActors();

		/**
		 * The meta object literal for the '<em><b>Allocator Systems</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_FUNCTION__ALLOCATOR_SYSTEMS = eINSTANCE.getSystemFunction_AllocatorSystems();

		/**
		 * The meta object literal for the '<em><b>Realized Operational Activities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_FUNCTION__REALIZED_OPERATIONAL_ACTIVITIES = eINSTANCE.getSystemFunction_RealizedOperationalActivities();

		/**
		 * The meta object literal for the '<em><b>Realizing Logical Functions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_FUNCTION__REALIZING_LOGICAL_FUNCTIONS = eINSTANCE.getSystemFunction_RealizingLogicalFunctions();

		/**
		 * The meta object literal for the '<em><b>Contained System Functions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_FUNCTION__CONTAINED_SYSTEM_FUNCTIONS = eINSTANCE.getSystemFunction_ContainedSystemFunctions();

		/**
		 * The meta object literal for the '<em><b>Children System Functions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_FUNCTION__CHILDREN_SYSTEM_FUNCTIONS = eINSTANCE.getSystemFunction_ChildrenSystemFunctions();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemFunctionPkgImpl <em>System Function Pkg</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.SystemFunctionPkgImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemFunctionPkg()
		 * @generated
		 */
		EClass SYSTEM_FUNCTION_PKG = eINSTANCE.getSystemFunctionPkg();

		/**
		 * The meta object literal for the '<em><b>Owned System Functions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTIONS = eINSTANCE.getSystemFunctionPkg_OwnedSystemFunctions();

		/**
		 * The meta object literal for the '<em><b>Owned System Function Pkgs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTION_PKGS = eINSTANCE.getSystemFunctionPkg_OwnedSystemFunctionPkgs();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemCommunicationHookImpl <em>System Communication Hook</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.SystemCommunicationHookImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemCommunicationHook()
		 * @generated
		 */
		EClass SYSTEM_COMMUNICATION_HOOK = eINSTANCE.getSystemCommunicationHook();

		/**
		 * The meta object literal for the '<em><b>Communication</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_COMMUNICATION_HOOK__COMMUNICATION = eINSTANCE.getSystemCommunicationHook_Communication();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_COMMUNICATION_HOOK__TYPE = eINSTANCE.getSystemCommunicationHook_Type();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemCommunicationImpl <em>System Communication</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.SystemCommunicationImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemCommunication()
		 * @generated
		 */
		EClass SYSTEM_COMMUNICATION = eINSTANCE.getSystemCommunication();

		/**
		 * The meta object literal for the '<em><b>Ends</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_COMMUNICATION__ENDS = eINSTANCE.getSystemCommunication_Ends();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.ActorImpl <em>Actor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.ActorImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getActor()
		 * @generated
		 */
		EClass ACTOR = eINSTANCE.getActor();

		/**
		 * The meta object literal for the '<em><b>Participations In Missions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__PARTICIPATIONS_IN_MISSIONS = eINSTANCE.getActor_ParticipationsInMissions();

		/**
		 * The meta object literal for the '<em><b>Participations In Capabilities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__PARTICIPATIONS_IN_CAPABILITIES = eINSTANCE.getActor_ParticipationsInCapabilities();

		/**
		 * The meta object literal for the '<em><b>Participations In Capability Realizations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS = eINSTANCE.getActor_ParticipationsInCapabilityRealizations();

		/**
		 * The meta object literal for the '<em><b>Contributed Missions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__CONTRIBUTED_MISSIONS = eINSTANCE.getActor_ContributedMissions();

		/**
		 * The meta object literal for the '<em><b>Contributed Capabilities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__CONTRIBUTED_CAPABILITIES = eINSTANCE.getActor_ContributedCapabilities();

		/**
		 * The meta object literal for the '<em><b>System Communication</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__SYSTEM_COMMUNICATION = eINSTANCE.getActor_SystemCommunication();

		/**
		 * The meta object literal for the '<em><b>Owned Operational Actor Realizations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__OWNED_OPERATIONAL_ACTOR_REALIZATIONS = eINSTANCE.getActor_OwnedOperationalActorRealizations();

		/**
		 * The meta object literal for the '<em><b>Owned Operational Entity Realizations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__OWNED_OPERATIONAL_ENTITY_REALIZATIONS = eINSTANCE.getActor_OwnedOperationalEntityRealizations();

		/**
		 * The meta object literal for the '<em><b>Allocated System Functions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__ALLOCATED_SYSTEM_FUNCTIONS = eINSTANCE.getActor_AllocatedSystemFunctions();

		/**
		 * The meta object literal for the '<em><b>Realized Entities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__REALIZED_ENTITIES = eINSTANCE.getActor_RealizedEntities();

		/**
		 * The meta object literal for the '<em><b>Realized Operational Actors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__REALIZED_OPERATIONAL_ACTORS = eINSTANCE.getActor_RealizedOperationalActors();

		/**
		 * The meta object literal for the '<em><b>Realizing Logical Actors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__REALIZING_LOGICAL_ACTORS = eINSTANCE.getActor_RealizingLogicalActors();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.ActorCapabilityInvolvementImpl <em>Actor Capability Involvement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.ActorCapabilityInvolvementImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getActorCapabilityInvolvement()
		 * @generated
		 */
		EClass ACTOR_CAPABILITY_INVOLVEMENT = eINSTANCE.getActorCapabilityInvolvement();

		/**
		 * The meta object literal for the '<em><b>Actor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR_CAPABILITY_INVOLVEMENT__ACTOR = eINSTANCE.getActorCapabilityInvolvement_Actor();

		/**
		 * The meta object literal for the '<em><b>Capability</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR_CAPABILITY_INVOLVEMENT__CAPABILITY = eINSTANCE.getActorCapabilityInvolvement_Capability();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.ActorMissionInvolvementImpl <em>Actor Mission Involvement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.ActorMissionInvolvementImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getActorMissionInvolvement()
		 * @generated
		 */
		EClass ACTOR_MISSION_INVOLVEMENT = eINSTANCE.getActorMissionInvolvement();

		/**
		 * The meta object literal for the '<em><b>Actor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR_MISSION_INVOLVEMENT__ACTOR = eINSTANCE.getActorMissionInvolvement_Actor();

		/**
		 * The meta object literal for the '<em><b>Mission</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR_MISSION_INVOLVEMENT__MISSION = eINSTANCE.getActorMissionInvolvement_Mission();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.ActorPkgImpl <em>Actor Pkg</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.ActorPkgImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getActorPkg()
		 * @generated
		 */
		EClass ACTOR_PKG = eINSTANCE.getActorPkg();

		/**
		 * The meta object literal for the '<em><b>Owned Actors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR_PKG__OWNED_ACTORS = eINSTANCE.getActorPkg_OwnedActors();

		/**
		 * The meta object literal for the '<em><b>Owned Actor Pkgs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR_PKG__OWNED_ACTOR_PKGS = eINSTANCE.getActorPkg_OwnedActorPkgs();

		/**
		 * The meta object literal for the '<em><b>Owned System Communication</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR_PKG__OWNED_SYSTEM_COMMUNICATION = eINSTANCE.getActorPkg_OwnedSystemCommunication();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.MissionImpl <em>Mission</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.MissionImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getMission()
		 * @generated
		 */
		EClass MISSION = eINSTANCE.getMission();

		/**
		 * The meta object literal for the '<em><b>Owned Actor Mission Involvements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MISSION__OWNED_ACTOR_MISSION_INVOLVEMENTS = eINSTANCE.getMission_OwnedActorMissionInvolvements();

		/**
		 * The meta object literal for the '<em><b>Owned System Mission Involvement</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT = eINSTANCE.getMission_OwnedSystemMissionInvolvement();

		/**
		 * The meta object literal for the '<em><b>Owned Capability Exploitations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MISSION__OWNED_CAPABILITY_EXPLOITATIONS = eINSTANCE.getMission_OwnedCapabilityExploitations();

		/**
		 * The meta object literal for the '<em><b>Participating Actors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MISSION__PARTICIPATING_ACTORS = eINSTANCE.getMission_ParticipatingActors();

		/**
		 * The meta object literal for the '<em><b>Participating System</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MISSION__PARTICIPATING_SYSTEM = eINSTANCE.getMission_ParticipatingSystem();

		/**
		 * The meta object literal for the '<em><b>Involved Actors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MISSION__INVOLVED_ACTORS = eINSTANCE.getMission_InvolvedActors();

		/**
		 * The meta object literal for the '<em><b>Involved System</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MISSION__INVOLVED_SYSTEM = eINSTANCE.getMission_InvolvedSystem();

		/**
		 * The meta object literal for the '<em><b>Exploited Capabilities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MISSION__EXPLOITED_CAPABILITIES = eINSTANCE.getMission_ExploitedCapabilities();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.MissionPkgImpl <em>Mission Pkg</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.MissionPkgImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getMissionPkg()
		 * @generated
		 */
		EClass MISSION_PKG = eINSTANCE.getMissionPkg();

		/**
		 * The meta object literal for the '<em><b>Owned Mission Pkgs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MISSION_PKG__OWNED_MISSION_PKGS = eINSTANCE.getMissionPkg_OwnedMissionPkgs();

		/**
		 * The meta object literal for the '<em><b>Owned Missions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MISSION_PKG__OWNED_MISSIONS = eINSTANCE.getMissionPkg_OwnedMissions();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemMissionInvolvementImpl <em>System Mission Involvement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.SystemMissionInvolvementImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemMissionInvolvement()
		 * @generated
		 */
		EClass SYSTEM_MISSION_INVOLVEMENT = eINSTANCE.getSystemMissionInvolvement();

		/**
		 * The meta object literal for the '<em><b>Mission</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_MISSION_INVOLVEMENT__MISSION = eINSTANCE.getSystemMissionInvolvement_Mission();

		/**
		 * The meta object literal for the '<em><b>System</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_MISSION_INVOLVEMENT__SYSTEM = eINSTANCE.getSystemMissionInvolvement_System();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl <em>Capability</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.CapabilityImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getCapability()
		 * @generated
		 */
		EClass CAPABILITY = eINSTANCE.getCapability();

		/**
		 * The meta object literal for the '<em><b>Owned Actor Capability Involvements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAPABILITY__OWNED_ACTOR_CAPABILITY_INVOLVEMENTS = eINSTANCE.getCapability_OwnedActorCapabilityInvolvements();

		/**
		 * The meta object literal for the '<em><b>Owned System Capability Involvement</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAPABILITY__OWNED_SYSTEM_CAPABILITY_INVOLVEMENT = eINSTANCE.getCapability_OwnedSystemCapabilityInvolvement();

		/**
		 * The meta object literal for the '<em><b>Involved Actors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAPABILITY__INVOLVED_ACTORS = eINSTANCE.getCapability_InvolvedActors();

		/**
		 * The meta object literal for the '<em><b>Involved System</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAPABILITY__INVOLVED_SYSTEM = eINSTANCE.getCapability_InvolvedSystem();

		/**
		 * The meta object literal for the '<em><b>Participating Actors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAPABILITY__PARTICIPATING_ACTORS = eINSTANCE.getCapability_ParticipatingActors();

		/**
		 * The meta object literal for the '<em><b>Participating System</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAPABILITY__PARTICIPATING_SYSTEM = eINSTANCE.getCapability_ParticipatingSystem();

		/**
		 * The meta object literal for the '<em><b>Purposes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAPABILITY__PURPOSES = eINSTANCE.getCapability_Purposes();

		/**
		 * The meta object literal for the '<em><b>Purpose Missions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAPABILITY__PURPOSE_MISSIONS = eINSTANCE.getCapability_PurposeMissions();

		/**
		 * The meta object literal for the '<em><b>Realized Operational Capabilities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAPABILITY__REALIZED_OPERATIONAL_CAPABILITIES = eINSTANCE.getCapability_RealizedOperationalCapabilities();

		/**
		 * The meta object literal for the '<em><b>Realizing Capability Realizations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAPABILITY__REALIZING_CAPABILITY_REALIZATIONS = eINSTANCE.getCapability_RealizingCapabilityRealizations();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.CapabilityExploitationImpl <em>Capability Exploitation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.CapabilityExploitationImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getCapabilityExploitation()
		 * @generated
		 */
		EClass CAPABILITY_EXPLOITATION = eINSTANCE.getCapabilityExploitation();

		/**
		 * The meta object literal for the '<em><b>Mission</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAPABILITY_EXPLOITATION__MISSION = eINSTANCE.getCapabilityExploitation_Mission();

		/**
		 * The meta object literal for the '<em><b>Capability</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAPABILITY_EXPLOITATION__CAPABILITY = eINSTANCE.getCapabilityExploitation_Capability();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.CapabilityPkgImpl <em>Capability Pkg</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.CapabilityPkgImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getCapabilityPkg()
		 * @generated
		 */
		EClass CAPABILITY_PKG = eINSTANCE.getCapabilityPkg();

		/**
		 * The meta object literal for the '<em><b>Owned Capabilities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAPABILITY_PKG__OWNED_CAPABILITIES = eINSTANCE.getCapabilityPkg_OwnedCapabilities();

		/**
		 * The meta object literal for the '<em><b>Owned Capability Pkgs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAPABILITY_PKG__OWNED_CAPABILITY_PKGS = eINSTANCE.getCapabilityPkg_OwnedCapabilityPkgs();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemCapabilityInvolvementImpl <em>System Capability Involvement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.SystemCapabilityInvolvementImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemCapabilityInvolvement()
		 * @generated
		 */
		EClass SYSTEM_CAPABILITY_INVOLVEMENT = eINSTANCE.getSystemCapabilityInvolvement();

		/**
		 * The meta object literal for the '<em><b>Capability</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_CAPABILITY_INVOLVEMENT__CAPABILITY = eINSTANCE.getSystemCapabilityInvolvement_Capability();

		/**
		 * The meta object literal for the '<em><b>System</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_CAPABILITY_INVOLVEMENT__SYSTEM = eINSTANCE.getSystemCapabilityInvolvement_System();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.OperationalActorRealizationImpl <em>Operational Actor Realization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.OperationalActorRealizationImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getOperationalActorRealization()
		 * @generated
		 */
		EClass OPERATIONAL_ACTOR_REALIZATION = eINSTANCE.getOperationalActorRealization();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.OperationalAnalysisRealizationImpl <em>Operational Analysis Realization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.OperationalAnalysisRealizationImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getOperationalAnalysisRealization()
		 * @generated
		 */
		EClass OPERATIONAL_ANALYSIS_REALIZATION = eINSTANCE.getOperationalAnalysisRealization();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.OperationalEntityRealizationImpl <em>Operational Entity Realization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.OperationalEntityRealizationImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getOperationalEntityRealization()
		 * @generated
		 */
		EClass OPERATIONAL_ENTITY_REALIZATION = eINSTANCE.getOperationalEntityRealization();

		/**
		 * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemContextImpl <em>System Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.polarsys.capella.core.data.ctx.impl.SystemContextImpl
		 * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemContext()
		 * @generated
		 */
		EClass SYSTEM_CONTEXT = eINSTANCE.getSystemContext();

	}

} //CtxPackage
