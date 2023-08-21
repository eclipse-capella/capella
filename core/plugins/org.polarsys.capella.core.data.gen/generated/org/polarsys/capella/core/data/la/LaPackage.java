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
package org.polarsys.capella.core.data.la;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
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
 * @see org.polarsys.capella.core.data.la.LaFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='LogicalAnalysis aims at defining the system logical analysis modelling language (close to the OMG Computation Independent Model (CIM)). \r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='logical' usage\040examples='none' constraints='This package depends on the model CompositeStructure.ecore\r\nThis package depends on the model Interaction.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface LaPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "la"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/core/la/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.core.data.la"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	LaPackage eINSTANCE = org.polarsys.capella.core.data.la.impl.LaPackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.la.impl.LogicalArchitecturePkgImpl <em>Logical Architecture Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.la.impl.LogicalArchitecturePkgImpl
   * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getLogicalArchitecturePkg()
   * @generated
   */
	int LOGICAL_ARCHITECTURE_PKG = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__OWNED_EXTENSIONS = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__ID = CsPackage.BLOCK_ARCHITECTURE_PKG__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__SID = CsPackage.BLOCK_ARCHITECTURE_PKG__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__CONSTRAINTS = CsPackage.BLOCK_ARCHITECTURE_PKG__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__OWNED_CONSTRAINTS = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__OWNED_MIGRATED_ELEMENTS = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__NAME = CsPackage.BLOCK_ARCHITECTURE_PKG__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__INCOMING_TRACES = CsPackage.BLOCK_ARCHITECTURE_PKG__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__OUTGOING_TRACES = CsPackage.BLOCK_ARCHITECTURE_PKG__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__VISIBLE_IN_DOC = CsPackage.BLOCK_ARCHITECTURE_PKG__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__VISIBLE_IN_LM = CsPackage.BLOCK_ARCHITECTURE_PKG__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__SUMMARY = CsPackage.BLOCK_ARCHITECTURE_PKG__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__DESCRIPTION = CsPackage.BLOCK_ARCHITECTURE_PKG__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__REVIEW = CsPackage.BLOCK_ARCHITECTURE_PKG__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUES = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUES = CsPackage.BLOCK_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.BLOCK_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__STATUS = CsPackage.BLOCK_ARCHITECTURE_PKG__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__FEATURES = CsPackage.BLOCK_ARCHITECTURE_PKG__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__OWNED_TRACES = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__CONTAINED_GENERIC_TRACES = CsPackage.BLOCK_ARCHITECTURE_PKG__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__NAMING_RULES = CsPackage.BLOCK_ARCHITECTURE_PKG__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_PKGS = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Logical Architectures</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG__OWNED_LOGICAL_ARCHITECTURES = CsPackage.BLOCK_ARCHITECTURE_PKG_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Logical Architecture Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_PKG_FEATURE_COUNT = CsPackage.BLOCK_ARCHITECTURE_PKG_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.la.impl.LogicalArchitectureImpl <em>Logical Architecture</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.la.impl.LogicalArchitectureImpl
   * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getLogicalArchitecture()
   * @generated
   */
	int LOGICAL_ARCHITECTURE = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_EXTENSIONS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__ID = CsPackage.COMPONENT_ARCHITECTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__SID = CsPackage.COMPONENT_ARCHITECTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__CONSTRAINTS = CsPackage.COMPONENT_ARCHITECTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_CONSTRAINTS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_MIGRATED_ELEMENTS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__NAME = CsPackage.COMPONENT_ARCHITECTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__INCOMING_TRACES = CsPackage.COMPONENT_ARCHITECTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OUTGOING_TRACES = CsPackage.COMPONENT_ARCHITECTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__VISIBLE_IN_DOC = CsPackage.COMPONENT_ARCHITECTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__VISIBLE_IN_LM = CsPackage.COMPONENT_ARCHITECTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__SUMMARY = CsPackage.COMPONENT_ARCHITECTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__DESCRIPTION = CsPackage.COMPONENT_ARCHITECTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__REVIEW = CsPackage.COMPONENT_ARCHITECTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_ARCHITECTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ARCHITECTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__STATUS = CsPackage.COMPONENT_ARCHITECTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__FEATURES = CsPackage.COMPONENT_ARCHITECTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_TRACES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__CONTAINED_GENERIC_TRACES = CsPackage.COMPONENT_ARCHITECTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__NAMING_RULES = CsPackage.COMPONENT_ARCHITECTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_PROPERTY_VALUE_PKGS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Function Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_FUNCTION_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_FUNCTION_PKG;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_FUNCTIONAL_LINKS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_FUNCTIONAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_FUNCTIONAL_ALLOCATIONS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
   * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_INTERFACE_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_INTERFACE_PKG;

	/**
   * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_DATA_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_DATA_PKG;

	/**
   * The feature id for the '<em><b>Provisioned Architecture Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS = CsPackage.COMPONENT_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Provisioning Architecture Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS = CsPackage.COMPONENT_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__ALLOCATED_ARCHITECTURES = CsPackage.COMPONENT_ARCHITECTURE__ALLOCATED_ARCHITECTURES;

	/**
   * The feature id for the '<em><b>Allocating Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__ALLOCATING_ARCHITECTURES = CsPackage.COMPONENT_ARCHITECTURE__ALLOCATING_ARCHITECTURES;

	/**
   * The feature id for the '<em><b>System</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__SYSTEM = CsPackage.COMPONENT_ARCHITECTURE__SYSTEM;

	/**
   * The feature id for the '<em><b>Owned Logical Component Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Contained Capability Realization Pkg</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Contained Logical Function Pkg</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__CONTAINED_LOGICAL_FUNCTION_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned System Analysis Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Allocated System Analysis Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSIS_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Allocated System Analyses</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSES = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Allocating Physical Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE__ALLOCATING_PHYSICAL_ARCHITECTURES = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 6;

	/**
   * The number of structural features of the '<em>Logical Architecture</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_ARCHITECTURE_FEATURE_COUNT = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 7;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.la.impl.LogicalFunctionImpl <em>Logical Function</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.la.impl.LogicalFunctionImpl
   * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getLogicalFunction()
   * @generated
   */
	int LOGICAL_FUNCTION = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_EXTENSIONS = FaPackage.ABSTRACT_FUNCTION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__ID = FaPackage.ABSTRACT_FUNCTION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__SID = FaPackage.ABSTRACT_FUNCTION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__CONSTRAINTS = FaPackage.ABSTRACT_FUNCTION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_CONSTRAINTS = FaPackage.ABSTRACT_FUNCTION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_MIGRATED_ELEMENTS = FaPackage.ABSTRACT_FUNCTION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__NAME = FaPackage.ABSTRACT_FUNCTION__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__INCOMING_TRACES = FaPackage.ABSTRACT_FUNCTION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OUTGOING_TRACES = FaPackage.ABSTRACT_FUNCTION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__VISIBLE_IN_DOC = FaPackage.ABSTRACT_FUNCTION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__VISIBLE_IN_LM = FaPackage.ABSTRACT_FUNCTION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__SUMMARY = FaPackage.ABSTRACT_FUNCTION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__DESCRIPTION = FaPackage.ABSTRACT_FUNCTION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__REVIEW = FaPackage.ABSTRACT_FUNCTION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_PROPERTY_VALUES = FaPackage.ABSTRACT_FUNCTION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_ENUMERATION_PROPERTY_TYPES = FaPackage.ABSTRACT_FUNCTION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__APPLIED_PROPERTY_VALUES = FaPackage.ABSTRACT_FUNCTION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_PROPERTY_VALUE_GROUPS = FaPackage.ABSTRACT_FUNCTION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__APPLIED_PROPERTY_VALUE_GROUPS = FaPackage.ABSTRACT_FUNCTION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__STATUS = FaPackage.ABSTRACT_FUNCTION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__FEATURES = FaPackage.ABSTRACT_FUNCTION__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_TRACES = FaPackage.ABSTRACT_FUNCTION__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__CONTAINED_GENERIC_TRACES = FaPackage.ABSTRACT_FUNCTION__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__NAMING_RULES = FaPackage.ABSTRACT_FUNCTION__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__INVOLVING_INVOLVEMENTS = FaPackage.ABSTRACT_FUNCTION__INVOLVING_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__IS_ABSTRACT = FaPackage.ABSTRACT_FUNCTION__IS_ABSTRACT;

	/**
   * The feature id for the '<em><b>Is Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__IS_STATIC = FaPackage.ABSTRACT_FUNCTION__IS_STATIC;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__VISIBILITY = FaPackage.ABSTRACT_FUNCTION__VISIBILITY;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__ABSTRACT_TYPE = FaPackage.ABSTRACT_FUNCTION__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__TYPE = FaPackage.ABSTRACT_FUNCTION__TYPE;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__ORDERED = FaPackage.ABSTRACT_FUNCTION__ORDERED;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__UNIQUE = FaPackage.ABSTRACT_FUNCTION__UNIQUE;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__MIN_INCLUSIVE = FaPackage.ABSTRACT_FUNCTION__MIN_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__MAX_INCLUSIVE = FaPackage.ABSTRACT_FUNCTION__MAX_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_DEFAULT_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_DEFAULT_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_MIN_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_MIN_VALUE;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_MAX_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_MAX_VALUE;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_NULL_VALUE = FaPackage.ABSTRACT_FUNCTION__OWNED_NULL_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_MIN_CARD = FaPackage.ABSTRACT_FUNCTION__OWNED_MIN_CARD;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_MIN_LENGTH = FaPackage.ABSTRACT_FUNCTION__OWNED_MIN_LENGTH;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_MAX_CARD = FaPackage.ABSTRACT_FUNCTION__OWNED_MAX_CARD;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_MAX_LENGTH = FaPackage.ABSTRACT_FUNCTION__OWNED_MAX_LENGTH;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__FINAL = FaPackage.ABSTRACT_FUNCTION__FINAL;

	/**
   * The feature id for the '<em><b>Aggregation Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__AGGREGATION_KIND = FaPackage.ABSTRACT_FUNCTION__AGGREGATION_KIND;

	/**
   * The feature id for the '<em><b>Is Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__IS_DERIVED = FaPackage.ABSTRACT_FUNCTION__IS_DERIVED;

	/**
   * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__IS_READ_ONLY = FaPackage.ABSTRACT_FUNCTION__IS_READ_ONLY;

	/**
   * The feature id for the '<em><b>Is Part Of Key</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__IS_PART_OF_KEY = FaPackage.ABSTRACT_FUNCTION__IS_PART_OF_KEY;

	/**
   * The feature id for the '<em><b>Association</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__ASSOCIATION = FaPackage.ABSTRACT_FUNCTION__ASSOCIATION;

	/**
   * The feature id for the '<em><b>Representing Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__REPRESENTING_INSTANCE_ROLES = FaPackage.ABSTRACT_FUNCTION__REPRESENTING_INSTANCE_ROLES;

	/**
   * The feature id for the '<em><b>Owned Functional Chains</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_FUNCTIONAL_CHAINS = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_CHAINS;

	/**
   * The feature id for the '<em><b>In Activity Partition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__IN_ACTIVITY_PARTITION = FaPackage.ABSTRACT_FUNCTION__IN_ACTIVITY_PARTITION;

	/**
   * The feature id for the '<em><b>In Interruptible Region</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__IN_INTERRUPTIBLE_REGION = FaPackage.ABSTRACT_FUNCTION__IN_INTERRUPTIBLE_REGION;

	/**
   * The feature id for the '<em><b>In Structured Node</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__IN_STRUCTURED_NODE = FaPackage.ABSTRACT_FUNCTION__IN_STRUCTURED_NODE;

	/**
   * The feature id for the '<em><b>Outgoing</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OUTGOING = FaPackage.ABSTRACT_FUNCTION__OUTGOING;

	/**
   * The feature id for the '<em><b>Incoming</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__INCOMING = FaPackage.ABSTRACT_FUNCTION__INCOMING;

	/**
   * The feature id for the '<em><b>Owned Handlers</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_HANDLERS = FaPackage.ABSTRACT_FUNCTION__OWNED_HANDLERS;

	/**
   * The feature id for the '<em><b>Local Precondition</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__LOCAL_PRECONDITION = FaPackage.ABSTRACT_FUNCTION__LOCAL_PRECONDITION;

	/**
   * The feature id for the '<em><b>Local Postcondition</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__LOCAL_POSTCONDITION = FaPackage.ABSTRACT_FUNCTION__LOCAL_POSTCONDITION;

	/**
   * The feature id for the '<em><b>Context</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__CONTEXT = FaPackage.ABSTRACT_FUNCTION__CONTEXT;

	/**
   * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__INPUTS = FaPackage.ABSTRACT_FUNCTION__INPUTS;

	/**
   * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OUTPUTS = FaPackage.ABSTRACT_FUNCTION__OUTPUTS;

	/**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__ARGUMENTS = FaPackage.ABSTRACT_FUNCTION__ARGUMENTS;

	/**
   * The feature id for the '<em><b>Results</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__RESULTS = FaPackage.ABSTRACT_FUNCTION__RESULTS;

	/**
   * The feature id for the '<em><b>Behavior</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__BEHAVIOR = FaPackage.ABSTRACT_FUNCTION__BEHAVIOR;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__ABSTRACT_TYPED_ELEMENTS = FaPackage.ABSTRACT_FUNCTION__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__KIND = FaPackage.ABSTRACT_FUNCTION__KIND;

	/**
   * The feature id for the '<em><b>Condition</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__CONDITION = FaPackage.ABSTRACT_FUNCTION__CONDITION;

	/**
   * The feature id for the '<em><b>Owned Functions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTIONS;

	/**
   * The feature id for the '<em><b>Owned Function Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_FUNCTION_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Functional Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES = FaPackage.ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES;

	/**
   * The feature id for the '<em><b>Sub Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__SUB_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION__SUB_FUNCTIONS;

	/**
   * The feature id for the '<em><b>Out Function Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OUT_FUNCTION_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__OUT_FUNCTION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>In Function Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__IN_FUNCTION_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__IN_FUNCTION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Component Functional Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__COMPONENT_FUNCTIONAL_ALLOCATIONS = FaPackage.ABSTRACT_FUNCTION__COMPONENT_FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocation Blocks</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__ALLOCATION_BLOCKS = FaPackage.ABSTRACT_FUNCTION__ALLOCATION_BLOCKS;

	/**
   * The feature id for the '<em><b>Available In States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__AVAILABLE_IN_STATES = FaPackage.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES;

	/**
   * The feature id for the '<em><b>Involving Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__INVOLVING_CAPABILITIES = FaPackage.ABSTRACT_FUNCTION__INVOLVING_CAPABILITIES;

	/**
   * The feature id for the '<em><b>Involving Capability Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__INVOLVING_CAPABILITY_REALIZATIONS = FaPackage.ABSTRACT_FUNCTION__INVOLVING_CAPABILITY_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Involving Functional Chains</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__INVOLVING_FUNCTIONAL_CHAINS = FaPackage.ABSTRACT_FUNCTION__INVOLVING_FUNCTIONAL_CHAINS;

	/**
   * The feature id for the '<em><b>Linked State Machine</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__LINKED_STATE_MACHINE = FaPackage.ABSTRACT_FUNCTION__LINKED_STATE_MACHINE;

	/**
   * The feature id for the '<em><b>Linked Function Specification</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__LINKED_FUNCTION_SPECIFICATION = FaPackage.ABSTRACT_FUNCTION__LINKED_FUNCTION_SPECIFICATION;

	/**
   * The feature id for the '<em><b>Owned Logical Function Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Allocating Logical Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__ALLOCATING_LOGICAL_COMPONENTS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Realized System Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__REALIZED_SYSTEM_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Realizing Physical Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__REALIZING_PHYSICAL_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Contained Logical Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__CONTAINED_LOGICAL_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Children Logical Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION__CHILDREN_LOGICAL_FUNCTIONS = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 5;

	/**
   * The number of structural features of the '<em>Logical Function</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_FEATURE_COUNT = FaPackage.ABSTRACT_FUNCTION_FEATURE_COUNT + 6;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.la.impl.LogicalFunctionPkgImpl <em>Logical Function Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.la.impl.LogicalFunctionPkgImpl
   * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getLogicalFunctionPkg()
   * @generated
   */
	int LOGICAL_FUNCTION_PKG = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OWNED_EXTENSIONS = FaPackage.FUNCTION_PKG__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__ID = FaPackage.FUNCTION_PKG__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__SID = FaPackage.FUNCTION_PKG__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__CONSTRAINTS = FaPackage.FUNCTION_PKG__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OWNED_CONSTRAINTS = FaPackage.FUNCTION_PKG__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OWNED_MIGRATED_ELEMENTS = FaPackage.FUNCTION_PKG__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__NAME = FaPackage.FUNCTION_PKG__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__INCOMING_TRACES = FaPackage.FUNCTION_PKG__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OUTGOING_TRACES = FaPackage.FUNCTION_PKG__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__VISIBLE_IN_DOC = FaPackage.FUNCTION_PKG__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__VISIBLE_IN_LM = FaPackage.FUNCTION_PKG__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__SUMMARY = FaPackage.FUNCTION_PKG__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__DESCRIPTION = FaPackage.FUNCTION_PKG__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__REVIEW = FaPackage.FUNCTION_PKG__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OWNED_PROPERTY_VALUES = FaPackage.FUNCTION_PKG__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = FaPackage.FUNCTION_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__APPLIED_PROPERTY_VALUES = FaPackage.FUNCTION_PKG__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OWNED_PROPERTY_VALUE_GROUPS = FaPackage.FUNCTION_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__APPLIED_PROPERTY_VALUE_GROUPS = FaPackage.FUNCTION_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__STATUS = FaPackage.FUNCTION_PKG__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__FEATURES = FaPackage.FUNCTION_PKG__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OWNED_TRACES = FaPackage.FUNCTION_PKG__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__CONTAINED_GENERIC_TRACES = FaPackage.FUNCTION_PKG__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__NAMING_RULES = FaPackage.FUNCTION_PKG__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OWNED_PROPERTY_VALUE_PKGS = FaPackage.FUNCTION_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OWNED_FUNCTIONAL_LINKS = FaPackage.FUNCTION_PKG__OWNED_FUNCTIONAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OWNED_EXCHANGES = FaPackage.FUNCTION_PKG__OWNED_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Exchange Specification Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OWNED_EXCHANGE_SPECIFICATION_REALIZATIONS = FaPackage.FUNCTION_PKG__OWNED_EXCHANGE_SPECIFICATION_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OWNED_CATEGORIES = FaPackage.FUNCTION_PKG__OWNED_CATEGORIES;

	/**
   * The feature id for the '<em><b>Owned Function Specifications</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OWNED_FUNCTION_SPECIFICATIONS = FaPackage.FUNCTION_PKG__OWNED_FUNCTION_SPECIFICATIONS;

	/**
   * The feature id for the '<em><b>Owned Logical Functions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS = FaPackage.FUNCTION_PKG_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Logical Function Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTION_PKGS = FaPackage.FUNCTION_PKG_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Logical Function Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_FUNCTION_PKG_FEATURE_COUNT = FaPackage.FUNCTION_PKG_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl <em>Logical Component</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.la.impl.LogicalComponentImpl
   * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getLogicalComponent()
   * @generated
   */
	int LOGICAL_COMPONENT = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_EXTENSIONS = CsPackage.COMPONENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__ID = CsPackage.COMPONENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__SID = CsPackage.COMPONENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__CONSTRAINTS = CsPackage.COMPONENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_CONSTRAINTS = CsPackage.COMPONENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_MIGRATED_ELEMENTS = CsPackage.COMPONENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__NAME = CsPackage.COMPONENT__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__ABSTRACT_TYPED_ELEMENTS = CsPackage.COMPONENT__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__INCOMING_TRACES = CsPackage.COMPONENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OUTGOING_TRACES = CsPackage.COMPONENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__VISIBLE_IN_DOC = CsPackage.COMPONENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__VISIBLE_IN_LM = CsPackage.COMPONENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__SUMMARY = CsPackage.COMPONENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__DESCRIPTION = CsPackage.COMPONENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__REVIEW = CsPackage.COMPONENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__STATUS = CsPackage.COMPONENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__FEATURES = CsPackage.COMPONENT__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_TRACES = CsPackage.COMPONENT__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__CONTAINED_GENERIC_TRACES = CsPackage.COMPONENT__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__NAMING_RULES = CsPackage.COMPONENT__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__TYPED_ELEMENTS = CsPackage.COMPONENT__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION = CsPackage.COMPONENT__OWNED_FUNCTIONAL_ALLOCATION;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_COMPONENT_EXCHANGES = CsPackage.COMPONENT__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__FUNCTIONAL_ALLOCATIONS = CsPackage.COMPONENT__FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__ALLOCATED_FUNCTIONS = CsPackage.COMPONENT__ALLOCATED_FUNCTIONS;

	/**
   * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__IN_EXCHANGE_LINKS = CsPackage.COMPONENT__IN_EXCHANGE_LINKS;

	/**
   * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OUT_EXCHANGE_LINKS = CsPackage.COMPONENT__OUT_EXCHANGE_LINKS;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG = CsPackage.COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
   * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_INTERFACE_PKG = CsPackage.COMPONENT__OWNED_INTERFACE_PKG;

	/**
   * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_DATA_PKG = CsPackage.COMPONENT__OWNED_DATA_PKG;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_STATE_MACHINES = CsPackage.COMPONENT__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__ABSTRACT = CsPackage.COMPONENT__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_GENERALIZATIONS = CsPackage.COMPONENT__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__SUPER_GENERALIZATIONS = CsPackage.COMPONENT__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__SUB_GENERALIZATIONS = CsPackage.COMPONENT__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__SUPER = CsPackage.COMPONENT__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__SUB = CsPackage.COMPONENT__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_FEATURES = CsPackage.COMPONENT__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__CONTAINED_PROPERTIES = CsPackage.COMPONENT__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_INTERFACE_ALLOCATIONS = CsPackage.COMPONENT__OWNED_INTERFACE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS = CsPackage.COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__ALLOCATED_INTERFACES = CsPackage.COMPONENT__ALLOCATED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_COMMUNICATION_LINKS = CsPackage.COMPONENT__OWNED_COMMUNICATION_LINKS;

	/**
   * The feature id for the '<em><b>Produce</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__PRODUCE = CsPackage.COMPONENT__PRODUCE;

	/**
   * The feature id for the '<em><b>Consume</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__CONSUME = CsPackage.COMPONENT__CONSUME;

	/**
   * The feature id for the '<em><b>Send</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__SEND = CsPackage.COMPONENT__SEND;

	/**
   * The feature id for the '<em><b>Receive</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__RECEIVE = CsPackage.COMPONENT__RECEIVE;

	/**
   * The feature id for the '<em><b>Call</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__CALL = CsPackage.COMPONENT__CALL;

	/**
   * The feature id for the '<em><b>Execute</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__EXECUTE = CsPackage.COMPONENT__EXECUTE;

	/**
   * The feature id for the '<em><b>Write</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__WRITE = CsPackage.COMPONENT__WRITE;

	/**
   * The feature id for the '<em><b>Access</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__ACCESS = CsPackage.COMPONENT__ACCESS;

	/**
   * The feature id for the '<em><b>Acquire</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__ACQUIRE = CsPackage.COMPONENT__ACQUIRE;

	/**
   * The feature id for the '<em><b>Transmit</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__TRANSMIT = CsPackage.COMPONENT__TRANSMIT;

	/**
   * The feature id for the '<em><b>Actor</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__ACTOR = CsPackage.COMPONENT__ACTOR;

	/**
   * The feature id for the '<em><b>Human</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__HUMAN = CsPackage.COMPONENT__HUMAN;

	/**
   * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_INTERFACE_USES = CsPackage.COMPONENT__OWNED_INTERFACE_USES;

	/**
   * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__USED_INTERFACE_LINKS = CsPackage.COMPONENT__USED_INTERFACE_LINKS;

	/**
   * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__USED_INTERFACES = CsPackage.COMPONENT__USED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS = CsPackage.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS;

	/**
   * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__IMPLEMENTED_INTERFACE_LINKS = CsPackage.COMPONENT__IMPLEMENTED_INTERFACE_LINKS;

	/**
   * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__IMPLEMENTED_INTERFACES = CsPackage.COMPONENT__IMPLEMENTED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Component Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_COMPONENT_REALIZATIONS = CsPackage.COMPONENT__OWNED_COMPONENT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Realized Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__REALIZED_COMPONENTS = CsPackage.COMPONENT__REALIZED_COMPONENTS;

	/**
   * The feature id for the '<em><b>Realizing Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__REALIZING_COMPONENTS = CsPackage.COMPONENT__REALIZING_COMPONENTS;

	/**
   * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__PROVIDED_INTERFACES = CsPackage.COMPONENT__PROVIDED_INTERFACES;

	/**
   * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__REQUIRED_INTERFACES = CsPackage.COMPONENT__REQUIRED_INTERFACES;

	/**
   * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__CONTAINED_COMPONENT_PORTS = CsPackage.COMPONENT__CONTAINED_COMPONENT_PORTS;

	/**
   * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__CONTAINED_PARTS = CsPackage.COMPONENT__CONTAINED_PARTS;

	/**
   * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__CONTAINED_PHYSICAL_PORTS = CsPackage.COMPONENT__CONTAINED_PHYSICAL_PORTS;

	/**
   * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_PHYSICAL_PATH = CsPackage.COMPONENT__OWNED_PHYSICAL_PATH;

	/**
   * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_PHYSICAL_LINKS = CsPackage.COMPONENT__OWNED_PHYSICAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES = CsPackage.COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
   * The feature id for the '<em><b>Representing Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__REPRESENTING_PARTS = CsPackage.COMPONENT__REPRESENTING_PARTS;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__INVOLVING_INVOLVEMENTS = CsPackage.COMPONENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Capability Realization Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__CAPABILITY_REALIZATION_INVOLVEMENTS = CsPackage.COMPONENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Involving Capability Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__INVOLVING_CAPABILITY_REALIZATIONS = CsPackage.COMPONENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Logical Components</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS = CsPackage.COMPONENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Logical Architectures</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_LOGICAL_ARCHITECTURES = CsPackage.COMPONENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Owned Logical Component Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS = CsPackage.COMPONENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Sub Logical Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__SUB_LOGICAL_COMPONENTS = CsPackage.COMPONENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Allocated Logical Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__ALLOCATED_LOGICAL_FUNCTIONS = CsPackage.COMPONENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Realized System Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__REALIZED_SYSTEM_COMPONENTS = CsPackage.COMPONENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Realizing Physical Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT__REALIZING_PHYSICAL_COMPONENTS = CsPackage.COMPONENT_FEATURE_COUNT + 9;

	/**
   * The number of structural features of the '<em>Logical Component</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_FEATURE_COUNT = CsPackage.COMPONENT_FEATURE_COUNT + 10;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.la.impl.LogicalComponentPkgImpl <em>Logical Component Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.la.impl.LogicalComponentPkgImpl
   * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getLogicalComponentPkg()
   * @generated
   */
	int LOGICAL_COMPONENT_PKG = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_EXTENSIONS = CsPackage.COMPONENT_PKG__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__ID = CsPackage.COMPONENT_PKG__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__SID = CsPackage.COMPONENT_PKG__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__CONSTRAINTS = CsPackage.COMPONENT_PKG__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_CONSTRAINTS = CsPackage.COMPONENT_PKG__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_MIGRATED_ELEMENTS = CsPackage.COMPONENT_PKG__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__NAME = CsPackage.COMPONENT_PKG__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__INCOMING_TRACES = CsPackage.COMPONENT_PKG__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OUTGOING_TRACES = CsPackage.COMPONENT_PKG__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__VISIBLE_IN_DOC = CsPackage.COMPONENT_PKG__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__VISIBLE_IN_LM = CsPackage.COMPONENT_PKG__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__SUMMARY = CsPackage.COMPONENT_PKG__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__DESCRIPTION = CsPackage.COMPONENT_PKG__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__REVIEW = CsPackage.COMPONENT_PKG__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_PKG__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__STATUS = CsPackage.COMPONENT_PKG__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__FEATURES = CsPackage.COMPONENT_PKG__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_TRACES = CsPackage.COMPONENT_PKG__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__CONTAINED_GENERIC_TRACES = CsPackage.COMPONENT_PKG__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__NAMING_RULES = CsPackage.COMPONENT_PKG__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_PROPERTY_VALUE_PKGS = CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Parts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_PARTS = CsPackage.COMPONENT_PKG__OWNED_PARTS;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES = CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS = CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS = CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_PHYSICAL_LINKS = CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_PHYSICAL_LINK_CATEGORIES = CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_STATE_MACHINES = CsPackage.COMPONENT_PKG__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Owned Logical Components</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS = CsPackage.COMPONENT_PKG_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Logical Component Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS = CsPackage.COMPONENT_PKG_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Logical Component Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int LOGICAL_COMPONENT_PKG_FEATURE_COUNT = CsPackage.COMPONENT_PKG_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl <em>Capability Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl
   * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getCapabilityRealization()
   * @generated
   */
	int CAPABILITY_REALIZATION = 6;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OWNED_EXTENSIONS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__ID = InteractionPackage.ABSTRACT_CAPABILITY__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__SID = InteractionPackage.ABSTRACT_CAPABILITY__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__CONSTRAINTS = InteractionPackage.ABSTRACT_CAPABILITY__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OWNED_CONSTRAINTS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OWNED_MIGRATED_ELEMENTS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__NAME = InteractionPackage.ABSTRACT_CAPABILITY__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__INCOMING_TRACES = InteractionPackage.ABSTRACT_CAPABILITY__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OUTGOING_TRACES = InteractionPackage.ABSTRACT_CAPABILITY__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__VISIBLE_IN_DOC = InteractionPackage.ABSTRACT_CAPABILITY__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__VISIBLE_IN_LM = InteractionPackage.ABSTRACT_CAPABILITY__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__SUMMARY = InteractionPackage.ABSTRACT_CAPABILITY__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__DESCRIPTION = InteractionPackage.ABSTRACT_CAPABILITY__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__REVIEW = InteractionPackage.ABSTRACT_CAPABILITY__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OWNED_PROPERTY_VALUES = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__APPLIED_PROPERTY_VALUES = InteractionPackage.ABSTRACT_CAPABILITY__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = InteractionPackage.ABSTRACT_CAPABILITY__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__STATUS = InteractionPackage.ABSTRACT_CAPABILITY__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__FEATURES = InteractionPackage.ABSTRACT_CAPABILITY__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OWNED_TRACES = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__CONTAINED_GENERIC_TRACES = InteractionPackage.ABSTRACT_CAPABILITY__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__NAMING_RULES = InteractionPackage.ABSTRACT_CAPABILITY__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OWNED_PROPERTY_VALUE_PKGS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Involved Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__INVOLVED_INVOLVEMENTS = InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Owned Functional Chains</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OWNED_FUNCTIONAL_CHAINS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAINS;

	/**
   * The feature id for the '<em><b>Pre Condition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__PRE_CONDITION = InteractionPackage.ABSTRACT_CAPABILITY__PRE_CONDITION;

	/**
   * The feature id for the '<em><b>Post Condition</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__POST_CONDITION = InteractionPackage.ABSTRACT_CAPABILITY__POST_CONDITION;

	/**
   * The feature id for the '<em><b>Owned Scenarios</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OWNED_SCENARIOS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_SCENARIOS;

	/**
   * The feature id for the '<em><b>Incoming Capability Allocation</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__INCOMING_CAPABILITY_ALLOCATION = InteractionPackage.ABSTRACT_CAPABILITY__INCOMING_CAPABILITY_ALLOCATION;

	/**
   * The feature id for the '<em><b>Outgoing Capability Allocation</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OUTGOING_CAPABILITY_ALLOCATION = InteractionPackage.ABSTRACT_CAPABILITY__OUTGOING_CAPABILITY_ALLOCATION;

	/**
   * The feature id for the '<em><b>Extends</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__EXTENDS = InteractionPackage.ABSTRACT_CAPABILITY__EXTENDS;

	/**
   * The feature id for the '<em><b>Extending</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__EXTENDING = InteractionPackage.ABSTRACT_CAPABILITY__EXTENDING;

	/**
   * The feature id for the '<em><b>Abstract Capability Extension Points</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__ABSTRACT_CAPABILITY_EXTENSION_POINTS = InteractionPackage.ABSTRACT_CAPABILITY__ABSTRACT_CAPABILITY_EXTENSION_POINTS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__SUPER_GENERALIZATIONS = InteractionPackage.ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__SUB_GENERALIZATIONS = InteractionPackage.ABSTRACT_CAPABILITY__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Includes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__INCLUDES = InteractionPackage.ABSTRACT_CAPABILITY__INCLUDES;

	/**
   * The feature id for the '<em><b>Including</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__INCLUDING = InteractionPackage.ABSTRACT_CAPABILITY__INCLUDING;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__SUPER = InteractionPackage.ABSTRACT_CAPABILITY__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__SUB = InteractionPackage.ABSTRACT_CAPABILITY__SUB;

	/**
   * The feature id for the '<em><b>Included Abstract Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__INCLUDED_ABSTRACT_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY__INCLUDED_ABSTRACT_CAPABILITIES;

	/**
   * The feature id for the '<em><b>Including Abstract Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__INCLUDING_ABSTRACT_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY__INCLUDING_ABSTRACT_CAPABILITIES;

	/**
   * The feature id for the '<em><b>Extended Abstract Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__EXTENDED_ABSTRACT_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES;

	/**
   * The feature id for the '<em><b>Extending Abstract Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__EXTENDING_ABSTRACT_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY__EXTENDING_ABSTRACT_CAPABILITIES;

	/**
   * The feature id for the '<em><b>Owned Functional Chain Abstract Capability Involvements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OWNED_FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENTS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Owned Abstract Function Abstract Capability Involvements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OWNED_ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENTS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Available In States</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__AVAILABLE_IN_STATES = InteractionPackage.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS = InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Involved Abstract Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__INVOLVED_ABSTRACT_FUNCTIONS = InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_ABSTRACT_FUNCTIONS;

	/**
   * The feature id for the '<em><b>Involved Functional Chains</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__INVOLVED_FUNCTIONAL_CHAINS = InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_FUNCTIONAL_CHAINS;

	/**
   * The feature id for the '<em><b>Owned Capability Realization Involvements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__OWNED_CAPABILITY_REALIZATION_INVOLVEMENTS = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Involved Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__INVOLVED_COMPONENTS = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Realized Capabilities</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__REALIZED_CAPABILITIES = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Realized Capability Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__REALIZED_CAPABILITY_REALIZATIONS = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Realizing Capability Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION__REALIZING_CAPABILITY_REALIZATIONS = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 4;

	/**
   * The number of structural features of the '<em>Capability Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_FEATURE_COUNT = InteractionPackage.ABSTRACT_CAPABILITY_FEATURE_COUNT + 5;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationPkgImpl <em>Capability Realization Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.la.impl.CapabilityRealizationPkgImpl
   * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getCapabilityRealizationPkg()
   * @generated
   */
	int CAPABILITY_REALIZATION_PKG = 7;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__OWNED_EXTENSIONS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__ID = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__SID = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__CONSTRAINTS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__OWNED_CONSTRAINTS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__OWNED_MIGRATED_ELEMENTS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__NAME = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__INCOMING_TRACES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__OUTGOING_TRACES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__VISIBLE_IN_DOC = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__VISIBLE_IN_LM = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__SUMMARY = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__DESCRIPTION = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__REVIEW = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__OWNED_PROPERTY_VALUES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__APPLIED_PROPERTY_VALUES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__STATUS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__FEATURES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__OWNED_TRACES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__CONTAINED_GENERIC_TRACES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__NAMING_RULES = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Capability Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATIONS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Capability Realization Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATION_PKGS = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Capability Realization Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CAPABILITY_REALIZATION_PKG_FEATURE_COUNT = CapellacommonPackage.ABSTRACT_CAPABILITY_PKG_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.la.impl.SystemAnalysisRealizationImpl <em>System Analysis Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.la.impl.SystemAnalysisRealizationImpl
   * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getSystemAnalysisRealization()
   * @generated
   */
	int SYSTEM_ANALYSIS_REALIZATION = 8;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__OWNED_EXTENSIONS = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__ID = CsPackage.ARCHITECTURE_ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__SID = CsPackage.ARCHITECTURE_ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__CONSTRAINTS = CsPackage.ARCHITECTURE_ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__OWNED_CONSTRAINTS = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__OWNED_MIGRATED_ELEMENTS = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__REALIZED_FLOW = CsPackage.ARCHITECTURE_ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__INCOMING_TRACES = CsPackage.ARCHITECTURE_ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__OUTGOING_TRACES = CsPackage.ARCHITECTURE_ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__VISIBLE_IN_DOC = CsPackage.ARCHITECTURE_ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__VISIBLE_IN_LM = CsPackage.ARCHITECTURE_ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__SUMMARY = CsPackage.ARCHITECTURE_ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__DESCRIPTION = CsPackage.ARCHITECTURE_ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__REVIEW = CsPackage.ARCHITECTURE_ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__OWNED_PROPERTY_VALUES = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__APPLIED_PROPERTY_VALUES = CsPackage.ARCHITECTURE_ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.ARCHITECTURE_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__STATUS = CsPackage.ARCHITECTURE_ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__FEATURES = CsPackage.ARCHITECTURE_ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__TARGET_ELEMENT = CsPackage.ARCHITECTURE_ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__SOURCE_ELEMENT = CsPackage.ARCHITECTURE_ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Allocated Architecture</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__ALLOCATED_ARCHITECTURE = CsPackage.ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE;

	/**
   * The feature id for the '<em><b>Allocating Architecture</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION__ALLOCATING_ARCHITECTURE = CsPackage.ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE;

	/**
   * The number of structural features of the '<em>System Analysis Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SYSTEM_ANALYSIS_REALIZATION_FEATURE_COUNT = CsPackage.ARCHITECTURE_ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.la.impl.ContextInterfaceRealizationImpl <em>Context Interface Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.la.impl.ContextInterfaceRealizationImpl
   * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getContextInterfaceRealization()
   * @generated
   */
	int CONTEXT_INTERFACE_REALIZATION = 9;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__OWNED_EXTENSIONS = CsPackage.INTERFACE_ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__ID = CsPackage.INTERFACE_ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__SID = CsPackage.INTERFACE_ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__CONSTRAINTS = CsPackage.INTERFACE_ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__OWNED_CONSTRAINTS = CsPackage.INTERFACE_ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__OWNED_MIGRATED_ELEMENTS = CsPackage.INTERFACE_ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__REALIZED_FLOW = CsPackage.INTERFACE_ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__INCOMING_TRACES = CsPackage.INTERFACE_ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__OUTGOING_TRACES = CsPackage.INTERFACE_ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__VISIBLE_IN_DOC = CsPackage.INTERFACE_ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__VISIBLE_IN_LM = CsPackage.INTERFACE_ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__SUMMARY = CsPackage.INTERFACE_ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__DESCRIPTION = CsPackage.INTERFACE_ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__REVIEW = CsPackage.INTERFACE_ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__OWNED_PROPERTY_VALUES = CsPackage.INTERFACE_ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.INTERFACE_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__APPLIED_PROPERTY_VALUES = CsPackage.INTERFACE_ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.INTERFACE_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.INTERFACE_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__STATUS = CsPackage.INTERFACE_ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__FEATURES = CsPackage.INTERFACE_ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__TARGET_ELEMENT = CsPackage.INTERFACE_ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__SOURCE_ELEMENT = CsPackage.INTERFACE_ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Allocated Interface</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__ALLOCATED_INTERFACE = CsPackage.INTERFACE_ALLOCATION__ALLOCATED_INTERFACE;

	/**
   * The feature id for the '<em><b>Allocating Interface Allocator</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION__ALLOCATING_INTERFACE_ALLOCATOR = CsPackage.INTERFACE_ALLOCATION__ALLOCATING_INTERFACE_ALLOCATOR;

	/**
   * The number of structural features of the '<em>Context Interface Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONTEXT_INTERFACE_REALIZATION_FEATURE_COUNT = CsPackage.INTERFACE_ALLOCATION_FEATURE_COUNT + 0;

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.la.LogicalArchitecturePkg <em>Logical Architecture Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Logical Architecture Pkg</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalArchitecturePkg
   * @generated
   */
	EClass getLogicalArchitecturePkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.la.LogicalArchitecturePkg#getOwnedLogicalArchitectures <em>Owned Logical Architectures</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Logical Architectures</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalArchitecturePkg#getOwnedLogicalArchitectures()
   * @see #getLogicalArchitecturePkg()
   * @generated
   */
	EReference getLogicalArchitecturePkg_OwnedLogicalArchitectures();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.la.LogicalArchitecture <em>Logical Architecture</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Logical Architecture</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalArchitecture
   * @generated
   */
	EClass getLogicalArchitecture();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getOwnedLogicalComponentPkg <em>Owned Logical Component Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Logical Component Pkg</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalArchitecture#getOwnedLogicalComponentPkg()
   * @see #getLogicalArchitecture()
   * @generated
   */
	EReference getLogicalArchitecture_OwnedLogicalComponentPkg();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getContainedCapabilityRealizationPkg <em>Contained Capability Realization Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Contained Capability Realization Pkg</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalArchitecture#getContainedCapabilityRealizationPkg()
   * @see #getLogicalArchitecture()
   * @generated
   */
	EReference getLogicalArchitecture_ContainedCapabilityRealizationPkg();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getContainedLogicalFunctionPkg <em>Contained Logical Function Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Contained Logical Function Pkg</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalArchitecture#getContainedLogicalFunctionPkg()
   * @see #getLogicalArchitecture()
   * @generated
   */
	EReference getLogicalArchitecture_ContainedLogicalFunctionPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getOwnedSystemAnalysisRealizations <em>Owned System Analysis Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned System Analysis Realizations</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalArchitecture#getOwnedSystemAnalysisRealizations()
   * @see #getLogicalArchitecture()
   * @generated
   */
	EReference getLogicalArchitecture_OwnedSystemAnalysisRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getAllocatedSystemAnalysisRealizations <em>Allocated System Analysis Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated System Analysis Realizations</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalArchitecture#getAllocatedSystemAnalysisRealizations()
   * @see #getLogicalArchitecture()
   * @generated
   */
	EReference getLogicalArchitecture_AllocatedSystemAnalysisRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getAllocatedSystemAnalyses <em>Allocated System Analyses</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated System Analyses</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalArchitecture#getAllocatedSystemAnalyses()
   * @see #getLogicalArchitecture()
   * @generated
   */
	EReference getLogicalArchitecture_AllocatedSystemAnalyses();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getAllocatingPhysicalArchitectures <em>Allocating Physical Architectures</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocating Physical Architectures</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalArchitecture#getAllocatingPhysicalArchitectures()
   * @see #getLogicalArchitecture()
   * @generated
   */
	EReference getLogicalArchitecture_AllocatingPhysicalArchitectures();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.la.LogicalFunction <em>Logical Function</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Logical Function</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalFunction
   * @generated
   */
	EClass getLogicalFunction();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.la.LogicalFunction#getOwnedLogicalFunctionPkgs <em>Owned Logical Function Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Logical Function Pkgs</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalFunction#getOwnedLogicalFunctionPkgs()
   * @see #getLogicalFunction()
   * @generated
   */
	EReference getLogicalFunction_OwnedLogicalFunctionPkgs();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.LogicalFunction#getAllocatingLogicalComponents <em>Allocating Logical Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocating Logical Components</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalFunction#getAllocatingLogicalComponents()
   * @see #getLogicalFunction()
   * @generated
   */
	EReference getLogicalFunction_AllocatingLogicalComponents();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.LogicalFunction#getRealizedSystemFunctions <em>Realized System Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized System Functions</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalFunction#getRealizedSystemFunctions()
   * @see #getLogicalFunction()
   * @generated
   */
	EReference getLogicalFunction_RealizedSystemFunctions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.LogicalFunction#getRealizingPhysicalFunctions <em>Realizing Physical Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Physical Functions</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalFunction#getRealizingPhysicalFunctions()
   * @see #getLogicalFunction()
   * @generated
   */
	EReference getLogicalFunction_RealizingPhysicalFunctions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.LogicalFunction#getContainedLogicalFunctions <em>Contained Logical Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Logical Functions</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalFunction#getContainedLogicalFunctions()
   * @see #getLogicalFunction()
   * @generated
   */
	EReference getLogicalFunction_ContainedLogicalFunctions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.LogicalFunction#getChildrenLogicalFunctions <em>Children Logical Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Children Logical Functions</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalFunction#getChildrenLogicalFunctions()
   * @see #getLogicalFunction()
   * @generated
   */
	EReference getLogicalFunction_ChildrenLogicalFunctions();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.la.LogicalFunctionPkg <em>Logical Function Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Logical Function Pkg</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalFunctionPkg
   * @generated
   */
	EClass getLogicalFunctionPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.la.LogicalFunctionPkg#getOwnedLogicalFunctions <em>Owned Logical Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Logical Functions</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalFunctionPkg#getOwnedLogicalFunctions()
   * @see #getLogicalFunctionPkg()
   * @generated
   */
	EReference getLogicalFunctionPkg_OwnedLogicalFunctions();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.la.LogicalFunctionPkg#getOwnedLogicalFunctionPkgs <em>Owned Logical Function Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Logical Function Pkgs</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalFunctionPkg#getOwnedLogicalFunctionPkgs()
   * @see #getLogicalFunctionPkg()
   * @generated
   */
	EReference getLogicalFunctionPkg_OwnedLogicalFunctionPkgs();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.la.LogicalComponent <em>Logical Component</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Logical Component</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalComponent
   * @generated
   */
	EClass getLogicalComponent();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.la.LogicalComponent#getOwnedLogicalComponents <em>Owned Logical Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Logical Components</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalComponent#getOwnedLogicalComponents()
   * @see #getLogicalComponent()
   * @generated
   */
	EReference getLogicalComponent_OwnedLogicalComponents();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.la.LogicalComponent#getOwnedLogicalArchitectures <em>Owned Logical Architectures</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Logical Architectures</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalComponent#getOwnedLogicalArchitectures()
   * @see #getLogicalComponent()
   * @generated
   */
	EReference getLogicalComponent_OwnedLogicalArchitectures();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.la.LogicalComponent#getOwnedLogicalComponentPkgs <em>Owned Logical Component Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Logical Component Pkgs</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalComponent#getOwnedLogicalComponentPkgs()
   * @see #getLogicalComponent()
   * @generated
   */
	EReference getLogicalComponent_OwnedLogicalComponentPkgs();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.LogicalComponent#getSubLogicalComponents <em>Sub Logical Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Sub Logical Components</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalComponent#getSubLogicalComponents()
   * @see #getLogicalComponent()
   * @generated
   */
	EReference getLogicalComponent_SubLogicalComponents();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.LogicalComponent#getAllocatedLogicalFunctions <em>Allocated Logical Functions</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated Logical Functions</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalComponent#getAllocatedLogicalFunctions()
   * @see #getLogicalComponent()
   * @generated
   */
	EReference getLogicalComponent_AllocatedLogicalFunctions();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.LogicalComponent#getRealizedSystemComponents <em>Realized System Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized System Components</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalComponent#getRealizedSystemComponents()
   * @see #getLogicalComponent()
   * @generated
   */
	EReference getLogicalComponent_RealizedSystemComponents();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.LogicalComponent#getRealizingPhysicalComponents <em>Realizing Physical Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Physical Components</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalComponent#getRealizingPhysicalComponents()
   * @see #getLogicalComponent()
   * @generated
   */
	EReference getLogicalComponent_RealizingPhysicalComponents();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.la.LogicalComponentPkg <em>Logical Component Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Logical Component Pkg</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalComponentPkg
   * @generated
   */
	EClass getLogicalComponentPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.la.LogicalComponentPkg#getOwnedLogicalComponents <em>Owned Logical Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Logical Components</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalComponentPkg#getOwnedLogicalComponents()
   * @see #getLogicalComponentPkg()
   * @generated
   */
	EReference getLogicalComponentPkg_OwnedLogicalComponents();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.la.LogicalComponentPkg#getOwnedLogicalComponentPkgs <em>Owned Logical Component Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Logical Component Pkgs</em>'.
   * @see org.polarsys.capella.core.data.la.LogicalComponentPkg#getOwnedLogicalComponentPkgs()
   * @see #getLogicalComponentPkg()
   * @generated
   */
	EReference getLogicalComponentPkg_OwnedLogicalComponentPkgs();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.la.CapabilityRealization <em>Capability Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Capability Realization</em>'.
   * @see org.polarsys.capella.core.data.la.CapabilityRealization
   * @generated
   */
	EClass getCapabilityRealization();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.la.CapabilityRealization#getOwnedCapabilityRealizationInvolvements <em>Owned Capability Realization Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Capability Realization Involvements</em>'.
   * @see org.polarsys.capella.core.data.la.CapabilityRealization#getOwnedCapabilityRealizationInvolvements()
   * @see #getCapabilityRealization()
   * @generated
   */
	EReference getCapabilityRealization_OwnedCapabilityRealizationInvolvements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.CapabilityRealization#getInvolvedComponents <em>Involved Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involved Components</em>'.
   * @see org.polarsys.capella.core.data.la.CapabilityRealization#getInvolvedComponents()
   * @see #getCapabilityRealization()
   * @generated
   */
	EReference getCapabilityRealization_InvolvedComponents();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilities <em>Realized Capabilities</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Capabilities</em>'.
   * @see org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilities()
   * @see #getCapabilityRealization()
   * @generated
   */
	EReference getCapabilityRealization_RealizedCapabilities();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilityRealizations <em>Realized Capability Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Capability Realizations</em>'.
   * @see org.polarsys.capella.core.data.la.CapabilityRealization#getRealizedCapabilityRealizations()
   * @see #getCapabilityRealization()
   * @generated
   */
	EReference getCapabilityRealization_RealizedCapabilityRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.la.CapabilityRealization#getRealizingCapabilityRealizations <em>Realizing Capability Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Capability Realizations</em>'.
   * @see org.polarsys.capella.core.data.la.CapabilityRealization#getRealizingCapabilityRealizations()
   * @see #getCapabilityRealization()
   * @generated
   */
	EReference getCapabilityRealization_RealizingCapabilityRealizations();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.la.CapabilityRealizationPkg <em>Capability Realization Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Capability Realization Pkg</em>'.
   * @see org.polarsys.capella.core.data.la.CapabilityRealizationPkg
   * @generated
   */
	EClass getCapabilityRealizationPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.la.CapabilityRealizationPkg#getOwnedCapabilityRealizations <em>Owned Capability Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Capability Realizations</em>'.
   * @see org.polarsys.capella.core.data.la.CapabilityRealizationPkg#getOwnedCapabilityRealizations()
   * @see #getCapabilityRealizationPkg()
   * @generated
   */
	EReference getCapabilityRealizationPkg_OwnedCapabilityRealizations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.la.CapabilityRealizationPkg#getOwnedCapabilityRealizationPkgs <em>Owned Capability Realization Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Capability Realization Pkgs</em>'.
   * @see org.polarsys.capella.core.data.la.CapabilityRealizationPkg#getOwnedCapabilityRealizationPkgs()
   * @see #getCapabilityRealizationPkg()
   * @generated
   */
	EReference getCapabilityRealizationPkg_OwnedCapabilityRealizationPkgs();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.la.SystemAnalysisRealization <em>System Analysis Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>System Analysis Realization</em>'.
   * @see org.polarsys.capella.core.data.la.SystemAnalysisRealization
   * @generated
   */
	EClass getSystemAnalysisRealization();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.la.ContextInterfaceRealization <em>Context Interface Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Context Interface Realization</em>'.
   * @see org.polarsys.capella.core.data.la.ContextInterfaceRealization
   * @generated
   */
	EClass getContextInterfaceRealization();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	LaFactory getLaFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.la.impl.LogicalArchitecturePkgImpl <em>Logical Architecture Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.la.impl.LogicalArchitecturePkgImpl
     * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getLogicalArchitecturePkg()
     * @generated
     */
		EClass LOGICAL_ARCHITECTURE_PKG = eINSTANCE.getLogicalArchitecturePkg();

		/**
     * The meta object literal for the '<em><b>Owned Logical Architectures</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_ARCHITECTURE_PKG__OWNED_LOGICAL_ARCHITECTURES = eINSTANCE.getLogicalArchitecturePkg_OwnedLogicalArchitectures();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.la.impl.LogicalArchitectureImpl <em>Logical Architecture</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.la.impl.LogicalArchitectureImpl
     * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getLogicalArchitecture()
     * @generated
     */
		EClass LOGICAL_ARCHITECTURE = eINSTANCE.getLogicalArchitecture();

		/**
     * The meta object literal for the '<em><b>Owned Logical Component Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG = eINSTANCE.getLogicalArchitecture_OwnedLogicalComponentPkg();

		/**
     * The meta object literal for the '<em><b>Contained Capability Realization Pkg</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG = eINSTANCE.getLogicalArchitecture_ContainedCapabilityRealizationPkg();

		/**
     * The meta object literal for the '<em><b>Contained Logical Function Pkg</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_ARCHITECTURE__CONTAINED_LOGICAL_FUNCTION_PKG = eINSTANCE.getLogicalArchitecture_ContainedLogicalFunctionPkg();

		/**
     * The meta object literal for the '<em><b>Owned System Analysis Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS = eINSTANCE.getLogicalArchitecture_OwnedSystemAnalysisRealizations();

		/**
     * The meta object literal for the '<em><b>Allocated System Analysis Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSIS_REALIZATIONS = eINSTANCE.getLogicalArchitecture_AllocatedSystemAnalysisRealizations();

		/**
     * The meta object literal for the '<em><b>Allocated System Analyses</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSES = eINSTANCE.getLogicalArchitecture_AllocatedSystemAnalyses();

		/**
     * The meta object literal for the '<em><b>Allocating Physical Architectures</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_ARCHITECTURE__ALLOCATING_PHYSICAL_ARCHITECTURES = eINSTANCE.getLogicalArchitecture_AllocatingPhysicalArchitectures();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.la.impl.LogicalFunctionImpl <em>Logical Function</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.la.impl.LogicalFunctionImpl
     * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getLogicalFunction()
     * @generated
     */
		EClass LOGICAL_FUNCTION = eINSTANCE.getLogicalFunction();

		/**
     * The meta object literal for the '<em><b>Owned Logical Function Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS = eINSTANCE.getLogicalFunction_OwnedLogicalFunctionPkgs();

		/**
     * The meta object literal for the '<em><b>Allocating Logical Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_FUNCTION__ALLOCATING_LOGICAL_COMPONENTS = eINSTANCE.getLogicalFunction_AllocatingLogicalComponents();

		/**
     * The meta object literal for the '<em><b>Realized System Functions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_FUNCTION__REALIZED_SYSTEM_FUNCTIONS = eINSTANCE.getLogicalFunction_RealizedSystemFunctions();

		/**
     * The meta object literal for the '<em><b>Realizing Physical Functions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_FUNCTION__REALIZING_PHYSICAL_FUNCTIONS = eINSTANCE.getLogicalFunction_RealizingPhysicalFunctions();

		/**
     * The meta object literal for the '<em><b>Contained Logical Functions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_FUNCTION__CONTAINED_LOGICAL_FUNCTIONS = eINSTANCE.getLogicalFunction_ContainedLogicalFunctions();

		/**
     * The meta object literal for the '<em><b>Children Logical Functions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_FUNCTION__CHILDREN_LOGICAL_FUNCTIONS = eINSTANCE.getLogicalFunction_ChildrenLogicalFunctions();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.la.impl.LogicalFunctionPkgImpl <em>Logical Function Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.la.impl.LogicalFunctionPkgImpl
     * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getLogicalFunctionPkg()
     * @generated
     */
		EClass LOGICAL_FUNCTION_PKG = eINSTANCE.getLogicalFunctionPkg();

		/**
     * The meta object literal for the '<em><b>Owned Logical Functions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS = eINSTANCE.getLogicalFunctionPkg_OwnedLogicalFunctions();

		/**
     * The meta object literal for the '<em><b>Owned Logical Function Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTION_PKGS = eINSTANCE.getLogicalFunctionPkg_OwnedLogicalFunctionPkgs();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl <em>Logical Component</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.la.impl.LogicalComponentImpl
     * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getLogicalComponent()
     * @generated
     */
		EClass LOGICAL_COMPONENT = eINSTANCE.getLogicalComponent();

		/**
     * The meta object literal for the '<em><b>Owned Logical Components</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS = eINSTANCE.getLogicalComponent_OwnedLogicalComponents();

		/**
     * The meta object literal for the '<em><b>Owned Logical Architectures</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_COMPONENT__OWNED_LOGICAL_ARCHITECTURES = eINSTANCE.getLogicalComponent_OwnedLogicalArchitectures();

		/**
     * The meta object literal for the '<em><b>Owned Logical Component Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS = eINSTANCE.getLogicalComponent_OwnedLogicalComponentPkgs();

		/**
     * The meta object literal for the '<em><b>Sub Logical Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_COMPONENT__SUB_LOGICAL_COMPONENTS = eINSTANCE.getLogicalComponent_SubLogicalComponents();

		/**
     * The meta object literal for the '<em><b>Allocated Logical Functions</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_COMPONENT__ALLOCATED_LOGICAL_FUNCTIONS = eINSTANCE.getLogicalComponent_AllocatedLogicalFunctions();

		/**
     * The meta object literal for the '<em><b>Realized System Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_COMPONENT__REALIZED_SYSTEM_COMPONENTS = eINSTANCE.getLogicalComponent_RealizedSystemComponents();

		/**
     * The meta object literal for the '<em><b>Realizing Physical Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_COMPONENT__REALIZING_PHYSICAL_COMPONENTS = eINSTANCE.getLogicalComponent_RealizingPhysicalComponents();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.la.impl.LogicalComponentPkgImpl <em>Logical Component Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.la.impl.LogicalComponentPkgImpl
     * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getLogicalComponentPkg()
     * @generated
     */
		EClass LOGICAL_COMPONENT_PKG = eINSTANCE.getLogicalComponentPkg();

		/**
     * The meta object literal for the '<em><b>Owned Logical Components</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS = eINSTANCE.getLogicalComponentPkg_OwnedLogicalComponents();

		/**
     * The meta object literal for the '<em><b>Owned Logical Component Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS = eINSTANCE.getLogicalComponentPkg_OwnedLogicalComponentPkgs();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl <em>Capability Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl
     * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getCapabilityRealization()
     * @generated
     */
		EClass CAPABILITY_REALIZATION = eINSTANCE.getCapabilityRealization();

		/**
     * The meta object literal for the '<em><b>Owned Capability Realization Involvements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPABILITY_REALIZATION__OWNED_CAPABILITY_REALIZATION_INVOLVEMENTS = eINSTANCE.getCapabilityRealization_OwnedCapabilityRealizationInvolvements();

		/**
     * The meta object literal for the '<em><b>Involved Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPABILITY_REALIZATION__INVOLVED_COMPONENTS = eINSTANCE.getCapabilityRealization_InvolvedComponents();

		/**
     * The meta object literal for the '<em><b>Realized Capabilities</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPABILITY_REALIZATION__REALIZED_CAPABILITIES = eINSTANCE.getCapabilityRealization_RealizedCapabilities();

		/**
     * The meta object literal for the '<em><b>Realized Capability Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPABILITY_REALIZATION__REALIZED_CAPABILITY_REALIZATIONS = eINSTANCE.getCapabilityRealization_RealizedCapabilityRealizations();

		/**
     * The meta object literal for the '<em><b>Realizing Capability Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPABILITY_REALIZATION__REALIZING_CAPABILITY_REALIZATIONS = eINSTANCE.getCapabilityRealization_RealizingCapabilityRealizations();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationPkgImpl <em>Capability Realization Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.la.impl.CapabilityRealizationPkgImpl
     * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getCapabilityRealizationPkg()
     * @generated
     */
		EClass CAPABILITY_REALIZATION_PKG = eINSTANCE.getCapabilityRealizationPkg();

		/**
     * The meta object literal for the '<em><b>Owned Capability Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATIONS = eINSTANCE.getCapabilityRealizationPkg_OwnedCapabilityRealizations();

		/**
     * The meta object literal for the '<em><b>Owned Capability Realization Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATION_PKGS = eINSTANCE.getCapabilityRealizationPkg_OwnedCapabilityRealizationPkgs();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.la.impl.SystemAnalysisRealizationImpl <em>System Analysis Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.la.impl.SystemAnalysisRealizationImpl
     * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getSystemAnalysisRealization()
     * @generated
     */
		EClass SYSTEM_ANALYSIS_REALIZATION = eINSTANCE.getSystemAnalysisRealization();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.la.impl.ContextInterfaceRealizationImpl <em>Context Interface Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.la.impl.ContextInterfaceRealizationImpl
     * @see org.polarsys.capella.core.data.la.impl.LaPackageImpl#getContextInterfaceRealization()
     * @generated
     */
		EClass CONTEXT_INTERFACE_REALIZATION = eINSTANCE.getContextInterfaceRealization();

	}

} //LaPackage
