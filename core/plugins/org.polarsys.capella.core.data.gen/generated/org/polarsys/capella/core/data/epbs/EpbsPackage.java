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
package org.polarsys.capella.core.data.epbs;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.CsPackage;

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
 * @see org.polarsys.capella.core.data.epbs.EpbsFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(E)PBS (for (End-)Product Breakdown Structure) aims at defining the system\'s work product breakdown (close to Clearcase/UCM\'s components concept).\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='epbs' usage\040examples='none' constraints='This package depends on the model CompositeStructure.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface EpbsPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "epbs"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/core/epbs/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.core.data.epbs"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	EpbsPackage eINSTANCE = org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.epbs.impl.EPBSArchitecturePkgImpl <em>EPBS Architecture Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.epbs.impl.EPBSArchitecturePkgImpl
   * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getEPBSArchitecturePkg()
   * @generated
   */
	int EPBS_ARCHITECTURE_PKG = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__OWNED_EXTENSIONS = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__ID = CsPackage.BLOCK_ARCHITECTURE_PKG__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__SID = CsPackage.BLOCK_ARCHITECTURE_PKG__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__CONSTRAINTS = CsPackage.BLOCK_ARCHITECTURE_PKG__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__OWNED_CONSTRAINTS = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__OWNED_MIGRATED_ELEMENTS = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__NAME = CsPackage.BLOCK_ARCHITECTURE_PKG__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__INCOMING_TRACES = CsPackage.BLOCK_ARCHITECTURE_PKG__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__OUTGOING_TRACES = CsPackage.BLOCK_ARCHITECTURE_PKG__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__VISIBLE_IN_DOC = CsPackage.BLOCK_ARCHITECTURE_PKG__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__VISIBLE_IN_LM = CsPackage.BLOCK_ARCHITECTURE_PKG__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__SUMMARY = CsPackage.BLOCK_ARCHITECTURE_PKG__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__DESCRIPTION = CsPackage.BLOCK_ARCHITECTURE_PKG__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__REVIEW = CsPackage.BLOCK_ARCHITECTURE_PKG__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUES = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUES = CsPackage.BLOCK_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.BLOCK_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__STATUS = CsPackage.BLOCK_ARCHITECTURE_PKG__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__FEATURES = CsPackage.BLOCK_ARCHITECTURE_PKG__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__OWNED_TRACES = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__CONTAINED_GENERIC_TRACES = CsPackage.BLOCK_ARCHITECTURE_PKG__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__NAMING_RULES = CsPackage.BLOCK_ARCHITECTURE_PKG__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_PKGS = CsPackage.BLOCK_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned EPBS Architectures</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG__OWNED_EPBS_ARCHITECTURES = CsPackage.BLOCK_ARCHITECTURE_PKG_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>EPBS Architecture Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_PKG_FEATURE_COUNT = CsPackage.BLOCK_ARCHITECTURE_PKG_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.epbs.impl.EPBSArchitectureImpl <em>EPBS Architecture</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.epbs.impl.EPBSArchitectureImpl
   * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getEPBSArchitecture()
   * @generated
   */
	int EPBS_ARCHITECTURE = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_EXTENSIONS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__ID = CsPackage.COMPONENT_ARCHITECTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__SID = CsPackage.COMPONENT_ARCHITECTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__CONSTRAINTS = CsPackage.COMPONENT_ARCHITECTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_CONSTRAINTS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_MIGRATED_ELEMENTS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__NAME = CsPackage.COMPONENT_ARCHITECTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__INCOMING_TRACES = CsPackage.COMPONENT_ARCHITECTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OUTGOING_TRACES = CsPackage.COMPONENT_ARCHITECTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__VISIBLE_IN_DOC = CsPackage.COMPONENT_ARCHITECTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__VISIBLE_IN_LM = CsPackage.COMPONENT_ARCHITECTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__SUMMARY = CsPackage.COMPONENT_ARCHITECTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__DESCRIPTION = CsPackage.COMPONENT_ARCHITECTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__REVIEW = CsPackage.COMPONENT_ARCHITECTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_ARCHITECTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_ARCHITECTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__STATUS = CsPackage.COMPONENT_ARCHITECTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__FEATURES = CsPackage.COMPONENT_ARCHITECTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_TRACES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__CONTAINED_GENERIC_TRACES = CsPackage.COMPONENT_ARCHITECTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__NAMING_RULES = CsPackage.COMPONENT_ARCHITECTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_PROPERTY_VALUE_PKGS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Function Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_FUNCTION_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_FUNCTION_PKG;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_COMPONENT_EXCHANGES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.COMPONENT_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_FUNCTIONAL_LINKS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_FUNCTIONAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_FUNCTIONAL_ALLOCATIONS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
   * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_INTERFACE_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_INTERFACE_PKG;

	/**
   * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_DATA_PKG = CsPackage.COMPONENT_ARCHITECTURE__OWNED_DATA_PKG;

	/**
   * The feature id for the '<em><b>Provisioned Architecture Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS = CsPackage.COMPONENT_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Provisioning Architecture Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS = CsPackage.COMPONENT_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__ALLOCATED_ARCHITECTURES = CsPackage.COMPONENT_ARCHITECTURE__ALLOCATED_ARCHITECTURES;

	/**
   * The feature id for the '<em><b>Allocating Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__ALLOCATING_ARCHITECTURES = CsPackage.COMPONENT_ARCHITECTURE__ALLOCATING_ARCHITECTURES;

	/**
   * The feature id for the '<em><b>System</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__SYSTEM = CsPackage.COMPONENT_ARCHITECTURE__SYSTEM;

	/**
   * The feature id for the '<em><b>Owned Configuration Item Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Contained Capability Realization Pkg</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Physical Architecture Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__OWNED_PHYSICAL_ARCHITECTURE_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Allocated Physical Architecture Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURE_REALIZATIONS = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Allocated Physical Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURES = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 4;

	/**
   * The number of structural features of the '<em>EPBS Architecture</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EPBS_ARCHITECTURE_FEATURE_COUNT = CsPackage.COMPONENT_ARCHITECTURE_FEATURE_COUNT + 5;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.epbs.impl.ConfigurationItemPkgImpl <em>Configuration Item Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.epbs.impl.ConfigurationItemPkgImpl
   * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getConfigurationItemPkg()
   * @generated
   */
	int CONFIGURATION_ITEM_PKG = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_EXTENSIONS = CsPackage.COMPONENT_PKG__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__ID = CsPackage.COMPONENT_PKG__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__SID = CsPackage.COMPONENT_PKG__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__CONSTRAINTS = CsPackage.COMPONENT_PKG__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_CONSTRAINTS = CsPackage.COMPONENT_PKG__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_MIGRATED_ELEMENTS = CsPackage.COMPONENT_PKG__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__NAME = CsPackage.COMPONENT_PKG__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__INCOMING_TRACES = CsPackage.COMPONENT_PKG__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OUTGOING_TRACES = CsPackage.COMPONENT_PKG__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__VISIBLE_IN_DOC = CsPackage.COMPONENT_PKG__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__VISIBLE_IN_LM = CsPackage.COMPONENT_PKG__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__SUMMARY = CsPackage.COMPONENT_PKG__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__DESCRIPTION = CsPackage.COMPONENT_PKG__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__REVIEW = CsPackage.COMPONENT_PKG__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_PROPERTY_VALUES = CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.COMPONENT_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__APPLIED_PROPERTY_VALUES = CsPackage.COMPONENT_PKG__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.COMPONENT_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__STATUS = CsPackage.COMPONENT_PKG__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__FEATURES = CsPackage.COMPONENT_PKG__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_TRACES = CsPackage.COMPONENT_PKG__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__CONTAINED_GENERIC_TRACES = CsPackage.COMPONENT_PKG__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__NAMING_RULES = CsPackage.COMPONENT_PKG__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_PROPERTY_VALUE_PKGS = CsPackage.COMPONENT_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Parts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_PARTS = CsPackage.COMPONENT_PKG__OWNED_PARTS;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_COMPONENT_EXCHANGES = CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_FUNCTIONAL_LINKS = CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_FUNCTIONAL_ALLOCATIONS = CsPackage.COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = CsPackage.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_PHYSICAL_LINKS = CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_PHYSICAL_LINK_CATEGORIES = CsPackage.COMPONENT_PKG__OWNED_PHYSICAL_LINK_CATEGORIES;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_STATE_MACHINES = CsPackage.COMPONENT_PKG__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Owned Configuration Items</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEMS = CsPackage.COMPONENT_PKG_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Configuration Item Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEM_PKGS = CsPackage.COMPONENT_PKG_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Configuration Item Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_PKG_FEATURE_COUNT = CsPackage.COMPONENT_PKG_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.epbs.impl.ConfigurationItemImpl <em>Configuration Item</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.epbs.impl.ConfigurationItemImpl
   * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getConfigurationItem()
   * @generated
   */
	int CONFIGURATION_ITEM = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_EXTENSIONS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__ID = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__SID = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__CONSTRAINTS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_CONSTRAINTS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_MIGRATED_ELEMENTS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__INCOMING_TRACES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OUTGOING_TRACES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__VISIBLE_IN_DOC = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__VISIBLE_IN_LM = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__SUMMARY = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__DESCRIPTION = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__REVIEW = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_PROPERTY_VALUES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__APPLIED_PROPERTY_VALUES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_PROPERTY_VALUE_GROUPS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__APPLIED_PROPERTY_VALUE_GROUPS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__STATUS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__FEATURES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__INVOLVING_INVOLVEMENTS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Capability Realization Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__CAPABILITY_REALIZATION_INVOLVEMENTS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CAPABILITY_REALIZATION_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Involving Capability Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__INVOLVING_CAPABILITY_REALIZATIONS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__NAME = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__ABSTRACT_TYPED_ELEMENTS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_TRACES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__CONTAINED_GENERIC_TRACES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__NAMING_RULES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__TYPED_ELEMENTS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_FUNCTIONAL_ALLOCATION = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_COMPONENT_EXCHANGES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__FUNCTIONAL_ALLOCATIONS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__ALLOCATED_FUNCTIONS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__IN_EXCHANGE_LINKS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OUT_EXCHANGE_LINKS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_ABSTRACT_CAPABILITY_PKG = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_INTERFACE_PKG = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_DATA_PKG = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_STATE_MACHINES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__ABSTRACT = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_GENERALIZATIONS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__SUPER_GENERALIZATIONS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 19;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__SUB_GENERALIZATIONS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 20;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__SUPER = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 21;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__SUB = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 22;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_FEATURES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 23;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__CONTAINED_PROPERTIES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 24;

	/**
   * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_INTERFACE_ALLOCATIONS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 25;

	/**
   * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__PROVISIONED_INTERFACE_ALLOCATIONS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 26;

	/**
   * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__ALLOCATED_INTERFACES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 27;

	/**
   * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_COMMUNICATION_LINKS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 28;

	/**
   * The feature id for the '<em><b>Produce</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__PRODUCE = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 29;

	/**
   * The feature id for the '<em><b>Consume</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__CONSUME = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 30;

	/**
   * The feature id for the '<em><b>Send</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__SEND = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 31;

	/**
   * The feature id for the '<em><b>Receive</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__RECEIVE = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 32;

	/**
   * The feature id for the '<em><b>Call</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__CALL = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 33;

	/**
   * The feature id for the '<em><b>Execute</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__EXECUTE = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 34;

	/**
   * The feature id for the '<em><b>Write</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__WRITE = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 35;

	/**
   * The feature id for the '<em><b>Access</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__ACCESS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 36;

	/**
   * The feature id for the '<em><b>Acquire</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__ACQUIRE = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 37;

	/**
   * The feature id for the '<em><b>Transmit</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__TRANSMIT = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 38;

	/**
   * The feature id for the '<em><b>Actor</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__ACTOR = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 39;

	/**
   * The feature id for the '<em><b>Human</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__HUMAN = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 40;

	/**
   * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_INTERFACE_USES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 41;

	/**
   * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__USED_INTERFACE_LINKS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 42;

	/**
   * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__USED_INTERFACES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 43;

	/**
   * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_INTERFACE_IMPLEMENTATIONS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 44;

	/**
   * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__IMPLEMENTED_INTERFACE_LINKS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 45;

	/**
   * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__IMPLEMENTED_INTERFACES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 46;

	/**
   * The feature id for the '<em><b>Owned Component Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_COMPONENT_REALIZATIONS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 47;

	/**
   * The feature id for the '<em><b>Realized Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__REALIZED_COMPONENTS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 48;

	/**
   * The feature id for the '<em><b>Realizing Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__REALIZING_COMPONENTS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 49;

	/**
   * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__PROVIDED_INTERFACES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 50;

	/**
   * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__REQUIRED_INTERFACES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 51;

	/**
   * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__CONTAINED_COMPONENT_PORTS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 52;

	/**
   * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__CONTAINED_PARTS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 53;

	/**
   * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__CONTAINED_PHYSICAL_PORTS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 54;

	/**
   * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_PHYSICAL_PATH = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 55;

	/**
   * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_PHYSICAL_LINKS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 56;

	/**
   * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_PHYSICAL_LINK_CATEGORIES = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 57;

	/**
   * The feature id for the '<em><b>Representing Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__REPRESENTING_PARTS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 58;

	/**
   * The feature id for the '<em><b>Item Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__ITEM_IDENTIFIER = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 59;

	/**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__KIND = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 60;

	/**
   * The feature id for the '<em><b>Owned Configuration Items</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_CONFIGURATION_ITEMS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 61;

	/**
   * The feature id for the '<em><b>Owned Configuration Item Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_CONFIGURATION_ITEM_PKGS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 62;

	/**
   * The feature id for the '<em><b>Owned Physical Artifact Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__OWNED_PHYSICAL_ARTIFACT_REALIZATIONS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 63;

	/**
   * The feature id for the '<em><b>Allocated Physical Artifacts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM__ALLOCATED_PHYSICAL_ARTIFACTS = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 64;

	/**
   * The number of structural features of the '<em>Configuration Item</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int CONFIGURATION_ITEM_FEATURE_COUNT = CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT_FEATURE_COUNT + 65;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.epbs.impl.PhysicalArchitectureRealizationImpl <em>Physical Architecture Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.epbs.impl.PhysicalArchitectureRealizationImpl
   * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getPhysicalArchitectureRealization()
   * @generated
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__OWNED_EXTENSIONS = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__ID = CsPackage.ARCHITECTURE_ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__SID = CsPackage.ARCHITECTURE_ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__CONSTRAINTS = CsPackage.ARCHITECTURE_ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__OWNED_CONSTRAINTS = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__OWNED_MIGRATED_ELEMENTS = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__REALIZED_FLOW = CsPackage.ARCHITECTURE_ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__INCOMING_TRACES = CsPackage.ARCHITECTURE_ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__OUTGOING_TRACES = CsPackage.ARCHITECTURE_ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__VISIBLE_IN_DOC = CsPackage.ARCHITECTURE_ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__VISIBLE_IN_LM = CsPackage.ARCHITECTURE_ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__SUMMARY = CsPackage.ARCHITECTURE_ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__DESCRIPTION = CsPackage.ARCHITECTURE_ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__REVIEW = CsPackage.ARCHITECTURE_ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__OWNED_PROPERTY_VALUES = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__APPLIED_PROPERTY_VALUES = CsPackage.ARCHITECTURE_ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CsPackage.ARCHITECTURE_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CsPackage.ARCHITECTURE_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__STATUS = CsPackage.ARCHITECTURE_ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__FEATURES = CsPackage.ARCHITECTURE_ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__TARGET_ELEMENT = CsPackage.ARCHITECTURE_ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__SOURCE_ELEMENT = CsPackage.ARCHITECTURE_ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Allocated Architecture</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__ALLOCATED_ARCHITECTURE = CsPackage.ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE;

	/**
   * The feature id for the '<em><b>Allocating Architecture</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION__ALLOCATING_ARCHITECTURE = CsPackage.ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE;

	/**
   * The number of structural features of the '<em>Physical Architecture Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARCHITECTURE_REALIZATION_FEATURE_COUNT = CsPackage.ARCHITECTURE_ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.epbs.impl.PhysicalArtifactRealizationImpl <em>Physical Artifact Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.epbs.impl.PhysicalArtifactRealizationImpl
   * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getPhysicalArtifactRealization()
   * @generated
   */
	int PHYSICAL_ARTIFACT_REALIZATION = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Realized Physical Artifact</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__REALIZED_PHYSICAL_ARTIFACT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Realizing Configuration Item</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION__REALIZING_CONFIGURATION_ITEM = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Physical Artifact Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_ARTIFACT_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.epbs.ConfigurationItemKind <em>Configuration Item Kind</em>}' enum.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.epbs.ConfigurationItemKind
   * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getConfigurationItemKind()
   * @generated
   */
	int CONFIGURATION_ITEM_KIND = 6;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.epbs.EPBSArchitecturePkg <em>EPBS Architecture Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>EPBS Architecture Pkg</em>'.
   * @see org.polarsys.capella.core.data.epbs.EPBSArchitecturePkg
   * @generated
   */
	EClass getEPBSArchitecturePkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.epbs.EPBSArchitecturePkg#getOwnedEPBSArchitectures <em>Owned EPBS Architectures</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned EPBS Architectures</em>'.
   * @see org.polarsys.capella.core.data.epbs.EPBSArchitecturePkg#getOwnedEPBSArchitectures()
   * @see #getEPBSArchitecturePkg()
   * @generated
   */
	EReference getEPBSArchitecturePkg_OwnedEPBSArchitectures();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.epbs.EPBSArchitecture <em>EPBS Architecture</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>EPBS Architecture</em>'.
   * @see org.polarsys.capella.core.data.epbs.EPBSArchitecture
   * @generated
   */
	EClass getEPBSArchitecture();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.epbs.EPBSArchitecture#getOwnedConfigurationItemPkg <em>Owned Configuration Item Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Configuration Item Pkg</em>'.
   * @see org.polarsys.capella.core.data.epbs.EPBSArchitecture#getOwnedConfigurationItemPkg()
   * @see #getEPBSArchitecture()
   * @generated
   */
	EReference getEPBSArchitecture_OwnedConfigurationItemPkg();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.epbs.EPBSArchitecture#getContainedCapabilityRealizationPkg <em>Contained Capability Realization Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Contained Capability Realization Pkg</em>'.
   * @see org.polarsys.capella.core.data.epbs.EPBSArchitecture#getContainedCapabilityRealizationPkg()
   * @see #getEPBSArchitecture()
   * @generated
   */
	EReference getEPBSArchitecture_ContainedCapabilityRealizationPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.epbs.EPBSArchitecture#getOwnedPhysicalArchitectureRealizations <em>Owned Physical Architecture Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Physical Architecture Realizations</em>'.
   * @see org.polarsys.capella.core.data.epbs.EPBSArchitecture#getOwnedPhysicalArchitectureRealizations()
   * @see #getEPBSArchitecture()
   * @generated
   */
	EReference getEPBSArchitecture_OwnedPhysicalArchitectureRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.epbs.EPBSArchitecture#getAllocatedPhysicalArchitectureRealizations <em>Allocated Physical Architecture Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated Physical Architecture Realizations</em>'.
   * @see org.polarsys.capella.core.data.epbs.EPBSArchitecture#getAllocatedPhysicalArchitectureRealizations()
   * @see #getEPBSArchitecture()
   * @generated
   */
	EReference getEPBSArchitecture_AllocatedPhysicalArchitectureRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.epbs.EPBSArchitecture#getAllocatedPhysicalArchitectures <em>Allocated Physical Architectures</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated Physical Architectures</em>'.
   * @see org.polarsys.capella.core.data.epbs.EPBSArchitecture#getAllocatedPhysicalArchitectures()
   * @see #getEPBSArchitecture()
   * @generated
   */
	EReference getEPBSArchitecture_AllocatedPhysicalArchitectures();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.epbs.ConfigurationItemPkg <em>Configuration Item Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Configuration Item Pkg</em>'.
   * @see org.polarsys.capella.core.data.epbs.ConfigurationItemPkg
   * @generated
   */
	EClass getConfigurationItemPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.epbs.ConfigurationItemPkg#getOwnedConfigurationItems <em>Owned Configuration Items</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Configuration Items</em>'.
   * @see org.polarsys.capella.core.data.epbs.ConfigurationItemPkg#getOwnedConfigurationItems()
   * @see #getConfigurationItemPkg()
   * @generated
   */
	EReference getConfigurationItemPkg_OwnedConfigurationItems();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.epbs.ConfigurationItemPkg#getOwnedConfigurationItemPkgs <em>Owned Configuration Item Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Configuration Item Pkgs</em>'.
   * @see org.polarsys.capella.core.data.epbs.ConfigurationItemPkg#getOwnedConfigurationItemPkgs()
   * @see #getConfigurationItemPkg()
   * @generated
   */
	EReference getConfigurationItemPkg_OwnedConfigurationItemPkgs();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.epbs.ConfigurationItem <em>Configuration Item</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Configuration Item</em>'.
   * @see org.polarsys.capella.core.data.epbs.ConfigurationItem
   * @generated
   */
	EClass getConfigurationItem();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.epbs.ConfigurationItem#getItemIdentifier <em>Item Identifier</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Item Identifier</em>'.
   * @see org.polarsys.capella.core.data.epbs.ConfigurationItem#getItemIdentifier()
   * @see #getConfigurationItem()
   * @generated
   */
	EAttribute getConfigurationItem_ItemIdentifier();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.epbs.ConfigurationItem#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see org.polarsys.capella.core.data.epbs.ConfigurationItem#getKind()
   * @see #getConfigurationItem()
   * @generated
   */
	EAttribute getConfigurationItem_Kind();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.epbs.ConfigurationItem#getOwnedConfigurationItems <em>Owned Configuration Items</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Configuration Items</em>'.
   * @see org.polarsys.capella.core.data.epbs.ConfigurationItem#getOwnedConfigurationItems()
   * @see #getConfigurationItem()
   * @generated
   */
	EReference getConfigurationItem_OwnedConfigurationItems();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.epbs.ConfigurationItem#getOwnedConfigurationItemPkgs <em>Owned Configuration Item Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Configuration Item Pkgs</em>'.
   * @see org.polarsys.capella.core.data.epbs.ConfigurationItem#getOwnedConfigurationItemPkgs()
   * @see #getConfigurationItem()
   * @generated
   */
	EReference getConfigurationItem_OwnedConfigurationItemPkgs();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.epbs.ConfigurationItem#getOwnedPhysicalArtifactRealizations <em>Owned Physical Artifact Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Physical Artifact Realizations</em>'.
   * @see org.polarsys.capella.core.data.epbs.ConfigurationItem#getOwnedPhysicalArtifactRealizations()
   * @see #getConfigurationItem()
   * @generated
   */
	EReference getConfigurationItem_OwnedPhysicalArtifactRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.epbs.ConfigurationItem#getAllocatedPhysicalArtifacts <em>Allocated Physical Artifacts</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated Physical Artifacts</em>'.
   * @see org.polarsys.capella.core.data.epbs.ConfigurationItem#getAllocatedPhysicalArtifacts()
   * @see #getConfigurationItem()
   * @generated
   */
	EReference getConfigurationItem_AllocatedPhysicalArtifacts();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization <em>Physical Architecture Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Physical Architecture Realization</em>'.
   * @see org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization
   * @generated
   */
	EClass getPhysicalArchitectureRealization();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization <em>Physical Artifact Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Physical Artifact Realization</em>'.
   * @see org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization
   * @generated
   */
	EClass getPhysicalArtifactRealization();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization#getRealizedPhysicalArtifact <em>Realized Physical Artifact</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realized Physical Artifact</em>'.
   * @see org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization#getRealizedPhysicalArtifact()
   * @see #getPhysicalArtifactRealization()
   * @generated
   */
	EReference getPhysicalArtifactRealization_RealizedPhysicalArtifact();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization#getRealizingConfigurationItem <em>Realizing Configuration Item</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realizing Configuration Item</em>'.
   * @see org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization#getRealizingConfigurationItem()
   * @see #getPhysicalArtifactRealization()
   * @generated
   */
	EReference getPhysicalArtifactRealization_RealizingConfigurationItem();

	/**
   * Returns the meta object for enum '{@link org.polarsys.capella.core.data.epbs.ConfigurationItemKind <em>Configuration Item Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Configuration Item Kind</em>'.
   * @see org.polarsys.capella.core.data.epbs.ConfigurationItemKind
   * @generated
   */
	EEnum getConfigurationItemKind();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	EpbsFactory getEpbsFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.epbs.impl.EPBSArchitecturePkgImpl <em>EPBS Architecture Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.epbs.impl.EPBSArchitecturePkgImpl
     * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getEPBSArchitecturePkg()
     * @generated
     */
		EClass EPBS_ARCHITECTURE_PKG = eINSTANCE.getEPBSArchitecturePkg();

		/**
     * The meta object literal for the '<em><b>Owned EPBS Architectures</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EPBS_ARCHITECTURE_PKG__OWNED_EPBS_ARCHITECTURES = eINSTANCE.getEPBSArchitecturePkg_OwnedEPBSArchitectures();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.epbs.impl.EPBSArchitectureImpl <em>EPBS Architecture</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.epbs.impl.EPBSArchitectureImpl
     * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getEPBSArchitecture()
     * @generated
     */
		EClass EPBS_ARCHITECTURE = eINSTANCE.getEPBSArchitecture();

		/**
     * The meta object literal for the '<em><b>Owned Configuration Item Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG = eINSTANCE.getEPBSArchitecture_OwnedConfigurationItemPkg();

		/**
     * The meta object literal for the '<em><b>Contained Capability Realization Pkg</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EPBS_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG = eINSTANCE.getEPBSArchitecture_ContainedCapabilityRealizationPkg();

		/**
     * The meta object literal for the '<em><b>Owned Physical Architecture Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EPBS_ARCHITECTURE__OWNED_PHYSICAL_ARCHITECTURE_REALIZATIONS = eINSTANCE.getEPBSArchitecture_OwnedPhysicalArchitectureRealizations();

		/**
     * The meta object literal for the '<em><b>Allocated Physical Architecture Realizations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURE_REALIZATIONS = eINSTANCE.getEPBSArchitecture_AllocatedPhysicalArchitectureRealizations();

		/**
     * The meta object literal for the '<em><b>Allocated Physical Architectures</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURES = eINSTANCE.getEPBSArchitecture_AllocatedPhysicalArchitectures();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.epbs.impl.ConfigurationItemPkgImpl <em>Configuration Item Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.epbs.impl.ConfigurationItemPkgImpl
     * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getConfigurationItemPkg()
     * @generated
     */
		EClass CONFIGURATION_ITEM_PKG = eINSTANCE.getConfigurationItemPkg();

		/**
     * The meta object literal for the '<em><b>Owned Configuration Items</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEMS = eINSTANCE.getConfigurationItemPkg_OwnedConfigurationItems();

		/**
     * The meta object literal for the '<em><b>Owned Configuration Item Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONFIGURATION_ITEM_PKG__OWNED_CONFIGURATION_ITEM_PKGS = eINSTANCE.getConfigurationItemPkg_OwnedConfigurationItemPkgs();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.epbs.impl.ConfigurationItemImpl <em>Configuration Item</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.epbs.impl.ConfigurationItemImpl
     * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getConfigurationItem()
     * @generated
     */
		EClass CONFIGURATION_ITEM = eINSTANCE.getConfigurationItem();

		/**
     * The meta object literal for the '<em><b>Item Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CONFIGURATION_ITEM__ITEM_IDENTIFIER = eINSTANCE.getConfigurationItem_ItemIdentifier();

		/**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute CONFIGURATION_ITEM__KIND = eINSTANCE.getConfigurationItem_Kind();

		/**
     * The meta object literal for the '<em><b>Owned Configuration Items</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONFIGURATION_ITEM__OWNED_CONFIGURATION_ITEMS = eINSTANCE.getConfigurationItem_OwnedConfigurationItems();

		/**
     * The meta object literal for the '<em><b>Owned Configuration Item Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONFIGURATION_ITEM__OWNED_CONFIGURATION_ITEM_PKGS = eINSTANCE.getConfigurationItem_OwnedConfigurationItemPkgs();

		/**
     * The meta object literal for the '<em><b>Owned Physical Artifact Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONFIGURATION_ITEM__OWNED_PHYSICAL_ARTIFACT_REALIZATIONS = eINSTANCE.getConfigurationItem_OwnedPhysicalArtifactRealizations();

		/**
     * The meta object literal for the '<em><b>Allocated Physical Artifacts</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference CONFIGURATION_ITEM__ALLOCATED_PHYSICAL_ARTIFACTS = eINSTANCE.getConfigurationItem_AllocatedPhysicalArtifacts();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.epbs.impl.PhysicalArchitectureRealizationImpl <em>Physical Architecture Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.epbs.impl.PhysicalArchitectureRealizationImpl
     * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getPhysicalArchitectureRealization()
     * @generated
     */
		EClass PHYSICAL_ARCHITECTURE_REALIZATION = eINSTANCE.getPhysicalArchitectureRealization();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.epbs.impl.PhysicalArtifactRealizationImpl <em>Physical Artifact Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.epbs.impl.PhysicalArtifactRealizationImpl
     * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getPhysicalArtifactRealization()
     * @generated
     */
		EClass PHYSICAL_ARTIFACT_REALIZATION = eINSTANCE.getPhysicalArtifactRealization();

		/**
     * The meta object literal for the '<em><b>Realized Physical Artifact</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_ARTIFACT_REALIZATION__REALIZED_PHYSICAL_ARTIFACT = eINSTANCE.getPhysicalArtifactRealization_RealizedPhysicalArtifact();

		/**
     * The meta object literal for the '<em><b>Realizing Configuration Item</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_ARTIFACT_REALIZATION__REALIZING_CONFIGURATION_ITEM = eINSTANCE.getPhysicalArtifactRealization_RealizingConfigurationItem();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.epbs.ConfigurationItemKind <em>Configuration Item Kind</em>}' enum.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.epbs.ConfigurationItemKind
     * @see org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl#getConfigurationItemKind()
     * @generated
     */
		EEnum CONFIGURATION_ITEM_KIND = eINSTANCE.getConfigurationItemKind();

	}

} //EpbsPackage
