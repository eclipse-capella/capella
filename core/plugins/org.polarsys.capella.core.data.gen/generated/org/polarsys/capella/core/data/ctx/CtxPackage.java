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
package org.polarsys.capella.core.data.ctx;

import org.eclipse.emf.ecore.EAttribute;
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
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='SystemAnalysis aims at defining the system context analysis modelling language. It is named ContextArchitecture due to MDSysE naming inheritance.\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='system' usage\040examples='none' constraints='This package depends on the model CompositeStructure.ecore\r\nThis package depends on the model Interaction.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
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
	String eNS_URI = "http://www.polarsys.org/capella/core/ctx/7.0.0"; //$NON-NLS-1$

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
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS__OWNED_MIGRATED_ELEMENTS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_MIGRATED_ELEMENTS;

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
   * The feature id for the '<em><b>System</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS__SYSTEM = CsPackage.COMPONENT_ARCHITECTURE__SYSTEM;

	/**
   * The feature id for the '<em><b>Owned System Component Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS__OWNED_SYSTEM_COMPONENT_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Mission Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS__OWNED_MISSION_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Contained Capability Pkg</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS__CONTAINED_CAPABILITY_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Contained System Function Pkg</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS__CONTAINED_SYSTEM_FUNCTION_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Operational Analysis Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Allocated Operational Analysis Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSIS_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Allocated Operational Analyses</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSES = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Allocating Logical Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS__ALLOCATING_LOGICAL_ARCHITECTURES = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 7;

	/**
   * The number of structural features of the '<em>System Analysis</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_FEATURE_COUNT = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 8;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemFunctionImpl <em>System Function</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.ctx.impl.SystemFunctionImpl
   * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemFunction()
   * @generated
   */
	int SYSTEM_FUNCTION = 1;

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
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_FUNCTION__OWNED_MIGRATED_ELEMENTS = FaPackage.ABSTRACT_FUNCTION__OWNED_MIGRATED_ELEMENTS;

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
   * The feature id for the '<em><b>Allocating System Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_FUNCTION__ALLOCATING_SYSTEM_COMPONENTS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Realized Operational Activities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_FUNCTION__REALIZED_OPERATIONAL_ACTIVITIES = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Realizing Logical Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_FUNCTION__REALIZING_LOGICAL_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Contained System Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_FUNCTION__CONTAINED_SYSTEM_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Children System Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_FUNCTION__CHILDREN_SYSTEM_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 5;

	/**
   * The number of structural features of the '<em>System Function</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_FUNCTION_FEATURE_COUNT = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 6;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemFunctionPkgImpl <em>System Function Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.ctx.impl.SystemFunctionPkgImpl
   * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemFunctionPkg()
   * @generated
   */
	int SYSTEM_FUNCTION_PKG = 2;

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
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_FUNCTION_PKG__OWNED_MIGRATED_ELEMENTS = FaPackage.FUNCTION_PKG__OWNED_MIGRATED_ELEMENTS;

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
	int SYSTEM_COMMUNICATION_HOOK = 3;

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
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMMUNICATION_HOOK__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

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
	int SYSTEM_COMMUNICATION = 4;

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
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMMUNICATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

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
   * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.CapabilityInvolvementImpl <em>Capability Involvement</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.ctx.impl.CapabilityInvolvementImpl
   * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getCapabilityInvolvement()
   * @generated
   */
	int CAPABILITY_INVOLVEMENT = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__OWNED_EXTENSIONS = CapellacorePackage.INVOLVEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__ID = CapellacorePackage.INVOLVEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__SID = CapellacorePackage.INVOLVEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__CONSTRAINTS = CapellacorePackage.INVOLVEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__OWNED_CONSTRAINTS = CapellacorePackage.INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.INVOLVEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__REALIZED_FLOW = CapellacorePackage.INVOLVEMENT__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__INCOMING_TRACES = CapellacorePackage.INVOLVEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__OUTGOING_TRACES = CapellacorePackage.INVOLVEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__VISIBLE_IN_DOC = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__VISIBLE_IN_LM = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__SUMMARY = CapellacorePackage.INVOLVEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__DESCRIPTION = CapellacorePackage.INVOLVEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__REVIEW = CapellacorePackage.INVOLVEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__STATUS = CapellacorePackage.INVOLVEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__FEATURES = CapellacorePackage.INVOLVEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involver</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__INVOLVER = CapellacorePackage.INVOLVEMENT__INVOLVER;

	/**
   * The feature id for the '<em><b>Involved</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__INVOLVED = CapellacorePackage.INVOLVEMENT__INVOLVED;

	/**
   * The feature id for the '<em><b>System Component</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__SYSTEM_COMPONENT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Capability</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT__CAPABILITY = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Capability Involvement</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_INVOLVEMENT_FEATURE_COUNT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.MissionInvolvementImpl <em>Mission Involvement</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.ctx.impl.MissionInvolvementImpl
   * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getMissionInvolvement()
   * @generated
   */
	int MISSION_INVOLVEMENT = 6;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__OWNED_EXTENSIONS = CapellacorePackage.INVOLVEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__ID = CapellacorePackage.INVOLVEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__SID = CapellacorePackage.INVOLVEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__CONSTRAINTS = CapellacorePackage.INVOLVEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__OWNED_CONSTRAINTS = CapellacorePackage.INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.INVOLVEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__REALIZED_FLOW = CapellacorePackage.INVOLVEMENT__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__INCOMING_TRACES = CapellacorePackage.INVOLVEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__OUTGOING_TRACES = CapellacorePackage.INVOLVEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__VISIBLE_IN_DOC = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__VISIBLE_IN_LM = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__SUMMARY = CapellacorePackage.INVOLVEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__DESCRIPTION = CapellacorePackage.INVOLVEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__REVIEW = CapellacorePackage.INVOLVEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__STATUS = CapellacorePackage.INVOLVEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__FEATURES = CapellacorePackage.INVOLVEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involver</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__INVOLVER = CapellacorePackage.INVOLVEMENT__INVOLVER;

	/**
   * The feature id for the '<em><b>Involved</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__INVOLVED = CapellacorePackage.INVOLVEMENT__INVOLVED;

	/**
   * The feature id for the '<em><b>System Component</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__SYSTEM_COMPONENT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Mission</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT__MISSION = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Mission Involvement</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_INVOLVEMENT_FEATURE_COUNT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.MissionImpl <em>Mission</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.ctx.impl.MissionImpl
   * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getMission()
   * @generated
   */
	int MISSION = 7;

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
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

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
   * The feature id for the '<em><b>Involved Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION__INVOLVED_INVOLVEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Mission Involvements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION__OWNED_MISSION_INVOLVEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Involved System Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION__INVOLVED_SYSTEM_COMPONENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Capability Exploitations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION__OWNED_CAPABILITY_EXPLOITATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Exploited Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION__EXPLOITED_CAPABILITIES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The number of structural features of the '<em>Mission</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.MissionPkgImpl <em>Mission Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.ctx.impl.MissionPkgImpl
   * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getMissionPkg()
   * @generated
   */
	int MISSION_PKG = 8;

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
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int MISSION_PKG__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.STRUCTURE__OWNED_MIGRATED_ELEMENTS;

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
   * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl <em>Capability</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.ctx.impl.CapabilityImpl
   * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getCapability()
   * @generated
   */
	int CAPABILITY = 9;

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
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY__OWNED_MIGRATED_ELEMENTS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_MIGRATED_ELEMENTS;

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
   * The feature id for the '<em><b>Owned Capability Involvements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Involved System Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY__INVOLVED_SYSTEM_COMPONENTS = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Purposes</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY__PURPOSES = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Purpose Missions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY__PURPOSE_MISSIONS = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Realized Operational Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY__REALIZED_OPERATIONAL_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Realizing Capability Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY__REALIZING_CAPABILITY_REALIZATIONS = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 5;

	/**
   * The number of structural features of the '<em>Capability</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_FEATURE_COUNT = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 6;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.CapabilityExploitationImpl <em>Capability Exploitation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.ctx.impl.CapabilityExploitationImpl
   * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getCapabilityExploitation()
   * @generated
   */
	int CAPABILITY_EXPLOITATION = 10;

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
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_EXPLOITATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

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
   * The feature id for the '<em><b>Mission</b></em>' reference.
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
	int CAPABILITY_PKG = 11;

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
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_PKG__OWNED_MIGRATED_ELEMENTS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_MIGRATED_ELEMENTS;

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
   * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.OperationalAnalysisRealizationImpl <em>Operational Analysis Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.ctx.impl.OperationalAnalysisRealizationImpl
   * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getOperationalAnalysisRealization()
   * @generated
   */
	int OPERATIONAL_ANALYSIS_REALIZATION = 12;

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
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int OPERATIONAL_ANALYSIS_REALIZATION__OWNED_MIGRATED_ELEMENTS = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_MIGRATED_ELEMENTS;

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
   * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentPkgImpl <em>System Component Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.ctx.impl.SystemComponentPkgImpl
   * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemComponentPkg()
   * @generated
   */
	int SYSTEM_COMPONENT_PKG = 13;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_EXTENSIONS = CsPackage.COMPONENT_PKG__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__ID = CsPackage.COMPONENT_PKG__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__SID = CsPackage.COMPONENT_PKG__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__CONSTRAINTS = CsPackage.COMPONENT_PKG__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_CONSTRAINTS = CsPackage.COMPONENT_PKG__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_MIGRATED_ELEMENTS = CsPackage.COMPONENT_PKG__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__NAME = CsPackage.COMPONENT_PKG__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__INCOMING_TRACES = CsPackage.COMPONENT_PKG__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OUTGOING_TRACES = CsPackage.COMPONENT_PKG__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__VISIBLE_IN_DOC = CsPackage.COMPONENT_PKG__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__VISIBLE_IN_LM = CsPackage.COMPONENT_PKG__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__SUMMARY = CsPackage.COMPONENT_PKG__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__DESCRIPTION = CsPackage.COMPONENT_PKG__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__REVIEW = CsPackage.COMPONENT_PKG__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_PKG__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__STATUS = CsPackage.COMPONENT_PKG__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__FEATURES = CsPackage.COMPONENT_PKG__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_TRACES = CsPackage.COMPONENT_PKG__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__CONTAINED_GENERIC_TRACES = CsPackage.COMPONENT_PKG__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__NAMING_RULES = CsPackage.COMPONENT_PKG__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_PROPERTY_VALUE_PKGS = CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Parts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_PARTS = CsPackage.COMPONENT_PKG__OWNED_PARTS;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES = CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS = CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS = CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_PHYSICAL_LINKS = CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_PHYSICAL_LINK_CATEGORIES = CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_STATE_MACHINES = CsPackage.COMPONENT_PKG__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Owned System Components</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENTS = CsPackage.COMPONENT_PKG_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned System Component Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENT_PKGS = CsPackage.COMPONENT_PKG_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>System Component Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_PKG_FEATURE_COUNT = CsPackage.COMPONENT_PKG_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl <em>System Component</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl
   * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemComponent()
   * @generated
   */
	int SYSTEM_COMPONENT = 14;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_EXTENSIONS = CsPackage.COMPONENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__ID = CsPackage.COMPONENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__SID = CsPackage.COMPONENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__CONSTRAINTS = CsPackage.COMPONENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_CONSTRAINTS = CsPackage.COMPONENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_MIGRATED_ELEMENTS = CsPackage.COMPONENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__NAME = CsPackage.COMPONENT__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__ABSTRACT_TYPED_ELEMENTS = CsPackage.COMPONENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__INCOMING_TRACES = CsPackage.COMPONENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OUTGOING_TRACES = CsPackage.COMPONENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__VISIBLE_IN_DOC = CsPackage.COMPONENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__VISIBLE_IN_LM = CsPackage.COMPONENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__SUMMARY = CsPackage.COMPONENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__DESCRIPTION = CsPackage.COMPONENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__REVIEW = CsPackage.COMPONENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__STATUS = CsPackage.COMPONENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__FEATURES = CsPackage.COMPONENT__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_TRACES = CsPackage.COMPONENT__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__CONTAINED_GENERIC_TRACES = CsPackage.COMPONENT__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__NAMING_RULES = CsPackage.COMPONENT__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__TYPED_ELEMENTS = CsPackage.COMPONENT__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION = CsPackage.COMPONENT__OWNED_FUNCTIONAL_ALLOCATION;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_COMPONENT_EXCHANGES = CsPackage.COMPONENT__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__FUNCTIONAL_ALLOCATIONS = CsPackage.COMPONENT__FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__ALLOCATED_FUNCTIONS = CsPackage.COMPONENT__ALLOCATED_FUNCTIONS;

	/**
   * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__IN_EXCHANGE_LINKS = CsPackage.COMPONENT__IN_EXCHANGE_LINKS;

	/**
   * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OUT_EXCHANGE_LINKS = CsPackage.COMPONENT__OUT_EXCHANGE_LINKS;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG = CsPackage.COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
   * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_INTERFACE_PKG = CsPackage.COMPONENT__OWNED_INTERFACE_PKG;

	/**
   * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_DATA_PKG = CsPackage.COMPONENT__OWNED_DATA_PKG;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_STATE_MACHINES = CsPackage.COMPONENT__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__ABSTRACT = CsPackage.COMPONENT__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_GENERALIZATIONS = CsPackage.COMPONENT__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__SUPER_GENERALIZATIONS = CsPackage.COMPONENT__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__SUB_GENERALIZATIONS = CsPackage.COMPONENT__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__SUPER = CsPackage.COMPONENT__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__SUB = CsPackage.COMPONENT__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_FEATURES = CsPackage.COMPONENT__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__CONTAINED_PROPERTIES = CsPackage.COMPONENT__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_INTERFACE_ALLOCATIONS = CsPackage.COMPONENT__OWNED_INTERFACE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS = CsPackage.COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__ALLOCATED_INTERFACES = CsPackage.COMPONENT__ALLOCATED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_COMMUNICATION_LINKS = CsPackage.COMPONENT__OWNED_COMMUNICATION_LINKS;

	/**
   * The feature id for the '<em><b>Produce</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__PRODUCE = CsPackage.COMPONENT__PRODUCE;

	/**
   * The feature id for the '<em><b>Consume</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__CONSUME = CsPackage.COMPONENT__CONSUME;

	/**
   * The feature id for the '<em><b>Send</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__SEND = CsPackage.COMPONENT__SEND;

	/**
   * The feature id for the '<em><b>Receive</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__RECEIVE = CsPackage.COMPONENT__RECEIVE;

	/**
   * The feature id for the '<em><b>Call</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__CALL = CsPackage.COMPONENT__CALL;

	/**
   * The feature id for the '<em><b>Execute</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__EXECUTE = CsPackage.COMPONENT__EXECUTE;

	/**
   * The feature id for the '<em><b>Write</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__WRITE = CsPackage.COMPONENT__WRITE;

	/**
   * The feature id for the '<em><b>Access</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__ACCESS = CsPackage.COMPONENT__ACCESS;

	/**
   * The feature id for the '<em><b>Acquire</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__ACQUIRE = CsPackage.COMPONENT__ACQUIRE;

	/**
   * The feature id for the '<em><b>Transmit</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__TRANSMIT = CsPackage.COMPONENT__TRANSMIT;

	/**
   * The feature id for the '<em><b>Actor</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__ACTOR = CsPackage.COMPONENT__ACTOR;

	/**
   * The feature id for the '<em><b>Human</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__HUMAN = CsPackage.COMPONENT__HUMAN;

	/**
   * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_INTERFACE_USES = CsPackage.COMPONENT__OWNED_INTERFACE_USES;

	/**
   * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__USED_INTERFACE_LINKS = CsPackage.COMPONENT__USED_INTERFACE_LINKS;

	/**
   * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__USED_INTERFACES = CsPackage.COMPONENT__USED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS = CsPackage.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
   * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__IMPLEMENTED_INTERFACE_LINKS = CsPackage.COMPONENT__IMPLEMENTED_INTERFACE_LINKS;

	/**
   * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__IMPLEMENTED_INTERFACES = CsPackage.COMPONENT__IMPLEMENTED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Component Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_COMPONENT_REALIZATIONS = CsPackage.COMPONENT__OWNED_COMPONENT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__REALIZED_COMPONENTS = CsPackage.COMPONENT__REALIZED_COMPONENTS;

	/**
   * The feature id for the '<em><b>Realizing Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__REALIZING_COMPONENTS = CsPackage.COMPONENT__REALIZING_COMPONENTS;

	/**
   * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__PROVIDED_INTERFACES = CsPackage.COMPONENT__PROVIDED_INTERFACES;

	/**
   * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__REQUIRED_INTERFACES = CsPackage.COMPONENT__REQUIRED_INTERFACES;

	/**
   * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__CONTAINED_COMPONENT_PORTS = CsPackage.COMPONENT__CONTAINED_COMPONENT_PORTS;

	/**
   * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__CONTAINED_PARTS = CsPackage.COMPONENT__CONTAINED_PARTS;

	/**
   * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__CONTAINED_PHYSICAL_PORTS = CsPackage.COMPONENT__CONTAINED_PHYSICAL_PORTS;

	/**
   * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_PHYSICAL_PATH = CsPackage.COMPONENT__OWNED_PHYSICAL_PATH;

	/**
   * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_PHYSICAL_LINKS = CsPackage.COMPONENT__OWNED_PHYSICAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES = CsPackage.COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
   * The feature id for the '<em><b>Representing Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__REPRESENTING_PARTS = CsPackage.COMPONENT__REPRESENTING_PARTS;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__INVOLVING_INVOLVEMENTS = CsPackage.COMPONENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned System Components</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENTS = CsPackage.COMPONENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned System Component Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENT_PKGS = CsPackage.COMPONENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Data Component</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__DATA_COMPONENT = CsPackage.COMPONENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Data Type</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__DATA_TYPE = CsPackage.COMPONENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Involving Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__INVOLVING_CAPABILITIES = CsPackage.COMPONENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Capability Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__CAPABILITY_INVOLVEMENTS = CsPackage.COMPONENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Involving Missions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__INVOLVING_MISSIONS = CsPackage.COMPONENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Mission Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__MISSION_INVOLVEMENTS = CsPackage.COMPONENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Realized Entities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__REALIZED_ENTITIES = CsPackage.COMPONENT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Realizing Logical Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__REALIZING_LOGICAL_COMPONENTS = CsPackage.COMPONENT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Allocated System Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT__ALLOCATED_SYSTEM_FUNCTIONS = CsPackage.COMPONENT_FEATURE_COUNT + 11;

	/**
   * The number of structural features of the '<em>System Component</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_COMPONENT_FEATURE_COUNT = CsPackage.COMPONENT_FEATURE_COUNT + 12;

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
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedSystemComponentPkg <em>Owned System Component Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned System Component Pkg</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedSystemComponentPkg()
   * @see #getSystemAnalysis()
   * @generated
   */
	EReference getSystemAnalysis_OwnedSystemComponentPkg();

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
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemFunction#getAllocatingSystemComponents <em>Allocating System Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocating System Components</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemFunction#getAllocatingSystemComponents()
   * @see #getSystemFunction()
   * @generated
   */
	EReference getSystemFunction_AllocatingSystemComponents();

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
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.CapabilityInvolvement <em>Capability Involvement</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Capability Involvement</em>'.
   * @see org.polarsys.capella.core.data.ctx.CapabilityInvolvement
   * @generated
   */
	EClass getCapabilityInvolvement();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.CapabilityInvolvement#getSystemComponent <em>System Component</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>System Component</em>'.
   * @see org.polarsys.capella.core.data.ctx.CapabilityInvolvement#getSystemComponent()
   * @see #getCapabilityInvolvement()
   * @generated
   */
	EReference getCapabilityInvolvement_SystemComponent();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.CapabilityInvolvement#getCapability <em>Capability</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Capability</em>'.
   * @see org.polarsys.capella.core.data.ctx.CapabilityInvolvement#getCapability()
   * @see #getCapabilityInvolvement()
   * @generated
   */
	EReference getCapabilityInvolvement_Capability();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.MissionInvolvement <em>Mission Involvement</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mission Involvement</em>'.
   * @see org.polarsys.capella.core.data.ctx.MissionInvolvement
   * @generated
   */
	EClass getMissionInvolvement();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.MissionInvolvement#getSystemComponent <em>System Component</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>System Component</em>'.
   * @see org.polarsys.capella.core.data.ctx.MissionInvolvement#getSystemComponent()
   * @see #getMissionInvolvement()
   * @generated
   */
	EReference getMissionInvolvement_SystemComponent();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.MissionInvolvement#getMission <em>Mission</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Mission</em>'.
   * @see org.polarsys.capella.core.data.ctx.MissionInvolvement#getMission()
   * @see #getMissionInvolvement()
   * @generated
   */
	EReference getMissionInvolvement_Mission();

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
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.Mission#getOwnedMissionInvolvements <em>Owned Mission Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Mission Involvements</em>'.
   * @see org.polarsys.capella.core.data.ctx.Mission#getOwnedMissionInvolvements()
   * @see #getMission()
   * @generated
   */
	EReference getMission_OwnedMissionInvolvements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Mission#getInvolvedSystemComponents <em>Involved System Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involved System Components</em>'.
   * @see org.polarsys.capella.core.data.ctx.Mission#getInvolvedSystemComponents()
   * @see #getMission()
   * @generated
   */
	EReference getMission_InvolvedSystemComponents();

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
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.Capability <em>Capability</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Capability</em>'.
   * @see org.polarsys.capella.core.data.ctx.Capability
   * @generated
   */
	EClass getCapability();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.Capability#getOwnedCapabilityInvolvements <em>Owned Capability Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Capability Involvements</em>'.
   * @see org.polarsys.capella.core.data.ctx.Capability#getOwnedCapabilityInvolvements()
   * @see #getCapability()
   * @generated
   */
	EReference getCapability_OwnedCapabilityInvolvements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.Capability#getInvolvedSystemComponents <em>Involved System Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involved System Components</em>'.
   * @see org.polarsys.capella.core.data.ctx.Capability#getInvolvedSystemComponents()
   * @see #getCapability()
   * @generated
   */
	EReference getCapability_InvolvedSystemComponents();

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
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.ctx.CapabilityExploitation#getMission <em>Mission</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Mission</em>'.
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
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization <em>Operational Analysis Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operational Analysis Realization</em>'.
   * @see org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization
   * @generated
   */
	EClass getOperationalAnalysisRealization();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.SystemComponentPkg <em>System Component Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>System Component Pkg</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemComponentPkg
   * @generated
   */
	EClass getSystemComponentPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.SystemComponentPkg#getOwnedSystemComponents <em>Owned System Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned System Components</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemComponentPkg#getOwnedSystemComponents()
   * @see #getSystemComponentPkg()
   * @generated
   */
	EReference getSystemComponentPkg_OwnedSystemComponents();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.SystemComponentPkg#getOwnedSystemComponentPkgs <em>Owned System Component Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned System Component Pkgs</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemComponentPkg#getOwnedSystemComponentPkgs()
   * @see #getSystemComponentPkg()
   * @generated
   */
	EReference getSystemComponentPkg_OwnedSystemComponentPkgs();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.ctx.SystemComponent <em>System Component</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>System Component</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemComponent
   * @generated
   */
	EClass getSystemComponent();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.SystemComponent#getOwnedSystemComponents <em>Owned System Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned System Components</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemComponent#getOwnedSystemComponents()
   * @see #getSystemComponent()
   * @generated
   */
	EReference getSystemComponent_OwnedSystemComponents();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.ctx.SystemComponent#getOwnedSystemComponentPkgs <em>Owned System Component Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned System Component Pkgs</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemComponent#getOwnedSystemComponentPkgs()
   * @see #getSystemComponent()
   * @generated
   */
	EReference getSystemComponent_OwnedSystemComponentPkgs();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.ctx.SystemComponent#isDataComponent <em>Data Component</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Component</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemComponent#isDataComponent()
   * @see #getSystemComponent()
   * @generated
   */
	EAttribute getSystemComponent_DataComponent();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemComponent#getDataType <em>Data Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Data Type</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemComponent#getDataType()
   * @see #getSystemComponent()
   * @generated
   */
	EReference getSystemComponent_DataType();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemComponent#getInvolvingCapabilities <em>Involving Capabilities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involving Capabilities</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemComponent#getInvolvingCapabilities()
   * @see #getSystemComponent()
   * @generated
   */
	EReference getSystemComponent_InvolvingCapabilities();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemComponent#getCapabilityInvolvements <em>Capability Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Capability Involvements</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemComponent#getCapabilityInvolvements()
   * @see #getSystemComponent()
   * @generated
   */
	EReference getSystemComponent_CapabilityInvolvements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemComponent#getInvolvingMissions <em>Involving Missions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involving Missions</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemComponent#getInvolvingMissions()
   * @see #getSystemComponent()
   * @generated
   */
	EReference getSystemComponent_InvolvingMissions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemComponent#getMissionInvolvements <em>Mission Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Mission Involvements</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemComponent#getMissionInvolvements()
   * @see #getSystemComponent()
   * @generated
   */
	EReference getSystemComponent_MissionInvolvements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemComponent#getRealizedEntities <em>Realized Entities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Entities</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemComponent#getRealizedEntities()
   * @see #getSystemComponent()
   * @generated
   */
	EReference getSystemComponent_RealizedEntities();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemComponent#getRealizingLogicalComponents <em>Realizing Logical Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Logical Components</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemComponent#getRealizingLogicalComponents()
   * @see #getSystemComponent()
   * @generated
   */
	EReference getSystemComponent_RealizingLogicalComponents();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.ctx.SystemComponent#getAllocatedSystemFunctions <em>Allocated System Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated System Functions</em>'.
   * @see org.polarsys.capella.core.data.ctx.SystemComponent#getAllocatedSystemFunctions()
   * @see #getSystemComponent()
   * @generated
   */
	EReference getSystemComponent_AllocatedSystemFunctions();

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
     * The meta object literal for the '<em><b>Owned System Component Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_ANALYSIS__OWNED_SYSTEM_COMPONENT_PKG = eINSTANCE.getSystemAnalysis_OwnedSystemComponentPkg();

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
     * The meta object literal for the '<em><b>Allocating System Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_FUNCTION__ALLOCATING_SYSTEM_COMPONENTS = eINSTANCE.getSystemFunction_AllocatingSystemComponents();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.CapabilityInvolvementImpl <em>Capability Involvement</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.ctx.impl.CapabilityInvolvementImpl
     * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getCapabilityInvolvement()
     * @generated
     */
		EClass CAPABILITY_INVOLVEMENT = eINSTANCE.getCapabilityInvolvement();

		/**
     * The meta object literal for the '<em><b>System Component</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPABILITY_INVOLVEMENT__SYSTEM_COMPONENT = eINSTANCE.getCapabilityInvolvement_SystemComponent();

		/**
     * The meta object literal for the '<em><b>Capability</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPABILITY_INVOLVEMENT__CAPABILITY = eINSTANCE.getCapabilityInvolvement_Capability();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.MissionInvolvementImpl <em>Mission Involvement</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.ctx.impl.MissionInvolvementImpl
     * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getMissionInvolvement()
     * @generated
     */
		EClass MISSION_INVOLVEMENT = eINSTANCE.getMissionInvolvement();

		/**
     * The meta object literal for the '<em><b>System Component</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MISSION_INVOLVEMENT__SYSTEM_COMPONENT = eINSTANCE.getMissionInvolvement_SystemComponent();

		/**
     * The meta object literal for the '<em><b>Mission</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MISSION_INVOLVEMENT__MISSION = eINSTANCE.getMissionInvolvement_Mission();

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
     * The meta object literal for the '<em><b>Owned Mission Involvements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MISSION__OWNED_MISSION_INVOLVEMENTS = eINSTANCE.getMission_OwnedMissionInvolvements();

		/**
     * The meta object literal for the '<em><b>Involved System Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MISSION__INVOLVED_SYSTEM_COMPONENTS = eINSTANCE.getMission_InvolvedSystemComponents();

		/**
     * The meta object literal for the '<em><b>Owned Capability Exploitations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference MISSION__OWNED_CAPABILITY_EXPLOITATIONS = eINSTANCE.getMission_OwnedCapabilityExploitations();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl <em>Capability</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.ctx.impl.CapabilityImpl
     * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getCapability()
     * @generated
     */
		EClass CAPABILITY = eINSTANCE.getCapability();

		/**
     * The meta object literal for the '<em><b>Owned Capability Involvements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS = eINSTANCE.getCapability_OwnedCapabilityInvolvements();

		/**
     * The meta object literal for the '<em><b>Involved System Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPABILITY__INVOLVED_SYSTEM_COMPONENTS = eINSTANCE.getCapability_InvolvedSystemComponents();

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
     * The meta object literal for the '<em><b>Mission</b></em>' reference feature.
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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.OperationalAnalysisRealizationImpl <em>Operational Analysis Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.ctx.impl.OperationalAnalysisRealizationImpl
     * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getOperationalAnalysisRealization()
     * @generated
     */
		EClass OPERATIONAL_ANALYSIS_REALIZATION = eINSTANCE.getOperationalAnalysisRealization();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentPkgImpl <em>System Component Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.ctx.impl.SystemComponentPkgImpl
     * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemComponentPkg()
     * @generated
     */
		EClass SYSTEM_COMPONENT_PKG = eINSTANCE.getSystemComponentPkg();

		/**
     * The meta object literal for the '<em><b>Owned System Components</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENTS = eINSTANCE.getSystemComponentPkg_OwnedSystemComponents();

		/**
     * The meta object literal for the '<em><b>Owned System Component Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENT_PKGS = eINSTANCE.getSystemComponentPkg_OwnedSystemComponentPkgs();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl <em>System Component</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.ctx.impl.SystemComponentImpl
     * @see org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl#getSystemComponent()
     * @generated
     */
		EClass SYSTEM_COMPONENT = eINSTANCE.getSystemComponent();

		/**
     * The meta object literal for the '<em><b>Owned System Components</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENTS = eINSTANCE.getSystemComponent_OwnedSystemComponents();

		/**
     * The meta object literal for the '<em><b>Owned System Component Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENT_PKGS = eINSTANCE.getSystemComponent_OwnedSystemComponentPkgs();

		/**
     * The meta object literal for the '<em><b>Data Component</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute SYSTEM_COMPONENT__DATA_COMPONENT = eINSTANCE.getSystemComponent_DataComponent();

		/**
     * The meta object literal for the '<em><b>Data Type</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_COMPONENT__DATA_TYPE = eINSTANCE.getSystemComponent_DataType();

		/**
     * The meta object literal for the '<em><b>Involving Capabilities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_COMPONENT__INVOLVING_CAPABILITIES = eINSTANCE.getSystemComponent_InvolvingCapabilities();

		/**
     * The meta object literal for the '<em><b>Capability Involvements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_COMPONENT__CAPABILITY_INVOLVEMENTS = eINSTANCE.getSystemComponent_CapabilityInvolvements();

		/**
     * The meta object literal for the '<em><b>Involving Missions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_COMPONENT__INVOLVING_MISSIONS = eINSTANCE.getSystemComponent_InvolvingMissions();

		/**
     * The meta object literal for the '<em><b>Mission Involvements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_COMPONENT__MISSION_INVOLVEMENTS = eINSTANCE.getSystemComponent_MissionInvolvements();

		/**
     * The meta object literal for the '<em><b>Realized Entities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_COMPONENT__REALIZED_ENTITIES = eINSTANCE.getSystemComponent_RealizedEntities();

		/**
     * The meta object literal for the '<em><b>Realizing Logical Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_COMPONENT__REALIZING_LOGICAL_COMPONENTS = eINSTANCE.getSystemComponent_RealizingLogicalComponents();

		/**
     * The meta object literal for the '<em><b>Allocated System Functions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference SYSTEM_COMPONENT__ALLOCATED_SYSTEM_FUNCTIONS = eINSTANCE.getSystemComponent_AllocatedSystemFunctions();

	}

} //CtxPackage
