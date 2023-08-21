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
package org.polarsys.capella.core.data.cs;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;

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
 * @see org.polarsys.capella.core.data.cs.CsFactory
 * @model kind="package"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/extension trackResourceModification='true' useUUIDs='false' useIDAttributes='true' extensibleProviderFactory='true' childCreationExtenders='true'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='CompositeStructure aims at defining the common component approach composite structure pattern language (close to the UML Composite structure).\r\n[source: Capella study]' usage\040guideline='none' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='none' constraints='This package depends on the model FunctionalAnalysis.ecore' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface CsPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "cs"; //$NON-NLS-1$

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http://www.polarsys.org/capella/core/cs/7.0.0"; //$NON-NLS-1$

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "org.polarsys.capella.core.data.cs"; //$NON-NLS-1$

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	CsPackage eINSTANCE = org.polarsys.capella.core.data.cs.impl.CsPackageImpl.init();

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.BlockArchitecturePkgImpl <em>Block Architecture Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.BlockArchitecturePkgImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getBlockArchitecturePkg()
   * @generated
   */
	int BLOCK_ARCHITECTURE_PKG = 0;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__OWNED_EXTENSIONS = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__ID = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__SID = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__CONSTRAINTS = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__OWNED_CONSTRAINTS = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__NAME = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__INCOMING_TRACES = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__OUTGOING_TRACES = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__VISIBLE_IN_DOC = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__VISIBLE_IN_LM = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__SUMMARY = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__DESCRIPTION = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__REVIEW = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__STATUS = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__FEATURES = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__OWNED_TRACES = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__NAMING_RULES = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.MODELLING_ARCHITECTURE_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The number of structural features of the '<em>Block Architecture Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_PKG_FEATURE_COUNT = CapellacorePackage.MODELLING_ARCHITECTURE_PKG_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.BlockArchitectureImpl <em>Block Architecture</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.BlockArchitectureImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getBlockArchitecture()
   * @generated
   */
	int BLOCK_ARCHITECTURE = 1;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_EXTENSIONS = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__ID = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__SID = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__CONSTRAINTS = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_CONSTRAINTS = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_MIGRATED_ELEMENTS = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__NAME = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__INCOMING_TRACES = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OUTGOING_TRACES = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__VISIBLE_IN_DOC = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__VISIBLE_IN_LM = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__SUMMARY = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__DESCRIPTION = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__REVIEW = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_PROPERTY_VALUES = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_ENUMERATION_PROPERTY_TYPES = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__APPLIED_PROPERTY_VALUES = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_PROPERTY_VALUE_GROUPS = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__APPLIED_PROPERTY_VALUE_GROUPS = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__STATUS = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__FEATURES = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_TRACES = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__CONTAINED_GENERIC_TRACES = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__NAMING_RULES = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_PROPERTY_VALUE_PKGS = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Function Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_FUNCTION_PKG = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_COMPONENT_EXCHANGES = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_FUNCTIONAL_LINKS = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTIONAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_FUNCTIONAL_ALLOCATIONS = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__OWNED_DATA_PKG = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Provisioned Architecture Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Provisioning Architecture Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Allocated Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__ALLOCATED_ARCHITECTURES = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Allocating Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__ALLOCATING_ARCHITECTURES = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>System</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE__SYSTEM = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE_FEATURE_COUNT + 7;

	/**
   * The number of structural features of the '<em>Block Architecture</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_ARCHITECTURE_FEATURE_COUNT = FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE_FEATURE_COUNT + 8;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.BlockImpl <em>Block</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.BlockImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getBlock()
   * @generated
   */
	int BLOCK = 2;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OWNED_EXTENSIONS = CapellacorePackage.MODELLING_BLOCK__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__ID = CapellacorePackage.MODELLING_BLOCK__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__SID = CapellacorePackage.MODELLING_BLOCK__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__CONSTRAINTS = CapellacorePackage.MODELLING_BLOCK__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OWNED_CONSTRAINTS = CapellacorePackage.MODELLING_BLOCK__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.MODELLING_BLOCK__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__NAME = CapellacorePackage.MODELLING_BLOCK__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__ABSTRACT_TYPED_ELEMENTS = CapellacorePackage.MODELLING_BLOCK__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__INCOMING_TRACES = CapellacorePackage.MODELLING_BLOCK__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OUTGOING_TRACES = CapellacorePackage.MODELLING_BLOCK__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__VISIBLE_IN_DOC = CapellacorePackage.MODELLING_BLOCK__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__VISIBLE_IN_LM = CapellacorePackage.MODELLING_BLOCK__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__SUMMARY = CapellacorePackage.MODELLING_BLOCK__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__DESCRIPTION = CapellacorePackage.MODELLING_BLOCK__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__REVIEW = CapellacorePackage.MODELLING_BLOCK__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OWNED_PROPERTY_VALUES = CapellacorePackage.MODELLING_BLOCK__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.MODELLING_BLOCK__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__APPLIED_PROPERTY_VALUES = CapellacorePackage.MODELLING_BLOCK__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.MODELLING_BLOCK__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.MODELLING_BLOCK__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__STATUS = CapellacorePackage.MODELLING_BLOCK__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__FEATURES = CapellacorePackage.MODELLING_BLOCK__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OWNED_TRACES = CapellacorePackage.MODELLING_BLOCK__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__CONTAINED_GENERIC_TRACES = CapellacorePackage.MODELLING_BLOCK__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__NAMING_RULES = CapellacorePackage.MODELLING_BLOCK__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__TYPED_ELEMENTS = CapellacorePackage.MODELLING_BLOCK__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OWNED_FUNCTIONAL_ALLOCATION = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OWNED_COMPONENT_EXCHANGES = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__FUNCTIONAL_ALLOCATIONS = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__ALLOCATED_FUNCTIONS = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__IN_EXCHANGE_LINKS = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OUT_EXCHANGE_LINKS = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OWNED_INTERFACE_PKG = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OWNED_DATA_PKG = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK__OWNED_STATE_MACHINES = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 10;

	/**
   * The number of structural features of the '<em>Block</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int BLOCK_FEATURE_COUNT = CapellacorePackage.MODELLING_BLOCK_FEATURE_COUNT + 11;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.ComponentArchitectureImpl <em>Component Architecture</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.ComponentArchitectureImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getComponentArchitecture()
   * @generated
   */
	int COMPONENT_ARCHITECTURE = 3;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_EXTENSIONS = BLOCK_ARCHITECTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__ID = BLOCK_ARCHITECTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__SID = BLOCK_ARCHITECTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__CONSTRAINTS = BLOCK_ARCHITECTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_CONSTRAINTS = BLOCK_ARCHITECTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_MIGRATED_ELEMENTS = BLOCK_ARCHITECTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__NAME = BLOCK_ARCHITECTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__INCOMING_TRACES = BLOCK_ARCHITECTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OUTGOING_TRACES = BLOCK_ARCHITECTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__VISIBLE_IN_DOC = BLOCK_ARCHITECTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__VISIBLE_IN_LM = BLOCK_ARCHITECTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__SUMMARY = BLOCK_ARCHITECTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__DESCRIPTION = BLOCK_ARCHITECTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__REVIEW = BLOCK_ARCHITECTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_PROPERTY_VALUES = BLOCK_ARCHITECTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_ENUMERATION_PROPERTY_TYPES = BLOCK_ARCHITECTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__APPLIED_PROPERTY_VALUES = BLOCK_ARCHITECTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_PROPERTY_VALUE_GROUPS = BLOCK_ARCHITECTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__APPLIED_PROPERTY_VALUE_GROUPS = BLOCK_ARCHITECTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__STATUS = BLOCK_ARCHITECTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__FEATURES = BLOCK_ARCHITECTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_TRACES = BLOCK_ARCHITECTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__CONTAINED_GENERIC_TRACES = BLOCK_ARCHITECTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__NAMING_RULES = BLOCK_ARCHITECTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_PROPERTY_VALUE_PKGS = BLOCK_ARCHITECTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Function Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_FUNCTION_PKG = BLOCK_ARCHITECTURE__OWNED_FUNCTION_PKG;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_COMPONENT_EXCHANGES = BLOCK_ARCHITECTURE__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES = BLOCK_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_FUNCTIONAL_LINKS = BLOCK_ARCHITECTURE__OWNED_FUNCTIONAL_LINKS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_FUNCTIONAL_ALLOCATIONS = BLOCK_ARCHITECTURE__OWNED_FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = BLOCK_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG = BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
   * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_INTERFACE_PKG = BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG;

	/**
   * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__OWNED_DATA_PKG = BLOCK_ARCHITECTURE__OWNED_DATA_PKG;

	/**
   * The feature id for the '<em><b>Provisioned Architecture Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS = BLOCK_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Provisioning Architecture Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS = BLOCK_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__ALLOCATED_ARCHITECTURES = BLOCK_ARCHITECTURE__ALLOCATED_ARCHITECTURES;

	/**
   * The feature id for the '<em><b>Allocating Architectures</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__ALLOCATING_ARCHITECTURES = BLOCK_ARCHITECTURE__ALLOCATING_ARCHITECTURES;

	/**
   * The feature id for the '<em><b>System</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE__SYSTEM = BLOCK_ARCHITECTURE__SYSTEM;

	/**
   * The number of structural features of the '<em>Component Architecture</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_ARCHITECTURE_FEATURE_COUNT = BLOCK_ARCHITECTURE_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.ComponentImpl <em>Component</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.ComponentImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getComponent()
   * @generated
   */
	int COMPONENT = 4;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_EXTENSIONS = BLOCK__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__ID = BLOCK__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__SID = BLOCK__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__CONSTRAINTS = BLOCK__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_CONSTRAINTS = BLOCK__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_MIGRATED_ELEMENTS = BLOCK__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__NAME = BLOCK__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__ABSTRACT_TYPED_ELEMENTS = BLOCK__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__INCOMING_TRACES = BLOCK__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OUTGOING_TRACES = BLOCK__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__VISIBLE_IN_DOC = BLOCK__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__VISIBLE_IN_LM = BLOCK__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__SUMMARY = BLOCK__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__DESCRIPTION = BLOCK__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__REVIEW = BLOCK__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_PROPERTY_VALUES = BLOCK__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_ENUMERATION_PROPERTY_TYPES = BLOCK__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__APPLIED_PROPERTY_VALUES = BLOCK__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_PROPERTY_VALUE_GROUPS = BLOCK__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__APPLIED_PROPERTY_VALUE_GROUPS = BLOCK__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__STATUS = BLOCK__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__FEATURES = BLOCK__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_TRACES = BLOCK__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__CONTAINED_GENERIC_TRACES = BLOCK__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__NAMING_RULES = BLOCK__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__TYPED_ELEMENTS = BLOCK__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Owned Functional Allocation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_FUNCTIONAL_ALLOCATION = BLOCK__OWNED_FUNCTIONAL_ALLOCATION;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_COMPONENT_EXCHANGES = BLOCK__OWNED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES = BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES;

	/**
   * The feature id for the '<em><b>Functional Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__FUNCTIONAL_ALLOCATIONS = BLOCK__FUNCTIONAL_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Functions</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__ALLOCATED_FUNCTIONS = BLOCK__ALLOCATED_FUNCTIONS;

	/**
   * The feature id for the '<em><b>In Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__IN_EXCHANGE_LINKS = BLOCK__IN_EXCHANGE_LINKS;

	/**
   * The feature id for the '<em><b>Out Exchange Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OUT_EXCHANGE_LINKS = BLOCK__OUT_EXCHANGE_LINKS;

	/**
   * The feature id for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG = BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG;

	/**
   * The feature id for the '<em><b>Owned Interface Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_INTERFACE_PKG = BLOCK__OWNED_INTERFACE_PKG;

	/**
   * The feature id for the '<em><b>Owned Data Pkg</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_DATA_PKG = BLOCK__OWNED_DATA_PKG;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_STATE_MACHINES = BLOCK__OWNED_STATE_MACHINES;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__ABSTRACT = BLOCK_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_GENERALIZATIONS = BLOCK_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__SUPER_GENERALIZATIONS = BLOCK_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__SUB_GENERALIZATIONS = BLOCK_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__SUPER = BLOCK_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__SUB = BLOCK_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_FEATURES = BLOCK_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__CONTAINED_PROPERTIES = BLOCK_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_INTERFACE_ALLOCATIONS = BLOCK_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS = BLOCK_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__ALLOCATED_INTERFACES = BLOCK_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Owned Communication Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_COMMUNICATION_LINKS = BLOCK_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Produce</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__PRODUCE = BLOCK_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Consume</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__CONSUME = BLOCK_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Send</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__SEND = BLOCK_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Receive</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__RECEIVE = BLOCK_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Call</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__CALL = BLOCK_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Execute</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__EXECUTE = BLOCK_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Write</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__WRITE = BLOCK_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Access</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__ACCESS = BLOCK_FEATURE_COUNT + 19;

	/**
   * The feature id for the '<em><b>Acquire</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__ACQUIRE = BLOCK_FEATURE_COUNT + 20;

	/**
   * The feature id for the '<em><b>Transmit</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__TRANSMIT = BLOCK_FEATURE_COUNT + 21;

	/**
   * The feature id for the '<em><b>Actor</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__ACTOR = BLOCK_FEATURE_COUNT + 22;

	/**
   * The feature id for the '<em><b>Human</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__HUMAN = BLOCK_FEATURE_COUNT + 23;

	/**
   * The feature id for the '<em><b>Owned Interface Uses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_INTERFACE_USES = BLOCK_FEATURE_COUNT + 24;

	/**
   * The feature id for the '<em><b>Used Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__USED_INTERFACE_LINKS = BLOCK_FEATURE_COUNT + 25;

	/**
   * The feature id for the '<em><b>Used Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__USED_INTERFACES = BLOCK_FEATURE_COUNT + 26;

	/**
   * The feature id for the '<em><b>Owned Interface Implementations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS = BLOCK_FEATURE_COUNT + 27;

	/**
   * The feature id for the '<em><b>Implemented Interface Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__IMPLEMENTED_INTERFACE_LINKS = BLOCK_FEATURE_COUNT + 28;

	/**
   * The feature id for the '<em><b>Implemented Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__IMPLEMENTED_INTERFACES = BLOCK_FEATURE_COUNT + 29;

	/**
   * The feature id for the '<em><b>Owned Component Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_COMPONENT_REALIZATIONS = BLOCK_FEATURE_COUNT + 30;

	/**
   * The feature id for the '<em><b>Realized Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__REALIZED_COMPONENTS = BLOCK_FEATURE_COUNT + 31;

	/**
   * The feature id for the '<em><b>Realizing Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__REALIZING_COMPONENTS = BLOCK_FEATURE_COUNT + 32;

	/**
   * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__PROVIDED_INTERFACES = BLOCK_FEATURE_COUNT + 33;

	/**
   * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__REQUIRED_INTERFACES = BLOCK_FEATURE_COUNT + 34;

	/**
   * The feature id for the '<em><b>Contained Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__CONTAINED_COMPONENT_PORTS = BLOCK_FEATURE_COUNT + 35;

	/**
   * The feature id for the '<em><b>Contained Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__CONTAINED_PARTS = BLOCK_FEATURE_COUNT + 36;

	/**
   * The feature id for the '<em><b>Contained Physical Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__CONTAINED_PHYSICAL_PORTS = BLOCK_FEATURE_COUNT + 37;

	/**
   * The feature id for the '<em><b>Owned Physical Path</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_PHYSICAL_PATH = BLOCK_FEATURE_COUNT + 38;

	/**
   * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_PHYSICAL_LINKS = BLOCK_FEATURE_COUNT + 39;

	/**
   * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES = BLOCK_FEATURE_COUNT + 40;

	/**
   * The feature id for the '<em><b>Representing Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT__REPRESENTING_PARTS = BLOCK_FEATURE_COUNT + 41;

	/**
   * The number of structural features of the '<em>Component</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_FEATURE_COUNT = BLOCK_FEATURE_COUNT + 42;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.PartImpl <em>Part</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.PartImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPart()
   * @generated
   */
	int PART = 5;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_EXTENSIONS = InformationPackage.ABSTRACT_INSTANCE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__ID = InformationPackage.ABSTRACT_INSTANCE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__SID = InformationPackage.ABSTRACT_INSTANCE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__CONSTRAINTS = InformationPackage.ABSTRACT_INSTANCE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_CONSTRAINTS = InformationPackage.ABSTRACT_INSTANCE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_MIGRATED_ELEMENTS = InformationPackage.ABSTRACT_INSTANCE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__NAME = InformationPackage.ABSTRACT_INSTANCE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__INCOMING_TRACES = InformationPackage.ABSTRACT_INSTANCE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OUTGOING_TRACES = InformationPackage.ABSTRACT_INSTANCE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__VISIBLE_IN_DOC = InformationPackage.ABSTRACT_INSTANCE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__VISIBLE_IN_LM = InformationPackage.ABSTRACT_INSTANCE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__SUMMARY = InformationPackage.ABSTRACT_INSTANCE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__DESCRIPTION = InformationPackage.ABSTRACT_INSTANCE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__REVIEW = InformationPackage.ABSTRACT_INSTANCE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_PROPERTY_VALUES = InformationPackage.ABSTRACT_INSTANCE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_ENUMERATION_PROPERTY_TYPES = InformationPackage.ABSTRACT_INSTANCE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__APPLIED_PROPERTY_VALUES = InformationPackage.ABSTRACT_INSTANCE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_PROPERTY_VALUE_GROUPS = InformationPackage.ABSTRACT_INSTANCE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__APPLIED_PROPERTY_VALUE_GROUPS = InformationPackage.ABSTRACT_INSTANCE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__STATUS = InformationPackage.ABSTRACT_INSTANCE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__FEATURES = InformationPackage.ABSTRACT_INSTANCE__FEATURES;

	/**
   * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__IS_ABSTRACT = InformationPackage.ABSTRACT_INSTANCE__IS_ABSTRACT;

	/**
   * The feature id for the '<em><b>Is Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__IS_STATIC = InformationPackage.ABSTRACT_INSTANCE__IS_STATIC;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__VISIBILITY = InformationPackage.ABSTRACT_INSTANCE__VISIBILITY;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__ABSTRACT_TYPE = InformationPackage.ABSTRACT_INSTANCE__ABSTRACT_TYPE;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__TYPE = InformationPackage.ABSTRACT_INSTANCE__TYPE;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__ORDERED = InformationPackage.ABSTRACT_INSTANCE__ORDERED;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__UNIQUE = InformationPackage.ABSTRACT_INSTANCE__UNIQUE;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__MIN_INCLUSIVE = InformationPackage.ABSTRACT_INSTANCE__MIN_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__MAX_INCLUSIVE = InformationPackage.ABSTRACT_INSTANCE__MAX_INCLUSIVE;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_DEFAULT_VALUE = InformationPackage.ABSTRACT_INSTANCE__OWNED_DEFAULT_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_MIN_VALUE = InformationPackage.ABSTRACT_INSTANCE__OWNED_MIN_VALUE;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_MAX_VALUE = InformationPackage.ABSTRACT_INSTANCE__OWNED_MAX_VALUE;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_NULL_VALUE = InformationPackage.ABSTRACT_INSTANCE__OWNED_NULL_VALUE;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_MIN_CARD = InformationPackage.ABSTRACT_INSTANCE__OWNED_MIN_CARD;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_MIN_LENGTH = InformationPackage.ABSTRACT_INSTANCE__OWNED_MIN_LENGTH;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_MAX_CARD = InformationPackage.ABSTRACT_INSTANCE__OWNED_MAX_CARD;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_MAX_LENGTH = InformationPackage.ABSTRACT_INSTANCE__OWNED_MAX_LENGTH;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__FINAL = InformationPackage.ABSTRACT_INSTANCE__FINAL;

	/**
   * The feature id for the '<em><b>Aggregation Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__AGGREGATION_KIND = InformationPackage.ABSTRACT_INSTANCE__AGGREGATION_KIND;

	/**
   * The feature id for the '<em><b>Is Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__IS_DERIVED = InformationPackage.ABSTRACT_INSTANCE__IS_DERIVED;

	/**
   * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__IS_READ_ONLY = InformationPackage.ABSTRACT_INSTANCE__IS_READ_ONLY;

	/**
   * The feature id for the '<em><b>Is Part Of Key</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__IS_PART_OF_KEY = InformationPackage.ABSTRACT_INSTANCE__IS_PART_OF_KEY;

	/**
   * The feature id for the '<em><b>Association</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__ASSOCIATION = InformationPackage.ABSTRACT_INSTANCE__ASSOCIATION;

	/**
   * The feature id for the '<em><b>Representing Instance Roles</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__REPRESENTING_INSTANCE_ROLES = InformationPackage.ABSTRACT_INSTANCE__REPRESENTING_INSTANCE_ROLES;

	/**
   * The feature id for the '<em><b>Incoming Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__INCOMING_INFORMATION_FLOWS = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Outgoing Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OUTGOING_INFORMATION_FLOWS = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__INFORMATION_FLOWS = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Deploying Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__DEPLOYING_LINKS = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Deployment Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__DEPLOYMENT_LINKS = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__INVOLVING_INVOLVEMENTS = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__PROVIDED_INTERFACES = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__REQUIRED_INTERFACES = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned Deployment Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_DEPLOYMENT_LINKS = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Deployed Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__DEPLOYED_PARTS = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Deploying Parts</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__DEPLOYING_PARTS = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Owned Abstract Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART__OWNED_ABSTRACT_TYPE = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 11;

	/**
   * The number of structural features of the '<em>Part</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PART_FEATURE_COUNT = InformationPackage.ABSTRACT_INSTANCE_FEATURE_COUNT + 12;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.ArchitectureAllocationImpl <em>Architecture Allocation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.ArchitectureAllocationImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getArchitectureAllocation()
   * @generated
   */
	int ARCHITECTURE_ALLOCATION = 6;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Allocated Architecture</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Allocating Architecture</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Architecture Allocation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ARCHITECTURE_ALLOCATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.ComponentRealizationImpl <em>Component Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.ComponentRealizationImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getComponentRealization()
   * @generated
   */
	int COMPONENT_REALIZATION = 7;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Realized Component</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__REALIZED_COMPONENT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Realizing Component</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION__REALIZING_COMPONENT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Component Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.InterfacePkgImpl <em>Interface Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.InterfacePkgImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getInterfacePkg()
   * @generated
   */
	int INTERFACE_PKG = 8;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__OWNED_EXTENSIONS = CommunicationPackage.MESSAGE_REFERENCE_PKG__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__ID = CommunicationPackage.MESSAGE_REFERENCE_PKG__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__SID = CommunicationPackage.MESSAGE_REFERENCE_PKG__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__CONSTRAINTS = CommunicationPackage.MESSAGE_REFERENCE_PKG__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__OWNED_CONSTRAINTS = CommunicationPackage.MESSAGE_REFERENCE_PKG__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__OWNED_MIGRATED_ELEMENTS = CommunicationPackage.MESSAGE_REFERENCE_PKG__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__NAME = CommunicationPackage.MESSAGE_REFERENCE_PKG__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__INCOMING_TRACES = CommunicationPackage.MESSAGE_REFERENCE_PKG__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__OUTGOING_TRACES = CommunicationPackage.MESSAGE_REFERENCE_PKG__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__VISIBLE_IN_DOC = CommunicationPackage.MESSAGE_REFERENCE_PKG__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__VISIBLE_IN_LM = CommunicationPackage.MESSAGE_REFERENCE_PKG__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__SUMMARY = CommunicationPackage.MESSAGE_REFERENCE_PKG__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__DESCRIPTION = CommunicationPackage.MESSAGE_REFERENCE_PKG__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__REVIEW = CommunicationPackage.MESSAGE_REFERENCE_PKG__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__OWNED_PROPERTY_VALUES = CommunicationPackage.MESSAGE_REFERENCE_PKG__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CommunicationPackage.MESSAGE_REFERENCE_PKG__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__APPLIED_PROPERTY_VALUES = CommunicationPackage.MESSAGE_REFERENCE_PKG__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__OWNED_PROPERTY_VALUE_GROUPS = CommunicationPackage.MESSAGE_REFERENCE_PKG__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CommunicationPackage.MESSAGE_REFERENCE_PKG__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__STATUS = CommunicationPackage.MESSAGE_REFERENCE_PKG__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__FEATURES = CommunicationPackage.MESSAGE_REFERENCE_PKG__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__OWNED_TRACES = CommunicationPackage.MESSAGE_REFERENCE_PKG__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__CONTAINED_GENERIC_TRACES = CommunicationPackage.MESSAGE_REFERENCE_PKG__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__NAMING_RULES = CommunicationPackage.MESSAGE_REFERENCE_PKG__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__OWNED_PROPERTY_VALUE_PKGS = CommunicationPackage.MESSAGE_REFERENCE_PKG__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Message References</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__OWNED_MESSAGE_REFERENCES = CommunicationPackage.MESSAGE_REFERENCE_PKG__OWNED_MESSAGE_REFERENCES;

	/**
   * The feature id for the '<em><b>Owned Exchange Items</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__OWNED_EXCHANGE_ITEMS = CommunicationPackage.MESSAGE_REFERENCE_PKG_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Interfaces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__OWNED_INTERFACES = CommunicationPackage.MESSAGE_REFERENCE_PKG_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Interface Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG__OWNED_INTERFACE_PKGS = CommunicationPackage.MESSAGE_REFERENCE_PKG_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Interface Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_PKG_FEATURE_COUNT = CommunicationPackage.MESSAGE_REFERENCE_PKG_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl <em>Interface</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.InterfaceImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getInterface()
   * @generated
   */
	int INTERFACE = 9;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__OWNED_EXTENSIONS = CapellacorePackage.GENERAL_CLASS__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__ID = CapellacorePackage.GENERAL_CLASS__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__SID = CapellacorePackage.GENERAL_CLASS__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__CONSTRAINTS = CapellacorePackage.GENERAL_CLASS__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__OWNED_CONSTRAINTS = CapellacorePackage.GENERAL_CLASS__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.GENERAL_CLASS__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__NAME = CapellacorePackage.GENERAL_CLASS__NAME;

	/**
   * The feature id for the '<em><b>Abstract Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__ABSTRACT_TYPED_ELEMENTS = CapellacorePackage.GENERAL_CLASS__ABSTRACT_TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__INCOMING_TRACES = CapellacorePackage.GENERAL_CLASS__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__OUTGOING_TRACES = CapellacorePackage.GENERAL_CLASS__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__VISIBLE_IN_DOC = CapellacorePackage.GENERAL_CLASS__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__VISIBLE_IN_LM = CapellacorePackage.GENERAL_CLASS__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__SUMMARY = CapellacorePackage.GENERAL_CLASS__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__DESCRIPTION = CapellacorePackage.GENERAL_CLASS__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__REVIEW = CapellacorePackage.GENERAL_CLASS__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__OWNED_PROPERTY_VALUES = CapellacorePackage.GENERAL_CLASS__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.GENERAL_CLASS__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__APPLIED_PROPERTY_VALUES = CapellacorePackage.GENERAL_CLASS__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.GENERAL_CLASS__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.GENERAL_CLASS__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__STATUS = CapellacorePackage.GENERAL_CLASS__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__FEATURES = CapellacorePackage.GENERAL_CLASS__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__OWNED_TRACES = CapellacorePackage.GENERAL_CLASS__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__CONTAINED_GENERIC_TRACES = CapellacorePackage.GENERAL_CLASS__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__NAMING_RULES = CapellacorePackage.GENERAL_CLASS__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Typed Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__TYPED_ELEMENTS = CapellacorePackage.GENERAL_CLASS__TYPED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__ABSTRACT = CapellacorePackage.GENERAL_CLASS__ABSTRACT;

	/**
   * The feature id for the '<em><b>Owned Generalizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__OWNED_GENERALIZATIONS = CapellacorePackage.GENERAL_CLASS__OWNED_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__SUPER_GENERALIZATIONS = CapellacorePackage.GENERAL_CLASS__SUPER_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Sub Generalizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__SUB_GENERALIZATIONS = CapellacorePackage.GENERAL_CLASS__SUB_GENERALIZATIONS;

	/**
   * The feature id for the '<em><b>Super</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__SUPER = CapellacorePackage.GENERAL_CLASS__SUPER;

	/**
   * The feature id for the '<em><b>Sub</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__SUB = CapellacorePackage.GENERAL_CLASS__SUB;

	/**
   * The feature id for the '<em><b>Owned Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__OWNED_FEATURES = CapellacorePackage.GENERAL_CLASS__OWNED_FEATURES;

	/**
   * The feature id for the '<em><b>Contained Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__CONTAINED_PROPERTIES = CapellacorePackage.GENERAL_CLASS__CONTAINED_PROPERTIES;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__FINAL = CapellacorePackage.GENERAL_CLASS__FINAL;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__VISIBILITY = CapellacorePackage.GENERAL_CLASS__VISIBILITY;

	/**
   * The feature id for the '<em><b>Contained Operations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__CONTAINED_OPERATIONS = CapellacorePackage.GENERAL_CLASS__CONTAINED_OPERATIONS;

	/**
   * The feature id for the '<em><b>Nested General Classes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__NESTED_GENERAL_CLASSES = CapellacorePackage.GENERAL_CLASS__NESTED_GENERAL_CLASSES;

	/**
   * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__OWNED_INTERFACE_ALLOCATIONS = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__PROVISIONED_INTERFACE_ALLOCATIONS = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__ALLOCATED_INTERFACES = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Mechanism</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__MECHANISM = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Structural</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__STRUCTURAL = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Implementor Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__IMPLEMENTOR_COMPONENTS = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>User Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__USER_COMPONENTS = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Interface Implementations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__INTERFACE_IMPLEMENTATIONS = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Interface Uses</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__INTERFACE_USES = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Provisioning Interface Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__PROVISIONING_INTERFACE_ALLOCATIONS = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Allocating Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__ALLOCATING_INTERFACES = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Allocating Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__ALLOCATING_COMPONENTS = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Exchange Items</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__EXCHANGE_ITEMS = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Owned Exchange Item Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__OWNED_EXCHANGE_ITEM_ALLOCATIONS = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Requiring Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__REQUIRING_COMPONENTS = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Requiring Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__REQUIRING_COMPONENT_PORTS = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Providing Components</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__PROVIDING_COMPONENTS = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Providing Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__PROVIDING_COMPONENT_PORTS = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Realizing Logical Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__REALIZING_LOGICAL_INTERFACES = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Realized Context Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__REALIZED_CONTEXT_INTERFACES = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 19;

	/**
   * The feature id for the '<em><b>Realizing Physical Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__REALIZING_PHYSICAL_INTERFACES = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 20;

	/**
   * The feature id for the '<em><b>Realized Logical Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE__REALIZED_LOGICAL_INTERFACES = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 21;

	/**
   * The number of structural features of the '<em>Interface</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_FEATURE_COUNT = CapellacorePackage.GENERAL_CLASS_FEATURE_COUNT + 22;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.InterfaceImplementationImpl <em>Interface Implementation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.InterfaceImplementationImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getInterfaceImplementation()
   * @generated
   */
	int INTERFACE_IMPLEMENTATION = 10;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__OWNED_EXTENSIONS = CapellacorePackage.RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__ID = CapellacorePackage.RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__SID = CapellacorePackage.RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__CONSTRAINTS = CapellacorePackage.RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__OWNED_CONSTRAINTS = CapellacorePackage.RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__REALIZED_FLOW = CapellacorePackage.RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__INCOMING_TRACES = CapellacorePackage.RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__OUTGOING_TRACES = CapellacorePackage.RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__VISIBLE_IN_DOC = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__VISIBLE_IN_LM = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__SUMMARY = CapellacorePackage.RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__DESCRIPTION = CapellacorePackage.RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__REVIEW = CapellacorePackage.RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__OWNED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__STATUS = CapellacorePackage.RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__FEATURES = CapellacorePackage.RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Interface Implementor</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__INTERFACE_IMPLEMENTOR = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Implemented Interface</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Interface Implementation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_IMPLEMENTATION_FEATURE_COUNT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.InterfaceUseImpl <em>Interface Use</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.InterfaceUseImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getInterfaceUse()
   * @generated
   */
	int INTERFACE_USE = 11;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__OWNED_EXTENSIONS = CapellacorePackage.RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__ID = CapellacorePackage.RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__SID = CapellacorePackage.RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__CONSTRAINTS = CapellacorePackage.RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__OWNED_CONSTRAINTS = CapellacorePackage.RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__REALIZED_FLOW = CapellacorePackage.RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__INCOMING_TRACES = CapellacorePackage.RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__OUTGOING_TRACES = CapellacorePackage.RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__VISIBLE_IN_DOC = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__VISIBLE_IN_LM = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__SUMMARY = CapellacorePackage.RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__DESCRIPTION = CapellacorePackage.RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__REVIEW = CapellacorePackage.RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__OWNED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__APPLIED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__STATUS = CapellacorePackage.RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__FEATURES = CapellacorePackage.RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Interface User</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__INTERFACE_USER = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Used Interface</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE__USED_INTERFACE = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Interface Use</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_USE_FEATURE_COUNT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.ProvidedInterfaceLinkImpl <em>Provided Interface Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.ProvidedInterfaceLinkImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getProvidedInterfaceLink()
   * @generated
   */
	int PROVIDED_INTERFACE_LINK = 12;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__OWNED_EXTENSIONS = CapellacorePackage.RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__ID = CapellacorePackage.RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__SID = CapellacorePackage.RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__CONSTRAINTS = CapellacorePackage.RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__OWNED_CONSTRAINTS = CapellacorePackage.RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__REALIZED_FLOW = CapellacorePackage.RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__INCOMING_TRACES = CapellacorePackage.RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__OUTGOING_TRACES = CapellacorePackage.RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__VISIBLE_IN_DOC = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__VISIBLE_IN_LM = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__SUMMARY = CapellacorePackage.RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__DESCRIPTION = CapellacorePackage.RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__REVIEW = CapellacorePackage.RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__OWNED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__APPLIED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__STATUS = CapellacorePackage.RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__FEATURES = CapellacorePackage.RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Interface</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK__INTERFACE = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Provided Interface Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PROVIDED_INTERFACE_LINK_FEATURE_COUNT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.RequiredInterfaceLinkImpl <em>Required Interface Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.RequiredInterfaceLinkImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getRequiredInterfaceLink()
   * @generated
   */
	int REQUIRED_INTERFACE_LINK = 13;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__OWNED_EXTENSIONS = CapellacorePackage.RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__ID = CapellacorePackage.RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__SID = CapellacorePackage.RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__CONSTRAINTS = CapellacorePackage.RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__OWNED_CONSTRAINTS = CapellacorePackage.RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__REALIZED_FLOW = CapellacorePackage.RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__INCOMING_TRACES = CapellacorePackage.RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__OUTGOING_TRACES = CapellacorePackage.RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__VISIBLE_IN_DOC = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__VISIBLE_IN_LM = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__SUMMARY = CapellacorePackage.RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__DESCRIPTION = CapellacorePackage.RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__REVIEW = CapellacorePackage.RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__OWNED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__APPLIED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__STATUS = CapellacorePackage.RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__FEATURES = CapellacorePackage.RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Interface</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK__INTERFACE = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Required Interface Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int REQUIRED_INTERFACE_LINK_FEATURE_COUNT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.InterfaceAllocationImpl <em>Interface Allocation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.InterfaceAllocationImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getInterfaceAllocation()
   * @generated
   */
	int INTERFACE_ALLOCATION = 14;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The feature id for the '<em><b>Allocated Interface</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__ALLOCATED_INTERFACE = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Allocating Interface Allocator</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION__ALLOCATING_INTERFACE_ALLOCATOR = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Interface Allocation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.InterfaceAllocator <em>Interface Allocator</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.InterfaceAllocator
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getInterfaceAllocator()
   * @generated
   */
	int INTERFACE_ALLOCATOR = 15;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Interface Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__OWNED_INTERFACE_ALLOCATIONS = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Provisioned Interface Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Allocated Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The number of structural features of the '<em>Interface Allocator</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int INTERFACE_ALLOCATOR_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.ExchangeItemAllocationImpl <em>Exchange Item Allocation</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.ExchangeItemAllocationImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getExchangeItemAllocation()
   * @generated
   */
	int EXCHANGE_ITEM_ALLOCATION = 16;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__OWNED_EXTENSIONS = CapellacorePackage.RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__ID = CapellacorePackage.RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__SID = CapellacorePackage.RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__CONSTRAINTS = CapellacorePackage.RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__OWNED_CONSTRAINTS = CapellacorePackage.RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__REALIZED_FLOW = CapellacorePackage.RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__INCOMING_TRACES = CapellacorePackage.RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__OUTGOING_TRACES = CapellacorePackage.RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__VISIBLE_IN_DOC = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__VISIBLE_IN_LM = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__SUMMARY = CapellacorePackage.RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__DESCRIPTION = CapellacorePackage.RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__REVIEW = CapellacorePackage.RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__OWNED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__STATUS = CapellacorePackage.RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__FEATURES = CapellacorePackage.RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__NAME = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Invoking Sequence Messages</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__INVOKING_SEQUENCE_MESSAGES = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__FINAL = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Send Protocol</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__SEND_PROTOCOL = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Receive Protocol</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__RECEIVE_PROTOCOL = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Allocated Item</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Allocating Interface</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 6;

	/**
   * The number of structural features of the '<em>Exchange Item Allocation</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int EXCHANGE_ITEM_ALLOCATION_FEATURE_COUNT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 7;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.DeployableElementImpl <em>Deployable Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.DeployableElementImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getDeployableElement()
   * @generated
   */
	int DEPLOYABLE_ELEMENT = 17;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Deploying Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT__DEPLOYING_LINKS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Deployable Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYABLE_ELEMENT_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.DeploymentTargetImpl <em>Deployment Target</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.DeploymentTargetImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getDeploymentTarget()
   * @generated
   */
	int DEPLOYMENT_TARGET = 18;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Deployment Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET__DEPLOYMENT_LINKS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Deployment Target</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DEPLOYMENT_TARGET_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.AbstractDeploymentLinkImpl <em>Abstract Deployment Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.AbstractDeploymentLinkImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getAbstractDeploymentLink()
   * @generated
   */
	int ABSTRACT_DEPLOYMENT_LINK = 19;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__OWNED_EXTENSIONS = CapellacorePackage.RELATIONSHIP__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__ID = CapellacorePackage.RELATIONSHIP__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__SID = CapellacorePackage.RELATIONSHIP__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__CONSTRAINTS = CapellacorePackage.RELATIONSHIP__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__OWNED_CONSTRAINTS = CapellacorePackage.RELATIONSHIP__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.RELATIONSHIP__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__REALIZED_FLOW = CapellacorePackage.RELATIONSHIP__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__INCOMING_TRACES = CapellacorePackage.RELATIONSHIP__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__OUTGOING_TRACES = CapellacorePackage.RELATIONSHIP__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__VISIBLE_IN_DOC = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__VISIBLE_IN_LM = CapellacorePackage.RELATIONSHIP__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__SUMMARY = CapellacorePackage.RELATIONSHIP__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__DESCRIPTION = CapellacorePackage.RELATIONSHIP__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__REVIEW = CapellacorePackage.RELATIONSHIP__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__OWNED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.RELATIONSHIP__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__APPLIED_PROPERTY_VALUES = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.RELATIONSHIP__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__STATUS = CapellacorePackage.RELATIONSHIP__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__FEATURES = CapellacorePackage.RELATIONSHIP__FEATURES;

	/**
   * The feature id for the '<em><b>Deployed Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Location</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK__LOCATION = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Abstract Deployment Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_DEPLOYMENT_LINK_FEATURE_COUNT = CapellacorePackage.RELATIONSHIP_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl <em>Abstract Path Involved Element</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getAbstractPathInvolvedElement()
   * @generated
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT = 20;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_EXTENSIONS = CapellacorePackage.INVOLVED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__ID = CapellacorePackage.INVOLVED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__SID = CapellacorePackage.INVOLVED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__CONSTRAINTS = CapellacorePackage.INVOLVED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_CONSTRAINTS = CapellacorePackage.INVOLVED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.INVOLVED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__INCOMING_TRACES = CapellacorePackage.INVOLVED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__OUTGOING_TRACES = CapellacorePackage.INVOLVED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_DOC = CapellacorePackage.INVOLVED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_LM = CapellacorePackage.INVOLVED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__SUMMARY = CapellacorePackage.INVOLVED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__DESCRIPTION = CapellacorePackage.INVOLVED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__REVIEW = CapellacorePackage.INVOLVED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.INVOLVED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.INVOLVED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__STATUS = CapellacorePackage.INVOLVED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__FEATURES = CapellacorePackage.INVOLVED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS = CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS;

	/**
   * The number of structural features of the '<em>Abstract Path Involved Element</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PATH_INVOLVED_ELEMENT_FEATURE_COUNT = CapellacorePackage.INVOLVED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.AbstractPhysicalArtifactImpl <em>Abstract Physical Artifact</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.AbstractPhysicalArtifactImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getAbstractPhysicalArtifact()
   * @generated
   */
	int ABSTRACT_PHYSICAL_ARTIFACT = 21;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Allocator Configuration Items</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Physical Artifact</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_ARTIFACT_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.AbstractPhysicalLinkEndImpl <em>Abstract Physical Link End</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.AbstractPhysicalLinkEndImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getAbstractPhysicalLinkEnd()
   * @generated
   */
	int ABSTRACT_PHYSICAL_LINK_END = 22;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__OWNED_EXTENSIONS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__ID = CapellacorePackage.CAPELLA_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__SID = CapellacorePackage.CAPELLA_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__OWNED_CONSTRAINTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__INCOMING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__OUTGOING_TRACES = CapellacorePackage.CAPELLA_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__VISIBLE_IN_DOC = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__VISIBLE_IN_LM = CapellacorePackage.CAPELLA_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__SUMMARY = CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__DESCRIPTION = CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__REVIEW = CapellacorePackage.CAPELLA_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__OWNED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__APPLIED_PROPERTY_VALUES = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__STATUS = CapellacorePackage.CAPELLA_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__FEATURES = CapellacorePackage.CAPELLA_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involved Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Abstract Physical Link End</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_LINK_END_FEATURE_COUNT = CapellacorePackage.CAPELLA_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.AbstractPhysicalPathLinkImpl <em>Abstract Physical Path Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.AbstractPhysicalPathLinkImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getAbstractPhysicalPathLink()
   * @generated
   */
	int ABSTRACT_PHYSICAL_PATH_LINK = 23;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__OWNED_EXTENSIONS = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__ID = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__SID = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__CONSTRAINTS = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__OWNED_CONSTRAINTS = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__OWNED_MIGRATED_ELEMENTS = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__NAME = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__INCOMING_TRACES = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__OUTGOING_TRACES = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__VISIBLE_IN_DOC = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__VISIBLE_IN_LM = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__SUMMARY = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__DESCRIPTION = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__REVIEW = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__OWNED_PROPERTY_VALUES = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__APPLIED_PROPERTY_VALUES = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__OWNED_PROPERTY_VALUE_GROUPS = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__APPLIED_PROPERTY_VALUE_GROUPS = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__STATUS = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__FEATURES = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Component Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK__ALLOCATED_COMPONENT_EXCHANGES = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR__ALLOCATED_COMPONENT_EXCHANGES;

	/**
   * The number of structural features of the '<em>Abstract Physical Path Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ABSTRACT_PHYSICAL_PATH_LINK_FEATURE_COUNT = FaPackage.COMPONENT_EXCHANGE_ALLOCATOR_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkImpl <em>Physical Link</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.PhysicalLinkImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalLink()
   * @generated
   */
	int PHYSICAL_LINK = 24;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__OWNED_EXTENSIONS = ABSTRACT_PHYSICAL_PATH_LINK__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__ID = ABSTRACT_PHYSICAL_PATH_LINK__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__SID = ABSTRACT_PHYSICAL_PATH_LINK__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__CONSTRAINTS = ABSTRACT_PHYSICAL_PATH_LINK__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__OWNED_CONSTRAINTS = ABSTRACT_PHYSICAL_PATH_LINK__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__OWNED_MIGRATED_ELEMENTS = ABSTRACT_PHYSICAL_PATH_LINK__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__NAME = ABSTRACT_PHYSICAL_PATH_LINK__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__INCOMING_TRACES = ABSTRACT_PHYSICAL_PATH_LINK__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__OUTGOING_TRACES = ABSTRACT_PHYSICAL_PATH_LINK__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__VISIBLE_IN_DOC = ABSTRACT_PHYSICAL_PATH_LINK__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__VISIBLE_IN_LM = ABSTRACT_PHYSICAL_PATH_LINK__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__SUMMARY = ABSTRACT_PHYSICAL_PATH_LINK__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__DESCRIPTION = ABSTRACT_PHYSICAL_PATH_LINK__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__REVIEW = ABSTRACT_PHYSICAL_PATH_LINK__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__OWNED_PROPERTY_VALUES = ABSTRACT_PHYSICAL_PATH_LINK__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_PHYSICAL_PATH_LINK__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__APPLIED_PROPERTY_VALUES = ABSTRACT_PHYSICAL_PATH_LINK__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_PHYSICAL_PATH_LINK__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_PHYSICAL_PATH_LINK__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__STATUS = ABSTRACT_PHYSICAL_PATH_LINK__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__FEATURES = ABSTRACT_PHYSICAL_PATH_LINK__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS = ABSTRACT_PHYSICAL_PATH_LINK__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocated Component Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__ALLOCATED_COMPONENT_EXCHANGES = ABSTRACT_PHYSICAL_PATH_LINK__ALLOCATED_COMPONENT_EXCHANGES;

	/**
   * The feature id for the '<em><b>Allocator Configuration Items</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__ALLOCATOR_CONFIGURATION_ITEMS = ABSTRACT_PHYSICAL_PATH_LINK_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__INVOLVING_INVOLVEMENTS = ABSTRACT_PHYSICAL_PATH_LINK_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Link Ends</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__LINK_ENDS = ABSTRACT_PHYSICAL_PATH_LINK_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Functional Exchange Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS = ABSTRACT_PHYSICAL_PATH_LINK_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Physical Link Ends</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__OWNED_PHYSICAL_LINK_ENDS = ABSTRACT_PHYSICAL_PATH_LINK_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Owned Physical Link Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS = ABSTRACT_PHYSICAL_PATH_LINK_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Categories</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__CATEGORIES = ABSTRACT_PHYSICAL_PATH_LINK_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Source Physical Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__SOURCE_PHYSICAL_PORT = ABSTRACT_PHYSICAL_PATH_LINK_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Target Physical Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__TARGET_PHYSICAL_PORT = ABSTRACT_PHYSICAL_PATH_LINK_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Realized Physical Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__REALIZED_PHYSICAL_LINKS = ABSTRACT_PHYSICAL_PATH_LINK_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Realizing Physical Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK__REALIZING_PHYSICAL_LINKS = ABSTRACT_PHYSICAL_PATH_LINK_FEATURE_COUNT + 10;

	/**
   * The number of structural features of the '<em>Physical Link</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_FEATURE_COUNT = ABSTRACT_PHYSICAL_PATH_LINK_FEATURE_COUNT + 11;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkCategoryImpl <em>Physical Link Category</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.PhysicalLinkCategoryImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalLinkCategory()
   * @generated
   */
	int PHYSICAL_LINK_CATEGORY = 25;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY__LINKS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Physical Link Category</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_CATEGORY_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkEndImpl <em>Physical Link End</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.PhysicalLinkEndImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalLinkEnd()
   * @generated
   */
	int PHYSICAL_LINK_END = 26;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__OWNED_EXTENSIONS = ABSTRACT_PHYSICAL_LINK_END__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__ID = ABSTRACT_PHYSICAL_LINK_END__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__SID = ABSTRACT_PHYSICAL_LINK_END__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__CONSTRAINTS = ABSTRACT_PHYSICAL_LINK_END__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__OWNED_CONSTRAINTS = ABSTRACT_PHYSICAL_LINK_END__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__OWNED_MIGRATED_ELEMENTS = ABSTRACT_PHYSICAL_LINK_END__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__INCOMING_TRACES = ABSTRACT_PHYSICAL_LINK_END__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__OUTGOING_TRACES = ABSTRACT_PHYSICAL_LINK_END__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__VISIBLE_IN_DOC = ABSTRACT_PHYSICAL_LINK_END__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__VISIBLE_IN_LM = ABSTRACT_PHYSICAL_LINK_END__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__SUMMARY = ABSTRACT_PHYSICAL_LINK_END__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__DESCRIPTION = ABSTRACT_PHYSICAL_LINK_END__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__REVIEW = ABSTRACT_PHYSICAL_LINK_END__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__OWNED_PROPERTY_VALUES = ABSTRACT_PHYSICAL_LINK_END__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__OWNED_ENUMERATION_PROPERTY_TYPES = ABSTRACT_PHYSICAL_LINK_END__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__APPLIED_PROPERTY_VALUES = ABSTRACT_PHYSICAL_LINK_END__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__OWNED_PROPERTY_VALUE_GROUPS = ABSTRACT_PHYSICAL_LINK_END__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__APPLIED_PROPERTY_VALUE_GROUPS = ABSTRACT_PHYSICAL_LINK_END__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__STATUS = ABSTRACT_PHYSICAL_LINK_END__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__FEATURES = ABSTRACT_PHYSICAL_LINK_END__FEATURES;

	/**
   * The feature id for the '<em><b>Involved Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__INVOLVED_LINKS = ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS;

	/**
   * The feature id for the '<em><b>Port</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__PORT = ABSTRACT_PHYSICAL_LINK_END_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Part</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END__PART = ABSTRACT_PHYSICAL_LINK_END_FEATURE_COUNT + 1;

	/**
   * The number of structural features of the '<em>Physical Link End</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_END_FEATURE_COUNT = ABSTRACT_PHYSICAL_LINK_END_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkRealizationImpl <em>Physical Link Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.PhysicalLinkRealizationImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalLinkRealization()
   * @generated
   */
	int PHYSICAL_LINK_REALIZATION = 27;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The number of structural features of the '<em>Physical Link Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_LINK_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalPathImpl <em>Physical Path</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.PhysicalPathImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalPath()
   * @generated
   */
	int PHYSICAL_PATH = 28;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__OWNED_EXTENSIONS = CapellacorePackage.NAMED_ELEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__ID = CapellacorePackage.NAMED_ELEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__SID = CapellacorePackage.NAMED_ELEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__OWNED_CONSTRAINTS = CapellacorePackage.NAMED_ELEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.NAMED_ELEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__NAME = CapellacorePackage.NAMED_ELEMENT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__INCOMING_TRACES = CapellacorePackage.NAMED_ELEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__OUTGOING_TRACES = CapellacorePackage.NAMED_ELEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__VISIBLE_IN_DOC = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__VISIBLE_IN_LM = CapellacorePackage.NAMED_ELEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__SUMMARY = CapellacorePackage.NAMED_ELEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__DESCRIPTION = CapellacorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__REVIEW = CapellacorePackage.NAMED_ELEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__OWNED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.NAMED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__APPLIED_PROPERTY_VALUES = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.NAMED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__STATUS = CapellacorePackage.NAMED_ELEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__FEATURES = CapellacorePackage.NAMED_ELEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Allocated Component Exchanges</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__ALLOCATED_COMPONENT_EXCHANGES = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Involving Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__INVOLVING_INVOLVEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Involved Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__INVOLVED_INVOLVEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Involved Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated See {@link org.polarsys.capella.core.data.cs.PhysicalPath#getInvolvedLinks() model documentation} for details.
   * @generated
   * @ordered
   */
	@Deprecated
	int PHYSICAL_PATH__INVOLVED_LINKS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Owned Physical Path Involvements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__OWNED_PHYSICAL_PATH_INVOLVEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>First Physical Path Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__FIRST_PHYSICAL_PATH_INVOLVEMENTS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Physical Path Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__OWNED_PHYSICAL_PATH_REALIZATIONS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Realized Physical Paths</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__REALIZED_PHYSICAL_PATHS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Realizing Physical Paths</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH__REALIZING_PHYSICAL_PATHS = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
   * The number of structural features of the '<em>Physical Path</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_FEATURE_COUNT = CapellacorePackage.NAMED_ELEMENT_FEATURE_COUNT + 10;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalPathInvolvementImpl <em>Physical Path Involvement</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.PhysicalPathInvolvementImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalPathInvolvement()
   * @generated
   */
	int PHYSICAL_PATH_INVOLVEMENT = 29;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__OWNED_EXTENSIONS = CapellacorePackage.INVOLVEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__ID = CapellacorePackage.INVOLVEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__SID = CapellacorePackage.INVOLVEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__CONSTRAINTS = CapellacorePackage.INVOLVEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__OWNED_CONSTRAINTS = CapellacorePackage.INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.INVOLVEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__REALIZED_FLOW = CapellacorePackage.INVOLVEMENT__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__INCOMING_TRACES = CapellacorePackage.INVOLVEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__OUTGOING_TRACES = CapellacorePackage.INVOLVEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__VISIBLE_IN_DOC = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__VISIBLE_IN_LM = CapellacorePackage.INVOLVEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__SUMMARY = CapellacorePackage.INVOLVEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__DESCRIPTION = CapellacorePackage.INVOLVEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__REVIEW = CapellacorePackage.INVOLVEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__OWNED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__APPLIED_PROPERTY_VALUES = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__STATUS = CapellacorePackage.INVOLVEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__FEATURES = CapellacorePackage.INVOLVEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involver</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__INVOLVER = CapellacorePackage.INVOLVEMENT__INVOLVER;

	/**
   * The feature id for the '<em><b>Involved</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__INVOLVED = CapellacorePackage.INVOLVEMENT__INVOLVED;

	/**
   * The feature id for the '<em><b>Next Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__NEXT_INVOLVEMENTS = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Previous Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__PREVIOUS_INVOLVEMENTS = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Involved Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__INVOLVED_ELEMENT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Involved Component</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT__INVOLVED_COMPONENT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 3;

	/**
   * The number of structural features of the '<em>Physical Path Involvement</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_INVOLVEMENT_FEATURE_COUNT = CapellacorePackage.INVOLVEMENT_FEATURE_COUNT + 4;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalPathReferenceImpl <em>Physical Path Reference</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.PhysicalPathReferenceImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalPathReference()
   * @generated
   */
	int PHYSICAL_PATH_REFERENCE = 30;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__OWNED_EXTENSIONS = PHYSICAL_PATH_INVOLVEMENT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__ID = PHYSICAL_PATH_INVOLVEMENT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__SID = PHYSICAL_PATH_INVOLVEMENT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__CONSTRAINTS = PHYSICAL_PATH_INVOLVEMENT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__OWNED_CONSTRAINTS = PHYSICAL_PATH_INVOLVEMENT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__OWNED_MIGRATED_ELEMENTS = PHYSICAL_PATH_INVOLVEMENT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__REALIZED_FLOW = PHYSICAL_PATH_INVOLVEMENT__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__INCOMING_TRACES = PHYSICAL_PATH_INVOLVEMENT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__OUTGOING_TRACES = PHYSICAL_PATH_INVOLVEMENT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__VISIBLE_IN_DOC = PHYSICAL_PATH_INVOLVEMENT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__VISIBLE_IN_LM = PHYSICAL_PATH_INVOLVEMENT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__SUMMARY = PHYSICAL_PATH_INVOLVEMENT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__DESCRIPTION = PHYSICAL_PATH_INVOLVEMENT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__REVIEW = PHYSICAL_PATH_INVOLVEMENT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__OWNED_PROPERTY_VALUES = PHYSICAL_PATH_INVOLVEMENT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__OWNED_ENUMERATION_PROPERTY_TYPES = PHYSICAL_PATH_INVOLVEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__APPLIED_PROPERTY_VALUES = PHYSICAL_PATH_INVOLVEMENT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__OWNED_PROPERTY_VALUE_GROUPS = PHYSICAL_PATH_INVOLVEMENT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__APPLIED_PROPERTY_VALUE_GROUPS = PHYSICAL_PATH_INVOLVEMENT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__STATUS = PHYSICAL_PATH_INVOLVEMENT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__FEATURES = PHYSICAL_PATH_INVOLVEMENT__FEATURES;

	/**
   * The feature id for the '<em><b>Involver</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__INVOLVER = PHYSICAL_PATH_INVOLVEMENT__INVOLVER;

	/**
   * The feature id for the '<em><b>Involved</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__INVOLVED = PHYSICAL_PATH_INVOLVEMENT__INVOLVED;

	/**
   * The feature id for the '<em><b>Next Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__NEXT_INVOLVEMENTS = PHYSICAL_PATH_INVOLVEMENT__NEXT_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Previous Involvements</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__PREVIOUS_INVOLVEMENTS = PHYSICAL_PATH_INVOLVEMENT__PREVIOUS_INVOLVEMENTS;

	/**
   * The feature id for the '<em><b>Involved Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__INVOLVED_ELEMENT = PHYSICAL_PATH_INVOLVEMENT__INVOLVED_ELEMENT;

	/**
   * The feature id for the '<em><b>Involved Component</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__INVOLVED_COMPONENT = PHYSICAL_PATH_INVOLVEMENT__INVOLVED_COMPONENT;

	/**
   * The feature id for the '<em><b>Referenced Physical Path</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE__REFERENCED_PHYSICAL_PATH = PHYSICAL_PATH_INVOLVEMENT_FEATURE_COUNT + 0;

	/**
   * The number of structural features of the '<em>Physical Path Reference</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REFERENCE_FEATURE_COUNT = PHYSICAL_PATH_INVOLVEMENT_FEATURE_COUNT + 1;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalPathRealizationImpl <em>Physical Path Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.PhysicalPathRealizationImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalPathRealization()
   * @generated
   */
	int PHYSICAL_PATH_REALIZATION = 31;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The number of structural features of the '<em>Physical Path Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PATH_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl <em>Physical Port</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalPort()
   * @generated
   */
	int PHYSICAL_PORT = 32;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_EXTENSIONS = InformationPackage.PORT__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__ID = InformationPackage.PORT__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__SID = InformationPackage.PORT__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__CONSTRAINTS = InformationPackage.PORT__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_CONSTRAINTS = InformationPackage.PORT__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_MIGRATED_ELEMENTS = InformationPackage.PORT__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__NAME = InformationPackage.PORT__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__INCOMING_TRACES = InformationPackage.PORT__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OUTGOING_TRACES = InformationPackage.PORT__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__VISIBLE_IN_DOC = InformationPackage.PORT__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__VISIBLE_IN_LM = InformationPackage.PORT__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__SUMMARY = InformationPackage.PORT__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__DESCRIPTION = InformationPackage.PORT__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__REVIEW = InformationPackage.PORT__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_PROPERTY_VALUES = InformationPackage.PORT__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_ENUMERATION_PROPERTY_TYPES = InformationPackage.PORT__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__APPLIED_PROPERTY_VALUES = InformationPackage.PORT__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_PROPERTY_VALUE_GROUPS = InformationPackage.PORT__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__APPLIED_PROPERTY_VALUE_GROUPS = InformationPackage.PORT__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__STATUS = InformationPackage.PORT__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__FEATURES = InformationPackage.PORT__FEATURES;

	/**
   * The feature id for the '<em><b>Incoming Port Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__INCOMING_PORT_REALIZATIONS = InformationPackage.PORT__INCOMING_PORT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Outgoing Port Realizations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OUTGOING_PORT_REALIZATIONS = InformationPackage.PORT__OUTGOING_PORT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Protocols</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_PROTOCOLS = InformationPackage.PORT__OWNED_PROTOCOLS;

	/**
   * The feature id for the '<em><b>Incoming Port Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__INCOMING_PORT_ALLOCATIONS = InformationPackage.PORT__INCOMING_PORT_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Outgoing Port Allocations</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OUTGOING_PORT_ALLOCATIONS = InformationPackage.PORT__OUTGOING_PORT_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__PROVIDED_INTERFACES = InformationPackage.PORT__PROVIDED_INTERFACES;

	/**
   * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__REQUIRED_INTERFACES = InformationPackage.PORT__REQUIRED_INTERFACES;

	/**
   * The feature id for the '<em><b>Owned Port Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_PORT_REALIZATIONS = InformationPackage.PORT__OWNED_PORT_REALIZATIONS;

	/**
   * The feature id for the '<em><b>Owned Port Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_PORT_ALLOCATIONS = InformationPackage.PORT__OWNED_PORT_ALLOCATIONS;

	/**
   * The feature id for the '<em><b>Allocator Configuration Items</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__ALLOCATOR_CONFIGURATION_ITEMS = InformationPackage.PORT_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Incoming Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__INCOMING_INFORMATION_FLOWS = InformationPackage.PORT_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Outgoing Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OUTGOING_INFORMATION_FLOWS = InformationPackage.PORT_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Information Flows</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__INFORMATION_FLOWS = InformationPackage.PORT_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Involved Links</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__INVOLVED_LINKS = InformationPackage.PORT_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__IS_ABSTRACT = InformationPackage.PORT_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Is Static</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__IS_STATIC = InformationPackage.PORT_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__VISIBILITY = InformationPackage.PORT_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Abstract Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__ABSTRACT_TYPE = InformationPackage.PORT_FEATURE_COUNT + 8;

	/**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__TYPE = InformationPackage.PORT_FEATURE_COUNT + 9;

	/**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__ORDERED = InformationPackage.PORT_FEATURE_COUNT + 10;

	/**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__UNIQUE = InformationPackage.PORT_FEATURE_COUNT + 11;

	/**
   * The feature id for the '<em><b>Min Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__MIN_INCLUSIVE = InformationPackage.PORT_FEATURE_COUNT + 12;

	/**
   * The feature id for the '<em><b>Max Inclusive</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__MAX_INCLUSIVE = InformationPackage.PORT_FEATURE_COUNT + 13;

	/**
   * The feature id for the '<em><b>Owned Default Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_DEFAULT_VALUE = InformationPackage.PORT_FEATURE_COUNT + 14;

	/**
   * The feature id for the '<em><b>Owned Min Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_MIN_VALUE = InformationPackage.PORT_FEATURE_COUNT + 15;

	/**
   * The feature id for the '<em><b>Owned Max Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_MAX_VALUE = InformationPackage.PORT_FEATURE_COUNT + 16;

	/**
   * The feature id for the '<em><b>Owned Null Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_NULL_VALUE = InformationPackage.PORT_FEATURE_COUNT + 17;

	/**
   * The feature id for the '<em><b>Owned Min Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_MIN_CARD = InformationPackage.PORT_FEATURE_COUNT + 18;

	/**
   * The feature id for the '<em><b>Owned Min Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_MIN_LENGTH = InformationPackage.PORT_FEATURE_COUNT + 19;

	/**
   * The feature id for the '<em><b>Owned Max Card</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_MAX_CARD = InformationPackage.PORT_FEATURE_COUNT + 20;

	/**
   * The feature id for the '<em><b>Owned Max Length</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_MAX_LENGTH = InformationPackage.PORT_FEATURE_COUNT + 21;

	/**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__FINAL = InformationPackage.PORT_FEATURE_COUNT + 22;

	/**
   * The feature id for the '<em><b>Aggregation Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__AGGREGATION_KIND = InformationPackage.PORT_FEATURE_COUNT + 23;

	/**
   * The feature id for the '<em><b>Is Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__IS_DERIVED = InformationPackage.PORT_FEATURE_COUNT + 24;

	/**
   * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__IS_READ_ONLY = InformationPackage.PORT_FEATURE_COUNT + 25;

	/**
   * The feature id for the '<em><b>Is Part Of Key</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__IS_PART_OF_KEY = InformationPackage.PORT_FEATURE_COUNT + 26;

	/**
   * The feature id for the '<em><b>Association</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__ASSOCIATION = InformationPackage.PORT_FEATURE_COUNT + 27;

	/**
   * The feature id for the '<em><b>Owned Component Port Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS = InformationPackage.PORT_FEATURE_COUNT + 28;

	/**
   * The feature id for the '<em><b>Owned Physical Port Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS = InformationPackage.PORT_FEATURE_COUNT + 29;

	/**
   * The feature id for the '<em><b>Allocated Component Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS = InformationPackage.PORT_FEATURE_COUNT + 30;

	/**
   * The feature id for the '<em><b>Realized Physical Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS = InformationPackage.PORT_FEATURE_COUNT + 31;

	/**
   * The feature id for the '<em><b>Realizing Physical Ports</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS = InformationPackage.PORT_FEATURE_COUNT + 32;

	/**
   * The number of structural features of the '<em>Physical Port</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_FEATURE_COUNT = InformationPackage.PORT_FEATURE_COUNT + 33;

	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortRealizationImpl <em>Physical Port Realization</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.PhysicalPortRealizationImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalPortRealization()
   * @generated
   */
	int PHYSICAL_PORT_REALIZATION = 33;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__OWNED_EXTENSIONS = CapellacorePackage.ALLOCATION__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__ID = CapellacorePackage.ALLOCATION__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__SID = CapellacorePackage.ALLOCATION__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__CONSTRAINTS = CapellacorePackage.ALLOCATION__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__OWNED_CONSTRAINTS = CapellacorePackage.ALLOCATION__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.ALLOCATION__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Realized Flow</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__REALIZED_FLOW = CapellacorePackage.ALLOCATION__REALIZED_FLOW;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__INCOMING_TRACES = CapellacorePackage.ALLOCATION__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__OUTGOING_TRACES = CapellacorePackage.ALLOCATION__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__VISIBLE_IN_DOC = CapellacorePackage.ALLOCATION__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__VISIBLE_IN_LM = CapellacorePackage.ALLOCATION__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__SUMMARY = CapellacorePackage.ALLOCATION__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__DESCRIPTION = CapellacorePackage.ALLOCATION__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__REVIEW = CapellacorePackage.ALLOCATION__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__OWNED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.ALLOCATION__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__APPLIED_PROPERTY_VALUES = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.ALLOCATION__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__STATUS = CapellacorePackage.ALLOCATION__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__FEATURES = CapellacorePackage.ALLOCATION__FEATURES;

	/**
   * The feature id for the '<em><b>Target Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__TARGET_ELEMENT = CapellacorePackage.ALLOCATION__TARGET_ELEMENT;

	/**
   * The feature id for the '<em><b>Source Element</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION__SOURCE_ELEMENT = CapellacorePackage.ALLOCATION__SOURCE_ELEMENT;

	/**
   * The number of structural features of the '<em>Physical Port Realization</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PHYSICAL_PORT_REALIZATION_FEATURE_COUNT = CapellacorePackage.ALLOCATION_FEATURE_COUNT + 0;


	/**
   * The meta object id for the '{@link org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl <em>Component Pkg</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl
   * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getComponentPkg()
   * @generated
   */
	int COMPONENT_PKG = 34;

	/**
   * The feature id for the '<em><b>Owned Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_EXTENSIONS = CapellacorePackage.STRUCTURE__OWNED_EXTENSIONS;

	/**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__ID = CapellacorePackage.STRUCTURE__ID;

	/**
   * The feature id for the '<em><b>Sid</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__SID = CapellacorePackage.STRUCTURE__SID;

	/**
   * The feature id for the '<em><b>Constraints</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__CONSTRAINTS = CapellacorePackage.STRUCTURE__CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_CONSTRAINTS = CapellacorePackage.STRUCTURE__OWNED_CONSTRAINTS;

	/**
   * The feature id for the '<em><b>Owned Migrated Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_MIGRATED_ELEMENTS = CapellacorePackage.STRUCTURE__OWNED_MIGRATED_ELEMENTS;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__NAME = CapellacorePackage.STRUCTURE__NAME;

	/**
   * The feature id for the '<em><b>Incoming Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__INCOMING_TRACES = CapellacorePackage.STRUCTURE__INCOMING_TRACES;

	/**
   * The feature id for the '<em><b>Outgoing Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OUTGOING_TRACES = CapellacorePackage.STRUCTURE__OUTGOING_TRACES;

	/**
   * The feature id for the '<em><b>Visible In Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__VISIBLE_IN_DOC = CapellacorePackage.STRUCTURE__VISIBLE_IN_DOC;

	/**
   * The feature id for the '<em><b>Visible In LM</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__VISIBLE_IN_LM = CapellacorePackage.STRUCTURE__VISIBLE_IN_LM;

	/**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__SUMMARY = CapellacorePackage.STRUCTURE__SUMMARY;

	/**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__DESCRIPTION = CapellacorePackage.STRUCTURE__DESCRIPTION;

	/**
   * The feature id for the '<em><b>Review</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__REVIEW = CapellacorePackage.STRUCTURE__REVIEW;

	/**
   * The feature id for the '<em><b>Owned Property Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Enumeration Property Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_ENUMERATION_PROPERTY_TYPES = CapellacorePackage.STRUCTURE__OWNED_ENUMERATION_PROPERTY_TYPES;

	/**
   * The feature id for the '<em><b>Applied Property Values</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__APPLIED_PROPERTY_VALUES = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUES;

	/**
   * The feature id for the '<em><b>Owned Property Value Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Applied Property Value Groups</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__APPLIED_PROPERTY_VALUE_GROUPS = CapellacorePackage.STRUCTURE__APPLIED_PROPERTY_VALUE_GROUPS;

	/**
   * The feature id for the '<em><b>Status</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__STATUS = CapellacorePackage.STRUCTURE__STATUS;

	/**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__FEATURES = CapellacorePackage.STRUCTURE__FEATURES;

	/**
   * The feature id for the '<em><b>Owned Traces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_TRACES = CapellacorePackage.STRUCTURE__OWNED_TRACES;

	/**
   * The feature id for the '<em><b>Contained Generic Traces</b></em>' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__CONTAINED_GENERIC_TRACES = CapellacorePackage.STRUCTURE__CONTAINED_GENERIC_TRACES;

	/**
   * The feature id for the '<em><b>Naming Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__NAMING_RULES = CapellacorePackage.STRUCTURE__NAMING_RULES;

	/**
   * The feature id for the '<em><b>Owned Property Value Pkgs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_PROPERTY_VALUE_PKGS = CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;

	/**
   * The feature id for the '<em><b>Owned Parts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_PARTS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Owned Component Exchanges</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Owned Functional Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Owned Functional Allocations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 4;

	/**
   * The feature id for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 5;

	/**
   * The feature id for the '<em><b>Owned Physical Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_PHYSICAL_LINKS = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 6;

	/**
   * The feature id for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_PHYSICAL_LINK_CATEGORIES = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 7;

	/**
   * The feature id for the '<em><b>Owned State Machines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG__OWNED_STATE_MACHINES = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 8;

	/**
   * The number of structural features of the '<em>Component Pkg</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int COMPONENT_PKG_FEATURE_COUNT = CapellacorePackage.STRUCTURE_FEATURE_COUNT + 9;


	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.BlockArchitecturePkg <em>Block Architecture Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Block Architecture Pkg</em>'.
   * @see org.polarsys.capella.core.data.cs.BlockArchitecturePkg
   * @generated
   */
	EClass getBlockArchitecturePkg();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.BlockArchitecture <em>Block Architecture</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Block Architecture</em>'.
   * @see org.polarsys.capella.core.data.cs.BlockArchitecture
   * @generated
   */
	EClass getBlockArchitecture();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getOwnedAbstractCapabilityPkg <em>Owned Abstract Capability Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Abstract Capability Pkg</em>'.
   * @see org.polarsys.capella.core.data.cs.BlockArchitecture#getOwnedAbstractCapabilityPkg()
   * @see #getBlockArchitecture()
   * @generated
   */
	EReference getBlockArchitecture_OwnedAbstractCapabilityPkg();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getOwnedInterfacePkg <em>Owned Interface Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Interface Pkg</em>'.
   * @see org.polarsys.capella.core.data.cs.BlockArchitecture#getOwnedInterfacePkg()
   * @see #getBlockArchitecture()
   * @generated
   */
	EReference getBlockArchitecture_OwnedInterfacePkg();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getOwnedDataPkg <em>Owned Data Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Data Pkg</em>'.
   * @see org.polarsys.capella.core.data.cs.BlockArchitecture#getOwnedDataPkg()
   * @see #getBlockArchitecture()
   * @generated
   */
	EReference getBlockArchitecture_OwnedDataPkg();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getProvisionedArchitectureAllocations <em>Provisioned Architecture Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Provisioned Architecture Allocations</em>'.
   * @see org.polarsys.capella.core.data.cs.BlockArchitecture#getProvisionedArchitectureAllocations()
   * @see #getBlockArchitecture()
   * @generated
   */
	EReference getBlockArchitecture_ProvisionedArchitectureAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getProvisioningArchitectureAllocations <em>Provisioning Architecture Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Provisioning Architecture Allocations</em>'.
   * @see org.polarsys.capella.core.data.cs.BlockArchitecture#getProvisioningArchitectureAllocations()
   * @see #getBlockArchitecture()
   * @generated
   */
	EReference getBlockArchitecture_ProvisioningArchitectureAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getAllocatedArchitectures <em>Allocated Architectures</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated Architectures</em>'.
   * @see org.polarsys.capella.core.data.cs.BlockArchitecture#getAllocatedArchitectures()
   * @see #getBlockArchitecture()
   * @generated
   */
	EReference getBlockArchitecture_AllocatedArchitectures();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getAllocatingArchitectures <em>Allocating Architectures</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocating Architectures</em>'.
   * @see org.polarsys.capella.core.data.cs.BlockArchitecture#getAllocatingArchitectures()
   * @see #getBlockArchitecture()
   * @generated
   */
	EReference getBlockArchitecture_AllocatingArchitectures();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getSystem <em>System</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>System</em>'.
   * @see org.polarsys.capella.core.data.cs.BlockArchitecture#getSystem()
   * @see #getBlockArchitecture()
   * @generated
   */
	EReference getBlockArchitecture_System();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.Block <em>Block</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Block</em>'.
   * @see org.polarsys.capella.core.data.cs.Block
   * @generated
   */
	EClass getBlock();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.cs.Block#getOwnedAbstractCapabilityPkg <em>Owned Abstract Capability Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Abstract Capability Pkg</em>'.
   * @see org.polarsys.capella.core.data.cs.Block#getOwnedAbstractCapabilityPkg()
   * @see #getBlock()
   * @generated
   */
	EReference getBlock_OwnedAbstractCapabilityPkg();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.cs.Block#getOwnedInterfacePkg <em>Owned Interface Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Interface Pkg</em>'.
   * @see org.polarsys.capella.core.data.cs.Block#getOwnedInterfacePkg()
   * @see #getBlock()
   * @generated
   */
	EReference getBlock_OwnedInterfacePkg();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.cs.Block#getOwnedDataPkg <em>Owned Data Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Data Pkg</em>'.
   * @see org.polarsys.capella.core.data.cs.Block#getOwnedDataPkg()
   * @see #getBlock()
   * @generated
   */
	EReference getBlock_OwnedDataPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.Block#getOwnedStateMachines <em>Owned State Machines</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned State Machines</em>'.
   * @see org.polarsys.capella.core.data.cs.Block#getOwnedStateMachines()
   * @see #getBlock()
   * @generated
   */
	EReference getBlock_OwnedStateMachines();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.ComponentArchitecture <em>Component Architecture</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Architecture</em>'.
   * @see org.polarsys.capella.core.data.cs.ComponentArchitecture
   * @generated
   */
	EClass getComponentArchitecture();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.Component <em>Component</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component</em>'.
   * @see org.polarsys.capella.core.data.cs.Component
   * @generated
   */
	EClass getComponent();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.cs.Component#isActor <em>Actor</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Actor</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#isActor()
   * @see #getComponent()
   * @generated
   */
	EAttribute getComponent_Actor();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.cs.Component#isHuman <em>Human</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Human</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#isHuman()
   * @see #getComponent()
   * @generated
   */
	EAttribute getComponent_Human();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.Component#getOwnedInterfaceUses <em>Owned Interface Uses</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Interface Uses</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getOwnedInterfaceUses()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_OwnedInterfaceUses();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Component#getUsedInterfaceLinks <em>Used Interface Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Used Interface Links</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getUsedInterfaceLinks()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_UsedInterfaceLinks();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Component#getUsedInterfaces <em>Used Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Used Interfaces</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getUsedInterfaces()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_UsedInterfaces();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.Component#getOwnedInterfaceImplementations <em>Owned Interface Implementations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Interface Implementations</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getOwnedInterfaceImplementations()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_OwnedInterfaceImplementations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Component#getImplementedInterfaceLinks <em>Implemented Interface Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Implemented Interface Links</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getImplementedInterfaceLinks()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_ImplementedInterfaceLinks();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Component#getImplementedInterfaces <em>Implemented Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Implemented Interfaces</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getImplementedInterfaces()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_ImplementedInterfaces();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.Component#getOwnedComponentRealizations <em>Owned Component Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Realizations</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getOwnedComponentRealizations()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_OwnedComponentRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Component#getRealizedComponents <em>Realized Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Components</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getRealizedComponents()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_RealizedComponents();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Component#getRealizingComponents <em>Realizing Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Components</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getRealizingComponents()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_RealizingComponents();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Component#getProvidedInterfaces <em>Provided Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Provided Interfaces</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getProvidedInterfaces()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_ProvidedInterfaces();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Component#getRequiredInterfaces <em>Required Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Required Interfaces</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getRequiredInterfaces()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_RequiredInterfaces();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Component#getContainedComponentPorts <em>Contained Component Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Component Ports</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getContainedComponentPorts()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_ContainedComponentPorts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Component#getContainedParts <em>Contained Parts</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Parts</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getContainedParts()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_ContainedParts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Component#getContainedPhysicalPorts <em>Contained Physical Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contained Physical Ports</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getContainedPhysicalPorts()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_ContainedPhysicalPorts();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.Component#getOwnedPhysicalPath <em>Owned Physical Path</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Physical Path</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getOwnedPhysicalPath()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_OwnedPhysicalPath();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.Component#getOwnedPhysicalLinks <em>Owned Physical Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Physical Links</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getOwnedPhysicalLinks()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_OwnedPhysicalLinks();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.Component#getOwnedPhysicalLinkCategories <em>Owned Physical Link Categories</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Physical Link Categories</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getOwnedPhysicalLinkCategories()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_OwnedPhysicalLinkCategories();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Component#getRepresentingParts <em>Representing Parts</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Representing Parts</em>'.
   * @see org.polarsys.capella.core.data.cs.Component#getRepresentingParts()
   * @see #getComponent()
   * @generated
   */
	EReference getComponent_RepresentingParts();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.Part <em>Part</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Part</em>'.
   * @see org.polarsys.capella.core.data.cs.Part
   * @generated
   */
	EClass getPart();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Part#getProvidedInterfaces <em>Provided Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Provided Interfaces</em>'.
   * @see org.polarsys.capella.core.data.cs.Part#getProvidedInterfaces()
   * @see #getPart()
   * @generated
   */
	EReference getPart_ProvidedInterfaces();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Part#getRequiredInterfaces <em>Required Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Required Interfaces</em>'.
   * @see org.polarsys.capella.core.data.cs.Part#getRequiredInterfaces()
   * @see #getPart()
   * @generated
   */
	EReference getPart_RequiredInterfaces();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.Part#getOwnedDeploymentLinks <em>Owned Deployment Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Deployment Links</em>'.
   * @see org.polarsys.capella.core.data.cs.Part#getOwnedDeploymentLinks()
   * @see #getPart()
   * @generated
   */
	EReference getPart_OwnedDeploymentLinks();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Part#getDeployedParts <em>Deployed Parts</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Deployed Parts</em>'.
   * @see org.polarsys.capella.core.data.cs.Part#getDeployedParts()
   * @see #getPart()
   * @generated
   */
	EReference getPart_DeployedParts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Part#getDeployingParts <em>Deploying Parts</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Deploying Parts</em>'.
   * @see org.polarsys.capella.core.data.cs.Part#getDeployingParts()
   * @see #getPart()
   * @generated
   */
	EReference getPart_DeployingParts();

	/**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.core.data.cs.Part#getOwnedAbstractType <em>Owned Abstract Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Owned Abstract Type</em>'.
   * @see org.polarsys.capella.core.data.cs.Part#getOwnedAbstractType()
   * @see #getPart()
   * @generated
   */
	EReference getPart_OwnedAbstractType();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.ArchitectureAllocation <em>Architecture Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Architecture Allocation</em>'.
   * @see org.polarsys.capella.core.data.cs.ArchitectureAllocation
   * @generated
   */
	EClass getArchitectureAllocation();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.ArchitectureAllocation#getAllocatedArchitecture <em>Allocated Architecture</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocated Architecture</em>'.
   * @see org.polarsys.capella.core.data.cs.ArchitectureAllocation#getAllocatedArchitecture()
   * @see #getArchitectureAllocation()
   * @generated
   */
	EReference getArchitectureAllocation_AllocatedArchitecture();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.ArchitectureAllocation#getAllocatingArchitecture <em>Allocating Architecture</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocating Architecture</em>'.
   * @see org.polarsys.capella.core.data.cs.ArchitectureAllocation#getAllocatingArchitecture()
   * @see #getArchitectureAllocation()
   * @generated
   */
	EReference getArchitectureAllocation_AllocatingArchitecture();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.ComponentRealization <em>Component Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Realization</em>'.
   * @see org.polarsys.capella.core.data.cs.ComponentRealization
   * @generated
   */
	EClass getComponentRealization();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.ComponentRealization#getRealizedComponent <em>Realized Component</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realized Component</em>'.
   * @see org.polarsys.capella.core.data.cs.ComponentRealization#getRealizedComponent()
   * @see #getComponentRealization()
   * @generated
   */
	EReference getComponentRealization_RealizedComponent();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.ComponentRealization#getRealizingComponent <em>Realizing Component</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Realizing Component</em>'.
   * @see org.polarsys.capella.core.data.cs.ComponentRealization#getRealizingComponent()
   * @see #getComponentRealization()
   * @generated
   */
	EReference getComponentRealization_RealizingComponent();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.InterfacePkg <em>Interface Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Interface Pkg</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfacePkg
   * @generated
   */
	EClass getInterfacePkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.InterfacePkg#getOwnedInterfaces <em>Owned Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Interfaces</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfacePkg#getOwnedInterfaces()
   * @see #getInterfacePkg()
   * @generated
   */
	EReference getInterfacePkg_OwnedInterfaces();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.InterfacePkg#getOwnedInterfacePkgs <em>Owned Interface Pkgs</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Interface Pkgs</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfacePkg#getOwnedInterfacePkgs()
   * @see #getInterfacePkg()
   * @generated
   */
	EReference getInterfacePkg_OwnedInterfacePkgs();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.Interface <em>Interface</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Interface</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface
   * @generated
   */
	EClass getInterface();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.cs.Interface#getMechanism <em>Mechanism</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Mechanism</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getMechanism()
   * @see #getInterface()
   * @generated
   */
	EAttribute getInterface_Mechanism();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.cs.Interface#isStructural <em>Structural</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Structural</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#isStructural()
   * @see #getInterface()
   * @generated
   */
	EAttribute getInterface_Structural();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getImplementorComponents <em>Implementor Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Implementor Components</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getImplementorComponents()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_ImplementorComponents();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getUserComponents <em>User Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>User Components</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getUserComponents()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_UserComponents();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getInterfaceImplementations <em>Interface Implementations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Interface Implementations</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getInterfaceImplementations()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_InterfaceImplementations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getInterfaceUses <em>Interface Uses</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Interface Uses</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getInterfaceUses()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_InterfaceUses();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getProvisioningInterfaceAllocations <em>Provisioning Interface Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Provisioning Interface Allocations</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getProvisioningInterfaceAllocations()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_ProvisioningInterfaceAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getAllocatingInterfaces <em>Allocating Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocating Interfaces</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getAllocatingInterfaces()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_AllocatingInterfaces();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getAllocatingComponents <em>Allocating Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocating Components</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getAllocatingComponents()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_AllocatingComponents();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getExchangeItems <em>Exchange Items</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Exchange Items</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getExchangeItems()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_ExchangeItems();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.Interface#getOwnedExchangeItemAllocations <em>Owned Exchange Item Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Exchange Item Allocations</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getOwnedExchangeItemAllocations()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_OwnedExchangeItemAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getRequiringComponents <em>Requiring Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Requiring Components</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getRequiringComponents()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_RequiringComponents();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getRequiringComponentPorts <em>Requiring Component Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Requiring Component Ports</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getRequiringComponentPorts()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_RequiringComponentPorts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getProvidingComponents <em>Providing Components</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Providing Components</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getProvidingComponents()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_ProvidingComponents();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getProvidingComponentPorts <em>Providing Component Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Providing Component Ports</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getProvidingComponentPorts()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_ProvidingComponentPorts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getRealizingLogicalInterfaces <em>Realizing Logical Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Logical Interfaces</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getRealizingLogicalInterfaces()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_RealizingLogicalInterfaces();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getRealizedContextInterfaces <em>Realized Context Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Context Interfaces</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getRealizedContextInterfaces()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_RealizedContextInterfaces();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getRealizingPhysicalInterfaces <em>Realizing Physical Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Physical Interfaces</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getRealizingPhysicalInterfaces()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_RealizingPhysicalInterfaces();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.Interface#getRealizedLogicalInterfaces <em>Realized Logical Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Logical Interfaces</em>'.
   * @see org.polarsys.capella.core.data.cs.Interface#getRealizedLogicalInterfaces()
   * @see #getInterface()
   * @generated
   */
	EReference getInterface_RealizedLogicalInterfaces();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.InterfaceImplementation <em>Interface Implementation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Interface Implementation</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfaceImplementation
   * @generated
   */
	EClass getInterfaceImplementation();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.InterfaceImplementation#getInterfaceImplementor <em>Interface Implementor</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Interface Implementor</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfaceImplementation#getInterfaceImplementor()
   * @see #getInterfaceImplementation()
   * @generated
   */
	EReference getInterfaceImplementation_InterfaceImplementor();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.InterfaceImplementation#getImplementedInterface <em>Implemented Interface</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Implemented Interface</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfaceImplementation#getImplementedInterface()
   * @see #getInterfaceImplementation()
   * @generated
   */
	EReference getInterfaceImplementation_ImplementedInterface();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.InterfaceUse <em>Interface Use</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Interface Use</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfaceUse
   * @generated
   */
	EClass getInterfaceUse();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.InterfaceUse#getInterfaceUser <em>Interface User</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Interface User</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfaceUse#getInterfaceUser()
   * @see #getInterfaceUse()
   * @generated
   */
	EReference getInterfaceUse_InterfaceUser();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.InterfaceUse#getUsedInterface <em>Used Interface</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Used Interface</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfaceUse#getUsedInterface()
   * @see #getInterfaceUse()
   * @generated
   */
	EReference getInterfaceUse_UsedInterface();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.ProvidedInterfaceLink <em>Provided Interface Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Provided Interface Link</em>'.
   * @see org.polarsys.capella.core.data.cs.ProvidedInterfaceLink
   * @generated
   */
	EClass getProvidedInterfaceLink();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.ProvidedInterfaceLink#getInterface <em>Interface</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Interface</em>'.
   * @see org.polarsys.capella.core.data.cs.ProvidedInterfaceLink#getInterface()
   * @see #getProvidedInterfaceLink()
   * @generated
   */
	EReference getProvidedInterfaceLink_Interface();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.RequiredInterfaceLink <em>Required Interface Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Required Interface Link</em>'.
   * @see org.polarsys.capella.core.data.cs.RequiredInterfaceLink
   * @generated
   */
	EClass getRequiredInterfaceLink();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.RequiredInterfaceLink#getInterface <em>Interface</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Interface</em>'.
   * @see org.polarsys.capella.core.data.cs.RequiredInterfaceLink#getInterface()
   * @see #getRequiredInterfaceLink()
   * @generated
   */
	EReference getRequiredInterfaceLink_Interface();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.InterfaceAllocation <em>Interface Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Interface Allocation</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfaceAllocation
   * @generated
   */
	EClass getInterfaceAllocation();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.InterfaceAllocation#getAllocatedInterface <em>Allocated Interface</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocated Interface</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfaceAllocation#getAllocatedInterface()
   * @see #getInterfaceAllocation()
   * @generated
   */
	EReference getInterfaceAllocation_AllocatedInterface();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.InterfaceAllocation#getAllocatingInterfaceAllocator <em>Allocating Interface Allocator</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocating Interface Allocator</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfaceAllocation#getAllocatingInterfaceAllocator()
   * @see #getInterfaceAllocation()
   * @generated
   */
	EReference getInterfaceAllocation_AllocatingInterfaceAllocator();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.InterfaceAllocator <em>Interface Allocator</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Interface Allocator</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfaceAllocator
   * @generated
   */
	EClass getInterfaceAllocator();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.InterfaceAllocator#getOwnedInterfaceAllocations <em>Owned Interface Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Interface Allocations</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfaceAllocator#getOwnedInterfaceAllocations()
   * @see #getInterfaceAllocator()
   * @generated
   */
	EReference getInterfaceAllocator_OwnedInterfaceAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.InterfaceAllocator#getProvisionedInterfaceAllocations <em>Provisioned Interface Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Provisioned Interface Allocations</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfaceAllocator#getProvisionedInterfaceAllocations()
   * @see #getInterfaceAllocator()
   * @generated
   */
	EReference getInterfaceAllocator_ProvisionedInterfaceAllocations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.InterfaceAllocator#getAllocatedInterfaces <em>Allocated Interfaces</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated Interfaces</em>'.
   * @see org.polarsys.capella.core.data.cs.InterfaceAllocator#getAllocatedInterfaces()
   * @see #getInterfaceAllocator()
   * @generated
   */
	EReference getInterfaceAllocator_AllocatedInterfaces();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.ExchangeItemAllocation <em>Exchange Item Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exchange Item Allocation</em>'.
   * @see org.polarsys.capella.core.data.cs.ExchangeItemAllocation
   * @generated
   */
	EClass getExchangeItemAllocation();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.cs.ExchangeItemAllocation#getSendProtocol <em>Send Protocol</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Send Protocol</em>'.
   * @see org.polarsys.capella.core.data.cs.ExchangeItemAllocation#getSendProtocol()
   * @see #getExchangeItemAllocation()
   * @generated
   */
	EAttribute getExchangeItemAllocation_SendProtocol();

	/**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.core.data.cs.ExchangeItemAllocation#getReceiveProtocol <em>Receive Protocol</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Receive Protocol</em>'.
   * @see org.polarsys.capella.core.data.cs.ExchangeItemAllocation#getReceiveProtocol()
   * @see #getExchangeItemAllocation()
   * @generated
   */
	EAttribute getExchangeItemAllocation_ReceiveProtocol();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.ExchangeItemAllocation#getAllocatedItem <em>Allocated Item</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocated Item</em>'.
   * @see org.polarsys.capella.core.data.cs.ExchangeItemAllocation#getAllocatedItem()
   * @see #getExchangeItemAllocation()
   * @generated
   */
	EReference getExchangeItemAllocation_AllocatedItem();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.ExchangeItemAllocation#getAllocatingInterface <em>Allocating Interface</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Allocating Interface</em>'.
   * @see org.polarsys.capella.core.data.cs.ExchangeItemAllocation#getAllocatingInterface()
   * @see #getExchangeItemAllocation()
   * @generated
   */
	EReference getExchangeItemAllocation_AllocatingInterface();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.DeployableElement <em>Deployable Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Deployable Element</em>'.
   * @see org.polarsys.capella.core.data.cs.DeployableElement
   * @generated
   */
	EClass getDeployableElement();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.DeployableElement#getDeployingLinks <em>Deploying Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Deploying Links</em>'.
   * @see org.polarsys.capella.core.data.cs.DeployableElement#getDeployingLinks()
   * @see #getDeployableElement()
   * @generated
   */
	EReference getDeployableElement_DeployingLinks();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.DeploymentTarget <em>Deployment Target</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Deployment Target</em>'.
   * @see org.polarsys.capella.core.data.cs.DeploymentTarget
   * @generated
   */
	EClass getDeploymentTarget();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.DeploymentTarget#getDeploymentLinks <em>Deployment Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Deployment Links</em>'.
   * @see org.polarsys.capella.core.data.cs.DeploymentTarget#getDeploymentLinks()
   * @see #getDeploymentTarget()
   * @generated
   */
	EReference getDeploymentTarget_DeploymentLinks();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.AbstractDeploymentLink <em>Abstract Deployment Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Deployment Link</em>'.
   * @see org.polarsys.capella.core.data.cs.AbstractDeploymentLink
   * @generated
   */
	EClass getAbstractDeploymentLink();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.AbstractDeploymentLink#getDeployedElement <em>Deployed Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Deployed Element</em>'.
   * @see org.polarsys.capella.core.data.cs.AbstractDeploymentLink#getDeployedElement()
   * @see #getAbstractDeploymentLink()
   * @generated
   */
	EReference getAbstractDeploymentLink_DeployedElement();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.AbstractDeploymentLink#getLocation <em>Location</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Location</em>'.
   * @see org.polarsys.capella.core.data.cs.AbstractDeploymentLink#getLocation()
   * @see #getAbstractDeploymentLink()
   * @generated
   */
	EReference getAbstractDeploymentLink_Location();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement <em>Abstract Path Involved Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Path Involved Element</em>'.
   * @see org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement
   * @generated
   */
	EClass getAbstractPathInvolvedElement();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact <em>Abstract Physical Artifact</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Physical Artifact</em>'.
   * @see org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact
   * @generated
   */
	EClass getAbstractPhysicalArtifact();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact#getAllocatorConfigurationItems <em>Allocator Configuration Items</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocator Configuration Items</em>'.
   * @see org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact#getAllocatorConfigurationItems()
   * @see #getAbstractPhysicalArtifact()
   * @generated
   */
	EReference getAbstractPhysicalArtifact_AllocatorConfigurationItems();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd <em>Abstract Physical Link End</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Physical Link End</em>'.
   * @see org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd
   * @generated
   */
	EClass getAbstractPhysicalLinkEnd();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd#getInvolvedLinks <em>Involved Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involved Links</em>'.
   * @see org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd#getInvolvedLinks()
   * @see #getAbstractPhysicalLinkEnd()
   * @generated
   */
	EReference getAbstractPhysicalLinkEnd_InvolvedLinks();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.AbstractPhysicalPathLink <em>Abstract Physical Path Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Physical Path Link</em>'.
   * @see org.polarsys.capella.core.data.cs.AbstractPhysicalPathLink
   * @generated
   */
	EClass getAbstractPhysicalPathLink();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.PhysicalLink <em>Physical Link</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Physical Link</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLink
   * @generated
   */
	EClass getPhysicalLink();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.PhysicalLink#getLinkEnds <em>Link Ends</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Link Ends</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLink#getLinkEnds()
   * @see #getPhysicalLink()
   * @generated
   */
	EReference getPhysicalLink_LinkEnds();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.PhysicalLink#getOwnedComponentExchangeFunctionalExchangeAllocations <em>Owned Component Exchange Functional Exchange Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Exchange Functional Exchange Allocations</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLink#getOwnedComponentExchangeFunctionalExchangeAllocations()
   * @see #getPhysicalLink()
   * @generated
   */
	EReference getPhysicalLink_OwnedComponentExchangeFunctionalExchangeAllocations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.PhysicalLink#getOwnedPhysicalLinkEnds <em>Owned Physical Link Ends</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Physical Link Ends</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLink#getOwnedPhysicalLinkEnds()
   * @see #getPhysicalLink()
   * @generated
   */
	EReference getPhysicalLink_OwnedPhysicalLinkEnds();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.PhysicalLink#getOwnedPhysicalLinkRealizations <em>Owned Physical Link Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Physical Link Realizations</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLink#getOwnedPhysicalLinkRealizations()
   * @see #getPhysicalLink()
   * @generated
   */
	EReference getPhysicalLink_OwnedPhysicalLinkRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.PhysicalLink#getCategories <em>Categories</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Categories</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLink#getCategories()
   * @see #getPhysicalLink()
   * @generated
   */
	EReference getPhysicalLink_Categories();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.PhysicalLink#getSourcePhysicalPort <em>Source Physical Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source Physical Port</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLink#getSourcePhysicalPort()
   * @see #getPhysicalLink()
   * @generated
   */
	EReference getPhysicalLink_SourcePhysicalPort();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.PhysicalLink#getTargetPhysicalPort <em>Target Physical Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target Physical Port</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLink#getTargetPhysicalPort()
   * @see #getPhysicalLink()
   * @generated
   */
	EReference getPhysicalLink_TargetPhysicalPort();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.PhysicalLink#getRealizedPhysicalLinks <em>Realized Physical Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Physical Links</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLink#getRealizedPhysicalLinks()
   * @see #getPhysicalLink()
   * @generated
   */
	EReference getPhysicalLink_RealizedPhysicalLinks();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.PhysicalLink#getRealizingPhysicalLinks <em>Realizing Physical Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Physical Links</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLink#getRealizingPhysicalLinks()
   * @see #getPhysicalLink()
   * @generated
   */
	EReference getPhysicalLink_RealizingPhysicalLinks();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.PhysicalLinkCategory <em>Physical Link Category</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Physical Link Category</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLinkCategory
   * @generated
   */
	EClass getPhysicalLinkCategory();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.PhysicalLinkCategory#getLinks <em>Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Links</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLinkCategory#getLinks()
   * @see #getPhysicalLinkCategory()
   * @generated
   */
	EReference getPhysicalLinkCategory_Links();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.PhysicalLinkEnd <em>Physical Link End</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Physical Link End</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLinkEnd
   * @generated
   */
	EClass getPhysicalLinkEnd();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.PhysicalLinkEnd#getPort <em>Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Port</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLinkEnd#getPort()
   * @see #getPhysicalLinkEnd()
   * @generated
   */
	EReference getPhysicalLinkEnd_Port();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.PhysicalLinkEnd#getPart <em>Part</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Part</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLinkEnd#getPart()
   * @see #getPhysicalLinkEnd()
   * @generated
   */
	EReference getPhysicalLinkEnd_Part();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.PhysicalLinkRealization <em>Physical Link Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Physical Link Realization</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalLinkRealization
   * @generated
   */
	EClass getPhysicalLinkRealization();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.PhysicalPath <em>Physical Path</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Physical Path</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPath
   * @generated
   */
	EClass getPhysicalPath();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.PhysicalPath#getInvolvedLinks <em>Involved Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Involved Links</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPath#getInvolvedLinks()
   * @see #getPhysicalPath()
   * @deprecated See {@link org.polarsys.capella.core.data.cs.PhysicalPath#getInvolvedLinks() model documentation} for details.
   * @generated
   */
	@Deprecated
	EReference getPhysicalPath_InvolvedLinks();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.PhysicalPath#getOwnedPhysicalPathInvolvements <em>Owned Physical Path Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Physical Path Involvements</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPath#getOwnedPhysicalPathInvolvements()
   * @see #getPhysicalPath()
   * @generated
   */
	EReference getPhysicalPath_OwnedPhysicalPathInvolvements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.PhysicalPath#getFirstPhysicalPathInvolvements <em>First Physical Path Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>First Physical Path Involvements</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPath#getFirstPhysicalPathInvolvements()
   * @see #getPhysicalPath()
   * @generated
   */
	EReference getPhysicalPath_FirstPhysicalPathInvolvements();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.PhysicalPath#getOwnedPhysicalPathRealizations <em>Owned Physical Path Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Physical Path Realizations</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPath#getOwnedPhysicalPathRealizations()
   * @see #getPhysicalPath()
   * @generated
   */
	EReference getPhysicalPath_OwnedPhysicalPathRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.PhysicalPath#getRealizedPhysicalPaths <em>Realized Physical Paths</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Physical Paths</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPath#getRealizedPhysicalPaths()
   * @see #getPhysicalPath()
   * @generated
   */
	EReference getPhysicalPath_RealizedPhysicalPaths();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.PhysicalPath#getRealizingPhysicalPaths <em>Realizing Physical Paths</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Physical Paths</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPath#getRealizingPhysicalPaths()
   * @see #getPhysicalPath()
   * @generated
   */
	EReference getPhysicalPath_RealizingPhysicalPaths();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.PhysicalPathInvolvement <em>Physical Path Involvement</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Physical Path Involvement</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPathInvolvement
   * @generated
   */
	EClass getPhysicalPathInvolvement();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.PhysicalPathInvolvement#getNextInvolvements <em>Next Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Next Involvements</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPathInvolvement#getNextInvolvements()
   * @see #getPhysicalPathInvolvement()
   * @generated
   */
	EReference getPhysicalPathInvolvement_NextInvolvements();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.PhysicalPathInvolvement#getPreviousInvolvements <em>Previous Involvements</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Previous Involvements</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPathInvolvement#getPreviousInvolvements()
   * @see #getPhysicalPathInvolvement()
   * @generated
   */
	EReference getPhysicalPathInvolvement_PreviousInvolvements();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.PhysicalPathInvolvement#getInvolvedElement <em>Involved Element</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Involved Element</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPathInvolvement#getInvolvedElement()
   * @see #getPhysicalPathInvolvement()
   * @generated
   */
	EReference getPhysicalPathInvolvement_InvolvedElement();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.PhysicalPathInvolvement#getInvolvedComponent <em>Involved Component</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Involved Component</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPathInvolvement#getInvolvedComponent()
   * @see #getPhysicalPathInvolvement()
   * @generated
   */
	EReference getPhysicalPathInvolvement_InvolvedComponent();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.PhysicalPathReference <em>Physical Path Reference</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Physical Path Reference</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPathReference
   * @generated
   */
	EClass getPhysicalPathReference();

	/**
   * Returns the meta object for the reference '{@link org.polarsys.capella.core.data.cs.PhysicalPathReference#getReferencedPhysicalPath <em>Referenced Physical Path</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referenced Physical Path</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPathReference#getReferencedPhysicalPath()
   * @see #getPhysicalPathReference()
   * @generated
   */
	EReference getPhysicalPathReference_ReferencedPhysicalPath();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.PhysicalPathRealization <em>Physical Path Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Physical Path Realization</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPathRealization
   * @generated
   */
	EClass getPhysicalPathRealization();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.PhysicalPort <em>Physical Port</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Physical Port</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPort
   * @generated
   */
	EClass getPhysicalPort();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.PhysicalPort#getOwnedComponentPortAllocations <em>Owned Component Port Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Port Allocations</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPort#getOwnedComponentPortAllocations()
   * @see #getPhysicalPort()
   * @generated
   */
	EReference getPhysicalPort_OwnedComponentPortAllocations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.PhysicalPort#getOwnedPhysicalPortRealizations <em>Owned Physical Port Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Physical Port Realizations</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPort#getOwnedPhysicalPortRealizations()
   * @see #getPhysicalPort()
   * @generated
   */
	EReference getPhysicalPort_OwnedPhysicalPortRealizations();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.PhysicalPort#getAllocatedComponentPorts <em>Allocated Component Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Allocated Component Ports</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPort#getAllocatedComponentPorts()
   * @see #getPhysicalPort()
   * @generated
   */
	EReference getPhysicalPort_AllocatedComponentPorts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.PhysicalPort#getRealizedPhysicalPorts <em>Realized Physical Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realized Physical Ports</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPort#getRealizedPhysicalPorts()
   * @see #getPhysicalPort()
   * @generated
   */
	EReference getPhysicalPort_RealizedPhysicalPorts();

	/**
   * Returns the meta object for the reference list '{@link org.polarsys.capella.core.data.cs.PhysicalPort#getRealizingPhysicalPorts <em>Realizing Physical Ports</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Realizing Physical Ports</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPort#getRealizingPhysicalPorts()
   * @see #getPhysicalPort()
   * @generated
   */
	EReference getPhysicalPort_RealizingPhysicalPorts();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.PhysicalPortRealization <em>Physical Port Realization</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Physical Port Realization</em>'.
   * @see org.polarsys.capella.core.data.cs.PhysicalPortRealization
   * @generated
   */
	EClass getPhysicalPortRealization();

	/**
   * Returns the meta object for class '{@link org.polarsys.capella.core.data.cs.ComponentPkg <em>Component Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Pkg</em>'.
   * @see org.polarsys.capella.core.data.cs.ComponentPkg
   * @generated
   */
	EClass getComponentPkg();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedParts <em>Owned Parts</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Parts</em>'.
   * @see org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedParts()
   * @see #getComponentPkg()
   * @generated
   */
	EReference getComponentPkg_OwnedParts();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedComponentExchanges <em>Owned Component Exchanges</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Exchanges</em>'.
   * @see org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedComponentExchanges()
   * @see #getComponentPkg()
   * @generated
   */
	EReference getComponentPkg_OwnedComponentExchanges();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedComponentExchangeCategories <em>Owned Component Exchange Categories</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Exchange Categories</em>'.
   * @see org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedComponentExchangeCategories()
   * @see #getComponentPkg()
   * @generated
   */
	EReference getComponentPkg_OwnedComponentExchangeCategories();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedFunctionalLinks <em>Owned Functional Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Functional Links</em>'.
   * @see org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedFunctionalLinks()
   * @see #getComponentPkg()
   * @generated
   */
	EReference getComponentPkg_OwnedFunctionalLinks();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedFunctionalAllocations <em>Owned Functional Allocations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Functional Allocations</em>'.
   * @see org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedFunctionalAllocations()
   * @see #getComponentPkg()
   * @generated
   */
	EReference getComponentPkg_OwnedFunctionalAllocations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedComponentExchangeRealizations <em>Owned Component Exchange Realizations</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Component Exchange Realizations</em>'.
   * @see org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedComponentExchangeRealizations()
   * @see #getComponentPkg()
   * @generated
   */
	EReference getComponentPkg_OwnedComponentExchangeRealizations();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedPhysicalLinks <em>Owned Physical Links</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Physical Links</em>'.
   * @see org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedPhysicalLinks()
   * @see #getComponentPkg()
   * @generated
   */
	EReference getComponentPkg_OwnedPhysicalLinks();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedPhysicalLinkCategories <em>Owned Physical Link Categories</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Physical Link Categories</em>'.
   * @see org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedPhysicalLinkCategories()
   * @see #getComponentPkg()
   * @generated
   */
	EReference getComponentPkg_OwnedPhysicalLinkCategories();

	/**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedStateMachines <em>Owned State Machines</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned State Machines</em>'.
   * @see org.polarsys.capella.core.data.cs.ComponentPkg#getOwnedStateMachines()
   * @see #getComponentPkg()
   * @generated
   */
	EReference getComponentPkg_OwnedStateMachines();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	CsFactory getCsFactory();

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
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.BlockArchitecturePkgImpl <em>Block Architecture Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.BlockArchitecturePkgImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getBlockArchitecturePkg()
     * @generated
     */
		EClass BLOCK_ARCHITECTURE_PKG = eINSTANCE.getBlockArchitecturePkg();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.BlockArchitectureImpl <em>Block Architecture</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.BlockArchitectureImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getBlockArchitecture()
     * @generated
     */
		EClass BLOCK_ARCHITECTURE = eINSTANCE.getBlockArchitecture();

		/**
     * The meta object literal for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG = eINSTANCE.getBlockArchitecture_OwnedAbstractCapabilityPkg();

		/**
     * The meta object literal for the '<em><b>Owned Interface Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG = eINSTANCE.getBlockArchitecture_OwnedInterfacePkg();

		/**
     * The meta object literal for the '<em><b>Owned Data Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BLOCK_ARCHITECTURE__OWNED_DATA_PKG = eINSTANCE.getBlockArchitecture_OwnedDataPkg();

		/**
     * The meta object literal for the '<em><b>Provisioned Architecture Allocations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BLOCK_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS = eINSTANCE.getBlockArchitecture_ProvisionedArchitectureAllocations();

		/**
     * The meta object literal for the '<em><b>Provisioning Architecture Allocations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BLOCK_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS = eINSTANCE.getBlockArchitecture_ProvisioningArchitectureAllocations();

		/**
     * The meta object literal for the '<em><b>Allocated Architectures</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BLOCK_ARCHITECTURE__ALLOCATED_ARCHITECTURES = eINSTANCE.getBlockArchitecture_AllocatedArchitectures();

		/**
     * The meta object literal for the '<em><b>Allocating Architectures</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BLOCK_ARCHITECTURE__ALLOCATING_ARCHITECTURES = eINSTANCE.getBlockArchitecture_AllocatingArchitectures();

		/**
     * The meta object literal for the '<em><b>System</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BLOCK_ARCHITECTURE__SYSTEM = eINSTANCE.getBlockArchitecture_System();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.BlockImpl <em>Block</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.BlockImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getBlock()
     * @generated
     */
		EClass BLOCK = eINSTANCE.getBlock();

		/**
     * The meta object literal for the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG = eINSTANCE.getBlock_OwnedAbstractCapabilityPkg();

		/**
     * The meta object literal for the '<em><b>Owned Interface Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BLOCK__OWNED_INTERFACE_PKG = eINSTANCE.getBlock_OwnedInterfacePkg();

		/**
     * The meta object literal for the '<em><b>Owned Data Pkg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BLOCK__OWNED_DATA_PKG = eINSTANCE.getBlock_OwnedDataPkg();

		/**
     * The meta object literal for the '<em><b>Owned State Machines</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference BLOCK__OWNED_STATE_MACHINES = eINSTANCE.getBlock_OwnedStateMachines();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.ComponentArchitectureImpl <em>Component Architecture</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.ComponentArchitectureImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getComponentArchitecture()
     * @generated
     */
		EClass COMPONENT_ARCHITECTURE = eINSTANCE.getComponentArchitecture();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.ComponentImpl <em>Component</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.ComponentImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getComponent()
     * @generated
     */
		EClass COMPONENT = eINSTANCE.getComponent();

		/**
     * The meta object literal for the '<em><b>Actor</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute COMPONENT__ACTOR = eINSTANCE.getComponent_Actor();

		/**
     * The meta object literal for the '<em><b>Human</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute COMPONENT__HUMAN = eINSTANCE.getComponent_Human();

		/**
     * The meta object literal for the '<em><b>Owned Interface Uses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__OWNED_INTERFACE_USES = eINSTANCE.getComponent_OwnedInterfaceUses();

		/**
     * The meta object literal for the '<em><b>Used Interface Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__USED_INTERFACE_LINKS = eINSTANCE.getComponent_UsedInterfaceLinks();

		/**
     * The meta object literal for the '<em><b>Used Interfaces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__USED_INTERFACES = eINSTANCE.getComponent_UsedInterfaces();

		/**
     * The meta object literal for the '<em><b>Owned Interface Implementations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS = eINSTANCE.getComponent_OwnedInterfaceImplementations();

		/**
     * The meta object literal for the '<em><b>Implemented Interface Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__IMPLEMENTED_INTERFACE_LINKS = eINSTANCE.getComponent_ImplementedInterfaceLinks();

		/**
     * The meta object literal for the '<em><b>Implemented Interfaces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__IMPLEMENTED_INTERFACES = eINSTANCE.getComponent_ImplementedInterfaces();

		/**
     * The meta object literal for the '<em><b>Owned Component Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__OWNED_COMPONENT_REALIZATIONS = eINSTANCE.getComponent_OwnedComponentRealizations();

		/**
     * The meta object literal for the '<em><b>Realized Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__REALIZED_COMPONENTS = eINSTANCE.getComponent_RealizedComponents();

		/**
     * The meta object literal for the '<em><b>Realizing Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__REALIZING_COMPONENTS = eINSTANCE.getComponent_RealizingComponents();

		/**
     * The meta object literal for the '<em><b>Provided Interfaces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__PROVIDED_INTERFACES = eINSTANCE.getComponent_ProvidedInterfaces();

		/**
     * The meta object literal for the '<em><b>Required Interfaces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__REQUIRED_INTERFACES = eINSTANCE.getComponent_RequiredInterfaces();

		/**
     * The meta object literal for the '<em><b>Contained Component Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__CONTAINED_COMPONENT_PORTS = eINSTANCE.getComponent_ContainedComponentPorts();

		/**
     * The meta object literal for the '<em><b>Contained Parts</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__CONTAINED_PARTS = eINSTANCE.getComponent_ContainedParts();

		/**
     * The meta object literal for the '<em><b>Contained Physical Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__CONTAINED_PHYSICAL_PORTS = eINSTANCE.getComponent_ContainedPhysicalPorts();

		/**
     * The meta object literal for the '<em><b>Owned Physical Path</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__OWNED_PHYSICAL_PATH = eINSTANCE.getComponent_OwnedPhysicalPath();

		/**
     * The meta object literal for the '<em><b>Owned Physical Links</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__OWNED_PHYSICAL_LINKS = eINSTANCE.getComponent_OwnedPhysicalLinks();

		/**
     * The meta object literal for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES = eINSTANCE.getComponent_OwnedPhysicalLinkCategories();

		/**
     * The meta object literal for the '<em><b>Representing Parts</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT__REPRESENTING_PARTS = eINSTANCE.getComponent_RepresentingParts();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.PartImpl <em>Part</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.PartImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPart()
     * @generated
     */
		EClass PART = eINSTANCE.getPart();

		/**
     * The meta object literal for the '<em><b>Provided Interfaces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PART__PROVIDED_INTERFACES = eINSTANCE.getPart_ProvidedInterfaces();

		/**
     * The meta object literal for the '<em><b>Required Interfaces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PART__REQUIRED_INTERFACES = eINSTANCE.getPart_RequiredInterfaces();

		/**
     * The meta object literal for the '<em><b>Owned Deployment Links</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PART__OWNED_DEPLOYMENT_LINKS = eINSTANCE.getPart_OwnedDeploymentLinks();

		/**
     * The meta object literal for the '<em><b>Deployed Parts</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PART__DEPLOYED_PARTS = eINSTANCE.getPart_DeployedParts();

		/**
     * The meta object literal for the '<em><b>Deploying Parts</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PART__DEPLOYING_PARTS = eINSTANCE.getPart_DeployingParts();

		/**
     * The meta object literal for the '<em><b>Owned Abstract Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PART__OWNED_ABSTRACT_TYPE = eINSTANCE.getPart_OwnedAbstractType();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.ArchitectureAllocationImpl <em>Architecture Allocation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.ArchitectureAllocationImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getArchitectureAllocation()
     * @generated
     */
		EClass ARCHITECTURE_ALLOCATION = eINSTANCE.getArchitectureAllocation();

		/**
     * The meta object literal for the '<em><b>Allocated Architecture</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE = eINSTANCE.getArchitectureAllocation_AllocatedArchitecture();

		/**
     * The meta object literal for the '<em><b>Allocating Architecture</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE = eINSTANCE.getArchitectureAllocation_AllocatingArchitecture();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.ComponentRealizationImpl <em>Component Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.ComponentRealizationImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getComponentRealization()
     * @generated
     */
		EClass COMPONENT_REALIZATION = eINSTANCE.getComponentRealization();

		/**
     * The meta object literal for the '<em><b>Realized Component</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_REALIZATION__REALIZED_COMPONENT = eINSTANCE.getComponentRealization_RealizedComponent();

		/**
     * The meta object literal for the '<em><b>Realizing Component</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_REALIZATION__REALIZING_COMPONENT = eINSTANCE.getComponentRealization_RealizingComponent();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.InterfacePkgImpl <em>Interface Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.InterfacePkgImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getInterfacePkg()
     * @generated
     */
		EClass INTERFACE_PKG = eINSTANCE.getInterfacePkg();

		/**
     * The meta object literal for the '<em><b>Owned Interfaces</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE_PKG__OWNED_INTERFACES = eINSTANCE.getInterfacePkg_OwnedInterfaces();

		/**
     * The meta object literal for the '<em><b>Owned Interface Pkgs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE_PKG__OWNED_INTERFACE_PKGS = eINSTANCE.getInterfacePkg_OwnedInterfacePkgs();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl <em>Interface</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.InterfaceImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getInterface()
     * @generated
     */
		EClass INTERFACE = eINSTANCE.getInterface();

		/**
     * The meta object literal for the '<em><b>Mechanism</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute INTERFACE__MECHANISM = eINSTANCE.getInterface_Mechanism();

		/**
     * The meta object literal for the '<em><b>Structural</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute INTERFACE__STRUCTURAL = eINSTANCE.getInterface_Structural();

		/**
     * The meta object literal for the '<em><b>Implementor Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__IMPLEMENTOR_COMPONENTS = eINSTANCE.getInterface_ImplementorComponents();

		/**
     * The meta object literal for the '<em><b>User Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__USER_COMPONENTS = eINSTANCE.getInterface_UserComponents();

		/**
     * The meta object literal for the '<em><b>Interface Implementations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__INTERFACE_IMPLEMENTATIONS = eINSTANCE.getInterface_InterfaceImplementations();

		/**
     * The meta object literal for the '<em><b>Interface Uses</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__INTERFACE_USES = eINSTANCE.getInterface_InterfaceUses();

		/**
     * The meta object literal for the '<em><b>Provisioning Interface Allocations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__PROVISIONING_INTERFACE_ALLOCATIONS = eINSTANCE.getInterface_ProvisioningInterfaceAllocations();

		/**
     * The meta object literal for the '<em><b>Allocating Interfaces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__ALLOCATING_INTERFACES = eINSTANCE.getInterface_AllocatingInterfaces();

		/**
     * The meta object literal for the '<em><b>Allocating Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__ALLOCATING_COMPONENTS = eINSTANCE.getInterface_AllocatingComponents();

		/**
     * The meta object literal for the '<em><b>Exchange Items</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__EXCHANGE_ITEMS = eINSTANCE.getInterface_ExchangeItems();

		/**
     * The meta object literal for the '<em><b>Owned Exchange Item Allocations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__OWNED_EXCHANGE_ITEM_ALLOCATIONS = eINSTANCE.getInterface_OwnedExchangeItemAllocations();

		/**
     * The meta object literal for the '<em><b>Requiring Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__REQUIRING_COMPONENTS = eINSTANCE.getInterface_RequiringComponents();

		/**
     * The meta object literal for the '<em><b>Requiring Component Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__REQUIRING_COMPONENT_PORTS = eINSTANCE.getInterface_RequiringComponentPorts();

		/**
     * The meta object literal for the '<em><b>Providing Components</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__PROVIDING_COMPONENTS = eINSTANCE.getInterface_ProvidingComponents();

		/**
     * The meta object literal for the '<em><b>Providing Component Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__PROVIDING_COMPONENT_PORTS = eINSTANCE.getInterface_ProvidingComponentPorts();

		/**
     * The meta object literal for the '<em><b>Realizing Logical Interfaces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__REALIZING_LOGICAL_INTERFACES = eINSTANCE.getInterface_RealizingLogicalInterfaces();

		/**
     * The meta object literal for the '<em><b>Realized Context Interfaces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__REALIZED_CONTEXT_INTERFACES = eINSTANCE.getInterface_RealizedContextInterfaces();

		/**
     * The meta object literal for the '<em><b>Realizing Physical Interfaces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__REALIZING_PHYSICAL_INTERFACES = eINSTANCE.getInterface_RealizingPhysicalInterfaces();

		/**
     * The meta object literal for the '<em><b>Realized Logical Interfaces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE__REALIZED_LOGICAL_INTERFACES = eINSTANCE.getInterface_RealizedLogicalInterfaces();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.InterfaceImplementationImpl <em>Interface Implementation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.InterfaceImplementationImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getInterfaceImplementation()
     * @generated
     */
		EClass INTERFACE_IMPLEMENTATION = eINSTANCE.getInterfaceImplementation();

		/**
     * The meta object literal for the '<em><b>Interface Implementor</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE_IMPLEMENTATION__INTERFACE_IMPLEMENTOR = eINSTANCE.getInterfaceImplementation_InterfaceImplementor();

		/**
     * The meta object literal for the '<em><b>Implemented Interface</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE = eINSTANCE.getInterfaceImplementation_ImplementedInterface();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.InterfaceUseImpl <em>Interface Use</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.InterfaceUseImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getInterfaceUse()
     * @generated
     */
		EClass INTERFACE_USE = eINSTANCE.getInterfaceUse();

		/**
     * The meta object literal for the '<em><b>Interface User</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE_USE__INTERFACE_USER = eINSTANCE.getInterfaceUse_InterfaceUser();

		/**
     * The meta object literal for the '<em><b>Used Interface</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE_USE__USED_INTERFACE = eINSTANCE.getInterfaceUse_UsedInterface();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.ProvidedInterfaceLinkImpl <em>Provided Interface Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.ProvidedInterfaceLinkImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getProvidedInterfaceLink()
     * @generated
     */
		EClass PROVIDED_INTERFACE_LINK = eINSTANCE.getProvidedInterfaceLink();

		/**
     * The meta object literal for the '<em><b>Interface</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PROVIDED_INTERFACE_LINK__INTERFACE = eINSTANCE.getProvidedInterfaceLink_Interface();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.RequiredInterfaceLinkImpl <em>Required Interface Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.RequiredInterfaceLinkImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getRequiredInterfaceLink()
     * @generated
     */
		EClass REQUIRED_INTERFACE_LINK = eINSTANCE.getRequiredInterfaceLink();

		/**
     * The meta object literal for the '<em><b>Interface</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference REQUIRED_INTERFACE_LINK__INTERFACE = eINSTANCE.getRequiredInterfaceLink_Interface();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.InterfaceAllocationImpl <em>Interface Allocation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.InterfaceAllocationImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getInterfaceAllocation()
     * @generated
     */
		EClass INTERFACE_ALLOCATION = eINSTANCE.getInterfaceAllocation();

		/**
     * The meta object literal for the '<em><b>Allocated Interface</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE_ALLOCATION__ALLOCATED_INTERFACE = eINSTANCE.getInterfaceAllocation_AllocatedInterface();

		/**
     * The meta object literal for the '<em><b>Allocating Interface Allocator</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE_ALLOCATION__ALLOCATING_INTERFACE_ALLOCATOR = eINSTANCE.getInterfaceAllocation_AllocatingInterfaceAllocator();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.InterfaceAllocator <em>Interface Allocator</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.InterfaceAllocator
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getInterfaceAllocator()
     * @generated
     */
		EClass INTERFACE_ALLOCATOR = eINSTANCE.getInterfaceAllocator();

		/**
     * The meta object literal for the '<em><b>Owned Interface Allocations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE_ALLOCATOR__OWNED_INTERFACE_ALLOCATIONS = eINSTANCE.getInterfaceAllocator_OwnedInterfaceAllocations();

		/**
     * The meta object literal for the '<em><b>Provisioned Interface Allocations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS = eINSTANCE.getInterfaceAllocator_ProvisionedInterfaceAllocations();

		/**
     * The meta object literal for the '<em><b>Allocated Interfaces</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES = eINSTANCE.getInterfaceAllocator_AllocatedInterfaces();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.ExchangeItemAllocationImpl <em>Exchange Item Allocation</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.ExchangeItemAllocationImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getExchangeItemAllocation()
     * @generated
     */
		EClass EXCHANGE_ITEM_ALLOCATION = eINSTANCE.getExchangeItemAllocation();

		/**
     * The meta object literal for the '<em><b>Send Protocol</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute EXCHANGE_ITEM_ALLOCATION__SEND_PROTOCOL = eINSTANCE.getExchangeItemAllocation_SendProtocol();

		/**
     * The meta object literal for the '<em><b>Receive Protocol</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute EXCHANGE_ITEM_ALLOCATION__RECEIVE_PROTOCOL = eINSTANCE.getExchangeItemAllocation_ReceiveProtocol();

		/**
     * The meta object literal for the '<em><b>Allocated Item</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM = eINSTANCE.getExchangeItemAllocation_AllocatedItem();

		/**
     * The meta object literal for the '<em><b>Allocating Interface</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE = eINSTANCE.getExchangeItemAllocation_AllocatingInterface();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.DeployableElementImpl <em>Deployable Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.DeployableElementImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getDeployableElement()
     * @generated
     */
		EClass DEPLOYABLE_ELEMENT = eINSTANCE.getDeployableElement();

		/**
     * The meta object literal for the '<em><b>Deploying Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DEPLOYABLE_ELEMENT__DEPLOYING_LINKS = eINSTANCE.getDeployableElement_DeployingLinks();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.DeploymentTargetImpl <em>Deployment Target</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.DeploymentTargetImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getDeploymentTarget()
     * @generated
     */
		EClass DEPLOYMENT_TARGET = eINSTANCE.getDeploymentTarget();

		/**
     * The meta object literal for the '<em><b>Deployment Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference DEPLOYMENT_TARGET__DEPLOYMENT_LINKS = eINSTANCE.getDeploymentTarget_DeploymentLinks();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.AbstractDeploymentLinkImpl <em>Abstract Deployment Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.AbstractDeploymentLinkImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getAbstractDeploymentLink()
     * @generated
     */
		EClass ABSTRACT_DEPLOYMENT_LINK = eINSTANCE.getAbstractDeploymentLink();

		/**
     * The meta object literal for the '<em><b>Deployed Element</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT = eINSTANCE.getAbstractDeploymentLink_DeployedElement();

		/**
     * The meta object literal for the '<em><b>Location</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_DEPLOYMENT_LINK__LOCATION = eINSTANCE.getAbstractDeploymentLink_Location();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl <em>Abstract Path Involved Element</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getAbstractPathInvolvedElement()
     * @generated
     */
		EClass ABSTRACT_PATH_INVOLVED_ELEMENT = eINSTANCE.getAbstractPathInvolvedElement();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.AbstractPhysicalArtifactImpl <em>Abstract Physical Artifact</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.AbstractPhysicalArtifactImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getAbstractPhysicalArtifact()
     * @generated
     */
		EClass ABSTRACT_PHYSICAL_ARTIFACT = eINSTANCE.getAbstractPhysicalArtifact();

		/**
     * The meta object literal for the '<em><b>Allocator Configuration Items</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS = eINSTANCE.getAbstractPhysicalArtifact_AllocatorConfigurationItems();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.AbstractPhysicalLinkEndImpl <em>Abstract Physical Link End</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.AbstractPhysicalLinkEndImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getAbstractPhysicalLinkEnd()
     * @generated
     */
		EClass ABSTRACT_PHYSICAL_LINK_END = eINSTANCE.getAbstractPhysicalLinkEnd();

		/**
     * The meta object literal for the '<em><b>Involved Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS = eINSTANCE.getAbstractPhysicalLinkEnd_InvolvedLinks();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.AbstractPhysicalPathLinkImpl <em>Abstract Physical Path Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.AbstractPhysicalPathLinkImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getAbstractPhysicalPathLink()
     * @generated
     */
		EClass ABSTRACT_PHYSICAL_PATH_LINK = eINSTANCE.getAbstractPhysicalPathLink();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkImpl <em>Physical Link</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.PhysicalLinkImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalLink()
     * @generated
     */
		EClass PHYSICAL_LINK = eINSTANCE.getPhysicalLink();

		/**
     * The meta object literal for the '<em><b>Link Ends</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_LINK__LINK_ENDS = eINSTANCE.getPhysicalLink_LinkEnds();

		/**
     * The meta object literal for the '<em><b>Owned Component Exchange Functional Exchange Allocations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_LINK__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS = eINSTANCE.getPhysicalLink_OwnedComponentExchangeFunctionalExchangeAllocations();

		/**
     * The meta object literal for the '<em><b>Owned Physical Link Ends</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_LINK__OWNED_PHYSICAL_LINK_ENDS = eINSTANCE.getPhysicalLink_OwnedPhysicalLinkEnds();

		/**
     * The meta object literal for the '<em><b>Owned Physical Link Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS = eINSTANCE.getPhysicalLink_OwnedPhysicalLinkRealizations();

		/**
     * The meta object literal for the '<em><b>Categories</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_LINK__CATEGORIES = eINSTANCE.getPhysicalLink_Categories();

		/**
     * The meta object literal for the '<em><b>Source Physical Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_LINK__SOURCE_PHYSICAL_PORT = eINSTANCE.getPhysicalLink_SourcePhysicalPort();

		/**
     * The meta object literal for the '<em><b>Target Physical Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_LINK__TARGET_PHYSICAL_PORT = eINSTANCE.getPhysicalLink_TargetPhysicalPort();

		/**
     * The meta object literal for the '<em><b>Realized Physical Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_LINK__REALIZED_PHYSICAL_LINKS = eINSTANCE.getPhysicalLink_RealizedPhysicalLinks();

		/**
     * The meta object literal for the '<em><b>Realizing Physical Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_LINK__REALIZING_PHYSICAL_LINKS = eINSTANCE.getPhysicalLink_RealizingPhysicalLinks();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkCategoryImpl <em>Physical Link Category</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.PhysicalLinkCategoryImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalLinkCategory()
     * @generated
     */
		EClass PHYSICAL_LINK_CATEGORY = eINSTANCE.getPhysicalLinkCategory();

		/**
     * The meta object literal for the '<em><b>Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_LINK_CATEGORY__LINKS = eINSTANCE.getPhysicalLinkCategory_Links();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkEndImpl <em>Physical Link End</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.PhysicalLinkEndImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalLinkEnd()
     * @generated
     */
		EClass PHYSICAL_LINK_END = eINSTANCE.getPhysicalLinkEnd();

		/**
     * The meta object literal for the '<em><b>Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_LINK_END__PORT = eINSTANCE.getPhysicalLinkEnd_Port();

		/**
     * The meta object literal for the '<em><b>Part</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_LINK_END__PART = eINSTANCE.getPhysicalLinkEnd_Part();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkRealizationImpl <em>Physical Link Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.PhysicalLinkRealizationImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalLinkRealization()
     * @generated
     */
		EClass PHYSICAL_LINK_REALIZATION = eINSTANCE.getPhysicalLinkRealization();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalPathImpl <em>Physical Path</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.PhysicalPathImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalPath()
     * @generated
     */
		EClass PHYSICAL_PATH = eINSTANCE.getPhysicalPath();

		/**
     * The meta object literal for the '<em><b>Involved Links</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @deprecated See {@link org.polarsys.capella.core.data.cs.PhysicalPath#getInvolvedLinks() model documentation} for details.
     * @generated
     */
		@Deprecated
		EReference PHYSICAL_PATH__INVOLVED_LINKS = eINSTANCE.getPhysicalPath_InvolvedLinks();

		/**
     * The meta object literal for the '<em><b>Owned Physical Path Involvements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_PATH__OWNED_PHYSICAL_PATH_INVOLVEMENTS = eINSTANCE.getPhysicalPath_OwnedPhysicalPathInvolvements();

		/**
     * The meta object literal for the '<em><b>First Physical Path Involvements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_PATH__FIRST_PHYSICAL_PATH_INVOLVEMENTS = eINSTANCE.getPhysicalPath_FirstPhysicalPathInvolvements();

		/**
     * The meta object literal for the '<em><b>Owned Physical Path Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_PATH__OWNED_PHYSICAL_PATH_REALIZATIONS = eINSTANCE.getPhysicalPath_OwnedPhysicalPathRealizations();

		/**
     * The meta object literal for the '<em><b>Realized Physical Paths</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_PATH__REALIZED_PHYSICAL_PATHS = eINSTANCE.getPhysicalPath_RealizedPhysicalPaths();

		/**
     * The meta object literal for the '<em><b>Realizing Physical Paths</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_PATH__REALIZING_PHYSICAL_PATHS = eINSTANCE.getPhysicalPath_RealizingPhysicalPaths();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalPathInvolvementImpl <em>Physical Path Involvement</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.PhysicalPathInvolvementImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalPathInvolvement()
     * @generated
     */
		EClass PHYSICAL_PATH_INVOLVEMENT = eINSTANCE.getPhysicalPathInvolvement();

		/**
     * The meta object literal for the '<em><b>Next Involvements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_PATH_INVOLVEMENT__NEXT_INVOLVEMENTS = eINSTANCE.getPhysicalPathInvolvement_NextInvolvements();

		/**
     * The meta object literal for the '<em><b>Previous Involvements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_PATH_INVOLVEMENT__PREVIOUS_INVOLVEMENTS = eINSTANCE.getPhysicalPathInvolvement_PreviousInvolvements();

		/**
     * The meta object literal for the '<em><b>Involved Element</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_PATH_INVOLVEMENT__INVOLVED_ELEMENT = eINSTANCE.getPhysicalPathInvolvement_InvolvedElement();

		/**
     * The meta object literal for the '<em><b>Involved Component</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_PATH_INVOLVEMENT__INVOLVED_COMPONENT = eINSTANCE.getPhysicalPathInvolvement_InvolvedComponent();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalPathReferenceImpl <em>Physical Path Reference</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.PhysicalPathReferenceImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalPathReference()
     * @generated
     */
		EClass PHYSICAL_PATH_REFERENCE = eINSTANCE.getPhysicalPathReference();

		/**
     * The meta object literal for the '<em><b>Referenced Physical Path</b></em>' reference feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_PATH_REFERENCE__REFERENCED_PHYSICAL_PATH = eINSTANCE.getPhysicalPathReference_ReferencedPhysicalPath();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalPathRealizationImpl <em>Physical Path Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.PhysicalPathRealizationImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalPathRealization()
     * @generated
     */
		EClass PHYSICAL_PATH_REALIZATION = eINSTANCE.getPhysicalPathRealization();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl <em>Physical Port</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalPort()
     * @generated
     */
		EClass PHYSICAL_PORT = eINSTANCE.getPhysicalPort();

		/**
     * The meta object literal for the '<em><b>Owned Component Port Allocations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS = eINSTANCE.getPhysicalPort_OwnedComponentPortAllocations();

		/**
     * The meta object literal for the '<em><b>Owned Physical Port Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS = eINSTANCE.getPhysicalPort_OwnedPhysicalPortRealizations();

		/**
     * The meta object literal for the '<em><b>Allocated Component Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS = eINSTANCE.getPhysicalPort_AllocatedComponentPorts();

		/**
     * The meta object literal for the '<em><b>Realized Physical Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS = eINSTANCE.getPhysicalPort_RealizedPhysicalPorts();

		/**
     * The meta object literal for the '<em><b>Realizing Physical Ports</b></em>' reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS = eINSTANCE.getPhysicalPort_RealizingPhysicalPorts();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortRealizationImpl <em>Physical Port Realization</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.PhysicalPortRealizationImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getPhysicalPortRealization()
     * @generated
     */
		EClass PHYSICAL_PORT_REALIZATION = eINSTANCE.getPhysicalPortRealization();

		/**
     * The meta object literal for the '{@link org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl <em>Component Pkg</em>}' class.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @see org.polarsys.capella.core.data.cs.impl.ComponentPkgImpl
     * @see org.polarsys.capella.core.data.cs.impl.CsPackageImpl#getComponentPkg()
     * @generated
     */
		EClass COMPONENT_PKG = eINSTANCE.getComponentPkg();

		/**
     * The meta object literal for the '<em><b>Owned Parts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PKG__OWNED_PARTS = eINSTANCE.getComponentPkg_OwnedParts();

		/**
     * The meta object literal for the '<em><b>Owned Component Exchanges</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES = eINSTANCE.getComponentPkg_OwnedComponentExchanges();

		/**
     * The meta object literal for the '<em><b>Owned Component Exchange Categories</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_CATEGORIES = eINSTANCE.getComponentPkg_OwnedComponentExchangeCategories();

		/**
     * The meta object literal for the '<em><b>Owned Functional Links</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PKG__OWNED_FUNCTIONAL_LINKS = eINSTANCE.getComponentPkg_OwnedFunctionalLinks();

		/**
     * The meta object literal for the '<em><b>Owned Functional Allocations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PKG__OWNED_FUNCTIONAL_ALLOCATIONS = eINSTANCE.getComponentPkg_OwnedFunctionalAllocations();

		/**
     * The meta object literal for the '<em><b>Owned Component Exchange Realizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PKG__OWNED_COMPONENT_EXCHANGE_REALIZATIONS = eINSTANCE.getComponentPkg_OwnedComponentExchangeRealizations();

		/**
     * The meta object literal for the '<em><b>Owned Physical Links</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PKG__OWNED_PHYSICAL_LINKS = eINSTANCE.getComponentPkg_OwnedPhysicalLinks();

		/**
     * The meta object literal for the '<em><b>Owned Physical Link Categories</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PKG__OWNED_PHYSICAL_LINK_CATEGORIES = eINSTANCE.getComponentPkg_OwnedPhysicalLinkCategories();

		/**
     * The meta object literal for the '<em><b>Owned State Machines</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EReference COMPONENT_PKG__OWNED_STATE_MACHINES = eINSTANCE.getComponentPkg_OwnedStateMachines();

	}

} //CsPackage
